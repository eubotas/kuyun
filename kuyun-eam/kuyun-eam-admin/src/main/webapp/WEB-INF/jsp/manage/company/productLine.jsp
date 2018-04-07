<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="ProductLineDialog" class="crudDialog">
	<div class="form-group text-right dialog-buttons">
		<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">确认授权</a>
		<a class="waves-effect waves-button" href="javascript:;" onclick="cancelAction();">取消</a>
	</div>

	<div>
		<table id="tableProductLine"></table>
	</div>

	<div class="form-group text-right dialog-buttons">
		<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">确认授权</a>
		<a class="waves-effect waves-button" href="javascript:;" onclick="cancelAction();">取消</a>
	</div>

</div>
<script>
    var $tableProductLine = $('#tableProductLine');
    $(function() {
        // bootstrap table初始化
        $tableProductLine.bootstrapTable({
            url: '${basePath}/manage/company/productLine/list',
            queryParams:function(p){
                return {    companyId : '${companyId}',
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
            idField: 'productLineId',
            maintainSelected: true,
//            toolbar: '#toolbar',
            columns: [
                {field: 'ck', checkbox: true,  formatter : checkFormatter},
                {field: 'name', title: '产线名称', sortable: true, align: 'center'}
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
    productLineDialog.close();
    $table.bootstrapTable('refresh');
}


function createSubmit() {
    var rows = $tableProductLine.bootstrapTable('getSelections');
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
            ids.push(rows[i].productLineId);
        }

        $.ajax({
            type: 'post',
            url: '${basePath}/manage/company/auth/',
            data: {companyId: '${companyId}', pIds: ids.join("::")},
            beforeSend: function() {
                if ($('#name').val() == '') {
                    $('#name').focus();
                    return false;
                }
            },
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