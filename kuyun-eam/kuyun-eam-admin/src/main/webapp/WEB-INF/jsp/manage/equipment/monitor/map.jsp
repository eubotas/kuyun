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
                    <li class="m-nav__item m-nav__item--home">
                        <a href="#" class="m-nav__link m-nav__link--icon">
                            <i class="m-nav__link-icon la la-home">设备监控</i>
                        </a>
                    </li>
                    <li class="m-nav__separator">
                        -
                    </li>
                    <li class="m-nav__item">
                        <a href="#" class="m-nav__link">
											<span class="m-nav__link-text">
												列表模式
											</span>
                        </a>
                    </li>
                    <li class="m-nav__item">
                        <a href="/manage/equipment/monitor/list" class="m-nav__link">
											<span class="m-nav__link-text">
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

    <div class="m-portlet m-portlet--mobile">
        <div class="m-portlet__body">
            <div class="nav-tabs-horizontal" data-approve="nav-tabs">
                <ul class="nav nav-tabs" role="tablist">
                    <li class="active" role="presentation">
                        <a data-toggle="tab" href="#exampleTabsOne" aria-controls="exampleTabsOne" role="tab" aria-expanded="true">
                            首页
                        </a>
                    </li>
                    <li role="presentation" class="">
                        <a data-toggle="tab" href="#exampleTabsTwo" aria-controls="exampleTabsTwo" role="tab" aria-expanded="false">
                            最新
                        </a>
                    </li>
                    <li role="presentation" class="">
                        <a data-toggle="tab" href="#exampleTabsThree" aria-controls="exampleTabsThree" role="tab" aria-expanded="false">
                            最热
                        </a>
                    </li>
                    <li role="presentation" class="">
                        <a data-toggle="tab" href="#exampleTabsFour" aria-controls="exampleTabsFour" role="tab" aria-expanded="false">
                            深度
                        </a>
                    </li>
                    <li class="dropdown hidden" role="presentation" style="opacity: 1;"><a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false"><span class="caret"></span> 更多</a><ul class="dropdown-menu" role="menu"></ul></li></ul>
                <div class="tab-content padding-top-20">
                    <div class="tab-pane active" id="exampleTabsOne" role="tabpanel">
                        近日沃尔沃宣布将联合瑞典汽车安全公司奥托立夫(AUTOLIV)成立一个独立子公司，该公司将专攻瞄准未来车型自动驾驶技
                        术相关的研发。奥托立夫公司(AUTOLIV)是在瑞典设立的一家国际跨国公司，公司多年来研发汽车电子安全系统、电子控制单元，汽车方向盘系统以及夜视
                        和雷达传感系统，新的合作能够让新公司吸取Autoliv多年来在汽车驾驶安全配件制造方面的经验，研发未来用于沃尔沃或其它厂商的无人驾驶软件系统。
                    </div>
                    <div class="tab-pane" id="exampleTabsTwo" role="tabpanel">
                        在试运营期间，Jio将向全印度人免费提供服务，直到今年年底。在免费期过后，其数据流量月资费也低至每月149卢比（约合15元人民币）。安巴尼上周在公司年度全体大会上对投资者说：“任何、所有能实现数字化的东西都将快速走向数字化，生活将走向数字化。”目前，只有五分之一的印度成年人口能够上网。在印度，公共WiFi热点极少。城市贫困区缺乏高速宽带所需的基础设备，更不用说乡村地区了。
                    </div>
                    <div class="tab-pane" id="exampleTabsThree" role="tabpanel">
                        车轮查违章与百度地图推出《中国车主违章地图大数据》报告，江苏高居“违章停车”榜首，在全国范围内，江苏、湖北、山东、浙江以及北京五个省市中的车主违章比例最高，其中江苏省以高达11.52%的比例遥遥领先。另外，江苏、湖北、山东三省的“马路杀手”已经超过了全国人口最多的广东省。有62.38%是不按规定行驶，26.18%超速、7.13%违章停车、2.82%无视尾号限行、1.49%不系安全带和不带头盔。
                    </div>
                    <div class="tab-pane" id="exampleTabsFour" role="tabpanel">
                        滴滴出行今日宣布，已在全国一百个城市正式上线了服务信用体系。未来每位快车车主都将拥有个人专属的服务信用档案和服务分值，为乘客提供优质服务的车主可获得更高的服务分，从而获得更多的订单和收入。因服务分每日变化，滴滴服务信用系统会根据每位快车车主的具体情况，帮助车主定制每天提高服务分的攻略。
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-2">
                </div>
                <div class="col-sm-9" id="container">
                </div>
            </div>
        </div>
    </div>

</content>


<pageResources>
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



</pageResources>


</body>
</html>