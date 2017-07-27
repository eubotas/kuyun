package com.kuyun.common.netease;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 2017-07-05.
 */
public abstract class AbstractSMS {

    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private String appKey ="a43197d5cf3e8085d99d5099b15fd5c2";
    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private String appSecret ="d3d559328df2";
    //随机数
    private static final String NONCE="123456";


    abstract String getServerUrl();

    abstract List<NameValuePair> buildNameValuePairs();


    public String sendSMS() throws Exception{
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(getServerUrl());
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        /*
         * 参考计算CheckSum的java代码，在上述文档的参数列表中，有CheckSum的计算文档示例
         */
        String checkSum = CheckSumBuilder.getCheckSum(getAppSecret(), NONCE, curTime);

        // 设置请求的header
        httpPost.addHeader("AppKey", getAppKey());
        httpPost.addHeader("Nonce", NONCE);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        List<NameValuePair> nvps = buildNameValuePairs();


        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        /*
         * 1.打印执行结果，打印结果一般会200、315、403、404、413、414、500
         * 2.具体的code有问题的可以参考官网的Code状态表
         */
        return EntityUtils.toString(response.getEntity(), "utf-8");
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
