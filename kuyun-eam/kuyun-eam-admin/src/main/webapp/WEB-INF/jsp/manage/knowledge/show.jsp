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
                        <a href="${basePath}/manage/knowledge/index" class="m-nav__link">
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

    <div class="m-portlet">
        <div class="m-portlet__head">
            <div class="m-portlet__head-caption">
                <div class="m-portlet__head-title">
                    <span class="m-portlet__head-icon">
                        <i class="flaticon-multimedia"></i>
                    </span>
                    <h3 class="m-portlet__head-text">
                        详细
                    </h3>
                </div>
            </div>
        </div>
        <div class="m-portlet__body">

            <c:if test="${category == 'training-video'}">
                <div class="row">
                    <div class="col-sm-6">
                        <span class="desc">标题: ${model.title}</span>
                    </div>
                    <div class="col-sm-3">
                        <span class="desc">日期: ${model.createTime}</span>
                    </div>
                </div>
                <div class="info">描述: ${model.description}</div>
                <div class="info" id="fileDiv">附件: </div>
            </c:if>

            <c:if test="${category == 'training-doc'}">
                <div class="row">
                    <div class="col-sm-6">
                        <span class="desc">标题: ${model.title}</span>
                    </div>
                    <div class="col-sm-3">
                        <span class="desc">日期: ${model.createTime}</span>
                    </div>
                </div>
                <div class="info">描述: ${model.description}</div>
                <div class="info">内容: ${model.content}</div>
                <div class="info" id="fileDiv">附件: </div>
            </c:if>

            <c:if test="${category == 'maintain-knowledge'}">
                <div class="row">
                    <div class="col-sm-6">
                        <span class="desc">标题: ${model.title}</span>
                    </div>
                    <div class="col-sm-3">
                        <span class="desc">日期: ${model.createTime}</span>
                    </div>
                </div>
                <div class="info">描述: ${model.description}</div>
                <div class="info">内容: ${model.content}</div>
                <div class="info" id="fileDiv">附件: </div>
            </c:if>

            <c:if test="${category == 'repair-knowledge'}">
                <div class="row">
                    <div class="col-sm-6">
                        <span class="desc">故障代码: ${model.codes}</span>
                    </div>
                    <div class="col-sm-3">
                        <span class="desc">日期: ${model.createTime}</span>
                    </div>
                </div>
                <div class="info">故障描述: ${model.description}</div>
                <div class="info">解决故障的方法: ${model.method}</div>
                <div class="info" id="fileDiv">附件: </div>
            </c:if>

            <c:if test="${category == 'equipment-manual'}">
                <div class="row">
                    <div class="col-sm-6">
                        <span class="desc">标题: ${model.title}</span>
                    </div>
                    <div class="col-sm-3">
                        <span class="desc">日期: ${model.createTime}</span>
                    </div>
                </div>
                <div class="info">描述: ${model.description}</div>
                <div class="info">内容: ${model.content}</div>
                <div class="info" id="fileDiv">附件: </div>
            </c:if>
        </div>
        <div class="modal-footer">
            <button id="backButton" type="button" class="btn btn-secondary" data-dismiss="modal">
                返回
            </button>
        </div>
    </div>



</content>


<pageResources>
    <script>
        $('#backButton').click(function(){
            goBack();
        });

        var html = fileFormatter('${model.path}');
        $("#fileDiv").append(html);

    </script>
</pageResources>


</body>
</html>
