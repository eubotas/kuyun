<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
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
												知识搜索
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

    <div class="m-portlet m-portlet--mobile">
        <div class="m-portlet__body">
            <div class="search">

                <div class="search-body common-wrapper clearfix">
                    <div class="search-body-main content">
                        <div class="list">

                            <ul>
                                <li class="search-item">

                                    <c:if test="${category == 'training-video'}">
                                        <p class="desc">标题:${model.title}</p>
                                        <div class="info">附件:${model.path}</div>
                                    </c:if>

                                    <c:if test="${category == 'training-doc'}">
                                        <p class="desc">标题:${model.title}</p>
                                        <div class="info">内容:${model.content}</div>
                                        <div class="info">附件:${model.path}</div>
                                    </c:if>

                                    <c:if test="${category == 'maintain-knowledge'}">
                                        <p class="desc">标题:${model.title}</p>
                                        <div class="info">内容:${model.content}</div>
                                    </c:if>

                                    <c:if test="${category == 'repair-knowledge'}">
                                        <p class="desc">故障代码:${model.codes}</p>
                                        <div class="info">故障描述:${model.description}</div>
                                        <div class="info">解决故障的方法:${model.method}</div>
                                    </c:if>

                                    <c:if test="${category == 'equipment-manual'}">
                                        <p class="desc">标题:${model.title}</p>
                                        <div class="info">内容:${model.content}</div>
                                        <div class="info">附件:${model.path}</div>
                                    </c:if>

                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</content>


<pageResources>
    <link href=https://static001-test.geekbang.org/static/s/css/app.27020f4b625d704bb12c2e9fdab26045.css rel=stylesheet>
    <script>


    </script>
</pageResources>


</body>
</html>
