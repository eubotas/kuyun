<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<div id="createDialog" class="crudDialog">
	<form id="createForm" method="post">
		<div class="row">
			<div class="col-sm-6">
				<div class="form-group">
					<label for="step">处理意见</label> <select id="step" name="step"
						style="width: 100%">
						<option value="处理中" selected="selected">处理中</option>
						<option value="已解决">已解决</option>
						<option value="不需处理">不需处理</option>
					</select>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="form-group">
					<label for="comments">具体处理意见</label>
					<textarea id="comments" class="form-control" name="comments"
						maxlength="200" rows="4"></textarea>
				</div>
			</div>
		</div>

		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;"
				onclick="createSubmit();">保存</a> <a
				class="waves-effect waves-button" href="javascript:;"
				onclick="updateDialog.close();">取消</a>
		</div>
	</form>
</div>
<div class="row">
	<hr />
</div>
<!-- records list create date desc -->
<c:forEach var="record" items="${records}">
	<div class="row">
		<div class="col-sm-12">
			<label for="comments${record.id}"></label> <span
				id="comments${record.id}">${record.comments }</span>
		</div>
		<div class="col-sm-12">
			<label for="createTime${record.id }"></label> <span
				id="createTime${record.id }">${record.createTime }</span>
		</div>
	</div>
	<div class="row">
		<hr />
	</div>
</c:forEach>
<!-- the ticket content -->
<div class="row">
	<div class="col-sm-12">
		<label for="description">工单描述</label> <span id="description">${ticket.description }</span>
	</div>
	<div class="col-sm-6">
		<label for="ticketTypeId">工单类型</label> <span>${ticket.ticketType.name }</span>
	</div>
	<div class="col-sm-6">
		<label for="priority">优先级</label> <span>${ticket.priority }</span>
	</div>

	<div class="col-sm-12">
		<label for="executorId">执行人</label> <span>${ticket.realname}</span>
	</div>
	<div class="col-sm-12">
		<label for="endDate">指定完成日期</label> <span id="endDate">${ticket.endDate}</span>
	</div>
	<div class="col-sm-12">
		<label for="createTime">创建时间</label> <span id="createTime">${ticket.createTime}</span>
	</div>
</div>
<div class="row">
	<hr />
</div>
<script>
	//document ready
	$(function() {

		$('span[id^="createTime"]').each(function() {
			$(this).text(timeFormatter($(this).text()));
		});
		$('span[id="endDate"]').each(function() {
			$(this).text(timeFormatter($(this).text()));
		});

	});

	//格式化时间
	function timeFormatter(value, row, index) {
		return new Date(value).toLocaleString();
	}
	function createSubmit() {
		$
				.ajax({
					type : 'post',
					url : '${basePath}/manage/ticket/update/${ticket.ticketId}',
					data : $('#createForm').serialize(),
					beforeSend : function() {
						if ($('#comments').val() == '') {
							$('#comments').focus();
							return false;
						}
					},
					success : function(result) {
						if (result.code != 1) {
							if (result.data instanceof Array) {
								$
										.each(
												result.data,
												function(index, value) {
													$
															.confirm({
																theme : 'dark',
																animation : 'rotateX',
																closeAnimation : 'rotateX',
																title : false,
																content : value.errorMsg,
																buttons : {
																	confirm : {
																		text : '确认',
																		btnClass : 'waves-effect waves-button waves-light'
																	}
																}
															});
												});
							} else {
								$
										.confirm({
											theme : 'dark',
											animation : 'rotateX',
											closeAnimation : 'rotateX',
											title : false,
											content : result.data.errorMsg,
											buttons : {
												confirm : {
													text : '确认',
													btnClass : 'waves-effect waves-button waves-light'
												}
											}
										});
							}
						} else {
							updateDialog.close();
							$table.bootstrapTable('refresh');
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$
								.confirm({
									theme : 'dark',
									animation : 'rotateX',
									closeAnimation : 'rotateX',
									title : false,
									content : textStatus,
									buttons : {
										confirm : {
											text : '确认',
											btnClass : 'waves-effect waves-button waves-light'
										}
									}
								});
					}
				});
	}
</script>