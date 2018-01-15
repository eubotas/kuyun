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
		<input type="hidden" name="userId" value="1">
		<div class="row">
			<div class="col-sm-6">
                <label for="assessmentLevel">工单评价星级</label>
				<div class="form-group">
					<div class="fg-line">
						<select id="assessmentLevel"
								name="assessmentLevel" style="width: 100%">
							<option value="1">1星</option>
							<option value="2">2星</option>
							<option value="3">3星</option>
							<option value="4">4星</option>
							<option value="5">5星</option>

						</select>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="description">工单评价备注</label>
						<input id="description" type="text" class="form-control" name="description" maxlength="200">
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-6">
				<label for="ticketTag">工单评价标签</label>
				<div class="form-group">
					<div class="fg-line">
                        <c:forEach var="ticketTag" items="${ticketTags}">
                            <input type="checkbox" name="ticketTag" value="${ticketTag.id}" />${ticketTag.name}
                        </c:forEach>
					</div>
				</div>
			</div>

		</div>

		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="closeBtn();">取消</a>
		</div>
	</form>

    <jsp:include page="../ticketInfo.jsp" flush="true"/>
</div>
<script>
function createSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/ticket/${ticketId}/assessment/create',
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
			    if(createDialog){
                    createDialog.close();
                    $table.bootstrapTable('refresh');
                }else if(assessmentDialog) {
                    assessmentDialog.close();
                    $table.bootstrapTable('refresh');
                }
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