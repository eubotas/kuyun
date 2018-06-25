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
												部门列表
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
                    <shiro:hasPermission name="upms:organization:create">
                    <a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a></shiro:hasPermission>

                    <shiro:hasPermission name="upms:organization:delete">
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
    <div id="addOrgFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editOrgFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-org-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_部门
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label class="col-2 col-form-label">名称:*</label>
                            <div class="col-8">
                                <input id="templateID_name" type="text" class="form-control" name="name">
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <label class="col-2 col-form-label">描述:*</label>
                            <div class="col-8">
                                <textarea class="form-control m-input m-input--air" id="templateID_description"  name="description" rows="4"></textarea>
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

    <div id="assignPersonDialog" class="crudDialog modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <form id="personForm" class="m-form m-form--fit m-form--label-align-right">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content" style="padding:20px">
                <div class="modal-header">
                    <h5 class="modal-title" >
                        员工列表
                    </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">
                            &times;
                        </span>
                    </button>
                </div>

                <div class="modal-body">
                    <table id="tableStaff"></table>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"> 取消 </button>
                    <button type="button" onclick="assignPersonSubmit();" class="btn btn-primary" >确认分配</button>
                </div>
            </div>
        </div>
        </form>
    </div>

    <div id="assignRoleDialog" class="crudDialog modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <form id="roleForm" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content" style="padding:20px">
                    <div class="modal-header">
                        <h5 class="modal-title" >
                            角色列表
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">
                                &times;
                            </span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <table id="tableRole"></table>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"> 取消 </button>
                        <button type="button" onclick="assignRoleSubmit();" class="btn btn-primary" >确认分配角色</button>
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
            $.validator.addMethod("checkSpecName", function (value, element) {
                return !isInSet(value, '维修部门', '维保部门', '报警部门');

            }, '请不要用这个名称， 系统保留名称.');

            //codes works on all bootstrap modal windows in application
            $('.modal').on('hidden.bs.modal', function(e)
            {
                jQuery("#add_Form").validate().resetForm();
                jQuery("#edit_Form").validate().resetForm();
            }) ;
            generateAddEditForm('template-org-addEditForm', 'add_,edit_', null, null, 'addOrgFormContainer,editOrgFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addOrgFormContainer").modal("show");
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });
        });

        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/organization/list',
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
                idField: 'organizationId',
                sortOrder: 'desc',
                sortName: 'organization_id',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'name', title: '部门名称'},
                    {field: 'description', title: '部门描述'},
                    {field: 'action', width: 150, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });

            setSearchPlaceholder('部门名称');
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            var arrBtns=[];
            if(!isInSet(row['name'], '维修部门', '维保部门', '报警部门')) {
                arrBtns.push('<shiro:hasPermission name="upms:organization:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>');
                arrBtns.push('<shiro:hasPermission name="upms:organization:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>');
            }
            arrBtns.push('<shiro:hasPermission name="upms:organization:update"><a id="assignPerson" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="分配人员">	<i class="la la-user-plus"></i>	</a></shiro:hasPermission>');
            arrBtns.push('<shiro:hasPermission name="upms:organization:update"><a id="assignRole" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="分配角色">	<i class="la la-chevron-circle-right"></i>	</a></shiro:hasPermission>');
            return arrBtns.join('');
        }

        var FormWidgets = function () {
            var createForm = function (formid) {
                $("#"+formid+"_Form").validate({
                    // define validation rules
                    rules: {
                        name: {
                            required: true,
                            checkSpecName: true,
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
            var targetUrl='${basePath}/manage/organization/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/organization/update/'+id;
                formId='edit_Form';
            }
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建部门成功");
                        $('#addOrgFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑部门成功");
                        $('#editOrgFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            $("#editOrgFormContainer").modal("show");

            ajaxGet('${basePath}/manage/organization/update/' + row["organizationId"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.org.organizationId);
                    $("#edit_name").val(data.org.name);
                    $("#edit_description").val(data.org.description);
                }
            });
        }

        window.actionEvents = {
            'click #update': function (e, value, row, index) {
                updateAction(row);
            },
            'click #assignPerson': function (e, value, row, index) {
                jQuery("#personForm").validate().resetForm();
                selectOrgId = row["organizationId"];
                assignPersonAction(selectOrgId);
            },
            'click #assignRole': function (e, value, row, index) {
                jQuery("#roleForm").validate().resetForm();
                selectOrgId = row["organizationId"];
                assignRoleAction(selectOrgId);
            },
            'click #delete': function (e, value, row, index) {
                var rows = new Array();
                rows.push(row);
                deleteActionImpl(rows);
            }
        };

        function deleteAction(){
            var rows = $table.bootstrapTable('getSelections');
            if(isSpecOrgInRow(rows)){
                swWarn("系统数据不能删除.");
            }else {
                deleteActionImpl(rows);
            }
        }

        function deleteActionImpl(rows) {
            if (rows.length == 0) {
                swWarn("请至少选择一条记录");
            }else {
                deleteRows(rows,'organizationId','${basePath}/manage/organization/delete/', "请确认要删除选中的部门吗？", "删除部门成功");
            }//end else
        }

        function isSpecOrgInRow(rows){
            for (var i in rows) {
                var name= rows[i]['name'];
                if(isInSet(name, '维修部门', '维保部门', '报警部门'))
                    return true;
            }
            return false
        }
    </script>


    <script>
        var tableStaff = $('#tableStaff');
        var selectOrgId, shownTableStaff;
        var staffOpt = {
            url: '${basePath}/manage/organization/assign/listStaff',
            queryParams:function(p){
                return {    orgId : selectOrgId,
                    limit : p.limit,
                    offset: p.offset,
                    search: p.search,
                    sort:   p.sort,
                    order:  p.order
                }
            },
            striped: true,
            // search: true,
            // searchAlign: 'left',
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
            idField: 'userId',
            maintainSelected: true,
            columns: [
                {field: 'ck', checkbox: true,  formatter : checkFormatter},
                {field: 'realname', title: '姓名', sortable: true, align: 'center'},
                {field: 'phone', title: '电话号码'},
                {field: 'email', title: 'Email地址', align: 'center'}
            ]
        };

        function assignPersonAction(orgId) {
            $("#assignPersonDialog").modal("show");
            if(shownTableStaff){
                tableStaff.bootstrapTable( 'refresh', staffOpt);
            }else{
                shownTableStaff = true;
                tableStaff.bootstrapTable(staffOpt);
            }
        }

        function checkFormatter(value, row, index) {
            if (row.checked == true)
                return {
                    disabled : false,//设置是否可用
                    checked : true//设置选中
                };
            return value;
        }

        function assignPersonSubmit() {
            var rows = tableStaff.bootstrapTable('getSelections');
            if(!selectOrgId)
                swWarn('出错了, 请刷新重试！');
            else if (rows.length == 0) {
                swWarn('请至少选择一条记录！');
            }else {
                var ids = new Array();
                for (var i in rows) {
                    if (rows[i].userId == null){
                        alert("请选择一记录！");
                        return false;
                    }
                }
                for (var i in rows) {
                    ids.push(rows[i].userId);
                }

                ajaxPostData('${basePath}/manage/organization/assign/'+selectOrgId, {orgId: selectOrgId, eIds: ids.join("::")}, function(result) {
                    if (result.code != 1) {
                        if (result.data instanceof Array) {
                            $.each(result.data, function(index, value) {
                                swError(value.errorMsg);
                            });
                        } else {
                            swError(result.data.errorMsg);
                        }
                    } else {
                        $('#assignPersonDialog').modal('toggle');
                        $table.bootstrapTable('refresh');
                    }
                });
            }//end else
        }

    </script>

    <script>
        var tableRole = $('#tableRole');
        var showTableRole;
        var roleOpt={
            url: '${basePath}/manage/organization/assignRole/listRole',
            queryParams:function(p){
                return {    orgId : selectOrgId,
                    limit : p.limit,
                    offset: p.offset,
                    search: p.search,
                    sort:   p.sort,
                    order:  p.order
                }
            },
            striped: true,
            // search: true,
            // searchAlign: 'left',
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
            idField: 'roleId',
            maintainSelected: true,
            columns: [
                {field: 'ck', checkbox: true,  formatter : checkFormatter},
                {field: 'name', title: '角色名', sortable: true, align: 'center'},
                {field: 'title', title: '角色标题'},
                {field: 'description', title: '角色描述', align: 'center'}
            ]
        };
        function assignRoleAction(orgId) {
            $("#assignRoleDialog").modal("show");
            if(showTableRole){
                tableRole.bootstrapTable( 'refresh', roleOpt);
            }else{
                showTableRole = true;
                tableRole.bootstrapTable(roleOpt);
            }
        }

        function assignRoleSubmit() {
            var rows = tableRole.bootstrapTable('getSelections');
            if(!selectOrgId)
                swWarn('出错了, 请刷新重试！');
            else if (rows.length == 0) {
                swWarn('请至少选择一条记录！');
            }else {
                var ids = new Array();
                for (var i in rows) {
                    if (rows[i].roleId == null){
                        alert("请选择一记录！");
                        return false;
                    }
                }
                for (var i in rows) {
                    ids.push(rows[i].roleId);
                }

                ajaxPostData('${basePath}/manage/organization/assignRole/'+selectOrgId, {orgId: selectOrgId, eIds: ids.join("::")}, function(result) {
                    if (result.code != 1) {
                        if (result.data instanceof Array) {
                            $.each(result.data, function(index, value) {
                                swError(value.errorMsg);
                            });
                        } else {
                            swError(result.data.errorMsg);
                        }
                    } else {
                        $('#assignRoleDialog').modal('toggle');
                        $table.bootstrapTable('refresh');
                    }
                });
            }//end else
        }

    </script>
</pageResources>


</body>
</html>