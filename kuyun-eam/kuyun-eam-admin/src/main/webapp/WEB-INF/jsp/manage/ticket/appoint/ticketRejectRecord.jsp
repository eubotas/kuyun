
﻿﻿<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
                        <a href="${basePath}/manage/ticket/index" class="m-nav__link m-nav__link--icon">
                            <i class="m-nav__link-icon la la-home"></i>工单列表
                        </a>
                    </li>
                    <li class="m-nav__separator">
                        -
                    </li>
                    <li class="m-nav__item">
                        <a href="${basePath}/manage/ticket/detail/${ticketId}" class="m-nav__link">
											<span class="m-nav__link-text">
												工单详细
											</span>
                        </a>
                    </li>
                    <li class="m-nav__separator">
                        -
                    </li>
                    <li class="m-nav__item">
                        <a href="" class="m-nav__link">
											<span class="m-nav__link-text">
												工单委派拒绝列表
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
                <div class="form-group m-form__group" style="width:600px;margin-left:200px;">
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
                                <span class="dateTime">${record.rejectDate }</span>
                            </div>
                        </div>
                        <div class="row">
                            <hr />
                        </div>
                    </c:forEach>

                    <div class="row">
                        <hr />
                    </div>
                </div>
            </div>
        </div>

</content>


<pageResources>

    <script>
        $(function() {
            $('span[class^="dateTime"]').each(function() {
                var val = $(this).text();
                if(val !='')
                $(this).text(timeFormatter(val));
            });
        });
    </script>
</pageResources>


</body>
</html>

