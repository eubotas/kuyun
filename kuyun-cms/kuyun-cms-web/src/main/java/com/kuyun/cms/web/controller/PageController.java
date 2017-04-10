package com.kuyun.cms.web.controller;

import com.kuyun.cms.dao.model.CmsPage;
import com.kuyun.cms.dao.model.CmsPageExample;
import com.kuyun.cms.rpc.api.CmsPageService;
import com.kuyun.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 单页控制器
 * Created by kuyun on 2017/3/26.
 */
@Controller
@RequestMapping(value = "/page")
public class PageController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private CmsPageService cmsPageService;

    @RequestMapping(value = "/{alias}", method = RequestMethod.GET)
    public String index(@PathVariable("alias") String alias, Model model) {
        CmsPageExample cmsPageExample = new CmsPageExample();
        cmsPageExample.createCriteria()
                .andAliasEqualTo(alias);
        CmsPage page = cmsPageService.selectFirstByExampleWithBLOBs(cmsPageExample);
        model.addAttribute("page", page);
        return thymeleaf("/page/index");
    }

}