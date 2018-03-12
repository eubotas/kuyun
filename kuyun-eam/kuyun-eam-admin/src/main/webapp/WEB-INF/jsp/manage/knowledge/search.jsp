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
                            <a href="${basePath}/manage/knowledge/${row.type}/${row.id}/index"><h4>${row.title}</h4>  </a>
                            <p class="desc">${row.description}</p>
                            <div class="info"></div>
                        </c:forEach>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>





<script src="${basePath}/resources/kuyun-admin/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/kuyun-admin/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
    $("#key").keydown(function() {//给输入框绑定按键事件
        if(event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
            window.location = "${basePath}/manage/knowledge/search/index?k="+$('#key').val();
        }
    })
</script>

</body>

</html>

