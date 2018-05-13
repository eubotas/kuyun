package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.admin.model.EquipmentManual;
import com.kuyun.eam.admin.repository.EquipmentManualRepository;
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
@Api(value = "设备手册管理", description = "设备手册管理")
@RequestMapping("/manage/knowledge/manual")
public class EquipmentManualController extends BaseController {
    private static Logger _log = LoggerFactory.getLogger(EquipmentManualController.class);

    @Resource
    private EquipmentManualRepository equipmentManualRepository;

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


    @ApiOperation(value = "设备手册首页")
    @RequiresPermissions("eam:equipmentManual:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/knowledge/manual/index.jsp";
    }

    @ApiOperation(value = "设备手册列表")
    @RequiresPermissions("eam:equipmentManual:read")
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
            boolQueryBuilder.should(termQuery("companyId", company.getCompanyId()));
        }
        if (StringUtils.isNotEmpty(search)){
            searchQuery = new NativeSearchQueryBuilder()
                    .withIndices(INDEX_NAME)
                    .withTypes(KnowledgeCategory.EQUIPMENT_MANUAL.getName())
                    .withQuery(multiMatchQuery(search, TITLE, DESCRIPTION, TAG))
                    .withFilter(boolQueryBuilder)
                    .withSort(SortBuilders.fieldSort(CREATE_TIME).order(SortOrder.DESC))
                    .withPageable(new PageRequest(page, size))
                    .build();
        }else {
            searchQuery = new NativeSearchQueryBuilder()
                    .withIndices(INDEX_NAME)
                    .withTypes(KnowledgeCategory.EQUIPMENT_MANUAL.getName())
                    .withFilter(boolQueryBuilder)
                    .withSort(SortBuilders.fieldSort(CREATE_TIME).order(SortOrder.DESC))
                    .withPageable(new PageRequest(page, size))
                    .build();
        }

        Page<EquipmentManual> pageObj = elasticsearchTemplate.queryForPage(searchQuery, EquipmentManual.class);

        Map<String, Object> result = new HashMap<>();
        result.put("rows", pageObj.getContent());
        result.put("total", pageObj.getTotalElements());
        return result;
    }

    @ApiOperation(value = "新增设备手册")
    @RequiresPermissions("eam:equipmentManual:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        modelMap.put("uploadServer", fileUploaderService.getServerInfo());
        return "/manage/knowledge/manual/create.jsp";
    }

    @ApiOperation(value = "新增设备手册")
    @RequiresPermissions("eam:equipmentManual:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(EquipmentManual doc) {
        ComplexResult result = FluentValidator.checkAll()
                .on(doc.getTitle(), new LengthValidator(1, 20, "设备手册标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new EamResult(INVALID_LENGTH, result.getErrors());
        }
        baseModelUtil.addAddtionalValue(doc);

        tagUtil.handleTag(ActionEnum.CREATE.getName(), null, doc.getTag());

        equipmentManualRepository.save(doc);
        return new EamResult(SUCCESS, 1);
    }

    @ApiOperation(value = "删除设备手册")
    @RequiresPermissions("eam:equipmentManual:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        if (StringUtils.isNotEmpty(ids)){
            String[] idArray = ids.split("::");
            for (String id : idArray) {
                if (StringUtils.isBlank(id)) {
                    continue;
                }

                Optional<EquipmentManual> optional = equipmentManualRepository.findById(id);
                EquipmentManual doc = optional.orElse(null);
                String tag = doc.getTag() == null ? null : doc.getTag();
                tagUtil.handleTag(ActionEnum.DELETE.getName(), null, tag);

                equipmentManualRepository.deleteById(id);
            }
        }
        return new EamResult(SUCCESS, 1);
    }

    @ApiOperation(value = "修改设备手册")
    @RequiresPermissions("eam:equipmentManual:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, ModelMap modelMap) {

        Optional<EquipmentManual> manual = equipmentManualRepository.findById(id);
        if (manual.isPresent()){
            modelMap.put("manual", manual.orElse(null));
        }

        return "/manage/knowledge/manual/update.jsp";
    }

    @ApiOperation(value = "修改设备手册")
    @RequiresPermissions("eam:equipmentManual:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") String id, EquipmentManual doc) {
        ComplexResult result = FluentValidator.checkAll()
                .on(doc.getTitle(), new LengthValidator(1, 20, "设备手册标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new EamResult(INVALID_LENGTH, result.getErrors());
        }
        baseModelUtil.updateAddtionalValue(doc);

        Optional<EquipmentManual> optional = equipmentManualRepository.findById(id);
        EquipmentManual oldDoc = optional.orElse(null);
        String oldTag = oldDoc.getTag() == null ? null : oldDoc.getTag();

        tagUtil.handleTag(ActionEnum.UPDATE.getName(), oldTag, doc.getTag());

        equipmentManualRepository.save(doc);
        return new EamResult(SUCCESS, 1);
    }

}
