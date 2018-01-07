<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<link href="${basePath}/resources/kuyun-admin/plugins/bootstrap-editable/css/bootstrap-editable.css" rel="stylesheet"/>
<script src="${basePath}/resources/kuyun-admin/plugins/bootstrap-editable/js/bootstrap-editable.min.js"></script>
<%--<script src="//rawgit.com/vitalets/x-editable/master/dist/bootstrap3-editable/js/bootstrap-editable.js"></script>--%>

<link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
<script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script src="${basePath}/resources/kuyun-admin/plugins/bootstrap-table-1.11.0/extensions/editable/bootstrap-table-editable.min.js"></script>


<div id="assignDialog" class="crudDialog">
    <div class="form-group text-right dialog-buttons">
        <a class="waves-effect waves-button" href="javascript:;" onclick="assignSubmit(${orgId});">确认分配</a>
        <a class="waves-effect waves-button" href="javascript:;" onclick="cancelAction();">取消</a>
    </div>

    <div>
        <table id="tableStaff"></table>
    </div>

    <div class="form-group text-right dialog-buttons">
        <a class="waves-effect waves-button" href="javascript:;" onclick="assignSubmit(${orgId});">确认分配</a>
        <a class="waves-effect waves-button" href="javascript:;" onclick="cancelAction();">取消</a>
    </div>

</div>
<script>
    var tableStaff = $('#tableStaff');
    $(function() {
        // bootstrap table初始化
        tableStaff.bootstrapTable({
            url: '${basePath}/manage/organization/assign/${orgId}/listStaff',
            queryParams:function(p){
                return {    orgId : '${orgId}',
                    limit : p.limit,
                    offset: p.offset,
                    search: p.search,
                    sort:   p.sort,
                    order:  p.order
                }
            },
            height: getHeight(),
            striped: true,
            minimumCountColumns: 2,
            clickToSelect: true,
            detailFormatter: 'detailFormatter',
            pagination: true,
            paginationLoop: false,
            sidePagination: 'server',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            idField: 'userId',
            maintainSelected: true,
//            toolbar: '#toolbar',
            columns: [
                {field: 'ck', checkbox: true,  formatter : checkFormatter},
                {field: 'realname', title: '姓名', sortable: true, align: 'center'},
                {field: 'phone', title: '电话号码'},
                {field: 'email', title: 'Email地址', align: 'center'}
            ]
        });
    });

    function checkFormatter(value, row, index) {
        if (row.checked == true)
            return {
                disabled : false,//设置是否可用
                checked : true//设置选中
            };
        return value;
    }

    function cancelAction() {
        assignDialog.close();
    }


    function assignSubmit(orgId) {
        var rows = tableStaff.bootstrapTable('getSelections');
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
        }else {
            var ids = new Array();
            for (var i in rows) {
                if (rows[i].userId == null){
                    alert("请选择一记录！");
                    return false;
                }
            }

            for (var i in rows) {
                ids.push(rows[i].userId);
            }

            $.ajax({
                type: 'post',
                url: '${basePath}/manage/organization/assign/'+orgId,
                data: {orgId: '${orgId}', eIds: ids.join("::")},
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
                        cancelAction();
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    $.confirm({
                        theme: 'dark',
                        animation: 'rotateX',
                        closeAnimation: 'rotateX',
                        title: false,
                        content: textStatus +': '+errorThrown,
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

    }

</script>