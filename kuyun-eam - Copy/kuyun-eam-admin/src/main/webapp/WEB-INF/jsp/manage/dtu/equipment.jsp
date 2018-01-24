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
		<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">确认接入</a>
		<a class="waves-effect waves-button" href="javascript:;" onclick="cancelAction();">取消</a>
	</div>

	<div>
		<table id="tableEquipment"></table>
	</div>

	<div class="form-group text-right dialog-buttons">
		<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">确认接入</a>
		<a class="waves-effect waves-button" href="javascript:;" onclick="cancelAction();">取消</a>
	</div>

</div>
<script>
    var $tableEquipment = $('#tableEquipment');
    $(function() {
        // bootstrap table初始化
        $tableEquipment.bootstrapTable({
            url: '${basePath}/manage/dtu/equipment/list',
            queryParams:function(p){
                return {    dtuId : '${dtuId}',
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
            idField: 'equipmentId',
            maintainSelected: true,
//            toolbar: '#toolbar',
            columns: [
                {field: 'ck', checkbox: true,  formatter : checkFormatter},
                {field: 'name', title: '设备名称', sortable: true, align: 'center'},
                {field: 'number', title: '设备编号'},
                {field: 'salveId', title: '从站地址', editable: {
                    type: 'text',
                    validate: function (value) {
                        if ($.trim(value) == '') {
                            return '从站地址不能为空!';
                        }
                    }
                }, align: 'center'}
            ],
            onEditableSave: function(field, row, oldValue, $el) {
                console.log(JSON.stringify(row));
                // field:修改的欄位
                // row:修改後的資料(JSON Object)
                // oldValue:修改前的值
                // -------------------------------------------------
                // 可用ajax方法去更新資料
                $.ajax({
                    type: "post",
                    url: '${basePath}/manage/dtu/equipment/write',
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

    function checkFormatter(value, row, index) {
        if (row.checked == true)
            return {
                disabled : false,//设置是否可用
                checked : true//设置选中
            };
        return value;
    }

function cancelAction() {
    equipmentDialog.close();
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
        var ids = new Array();
        for (var i in rows) {
            if (rows[i].salveId == null){
                alert("请输入从站地址！");
                return false;
			}
        }

        for (var i in rows) {
            ids.push(rows[i].equipmentId);
        }

        $.ajax({
            type: 'post',
            url: '${basePath}/manage/dtu/connect/',
            data: {dtuId: '${dtuId}', eIds: ids.join("::")},
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