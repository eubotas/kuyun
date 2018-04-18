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
    <div class="m-content">
        <div class="row">
            <div class="col-xl-3 col-lg-4">
                <div class="m-portlet m-portlet--full-height  ">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-caption">
                            <div class="m-portlet__head-title">
                                <h2 class="m-portlet__head-text">
                                    各地设备列表
                                </h2>
                            </div>
                        </div>
                    </div>

                    <div id="ticketList" class="m-portlet__body">
                        <ul id="treeCity" class="ztree" style="width:260px; overflow:auto;"></ul>
                    </div>
                </div>
            </div>
            <div class="col-xl-9 col-lg-8">

    <div class="m-portlet m-portlet--mobile">
        <div class="m-portlet__body">
            <div class="nav-tabs-horizontal" data-approve="nav-tabs">
                <div class="m-portlet m-portlet--tabs m-portlet--head-solid-bg m-portlet--head-sm">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-tools">
                            <ul class="nav nav-tabs m-tabs m-tabs-line  m-tabs-line--right" role="tablist">
                                <li class="nav-item m-tabs__item">
                                    <a class="nav-link m-tabs__link active show" data-toggle="tab" href="#equipment" role="tab" aria-selected="true">
                                        设备概况
                                    </a>
                                </li>
                                <li class="nav-item m-tabs__item">
                                    <a class="nav-link m-tabs__link" data-toggle="tab" href="#run" role="tab" aria-selected="false">
                                        运行监控
                                    </a>
                                </li>
                                <li class="nav-item m-tabs__item">
                                    <a class="nav-link m-tabs__link" data-toggle="tab" href="#video" role="tab" aria-selected="false">
                                        视频监控
                                    </a>
                                </li>
                                <li class="nav-item m-tabs__item">
                                    <a class="nav-link m-tabs__link" data-toggle="tab" href="#runData" role="tab" aria-selected="false">
                                        运行数据
                                    </a>
                                </li>
                                <li class="nav-item m-tabs__item">
                                    <a class="nav-link m-tabs__link" data-toggle="tab" href="#startStop" role="tab" aria-selected="false">
                                        机组启停
                                    </a>
                                </li>
                                <li class="nav-item m-tabs__item">
                                    <a class="nav-link m-tabs__link" data-toggle="tab" href="#histroyData" role="tab" aria-selected="false">
                                        历史数据
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="m-portlet__body">
                        <div class="tab-content">
                            <div class="tab-pane active show" id="equipment" role="tabpanel">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="m-portlet m-portlet--tabs m-portlet--brand m-portlet--head-solid-bg m-portlet--head-sm">
                                            <div class="m-portlet__head">
                                                <div class="m-portlet__head-caption">
                                                    <div class="m-portlet__head-title">
                                                        <h3 class="m-portlet__head-text">
                                                            <i class="fa fa-cogs"></i>
                                                            设备信息
                                                        </h3>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="m-portlet__body">
                                                <div class="tab-content">
                                                    <div class="tab-pane active" role="tabpanel">
                                                        <div class="portlet yellow-crusta box">
                                                            <div class="portlet-body">
                                                                <div class="row static-info">
                                                                    <div class="col-md-5 name">设备序列号:</div>
                                                                    <div class="col-md-7 value" id="serialNumber">
                                                                    </div>
                                                                </div>
                                                                <div class="row static-info">
                                                                    <div class="col-md-5 name">设备ID:</div>
                                                                    <div class="col-md-7 value" id="equipmentId">
                                                                    </div>
                                                                </div>
                                                                <div class="row static-info">
                                                                    <div class="col-md-5 name">设备名称:</div>
                                                                    <div class="col-md-7 value" id="equipname"></div>
                                                                </div>
                                                                <div class="row static-info">
                                                                    <div class="col-md-5 name">设备编号:</div>
                                                                    <div class="col-md-7 value" id="number"></div>
                                                                </div>
                                                                <div class="row static-info">
                                                                    <div class="col-md-5 name">出厂日期:</div>
                                                                    <div class="col-md-7 value" id="factoryDate"></div>
                                                                </div>
                                                                <div class="row static-info">
                                                                    <div class="col-md-5 name">投产日期:</div>
                                                                    <div class="col-md-7 value" id="commissioningDate"></div>
                                                                </div>
                                                                <div class="row static-info">
                                                                    <div class="col-md-5 name">质保开始日期:</div>
                                                                    <div class="col-md-7 value" id="warrantyStartDate"></div>
                                                                </div>
                                                                <div class="row static-info">
                                                                    <div class="col-md-5 name">质保结束日期:</div>
                                                                    <div class="col-md-7 value" id="warrantyEndDate"></div>
                                                                </div>

                                                                <div class="row static-info">
                                                                    <div class="col-md-5 name">设备状态:</div>
                                                                    <div class="col-md-7 value">
                                                                        <span class="label label-success" id="isOnline"></span>
                                                                    </div>
                                                                </div>
                                                                <div class="row static-info">
                                                                    <div class="col-md-5 name">查看地图:</div>
                                                                    <div class="col-md-7 value">
                                                                        <a href="#"  onClick="changelistState()">
                                                                            <i class="fa fa-map"></i> </a>
                                                                    </div>
                                                                </div>

                                                            </div>



                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                <!-- left end -->
                                    <div class="col-lg-6">
                                        <div class="m-portlet m-portlet--tabs m-portlet--warning m-portlet--head-solid-bg m-portlet--head-sm">
                                        <div class="m-portlet__head">
                                            <div class="m-portlet__head-caption">
                                                <div class="m-portlet__head-title">
                                                    <h3 class="m-portlet__head-text">
                                                        设备快照
                                                    </h3>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="m-portlet__body">
                                            <div class="tab-content">
                                                <div class="tab-pane active" role="tabpanel">
                                                    <div class="portlet-body" style="height:310px">
                                                        <img class="img-responsive" style="max-height:300px;" id="imagesrc" src="" alt="">
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                <!-- right end -->
                                </div>
                            </div>
                            <div class="tab-pane" id="run" role="tabpanel">
                                <div class="m-portlet m-portlet--tabs m-portlet--brand m-portlet--head-solid-bg m-portlet--head-sm m-portlet--bordered">
                                    <div class="m-portlet__head">
                                        <div class="m-portlet__head-caption">
                                            <div class="m-portlet__head-title">
                                                <h3 class="m-portlet__head-text">
                                                    <i class="fa fa-cogs"></i>
                                                    运行监控
                                                </h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="m-portlet__body">
                                        <div class="tab-content">
                                            采集器中断，请确认您的设备连接和设置是否正常，并在资产管理->设备信息管理 中开启设备
                                        </div>
                                    </div>
                                </div>


                            </div>
                            <div class="tab-pane" id="video" role="tabpanel">
                                <div class="m-portlet m-portlet--tabs m-portlet--brand m-portlet--head-solid-bg m-portlet--head-sm m-portlet--bordered">
                                    <div class="m-portlet__head">
                                        <div class="m-portlet__head-caption">
                                            <div class="m-portlet__head-title">
                                                <h3 class="m-portlet__head-text">
                                                    <i class="fa fa-cogs"></i>
                                                    视频监控
                                                </h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="m-portlet__body">
                                        <div class="portlet-body">
                                                <video style="max-width:1200px;width:95%;height:500px;" id="myPlayer" poster="" controls playsInline webkit-playsinline >
                                                    <source src="http://hls.open.ys7.com/openlive/6e84099c7983453998b2a3c3c5c95ac1.hd.m3u8" type="application/x-mpegURL" />
                                                </video>

                                        </div>
                                    </div>
                                </div>


                            </div>
                            <div class="tab-pane" id="runData" role="tabpanel">
                                <div class="m-portlet m-portlet--tabs m-portlet--brand m-portlet--head-solid-bg m-portlet--head-sm m-portlet--bordered">
                                    <div class="m-portlet__head">
                                        <div class="m-portlet__head-caption">
                                            <div class="m-portlet__head-title">
                                                <h3 class="m-portlet__head-text">
                                                    <i class="fa fa-cogs"></i>
                                                    运行数据
                                                </h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="m-portlet__body">
                                        <div class="portlet-body">
                                            <div class="row static-info">
                                                <!-- ngRepeat: vars in varsArr0 --><div class="col-md-6 ng-scope" ng-repeat="vars in varsArr0">
                                                <div class="col-md-6" style="margin-bottom:15px;" ng-bind-html="htmlStr2">
                                                    <button type="button" class="btn btn-success">
                                                        生产速度
                                                    </button>
                                                 </div>
                                                <div class="col-md-6 value ng-binding" style="margin-bottom:15px;" ng-bind-html="htmlStr"><a class="btn green btn-info">60000个</a></div>
                                            </div><!-- end ngRepeat: vars in varsArr0 --><div class="col-md-6 ng-scope" ng-repeat="vars in varsArr0">
                                                <div class="col-md-6" style="margin-bottom:15px;" ng-bind-html="htmlStr2">
                                                    <button type="button" class="btn btn-success">
                                                        设定速度
                                                    </button></div>
                                                <div class="col-md-6 value ng-binding" style="margin-bottom:15px;" ng-bind-html="htmlStr"><a class="btn green btn-info">60000个</a></div>
                                            </div><!-- end ngRepeat: vars in varsArr0 --><div class="col-md-6 ng-scope" ng-repeat="vars in varsArr0">
                                                <div class="col-md-6" style="margin-bottom:15px;" ng-bind-html="htmlStr2">
                                                    <button type="button" class="btn btn-success">
                                                    消耗瓶胚
                                                </button></div>
                                                <div class="col-md-6 value ng-binding" style="margin-bottom:15px;" ng-bind-html="htmlStr"><a class="btn green btn-info">429760个</a></div>
                                            </div><!-- end ngRepeat: vars in varsArr0 --><div class="col-md-6 ng-scope" ng-repeat="vars in varsArr0">
                                                <div class="col-md-6" style="margin-bottom:15px;" ng-bind-html="htmlStr2">
                                                    <button type="button" class="btn btn-success">
                                                        合格瓶数
                                                    </button></div>
                                                <div class="col-md-6 value ng-binding" style="margin-bottom:15px;" ng-bind-html="htmlStr"><a class="btn green btn-info">428458个</a></div>
                                            </div><!-- end ngRepeat: vars in varsArr0 --><div class="col-md-6 ng-scope" ng-repeat="vars in varsArr0">
                                                <div class="col-md-6" style="margin-bottom:15px;" ng-bind-html="htmlStr2">
                                                    <button type="button" class="btn btn-success">
                                                        合格品率
                                                    </button></div>
                                                <div class="col-md-6 value ng-binding" style="margin-bottom:15px;" ng-bind-html="htmlStr"><a class="btn green btn-info">99.94%</a></div>
                                            </div><!-- end ngRepeat: vars in varsArr0 --><div class="col-md-6 ng-scope" ng-repeat="vars in varsArr0">
                                                <div class="col-md-6" style="margin-bottom:15px;" ng-bind-html="htmlStr2">
                                                    <button type="button" class="btn btn-success">
                                                        主机运转时间
                                                    </button></div>
                                                <div class="col-md-6 value ng-binding" style="margin-bottom:15px;" ng-bind-html="htmlStr"><a class="btn green btn-info">1分</a></div>
                                            </div><!-- end ngRepeat: vars in varsArr0 -->
                                            </div>


                                        </div>
                                    </div>
                                </div>


                            </div>
                            <div class="tab-pane" id="startStop" role="tabpanel">
                                <div class="m-portlet m-portlet--tabs m-portlet--brand m-portlet--head-solid-bg m-portlet--head-sm m-portlet--bordered">
                                    <div class="m-portlet__head">
                                        <div class="m-portlet__head-caption">
                                            <div class="m-portlet__head-title">
                                                <h3 class="m-portlet__head-text">
                                                    <i class="fa fa-cogs"></i>
                                                    机组启停
                                                </h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="m-portlet__body">
                                            <div class="row static-info">

                                                <button type="button" class="btn btn-outline-primary m-btn m-btn--outline-2x ">
                                                    操作模式
                                                </button>
                                                <!-- ngRepeat: vars in varsArr1 --><div class="col-md-6 ng-scope" ng-repeat="vars in varsArr1">
                                                <div class="col-md-6 text-right name ng-binding" style="margin-bottom:15px;" ng-bind-html="htmlStr2"><a class="btn blue btn-outline">操作模式</a></div>
                                                <div class="col-md-6 value ng-binding" style="margin-bottom:15px;" ng-bind-html="htmlStr"><a class="btn green btn-info">0</a></div>
                                            </div><!-- end ngRepeat: vars in varsArr1 --><div class="col-md-6 ng-scope" ng-repeat="vars in varsArr1">
                                                <div class="col-md-6 text-right name ng-binding" style="margin-bottom:15px;" ng-bind-html="htmlStr2"><a class="btn blue btn-outline">停机中</a></div>
                                                <div class="col-md-6 value ng-binding" style="margin-bottom:15px;" ng-bind-html="htmlStr"><a class="btn green btn-info">0</a></div>
                                            </div><!-- end ngRepeat: vars in varsArr1 --><div class="col-md-6 ng-scope" ng-repeat="vars in varsArr1">
                                                <div class="col-md-6 text-right name ng-binding" style="margin-bottom:15px;" ng-bind-html="htmlStr2"><a class="btn blue btn-outline">班次开始</a></div>
                                                <div class="col-md-6 value ng-binding" style="margin-bottom:15px;" ng-bind-html="htmlStr"><a class="btn green btn-info">1</a></div>
                                            </div><!-- end ngRepeat: vars in varsArr1 --><div class="col-md-6 ng-scope" ng-repeat="vars in varsArr1">
                                                <div class="col-md-6 text-right name ng-binding" style="margin-bottom:15px;" ng-bind-html="htmlStr2"><a class="btn blue btn-outline">班次结束</a></div>
                                                <div class="col-md-6 value ng-binding" style="margin-bottom:15px;" ng-bind-html="htmlStr"><a class="btn green btn-info">0</a></div>
                                            </div><!-- end ngRepeat: vars in varsArr1 -->
                                            </div>

                                    </div>
                                </div>


                            </div>
                            <div class="tab-pane" id="histroyData" role="tabpanel">
                                <div class="m-portlet m-portlet--tabs m-portlet--brand m-portlet--head-solid-bg m-portlet--head-sm m-portlet--bordered">
                                    <div class="m-portlet__head">
                                        <div class="m-portlet__head-caption">
                                            <div class="m-portlet__head-title">
                                                <h3 class="m-portlet__head-text">
                                                    <i class="fa fa-cogs"></i>
                                                    历史数据
                                                </h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="m-portlet__body">
                                        <div class="margin-top-10 margin-bottom-10 text-center row">
                                            <!-- <select class="selectAnalogTab" ng-model="selectedlinetab"  ng-options="var.name for var in linevarstab" ng-change="checktab()"></select> -->
                                            <div class="col-md-3 col-md-offset-1 margin-top-10">
                                                <div class="ui-select-container ui-select-bootstrap dropdown ng-valid ng-touched" ng-class="{open: $select.open}" ng-model="selectedlinetab" theme="bootstrap"><div class="ui-select-match" ng-hide="$select.open" ng-disabled="$select.disabled" ng-class="{'btn-default-focus':$select.focus}"><span tabindex="-1" class="btn btn-default form-control ui-select-toggle" aria-label="Select box activate" ng-disabled="$select.disabled" ng-click="$select.activate()" style="outline: 0;"><span ng-show="$select.isEmpty()" class="ui-select-placeholder text-muted ng-binding ng-hide"></span> <span ng-hide="$select.isEmpty()" class="ui-select-match-text pull-left" ng-class="{'ui-select-allow-clear': $select.allowClear &amp;&amp; !$select.isEmpty()}" ng-transclude=""><span class="ng-binding ng-scope">生产速度</span></span> <i class="caret pull-right" ng-click="$select.toggle($event)"></i> <a ng-show="$select.allowClear &amp;&amp; !$select.isEmpty()" aria-label="Select box clear" style="margin-right: 10px" ng-click="$select.clear($event)" class="btn btn-xs btn-link pull-right ng-hide"><i class="glyphicon glyphicon-remove" aria-hidden="true"></i></a></span></div><input type="text" autocomplete="false" tabindex="-1" aria-expanded="true" aria-label="Select box" aria-owns="ui-select-choices-0" aria-activedescendant="ui-select-choices-row-0-0" class="form-control ui-select-search ng-pristine ng-valid ng-touched ng-hide" placeholder="" ng-model="$select.search" ng-show="$select.searchEnabled &amp;&amp; $select.open"><ul class="ui-select-choices ui-select-choices-content ui-select-dropdown dropdown-menu ng-scope" role="listbox" ng-show="$select.items.length > 0" repeat="var in linevarstab" style="opacity: 1;"><li class="ui-select-choices-group" id="ui-select-choices-0"><div class="divider ng-hide" ng-show="$select.isGrouped &amp;&amp; $index > 0"></div><div ng-show="$select.isGrouped" class="ui-select-choices-group-label dropdown-header ng-binding ng-hide" ng-bind="$group.name"></div><!-- ngRepeat: var in $select.items --><!-- ngIf: $select.open --><!-- end ngRepeat: var in $select.items --><!-- ngIf: $select.open --><!-- end ngRepeat: var in $select.items --><!-- ngIf: $select.open --><!-- end ngRepeat: var in $select.items --><!-- ngIf: $select.open --><!-- end ngRepeat: var in $select.items --><!-- ngIf: $select.open --><!-- end ngRepeat: var in $select.items --><!-- ngIf: $select.open --><!-- end ngRepeat: var in $select.items --></li></ul><ui-select-single></ui-select-single><input ng-disabled="$select.disabled" class="ui-select-focusser ui-select-offscreen ng-scope" type="text" id="focusser-0" aria-label="Select box focus" aria-haspopup="true" role="button"></div>
                                            </div>
                                            <div class="col-md-8 margin-top-10">
                                                <div class="input-group input-large input-daterange">
                                                    <input type="text" class="form-control date start_date col-md-3" style="width: 150px" placeholder="选择开始时间">
                                                    <span class="input-group-addon"> ~ </span>
                                                    <input type="text" class="form-control date end_date col-md-3" style="width: 150px" placeholder="选择结束时间">
                                                    <span class="input-group-btn">
                                                                    <button class="btn default" type="button" ng-click="setCurvetime()">
                                                                        设置
                                                                    </button>
                                                                </span>
                                                </div>

                                            </div>
                                            <div class="col-md-12">
                                                <div class="margin-top-10 margin-bottom-20 text-center">
                                                    <div class="clearfix">
                                                        <input type="button" class="btn blue btn-outline" ng-click="setHistoryTime(1)" value="最近10分钟">
                                                        <input type="button" class="btn red btn-outline" ng-click="setHistoryTime(2)" value="最近24小时">
                                                        <input type="button" class="btn green btn-outline" ng-click="setHistoryTime(3)" value="最近7天">
                                                        <input type="button" class="btn purple btn-outline" ng-click="setFreeTime()" ng-value="curve.setTime" value="自定义时间">
                                                    </div>
                                                </div>
                                            </div>

                                        </div>


                                    </div>
                                    </div>
                                </div>


                            </div>

                        </div>
                    </div>
                </div>
            </div>  <!-- nav end -->
        </div>

    </div>
    </div>  <!-- end right content-->
  </div>  <!--end row -->
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
                    var zTree = $.fn.zTree.getZTreeObj("treeCity");
                    if (treeNode.isParent) {
                        zTree.expandNode(treeNode);
                        return false;
                    } else {
                        var selId=treeNode.id;
                        ajaxGet('${basePath}/manage/equipment/'+selId, function (responseData) {
                            if (responseData) {
                                var data=responseData.data;
                                setText('serialNumber',data.serialNumber);
                                setText('equipmentId',data.equipmentId);
                                setText('equipname',data.name);
                                setText('number',data.number);
                                setText('factoryDate',data.factoryDate);
                                setText('commissioningDate',changeTimeFormat(data.commissioningDate));
                                setText('warrantyStartDate',changeTimeFormat(data.warrantyStartDate));
                                setText('warrantyEndDate',changeTimeFormat(data.warrantyEndDate));
                                isOnline = data.isOnline;
                                if(isOnline==null){
                                    isOnline = false;
                                }
                                setText('isOnline',data.isOnline? '在线':'离线');
                                longitude = data.longitude;
                                latitude =data.latitude;

                                if(data.imagePath ==''|| data.imagePath ==null){
                                    $("#imagesrc").attr("src","../assets/pages/media/works/img7.jpg");
                                }else{
                                    $("#imagesrc").attr("src",'/files/'+data.imagePath);//uploaderUrl
                                }
                            }
                        });
                        return true;
                    }
                }
            }
        };

        $(document).ready(function(){
            var longitude, latitude, isOnline;
            ajaxGet('${basePath}/manage/equipment/city/tree', function (responseData) {
                if (responseData) {
                    var data = responseData.data.provices;
                    var jsonstr = "[]",  jsonTemp, pid=0, online, latitude,longitude, tmpId;
                    var jsonarray = eval('('+jsonstr+')');
                    $.each(data, function (n, value) {  //省
                        tmpId=parseInt(value.code);
                        if(n==0)
                            jsonTemp ={"id":tmpId, "name":value.name,"pId":pid,"online":value.online, "open":true};
                        else
                            jsonTemp ={"id":tmpId, "name":value.name,"pId":pid,"online":value.online};
                        jsonarray.push(jsonTemp);
                        pid=tmpId;
                        $.each(value.children, function (n, value) { //市
                            tmpId=parseInt(value.code);
                            if(n==0)
                                jsonTemp ={"id":tmpId, "name":value.name,"pId":pid,"online":value.online,"latitude":value.latitude,"longitude":value.longitude, "open":true};
                            else
                                jsonTemp ={"id":tmpId, "name":value.name,"pId":pid,"online":value.online,"latitude":value.latitude,"longitude":value.longitude};
                            jsonarray.push(jsonTemp);
                            pid=tmpId;
                            online = value.online;
                            latitude=value.latitude;
                            longitude = value.longitude;
                            $.each(value.children, function (n, value) { //data
                                jsonTemp ={"id":value.id, "name":value.name,"pId":pid,"online":online,"latitude":latitude,"longitude":longitude};
                                jsonarray.push(jsonTemp);
                            });
                        });
                        pid= 0;
                    });
                    var t = $("#treeCity");
                    t = $.fn.zTree.init(t, setting, jsonarray);
                }
            });


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