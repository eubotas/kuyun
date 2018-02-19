<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />

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
        <label for="assessmentDescription">评价描述</label> <span >${ticket.assessmentDescription}</span>
    </div>
</div>
</c:if>

<div class="row">
    <div class="form-group text-right dialog-buttons">
        ${nextOperateBtn}
        <a class="waves-effect waves-button" href="javascript:;" onclick="detailDialog.close();">取消</a>
    </div>
</div>

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