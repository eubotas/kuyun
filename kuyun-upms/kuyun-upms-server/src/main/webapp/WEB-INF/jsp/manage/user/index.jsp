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
                    <shiro:hasPermission name="upms:user:create">
                    <a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="upms:user:delete">
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
                        <div class="form-group m-form__group row">
                            <label class="col-3 col-form-label">姓名:*</label>
                            <div class="col-8">
                                <input id="templateID_realname" type="text" class="form-control" name="realname">
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-3 col-form-label">密码:*</label>
                            <div class="col-8">
                                <input id="templateID_password" type="text" class="form-control" name="password">
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-3 col-form-label">电话:*</label>
                            <div class="col-8">
                                <input id="templateID_phone" type="text" class="form-control" name="phone">
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-3 col-form-label">邮箱:*</label>
                            <div class="col-8">
                                <input id="templateID_email" type="text" class="form-control" name="email">
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-3 col-form-label">头像:</label>
                            <div id="templateID_fine-uploader-gallery" class="col-8"></div>
                            <input id="templateID_avatar" type="hidden" class="form-control" name="avatar">
                        </div>

                        <div class="form-group m-form__group row">
                            <label class="col-3 col-form-label">是否启用:</label>
                            <div class="col-8">
                                <div class="m-radio-inline">
                                    <label class="m-radio">
                                        <input id="templateID_locked_0" type="radio" name="locked" value="0" checked>正常<span></span>
                                    </label>
                                    <label class="m-radio">
                                        <input id="templateID_locked_1" type="radio" name="locked" value="1">锁定<span></span>
                                    </label>
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

    <div id="orgDialog" class="modal fade crudDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="organizationForm" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">
                            部门列表
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">
                                &times;
                            </span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label class="col-2 col-form-label">部门:</label>
                            <div class="col-6">
                                <select multiple="" class="form-control m-input m-input--air" id="organizationId" name="organizationId">
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"> 取消 </button>
                        <button type="button" class="btn btn-primary" href="javascript:;" onclick="organizationSubmit();"> 保存 </button>
                    </div>
                </div>
            </div>
        </form>
    </div>


    <div id="roleDialog" class="modal fade crudDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="roleForm" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">
                            角色列表
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">
                                &times;
                            </span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label class="col-2 col-form-label">角色:</label>
                            <div class="col-6">
                                <select multiple="" class="form-control m-input m-input--air" id="roleId" name="roleId">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"> 取消 </button>
                        <button type="button" class="btn btn-primary" href="javascript:;" onclick="roleSubmit();"> 保存 </button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div id="permissionDialog" class="modal fade crudDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="permissionForm" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="row" style="padding:10px;">
                        <div class="col-sm-6">
                            <label>加权限</label>
                            <div class="form-group">
                                <div class="fg-line">
                                    <ul id="ztree1" class="ztree"></ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <label>减权限</label>
                            <div class="form-group">
                                <div class="fg-line">
                                    <ul id="ztree2" class="ztree"></ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"> 取消 </button>
                        <button type="button" class="btn btn-primary" href="javascript:;" onclick="permissionSubmit();"> 保存 </button>
                    </div>
                </div>
            </div>
        </form>
    </div>

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
            generateAddEditForm('template-user-addEditForm', 'add_,edit_', null, null, 'addUserFormContainer,editUserFormContainer');
             FormWidgets.init('add');
             FormWidgets.init('edit');

            addGalleryUploader = new qq.FineUploader($.extend(uploadOpt, {element : document.getElementById("add_fine-uploader-gallery")}));
            editGalleryUploader = new qq.FineUploader($.extend(uploadOpt, {element : document.getElementById("edit_fine-uploader-gallery")}));


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
                sortOrder: 'desc',
                sortName: 'user_id',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'realname', title: '姓名'},
                    {field: 'avatar', title: '头像', align: 'center', formatter: 'avatarFormatter'},
                    {field: 'phone', title: '电话'},
                    {field: 'email', title: '邮箱'},
                    {field: 'locked', title: '状态', sortable: true, align: 'center', formatter: 'lockedFormatter'},
                    {field: 'action', width: 190, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="upms:user:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="upms:user:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="upms:user:organization"><a id="userOrg" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="用户部门">	<i class="la flaticon-map"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="upms:user:role"><a id="userRole" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="用户角色">	<i class="la flaticon-users"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="upms:user:permission"><a id="userPermission" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="用户权限">	<i class="la flaticon-user"></i>	</a></shiro:hasPermission>',
            ].join('');
        }

        var FormWidgets = function () {
            var createForm = function (formid) {
                $("#"+formid+"_Form").validate({
                    // define validation rules
                    rules: {
                        username: {
                            required: true,
                            minlength: 2,
                            maxlength: 20
                        },
                        realname: {
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
                $('#edit_avatar').val(getUploadFileName(editGalleryUploader));
            }else {
                $('#add_avatar').val(getUploadFileName(editGalleryUploader));
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
                    $("#edit_realname").val(data.user.realname);
                    $("#edit_password").val(data.user.password);
                    $("#edit_avatar").val(data.user.avatar);
                    $("#edit_phone").val(data.user.phone);
                    $("#edit_email").val(data.user.email);
                    radioBoxcheck(data.user.locked,'locked');
                }
            });
        }

        window.actionEvents = {
            'click #update': function (e, value, row, index) {
                updateAction(row);

            },
            'click #userOrg': function (e, value, row, index) {
                $("#orgDialog").modal("show");
                selectUserId =row["userId"];
                userOrgAction();

            },
            'click #userRole': function (e, value, row, index) {
                $("#roleDialog").modal("show");
                selectUserId =row["userId"];
                userRoleAction();
            },
            'click #userPermission': function (e, value, row, index) {
                $("#permissionDialog").modal("show");
                selectUserId =row["userId"];
                loadTree();
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
                return '<img src="${basePath}/fileStorage/eam/' + value + '" style="width:20px;height:20px;"/>';
            else
                return "";

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


    <script>
        function userOrgAction() {
            ajaxGet('${basePath}/manage/user/organization/' + selectUserId, function (responseData) {
                if (responseData) {
                    var data = responseData;
                    addOptionToHtmlMultiSelect("organizationId",data.upmsOrganizations,data.upmsUserOrganizations);
                }
            });
        }

        function organizationSubmit() {
            ajaxPost('${basePath}/manage/user/organization/' + selectUserId,
                'organizationForm',function(result) {
                    if (result.code != 1) {
                        if (result.data instanceof Array) {
                            $.each(result.data, function (index, value) {
                                swError(value.errorMsg);
                            });
                        } else {
                            swError(result.data.errorMsg);
                        }
                    } else {
                        $("#orgDialog").modal("hide");
                        $table.bootstrapTable('refresh');
                    }
                }
            );
        }
    </script>

    <script>
        function userRoleAction() {
            ajaxGet('${basePath}/manage/user/role/' + selectUserId, function (responseData) {
                if (responseData) {
                    var data = responseData;
                    addOptionToHtmlMultiSelect("roleId",data.upmsRoles,data.upmsUserRoles);
                }
            });
        }

        function roleSubmit() {
            ajaxPost('${basePath}/manage/user/role/' + selectUserId,
                'roleForm',function(result) {
                    if (result.code != 1) {
                        if (result.data instanceof Array) {
                            $.each(result.data, function (index, value) {
                                swError(value.errorMsg);
                            });
                        } else {
                            swError(result.data.errorMsg);
                        }
                    } else {
                        $("#roleDialog").modal("hide");
                        $table.bootstrapTable('refresh');
                    }
                }
            );
        }
    </script>

    <script>
        //permission
        var selectUserId;
        var changeDatas = [];
        function loadTree() {
            var setting1 = {
                check: {
                    enable: true,
                    // 勾选关联父，取消关联子
                    chkboxType: { "Y" : "", "N" : "" }
                },
                async: {
                    enable: true,
                    url: '${basePath}/manage/permission/user/' + selectUserId + '?type=1'
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onCheck: function() {
                        var zTree = $.fn.zTree.getZTreeObj("ztree1")
                        var changeNodes = zTree.getChangeCheckedNodes();
                        for (var i = 0; i < changeNodes.length; i ++) {
                            var changeData = {};
                            changeData.id = changeNodes[i].id;
                            changeData.checked = changeNodes[i].checked;
                            changeData.type = 1;
                            changeDatas.push(changeData);
                        }
                    }
                }
            };
            var setting2 = {
                check: {
                    enable: true,
                    // 勾选关联父，取消关联子
                    chkboxType: { "Y" : "", "N" : "" }
                },
                async: {
                    enable: true,
                    url: '${basePath}/manage/permission/user/' + selectUserId + '?type=-1'
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onCheck: function() {
                        var zTree = $.fn.zTree.getZTreeObj("ztree2")
                        var changeNodes = zTree.getChangeCheckedNodes();
                        for (var i = 0; i < changeNodes.length; i ++) {
                            var changeData = {};
                            changeData.id = changeNodes[i].id;
                            changeData.checked = changeNodes[i].checked;
                            changeData.type = -1;
                            changeDatas.push(changeData);
                        }
                    }
                }
            };

            $.fn.zTree.init($('#ztree1'), setting1);
            $.fn.zTree.init($('#ztree2'), setting2);
        }

        function permissionSubmit() {
            ajaxPostData( '${basePath}/manage/user/permission/' + selectUserId,
                {datas: JSON.stringify(changeDatas), permissionUserId: selectUserId},function(result) {
                    if (result.code != 1) {
                        if (result.data instanceof Array) {
                            $.each(result.data, function (index, value) {
                                swError(value.errorMsg);
                            });
                        } else {
                            swError(result.data.errorMsg);
                        }
                    } else {
                        $("#permissionDialog").modal("hide");
                        $table.bootstrapTable('refresh');
                    }
                }
            );
        }
    </script>


    <link href="${basePath}/resources/kuyun-admin/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"/>
    <script src="${basePath}/resources/kuyun-admin/plugins/zTree_v3/js/jquery.ztree.all.min.js"></script>
</pageResources>


</body>
</html>