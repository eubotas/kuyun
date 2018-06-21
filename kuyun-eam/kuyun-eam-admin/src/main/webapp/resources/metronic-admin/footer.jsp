<%@ page import="com.kuyun.eam.admin.util.SessionUser" %>
<%@ page import="com.kuyun.common.util.BasePath" %>
<%@ page import="com.kuyun.upms.dao.vo.CompanyInfo" %>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
    <c:set var="upmsPath" value="<%=BasePath.getInstance().getKuyunUpmsServer()%>"/>

<script src="${basePath}/resources/metronic-admin/assets/vendors/base/vendors.bundle.js" type="text/javascript"></script>
<script src="${basePath}/resources/metronic-admin/assets/js/kuyun_common.js" type="text/javascript"></script>
<!-- begin::Footer -->
<footer class="m-grid__item		m-footer ">
    <div class="m-container m-container--fluid m-container--full-height m-page__container">
        <div class="m-stack m-stack--flex-tablet-and-mobile m-stack--ver m-stack--desktop">
            <div class="m-stack__item m-stack__item--left m-stack__item--middle m-stack__item--last">
                <span class="m-footer__copyright">
                        <% CompanyInfo company = new SessionUser().getCompany(request);
                         if(company !=null){  //session expired  %>
                            <script>
                            KConfirm('Session 过期.',function(){
                                window.location.href ='${upmsPath}/sso/login';
                            });
                            </script>
                         <%}
                        String companyName=company.getCompanyName();
                        if(companyName == null || "".equals(companyName)){
                        %>
                                版权所有  2018©库德莱兹物联科技（苏州）有限公司
								<a href="http://www.coderise.cn" target="_blank">
									coderise.cn
								</a>
                         <%}else{%>
                            <%=companyName%>
                         <%}%>
                </span>
            </div>
        </div>
    </div>
</footer>
<!-- end::Footer -->



