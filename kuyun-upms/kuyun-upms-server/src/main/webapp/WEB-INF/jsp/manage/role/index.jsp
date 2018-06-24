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
												角色列表
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
                    <shiro:hasPermission name="upms:role:create">
                    <a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a></shiro:hasPermission>

                    <shiro:hasPermission name="upms:role:delete">
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
    <div id="addRoleFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editRoleFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-role-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_角色
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label class="col-3 col-form-label">名称:*</label>
                            <div class="col-8">
                                <input id="templateID_name" type="text" class="form-control" name="name" >
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-3 col-form-label">标题</label>
                            <div class="col-8">
                                <input id="templateID_title" type="text" class="form-control" name="title" >
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <label class="col-3 col-form-label">描述:*</label>
                            <div class="col-8">
                                <textarea class="form-control m-input m-input--air" id="templateID_description" name="description" rows="3"></textarea>
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

    <div id="permissionDialog" class="modal fade crudDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="permissionForm" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="form-group" style="padding:10px">
                        <ul id="ztree" class="ztree"></ul>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-primary" href="javascript:;" onclick="permissionSubmit();">
                            保存
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
            //codes works on all bootstrap modal windows in application
            $('.modal').on('hidden.bs.modal', function(e)
            {
                jQuery("#add_Form").validate().resetForm();
                jQuery("#edit_Form").validate().resetForm();
            }) ;
            generateAddEditForm('template-role-addEditForm', 'add_,edit_', null, null, 'addRoleFormContainer,editRoleFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addRoleFormContainer").modal("show");
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });

        });

        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/role/list',
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
                idField: 'roleId',
                sortOrder: 'desc',
                sortName: 'role_id',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'name', title: '角色名称'},
                    {field: 'title', title: '角色标题'},
                    {field: 'description', title: '角色描述'},
                    {field: 'action', width: 120, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });

            setSearchPlaceholder('角色标题');
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="upms:role:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="upms:role:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="upms:role:permission"><a id="assignPermission" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="分配权限">	<i class="la la-chevron-circle-right"></i>	</a></shiro:hasPermission>'
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
                        title: {
                            required: true,
                            minlength: 2,
                            maxlength: 20
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
            var targetUrl='${basePath}/manage/role/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/role/update/'+id;
                formId='edit_Form';
            }
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建角色成功");
                        $('#addRoleFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑角色成功");
                        $('#editRoleFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            $("#editRoleFormContainer").modal("show");

            ajaxGet('${basePath}/manage/role/update/' + row["roleId"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.role.roleId);
                    $("#edit_name").val(data.role.name);
                    $("#edit_title").val(data.role.title);
                    $("#edit_description").val(data.role.description);
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
            'click #assignPermission': function (e, value, row, index) {
                if (!row) {
                    swWarn("请至少选择一条记录");
                }else {
                    selectRoleId = row.roleId;
                    loadTree();
                }
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
                deleteRows(rows,'roleId','${basePath}/manage/role/delete/', "请确认要删除选中的角色吗？", "删除角色成功");
            }//end else
        }

    </script>


    <script>
        var selectRoleId;
        var changeDatas = [];
        function loadTree() {
            $("#permissionDialog").modal("show");
            var setting = {
                check: {
                    enable: true,
                    chkStyle: "checkbox",
                    // 勾选关联父，取消关联子
                    chkboxType: { "Y" : "p", "N" : "s" }
                },
                async: {
                    enable: true,
                    url: '${basePath}/manage/permission/role/' + selectRoleId
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onCheck: function() {
                        var zTree = $.fn.zTree.getZTreeObj("ztree")
                        var changeNodes = zTree.getChangeCheckedNodes();
                        for (var i = 0; i < changeNodes.length; i ++) {
                            var changeData = {};
                            changeData.id = changeNodes[i].id;
                            changeData.checked = changeNodes[i].checked;
                            changeDatas.push(changeData);
                        }
                    }
                }
            };
            $.fn.zTree.init($('#ztree'), setting);
        }

        function permissionSubmit() {
            ajaxPostData('${basePath}/manage/role/permission/' + selectRoleId, {datas: JSON.stringify(changeDatas), roleId: selectRoleId}, function(result) {
                if (result.code != 1) {
                    if (result.data instanceof Array) {
                        $.each(result.data, function(index, value) {
                            swSuccess(value.errorMsg);
                        });
                    } else {
                        swSuccess(result.data.errorMsg);
                    }
                } else {
                    $("#permissionDialog").modal("hide");
                    $table.bootstrapTable('refresh');
                }
            });
        }
    </script>

    <link href="${basePath}/resources/kuyun-admin/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"/>
    <script src="${basePath}/resources/kuyun-admin/plugins/zTree_v3/js/jquery.ztree.all.min.js"></script>
</pageResources>


</body>
</html>