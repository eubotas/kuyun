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
	<title>设备管理</title>
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
		<shiro:hasPermission name="eam:equipment:create"><a class="waves-effect waves-button" href="javascript:;" onclick="createAction()"><i class="zmdi zmdi-plus"></i> 新增设备</a></shiro:hasPermission>
		<shiro:hasPermission name="eam:equipment:update"><a class="waves-effect waves-button" href="javascript:;" onclick="updateAction()"><i class="zmdi zmdi-edit"></i> 编辑设备</a></shiro:hasPermission>
		<shiro:hasPermission name="eam:equipment:delete"><a class="waves-effect waves-button" href="javascript:;" onclick="deleteAction()"><i class="zmdi zmdi-close"></i> 删除设备</a></shiro:hasPermission>
		<shiro:hasPermission name="eam:equipment:update"><a class="waves-effect waves-button" href="javascript:;" onclick="connectAction()"><i class="zmdi zmdi-plus"></i> 设备接入</a></shiro:hasPermission>
		<shiro:hasPermission name="eam:equipmentSensor:write"><a class="waves-effect waves-button" href="javascript:;" onclick="sensorAction()"><i class="zmdi zmdi-plus"></i> 数据写入</a></shiro:hasPermission>
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
		url: '${basePath}/manage/equipment/list',
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
		idField: 'equipmentId',
//		sortName: 'orders',
//        sortOrder: 'desc',
		maintainSelected: true,
		toolbar: '#toolbar',
		columns: [
			{field: 'ck', checkbox: true},
			{field: 'name', title: '设备名称', sortable: true, align: 'center'},
			{field: 'number', title: '设备编号'},
			{field: 'maintenancePeriod', title: '维保周期'},
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

// 格式化时间
function timeFormatter(value , row, index) {
	return new Date(value).toLocaleString();
}
// 新增
var createDialog;
function createAction() {
	createDialog = $.dialog({
		animationSpeed: 300,
		title: '新增设备',
		columnClass: 'xlarge',
		content: 'url:${basePath}/manage/equipment/create',
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
			title: '编辑设备',
			columnClass: 'xlarge',
			content: 'url:${basePath}/manage/equipment/update/' + rows[0].equipmentId,
			onContentReady: function () {
				initMaterialInput();
				$('select').select2();
			}
		});
	}
}
// 删除
var deleteDialog;
function deleteAction() {
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
		deleteDialog = $.confirm({
			type: 'red',
			animationSpeed: 300,
			title: false,
			content: '确认删除该设备吗？',
			buttons: {
				confirm: {
					text: '确认',
					btnClass: 'waves-effect waves-button',
					action: function () {
						var ids = new Array();
						for (var i in rows) {
							ids.push(rows[i].equipmentId);
						}
						$.ajax({
							type: 'get',
							url: '${basePath}/manage/equipment/delete/' + ids.join("-"),
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
									deleteDialog.close();
									$table.bootstrapTable('refresh');
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
				},
				cancel: {
					text: '取消',
					btnClass: 'waves-effect waves-button'
				}
			}
		});
	}
}

var connectDialog;
function connectAction() {
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
        connectDialog = $.dialog({
            animationSpeed: 300,
            title: '设备接入',
            columnClass: 'xlarge',
            content: 'url:${basePath}/manage/equipment/connect/' + rows[0].equipmentId,
            onContentReady: function () {
                initMaterialInput();
                $('select').select2();
            }
        });
    }
}

function sensorAction() {
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

        window.location = "${basePath}/manage/equipment/sensor/" + rows[0].equipmentId;
    }
}

</script>
</body>
</html>