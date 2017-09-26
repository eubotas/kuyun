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
			<div class="row">
				<div class="fg-line">
					<label for="protocolId">设备协议: <c:out value="${protocol.name}"/></label>
				</div>
			</div>

			<div class="row">
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
								<label for="modbusRtuPeriod">采集频率</label>
								<input id="modbusRtuPeriod" type="text" class="form-control" name="modbusRtuPeriod" maxlength="10" <c:if test="${equipment.modbusRtuPeriod != null}"> value="${equipment.modbusRtuPeriod}"</c:if>>
							</div>
						</div>
					</div>

					<div class="col-sm-12">
						<div class="form-group">
							<div class="fg-line">
								<label for="IP">IP: <c:out value="${protocol.ip}"/></label>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="form-group">
							<div class="fg-line">
								<label for="port">端口号: <c:out value="${protocol.port}"/></label>
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
			</div>
			<div class="row">
				<div id="modbusTcpDiv" style="display:none">
					<div class="col-sm-12">
						<div class="form-group">
							<div class="fg-line">
								<label for="IP">IP: <c:out value="${protocol.ip}"/></label>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="form-group">
							<div class="fg-line">
								<label for="port">端口号: <c:out value="${protocol.port}"/></label>
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
			</div>
			<div class="row">
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

                    connectDialog.close();
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

//    function onload() {
        $('#modbusRtuDiv').hide();
        $('#modbusTcpDiv').hide();
        $('#grmDiv').hide();

        var protocolId = <c:out value="${protocol.protocolId}"/>;

        console.log("protocolId="+protocolId);

        if (1 == protocolId){
            $('#modbusRtuDiv').show();

		}else if (2 == protocolId){
            $('#modbusTcpDiv').show();

		}else if (4 == protocolId){
            $('#grmDiv').show();

		}
//	}
</script>
</body>
</html>