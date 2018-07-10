package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.admin.model.MaintainKnowledge;
import com.kuyun.eam.admin.repository.MaintainKnowledgeRepository;
import com.kuyun.eam.admin.util.ActionEnum;
import com.kuyun.eam.admin.util.BaseModelUtil;
import com.kuyun.eam.admin.util.KnowledgeCategory;
import com.kuyun.eam.admin.util.TagUtil;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.kuyun.eam.admin.controller.manager.KnowledgeController.*;
import static com.kuyun.eam.common.constant.EamResultConstant.INVALID_LENGTH;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Created by user on 2017-10-27.
 */
@Controller
@Api(value = "保养知识管理", description = "保养知识管理")
@RequestMapping("/manage/knowledge/maintain")
public class MaintainKnowledgeController extends BaseController {
    private static Logger _log = LoggerFactory.getLogger(MaintainKnowledgeController.class);

    @Resource
    private MaintainKnowledgeRepository maintainKnowledgeRepository;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private TagUtil tagUtil;

    @Autowired
    private BaseModelUtil baseModelUtil;

    @Autowired
    private BaseEntityUtil baseEntityUtil;

    @Autowired
    private com.kuyun.fileuploader.rpc.api.FileUploaderService fileUploaderService;


    @ApiOperation(value = "保养知识首页")
    @RequiresPermissions("eam:maintainKnowledge:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.put("uploadServer", fileUploaderService.getServerInfo());
        return "/manage/knowledge/maintain/index.jsp";
    }

    @ApiOperation(value = "保养知识列表")
    @RequiresPermissions("eam:maintainKnowledge:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "page") int page,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, value = "search") String search) {

        _log.info("search content [ {} ]", search);

        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
        _log.info("companyId="+company.getCompanyId());

        SearchQuery searchQuery = null;
        BoolQueryBuilder boolQueryBuilder = boolQuery();
        if (company.getParentId() != null) {
            boolQueryBuilder.should(termQuery("companyId", company.getCompanyId()));
            boolQueryBuilder.should(termQuery("companyId", company.getParentId()));
        } else {
            boolQueryBuilder.filter(termQuery("companyId", company.getCompanyId()));
        }
        if (StringUtils.isNotEmpty(search)){
            searchQuery = new NativeSearchQueryBuilder()
                    .withIndices(INDEX_NAME)
                    .withTypes(KnowledgeCategory.MAINTAIN_KNOWLEDGE.getName())
                    .withQuery(multiMatchQuery(search, TITLE, DESCRIPTION, TAG))
                    .withFilter(boolQueryBuilder)
                    .withSort(SortBuilders.fieldSort(CREATE_TIME).order(SortOrder.DESC))
                    .withPageable(new PageRequest(page, size))
                    .build();
        }else {
            searchQuery = new NativeSearchQueryBuilder()
                    .withIndices(INDEX_NAME)
                    .withTypes(KnowledgeCategory.MAINTAIN_KNOWLEDGE.getName())
                    .withFilter(boolQueryBuilder)
                    .withSort(SortBuilders.fieldSort(CREATE_TIME).order(SortOrder.DESC))
                    .withPageable(new PageRequest(page, size))
                    .build();
        }

        Page<MaintainKnowledge> pageObj = elasticsearchTemplate.queryForPage(searchQuery, MaintainKnowledge.class);

        Map<String, Object> result = new HashMap<>();
        result.put("rows", pageObj.getContent());
        result.put("total", pageObj.getTotalElements());
        return result;
    }


    @ApiOperation(value = "新增保养知识")
    @RequiresPermissions("eam:maintainKnowledge:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(MaintainKnowledge doc) {
        ComplexResult result = FluentValidator.checkAll()
                .on(doc.getTitle(), new LengthValidator(1, 20, "保养知识标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new EamResult(INVALID_LENGTH, result.getErrors());
        }
        baseModelUtil.addAddtionalValue(doc);
        doc.setId(null);
        tagUtil.handleTag(ActionEnum.CREATE.getName(), null, doc.getTag());

        maintainKnowledgeRepository.save(doc);
        return new EamResult(SUCCESS, 1);
    }

    @ApiOperation(value = "删除保养知识")
    @RequiresPermissions("eam:maintainKnowledge:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        if (StringUtils.isNotEmpty(ids)){
            String[] idArray = ids.split("::");
            for (String id : idArray) {
                if (StringUtils.isBlank(id)) {
                    continue;
                }

                Optional<MaintainKnowledge> optional = maintainKnowledgeRepository.findById(id);
                MaintainKnowledge maintainKnowledge = optional.orElse(null);
                String tag = maintainKnowledge.getTag() == null ? null : maintainKnowledge.getTag();
                tagUtil.handleTag(ActionEnum.DELETE.getName(), null, tag);

                maintainKnowledgeRepository.deleteById(id);
            }
        }
        return new EamResult(SUCCESS, 1);
    }

    @ApiOperation(value = "修改保养知识")
    @RequiresPermissions("eam:maintainKnowledge:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object update(@PathVariable("id") String id) {
        HashMap<String, Object> map = new HashMap<>(1);

        Optional<MaintainKnowledge> maintain = maintainKnowledgeRepository.findById(id);
        if (maintain.isPresent()){
            map.put("maintain", maintain.orElse(null));
        }

        return map;
    }

    @ApiOperation(value = "修改保养知识")
    @RequiresPermissions("eam:maintainKnowledge:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") String id, MaintainKnowledge maintain) {
        ComplexResult result = FluentValidator.checkAll()
                .on(maintain.getTitle(), new LengthValidator(1, 20, "保养知识标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new EamResult(INVALID_LENGTH, result.getErrors());
        }
        baseModelUtil.updateAddtionalValue(maintain);

        Optional<MaintainKnowledge> optional = maintainKnowledgeRepository.findById(id);
        MaintainKnowledge oldMaintain = optional.orElse(null);
        String oldTag = oldMaintain.getTag() == null ? null : oldMaintain.getTag();

        tagUtil.handleTag(ActionEnum.UPDATE.getName(), oldTag, maintain.getTag());

        maintain.setCreateTime(oldMaintain.getCreateTime());

        maintainKnowledgeRepository.save(maintain);
        return new EamResult(SUCCESS, 1);
    }

}