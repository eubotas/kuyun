package com.kuyun.upms.server;

import com.google.gson.Gson;
import com.kuyun.upms.common.pojo.Token;
import com.kuyun.upms.common.pojo.AuthRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 2018-03-29.
 */
public class TokenTest {
    private final static Logger _log = LoggerFactory.getLogger(TokenTest.class);

    public static void main(String[] args) {
        AuthRequest userInfo = new AuthRequest();

        Token token = new Token();
        token.setPrincipal("admin");
        token.setCredentials("password");

        userInfo.setToken(token);

        Gson gson = new Gson();
        String jsonString = gson.toJson(userInfo);

        _log.info("userInfo:{}", jsonString);
    }
}
