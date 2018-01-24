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

    <style type="text/css">
        body {
            font-family: Helvetica Neue,Helvetica,PingFang SC,Hiragino Sans GB,Microsoft YaHei,\\5FAE\8F6F\96C5\9ED1,Arial,sans-serif;
            font-size: 12px;
            line-height: 1.5;
            color: #657180;
            background-color: #fff;
            -webkit-font-smoothing: antialiased;
        }

        body .index-tags{
            font-size: 1rem;
            margin-top: 3.125rem;
        }
        body .index-tags .index-tags-wrapper {
            width: 45.375rem;
            margin: 0 auto;
            display: flex;
            -webkit-box-orient: horizontal;
            -webkit-box-direction: normal;
            flex-direction: row;
            flex-wrap: wrap;
            -webkit-box-pack: center;
            justify-content: center;
        }

        a {
            color: #888;
            font-weight: 500;
            line-height: 1.8rem;
            margin-right: 1.5rem;
        }

        body .index-search-box {
            width: 45.375rem;
            height: 4rem;
            display: flex;
            -webkit-box-orient: horizontal;
            -webkit-box-direction: normal;
            flex-direction: row;
            -webkit-box-align: center;
            align-items: center;
            -webkit-box-pack: justify;
            justify-content: space-between;
            margin: 0 auto;
            margin-top: 3.75rem;
            background: #fff;
            border-radius: 2rem;
            overflow: hidden;
            box-sizing: border-box;
            border: 1px solid #e8e8e8;
            box-shadow: 0 0 30px rgba(0,0,0,.05);
            transition: all .3s ease;
        }

        body .index-search-box input {
            height: 2rem;
            background: transparent;
            font-size: 1.25rem;
            margin-left: 2rem;
            width: 38.75rem;
            border: none;
            outline: none;
        }

        /*.iconfont {*/
            /*font-family: iconfont;*/
            /*font-size: 16px;*/
            /*font-style: normal;*/
            /*-webkit-font-smoothing: antialiased;*/
        /*}*/


    </style>
</head>


<body>
    <div class="index-search-box">
        <input id="key" name="key" type="search" placeholder="请输入你要搜索的关键词...">
    </div>

<div class="index-tags">
    <div class="index-tags-wrapper clearfix">
        <c:forEach var="tag" items="${tags}">
            <a href="${basePath}/manage/knowledge/search?k=&t=${tag.tag}">${tag.tag}  </a>
        </c:forEach>
    </div>
</div>


<script src="${basePath}/resources/kuyun-admin/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/kuyun-admin/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
    $("#key").keydown(function() {//给输入框绑定按键事件
        if(event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
            window.location = "${basePath}/manage/knowledge/search?k="+$('#key').val();
        }
    })
</script>

</body>

</html>

