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
	<title>工单类型管理</title>
	<jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>
<div id="main">
	<div id="toolbar">
		<shiro:hasPermission name="eam:ticketType:create"><a class="waves-effect waves-button" href="javascript:;" onclick="createAction('创建工单类型','${basePath}/manage/ticket/type/create')"><i class="zmdi zmdi-plus"></i> 新增工单类型</a></shiro:hasPermission>
		<shiro:hasPermission name="eam:ticketType:update"><a class="waves-effect waves-button" href="javascript:;" onclick="updateAction('编辑工单类型','${basePath}/manage/ticket/type/update/')"><i class="zmdi zmdi-edit"></i> 编辑工单类型</a></shiro:hasPermission>
		<shiro:hasPermission name="eam:ticketType:delete"><a class="waves-effect waves-button" href="javascript:;" onclick="deleteAction('删除工单类型','${basePath}/manage/ticket/type/delete/')"><i class="zmdi zmdi-close"></i> 删除工单类型</a></shiro:hasPermission>
	</div>
	<table id="table"></table>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script src="/resources/inc/eamcommon.js"></script>
<script>
var $table = $('#table');
$(function() {
	// bootstrap table初始化
	$table.bootstrapTable({
		url: '${basePath}/manage/ticket/type/list',
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
		idField: 'id',
//		sortName: 'orders',
//        sortOrder: 'desc',
		maintainSelected: true,
		toolbar: '#toolbar',
		columns: [
			{field: 'ck', checkbox: true},
			{field: 'id', title: 'ID', sortable: true, align: 'center'},
			{field: 'name', title: '名称'},
			{field: 'createTime', title: '创建时间', formatter: 'timeFormatter'},
			{field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
		]
	});
});
// 格式化操作按钮
function actionFormatter(value, row, index) {
    return [
        '<a class="update" href="javascript:;" onclick="updateAction(\'编辑工单类型\',\'${basePath}/manage/ticket/type/update/\')" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　',
        '<a class="delete" href="javascript:;" onclick="deleteAction(\'删除工单类型\',\'${basePath}/manage/ticket/type/delete/\')" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>'
    ].join('');
}

// 格式化时间
function timeFormatter(value , row, index) {
	return new Date(value).toLocaleString();
}

</script>
</body>
</html>