<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
    <title>库云工业设备物联平台</title>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <meta name="keywords" content="设备云,电力云,光伏云,资产管理,设备管理,检修管理,运行管理,巡点检管理,物资管理,检修工程管理,监测与诊断,安监管理,工器具管理,EAM,TPM,RCM">
    <meta name="description" content="库云以“十三五规划”、“互联网+”、“中国制造2025（工业4.0）”等为政策导向，以推动“互联网+”模式下的资产管理更安全、更节约、更高效为使命，以打造互联共享的资产管理生态圈为目标，以10+年以上资产管理服务和解决方案为实践基础，以互联网、云计算、大数据、工业互联和信息安全等为技术基础，全新打造以SaaS公有云、私有云和混合云为主要商业模式的大型企业级互联网平台">

    <link href="${basePath}/resources/metronic-admin/assets/vendors/base/vendors.bundle.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}/resources/metronic-admin/assets/demo/default/base/style.bundle.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut icon" href="${basePath}/resources/metronic-admin/assets/demo/default/media/img/logo/favicon.ico" />
    <link href="${basePath}/resources/kuyun-admin/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/metronic-admin/assets/css/login/darkblue.min.css" rel="stylesheet"/>

    <link href="${basePath}/resources/metronic-admin/assets/css/login/login-6.css" rel="stylesheet"/>

    <style>
        .form-control-feedback{min-width:150px;}
        .form-control{width:415px !important;}
    </style>
</head>
<body>
<div style="height: 100%;">
<jsp:include page="/resources/metronic-admin/homeheader.jsp" flush="true"/>

<div class="m-content" id="heightDiv" style="margin: 80px;min-height:695px;">
    <!--begin::Portlet-->
    <div class="m-portlet m-portlet--mobile">
        <div class="m-portlet__head">
            <div class="m-portlet__head-caption">
                <div class="m-portlet__head-title">
                    <h3 class="m-portlet__head-text">
                        注册新用户
                    </h3>
                </div>
            </div>
        </div>
        <!--begin::Form-->
        <form class="m-form m-form--fit m-form--label-align-right" name="regForm" id="regForm">
            <div class="m-portlet__body">
                <div class="form-group m-form__group row">
                    <label class="col-form-label col-lg-3 col-sm-12">
                        姓名 *
                    </label>
                    <div class="col-lg-4 col-md-6 col-sm-12">
                        <input type="text" class="form-control" name="name" id="name" placeholder="姓名" maxlength="20"
                               data-toggle="m-tooltip" title="" data-original-title="姓名" aria-describedby="name-error">
                    </div>
                </div>
                <div class="form-group m-form__group row">
                    <label class="col-form-label col-lg-3 col-sm-12">
                        密码 *
                    </label>
                    <div class="col-lg-3 col-md-7 col-sm-12">
                        <input type="password" class="form-control m-input" name="password" id="password" placeholder="密码" maxlength="32"
                               data-toggle="m-tooltip" title="" data-original-title="密码" aria-describedby="password-error">
                    </div>
                    <span class="input-group-btn">
                          <a href="javascript:;">
                              <i id="showPassword" class="glyphicon glyphicon-eye-close btn btn-link" onclick="hideShowPassword()"></i>
                          </a>
                    </span>
                </div>
                <div class="form-group m-form__group row">
                    <label class="col-form-label col-lg-3 col-sm-12">
                        公司名称 *
                    </label>
                    <div class="col-lg-4 col-md-6 col-sm-12">
                        <input type="text" class="form-control m-input " name="company" id="company" placeholder="公司名称" maxlength="30"
                               data-toggle="m-tooltip" title="" data-original-title="公司名称" aria-describedby="company-error">
                    </div>
                </div>
                <div class="form-group m-form__group row">
                    <label class="col-form-label col-lg-3 col-sm-12">
                        邮箱 *
                    </label>
                    <div class="col-lg-4 col-md-6 col-sm-12">
                        <input type="text" class="form-control m-input " name="email" id="email" placeholder="邮箱" maxlength="50" value=""
                               data-toggle="m-tooltip" title="" data-original-title="邮箱" aria-describedby="email-error">
                    </div>
                </div>
                <div class="form-group m-form__group row">
                    <label class="col-form-label col-lg-3 col-sm-12">
                        电话 *
                    </label>
                    <div class="col-lg-3 col-md-6 col-sm-12">
                        <input type="text" class="form-control m-input" name="phone" id="phone" placeholder="电话" maxlength="20"
                               data-toggle="m-tooltip" title="" data-original-title="电话" aria-describedby="phone-error">
                    </div>
                    <a href="javascript:;" class="btn blue ng-binding" id="smsSendBtn" onclick="smsToSend()">获取验证码</a>
                </div>
                <div class="form-group m-form__group row">
                    <label class="col-form-label col-lg-3 col-sm-12">
                        验证码 *
                    </label>
                    <div class="col-lg-4 col-md-6 col-sm-12">
                        <input type="text" class="form-control m-input " name="code" id="code" placeholder="验证码" maxlength="10"
                               data-toggle="m-tooltip" title="" data-original-title="验证码" aria-describedby="code-error">
                    </div>
                </div>
                <div class="form-group m-form__group row">
                    <label class=" col-md-offset-3  mt-checkbox mt-checkbox-outline">
                        <input type="checkbox" name="agree" id="agree" ng-model="formData.agree" value="1" class="ng-pristine ng-untouched ng-valid">同意
                        <a href="/#myModal_autocomplete" data-toggle="modal">用户协议
                        </a>
                        <span></span>
                    </label>
                </div>
            </div>
            <div class="m-portlet__foot m-portlet__foot--fit">
                <div class="m-form__actions m-form__actions">
                    <div class="row">
                        <div class="col-lg-9 ml-lg-auto">
                            <button type="button" class="btn btn-secondary" id="goLogin">
                                去登录
                            </button>
                            <button type="submit" class="btn btn-primary" id="regsubmit">
                                注册
                            </button>
                        </div>
                    </div>
                </div>

            </div>
        </form>
        <!--end::Form-->

    </div>

</div>

<script src="${basePath}/resources/kuyun-admin/plugins/jquery.1.12.4.min.js"></script>
<script>
    function swWarn(newtips)
    {
        swal({
            title: "操作提示",
            text: newtips,
            type: "warning"
        });
    }

    function regSubmit(formId){
        $.ajax({
            type: 'post',
            url: "${basePath}/sso/reg",
            data: $('#'+formId).serialize(),
            success: function (result) {
                swWarn(result.data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                swWarn(textStatus);
            }
        });
    }

    function smsToSend(){

        if ($('#phone').val() == '') {
            swWarn("请填入手机号码");
            return;
        }
        $("#smsSendBtn").attr("disabled", true);
        $.ajax({
            type: 'post',
            url: "${basePath}/sso/send_code",
            data: {phone: $('#phone').val()},
            success: function (result) {
                swWarn("验证码已经发送到该手机");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                swWarn(textStatus);
                $("#smsSendBtn").attr("disabled", false);
            }
        });
    }

    function checkAccount(account) {
        $.ajax({
            type: 'post',
            url: '${basePath}/sso/check_user_name',
            data: {account: account},
            beforeSend: function() {
                if ( account == '') {
                    $('#phone').focus();
                    swWarn("请填入手机号码");
                    $('#smsSendBtn').attr("disbled","false");
                    return false;
                }
            },
            success: function(result) {
                $('#smsSendBtn').attr("disbled","false");
                if (result.code != 1) {
                    if (result.data instanceof Array) {
                        $.each(result.data, function(index, value) {
                            swWarn(value.errorMsg);
                        });
                    } else {
                        swWarn(result.data);
                    }
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                $('#smsSendBtn').attr("disbled","false");
                swWarn(textStatus);
            }
        });
    }

    var hidePassword=true;
    function hideShowPassword(){
        if(hidePassword) {
            $("#password").attr("type","text");
            $("#showPassword").removeClass("glyphicon-eye-close");
            $("#showPassword").addClass("glyphicon-eye-open");
            hidePassword = false;
        }else{
            $("#showPassword").removeClass("glyphicon-eye-open");
            $("#showPassword").addClass("glyphicon-eye-close");
            $("#password").attr("type","password");
            hidePassword = true;
        }
    }

    var FormWidgets = function () {
        var createForm = function (formid) {
            $("#"+formid).validate({
                rules: {
                    password: {
                        required: true,
                        maxlength: 20
                    },
                    name: {
                        required: true,
                        maxlength: 20
                    },
                    company: {
                        required: true,
                        maxlength: 30
                    },
                    email: {
                        email: true,
                        maxlength: 50
                    },
                    phone: {
                        required: true,
                        maxlength: 20
                    },
                    code: {
                        required: true,
                        maxlength: 10
                    },
                    agree: {
                        required: true
                    },
                },
                submitHandler: function (form) {
                    regSubmit('regForm');
                }
            });
        }

        return {
            // public functions
            init: function (formid) {
                createForm(formid);
            }
        };
    }();

    $(document).ready(function() {
        FormWidgets.init('regForm');
        setTimeout(function(){
            $('#email').val('');
            $('#email-error').css('display','none');
        }, 500);
        $('#phone').blur(function(){
            $('#smsSendBtn').attr("disbled","true");
            checkAccount($('#phone').val());
        });

        $('#goLogin').click(function() {
            window.location.href = '${basePath}/sso/login';
        });

        $('#heightDiv').css("min-height",window.innerHeight - 220);
    });

</script>


<script src="${basePath}/resources/kuyun-admin/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>

<script src="${basePath}/resources/metronic-admin/assets/vendors/base/vendors.bundle.js" type="text/javascript"></script>
<script src="${basePath}/resources/metronic-admin/assets/demo/default/base/scripts.bundle.js" type="text/javascript"></script>

    <script>
    function swWarn(newtips)
    {
        swal({
        title: "操作提示",
        text: newtips,
        type: "warning"
        });
    }
    </script>

<jsp:include page="/resources/metronic-admin/homefooter.jsp" flush="true"/>
</div>
</body>
</html>
