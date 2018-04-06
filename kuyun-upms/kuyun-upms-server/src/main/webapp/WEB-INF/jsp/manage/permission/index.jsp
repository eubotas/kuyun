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
												权限列表
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
                    <shiro:hasPermission name="upms:permission:create">
                    <a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a></shiro:hasPermission>

                    <shiro:hasPermission name="upms:permission:delete">
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
    <div id="addPermissionFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editPermissionFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-permission-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_权限
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="radio  m-form__group">
                            <div class="radio radio-inline radio-success">
                                <input id="templateID_type_1" type="radio" name="type" value="1" checked>
                                <label for="templateID_type_1" class="form-control-label">目录 </label>
                            </div>
                            <div class="radio radio-inline radio-info">
                                <input id="templateID_type_2" type="radio" name="type" value="2">
                                <label for="templateID_type_2" class="form-control-label">菜单 </label>
                            </div>
                            <div class="radio radio-inline radio-warning">
                                <input id="templateID_type_3" type="radio" name="type" value="3">
                                <label for="templateID_type_3" class="form-control-label">按钮 </label>
                            </div>
                        </div>
                        <div class="form-group m-form__group">
                            <span class="type1 type2 type3">
                                <select id="templateID_systemId" name="systemId">
                                </select>
                            </span>
                                            <span class="type2 type3" hidden>
                                <select id="templateID_pid" name="pid">
                                    <option value="0">请选择上级</option>
                                </select>
                            </span>
                        </div>
                        <div class="form-group m-form__group">
                            <label for="templateID_name" class="form-control-label">名称</label>
                            <input id="templateID_name" type="text" class="form-control" name="name" maxlength="20">
                        </div>
                        <div class="form-group m-form__group type2 type3" hidden>
                            <label for="templateID_permissionValue" class="form-control-label">权限值</label>
                            <input id="templateID_permissionValue" type="text" class="form-control" name="permissionValue" maxlength="50">
                        </div>
                        <div class="form-group  m-form__group type2 type3" hidden>
                            <label for="templateID_uri" class="form-control-label">路径</label>
                            <input id="templateID_uri" type="text" class="form-control" name="uri" maxlength="100">
                        </div>
                        <div class="form-group  m-form__group type1 type3">
                            <label for="templateID_icon" class="form-control-label">图标</label>
                            <input id="templateID_icon" type="text" class="form-control" name="icon" maxlength="50" value="zmdi zmdi-widgets">
                        </div>
                        <div class="radio  m-form__group">
                            <div class="radio radio-inline radio-success">
                                <input id="templateID_status_1" type="radio" name="status" value="1" checked>
                                <label for="templateID_status_1" class="form-control-label">正常 </label>
                            </div>
                            <div class="radio radio-inline">
                                <input id="templateID_status_0" type="radio" name="status" value="0">
                                <label for="templateID_status_0" class="form-control-label">锁定 </label>
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
            generateAddEditForm('template-permission-addEditForm', 'add_,edit_', null, null, 'addPermissionFormContainer,editPermissionFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addPermissionFormContainer").modal("show");
                ajaxGet('${basePath}/manage/permission/create', function (responseData) {
                    if (responseData) {
                        var data = responseData;
                        addOptionToHtmlSelect(null, "add_systemId",data.upmsSystems, "0","请选择系统");
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
                url: '${basePath}/manage/permission/list',
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
                idField: 'permissionId',
                columns: [
                    {field: 'ck', checkbox: true},
                    // {field: 'permissionId', title: '编号', sortable: true, align: 'center'},
                    // {field: 'systemId', title: '所属系统'},
                    // {field: 'pid', title: '所属上级'},
                    {field: 'name', title: '权限名称'},
                    {field: 'type', title: '类型', formatter: 'typeFormatter'},
                    {field: 'permissionValue', title: '权限值'},
                    {field: 'uri', title: '路径'},
                    {field: 'icon', title: '图标', align: 'center', formatter: 'iconFormatter'},
                    {field: 'status', title: '状态', sortable: true, align: 'center', formatter: 'statusFormatter'},
                    {field: 'action', width: 100, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="upms:permission:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="upms:permission:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>'
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
            var targetUrl='${basePath}/manage/permission/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/permission/update/'+id;
                formId='edit_Form';
            }
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建权限成功");
                        $('#addPermissionFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑权限成功");
                        $('#editPermissionFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            $("#editPermissionFormContainer").modal("show");

            ajaxGet('${basePath}/manage/permission/update/' + row["permissionId"], function (responseData) {
                if (responseData) {
                    var data = responseData;

                    $("#edit_id").val(data.permission.permissionId);
                    radioBoxcheck(data.permission.type,'type');
                    addOptionToHtmlSelect(data.permission.systemId, "edit_systemId",data.upmsSystems, "0","请选择系统");
                    $("#edit_name").val(data.permission.name);
                    $("#edit_permissionValue").val(ifNull(data.permission.permissionValue));
                    $("#edit_uri").val(ifNull(data.permission.uri));
                    $("#edit_icon").val(ifNull(data.permission.icon));
                    radioBoxcheck(data.permission.status,'status');
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
                deleteRows(rows,'permissionId','${basePath}/manage/permission/delete/', "请确认要删除选中的权限吗？", "删除权限成功");
            }//end else
        }

        //original function
        // 格式化图标
        function iconFormatter(value, row, index) {
            return '<i class="' + value + '"></i>';
        }
        // 格式化类型
        function typeFormatter(value, row, index) {
            if (value == 1) {
                return '目录';
            }
            if (value == 2) {
                return '菜单';
            }
            if (value == 3) {
                return '按钮';
            }
            return '-';
        }
        // 格式化状态
        function statusFormatter(value, row, index) {
            if (value == 1) {
                return '<span class="label label-success">正常</span>';
            } else {
                return '<span class="label label-default">锁定</span>';
            }
        }
    </script>



</pageResources>


</body>
</html>