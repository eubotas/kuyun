package com.kuyun.mkt.rpc.service;

import com.kuyun.marketing.dao.model.MktSms;
import com.kuyun.marketing.rpc.api.MktSmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试
 * Created by kuyun on 2017/2/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:applicationContext.xml",
        "classpath:META-INF/spring/applicationContext-jdbc.xml",
        "classpath:META-INF/spring/applicationContext-listener.xml"
})
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class MktServiceTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private MktSmsService mktSmsService;


    @Test
    public void list() {
        MktSms sms = new MktSms();
        sms.setSmsTemplateId(1234);
        int id = mktSmsService.insertSelective(sms);
        System.out.println("id = " + id);
    }

}
