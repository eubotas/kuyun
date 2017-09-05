<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="changeDialog" class="crudDialog">
	<form id="sensorForm" method="post">
		<input type="hidden" name="equipmentId" value="${equipment.equipmentId}">
		<input type="hidden" name="equipmentModelPropertyId" value="${equipmentModelProperties.equipmentModelPropertyId}">
		<c:if test="${sensor != null}">
			<input type="hidden" name="sensorId" value="${sensor.sensorId}">
		</c:if>

		<div class="row">
			<div class="col-sm-2">
				<div class="fg-line">
					<input id="isl" type="text"  class="form-control" name="isl"  <c:if test="${sensor != null}"> value="${sensor.isl}" </c:if>>
				</div>
			</div>
			<div class="col-sm-1">
				<div class="fg-line">
					一
				</div>
			</div>
			<div class="col-sm-2">
				<div class="fg-line">
					<input id="ish" type="text"  class="form-control" name="ish"  <c:if test="${sensor != null}"> value="${sensor.ish}" </c:if>>
				</div>
			</div>
			
			
			<div class="col-sm-1">
				<div class="fg-line">
					=>
				</div>
			</div>
			
			<div class="col-sm-2">
				<div class="fg-line">
					<input id="osl" type="text"  class="form-control" name="osl"  <c:if test="${sensor != null}"> value="${sensor.osl}" </c:if>>
				</div>
			</div>
			<div class="col-sm-1">
				<div class="fg-line">
					一
				</div>
			</div>
			<div class="col-sm-2">
				<div class="fg-line">
					 <input id="osh" type="text"  class="form-control" name="osh"  <c:if test="${sensor != null}"> value="${sensor.osh}" </c:if>>
				</div>
			</div>

		</div>

		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="changeDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>
function createSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/sensor/update',
        data: $('#sensorForm').serialize(),
        beforeSend: function() {
//            if ($('#osl').val() == '') {
//                $('#osl').focus();
//                return false;
//            }
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
                changeDialog.close();
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