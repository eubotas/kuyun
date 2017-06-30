<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2017-06-27
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>
<div id="createDialog" class="crudDialog">
    <form id="createForm" method="post">
        <div class="form-group">
            <label for="account">用户名</label>
            <input id="account" type="text" class="form-control" name="account" maxlength="20">
            <a class="waves-effect waves-button" href="javascript:;" onclick="checkAccount();">用户名可用</a>
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input id="password" type="text" class="form-control" name="password" maxlength="32">
        </div>
        <div class="form-group">
            <label for="name">姓名</label>
            <input id="name" type="text" class="form-control" name="name" maxlength="20">
        </div>
        <div class="form-group">
            <label for="company">公司名称</label>
            <input id="company" type="text" class="form-control" name="company" maxlength="20">
        </div>

        <div class="form-group">
            <label for="email">邮箱</label>
            <input id="email" type="text" class="form-control" name="email" maxlength="50">
        </div>
        <div class="form-group">
            <label for="phone">电话</label>
            <input id="phone" type="text" class="form-control" name="phone" maxlength="20">
        </div>

        <div class="form-group">
            <label for="code">验证码</label>
            <input id="code" type="text" class="form-control" name="code" maxlength="10">
        </div>

        <div class="form-group text-right dialog-buttons">
            <a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
            <a class="waves-effect waves-button" href="javascript:;" onclick="createDialog.close();">取消</a>
        </div>
    </form>
</div>

<jsp:include page="/resources/inc/footer.jsp" flush="true"/>

<script>
    function createSubmit() {
        $.ajax({
            type: 'post',
            url: '${basePath}/sso/reg',
            data: $('#createForm').serialize(),
            beforeSend: function() {
                if ($('#username').val() == '') {
                    $('#username').focus();
                    return false;
                }
                if ($('#password').val() == '' || $('#password').val().length < 5) {
                    $('#password').focus();
                    return false;
                }
            },
            success: function(result) {
                if (result.code != 1) {
                    if (result.data instanceof Array) {
                        $.each(result.data, function(index, value) {
                            $.confirm({
                                theme: 'dark',
                                animation: 'rotateX',
                                closeAnimation: 'rotateX',
                                title: false,
                                content: value.errorMsg,
                                buttons: {
                                    confirm: {
                                        text: '确认',
                                        btnClass: 'waves-effect waves-button waves-light'
                                    }
                                }
                            });
                        });
                    } else {
                        $.confirm({
                            theme: 'dark',
                            animation: 'rotateX',
                            closeAnimation: 'rotateX',
                            title: false,
                            content: result.data,
                            buttons: {
                                confirm: {
                                    text: '确认',
                                    btnClass: 'waves-effect waves-button waves-light'
                                }
                            }
                        });
                    }
                } else {
//                    createDialog.close();
//                    $table.bootstrapTable('refresh');
                    window.location = "${basePath}/sso/login/";
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                $.confirm({
                    theme: 'dark',
                    animation: 'rotateX',
                    closeAnimation: 'rotateX',
                    title: false,
                    content: textStatus,
                    buttons: {
                        confirm: {
                            text: '确认',
                            btnClass: 'waves-effect waves-button waves-light'
                        }
                    }
                });
            }
        });
    }


    function checkAccount() {
        $.ajax({
            type: 'post',
            url: '${basePath}/sso/check_user_name',
            data: $('#createForm').serialize(),
            beforeSend: function() {
                if ($('#account').val() == '') {
                    $('#account').focus();
                    return false;
                }

            },
            success: function(result) {
                if (result.code != 1) {
                    if (result.data instanceof Array) {
                        $.each(result.data, function(index, value) {
                            $.confirm({
                                theme: 'dark',
                                animation: 'rotateX',
                                closeAnimation: 'rotateX',
                                title: false,
                                content: value.errorMsg,
                                buttons: {
                                    confirm: {
                                        text: '确认',
                                        btnClass: 'waves-effect waves-button waves-light'
                                    }
                                }
                            });
                        });
                    } else {
                        $.confirm({
                            theme: 'dark',
                            animation: 'rotateX',
                            closeAnimation: 'rotateX',
                            title: false,
                            content: result.data,
                            buttons: {
                                confirm: {
                                    text: '确认',
                                    btnClass: 'waves-effect waves-button waves-light'
                                }
                            }
                        });
                    }
                } else {
                    $.confirm({
                        theme: 'dark',
                        animation: 'rotateX',
                        closeAnimation: 'rotateX',
                        title: false,
                        content: result.data,
                        buttons: {
                            confirm: {
                                text: '确认',
                                btnClass: 'waves-effect waves-button waves-light'
                            }
                        }
                    });
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                $.confirm({
                    theme: 'dark',
                    animation: 'rotateX',
                    closeAnimation: 'rotateX',
                    title: false,
                    content: textStatus,
                    buttons: {
                        confirm: {
                            text: '确认',
                            btnClass: 'waves-effect waves-button waves-light'
                        }
                    }
                });
            }
        });
    }
</script>
</body>
</html>
