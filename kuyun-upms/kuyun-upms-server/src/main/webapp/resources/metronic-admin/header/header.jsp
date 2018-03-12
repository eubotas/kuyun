<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>


<!-- BEGIN: Header -->
<header class="m-grid__item    m-header " data-minimize-offset="200" data-minimize-mobile-offset="200">
    <div class="m-container m-container--fluid m-container--full-height">
        <div class="m-stack m-stack--ver m-stack--desktop">

			<jsp:include page="/resources/metronic-admin/header/brand.jsp" flush="true"/>

            <div class="m-stack__item m-stack__item--fluid m-header-head" id="m_header_nav">
				<jsp:include page="/resources/metronic-admin/header/horizontalMenu.jsp" flush="true"/>

				<jsp:include page="/resources/metronic-admin/header/topBar.jsp" flush="true"/>

            </div>
        </div>
    </div>
</header>
<!-- END: Header -->