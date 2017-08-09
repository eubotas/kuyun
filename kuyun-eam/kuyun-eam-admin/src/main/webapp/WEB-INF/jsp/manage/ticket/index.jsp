﻿<%@ 
page contentType="text/html; charset=utf-8"%><%@ 
taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%><%@ 
taglib uri="http://www.springframework.org/tags" prefix="spring"%><%@ 
taglib uri="http://www.springframework.org/tags/form" prefix="form"%><%@
taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>工单管理</title>
	<jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>
<div id="main">
	<div id="toolbar">
		<shiro:hasPermission name="eam:ticket:create"><a class="waves-effect waves-button" href="javascript:;" onclick="createAction()"><i class="zmdi zmdi-plus"></i> 新增工单</a></shiro:hasPermission>
		<shiro:hasPermission name="eam:ticket:update"><a class="waves-effect waves-button" href="javascript:;" onclick="updateAction()"><i class="zmdi zmdi-edit"></i> 处理工单</a></shiro:hasPermission>
	</div>
	<table id="table"></table>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
var $table = $('#table');
$(function() {
	// bootstrap table初始化
	$table.bootstrapTable({
		url: '${basePath}/manage/ticket/list?category=${category}',
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
		idField: 'ticketId',
//		sortName: 'orders',
//        sortOrder: 'desc',
		maintainSelected: true,
		toolbar: '#toolbar',
		columns: [
			{field: 'ck', checkbox: true},
			{field: 'description', title: '工单描述', sortable: true, align: 'center'},
			{field: 'priority', title: '优先级'},
			{field: 'ticketType.name', title: '工单类型'},
			{field: 'realname', title: '执行人'},
			{field: 'status', title: '当前状态'},
			{field: 'endDate', title: '指定完成日期', formatter:'timeFormatter'},
			{field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
		]
	});
});
// 格式化操作按钮
function actionFormatter(value, row, index) {
    return [
        '<a class="update" href="javascript:;" onclick="updateAction()" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　'
    ].join('');
}

// 格式化时间
function timeFormatter(value , row, index) {
	return new Date(value).toLocaleString();
}

//
// 新增
var createDialog;
function createAction() {
	createDialog = $.dialog({
		animationSpeed: 300,
		title: '新增工单',
		columnClass: 'xlarge',
		content: 'url:${basePath}/manage/ticket/create',
		onContentReady: function () {
			initMaterialInput();
			$('select').select2();
		}
	});
}
// 编辑
var updateDialog;
function updateAction() {
	var rows = $table.bootstrapTable('getSelections');
	if (rows.length != 1) {
		$.confirm({
			title: false,
			content: '请选择一条记录！',
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
		updateDialog = $.dialog({
			animationSpeed: 300,
			title: '工单处理',
			columnClass: 'xlarge',
			content: 'url:${basePath}/manage/ticket/update/' + rows[0].ticketId,
			onContentReady: function () {
				initMaterialInput();
				$('select').select2();
			}
		});
	}
}

</script>
</body>
</html>