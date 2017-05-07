package com.kuyun.eam.admin.util;

import com.kuyun.eam.dao.model.BaseEntity;
import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.rpc.api.UpmsApiService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by user on 4/29/2017.
 */
public class EamUtils {

    @Autowired
    UpmsApiService upmsApiService;


    public void addAddtionalValue(BaseEntity record){
        Date currentDate = new Date();
        if (record.getCreateTime() == null){
            record.setCreateTime(currentDate);
        }
        record.setUpdateTime(currentDate);

        UpmsUser user = getCurrentUser();

        if (user != null){
            if (record.getCreateUserId() == null){
                record.setCreateUserId(user.getUserId());
            }
            record.setUpdateUserId(user.getUserId());
        }

        UpmsOrganization organization = upmsApiService.selectParentOrganizationByUserId(user.getUserId());

        if (organization != null){

            record.setOrganizationId(organization.getOrganizationId());
        }

    }

    public void updateAddtionalValue(BaseEntity record){
        Date currentDate = new Date();
        record.setUpdateTime(currentDate);

        UpmsUser user = getCurrentUser();

        if (user != null){
            record.setUpdateUserId(user.getUserId());
        }
    }


    public UpmsUser getCurrentUser(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
        return upmsUser;
    }

    public UpmsOrganization getCurrentUserParentOrignization(){
        UpmsUser user = getCurrentUser();
        return upmsApiService.selectParentOrganizationByUserId(user.getUserId());

    }
}
