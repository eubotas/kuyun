package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.admin.model.BaseModel;
import com.kuyun.eam.admin.model.MaintainKnowledge;
import com.kuyun.eam.admin.model.ResultAggregator;
import com.kuyun.eam.admin.repository.MaintainKnowledgeRepository;
import com.kuyun.eam.admin.repository.TagRepository;
import com.kuyun.eam.admin.util.BaseModelUtil;
import com.kuyun.eam.admin.util.KnowledgeCategory;
import com.kuyun.eam.admin.util.TagUtil;
import com.kuyun.eam.common.constant.EamResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.kuyun.eam.common.constant.EamResultConstant.INVALID_LENGTH;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * Created by user on 2017-10-27.
 */
@Controller
@Api(value = "知识搜索管理", description = "知识搜索管理")
@RequestMapping("/manage/knowledge")
public class KnowledgeController extends BaseController {
    private static Logger _log = LoggerFactory.getLogger(KnowledgeController.class);

    @Resource
    private TagRepository tagRepository;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private TagUtil tagUtil;

    @Autowired
    private BaseModelUtil baseModelUtil;

    private static final String INDEX_NAME = "knowledge";
    private static final String DESCRIPTION = "description";
    private static final String TITLE = "title";
    private static final String CODE = "codes";
    private static final String PRE_TAGS = "<span class='keyword-highlight'>";
    private static final String POST_TAGS = "</span>";
    private static final String TAG = "tag";


    @ApiOperation(value = "知识搜索首页")
    @RequiresPermissions("eam:knowledge:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.put("tags", tagRepository.findAll());
        return "/manage/knowledge/index.jsp";
    }

    @ApiOperation(value = "知识搜索列表")
    @RequiresPermissions("eam:knowledge:read")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(
            @RequestParam(required = false, defaultValue = "0", value = "page") int page,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size,
            @RequestParam(required = false, value = "k") String k,
            @RequestParam(required = false, value = "t") String t,
            @RequestParam(required = false, value = "c") String c, ModelMap modelMap, HttpServletRequest request) {

        _log.info("key [ {} ], tag [ {} ], category [ {} ]", k, t, c);

        SearchQuery searchQuery = null;

        List<String> types = new ArrayList<>();
        for(KnowledgeCategory category : KnowledgeCategory.values()){
            types.add(category.getName());
        }
        if (!StringUtils.isEmpty(c)){
            types.clear();
            types.add(c);
        }



//        final List<HighlightBuilder.Field> fields = new HighlightBuilder().field("description")
//                                                                          .field("title")
//                                                                          .field("code")
//                                                                          .fields();
        HighlightBuilder.Field descriptionField = new HighlightBuilder.Field(DESCRIPTION).preTags(PRE_TAGS).postTags(POST_TAGS);
        HighlightBuilder.Field titleField = new HighlightBuilder.Field(TITLE).preTags(PRE_TAGS).postTags(POST_TAGS);
        HighlightBuilder.Field codeField = new HighlightBuilder.Field(CODE).preTags(PRE_TAGS).postTags(POST_TAGS);

        if (!StringUtils.isEmpty(k)){
            searchQuery = new NativeSearchQueryBuilder()
                    .withIndices(INDEX_NAME)
                    .withTypes(types.toArray(new String[types.size()]))
                    .withQuery(multiMatchQuery(k, TITLE, DESCRIPTION, CODE))
                    .withHighlightFields(codeField, descriptionField, titleField)
//                    .withHighlightFields(fields.toArray(new HighlightBuilder.Field[fields.size()]))
                    .withPageable(new PageRequest(page, size))
                    .build();
        }else if (!StringUtils.isEmpty(t)){
            searchQuery = new NativeSearchQueryBuilder()
                    .withIndices(INDEX_NAME)
                    .withTypes(types.toArray(new String[types.size()]))
                    .withQuery(termQuery(TAG, t))
                    .withPageable(new PageRequest(page, size))
                    .build();
        }


        Page<ResultAggregator> pageObj = elasticsearchTemplate.queryForPage(searchQuery, ResultAggregator.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<ResultAggregator> chunk = new ArrayList<>();
                for (SearchHit searchHit : response.getHits()) {
                    if (response.getHits().getHits().length <= 0) {
                        return null;
                    }
                    ResultAggregator result = new ResultAggregator();
                    result.setType(searchHit.getType());
                    result.setId(searchHit.getId());
                    result.setCreateUserId((Integer) searchHit.getSource().get("createUserId"));
                    result.setCreateTime((String) searchHit.getSource().get("createTime"));
                    result.setUpdateUserId((Integer) searchHit.getSource().get("updateUserId"));
                    result.setUpdateTime((String) searchHit.getSource().get("updateTime"));
                    result.setCompanyId((Integer) searchHit.getSource().get("companyId"));
                    result.setTag((String) searchHit.getSource().get("tag"));
                    result.setContent((String) searchHit.getSource().get("content"));
                    result.setPath((String) searchHit.getSource().get("path"));
                    result.setDescription((String) searchHit.getSource().get(DESCRIPTION));

                    if (StringUtils.isNotEmpty((String) searchHit.getSource().get(TITLE))){
                        result.setTitle((String) searchHit.getSource().get(TITLE));

                    }else if (StringUtils.isNotEmpty((String) searchHit.getSource().get(CODE))){
                        result.setTitle((String) searchHit.getSource().get(CODE));

                    }
                    Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();


                    if (highlightFields.get(DESCRIPTION) != null && highlightFields.get(DESCRIPTION).getFragments() != null){
                        result.setHighlightedDescription(highlightFields.get(DESCRIPTION).getFragments()[0].toString());
                    }

                    if (highlightFields.get(TITLE) != null && highlightFields.get(TITLE).getFragments() != null){
                        result.setHighlightedTitle(highlightFields.get(TITLE).getFragments()[0].toString());
                    }else if (highlightFields.get(CODE) != null && highlightFields.get(CODE).getFragments() != null){
                        result.setHighlightedTitle(highlightFields.get(CODE).getFragments()[0].toString());
                    }

                    chunk.add(result);
                }
                if (chunk.size() > 0) {
                    return new AggregatedPageImpl<>((List<T>) chunk);
                }
                return null;
            }
        });

//        Map<String, Object> result = new HashMap<>();
        modelMap.put("rows", pageObj != null ? pageObj.getContent() : null);
        modelMap.put("total", pageObj != null ? pageObj.getTotalElements() : 0);
        modelMap.put("k", k);
        modelMap.put("c", c);
        modelMap.put("tabs", buildTabs(k, t, c, request));
        return "/manage/knowledge/search.jsp";
    }

    private List<String> buildTabs(String k, String t, String c, HttpServletRequest request){
        List<String> result = new ArrayList<>();

        StringBuilder allTab = new StringBuilder();
        allTab.append("<a href='").append(request.getContextPath());
        allTab.append("/manage/knowledge/search?k=").append(k).append("&t=").append(t);
        if (StringUtils.isEmpty(c)){
            allTab.append("' class='on'>全部</a>");
        }else {
            allTab.append("' >全部</a>");
        }
        result.add(allTab.toString());

        for(KnowledgeCategory category : KnowledgeCategory.values()){

            StringBuilder tab = new StringBuilder();
            tab.append("<a href='").append(request.getContextPath());
            tab.append("/manage/knowledge/search?k=").append(k).append("&t=").append(t).append("&c=").append(category.getName()).append("'");

            if (category.getName().equals(c)){
                tab.append(" class='on'>").append(category.getDescription()).append("</a>");
            }else {
                tab.append(" >").append(category.getDescription()).append("</a>");
            }
            result.add(tab.toString());

        }

        return result;
    }

}
