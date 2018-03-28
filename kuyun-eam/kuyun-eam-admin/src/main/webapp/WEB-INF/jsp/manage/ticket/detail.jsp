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
                        <a href="" class="m-nav__link">
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
            <div class="col-xl-3 col-lg-4">
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
                            <li class="m-nav__item"><a href="${basePath}/manage/ticket/${ticket.ticketId}/appoint/create" class="m-nav__link"> <span class="m-nav__link-text">委派工单</span></a></li>
                            <li class="m-nav__item"><a href="${basePath}/manage/ticket/${ticket.ticketId}/appoint/toreject" class="m-nav__link"> <span class="m-nav__link-text">拒绝工单</span></a></li>
                            <li class="m-nav__item"><a href="${basePath}/manage/ticket/complete/${ticket.ticketId}" class="m-nav__link"> <span class="m-nav__link-text">完成工单</span></a></li>
                            <li class="m-nav__item"><a href="${basePath}/manage/ticket/${ticket.ticketId}/assessment/assess" class="m-nav__link"> <span class="m-nav__link-text">评价</span></a></li>
                            <li class="m-nav__item"><a href="${basePath}/manage/ticket/${ticket.ticketId}/record/create" class="m-nav__link"> <span class="m-nav__link-text">处理工单</span></a></li>
                            <li></li>
                            <li class="m-nav__item"><a href="${basePath}/manage/ticket/${ticket.ticketId}/record/index" class="m-nav__link"> <span class="m-nav__link-text">工单记录管理</span></a></li>
                            <li class="m-nav__item"><a href="${basePath}/manage/ticket/${ticket.ticketId}/appoint/index" class="m-nav__link"> <span class="m-nav__link-text">工单委派管理</span></a></li>
                            <li class="m-nav__item"><a href="${basePath}/manage/ticket/${ticket.ticketId}/assessment/index" class="m-nav__link"> <span class="m-nav__link-text">工单评价管理</span></a></li>
                        </ul>
                        <div class="m-portlet__body-separator"></div>

                    </div>
                </div>
            </div>
            <div class="col-xl-9 col-lg-8">
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
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <!-- the ticket content -->
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <label for="description">故障描述</label> <span id="description">${ticket.description }</span>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <label >工单类型</label> <span>${ticket.ticketType.name }</span>
                                        </div>
                                        <div class="col-sm-6">
                                            <label>优先级</label> <span>${ticket.priority }</span>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <label >执行人</label> <span>${ticket.serviceman}</span>
                                        </div>
                                        <div class="col-sm-6">
                                            <label >执行人电话</label> <span>${ticket.servicePhone}</span>
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <label >顾客联系人</label> <span>${ticket.customerContacts}</span>
                                        </div>
                                        <div class="col-sm-6">
                                            <label >顾客电话</label> <span>${ticket.customerPhone}</span>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <label for="status">工单状态</label> <span id="status">${ticket.status}</span>
                                        </div>
                                        <div class="col-sm-6">
                                            <label for="createTime">创建时间</label> <span id="createTime">${ticket.createTime}</span>
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
                                                <label for="assessmentLevel">评价星级</label> <span id="assessmentLevel">${ticket.assessmentLevel}</span>
                                            </div>
                                            <div class="col-sm-6">
                                                <label for="tagNames">评价标签</label> <span id="tagNames">${ticket.tagNames}</span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <label>评价描述</label> <span >${ticket.assessmentDescription}</span>
                                            </div>
                                        </div>
                                    </c:if>

                                    <div class="row">
                                        <div class="form-group text-right dialog-buttons">
                                            ${nextOperateBtn}
                                            <a class="waves-effect waves-button" href="javascript:;" onclick="detailDialog.close();">取消</a>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</content>

<pageResources>
<script>
    //document ready
    $(function() {

        $('span[id^="createTime"]').each(function() {
            $(this).text(timeFormatter($(this).text()));
        });

    });

    //格式化时间
    function timeFormatter(value, row, index) {
        return new Date(value).toLocaleString();
    }

    function toaction(type) {
            var ticketId= ${ticket.ticketId};
            var status= '${ticket.status}';
           if('TOAPPOINT'==type){
                if(status == '待派工')
                    createChildWindow('appoint', '委派工单', '${basePath}/manage/ticket/' + ticketId + '/appoint/create');
                else
                    showInfo('未委派的订单才能委派');
            }else if('TOASSESSMENT'==type){
                if(status == '待评价')
                    createChildWindow('assessment', '评价', '${basePath}/manage/ticket/' + ticketId + '/assessment/assess');
                else
                    showInfo('完成状态的订单才能评价');
            }
            else if('TORECORD'==type)
                createChildWindow('record', '处理工单', '${basePath}/manage/ticket/' +ticketId  + '/record/create');
            else if('REJECT'==type){
                if(status == '待维修')
                    createChildWindow('reject', '拒绝工单', '${basePath}/manage/ticket/' + ticketId + '/appoint/toreject');
                else
                    showInfo('已委派的订单才能拒绝');
            }
            else if('COMPLETE'==type){
                if(status == '维修中')
                    directlyAction('完成工单',  '${basePath}/manage/ticket/complete/' + ticketId);
                else
                    showInfo('维修状态的订单才能完成工单');
            }
    }


    var appointDialog, rejectDialog, assessmentDialog, recordDialog;
    function createChildWindow(vardialog, title, url) {
        var d = $.dialog({
            animationSpeed: 300,
            title: title,
            columnClass: 'xlarge',
            content: 'url:'+url,
            onContentReady: function () {
                initMaterialInput();
                $('select').select2();
            }
        });
        if(vardialog == 'appoint'){
            appointDialog = d;
            rejectDialog  =null;
            assessmentDialog  =null;
            recordDialog  =null;
        }else if(vardialog == 'reject'){
            rejectDialog=d;
            appointDialog  =null;
            assessmentDialog  =null;
            recordDialog  =null;
        }else if(vardialog == 'assessment'){
            assessmentDialog =d;
            appointDialog  =null;
            rejectDialog  =null;
            recordDialog  =null;
        }else if(vardialog == 'record'){
            recordDialog =d;
            appointDialog  =null;
            assessmentDialog  =null;
            rejectDialog  =null;
        }
    }

    var directlyOperateDialog;
    function directlyAction(name,url) {
        directlyOperateDialog = $.confirm({
            type: 'red',
            animationSpeed: 300,
            title: false,
            content: '确认'+name+'吗？',
            buttons: {
                confirm: {
                    text: '确认',
                    btnClass: 'waves-effect waves-button',
                    action: function () {
                        $.ajax({
                            type: 'get',
                            url: url,
                            success: function(result) {
                                if (result.code != 1) {
                                    showInfo('failure');
                                } else {
                                    directlyOperateDialog.close();
                                    showInfo('success');
                                    $table.bootstrapTable('refresh');
                                }
                            },
                            error: function(XMLHttpRequest, textStatus, errorThrown) {
                                showInfo(textStatus);
                            }
                        });
                    }
                },
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
    }

    function showInfo( textStatus) {
        $.confirm({
            theme: 'dark',
            animation: 'rotateX',
            closeAnimation: 'rotateX',
            title: false,
            content: textStatus,
            buttons: {
                confirm: {
                    text: '确认',
                    btnClass: 'waves-effect waves-button waves-light'
                }
            }
        });
    }


</script>
</pageResources>
</body>
</html>