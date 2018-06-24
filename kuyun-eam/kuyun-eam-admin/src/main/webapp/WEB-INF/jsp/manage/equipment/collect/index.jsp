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
                            <i class="m-nav__link-icon la la-home"></i>
                        </a>
                    </li>
                    <li class="m-nav__separator">
                        -
                    </li>
                    <li class="m-nav__item">
                        <a href="" class="m-nav__link">
											<span class="m-nav__link-text">
												设备列表
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
            <div id="toolbar">
                <div>
                    <shiro:hasPermission name="eam:equipment:update"><a class="waves-effect waves-button" href="javascript:;" onclick="startAction()"><i class="zmdi zmdi-plus"></i> 启动</a></shiro:hasPermission>
                    <shiro:hasPermission name="eam:equipment:update"><a class="waves-effect waves-button" href="javascript:;" onclick="stopAction()"><i class="zmdi zmdi-close"></i> 停止</a></shiro:hasPermission>

                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                </div>
            </div>

            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>


</content>


<pageResources>

    <script>

        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/equipment/list',
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
                idField: 'equipmentId',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'name', title: '设备名称', sortable: true, align: 'center'},
                    {field: 'number', title: '设备编号'},
                    {field: 'maintenancePeriod', title: '维保周期'},
                    {field: 'collectStatus', title: '采集状态'},
                    {field: 'action', width: 100, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });

            setSearchPlaceholder('设备名称');
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:equipment:update"><a class="update" href="javascript:;" onclick="startAction()" data-toggle="tooltip" title="start">启动</a></shiro:hasPermission>　',
                '<shiro:hasPermission name="eam:equipment:update"><a class="delete" href="javascript:;" onclick="stopAction()" data-toggle="tooltip" title="stop">停止</a></shiro:hasPermission>'
            ].join('');
        }


        function startAction() {
            var rows = $table.bootstrapTable('getSelections');
            if (rows.length == 0) {
                swWarn("请至少选择一条记录");
            } else {
                var ids = new Array();
                for (var i in rows) {
                    ids.push(rows[i].equipmentId);
                }
                var json = { "ids" : ids.join("-")};

                $.ajax({
                    type: 'post',
                    url: '${basePath}/manage/equipment/collect/start/',
                    data: JSON.stringify(json),
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: function(result) {
                        $table.bootstrapTable('refresh');
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        swWarn(textStatus);
                    }
                });
            }
        }
        function stopAction() {
            var rows = $table.bootstrapTable('getSelections');
            if (rows.length == 0) {
                swWarn("请至少选择一条记录");
            } else {
                var ids = new Array();
                for (var i in rows) {
                    ids.push(rows[i].equipmentId);
                }
                var json = { "ids" : ids.join("::")};

                $.ajax({
                    type: 'post',
                    url: '${basePath}/manage/equipment/collect/stop/',
                    data: JSON.stringify(json),
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: function(result) {
                        $table.bootstrapTable('refresh');
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        swWarn(textStatus);
                    }
                });
            }
        }

    </script>



</pageResources>


</body>
</html>