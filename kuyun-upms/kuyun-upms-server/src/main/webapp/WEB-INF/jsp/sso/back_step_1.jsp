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

    <link href="${basePath}/resources/metronic-admin/assets/demo/default/base/style.bundle.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut icon" href="${basePath}/resources/metronic-admin/assets/demo/default/media/img/logo/favicon.ico" />

    <link href="${basePath}/resources/kuyun-admin/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/metronic-admin/assets/css/login/components-rounded.css" rel="stylesheet"/>
    <link href="${basePath}/resources/metronic-admin/assets/css/login/darkblue.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/metronic-admin/assets/css/login/login-6.css" rel="stylesheet"/>

    <style>
        .form-control-feedback{min-width:150px;}
        .form-control{with:80%;}
    </style>
</head>
<body>
<jsp:include page="/resources/metronic-admin/homeheader.jsp" flush="true"/>

<div class="m-content" id="heightDiv" style="margin: 80px; min-height:695px;">
    <!--begin::Portlet-->
    <div class="m-portlet">
        <div class="m-portlet__head">
            <div style="background-color:#fff;">
                <div class="mt-element-step">
                    <div class="row step-line">
                        <div class="col-sm-4 col-sm-offset-0 col-xs-3 col-xs-offset-2 mt-step-col first ">
                            <div id="step1" class="mt-step-number bg-blue">1</div>
                            <div id="steptext1" class="mt-step-title uppercase ">身份验证</div>
                        </div>
                        <div class="col-sm-4 col-xs-3 mt-step-col second">
                            <div id="step2" class="mt-step-number bg-white">2</div>
                            <div id="steptext2" class="mt-step-title uppercase font-grey-cascade">修改密码</div>
                        </div>
                        <div class="col-sm-4 col-xs-3 mt-step-col last">
                            <div id="step3" class="mt-step-number bg-white">3</div>
                            <div id="steptext3" class="mt-step-title uppercase font-grey-cascade">完成</div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!--begin::Form-->
        <form class="m-form m-form--fit m-form--label-align-right" name="findPwdForm" id="findPwdForm">
            <div class="m-portlet__body" id="step1Level">
                <div class="form-group m-form__group row">
                    <label class="col-form-label col-lg-3 col-sm-12">
                        手机 *
                    </label>
                    <div class="col-lg-3 col-md-3 col-sm-12">
                        <input type="text" class="form-control m-input" name="phone" id="phone" placeholder="手机号码" maxlength="20"
                               data-toggle="m-tooltip" title="" data-original-title="请填写11位有效手机号码" aria-describedby="phone-error">
                    </div>
                    <a href="javascript:;" class="btn blue ng-binding" id="smsSendBtn" onclick="smsToSend()">获取验证码</a>
                </div>
                <div class="form-group m-form__group row">
                    <label class="col-form-label col-lg-3 col-sm-12">
                        验证码 *
                    </label>
                    <div class="col-lg-4 col-md-9 col-sm-12">
                        <input type="text" class="form-control m-input" name="code" id="code" placeholder="验证码" maxlength="10"
                               data-toggle="m-tooltip" title="" data-original-title="验证码" aria-describedby="code-error">
                    </div>
                </div>
            </div>

            <div class="m-portlet__body" id="step2Level" style="display:none">
                <div class="form-group m-form__group row has-danger">
                    <label class="col-form-label col-lg-3 col-sm-12">
                        密码 *
                    </label>
                    <div class="col-lg-3 col-md-3 col-sm-12">
                        <input type="password" class="form-control m-input" name="password" id="password" placeholder="密码" maxlength="20"
                               data-toggle="m-tooltip" title="" data-original-title="密码" aria-describedby="phone-error">
                    </div>
                </div>
                <div class="form-group m-form__group row has-danger">
                    <label class="col-form-label col-lg-3 col-sm-12">
                        密码 *
                    </label>
                    <div class="col-lg-3 col-md-3 col-sm-12">
                        <input type="password" class="form-control m-input" name="confirmPassword" id="confirmPassword" placeholder="密码确认" maxlength="20"
                               data-toggle="m-tooltip" title="" data-original-title="再次输入密码" aria-describedby="phone-error">
                    </div>
                </div>
            </div>

            <div class="m-portlet__body" id="step3Level" style="display:none">
                <div class="form-group m-form__group row has-danger">
                    <label class="col-form-label col-lg-3 col-sm-12">
                        密码修改成功
                    </label>
                </div>

            </div>

            <div class="m-portlet__foot m-portlet__foot--fit">
                <div class="m-form__actions m-form__actions">
                    <div class="row">
                        <div class="col-lg-9 ml-lg-auto">
                            <button type="button" class="btn btn-primary" id="goLogin">
                                去登录
                            </button>

                            <button type="submit" class="btn btn-primary" id="nextStep">
                                下一步
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>

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

    function smsToSend(){

        if ($('#phone').val() == '') {
            swWarn("请填入手机号码");
            return;
        }
        $("#smsSendBtn").attr("disabled", true);
        minuteCountTime(600);
        $.ajax({
            type: 'post',
            url: "${basePath}/sso/send_code",
            data: {phone: $('#phone').val()},
            success: function (result) {
                // var json=$.parseJSON( result );
                // $('#code').val(json.obj);
                swWarn("验证码已经发到该手机");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                swWarn(textStatus);
                $("#smsSendBtn").attr("disabled", false);
            }
        });
    }

    var currStep = 1;
    var FormWidgets = function () {
        var createForm = function (formid) {
            $("#"+formid).validate({
                rules: {
                    phone: {
                        required: true,
                        maxlength: 20
                    },
                    code: {
                        required: true,
                        maxlength: 6
                    },
                },
                submitHandler: function (form) {
                    findPasswordSubmit('findPwdForm');
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
        FormWidgets.init('findPwdForm');

        $('#goLogin').click(function() {
            window.location.href = '${basePath}/sso/login';
        });
        $('#heightDiv').css("min-height",window.innerHeight - 220);
    });

    function findPasswordSubmit(formId){
        if(currStep == 1){
            $.ajax({
                type: 'post',
                url: "/sso/back",
                data: $('#' + formId).serialize(),
                success: function (result) {
                    if(result.code =1) {
                        $("#step1").removeClass("bg-blue");
                        $("#steptext1").addClass("font-grey-cascade");
                        $("#step1").addClass("font-grey-cascade");
                        $("#step2").removeClass("bg-white");
                        $("#step2").addClass("bg-blue");
                        $("#steptext2").removeClass("font-grey-cascade");
                        $("#step1Level").hide();
                        $('#step2Level').show();
                        currStep = currStep + 1;
                    }else{
                        swWarn(result.message);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    swWarn(textStatus);
                }
            });
        }
        if(currStep > 1) {
            if ($('#password').val() == '' || $('#confirmPassword').val() == '') {
                swWarn("密码不能为空");
            } else if ($('#password').val() != $('#confirmPassword').val()) {
                swWarn("密码不一致");
            } else {
                $.ajax({
                    type: 'post',
                    url: "/sso/back",
                    data: $('#' + formId).serialize(),
                    success: function (result) {
                        if(result.code =1) {
                            swWarn("密码找回成功");

                            $("#step2").removeClass("bg-blue");
                            $("#steptext2").addClass("font-grey-cascade");
                            $("#step2").addClass("font-grey-cascade");
                            $("#step3").removeClass("bg-white");
                            $("#step3").addClass("bg-blue");
                            $("#steptext3").removeClass("font-grey-cascade");

                            $("#step2Level").hide();
                            $('#step3Level').show();
                            $('#nextStep').hide();
                            currStep = currStep + 1;
                        }else{
                            swWarn("密码找回失败:"+result.message);
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        swWarn(textStatus);
                    }
                });
            }
        }
    }

    function minuteCountTime(time){
        var timer=null;
        var t=time;
        var m=0;
        var s=0;
        m=Math.floor(t/60%60);
        m<10&&(m='0'+m);
        s=Math.floor(t%60);
        function countDown(){
            s--;
            s<10&&(s='0'+s);
            if(s.length>=3){
                s=59;
                m="0"+(Number(m)-1);
            }
            if(m.length>=3){
                m='00';
                s='00';
                clearInterval(timer);
            }
            $('#smsSendBtn').text(m+"分钟"+s+"秒");
            if(m==0  && s==0){
                $('#smsSendBtn').text('获取验证码');
                $('#smsSendBtn').show();
            }
        }
        timer=setInterval(countDown,1000);
    }
</script>


<script src="${basePath}/resources/kuyun-admin/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>

<script src="${basePath}/resources/metronic-admin/assets/vendors/base/vendors.bundle.js" type="text/javascript"></script>
<script src="${basePath}/resources/metronic-admin/assets/demo/default/base/scripts.bundle.js" type="text/javascript"></script>

<jsp:include page="/resources/metronic-admin/homefooter.jsp" flush="true"/>
</body>
</html>
