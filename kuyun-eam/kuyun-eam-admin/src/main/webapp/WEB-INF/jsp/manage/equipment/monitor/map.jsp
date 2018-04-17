﻿﻿<%@ page contentType="text/html; charset=utf-8" %>
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
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <style>
        .marker {
            color: #ff6600;
            padding: 4px 10px;
            border: 1px solid #fff;
            white-space: nowrap;
            font-size: 12px;
            font-family: "";
        }
    </style>
    <script src="http://webapi.amap.com/maps?v=1.4.5&key=706c38ea4b86152fb9862ed09bd23ff8"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>

    <!-- BEGIN: Subheader -->
    <div class="m-subheader ">
        <div class="d-flex align-items-center">
            <div class="mr-auto">
                <ul class="m-subheader__breadcrumbs m-nav m-nav--inline">
                    <a href="" class="m-nav__link">
											<span class="m-nav__link-text">
												设备监控
											</span>
                    </a>
                    <li class="m-nav__separator">
                        -
                    </li>
                    <li class="m-nav__item">
                        <a href="#" class="m-nav__link">
                            <i class="icon-shield"></i>
                            <span class="m-nav__link-text">
												地图模式
											</span>
                        </a>
                    </li>

                    <li class="m-nav__item">
                        <a href="/manage/equipment/monitor/list" class="m-nav__link">
											<span class="m-nav__link-text">
												列表模式
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
            <div class="col-xl-2 col-lg-4">
                <div class="m-portlet m-portlet--full-height  ">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-caption">
                            <div class="m-portlet__head-title">
                                <h1 class="m-portlet__head-text">
                                    设备列表
                                </h1>
                            </div>
                        </div>
                    </div>

                    <div class="m-portlet__body">
                        <ul id="treeCity" class="ztree m-nav m-nav--hover-bg m-portlet-fit--sides">
                        </ul>
                        <div class="m-portlet__body-separator"></div>

                    </div>
                </div>
            </div>
            <div class="col-xl-10 col-lg-8">

                <div class="m-portlet m-portlet--mobile">
                    <div class="m-portlet__body">


                        <div id="mapContainer"></div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</content>


<pageResources>
    <link href="${basePath}/resources/kuyun-admin/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"/>
    <script src="${basePath}/resources/kuyun-admin/plugins/zTree_v3/js/jquery.ztree.all.min.js"></script>

    <script>
        $(document).ready(function()
        {
            addMarker();
        });
    </script>


    <script>
        var marker, map = new AMap.Map("mapContainer", {
            resizeEnable: false,
            center: [116.397428, 39.90923],
            zoom: 5
        });
        AMap.event.addDomListener(document.getElementById('clearMarker'), 'click', function() {
            if (marker) {
                marker.setMap(null);
                marker = null;
            }
        }, false);

        // 实例化点标记
        function addMarker() {
            marker = new AMap.Marker({
                icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
                position: [116.405467, 39.907761]
            });
            marker.setMap(map);
            updateMarker();
        }

        function updateMarker() {
            // 自定义点标记内容
            var markerContent = document.createElement("div");

            // 点标记中的图标
            var markerImg = document.createElement("img");
            markerImg.className = "markerlnglat";
            markerImg.src = "http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png";
            markerContent.appendChild(markerImg);

            // 点标记中的文本
            var markerSpan = document.createElement("span");
            markerSpan.className = 'marker';

            var markInfo='<div style="color: white;font-size: 14px;background-color: rgba(0,155,255,0.8);line-height: 26px; padding: 0px 0 0 6px; font-weight: lighter; varter-spacing: 1px">设备概况</div>' +
                '<div style="padding: 4px;line-height: 45px; width:200px; background-color:rgba(100, 119, 132, 0.8);">' +
                '<img style=" float: left; margin: 3px; width: 60px" src="http://122.112.237.243:9498/files/f46a4f9d-abcc-43bf-8020-c8d3677b5f16">' +
                '<a href="javascritp:void(0);" ng-click="selectNodefromMap()">测试</a><br>wlPQ5hKAIFSyzu5g<br></div>';

            markerSpan.innerHTML = markInfo;
            markerContent.appendChild(markerSpan);

            marker.setContent(markerContent); //更新点标记内容
            marker.setPosition([116.391467, 39.927761]); //更新点标记位置
        }
    </script>
</pageResources>


</body>
</html>