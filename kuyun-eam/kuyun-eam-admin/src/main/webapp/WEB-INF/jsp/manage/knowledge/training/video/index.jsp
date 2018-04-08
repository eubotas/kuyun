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
												培训视频列表
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
                    <shiro:hasPermission name="eam:trainingVideo:create"><a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a></shiro:hasPermission>

                    <shiro:hasPermission name="eam:trainingVideo:delete"><a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                        <i class="la la-remove"></i>
                    </a></shiro:hasPermission>

                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                </div>
            </div>

            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>


    <!--begin::Modal-->
    <div id="addTrainingVideoFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editTrainingVideoFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-trainingVideo-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_培训视频
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
							<label for="templateID_title">标题</label>
							<input id="templateID_title" type="text" class="form-control" name="title" maxlength="30" >
                        </div>

						<div class="form-group m-form__group row">
							<label for="templateID_description">描述</label>
							<input id="templateID_description" type="text" class="form-control" name="description" maxlength="30" >
						</div>
						<div class="form-group m-form__group row">
							<label for="templateID_tag">标签</label>
							<input id="templateID_tag" type="text" class="form-control" name="tag" maxlength="200" >
						</div>

                        <div class="col-sm-12">
                            <div id="templateID_fine-uploader-gallery"></div>
                            <input id="templateID_path" type="hidden" class="form-control"  name="path" maxlength="500">
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

    <jsp:include page="/resources/metronic-admin/file_upload.jsp" flush="true"/>
    <script>

        var uploadOpt={
            template : 'qq-template-gallery',
            request : {
                endpoint : '${uploadServer.endpoint_upload}',
                params : {
                    kuyunModule : "eam"
                }
            },
            thumbnails : {
                placeholders : {
                    waitingPath : '${basePath}/resources/kuyun-admin/plugins/fileupload/placeholders/waiting-generic.png',
                    notAvailablePath : '${basePath}/resources/kuyun-admin/plugins/fileupload/placeholders/not_available-generic.png'
                }
            },
            validation : {
                /*  allowedExtensions: ['jpeg', 'jpg', 'gif', 'png'] */
            },
            chunking : {
                enabled : true,
                concurrent : {
                    enabled : true
                },
                success : {
                    endpoint : '${uploadServer.endpoint_uploadDone}'
                },
                mandatory : true
            },
            deleteFile : {
                enabled : true,
                forceConfirm : true,
                endpoint : '${uploadServer.endpoint_delete}'
            },
            cors : {
                //all requests are expected to be cross-domain requests
                expected : true,

                //if you want cookies to be sent along with the request
                // sendCredentials : true
            }
            /* init file list
            session:{
                    endpoint: '${uploadServer.endpoint_list}?ids=${uuids}'
                    }, */
        };

        var addGalleryUploader = new qq.FineUploader($.extend(uploadOpt, {element : document.getElementById("add_fine-uploader-gallery")}));
        var editGalleryUploader = new qq.FineUploader($.extend(uploadOpt, {element : document.getElementById("edit_fine-uploader-gallery")}));
    </script>

    <script>
        $(document).ready(function()
        {
            //codes works on all bootstrap modal windows in application
            $('.modal').on('hidden.bs.modal', function(e)
            {
                jQuery("#add_Form").validate().resetForm();
                jQuery("#edit_Form").validate().resetForm();
            }) ;
            generateAddEditForm('template-trainingVideo-addEditForm', 'add_,edit_', null, null, 'addTrainingVideoFormContainer,editTrainingVideoFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addTrainingVideoFormContainer").modal("show");
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });

        });

        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/knowledge/training/video/list',
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
                    {field: 'tag', title: '标签'},
                    {field: 'title', title: '标题'},
                    {field: 'path', title: '附件'},
                    {field: 'createTime', title: '创建时间', formatter: 'timeFormatter'},
                    {field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:trainingVideo:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:trainingVideo:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>'
            ].join('');
        }

        function submitForm(id) {
            var targetUrl='${basePath}/manage/knowledge/training/video/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/knowledge/training/video/update/'+id;
                formId='edit_Form';
                $('#edit_path').val(getUploadFileName(editGalleryUploader));
            }else
                $('#add_path').val(getUploadFileName(addGalleryUploader));

            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建培训视频成功");
                        $('#addTrainingVideoFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑培训视频成功");
                        $('#editTrainingVideoFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            jQuery("#editTrainingVideoFormContainer").modal("show");
            ajaxGet('${basePath}/manage/knowledge/training/video/update/' + row["id"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.maintain.id);
                    $("#edit_title").val(data.maintain.title);
                    $("#edit_description").val(data.maintain.description);
                    $("#edit_content").val(data.maintain.content);
                    $("#edit_tag").val(data.maintain.tag);
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
                deleteRows(rows,'id','${basePath}/manage/knowledge/training/video/delete/', "请确认要删除选中的培训视频吗？", "删除培训视频成功");
            }//end else
        }

    </script>


    <script src="${basePath}/resources/kuyun-admin/plugins/fileupload/fine-uploader.js"></script>
</pageResources>


</body>
</html>