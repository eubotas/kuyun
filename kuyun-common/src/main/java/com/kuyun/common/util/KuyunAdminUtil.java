package com.kuyun.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 启动解压kuyunAdmin-x.x.x.jar到resources目录
 * Created by kuyun on 2016/12/18.
 */
public class KuyunAdminUtil implements InitializingBean, ServletContextAware {

    private static Logger _log = LoggerFactory.getLogger(KuyunAdminUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        _log.info("===== 开始解压kuyun-admin =====");
        String version = PropertiesFileUtil.getInstance().get("kuyun-admin.version");
        _log.info("kuyun-admin.jar 版本: {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/kuyun-admin-" + version + ".jar");
        _log.info("kuyun-admin.jar 包路径: {}", jarPath);
        String resources = servletContext.getRealPath("/") + "/resources/kuyun-admin";
        _log.info("kuyun-admin.jar 解压到: {}", resources);
        JarUtil.decompress(jarPath, resources);
        _log.info("===== 解压kuyun-admin完成 =====");
    }

}
