﻿<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="createDialog" class="crudDialog">
	<form id="createForm" method="post">

		<div class="row">
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="name">名称</label>
						<input id="name" type="text" class="form-control" name="name" maxlength="20">
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="imagePath">图片</label>
						<input id="imagePath" type="text" class="form-control" name="imagePath" maxlength="20">
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="longitude">设备位置:经度</label>
						<input id="longitude" type="text" class="form-control" name="longitude" maxlength="100">
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="latitude">纬度</label>
						<input id="latitude" type="text" class="form-control" name="latitude" maxlength="100">
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="morningShiftTime">早班</label>
						<input id="morningShiftStartTime" type="text" class="form-control" name="morningShiftStartTime" maxlength="20" >
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<input id="morningShiftEndTime" type="text" class="form-control" name="morningShiftEndTime" maxlength="20" >
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="middleShiftTime">中班</label>
						<input id="middleShiftStartTime" type="text" class="form-control" name="middleShiftStartTime" maxlength="20" >
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<input id="middleShiftEndTime" type="text" class="form-control" name="middleShiftEndTime" maxlength="20" >
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="nightShiftTime">晚班</label>
						<input id="nightShiftStartTime" type="text" class="form-control" name="nightShiftStartTime" maxlength="20" >
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<input id="nightShiftEndTime" type="text" class="form-control" name="nightShiftEndTime" maxlength="20" >
					</div>
				</div>
			</div>


			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="grm">智库网关ID</label>
						<input id="grm" type="text" class="form-control" name="grm" maxlength="20">
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="grmPassword">智库网关密码</label>
						<input id="grmPassword" type="text" class="form-control" name="grmPassword" maxlength="20">
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="grmPeriod">采集频率</label>
						<input id="grmPeriod" type="text" class="form-control" name="grmPeriod" maxlength="20">
					</div>
				</div>
			</div>



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
        url: '${basePath}/manage/productLine/create',
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