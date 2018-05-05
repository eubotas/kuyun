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
            <div class="modal-dialog modal-lg" role="document">
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
                        <div class="form-group m-form__group row">
                            <label class="col-lg-3 col-form-label">设备名称:* </label>
                            <div class="col-4" >
                                <select id="templateID_equipmentId" name="equipmentId" style="width: 100%">
                                </select>
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-3 col-form-label">工作内容:* </label>
                            <div class="col-8">
                                <textarea class="form-control m-input m-input--air" id="templateID_workContent" name="workContent" rows="4"></textarea>
                            </div>
                        </div>
                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-3 col-form-label">维修人员:* </label>
                            <div class="col-8" id="templateID_maintainUsersDiv">
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-3 col-form-label">下个维修日期:* </label>
                            <div class="form-group">
                                <div class="fg-line">
                                    <div class="input-group date" >
                                        <input id="templateID_nextMaintainDate" type="text" class="form-control" readonly  name="nextMaintainDate"/>
                                        <div class="input-group-append">
                                        <span class="input-group-text">
                                            <i class="la la-calendar-check-o"></i>
                                        </span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>


                        <div class="form-group m-form__group row">
                            <label class="col-lg-3 col-form-label">维修频率:* </label>
                            <div class="col-3" >
                                <input id="templateID_maintainFrequencyQuantity" type="text" class="form-control" name="maintainFrequencyQuantity" maxlength="4" value="${plan.maintainFrequencyQuantity}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                            </div>

                            <label class="col-lg-3 col-form-label">维修频率单位:* </label>
                            <div class="col-3" >
                                <select id="templateID_maintainFrequencyUnit" name="maintainFrequencyUnit" style="width: 100%">
                                </select>
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-3 col-form-label">维修提前提醒天数:* </label>
                            <div class="col-3" >
                                <input id="templateID_remindTime" type="text" class="form-control" name="remindTime" maxlength="4" value="${plan.remindTime}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
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
        <form id="updateForm" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
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
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">设备名称: </label>
                            <div class="col-sm-3" >
                                <span class="form-control" id="equipmentId"></span>
                            </div>

                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">工单描述: </label>

                            <div class="col-sm-10">

                               <textarea id="workContent" class="form-control" name="workContent"
                                      maxlength="200" rows="5" cols="150" readonly></textarea>

                            </div>
                        </div>
                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">维修人员: </label>
                            <div class="col-sm-3" >
                                <span class="form-control" id="maintainUsers"></span>
                            </div>

                            <label class="col-lg-3 col-form-label">下个维修日期: </label>
                            <div class="col-sm-3" >
                                <span class="form-control" id="nextMaintainDate"></span>
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">维修频率: </label>
                            <div class="col-sm-3" >
                                <span class="form-control" id="maintainFrequencyQuantity"></span>
                            </div>
                            <label class="col-lg-3 col-form-label">维修提前提醒天数: </label>
                            <div class="col-sm-3" >
                                <span class="form-control" id="remindTime"></span>
                            </div>
                        </div>
                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>


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

            $('#add_nextMaintainDate, #edit_nextMaintainDate').datepicker({
                todayHighlight: true,
                autoclose:true,
                format: "yyyy/mm/dd",
                orientation: "top left",
                templates: {
                    leftArrow: '<i class="la la-angle-left"></i>',
                    rightArrow: '<i class="la la-angle-right"></i>'
                }
            });

            $('#add_equipmentId, #edit_equipmentId').select2({minimumResultsForSearch: -1});
            $('#add_maintainFrequencyUnit, #edit_maintainFrequencyUnit').select2({minimumResultsForSearch: -1});

            $('#createButton').click(function(){
                $("#addMaintainPlanFormContainer").modal("show");
                ajaxGet('${basePath}/manage/maintainPlan/create', function (responseData) {
                    if (responseData) {
                        var data = responseData;
                        // 赋值
                        addOptionToHtmlSelect(null, "add_equipmentId", data.equipments );
                        addOptionToHtmlSelect(null, "add_maintainFrequencyUnit", data.units );

                        buildMaintainUsers('add',data.users);
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
                sortOrder: 'desc',
                sortName: 'a.create_time',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'equipmentName', title: '设备名称'},
                    {field: 'workContent', title: '维修内容'},
                    {field: 'maintainUsers', title: '维修人员'},
                    {field: 'nextMaintainDate', title: '下个维修日期', formatter: 'timeFormatter'},
                    {field: 'maintainFrequencyQuantity', title: '维修频率', formatter: 'maintainFrequencyQuantityFormatter'},
                    {field: 'action', width: 120, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });

        function maintainFrequencyQuantityFormatter(value , row, index) {
            return value + ' ' + getUnitName(row['maintainFrequencyUnit']);
            //return new Date(value).toLocaleString();
        }

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
                            required: true
                        },
                        maintainFrequencyUnit: {
                            required: true
                        },
                        remindTime: {
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
                    addOptionToHtmlSelect(data.plan.equipmentId, "edit_equipmentId", data.equipments );
                    addOptionToHtmlSelect(data.plan.maintainFrequencyUnit, "edit_maintainFrequencyUnit", data.units );
                    $("#edit_nextMaintainDate").val(data.MaintainDate);
                    $("#edit_workContent").val(data.plan.workContent);
                    $("#edit_maintainFrequencyQuantity").val(data.plan.maintainFrequencyQuantity);
                    $("#edit_remindTime").val(data.plan.remindTime);

                    buildMaintainUsers('edit', data.users, data.userIds);
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
                        //$("#equipmentCategoryId").text(data.plan.equipmentCategoryName);
                        $("#equipmentId").text(data.plan.equipmentName);
                        $("#maintainUsers").text(data.plan.maintainUsers);
                        $("#maintainFrequencyQuantity").text(data.plan.maintainFrequencyQuantity +' '+ getUnitName(data.plan.maintainFrequencyUnit));

                        $("#nextMaintainDate").text(data.plan.strNextMaintainDate);
                        $("#workContent").text(data.plan.workContent);
                        $("#remindTime").text(data.plan.remindTime);
                        loadTicketTab(planId);
                }
                });
            }
        };

        function buildMaintainUsers(templateId, users, userIds) {
            $("#"+templateId+"_maintainUsersDiv").empty();
            html = '<div class="m-checkbox-inline">';
            $.each(users,
                function(i, user) {
                    html += '<label class="m-checkbox">';

                    if (isChecked(user, userIds)){
                        html += '<input type="checkbox" name="maintainUserId" value='+user.VALUEFIELD +' checked>';

                    }else {
                        html += '<input type="checkbox" name="maintainUserId" value='+user.VALUEFIELD +'>';

                    }
                    html += user.DESCFIELD;
                    html += '<span></span>';
                    html += '</label>';
                }
            );
            $("#"+templateId+"_maintainUsersDiv").append(html);

        }

        function isChecked(user, userIds) {
            checked = false;
            $.each(userIds,
                function(i, id) {
                    if (id == user.VALUEFIELD){
                        checked = true;
                    }
                }
            );
            return checked;
        }

        function deleteAction(){
            var rows = $table.bootstrapTable('getSelections');
            deleteActionImpl(rows);
        }

        function deleteActionImpl(rows) {
            if (rows.length == 0) {
                swWarn("请至少选择一条记录");
            }else {
                deleteRows(rows,'planId','${basePath}/manage/maintainPlan/delete/', "请确认要删除选中的维修计划吗？", "删除维修计划成功");
            }//end else
        }

    </script>


    <script>
        function loadTicketTab(planId) {
            $('#ticketTable').bootstrapTable({
                url: '${basePath}/manage/maintainPlan/'+planId+'/tickets',
                striped: true,
                search: false,
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
                    {field: 'serviceman', title: '维修人'},
                    {field: 'servicePhone', title: '维修人电话'},
                    {field: 'ticketStatus', title: '当前状态'},
                    {field: 'createTime', title: '创建时间', formatter: 'timeFormatter'}
                ]
            });
        }

    </script>
</pageResources>


</body>
</html>