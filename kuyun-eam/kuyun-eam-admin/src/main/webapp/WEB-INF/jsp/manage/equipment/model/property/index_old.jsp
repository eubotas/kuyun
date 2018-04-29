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
	<title>设备模型参数管理</title>
	<jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>
<div id="main">
	<div id="toolbar">
		<shiro:hasPermission name="eam:equipmentModelProperty:create"><a class="waves-effect waves-button" href="javascript:;" onclick="createAction()"><i class="zmdi zmdi-plus"></i> 新增设备模型参数</a></shiro:hasPermission>
		<shiro:hasPermission name="eam:equipmentModelProperty:update"><a class="waves-effect waves-button" href="javascript:;" onclick="updateAction()"><i class="zmdi zmdi-edit"></i> 编辑设备模型参数</a></shiro:hasPermission>
		<shiro:hasPermission name="eam:equipmentModelProperty:delete"><a class="waves-effect waves-button" href="javascript:;" onclick="deleteAction()"><i class="zmdi zmdi-close"></i> 删除设备模型参数</a></shiro:hasPermission>
	</div>
	<table id="table"></table>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
var $table = $('#table');
$(function() {
	// bootstrap table初始化
	$table.bootstrapTable({
		url: '${basePath}/manage/equipment/model/property/list/${id}',
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
		idField: 'equipmentModelPropertyId',
//		sortName: 'orders',
//        sortOrder: 'desc',
		maintainSelected: true,
		toolbar: '#toolbar',
		columns: [
			{field: 'ck', checkbox: true},
			{field: 'equipmentModelPropertyId', title: '设备模型参数ID', sortable: true, align: 'center'},
			{field: 'name', title: '参数名称'},
			{field: 'dataType', title: '数据类型'},
			{field: 'unit', title: '参数单位'},
			/*{field: 'refreshPeriod', title: '刷新周期'},*/
            {field: 'action', title: '读写指令', align: 'center', formatter: 'readWriteFormatter', events: 'actionEvents', clickToSelect: false},
            {field: 'action', title: '数据转换', align: 'center', formatter: 'changeFormatter', events: 'actionEvents', clickToSelect: false},
            {field: 'action', title: '报警设定', align: 'center', formatter: 'alarmFormatter', events: 'actionEvents', clickToSelect: false}
//            {field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
		]
	});
});

// 格式化操作按钮
function readWriteFormatter(value, row, index) {
    return [
        '<a class="update" href="javascript:;" onclick="readWriteAction()" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　'
    ].join('');
}

function changeFormatter(value, row, index) {
    return [
        '<a class="update" href="javascript:;" onclick="changeAction()" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　'
    ].join('');
}

function alarmFormatter(value, row, index) {
    return [
        '<a class="update" href="javascript:;" onclick="alarmAction()" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　'
    ].join('');
}

var readWriteDialog;
function readWriteAction() {
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
        var readWriteUrl = '${basePath}/manage/equipment/model/property/modbus/${equipmentModel.equipmentModelId}/' + rows[0].equipmentModelPropertyId;
        if (1 == ${equipmentModel.protocolId}){
            readWriteUrl = '${basePath}/manage/equipment/model/property/modbus/${equipmentModel.equipmentModelId}/' + rows[0].equipmentModelPropertyId;
        }else if (4 == ${equipmentModel.protocolId}){
            readWriteUrl = '${basePath}/manage/equipment/model/property/grm/${equipmentModel.equipmentModelId}/' + rows[0].equipmentModelPropertyId;
        }
        readWriteDialog = $.dialog({
            animationSpeed: 300,
            title: '读写指令',
            columnClass: 'xlarge',
            content: 'url:'+readWriteUrl,
            onContentReady: function () {
                initMaterialInput();
                $('select').select2({minimumResultsForSearch: -1});
            }
        });
    }
}

var changeDialog;
function changeAction() {
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
        var changeUrl = '${basePath}/manage/equipment/model/property/data/change/${equipmentModel.equipmentModelId}/' + rows[0].equipmentModelPropertyId;

        changeDialog = $.dialog({
            animationSpeed: 300,
            title: '数据转换',
            columnClass: 'xlarge',
            content: 'url:'+changeUrl,
            onContentReady: function () {
                initMaterialInput();
                $('select').select2({minimumResultsForSearch: -1});
            }
        });
    }
}

var alarmDialog;
function alarmAction() {
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
        var alarmUrl = '${basePath}/manage/equipment/model/property/alarm/${equipmentModel.equipmentModelId}/' + rows[0].equipmentModelPropertyId;

        alarmDialog = $.dialog({
            animationSpeed: 300,
            title: '报警设定',
            columnClass: 'xlarge',
            content: 'url:'+alarmUrl,
            onContentReady: function () {
                initMaterialInput();
                $('select').select2({minimumResultsForSearch: -1});
            }
        });
    }
}


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
		title: '新增设备模型参数',
		columnClass: 'xlarge',
		content: 'url:${basePath}/manage/equipment/model/property/create/${id}',
		onContentReady: function () {
			initMaterialInput();
			$('select').select2({minimumResultsForSearch: -1});
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
			title: '编辑设备模型参数',
			columnClass: 'xlarge',
			content: 'url:${basePath}/manage/equipment/model/property/update/' + rows[0].equipmentModelPropertyId,
			onContentReady: function () {
				initMaterialInput();
				$('select').select2({minimumResultsForSearch: -1});
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
			content: '确认删除该设备模型参数吗？',
			buttons: {
				confirm: {
					text: '确认',
					btnClass: 'waves-effect waves-button',
					action: function () {
						var ids = new Array();
						for (var i in rows) {
							ids.push(rows[i].equipmentModelPropertyId);
						}
						$.ajax({
							type: 'get',
							url: '${basePath}/manage/equipment/model/property/delete/' + ids.join("-"),
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
</script>
</body>
</html>