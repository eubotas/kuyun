﻿<%@
        page contentType="text/html; charset=utf-8"%><%@
        taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%><%@
        taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@
        taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%><%@
        taglib uri="http://www.springframework.org/tags" prefix="spring"%><%@
        taglib uri="http://www.springframework.org/tags/form" prefix="form"%><%@
        taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>工单管理</title>
    <jsp:include page="/resources/inc/head.jsp" flush="true"/>
    <link href="${basePath}/resources/kuyun-admin/plugins/fileupload/fine-uploader-gallery.css" rel="stylesheet">
</head>
<body>
<div class="row">
    <div class="col-sm-3">
        <label >累计报修</label>
         <a class="waves-effect waves-button" href="javascript:;" onclick="toAction('all');"><span>${ticketSummaryVo.totalTicketCount}</span></a>
    </div>
    <div class="col-sm-3">
        <label>未派工</label>
        <a class="waves-effect waves-button" href="javascript:;" onclick="toAction('init');"><span>${ticketSummaryVo.noAppointTicketCount}</span></a>
    </div>
    <div class="col-sm-3">
        <label >维修中</label>
        <a class="waves-effect waves-button" href="javascript:;" onclick="toAction('processing');"><span>${ticketSummaryVo.processingTicketCount}</span></a>
    </div>
    <div class="col-sm-3">
        <label >未完成</label>
        <a class="waves-effect waves-button" href="javascript:;" onclick="toAction('notResolved');"><span>${ticketSummaryVo.notResolvedTicketCount}</span></a>
    </div>
    <div class="col-sm-3">
        <label >已完成</label>
        <a class="waves-effect waves-button" href="javascript:;" onclick="toAction('resolved');"><span>${ticketSummaryVo.resolvedTicketCount}</span></a>
    </div>
</div>

<div class="row">
    <hr />
</div>

<jsp:include page="/resources/inc/footer.jsp" flush="true"/>

<script>
    function toAction(type){
        window.location = '${basePath}/manage/ticket/summary?category='+ type;
    }
</script>