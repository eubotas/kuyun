
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
                <div class="search-header">
                    <div class="search-header-top common-wrapper clearfix">
                        <div class="search-header-searchbox">
                            <input id="key" name="key" type="search" placeholder="请输入你要搜索的关键词..." value="${k}">
                        </div>
                    </div>

                    <div class="search-header-tabs">
                        <div class="common-wrapper">
                            <c:forEach var="tab" items="${tabs}">
                                ${tab}
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="search-body common-wrapper clearfix">
                    <div class="search-body-main content">
                        <div class="list">
                            <div class="search-body-main-tips">
                                为您找到相关结果约 <span>${total}</span> 个
                            </div>
                            <ul>
                                <li class="search-item">
                                    <c:forEach var="row" items="${rows}">
                                        <a href="${basePath}/manage/knowledge/${row.type}/${row.id}"><h4>${row.title}</h4>  </a>
                                        <p class="desc">${row.description}</p>
                                        <div class="info"></div>
                                    </c:forEach>
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

    <script type="text/javascript">
        $("#key").keydown(function() {//给输入框绑定按键事件
            if(event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
                window.location = "${basePath}/manage/knowledge/search?k="+$('#key').val();
            }
        })
    </script>

</pageResources>


</body>
</html>
