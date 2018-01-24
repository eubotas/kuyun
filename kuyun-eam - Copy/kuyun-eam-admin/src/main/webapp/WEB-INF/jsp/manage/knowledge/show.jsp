<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>知识搜索</title>
    <link href=https://static001-test.geekbang.org/static/s/css/app.27020f4b625d704bb12c2e9fdab26045.css rel=stylesheet>
</head>


<body>
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





<script src="${basePath}/resources/kuyun-admin/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/kuyun-admin/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>

<script type="text/javascript">

</script>

</body>

</html>

