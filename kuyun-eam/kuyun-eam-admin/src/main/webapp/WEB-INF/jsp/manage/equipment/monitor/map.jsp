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
            <div class="col-xl-3 col-lg-4">
                <jsp:include page="./city.jsp" flush="true"/>

            </div>
            <div class="col-xl-9 col-lg-8">
                <div class="m-portlet m-portlet--mobile">
                    <div class="m-portlet__body" >

                        <div id="container" style="width: 750px;height: 750px;">
                        </div>

                    </div>
                </div>
            </div> <!-- right end-->
        </div>
    </div>

</content>


<pageResources>
     <link href="${basePath}/resources/kuyun-admin/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"/>
     <script src="${basePath}/resources/kuyun-admin/plugins/zTree_v3/js/jquery.ztree.all.min.js"></script>

    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <style>
        .amap-logo{
            display: none;    /*隐藏左下角地图logo*/
        }
        .amap-copyright{
            display: none!important;
        }
    </style>
    <script src="http://webapi.amap.com/maps?v=1.4.5&key=706c38ea4b86152fb9862ed09bd23ff8"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>

    <script>
        var map = new AMap.Map('container', {});
        map.plugin(["AMap.ToolBar"], function() {
            map.addControl(new AMap.ToolBar());
        });
        var infoWindow = new AMap.InfoWindow({});     //创建信息窗口对象  ps.高德目前不支持多信息窗口，即使创建多个窗口对象，也只会显示一个

        var lnglats= [["116.4123","39.906422"],["116.4352","39.906933"],["113.65", "34.76"]];
        var data = [{"id":1,"name":"我是第1个点","type":1},{"id":2,"name":"我是第2个点","type":3},{"id":3,"name":"我是第3个点","type":7}];
        for(var i= 0;i<lnglats.length;i++){
            var marker=new AMap.Marker({
                position:lnglats[i],        //采用默认样式，无需自定义
                map:map
            });
            content = [];
            content.push('ID：'+data[i].id);
            content.push('名称：<a href="http://www.baidu.com">'+data[i].name+'</a>');
            marker.content = content;
            marker.title = data[i].id+"."+data[i].name;     //标记点的title

            if(i==0){              //默认打开第1个点的信息窗口
                infoWindow.setContent(content.join("<br/>"));
                infoWindow.open(map,lnglats[i]);
            }

            marker.on('dblclick',openAmap);
            //给Marker绑定单击事件
            marker.on('click', markerClick);
        }

        map.setFitView();
        //跳至地图当中导航
        function openAmap(e){
            e.target.markOnAMAP({
                name:e.target.title,
                position:e.target.getPosition()
            })
        }
        //信息窗口
        function markerClick(e){
            infoWindow.setContent(e.target.content.join('<br/>'));
            infoWindow.open(map, e.target.getPosition());
        }

        //地图加载完成事件
        map.on('complete', function() {
        });
    </script>

    <script>
        //toMap
        function callbackClickItem(treeNode){
            var equipId=treeNode.id;
            alert(1);
        }
    </script>

</pageResources>


</body>
</html>