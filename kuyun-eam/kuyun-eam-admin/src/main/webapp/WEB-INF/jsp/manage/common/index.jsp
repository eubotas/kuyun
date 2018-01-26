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
	<title>数据字典管理</title>
	<jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>
<div id="main">
	<div id="toolbar">
		<shiro:hasPermission name="eam:codeValue:create"><a class="waves-effect waves-button" href="javascript:;" onclick="createAction('新增数据字典','${basePath}/manage/code/create')"><i class="zmdi zmdi-plus"></i> 新增数据字典</a></shiro:hasPermission>
		<shiro:hasPermission name="eam:codeValue:update"><a class="waves-effect waves-button" href="javascript:;" onclick="updateAction('新增数据字典','${basePath}/manage/code/update')"><i class="zmdi zmdi-edit"></i> 编辑数据字典</a></shiro:hasPermission>
		<shiro:hasPermission name="eam:codeValue:delete"><a class="waves-effect waves-button" href="javascript:;" onclick="deleteAction('新增数据字典','${basePath}/manage/code/delete')"><i class="zmdi zmdi-close"></i> 删除数据字典</a></shiro:hasPermission>
	</div>
	<table id="table"></table>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script src="${basePath}/resources/kuyun-admin/js/eamcommon.js"></script>
<script>
    var $table = $('#table');
    $(function() {
        // bootstrap table初始化
        $table.bootstrapTable({
            url: '${basePath}/manage/code/list',
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
                {field: 'category', title: '类别'},
                {field: 'code', title: 'Code'},
                {field: 'code_name', title: 'Code名称'},
                {field: 'description', title: '描述'},
                {field: 'createTime', title: '创建时间', formatter: 'timeFormatter'},
                {field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
            ]
        });
    });
    // 格式化操作按钮
    function actionFormatter(value, row, index) {
        return [
            '<a class="update" href="javascript:;" onclick="updateAction()" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　',
            '<a class="delete" href="javascript:;" onclick="deleteAction()" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>'
        ].join('');
    }
</script>

</body>
</html>