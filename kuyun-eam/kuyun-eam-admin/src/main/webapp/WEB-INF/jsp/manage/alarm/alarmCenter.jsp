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
                        <span class="m-nav__link-text">
												报警中心
						</span>
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
                <div class="m-portlet m-portlet--tabs m-portlet--head-solid-bg m-portlet--head-sm">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-tools" id="navContainer">
                            <ul class="nav nav-tabs m-tabs m-tabs-line  m-tabs-line--left" role="tablist">
                                <li class="nav-item m-tabs__item">
                                    <a id="tab_currAlarm" class="nav-link m-tabs__link active show" data-toggle="tab" href="#currAlarm" role="tab" aria-selected="true">
                                        活跃报警
                                    </a>
                                </li>
                                <li class="nav-item m-tabs__item">
                                    <a id="tab_historyAlarm" class="nav-link m-tabs__link" data-toggle="tab" href="#historyAlarm" role="tab" aria-selected="false">
                                        历史报警
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="m-portlet__body">
                        <div class="tab-content">
                            <div class="tab-pane active show" id="currAlarm" role="tabpanel">
                                <div class="row">
                                    <div class="col-md-2 col-md-offset-1 margin-top-10">
                                        <select id="equipments1" name="equipments" style="width: 100%">
                                            <option value="">所有</option>
                                        </select>
                                    </div>
                                    <div class="col-md-8 margin-top-10">
                                        <div class="input-group input-large input-daterange">
                                            <input type="text" id="startDate1" class="start_date input-group date form-control m-input col-md-3" readonly style="width: 150px" placeholder="选择开始时间">
                                            <span class="input-group-addon">  ~ </span>
                                            <input type="text" id="endDate1" class="end_date input-group date form-control m-input col-md-3" readonly style="width: 150px" placeholder="选择结束时间">
                                            <span class="input-group-btn">
                                                        <button class="btn default" type="button" onclick="searchCurrAlarm()">
                                                            查询
                                                        </button>
                                                    </span>
                                        </div>
                                    </div>
                                </div>
                                    <div id="toolbar">
                                        <div>

                                            <div class="m-separator m-separator--dashed d-xl-none"></div>
                                        </div>
                                    </div>
                                    <table id="currAlarmTable" data-toolbar="#toolbar"></table>
                            </div>

                            <div class="tab-pane" id="historyAlarm" role="tabpanel">

                                <div class="row">
                                    <div class="col-md-2 col-md-offset-1 margin-top-10">
                                        <select id="searchType" name="searchType" style="width: 100%">
                                            <option value="">所有</option>
                                            <option value="ANU|ANA">活跃</option>
                                            <option value="CNU|CNA">已消除</option>
                                        </select>
                                    </div>
                                    <div class="col-md-2 col-md-offset-1 margin-top-10">
                                        <select id="equipments" name="equipments" style="width: 100%">
                                            <option value="">所有</option>
                                        </select>
                                    </div>
                                    <div class="col-md-8 margin-top-10">
                                        <div class="input-group input-large input-daterange">
                                            <input type="text" id="startDate" class="start_date input-group date form-control m-input col-md-3" readonly style="width: 150px" placeholder="选择开始时间">
                                            <span class="input-group-addon">  ~ </span>
                                            <input type="text" id="endDate" class="end_date input-group date form-control m-input col-md-3" readonly style="width: 150px" placeholder="选择结束时间">
                                            <span class="input-group-btn">
                                                        <button class="btn default" type="button" onclick="searchHistory()">
                                                            查询
                                                        </button>
                                                    </span>
                                        </div>
                                    </div>
                                </div>

                                    <div id="toolbar1">
                                        <div>
                                            <div class="m-separator m-separator--dashed d-xl-none"></div>
                                        </div>
                                    </div>
                                    <table id="historyAlarmTable" data-toolbar="#toolbar"></table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>




</content>


<pageResources>
    <link href="${basePath}/resources/kuyun-admin/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css" rel="stylesheet"/>
    <script src="${basePath}/resources/kuyun-admin/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>

    <script>

        var alarmUrl ='${basePath}/manage/alarm/record/list';
        var historyUrl='${basePath}/manage/alarm/record/history/list';
        var alarmTable = $('#currAlarmTable');
        var histAlarmTable = $('#historyAlarmTable');
        var selectSearchType=null, selectEquipment1=null ,selectEquipment=null;
        $(function() {
            $('#equipments1').select2();
            $('#equipments').select2();
            $('#searchType').select2();
            alarmTable.bootstrapTable({
                url: alarmUrl,
                striped: true,
                search: true,
                searchAlign: 'left',
                toolbarAlign: 'right',
                minimumCountColumns: 2,
                clickToSelect: true,
                detailView: true,
                detailFormatter: 'detailFormatter',
                pagination: true,
                paginationLoop: false,
                sidePagination: 'server',
                silentSort: false,
                smartDisplay: false,
                escape: true,
                searchOnEnterKey: true,
                maintainSelected: true,
                idField: 'warehouseId',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'alarmType', title: '报警设备', sortable: true, align: 'center'},
                    {field: 'duration', title: '报警时间'},
                    {field: 'alarmClearValue', title: '报警参数'},
                    {field: 'upperBound', title: '报警值'},
                    {field: 'alarmTarget', title: '内容'}
                ]
            });

            histAlarmTable.bootstrapTable({
                url: historyUrl,
                striped: true,
                search: true,
                searchAlign: 'left',
                toolbarAlign: 'right',
                minimumCountColumns: 2,
                clickToSelect: true,
                detailView: true,
                detailFormatter: 'detailFormatter',
                pagination: true,
                paginationLoop: false,
                sidePagination: 'server',
                silentSort: false,
                smartDisplay: false,
                escape: true,
                searchOnEnterKey: true,
                maintainSelected: true,
                idField: 'warehouseId',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'alarmType', title: '报警设备', sortable: true, align: 'center'},
                    {field: 'duration', title: '报警时间'},
                    {field: 'alarmClearValue', title: '报警参数'},
                    {field: 'upperBound', title: '报警值'},
                    {field: 'alarmTarget', title: '内容'}
                ]
            });

            $('.start_date','.end_date').datetimepicker({
                language: 'zh-CN',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                startView: 2,
                forceParse: 0,
                // minView:'day',
                format: 'yyyy/mm/dd hh:ii',
                todayHighlight: true,
            }).on('hide', function (e) {
            });

            ajaxGet('${basePath}/manage/equipment/list', function (responseData) {
                if (responseData) {
                    var data = responseData.rows;
                    var items=[];
                    $.each(data,function(i, val) {
                        items.push({'VALUEFIELD':val.equipmentId,'DESCFIELD': val.name});
                    });

                    addOptionToHtmlSelect(selectEquipment1, "equipments1", items, "","所有");
                    addOptionToHtmlSelect(selectEquipment, "equipments", items, "","所有");
                }
            });
        });

        function searchCurrAlarm(){
            selectEquipment1 = $('#equipments1').val();
            var startDate = $('#startDate1').val();
            var endDate = $('#endDate1').val();
            startDate = (startDate == '')?null:startDate;
            endDate = (endDate == '')?null:endDate;
            var checkValidate=true;
            if(startDate && endDate ) {
                var startDate1 = new Date(startDate);
                var endDate1 = new Date(endDate);
                if(Date.parse(endDate1)-Date.parse(startDate1)<=0){
                    swWarn( '开始时间必须早于结束时间');
                    checkValidate=false;
                }
            }

            if(checkValidate) {
                var opt = {
                    url: alarmUrl,
                    silent: true,
                    query:{
                        equipmentId:selectEquipment,
                        startDate: startDate,
                        endDate: endDate
                    }
                };
                alarmTable.bootstrapTable('refresh', opt);
            }
        }

        function searchHistory(){
            selectSearchType = $('#searchType').val();
            selectEquipment = $('#equipments').val();
            var startDate = $('#startDate').val();
            var endDate = $('#endDate').val();
            var checkValidate=true;
            if(startDate != "" && endDate !="") {
                var startDate1 = new Date(startDate);
                var endDate1 = new Date(endDate);
                if(Date.parse(endDate1)-Date.parse(startDate1)<=0){
                    swWarn( '开始时间必须早于结束时间');
                    checkValidate=false;
                }
            }

            if(checkValidate) {
                var opt = {
                    url: historyUrl,
                    silent: true,
                    query:{
                        alarmType : selectSearchType,
                        equipmentId:selectEquipment,
                        startDate: startDate,
                        endDate: endDate
                    }
                };
                histAlarmTable.bootstrapTable('refresh', opt);
            }
        }

 </script>



</pageResources>


</body>
</html>