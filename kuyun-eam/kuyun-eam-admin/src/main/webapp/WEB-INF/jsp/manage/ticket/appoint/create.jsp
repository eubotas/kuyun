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
			<div class="col-sm-6">
                <label for="orderTakerId">工单执行人</label>
				<a class="waves-effect waves-button" href="javascript:;" onclick="createUser();">创建新用户</a>
				<div class="form-group">
					<div class="fg-line">
                        <select id="orderTakerId" name="orderTakerId" style="width: 100%">
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
						<label for="rejectCommont">工单委派备注/拒绝原因</label>
						<input id="rejectCommont" type="text" class="form-control" name="rejectCommont" maxlength="200">
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
    // 新增用户
    var createUserDialog;
    function createUser() {
        createUserDialog = $.dialog({
            animationSpeed: 300,
            title: '新增用户',
            content: 'url:${basePath}/manage/company/createUser',
            onContentReady: function () {
                initMaterialInput();
            }
        });
    }

function createSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/ticket/${ticketId}/appoint/create',
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
                }else if(appointDialog) {
                    appointDialog.close();
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