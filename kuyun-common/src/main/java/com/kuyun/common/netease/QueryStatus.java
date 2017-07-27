package com.kuyun.common.netease;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-07-27.
 */
public class QueryStatus extends AbstractSMS {
    private static final String SERVER_URL ="https://api.netease.im/sms/querystatus.action";

    private String sendid;

    public QueryStatus(String sendid){
        this.sendid = sendid;
    }

    @Override
    String getServerUrl() {
        return SERVER_URL;
    }

    @Override
    List<NameValuePair> buildNameValuePairs() {
        // 设置请求的的参数，requestBody参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("sendid", sendid));
        return nvps;
    }
}
