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
												DTU列表
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
        <div class="m-portlet__head">
            <div class="m-portlet__head-caption">
                <div class="m-portlet__head-title">
                    <h3 class="m-portlet__head-text">
                        <small>
                        <span class="col-sm-5">
                            设备协议: <c:out value="${protocol.name}"/>
                        </span>
                        <span class="col-sm-5">
                            IP: <c:out value="${protocol.ip}"/>
                        </span>
                        <span class="col-sm-5">
                            端口: <c:out value="${protocol.port}"/>
                        </span>
                        </small>
                    </h3>
                </div>
            </div>
        </div>

        <div class="m-portlet__body">
            <div id="toolbar">
                <div>
                    <shiro:hasPermission name="eam:dtu:create"><a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a></shiro:hasPermission>

                    <shiro:hasPermission name="eam:dtu:delete"><a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                        <i class="la la-remove"></i>
                    </a></shiro:hasPermission>

                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                </div>
            </div>

            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>

    <!--begin::Modal-->
    <div id="addDtuFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editDtuFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-dtu-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_DTU
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
						<div class="form-group m-form__group row">
                            <label for="templateID_name" class="col-3 col-form-label">名称:*</label>
                            <div class="col-sm-8">
                                <input id="templateID_name" type="text" class="form-control" name="name" maxlength="20">
                            </div>
						</div>

						<div class="form-group m-form__group row">
                            <label for="templateID_heartData" class="col-3 col-form-label">心跳包:</label>
                            <div class="col-sm-8">
                                <input id="templateID_heartData" type="text" class="form-control" name="heartData" maxlength="20">
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


    <div class="modal fade" id="equipmentDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="equipmentForm" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">
                            接入设备
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table id="tableEquipment"></table>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-primary" onclick="equipmentSubmit('equipmentForm');">
                            确认接入
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!--end::Modal-->


</content>


<pageResources>
    <link href="${basePath}/resources/metronic-admin/assets/js/bootstrap4-editable/css/bootstrap-editable.css" rel="stylesheet"/>
    <script src="${basePath}/resources/metronic-admin/assets/js/bootstrap4-editable/js/bootstrap-editable.min.js"></script>
    <script src="${basePath}/resources/metronic-admin/assets/js/bootstrap-table.1.11.1/extensions/editable/bootstrap-table-editable.min.js"></script>



    <script>
        $.fn.editable.defaults.mode = 'inline';

        $(document).ready(function()
        {
            //codes works on all bootstrap modal windows in application
            $('.modal').on('hidden.bs.modal', function(e)
            {
                jQuery("#add_Form").validate().resetForm();
                jQuery("#edit_Form").validate().resetForm();
            }) ;
            generateAddEditForm('template-dtu-addEditForm', 'add_,edit_', null, null, 'addDtuFormContainer,editDtuFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addDtuFormContainer").modal("show");
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });

        });

        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/dtu/list',
                striped: true,
                search: true,
                searchAlign: 'left',
                toolbarAlign: 'right',
                minimumCountColumns: 2,
                clickToSelect: true,
                pagination: true,
                paginationLoop: false,
                sidePagination: 'server',
                silentSort: false,
                smartDisplay: false,
                escape: true,
                searchOnEnterKey: true,
                maintainSelected: true,
                idField: 'dtuId',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'dtuId', title: '注册包', align: 'center'},
                    {field: 'name', title: '名称', align: 'center'},
                    {field: 'heartData', title: '心跳包', align: 'center'},
                    {field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
            setSearchPlaceholder('名称');
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:dtu:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:dtu:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:dtu:update"><a id="connectEquipment" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="接入设备">	<i class="la la-cogs"></i>	</a></shiro:hasPermission>',
            ].join('');
        }


        var FormWidgets = function () {
            var createForm = function (formid) {
                $("#"+formid+"_Form").validate({
                    // define validation rules
                    rules: {
                        name: {
                            required: true,
                            maxlength: 50
                        },
                    },
                    submitHandler: function (form) {
                        if(formid === 'add')
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
            var targetUrl='${basePath}/manage/dtu/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/dtu/update/'+id;
                formId='edit_Form';
            }
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建DTU成功");
                        $('#addDtuFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑DTU成功");
                        $('#editDtuFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            jQuery("#editDtuFormContainer").modal("show");
            ajaxGet('${basePath}/manage/dtu/update/' + row["dtuId"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.dtu.dtuId);
                    $("#edit_name").val(data.dtu.name);
                    $("#edit_heartData").val(data.dtu.heartData);
                }
            });
        }

        window.actionEvents = {
            'click #update': function (e, value, row, index) {
                updateAction(row);
            },
            'click #delete': function (e, value, row, index) {
                var rows = new Array();
                rows.push(row);
                deleteActionImpl(rows);
            },
            'click #connectEquipment': function (e, value, row, index) {
                $('#equipmentDialog').modal('show');
                selectedDtuId = row['dtuId'];
                equipmentAction();
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
                deleteRows(rows,'dtuId','${basePath}/manage/dtu/delete/', "请确认要删除选中的DTU吗？", "删除DTU成功");
            }//end else
        }

        var selectedDtuId, showEquipmentDialog;
        var tableEquipment = $('#tableEquipment');
        var equipmentOpt={
            url: '${basePath}/manage/dtu/equipment/list',
            queryParams:function(p){
                return {    dtuId : selectedDtuId,
                    limit : p.limit,
                    offset: p.offset,
                    search: p.search,
                    sort:   p.sort,
                    order:  p.order
                }
            },
            striped: true,
            minimumCountColumns: 2,
            clickToSelect: true,
            pagination: true,
            paginationLoop: false,
            sidePagination: 'server',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            idField: 'equipmentId',
            maintainSelected: true,
            columns: [
                {field: 'ck', checkbox: true,  formatter : checkFormatter},
                {field: 'name', title: '设备名称', sortable: true, align: 'center'},
                {field: 'number', title: '设备编号'},
                {field: 'modbusRtuPeriod', title: '采集频率', editable: {
                    type: 'text',
                    tpl: '<input type="number" style="width: 80px">',
                    validate: function (value) {
                        if ($.trim(value) == '') {
                            return '采集频率不能为空!';
                        }
                    }
                }, align: 'center'},

                {field: 'salveId', title: '从站地址', editable: {
                        type: 'text',
                        tpl: '<input type="number" style="width: 80px">',
                        validate: function (value) {
                            if ($.trim(value) == '') {
                                return '从站地址不能为空!';
                            }
                        }
                    }, align: 'center'}
            ],
            onEditableSave: function(field, row, oldValue, $el) {
                console.log(JSON.stringify(row));
                // field:修改的欄位
                // row:修改後的資料(JSON Object)
                // oldValue:修改前的值
                // -------------------------------------------------
                // 可用ajax方法去更新資料
                $.ajax({
                    type: "post",
                    url: '${basePath}/manage/dtu/equipment/write',
                    data: JSON.stringify(row),
                    contentType:"application/json",
                    dataType: 'JSON',
                    success: function(data, status) {
                        if (status == "success") {
                            toastr.success(data.data);
                        }
                    },
                    error: function() {
                        toastr.error("更新数据失败");
                    },
                    complete: function() {

                    }

                });
                // -------------------------------------------------
            }
        };

        function equipmentAction() {
            $("#equipmentDialog").modal("show");
            if(showEquipmentDialog){
                tableEquipment.bootstrapTable( 'refresh', equipmentOpt);
            }else{
                showEquipmentDialog = true;
                tableEquipment.bootstrapTable(equipmentOpt);
            }
        }

        function equipmentSubmit() {
            var rows = tableEquipment.bootstrapTable('getSelections');

            for (var i in rows) {
                if (rows[i].modbusRtuPeriod == null ){
                    toastr.error("请输入采集频率");
                    return false;
                }else if (rows[i].salveId == null ){
                    toastr.error("请输入从站地址");
                    return false;
                }
            }

            var ids = new Array();
            for (var i in rows) {
                ids.push(rows[i].equipmentId);
            }
            ajaxPostData('${basePath}/manage/dtu/connect', {dtuId: selectedDtuId, eIds: ids.join("::")}, function(result) {
                if (result.code != 1) {
                    if (result.data instanceof Array) {
                        $.each(result.data, function(index, value) {
                            swError(value.errorMsg);
                        });
                    } else {
                        swError(result.data.errorMsg);
                    }
                } else {
                    if (ids.length == 0){
                        toastr.success("取消接入成功");
                    }else{
                        toastr.success("接入设备成功");
                    }

                    $("#equipmentDialog").modal("hide");
                }
            });
        }


        function checkFormatter(value, row, index) {
            if (row.checked == true)
                return {
                    checked : true//设置选中
                };
            return value;
        }
    </script>



</pageResources>


</body>
</html>