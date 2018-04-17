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
											<span class="m-nav__link-text">
												列表模式
											</span>
                        </a>
                    </li>
                    <li class="m-nav__item">
                        <a href="/manage/equipment/monitor/map" class="m-nav__link">
                            <i class="icon-shield"></i>
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
            <div class="nav-tabs-horizontal" data-approve="nav-tabs">
                <ul class="nav nav-tabs" role="tablist">
                    <li class="active" role="equipment">
                        <a data-toggle="tab" href="#equipment" aria-controls="equipment" role="tab" aria-expanded="true">
                            设备概况
                        </a>
                    </li>
                    <li role="run" class="">
                        <a data-toggle="tab" href="#run" aria-controls="run" role="tab" aria-expanded="false">
                            运行监控
                        </a>
                    </li>
                    <li role="video" class="">
                        <a data-toggle="tab" href="#video" aria-controls="video" role="tab" aria-expanded="false">
                            视频监控
                        </a>
                    </li>
                    <li role="runData" class="">
                        <a data-toggle="tab" href="#runData" aria-controls="runData" role="tab" aria-expanded="false">
                            运行数据
                        </a>
                    </li>
                    <li role="startStop" class="">
                        <a data-toggle="tab" href="#startStop" aria-controls="startStop" role="tab" aria-expanded="false">
                            机组启停
                        </a>
                    </li>
                    <li role="histroyData" class="">
                        <a data-toggle="tab" href="#histroyData" aria-controls="histroyData" role="tab" aria-expanded="false">
                            历史数据
                        </a>
                    </li>
                <div class="tab-content padding-top-20">
                    <div class="tab-pane active" id="equipment" role="tabpanel">
                        近日沃尔沃宣布将联合瑞典汽车安全公司奥托立夫(AUTOLIV)成立一个独立子公司，该公司将专攻瞄准未来车型自动驾驶技
                        术相关的研发。奥托立夫公司(AUTOLIV)是在瑞典设立的一家国际跨国公司，公司多年来研发汽车电子安全系统、电子控制单元，汽车方向盘系统以及夜视
                        和雷达传感系统，新的合作能够让新公司吸取Autoliv多年来在汽车驾驶安全配件制造方面的经验，研发未来用于沃尔沃或其它厂商的无人驾驶软件系统。
                    </div>
                    <div class="tab-pane" id="run" role="tabpanel">
                        在试运营期间，Jio将向全印度人免费提供服务，直到今年年底。在免费期过后，其数据流量月资费也低至每月149卢比（约合15元人民币）。安巴尼上周在公司年度全体大会上对投资者说：“任何、所有能实现数字化的东西都将快速走向数字化，生活将走向数字化。”目前，只有五分之一的印度成年人口能够上网。在印度，公共WiFi热点极少。城市贫困区缺乏高速宽带所需的基础设备，更不用说乡村地区了。
                    </div>
                    <div class="tab-pane" id="video" role="tabpanel">
                        车轮查违章与百度地图推出《中国车主违章地图大数据》报告，江苏高居“违章停车”榜首，在全国范围内，江苏、湖北、山东、浙江以及北京五个省市中的车主违章比例最高，其中江苏省以高达11.52%的比例遥遥领先。另外，江苏、湖北、山东三省的“马路杀手”已经超过了全国人口最多的广东省。有62.38%是不按规定行驶，26.18%超速、7.13%违章停车、2.82%无视尾号限行、1.49%不系安全带和不带头盔。
                    </div>
                    <div class="tab-pane" id="runData" role="tabpanel">
                        滴滴出行今日宣布，已在全国一百个城市正式上线了服务信用体系。未来每位快车车主都将拥有个人专属的服务信用档案和服务分值，为乘客提供优质服务的车主可获得更高的服务分，从而获得更多的订单和收入。因服务分每日变化，滴滴服务信用系统会根据每位快车车主的具体情况，帮助车主定制每天提高服务分的攻略。
                    </div>
                    <div class="tab-pane" id="startStop" role="tabpanel">
                        startStop
                    </div>
                    <div class="tab-pane" id="histroyData" role="tabpanel">
                        histroyData
                    </div>
                </div>
            </div>

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

        // zTree = $.fn.zTree.getZTreeObj("treeCity");//treeDemo界面中加载ztree的div
        // var node = zTree.getNodeByParam("id",pid );
        // zTree.cancelSelectedNode();//先取消所有的选中状态
        // zTree.selectNode(node,true);//将指定ID的节点选中
        // zTree.expandNode(node, true, false);//将指定ID节点展开

        $(document).ready(function()
        {
            $.fn.zTree.init($("#treeCity"), setting, null);

            var setting = {
                async: {
                    enable: true,//采用异步加载
                    dataFilter: ajaxDataFilter,    //预处理数据
                    url : "${basePath}/manage/equipment/city/tree",
                    dataType : "json"
                },
                data : {
                    key : {
                        title : "name",
                        name : "name"
                    },
                    simpleData : {
                        enable : true,
                        idKey : "code",
                        pIdKey : "code",
                        rootPid : 000
                    }
                },
                callback : {
                    beforeClick: zTreeBeforeClick,
                    onClick : zTreeOnClick,
                    onAsyncSuccess: zTreeOnAsyncSuccess //异步加载完成调用
                }
            };


        });


        function onCheck(e,treeId,treeNode) {
            var treeObj = $.fn.zTree.getZTreeObj("treeCity"),
                nodes = treeObj.getCheckedNodes(true),
                v = "";
            for (var i = 0; i < nodes.length; i++) {
                v += nodes[i].name + ",";
                alert(nodes[i].id); //获取选中节点的值
            }
        }

        /* 获取返回的数据，进行预操作 */
        function ajaxDataFilter(treeId, parentNode, responseData) {
            responseData = responseData.jsonArray;
            return responseData;
        };
        //异步加载完成时运行，此方法将所有的节点打开
        function zTreeOnAsyncSuccess(event, treeId, msg) {
            var treeObj = $.fn.zTree.getZTreeObj("treeCity");
            treeObj.expandAll(true);
        }
    </script>



</pageResources>


</body>
</html>