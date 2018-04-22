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

<pageResources>
    <link href="${basePath}/resources/kuyun-admin/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"/>
    <script src="${basePath}/resources/kuyun-admin/plugins/zTree_v3/js/jquery.ztree.all.min.js"></script>

    <script>
        var currMode='list';
        function changeMode(){
            if(currMode=='list'){
                currMode='map';
                $("#currModeId").text("地图模式");
                $("#NextMode").text("列表模式");
                $("#equipmentInfo").hide();
                $("#equipmentMap").show();
            }else{
                currMode='list';
                $("#currModeId").text("列表模式");
                $("#NextMode").text("地图模式");
                $("#equipmentInfo").show();
                $("#equipmentMap").hide();
            }
        }
    </script>

</pageResources>

<subHeader>
    <!-- BEGIN: Subheader -->
    <div class="m-subheader ">
        <div class="d-flex align-items-center">
            <div class="mr-auto">
                <ul class="m-subheader__breadcrumbs m-nav m-nav--inline">
                    <li class="m-nav__item">
                        <a href="#" class="m-nav__link">
											<span class="m-nav__link-text">
												设备监控
											</span>
                        </a>
                    </li>
                    <li class="m-nav__separator">
                        -
                    </li>
                    <li class="m-nav__item">
                        <a href="#" class="m-nav__link">
											<span class="m-nav__link-text" id="currModeId">
												列表模式
											</span>
                        </a>
                    </li>
                    <li class="m-nav__item">
                        <a id="goModeAddr" href="javascript:changeMode()" class="m-nav__link">
											<span class="m-nav__link-text" id="NextMode">
												地图模式
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
    <div class="m-content">
        <div class="row">
            <div class="col-xl-3 col-lg-4">

                <jsp:include page="./city.jsp" flush="true"/>

            </div>
            <div class="col-xl-9 col-lg-8">

            <div class="m-portlet m-portlet--mobile">
                <div class="m-portlet__body" id="equipmentInfo">
                    <jsp:include page="./equipmentInfo.jsp" flush="true"/>
                </div>

                <div class="m-portlet__body" id="equipmentMap" style="display: none">
                    <jsp:include page="./map.jsp" flush="true"/>
                </div>
            </div>
        </div>  <!-- end right content-->
        </div>  <!--end row -->
    </div>
</content>


</body>
</html>