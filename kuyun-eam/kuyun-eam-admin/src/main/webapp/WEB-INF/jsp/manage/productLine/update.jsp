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
						<label for="name">名称</label>
						<input id="name" type="text" class="form-control" name="name" maxlength="20" value="${eamProductLine.name}">
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="imagePath">图片</label>
						<input id="imagePath" type="text" class="form-control" name="imagePath" maxlength="20" value="${eamProductLine.imagePath}">
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="morningShiftTime">早班</label>
						<input id="morningShiftStartTime" type="text" class="form-control" name="morningShiftStartTime" maxlength="20" value="${eamProductLine.morningShiftStartTime}">
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<input id="morningShiftEndTime" type="text" class="form-control" name="morningShiftEndTime" maxlength="20" value="${eamProductLine.morningShiftEndTime}">
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="middleShiftTime">中班</label>
						<input id="middleShiftStartTime" type="text" class="form-control" name="middleShiftStartTime" maxlength="20" value="${eamProductLine.middleShiftStartTime}">
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<input id="middleShiftEndTime" type="text" class="form-control" name="middleShiftEndTime" maxlength="20" value="${eamProductLine.middleShiftEndTime}">
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="nightShiftTime">晚班</label>
						<input id="nightShiftStartTime" type="text" class="form-control" name="nightShiftStartTime" maxlength="20" value="${eamProductLine.nightShiftStartTime}">
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<input id="nightShiftEndTime" type="text" class="form-control" name="nightShiftEndTime" maxlength="20" value="${eamProductLine.nightShiftEndTime}">
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="grm">智库网关ID</label>
						<input id="grm" type="text" class="form-control" name="grm" maxlength="20" value="${eamProductLine.grm}">
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="grmPassword">智库网关密码</label>
						<input id="grmPassword" type="text" class="form-control" name="grmPassword" maxlength="20" value="${eamProductLine.grmPassword}">
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="grmPeriod">采集频率</label>
						<input id="grmPeriod" type="text" class="form-control" name="grmPeriod" maxlength="20" value="${eamProductLine.grmPeriod}">
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
        url: '${basePath}/manage/productLine/update/${eamProductLine.productLineId}',
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