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
												配件列表
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
                    <shiro:hasPermission name="eam:part:create"><a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a></shiro:hasPermission>

                    <shiro:hasPermission name="eam:part:delete"><a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                        <i class="la la-remove"></i>
                    </a></shiro:hasPermission>

                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                </div>
            </div>

            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>

    <!--begin::Modal-->
    <div id="addPartFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editPartFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-part-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_配件
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label for="templateID_name">配件名称</label>
                            <input id="templateID_name" type="text" class="form-control" name="name" maxlength="50">
                        </div>

                        <div class="form-group m-form__group row">
                            <label for="templateID_categoryId">配件目录</label>
                            <select id="templateID_categoryId" name="categoryId" style="width: 100%">
                                <c:forEach var="category" items="${categoryList}">
                                    <option value="${category.categoryId}">${category.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group m-form__group row">
                            <label for="templateID_spec">规格</label>
                            <input id="templateID_spec" type="text" class="form-control" name="spec" maxlength="50">
                        </div>

                        <div class="form-group m-form__group row">
                            <label for="templateID_model">型号</label>
                            <input id="templateID_model" type="text" class="form-control" name="model" maxlength="50">
                        </div>

                        <div class="form-group m-form__group row">
                            <label for="templateID_unit">单位</label>
                            <input id="templateID_unit" type="text" class="form-control" name="unit" maxlength="20">
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="templateID_brand">品牌</label>
                            <input id="templateID_brand" type="text" class="form-control" name="brand" maxlength="20">
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
    <div id="intaskDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <form id="intaskForm" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label for="name">配件名称</label>
                            <input id="name" type="text" class="form-control" name="name" maxlength="50" readonly="readonly">
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="name">仓库</label>
                            <select id="warehouseId" name="warehouseId" style="width: 100%">
                                <c:forEach var="warehouse" items="${warehouseList}">
                                    <option value="${warehouse.warehouseId}">${warehouse.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="name">仓位</label>
                            <select id="locationId" name="locationId" style="width: 100%">
                                <c:forEach var="location" items="${locationList}">
                                    <option value="${location.locationId}">${location.number}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="quantity">数量</label>
                            <input id="quantity" type="text" class="form-control" name="quantity" maxlength="20">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" id="in_id" name="partId">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-primary" onclick="intaskSubmit('intaskForm');">
                            提交
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div id="outtaskDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <form id="outtaskForm" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label for="name">配件名称</label>
                            <input id="out_name" type="text" class="form-control" name="name" maxlength="50" readonly="readonly">
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="name">仓库</label>
                            <select id="out_warehouseId" name="warehouseId" style="width: 100%">
                                <c:forEach var="warehouse" items="${warehouseList}">
                                    <option value="${warehouse.warehouseId}">${warehouse.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="name">仓位</label>
                            <select id="out_locationId" name="locationId" style="width: 100%">
                                <c:forEach var="location" items="${locationList}">
                                    <option value="${location.locationId}">${location.number}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="out_quantity">数量</label>
                            <input id="out_quantity" type="text" class="form-control" name="quantity" maxlength="20">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" id="out_id" name="partId">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-primary" onclick="outtaskSubmit('outtaskForm');">
                            提交
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
            generateAddEditForm('template-part-addEditForm', 'add_,edit_', null, null, 'addPartFormContainer,editPartFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addPartFormContainer").modal("show");
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });

        });

        var selectPartId;
        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/part/list',
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
                idField: 'partId',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'partId', title: '配件ID', sortable: true, align: 'center'},
                    {field: 'name', title: '配件名称'},
                    {field: 'categoryName', title: '配件类别'},
                    {field: 'spec', title: '规格'},
                    {field: 'model', title: '型号'},
                    {field: 'unit', title: '单位'},
                    {field: 'brand', title: '品牌'},
                    {field: 'createTime', title: '创建时间', formatter: 'timeFormatter'},
                    {field: 'action', width: 100, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:part:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:part:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:inventory:create"><a id="intask" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="入库">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:inventory:create"><a id="outtask" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="出库">	<i class="la la-edit"></i>	</a></shiro:hasPermission>'
            ].join('');
        }

        var FormWidgets = function () {
            var createForm = function (formid) {
                $("#"+formid+"_Form").validate({
                    // define validation rules
                    rules: {
                        name: {
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
                init: function (formid) {
                    createForm(formid);
                }
            };
        }();

        function submitForm(id) {
            var targetUrl='${basePath}/manage/part/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/part/update/'+id;
                formId='edit_Form';
            }
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建配件成功");
                        $('#addPartFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑配件成功");
                        $('#editPartFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            jQuery("#editPartFormContainer").modal("show");
            ajaxGet('${basePath}/manage/part/update/' + row["partId"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.part.partId);
                    $("#edit_name").val(data.part.name);
                    $("#edit_categoryId").val(data.part.categoryId);
                    $("#edit_spec").val(data.part.spec);
                    $("#edit_model").val(data.part.model);
                    $("#edit_unit").val(data.part.unit);
                    $("#edit_brand").val(data.part.brand);
                }
            });
        }

        window.actionEvents = {
            'click #update': function (e, value, row, index) {
                updateAction(row);
            },
            'click #intask': function (e, value, row, index) {
                $('#intaskDialog').modal('show');
                getPart(row);
            },
            'click #outtask': function (e, value, row, index) {
                $('#outtaskDialog').modal('show');
                getPart(row);
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
                deleteRows(rows,'partId','${basePath}/manage/part/delete/', "请确认要删除选中的配件吗？", "删除配件成功");
            }//end else
        }

        function intaskSubmit(formId){
            var targetUrl= '${basePath}/manage/part/intask';
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    toastr.success("入库成功");
                    $('#intaskDialog').modal('toggle');
                    location.reload();
                }
            });
        }

        function outtaskSubmit(formId){
            var targetUrl= '${basePath}/manage/part/intask';
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    toastr.success("出库成功");
                    $('#outtaskDialog').modal('toggle');
                    location.reload();
                }
            });
        }

        function getPart(row){
            selectPartId = row["partId"];
            ajaxGet('${basePath}/manage/part/getPart/' + selectPartId, function (responseData) {
                if (responseData) {
                    var data = responseData;
                    $("#in_id").val(data.part.partId);
                    $("#out_id").val(data.part.partId);
                    $("#name").val(data.part.name);
                    $("#out_name").val(data.part.name);
                }
            });
        }
    </script>



</pageResources>


</body>
</html>