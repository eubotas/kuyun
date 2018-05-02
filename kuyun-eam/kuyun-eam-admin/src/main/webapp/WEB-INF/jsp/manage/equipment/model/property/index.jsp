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
												设备模型参数列表
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

    <div class="m-portlet m-portlet--mobile">
        <div class="m-portlet__body">
            <div id="toolbar">
                <div>
                    <shiro:hasPermission name="eam:equipmentModelProperty:create"><a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a></shiro:hasPermission>

                    <shiro:hasPermission name="eam:equipmentModelProperty:delete"><a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                        <i class="la la-remove"></i>
                    </a></shiro:hasPermission>

                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                </div>
            </div>

            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>

    <!--begin::Modal-->
    <div id="addEquipmentModelPropertyFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editEquipmentModelPropertyFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-equipmentModelProperty-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
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
                            <label for="templateID_name">设备模型参数名称</label>
                            <input id="templateID_name" type="text" class="form-control" name="name" maxlength="200">
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="templateID_unit">参数单位</label>
                            <input id="templateID_unit" type="text" class="form-control" name="unit" maxlength="20">
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="templateID_dataType">设备模型参数类别</label>
                            <select id="templateID_dataType" name="dataType" style="width: 100%">
                                <c:forEach var="dataType" items="${dataTypes}">
                                    <option value="${dataType.code}">${dataType.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="templateID_displayType">设备模型参数显示类别</label>
                            <select id="templateID_displayType" name="displayType" style="width: 100%">
                                <c:forEach var="displayType" items="${displayTypes}">
                                    <option value="${displayType.code}">${displayType.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" name="equipmentModelId" value="${id}">
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

    <div class="modal fade" id="alarmDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="alermForm" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" >
                            报警设定
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label for="alarmType">类别</label>
                            <select id="alarmType" name="alarmType" style="width: 100%">
                            </select>
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="upperBound">X</label>
                            <input id="upperBound" type="text" class="form-control" name="upperBound" maxlength="20" placeholder="X">
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="lowerBound">Y</label>
                            <input id="lowerBound" type="text" class="form-control" name="lowerBound" maxlength="20" placeholder="Y" >
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="duration">M</label>
                            <input id="duration" type="text" class="form-control" name="duration" maxlength="20" placeholder="M">
                        </div>
                        <div class="form-group m-form__group row">
                            <select id="alarmTarget" name="alarmTarget" style="width: 100%">
                            </select>
                        </div>
                        <div class="form-group m-form__group row">
                            <select id="targetUser" name="targetUser" style="width: 100%">
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" id="alarmId" name="alarmId">
                        <input type="hidden" id="equipmentModelPropertyId" name="equipmentModelPropertyId">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-primary" onclick="alermSubmit('alermForm');">
                            提交
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div class="modal fade" id="dataChangeDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="dataChangeForm" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" >
                            数据转换
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">

                        <div class="form-group m-form__group row">
                            <div class="row">
                                <div class="col-sm-2">
                                    <div class="fg-line">
                                        <input id="isl" type="text"  class="form-control" name="isl" >
                                    </div>
                                </div>
                                <div class="col-sm-1">
                                    <div class="fg-line">
                                        一
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <div class="fg-line">
                                        <input id="ish" type="text"  class="form-control" name="ish" >
                                    </div>
                                </div>


                                <div class="col-sm-1">
                                    <div class="fg-line">
                                        =>
                                    </div>
                                </div>

                                <div class="col-sm-2">
                                    <div class="fg-line">
                                        <input id="osl" type="text"  class="form-control" name="osl" >
                                    </div>
                                </div>
                                <div class="col-sm-1">
                                    <div class="fg-line">
                                        一
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <div class="fg-line">
                                        <input id="osh" type="text"  class="form-control" name="osh" >
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" id="sensorId" name="sensorId">
                        <input type="hidden" id="data_equipmentModelPropertyId" name="equipmentModelPropertyId">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-primary" onclick="dataChangeSubmit('dataChangeForm');">
                            提交
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div class="modal fade" id="grmDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="grmForm" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" >
                            读写指令
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div id="grmShow">
                            <div class="form-group m-form__group row">
                                <select id="grmAction" name="grmAction" style="width: 100%">
                                </select>
                            </div>
                            <div class="form-group m-form__group row">
                                <label for="lowerBound">巨控 变量名</label>
                                <input id="grmVariable" type="text" class="form-control" name="grmVariable" maxlength="20" >
                            </div>
                            <div class="form-group m-form__group row">
                                <label for="grmVariableValue">巨控 写变量值</label>
                                <input id="grmVariableValue" type="text" class="form-control" name="grmVariableValue" maxlength="20" >
                            </div>
                        </div>

                        <div id="modbusShow">
                            <div class="form-group m-form__group row">
                                <select id="functionCode" name="functionCode" style="width: 100%">
                                </select>
                            </div>
                            <div class="form-group m-form__group row">
                                <label for="address">地址</label>
                                <input id="address" type="text" class="form-control" name="address" maxlength="500" >
                            </div>
                            <div class="form-group m-form__group row">
                                <select id="dataFormat" name="dataFormat" style="width: 100%">
                                </select>
                            </div>
                            <div class="form-group m-form__group row">
                                <select id="bitOrder" name="bitOrder" style="width: 100%">
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <input type="hidden" name="equipmentModelId" value="${id}">
                        <input type="hidden" id="grm_sensorId" name="sensorId">
                        <input type="hidden" id="grm_equipmentModelPropertyId" name="equipmentModelPropertyId">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-primary" onclick="grmSubmit('grmForm');">
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
            //codes works on all bootstrap modal windows in application
            $('.modal').on('hidden.bs.modal', function(e)
            {
                jQuery("#add_Form").validate().resetForm();
                jQuery("#edit_Form").validate().resetForm();
            }) ;
            generateAddEditForm('template-equipmentModelProperty-addEditForm', 'add_,edit_', null, null, 'addEquipmentModelPropertyFormContainer,editEquipmentModelPropertyFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addEquipmentModelPropertyFormContainer").modal("show");
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });

        });

        var $table = $('#table');
        var selectEquipmentModelPropertyId;
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/equipment/model/property/list/'+${id}',
                striped: true,
                search: true,
                searchAlign: 'left',
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
                idField: 'equipmentModelPropertyId',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'equipmentModelPropertyId', title: '设备模型参数ID', sortable: true, align: 'center'},
                    {field: 'name', title: '参数名称'},
                    {field: 'dataType', title: '数据类型'},
                    {field: 'unit', title: '参数单位'},
                    /*{field: 'refreshPeriod', title: '刷新周期'},*/
                    {field: 'action', title: '读写指令', align: 'center', formatter: 'readWriteFormatter', events: 'actionEvents', clickToSelect: false},
                    {field: 'action', title: '数据转换', align: 'center', formatter: 'changeFormatter', events: 'actionEvents', clickToSelect: false},
                    {field: 'action', title: '报警设定', align: 'center', formatter: 'alarmFormatter', events: 'actionEvents', clickToSelect: false},
                    {field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });

        // 格式化操作按钮
        function readWriteFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:equipmentModelProperty:update"><a class="update" id="readWrite" href="javascript:void(0)"  data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a></shiro:hasPermission>　'
            ].join('');
        }

        function changeFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:equipmentModelProperty:update"><a class="update" id="change" href="javascript:void(0)" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a></shiro:hasPermission>　'
            ].join('');
        }

        function alarmFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:equipmentModelProperty:update"><a class="update" id="alarm" href="javascript:void(0)" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a></shiro:hasPermission>　'
            ].join('');
        }

        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:equipmentModelProperty:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:equipmentModelProperty:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>'
            ].join('');
        }

        var FormWidgets = function () {
            var createForm = function (formid) {
                $("#"+formid+"_Form").validate({
                    // define validation rules
                    rules: {
                        name: {
                            required: true
                        }
                    },
                    submitHandler: function (form) {
                        if(formid == 'add')
                            submitForm();
                        else{
                            submitForm($('#edit_id').val());
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

        function submitForm(id) {
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
                        $('#addEquipmentModelPropertyFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑设备模型参数成功");
                        $('#editEquipmentModelPropertyFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            jQuery("#editEquipmentModelPropertyFormContainer").modal("show");
            ajaxGet('${basePath}/manage/equipment/model/property/update/' + row["equipmentModelPropertyId"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.equipmentModelProperties.equipmentModelPropertyId);
                    $("#edit_name").val(data.equipmentModelProperties.name);
                    $("#edit_unit").val(data.equipmentModelProperties.unit);
                    $("#edit_dataType").val(data.equipmentModelProperties.dataType);
                    $("#edit_displayType").val(data.equipmentModelProperties.displayType);
                }
            });
        }

        window.actionEvents = {
            'click #readWrite': function (e, value, row, index) {
                selectEquipmentModelPropertyId = row['equipmentModelPropertyId'];
                $("#grmDialog").modal("show");

                var readWriteUrl = '${basePath}/manage/equipment/model/property/modbus/${equipmentModel.equipmentModelId}/' + selectEquipmentModelPropertyId;
                if (1 == ${equipmentModel.protocolId}){
                    $("#modbusShow").modal("show");
                    readWriteUrl = '${basePath}/manage/equipment/model/property/modbus/${equipmentModel.equipmentModelId}/' + selectEquipmentModelPropertyId;
                }else if (4 == ${equipmentModel.protocolId}){
                    $("#grmShow").modal("show");
                    readWriteUrl = '${basePath}/manage/equipment/model/property/grm/${equipmentModel.equipmentModelId}/' + selectEquipmentModelPropertyId;
                }

                ajaxGet(readWriteUrl, function (responseData) {
                    if (responseData) {
                        var data = responseData;
                        $('#grm_sensorId').val(data.alarm.sensorId);
                        $('#grm_equipmentModelPropertyId').val(selectEquipmentModelPropertyId);
                        if (4 == ${equipmentModel.protocolId}) {
                            $('#grmVariable').val(data.sensor.grmVariable);
                            $('#grmVariableValue').val(data.sensor.grmVariableValue);
                            addOptionToHtmlSelect(data.sensor.grmAction, "grmAction", data.grmActions);
                        }else{
                            addOptionToHtmlSelect(data.sensor.functionCode, "functionCode", data.modbusFunctionCodes);
                            addOptionToHtmlSelect(data.sensor.dataFormat, "dataFormat", data.dataFormats);
                            addOptionToHtmlSelect(data.sensor.bitOrder, "bitOrder", data.bitOrders);
                            $('#address').val(data.sensor.address);
                        }
                    }
                });
            },
            'click #change': function (e, value, row, index) {
                selectEquipmentModelPropertyId = row['equipmentModelPropertyId'];
                $("#dataChangeDialog").modal("show");
                ajaxGet('${basePath}/manage/equipment/model/property/data/change/${equipmentModel.equipmentModelId}/'+selectEquipmentModelPropertyId, function (responseData) {
                    if (responseData) {
                        var data = responseData;
                        $('#sensorId').val(data.alarm.sensorId);
                        $('#data_equipmentModelPropertyId').val(selectEquipmentModelPropertyId);
                        $('#isl').val(data.sensor.isl);
                        $('#ish').val(data.sensor.ish);
                        $('#osl').val(data.sensor.osl);
                        $('#osh').val(data.sensor.osh);
                    }
                });
            },
            'click #alarm': function (e, value, row, index) {
                selectEquipmentModelPropertyId = row['equipmentModelPropertyId'];
                $("#alarmDialog").modal("show");
                ajaxGet('${basePath}/manage/equipment/model/property/alarm/${equipmentModel.equipmentModelId}/'+selectEquipmentModelPropertyId, function (responseData) {
                    if (responseData) {
                        var data = responseData;
                        $('#alarmId').val(data.alarm.alarmId);
                        $('#equipmentModelPropertyId').val(selectEquipmentModelPropertyId);
                        $('#duration').val(data.alarm.duration);
                        $('#lowerBound').val(data.alarm.lowerBound);
                        $('#upperBound').val(data.alarm.upperBound);
                        addOptionToHtmlSelect(data.alarm.alarmType, "alarmType", data.alarmTypes);
                        addOptionToHtmlSelect(data.alarm.alarmTarget, "alarmTarget", data.alarmTargets);
                        addOptionToHtmlSelect(null, "targetUser", data.users);
                    }
                });
            },
            'click #update': function (e, value, row, index) {
                updateAction(row);
            },
            'click #delete': function (e, value, row, index) {
                var rows = new Array();
                rows.push(row);
                deleteActionImpl(rows);
            }
        };

        function deleteAction(){
            var rows = $table.bootstrapTable('getSelections');
            deleteActionImpl(rows);
        }

        function deleteActionImpl(rows) {
            if (rows.length == 0) {
                swWarn("请至少选择一条记录");
            }else {
                deleteRows(rows,'equipmentModelPropertyId','${basePath}/manage/equipment/model/property/delete/', "请确认要删除选中的设备模型参数吗？", "删除设备模型参数成功");
            }//end else
        }

        function alermSubmit(formId){
            var targetUrl= '${basePath}/manage/alarm/create';
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    toastr.success("报警设置成功");
                    $('#alarmDialog').modal('toggle');
                    location.reload();
                }
            });
        }

        function dataChangeSubmit(formId){
            var targetUrl= '${basePath}/manage/sensor/update';
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    toastr.success("数据成功");
                    $('#dataChangeDialog').modal('toggle');
                    location.reload();
                }
            });
        }

        function grmSubmit(formId){
            var targetUrl= '${basePath}/manage/sensor/create';
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    toastr.success("读写指令成功");
                    $('#dataChangeDialog').modal('toggle');
                    location.reload();
                }
            });
        }
    </script>



</pageResources>


</body>
</html>