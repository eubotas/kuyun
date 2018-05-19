package com.kuyun.eam.admin.util;

import com.kuyun.upms.dao.vo.CompanyInfo;
import com.kuyun.common.util.SpringContextUtil;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsUser;
import javax.servlet.http.HttpServletRequest;

public class SessionUser {

    public static String USER="USER";
    public static String COMPANY="COMPANY";

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

}
