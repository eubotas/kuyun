﻿﻿<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
                                工单列表
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

    <div class="m-content">
        <div class="row">
            <div class="col-xl-2 col-lg-4">
                <div class="m-portlet m-portlet--full-height  ">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-caption">
                            <div class="m-portlet__head-title">
                                <h1 class="m-portlet__head-text">
                                    工单状态
                                </h1>
                            </div>
                        </div>
                    </div>

                    <div id="ticketList" class="m-portlet__body">
                        <ul class="m-nav m-nav--hover-bg m-portlet-fit--sides" id="models">
                            <shiro:hasAnyRoles name="ticketCreate,ticketRepair">
                                <li id="myOpen" class="m-nav__item"><a href="javascript:;" onclick="toAction('myOpen');" class="m-nav__link"> <span class="m-nav__link-text">未处理</span></a></li>
                                <li id="myResolved" class="m-nav__item"><a href="javascript:;" onclick="toAction('myResolved');" class="m-nav__link"> <span class="m-nav__link-text">已处理</span></a></li>
                                <li id="myAll" class="m-nav__item"><a href="javascript:;" onclick="toAction('myAll');" class="m-nav__link"> <span class="m-nav__link-text">全部工单</span></a></li>
                            </shiro:hasAnyRoles>

                            <shiro:hasRole name="ticketAppoint">
                                <li id="init" class="m-nav__item"><a href="javascript:;" onclick="toAction('init');" class="m-nav__link"> <span class="m-nav__link-text">待派工</span></a></li>
                                <li id="resolved" class="m-nav__item"><a href="javascript:;" onclick="toAction('resolved');" class="m-nav__link"> <span class="m-nav__link-text">已完成</span></a></li>
                                <li id="all" class="m-nav__item"><a href="javascript:;" onclick="toAction('all');" class="m-nav__link"> <span class="m-nav__link-text">全部工单</span></a></li>
                            </shiro:hasRole>
                        </ul>
                        <div class="m-portlet__body-separator"></div>

                    </div>
                </div>
            </div>
            <div class="col-xl-10 col-lg-8">
                <div class="m-portlet m-portlet--full-height">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-caption">
                            <div class="m-portlet__head-title">
												<span class="m-portlet__head-icon">
													<i class="flaticon-multimedia"></i>
												</span>
                                <h3 class="m-portlet__head-text">
                                    ${categoryType}工单列表
                                </h3>
                            </div>
                        </div>
                    </div>


                    <div class="m-portlet__body">
                        <div id="toolbar">
                            <div>
                                <shiro:hasPermission name="eam:ticket:create"><a href="#" id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
                                    <i class="la la-plus"></i>
                                </a></shiro:hasPermission>

                                <shiro:hasPermission name="eam:ticket:delete"><a href="#" id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
                                    <i class="la la-remove"></i>
                                </a></shiro:hasPermission>

                                <div class="m-separator m-separator--dashed d-xl-none"></div>
                            </div>
                        </div>

                        <table id="table" data-toolbar="#toolbar"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--begin::Modal-->
    <div id="addTicketFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div id="editTicketFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
    </div>

    <div class="modal fade" id="template-ticket-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            templateTitleName_工单
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">工单描述:</label>
                            <div class="col-lg-9">
                                <textarea id="templateID_description" class="form-control" name="description" rows="5"></textarea>
                            </div>
                        </div>


                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">上传附件:</label>
                            <div id="templateID_fine-uploader-gallery" class="col-sm-9"></div>
                            <input id="templateID_imagePath" type="hidden" class="form-control" name="imagePath">
                        </div>
                        <div class="form-group m-form__group row">
                            <div id="templateID_fileExistingSection" style="height: 70px; padding-left:120px;display:none;"> </div>
                        </div>

                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

                        <div class="form-group m-form__group row">
                            <label class="col-2 col-form-label">
                                工单优先级:
                            </label>
                            <div class="col-8" id="templateID_ticketPriorityDiv">
                            </div>
                        </div>
                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

                        <div class="form-group m-form__group row">
                            <label class="col-2 col-form-label">
                                工单类型:
                            </label>
                            <div class="col-8" id="templateID_ticketTypeDiv">
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">设备名称:</label>
                            <div class="col-sm-4">
                                <select id="templateID_equipmentId" name="equipmentId" style="width: 100%"></select>
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

</content>

<pageResources>
    <jsp:include page="/resources/metronic-admin/file_upload.jsp" flush="true"/>

    <script>
        var basePath = "${basePath}";
    </script>
    <script src="${basePath}/resources/metronic-admin/assets/app/js/ticket/ticket.js"></script>

</pageResources>
</body>
</html>