﻿﻿<%@ page contentType="text/html; charset=utf-8" %>
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
		.a{float:left;}
		.b{float:right; text-align:right}
	</style>
</head>
<body>


<subHeader>
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
						<ul class="m-nav m-nav--hover-bg m-portlet-fit--sides" id="models">
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
							<label for="templateID_name" class="form-control-label">
								参数名称:*
							</label>
							<div class="col-6">
								<input type="text" class="form-control" id="templateID_name" name="name">
							</div>

						</div>

						<div class="form-group m-form__group row">
							<label for="templateID_name" class="form-control-label">
								参数单位:*
							</label>
							<div class="col-6">
								<input type="text" class="form-control" id="templateID_unit" name="unit">
							</div>
						</div>

						<div class="form-group m-form__group row">
							<label class="form-control-label">
								参数类型:*
							</label>
							<div class="col-10">
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
							<label class="form-control-label">
								展示类型:
							</label>
							<div class="col-10">
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
							<label for="templateID_name" class="form-control-label">
								设备模型名称: *
							</label>
							<div class="col-6">
								<input type="text" class="form-control" id="templateID_name" name="name" maxlength="20">
							</div>
						</div>
						<div class="form-group m-form__group row">
							<label for="templateID_name" class="form-control-label">
								设备模型编号: *
							</label>
							<div class="col-6">
								<input type="text" class="form-control" id="templateID_number" name="number" maxlength="20">
							</div>
						</div>

						<div class="form-group m-form__group row">
							<label for="templateID_name" class="form-control-label">
								链接协议: *
							</label>
							<div class="col-8">
							    <select id="templateID_protocolId" name="protocolId" >
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
		<form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">

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
							<label for="templateID_name" class="form-control-label">
								读取模式: *
							</label>
							<div class="col-6">
								<select id="templateID_functionCode" name="functionCode" style="width: 100%">
								</select>
							</div>
						</div>
						<div class="form-group m-form__group row">
							<label for="templateID_name" class="form-control-label">
								参数地址: *
							</label>
							<div class="col-6">
								<div class="input-group bootstrap-touchspin">
									<span class="input-group-addon bootstrap-touchspin-prefix" style="display: none;"></span>
									<input id="m_touchspin_5" type="text" class="form-control bootstrap-touchspin-vertical-btn" value="" name="demo1" placeholder="30" style="display: block;">
									<span class="input-group-addon bootstrap-touchspin-postfix" style="display: none;"></span>
									<span class="input-group-btn-vertical">
										<button class="btn btn-secondary bootstrap-touchspin-up" type="button"><i class="la la-angle-up"></i></button>
										<button class="btn btn-secondary bootstrap-touchspin-down" type="button"><i class="la la-angle-down"></i></button>
									</span>
								</div>
								<%--<input type="text" class="form-control" id="templateID_number" name="number" maxlength="20">--%>
							</div>
						</div>

						<div class="form-group m-form__group row">
							<label for="templateID_name" class="form-control-label">
								数据格式: *
							</label>
							<select id="templateID_dataFormat" name="dataFormat" style="width: 100%">
							</select>
						</div>

						<div class="form-group m-form__group row">
							<label for="templateID_name" class="form-control-label">
								字节顺序: *
							</label>
							<select id="templateID_bitOrder" name="bitOrder" style="width: 100%">
							</select>
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
    $(document).ready(function()
    {
        $('.modal').on('hidden.bs.modal', function(e)
        {
            jQuery("#addModel_Form").validate().resetForm();
            jQuery("#addModbus_Form").validate().resetForm();
            jQuery("#addGrm_Form").validate().resetForm();
            jQuery("#add_Form").validate().resetForm();

            jQuery("#editModel_Form").validate().resetForm();
            jQuery("#editModbus_Form").validate().resetForm();
            jQuery("#editGrm_Form").validate().resetForm();
            jQuery("#edit_Form").validate().resetForm();
        }) ;

        EquipmentModels.init();
        generateAddEditForm('template-model-addEditForm', 'addModel_,editModel_', null, null, 'addModelFormContainer,editModelFormContainer');
        ModelFormWidgets.init('addModel');
        ModelFormWidgets.init('editModel');


        generateAddEditForm('template-modelProperty-addEditForm', 'add_,edit_', null, null, 'addModelPropertyFormContainer,editModelPropertyFormContainer');
        ModelPropertyFormWidgets.init('add');
        ModelPropertyFormWidgets.init('edit');

        generateAddEditForm('template-modbus-addEditForm', 'addModbus_,editMobus_', null, null, 'addModbusFormContainer,editModbusFormContainer');
        ModelFormWidgets.init('addModbus');
        ModelFormWidgets.init('editModbus');

        $('#createModelButton').click(function(){

            $("#addModelFormContainer").modal("show");

            ajaxGet('${basePath}/manage/equipment/model/create', function (responseData) {
				if (responseData) {
				    addOptionToHtmlSelect(null, "addModel_protocolId", responseData.protocols, "", "");
				}
            });
        });


        $('#createModelPropertyButton').click(function(){

            var equipmentModelId = $('#equipmentModelId').val();
            if (equipmentModelId == ''){
                swWarn("请选择一个设备模型");
            }else {
                $("#addModelPropertyFormContainer").modal("show");
            }
		});

        $('#deleteModelPropertyButton').click(function(){
            deleteAction();
        });

        $("#add_dataType_analog, #add_dataType_digital").change(function () {

            if ($("#add_dataType_analog").is(":checked")) {
                $('#add_displayType').show();
            }

            if ($("#add_dataType_digital").is(":checked")) {
                $('input[type="radio"][name="displayType"]').prop('checked', false);
                $('#add_displayType').hide();
            }

        });

        $("#edit_dataType_analog, #edit_dataType_digital").change(function () {

            if ($("#add_dataType_analog, #edit_dataType_analog").is(":checked")) {
                $('#edit_displayType').show();
            }

            if ($("#add_dataType_digital, #edit_dataType_digital").is(":checked")) {
                $('input[type="radio"][name="displayType"]').prop('checked', false);
                $('#edit_displayType').hide();
            }

        });
    });

/*************Start Equipment Model******************************/

    var EquipmentModels = function () {
        var getEquipmentModels = function () {
			var data = {limit:200, sort: 'update_time', order:'desc'};

            ajaxGetWithData('${basePath}/manage/equipment/model/list', data, function (responseData) {
				if (responseData) {
					var data = responseData;
                    $("#models").empty();

                    $.each(data.rows, function(index, row) {
						var modelId=$.trim(row.equipmentModelId);
                        var html = '<li id="eqModel' +modelId +'" class="m-nav__item">' +
                            '<a href="javascript:void(0)" class="m-nav__link" onclick="showModelProperties(' +modelId +')"> ' +
                            '<span class="m-nav__link-text">' + row.name + '</span>' +
                            '</a>'+
                            '<div class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill"> <i class="modelEdit " onclick="modelEdit('+modelId+')"></i></div>'+
                            '<div class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill"><i class="modelDel " onclick="ModelDelete('+modelId+')"></i></div>'+
                            '</li>';

                        $("#models").append(html);

                    });

                    $('[id^="eqModel"]').hover(function(){
                        var iObj= $(this);
                        iObj.find('.modelEdit').addClass('la la-edit');
                        iObj.find('.modelDel').addClass('la la-remove');
                    },function(){
                        var iObj= $(this);
                        iObj.find('.modelEdit').removeClass('la la-edit');
                        iObj.find('.modelDel').removeClass('la la-remove');
                    });
				}
			});

		}

        return {
            // public functions
            init: function () {
                getEquipmentModels();
            }
        };
	}();

    function modelEdit(modelId){
        $("#editModelFormContainer").modal("show");
        ajaxGet('${basePath}/manage/equipment/model/update/' + modelId, function (responseData) {
            if (responseData) {
                var data = responseData.equipmentModel;

                $("#editModel_id").val(modelId);
                $("#editModel_name").val(data.name);
                $("#editModel_number").val(data.number);

                addOptionToHtmlSelect(data.protocolId, 'editModel_protocolId', responseData.protocols);
            }
        });
	}
    function ModelDelete(modelId){
        var rows = new Array();
        var row = {equipmentModelId:modelId}
        rows.push(row);
        deleteRows(rows,'equipmentModelId','${basePath}/manage/equipment/model/delete/', "请确认要删除选中的设备模型吗？", "删除设备模型成功", null, EquipmentModels.init);
    }

    var ModelFormWidgets = function () {
        var createForm = function (formid) {
            $("#"+formid+"_Form").validate({
                // define validation rules
                rules: {
                    name: {
                        required: true,
                        minlength: 2,
                        maxlength: 20
                    },
                    number: {
                        required: true,
                        minlength: 1,
                        maxlength: 20
                    },
                },
                submitHandler: function (form) {
                    if(formid === 'addModel')
                        submitModelForm();
                    else{
                        submitModelForm($('#editModel_id').val());
                    }

                }
            });
        }
        return {
            // public functions
            init: function (formid) {
                createForm(formid);
            }
        };
    }();

    function submitModelForm(id) {
        var targetUrl='${basePath}/manage/equipment/model/create';
        var formId='addModel_Form';
        if(id){
            targetUrl='${basePath}/manage/equipment/model/update/'+id;
            formId='editModel_Form';
        }

        ajaxPost(targetUrl, formId, function(result) {
            if (result.code != 1) {
                sendErrorInfo(result);
            } else {
                if(formId=='addModel_Form') {
                    toastr.success("新建设备模型成功");
                    $('#addModelFormContainer').modal('toggle');
                }else{
                    toastr.success("编辑设备模型成功");
                    $('#editModelFormContainer').modal('toggle');
                }
                EquipmentModels.init();
            }
        });
    }

    /*************Start Equipment Model Property******************************/

    var $table = $('#table');
    $(function() {
        // bootstrap table初始化
        $table.bootstrapTable({
            url: '${basePath}/manage/equipment/model/property/list/-1',
            striped: true,
            search: true,
            searchAlign: 'right',
            toolbarAlign: 'right',
            minimumCountColumns: 2,
            clickToSelect: true,
            detailView: true,
            detailFormatter: 'detailFormatter',
            pagination: true,
            paginationLoop: false,
            sidePagination: 'server',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            maintainSelected: true,
            idField: 'equipmentModelId',
            columns: [
                {field: 'ck', checkbox: true},
                {field: 'name', title: '参数名称'},
                {field: 'dataType', title: '数据类型'},
                {field: 'unit', title: '参数单位'},
                {field: 'action', width: 150, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
			]
        });
    });
    // 格式化操作按钮
    function actionFormatter(value, row, index) {
        return [
            '<shiro:hasPermission name="eam:equipmentModelProperty:update"><a id="updateAction" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
            '<shiro:hasPermission name="eam:equipmentModelProperty:update"><a id="readWriteAction" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-success m-btn--icon m-btn--icon-only m-btn--pill" title="读写指令">	<i class="la la-recycle"></i>	</a></shiro:hasPermission>',
            '<shiro:hasPermission name="eam:equipmentModelProperty:update"><a id="alarmAction" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="报警设置">	<i class="la la-bell"></i>	</a></shiro:hasPermission>'
        ].join('');
    }

    var ModelPropertyFormWidgets = function () {
        var createForm = function (formid) {
            $("#"+formid+"_Form").validate({
                // define validation rules
                rules: {
                    name: {
                        required: true,
                        minlength: 2,
                        maxlength: 20
                    },
                    unit: {
                        required: true,
                        minlength: 1,
                        maxlength: 20
                    },
                },
                submitHandler: function (form) {
                    if(formid === 'add')
                        submitModelPropertyForm();
                    else{
                        submitModelPropertyForm($('#edit_id').val());
                    }

                }
            });
        }

        return {
            // public functions
            init: function (formid) {
                createForm(formid);
            }
        };
    }();

    function showModelProperties(id) {
        selectedItemColor("models", 'eqModel'+id);
        $('#equipmentModelId').val(id);
        refreshTable();
    }

    function refreshTable() {
        var id = $('#equipmentModelId').val();
        var opt = {
            url: "${basePath}/manage/equipment/model/property/list/"+id
        };

        $table.bootstrapTable('refresh', opt);
    }

    function submitModelPropertyForm(id) {
        var targetUrl='${basePath}/manage/equipment/model/property/create';
        var formId='add_Form';
        if(id){
            targetUrl='${basePath}/manage/equipment/model/property/update/'+id;
            formId='edit_Form';
        }

        ajaxPost(targetUrl, formId, function(result) {
            if (result.code != 1) {
                sendErrorInfo(result);
            } else {
                if(formId=='add_Form') {
                    toastr.success("新建设备模型参数成功");
                    $('#addModelPropertyFormContainer').modal('toggle');
                }else{
                    toastr.success("编辑设备模型参数成功");
                    $('#editModelPropertyFormContainer').modal('toggle');
                }
                refreshTable();
            }
        });
    }

    function updateAction(row) {
        $("#editModelPropertyFormContainer").modal("show");

        ajaxGet('${basePath}/manage/equipment/model/property/update/' + row["equipmentModelPropertyId"], function (responseData) {
            if (responseData) {
                var data = responseData.equipmentModelProperties;
                // 赋值
                setModelProperty(data);
            }
        });
    }

    function setModelProperty(data) {
        $("#edit_id").val(data.equipmentModelPropertyId);
        $("#edit_name").val(data.name);
        $("#edit_unit").val(data.unit);

        if ('analog' === data.dataType) {
            $("#edit_dataType_analog").prop('checked', true);
        } else if ('digital' === data.dataType) {
            $("#edit_dataType_digital").prop('checked', true);
        }

        if ('pie' === data.displayType) {
            $("#edit_displayType_pie").prop('checked', true);
        } else if ('led' === data.displayType) {
            $("#edit_displayType_led").prop('checked', true);
        } else if ('guage' === data.displayType) {
            $("#edit_displayType_guage").prop('checked', true);
        }
    }

    window.actionEvents = {
        'click #updateAction': function (e, value, row, index) {
            updateAction(row);
        },
        'click #readWriteAction': function (e, value, row, index) {
            readWriteAction(row);
        },
        'click #alarmAction': function (e, value, row, index) {
            alarmAction(row);
        }
    };

    function readWriteAction(row) {
		var equipmentModelId = row["equipmentModelId"];
        ajaxGet('${basePath}/manage/equipment/model/update/' + equipmentModelId, function (responseData) {
            if (responseData) {
                var protocolId = responseData.equipmentModel.protocolId;
                console.log("protocolId:"+protocolId);

                if (1 == protocolId){
                    //modbus
                    $("#addModbusFormContainer").modal("show");
                    addOptionToHtmlSelect(null, "addModel_functionCode", responseData.modbusFunctionCodes, "", "");
                    addOptionToHtmlSelect(null, "addModel_dataFormat", responseData.dataFormats, "", "");
                    addOptionToHtmlSelect(null, "addModel_bitOrder", responseData.bitOrders, "", "");

                }else if (4 == protocolId){
                    //grm
                }

            }
        });





    }

    function alarmAction(row) {
        $("#editModelPropertyFormContainer").modal("show");

        ajaxGet('${basePath}/manage/equipment/model/property/update/' + row["equipmentModelPropertyId"], function (responseData) {
            if (responseData) {
                var data = responseData.equipmentModelProperties;
                // 赋值
                setModelProperty(data);
            }
        });
    }

    function deleteAction(){
        var rows = $table.bootstrapTable('getSelections');
        deleteActionImpl(rows);
    }

    function deleteActionImpl(rows) {
        if (rows.length == 0) {
            swWarn("请至少选择一条记录");
        }else {
            deleteRows(rows,'equipmentModelPropertyId','${basePath}/manage/equipment/model/property/delete/', "请确认要删除选中的设备模型参数吗？", "删除设备模型参数成功", null, refreshTable);
        }
    }
</script>

</pageResources>
</body>
</html>