package com.kuyun.upms.client.util;

import com.kuyun.common.dao.model.BaseEntity;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.dao.model.UpmsUserCompanyExample;
import com.kuyun.upms.rpc.api.UpmsApiService;
import com.kuyun.upms.rpc.api.UpmsUserCompanyService;
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

    @Autowired
    UpmsUserCompanyService upmsUserCompanyService;


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
            UpmsUserCompany userCompany = getUserCompany(user);
            if (userCompany != null){
                record.setCompanyId(userCompany.getCompanyId());
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

    private UpmsUserCompany getUserCompany(UpmsUser user){
        UpmsUserCompanyExample example = new UpmsUserCompanyExample();
        example.createCriteria().andUserIdEqualTo(user.getUserId());
        return upmsUserCompanyService.selectFirstByExample(example);
    }

    public UpmsUserCompany getCurrentUserCompany(){
        UpmsUserCompany result = null;
        UpmsUser user = getCurrentUser();
        if (user != null){
            result = getUserCompany(user);
        }
        return result;

    }
}
