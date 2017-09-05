package com.kuyun.upms.client.shiro.realm;


import com.kuyun.upms.client.shiro.filter.JWTAuthenticationToken;
import com.kuyun.upms.dao.model.UpmsUser;
import org.apache.shiro.authc.*;

public class JWTRealm extends UpmsRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof JWTAuthenticationToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        JWTAuthenticationToken upToken = (JWTAuthenticationToken) token;

        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername((String) upToken.getUsername());
        boolean validToken = upmsApiService.validateToken(upToken.getToken());

        if (upmsUser != null && validToken) {
            SimpleAccount account = new SimpleAccount(upmsUser.getUsername(), upToken.getToken(), getName());
            return account;
        }

        if (null == upmsUser) {
            throw new UnknownAccountException();
        }
        if (!validToken) {
            throw new IncorrectCredentialsException();
        }
        if (upmsUser.getLocked() == 1) {
            throw new LockedAccountException();
        }

        return new SimpleAuthenticationInfo(upToken.getUsername(), upToken.getToken(), getName());

    }

}
