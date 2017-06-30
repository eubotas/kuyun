package com.kuyun.upms.server;

import com.kuyun.upms.dao.model.UpmsSystemExample;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.rpc.api.UpmsSystemService;
import com.kuyun.upms.rpc.api.UpmsUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * 单元测试
 * Created by kuyun on 2017/2/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:applicationContext.xml",
        "classpath:applicationContext-dubbo-consumer.xml"
})
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UpmsServiceTest {

    @Autowired
    private UpmsSystemService upmsSystemService;

    @Autowired
    private UpmsUserService upmsUserService;

//    @Test
//    public void index() {
//        int count = upmsSystemService.countByExample(new UpmsSystemExample());
//        System.out.println(count);
//    }


    @Test
    public void insert(){
        UpmsUser user = new UpmsUser();
        user.setUsername("abc123");
        user.setPassword("abc123");
        int count = upmsUserService.insertSelective(user);

        System.out.println("count = " + count);
        System.out.println("userId = " + user.getUserId());
    }
}
