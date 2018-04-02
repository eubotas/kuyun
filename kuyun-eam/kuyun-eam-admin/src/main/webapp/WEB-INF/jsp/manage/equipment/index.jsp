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
                            templateTitleName_设备信息
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label for="templateID_name">设备名称</label>
                            <input id="templateID_name" type="text" class="form-control" name="name" maxlength="200">
                        </div>

                        <div class="form-group m-form__group row">
                            <div class="col-sm-6">
                            <label for="templateID_number">设备编号</label>
                            <input id="templateID_number" type="text" class="form-control" name="number" maxlength="20">
                            </div>
                            <div class="col-sm-6">
                            <label for="templateID_serialNumber">设备序列号</label>
                            <input id="templateID_serialNumber" type="text" class="form-control" name="serialNumber" maxlength="20" >
                            </div>
						</div>
                        <div class="form-group m-form__group row">
                            <select id="templateID_equipmentModelId" name="equipmentModelId" style="width: 100%"></select>
                        </div>
                        <div class="form-group m-form__group row">
                            <select id="templateID_equipmentCategoryId" name="equipmentCategoryId" style="width: 100%"></select>
                        </div>

                        <div class="form-group m-form__group row">
                            <label for="templateID_imagePath">设备图片</label>
                            <input id="templateID_imagePath" type="text" class="form-control" name="imagePath" maxlength="300">
                        </div>
                        <div class="form-group m-form__group row">
                            <div class="col-sm-6">
                            <label for="templateID_longitude">设备位置:经度</label>
                            <input id="templateID_longitude" type="text" class="form-control" name="longitude" maxlength="100">
                            </div>
                            <div class="col-sm-6">
                            <label for="templateID_latitude">纬度</label>
                            <input id="templateID_latitude" type="text" class="form-control" name="latitude" maxlength="100">
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <div class="col-sm-6">
                            <label for="templateID_factoryDate">出厂日期</label>
                            <input id="templateID_factoryDate" type="date" class="form-control m-input" name="factoryDate">
                            </div>
                            <div class="col-sm-6">
                            <label for="templateID_commissioningDate">投产日期</label>
                            <input id="templateID_commissioningDate" type="date" class="form-control m-input" name="commissioningDate">
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <div class="col-sm-6">
                            <label for="templateID_warrantyStartDate">质保开始日期</label>
                            <input id="templateID_warrantyStartDate" type="date" class="form-control m-input" name="warrantyStartDate">
                        </div>
                            <div class="col-sm-6">
                            <label for="templateID_warrantyEndDate">质保结束日期</label>
                            <input id="templateID_warrantyEndDate" type="date" class="form-control m-input" name="warrantyEndDate">
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="templateID_maintenancePeriod">维保周期</label>
                            <input id="templateID_maintenancePeriod" type="text" class="form-control" name="maintenancePeriod">
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
                ajaxGet('${basePath}/manage/equipment/create', function (responseData) {
                    if (responseData) {
                        var data = responseData;
                        addOptionToHtmlSelect(null, "add_equipmentCategoryId", data.equipmentCategories);
                        addOptionToHtmlSelect(null, "add_equipmentModelId", data.equipmentModels);
                    }
                });
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
                    {field: 'action', width: 120, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:equipment:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:equipment:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:equipmentSensor:write"><a id="write" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="数据写入">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
            ].join('');
        }

        function submitForm(id) {
            var uploads = galleryUploader.getUploads({
                status: qq.status.UPLOAD_SUCCESSFUL
            });
            var fileUuids = '';
            for (var i = 0; i < uploads.length; i++) {
                fileUuids = fileUuids + uploads[i].uuid + ",";
            }
            console.log("fileUuids = " + fileUuids);
            $('#imagePath').val(fileUuids);

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
                    var data = responseData.equipment;
                    addOptionToHtmlSelect(data.equipmentCategoryId, "edit_equipmentCategoryId", responseData.equipmentCategories);
                    addOptionToHtmlSelect(data.equipmentModelId, "edit_equipmentModelId", responseData.equipmentModels);
                    $("#edit_id").val(data.equipmentId);
                    $("#edit_name").val(data.name);
                    $("#edit_number").val(data.number);
                    $("#edit_serialNumber").val(data.serialNumber);
                    $("#edit_imagePath").val(data.imagePath);
                    $("#edit_longitude").val(data.longitude);
                    $("#edit_latitude").val(data.latitude);
                    $("#edit_factoryDate").val(data.factoryDate);
                    $("#edit_commissioningDate").val(data.commissioningDate);
                    $("#edit_warrantyStartDate").val(data.warrantyStartDate);
                    $("#edit_warrantyEndDate").val(data.warrantyEndDate);
                    $("#edit_maintenancePeriod").val(data.maintenancePeriod);
                }
            });
        }

        window.actionEvents = {
            'click #update': function (e, value, row, index) {
                updateAction(row);

            },
            'click #write': function (e, value, row, index) {
                window.location = "${basePath}/manage/equipment/sensor/" + row['equipmentId'];
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


    <link href="${basePath}/resources/kuyun-admin/plugins/fileupload/fine-uploader-gallery.css" rel="stylesheet">

    <!-- Fine Uploader Gallery template
  ====================================================================== -->
    <script type="text/template" id="qq-template-gallery">
        <div class="qq-uploader-selector qq-uploader qq-gallery" qq-drop-area-text="Drop files here">
            <div class="qq-total-progress-bar-container-selector qq-total-progress-bar-container">
                <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-total-progress-bar-selector qq-progress-bar qq-total-progress-bar"></div>
            </div>
            <div class="qq-upload-drop-area-selector qq-upload-drop-area" qq-hide-dropzone>
                <span class="qq-upload-drop-area-text-selector"></span>
            </div>
            <div class="qq-upload-button-selector qq-upload-button">
                <div>上传文件</div>
            </div>
            <span class="qq-drop-processing-selector qq-drop-processing">
                <span>Processing dropped files...</span>
                <span class="qq-drop-processing-spinner-selector qq-drop-processing-spinner"></span>
            </span>
            <ul class="qq-upload-list-selector qq-upload-list" role="region" aria-live="polite" aria-relevant="additions removals">
                <li>
                    <span role="status" class="qq-upload-status-text-selector qq-upload-status-text"></span>
                    <div class="qq-progress-bar-container-selector qq-progress-bar-container">
                        <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-progress-bar-selector qq-progress-bar"></div>
                    </div>
                    <span class="qq-upload-spinner-selector qq-upload-spinner"></span>
                    <div class="qq-thumbnail-wrapper">
                        <img class="qq-thumbnail-selector" qq-max-size="120" qq-server-scale>
                    </div>
                    <button type="button" class="qq-upload-cancel-selector qq-upload-cancel">X</button>
                    <button type="button" class="qq-upload-retry-selector qq-upload-retry">
                        <span class="qq-btn qq-retry-icon" aria-label="Retry"></span>
                        Retry
                    </button>

                    <div class="qq-file-info">
                        <div class="qq-file-name">
                            <span class="qq-upload-file-selector qq-upload-file"></span>
                            <span class="qq-edit-filename-icon-selector qq-edit-filename-icon" aria-label="Edit filename"></span>
                        </div>
                        <input class="qq-edit-filename-selector qq-edit-filename" tabindex="0" type="text">
                        <span class="qq-upload-size-selector qq-upload-size"></span>
                        <button type="button" class="qq-btn qq-upload-delete-selector qq-upload-delete">
                            <span class="qq-btn qq-delete-icon" aria-label="Delete"></span>
                        </button>
                        <button type="button" class="qq-btn qq-upload-pause-selector qq-upload-pause">
                            <span class="qq-btn qq-pause-icon" aria-label="Pause"></span>
                        </button>
                        <button type="button" class="qq-btn qq-upload-continue-selector qq-upload-continue">
                            <span class="qq-btn qq-continue-icon" aria-label="Continue"></span>
                        </button>
                    </div>
                </li>
            </ul>

            <dialog class="qq-alert-dialog-selector">
                <div class="qq-dialog-message-selector"></div>
                <div class="qq-dialog-buttons">
                    <button type="button" class="qq-cancel-button-selector">Close</button>
                </div>
            </dialog>

            <dialog class="qq-confirm-dialog-selector">
                <div class="qq-dialog-message-selector"></div>
                <div class="qq-dialog-buttons">
                    <button type="button" class="qq-cancel-button-selector">No</button>
                    <button type="button" class="qq-ok-button-selector">Yes</button>
                </div>
            </dialog>

            <dialog class="qq-prompt-dialog-selector">
                <div class="qq-dialog-message-selector"></div>
                <input type="text">
                <div class="qq-dialog-buttons">
                    <button type="button" class="qq-cancel-button-selector">Cancel</button>
                    <button type="button" class="qq-ok-button-selector">Ok</button>
                </div>
            </dialog>
        </div>
    </script>

</pageResources>


</body>
</html>