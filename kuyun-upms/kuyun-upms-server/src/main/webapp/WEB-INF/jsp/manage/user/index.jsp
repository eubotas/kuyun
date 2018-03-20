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
												用户列表
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
    <div id="addUserFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editUserFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-user-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_用户
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="templateID_username">帐号</label>
                            <input id="templateID_username" type="text" class="form-control" name="username" maxlength="20">
                        </div>
                        <div class="form-group">
                            <label for="templateID_password">密码</label>
                            <input id="templateID_password" type="text" class="form-control" name="password" maxlength="32">
                        </div>
                        <div class="form-group">
                            <label for="templateID_realname">姓名</label>
                            <input id="templateID_realname" type="text" class="form-control" name="realname" maxlength="20">
                        </div>
                        <div class="form-group">
                            <label for="templateID_avatar">头像</label>
                            <input id="templateID_avatar" type="text" class="form-control" name="avatar" maxlength="50">
                        </div>
                        <div class="form-group">
                            <label for="templateID_phone">电话</label>
                            <input id="templateID_phone" type="text" class="form-control" name="phone" maxlength="20">
                        </div>
                        <div class="form-group">
                            <label for="templateID_email">邮箱</label>
                            <input id="templateID_email" type="text" class="form-control" name="email" maxlength="50">
                        </div>
                        <div class="radio">
                            <div class="radio radio-inline radio-info">
                                <input id="templateID_sex_1" type="radio" name="sex" value="1" checked>
                                <label for="templateID_sex_1">男 </label>
                            </div>
                            <div class="radio radio-inline radio-danger">
                                <input id="templateID_sex_0" type="radio" name="sex" value="0">
                                <label for="templateID_sex_0">女 </label>
                            </div>
                            <div class="radio radio-inline radio-success">
                                <input id="templateID_locked_0" type="radio" name="locked" value="0" checked>
                                <label for="templateID_locked_0">正常 </label>
                            </div>
                            <div class="radio radio-inline">
                                <input id="templateID_locked_1" type="radio" name="locked" value="1">
                                <label for="templateID_locked_1">锁定 </label>
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
             applyTemplate(jQuery, '#template-user-addEditForm', 'add_', null, null, jQuery('#addUserFormContainer'));
             applyTemplate(jQuery, '#template-user-addEditForm', 'edit_', null, null, jQuery('#editUserFormContainer'));
             FormWidgets.init('add');
             FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addUserFormContainer").modal("show");
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });

        });

        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/user/list',
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
                idField: 'userId',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'userId', title: '编号', sortable: true, align: 'center'},
                    {field: 'username', title: '帐号'},
                    {field: 'realname', title: '姓名'},
                    {field: 'avatar', title: '头像', align: 'center', formatter: 'avatarFormatter'},
                    {field: 'phone', title: '电话'},
                    {field: 'email', title: '邮箱'},
                    {field: 'sex', title: '性别', formatter: 'sexFormatter'},
                    {field: 'locked', title: '状态', sortable: true, align: 'center', formatter: 'lockedFormatter'},
                    {field: 'action', width: 100, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
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
                        title: {
                            required: true,
                            minlength: 2,
                            maxlength: 20
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
            var targetUrl='${basePath}/manage/user/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/user/update/'+id;
                formId='edit_Form';
            }
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建用户成功");
                        $('#addUserFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑用户成功");
                        $('#editUserFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            $("#editUserFormContainer").modal("show");

            ajaxGet('${basePath}/manage/user/update/' + row["userId"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.user.userId);
                    $("#edit_username").val(data.user.username);
                    $("#edit_password").val(data.user.password);
                    $("#edit_realname").val(data.user.realname);
                    $("#edit_avatar").val(data.user.avatar);
                    $("#edit_phone").val(data.user.phone);
                    $("#edit_email").val(data.user.email);
                    radioBoxcheck(data.user.sex,'sex');
                    radioBoxcheck(data.user.locked,'locked');
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
                deleteRows(rows,'userId','${basePath}/manage/user/delete/', "请确认要删除选中的用户吗？", "删除用户成功");
            }//end else
        }

        // 格式化图标
        function avatarFormatter(value, row, index) {
            if(value != null && value != '')
                return '<img src="${basePath}' + value + '" style="width:20px;height:20px;"/>';
            else
                return "";

        }
        // 格式化性别
        function sexFormatter(value, row, index) {
            if (value == 1) {
                return '男';
            }
            if (value == 2) {
                return '女';
            }
            return '-';
        }
        // 格式化状态
        function lockedFormatter(value, row, index) {
            if (value == 1) {
                return '<span class="label label-default">锁定</span>';
            } else {
                return '<span class="label label-success">正常</span>';
            }
        }
    </script>



</pageResources>


</body>
</html>