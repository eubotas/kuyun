package com.kuyun.common.util;


import javax.servlet.http.HttpServletRequest;

public class BasePath {
    private  String siteDomain="122.112.237.243";
    private  String kuyunUpmsServer="http://122.112.237.243:1111";
    public static String kuyunEamAdmin=null;

    private static BasePath instance=null;
    public static BasePath getInstance(){
        if(instance == null) {
            instance = new BasePath();
            PropertiesFileUtil prop=PropertiesFileUtil.getInstance("kuyun-upms-client");
            instance.setSiteDomain(prop.get("kuyun.site.domain"));
            instance.setKuyunUpmsServer(prop.get("kuyun.upms.sso.server.url"));
        }
        return instance;
    }
    public String getSiteDomain() {
        return siteDomain;
    }

    public String getKuyunUpmsServer() {
        return kuyunUpmsServer;
    }

    public String getKuyunEamAdmin(HttpServletRequest request) {
        if(kuyunEamAdmin == null){
            int port = request.getServerPort();
            if (port != 0) {
                kuyunEamAdmin = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            }else {
                kuyunEamAdmin = request.getScheme() + "://" + request.getServerName();
            }
        }
        return kuyunEamAdmin;
    }

    public  void setSiteDomain(String siteDomain) {
        this.siteDomain = siteDomain;
    }

    public  void setKuyunUpmsServer(String kuyunUpmsServer) {
        this.kuyunUpmsServer = kuyunUpmsServer;
    }

}
