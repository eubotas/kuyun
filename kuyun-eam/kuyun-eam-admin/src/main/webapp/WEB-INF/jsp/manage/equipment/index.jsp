﻿﻿﻿<%@ page contentType="text/html; charset=utf-8" %>
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

<pageResources>
    <jsp:include page="./map.jsp" flush="true"/>
      <script src="${basePath}/resources/metronic-admin/assets/js/bootstrap4-editable/js/bootstrap-editable.min.js"></script>
      <script src="${basePath}/resources/metronic-admin/assets/js/bootstrap-table.1.11.1/extensions/editable/bootstrap-table-editable.min.js"></script>

    <jsp:include page="/resources/metronic-admin/equipment_qrcode.jsp" flush="true"/>
    <jsp:include page="/resources/metronic-admin/file_upload.jsp" flush="true"/>
    <script>
        var basePath = "${basePath}";
    </script>
    <script src="${basePath}/resources/metronic-admin/assets/app/js/equipment/equipment.js"></script>

</pageResources>

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
												设备列表
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
                    <shiro:hasPermission name="eam:equipment:create"><a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                        <i class="la la-plus"></i>
                    </a></shiro:hasPermission>

                    <shiro:hasPermission name="eam:equipment:delete"><a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                        <i class="la la-remove"></i>
                    </a></shiro:hasPermission>

                    <%--<shiro:hasPermission name="eam:equipment:create"><a href="#" id="openCloseButton" class="btn btn-outline-info m-btn m-btn--icon m-btn--icon-only" title="启停">--%>
                        <%--<i class="la la-send"></i>--%>
                    <%--</a></shiro:hasPermission>--%>

                    <a href="#" id="qrcodeBtn" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="二维码">
                        <i class="la flaticon-squares-3"></i>
                    </a>
                    <div class="m-separator m-separator--dashed d-xl-none"></div>
                </div>
            </div>

            <table id="table" data-toolbar="#toolbar"></table>
        </div>
    </div>

    <!--begin::Modal-->
    <div id="addEquipmentFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editEquipmentFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-equipment-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_设备信息
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">设备名称:</label>
                            <div class="col-lg-4">
                                <input id="templateID_name" type="text" class="form-control" name="name">
                            </div>

                            <label class="col-lg-2 col-form-label">设备编号:</label>
                            <div class="col-lg-4">
                                <input id="templateID_number" type="text" class="form-control" name="number">
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">设备序列号:</label>
                            <div class="col-4">
                            <input id="templateID_serialNumber" type="text" class="form-control" name="serialNumber">
                            </div>

                            <label class="col-lg-2 col-form-label">设备模型:</label>
                            <div class="col-lg-4">
                                <select id="templateID_equipmentModelId" name="equipmentModelId" style="width: 100%"></select>
                            </div>
						</div>
                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>
                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">设备图片:</label>
                            <img id="templateID_showImage" src="" style="display:none;">
                            <div id="templateID_fine-uploader-gallery" class="col-sm-5"></div>
                            <input id="templateID_imagePath" type="hidden" class="form-control" name="imagePath">
                        </div>

                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">地区:</label>
                            <div class="col-sm-4">
                                <select id="templateID_province" name="province" onchange="changeCity(this)" style="width: 100%" ></select>
                            </div>

                            <div class="col-sm-4">
                                <select id="templateID_city" name="city" onchange="changeCity(this)" style="width: 100%" ></select>
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">
                                设备位置
                            </label>
                            <div >
                                <div class="col-4">
                                    <button type="button" id="templateID_mapLocation" class="btn btn-primary m-btn m-btn--icon">
										<span>
											<i class="flaticon-placeholder-1"></i>
											<span>
												地图获取
											</span>
										</span>
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div id='templateID_mapContainer' class="form-group m-form__group row" style="width:90%; height:300px; margin-left:30px; display:none;"></div>
                        <div id='templateID_detailAddr' style="width:90%; margin-left:30px; display:none;"></div>
                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label"> 经度:</label>
                            <div class="col-sm-4">
                                <input id="templateID_longitude" type="text" class="form-control" name="longitude">
                            </div>
                            <label class="col-lg-2 col-form-label">纬度:</label>
                            <div class="col-sm-4">
                                <input id="templateID_latitude" type="text" class="form-control" name="latitude">
                            </div>
                        </div>

                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

                        <div class="form-group m-form__group row">


                            <label class="col-lg-2 col-form-label">出厂日期:</label>
                            <div class="col-sm-4">
                                <div class="input-group date" >
                                    <input id="templateID_factoryDate" type="text" class="form-control" readonly  name="factoryDate"/>
                                    <div class="input-group-append">
                                        <span class="input-group-text">
                                            <i class="la la-calendar-check-o"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <label class="col-lg-2 col-form-label">投产日期:</label>
                            <div class="col-sm-4">
                                <div class="input-group date" >
                                    <input id="templateID_commissioningDate" type="text" class="form-control" readonly  name="commissioningDate"/>
                                    <div class="input-group-append">
                                        <span class="input-group-text">
                                            <i class="la la-calendar-check-o"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">质保开始日期:</label>
                            <div class="col-sm-4">
                                <div class="input-group date" >
                                    <input id="templateID_warrantyStartDate" type="text" class="form-control" readonly  name="warrantyStartDate"/>
                                    <div class="input-group-append">
                                        <span class="input-group-text">
                                            <i class="la la-calendar-check-o"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <label class="col-lg-2 col-form-label">质保结束日期:</label>
                            <div class="col-sm-4">
                                <div class="input-group date" >
                                    <input id="templateID_warrantyEndDate" type="text" class="form-control" readonly  name="warrantyEndDate"/>
                                    <div class="input-group-append">
                                        <span class="input-group-text">
                                            <i class="la la-calendar-check-o"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
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

    <div class="modal fade" id="sensorDialog" tabindex="-1" role="dialog" aria-labelledby="sensorModalLabel"
         aria-hidden="true">
        <form id="equipmentForm" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">
                            数据写入
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table id="tableSensor"></table>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            取消
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!--end::Modal-->


</content>



</body>
</html>