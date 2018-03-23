﻿﻿<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
</head>
<body>


<subHeader>
    <!-- BEGIN: Subheader -->
    <div class="m-subheader ">
        <div class="d-flex align-items-center">
            <div class="mr-auto">
                <ul class="m-subheader__breadcrumbs m-nav m-nav--inline">
                    <li class="m-nav__item m-nav__item--home">
                        <a href="#" class="m-nav__link m-nav__link--icon">
                            <i class="m-nav__link-icon la la-home"></i>
                        </a>
                    </li>
                    <li class="m-nav__separator">
                        -
                    </li>
                    <li class="m-nav__item">
                        <a href="" class="m-nav__link">
											<span class="m-nav__link-text">
												工单统计
											</span>
                        </a>
                    </li>
                </ul>
            </div>

        </div>
    </div>
    <!-- END: Subheader -->
</subHeader>


<content>

    <div class="m-portlet m-portlet--mobile">
        <div class="m-portlet__body">
            <div class="modal-header">
                <div class="form-group m-form__group row">
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
                <div class="form-group m-form__group row">
                    ${categoryType}列表
                </div>
            </div>
            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>
</content>

<pageResources>
<script>
    function toAction(type){
        window.location = '${basePath}/manage/ticket/summary?category='+ type;
    }

    var $table = $('#table');
    $(function() {
        $table.bootstrapTable({
            url: '${basePath}/manage/ticket/list?category=${category}',
            striped: true,
            //search: true,
            searchAlign: 'left',
            toolbarAlign: 'right',
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
            maintainSelected: true,
            idField: 'ticketId',
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
                {field: 'action', width: 100, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
            ]
        });
    });
    // 格式化操作按钮
    function actionFormatter(value, row, index) {
        return [
            '<a id="detail" href="javascript:onclick="detailAction()" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="详细"><i class="la la-file-text-o"></i></a>'
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
</pageResources>
</body>
</html>