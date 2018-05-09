package com.kuyun.common.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.PlatformNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by user on 2017-09-21.
 */
public class JpushUtil {
    private static Logger LOG = LoggerFactory.getLogger(JpushUtil.class);
    private static boolean isTest = false;

    protected static final String APP_KEY ="db1c4a19885c07e022791153";
    protected static final String MASTER_SECRET = "b2e2f10627c2d38b59f420de";

    public void sendPush(List<String> mobiles, String alter, String extras){
        ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        final PushPayload payload = buildPushObject_all_alias_alert(mobiles, alter, extras);
        try {

            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
         } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            LOG.error("Sendno: " + payload.getSendno());

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.error("Sendno: " + payload.getSendno());
        }
    }

    private static PushPayload buildPushObject_all_alias_alert(List<String> mobiles, String alter, String extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setOptions(Options.newBuilder().setApnsProduction(!isTest).build())
                .setAudience(Audience.alias(mobiles))
                .setNotification(Notification.newBuilder().setAlert(alter)
                        .addPlatformNotification(AndroidNotification.newBuilder().addExtra("type", extras).build())
                        .addPlatformNotification(IosNotification.newBuilder().addExtra("type", extras).build())
                        .build())
                .build();


    }

}
