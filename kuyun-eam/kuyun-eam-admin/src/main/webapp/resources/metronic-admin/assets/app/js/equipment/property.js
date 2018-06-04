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
        url: basePath+'/manage/equipment/model/property/list/'+${id}',
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
    var targetUrl=basePath+'/manage/equipment/model/property/create';
    var formId='add_Form';
    if(id){
        targetUrl=basePath+'/manage/equipment/model/property/update/'+id;
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
    ajaxGet(basePath+'/manage/equipment/model/property/update/' + row["equipmentModelPropertyId"], function (responseData) {
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

        var readWriteUrl = basePath+'/manage/equipment/model/property/modbus/${equipmentModel.equipmentModelId}/' + selectEquipmentModelPropertyId;
        if (1 == ${equipmentModel.protocolId}){
            $("#modbusShow").modal("show");
            readWriteUrl = basePath+'/manage/equipment/model/property/modbus/${equipmentModel.equipmentModelId}/' + selectEquipmentModelPropertyId;
        }else if (4 == ${equipmentModel.protocolId}){
            $("#grmShow").modal("show");
            readWriteUrl = basePath+'/manage/equipment/model/property/grm/${equipmentModel.equipmentModelId}/' + selectEquipmentModelPropertyId;
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
        ajaxGet(basePath+'/manage/equipment/model/property/data/change/${equipmentModel.equipmentModelId}/'+selectEquipmentModelPropertyId, function (responseData) {
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
        ajaxGet(basePath+'/manage/equipment/model/property/alarm/${equipmentModel.equipmentModelId}/'+selectEquipmentModelPropertyId, function (responseData) {
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
        deleteRows(rows,'equipmentModelPropertyId',basePath+'/manage/equipment/model/property/delete/', "请确认要删除选中的设备模型参数吗？", "删除设备模型参数成功");
    }//end else
}

function alermSubmit(formId){
    var targetUrl= basePath+'/manage/alarm/create';
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
    var targetUrl= basePath+'/manage/sensor/update';
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
    var targetUrl= basePath+'/manage/sensor/create';
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