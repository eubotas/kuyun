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
												工单评价列表
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
                                    工单评价
                                </h3>
                            </div>
                        </div>
                    </div>
                    <div class="m-portlet m-portlet--mobile">
                        <div class="m-portlet__body">
                            <div id="toolbar">
                                <div>
                                    <shiro:hasPermission name="eam:ticketAssessment:create"><a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                                        <i class="la la-plus"></i>
                                    </a></shiro:hasPermission>

                                    <shiro:hasPermission name="eam:ticketAssessment:delete"><a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
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
    <div id="addTicketAssessmentFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editTicketAssessmentFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-ticketAssessment-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_工单评价
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label for="templateID_assessmentLevel">工单评价星级</label>
                            <select id="templateID_assessmentLevel"
                                    name="assessmentLevel" style="width: 100%">
                                <option value="1" <c:if test="${ticketAssessment.assessmentLevel==1}">selected</c:if> >1星</option>
                                <option value="2"<c:if test="${ticketAssessment.assessmentLevel==2}">selected</c:if> >2星</option>
                                <option value="3" <c:if test="${ticketAssessment.assessmentLevel==3}">selected</c:if> >3星</option>
                                <option value="4" <c:if test="${ticketAssessment.assessmentLevel==4}">selected</c:if> >4星</option>
                                <option value="5" <c:if test="${ticketAssessment.assessmentLevel==5}">selected</c:if> >5星</option>
                            </select>
                        </div>

                        <div class="form-group m-form__group row">
                            <label for="templateID_description">工单评价备注</label>
                            <input id="templateID_description" type="text" class="form-control" name="description" maxlength="200" >
                        </div>

                        <div class="form-group m-form__group row">
                            <label>工单评价标签</label>
                            <div style="margin-left: 20px;">
                            <c:forEach var="ticketTag" items="${ticketTags}" varStatus="status">
                                <input type="checkbox" name="ticketTag" value="${ticketTag.id}"  />${ticketTag.name}
                            </c:forEach>
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
            generateAddEditForm('template-ticketAssessment-addEditForm', 'add_,edit_', null, null, 'addTicketAssessmentFormContainer,editTicketAssessmentFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addTicketAssessmentFormContainer").modal("show");
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });

        });

        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/ticket/${ticketId}/assessment/list',
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
                    {field: 'id', title: 'ID', sortable: true, align: 'center'},
                    {field: 'assessmentLevel', title: '星级'},
                    {field: 'description', title: '备注'},
                    {field: 'tagNames', title: '评价标签'},
                    {field: 'action', width: 100, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:ticketAssessment:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:ticketAssessment:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>'
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
            var targetUrl='${basePath}/manage/ticket/${ticketId}/assessment/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/ticket/${ticketId}/assessment/update/'+id;
                formId='edit_Form';
            }
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建工单评价成功");
                        $('#addTicketAssessmentFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑工单评价成功");
                        $('#editTicketAssessmentFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            jQuery("#editTicketAssessmentFormContainer").modal("show");
            ajaxGet('${basePath}/manage/ticket/${ticketId}/assessment/update/' + row["id"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.ticketAssessment.id);
                    $("#edit_assessmentLevel").val(data.ticketAssessment.assessmentLevel);
                    $("#edit_description").val(data.ticketAssessment.description);
                    var tags = data.ticketAssessmentTags;
                    if(tags != undefined) {
                        $("#editTicketAssessmentFormContainer").find("input[name='ticketTag']").each(
                            function () {
                                var item = $(this).get(0);
                                if ($.inArray(Number(item.value), tags) >-1) {
                                    item.checked =true;
                                }
                            });
                    }
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
                deleteRows(rows,'id','${basePath}/manage/ticket/${ticketId}/assessment/delete/', "请确认要删除选中的工单评价吗？", "删除工单评价成功");
            }//end else
        }

    </script>



</pageResources>


</body>
</html>