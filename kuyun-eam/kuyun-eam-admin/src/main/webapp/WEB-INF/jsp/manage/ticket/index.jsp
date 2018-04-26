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
                                工单列表
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

    <div class="m-content">
        <div class="row">
            <div class="col-xl-2 col-lg-4">
                <div class="m-portlet m-portlet--full-height  ">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-caption">
                            <div class="m-portlet__head-title">
                                <h1 class="m-portlet__head-text">
                                    工单状态
                                </h1>
                            </div>
                        </div>
                    </div>

                    <div id="ticketList" class="m-portlet__body">
                        <ul class="m-nav m-nav--hover-bg m-portlet-fit--sides" id="models">
                            <li id="myOpen" class="m-nav__item"><a href="javascript:;" onclick="toAction('myOpen');" class="m-nav__link"> <span class="m-nav__link-text">未处理</span></a></li>
                            <li id="myResolved" class="m-nav__item"><a href="javascript:;" onclick="toAction('myResolved');" class="m-nav__link"> <span class="m-nav__link-text">已处理</span></a></li>
                            <li id="myAll" class="m-nav__item"><a href="javascript:;" onclick="toAction('myAll');" class="m-nav__link"> <span class="m-nav__link-text">全部工单</span></a></li>
                            <li id="init" class="m-nav__item"><a href="javascript:;" onclick="toAction('init');" class="m-nav__link"> <span class="m-nav__link-text">待派工</span></a></li>
                            <li id="resolved" class="m-nav__item"><a href="javascript:;" onclick="toAction('resolved');" class="m-nav__link"> <span class="m-nav__link-text">已完成</span></a></li>
                            <li id="all" class="m-nav__item"><a href="javascript:;" onclick="toAction('all');" class="m-nav__link"> <span class="m-nav__link-text">全部工单</span></a></li>

                        </ul>
                        <div class="m-portlet__body-separator"></div>

                    </div>
                </div>
            </div>
            <div class="col-xl-10 col-lg-8">
                <div class="m-portlet m-portlet--full-height">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-caption">
                            <div class="m-portlet__head-title">
												<span class="m-portlet__head-icon">
													<i class="flaticon-multimedia"></i>
												</span>
                                <h3 class="m-portlet__head-text">
                                    ${categoryType}工单列表
                                </h3>
                            </div>
                        </div>
                    </div>


                    <div class="m-portlet__body">
                        <div id="toolbar">
                            <div>
                                <shiro:hasPermission name="eam:ticket:create"><a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                                    <i class="la la-plus"></i>
                                </a></shiro:hasPermission>

                                <shiro:hasPermission name="eam:ticket:delete"><a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                                    <i class="la la-remove"></i>
                                </a></shiro:hasPermission>

                                <div class="m-separator m-separator--dashed d-xl-none"></div>
                            </div>
                        </div>

                        <table id="table" data-toolbar="#toolbar"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--begin::Modal-->
    <div id="addTicketFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editTicketFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-ticket-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_工单
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">工单描述:</label>
                            <div class="col-lg-9">
                                <textarea id="templateID_description" class="form-control" name="description" rows="5"></textarea>
                            </div>
                        </div>


                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">上传附件:</label>
                            <div id="templateID_fine-uploader-gallery" class="col-sm-9"></div>
                            <input id="templateID_imagePath" type="hidden" class="form-control" name="imagePath">
                        </div>

                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

                        <div class="form-group m-form__group row">
                            <label class="col-2 col-form-label">
                                工单优先级:
                            </label>
                            <div class="col-8" id="templateID_ticketPriorityDiv">
                            </div>
                        </div>
                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

                        <div class="form-group m-form__group row">
                            <label class="col-2 col-form-label">
                                工单类型:
                            </label>
                            <div class="col-8" id="templateID_ticketTypeDiv">
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">设备名称:</label>
                            <div class="col-sm-4">
                                <select id="templateID_equipmentId" name="equipmentId" style="width: 100%"></select>
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

</content>

<pageResources>
    <jsp:include page="/resources/metronic-admin/file_upload.jsp" flush="true"/>

    <script>
        var addGalleryUploader;
        var editGalleryUploader;

        $(document).ready(function() {
            selectedItemColor("ticketList", "${category}");

            //codes works on all bootstrap modal windows in application
            $('.modal').on('hidden.bs.modal', function(e)
            {
                jQuery("#add_Form").validate().resetForm();
                jQuery("#edit_Form").validate().resetForm();
            }) ;
            generateAddEditForm('template-ticket-addEditForm', 'add_,edit_', null, null, 'addTicketFormContainer,editTicketFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#add_equipmentId, #edit_equipmentId').select2();

            addGalleryUploader = new qq.FineUploader($.extend(uploadOpt, {element : document.getElementById("add_fine-uploader-gallery")}));
            editGalleryUploader = new qq.FineUploader($.extend(uploadOpt, {element : document.getElementById("edit_fine-uploader-gallery")}));


            $('#createButton').click(function(){
                $("#addTicketFormContainer").modal("show");
                ajaxGet('${basePath}/manage/ticket/create', function (responseData) {
                    if (responseData) {
                        var data = responseData.data;
                        addOptionToHtmlSelect(null, "add_equipmentId", data.equipments);
                        buildTicketType('add', data.ticketTypes);
                        buildTicketPriority('add', data.ticketPriorityList);
                    }
                });
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });
        });

        function buildTicketType(templateId, ticketTypes, ticketTypeId) {
            $("#"+templateId+"_ticketTypeDiv").empty();
            var html = '<div class="m-radio-inline">';
            $.each(ticketTypes,
                function(i, ticketType) {
                    html += '<label class="m-radio">';

                    var checked = "";



                    if ('edit' === templateId && ticketType.VALUEFIELD === ticketTypeId.toString()){
                        checked  = "checked";
                    }else if ('add' === templateId && i == 0){
                        checked  = "checked";
                    }

                    html += '<input type="radio" id="'+templateId+'_ticketTypeId_'+ ticketType.VALUEFIELD +'" name="ticketTypeId" value='+ ticketType.VALUEFIELD + ' ' + checked+'>';
                    html += ticketType.DESCFIELD;
                    html += '<span></span></label>';
                }
            );

            html += '</div>';
            $("#"+templateId+"_ticketTypeDiv").append(html);

        }

        function buildTicketPriority(templateId, ticketPriorityList, priority) {
            $("#"+templateId+"_ticketPriorityDiv").empty();
            var html = '<div class="m-radio-inline">';
            $.each(ticketPriorityList,
                function(i, ticketPriority) {
                    html += '<label class="m-radio">';

                    var checked = "";

                    if ('edit' === templateId && ticketPriority.DESCFIELD === priority){
                        checked  = "checked";
                    }else if ('add' === templateId && i == 0){
                        checked  = "checked";
                    }

                    html += '<input type="radio" id="'+templateId+'_priority_'+ ticketPriority.DESCFIELD +'" name="priority" value='+ ticketPriority.DESCFIELD +' '+checked+'>';

                    html += ticketPriority.DESCFIELD;
                    html += '<span></span></label>';
                }
            );

            html += '</div>';
            $("#"+templateId+"_ticketPriorityDiv").append(html);
        }

        function toAction(type){
            window.location = '${basePath}/manage/ticket/index?category='+ type;
        }

        var $table = $('#table');
        $(function() {
            $table.bootstrapTable({
                url: '${basePath}/manage/ticket/list?category=${category}',
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
                idField: 'ticketId',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'ticketNumber', title: '工单编号',  align: 'center', searchable: true},
//                    {field: 'ticketType.name', title: '工单类型',  align: 'center', searchable: true},
//                    {field: 'priority', title: '优先级',  align: 'center', searchable: true},
//                    {field: 'serviceman', title: '维修人',  align: 'center', searchable: true},
//                    {field: 'servicePhone', title: '维修人电话',  align: 'center', searchable: true},
//                    {field: 'customerContacts', title: '提报人',  align: 'center', searchable: true},
//                    {field: 'customerPhone', title: '提报人电话',  align: 'center', searchable: true},
                    {field: 'status', title: '当前状态',  align: 'center', searchable: true, formatter: 'formatStatus'},
                    {field: 'description', title: '工单描述' , width: 350, searchable: true},
                    {field: 'action', width: 120, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });

        function actionFormatter(value, row, index) {
            return [
                '<a id="detail" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="详细">	<i class="la flaticon-file-1"></i>	</a>',
                '<shiro:hasPermission name="eam:ticket:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:ticket:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>'
            ].join('');
        }

        var FormWidgets = function () {
            var createForm = function (formid) {
                $("#"+formid+"_Form").validate({
                    // define validation rules
                    rules: {
                        description: {
                            required: true,
                            maxlength: 200
                        },
                        equipmentId: {
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
            var targetUrl='${basePath}/manage/ticket/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/ticket/update/'+id;
                formId='edit_Form';
                $('#edit_imagePath').val(getUploadFileName(editGalleryUploader));
            }else{
                $('#add_impagePath').val(getUploadFileName(addGalleryUploader));
            }
            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建工单成功");
                        $('#addTicketFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑工单成功");
                        $('#editTicketFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            $("#editTicketFormContainer").modal("show");

            ajaxGet('${basePath}/manage/ticket/update/' + row["ticketId"], function (responseData) {
                if (responseData) {
                    var data = responseData.data;
                    // 赋值
                    $("#edit_id").val(data.ticket.ticketId);
                    $("#edit_description").val(data.ticket.description);
                    addOptionToHtmlSelect(data.ticket.equipmentId, "edit_equipmentId", data.equipments );
                    buildTicketType('edit', data.ticketTypes, data.ticket.ticketTypeId);
                    buildTicketPriority('edit', data.ticketPriorityList, data.ticket.priority);

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
            },
            'click #detail': function (e, value, row, index) {
                <%--$("#detailDialog").modal("show");--%>
                var ticketId = row["ticketId"];
                <%--ajaxGet('${basePath}/manage/ticket/detail/' +ticketId , function (responseData) {--%>
                    <%--if (responseData) {--%>
                        <%--var data = responseData;--%>
                        <%--//$("#equipmentCategoryId").text(data.plan.equipmentCategoryName);--%>
                        <%--$("#equipmentId").text(data.plan.equipmentName);--%>
                        <%--$("#orgId").text(data.plan.orgName);--%>
                        <%--$("#maintainFrequencyQuantity").text(data.plan.maintainFrequencyQuantity +' '+ getUnitName(data.plan.maintainFrequencyUnit));--%>

                        <%--$("#nextMaintainDate").text(data.plan.strNextMaintainDate);--%>
                        <%--$("#workContent").text(data.plan.workContent);--%>
                        <%--$("#remindTime").text(data.plan.remindTime);--%>
                        <%--loadTicketTab(planId);--%>
                    <%--}--%>
                <%--});--%>

                window.location = '${basePath}/manage/ticket/detail/' +ticketId;
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
                deleteRows(rows,'ticketId','${basePath}/manage/ticket/delete/', "请确认要删除选中的工单吗？", "删除工单成功");
            }//end else
        }
    </script>
</pageResources>
</body>
</html>