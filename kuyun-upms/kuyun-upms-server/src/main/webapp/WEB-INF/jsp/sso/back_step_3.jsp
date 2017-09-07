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
        <input type="hidden" name="username" value="${username}">

        <div class="form-group">
            <label for="password">密码</label>
            <input id="password" type="text" class="form-control" name="password" maxlength="32">
        </div>
        <div class="form-group">
            <label for="confirmPassword">密码</label>
            <input id="confirmPassword" type="text" class="form-control" name="confirmPassword" maxlength="32">
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
            url: '${basePath}/sso/back_step3',
            data: $('#createForm').serialize(),
            beforeSend: function() {

                if ($('#password').val() == '' || $('#password').val().length < 5) {
                    $('#password').focus();
                    return false;
                }

                if ($('#password').val() != $('#confirmPassword').val()){
                    alert("两次输入密码不一致请重新输入！");
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


</script>
</body>
</html>
