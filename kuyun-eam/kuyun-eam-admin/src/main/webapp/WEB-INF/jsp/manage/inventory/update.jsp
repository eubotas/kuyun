<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="updateDialog" class="crudDialog">
	<form id="updateForm" method="post">

		<div class="row">
				<div class="col-sm-12">
					<div class="form-group">
						<div class="fg-line">
							<select id="warehouseId" name="warehouseId" style="width: 100%">
								<c:forEach var="warehouse" items="${warehouseList}">
									<option value="${warehouse.warehouseId}" <c:if test="${warehouse.warehouseId==inventory.warehouseId}">selected</c:if>>${warehouse.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<div class="fg-line">
							<select id="inventoryId" name="inventoryId" style="width: 100%">
								<c:forEach var="inventory" items="${inventoryList}">
									<option value="${inventory.inventoryId}" <c:if test="${inventory.inventoryId==inventory.inventoryId}">selected</c:if>>${inventory.number}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<div class="fg-line">
							<select id="partId" name="partId" style="width: 100%">
								<c:forEach var="part" items="${partList}">
									<option value="${part.partId}" <c:if test="${part.partId==inventory.partId}">selected</c:if>>${part.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<div class="fg-line">
							<label for="quantity">数量</label>
							<input id="quantity" type="text" class="form-control" name="quantity" maxlength="20" value="${inventory.quantity}">
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<div class="fg-line">
							<label for="inTaskDate">入库日期</label>
							<input id="inTaskDate" type="text" class="form-control" name="inTaskDate" maxlength="20" value="${inventory.inTaskDate}">
						</div>
					</div>
				</div>
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
        url: '${basePath}/manage/inventory/update/${inventory.inventoryId}',
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