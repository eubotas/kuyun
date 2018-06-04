﻿﻿﻿<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8"/>
	<style>
		span{ display:block;}
		.m-nav-row-left{float:left; }
		.m-nav-row-right{float:right; text-align:right; }
	</style>
</head>
<body>


<subHeader>
    <style>
        .input-group {min-width: 100px;}
    </style>
	<!-- BEGIN: Subheader -->
	<div class="m-subheader ">
		<div class="d-flex align-items-center">
			<div class="mr-auto">
				<ul class="m-subheader__breadcrumbs m-nav m-nav--inline">
					<li class="m-nav__item m-nav__item--home">
						<a href="#" class="m-nav__link m-nav__link--icon">
							<i class="m-nav__link-icon la la-home"></i>
						</a>
					</li>
					<li class="m-nav__separator">
						-
					</li>
					<li class="m-nav__item">
						<a href="" class="m-nav__link">
											<span class="m-nav__link-text">
												设备模型
											</span>
						</a>
					</li>
				</ul>
			</div>

		</div>
	</div>
	<!-- END: Subheader -->
</subHeader>


<content>


	<div class="m-content">
		<div class="row">
			<div class="col-xl-3 col-lg-4">
				<div class="m-portlet m-portlet--full-height  ">
					<div class="m-portlet__head">
						<div class="m-portlet__head-caption">
							<div class="m-portlet__head-title">
								<h1 class="m-portlet__head-text">
									设备模型
								</h1>
							</div>
						</div>
						<div class="m-portlet__head-tools">
							<ul class="m-portlet__nav">
								<shiro:hasPermission name="eam:equipmentModel:create">
								<li class="m-portlet__nav-item">
									<button id="createModelButton" class="btn btn-outline-metal m-btn m-btn--icon m-btn--icon-only" title="新建">
										<i class="la la-plus"></i>
									</button>
								</li>
								</shiro:hasPermission>
							</ul>
						</div>
					</div>

					<div class="m-portlet__body">
						<ul class="m-nav m-nav--hover-bg m-portlet-fit--sides" id="models" style="line-height:45px;">
						</ul>
						<div class="m-portlet__body-separator"></div>

					</div>
				</div>
			</div>
			<div class="col-xl-9 col-lg-8">
				<div class="m-portlet m-portlet--full-height">
					<div class="m-portlet__head">
						<div class="m-portlet__head-caption">
							<div class="m-portlet__head-title">
												<span class="m-portlet__head-icon">
													<i class="flaticon-multimedia"></i>
												</span>
								<h3 class="m-portlet__head-text">
									设备模型参数
								</h3>
							</div>
						</div>
					</div>


					<div class="m-portlet__body">

						<div id="toolbar">
							<div>
								<span>&nbsp;</span>
                                <shiro:hasPermission name="eam:equipmentModelProperty:create">
								<button id="createModelPropertyButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
									<i class="la la-plus"></i>
								</button>
                                </shiro:hasPermission>

                                <shiro:hasPermission name="eam:equipmentModelProperty:delete">
								<button id="deleteModelPropertyButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
									<i class="la la-remove"></i>
								</button>
                                </shiro:hasPermission>
								<div class="m-separator m-separator--dashed d-xl-none"></div>
							</div>
						</div>
						<table id="table" data-toolbar="#toolbar"></table>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!--begin::Modal-->
	<div id="addModelPropertyFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modelPropertiesLabel"
		 aria-hidden="true">
	</div>

	<div id="editModelPropertyFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modelPropertiesLabel"
		 aria-hidden="true">
	</div>

	<div class="modal fade" id="template-modelProperty-addEditForm" tabindex="-1" role="dialog" aria-labelledby="modelPropertiesLabel"
		 aria-hidden="true">
		<form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
			<input id="equipmentModelId" type="hidden" name="equipmentModelId" value="">

			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="modelPropertiesLabel">
							templateTitleName_设备模型参数
						</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group m-form__group row">
							<label class="col-3 col-form-label">
								参数名称:*
							</label>
							<div class="col-6">
								<input type="text" class="form-control" id="templateID_name" name="name">
							</div>

						</div>

						<div class="form-group m-form__group row">
							<label class="col-3 col-form-label">
								参数单位:*
							</label>
							<div class="col-6">
								<input type="text" class="form-control" id="templateID_unit" name="unit">
							</div>
						</div>

						<div class="form-group m-form__group row">
							<label class="col-3 col-form-label">
								参数类型:*
							</label>
							<div class="col-8">
								<div class="m-radio-inline">
									<label class="m-radio">
										<input type="radio" id="templateID_dataType_analog" name="dataType" value="analog" checked>
										模拟量
										<span></span>
									</label>
									<label class="m-radio">
										<input type="radio" id="templateID_dataType_digital" name="dataType" value="digital">
										开关量
										<span></span>
									</label>
								</div>

							</div>
						</div>
						<div class="form-group m-form__group row" id="templateID_displayType">
							<label class="col-3 col-form-label">
								展示类型:
							</label>
							<div class="col-8">
								<div class="m-radio-inline">
									<label class="m-radio">
										<input type="radio" id="templateID_displayType_pie" name="displayType" value="pie">
										饼图
										<span></span>
									</label>
									<label class="m-radio">
										<input type="radio" id="templateID_displayType_led" name="displayType" value="led">
										LED
										<span></span>
									</label>
									<label class="m-radio">
										<input type="radio" id="templateID_displayType_guage" name="displayType" value="guage">
										仪表盘
										<span></span>
									</label>
								</div>

							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="hidden" id="templateID_id" name="id">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">
							取消
						</button>
						<button type="submit" class="btn btn-primary" id="templateID_submit">
							提交
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>


	<div id="addModelFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modelLabel"
		 aria-hidden="true">
	</div>

	<div id="editModelFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modelLabel"
		 aria-hidden="true">
	</div>


	<div class="modal fade" id="template-model-addEditForm" tabindex="-1" role="dialog" aria-labelledby="modelLabel"
		 aria-hidden="true">
		<form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="modelLabel">
							templateTitleName_设备模型
						</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group m-form__group row">
							<label class="col-4 col-form-label">
								设备模型名称: *
							</label>
							<div class="col-6">
								<input type="text" class="form-control" id="templateID_name" name="name" maxlength="20">
							</div>
						</div>
						<div class="form-group m-form__group row">
							<label class="col-4 col-form-label">
								设备模型编号: *
							</label>
							<div class="col-6">
								<input type="text" class="form-control" id="templateID_number" name="number" maxlength="20">
							</div>
						</div>
						<div class="form-group m-form__group row">
							<label class="col-4 col-form-label">
								链接协议: *
							</label>
							<div class="col-6">
							    <select class="form-control m-select2" style="width: 180px;" id="templateID_protocolId" name="protocolId" >
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="hidden" id="templateID_id" name="id">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">
							取消
						</button>
						<button type="submit" class="btn btn-primary" id="templateID_submit">
							提交
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>

	<div id="addModbusFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modbusLabel"
		 aria-hidden="true">
	</div>

	<div id="editModbusFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modbusLabel"
		 aria-hidden="true">
	</div>


	<div class="modal fade" id="template-modbus-addEditForm" tabindex="-1" role="dialog" aria-labelledby="modbusLabel"
		 aria-hidden="true">
		<form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right ">
			<input type="hidden" id="templateID_equipmentModelId" name="equipmentModelId" value="">
			<input type="hidden" id="templateID_equipmentModelPropertyId" name="equipmentModelPropertyId" value="">
			<input type="hidden" id="templateID_sensorId" name="sensorId" value="">
			<input type="hidden" id="templateID_protocolId" name="protocolId" value="">


			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="modbusLabel">
							templateTitleName_读写指令
						</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group m-form__group row">
							<label class="col-3 col-form-label">
								读取模式: *
							</label>
							<div class="col-6">
								<select id="templateID_functionCode" name="functionCode" style="width: 100%">
								</select>
							</div>
						</div>
						<div class="form-group m-form__group row">
							<label class="col-3 col-form-label">
								参数地址: *
							</label>
							<div class="col-6">
								<input id="templateID_address" type="text" class="form-control bootstrap-touchspin-vertical-btn" value="" name="address"  type="text">
							</div>
						</div>

						<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

						<div class="form-group m-form__group row m-form--group-seperator-dashed">
							<label class="col-3 col-form-label">
								数据格式: *
							</label>
							<div class="col-6">
								<select id="templateID_dataFormat" name="dataFormat" style="width: 100%">
								</select>
							</div>
						</div>

						<div id="templateID_bitOrderRow" class="form-group m-form__group row" >
							<label class="col-3 col-form-label">
								字节顺序:
							</label>
							<div class="col-6">
								<select id="templateID_bitOrder" name="bitOrder" style="width: 100%">
								</select>
							</div>
						</div>

						<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

						<div class="form-group m-form__group row">
							<label class="col-3 col-form-label">
								数据转换
							</label>
							<div id="templateID_addDataChangeButtonDiv">
								<div class="col-6">
									<button id="templateID_addDataChangeButton" class="btn btn-primary m-btn m-btn--icon">
										<span>
											<i class="la la-archive"></i>
											<span>
												添加参数映射
											</span>
										</span>
									</button>
								</div>
							</div>
						</div>

						<div id="templateID_dataChangeDiv">
							<div class="form-group m-form__group row">
								<label>
									原始值:
								</label>
								<div class="col-3">
									<input type="text" id="templateID_isl" name="isl" />
								</div>
								<div class="col-1">
									<span class="input-group-addon">~</span>
								</div>
								<div class="col-3">
									<input type="text" id="templateID_ish" name="ish" />
								</div>
								<div class="col-12">
									<span class="m-form__help">参数读取值的范围 如 0~1000</span>
								</div>
							</div>

							<div class="form-group m-form__group row">
								<label>
									工程值:
								</label>
								<div class="col-3">
									<input id="templateID_osl" type="text" name="osl">
								</div>
								<div class="col-1">
									~
								</div>
								<div class="col-3">
									<input id="templateID_osh" type="text" name="osh">
								</div>
								<div class="col-3">
									<button id="templateID_deleteDataChangeButton" class="btn btn-primary m-btn m-btn--icon">
										<span>
											删除映射
										</span>
									</button>
								</div>

								<div class="col-12">
									<span class="m-form__help">
										参数真实值的范围 如 0~100，表示真实值对应读取值的0.1倍
									</span>
								</div>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<input type="hidden" id="templateID_id" name="id">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">
							取消
						</button>
						<button type="submit" class="btn btn-primary" id="templateID_submit">
							提交
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>


	<div id="addGrmFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="GrmLabel"
		 aria-hidden="true">
	</div>

	<div id="editGrmFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="GrmLabel"
		 aria-hidden="true">
	</div>


	<div class="modal fade" id="template-Grm-addEditForm" tabindex="-1" role="dialog" aria-labelledby="GrmLabel"
		 aria-hidden="true">
		<form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right ">
			<input type="hidden" id="templateID_equipmentModelId" name="equipmentModelId" value="">
			<input type="hidden" id="templateID_equipmentModelPropertyId" name="equipmentModelPropertyId" value="">
			<input type="hidden" id="templateID_sensorId" name="sensorId" value="">
			<input type="hidden" id="templateID_protocolId" name="protocolId" value="">
			<input type="hidden" id="templateID_grmAction" name="grmAction" value="R">


			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="GrmLabel">
							templateTitleName_读写指令
						</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group m-form__group row">
							<label class="col-3 col-form-label">
								变量名: *
							</label>
							<div class="col-6">
								<input type="text" class="form-control" id="templateID_grmVariable" name="grmVariable">
							</div>
						</div>


						<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

						<div class="form-group m-form__group row">
							<label class="col-3 col-form-label">
								数据转换
							</label>
							<div id="templateID_addDataChangeButtonDiv">
								<div class="col-6">
									<button id="templateID_addDataChangeButton" class="btn btn-primary m-btn m-btn--icon">
										<span>
											<i class="la la-archive"></i>
											<span>
												添加参数映射
											</span>
										</span>
									</button>
								</div>
							</div>
						</div>

						<div id="templateID_dataChangeDiv">
							<div class="form-group m-form__group row">
								<label class="form-control-label">
									原始值:
								</label>
								<div class="col-3">
									<input type="text" id="templateID_isl" name="isl" />
								</div>
								<div class="col-1">
									<span class="input-group-addon">~</span>
								</div>
								<div class="col-3">
									<input type="text" id="templateID_ish" name="ish" />
								</div>
								<div class="col-12">
									<span class="m-form__help">参数读取值的范围 如 0~1000</span>
								</div>
							</div>

							<div class="form-group m-form__group row">
								<label class="form-control-label">
									工程值:
								</label>
								<div class="col-3">
									<input id="templateID_osl" type="text" name="osl">
								</div>
								<div class="col-1">
									~
								</div>
								<div class="col-3">
									<input id="templateID_osh" type="text" name="osh">
								</div>
								<div class="col-3">
									<button id="templateID_deleteDataChangeButton" class="btn btn-primary m-btn m-btn--icon">
										<span>
											删除映射
										</span>
									</button>
								</div>

								<div class="col-12">
									<span class="m-form__help">
										参数真实值的范围 如 0~100，表示真实值对应读取值的0.1倍
									</span>
								</div>
							</div>
						</div>

					</div>

					<div class="modal-footer">
						<input type="hidden" id="templateID_id" name="id">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">
							取消
						</button>
						<button type="submit" class="btn btn-primary" id="templateID_submit">
							提交
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>

	<div id="addAlarmFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="AlarmLabel"
		 aria-hidden="true">
	</div>
	<div id="editAlarmFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="AlarmLabel"
		 aria-hidden="true">
	</div>
	<div class="modal fade" id="template-Alarm-addEditForm" tabindex="-1" role="dialog" aria-labelledby="AlarmLabel"
		 aria-hidden="true">
		<form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right ">
			<input type="hidden" id="templateID_equipmentModelPropertyId" name="equipmentModelPropertyId" value="">
			<input type="hidden" id="templateID_alarmId" name="alarmId" value="">

			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="AlarmLabel">
							templateTitleName_报警参数设置
						</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">
								&times;
							</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group m-form__group row">
							<label class="col-3 col-form-label">
								报警参数 *
							</label>
							<div class="col-9">
								<select id="templateID_alarmType" name="alarmType" style="width: 100%">
								</select>
							</div>
						</div>
						<div class="form-group m-form__group row">
							<div class="col-4" id="templateID_upperBoundDiv">
								<input id="templateID_upperBound" type="text" name="upperBound" placeholder="X">
							</div>

							<div class="col-4" id="templateID_lowerBoundDiv">
								<input id="templateID_lowerBound" type="text" name="lowerBound" placeholder="Y">
							</div>

							<div class="col-4" id="templateID_durationDiv">
								<input id="templateID_duration" type="text" name="duration" placeholder="M">
							</div>
						</div>
						<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>
						<div class="form-group m-form__group row" >
							<label class="col-3 col-form-label">
								提醒方式:
							</label>
							<div class="col-9">
								<select id="templateID_alarmTarget" name="alarmTarget" style="width: 100%">
								</select>
							</div>
						</div>
						<div class="form-group m-form__group row" >
							<label class="col-3 col-form-label">
								提醒对象:
							</label>
							<div class="col-9">
								<select id="templateID_targetUser" name="targetUser" style="width: 100%">
								</select>
							</div>
						</div>


					</div>
					<div class="modal-footer">
						<input type="hidden" id="templateID_id" name="id">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">
							取消
						</button>
						<button type="submit" class="btn btn-primary" id="templateID_submit">
							提交
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!--end::Modal-->

</content>


<pageResources>


<script>
    var basePath = "${basePath}";
</script>
    <script src="${basePath}/resources/metronic-admin/assets/app/js/equipment/model.js"></script>

</pageResources>
</body>
</html>