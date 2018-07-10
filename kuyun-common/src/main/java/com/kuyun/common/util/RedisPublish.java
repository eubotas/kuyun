package com.kuyun.common.util;

import redis.clients.jedis.Jedis;


public class RedisPublish {
    public static final String ALARM_USER="ALARM_USER";
    public static final String DATA_CHANGE="DATA_CHANGE";

    public void publishDataChange(String companyId){
        Jedis jRedis = new Jedis("localhost");
        jRedis.publish(DATA_CHANGE+companyId,"Data changed");
    }

    public void publishAlarm(String userId){
        Jedis jRedis = new Jedis("localhost");
        jRedis.publish(ALARM_USER+userId,"Data changed");
    }

    public static void main(String[] args)  {
        new RedisPublish().publishAlarm("1001");
    }
}
