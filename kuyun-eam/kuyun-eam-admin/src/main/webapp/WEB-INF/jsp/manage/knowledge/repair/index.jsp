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
												维修知识列表
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
                    <shiro:hasPermission name="eam:repairKnowledge:create"><a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a></shiro:hasPermission>

                    <shiro:hasPermission name="eam:repairKnowledge:delete"><a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                        <i class="la la-remove"></i>
                    </a></shiro:hasPermission>

                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                </div>
            </div>

            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>

    <!--begin::Modal-->
    <div id="addRepairKnowledgeFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editRepairKnowledgeFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-repairKnowledge-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_维修知识
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label class="col-4 col-form-label">故障代码:*</label>
                            <div class="col-8">
                                <input id="templateID_codes" type="text" class="form-control" name="codes">
                            </div>
                        </div>

						<div class="form-group m-form__group row">
							<label class="col-4 col-form-label">故障描述:*</label>
                            <div class="col-8">
                                <textarea class="form-control m-input m-input--air" id="templateID_description"  name="description" rows="3"></textarea>
                            </div>
                        </div>

						<div class="form-group m-form__group row">
							<label class="col-4 col-form-label">解决故障的方法:*</label>
                            <div class="col-8">
                                <textarea class="form-control m-input m-input--air" id="templateID_method"  name="method" rows="5"></textarea>
                            </div>
						</div>

                        <div class="form-group m-form__group row">
                            <label class="col-4 col-form-label">标签</label>
                            <div class="col-8">
                                <input id="templateID_tag" type="text" class="form-control" name="tag" >
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <label class="col-4 col-form-label">附件:</label>
                            <div id="templateID_fileExistingSection" style="display:none;"> </div>
                            <div id="templateID_fine-uploader-gallery" class="col-8"></div>
                            <input id="templateID_path" type="hidden" class="form-control" name="path">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" id="templateID_id" name="id">
                        <input type="hidden" id="templateID_companyId" name="companyId">
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
    <jsp:include page="/resources/metronic-admin/file_upload.jsp" flush="true"/>

    <script>
        var addGalleryUploader;
        var editGalleryUploader;

        $(document).ready(function()
        {
            //codes works on all bootstrap modal windows in application
            $('.modal').on('hidden.bs.modal', function(e)
            {
                jQuery("#add_Form").validate().resetForm();
                jQuery("#edit_Form").validate().resetForm();
            }) ;
            generateAddEditForm('template-repairKnowledge-addEditForm', 'add_,edit_', null, null, 'addRepairKnowledgeFormContainer,editRepairKnowledgeFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            addGalleryUploader = new qq.FineUploader($.extend(uploadOpt, {element : document.getElementById("add_fine-uploader-gallery")}));
            editGalleryUploader = new qq.FineUploader($.extend(uploadOpt, {element : document.getElementById("edit_fine-uploader-gallery")}));


            $('#createButton').click(function(){
                resetFileUpload('addRepairKnowledgeFormContainer');
                $("#addRepairKnowledgeFormContainer").modal("show");
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });

        });

        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/knowledge/repair/list',
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
                idField: 'id',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'codes', title: '故障代码'},
                    {field: 'description', title: '故障描述'},
                    {field: 'tag', title: '标签'},
                    {field: 'path', title: '附件', formatter: 'fileFormatter', width:"250px", align: 'center'},
                    {field: 'action', title: '操作', align: 'center', width:"100px", formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:repairKnowledge:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:repairKnowledge:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>'
            ].join('');
        }

        var FormWidgets = function () {
            var createForm = function (formid) {
                $("#"+formid+"_Form").validate({
                    // define validation rules
                    rules: {
                        codes: {
                            required: true,
                        },
                        description: {
                            required: true,
                        },
                        method: {
                            required: true,
                        }
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
            var targetUrl='${basePath}/manage/knowledge/repair/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/knowledge/repair/update/'+id;
                formId='edit_Form';
                setImagePathEdit('edit_path', getUploadFileName(editGalleryUploader));
            }else {
                $('#add_path').val(getUploadFileName(addGalleryUploader));
            }

            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建维修知识成功");
                        $('#addRepairKnowledgeFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑维修知识成功");
                        $('#editRepairKnowledgeFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            resetFileUpload('editRepairKnowledgeFormContainer');
            jQuery("#editRepairKnowledgeFormContainer").modal("show");
            ajaxGet('${basePath}/manage/knowledge/repair/update/' + row["id"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.repair.id);
                    $("#edit_companyId").val(data.repair.companyId);
                    $("#edit_codes").val(data.repair.codes);
                    $("#edit_description").val(data.repair.description);
                    $("#edit_method").val(data.repair.method);
                    $("#edit_tag").val(data.repair.tag);
                    showFiles(data.repair.path, 'edit_path');
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
                deleteRowsChar(rows,'id','${basePath}/manage/knowledge/repair/delete/', '::', "请确认要删除选中的维修知识吗？", "删除维修知识成功");
            }//end else
        }

    </script>



</pageResources>


</body>
</html>