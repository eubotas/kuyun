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
					<div class="fg-line">
						<label for="name">报警名称</label>
						<input id="name" type="text" class="form-control" name="name" maxlength="20">
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<select id="eamDataElementId" name="eamDataElementId" style="width: 100%">
							<c:forEach var="eamDataElement" items="${eamDataElements}">
								<option value="${eamDataElement.id}">${eamDataElement.lableName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<select id="alarmType" name="alarmType" style="width: 100%">
							<c:forEach var="alarmType" items="${alarmTypes}">
								<option value="${alarmType.code}" ${alarm != null && alarm.alarmType == alarmType.code ? 'selected' : ''}>${alarmType.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<div id="upperBoundDiv">
				<div class="col-sm-4">
					<div class="form-group">
						<div class="fg-line">
							<input id="upperBound" type="text" class="form-control" name="upperBound" maxlength="20" placeholder="X" <c:if test="${alarm != null}"> value="${alarm.upperBound}"</c:if>>
						</div>
					</div>
				</div>
			</div>

			<div id="lowerBoundDiv" style="display:none">
				<div class="col-sm-4">
					<div class="form-group">
						<div class="fg-line">
							<input id="lowerBound" type="text" class="form-control" name="lowerBound" maxlength="20" placeholder="Y" <c:if test="${alarm != null}"> value="${alarm.lowerBound}"</c:if>>
						</div>
					</div>
				</div>
			</div>

			<div id="durationDiv" style="display:none">
				<div class="col-sm-4">
					<div class="form-group">
						<div class="fg-line">
							<input id="duration" type="text" class="form-control" name="duration" maxlength="20" placeholder="M" <c:if test="${alarm != null}"> value="${alarm.duration}"</c:if>>
						</div>
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="isCreateTicket">是否产生工单</label>
						<input id="isCreateTicket" type="Checkbox" class="form-control" name="isCreateTicket" value="1">
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
        url: '${basePath}/manage/alarmModel/create',
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


$('#upperBoundDiv').hide();
$('#lowerBoundDiv').hide();
$('#durationDiv').hide();

var alarmType = $('#alarmType').val();

if ('val_above' == alarmType){
    $('#upperBoundDiv').show();
}else if ('val_below' == alarmType){
    $('#lowerBoundDiv').show();
}else if ('val_above_below' == alarmType){
    $('#upperBoundDiv').show();
    $('#lowerBoundDiv').show();
}else if ('val_above_below_ofm' == alarmType){
    $('#upperBoundDiv').show();
    $('#lowerBoundDiv').show();
    $('#durationDiv').show();
}else if ('x_tir_y_rec' == alarmType){
    $('#upperBoundDiv').show();
    $('#lowerBoundDiv').show();
}else if ('val_between' == alarmType){
    $('#upperBoundDiv').show();
    $('#lowerBoundDiv').show();
}else if ('val_above_bound' == alarmType){
    $('#upperBoundDiv').show();
    $('#durationDiv').show();

}else if ('val_below_bound' == alarmType){
    $('#lowerBoundDiv').show();
    $('#durationDiv').show();

}else if ('offline' == alarmType){


}else if ('offline_for_minutes' == alarmType){

}else if ('switch_on' == alarmType){

}else if ('switch_off' == alarmType){

}

$('#alarmType').change(function() {
    $('#upperBoundDiv').hide();
    $('#lowerBoundDiv').hide();
    $('#durationDiv').hide();


    if ('val_above' == this.value){
        $('#upperBoundDiv').show();
    }else if ('val_below' == this.value){
        $('#lowerBoundDiv').show();
    }else if ('val_above_below' == this.value){
        $('#upperBoundDiv').show();
        $('#lowerBoundDiv').show();
    }else if ('val_above_below_ofm' == this.value){
        $('#upperBoundDiv').show();
        $('#lowerBoundDiv').show();
        $('#durationDiv').show();
    }else if ('x_tir_y_rec' == this.value){
        $('#upperBoundDiv').show();
        $('#lowerBoundDiv').show();
    }else if ('val_between' == this.value){
        $('#upperBoundDiv').show();
        $('#lowerBoundDiv').show();
    }else if ('val_above_bound' == this.value){
        $('#upperBoundDiv').show();
        $('#durationDiv').show();

    }else if ('val_below_bound' == this.value){
        $('#lowerBoundDiv').show();
        $('#durationDiv').show();

    }else if ('offline' == this.value){


    }else if ('offline_for_minutes' == this.value){

    }else if ('switch_on' == this.value){

    }else if ('switch_off' == this.value){

    }
});
</script>