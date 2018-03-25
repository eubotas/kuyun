﻿﻿﻿<%@ page contentType="text/html; charset=utf-8" %>
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
												DTU列表
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
            <div><label for="protocolId">设备协议: <c:out value="${protocol.name}"/></label> </div>
            <div><label for="IP">IP: <c:out value="${protocol.ip}"/></label> </div>
            <div><label for="port">端口号: <c:out value="${protocol.port}"/></label></div>


            <div id="toolbar">
                <div>
                    <shiro:hasPermission name="eam:dtu:create"><a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a></shiro:hasPermission>

                    <shiro:hasPermission name="eam:dtu:delete"><a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                        <i class="la la-remove"></i>
                    </a></shiro:hasPermission>

                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                </div>
            </div>

            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>

    <!--begin::Modal-->
    <div id="addDtuFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editDtuFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-dtu-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_DTU
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
						<div class="form-group m-form__group row">
                            <label for="templateID_name">名称</label>
                            <input id="templateID_name" type="text" class="form-control" name="name" maxlength="20">
						</div>

						<div class="form-group m-form__group row">
                            <label for="templateID_heartData">心跳包</label>
                            <input id="templateID_heartData" type="text" class="form-control" name="heartData" maxlength="20">
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
            generateAddEditForm('template-dtu-addEditForm', 'add_,edit_', null, null, 'addDtuFormContainer,editDtuFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addDtuFormContainer").modal("show");
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });

        });

        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/dtu/list',
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
                idField: 'dtuId',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'dtuId', title: '注册包', sortable: true, align: 'center'},
                    {field: 'name', title: '名称'},
                    {field: 'heartData', title: '心跳包'},
                    {field: 'modbusRtuPeriod', title: '采集频率'},
                    {field: 'createTime', title: '创建时间', formatter: 'timeFormatter'},
                    {field: 'action', title: '接入设备', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:dtu:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:dtu:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>'
            ].join('');
        }

        function submitForm(id) {
            var targetUrl='${basePath}/manage/dtu/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/dtu/update/'+id;
                formId='edit_Form';
            }
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建DTU成功");
                        $('#addDtuFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑DTU成功");
                        $('#editDtuFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            jQuery("#editDtuFormContainer").modal("show");
            ajaxGet('${basePath}/manage/dtu/update/' + row["dtuId"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.dtu.dtuId);
                    $("#edit_name").val(data.dtu.name);
                    $("#edit_heartData").val(data.dtu.heartData);
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
                deleteRows(rows,'dtuId','${basePath}/manage/dtu/delete/', "请确认要删除选中的DTU吗？", "删除DTU成功");
            }//end else
        }

    </script>



</pageResources>


</body>
</html>