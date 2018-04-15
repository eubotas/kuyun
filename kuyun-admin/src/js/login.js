
$(function() {
    // 点击登录按钮
    $('#login-bt').click(function() {
        login();
    });
    // 回车事件
    $('#username, #password').keypress(function (event) {
        if (13 == event.keyCode) {
            	login();
        }
    });

    $('#username, #password').blur(function(){
        chechLoginBtn();
    });
    chechLoginBtn();
});

function chechLoginBtn(){
    var username = $('#username').val();
    var password = $('#password').val();
    if(username !='' && password !='') {
        $("#login-bt").css("cursor", "pointer");
        $("#login-bt").attr("disabled", false);
    }else if(username =='' || password =='') {
        $("#login-bt").attr("disabled", true);
        $("#login-bt").css("cursor", "not-allowed");
    }
}

// 登录
function login() {
    $.ajax({
        url: BASE_PATH + '/sso/login',
        type: 'POST',
        data: {
            username: $('#username').val(),
            password: $('#password').val(),
            rememberMe: $('#rememberMe').is(':checked'),
            backurl: BACK_URL
        },
        beforeSend: function() {

        },
        success: function(json){
            if (json.code == 1) {
                location.href = json.data;
            } else {
                swWarn(json.data);
                if (10101 == json.code) {
                    $('#username').focus();
                }
                if (10102 == json.code) {
                    $('#password').focus();
                }
            }
        },
        error: function(error){
            swWarn(error);
        }
    });
}

function swWarn(newtips)
{
    swal({
        title: "操作提示",
        text: newtips,
        type: "warning"
    });
}