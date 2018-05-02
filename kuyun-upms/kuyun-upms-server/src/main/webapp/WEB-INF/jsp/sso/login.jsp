<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>库云工业设备物联平台</title>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <meta name="keywords" content="设备云,电力云,光伏云,资产管理,设备管理,检修管理,运行管理,巡点检管理,物资管理,检修工程管理,监测与诊断,安监管理,工器具管理,EAM,TPM,RCM">
    <meta name="description" content="库云以“十三五规划”、“互联网+”、“中国制造2025（工业4.0）”等为政策导向，以推动“互联网+”模式下的资产管理更安全、更节约、更高效为使命，以打造互联共享的资产管理生态圈为目标，以10+年以上资产管理服务和解决方案为实践基础，以互联网、云计算、大数据、工业互联和信息安全等为技术基础，全新打造以SaaS公有云、私有云和混合云为主要商业模式的大型企业级互联网平台">

    <link href="${basePath}/resources/metronic-admin/assets/vendors/base/vendors.bundle.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}/resources/metronic-admin/assets/demo/default/base/style.bundle.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut icon" href="${basePath}/resources/metronic-admin/assets/demo/default/media/img/logo/favicon.ico" />

    <link href="${basePath}/resources/metronic-admin/assets/css/login/components-rounded.css" rel="stylesheet"/>
    <link href="${basePath}/resources/metronic-admin/assets/css/login/darkblue.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/metronic-admin/assets/css/login/login-6.css" rel="stylesheet"/>
</head>
<body>
<div class="clearfix"> </div>
<div ui-view="" class="ng-scope">
    <div class="user-login-6 ng-scope">
        <a class="logo"></a>
        <div class="logincontent" id="login">
            <form class="login-form ng-pristine ng-invalid ng-invalid-required" action="javascript:;" method="post" ng-submit="login()">
                <h3 class="form-title">用户登录</h3>
                <div class="alert alert-danger display-hide">
                    <button class="close" data-close="alert"></button>
                    <span> 请填写用户名和密码 </span>
                </div>
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">手机号码</label>
                    <div class="input-icon">
                        <i class="fa fa-user"></i>
                        <input class="form-control placeholder-no-fix ng-pristine ng-untouched ng-invalid ng-invalid-required" type="text" autocomplete="off" placeholder="手机号码" name="username" id="username" value="18652424835"> </div>
                </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">密码</label>
                    <div class="input-icon">
                        <i class="fa fa-lock"></i>
                        <input class="form-control placeholder-no-fix ng-pristine ng-untouched ng-invalid ng-invalid-required" id="password" type="password" autocomplete="off" placeholder="密码" name="password" ng-keyup="$event.keyCode == 13 &amp;&amp; login()" value="123456"> </div>
                </div>
                <div class="form-actions">
                    <label class="rememberme mt-checkbox mt-checkbox-outline mt-checkbox-login">
                        <input type="checkbox"  id="rememberMe" name="rememberMe" ng-model="loginForm.remember" class="ng-pristine ng-untouched ng-valid"> 记住密码
                        <span></span>
                    </label>
                    <button type="button" id="login-bt" class="btn pull-right" disabled="disabled"> 登录 </button>
                </div>
                <div class="forget-container">
                    <div class="forget-password">
                        <a href="password_find" id="back-password"><span class="caption-subject  bold uppercase">忘记密码？</span></a>
                    </div>
                    <div class="create-account">

                        <a href="reg" id="forget-password"><span class="caption-subject bold uppercase">立刻注册</span><i class="icon-arrow-right"></i></a>
                    </div>
                    <div class="device-guide">
                        <a href="http://www.coderise.cn/col.jsp?id=152" target="_blank"><span class="caption-subject  bold uppercase">库云设备接入小课堂</span></a>
                    </div>
                </div>

            </form>
        </div>

        <div id="footer">
            <div class="footer-w">
                <span>©2018 库德莱兹物联科技（苏州）有限公司  地址：苏州工业园苏虹东路177号方正科技园</span>
                <span class="tel-d">客服电话:<em>0512-86861827 18652424835</em></span>
            </div>
        </div>


        <div class="customer-service" id="customer-service">
            <ul>
                <li class="app-service"><a title="APP" href="javascript:;"><span class="icon"></span><span class="text"></span></a>
                    <div class="tips">
                        <div class="con"><img src="/resources/images/appdown.png" alt=""></div><i class="arrow-right"></i></div>
                </li>
                <li class="tel-service"><a title="电话" href="tel:0512-86861827"><span class="icon"></span><span class="text"></span></a>
                    <div class="tips">
                        <div class="con">
                            <h2>0512-86861827</h2>
                            <h3>工作时间周一至周五9:00-18:00</h3>
                            <h2>18652424835</h2>search
                            <h3>工作时间7x24小时</h3>
                        </div><i class="arrow-right"></i>
                    </div>
                </li>
                <li class="wx-service"><a title="微信" href="javascript:;"><span class="icon"></span><span class="text"></span></a>
                    <div class="tips">
                        <div class="con"><img src="/resources/images/wx.png" alt=""></div><i class="arrow-right"></i></div>
                </li>
            </ul>
        </div>
    </div>
</div>

<script src="${basePath}/resources/kuyun-admin/plugins/jquery.1.12.4.min.js"></script>

<script src="${basePath}/resources/kuyun-admin/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>

<script src="${basePath}/resources/metronic-admin/assets/vendors/base/vendors.bundle.js" type="text/javascript"></script>
<script src="${basePath}/resources/metronic-admin/assets/demo/default/base/scripts.bundle.js" type="text/javascript"></script>

<script>var BASE_PATH = '${basePath}';</script>
<script>var BACK_URL = '${param.backurl}';</script>
<script src="${basePath}/resources/metronic-admin/assets/kuyun/login.js"></script>
<script>
<c:if test="${param.forceLogout == 1}">
swWarn('您已被强制下线！');
top.location.href = '${basePath}/sso/login';
</c:if>


</script>
</body>
</html>