﻿<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="updateDialog" class="crudDialog">
	<form id="updateForm" method="post">

		<div class="form-group">
			<label for="name">客户名称</label>
			<input id="name" type="text" class="form-control" name="name" maxlength="50" value="${company.name}">
		</div>
		<div class="form-group">
			<label for="name">地址</label>
			<input id="address" type="text" class="form-control" name="address" maxlength="50" value="${company.address}">
		</div>
		<div class="form-group">
			<label for="phone">电话</label>
			<input id="phone" type="text" class="form-control" name="phone" maxlength="15" value="${company.phone}">
		</div>
		<div class="form-group">
			<label for="fax">传真</label>
			<input id="fax" type="text" class="form-control" name="fax" maxlength="15" value="${company.fax}">
		</div>
		<div class="form-group">
			<label for="zip">邮编</label>
			<input id="zip" type="text" class="form-control" name="zip" maxlength="10" value="${company.zip}">
		</div>
		<div class="form-group">
			<label for="www">网址</label>
			<input id="www" type="text" class="form-control" name="www" maxlength="15" value="${company.www}">
		</div>
		
		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="updateDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>
function createSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/company/update/${company.companyId}',
        data: $('#updateForm').serialize(),
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
				updateDialog.close();
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
</script>