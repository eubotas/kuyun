package com.kuyun.upms.server.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.base.BaseResult;
import com.kuyun.common.netease.SMSUtil;
import com.kuyun.common.util.MD5Util;
import com.kuyun.common.util.RedisUtil;
import com.kuyun.upms.client.shiro.session.UpmsSession;
import com.kuyun.upms.client.shiro.session.UpmsSessionDao;
import com.kuyun.upms.common.constant.UpmsResult;
import com.kuyun.upms.common.constant.UpmsResultConstant;
import com.kuyun.upms.common.pojo.AuthRequest;
import com.kuyun.upms.common.pojo.auth.*;
import com.kuyun.upms.dao.model.*;
import com.kuyun.upms.rpc.api.UpmsApiService;
import com.kuyun.upms.rpc.api.UpmsSystemService;
import com.kuyun.upms.rpc.api.UpmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 单点登录管理
 * Created by kuyun on 2016/12/10.
 */
@Controller
@RequestMapping("/sso")
@Api(value = "单点登录管理", description = "单点登录管理")
public class SSOController extends BaseController {

    private final static Logger _log = LoggerFactory.getLogger(SSOController.class);
    // 全局会话key
    private final static String kuyun_UPMS_SERVER_SESSION_ID = "kuyun-upms-server-session-id";
    // 全局会话key列表
    private final static String kuyun_UPMS_SERVER_SESSION_IDS = "kuyun-upms-server-session-ids";
    // code key
    private final static String kuyun_UPMS_SERVER_CODE = "kuyun-upms-server-code";

    @Autowired
    UpmsSystemService upmsSystemService;

    @Autowired
    UpmsUserService upmsUserService;

    @Autowired
    UpmsSessionDao upmsSessionDao;

    @Autowired
    private UpmsApiService upmsApiService;


    @ApiOperation(value = "认证中心首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws Exception {
        String appid = request.getParameter("appid");
        String backurl = request.getParameter("backurl");
        if (StringUtils.isBlank(appid)) {
            throw new RuntimeException("无效访问！");
        }
        // 判断请求认证系统是否注册
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andNameEqualTo(appid);
        int count = upmsSystemService.countByExample(upmsSystemExample);
        if (0 == count) {
            throw new RuntimeException(String.format("未注册的系统:%s", appid));
        }
        return "redirect:/sso/login?backurl=" + URLEncoder.encode(backurl, "utf-8");
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, ServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String serverSessionId = session.getId().toString();
        // 判断是否已登录，如果已登录，则回跳
        String code = RedisUtil.get(kuyun_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);

        // 回跳
        String backurl = request.getParameter("backurl");
        // code校验值
        if (StringUtils.isNotBlank(code)) {
            String username = (String) subject.getPrincipal();
            if (StringUtils.isBlank(backurl)) {
                backurl = "/";
            } else {
                if (backurl.contains("?")) {
                    backurl += "&upms_code=" + code + "&upms_username=" + username;
                } else {
                    backurl += "?upms_code=" + code + "&upms_username=" + username;
                }
            }
            _log.debug("认证中心帐号通过，带code回跳：{}", backurl);
            return "redirect:" + backurl;
        } else {
            if (StringUtils.isBlank(backurl)) {
                return "/sso/login.jsp";
            } else {
                handleSessionTimeOut(response);
            }
            return null;
        }

    }

    @RequestMapping(value = "/session_time_out", method = RequestMethod.GET)
    public void sessionTimeOut(HttpServletRequest request, ServletResponse response) {
        handleSessionTimeOut(response);
    }

    private void handleSessionTimeOut(ServletResponse response) {
        BaseResult result = new BaseResult(403, "Session Timeout", null);
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            _log.error("Send Time Out Response Error:" + e.getMessage());
        }
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        if (StringUtils.isBlank(username)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_USERNAME, "帐号不能为空！");
        }
        if (StringUtils.isBlank(password)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_PASSWORD, "密码不能为空！");
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        // 判断是否已登录，如果已登录，则回跳，防止重复登录
        String hasCode = RedisUtil.get(kuyun_UPMS_SERVER_SESSION_ID + "_" + sessionId);
        // code校验值
        if (StringUtils.isBlank(hasCode)) {
            // 使用shiro认证
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            try {
                if (BooleanUtils.toBoolean(rememberMe)) {
                    usernamePasswordToken.setRememberMe(true);
                } else {
                    usernamePasswordToken.setRememberMe(false);
                }
                subject.login(usernamePasswordToken);
            } catch (UnknownAccountException e) {
                return new UpmsResult(UpmsResultConstant.INVALID_USERNAME, "帐号不存在！");
            } catch (IncorrectCredentialsException e) {
                return new UpmsResult(UpmsResultConstant.INVALID_PASSWORD, "密码错误！");
            } catch (LockedAccountException e) {
                return new UpmsResult(UpmsResultConstant.INVALID_ACCOUNT, "帐号已锁定！");
            }
            // 更新session状态
            upmsSessionDao.updateStatus(sessionId, UpmsSession.OnlineStatus.on_line);
            // 全局会话sessionId列表，供会话管理
            RedisUtil.lpush(kuyun_UPMS_SERVER_SESSION_IDS, sessionId.toString());
            // 默认验证帐号密码正确，创建code
            String code = UUID.randomUUID().toString();
            // 全局会话的code
            RedisUtil.set(kuyun_UPMS_SERVER_SESSION_ID + "_" + sessionId, code, (int) subject.getSession().getTimeout() / 1000);
            // code校验值
            RedisUtil.set(kuyun_UPMS_SERVER_CODE + "_" + code, code, (int) subject.getSession().getTimeout() / 1000);


            String clientToken = upmsApiService.createToken(usernamePasswordToken.getUsername());

            _log.info("user name:" + usernamePasswordToken.getUsername());
            _log.info("clientToken:" + clientToken);

            response.addHeader("token", clientToken);
        }


        // 回跳登录前地址
        String backurl = request.getParameter("backurl");
        if (StringUtils.isBlank(backurl)) {

            UpmsUser user = getUpmsUser(username);
            String phone = "";
            if (user != null){
                phone = user.getPhone();
            }
            return new UpmsResult(UpmsResultConstant.SUCCESS, phone);
        } else {
            return new UpmsResult(UpmsResultConstant.SUCCESS, backurl);
        }
    }



    @ApiOperation(value = "校验code")
    @RequestMapping(value = "/code", method = RequestMethod.POST)
    @ResponseBody
    public Object code(HttpServletRequest request) {
        String codeParam = request.getParameter("code");
        String code = RedisUtil.get(kuyun_UPMS_SERVER_CODE + "_" + codeParam);
        if (StringUtils.isBlank(codeParam) || !codeParam.equals(code)) {
            new UpmsResult(UpmsResultConstant.FAILED, "无效code");
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, code);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        // shiro退出登录
        SecurityUtils.getSubject().logout();
        // 跳回原地址
        String redirectUrl = request.getHeader("Referer");
        if (null == redirectUrl) {
            redirectUrl = "/";
        }
        return "redirect:" + redirectUrl;
    }

    @ApiOperation(value = "退出登录")
    @RequestMapping(value = "/api/logout", method = RequestMethod.GET)
    @ResponseBody
    public Object apiLogout(HttpServletRequest request) {
        // shiro退出登录
        SecurityUtils.getSubject().logout();
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }


    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String reg(HttpServletRequest request) {
        return "/sso/reg.jsp";
    }

    @RequestMapping(value = "/check_user_name", method = RequestMethod.POST)
    @ResponseBody
    public Object checkUserName(HttpServletRequest request, ModelMap modelMap) {
        String userName = request.getParameter("account");
        if (StringUtils.isBlank(userName)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_USERNAME, "用户名不能为空！");
        }

        UpmsUser user = getUpmsUser(userName);

        if (user != null) {
            return new UpmsResult(UpmsResultConstant.FAILED, "用户名已存在");
        } else {
            return new UpmsResult(UpmsResultConstant.SUCCESS, "用户名可用");
        }
    }

    @RequestMapping(value = "/send_code", method = RequestMethod.POST)
    @ResponseBody
    public Object sendCode(HttpServletRequest request, ModelMap modelMap) {
        String phone = request.getParameter("phone");
        if (StringUtils.isBlank(phone)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_PHONE, "电话号码不能为空！");
        }
        return SMSUtil.sendCode(phone);
    }

    private UpmsUser getUpmsUser(String userName) {
        UpmsUserExample userExample = new UpmsUserExample();
        userExample.createCriteria().andUsernameEqualTo(userName);

        return upmsUserService.selectFirstByExample(userExample);
    }


    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    @ResponseBody
    public Object reg(HttpServletRequest request, ModelMap modelMap) {
        String userName = request.getParameter("account");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String company = request.getParameter("company");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String code = request.getParameter("code");

        UpmsUser user = getUpmsUser(userName);
        if (user != null) {
            return new UpmsResult(UpmsResultConstant.FAILED, "用户名已存在");
        }

        String resCode = checkVerifyCode(phone, code);
        if (!"200".equals(resCode)) {
            return new UpmsResult(UpmsResultConstant.FAILED, "验证码不正确");
        }

        upmsApiService.handleReg(userName, name, password, email, phone, company);

        return new UpmsResult(UpmsResultConstant.SUCCESS, null);
    }

    private String checkVerifyCode(String phone, String code) {
        String resData = SMSUtil.verifyCode(phone, code);
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> map = gson.fromJson(resData, type);

        return map.get("code");
    }


    @RequestMapping(value = "/password_find", method = RequestMethod.GET)
    public String passwordFind(HttpServletRequest request) {
        return "/sso/back_step_1.jsp";
    }

    @RequestMapping(value = "/back", method = RequestMethod.POST)
    @ResponseBody
    public Object back(HttpServletRequest request, ModelMap modelMap) {
        String phone = request.getParameter("phone");
        String code = request.getParameter("code");
        String resCode = checkVerifyCode(phone, code);
        if (!"200".equals(resCode)) {
            return new UpmsResult(UpmsResultConstant.FAILED, "验证码不正确");
        }
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        UpmsUser user = getUpmsUserByPhone(phone);
        if (user == null) {
            return new UpmsResult(UpmsResultConstant.FAILED, "用户不存在");
        }

        if (!password.equals(confirmPassword)){
            return new UpmsResult(UpmsResultConstant.FAILED, "两次密码不一致");
        }

        user.setLocked(Byte.decode("0"));

        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        user.setSalt(salt);
        user.setPassword(MD5Util.md5(password + salt));

        int count = upmsUserService.updateByPrimaryKeySelective(user);

        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    private UpmsUser getUpmsUserByPhone(String phone) {
        UpmsUserExample userExample = new UpmsUserExample();
        userExample.createCriteria().andPhoneEqualTo(phone);

        return upmsUserService.selectFirstByExample(userExample);
    }

    @RequestMapping(value = "/back_step1", method = RequestMethod.POST)
    @ResponseBody
    public Object backStep1(HttpServletRequest request, ModelMap modelMap) {
        String username = request.getParameter("account");
        String phone = "";

        UpmsUser user = getUpmsUser(username);
        if (user == null) {
            return new UpmsResult(UpmsResultConstant.FAILED, "用户名不存在");
        }else {
            phone = user.getPhone();
        }
        modelMap.put("maskPhone", replacePhone(phone));
        modelMap.put("phone", phone);
        modelMap.put("username", username);

        return new UpmsResult(UpmsResultConstant.SUCCESS, modelMap);
    }

    @RequestMapping(value = "/back_step2", method = RequestMethod.POST)
    @ResponseBody
    public Object backStep2(HttpServletRequest request, ModelMap modelMap) {
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String code = request.getParameter("code");
        String resCode = checkVerifyCode(phone, code);
        if (!"200".equals(resCode)) {
            return new UpmsResult(UpmsResultConstant.FAILED, "验证码不正确");
        }
        modelMap.put("username", username);
        return new UpmsResult(UpmsResultConstant.SUCCESS, modelMap);
    }

    private String replacePhone(String phone){
        StringBuilder result = new StringBuilder(11);
        result.append("*******");
        result.append(StringUtils.substring(phone,7));
        return result.toString();
    }

    @RequestMapping(value = "/back_step3", method = RequestMethod.POST)
    @ResponseBody
    public Object backStep3(HttpServletRequest request, ModelMap modelMap) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UpmsUser user = getUpmsUser(username);
        if (user == null) {
            return new UpmsResult(UpmsResultConstant.FAILED, "用户名不存在");
        }

        user.setLocked(Byte.decode("0"));

        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        user.setSalt(salt);
        user.setPassword(MD5Util.md5(password + salt));

        int count = upmsUserService.updateByPrimaryKeySelective(user);

        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "用户鉴权")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ResponseBody
    public Object authenticate(@RequestBody AuthRequest request) {
        String username = request.getToken().getPrincipal();
        String password = request.getToken().getCredentials();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            return new UpmsResult(UpmsResultConstant.INVALID_USERNAME, "帐号不存在！");
        } catch (IncorrectCredentialsException e) {
            return new UpmsResult(UpmsResultConstant.INVALID_PASSWORD, "密码错误！");
        } catch (LockedAccountException e) {
            return new UpmsResult(UpmsResultConstant.INVALID_ACCOUNT, "帐号已锁定！");
        }


        AuthResponse response = buildAuthResponse(username);
        return response;
    }

    private AuthResponse buildAuthResponse(String username){
        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);

        List<UpmsRole> roles = upmsApiService.selectUpmsRoleByUpmsUserId(upmsUser.getUserId());
        List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());

        AuthResponse response = new AuthResponse();
        Info info = new Info();
        Authc authc = new Authc();
        Principal principal = new Principal();
        principal.setLogin(username);
        principal.setApiKey("*****");
        authc.setPrincipal(principal);

        Credentials credentials = new Credentials();
        credentials.setName(upmsUser.getRealname());
        credentials.setEmail(upmsUser.getEmail());
        authc.setCredentials(credentials);

        info.setAuthc(authc);

        Authz authz = new Authz();
        authz.setRoles(buildRoles(roles));
        authz.setPermissions(buildPermissions(upmsPermissions));
        info.setAuthz(authz);

        response.setInfo(info);

        return response;
    }

    private List<String> buildPermissions(List<UpmsPermission> upmsPermissions) {
        List<String> result = new ArrayList<>();
        if (upmsPermissions != null && !upmsPermissions.isEmpty()){
            for(UpmsPermission permission : upmsPermissions){
                if (StringUtils.isNotEmpty(permission.getPermissionValue())){
                    result.add(permission.getPermissionValue());
                }
            }
        }
        return result;
    }

    private List<String> buildRoles(List<UpmsRole> roles) {
        List<String> result = new ArrayList<>();
        if (roles != null && !roles.isEmpty()){
            for (UpmsRole role : roles){
                result.add(role.getName());
            }
        }
        return result;
    }
}