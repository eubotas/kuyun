package com.kuyun.common.netease;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-07-05.
 */
public class SendTemplate extends AbstractSMS {

    private static final String TEMPLATE_SERVER_URL = "https://api.netease.im/sms/sendtemplate.action";

    private String templateid;
    private String mobiles;
    private String params;

    public SendTemplate(String templateid, String mobiles, String params){
        this.templateid = templateid;
        this.mobiles = mobiles;
        this.params = params;
    }

    @Override
    String getServerUrl() {
        return TEMPLATE_SERVER_URL;
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
        nvps.add(new BasicNameValuePair("templateid", templateid));
        nvps.add(new BasicNameValuePair("mobiles", mobiles));

        if (!StringUtils.isEmpty(params)){
            nvps.add(new BasicNameValuePair("params", params));
        }
        return nvps;
    }
}
