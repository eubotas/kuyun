package com.kuyun.marketing.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.kuyun.marketing.dao.model.*;
import com.kuyun.marketing.rpc.api.*;
import com.kuyun.marketing.rpc.mapper.MktApiMapper;
import com.kuyun.marketing.vo.MktSmsVo;
import com.kuyun.upms.dao.model.UpmsCompany;
import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.dao.model.UpmsUserExample;
import com.kuyun.upms.dao.vo.UpmsUserVo;
import com.kuyun.upms.rpc.api.UpmsApiService;
import com.kuyun.upms.rpc.api.UpmsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 4/24/2017.
 */
@Service
@Transactional
public class MktApiServiceImpl implements MktApiService {
    private static Logger _log = LoggerFactory.getLogger(MktApiServiceImpl.class);

    @Autowired
    MktApiMapper mktApiMapper;

    @Autowired
    UpmsUserService upmsUserService;

    @Autowired
    UpmsApiService upmsApiService;

    @Autowired
    MktSmsService mktSmsService;

    @Autowired
    MktSmsUserService mktSmsUserService;

    @Autowired
    MktSmsTemplateService mktSmsTemplateService;

    @Autowired
    MktSmsSettingService mktSmsSettingService;



    @Override
    public List<MktSmsVo> getSmsList(MktSmsVo vo) {
        return mktApiMapper.getSmsList(vo);
    }

    public List<UpmsUserVo> getUsers(UpmsUserExample example){
        List<UpmsUserVo> result = getUpmsUserVos(example);
        return result;
    }

    private List<UpmsUserVo> getUpmsUserVos(UpmsUserExample example) {
        List<UpmsUserVo> result = new ArrayList<>();
        List<UpmsUser> users = upmsUserService.selectByExample(example);
        for(UpmsUser user : users){
            UpmsCompany company = upmsApiService.getUpmsCompany(user.getUserId());
            UpmsUserVo vo = new UpmsUserVo();
            vo.setUserId(user.getUserId());
            vo.setRealname(user.getRealname());
            vo.setPhone(user.getPhone());
            vo.setCompanyName(company.getName());
            result.add(vo);
        }
        return result;
    }

    public List<UpmsUserVo> getUsers(List<Integer> userIds){
        UpmsUserExample example = new UpmsUserExample();
        example.createCriteria().andUserIdIn(userIds);
        List<UpmsUserVo> result = getUpmsUserVos(example);
        return result;
    }

    private UpmsUser getUser(String userId){
        UpmsUserExample example = new UpmsUserExample();
        example.createCriteria().andUserIdEqualTo(Integer.valueOf(userId));
        return upmsUserService.selectFirstByExample(example);
    }

    @Override
    public int createSms(MktSms sms, String argUserIds) {
        String[] userIds = argUserIds.split("::");
        mktSmsService.insertSelective(sms);
        int smsId = sms.getId();
        _log.info("smsId="+smsId);

        JSONArray jsonArray = new JSONArray();

        for(String userId : userIds){
            MktSmsUser smsUser = new MktSmsUser();
            smsUser.setSmsId(smsId);
            smsUser.setUserId(Integer.valueOf(userId));
            mktSmsUserService.insertSelective(smsUser);

            UpmsUser user = getUser(userId);
            if (user != null){
                jsonArray.add(user.getPhone());
            }
        }
        //send sms
        sendSms(sms, jsonArray);
        return 1;
    }

    private void sendSms(MktSms sms, JSONArray mobileArray) {
        MktSmsSetting smsSetting = mktSmsSettingService.selectFirstByExample(new MktSmsSettingExample());
        if(smsSetting != null){
            MktSmsTemplate smsTemplate = mktSmsTemplateService.selectByPrimaryKey(sms.getSmsTemplateId());
            if (smsTemplate != null){
                String appKey = smsSetting.getAppKey();
                String appSecret = smsSetting.getAppSecret();
                String templateId = smsTemplate.getTemplateId();
                String mobiles = mobileArray.toJSONString();
                Date sendTime = sms.getSendTime();

                _log.info("appKey="+appKey);
                _log.info("appSecret="+appSecret);
                _log.info("templateId="+templateId);
                _log.info("mobiles="+mobiles);
                _log.info("sendTime="+sendTime);

                SendSmsUtil sendSmsUtil = new SendSmsUtil();
                sendSmsUtil.setAppKey(appKey);
                sendSmsUtil.setAppSecrt(appSecret);
                sendSmsUtil.setTemplateId(templateId);
                sendSmsUtil.setMobiles(mobiles);
                sendSmsUtil.setSendTime(sendTime);

                sendSmsUtil.sendSMS();

            }
        }
    }

    @Override
    public int recreateSms(int id) {
        MktSms sms = mktSmsService.selectByPrimaryKey(id);
        if (sms != null){
            List<MktSmsUser> users = getSmsUsers(sms);

            recreate(sms, users);

            JSONArray jsonArray = new JSONArray();
            for(MktSmsUser mktSmsUser : users){
                UpmsUser user = getUser(String.valueOf(mktSmsUser.getUserId()));
                if (user != null){
                    jsonArray.add(user.getPhone());
                }
            }
            sendSms(sms, jsonArray);
        }
        return 1;
    }

    private void recreate(MktSms sms, List<MktSmsUser> users){
        MktSms newSms = new MktSms();
        BeanUtils.copyProperties(sms, newSms);
        newSms.setSendTime(new Date());
        newSms.setId(null);
        mktSmsService.insertSelective(newSms);

        for (MktSmsUser user : users){
            MktSmsUser newUser = new MktSmsUser();
            BeanUtils.copyProperties(user, newUser);
            newUser.setSmsId(newSms.getId());
            newUser.setId(null);
            mktSmsUserService.insertSelective(newUser);
        }
    }

    private List<MktSmsUser> getSmsUsers(MktSms sms) {
        MktSmsUserExample smsUserExample = new MktSmsUserExample();
        smsUserExample.createCriteria().andSmsIdEqualTo(sms.getId());
        return mktSmsUserService.selectByExample(smsUserExample);
    }


}
