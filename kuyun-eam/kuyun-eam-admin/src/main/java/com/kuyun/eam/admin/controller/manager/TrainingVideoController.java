package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.admin.model.TrainingVideo;
import com.kuyun.eam.admin.repository.TrainingVideoRepository;
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
@Api(value = "培训视频管理", description = "培训视频管理")
@RequestMapping("/manage/knowledge/training/video")
public class TrainingVideoController extends BaseController {
    private static Logger _log = LoggerFactory.getLogger(TrainingVideoController.class);

    @Resource
    private TrainingVideoRepository trainingVideoRepository;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private BaseEntityUtil baseEntityUtil;

    @Autowired
    private TagUtil tagUtil;

    @Autowired
    private BaseModelUtil baseModelUtil;

    @ApiOperation(value = "培训视频首页")
    @RequiresPermissions("eam:trainingVideo:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/knowledge/training/video/index.jsp";
    }

    @ApiOperation(value = "培训视频列表")
    @RequiresPermissions("eam:trainingVideo:read")
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
                    .withTypes(KnowledgeCategory.TRAINING_VIDEO.getName())
                    .withQuery(multiMatchQuery(search, TITLE, DESCRIPTION, TAG))
                    .withFilter(boolQuery().filter(termQuery("companyId", company.getCompanyId())))
                    .withSort(SortBuilders.fieldSort(CREATE_TIME).order(SortOrder.DESC))
                    .withPageable(new PageRequest(page, size))
                    .build();
        }else {
            searchQuery = new NativeSearchQueryBuilder()
                    .withIndices(INDEX_NAME)
                    .withTypes(KnowledgeCategory.TRAINING_VIDEO.getName())
                    .withFilter(boolQuery().filter(termQuery("companyId", company.getCompanyId())))
                    .withSort(SortBuilders.fieldSort(CREATE_TIME).order(SortOrder.DESC))
                    .withPageable(new PageRequest(page, size))
                    .build();
        }

        Page<TrainingVideo> pageObj = elasticsearchTemplate.queryForPage(searchQuery, TrainingVideo.class);

        Map<String, Object> result = new HashMap<>();
        result.put("rows", pageObj.getContent());
        result.put("total", pageObj.getTotalElements());
        return result;
    }

    @ApiOperation(value = "新增培训视频")
    @RequiresPermissions("eam:trainingVideo:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/knowledge/training/video/create.jsp";
    }

    @ApiOperation(value = "新增培训视频")
    @RequiresPermissions("eam:trainingVideo:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(TrainingVideo video) {
        ComplexResult result = FluentValidator.checkAll()
                .on(video.getTitle(), new LengthValidator(1, 20, "培训视频标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new EamResult(INVALID_LENGTH, result.getErrors());
        }
        baseModelUtil.addAddtionalValue(video);

        tagUtil.handleTag(ActionEnum.CREATE.getName(), null, video.getTag());
        trainingVideoRepository.save(video);
        return new EamResult(SUCCESS, 1);
    }

    @ApiOperation(value = "删除培训视频")
    @RequiresPermissions("eam:trainingVideo:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        if (StringUtils.isNotEmpty(ids)){
            String[] idArray = ids.split("::");
            for (String id : idArray) {
                if (StringUtils.isBlank(id)) {
                    continue;
                }

                Optional<TrainingVideo> optional = trainingVideoRepository.findById(id);
                TrainingVideo video = optional.orElse(null);
                String tag = video.getTag() == null ? null : video.getTag();

                tagUtil.handleTag(ActionEnum.DELETE.getName(), null, tag);
                trainingVideoRepository.deleteById(id);
            }
        }
        return new EamResult(SUCCESS, 1);
    }

    @ApiOperation(value = "修改培训视频")
    @RequiresPermissions("eam:trainingVideo:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, ModelMap modelMap) {

        Optional<TrainingVideo> video = trainingVideoRepository.findById(id);
        if (video.isPresent()){
            modelMap.put("video", video.orElse(null));
        }

        return "/manage/knowledge/training/video/update.jsp";
    }

    @ApiOperation(value = "修改培训视频")
    @RequiresPermissions("eam:trainingVideo:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") String id, TrainingVideo video) {
        ComplexResult result = FluentValidator.checkAll()
                .on(video.getTitle(), new LengthValidator(1, 20, "培训视频标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new EamResult(INVALID_LENGTH, result.getErrors());
        }
        baseModelUtil.updateAddtionalValue(video);

        Optional<TrainingVideo> optional = trainingVideoRepository.findById(id);
        TrainingVideo oldVideo = optional.orElse(null);
        String oldTag = oldVideo.getTag() == null ? null : oldVideo.getTag();

        tagUtil.handleTag(ActionEnum.UPDATE.getName(), oldTag, video.getTag());

        trainingVideoRepository.save(video);
        return new EamResult(SUCCESS, 1);
    }

}
