package com.kuyun.common.netease;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-07-05.
 */
public class SMSUtil {
    private static Logger _log = LoggerFactory.getLogger(SMSUtil.class);
    private static List<NameValuePair> nvps;
    static {
        nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("message", "error"));
        nvps.add(new BasicNameValuePair("code", "-1"));
    }


    public static String sendCode(String mobile) {
        SendCode sms = new SendCode(mobile);
        try {
            return sms.sendSMS();
        } catch (Exception e) {
            _log.error("SMS Send code error:" + e.getMessage());
            return nvps.toString();
        }
    }
    
    public static String verifyCode(String mobile, String code) {
        VerifyCode sms = new VerifyCode(mobile, code);
        try {
            return sms.sendSMS();
        } catch (Exception e) {
            _log.error("SMS Verify code error:" + e.getMessage());
            return nvps.toString();
        }
    }

    public static String sendTemplate(String templateid, String mobiles, String params){
        SendTemplate sms = new SendTemplate(templateid, mobiles, params);
        try {
            return sms.sendSMS();
        } catch (Exception e) {
            _log.error("SMS Verify code error:" + e.getMessage());
            return nvps.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("sendCode(\"13402559532\") = " + sendCode("13402559532"));
        System.out.println("verifyCode(\"13402559532\") = " + verifyCode("13402559532", "650388"));
    }
}
