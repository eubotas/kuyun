﻿﻿<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
												维修计划列表
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
                    <shiro:hasPermission name="eam:maintainPlan:create">
                    <a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="eam:maintainPlan:delete">
                    <a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                        <i class="la la-remove"></i>
                    </a></shiro:hasPermission>

                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                </div>
            </div>

            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>

    <!--begin::Modal-->
    <div id="addMaintainPlanFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editMaintainPlanFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-plan-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_维修计划
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-6">
                                <label for="templateID_equipmentCategoryId">设备类型 *</label>
                                <div class="form-group">
                                    <select id="templateID_equipmentCategoryId" name="equipmentCategoryId" style="width: 100%">
                                    </select>
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="templateID_equipmentId">设备名称 * </label>
                                <div class="form-group">
                                    <div class="fg-line">
                                        <select id="templateID_equipmentId" name="equipmentId" style="width: 100%">
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="row">
                                <div class="col-sm-12" style="margin-left: 10px">
                                    <label for="templateID_workContent">工单描述</label>
                                    <div class="form-group">
					<textarea id="templateID_workContent" class="form-control" name="workContent"
                              maxlength="200" rows="4" cols="50">${plan.workContent }</textarea>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                <label for="templateID_orgId">维护部门 *</label>
                                <div class="form-group">
                                    <div class="fg-line">
                                        <select id="templateID_orgId" name="orgId" style="width: 100%">
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="templateID_nextMaintainDate">下个维护日期 *</label>
                                <div class="form-group">
                                    <div class="fg-line">
                                        <input id="templateID_nextMaintainDate" name="nextMaintainDate" type="date" class="form-control m-input" />
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-3">
                                <label for="templateID_maintainFrequencyQuantity">维护频率 *</label>
                                <div class="form-group">
                                    <div class="fg-line">
                                        <input id="templateID_maintainFrequencyQuantity" type="text" class="form-control" name="maintainFrequencyQuantity" maxlength="4" value="${plan.maintainFrequencyQuantity}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <label for="templateID_maintainFrequencyUnit">维护频率单位 *</label>
                                <div class="form-group">
                                    <div class="fg-line">
                                        <select id="templateID_maintainFrequencyUnit" name="maintainFrequencyUnit" style="width: 100%">
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-5">
                                <label for="templateID_remindTime">维护提前提醒天数 *</label>
                                <div class="form-group">
                                    <div class="fg-line">
                                        <input id="templateID_remindTime" type="text" class="form-control" name="remindTime" maxlength="4" value="${plan.remindTime}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                    </div>
                                </div>
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

    <div id="detailDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="updateForm" method="post" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content" style="padding:20px; width:700px;">
                    <div class="modal-header">
                        <h5 class="modal-title">
                            维修计划详情
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>

                    <div class="form-group m-form__group row">
                        <div class="col-sm-6">
                            <label >设备类型</label>
                            <div class="form-group" id="equipmentCategoryId">
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <label >设备名称</label>
                            <div class="form-group">
                                <div class="fg-line" id="equipmentId">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group m-form__group row" style="padding-left: 35px">
                        <div class="col-sm-12">
                            <label for="workContent">工单描述</label>
                            <div class="form-group">
                        <textarea id="workContent" class="form-control" name="workContent"
                                  maxlength="200" rows="4" cols="200"></textarea>

                            </div>
                        </div>
                </div>

                <div class="form-group m-form__group row">
                    <div class="col-sm-6">
                        <label >维护部门</label>
                        <div class="form-group">
                            <div class="fg-line" id="orgId">
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <label for="nextMaintainDate">下个维护日期</label>
                        <div class="form-group">
                            <div class="fg-line">
                                <input id="nextMaintainDate" name="nextMaintainDate" type="text" readonly />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group m-form__group row">
                    <div class="col-sm-6">
                        <label >维护频率</label>
                        <div class="form-group">
                            <div class="fg-line" id="maintainFrequencyQuantity">
                            </div>
                        </div>
                    </div>


                    <div class="col-sm-6">
                        <label >维护提前提醒天数</label>
                        <div class="form-group">
                            <div class="fg-line" id="remindTime">
                            </div>
                        </div>
                    </div>
                </div>


                    <div class="form-group m-form__group row">
                        <div class="col-sm-12">
                            <h5 class="modal-title">
                                关联工单列表
                            </h5>
                        </div>
                    </div>
                    <div>
                        <table id="ticketTable"></table>
                    </div>


                    <div class="form-group text-right dialog-buttons">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            关闭
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>


</content>


<pageResources>


    <script>
        $(document).ready(function()
        {
            $('.modal').on('hidden.bs.modal', function(e)
            {
                jQuery("#add_Form").validate().resetForm();
                jQuery("#edit_Form").validate().resetForm();
            }) ;
            generateAddEditForm('template-plan-addEditForm', 'add_,edit_', null, null, 'addMaintainPlanFormContainer,editMaintainPlanFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addMaintainPlanFormContainer").modal("show");
                ajaxGet('${basePath}/manage/maintainPlan/create', function (responseData) {
                    if (responseData) {
                        var data = responseData;
                        // 赋值
                        addOptionToHtmlSelect(null, "add_equipmentCategoryId", data.equipmentCategorys );
                        addOptionToHtmlSelect(null, "add_equipmentId", data.equipments );
                        addOptionToHtmlSelect(null, "add_orgId", data.orgs );
                        addOptionToHtmlSelect(null, "add_maintainFrequencyUnit", data.units );
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
                url: '${basePath}/manage/maintainPlan/list',
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
                idField: 'planId',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'planId', title: 'ID', sortable: true, align: 'center'},
                    {field: 'equipmentCategoryName', title: '设备目录'},
                    {field: 'equipmentName', title: '设备名称'},
                    {field: 'workContent', title: '工单内容'},
                    {field: 'orgName', title: '负责部门'},
                    {field: 'createTime', title: '创建时间', formatter: 'timeFormatter'},
                    {field: 'action', width: 120, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<a id="detail" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="详细">	<i class="la flaticon-file-1"></i>	</a>',
                '<shiro:hasPermission name="eam:maintainPlan:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:maintainPlan:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>'
            ].join('');
        }

        var FormWidgets = function () {
            var createForm = function (formid) {
                $("#"+formid+"_Form").validate({
                    // define validation rules
                    rules: {
                        nextMaintainDate: {
                            required: true
                        },
                        maintainFrequencyQuantity: {
                            required: true,
                            minlength: 1,
                            maxlength: 200
                        },
                        maintainFrequencyUnit: {
                            required: true
                        },
                        remindTime: {
                            required: true,
                            minlength: 1,
                            maxlength: 200
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
            var targetUrl='${basePath}/manage/maintainPlan/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/maintainPlan/update/'+id;
                formId='edit_Form';
            }
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建维修计划成功");
                        $('#addMaintainPlanFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑维修计划成功");
                        $('#editMaintainPlanFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            $("#editMaintainPlanFormContainer").modal("show");

            ajaxGet('${basePath}/manage/maintainPlan/update/' + row["planId"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.plan.planId);
                    addOptionToHtmlSelect(data.plan.equipmentCategoryId, "edit_equipmentCategoryId", data.equipmentCategorys );
                    addOptionToHtmlSelect(data.plan.equipmentId, "edit_equipmentId", data.equipments );
                    addOptionToHtmlSelect(data.plan.orgId, "edit_orgId", data.orgs );
                    addOptionToHtmlSelect(data.plan.maintainFrequencyUnit, "edit_maintainFrequencyUnit", data.units );
                    $("#edit_nextMaintainDate").val(data.MaintainDate);
                    $("#edit_workContent").val(data.plan.workContent);
                    $("#edit_maintainFrequencyQuantity").val(data.plan.maintainFrequencyQuantity);
                    $("#edit_remindTime").val(data.plan.remindTime);
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
            'click #detail': function (e, value, row, index) {
                $("#detailDialog").modal("show");
                var planId=row["planId"];
                ajaxGet('${basePath}/manage/maintainPlan/detail/' +planId , function (responseData) {
                    if (responseData) {
                        var data = responseData;
                        // 赋值
                        $("#equipmentCategoryId").text(data.plan.equipmentCategoryName);
                        $("#equipmentId").text(data.plan.equipmentName);
                        $("#orgId").text(data.plan.orgName);
                        $("#maintainFrequencyQuantity").text(data.plan.maintainFrequencyQuantity +' '+ data.plan.maintainFrequencyUnit);

                        $("#nextMaintainDate").text(data.plan.strNextMaintainDate);
                        $("#workContent").text(data.plan.workContent);
                        $("#remindTime").text(data.plan.remindTime);
                        loadTicketTab(planId);
                }
                });
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
                deleteRows(rows,'maintainPlanId','${basePath}/manage/maintainPlan/delete/', "请确认要删除选中的维修计划吗？", "删除维修计划成功");
            }//end else
        }

    </script>


    <script>
        function loadTicketTab(planId) {
            $('#ticketTable').bootstrapTable({
                url: '${basePath}/manage/maintainPlan/'+planId+'/tickets',
                striped: true,
                search: true,
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
                idField: 'planTicketId',
                sortName: 'planTicketId',
                sortOrder: 'desc',
                maintainSelected: true,
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'ticketDescription', title: '工单描述', sortable: true, align: 'center'},
                    {field: 'ticketPriority', title: '优先级'},
                    {field: 'ticketTypeName', title: '工单类型'},
                    {field: 'serviceman', title: '执行人'},
                    {field: 'servicePhone', title: '执行人电话'},
                    {field: 'customerContacts', title: '顾客'},
                    {field: 'customerPhone', title: '顾客电话'},
                    {field: 'ticketStatus', title: '当前状态'}
                ]
            });
        }

    </script>
</pageResources>


</body>
</html>