package com.kuyun.common.util;


public class BasePath {
    public static String kuyunDomain=PropertiesFileUtil.getInstance("kuyun-upms-client").get("kuyun.site.domain");
    public static String kuyunUpmsServer=PropertiesFileUtil.getInstance("kuyun-upms-client").get("kuyun.upms.sso.server.url");
    public static String kuyunEamAdmin="http://eam.kuyun.cn:9999";

}
