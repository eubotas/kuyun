package com.kuyun.common.netease;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-07-05.
 */
public class SendCode extends AbstractSMS {
    //发送验证码的请求路径URL
    private static final String SEND_CODE_SERVER_URL ="https://api.netease.im/sms/sendcode.action";
    //验证码长度，范围4～10，默认为4
    private static final String CODELEN="6";

    private String mobile = null;

    public SendCode(String mobile){
        this.mobile = mobile;
    }

    @Override
    String getServerUrl() {
        return SEND_CODE_SERVER_URL;
    }



    @Override
    List<NameValuePair> buildNameValuePairs() {
        // 设置请求的的参数，requestBody参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        /*
         * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
         * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
         * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
         */
//        nvps.add(new BasicNameValuePair("templateid", TEMPLATEID));
        nvps.add(new BasicNameValuePair("mobile", mobile));
        nvps.add(new BasicNameValuePair("codeLen", CODELEN));
        return nvps;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
