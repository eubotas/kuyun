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
		<input type="hidden" name="equipmentModelId" value="${equipmentModel.equipmentModelId}">
		<input type="hidden" name="equipmentModelPropertyId" value="${equipmentModelProperties.equipmentModelPropertyId}">
		<c:if test="${sensor != null}">
			<input type="hidden" name="sensorId" value="${sensor.sensorId}">
		</c:if>


		<div class="row">

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<select id="grmAction" name="grmAction" style="width: 100%">
							<c:forEach var="grmAction" items="${grmActions}">
								<option value="${grmAction.code}" ${sensor != null && sensor.grmAction == grmAction.code ? 'selected' : ''}>${grmAction.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="grmVariable">巨控 变量名</label>
						<input id="grmVariable" type="text" class="form-control" name="grmVariable" maxlength="20" <c:if test="${sensor != null}"> value="${sensor.grmVariable}"</c:if>>
					</div>
				</div>
			</div>

			<div id="grmVariableValueDiv" style="display:none">
				<div class="col-sm-12">
					<div class="form-group">
						<div class="fg-line">
							<label for="grmVariableValue">巨控 写变量值</label>
							<input id="grmVariableValue" type="text" class="form-control" name="grmVariableValue" maxlength="20" <c:if test="${sensor != null}"> value="${sensor.grmVariableValue}"</c:if>>
						</div>
					</div>
				</div>
			</div>


			<%--<div class="col-sm-12">--%>
				<%--<div class="form-group">--%>
					<%--<div class="fg-line">--%>
						<%--<label for="grmVariableOrder">巨控 读写变量顺序</label>--%>
						<%--<input id="grmVariableOrder" type="text" class="form-control" name="grmVariableOrder" maxlength="20" <c:if test="${sensor != null}"> value="${sensor.grmVariableOrder}"</c:if>>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>

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

$('#grmAction').change(function() {
	if ('R' == this.value){
        $('#grmVariableValueDiv').hide();
	}else {
        $('#grmVariableValueDiv').show();
	}
});
</script>