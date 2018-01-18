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
    <title>工单统计</title>
    <jsp:include page="/resources/inc/head.jsp" flush="true"/>

</head>
<body>
<div id="main">
<div class="row">
    <div class="col-sm-3">
        <label >累计报修</label>
         <a class="waves-effect waves-button" href="javascript:;" onclick="toAction('all');"><span>${ticketSummaryVo.totalTicketCount}</span></a>
    </div>
    <div class="col-sm-2">
        <label>未派工</label>
        <a class="waves-effect waves-button" href="javascript:;" onclick="toAction('init');"><span>${ticketSummaryVo.noAppointTicketCount}</span></a>
    </div>
    <div class="col-sm-2">
        <label >维修中</label>
        <a class="waves-effect waves-button" href="javascript:;" onclick="toAction('processing');"><span>${ticketSummaryVo.processingTicketCount}</span></a>
    </div>
    <div class="col-sm-2">
        <label >未完成</label>
        <a class="waves-effect waves-button" href="javascript:;" onclick="toAction('notResolved');"><span>${ticketSummaryVo.notResolvedTicketCount}</span></a>
    </div>
    <div class="col-sm-3">
        <label >已完成</label>
        <a class="waves-effect waves-button" href="javascript:;" onclick="toAction('resolved');"><span>${ticketSummaryVo.resolvedTicketCount}</span></a>
    </div>
</div>

<div class="row">
    <hr />
</div>
    <div class="row">
        ${categoryType}列表
    </div>
<table id="table"></table>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>

<script>
    function toAction(type){
        window.location = '${basePath}/manage/ticket/summary?category='+ type;
    }
</script>

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
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'ck', checkbox: true},
                {field: 'description', title: '工单描述', sortable: true, align: 'center'},
                {field: 'priority', title: '优先级'},
                {field: 'ticketType.name', title: '工单类型'},
                {field: 'serviceman', title: '执行人'},
                {field: 'servicePhone', title: '执行人电话'},
                {field: 'customerContacts', title: '顾客'},
                {field: 'customerPhone', title: '顾客电话'},
                {field: 'status', title: '当前状态'},
                {field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
            ]
        });
    });
    // 格式化操作按钮
    function actionFormatter(value, row, index) {
        return [
            '<a class="update" href="javascript:;" onclick="detailAction()" data-toggle="tooltip" title="Detail"><i class="glyphicon glyphicon-edit"></i></a>　'
        ].join('');
    }


    // Detail
    var detailDialog;
    function detailAction() {
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
            detailDialog = $.dialog({
                animationSpeed: 300,
                title: '工单详细',
                columnClass: 'xlarge',
                content: 'url:${basePath}/manage/ticket/detail/' + rows[0].ticketId,
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