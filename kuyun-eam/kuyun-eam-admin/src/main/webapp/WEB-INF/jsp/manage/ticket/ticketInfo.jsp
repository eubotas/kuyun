﻿<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />


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
<script>
	//document ready
	$(function() {

		$('span[id^="createTime"]').each(function() {
			$(this).text(timeFormatter($(this).text()));
		});
		// $('span[id="endDate"]').each(function() {
		// 	$(this).text(timeFormatter($(this).text()));
		// });

	});

	//格式化时间
	function timeFormatter(value, row, index) {
		return new Date(value).toLocaleString();
	}

    function closeBtn(refreshtable){
        if(createDialog) {
            createDialog.close();
            if(refreshtable)
                refreshtable.bootstrapTable('refresh');
        }else if(updateDialog)
            updateDialog.close();
        else if(appointDialog)
            appointDialog.close();
        else if(rejectDialog)
            rejectDialog.close();
        else if(assessmentDialog)
            assessmentDialog.close();
        else if(recordDialog)
            recordDialog.close();
    }
</script>