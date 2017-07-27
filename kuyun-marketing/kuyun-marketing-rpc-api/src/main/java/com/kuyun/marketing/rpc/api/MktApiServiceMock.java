package com.kuyun.marketing.rpc.api;

import com.kuyun.marketing.dao.model.MktSms;
import com.kuyun.marketing.vo.MktSmsVo;
import com.kuyun.upms.dao.vo.UpmsUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by user on 2017-07-23.
 */
public class MktApiServiceMock implements MktApiService {

    private static Logger _log = LoggerFactory.getLogger(MktApiServiceMock.class);

    public List<MktSmsVo> getSmsList(MktSmsVo vo) {
        return null;
    }

    public List<UpmsUserVo> getUsers() {
        return null;
    }

    public List<UpmsUserVo> getUsers(List<Integer> userIds) {
        return null;
    }

    public int createSms(MktSms sms, String userIds) {
        return 0;
    }

    public int recreateSms(int id) {
        return 0;
    }

}
