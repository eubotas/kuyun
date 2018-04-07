﻿<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>产线数据点管理</title>
	<jsp:include page="/resources/inc/head.jsp" flush="true"/>
	<link href="${basePath}/resources/kuyun-admin/plugins/bootstrap-editable/css/bootstrap-editable.css" rel="stylesheet"/>
</head>
<body>
<div id="main">
	<div id="toolbar">
		<shiro:hasPermission name="eam:productLine:update"><a class="waves-effect waves-button" href="javascript:;" onclick="batchUpate()"><i class="zmdi zmdi-edit"></i> 批量设置采集频率</a></shiro:hasPermission>

	</div>
	<table id="table"></table>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script src="${basePath}/resources/kuyun-admin/plugins/bootstrap-editable/js/bootstrap-editable.min.js"></script>
<link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
<script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script src="${basePath}/resources/kuyun-admin/plugins/bootstrap-table-1.11.0/extensions/editable/bootstrap-table-editable.min.js"></script>

<script>
    var $table = $('#table');
    $(function() {
        // bootstrap table初始化
        $table.bootstrapTable({
            url: '${basePath}/manage/${productLineId}/variable/list',
            height: getHeight(),
            striped: true,
            search: true,
            showRefresh: true,
            showColumns: true,
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
            idField: 'id',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'ck', checkbox: true},
                {field: 'productLineName', title: '产线'},
                {field: 'equipmentName', title: '设备'},
                {field: 'name', title: '数据点'},
                {field: 'grmPeriod', title: '采集频率', editable: {
                    type: 'text',
                    validate: function (value) {
                        if ($.trim(value) == '') {
                            return '采集频率不能为空!';
                        }
                    }
                }, align: 'center'}
            ],
            onEditableSave: function(field, row, oldValue, $el) {
                // field:修改的欄位
                // row:修改後的資料(JSON Object)
                // oldValue:修改前的值
                // -------------------------------------------------
                // 可用ajax方法去更新資料
                $.ajax({
                    type: "post",
                    url: '${basePath}/manage/${productLineId}/variable/update',
                    data: JSON.stringify(row),
                    contentType:"application/json",
                    dataType: 'JSON',
                    success: function(data, status) {
                        if (status == "success") {
                            alert(data.data);
                        }
                    },
                    error: function() {
                        alert('写入数据失败');
                    },
                    complete: function() {

                    }

                });
                // -------------------------------------------------
            }
        });
    });


</script>

<script>

    var batchUpdateDialog;
    function batchUpate() {
        var rows = $table.bootstrapTable('getSelections');
        if (rows.length == 0) {
            $.confirm({
                title: false,
                content: '请至少选择一条记录！',
                autoClose: 'cancel|3000',
                backgroundDismiss: true,
                buttons: {
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        } else {
            batchUpdateDialog = $.confirm({
//                type: 'red',
//                animationSpeed: 300,
                title: false,
                content: '' +
                '<form action="" class="formName">' +
                '<div class="form-group">' +
                '<label>采集频率</label>' +
                '<input id="period" name="period" type="text" class="name form-control" required />' +
                '</div>' +
                '</form>',
                buttons: {
                    confirm: {
                        text: '确认',
                        btnClass: 'waves-effect waves-button',
                        action: function () {
                            var ids = new Array();
                            for (var i in rows) {
                                ids.push(rows[i].id);
                            }
                            var period = this.$content.find('.name').val();

                            $.ajax({
                                type: 'post',
                                url: '${basePath}/manage/${productLineId}/variable/batchUpdate',
                                data: {period : period, ids: JSON.stringify(ids)},
								success: function(result) {
                                    if (result.code != 1) {
                                        if (result.data instanceof Array) {
                                            $.each(result.data, function(index, value) {
                                                $.confirm({
                                                    theme: 'dark',
                                                    animation: 'rotateX',
                                                    closeAnimation: 'rotateX',
                                                    title: false,
                                                    content: value.errorMsg,
                                                    buttons: {
                                                        confirm: {
                                                            text: '确认',
                                                            btnClass: 'waves-effect waves-button waves-light'
                                                        }
                                                    }
                                                });
                                            });
                                        } else {
                                            $.confirm({
                                                theme: 'dark',
                                                animation: 'rotateX',
                                                closeAnimation: 'rotateX',
                                                title: false,
                                                content: result.data.errorMsg,
                                                buttons: {
                                                    confirm: {
                                                        text: '确认',
                                                        btnClass: 'waves-effect waves-button waves-light'
                                                    }
                                                }
                                            });
                                        }
                                    } else {
                                        batchUpdateDialog.close();
                                        $table.bootstrapTable('refresh');
                                    }
                                },
                                error: function(XMLHttpRequest, textStatus, errorThrown) {
                                    $.confirm({
                                        theme: 'dark',
                                        animation: 'rotateX',
                                        closeAnimation: 'rotateX',
                                        title: false,
                                        content: textStatus,
                                        buttons: {
                                            confirm: {
                                                text: '确认',
                                                btnClass: 'waves-effect waves-button waves-light'
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                },
                onContentReady: function () {
                    // bind to events
                    var jc = this;
                    this.$content.find('form').on('submit', function (e) {
                        // if the user submits the form by pressing enter in the field.
                        e.preventDefault();
                        jc.$$formSubmit.trigger('click'); // reference the button and click it
                    });
                }
            });
        }
    }


</script>
</body>
</html>