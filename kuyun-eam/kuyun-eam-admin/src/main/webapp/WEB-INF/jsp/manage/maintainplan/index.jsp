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
                    <a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a>

                    <a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                        <i class="la la-remove"></i>
                    </a>

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
                                <label for="templateID_equipmentCategoryId">设备类型</label>
                                <div class="form-group">
                                    <select id="templateID_equipmentCategoryId" name="equipmentCategoryId" style="width: 100%">
                                        <c:forEach var="equipmentCategory" items="${equipmentCategorys}">
                                            <option value="${equipmentCategory.equipmentCategoryId}" <c:if test="${plan.equipmentCategoryId== equipmentCategory.equipmentCategoryId}">selected</c:if> >${equipmentCategory.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="templateID_equipmentId">设备名称</label>
                                <div class="form-group">
                                    <div class="fg-line">
                                        <select id="templateID_equipmentId" name="equipmentId" style="width: 100%">
                                            <c:forEach var="equipment" items="${equipments}">
                                                <option value="${equipment.equipmentId}"  <c:if test="${plan.equipmentId== equipment.equipmentId}">selected</c:if> >${equipment.name}</option>
                                            </c:forEach>

                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="row">
                                <div class="col-sm-12">
                                    <label for="templateID_workContent">工单描述</label>
                                    <div class="form-group">
					<textarea id="templateID_workContent" class="form-control" name="workContent"
                              maxlength="200" rows="4">${plan.workContent }</textarea>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                <label for="equipmentId">维护部门</label>
                                <div class="form-group">
                                    <div class="fg-line">
                                        <select id="templateID_orgId" name="orgId" style="width: 100%">
                                            <c:forEach var="org" items="${orgs}">
                                                <option value="${org.organizationId}" <c:if test="${plan.orgId== org.organizationId}">selected</c:if> >${org.name}</option>
                                            </c:forEach>

                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="templateID_nextMaintainDate">下个维护日期</label>
                                <div class="form-group">
                                    <div class="fg-line">
                                        <input id="templateID_nextMaintainDate" name="nextMaintainDate" type="text" value="${MaintainDate}" readonly />
                                        <img onclick="WdatePicker({dateFmt:dateFormat, minDate:getToday(), el:'nextMaintainDate'})" src="${basePath}/resources/kuyun-admin/plugins/My97DatePicker/skin/datePicker.gif" width="16" height="32" align="absmiddle">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label for="templateID_maintainFrequencyQuantity">维护频率</label>
                                <div class="form-group">
                                    <div class="fg-line">
                                        <input id="templateID_maintainFrequencyQuantity" type="text" class="form-control" name="maintainFrequencyQuantity" maxlength="4" value="${plan.maintainFrequencyQuantity}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <label for="equipmentId">维护频率单位</label>
                                <div class="form-group">
                                    <div class="fg-line">
                                        <select id="templateID_maintainFrequencyUnit" name="maintainFrequencyUnit" style="width: 100%">
                                            <c:forEach var="unit" items="${units}">
                                                <option value="${unit.codeValue}" <c:if test="${plan.maintainFrequencyUnit== unit.codeValue}">selected</c:if>>${unit.codeName}</option>
                                            </c:forEach>

                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="templateID_remindTime">维护提前提醒天数</label>
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


</content>


<pageResources>


    <script>
        $(document).ready(function()
        {
            //codes works on all bootstrap modal windows in application
            $('.modal').on('hidden.bs.modal', function(e)
            {
                //$(this).find('#add_Form')[0].reset();
                //$(this).find('#edit_Form')[0].reset();
            }) ;
            applyTemplate(jQuery, '#template-plan-addEditForm', 'add_', null, null, jQuery('#addMaintainPlanFormContainer'));
            applyTemplate(jQuery, '#template-plan-addEditForm', 'edit_', null, null, jQuery('#editMaintainPlanFormContainer'));
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addMaintainPlanFormContainer").modal("show");
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
                idField: 'maintainPlanId',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'planId', title: 'ID', sortable: true, align: 'center'},
                    {field: 'equipmentCategoryName', title: '设备目录'},
                    {field: 'equipmentName', title: '设备名称'},
                    {field: 'workContent', title: '工单内容'},
                    {field: 'orgName', title: '负责部门'},
                    {field: 'createTime', title: '创建时间', formatter: 'timeFormatter'},
                    {field: 'action', width: 100, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<a id="detail" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="详细">	<i class="la la-edit"></i>	</a>',
                '<a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a>',
                '<a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a>'
            ].join('');
        }

        var FormWidgets = function () {
            var createForm = function (formid) {
                $("#"+formid+"_Form").validate({
                    // define validation rules
                    rules: {
                        name: {
                            required: true,
                            minlength: 2,
                            maxlength: 20
                        },
                        description: {
                            required: true,
                            minlength: 2,
                            maxlength: 200
                        },
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

            ajaxGet('${basePath}/manage/maintainPlan/update/' + row["maintainPlanId"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.plan.maintainPlanId);
                    $("#edit_name").val(data.plan.name);
                    $("#edit_description").val(data.plan.description);
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
                deleteRows(rows,'maintainPlanId','${basePath}/manage/maintainPlan/delete/', "请确认要删除选中的维修计划吗？", "删除维修计划成功");
            }//end else
        }

    </script>



</pageResources>


</body>
</html>