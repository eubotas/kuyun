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
				<div class="form-group">
					
						<label for="description">工单描述</label>
						<textarea id="description" class="form-control" name="description" maxlength="200" rows="4"></textarea>
					
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="ticketTypeId">工单类型</label>
						<select id="ticketTypeId" name="ticketTypeId" style="width: 100%">
							<%--<option value="0">设备模型</option>--%>
							<c:forEach var="ticketType" items="${ticketTypes}">
								<option value="${ticketType.id}">${ticketType.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="priority">优先级</label>
						<select id="priority" name="priority" style="width: 100%">
							<option value="一般">一般</option>
							<option value="紧急">紧急</option>
							<option value="非常紧急">非常紧急</option>
		
						</select>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="executorId">执行人</label>
						<select id="executorId" name="executorId" style="width: 100%">
							<%--<option value="0">设备模型</option>--%>
							<c:forEach var="user" items="${users}">
								<option value="${user.userId}">${user.realname}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="endDate">指定完成日期</label>
						<input id="endDate" type = "date" name="endDate" class="form-control"></input>
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="imagePath1">相关图片</label>
						<input id="imagePath1" type="text" class="form-control" name="imagePath1" maxlength="500">
						<input id="imagePath2" type="text" class="form-control" name="imagePath2" maxlength="500">
						<input id="imagePath3" type="text" class="form-control" name="imagePath3" maxlength="500">
						<input id="imagePath4" type="text" class="form-control" name="imagePath4" maxlength="500">
					</div>
				</div>
			</div>

			
		</div>

		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="createDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>
function createSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/ticket/create',
        data: $('#createForm').serialize(),
        beforeSend: function() {
            if ($('#description').val() == '') {
                $('#description').focus();
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