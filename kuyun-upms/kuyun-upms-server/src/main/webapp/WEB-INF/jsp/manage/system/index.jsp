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
												系统列表
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
                    <shiro:hasPermission name="upms:system:create"><a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a></shiro:hasPermission>

                    <shiro:hasPermission name="upms:system:delete"><a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                        <i class="la la-remove"></i>
                    </a></shiro:hasPermission>

                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                </div>
            </div>

            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>

    <!--begin::Modal-->
    <div id="addSystemFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editSystemFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-company-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_系统
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label for="templateID_icon">图标</label>
                            <input id="templateID_icon" type="text" class="form-control" name="icon" maxlength="20" value="${system.icon}">
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="templateID_title">标题</label>
                            <input id="templateID_title" type="text" class="form-control" name="title" maxlength="20" value="${system.title}">
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="templateID_name">名称</label>
                            <input id="templateID_name" type="text" class="form-control" name="name" maxlength="20" value="${system.name}">
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="templateID_theme">主题</label>
                            <input id="templateID_theme" type="text" class="form-control" name="theme" maxlength="50" value="${system.theme}">
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="templateID_banner">背景图</label>
                            <input id="templateID_banner" type="text" class="form-control" name="banner" maxlength="50" value="${system.banner}">
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="templateID_description">描述</label>
                            <input id="templateID_description" type="text" class="form-control" name="description" maxlength="300" value="${system.description}">
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="basepath">根目录</label>
                            <input id="basepath" type="text" class="form-control" name="basepath" maxlength="100" value="${system.basepath}">
                        </div>
                        <div class="radio  m-form__group row">
                            <div class="radio radio-inline radio-success">
                                <input id="templateID_status_1" type="radio" name="status" value="1" <c:if test="${system.status==1}">checked</c:if>>
                                <label for="templateID_status_1">正常 </label>
                            </div>
                            <div class="radio radio-inline">
                                <input id="templateID_status_0" type="radio" name="status" value="-1" <c:if test="${system.status!=1}">checked</c:if>>
                                <label for="templateID_status_0">锁定 </label>
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
            generateAddEditForm('template-system-addEditForm', 'add_,edit_', null, null, 'addSystemFormContainer,editSystemFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addSystemFormContainer").modal("show");
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });

        });

        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/system/list',
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
                idField: 'systemId',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'systemId', title: '编号', sortable: true, align: 'center'},
                    {field: 'icon', title: '图标', sortable: true, align: 'center', formatter: 'iconFormatter'},
                    {field: 'title', title: '系统标题'},
                    {field: 'name', title: '系统名称'},
                    {field: 'basepath', title: '根目录'},
                    {field: 'status', title: '状态', sortable: true, align: 'center', formatter: 'statusFormatter'},
                    {field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="upms:system:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="upms:system:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>'
            ].join('');
        }

        // 格式化图标
        function iconFormatter(value, row, index) {
            return '<i class="' + value + '"></i>';
        }
        // 格式化状态
        function statusFormatter(value, row, index) {
            if (value == 1) {
                return '<span class="label label-success">正常</span>';
            } else {
                return '<span class="label label-default">锁定</span>';
            }
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
            var targetUrl='${basePath}/manage/system/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/system/update/'+id;
                formId='edit_Form';
            }
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建系统成功");
                        $('#addSystemFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑系统成功");
                        $('#editSystemFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            jQuery("#editSystemFormContainer").modal("show");
            ajaxGet('${basePath}/manage/system/update/' + row["systemId"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.system.systemId);
                    $("#edit_icon").val(data.system.icon);
                    $("#edit_title").val(data.system.title);
                    $("#edit_name").val(data.system.name);
                    $("#edit_theme").val(data.system.theme);
                    $("#edit_banner").val(data.system.banner);
                    $("#edit_description").val(data.system.description);
                    $("#edit_basepath").val(data.system.basepath);
                    radioBoxcheck(data.system.status,'status');
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
                deleteRows(rows,'systemId','${basePath}/manage/system/delete/', "请确认要删除选中的系统吗？", "删除系统成功");
            }//end else
        }

    </script>



</pageResources>


</body>
</html>