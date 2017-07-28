<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="readWriteDialog" class="crudDialog">
	<form id="sensorForm" method="post">
		<input type="hidden" name="equipmentId" value="${equipment.equipmentId}">
		<input type="hidden" name="equipmentModelPropertyId" value="${equipmentModelProperties.equipmentModelPropertyId}">
		<c:if test="${sensor != null}">
			<input type="hidden" name="sensorId" value="${sensor.sensorId}">
		</c:if>


		<div class="row">
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="salveId">从站地址</label>
						<input id="salveId" type="text" placeholder="地址范围 1 - 247" class="form-control" name="salveId" maxlength="200" <c:if test="${sensor != null}"> value="${sensor.salveId}" </c:if>>
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<select id="functionCode" name="functionCode" style="width: 100%">
							<c:forEach var="functionCode" items="${modbusFunctionCodes}">
								<option value="${functionCode.code}" ${sensor != null && sensor.functionCode == functionCode.code ? 'selected' : ''}>${functionCode.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="address">地址</label>
						<input id="address" type="text" class="form-control" name="address" maxlength="500" <c:if test="${sensor != null}"> value="${sensor.address}"</c:if>>
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="quantity">数量</label>
						<input id="quantity" type="text" class="form-control" name="quantity" maxlength="500" <c:if test="${sensor != null}"> value="${sensor.quantity}"</c:if>>
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="period">采集周期</label>
						<input id="period" type="text" class="form-control" name="period" maxlength="500" <c:if test="${sensor != null}"> value="${sensor.period}" </c:if>>
					</div>
				</div>
			</div>



		</div>

		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="readWriteDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>
function createSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/sensor/create',
        data: $('#sensorForm').serialize(),
        beforeSend: function() {
            if ($('#salveId').val() == '') {
                $('#salveId').focus();
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
				readWriteDialog.close();
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