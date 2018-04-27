<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">

<jsp:include page="/resources/metronic-admin/head.jsp" flush="true"/>
<body class="m-page--fluid m--skin- m-content--skin-light2 m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--skin-dark m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default"  >
<!-- begin:: Page -->
<div class="m-grid m-grid--hor m-grid--root m-page">

    <jsp:include page="/resources/metronic-admin/header/header.jsp" flush="true"/>

    <jsp:include page="/resources/metronic-admin/body.jsp" flush="true"/>

    <jsp:include page="/resources/metronic-admin/footer.jsp" flush="true"/>

</div>
<!-- end:: Page -->

<%--<jsp:include page="/resources/metronic-admin/quickSidebar.jsp" flush="true"/>--%>
<jsp:include page="/resources/metronic-admin/scrollTop.jsp" flush="true"/>
<%--<jsp:include page="/resources/metronic-admin/quickNav.jsp" flush="true"/>--%>
<jsp:include page="/resources/metronic-admin/baseScripts.jsp" flush="true"/>

<%--<jsp:include page="/resources/metronic-admin/pageResources.jsp" flush="true"/>--%>

<sitemesh:write property='pageResources' />

</body>

</html>