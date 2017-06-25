<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="createDialog" class="crudDialog">
	<form id="createForm" method="post">
		<input type="hidden" name="userId" value="1">
		<input type="hidden" name="equipmentModelId" value="${id}">
		<div class="row">
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="name">设备模型参数名称</label>
						<input id="name" type="text" class="form-control" name="name" maxlength="20">
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="lable">参数显示名称</label>
						<input id="lable" type="text" class="form-control" name="lable" maxlength="20">
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="unit">参数单位</label>
						<input id="unit" type="text" class="form-control" name="unit" maxlength="20">
					</div>
				</div>
			</div>

			<%--<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="address">参数地址</label>
						<input id="address" type="text" class="form-control" name="address" maxlength="20">
					</div>
				</div>
			</div>--%>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="dataType">数据类型</label>
						<input id="dataType" type="text" class="form-control" name="dataType" maxlength="20">
					</div>
				</div>
			</div>
			<%--<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="alarmType">参数报警</label>
						<input id="alarmType" type="text" class="form-control" name="alarmType" maxlength="20">
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="refreshPeriod">数据刷新周期</label>
						<input id="refreshPeriod" type="text" class="form-control" name="refreshPeriod" maxlength="20">
					</div>
				</div>
			</div>--%>
		</div>

		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="createDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>
function createSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/equipment/model/property/create',
        data: $('#createForm').serialize(),
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
				createDialog.close();
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