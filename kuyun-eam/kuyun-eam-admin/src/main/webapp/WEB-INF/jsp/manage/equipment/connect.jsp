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
		<form id="connectForm" method="post">

			<input type="hidden" name="equipmentId" value="${equipment.equipmentId}">
			<div class="row">
				<div class="col-sm-12">
					<div class="form-group">
						<div class="fg-line">
							<label for="name">设备名称</label>
							<input id="name" type="text" class="form-control" name="name" maxlength="200" value="${equipment.name}" readonly>
						</div>
					</div>
				</div>

				<div class="col-sm-12">
					<div class="form-group">
						<div class="fg-line">
							<select id="protocolId" name="protocolId" style="width: 100%">
								<option value=""></option>
								<c:forEach var="protocol" items="${protocols}">
									<option value="${protocol.protocolId}" ${equipment.protocolId == protocol.protocolId ? 'selected' : ''}>${protocol.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>

				<div id="modbusRtuDiv" style="display:none">
					<div class="col-sm-12">
						<div class="form-group">
							<div class="fg-line">
								<label for="heartData">心跳包格式</label>
								<input id="heartData" type="text" class="form-control" name="heartData" maxlength="50" <c:if test="${equipment.heartData != null}"> value="${equipment.heartData}"</c:if>>
							</div>
						</div>
					</div>

					<div class="col-sm-12">
						<div class="form-group">
							<div class="fg-line">
								<label for="IP">IP: <c:out value="${protocols[0].ip}"/></label>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="form-group">
							<div class="fg-line">
								<label for="port">端口号: <c:out value="${protocols[0].port}"/></label>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="form-group">
							<div class="fg-line">
								<label for="serviceId">接入注册包: ${equipment.equipmentId}</label>
							</div>
						</div>
					</div>
				  </div>

				<div id="modbusTcpDiv" style="display:none">
					<div class="col-sm-12">
						<div class="form-group">
							<div class="fg-line">
								<label for="IP">IP: <c:out value="${protocols[1].ip}"/></label>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="form-group">
							<div class="fg-line">
								<label for="port">端口号: <c:out value="${protocols[1].port}"/></label>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="form-group">
							<div class="fg-line">
								<label for="serviceId">接入注册包: ${equipment.equipmentId}</label>
							</div>
						</div>
					</div>
				</div>

				<div id="grmDiv" style="display:none">
					<div class="col-sm-12">
						<div class="form-group">
							<div class="fg-line">
								<label for="grm">巨控设备ID</label>
								<input id="grm" type="text" class="form-control" name="grm" maxlength="50" <c:if test="${equipment.grm != null}"> value="${equipment.grm}"</c:if>>
							</div>
						</div>
					</div>

					<div class="col-sm-12">
						<div class="form-group">
							<div class="fg-line">
								<label for="grmPassword">巨控设备密码</label>
								<input id="grmPassword" type="text" class="form-control" name="grmPassword" maxlength="50" <c:if test="${equipment.grmPassword != null}"> value="${equipment.grmPassword}"</c:if>>
							</div>
						</div>
					</div>

					<div class="col-sm-12">
						<div class="form-group">
							<div class="fg-line">
								<label for="grmPeriod">巨控设备采集频率(单位秒)</label>
								<input id="grmPeriod" type="text" class="form-control" name="grmPeriod" maxlength="50" <c:if test="${equipment.grmPeriod != null}"> value="${equipment.grmPeriod}"</c:if>>
							</div>
						</div>
					</div>


				</div>


			</div>

			<div class="form-group text-right dialog-buttons">
				<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
				<a class="waves-effect waves-button" href="javascript:;" onclick="connectDialog.close();">取消</a>
			</div>
		</form>
	</div>
	<table id="table"></table>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    var $table = $('#table');
    $(function() {
        // bootstrap table初始化
        $table.bootstrapTable({
            url: '${basePath}/manage/equipment/model/property/list/${equipment.equipmentModelId}',
            height: getHeight(),
            striped: true,
            search: true,
            showRefresh: false,
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
            searchOnEnterKey: false,
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
                {field: 'action', title: '读写指令', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false},
                {field: 'action', title: '数据转换', align: 'center', formatter: 'changeFormatter', events: 'actionEvents', clickToSelect: false},
                {field: 'action', title: '报警设定', align: 'center', formatter: 'alarmFormatter', events: 'actionEvents', clickToSelect: false}
            ]
        });
    });
    // 格式化操作按钮
    function actionFormatter(value, row, index) {
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
            var readWriteUrl = '${basePath}/manage/equipment/modbus/${equipment.equipmentId}/' + rows[0].equipmentModelPropertyId;
            if (1 == $('#protocolId').val()){
                readWriteUrl = '${basePath}/manage/equipment/modbus/${equipment.equipmentId}/' + rows[0].equipmentModelPropertyId;
            }else if (4 == $('#protocolId').val()){
                readWriteUrl = '${basePath}/manage/equipment/grm/${equipment.equipmentId}/' + rows[0].equipmentModelPropertyId;

            }
            readWriteDialog = $.dialog({
                animationSpeed: 300,
                title: '读写指令',
                columnClass: 'xlarge',
                content: 'url:'+readWriteUrl,
                onContentReady: function () {
                    initMaterialInput();
                    $('select').select2();
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
            var changeUrl = '${basePath}/manage/equipment/data/change/${equipment.equipmentId}/' + rows[0].equipmentModelPropertyId;

            changeDialog = $.dialog({
                animationSpeed: 300,
                title: '数据转换',
                columnClass: 'xlarge',
                content: 'url:'+changeUrl,
                onContentReady: function () {
                    initMaterialInput();
                    $('select').select2();
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
            var alarmUrl = '${basePath}/manage/alarm/${equipment.equipmentId}/' + rows[0].equipmentModelPropertyId;

            alarmDialog = $.dialog({
                animationSpeed: 300,
                title: '报警设定',
                columnClass: 'xlarge',
                content: 'url:'+alarmUrl,
                onContentReady: function () {
                    initMaterialInput();
                    $('select').select2();
                }
            });
        }
    }
    
    function checkForm() {
        if (1 == $('#protocolId').val()){
            if ($('#heartData').val() == '') {
                $('#heartData').focus();
                return false;
            }
		}else if (4 == $('#protocolId').val()){
            if ($('#grm').val() == '') {
                $('#grm').focus();
                return false;
            }else if ($('#grmPassword').val() == '') {
                $('#grmPassword').focus();
                return false;
            }else if ($('#grmPeriod').val() == '') {
                $('#grmPeriod').focus();
                return false;
            }
        }

        return true;
    }

    function createSubmit() {
        $.ajax({
            type: 'post',
            url: '${basePath}/manage/equipment/connect/${equipment.equipmentId}',
            data: $('#connectForm').serialize(),
            beforeSend: function() {
                if(!checkForm()){
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
                        content: '保存成功',
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

    $('#protocolId').change(function() {
        $('#modbusRtuDiv').hide();
        $('#modbusTcpDiv').hide();
        $('#grmDiv').hide();

        if (1 == this.value){
            $('#modbusRtuDiv').show();

		}else if (2 == this.value){
            $('#modbusTcpDiv').show();

		}else if (4 == this.value){
            $('#grmDiv').show();

		}

    });
</script>
</body>
</html>