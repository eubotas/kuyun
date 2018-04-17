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
                        <a href="/manage/equipment/monitor/map" class="m-nav__link">
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
                        <ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
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


        </div>
    </div>

</content>


<pageResources>
    <link href="${basePath}/resources/kuyun-admin/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"/>
    <script src="${basePath}/resources/kuyun-admin/plugins/zTree_v3/js/jquery.ztree.all.min.js"></script>

    <script type="text/javascript">
        var zTree;
        var setting = {
            view: {
                dblClickExpand: false,
                showLine: true,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable:true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: ""
                }
            },
            callback: {
                beforeClick: function(treeId, treeNode) {
                    var zTree = $.fn.zTree.getZTreeObj("tree");
                    if (treeNode.isParent) {
                        zTree.expandNode(treeNode);
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        };

        var zNodes =[
            {id:1, pId:0, name:"水果", open:true},
            {id:101, pId:1, name:"苹果"},
            {id:102, pId:1, name:"香蕉"},
            {id:103, pId:1, name:"梨"},
            {id:10101, pId:101, name:"红富士苹果"},
            {id:10102, pId:101, name:"红星苹果"},
            {id:10103, pId:101, name:"嘎拉"},
            {id:10104, pId:101, name:"桑萨"},
            {id:10201, pId:102, name:"千层蕉"},
            {id:10202, pId:102, name:"仙人蕉"},
            {id:10203, pId:102, name:"吕宋蕉"},
        ];

        $(document).ready(function(){
            var t = $("#tree");

            /**
             * zTree 初始化方法：$.fn.zTree.init(t, setting, zNodes)
             * t:用于展现 zTree 的 DOM 容器
             * setting:zTree 的配置数据
             * zNodes:zTree 的节点数据
             */
            t = $.fn.zTree.init(t, setting, zNodes);

            //zTree默认选中指定节点并执行事件
            // var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            // var node = treeObj.getNodeByParam("id", "370000");
            // treeObj.selectNode(node);
            // setting.callback.onClick = function(){};
        });
    </script>

</pageResources>


</body>
</html>