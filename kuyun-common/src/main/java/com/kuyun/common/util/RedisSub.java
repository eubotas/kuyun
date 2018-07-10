package com.kuyun.common.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisSub {

    public void SubDataChange(String companyId){
        final Jedis jRedis = new Jedis("localhost");

        JedisPubSub jedisPub=new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                // 执行订阅消息
                super.onMessage(channel, message);
                //终止订阅
                super.unsubscribe();
                System.out.println(message);
            }
        };
        jRedis.subscribe(jedisPub,RedisPublish.DATA_CHANGE+companyId);
    }


    public void SubAlarm(String userId){
        final Jedis jRedis = new Jedis("localhost");

        JedisPubSub jedisPub=new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                // 执行订阅消息
                super.onMessage(channel, message);
                //终止订阅
                super.unsubscribe();
                System.out.println(message);
            }
        };
        jRedis.subscribe(jedisPub,RedisPublish.ALARM_USER+userId);
    }

    public static void main(String[] args)  {
        new RedisSub().SubAlarm("1001");
    }
}
