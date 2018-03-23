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
	<link href="${basePath}/resources/kuyun-admin/plugins/fileupload/fine-uploader-gallery.css" rel="stylesheet">
	
	  <!-- Fine Uploader Gallery template
    ====================================================================== -->
    <script type="text/template" id="qq-template-gallery">
        <div class="qq-uploader-selector qq-uploader qq-gallery" qq-drop-area-text="Drop files here">
            <div class="qq-total-progress-bar-container-selector qq-total-progress-bar-container">
                <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-total-progress-bar-selector qq-progress-bar qq-total-progress-bar"></div>
            </div>
            <div class="qq-upload-drop-area-selector qq-upload-drop-area" qq-hide-dropzone>
                <span class="qq-upload-drop-area-text-selector"></span>
            </div>
            <div class="qq-upload-button-selector qq-upload-button">
                <div>上传文件</div>
            </div>
            <span class="qq-drop-processing-selector qq-drop-processing">
                <span>Processing dropped files...</span>
                <span class="qq-drop-processing-spinner-selector qq-drop-processing-spinner"></span>
            </span>
            <ul class="qq-upload-list-selector qq-upload-list" role="region" aria-live="polite" aria-relevant="additions removals">
                <li>
                    <span role="status" class="qq-upload-status-text-selector qq-upload-status-text"></span>
                    <div class="qq-progress-bar-container-selector qq-progress-bar-container">
                        <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-progress-bar-selector qq-progress-bar"></div>
                    </div>
                    <span class="qq-upload-spinner-selector qq-upload-spinner"></span>
                    <div class="qq-thumbnail-wrapper">
                        <img class="qq-thumbnail-selector" qq-max-size="120" qq-server-scale>
                    </div>
                    <button type="button" class="qq-upload-cancel-selector qq-upload-cancel">X</button>
                    <button type="button" class="qq-upload-retry-selector qq-upload-retry">
                        <span class="qq-btn qq-retry-icon" aria-label="Retry"></span>
                        Retry
                    </button>

                    <div class="qq-file-info">
                        <div class="qq-file-name">
                            <span class="qq-upload-file-selector qq-upload-file"></span>
                            <span class="qq-edit-filename-icon-selector qq-edit-filename-icon" aria-label="Edit filename"></span>
                        </div>
                        <input class="qq-edit-filename-selector qq-edit-filename" tabindex="0" type="text">
                        <span class="qq-upload-size-selector qq-upload-size"></span>
                        <button type="button" class="qq-btn qq-upload-delete-selector qq-upload-delete">
                            <span class="qq-btn qq-delete-icon" aria-label="Delete"></span>
                        </button>
                        <button type="button" class="qq-btn qq-upload-pause-selector qq-upload-pause">
                            <span class="qq-btn qq-pause-icon" aria-label="Pause"></span>
                        </button>
                        <button type="button" class="qq-btn qq-upload-continue-selector qq-upload-continue">
                            <span class="qq-btn qq-continue-icon" aria-label="Continue"></span>
                        </button>
                    </div>
                </li>
            </ul>

            <dialog class="qq-alert-dialog-selector">
                <div class="qq-dialog-message-selector"></div>
                <div class="qq-dialog-buttons">
                    <button type="button" class="qq-cancel-button-selector">Close</button>
                </div>
            </dialog>

            <dialog class="qq-confirm-dialog-selector">
                <div class="qq-dialog-message-selector"></div>
                <div class="qq-dialog-buttons">
                    <button type="button" class="qq-cancel-button-selector">No</button>
                    <button type="button" class="qq-ok-button-selector">Yes</button>
                </div>
            </dialog>

            <dialog class="qq-prompt-dialog-selector">
                <div class="qq-dialog-message-selector"></div>
                <input type="text">
                <div class="qq-dialog-buttons">
                    <button type="button" class="qq-cancel-button-selector">Cancel</button>
                    <button type="button" class="qq-ok-button-selector">Ok</button>
                </div>
            </dialog>
        </div>
    </script>
    
</head>
<body>
<div id="main">
	<div id="toolbar">
        <shiro:hasPermission name="eam:ticket:create"><a class="waves-effect waves-button" href="javascript:;" onclick="createAction()"><i class="zmdi zmdi-plus"></i> 新增工单</a></shiro:hasPermission>
        <shiro:hasPermission name="eam:ticket:create"><a class="waves-effect waves-button" href="javascript:;" onclick="toaction('TOASSESSMENT')"><i class="zmdi zmdi-edit"></i> 评价工单</a></shiro:hasPermission>

        <!-- 维修人员 -->
        <shiro:hasPermission name="eam:ticket:create"><a class="waves-effect waves-button" href="javascript:;" onclick="toaction('REJECT')"><i class="zmdi zmdi-edit"></i> 拒绝工单</a></shiro:hasPermission>
        <shiro:hasPermission name="eam:ticketAppointedRecord:create"><a class="waves-effect waves-button" href="javascript:;" onclick="toaction('TORECORD')"><i class="zmdi zmdi-edit"></i> 处理工单</a></shiro:hasPermission>
        <shiro:hasPermission name="eam:ticket:create"><a class="waves-effect waves-button" href="javascript:;" onclick="toaction('COMPLETE')"><i class="zmdi zmdi-edit"></i> 完成工单</a></shiro:hasPermission>

        <!-- manager -->
        <shiro:hasPermission name="eam:ticketRecord:update"><a class="waves-effect waves-button" href="javascript:;" onclick="toaction('RECORD')"><i class="zmdi zmdi-edit"></i> 工单记录管理</a></shiro:hasPermission>
        <shiro:hasPermission name="eam:ticketAppointedRecord:create"><a class="waves-effect waves-button" href="javascript:;" onclick="toaction('APPOINT')"><i class="zmdi zmdi-edit"></i> 工单委派管理</a></shiro:hasPermission>
        <shiro:hasPermission name="eam:ticketAssessment:create"><a class="waves-effect waves-button" href="javascript:;" onclick="toaction('ASSESSMENT')"><i class="zmdi zmdi-edit"></i> 工单评价管理</a></shiro:hasPermission>
        <shiro:hasPermission name="eam:ticketAppointedRecord:create"><a class="waves-effect waves-button" href="javascript:;" onclick="toaction('TOAPPOINT')"><i class="zmdi zmdi-edit"></i> 委派工单</a></shiro:hasPermission>

        </div>
	<table id="table"></table>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script src="${basePath}/resources/kuyun-admin/plugins/fileupload/fine-uploader.js"></script>
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
        '<a class="update" href="javascript:;" onclick="detailAction()" data-toggle="tooltip" title="Detail"><i class="glyphicon glyphicon-edit"></i></a>　',
        '<a class="update" href="javascript:;" onclick="updateAction()" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　',
        '<a class="delete" href="javascript:;" onclick="deleteAction()" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>',
        '<a class="update" href="javascript:;" onclick="rejectRecordAction()" data-toggle="tooltip" title="Reject Record"><i class="glyphicon glyphicon-edit"></i></a>　'
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
			title: '修改工单',
			columnClass: 'xlarge',
			content: 'url:${basePath}/manage/ticket/update/' + rows[0].ticketId,
			onContentReady: function () {
				initMaterialInput();
				$('select').select2();
			}
		});
	}
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

// rejectRecordAction
var rejectRecordDialog;
function rejectRecordAction() {
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
        rejectRecordDialog = $.dialog({
            animationSpeed: 300,
            title: '工单委派拒绝记录',
            columnClass: 'xlarge',
            content: 'url:${basePath}/manage/ticket/rejectRecord/' + rows[0].ticketId,
            onContentReady: function () {
                initMaterialInput();
                $('select').select2();
            }
        });
    }
}

function toaction(type) {
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
        var ticketId= rows[0].ticketId;
        var status= rows[0].status;
        if('RECORD'==type)
            window.location = '${basePath}/manage/ticket/' +ticketId  + '/record/index';
        else if('APPOINT'==type)
            window.location = '${basePath}/manage/ticket/' + ticketId + '/appoint/index';
        else if('ASSESSMENT'==type){
            window.location = '${basePath}/manage/ticket/' + ticketId + '/assessment/index';
        }
        else if('TOAPPOINT'==type){
            if(status == '待派工')
                createChildWindow('appoint', '委派工单', '${basePath}/manage/ticket/' + ticketId + '/appoint/create');
            else
                showInfo('未委派的订单才能委派');
        }else if('TOASSESSMENT'==type){
            if(status == '待评价')
                createChildWindow('assessment', '评价', '${basePath}/manage/ticket/' + ticketId + '/assessment/assess');
            else
                showInfo('完成状态的订单才能评价');
        }
        else if('TORECORD'==type)
            createChildWindow('record', '处理工单', '${basePath}/manage/ticket/' +ticketId  + '/record/create');
        else if('REJECT'==type){
            if(status == '待维修')
                createChildWindow('reject', '拒绝工单', '${basePath}/manage/ticket/' + ticketId + '/appoint/toreject');
            else
                showInfo('已委派的订单才能拒绝');
        }
        else if('COMPLETE'==type){
            if(status == '维修中')
                directlyAction('完成工单',  '${basePath}/manage/ticket/complete/' + ticketId);
            else
                showInfo('维修状态的订单才能完成工单');
        }
    }
}

var appointDialog, rejectDialog, assessmentDialog, recordDialog;
function createChildWindow(vardialog, title, url) {
    var d = $.dialog({
        animationSpeed: 300,
        title: title,
        columnClass: 'xlarge',
        content: 'url:'+url,
        onContentReady: function () {
            initMaterialInput();
            $('select').select2();
        }
    });
    if(vardialog == 'appoint'){
        appointDialog = d;
        rejectDialog  =null;
        assessmentDialog  =null;
        recordDialog  =null;
    }else if(vardialog == 'reject'){
        rejectDialog=d;
        appointDialog  =null;
        assessmentDialog  =null;
        recordDialog  =null;
    }else if(vardialog == 'assessment'){
        assessmentDialog =d;
        appointDialog  =null;
        rejectDialog  =null;
        recordDialog  =null;
    }else if(vardialog == 'record'){
        recordDialog =d;
        appointDialog  =null;
        assessmentDialog  =null;
        rejectDialog  =null;
    }
}

var directlyOperateDialog;
function directlyAction(name,url) {
        directlyOperateDialog = $.confirm({
            type: 'red',
            animationSpeed: 300,
            title: false,
            content: '确认'+name+'吗？',
            buttons: {
                confirm: {
                    text: '确认',
                    btnClass: 'waves-effect waves-button',
                    action: function () {
                        $.ajax({
                            type: 'get',
                            url: url,
                            success: function(result) {
                                if (result.code != 1) {
                                    showInfo('failure');
                                } else {
                                    directlyOperateDialog.close();
                                    showInfo('success');
                                    $table.bootstrapTable('refresh');
                                }
                            },
                            error: function(XMLHttpRequest, textStatus, errorThrown) {
                                showInfo(textStatus);
                            }
                        });
                    }
                },
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
}

function showInfo( textStatus) {
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

// 删除
var deleteDialog;
function deleteAction() {
    var rows = $table.bootstrapTable('getSelections');
    if (rows.length == 0) {
        $.confirm({
            title: false,
            content: '请至少选择一条评价！',
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
        deleteDialog = $.confirm({
            type: 'red',
            animationSpeed: 300,
            title: false,
            content: '确认删除该评价评价吗？',
            buttons: {
                confirm: {
                    text: '确认',
                    btnClass: 'waves-effect waves-button',
                    action: function () {
                        var ids = new Array();
                        for (var i in rows) {
                            ids.push(rows[i].ticketId);
                        }
                        $.ajax({
                            type: 'get',
                            url: '${basePath}/manage/ticket/delete/' + ids.join("-"),
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
                                        showInfo('错误');
                                    }
                                } else {
                                    deleteDialog.close();
                                    $table.bootstrapTable('refresh');
                                }
                            },
                            error: function(XMLHttpRequest, textStatus, errorThrown) {
                                showInfo(textStatus);
                            }
                        });
                    }
                },
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
    }
}
</script>
</body>
</html>