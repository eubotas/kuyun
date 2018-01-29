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


<div id="equipmentDialog" class="crudDialog">
	<div class="form-group text-right dialog-buttons">
		<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">确认选择</a>
		<a class="waves-effect waves-button" href="javascript:;" onclick="cancelAction();">取消</a>
	</div>

	<div>
		<table id="tableEquipment"></table>
	</div>

	<div class="form-group text-right dialog-buttons">
		<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">确认选择</a>
		<a class="waves-effect waves-button" href="javascript:;" onclick="cancelAction();">取消</a>
	</div>

</div>
<script>
    var $tableEquipment = $('#tableEquipment');
    $(function() {
        // bootstrap table初始化
        $tableEquipment.bootstrapTable({
            url: '${basePath}/manage/${equipmentId}/dataGroup/all',
            height: getHeight(),
            minimumCountColumns: 2,
            clickToSelect: true,
            detailFormatter: 'detailFormatter',
            pagination: false,
            paginationLoop: false,
            sidePagination: 'server',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            idField: 'id',
            maintainSelected: true,
//            toolbar: '#toolbar',
            columns: [
                {field: 'ck', checkbox: true,  formatter : checkFormatter},
                {field: 'name', title: '分组名称'}
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
    createDialog.close();
    $table.bootstrapTable('refresh');
}


function createSubmit() {
    var rows = $tableEquipment.bootstrapTable('getSelections');
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
		console.info(rows);
        var ids = new Array();
        for (var i in rows) {
            ids.push(rows[i].id);
        }

        $.ajax({
            type: 'get',
            url: '${basePath}/manage/${equipmentId}/dataGroup/confirm',
            data: {ids: ids.join("::")},
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

}
</script>