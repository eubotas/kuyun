package com.kuyun.upms.client.util;

import com.kuyun.upms.dao.vo.CompanyInfo;
import com.kuyun.common.util.SpringContextUtil;
import com.kuyun.upms.dao.model.UpmsUser;

import javax.servlet.http.HttpServletRequest;

public class SessionUser {
    public static String USER="USER";
    public static String COMPANY="COMPANY";
    private UpmsUser upmsUser;

    public UpmsUser getUser(HttpServletRequest request){
        Object obj = request.getSession(true).getAttribute(USER);
        UpmsUser user = null;
        if(obj == null) {
            BaseEntityUtil baseEntityUtil = SpringContextUtil.getBean(BaseEntityUtil.class);
            user = baseEntityUtil.getCurrentUser();
            request.getSession().setAttribute(USER, user);
        }else
            user = (UpmsUser)obj;
        return user;
    }

    public CompanyInfo getCompany(HttpServletRequest request){
        return (CompanyInfo)request.getSession(true).getAttribute(COMPANY);
    }

    public String getCompanyLogo(HttpServletRequest request){
        CompanyInfo comp= getCompany(request);
        if(comp != null){
            return comp.getCompanyLogo();
        }
        return null;
    }
}
