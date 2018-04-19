package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.admin.model.TrainingDoc;
import com.kuyun.eam.admin.repository.TrainingDocRepository;
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
@Api(value = "培训文档管理", description = "培训文档管理")
@RequestMapping("/manage/knowledge/training/doc")
public class TrainingDocController extends BaseController {
    private static Logger _log = LoggerFactory.getLogger(TrainingDocController.class);

    @Resource
    private TrainingDocRepository trainingDocRepository;

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


    @ApiOperation(value = "培训文档首页")
    @RequiresPermissions("eam:trainingDoc:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/knowledge/training/doc/index.jsp";
    }

    @ApiOperation(value = "培训文档列表")
    @RequiresPermissions("eam:trainingDoc:read")
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

        if (StringUtils.isNotEmpty(search)){
            searchQuery = new NativeSearchQueryBuilder()
                    .withIndices(INDEX_NAME)
                    .withTypes(KnowledgeCategory.TRAINING_DOC.getName())
                    .withQuery(multiMatchQuery(search, TITLE, DESCRIPTION, TAG))
                    .withFilter(boolQuery().filter(termQuery("companyId", company.getCompanyId())))
                    .withSort(SortBuilders.fieldSort(CREATE_TIME).order(SortOrder.DESC))
                    .withPageable(new PageRequest(page, size))
                    .build();
        }else {
            searchQuery = new NativeSearchQueryBuilder()
                    .withIndices(INDEX_NAME)
                    .withTypes(KnowledgeCategory.TRAINING_DOC.getName())
                    .withFilter(boolQuery().filter(termQuery("companyId", company.getCompanyId())))
                    .withSort(SortBuilders.fieldSort(CREATE_TIME).order(SortOrder.DESC))
                    .withPageable(new PageRequest(page, size))
                    .build();
        }

        Page<TrainingDoc> pageObj = elasticsearchTemplate.queryForPage(searchQuery, TrainingDoc.class);


        Map<String, Object> result = new HashMap<>();
        result.put("rows", pageObj.getContent());
        result.put("total", pageObj.getTotalElements());
        return result;
    }


    @ApiOperation(value = "新增培训文档")
    @RequiresPermissions("eam:trainingDoc:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(TrainingDoc doc) {
        ComplexResult result = FluentValidator.checkAll()
                .on(doc.getTitle(), new LengthValidator(1, 20, "培训文档标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new EamResult(INVALID_LENGTH, result.getErrors());
        }
        baseModelUtil.addAddtionalValue(doc);
        doc.setId(null);

        tagUtil.handleTag(ActionEnum.CREATE.getName(), null, doc.getTag());
        trainingDocRepository.save(doc);
        return new EamResult(SUCCESS, 1);
    }

    @ApiOperation(value = "删除培训文档")
    @RequiresPermissions("eam:trainingDoc:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        if (StringUtils.isNotEmpty(ids)){
            String[] idArray = ids.split("::");
            for (String id : idArray) {
                if (StringUtils.isBlank(id)) {
                    continue;
                }

                Optional<TrainingDoc> optional = trainingDocRepository.findById(id);
                TrainingDoc doc = optional.orElse(null);
                String tag = doc.getTag() == null ? null : doc.getTag();
                tagUtil.handleTag(ActionEnum.DELETE.getName(), null, tag);

                trainingDocRepository.deleteById(id);
            }
        }
        return new EamResult(SUCCESS, 1);
    }

    @ApiOperation(value = "修改培训文档")
    @RequiresPermissions("eam:trainingDoc:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object update(@PathVariable("id") String id) {
        HashMap<String, Object> map = new HashMap<>(1);
        Optional<TrainingDoc> doc = trainingDocRepository.findById(id);
        if (doc.isPresent()){
            map.put("doc", doc.orElse(null));
        }

        return map;
    }

    @ApiOperation(value = "修改培训文档")
    @RequiresPermissions("eam:trainingDoc:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") String id, TrainingDoc doc) {
        ComplexResult result = FluentValidator.checkAll()
                .on(doc.getTitle(), new LengthValidator(1, 20, "培训文档标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new EamResult(INVALID_LENGTH, result.getErrors());
        }
        baseModelUtil.updateAddtionalValue(doc);


        Optional<TrainingDoc> optional = trainingDocRepository.findById(id);
        TrainingDoc oldDoc = optional.orElse(null);
        String oldTag = oldDoc.getTag() == null ? null : oldDoc.getTag();

        tagUtil.handleTag(ActionEnum.UPDATE.getName(), oldTag, doc.getTag());
        trainingDocRepository.save(doc);
        return new EamResult(SUCCESS, 1);
    }

}