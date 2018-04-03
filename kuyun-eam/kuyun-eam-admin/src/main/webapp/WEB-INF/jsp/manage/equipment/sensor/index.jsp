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
												Sensor列表
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


                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                </div>
            </div>

            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>

</content>


<pageResources>


    <script>
        $(document).ready(function()
        {

        });

        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/equipment/sensor/list/${equipment.equipmentId}',
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
                idField: 'equipment_model_property_id',
                columns: [
                    {field: 'name', title: '名称', sortable: true, align: 'center'},
                    {field: 'writeValue', title: '写入值', editable: {
                            type: 'text',
                            validate: function (value) {
                                if ($.trim(value) == '') {
                                    return '写入值不能为空!';
                                }
                            }
                        }, align: 'center'}
                ],
                onEditableSave: function(field, row, oldValue, $el) {
                    // field:修改的欄位
                    // row:修改後的資料(JSON Object)
                    // oldValue:修改前的值
                    // -------------------------------------------------
                    // 可用ajax方法去更新資料
                    $.ajax({
                        type: "post",
                        url: '${basePath}/manage/equipment/sensor/write',
                        data: JSON.stringify(row),
                        contentType:"application/json",
                        dataType: 'JSON',
                        success: function(data, status) {
                            if (status == "success") {
                                alert(data.data);
                            }
                        },
                        error: function() {
                            alert('写入数据失败');
                        },
                        complete: function() {

                        }

                    });
                    // -------------------------------------------------
                }
            });
        });


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
                // public functions
                init: function (formid) {
                    createForm(formid);
                }
            };
        }();

        function submitForm(id) {
            var targetUrl='${basePath}/manage/equipment/inventory/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/equipment/inventory/update/'+id;
                formId='edit_Form';
            }
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建Sensor成功");
                        $('#addEquipmentCategoryFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑Sensor成功");
                        $('#editEquipmentCategoryFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            jQuery("#editEquipmentCategoryFormContainer").modal("show");
            ajaxGet('${basePath}/manage/equipment/inventory/update/' + row["equipment_model_property_id"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.equipmentCategory.equipment_model_property_id);
                    $("#edit_name").val(data.equipmentCategory.name);
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
                deleteRows(rows,'equipment_model_property_id','${basePath}/manage/equipment/inventory/delete/', "请确认要删除选中的Sensor吗？", "删除Sensor成功");
            }//end else
        }

    </script>



</pageResources>


</body>
</html>