﻿﻿<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

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
                                <video style="max-width:1200px;width:95%;height:300px;" id="myPlayer" poster="" controls playsInline webkit-playsinline >
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
                                        <span id="typeName">运行数据</span>
                                    </h3>
                                </div>
                            </div>
                        </div>
                        <div class="m-portlet__body">
                            <div class="portlet-body">
                                <div class="row static-info" id="typeList">

                                    <div class="col-md-2" style="margin-bottom:15px;">
                                        <button type="button" class="btn btn-outline-primary m-btn m-btn--outline-2x">
                                            生产速度
                                        </button>
                                    </div>
                                    <div class="col-md-4 value" style="margin-bottom:15px;" ><a class="btn green btn-info">60000个</a></div>

                                    <div class="col-md-2" style="margin-bottom:15px;">
                                        <button type="button" class="btn btn-outline-primary m-btn m-btn--outline-2x" id="itenBtn">
                                            生产速度
                                        </button>
                                    </div>
                                    <div class="col-md-4 value" style="margin-bottom:15px;" ><a class="btn green btn-info" id="ItemVal">60000个</a></div>


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

                                <div class="col-md-2 text-right name" style="margin-bottom:15px;" >
                                    <button type="button" class="btn btn-outline-primary m-btn m-btn--outline-2x ">
                                        操作模式
                                    </button></div>
                                <div class="col-md-4 value" style="margin-bottom:15px;"><a class="btn green btn-info">0</a></div>

                                <div class="col-md-2 text-right name" style="margin-bottom:15px;" >
                                    <button type="button" class="btn btn-outline-primary m-btn m-btn--outline-2x ">
                                        操作模式
                                    </button></div>
                                <div class="col-md-4 value" style="margin-bottom:15px;"><a class="btn green btn-info">0</a></div>


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
                                    <select class="form-control m-select2 select2-hidden-accessible" id="m_select2_1" name="param" data-select2-id="m_select2_1" tabindex="-1" aria-hidden="true">
                                        <option value="AK" data-select2-id="2">
                                            温度
                                        </option>
                                        <option value="HI" data-select2-id="148">
                                            湿度
                                        </option>
                                        <option value="HI" data-select2-id="148">
                                            光照度
                                        </option>
                                    </select>
                                </div>
                                <div class="col-md-8 margin-top-10">
                                    <div class="input-group input-large input-daterange">

                                        <input type="text" class="form-control input-group date col-md-3" style="width: 150px" placeholder="选择开始时间">
                                        <span class="input-group-addon"> ~ </span>
                                        <input type="text" class="form-control input-group date col-md-3" style="width: 150px" placeholder="选择结束时间">
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
                                            <input type="button" class="btn blue btn-outline" onclick="setHistoryTime(1)" value="最近10分钟">
                                            <input type="button" class="btn red btn-outline" onclick="setHistoryTime(2)" value="最近24小时">
                                            <input type="button" class="btn green btn-outline" onclick="setHistoryTime(3)" value="最近7天">
                                            <input type="button" class="btn purple btn-outline" onclick="setFreeTime()" value="curve.setTime" value="自定义时间">
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


<script>

    function callbackShowEquip(treeNode){
        equipId=treeNode.id;
        showEquipmentInfo(equipId);
    }

    function showEquipmentInfo(eid){
        equipId = eid;
        ajaxGet('${basePath}/manage/equipment/'+equipId, function (responseData) {
            if (responseData.code ==1) {
                var data=responseData.data;
                equipmentId = data.equipmentId;
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

        <%--ajaxGet('${basePath}/manage/equipment/sensor/data/'+equipId, function (responseData) {--%>
        <%--if (responseData.code ==1) {--%>
        <%--var dataArr=responseData.data;--%>
        <%--if(dataArr!=null && dataArr.length>0){--%>
        <%--$.each(dataArr, function(i, val) {--%>
        <%--if(val.type == 'analog'){--%>
        <%--$('#typeName').text(val.groupName);--%>
        <%--showListInfo(val.vars)--%>
        <%--}--%>
        <%--if(val.type == 'digital'){--%>
        <%--$('#typeName').text(val.groupName);--%>
        <%--//list--%>
        <%--}--%>
        <%--}--%>
        <%--);--%>
        <%--}--%>
        <%--}--%>
        <%--});--%>
    }

    function showListInfo(list){
        var html="";
        $.each(list, function(i, val) {
            html= html+'<div class="col-md-2" style="margin-bottom:15px;"><button type="button" class="btn btn-outline-primary m-btn m-btn--outline-2x">'+
                val+ '</button></div>'+
                '<div class="col-md-4 value" style="margin-bottom:15px;" ><a class="btn green btn-info">'+
                val+'</a></div>';
        });
        return html;
    }
</script>