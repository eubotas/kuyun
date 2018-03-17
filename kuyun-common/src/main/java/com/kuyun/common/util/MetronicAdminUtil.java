package com.kuyun.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 启动解压metronicAdmin-x.x.x.jar到resources目录
 * Created by metronic on 2016/12/18.
 */
public class MetronicAdminUtil implements InitializingBean, ServletContextAware {

    private static Logger _log = LoggerFactory.getLogger(MetronicAdminUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        _log.info("===== 开始解压metronic-admin =====");
        String version = PropertiesFileUtil.getInstance().get("metronic.admin.version");
        _log.info("metronic-admin.jar 版本: {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/metronic-admin-" + version + ".jar");
        _log.info("metronic-admin.jar 包路径: {}", jarPath);
        String resources = servletContext.getRealPath("/") + "/resources/metronic-admin";
        _log.info("metronic-admin.jar 解压到: {}", resources);
        JarUtil.decompress(jarPath, resources);
        _log.info("===== 解压metronic-admin完成 =====");
    }

}
