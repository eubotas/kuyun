﻿﻿<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
</head>
<body>


<subHeader>
    <!-- BEGIN: Subheader -->
    <div class="m-subheader ">
        <div class="d-flex align-items-center">
            <div class="mr-auto">
                <ul class="m-subheader__breadcrumbs m-nav m-nav--inline">
                    <li class="m-nav__item m-nav__item--home">
                        <a href="#" class="m-nav__link m-nav__link--icon">
                            <i class="m-nav__link-icon la la-home"></i>
                        </a>
                    </li>
                    <li class="m-nav__separator">
                        -
                    </li>
                    <li class="m-nav__item">
                        <a href="" class="m-nav__link">
											<span class="m-nav__link-text">
												设备模型参数列表
											</span>
                        </a>
                    </li>
                </ul>
            </div>

        </div>
    </div>
    <!-- END: Subheader -->
</subHeader>


<content>

    <div class="m-portlet m-portlet--mobile">
        <div class="m-portlet__body">
            <div id="toolbar">
                <div>
                    <shiro:hasPermission name="eam:equipmentModelProperty:create"><a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a></shiro:hasPermission>

                    <shiro:hasPermission name="eam:equipmentModelProperty:delete"><a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                        <i class="la la-remove"></i>
                    </a></shiro:hasPermission>

                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                </div>
            </div>

            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>

    <!--begin::Modal-->
    <div id="addEquipmentModelPropertyFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editEquipmentModelPropertyFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-equipmentModelProperty-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_设备模型参数
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label for="templateID_name">设备模型参数名称</label>
                            <input id="templateID_name" type="text" class="form-control" name="name" maxlength="200">
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="templateID_unit">参数单位</label>
                            <input id="templateID_unit" type="text" class="form-control" name="unit" maxlength="20">
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="templateID_dataType">设备模型参数类别</label>
                            <select id="templateID_dataType" name="dataType" style="width: 100%">
                                <c:forEach var="dataType" items="${dataTypes}">
                                    <option value="${dataType.code}">${dataType.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="templateID_displayType">设备模型参数显示类别</label>
                            <select id="templateID_displayType" name="displayType" style="width: 100%">
                                <c:forEach var="displayType" items="${displayTypes}">
                                    <option value="${displayType.code}">${displayType.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" name="equipmentModelId" value="${id}">
                        <input type="hidden" id="templateID_id" name="id">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="submit" class="btn btn-primary" id="templateID_submit">
                            提交
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div class="modal fade" id="alarmDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="alermForm" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" >
                            报警设定
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label for="alarmType">类别</label>
                            <select id="alarmType" name="alarmType" style="width: 100%">
                            </select>
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="upperBound">X</label>
                            <input id="upperBound" type="text" class="form-control" name="upperBound" maxlength="20" placeholder="X">
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="lowerBound">Y</label>
                            <input id="lowerBound" type="text" class="form-control" name="lowerBound" maxlength="20" placeholder="Y" >
                        </div>
                        <div class="form-group m-form__group row">
                            <label for="duration">M</label>
                            <input id="duration" type="text" class="form-control" name="duration" maxlength="20" placeholder="M">
                        </div>
                        <div class="form-group m-form__group row">
                            <select id="alarmTarget" name="alarmTarget" style="width: 100%">
                            </select>
                        </div>
                        <div class="form-group m-form__group row">
                            <select id="targetUser" name="targetUser" style="width: 100%">
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" id="alarmId" name="alarmId">
                        <input type="hidden" id="equipmentModelPropertyId" name="equipmentModelPropertyId">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-primary" onclick="alermSubmit('alermForm');">
                            提交
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div class="modal fade" id="dataChangeDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="dataChangeForm" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" >
                            数据转换
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">

                        <div class="form-group m-form__group row">
                            <div class="row">
                                <div class="col-sm-2">
                                    <div class="fg-line">
                                        <input id="isl" type="text"  class="form-control" name="isl" >
                                    </div>
                                </div>
                                <div class="col-sm-1">
                                    <div class="fg-line">
                                        一
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <div class="fg-line">
                                        <input id="ish" type="text"  class="form-control" name="ish" >
                                    </div>
                                </div>


                                <div class="col-sm-1">
                                    <div class="fg-line">
                                        =>
                                    </div>
                                </div>

                                <div class="col-sm-2">
                                    <div class="fg-line">
                                        <input id="osl" type="text"  class="form-control" name="osl" >
                                    </div>
                                </div>
                                <div class="col-sm-1">
                                    <div class="fg-line">
                                        一
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <div class="fg-line">
                                        <input id="osh" type="text"  class="form-control" name="osh" >
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" id="sensorId" name="sensorId">
                        <input type="hidden" id="data_equipmentModelPropertyId" name="equipmentModelPropertyId">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-primary" onclick="dataChangeSubmit('dataChangeForm');">
                            提交
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div class="modal fade" id="grmDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="grmForm" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" >
                            读写指令
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div id="grmShow">
                            <div class="form-group m-form__group row">
                                <select id="grmAction" name="grmAction" style="width: 100%">
                                </select>
                            </div>
                            <div class="form-group m-form__group row">
                                <label for="lowerBound">巨控 变量名</label>
                                <input id="grmVariable" type="text" class="form-control" name="grmVariable" maxlength="20" >
                            </div>
                            <div class="form-group m-form__group row">
                                <label for="grmVariableValue">巨控 写变量值</label>
                                <input id="grmVariableValue" type="text" class="form-control" name="grmVariableValue" maxlength="20" >
                            </div>
                        </div>

                        <div id="modbusShow">
                            <div class="form-group m-form__group row">
                                <select id="functionCode" name="functionCode" style="width: 100%">
                                </select>
                            </div>
                            <div class="form-group m-form__group row">
                                <label for="address">地址</label>
                                <input id="address" type="text" class="form-control" name="address" maxlength="500" >
                            </div>
                            <div class="form-group m-form__group row">
                                <select id="dataFormat" name="dataFormat" style="width: 100%">
                                </select>
                            </div>
                            <div class="form-group m-form__group row">
                                <select id="bitOrder" name="bitOrder" style="width: 100%">
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <input type="hidden" name="equipmentModelId" value="${id}">
                        <input type="hidden" id="grm_sensorId" name="sensorId">
                        <input type="hidden" id="grm_equipmentModelPropertyId" name="equipmentModelPropertyId">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                        <button type="button" class="btn btn-primary" onclick="grmSubmit('grmForm');">
                            提交
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!--end::Modal-->


</content>


<pageResources>


    <script>
        var basePath = "${basePath}";
    </script>
    <script src="${basePath}/resources/metronic-admin/assets/app/js/equipment/property.js"></script>


</pageResources>


</body>
</html>