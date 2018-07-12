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
                        <a href="${basePath}/manage/ticket/index" class="m-nav__link m-nav__link--icon">
                            <i class="m-nav__link-icon la la-home"></i>工单列表
                        </a>
                    </li>
                    <li class="m-nav__separator">
                        -
                    </li>
                    <li class="m-nav__item">
                        <a href="${basePath}/manage/ticket/detail/${ticketId}" class="m-nav__link">
											<span class="m-nav__link-text">
												工单详细
											</span>
                        </a>
                    </li>
                    <li class="m-nav__separator">
                        -
                    </li>
                    <li class="m-nav__item">
                        <a href="" class="m-nav__link">
											<span class="m-nav__link-text">
												工单委派列表
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
            <div class="col-xl-2 col-lg-4">
                <div class="m-portlet m-portlet--full-height  ">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-caption">
                            <div class="m-portlet__head-title">
                                <h1 class="m-portlet__head-text">
                                    对该工单操作
                                </h1>
                            </div>
                        </div>
                    </div>

                    <div class="m-portlet__body">
                        <ul class="m-nav m-nav--hover-bg m-portlet-fit--sides" id="models">

                            <li class="m-nav__item"><a href="${basePath}/manage/ticket/${ticketId}/record/index" class="m-nav__link"> <span class="m-nav__link-text">工单记录管理</span></a></li>
                            <li class="m-nav__item"><a href="${basePath}/manage/ticket/${ticketId}/appoint/index" class="m-nav__link"> <span class="m-nav__link-text">工单委派管理</span></a></li>
                            <li class="m-nav__item"><a href="${basePath}/manage/ticket/${ticketId}/assessment/index" class="m-nav__link"> <span class="m-nav__link-text">工单评价管理</span></a></li>
                        </ul>
                        <div class="m-portlet__body-separator"></div>

                    </div>
                </div>
            </div>
            <div class="col-xl-10 col-lg-8">
                <div class="m-portlet m-portlet--full-height">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-caption">
                            <div class="m-portlet__head-title">
												<span class="m-portlet__head-icon">
													<i class="flaticon-multimedia"></i>
												</span>
                                <h3 class="m-portlet__head-text">
                                    工单委派
                                </h3>
                            </div>
                        </div>
                    </div>
                    <div class="m-portlet m-portlet--mobile">
                        <div class="m-portlet__body">
                            <div id="toolbar">
                                <div>
                                    <shiro:hasPermission name="eam:ticketAppointedRecord:create"><a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                                        <i class="la la-plus"></i>
                                    </a></shiro:hasPermission>

                                    <shiro:hasPermission name="eam:ticketAppointedRecord:delete"><a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                                        <i class="la la-remove"></i>
                                    </a></shiro:hasPermission>

                                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                                </div>
                            </div>

                            <table id="table" data-toolbar="#toolbar"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--begin::Modal-->
    <div id="addTicketRecordFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editTicketRecordFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-ticketRecord-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_工单委派
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label for="templateID_orderTakerId" class="form-control-label">工单维修人 *</label>
                            <select id="templateID_orderTakerId" name="orderTakerId" style="width: 100%">
                            </select>
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="templateID_rejectCommont">工单委派备注/拒绝原因</label>
                            <input id="templateID_rejectCommont" type="text" class="form-control" name="rejectCommont" maxlength="200">
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
            generateAddEditForm('template-ticketRecord-addEditForm', 'add_,edit_', null, null, 'addTicketRecordFormContainer,editTicketRecordFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#add_orderTakerId, #edit_orderTakerId').select2({minimumResultsForSearch: -1});

            $('#createButton').click(function(){
                $("#addTicketAppointFormContainer").modal("show");
                ajaxGet('${basePath}/manage/ticket/' + ticketId + '/appoint/create', function (responseData) {
                    if (responseData) {
                        var data = responseData;
                        addOptionToHtmlSelect(null, "add_orderTakerId", data.users);
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
                url: '${basePath}/manage/ticket/${ticketId}/appoint/list',
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
                idField: 'id',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'id', title: 'ID', sortable: true, align: 'center'},
                    {field: 'userName', title: '维修者'},
                    {field: 'phone', title: '电话号码'},
                    {field: 'rejectCommont', title: '备注'},
                    {field: 'createTime', title: '创建时间', formatter: 'timeFormatter'},
                    {field: 'action', width: 100, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:ticketRecord:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>'
            ].join('');
        }
        // 格式化时间
        function timeFormatter(value , row, index) {
            return new Date(value).toLocaleString();
        }

        var FormWidgets = function () {
            var createForm = function (formid) {
                $("#"+formid+"_Form").validate({
                    // define validation rules
                    rules: {
                        name: {
                            required: true,
                            minlength: 2,
                            maxlength: 50
                        },
                        address: {
                            required: true,
                            minlength: 2,
                            maxlength: 50
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
            var targetUrl='${basePath}/manage/ticket/${ticketId}/appoint/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/ticket/${ticketId}/appoint/reject';
                formId='edit_Form';
            }
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建工单委派成功");
                        $('#addTicketAppointFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑工单委派成功");
                        $('#editTicketAppointFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        window.actionEvents = {
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
                deleteRows(rows,'id','${basePath}/manage/ticket/${ticketId}/appoint/delete/', "请确认要删除选中的工单委派吗？", "删除工单委派成功");
            }//end else
        }

    </script>



</pageResources>


</body>
</html>