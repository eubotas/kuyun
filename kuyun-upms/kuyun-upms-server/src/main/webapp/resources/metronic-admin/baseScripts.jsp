<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<!--begin::Base Scripts -->
<script src="${basePath}/resources/metronic-admin/assets/vendors/base/vendors.bundle.js" type="text/javascript"></script>
<script src="${basePath}/resources/metronic-admin/assets/demo/default/base/scripts.bundle.js" type="text/javascript"></script>
<!--end::Base Scripts -->

<link rel="stylesheet" href="${basePath}/resources/metronic-admin/assets/css/bootstrap-table.1.11.1/bootstrap-table.min.css">
<script src="${basePath}/resources/metronic-admin/assets/js/bootstrap-table.1.11.1/bootstrap-table.min.js"></script>
<script src="${basePath}/resources/metronic-admin/assets/js/bootstrap-table.1.11.1/bootstrap-table-zh-CN.min.js"></script>
<script src="${basePath}/resources/metronic-admin/assets/js/jquery.tmpl.min.js"></script>
<script src="${basePath}/resources/metronic-admin/assets/js/sweetalert.2.1.0/sweetalert.min.js"></script>
<script src="${basePath}/resources/metronic-admin/assets/js/common.js" type="text/javascript"></script>
