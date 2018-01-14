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
	<title>产线管理</title>
	<jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>
<div id="main">
	<div id="toolbar">
		<shiro:hasPermission name="eam:productLine:update"><a class="waves-effect waves-button" href="javascript:;" onclick="startAction()"><i class="zmdi zmdi-plus"></i> 启动</a></shiro:hasPermission>
		<shiro:hasPermission name="eam:productLine:update"><a class="waves-effect waves-button" href="javascript:;" onclick="stopAction()"><i class="zmdi zmdi-close"></i> 停止</a></shiro:hasPermission>
	</div>
	<table id="table"></table>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
var $table = $('#table');
$(function() {
	// bootstrap table初始化
	$table.bootstrapTable({
		url: '${basePath}/manage/productLine/list',
		height: getHeight(),
		striped: true,
		search: true,
		showRefresh: true,
		showColumns: true,
		minimumCountColumns: 2,
		clickToSelect: true,
		detailView: true,
		detailFormatter: 'detailFormatter',
		pagination: true,
		paginationLoop: false,
		sidePagination: 'server',
		silentSort: false,
		smartDisplay: false,
		escape: true,
		searchOnEnterKey: true,
		idField: 'productLineId',
//		sortName: 'orders',
//        sortOrder: 'desc',
		maintainSelected: true,
		toolbar: '#toolbar',
		columns: [
			{field: 'ck', checkbox: true},
			{field: 'name', title: '产线名称', sortable: true, align: 'center'},
			{field: 'collectStatus', title: '采集状态'},
			{field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
		]
	});
});
// 格式化操作按钮
function actionFormatter(value, row, index) {
    return [
        '<a class="update" href="javascript:;" onclick="startAction()" data-toggle="tooltip" title="start">启动</a>　',
        '<a class="delete" href="javascript:;" onclick="stopAction()" data-toggle="tooltip" title="stop">停止</a>'
    ].join('');
}

// 格式化时间
function timeFormatter(value , row, index) {
	return new Date(value).toLocaleString();
}
function startAction() {
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
    } else {
        var ids = new Array();
        for (var i in rows) {
            ids.push(rows[i].productLineId);
        }

        var json = { "ids" : ids.join("-")};

        console.log(json);

        $.ajax({
            type: 'post',
            url: '${basePath}/manage/productLine/collect/start/',
            data: JSON.stringify(json),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function(result) {
                $table.bootstrapTable('refresh');
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
function stopAction() {
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
	} else {
        var ids = new Array();
        for (var i in rows) {
            ids.push(rows[i].productLineId);
        }

        var json = { "ids" : ids.join("::")};

        console.log(json);


        $.ajax({
            type: 'post',
            url: '${basePath}/manage/productLine/collect/stop/',
            data: JSON.stringify(json),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function(result) {
                $table.bootstrapTable('refresh');
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
</body>
</html>