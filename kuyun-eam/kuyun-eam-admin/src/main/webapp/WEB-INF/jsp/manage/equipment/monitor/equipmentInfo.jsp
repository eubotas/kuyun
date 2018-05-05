﻿﻿<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<link href="${basePath}/resources/kuyun-admin/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css" rel="stylesheet"/>
<script src="${basePath}/resources/kuyun-admin/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
<script src="${basePath}/resources/kuyun-admin/plugins/ezuikit/ezuikit.js"></script>
<style>
    .btnRed {
        border-color: #e7505a;
        color: #e7505a !important;
        background: none;}

    .btnBlue {
        border-color: #3598dc;
        color: #3598dc !important;
        background: none;}

    .btnGreen {
        border-color: #32c5d2;
        color: #32c5d2 !important;
        background: none;}

    .btnPurple {
        border-color: #8E44AD;
        color: #8E44AD !important;
        background: none;}
</style>
<div class="nav-tabs-horizontal" data-approve="nav-tabs">
    <div class="m-portlet m-portlet--tabs m-portlet--head-solid-bg m-portlet--head-sm">
        <div class="m-portlet__head">
            <div class="m-portlet__head-tools" id="navContainer">
                <ul class="nav nav-tabs m-tabs m-tabs-line  m-tabs-line--left" role="tablist">
                    <li class="nav-item m-tabs__item">
                        <a id="tab_equipment" class="nav-link m-tabs__link active show" data-toggle="tab" href="#equipment" role="tab" aria-selected="true">
                            设备概况
                        </a>
                    </li>
                    <li class="nav-item m-tabs__item">
                        <a id="tab_run" class="nav-link m-tabs__link" data-toggle="tab" href="#run" role="tab" aria-selected="false">
                            运行监控
                        </a>
                    </li>
                    <li class="nav-item m-tabs__item">
                        <a id="tab_video" class="nav-link m-tabs__link" data-toggle="tab" href="#video" role="tab" aria-selected="false">
                            视频监控
                        </a>
                    </li>
                    <li class="nav-item m-tabs__item" id="nav_runData" style="display:none">
                        <a id="tab_runData" class="nav-link m-tabs__link" data-toggle="tab" href="#runData" role="tab" aria-selected="false">
                            运行数据
                        </a>
                    </li>
                    <li class="nav-item m-tabs__item" id="nav_startStop" style="display:none">
                        <a id="tab_startStop" class="nav-link m-tabs__link" data-toggle="tab" href="#startStop" role="tab" aria-selected="false">
                            机组启停
                        </a>
                    </li>
                    <li class="nav-item m-tabs__item">
                        <a id="tab_histroyData" class="nav-link m-tabs__link" data-toggle="tab" href="#histroyData" role="tab" aria-selected="false">
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
                                                <div class="portlet-body" id="eqLeft">
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
                                                            <a href="javascript:change2Map()">
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
                                            <div class="portlet-body" id="eqRight" style="min-height:100px;">
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
                                        <span id="gg">运行监控</span>
                                    </h3>
                                </div>
                            </div>
                        </div>
                        <div class="m-portlet__body">
                            <div class="row">
                                <div id="runDataList" class="isOnline row">
                                </div>
                                <div class="tab-content notOnline">
                                采集器中断，请确认您的设备连接和设置是否正常，并在资产管理->设备信息管理 中开启设备
                                </div>
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
                                <div class="row" style="text-align: center;">
                                <video style="max-width:1200px;width:95%;height:300px;" id="myPlayer" poster="" controls playsInline webkit-playsinline >
                                    <source src="http://hls.open.ys7.com/openlive/6e84099c7983453998b2a3c3c5c95ac1.hd.m3u8" type="application/x-mpegURL" />
                                </video>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
                <div class="tab-pane" id="runData" role="tabpanel" >
                    <div class="m-portlet m-portlet--tabs m-portlet--brand m-portlet--head-solid-bg m-portlet--head-sm m-portlet--bordered">
                        <div class="m-portlet__head">
                            <div class="m-portlet__head-caption">
                                <div class="m-portlet__head-title">
                                    <h3 class="m-portlet__head-text">
                                        <i class="fa fa-cogs"></i>
                                        <span id="groupname0">运行数据</span>
                                    </h3>
                                </div>
                            </div>
                        </div>
                        <div class="m-portlet__body">
                            <div class="portlet-body">
                                <div class="row static-info" id="groupname0List">
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
                                        <span id="groupname1">机组启停</span>

                                    </h3>
                                </div>
                            </div>
                        </div>
                        <div class="m-portlet__body">
                            <div class="row static-info" id="groupname1List">

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
                                        <span id="lineLabel">历史数据</span>
                                    </h3>

                                </div>
                            </div>
                            <div class="m-portlet__head-tools">
                                <ul class="nav nav-pills nav-pills--brand m-nav-pills--align-right m-nav-pills--btn-pill m-nav-pills--btn-sm" role="tablist">
                                    <button class="btn btn-circle btn-icon-only btn-default"  onclick="changemode('line')" uib-tooltip="显示曲线">
                                        <i class="fa fa-line-chart"></i>

                                    </button>
                                    <button class="btn btn-circle btn-icon-only btn-default"  onclick="changemode('table')" uib-tooltip="显示表格">
                                        <i class="fa fa-table"></i>
                                    </button>
                                </ul>
                            </div>
                        </div>
                        <div class="m-portlet__body">
                            <div class="text-center row">
                                <div class="row">
                                <div class="col-md-3 col-md-offset-1 margin-top-10">
                                    <select id="equipmentModelType" name="equipmentModelType" onchange="onChangeEquipmentModelType(this)" style="width: 100%; min-width:80px;"></select>
                                </div>
                                <div class="col-md-9 margin-top-10">
                                    <div class="input-group input-large input-daterange">
                                        <input type="text" class="start_date input-group date form-control m-input col-md-4" readonly style="width: 150px" placeholder="选择开始时间">
                                        <span class="input-group-addon"> ~ </span>
                                        <input type="text" class="end_date input-group date form-control m-input col-md-4" readonly style="width: 150px" placeholder="选择结束时间">
                                        <span class="input-group-btn" style="padding-left: 15px">
                                                                    <button class="btn default" type="button" onclick="setCurvetime()">
                                                                        设置
                                                                    </button>
                                                                </span>
                                    </div>
                                </div>
                                </div>
                                <div class="row"  style="margin-top:10px;">
                                    <div class="margin-top-10 margin-bottom-20 text-center">
                                        <div class="clearfix">
                                            <input type="button" class="btn btnBlue btn-outline-primary m-btn m-btn--outline-2x" onclick="setHistoryTime(1)" value="最近10分钟">
                                            <input type="button" class="btn btnRed btn-outline-primary m-btn m-btn--outline-2x" onclick="setHistoryTime(2)" value="最近24小时">
                                            <input type="button" class="btn btnGreen btn-outline-primary m-btn m-btn--outline-2x" onclick="setHistoryTime(3)" value="最近7天">
                                            <input type="button" id="curveTimeBtn" class="btn btnPurple btn-outline-primary m-btn m-btn--outline-2x" onclick="setFreeTime()"  value="自定义时间">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div id="echarts_line" style="height: 500px" ></div>
                            <div id="historyTableParamsDiv" style="margin-top: 5px;display:none;" >
                            <table id="historyTableParams"  class="table table-striped table-bordered table-hover table-checkable order-column" >
                            </table>
                            </div>
                        </div>
                    </div>
                </div>


            </div>

        </div>
    </div>
</div>  <!-- nav end -->
<script src="${basePath}/resources/kuyun-admin/plugins/echarts/echarts.min.js"></script>
<script src="${basePath}/resources/metronic-admin/assets/kuyun/deviceMonitor.js"></script>

<script>
    $(document).ready(function()
    {
        $('#equipmentModelType').select2({minimumResultsForSearch: -1});

    });

    function callbackShowEquip(treeNode){
        selectedequipid=treeNode.id;
        showEquipmentInfo(selectedequipid);
        getDataModel(selectedequipid);
    }

    function showEquipmentInfo(eid){
        ajaxGet('${basePath}/manage/equipment/'+selectedequipid, function (responseData) {
            if (responseData.code ==1) {
                var data=responseData.data;

                $('#tab_equipment').addClass('active').siblings().removeClass('active');
                $('.tab-content').find('#equipment').addClass('active').siblings().removeClass('active');
                equipmentId = data.equipmentId;
                equipmentModelId = data.equipmentModelId;
                setText('serialNumber',data.serialNumber);
                setText('equipmentId',data.equipmentId);
                setText('equipname',data.name);
                setText('number',data.number);
                setText('factoryDate',changeTimeFormat(data.factoryDate));
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
                    $("#imagesrc").attr("src","/resources/kuyun-admin/images/logo.png");
                }else{
                    $("#imagesrc").attr("src",uploadServerPath+'/files/'+data.imagePath);
                }
                onlineHideShow(isOnline);
            }
            $('#eqRight').height($('#eqLeft').height());
        });

    }

    function getDataModel(equipid){
        ajaxGet('${basePath}/manage/equipment/sensor/data/'+selectedequipid, function (responseData) {
                if(responseData.code == 1) {
                    var analogflag=0,digitalflag=0;
                    var dataArr=responseData.data;
                    if(dataArr!=null && dataArr.length>0){
                        $.each(dataArr,function(i, val) {
                                if(val.type == 'analog'){
                                    analogflag = analogflag+1;
                                    showAnalogTab = true;
                                    setText('groupname0',val.groupName);
                                    varsArr0=val.vars;
                                    $('#groupname0List').html(showRunDataListHtml(varsArr0));
                                    showHistorySelect2(varsArr0);
                                    formatEchartValue(varsArr0);
                                    linevarstab = [];
                                    for(var j=0;j<val.vars.length;j++){
                                        if(val.vars[j].showchart == true){
                                            linevarstab.push(val.vars[j]);
                                        }
                                    }
                                    if(linevarstab.length>0){
                                        selectedlinetab = linevarstab[0];
                                        lineTab=selectedlinetab.name;
                                        lineLabel=lineType+lineTab;
                                    }
                                }
                                if(val.type == 'digital'){
                                    digitalflag = digitalflag+1;
                                    showDigitalTab = true;
                                    setText('groupname1',val.groupName);
                                    varsArr1=val.vars;
                                    $('#groupname1List').html(showRunDataListHtml(varsArr1));
                                    showHistorySelect2(varsArr1);
                                }
                                if(digitalflag == 0){showDigitalTab = false;}
                                if(analogflag == 0){showAnalogTab = false;}
                            }
                        );
                        generateRunHtml();
                        $('#groupname0List, #groupname1List').find(".btn").css("cursor","text");
                    }else if(dataArr!=null && dataArr.length==0){
                        groupname0=null;
                        varsArr0=[];
                        formatEchartValue(varsArr0);
                        linevarstab = [];
                        groupname1=null;
                        varsArr1=[];
                        showDigitalTab = false;
                        showAnalogTab = false;
                    }

                    showId('nav_runData',showAnalogTab);
                    showId('nav_startStop',showDigitalTab);
                    onlineHideShow(isOnline);
                }
            });
    }

    function getDataModelAndValues(equipid) {
        ajaxGet('${basePath}/manage/equipment/sensor/data/'+selectedequipid, function (responseData) {
            if(responseData.code == 1) {
                    var dataArr=responseData.data;
                    if(dataArr!=null && dataArr.length>0){
                        $.each(dataArr,function(i, val) {
                                if(val.type == 'analog'){
                                    groupname0=val.groupName;
                                    varsArr0=val.vars;
                                    formatEchartValue(varsArr0);
                                }
                                if(val.type == 'digital'){
                                    groupname1=val.groupName;
                                    varsArr1=val.vars;
                                }
                            });
                        onlineHideShow(isOnline);
                    }
                }
            });
    }


</script>