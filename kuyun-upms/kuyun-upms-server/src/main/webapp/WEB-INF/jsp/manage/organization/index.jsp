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
                <%--<div class="col-xl-4 order-1 order-xl-2 m--align-right">--%>
                <%--<button type="button" class="btn btn-info m-btn m-btn--custom m-btn--icon m-btn--air" data-toggle="modal" data-target="#create_modal">--%>
                <%--<span><i class="la la-plus"></i> <span>新建</span></span>--%>
                <%--</button>--%>


                <%--&lt;%&ndash;<a href="${basePath}/manage/organization/create" data-target="#createModal" data-toggle="modal" class="btn btn-info m-btn m-btn--custom m-btn--icon m-btn--air">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="la la-plus"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;新建&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>

                <%--<div class="m-separator m-separator--dashed d-xl-none"></div>--%>
                <%--</div>--%>
                <div class="col-xl-4 order-1 order-xl-2 m--align-right">

                    <div class="m-dropdown m-dropdown--inline m-dropdown--arrow m-dropdown--align-right m-dropdown--align-push" data-dropdown-toggle="hover" aria-expanded="true">
                        <a href="#" class="m-portlet__nav-link btn btn-lg btn-secondary  m-btn m-btn--outline-2x m-btn--air m-btn--icon m-btn--icon-only m-btn--pill  m-dropdown__toggle">
                            <i class="la la-plus m--hide"></i>
                            <i class="la la-ellipsis-h"></i>
                        </a>
                        <div class="m-dropdown__wrapper">
                            <span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>
                            <div class="m-dropdown__inner">
                                <div class="m-dropdown__body">
                                    <div class="m-dropdown__content">
                                        <ul class="m-nav">
                                            <li class="m-nav__item">
                                                <a href="" class="m-nav__link" data-toggle="modal" data-target="#create_modal">
                                                    <i class="m-nav__link-icon flaticon-plus"></i>
                                                    <span class="m-nav__link-text">
																	新建
																</span>

                                                </a>
                                            </li>
                                            <li class="m-nav__item">
                                                <a href="" class="m-nav__link">
                                                    <i class="m-nav__link-icon flaticon-chat-1"></i>
                                                    <span class="m-nav__link-text">
																	修改
																</span>
                                                </a>
                                            </li>
                                            <li class="m-nav__separator m-nav__separator--fit"></li>
                                            <li class="m-nav__item">
                                                <a href="#" class="btn btn-outline-danger m-btn m-btn--pill m-btn--wide btn-sm">
                                                    删除
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
                        <div class="form-group">
                            <label for="templateID_name" class="form-control-label">
                                名称:*
                            </label>
                            <input type="text" class="form-control" id="templateID_name" name="name">
                        </div>

                        <div class="form-group">
                            <label for="templateID_description" class="form-control-label">
                                描述:*
                            </label>
                            <textarea class="form-control" id="templateID_description" name="description" rows="6"></textarea>
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
            // codes works on all bootstrap modal windows in application
            // $('.modal').on('hidden.bs.modal', function(e)
            // {
            //     $(this).find('#createForm')[0].reset();
            // }) ;
            applyTemplate(jQuery, '#template-org-addEditForm', 'add_', null, null, jQuery('#addOrgFormContainer'));
            applyTemplate(jQuery, '#template-org-addEditForm', 'edit_', null, null, jQuery('#editOrgFormContainer'));

            $('#add_submit').click(function(){
                createSubmit();
            });
            $('#edit_submit').click(function(){
                createSubmit($('#edit_id').val());
            });

            $('#delete').click(function(e) {
                deleteRow("请确认要删除选中的 类别 吗？", function () {
                    swSuccess('Deleted!');
                });
            });
        });

        toastr.options = {
            "closeButton": false,
            "debug": false,
            "newestOnTop": false,
            "progressBar": false,
            "positionClass": "toast-top-right",
            "preventDuplicates": false,
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };


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
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'name', title: '部门名称'},
                    {field: 'description', title: '部门描述'},
                    {field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<a href="javascript:;" onclick="updateAction()" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="Edit details">	<i class="la la-edit"></i>	</a>',
                '<button type="button" id="delete" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="Delete">	<i class="la la-trash"></i>	</button>'
            ].join('');
        }

    //== Class definition

    var FormWidgets = function () {
        var createForm = function () {
            $("#createForm").validate({
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
                    //form[0].submit(); // submit the form
                    createSubmit();

                }
            });
        }

        return {
            // public functions
            init: function () {
                createForm();
            }
        };

    }();

    jQuery(document).ready(function () {
        FormWidgets.init();
    });
</script>
        <script>
            function createSubmit(id) {
                var targetUrl='${basePath}/manage/organization/create';
                var formId='#add_Form';
                if(id){
                    targetUrl='${basePath}/manage/organization/update/'+id;
                    formId='#edit_Form';
                }
                $.ajax({
                    type: 'post',
                    url: targetUrl,
                    data: $(formId).serialize(),
                    beforeSend: function() {
                        if ($('#name').val() == '') {
                            $('#name').focus();
                            return false;
                        }
                    },
                    success: function(result) {
                        if (result.code != 1) {
                            var errorMsgs = "";

                            if (result.data instanceof Array) {
                                $.each(result.data, function(index, value) {
                                    errorMsgs += value.errorMsg + "/r/n";
                                });
                            } else {
                                errorMsgs = result.data.errorMsg;
                            }

                            toastr.warning(errorMsgs);
                        } else {
                            if(formId=='#add_Form') {
                                toastr.success("新建部门成功");
                                $('#addOrgFormContainer').modal('toggle');
                            }else{
                                toastr.success("编辑部门成功");
                                $('#editOrgFormContainer').modal('toggle');
                            }
                            $table.bootstrapTable('refresh');
                        }
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error(textStatus);
                    }
                });
            }


            function updateAction() {

                var rows = $table.bootstrapTable('getSelections');
                if (rows.length != 1) {
                    swWarn("请选择一条记录");
                } else {
                    $("#editOrgFormContainer").modal("show");
                    get( '${basePath}/manage/organization/update/' + rows[0].organizationId, function (responseData) {
                        if (responseData) {
                            var data = responseData;
                            // 赋值
                            $("#edit_id").val(data.org.organizationId);
                            $("#edit_name").val(data.org.name);
                            $("#edit_description").val(data.org.description);
                        }
                    });
                }
            }


        </script>

    <script>

        //== Class definition
        var SweetAlert2Demo = function() {

            //== Demos
            var initDemos = function() {
                $('#delete').click(function(e) {
                    swal({
                        title: 'Are you sure?',
                        text: "请确认要删除选中的 类别 吗？",
                        type: 'warning',
                        showCancelButton: true,
                        confirmButtonText: '确认',
                        cancelButtonText: '取消'
                    }).then(function(result) {
                        if (result.value) {
                            swal(
                                'Deleted!',
                                'Your file has been deleted.',
                                'success'
                            )
                        }
                    });
                });
            };

            return {
                //== Init
                init: function() {
                    initDemos();
                },
            };
        }();

        //== Class Initialization
        jQuery(document).ready(function() {
            SweetAlert2Demo.init();
        });
    </script>



</pageResources>


</body>
</html>