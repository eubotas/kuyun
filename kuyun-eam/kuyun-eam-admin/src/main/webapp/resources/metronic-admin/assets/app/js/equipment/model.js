$(document).ready(function()
{
    $('.modal').on('hidden.bs.modal', function(e)
    {
        jQuery("#addModel_Form").validate().resetForm();
        jQuery("#addModbus_Form").validate().resetForm();
        jQuery("#addGrm_Form").validate().resetForm();
        jQuery("#addModelProperty_Form").validate().resetForm();
        jQuery("#addAlarm_Form").validate().resetForm();

        jQuery("#editModel_Form").validate().resetForm();
        jQuery("#editModbus_Form").validate().resetForm();
        jQuery("#editGrm_Form").validate().resetForm();
        jQuery("#editModelProperty_Form").validate().resetForm();
        jQuery("#editAlarm_Form").validate().resetForm();
    }) ;

    EquipmentModels.init();
    generateAddEditForm('template-model-addEditForm', 'addModel_,editModel_', null, null, 'addModelFormContainer,editModelFormContainer');
    ModelFormWidgets.init('addModel');
    ModelFormWidgets.init('editModel');


    $('#addModel_protocolId, #editModel_protocolId').select2({minimumResultsForSearch: -1});

    generateAddEditForm('template-modelProperty-addEditForm', 'addModelProperty_,editModelProperty_', null, null, 'addModelPropertyFormContainer,editModelPropertyFormContainer');
    ModelPropertyFormWidgets.init('addModelProperty');
    ModelPropertyFormWidgets.init('editModelProperty');

    generateAddEditForm('template-modbus-addEditForm', 'addModbus_,editModbus_', null, null, 'addModbusFormContainer,editModbusFormContainer');
    ModbusFormWidgets.init('addModbus');
    ModbusFormWidgets.init('editModbus');

    generateAddEditForm('template-Grm-addEditForm', 'addGrm_,editGrm_', null, null, 'addGrmFormContainer,editGrmFormContainer');
    GrmFormWidgets.init('addGrm');
    GrmFormWidgets.init('editGrm');

    generateAddEditForm('template-Alarm-addEditForm', 'addAlarm_,editAlarm_', null, null, 'addAlarmFormContainer,editAlarmFormContainer');
    AlarmFormWidgets.init('addAlarm');
    AlarmFormWidgets.init('editAlarm');


    $('#addModbus_functionCode, #addModbus_dataFormat, #addModbus_bitOrder').select2({minimumResultsForSearch: -1});
    $('#editModbus_functionCode, #editModbus_dataFormat, #editModbus_bitOrder').select2({minimumResultsForSearch: -1});

    $('#addAlarm_alarmType, #addAlarm_alarmTarget, #addAlarm_targetUser').select2({minimumResultsForSearch: -1});
    $('#editAlarm_alarmType, #editAlarm_alarmTarget, #editAlarm_targetUser').select2({minimumResultsForSearch: -1});

    $('#addGrm_ish, #addGrm_osh, #addGrm_isl, #addGrm_osl, #editGrm_isl, #editGrm_osl, #editGrm_ish, #editGrm_osh, #addModbus_ish, #addModbus_osh, #addModbus_isl, #addModbus_osl, #editModbus_isl, #editModbus_osl, #editModbus_ish, #editModbus_osh').TouchSpin({
        buttondown_class: 'btn btn-secondary',
        buttonup_class: 'btn btn-secondary',
        verticalbuttons: true,
        verticalupclass: 'la la-angle-up',
        verticaldownclass: 'la la-angle-down',
        min:-9999999,
        max: 9999999
    });

    $('#addAlarm_upperBound, #addAlarm_lowerBound, #addAlarm_duration, #editAlarm_upperBound, #editAlarm_lowerBound, #editAlarm_duration').TouchSpin({
        buttondown_class: 'btn btn-secondary',
        buttonup_class: 'btn btn-secondary',
        verticalbuttons: true,
        verticalupclass: 'la la-angle-up',
        verticaldownclass: 'la la-angle-down',
        min:-9999999,
        max: 9999999
    });



    $('#createModelButton').click(function(){

        $("#addModelFormContainer").modal("show");

        ajaxGet(getRequestUrl('/manage/equipment/model/create',basePath), function (responseData) {
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

    $('#addModbus_addDataChangeButton').click(function(event){
        addDataChangeForModbus('add');
        event.preventDefault();
    });

    $('#editModbus_addDataChangeButton').click(function(event){
        addDataChangeForModbus('edit')
        event.preventDefault();
    });

    $('#addModbus_deleteDataChangeButton').click(function(event){
        removeDataChangeForModbus('add')
        event.preventDefault();
    });

    $('#editModbus_deleteDataChangeButton').click(function(event){
        removeDataChangeForModbus('edit')
        event.preventDefault();
    });



    $('#addGrm_addDataChangeButton').click(function(event){
        addDataChangeForGrm('add');
        event.preventDefault();
    });

    $('#editGrm_addDataChangeButton').click(function(event){
        addDataChangeForGrm('edit')
        event.preventDefault();
    });

    $('#addGrm_deleteDataChangeButton').click(function(event){
        removeDataChangeForGrm('add')
        event.preventDefault();
    });

    $('#editGrm_deleteDataChangeButton').click(function(event){
        removeDataChangeForGrm('edit')
        event.preventDefault();
    });


    dataTypeChange();
    dataTypeChange('addModelProperty');
    dataTypeChange('editModelProperty');

    dataFormatChange();



    alarmTypeChange('addAlarm_');
    alarmTypeChange('editAlarm_')

});

function dataTypeChange() {
    $("#addModelProperty_dataType_analog, #addModelProperty_dataType_digital").change(function () {

        if ($("#addModelProperty_dataType_analog").is(":checked")) {
            $('#addModelProperty_displayType').show();
        }

        if ($("#addModelProperty_dataType_digital").is(":checked")) {
            $('input[type="radio"][name="displayType"]').prop('checked', false);
            $('#addModelProperty_displayType').hide();
        }

    });

    $("#editModelProperty_dataType_analog, #editModelProperty_dataType_digital").change(function () {

        if ($("#addModelProperty_dataType_analog, #editModelProperty_dataType_analog").is(":checked")) {
            $('#editModelProperty_displayType').show();
        }

        if ($("#addModelProperty_dataType_digital, #editModelProperty_dataType_digital").is(":checked")) {
            $('input[type="radio"][name="displayType"]').prop('checked', false);
            $('#editModelProperty_displayType').hide();
        }

    });
}

function dataFormatChange() {
    $("#addModbus_dataFormat, #editModbus_dataFormat").change(function(){
        hideAndShowBitOrder();
    });

}
/*************Start Equipment Model******************************/

var EquipmentModels = function () {
    var getEquipmentModels = function () {
        var data = {limit:200, sort: 'update_time', order:'desc'};

        ajaxGetWithData(getRequestUrl('/manage/equipment/model/list',basePath), data, function (responseData) {
            if (responseData) {
                var data = responseData;
                $("#models").empty();

                $.each(data.rows, function(index, row) {
                    var modelId=$.trim(row.equipmentModelId);
                    var html = '<li id="eqModel' +modelId +'" class="m-nav__item">' +
                        '<span class="m-nav-row-left">'+
                        '<a href="javascript:void(0)" style="color:#6f727d; padding-left:15px; height:50px;font-weight:400; font-size:1rem;" onclick="showModelProperties(' +modelId +')"> ' +row.name +
                        '</a></span> <span class="m-nav-row-right" style="position:absolute ;right:25px;">'+
                        '<div class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill"> <i class="modelEdit " onclick="modelEdit('+modelId+')"></i></div>'+
                        '<div class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill"><i class="modelDel " onclick="ModelDelete('+modelId+')"></i></div>'+
                        '</span>'+
                        '</li>';

                    $("#models").append(html);

                });

                $('[id^="eqModel"]').hover(function(){
                    var iObj= $(this);
                    iObj.find('.modelEdit').addClass('la la-edit');
                    iObj.find('.modelDel').addClass('la la-remove');
                    iObj.css("background-color","#f4f5f8");
                },function(){
                    var iObj= $(this);
                    iObj.find('.modelEdit').removeClass('la la-edit');
                    iObj.find('.modelDel').removeClass('la la-remove');
                    iObj.css("background-color","");
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
    ajaxGet(getRequestUrl('/manage/equipment/model/update/' + modelId,basePath), function (responseData) {
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
    deleteRows(rows,'equipmentModelId',getRequestUrl('/manage/equipment/model/delete/',basePath), "请确认要删除选中的设备模型吗？", "删除设备模型成功", null, EquipmentModels.init);
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
    var targetUrl=getRequestUrl('/manage/equipment/model/create',basePath);
    var formId='addModel_Form';
    if(id){
        targetUrl=getRequestUrl('/manage/equipment/model/update/'+id,basePath);
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
        url: getRequestUrl('/manage/equipment/model/property/list/-1',basePath),
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
        idField: 'equipmentModelId',
        sortOrder: 'desc',
        sortName: 'create_time',
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
        '<shiro:hasPermission name="eam:equipmentModelProperty:update"><a id="readWriteAction" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-success m-btn--icon m-btn--icon-only m-btn--pill" title="读写指令">	<i class="la la-send"></i>	</a></shiro:hasPermission>',
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
                if(formid === 'addModelProperty')
                    submitModelPropertyForm();
                else{
                    submitModelPropertyForm($('#editModelProperty_id').val());
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
        url: getRequestUrl("/manage/equipment/model/property/list/"+id,basePath)
    };

    $table.bootstrapTable('refresh', opt);
}

function submitModelPropertyForm(id) {
    var targetUrl=getRequestUrl('/manage/equipment/model/property/create',basePath);
    var formId='addModelProperty_Form';
    if(id){
        targetUrl=getRequestUrl('/manage/equipment/model/property/update/'+id,basePath);
        formId='editModelProperty_Form';
    }

    ajaxPost(targetUrl, formId, function(result) {
        if (result.code != 1) {
            sendErrorInfo(result);
        } else {
            if(formId=='addModelProperty_Form') {
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

    ajaxGet(getRequestUrl('/manage/equipment/model/property/update/' + row["equipmentModelPropertyId"],basePath), function (responseData) {
        if (responseData) {
            var data = responseData.equipmentModelProperties;
            // 赋值
            setModelProperty(data);
        }
    });
}

function setModelProperty(data) {
    $("#editModelProperty_id").val(data.equipmentModelPropertyId);
    $("#editModelProperty_name").val(data.name);
    $("#editModelProperty_unit").val(data.unit);

    if ('analog' === data.dataType) {
        $("#editModelProperty_dataType_analog").prop('checked', true);
    } else if ('digital' === data.dataType) {
        $("#editModelProperty_dataType_digital").prop('checked', true);
    }

    if ('pie' === data.displayType) {
        $("#editModelProperty_displayType_pie").prop('checked', true);
    } else if ('led' === data.displayType) {
        $("#editModelProperty_displayType_led").prop('checked', true);
    } else if ('guage' === data.displayType) {
        $("#editModelProperty_displayType_guage").prop('checked', true);
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
    var equipmentModelPropertyId = row["equipmentModelPropertyId"];
    ajaxGet(getRequestUrl('/manage/equipment/model/property/modbus/' + equipmentModelId +'/' + equipmentModelPropertyId, basePath), function (responseData) {
        if (responseData) {
            var protocolId = responseData.equipmentModel.protocolId;
            var equipmentModelId = responseData.equipmentModelProperties.equipmentModelId;
            var equipmentModelPropertyId = responseData.equipmentModelProperties.equipmentModelPropertyId;

            if (1 == protocolId){

                var sensor = responseData.sensor;
                if (sensor){

                    $("#editModbusFormContainer").modal("show");

                    addOptionToHtmlSelect(sensor.functionCode, 'editModbus_functionCode', responseData.modbusFunctionCodes);
                    addOptionToHtmlSelect(sensor.dataFormat, 'editModbus_dataFormat', responseData.dataFormats);
                    addOptionToHtmlSelect(sensor.bitOrder, 'editModbus_bitOrder', responseData.bitOrders);
                    $("#editModbus_address").val(sensor.address);
                    $("#editModbus_isl").val(sensor.isl);
                    $("#editModbus_ish").val(sensor.ish);
                    $("#editModbus_osl").val(sensor.osl);
                    $("#editModbus_osh").val(sensor.osh);
                    $("#editModbus_id").val(sensor.sensorId);
                    $("#editModbus_sensorId").val(sensor.sensorId);

                    $("#editModbus_protocolId").val(protocolId);
                    $("#editModbus_equipmentModelId").val(equipmentModelId);
                    $("#editModbus_equipmentModelPropertyId").val(equipmentModelPropertyId);

                    hideAndShowBitOrder();
                    hideAndShowDataChangeForModbus('edit')

                }else{
                    //modbus
                    $("#addModbusFormContainer").modal("show");
                    addOptionToHtmlSelect(null, "addModbus_functionCode", responseData.modbusFunctionCodes, "", "");
                    addOptionToHtmlSelect(null, "addModbus_dataFormat", responseData.dataFormats, "", "");
                    addOptionToHtmlSelect(null, "addModbus_bitOrder", responseData.bitOrders, "", "");
                    hideAndShowBitOrder();
                    hideAndShowDataChangeForModbus('add')

                    $("#addModbus_protocolId").val(protocolId);
                    $("#addModbus_equipmentModelId").val(equipmentModelId);
                    $("#addModbus_equipmentModelPropertyId").val(equipmentModelPropertyId);
                }
            }else if (4 == protocolId){
                //grm

                var sensor = responseData.sensor;
                if (sensor){

                    $("#editGrmFormContainer").modal("show");

                    $("#editGrm_isl").val(sensor.isl);
                    $("#editGrm_ish").val(sensor.ish);
                    $("#editGrm_osl").val(sensor.osl);
                    $("#editGrm_osh").val(sensor.osh);
                    $("#editGrm_id").val(sensor.sensorId);
                    $("#editGrm_sensorId").val(sensor.sensorId);
                    $("#editGrm_grmVariable").val(sensor.grmVariable);

                    $("#editGrm_protocolId").val(protocolId);
                    $("#editGrm_equipmentModelId").val(equipmentModelId);
                    $("#editGrm_equipmentModelPropertyId").val(equipmentModelPropertyId);

                    hideAndShowDataChangeForGrm('edit')

                }else{
                    $("#addGrmFormContainer").modal("show");
                    hideAndShowDataChangeForGrm('add')

                    $("#addGrm_protocolId").val(protocolId);
                    $("#addGrm_equipmentModelId").val(equipmentModelId);
                    $("#addGrm_equipmentModelPropertyId").val(equipmentModelPropertyId);
                }
            }

        }
    });

}

function addDataChangeForModbus(action) {
    if ('add' === action){
        $("#addModbus_dataChangeDiv").show();
        $("#addModbus_addDataChangeButtonDiv").hide();
    }else {
        $("#editModbus_dataChangeDiv").show();
        $("#editModbus_addDataChangeButtonDiv").hide();
    }
}

function removeDataChangeForModbus(action) {
    if ('add' === action){
        $("#addModbus_isl").val('');
        $("#addModbus_ish").val('');
        $("#addModbus_osl").val('');
        $("#addModbus_osh").val('');
        $("#addModbus_dataChangeDiv").hide();
        $("#addModbus_addDataChangeButtonDiv").show();
    }else {
        $("#editModbus_isl").val('');
        $("#editModbus_ish").val('');
        $("#editModbus_osl").val('');
        $("#editModbus_osh").val('');
        $("#editModbus_dataChangeDiv").hide();
        $("#editModbus_addDataChangeButtonDiv").show();
    }

}

function hideAndShowDataChangeForModbus(action) {
    if ('add' === action){
        $("#addModbus_dataChangeDiv").hide();
        $("#addModbus_addDataChangeButtonDiv").show();
    }else {
        if ($("#editModbus_isl").val() != ''){
            $("#editModbus_dataChangeDiv").show();
            $("#editModbus_addDataChangeButtonDiv").hide();
        }else {
            $("#editModbus_dataChangeDiv").hide();
            $("#editModbus_addDataChangeButtonDiv").show();
        }

    }
}

function addDataChangeForGrm(action) {
    if ('add' === action){
        $("#addGrm_dataChangeDiv").show();
        $("#addGrm_addDataChangeButtonDiv").hide();
    }else {
        $("#editGrm_dataChangeDiv").show();
        $("#editGrm_addDataChangeButtonDiv").hide();
    }
}

function removeDataChangeForGrm(action) {
    if ('add' === action){
        $("#addGrm_isl").val('');
        $("#addGrm_ish").val('');
        $("#addGrm_osl").val('');
        $("#addGrm_osh").val('');
        $("#addGrm_dataChangeDiv").hide();
        $("#addGrm_addDataChangeButtonDiv").show();
    }else {
        $("#editGrm_isl").val('');
        $("#editGrm_ish").val('');
        $("#editGrm_osl").val('');
        $("#editGrm_osh").val('');
        $("#editGrm_dataChangeDiv").hide();
        $("#editGrm_addDataChangeButtonDiv").show();
    }
}

function hideAndShowDataChangeForGrm(action) {
    if ('add' === action){
        $("#addGrm_dataChangeDiv").hide();
        $("#addGrm_addDataChangeButtonDiv").show();
    }else {
        if ($("#editGrm_isl").val() != ''){
            $("#editGrm_dataChangeDiv").show();
            $("#editGrm_addDataChangeButtonDiv").hide();
        }else {
            $("#editGrm_dataChangeDiv").hide();
            $("#editGrm_addDataChangeButtonDiv").show();
        }

    }
}


function hideAndShowBitOrder() {
    var addDataFormat = $("#addModbus_dataFormat").val();
    if (isContains(addDataFormat, '32')){
        $("#addModbus_bitOrderRow").show();
    }else{
        $("#addModbus_bitOrderRow").hide();
        $("#addModbus_bitOrder").val('');
    }


    var editDataFormat = $("#editModbus_dataFormat").val();
    if (isContains(editDataFormat, '32')){
        $("#editModbus_bitOrderRow").show();
    }else{
        $("#editModbus_bitOrderRow").hide();
        $("#editModbus_bitOrder").val('');
    }

}

var ModbusFormWidgets = function () {
    var createForm = function (formid) {
        $("#"+formid+"_Form").validate({
            // define validation rules
            rules: {
                functionCode: {
                    required: true
                },
                address: {
                    required: true
                },
                dataFormat: {
                    required: true
                },
            },
            submitHandler: function (form) {
                if(formid === 'addModbus')
                    submitModbusForm();
                else{
                    submitModbusForm($('#editModbus_id').val());
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

function submitModbusForm(id) {
    var targetUrl=getRequestUrl('/manage/sensor/create',basePath);
    var formId='addModbus_Form';
    if(id){
        formId='editModbus_Form';
    }

    ajaxPost(targetUrl, formId, function(result) {
        if (result.code != 1) {
            sendErrorInfo(result);
        } else {
            toastr.success("读写指令设置成功");
            if(formId=='addModbus_Form') {
                $('#addModbusFormContainer').modal('toggle');
            }else{
                $('#editModbusFormContainer').modal('toggle');
            }
        }
    });
}


var GrmFormWidgets = function () {
    var createForm = function (formid) {
        $("#"+formid+"_Form").validate({
            // define validation rules
            rules: {
                grmVariable: {
                    required: true
                }
            },
            submitHandler: function (form) {
                if(formid === 'addGrm')
                    submitGrmForm();
                else{
                    submitGrmForm($('#editGrm_id').val());
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

function submitGrmForm(id) {
    var targetUrl=getRequestUrl('/manage/sensor/create',basePath);
    var formId='addGrm_Form';
    if(id){
        formId='editGrm_Form';
    }

    ajaxPost(targetUrl, formId, function(result) {
        if (result.code != 1) {
            sendErrorInfo(result);
        } else {
            toastr.success("读写指令设置成功");
            if(formId=='addGrm_Form') {
                $('#addGrmFormContainer').modal('toggle');
            }else{
                $('#editGrmFormContainer').modal('toggle');
            }
        }
    });
}

var AlarmFormWidgets = function () {
    var createForm = function (formid) {
        $("#"+formid+"_Form").validate({
            // define validation rules
            rules: {
                AlarmVariable: {
                    required: true
                },
                alarmTarget: {
                    required: true
                },
                targetUser: {
                    required: true
                }
            },
            submitHandler: function (form) {
                if(formid === 'addAlarm')
                    submitAlarmForm();
                else{
                    submitAlarmForm($('#editAlarm_alarmId').val());
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

function submitAlarmForm(id) {
    var targetUrl=getRequestUrl('/manage/alarm/create',basePath);
    var formId='addAlarm_Form';
    if(id){
        formId='editAlarm_Form';
    }

    ajaxPost(targetUrl, formId, function(result) {
        if (result.code != 1) {
            sendErrorInfo(result);
        } else {
            toastr.success("报警参数设置成功");
            if(formId=='addAlarm_Form') {
                $('#addAlarmFormContainer').modal('toggle');
            }else{
                $('#editAlarmFormContainer').modal('toggle');
            }
        }
    });
}

function alarmAction(row) {
    var equipmentModelId = row["equipmentModelId"];
    var equipmentModelPropertyId = row["equipmentModelPropertyId"];
    ajaxGet(getRequestUrl('/manage/equipment/model/property/alarm/' + equipmentModelId +'/' + equipmentModelPropertyId,basePath), function (responseData) {
        if (responseData) {
            console.dir(responseData);
            var equipmentModelPropertyId = responseData.equipmentModelProperties.equipmentModelPropertyId;
            var alarmTypes = responseData.alarmTypes;
            var alarmTargets = responseData.alarmTargets;
            var users = responseData.users;

            var targetUsers = responseData.targetUsers;

            var targetUser = '';
            if (targetUsers.length > 0){
                targetUser = targetUsers[0];
            }
            var alarm = responseData.alarm;
            if (alarm){

                $("#editAlarmFormContainer").modal("show");
                $("#editAlarm_alarmId").val(alarm.alarmId);
                $("#editAlarm_id").val(alarm.alarmId);
                $("#editAlarm_equipmentModelPropertyId").val(equipmentModelPropertyId);

                addOptionToHtmlSelect(alarm.alarmType, 'editAlarm_alarmType', alarmTypes);
                $("#editAlarm_upperBound").val(alarm.upperBound);
                $("#editAlarm_lowerBound").val(alarm.lowerBound);
                $("#editAlarm_duration").val(alarm.duration);

                addOptionToHtmlSelect(alarm.alarmTarget, 'editAlarm_alarmTarget', alarmTargets);
                addOptionToHtmlSelect(targetUser.userId, 'editAlarm_targetUser', users);

                initAlarmType('editAlarm_');
            }else{
                $("#addAlarmFormContainer").modal("show");
                $("#addAlarm_equipmentModelPropertyId").val(equipmentModelPropertyId);

                addOptionToHtmlSelect(null, "addAlarm_alarmType", alarmTypes, "", "");
                addOptionToHtmlSelect(null, "addAlarm_alarmTarget", alarmTargets, "", "");
                addOptionToHtmlSelect(null, "addAlarm_targetUser", users, "", "");

                initAlarmType('addAlarm_');
            }


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
        deleteRows(rows,'equipmentModelPropertyId',getRequestUrl('/manage/equipment/model/property/delete/',basePath), "请确认要删除选中的设备模型参数吗？", "删除设备模型参数成功", null, refreshTable);
    }
}


function initAlarmType(templateId) {
    $('#'+templateId+'upperBoundDiv').hide();
    $('#'+templateId+'lowerBoundDiv').hide();
    $('#'+templateId+'durationDiv').hide();

    var alarmType = $('#'+templateId+'alarmType').val();

    if ('val_above' == alarmType){
        $('#'+templateId+'upperBoundDiv').show();
    }else if ('val_below' == alarmType){
        $('#'+templateId+'lowerBoundDiv').show();
    }else if ('val_above_below' == alarmType){
        $('#'+templateId+'upperBoundDiv').show();
        $('#'+templateId+'lowerBoundDiv').show();
    }else if ('val_above_below_ofm' == alarmType){
        $('#'+templateId+'upperBoundDiv').show();
        $('#'+templateId+'lowerBoundDiv').show();
        $('#'+templateId+'durationDiv').show();
    }else if ('x_tir_y_rec' == alarmType){
        $('#'+templateId+'upperBoundDiv').show();
        $('#'+templateId+'lowerBoundDiv').show();
    }else if ('val_between' == alarmType){
        $('#'+templateId+'upperBoundDiv').show();
        $('#'+templateId+'lowerBoundDiv').show();
    }else if ('val_above_bound' == alarmType){
        $('#'+templateId+'upperBoundDiv').show();
        $('#'+templateId+'durationDiv').show();

    }else if ('val_below_bound' == alarmType){
        $('#'+templateId+'lowerBoundDiv').show();
        $('#'+templateId+'durationDiv').show();

    }
}

function alarmTypeChange(templateId) {
    $('#'+templateId+'alarmType').change(function() {
        $('#'+templateId+'upperBoundDiv').hide();
        $('#'+templateId+'lowerBoundDiv').hide();
        $('#'+templateId+'durationDiv').hide();


        if ('val_above' == this.value){
            $('#'+templateId+'upperBoundDiv').show();

            $('#'+templateId+'lowerBoundDiv').val('');
            $('#'+templateId+'durationDiv').val('');
        }else if ('val_below' == this.value){
            $('#'+templateId+'lowerBoundDiv').show();

            $('#'+templateId+'upperBoundDiv').val('');
            $('#'+templateId+'durationDiv').val('');
        }else if ('val_above_below' == this.value){
            $('#'+templateId+'upperBoundDiv').show();
            $('#'+templateId+'lowerBoundDiv').show();

            $('#'+templateId+'durationDiv').val('');
        }else if ('val_above_below_ofm' == this.value){
            $('#'+templateId+'upperBoundDiv').show();
            $('#'+templateId+'lowerBoundDiv').show();
            $('#'+templateId+'durationDiv').show();
        }else if ('x_tir_y_rec' == this.value){
            $('#'+templateId+'upperBoundDiv').show();
            $('#'+templateId+'lowerBoundDiv').show();
            $('#'+templateId+'durationDiv').val('');
        }else if ('val_between' == this.value){
            $('#'+templateId+'upperBoundDiv').show();
            $('#'+templateId+'lowerBoundDiv').show();
            $('#'+templateId+'durationDiv').val('');
        }else if ('val_above_bound' == this.value){
            $('#'+templateId+'upperBoundDiv').show();
            $('#'+templateId+'durationDiv').show();
            $('#'+templateId+'lowerBoundDiv').val('');

        }else if ('val_below_bound' == this.value){
            $('#'+templateId+'lowerBoundDiv').show();
            $('#'+templateId+'durationDiv').show();
            $('#'+templateId+'upperBoundDiv').val('');

        }
    });
}