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
												客户列表
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
                    <shiro:hasPermission name="eam:company:create"><a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a></shiro:hasPermission>

                    <shiro:hasPermission name="eam:company:delete"><a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                        <i class="la la-remove"></i>
                    </a></shiro:hasPermission>

                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                </div>
            </div>

            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>

    <!--begin::Modal-->
    <div id="addCompanyFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editCompanyFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-company-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_客户
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

                        <div class="m-form__group form-group">
                            <div class="m-radio-inline">
                                <label class="m-radio">
                                    <input id="templateID_sex_1" type="radio" name="sex" value="1" checked>男
                                    <span></span>
                                </label>
                                <label class="m-radio">
                                    <input id="templateID_sex_0" type="radio" name="sex" value="0">女<span></span>
                                </label>
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

    <div id="authEquipmentDialog" class="crudDialog modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <form id="personForm" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content" style="padding:20px">
                    <div class="modal-header">
                        <h5 class="modal-title" >
                            设备列表
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="form-group text-right dialog-buttons">
                        <button type="button" onclick="authEquipmentSubmit();" class="btn btn-primary" >确认授权</button>
                    </div>

                    <div>
                        <table id="tableEquipment"></table>
                    </div>

                    <div class="form-group text-right dialog-buttons">
                        <button type="button" onclick="authEquipmentSubmit();" class="btn btn-primary" >确认授权</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"> 取消 </button>
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
            generateAddEditForm('template-company-addEditForm', 'add_,edit_', null, null, 'addCompanyFormContainer,editCompanyFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addCompanyFormContainer").modal("show");
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });

        });

        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/company/list',
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
                    {field: 'name', title: '客户名称'},
                    {field: 'phone', title: '电话'},
                    {field: 'fax', title: '传真'},
                    {field: 'zip', title: '邮编'},
                    {field: 'www', title: '网址'},
                    {field: 'address', title: '客户地址'},
                    {field: 'action', title: '设备授权', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:company:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:company:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:company:update"><a id="equipmentAction" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="设备授权">	<i class="la la-edit"></i>	</a></shiro:hasPermission>'
            ].join('');
        }

        function submitForm(id) {
            var targetUrl='${basePath}/manage/company/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/company/update/'+id;
                formId='edit_Form';
            }
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建客户成功");
                        $('#addCompanyFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑客户成功");
                        $('#editCompanyFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            jQuery("#editCompanyFormContainer").modal("show");
            ajaxGet('${basePath}/manage/company/update/' + row["id"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.company.id);
                    $("#edit_name").val(data.company.name);
                    $("#edit_address").val(data.company.address);
                    $("#edit_phone").val(data.company.phone);
                    $("#edit_fax").val(data.company.fax);
                    $("#edit_zip").val(data.company.zip);
                    $("#edit_www").val(data.company.www);
                }
            });
        }

        window.actionEvents = {
            'click #update': function (e, value, row, index) {
                updateAction(row);
            },
            'click #equipmentAction': function (e, value, row, index) {
                selectCompanyId = row['id'];
                authEquipmentAction();
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
                deleteRows(rows,'id','${basePath}/manage/company/delete/', "请确认要删除选中的客户吗？", "删除客户成功");
            }//end else
        }

    </script>


    <script>
        var tableEquipment = $('#tableEquipment');
        var selectCompanyId, shownTableEquipment;

        function authEquipmentAction() {
            $("#authEquipmentDialog").modal("show");
            if(shownTableEquipment){
                tableEquipment.bootstrapTable( 'refresh', equipmentOpt);
            }else{
                shownTableEquipment = true;
                tableEquipment.bootstrapTable(equipmentOpt);
            }
        }

        var equipmentOpt = {
                url: '${basePath}/manage/company/equipment/list',
                queryParams:function(p){
                    return {    companyId : selectCompanyId,
                        limit : p.limit,
                        offset: p.offset,
                        search: p.search,
                        sort:   p.sort,
                        order:  p.order
                    }
                },
                striped: true,
                minimumCountColumns: 2,
                clickToSelect: true,
                detailFormatter: 'detailFormatter',
                pagination: true,
                paginationLoop: false,
                sidePagination: 'server',
                silentSort: false,
                smartDisplay: false,
                escape: true,
                searchOnEnterKey: true,
                idField: 'equipmentId',
                maintainSelected: true,
//            toolbar: '#toolbar',
                columns: [
                    {field: 'ck', checkbox: true,  formatter : checkFormatter},
                    {field: 'name', title: '设备名称', sortable: true, align: 'center'},
                    {field: 'number', title: '设备编号'}
                ]
            };

        function checkFormatter(value, row, index) {
            if (row.checked == true)
                return {
                    disabled : false,//设置是否可用
                    checked : true//设置选中
                };
            return value;
        }

        function authEquipmentSubmit() {
            var rows = tableEquipment.bootstrapTable('getSelections');
            if(!selectEquipmentId)
                swWarn('出错了, 请刷新重试！');
            else if (rows.length == 0) {
                swWarn('请至少选择一条记录！');
            }else {
                var ids = new Array();
                for (var i in rows) {
                    if (rows[i].equipmentId == null){
                        alert("请选择一记录！");
                        return false;
                    }
                }
                for (var i in rows) {
                    ids.push(rows[i].equipmentId);
                }

                ajaxPostData('${basePath}/manage/company/auth', {companyId: selectCompanyId, eIds: ids.join("::")}, function(result) {
                    if (result.code != 1) {
                        if (result.data instanceof Array) {
                            $.each(result.data, function(index, value) {
                                swError(value.errorMsg);
                            });
                        } else {
                            swError(result.data.errorMsg);
                        }
                    } else {
                        $('#authEquipmentDialog').modal('toggle');
                        $table.bootstrapTable('refresh');
                    }
                });
            }//end else
        }
    </script>

</pageResources>


</body>
</html>