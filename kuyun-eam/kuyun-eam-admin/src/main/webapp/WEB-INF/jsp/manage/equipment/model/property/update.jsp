﻿<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="updateDialog" class="crudDialog">
	<form id="updateForm" method="post">

		<input type="hidden" name="equipmentModelId" value="${equipmentModelProperties.equipmentModelId}">

		<div class="row">
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="name">设备模型参数名称</label>
						<input id="name" type="text" class="form-control" name="name" maxlength="20" value="${equipmentModelProperties.name}">
					</div>
				</div>
			</div>
			<%--<div class="col-sm-12">--%>
				<%--<div class="form-group">--%>
					<%--<div class="fg-line">--%>
						<%--<label for="label">参数显示名称</label>--%>
						<%--<input id="label" type="text" class="form-control" name="label" maxlength="20" value="${equipmentModelProperties.label}">--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="unit">参数单位</label>
						<input id="unit" type="text" class="form-control" name="unit" maxlength="20" value="${equipmentModelProperties.unit}">
					</div>
				</div>
			</div>


			<%--<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="address">参数地址</label>
						<input id="address" type="text" class="form-control" name="address" maxlength="20" value="${equipmentModelProperties.address}">
					</div>
				</div>
			</div>--%>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">

						<select id="dataType" name="dataType" style="width: 100%">
							<c:forEach var="dataType" items="${dataTypes}">
								<option value="${dataType.code}" ${equipmentModelProperties.dataType.equals(dataType.code) ? 'selected' : ''}>${dataType.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">

						<select id="displayType" name="displayType" style="width: 100%">
							<c:forEach var="displayType" items="${displayTypes}">
								<option value="${displayType.code}" ${equipmentModelProperties.displayType.equals(displayType.code) ? 'selected' : ''}>${displayType.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<%--<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="alarmType">参数报警</label>
						<input id="alarmType" type="text" class="form-control" name="alarmType" maxlength="20" value="${equipmentModelProperties.alarmType}">
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="refreshPeriod">数据刷新周期</label>
						<input id="refreshPeriod" type="text" class="form-control" name="refreshPeriod" maxlength="20" value="${equipmentModelProperties.name}">
					</div>
				</div>
			</div>--%>
		</div>

		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="updateSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="updateDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>
function updateSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/equipment/model/property/update/${equipmentModelProperties.equipmentModelPropertyId}',
        data: $('#updateForm').serialize(),
        beforeSend: function() {
			if ($('#name').val() == '') {
				$('#name').focus();
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
				updateDialog.close();
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
</script>