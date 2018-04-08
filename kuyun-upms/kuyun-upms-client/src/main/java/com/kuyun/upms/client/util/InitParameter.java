package com.kuyun.upms.client.util;

import com.kuyun.common.util.BasePath;
import com.kuyun.upms.dao.model.UpmsSystem;
import com.kuyun.upms.dao.model.UpmsSystemExample;
import com.kuyun.upms.rpc.api.UpmsSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitParameter implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UpmsSystemService upmsSystemService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        UpmsSystemExample.Criteria cr= upmsSystemExample.createCriteria();
        cr.andDeleteFlagEqualTo(false);
        List list=new ArrayList(){{add("kuyun-upms-server"); add("kuyun-eam-admin");}};
        cr.andNameIn(list);
        List<UpmsSystem> rows = upmsSystemService.selectByExample(upmsSystemExample);
        String name="";
        for(UpmsSystem sys : rows){
            name=sys.getName();
            if("kuyun-upms-server".equals(name))
                BasePath.kuyunUpmsServer = sys.getBasepath();
            else if("kuyun-eam-admin".equals(name))
                BasePath.kuyunEamAdmin = sys.getBasepath();
        }
    }
}
