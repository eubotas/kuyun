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

    <div class="m-portlet m-portlet--mobile">
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
            <div class="modal-dialog" role="document">
                <div class="modal-content" style="min-width: 600px">
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
                                <div class="col-sm-12">
                                    <label for="templateID_description">工单描述</label>
                                    <div class="form-group">
                                        <textarea id="templateID_description" class="form-control" name="description"
                                                  maxlength="200" rows="4"></textarea>

                                    </div>
                                </div>
                            </div>

                        <div class="form-group m-form__group row">
                                <div class="col-sm-2">
                                    <label >工单类型</label>
                                </div>
                            <div class="col-sm-4">
                                <select id="templateID_ticketTypeId" name="ticketTypeId" style="width: 100%">
                                </select>
                            </div>
                                <div class="col-sm-2">
                                    <label for="templateID_equipmentCategoryId">设备类型</label>
                                </div>
                            <div class="col-sm-4">
                                    <select id="templateID_equipmentCategoryId" name="equipmentCategoryId" style="width: 100%">
                                    </select>
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <div class="col-sm-2">
                                <label >设备名称</label>
                            </div>
                            <div class="col-sm-4">
                                <select id="templateID_equipmentId" name="equipmentId" style="width: 100%">
                                </select>
                            </div>
                            <div class="col-sm-2">
                                <label for="templateID_equipmentCategoryId">优先级</label>
                            </div>
                            <div class="col-sm-4">
                                <select id="templateID_priority"
                                        name="priority" style="width: 100%">
                                    <option value="一般" <c:if test="${ticket.priority== '一般'}">selected</c:if> >一般</option>
                                    <option value="紧急" <c:if test="${ticket.priority== '紧急'}">selected</c:if> >紧急</option>
                                    <option value="非常紧急" <c:if test="${ticket.priority== '非常紧急'}">selected</c:if>  >非常紧急</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                                <div class="col-sm-6">
                                    <div id="fine-uploader-gallery"></div>
                                    <input id="templateID_imagePath" type="hidden" class="form-control"
                                           name="imagePath" maxlength="500">
                                </div>
                                <div class="col-sm-6">
                                    <div id="fine-uploader-gallery2"></div>
                                    <input id="templateID_voicePath" type="hidden" class="form-control"
                                           name="voicePath" maxlength="500">
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
            </div>
        </form>
    </div>
    <!--end::Modal-->

    <div class="modal fade" id="detailContainer" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content" style="width: 600px; padding:10px;">
                    <div class="row">
                        <div class="col-sm-2">
                            <label for="description">故障描述 :</label>
                        </div>
                        <div class="col-sm-10">
                            <span id="description"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <label >工单类型: </label> <span id="ticketType"></span>
                        </div>
                        <div class="col-sm-6">
                            <label>优先级: </label> <span id="priority"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <label >执行人: </label> <span id="serviceman"></span>
                        </div>
                        <div class="col-sm-6">
                            <label >执行人电话: </label> <span id="servicePhone"></span>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <label >顾客联系人: </label> <span id="customerContacts"></span>
                        </div>
                        <div class="col-sm-6">
                            <label >顾客电话: </label> <span id="customerPhone"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <label for="status">工单状态: </label> <span id="status"></span>
                        </div>
                        <div class="col-sm-6">
                            <label for="createTime">创建时间: </label> <span id="createTime"></span>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12" id="images">
                        </div>
                        <div class="col-sm-12" id="voiceList">
                        </div>
                    </div>
                    <div class="row">
                        <hr />
                    </div>

                    <div id="records" ></div>

                    <div id="assessmentSection" style="display:none">
                        <div class="row">
                            <div class="col-sm-6">
                                <label for="assessmentLevel">评价星级</label> <span id="assessmentLevel"></span>
                            </div>
                            <div class="col-sm-6">
                                <label for="tagNames">评价标签</label> <span id="tagNames"></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <label >评价描述</label> <span id="assessmentDescription"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="modal-footer">
                            <div id="nextOperateBtn"> </div>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                取消
                            </button>
                        </div>
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
            generateAddEditForm('template-ticket-addEditForm', 'add_,edit_', null, null, 'addTicketFormContainer,editTicketFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#createButton').click(function(){
                $("#addTicketFormContainer").modal("show");
                ajaxGet('${basePath}/manage/ticket/create', function (responseData) {
                    if (responseData) {
                        var data = responseData;
                        addOptionToHtmlSelect(null, "add_ticketTypeId", data.ticketTypes);
                        addOptionToHtmlSelect(null, "add_equipmentCategoryId", data.equipmentCategorys);
                        addOptionToHtmlSelect(null, "add_equipmentId", data.equipments);
                    }
                });
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });

        });

        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
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
                    {field: 'description', title: '工单描述', sortable: true, align: 'center'},
                    {field: 'priority', title: '优先级'},
                    {field: 'ticketType.name', title: '工单类型'},
                    {field: 'serviceman', title: '执行人'},
                    {field: 'servicePhone', title: '执行人电话'},
                    {field: 'customerContacts', title: '顾客'},
                    {field: 'customerPhone', title: '顾客电话'},
                    {field: 'status', title: '当前状态'},
                    {field: 'action', width: 260, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:ticket:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:ticket:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:ticket:read"><a id="detail" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="详细">	<i class="la la-file-text-o"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:ticket:read"><a id="rejectList" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="拒绝工单列表">	<i class="la la-times-circle"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:ticket:read"><a id="assessment" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="去评价">	<i class="la la-thumbs-up"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:ticket:read"><a id="rejectTicket" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="拒绝工单">	<i class="la la-thumbs-o-down"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:ticket:read"><a id="processTicket" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="处理工单">	<i class="la la-sign-in"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:ticket:read"><a id="completeTicket" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="完成工单">	<i class="la la-ticket"></i>	</a></shiro:hasPermission>',

                '<shiro:hasPermission name="eam:ticketRecord:read"><a id="ticketRecordMgr" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="工单记录管理">	<i class="la la-th-list"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:ticketAppointedRecord:read"><a id="ticketAppointedRecordMgr" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="工单委派管理">	<i class="la la-step-forward"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:ticketAssessment:read"><a id="ticketAssessmentMgr" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="工单评价管理">	<i class="la la-thumbs-up"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:ticketAppointedRecord:read"><a id="ticketAppointedRecordMgr" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="委派工单">	<i class="la la-tasks"></i>	</a></shiro:hasPermission>'
            ].join('');
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
            var targetUrl='${basePath}/manage/ticket/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/ticket/update/'+id;
                formId='edit_Form';
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
            jQuery("#editTicketFormContainer").modal("show");
            ajaxGet('${basePath}/manage/ticket/update/' + row["ticketId"], function (responseData) {
                if (responseData) {
                    var data = responseData;
                    // 赋值
                    $("#edit_id").val(data.ticket.ticketId);
                    $("#edit_description").val(data.ticket.description);
                    addOptionToHtmlSelect(data.ticket.ticketTypeId, "edit_ticketTypeId", data.ticketTypes);
                    addOptionToHtmlSelect(data.ticket.equipmentCategoryId, "edit_equipmentCategoryId", data.equipmentCategorys);
                    addOptionToHtmlSelect(data.ticket.equipmentId, "edit_equipmentId", data.equipments);
                }
            });
        }

        window.actionEvents = {
            'click #update': function (e, value, row, index) {
                updateAction(row);

            },
            'click #detail': function (e, value, row, index) {
                toaction(row, 'detail');
            },
            'click #delete': function (e, value, row, index) {
                var rows = new Array();
                rows.push(row);
                deleteActionImpl(rows);
            },
            'click #rejectTicket': function (e, value, row, index) {
                toaction(row, 'rejectTicket');
            },
            'click #rejectList': function (e, value, row, index) {
                toaction(row, 'REJECT');
            },
            'click #processTicket': function (e, value, row, index) {
                toaction(row, 'processTicket');
            },
            'click #completeTicket': function (e, value, row, index) {
                toaction(row,'completeTicket');
            },
            'click #ticketRecordMgr': function (e, value, row, index) {
                toaction(row,'ticketRecordMgr');
            },
            'click #ticketAppointedRecordMgr': function (e, value, row, index) {
                toaction(row,'ticketAppointedRecordMgr');
            },
            'click #ticketAssessmentMgr': function (e, value, row, index) {
                toaction(row,'ticketAssessmentMgr');
            },
            'click #ticketAppointedRecordMgr': function (e, value, row, index) {
                toaction(row,'ticketAppointedRecordMgr');
            },
        };

        function toaction(row, type) {
            var ticketId= row.ticketId;
            var status= row.status;
            if('ticketRecordMgr'==type)
                window.location = '${basePath}/manage/ticket/' +ticketId  + '/record/index';
            else if('ticketAppointedRecordMgr'==type)
                window.location = '${basePath}/manage/ticket/' + ticketId + '/appoint/index';
            else if('ticketAssessmentMgr'==type){
                window.location = '${basePath}/manage/ticket/' + ticketId + '/assessment/index';
            }
            else if('ticketAppointedRecordMgr'==type){
                if(status == '待派工')
                    createChildWindow('appoint', '委派工单', '${basePath}/manage/ticket/' + ticketId + '/appoint/create');
                else
                    swWarn('未委派的订单才能委派');
            }else if('TOASSESSMENT'==type){
                if(status == '待评价')
                    createChildWindow('assessment', '评价', '${basePath}/manage/ticket/' + ticketId + '/assessment/assess');
                else
                    swWarn('完成状态的订单才能评价');
            }
            else if('processTicket'==type)
                createChildWindow('record', '处理工单', '${basePath}/manage/ticket/' +ticketId  + '/record/create');
            else if('rejectTicket'==type){
                if(status == '待维修')
                    createChildWindow('reject', '拒绝工单', '${basePath}/manage/ticket/' + ticketId + '/appoint/toreject');
                else
                    swWarn('已委派的订单才能拒绝');
            }
            else if('completeTicket'==type){
                if(status == '维修中')
                    directlyAction('完成工单',  '${basePath}/manage/ticket/complete/' + ticketId);
                else
                    swWarn('维修状态的订单才能完成工单');
            }
            else if('detail'==type){
                jQuery("#detailContainer").modal("show");
                ajaxGet('${basePath}/manage/ticket/detail/' + row["ticketId"], function (responseData) {
                    if (responseData) {
                        var data = responseData.ticket;
                        $("#description").text(data.description);
                        $("#ticketType").text(data.ticketType.name);
                        $("#priority").text(data.priority);
                        $("#serviceman").text(data.serviceman);
                        $("#servicePhone").text(data.servicePhone);
                        $("#customerContacts").text(data.customerContacts);
                        $("#customerPhone").text(data.customerPhone);
                        $("#status").text(data.status);
                        $("#createTime").text(timeFormatter(data.createTime));

                        if(data.assessmentLevel) {
                            $("#assessmentLevel").text(data.assessmentLevel);
                            $("#tagNames").text(data.tagNames);
                            $("#assessmentDescription").text(data.assessmentDescription);
                            $("assessmentSection").show();
                        }

                        var images;
                        $.each(responseData.imageList,function(n,value) {
                            images +="<image src="+value+" />";
                        });
                        $('#images').html(images);

                        var records;
                        $.each(responseData.records,function(n,value) {
                            records +='<div class="col-sm-12">';
                            records +=' <span>\'+value+\'</span>';
                            records +=' </div>';
                            records +=' <div class="col-sm-12">';
                            records +='    <span>\'+value+\'</span>';
                            records +='  </div>';
                            records +=' <div class="row"> <hr />  </div>';
                        });
                        $('#records').html(records);
                        $("#nextOperateBtn").html(responseData.nextOperateBtn);
                     }
                });
            }
        }

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



    <!-- Fine Uploader Gallery template
  ====================================================================== -->
    <script type="text/template" id="qq-template-gallery">
        <div class="qq-uploader-selector qq-uploader qq-gallery" qq-drop-area-text="Drop files here">
            <div class="qq-total-progress-bar-container-selector qq-total-progress-bar-container">
                <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-total-progress-bar-selector qq-progress-bar qq-total-progress-bar"></div>
            </div>
            <div class="qq-upload-drop-area-selector qq-upload-drop-area" qq-hide-dropzone>
                <span class="qq-upload-drop-area-text-selector"></span>
            </div>
            <div class="qq-upload-button-selector qq-upload-button">
                <div>上传文件</div>
            </div>
            <span class="qq-drop-processing-selector qq-drop-processing">
                <span>Processing dropped files...</span>
                <span class="qq-drop-processing-spinner-selector qq-drop-processing-spinner"></span>
            </span>
            <ul class="qq-upload-list-selector qq-upload-list" role="region" aria-live="polite" aria-relevant="additions removals">
                <li>
                    <span role="status" class="qq-upload-status-text-selector qq-upload-status-text"></span>
                    <div class="qq-progress-bar-container-selector qq-progress-bar-container">
                        <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-progress-bar-selector qq-progress-bar"></div>
                    </div>
                    <span class="qq-upload-spinner-selector qq-upload-spinner"></span>
                    <div class="qq-thumbnail-wrapper">
                        <img class="qq-thumbnail-selector" qq-max-size="120" qq-server-scale>
                    </div>
                    <button type="button" class="qq-upload-cancel-selector qq-upload-cancel">X</button>
                    <button type="button" class="qq-upload-retry-selector qq-upload-retry">
                        <span class="qq-btn qq-retry-icon" aria-label="Retry"></span>
                        Retry
                    </button>

                    <div class="qq-file-info">
                        <div class="qq-file-name">
                            <span class="qq-upload-file-selector qq-upload-file"></span>
                            <span class="qq-edit-filename-icon-selector qq-edit-filename-icon" aria-label="Edit filename"></span>
                        </div>
                        <input class="qq-edit-filename-selector qq-edit-filename" tabindex="0" type="text">
                        <span class="qq-upload-size-selector qq-upload-size"></span>
                        <button type="button" class="qq-btn qq-upload-delete-selector qq-upload-delete">
                            <span class="qq-btn qq-delete-icon" aria-label="Delete"></span>
                        </button>
                        <button type="button" class="qq-btn qq-upload-pause-selector qq-upload-pause">
                            <span class="qq-btn qq-pause-icon" aria-label="Pause"></span>
                        </button>
                        <button type="button" class="qq-btn qq-upload-continue-selector qq-upload-continue">
                            <span class="qq-btn qq-continue-icon" aria-label="Continue"></span>
                        </button>
                    </div>
                </li>
            </ul>

            <dialog class="qq-alert-dialog-selector">
                <div class="qq-dialog-message-selector"></div>
                <div class="qq-dialog-buttons">
                    <button type="button" class="qq-cancel-button-selector">Close</button>
                </div>
            </dialog>

            <dialog class="qq-confirm-dialog-selector">
                <div class="qq-dialog-message-selector"></div>
                <div class="qq-dialog-buttons">
                    <button type="button" class="qq-cancel-button-selector">No</button>
                    <button type="button" class="qq-ok-button-selector">Yes</button>
                </div>
            </dialog>

            <dialog class="qq-prompt-dialog-selector">
                <div class="qq-dialog-message-selector"></div>
                <input type="text">
                <div class="qq-dialog-buttons">
                    <button type="button" class="qq-cancel-button-selector">Cancel</button>
                    <button type="button" class="qq-ok-button-selector">Ok</button>
                </div>
            </dialog>
        </div>
    </script>
</pageResources>


</body>
</html>