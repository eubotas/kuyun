package com.kuyun.marketing.rpc.api;

import com.kuyun.marketing.dao.model.MktSms;
import com.kuyun.marketing.vo.MktSmsVo;
import com.kuyun.upms.dao.vo.UpmsUserVo;

import java.util.List;

/**
 * Created by user on 2017-07-23.
 */
public interface MktApiService {

    public List<MktSmsVo> getSmsList(MktSmsVo vo);

    public List<UpmsUserVo> getUsers();

    public List<UpmsUserVo> getUsers(List<Integer> userIds);

    public int createSms(MktSms sms, String userIds);

    public int recreateSms(int id);
}
