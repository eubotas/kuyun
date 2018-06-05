package com.kuyun.upms.server.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.base.BaseResult;
import com.kuyun.common.netease.SMSUtil;
import com.kuyun.common.util.*;
import com.kuyun.upms.client.shiro.session.UpmsSession;
import com.kuyun.upms.client.shiro.session.UpmsSessionDao;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.common.constant.UpmsResult;
import com.kuyun.upms.common.constant.UpmsResultConstant;
import com.kuyun.upms.dao.model.*;
import com.kuyun.upms.dao.vo.CompanyInfo;
import com.kuyun.upms.rpc.api.*;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
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
    private BaseEntityUtil baseEntityUtil;

    @Autowired
    private UpmsApiService upmsApiService;

    @Autowired
    private UpmsCompanyService upmsCompanyService;
    @Autowired
    private UpmsCompanyOptionService upmsCompanyOptionService;
    @Autowired
    private com.kuyun.fileuploader.rpc.api.FileUploaderService fileUploaderService;

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
            return "/sso/login.jsp";
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

        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria().andDeleteFlagEqualTo(false).andPhoneEqualTo(username);
        UpmsUser upmsUser = upmsUserService.selectFirstByExample(upmsUserExample);
        request.getSession().setAttribute("USER", upmsUser);

        setCompanyInfo(baseEntityUtil.getUserCompany(upmsUser).getCompanyId(), request);

        // 回跳登录前地址
        String backurl = request.getParameter("backurl");
        if (StringUtils.isBlank(backurl)) {
            return new UpmsResult(UpmsResultConstant.SUCCESS, "/");
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

        redirectUrl = BasePath.getInstance().getKuyunUpmsServer() +"/sso/login";
        return "redirect:"+redirectUrl;
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
            return new UpmsResult(UpmsResultConstant.EMPTY_PHONE, "手机号码不能为空！");
        }

        UpmsUser user = getUpmsUser(userName);

        if (user != null) {
            return new UpmsResult(UpmsResultConstant.FAILED, "手机号码已注册");
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
        UpmsUserExample.Criteria criteria =  userExample.createCriteria();
        criteria.andUsernameEqualTo(userName);
        criteria.andDeleteFlagEqualTo(false);

        return upmsUserService.selectFirstByExample(userExample);
    }


    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    @ResponseBody
    public Object reg(HttpServletRequest request, ModelMap modelMap) {
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String company = request.getParameter("company");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String code = request.getParameter("code");
        String userName = phone;

        UpmsUser user = getUpmsUser(userName);
        if (user != null) {
            return new UpmsResult(UpmsResultConstant.FAILED, "手机号码已经注册过, 请找回密码.");
        }

        String resCode = checkVerifyCode(phone, code);
        if (!"200".equals(resCode)) {
            return new UpmsResult(UpmsResultConstant.FAILED, "验证码不正确");
        }

        upmsApiService.handleReg(userName, name, password, email, phone, company, true);

        return new UpmsResult(UpmsResultConstant.SUCCESS, "注册成功");
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

        UpmsUser user = getUpmsUser(phone);
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

    private void setCompanyInfo(int companyId, HttpServletRequest request){
        UpmsCompany company = upmsCompanyService.selectByPrimaryKey(companyId);
        UpmsCompanyOption opt = upmsCompanyOptionService.selectByPrimaryKey(companyId);
        CompanyInfo comp = new CompanyInfo();
        if (opt != null){
            if(!StringUtil.isEmpty(opt.getLogoPath())){
                comp.setCompanyLogo( fileUploaderService.getServerInfo().getServerBaseUri()+"/fileStorage/eam/"+opt.getLogoPath());
            }
            comp.setCompanySystemName(opt.getSystemName());
            comp.setCompanyName(company.getName());
            comp.setCompanyAddr(company.getAddress());
            comp.setCompanyTel(company.getPhone());
        }


        request.getSession(true).setAttribute("COMPANY", comp);
    }

    @RequestMapping(value = "/manage1", method = RequestMethod.POST)
    @ResponseBody
    public Object manage1(HttpServletRequest request, ModelMap modelMap) {
        if (!HuaWeiSaasUtil.verificateRequestParams(request, HuaWeiSaasUtil.key, 256)
                && !HuaWeiSaasUtil.verificateDecodedRequestParams(request, HuaWeiSaasUtil.key, 256)) {
            return new UpmsResult(UpmsResultConstant.WRONG_TOKEN, "authToken error");
        }
        String userName = request.getParameter("account");
        boolean isLocked = true;
        try {
            isLocked = request.getParameter("isLocked").equals("1");
        } catch (Exception e) {
            e.printStackTrace();
        }

        UpmsUser user = getUpmsUser1(userName);
        if (user == null) {
            return new UpmsResult(UpmsResultConstant.FAILED, "用户名不存在");
        }
        user.setLocked(isLocked ? (byte) 1 : (byte) 0);
        upmsUserService.updateByPrimaryKey(user);
        return new UpmsResult(UpmsResultConstant.SUCCESS, null);
    }

    @RequestMapping(value = "/reg1", method = RequestMethod.POST)
    @ResponseBody
    public Object reg1(HttpServletRequest request, ModelMap modelMap) {
        if (!HuaWeiSaasUtil.verificateRequestParams(request, HuaWeiSaasUtil.key, 256)
                && !HuaWeiSaasUtil.verificateDecodedRequestParams(request, HuaWeiSaasUtil.key, 256)) {
            return new UpmsResult(UpmsResultConstant.WRONG_TOKEN, "authToken error");
        }
        String userName = request.getParameter("account");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String company = request.getParameter("company");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        UpmsUser user = getUpmsUser1(userName);
        if (user != null) {
            return new UpmsResult(UpmsResultConstant.FAILED, "用户名已存在");
        }
        upmsApiService.handleReg(userName, name, password, email, phone, company,true);

        return new UpmsResult(UpmsResultConstant.SUCCESS, null);
    }
    private UpmsUser getUpmsUser1(String userName) {
        UpmsUserExample userExample = new UpmsUserExample();
        UpmsUserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(userName);
//        criteria.andDeleteFlagEqualTo(false);

        return upmsUserService.selectFirstByExample(userExample);
    }
}