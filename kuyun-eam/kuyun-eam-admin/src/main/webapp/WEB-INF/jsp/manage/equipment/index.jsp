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
												设备列表
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
                    <shiro:hasPermission name="eam:equipment:create"><a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a></shiro:hasPermission>

                    <shiro:hasPermission name="eam:equipment:delete"><a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                        <i class="la la-remove"></i>
                    </a></shiro:hasPermission>

                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                </div>
            </div>

            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>

    <!--begin::Modal-->
    <div id="addEquipmentFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editEquipmentFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-equipment-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_设备
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label for="templateID_warehouseId">仓库</label>
                            <select id="templateID_warehouseId" name="warehouseId" style="width: 100%" />
                        </div>

                        <div class="form-group m-form__group row">
                            <label for="templateID_number">设备编号</label>
                            <input id="templateID_number" type="text" class="form-control" name="number" maxlength="20">
                        </div>

						<div class="form-group m-form__group row">
							<label for="templateID_comments">备注</label>
							<input id="templateID_comments" type="text" class="form-control" name="comments" maxlength="200" >
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
            //codes works on all bootstrap modal windows in application
            $('.modal').on('hidden.bs.modal', function(e)
            {
                jQuery("#add_Form").validate().resetForm();
                jQuery("#edit_Form").validate().resetForm();
            }) ;
            generateAddEditForm('template-equipment-addEditForm', 'add_,edit_', null, null, 'addEquipmentFormContainer,editEquipmentFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addEquipmentFormContainer").modal("show");
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });

        });

        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/equipment/list',
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
                idField: 'equipmentId',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'name', title: '设备名称', sortable: true, align: 'center'},
                    {field: 'number', title: '设备编号'},
                    {field: 'maintenancePeriod', title: '维保周期'},
                    {field: 'action', width: 100, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:equipment:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:equipment:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>'
            ].join('');
        }

        function submitForm(id) {
            var targetUrl='${basePath}/manage/equipment/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/equipment/update/'+id;
                formId='edit_Form';
            }
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建设备成功");
                        $('#addEquipmentFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑设备成功");
                        $('#editEquipmentFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            jQuery("#editEquipmentFormContainer").modal("show");
            ajaxGet('${basePath}/manage/equipment/update/' + row["equipmentId"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.equipment.equipmentId);
                    $("#edit_category").val(data.equipment.category);
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
                deleteRows(rows,'equipmentId','${basePath}/manage/equipment/delete/', "请确认要删除选中的设备吗？", "删除设备成功");
            }//end else
        }

    </script>



</pageResources>


</body>
</html>