package com.kuyun.upms.client.util;

import com.kuyun.common.dao.model.BaseEntity;
import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.rpc.api.UpmsApiService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Created by user on 4/29/2017.
 */
public class BaseEntityUtil {

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
            UpmsOrganization organization = upmsApiService.selectParentOrganizationByUserId(user.getUserId());

            if (organization != null){
                record.setOrganizationId(organization.getOrganizationId());
            }
        }

        record.setDeleteFlag(Boolean.FALSE);

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
        UpmsUser result = null;
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if (!StringUtils.isEmpty(username)){
            result = upmsApiService.selectUpmsUserByUsername(username);
        }
        return result;
    }

    public UpmsOrganization getCurrentUserParentOrignization(){
        UpmsOrganization result = null;
        UpmsUser user = getCurrentUser();
        if (user != null){
            result = upmsApiService.selectParentOrganizationByUserId(user.getUserId());
        }
        return result;

    }
}
