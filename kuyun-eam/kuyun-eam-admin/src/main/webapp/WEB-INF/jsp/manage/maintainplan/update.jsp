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
        <div class="row">
            <div class="col-sm-6">
                <label for="equipmentCategoryId">设备类型</label>
                <div class="form-group">
                    <select id="equipmentCategoryId" name="equipmentCategoryId" style="width: 100%">
                        <c:forEach var="equipmentCategory" items="${equipmentCategorys}">
                            <option value="${equipmentCategory.equipmentCategoryId}" <c:if test="${plan.equipmentCategoryId== equipmentCategory.equipmentCategoryId}">selected</c:if> >${equipmentCategory.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="col-sm-6">
                <label for="equipmentId">设备名称</label>
                <div class="form-group">
                    <div class="fg-line">
                        <select id="equipmentId" name="equipmentId" style="width: 100%">
                            <c:forEach var="equipment" items="${equipments}">
                                <option value="${equipment.equipmentId}"  <c:if test="${plan.equipmentId== equipment.equipmentId}">selected</c:if> >${equipment.name}</option>
                            </c:forEach>

                        </select>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="row">
                <div class="col-sm-12">
                    <label for="workContent">工单描述</label>
                    <div class="form-group">
					<textarea id="workContent" class="form-control" name="workContent"
                              maxlength="200" rows="4">${plan.workContent }</textarea>

                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-6">
                <label for="equipmentId">维护部门</label>
                <div class="form-group">
                    <div class="fg-line">
                        <select id="orgId" name="orgId" style="width: 100%">
                            <c:forEach var="org" items="${orgs}">
                                <option value="${org.organizationId}" <c:if test="${plan.orgId== org.organizationId}">selected</c:if> >${org.name}</option>
                            </c:forEach>

                        </select>
                    </div>
                </div>
            </div>

            <div class="col-sm-6">
                <label for="nextMaintainDate">下个维护日期</label>
                <div class="form-group">
                    <div class="fg-line">
                        <input id="nextMaintainDate" name="nextMaintainDate" type="text" value="${MaintainDate}" readonly />
                        <img onclick="WdatePicker({dateFmt:dateFormat, minDate:getToday(), el:'nextMaintainDate'})" src="${basePath}/resources/kuyun-admin/plugins/My97DatePicker/skin/datePicker.gif" width="16" height="32" align="absmiddle">
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-2">
                <label for="maintainFrequencyQuantity">维护频率</label>
                <div class="form-group">
                    <div class="fg-line">
                        <input id="maintainFrequencyQuantity" type="text" class="form-control" name="maintainFrequencyQuantity" maxlength="4" value="${plan.maintainFrequencyQuantity}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <label for="equipmentId">维护频率单位</label>
                <div class="form-group">
                    <div class="fg-line">
                        <select id="maintainFrequencyUnit" name="maintainFrequencyUnit" style="width: 100%">
                            <c:forEach var="unit" items="${units}">
                                <option value="${unit.codeValue}" <c:if test="${plan.maintainFrequencyUnit== unit.codeValue}">selected</c:if>>${unit.codeName}</option>
                            </c:forEach>

                        </select>
                    </div>
                </div>
            </div>

            <div class="col-sm-6">
                <label for="remindTime">维护提前提醒天数</label>
                <div class="form-group">
                    <div class="fg-line">
                        <input id="remindTime" type="text" class="form-control" name="remindTime" maxlength="4" value="${plan.remindTime}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group text-right dialog-buttons">
            <a class="waves-effect waves-button" href="javascript:;" onclick="updateSubmit('${basePath}/manage/maintainPlan/update/${plan.planId}',updateDialog, 'workContent,nextMaintainDate,maintainFrequencyQuantity,maintainFrequencyUnit,remindTime');">保存</a>
            <a class="waves-effect waves-button" href="javascript:;" onclick="updateDialog.close();">取消</a>
        </div>
	</form>
</div>

<div >
    <table id="ticketTable"></table>
</div>

<script src="/resources/inc/eamcommon.js"></script>


    <script>
    //document ready
    $(function() {

        $('span[id^="nextMaintainDate"]').each(function() {
            $(this).text(timeFormatter($(this).val()));
        });

    });
    </script>


<script>
    var ticketTable = $('#ticketTable');
    $(function() {
        // bootstrap table初始化
        ticketTable.bootstrapTable({
            url: '${basePath}/manage/maintainPlan/${plan.planId}/tickets',
            height: getHeight(),
            striped: true,
            search: true,
            showRefresh: true,
            showColumns: true,
            minimumCountColumns: 2,
            clickToSelect: true,
            detailView: true,
            detailFormatter: 'detailFormatter',
            pagination: true,
            paginationLoop: false,
            sidePagination: 'server',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            idField: 'planTicketId',
            sortName: 'planTicketId',
            sortOrder: 'desc',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'ck', checkbox: true},
                {field: 'ticketDescription', title: '工单描述', sortable: true, align: 'center'},
                {field: 'ticketPriority', title: '优先级'},
                {field: 'ticketTypeName', title: '工单类型'},
                {field: 'serviceman', title: '执行人'},
                {field: 'servicePhone', title: '执行人电话'},
                {field: 'customerContacts', title: '顾客'},
                {field: 'customerPhone', title: '顾客电话'},
                {field: 'ticketStatus', title: '当前状态'}
            ]
        });
    });

</script>