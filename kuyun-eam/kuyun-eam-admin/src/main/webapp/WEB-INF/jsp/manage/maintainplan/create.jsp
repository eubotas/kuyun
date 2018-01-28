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
                <label for="equipmentCategoryId">设备类型</label>
                <div class="form-group">
                    <select id="equipmentCategoryId" name="equipmentCategoryId" style="width: 100%">
                        <c:forEach var="equipmentCategory" items="${equipmentCategorys}">
                            <option value="${equipmentCategory.equipmentCategoryId}">${equipmentCategory.name}</option>
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
                                <option value="${equipment.equipmentId}">${equipment.name}</option>
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
                              maxlength="200" rows="4"></textarea>

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
                                <option value="${org.organizationId}">${org.name}</option>
                            </c:forEach>

                        </select>
                    </div>
                </div>
            </div>

            <div class="col-sm-6">
                <label for="nextMaintainDate">维护日期</label>
                <div class="form-group">
                    <div class="fg-line">
                        <input id="nextMaintainDate" type="text" class="form-control" name="nextMaintainDate" maxlength="15">
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-2">
                <label for="maintainFrequencyQuantity">维护频率</label>
                <div class="form-group">
                    <div class="fg-line">
                        <input id="maintainFrequencyQuantity" type="text" class="form-control" name="maintainFrequencyQuantity" maxlength="4">
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <label for="equipmentId">维护频率单位</label>
                <div class="form-group">
                    <div class="fg-line">
                        <select id="maintainFrequencyUnit" name="maintainFrequencyUnit" style="width: 100%">
                            <c:forEach var="unit" items="${units}">
                                <option value="${unit.codeValue}">${unit.codeName}</option>
                            </c:forEach>

                        </select>
                    </div>
                </div>
            </div>

            <div class="col-sm-6">
                <label for="remindTime">维护提醒天数</label>
                <div class="form-group">
                    <div class="fg-line">
                        <input id="remindTime" type="text" class="form-control" name="remindTime" maxlength="4">
                    </div>
                </div>
            </div>
        </div>

		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit('${basePath}/manage/maintainPlan/create',createDialog, 'workContent,nextMaintainDate,maintainFrequencyQuantity,maintainFrequencyUnit,remindTime');">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="createDialog.close();">取消</a>
		</div>
	</form>
</div>
<script src="/resources/inc/eamcommon.js"></script>

