package com.kuyun.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisSubscribe {
    private static Logger _log = LoggerFactory.getLogger(RedisSubscribe.class);

    boolean isChangedAlarm=false;
    boolean isDataChanged=false;

    public boolean isChangedAlarm() {
        return isChangedAlarm;
    }

    public boolean isDataChanged() {
        return isDataChanged;
    }

    public void SubDataChange(Integer companyId){
        final Jedis jRedis = new Jedis("localhost");

        JedisPubSub jedisPub=new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                isDataChanged = true;
                // 执行订阅消息
                super.onMessage(channel, message);
                //终止订阅
                super.unsubscribe();
                _log.debug(message);
            }
        };
        jRedis.subscribe(jedisPub,RedisPublish.DATA_CHANGE+companyId);
    }


    public void SubAlarm(Integer companyId, String userId){
        final Jedis jRedis = new Jedis("localhost");
        JedisPubSub jedisPub=new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                // 执行订阅消息
                if( message != null){
                    String[] userIds = message.split(",");
                    if(isInclude(userIds, userId)) {
                        isChangedAlarm = true;
                        super.onMessage(channel, message);
                        //终止订阅
                        super.unsubscribe();
                        _log.info(message);
                    }
                }
            }
        };
        jRedis.subscribe(jedisPub,RedisPublish.ALARM_USER+companyId);
    }

    private boolean isInclude(String[] strs, String id){
        if(id == null || strs ==null)
            return false;

        for(String s: strs){
            if(id.equals(s))
                return true;
        }
        return false;
    }
}
