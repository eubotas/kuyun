package com.kuyun.upms.client.util;

import com.kuyun.common.dao.model.BaseEntity;
import com.kuyun.upms.dao.model.UpmsCompany;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.dao.vo.UpmsCompanyVo;
import com.kuyun.upms.rpc.api.UpmsApiService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public UpmsUserCompany getUserCompany(UpmsUser user){
        return upmsApiService.getUserCompany(user);
    }

    public UpmsUser getCurrentUser(){
        UpmsUser result = null;
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if (!org.springframework.util.StringUtils.isEmpty(username)){
            result = upmsApiService.selectUpmsUserByUsername(username);
        }
        return result;
    }

    public UpmsUserCompany getCurrentUserCompany(){
        UpmsUserCompany result = null;
        UpmsUser user = getCurrentUser();
        if (user != null){
            result = upmsApiService.getUpmsUserCompany(user.getUserId());
        }
        return result;
    }

    public List<Integer> getChildCompanys(Integer cid){
        List<Integer> list = new ArrayList<Integer>();
        UpmsCompanyVo vo =new UpmsCompanyVo();
        vo.setParentId(cid);
        List<UpmsCompany> companies = upmsApiService.selectCompanies(vo);
        for(UpmsCompany co : companies){
            list.add(co.getCompanyId());
        }
        return list;
    }
}
