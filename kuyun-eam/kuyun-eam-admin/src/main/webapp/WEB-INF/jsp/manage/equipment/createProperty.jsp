<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="updateDialog" class="crudDialog">
	<form id="updateForm" method="post">
	 <div id="propList">
		<c:forEach var="prop" items="${props}">
		<div class="row">
			<div class="col-sm-8">
				<div class="form-group">
					<div class="fg-line">
						<label >属性名称</label>
						<input type="text" class="form-control" name="propertyLabel" maxlength="200" value="${prop.propertyLabel}">
					</div>
				</div>
			</div>
		</div>
			<div class="row">
				<div class="col-sm-8">
					<div class="form-group">
						<div class="fg-line">
							<label >属性值</label>
							<input type="text" class="form-control" name="propertyValue" maxlength="200" value="${prop.propertyValue}">
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	 </div>
		<div class="form-group text-right dialog-buttons">
			<input hidden name="equipmentId" value="${equipmentId}"/>
			<a class="waves-effect waves-button" href="javascript:;" onclick="addrow();">增加</a>
		</div>

		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="updateSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="equipPropertyDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>

    $(function() {
        <c:if test="${empty props}">
        addrow();
        </c:if>
    });

    function addrow() {
        $('#propList').append('\t\t<div class="row">\n' +
            '<div class="col-sm-8">\n' +
            '\t<div class="form-group">\n' +
            '\t\t<div class="fg-line">\n' +
            '<label for="propertyLabel">属性名称</label>\n' +
            '<input type="text" class="form-control" name="propertyLabel" maxlength="200" >\n' +
            '\t\t</div>\n' +
            '\t</div>\n' +
            '</div>\n' +
            '\t\t</div>\n' +
            '<div class="row">\n' +
            '\t<div class="col-sm-8">\n' +
            '\t\t<div class="form-group">\n' +
            '<div class="fg-line">\n' +
            '\t<label for="propertyValue">属性值</label>\n' +
            '\t<input type="text" class="form-control" name="propertyValue" maxlength="200">\n' +
            '</div>\n' +
            '\t\t</div>\n' +
            '\t</div>\n' +
            '</div>\n' +
            '<input hidden name="equipmentId" />');
    }

function updateSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/${productLineId}/equipment/${equipmentId}/property/create',
        data: $('#updateForm').serialize(),
        beforeSend: function() {
			// if ($('#name').val() == '') {
			// 	$('#name').focus();
			// 	return false;
			// }
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
                equipPropertyDialog.close();
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