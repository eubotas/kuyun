package com.kuyun.eam.admin.util;

import com.kuyun.eam.admin.model.BaseModel;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by user on 2017-10-31.
 */
public class BaseModelUtil {

    @Autowired
    private BaseEntityUtil baseEntityUtil;

    public void addAddtionalValue(BaseModel record){
        if (record.getCreateTime() == null){
            record.setCreateTime(getCurrentDate());
        }
        record.setUpdateTime(getCurrentDate());

        UpmsUser user = baseEntityUtil.getCurrentUser();

        if (user != null){
            if (record.getCreateUserId() == null){
                record.setCreateUserId(user.getUserId());
            }

            record.setUpdateUserId(user.getUserId());
            UpmsUserCompany userCompany = baseEntityUtil.getUserCompany(user);
            if (userCompany != null){
                record.setCompanyId(userCompany.getCompanyId());
            }
        }

    }

    public void updateAddtionalValue(BaseModel record){
        record.setUpdateTime(getCurrentDate());

        UpmsUser user = baseEntityUtil.getCurrentUser();

        if (user != null){
            record.setUpdateUserId(user.getUserId());
        }
    }

    private String getCurrentDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
