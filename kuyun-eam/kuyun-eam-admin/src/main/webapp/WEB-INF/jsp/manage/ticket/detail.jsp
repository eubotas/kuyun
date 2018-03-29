<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />

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
                        <a href="${basePath}/manage/ticket/index" class="m-nav__link">
											<span class="m-nav__link-text">
												工单列表
											</span>
                        </a>
                    </li>
                    <li class="m-nav__separator">
                        -
                    </li>
                    <li class="m-nav__item">
                        <a href="" class="m-nav__link">
											<span class="m-nav__link-text">
												工单详情
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
                                    工单操作
                                </h1>
                            </div>
                        </div>
                    </div>

                    <div class="m-portlet__body">
                        <ul class="m-nav m-nav--hover-bg m-portlet-fit--sides" id="models">
                            <c:choose>
                            <c:when test="${ticket.status=='待派工'}">
                            <li class="m-nav__item"><shiro:hasPermission name="eam:ticketAppointedRecord:update"><a href="javascript:;" onclick="toaction('TOAPPOINT');" class="m-nav__link"> <span class="m-nav__link-text">委派工单</span></a></shiro:hasPermission></li>
                            </c:when>
                            <c:when test="${ticket.status=='待评价'}">
                                <li class="m-nav__item"><shiro:hasPermission name="eam:ticketAssessment:create"><a href="javascript:;" onclick="toaction('TOASSESSMENT');" class="m-nav__link"> <span class="m-nav__link-text">评价</span></a></shiro:hasPermission></li>
                            </c:when>
                            <c:when test="${ticket.status=='待维修'}">
                                <li class="m-nav__item"><shiro:hasPermission name="eam:ticketAppointedRecord:create"><a href="javascript:;" onclick="toaction('REJECT');" class="m-nav__link"> <span class="m-nav__link-text">拒绝工单</span></a></shiro:hasPermission></li>
                            </c:when>
                            <c:when test="${ticket.status=='维修中'}">
                                <li class="m-nav__item"><shiro:hasPermission name="eam:ticket:update"><a href="javascript:;" onclick="toaction('COMPLETE');" class="m-nav__link"> <span class="m-nav__link-text">完成工单</span></a></shiro:hasPermission></li>
                            </c:when>
                            </c:choose>
                            <li class="m-nav__item"><shiro:hasPermission name="eam:ticketRecord:create"><a href="javascript:;" onclick="toaction('TORECORD');" class="m-nav__link"> <span class="m-nav__link-text">处理工单</span></a></shiro:hasPermission></li>


                            <li></li>
                            <li class="m-nav__item"><shiro:hasPermission name="eam:ticketAppointedRecord:read"><a href="${basePath}/manage/ticket/${ticket.ticketId}/record/index" class="m-nav__link"> <span class="m-nav__link-text">工单记录管理</span></a></shiro:hasPermission></li>
                            <li class="m-nav__item"><shiro:hasPermission name="eam:ticketAppointedRecord:read"><a href="${basePath}/manage/ticket/${ticket.ticketId}/appoint/index" class="m-nav__link"> <span class="m-nav__link-text">工单委派管理</span></a></shiro:hasPermission></li>
                            <li class="m-nav__item"><shiro:hasPermission name="eam:ticketAssessment:read"><a href="${basePath}/manage/ticket/${ticket.ticketId}/assessment/index" class="m-nav__link"> <span class="m-nav__link-text">工单评价管理</span></a></shiro:hasPermission></li>
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
                                    工单详情
                                </h3>
                            </div>
                        </div>
                    </div>


                    <div class="m-portlet__body">
                        <form class="m-form m-form--fit m-form--label-align-right">
                                    <!-- the ticket content -->
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <label for="description">故障描述: </label> <span id="description">${ticket.description }</span>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <label >工单类型: </label> <span>${ticket.ticketType.name }</span>
                                        </div>
                                        <div class="col-sm-6">
                                            <label>优先级: </label> <span>${ticket.priority }</span>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <label >执行人: </label> <span>${ticket.serviceman}</span>
                                        </div>
                                        <div class="col-sm-6">
                                            <label >执行人电话: </label> <span>${ticket.servicePhone}</span>
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <label >顾客联系人: </label> <span>${ticket.customerContacts}</span>
                                        </div>
                                        <div class="col-sm-6">
                                            <label >顾客电话: </label> <span>${ticket.customerPhone}</span>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <label for="status">工单状态: </label> <span id="status">${ticket.status}</span>
                                        </div>
                                        <div class="col-sm-6">
                                            <label for="createTime">创建时间: </label> <span id="createTime">${ticket.createTime}</span>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-12">
                                            <!-- image list  -->
                                            <c:forEach var="image" items="${imageList}">
                                                <image src='${image}' />
                                            </c:forEach>
                                        </div>
                                        <div class="col-sm-12">
                                            <!-- voice list  -->
                                            <c:forEach var="voice" items="${voiceList}">
                                                <a href="${voice}">click here</a>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <hr />
                                    </div>

                                    <!-- records list create date desc -->
                                    <c:forEach var="record" items="${records}">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <label for="comments${record.id}"></label> <span
                                                    id="comments${record.id}">${record.comments }</span>
                                            </div>
                                            <div class="col-sm-12">
                                                <label for="createTime${record.id }"></label> <span
                                                    id="createTime${record.id }">${record.createTime }</span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <hr />
                                        </div>
                                    </c:forEach>

                                    <c:if test="${ticket.assessmentId != null}">
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <label for="assessmentLevel">评价星级: </label> <span id="assessmentLevel">${ticket.assessmentLevel}</span>
                                            </div>
                                            <div class="col-sm-6">
                                                <label for="tagNames">评价标签: </label> <span id="tagNames">${ticket.tagNames}</span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <label>评价描述: </label> <span >${ticket.assessmentDescription}</span>
                                            </div>
                                        </div>
                                    </c:if>


                                </div>
                            </div>
                        </form>
            </div>
        </div>
    </div>


    <div id="createAppointDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <form id="createAppointForm" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <div class="col-sm-12">
                                <label for="orderTakerId">工单执行人</label>
                                <div class="form-group">
                                    <select id="orderTakerId" name="orderTakerId">
                                    </select><a style="margin-left: 10px;" href="javascript:;" onclick="toaction('CREATEUSER');">创建新用户</a>

                                </div>
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <div class="col-sm-12">
                                <label for="rejectCommont">工单委派备注/拒绝原因</label>
                                <div class="form-group">
                                    <input id="rejectCommont" type="text" class="form-control" name="rejectCommont" maxlength="200">

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-primary" onclick="createAppointSubmit('createAppointForm');">
                            提交
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div id="createAssessmentDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <form id="createAssessmentForm" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <div class="col-sm-12">
                                <label for="ass_assessmentLevel">工单评价星级</label>
                                <div class="form-group">
                                    <select id="ass_assessmentLevel"
                                            name="assessmentLevel" style="width: 100%">
                                        <option value="1">1星</option>
                                        <option value="2">2星</option>
                                        <option value="3">3星</option>
                                        <option value="4">4星</option>
                                        <option value="5">5星</option>
                                    </select>

                                </div>
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <div class="col-sm-12">
                                <label for="ass_description">工单评价备注</label>
                                <div class="form-group">
                                    <input id="ass_description" type="text" class="form-control" name="description" maxlength="200">
                                </div>
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <div class="col-sm-12">
                                <label>工单评价标签</label>
                                <div class="form-group">
                                    <c:forEach var="ticketTag" items="${ticketTags}">
                                        <input type="checkbox" name="ticketTag" value="${ticketTag.id}" />${ticketTag.name}
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" id="ass_id">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-primary" onclick="createAssessmentSubmit('createAssessmentForm');">
                            提交
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div id="rejectTicketDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <form id="rejectTicketForm" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <input type="hidden" name="orderTakerId">
                        <div class="form-group m-form__group row">
                            <div class="col-sm-12">
                                <label for="rejectCommont">工单委派备注/拒绝原因</label>
                                <div class="form-group">
                                    <input id="rej_rejectCommont" type="text" class="form-control" name="rejectCommont" maxlength="200">

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-primary" onclick="rejectTicketSubmit('rejectTicketForm');">
                            提交
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div id="createRecordDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <form id="createRecordForm" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <input type="hidden" name="orderTakerId">
                        <div class="form-group m-form__group row">
                            <div class="col-sm-12">
                                <label for="comments">工单记录备注</label>
                                <div class="form-group">
                                    <input id="comments" type="text" class="form-control" name="comments" maxlength="200">

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-primary" onclick="createRecordSubmit('createRecordForm');">
                            提交
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div id="createUserDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <form id="createUserForm" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="username">帐号</label>
                            <input id="username" type="text" class="form-control" name="username" maxlength="20">
                        </div>
                        <div class="form-group">
                            <label for="password">密码</label>
                            <input id="password" type="text" class="form-control" name="password" maxlength="32">
                        </div>
                        <div class="form-group">
                            <label for="realname">姓名</label>
                            <input id="realname" type="text" class="form-control" name="realname" maxlength="20">
                        </div>
                        <div class="form-group">
                            <label for="avatar">头像</label>
                            <input id="avatar" type="text" class="form-control" name="avatar" maxlength="50">
                        </div>
                        <div class="form-group">
                            <label for="phone">电话</label>
                            <input id="phone" type="text" class="form-control" name="phone" maxlength="20">
                        </div>
                        <div class="form-group">
                            <label for="email">邮箱</label>
                            <input id="email" type="text" class="form-control" name="email" maxlength="50">
                        </div>

                        <div class="m-form__group form-group">
                            <div class="m-radio-inline">
                                <label class="m-radio">
                                    <input id="sex_1" type="radio" name="sex" value="1" checked>男
                                    <span></span>
                                </label>
                                <label class="m-radio">
                                    <input id="sex_0" type="radio" name="sex" value="0">女<span></span>
                                </label>
                                <label class="m-radio">
                                    <input id="locked_0" type="radio" name="locked" value="0" checked>正常<span></span>
                                </label>
                                <label class="m-radio">
                                    <input id="locked_1" type="radio" name="locked" value="1">锁定<span></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-primary" onclick="createUserSubmit('createUserForm');">
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
    $(function() {
        $('span[id^="createTime"]').each(function() {
            $(this).text(timeFormatter($(this).text()));
        });
    });

    //格式化时间
    function timeFormatter(value) {
        return new Date(value).toLocaleString();
    }

    function toaction(type) {
            var ticketId= ${ticket.ticketId};
           if('TOAPPOINT'==type){
                $("#createAppointDialog").modal("show");
                ajaxGet('${basePath}/manage/ticket/' + ticketId + '/appoint/create', function (responseData) {
                    if (responseData) {
                        var data = responseData;
                        addOptionToHtmlSelect(null, "orderTakerId", data.users);
                    }
                });
            }else if('TOASSESSMENT'==type){
               $("#createAssessmentDialog").modal("show");
               ajaxGet('${basePath}/manage/ticket/' + ticketId + '/assessment/assess', function (responseData) {
                   if (responseData) {
                       var data = responseData;
                       if(data.ticketAssessment != undefined) {
                           $("#ass_id").val(data.ticketAssessment.id);
                           $("#ass_assessmentLevel").val(data.ticketAssessment.assessmentLevel);
                           $("#ass_description").val(data.ticketAssessment.description);
                           var tags = data.ticketAssessment.ticketAssessmentTags;
                           if(tags != undefined) {
                               $("#createAssessmentDialog").find("input[name='ticketTag']").each(
                                   function () {
                                       var item = $(this).get(0);
                                       if ($.inArray(item.val(), tags)) {
                                           item.prop("checked", true);
                                       }
                                   });
                           }
                       }//update
                   }
               });
            }
            else if('TORECORD'==type){
               $("#createRecordDialog").modal("show");
           }else if('REJECT'==type){
               $("#rejectTicketDialog").modal("show");
               ajaxGet('${basePath}/manage/ticket/' + ticketId + '/appoint/toreject', function (responseData) {
                   if (responseData) {
                       var data = responseData;
                       $('#orderTakerId').val(data.orderTakerId);
                   }
               });
            }
            else if('COMPLETE'==type){
               ajaxGet('${basePath}/manage/ticket/complete/' + ticketId, function (responseData) {
                   if (responseData) {
                       var data = responseData;
                       toastr.success("工单成功完成");
                       location.reload();
                   }else
                       toastr.success("工单完成失败");
               });
            }
           else if('CREATEUSER'==type){
               $("#createUserDialog").modal("show");
           }
    }

    function createAppointSubmit(formId){
        var targetUrl= '${basePath}/manage/ticket/${ticket.ticketId}/appoint/create';
        ajaxPost(targetUrl, formId, function(result) {
            if (result.code != 1) {
                sendErrorInfo(result);
            } else {
                toastr.success("委派工单成功");
                $('#createAppointDialog').modal('toggle');
                location.reload();
            }
        });
    }

    function createAssessmentSubmit(formId){
        var targetUrl= '${basePath}/manage/ticket/${ticket.ticketId}/assessment/create';
        var id= $("#ass_id").val();
        if(id != '')
            targetUrl= '${basePath}/manage/ticket/${ticket.ticketId}/assessment/update/'+id;
        ajaxPost(targetUrl, formId, function(result) {
            if (result.code != 1) {
                sendErrorInfo(result);
            } else {
                toastr.success("评价工单成功");
                $('#createAssessmentDialog').modal('toggle');
                location.reload();
            }
        });
    }

    function rejectTicketSubmit(formId){
        var targetUrl= '${basePath}/manage/ticket/${ticket.ticketId}/appoint/reject';
        ajaxPost(targetUrl, formId, function(result) {
            if (result.code != 1) {
                sendErrorInfo(result);
            } else {
                toastr.success("工单拒绝成功");
                $('#rejectTicketDialog').modal('toggle');
                location.reload();
            }
        });
    }

    function createRecordSubmit(formId){
        var targetUrl= '${basePath}/manage/ticket/${ticket.ticketId}/record/create';
        ajaxPost(targetUrl, formId, function(result) {
            if (result.code != 1) {
                sendErrorInfo(result);
            } else {
                toastr.success("工单记录创建成功");
                $('#createRecordDialog').modal('toggle');
                location.reload();
            }
        });
    }

    function createUserSubmit(formId){
        var targetUrl= '${basePath}/manage/company/createUser';
        ajaxPost(targetUrl, formId, function(result) {
            if (result.code != 1) {
                sendErrorInfo(result);
            } else {
                toastr.success("用户创建成功");
                $('#createUserDialog').modal('toggle');
            }
        });
    }

</script>
</pageResources>
</body>
</html>