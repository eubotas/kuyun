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
                <label >设备类型</label>
                <div class="form-group">
                    ${plan.equipmentCategoryName}
                </div>
            </div>

            <div class="col-sm-6">
                <label >设备名称</label>
                <div class="form-group">
                    <div class="fg-line">
                        ${plan.equipmentName}
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
                <label >维护部门</label>
                <div class="form-group">
                    <div class="fg-line">
                        ${plan.orgName}
                    </div>
                </div>
            </div>

            <div class="col-sm-6">
                <label for="nextMaintainDate">下个维护日期</label>
                <div class="form-group">
                    <div class="fg-line">
                        <input id="nextMaintainDate" name="nextMaintainDate" type="text" value="${plan.nextMaintainDate}" readonly />
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-6">
                <label >维护频率</label>
                <div class="form-group">
                    <div class="fg-line">
                        ${plan.maintainFrequencyQuantity}
                        ${plan.maintainFrequencyUnit}
                    </div>
                </div>
            </div>


            <div class="col-sm-6">
                <label >维护提前提醒天数</label>
                <div class="form-group">
                    <div class="fg-line">
                        ${plan.remindTime}
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group text-right dialog-buttons">
            <a class="waves-effect waves-button" href="javascript:;" onclick="detailDialog.close();">关闭</a>
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