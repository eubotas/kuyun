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
		<div class="row">
			<div class="col-sm-12">
					<label for="companyName">客户名称</label>
					<input id="companyName" type="text" class="form-control" name="companyName" maxlength="50">
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<label for="companyName">客户简称</label>
				<input id="shortName" type="text" class="form-control" name="shortName" maxlength="50">
			</div>
		</div>

		<div class="row">
			<div class="col-sm-4">
				<label for="year">年份</label>
				<input id="year" type="text" class="form-control" name="year" maxlength="5">
			</div>

			<div class="col-sm-4">
				<label for="taskNumber">任务单号</label>
				<input id="taskNumber" type="text" class="form-control" name="taskNumber" maxlength="10">
			</div>

			<div class="col-sm-4">
				<label for="state">洲</label>
				<div class="fg-line">
					<select id="state" name="state" style="width: 100%">
						<c:forEach var="state" items="${states}">
							<option value="${state.codeValue}">${state.codeName}</option>
						</c:forEach>

					</select>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-sm-4">
				<label for="country">国家</label>
				<input id="country" type="text" class="form-control" name="country" maxlength="10">
			</div>

			<div class="col-sm-4">
				<label for="province">省/州</label>
				<input id="province" type="text" class="form-control" name="province" maxlength="10">
			</div>
			<div class="col-sm-4">
				<label for="city">地/市</label>
				<input id="city" type="text" class="form-control" name="city" maxlength="10">
			</div>

		</div>



		<div class="row">
			<div class="col-sm-4">
				<label for="industry">所属行业</label>
				<div class="fg-line">
					<select id="industry" name="industry" style="width: 100%">
						<c:forEach var="industry" items="${industries}">
							<option value="${industry.codeValue}">${industry.codeName}</option>
						</c:forEach>

					</select>
				</div>
			</div>

			<div class="col-sm-4">
				<label for="productLineType">产线类型</label>
				<div class="fg-line">
					<select id="productLineType" name="productLineType" style="width: 100%">
						<c:forEach var="productLineType" items="${productLineTypes}">
							<option value="${productLineType.codeValue}">${productLineType.codeName}</option>
						</c:forEach>

					</select>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-sm-6">
				<label for="hasCxg">是否含吹灌旋</label>
				<div class="radio radio-inline radio-info">
					<input id="hasCxg_1" type="radio" name="hasCxg" value="1" checked>
					<label for="hasCxg_1">是</label>
				</div>
				<div class="radio radio-inline radio-danger">
					<input id="hasCxg_2" type="radio" name="hasCxg" value="0">
					<label for="hasCxg_2">否</label>
				</div>
			</div>


			<div class="col-sm-6">
				<label for="hasZnlk">是否含智能立库</label>
				<div class="radio radio-inline radio-info">
					<input id="hasZnlk_1" type="radio" name="hasZnlk" value="1" checked>
					<label for="hasCxg_1">是</label>
				</div>
				<div class="radio radio-inline radio-danger">
					<input id="hasZnlk_2" type="radio" name="hasZnlk" value="0">
					<label for="hasCxg_2">否</label>
				</div>
			</div>
		</div>

		<div class="row">

			<div class="col-sm-4">
				<label for="productLineCapacity">生产线产能</label>
				<div class="fg-line">
					<select id="productLineCapacity" name="productLineCapacity" style="width: 100%">
						<c:forEach var="productLineCapacity" items="${productLineCapacities}">
							<option value="${productLineCapacity.codeValue}">${productLineCapacity.codeName}</option>
						</c:forEach>

					</select>
				</div>
			</div>


			<div class="col-sm-4">
				<label for="packagingMaterial">包装材质</label>
				<div class="fg-line">
					<select id="packagingMaterial" name="packagingMaterial" style="width: 100%">
						<c:forEach var="packagingMaterial" items="${packagingMaterials}">
							<option value="${packagingMaterial.codeValue}">${packagingMaterial.codeName}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="col-sm-4">
				<label for="productSpec">产品规格</label>
				<div class="fg-line">
					<select id="productSpec" name="productSpec" style="width: 100%">
						<c:forEach var="productSpec" items="${productSpecs}">
							<option value="${productSpec.codeValue}">${productSpec.codeName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-6">
				<label for="majorEquipment">主要设备</label>
				<input id="majorEquipment" type="text" class="form-control" name="majorEquipment" maxlength="50">
			</div>


			<div class="col-sm-6">
				<label for="comment">备注</label>
				<input id="comment" type="text" class="form-control" name="comment" maxlength="50">
			</div>
		</div>

		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="createDialog.close();">取消</a>
		</div>

		</div>
	</form>
</div>
<script>
function createSubmit() {


    $.ajax({
        type: 'post',
        url: '${basePath}/manage/order/create',
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