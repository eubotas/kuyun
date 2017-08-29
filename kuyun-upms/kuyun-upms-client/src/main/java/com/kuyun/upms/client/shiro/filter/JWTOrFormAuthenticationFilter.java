package com.kuyun.upms.client.shiro.filter;

import com.google.gson.Gson;
import com.kuyun.common.base.BaseResult;
import com.kuyun.upms.common.constant.UpmsResultConstant;
import com.nimbusds.jose.JWSObject;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

public class JWTOrFormAuthenticationFilter extends UpmsAuthenticationFilter {

    private static Logger _log = LoggerFactory.getLogger(JWTOrFormAuthenticationFilter.class);

    protected static final String AUTHORIZATION_HEADER = "Authorization";
    protected static final String IS_APP_HEADER = "isApp";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        boolean result;
        if (isApp(request)) {
            result = false;
        } else {
            result = super.isAccessAllowed(request, response, mappedValue);
        }
        return result;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        boolean loggedIn = false;

        _log.info("onAccessDenied start");

        if (isApp(request)) {
            loggedIn = executeLogin(request, response);

        } else {
            loggedIn = super.onAccessDenied(request, response);
        }

        _log.info("onAccessDenied end");

        return loggedIn;
    }

    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        if (token != null) {
            try {
                Subject subject = getSubject(request, response);
                subject.login(token);
                return onLoginSuccess(token, subject, request, response);
            } catch (UnknownAccountException e) {
                return onLoginFailure(response, UpmsResultConstant.INVALID_USERNAME);
            } catch (IncorrectCredentialsException e) {
                return onLoginFailure(response, UpmsResultConstant.WRONG_TOKEN);
            } catch (LockedAccountException e) {
                return onLoginFailure(response, UpmsResultConstant.INVALID_ACCOUNT);
            }
        }else{
            return false;
        }
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws IOException {
        AuthenticationToken token = null;
        String jwtToken = getAuthzHeader(request);

        if (jwtToken != null) {
            token = createToken(jwtToken, response);
        }else{
            onLoginFailure(response, UpmsResultConstant.EMPTY_TOKEN);
        }
        return token;
    }

    protected boolean onLoginFailure(ServletResponse response, UpmsResultConstant code) {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        BaseResult data = new BaseResult(code.getCode(), code.getMessage(), null);
        String json = new Gson().toJson(data);
        httpResponse.setContentType("application/json");
        httpResponse.setCharacterEncoding("UTF-8");
        try {
            httpResponse.getWriter().write(json);
        } catch (IOException e) {
            _log.error("Parse token error:"+e.getMessage());
        }
        return false;
    }

    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        return true;
    }

    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return httpRequest.getHeader(AUTHORIZATION_HEADER);
    }

    protected boolean isApp(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String isApp = httpRequest.getHeader(IS_APP_HEADER);

        return Boolean.valueOf(isApp);
    }


    public JWTAuthenticationToken createToken(String token, ServletResponse response) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            String decrypted = jwsObject.getPayload().toString();

            try (JsonReader jr = Json.createReader(new StringReader(decrypted))) {
                JsonObject object = jr.readObject();

                String username = object.getString("sub", null);
                return new JWTAuthenticationToken(username, token);
            }

        } catch (ParseException ex) {
            onLoginFailure(response, UpmsResultConstant.WRONG_TOKEN);
            _log.error("Parse token error:"+ex.getMessage());
            return null;
        }

    }

}
