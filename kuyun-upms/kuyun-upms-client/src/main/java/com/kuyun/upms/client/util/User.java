package com.kuyun.upms.client.util;

import com.kuyun.common.util.SpringContextUtil;
import com.kuyun.upms.dao.model.UpmsUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class User {

    private UpmsUser upmsUser;

    public UpmsUser getUser(HttpServletRequest request){
        Object obj = request.getSession(true).getAttribute("USER");
        UpmsUser user = null;
        if(obj == null) {
            BaseEntityUtil baseEntityUtil = SpringContextUtil.getBean(BaseEntityUtil.class);
            user = baseEntityUtil.getCurrentUser();
            request.getSession().setAttribute("USER", user);
        }else
            user = (UpmsUser)obj;
        return user;
    }


}
