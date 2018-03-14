<%@ page contentType="text/html; charset=utf-8" %>
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
                <div class="col-xl-4 order-1 order-xl-2 m--align-right">
                    <button type="button" class="btn btn-info m-btn m-btn--custom m-btn--icon m-btn--air" data-toggle="modal" data-target="#addOrgFormContainer">
                        <span><i class="la la-plus"></i> <span>新建</span></span>
                    </button>

                    <%--<a href="${basePath}/manage/organization/create" data-target="#createModal" data-toggle="modal" class="btn btn-info m-btn m-btn--custom m-btn--icon m-btn--air">--%>
                            <%--<span>--%>
                                <%--<i class="la la-plus"></i>--%>
                                <%--<span>--%>
                                    <%--新建--%>
                                <%--</span>--%>
                            <%--</span>--%>
                    <%--</a>--%>

                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                </div>
            </div>
            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>

    <!--begin::Modal-->
    <div id="addOrgFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <h5 class="modal-title">新建部门 </h5>
    </div>

    <div id="editOrgFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <h5 class="modal-title">编辑部门 </h5>
    </div>

    <div class="modal fade" id="template-org-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            新建部门
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                            <div class="form-group">
                                <label for="name" class="form-control-label">
                                    名称:*
                                </label>
                                <input type="text" class="form-control" id="templateID_name" name="name">
                            </div>

                            <div class="form-group">
                                <label for="description" class="form-control-label">
                                    描述:*
                                </label>
                                <textarea class="form-control" id="templateID_description" name="description" rows="6"></textarea>
                            </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="submit" class="btn btn-primary" id="m_blockui_4_1">
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


    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.min.js"></script>


    <script>

        $(document).ready(function()
        {
            // codes works on all bootstrap modal windows in application
            // $('.modal').on('hidden.bs.modal', function(e)
            // {
            //     $(this).find('#addForm')[0].reset();
            // }) ;
            applyTemplate(jQuery, '#template-org-addEditForm', 'add_', null, null, jQuery('#addOrgFormContainer'));
            applyTemplate(jQuery, '#template-org-addEditForm', 'edit_', null, null, jQuery('#editOrgFormContainer'));
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
                idField: 'organizationId',
                maintainSelected: true,
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
                '<a class="update" href="javascript:;" onclick="updateAction()" data-toggle="tooltip" title="Edit" data-toggle="modal" data-target="#editOrgFormContainer">Edit<i class="glyphicon glyphicon-edit"></i></a>　',
                '<a class="delete" href="javascript:;" onclick="deleteAction()" data-toggle="tooltip" title="Remove">Delete<i class="glyphicon glyphicon-remove"></i></a>'
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
            function createSubmit() {
                $.ajax({
                    type: 'post',
                    url: '${basePath}/manage/organization/create',
                    data: $('#createForm').serialize(),
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
                            toastr.warning("bob");
                            $('#OrgAddEdit').modal('toggle');
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

                } else {
                    $("#editOrgFormContainer").modal("show");
                    <%--$.ajax({--%>
                            <%--url: '${basePath}/manage/organization/update/' + rows[0].organizationId,--%>
                            <%--type: 'post',--%>
                            <%--beforeSend: function () {--%>
                                <%--// $("#tip").html("<span style='color:blue'>正在处理...</span>");--%>
                                <%--return true;--%>
                            <%--},--%>
                            <%--success: function (responseData) {--%>
                                <%--if (responseData) {--%>
                                    <%--// 解析json数据--%>
                                    <%--var dataObj = responseData;--%>
                                    <%--var data = eval("(" + dataObj + ")");--%>

                                    <%--// 赋值--%>
                                    <%--$("#name").val(data.name);--%>
                                <%--}--%>
                            <%--}--%>
                        <%--});--%>
                }
            }



            function loadHtmlTemplate(jQuery, prefix, el) {
                var html = jQuery(el).html();
                html = strReplaceAll(html, 'templateID_', prefix);
                return html;
            }

            function applyTemplate(jQuery, templateID, prefix, data, options, targetEl) {
                prefix = ifNull(prefix, '');
                jQuery.template((prefix+templateID), loadHtmlTemplate(jQuery, prefix, jQuery(templateID)));
                return jQuery.tmpl((prefix+templateID), data, options).appendTo(targetEl);
            }

            function ifNull(firstValue) {
                for(var i=0; i<arguments.length; i++) {
                    var value = arguments[i];
                    if((typeof value != 'undefined') && (value != null))
                        return value;
                }
                return firstValue;
            }

            function strReplaceAll(str, oldValue, newValue) {
                if(str == null)
                    return null;
                var idx = 0;
                while((idx = str.indexOf(oldValue, idx)) != -1) {
                    str = (str.substring(0, idx) + str.substring(idx).replace(oldValue, newValue));
                    idx += newValue.length;
                }
                return str;
            }
        </script>




</pageResources>


</body>
</html>