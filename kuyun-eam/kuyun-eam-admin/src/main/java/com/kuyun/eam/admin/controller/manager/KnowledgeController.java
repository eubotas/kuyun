package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.admin.model.BaseModel;
import com.kuyun.eam.admin.model.ResultAggregator;
import com.kuyun.eam.admin.repository.TagRepository;
import com.kuyun.eam.admin.util.BaseModelUtil;
import com.kuyun.eam.admin.util.KnowledgeCategory;
import com.kuyun.eam.admin.util.TagUtil;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.*;

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
    private BaseEntityUtil baseEntityUtil;

    @Autowired
    private TagUtil tagUtil;

    @Autowired
    private BaseModelUtil baseModelUtil;

    public static final String INDEX_NAME = "knowledge";
    public static final String DESCRIPTION = "description";
    public static final String TITLE = "title";
    public static final String CODE = "codes";
    private static final String PRE_TAGS = "<span class='keyword-highlight'>";
    private static final String POST_TAGS = "</span>";
    public static final String TAG = "tag";
    public static final String CREATE_TIME = "createTime";


    @ApiOperation(value = "知识搜索首页")
    @RequiresPermissions("eam:knowledge:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        handleIndexModel(modelMap, new HashMap<>());
        return "/manage/knowledge/index.jsp";
    }

    private void handleIndexModel(ModelMap modelMap, HashMap<String, Object> map) {
        modelMap.put("tags", tagRepository.findAll());
        map.put("tags", tagRepository.findAll());
    }

    @ApiOperation(value = "Tag列表")
    @RequiresPermissions("eam:knowledge:read")
    @RequestMapping(value = "/tag", method = RequestMethod.GET)
    @ResponseBody
    public Object tag( ModelMap modelMap) {
        HashMap map = new HashMap();
        handleIndexModel(modelMap, map);
        return map;
    }

    @ApiOperation(value = "知识搜索列表")
    @RequiresPermissions("eam:knowledge:read")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Object search(
            @RequestParam(required = false, value = "page") Integer page,
            @RequestParam(required = false, value = "size") Integer size,
            @RequestParam(required = false, value = "k") String k,
            @RequestParam(required = false, value = "t") String t,
            @RequestParam(required = false, value = "c") String c, ModelMap modelMap, HttpServletRequest request) {

        _log.info("key [ {} ], tag [ {} ], category [ {} ]", k, t, c);
        HashMap map = new HashMap<String, Object>();
        handelSeachModel(page, size, k, t, c, modelMap, request, map);
        return map;
    }

    @ApiOperation(value = "知识搜索列表")
    @RequiresPermissions("eam:knowledge:read")
    @RequestMapping(value = "/search/index", method = RequestMethod.GET)
    public String searchIndex(
            @RequestParam(required = false, value = "page") Integer page,
            @RequestParam(required = false, value = "size") Integer size,
            @RequestParam(required = false, value = "k") String k,
            @RequestParam(required = false, value = "t") String t,
            @RequestParam(required = false, value = "c") String c, ModelMap modelMap, HttpServletRequest request) {

        _log.info("key [ {} ], tag [ {} ], category [ {} ]", k, t, c);

        handelSeachModel(page, size, k, t, c, modelMap, request, new HashMap<String, Object>());
        return "/manage/knowledge/search.jsp";
    }

    private void handelSeachModel(Integer page, Integer size, String k, String t, String c, ModelMap modelMap, HttpServletRequest request, HashMap<String, Object> map) {
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

        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

        BoolQueryBuilder boolQueryBuilder = boolQuery();
        if (company.getParentId() != null) {
            boolQueryBuilder.should(termQuery("companyId", company.getCompanyId()));
            boolQueryBuilder.should(termQuery("companyId", company.getParentId()));
        } else {
            boolQueryBuilder.filter(termQuery("companyId", company.getCompanyId()));
        }

        if (!StringUtils.isEmpty(k)){
            NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder()
                    .withIndices(INDEX_NAME)
                    .withTypes(types.toArray(new String[types.size()]))
                    .withQuery(multiMatchQuery(k, TITLE, DESCRIPTION, CODE))
                    .withFilter(boolQueryBuilder)
                    // .withFilter(boolQuery().should(termQuery("companyId", company.getParentId())))
//                    .withQuery(termQuery("companyId", company.getCompanyId()))
                    .withHighlightFields(codeField, descriptionField, titleField)
//                    .withHighlightFields(fields.toArray(new HighlightBuilder.Field[fields.size()]))
                    .withSort(SortBuilders.fieldSort(CREATE_TIME).order(SortOrder.DESC));

            if (page != null && size != null){
                queryBuilder.withPageable(new PageRequest(page, size));
            }
            searchQuery = queryBuilder.build();


        }else if (!StringUtils.isEmpty(t)){
            NativeSearchQueryBuilder queryBuilder  = new NativeSearchQueryBuilder()
                    .withIndices(INDEX_NAME)
                    .withTypes(types.toArray(new String[types.size()]))
                    .withQuery(termQuery(TAG, t))
                    .withFilter(boolQueryBuilder)
                    .withSort(SortBuilders.fieldSort(CREATE_TIME).order(SortOrder.DESC));

            if (page != null && size != null){
                queryBuilder.withPageable(new PageRequest(page, size));
            }
            searchQuery = queryBuilder.build();
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

        List<String> tabs = buildTabs(k, t, c, request);
        List rows = pageObj != null ? pageObj.getContent() : null;
        long total = pageObj != null ? pageObj.getTotalElements() : 0;

        modelMap.put("rows", rows);
        modelMap.put("total", total);
        modelMap.put("k", k);
        modelMap.put("c", c);
        modelMap.put("tabs", tabs);

        map.put("rows", rows);
        map.put("total", total);
        map.put("k", k);
        map.put("c", c);
        map.put("tabs", tabs);
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

    @ApiOperation(value = "显示知识内容")
    @RequiresPermissions("eam:knowledge:read")
    @RequestMapping(value = "/{category}/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object show(@PathVariable("category") String category, @PathVariable("id") String id, ModelMap modelMap) {
        HashMap map = new HashMap();
        handleShowModel(category, id, modelMap, map);
        return map;
    }

    private void handleShowModel(String category, String id, ModelMap modelMap, HashMap map) {
        KnowledgeCategory knowledge = KnowledgeCategory.getKnowledge(category);
        if (knowledge != null && StringUtils.isNotEmpty(id)){
            GetQuery query = new GetQuery();
            query.setId(id);
            BaseModel model = (BaseModel) elasticsearchTemplate.queryForObject(query, knowledge.getClazz());
            modelMap.put("model", model);
            modelMap.put("category", knowledge.getName());

            map.put("model", model);
            map.put("category", knowledge.getName());
        }
    }

    @ApiOperation(value = "显示知识内容")
    @RequiresPermissions("eam:knowledge:read")
    @RequestMapping(value = "/{category}/{id}/index", method = RequestMethod.GET)
    public String showIndex(@PathVariable("category") String category, @PathVariable("id") String id, ModelMap modelMap) {
        handleShowModel(category, id, modelMap, new HashMap());
        return "/manage/knowledge/show.jsp";
    }

}
