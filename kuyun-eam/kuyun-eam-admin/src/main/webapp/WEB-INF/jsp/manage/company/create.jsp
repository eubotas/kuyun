<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="createDialog" class="crudDialog">
	<form id="createForm" method="post">
		<div class="row">
			<div class="col-sm-12">
					<label for="name">客户名称</label>
					<input id="name" type="text" class="form-control" name="name" maxlength="50">
			</div>
		</div>

		<div class="row">
			<div class="col-sm-4">
				<label for="name">地址</label>
				<input id="address" type="text" class="form-control" name="address" maxlength="50">
			</div>
			<div class="col-sm-4">
				<label for="phone">电话</label>
				<input id="phone" type="text" class="form-control" name="phone" maxlength="15">
			</div>
			<div class="col-sm-4">
				<label for="fax">传真</label>
				<input id="fax" type="text" class="form-control" name="fax" maxlength="15">
			</div>
		</div>


		<div class="row">
			<div class="col-sm-6">
				<label for="zip">邮编</label>
				<input id="zip" type="text" class="form-control" name="zip" maxlength="10">
			</div>
			<div class="col-sm-6">
				<label for="www">网址</label>
				<input id="www" type="text" class="form-control" name="www" maxlength="15">
			</div>
		</div>

		<div class="row">
			<div class="col-sm-6">
				<label for="adminName">管理员账号</label>
				<input id="adminName" type="text" class="form-control" name="adminName" maxlength="15">
			</div>
			<div class="col-sm-6">
				<label for="adminPassword">管理员密码</label>
				<input id="adminPassword" type="text" class="form-control" name="adminPassword" maxlength="15">
			</div>
		</div>

		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="createDialog.close();">取消</a>
		</div>

		</div>
	</form>
</div>
<script>
function createSubmit() {


    $.ajax({
        type: 'post',
        url: '${basePath}/manage/company/create',
        data: $('#createForm').serialize(),
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
				createDialog.close();
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