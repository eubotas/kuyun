package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.admin.model.TrainingDoc;
import com.kuyun.eam.admin.repository.TrainingDocRepository;
import com.kuyun.eam.admin.util.BaseModelUtil;
import com.kuyun.eam.admin.util.TagUtil;
import com.kuyun.eam.common.constant.EamResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.kuyun.eam.common.constant.EamResultConstant.INVALID_LENGTH;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * Created by user on 2017-10-27.
 */
@Controller
@Api(value = "培训文档管理", description = "培训文档管理")
@RequestMapping("/manage/training/doc")
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

    @ApiOperation(value = "培训文档首页")
    @RequiresPermissions("eam:trainingDoc:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/training/doc/index.jsp";
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

        Pageable pageable = new PageRequest(page, size);
        Page<TrainingDoc> pageObj = new PageImpl<TrainingDoc>(new ArrayList<TrainingDoc>());

        if (StringUtils.isEmpty(search)){
            pageObj = trainingDocRepository.findAll(pageable);
        }else {
            pageObj = trainingDocRepository.findByTitleContainingOrTagContainingOrDescriptionContainingOrderByCreateTimeDesc(search, search, search, pageable);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("rows", pageObj.getContent());
        result.put("total", pageObj.getTotalElements());
        return result;
    }

    @ApiOperation(value = "新增培训文档")
    @RequiresPermissions("eam:trainingDoc:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/training/doc/create.jsp";
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

        tagUtil.handleTag(doc.getTag());
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
                trainingDocRepository.deleteById(id);
            }
        }
        return new EamResult(SUCCESS, 1);
    }

    @ApiOperation(value = "修改培训文档")
    @RequiresPermissions("eam:trainingDoc:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, ModelMap modelMap) {

        Optional<TrainingDoc> doc = trainingDocRepository.findById(id);
        if (doc.isPresent()){
            modelMap.put("doc", doc.orElse(null));
        }

        return "/manage/training/doc/update.jsp";
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
        tagUtil.handleTag(doc.getTag());
        trainingDocRepository.save(doc);
        return new EamResult(SUCCESS, 1);
    }

}
