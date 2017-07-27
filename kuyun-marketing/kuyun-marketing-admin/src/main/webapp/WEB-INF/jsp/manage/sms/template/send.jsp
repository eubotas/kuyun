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
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>
<div id="main">
	<div id="connectDialog" class="crudDialog">
		<form id="smsForm" method="post">
			<div class="form-group text-right dialog-buttons">
				<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">确认发送</a>
			</div>

			<input type="hidden" name="smsTemplateId" value="${smsTemplate.id}">
			<div class="row">
				<div class="col-sm-12">
					<div class="form-group">
						<div class="fg-line">
							<label for="sendTime">发送短信时间</label>
							<input id="sendTime" type="text" class="form-control" name="sendTime" onClick="WdatePicker({dateFmt:'yyyy/MM/dd HH:mm:ss', minDate:'%y-%M-%d',minTime:'%H:{%m+5}:%s'})">
						</div>
					</div>
				</div>
			</div>

			<table id="table"></table>

			<div class="form-group text-right dialog-buttons">
				<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">确认发送</a>
			</div>
		</form>
	</div>

</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    var $table = $('#table');
    $(function() {
        // bootstrap table初始化
        $table.bootstrapTable({
            url: '${basePath}/manage/sms/template/user/list',
            height: getHeight(),
            striped: true,
            search: false,
            showRefresh: false,
            showColumns: false,
            minimumCountColumns: 2,
            clickToSelect: true,
            detailView: true,
            detailFormatter: 'detailFormatter',
            pagination: false,
            paginationLoop: false,
            sidePagination: 'server',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: false,
            idField: 'userId',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'ck', checkbox: true},
                {field: 'realname', title: '姓名', sortable: true, align: 'center'},
                {field: 'phone', title: '电话'},
				{field: 'orgName', title: '公司名称'},
                {field: 'ctime', title: '注册时间', formatter: 'timeFormatter'},
            ]
        });
    });

    // 格式化时间
    function timeFormatter(value , row, index) {
        var dateType = "";
        var date = new Date();
        date.setTime(value);
        dateType = date.getFullYear()+"-"+getMonth(date)+"-"+getDay(date);//yyyy-MM-dd格式日期
        return dateType;
    }

    //返回 01-12 的月份值
    function getMonth(date){
        var month = "";
        month = date.getMonth() + 1; //getMonth()得到的月份是0-11
        if(month<10){
            month = "0" + month;
        }
        return month;
    }
    //返回01-30的日期
    function getDay(date){
        var day = "";
        day = date.getDate();
        if(day<10){
            day = "0" + day;
        }
        return day;
    }

    function createSubmit() {

        var rows = $table.bootstrapTable('getSelections');
        if (rows.length == 0) {
            $.confirm({
                title: false,
                content: '请至少选择一条记录！',
                autoClose: 'cancel|3000',
                backgroundDismiss: true,
                buttons: {
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        }else {
            var ids = new Array();
            for (var i in rows) {
                ids.push(rows[i].userId);
            }

            $.ajax({
                type: 'post',
                url: '${basePath}/manage/sms/create/' + ids.join('::'),
                data: $('#smsForm').serialize(),
                beforeSend: function() {
                    if ($('#sendTime').val() == '') {
                        $('#sendTime').focus();
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
                                content: result.data.errorMsg,
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
                            content: '确认发送成功',
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
    }

</script>
<script src="${basePath}/resources/kuyun-admin/plugins/My97DatePicker/WdatePicker.js"></script>

</body>
</html>