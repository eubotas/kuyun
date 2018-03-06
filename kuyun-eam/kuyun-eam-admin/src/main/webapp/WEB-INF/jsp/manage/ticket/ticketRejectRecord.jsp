<%@ page contentType="text/html; charset=utf-8"%>
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

<div class="row">
    <div class="col-sm-4">
        <span>用户名</span>
    </div>
    <div class="col-sm-4">
        <span>拒绝原因</span>
    </div>
    <div class="col-sm-4">
        <span>拒绝时间</span>
    </div>
</div>
<c:forEach var="record" items="${records}">
    <div class="row">
        <div class="col-sm-4">
            <span>${record.userName }</span>
        </div>
        <div class="col-sm-4">
            <span>${record.rejectCommont }</span>
        </div>
        <div class="col-sm-4">
             <span>${record.rejectDate }</span>
        </div>
    </div>
    <div class="row">
        <hr />
    </div>
</c:forEach>

<div class="row">
	<hr />
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
</script>