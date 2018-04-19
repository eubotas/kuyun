<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<body>

<subHeader>
    <!-- BEGIN: Subheader -->
    <div class="m-subheader ">
        <div class="d-flex align-items-center">
            <div class="mr-auto">
                <ul class="m-subheader__breadcrumbs m-nav m-nav--inline">
                    <li class="m-nav__item">
                        <a href="#" class="m-nav__link">
											<span class="m-nav__link-text">
												首页
											</span>
                        </a>
                    </li>
                    <li class="m-nav__separator">
                        -
                    </li>
                    <li class="m-nav__item">
                        <a href="#" class="m-nav__link">
											<span class="m-nav__link-text" id="currModeId">
												平台概览
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
            <div class="col-lg-6">
                <div class="m-portlet m-portlet--tabs m-portlet--brand m-portlet--head-solid-bg m-portlet--head-sm">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-caption">
                            <div class="m-portlet__head-title">
                                <h3 class="m-portlet__head-text">
                                    <i class="fa fa-cogs"></i>
                                    总览
                                </h3>
                            </div>
                        </div>
                    </div>
                    <div class="m-portlet__body">

                        <div class="m-widget17__item">
														<span class="m-widget17__icon">
															<i class="flaticon-truck m--font-brand"></i>
														</span>
                            <span class="m-widget17__subtitle">
															设备数量
														</span>
                            <span class="m-widget17__desc">
															15000
														</span>
                        </div>

                        <div class="m-widget17__item">
														<span class="m-widget17__icon">
															<i class="flaticon-paper-plane m--font-info"></i>
														</span>
                            <span class="m-widget17__subtitle">
															报警提醒数量
														</span>
                            <span class="m-widget17__desc">
															72
														</span>
                        </div>

                        <div class="m-widget17__item">
														<span class="m-widget17__icon">
															<i class="flaticon-time m--font-danger"></i>
														</span>
                            <span class="m-widget17__subtitle">
															工单处理数量
														</span>
                            <span class="m-widget17__desc">
															340
														</span>
                        </div>


                    </div>
                </div>

                <div class="m-portlet m-portlet--tabs m-portlet--brand m-portlet--head-solid-bg m-portlet--head-sm">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-caption">
                            <div class="m-portlet__head-title">
                                <h3 class="m-portlet__head-text">
                                    <i class="fa fa-cogs"></i>
                                    状态
                                </h3>
                            </div>
                        </div>
                    </div>
                    <div class="m-portlet__body">
                        <div class="row portlet light bordered" style="width:100%; height: 217px;padding: 0; margin:-5px 0">
                            <div id="echarts_pie1" style="height: 100%; width: 50%;border:1px solid #fff; border-radius: 4px">
                            </div>
                            <div id="echarts_pie2" style="height: 100%; width: 49%;border:1px solid #fff; border-radius: 4px">
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <!-- left end -->
            <div class="col-lg-6">
                <div class="m-portlet m-portlet--tabs m-portlet--warning m-portlet--head-solid-bg m-portlet--head-sm">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-caption">
                            <div class="m-portlet__head-title">
                                <h4 class="m-portlet__head-text">
                                    <i class="icon-settings font-dark"></i>
                                    故障报警
                                </h4>
                            </div>
                        </div>
                    </div>
                    <div class="m-portlet__body">
                        <div class="tab-content">
                            <table id="alarmTable" data-toolbar="#toolbar"></table>


                        </div>
                    </div>
                </div>
            </div>
            <!-- right end -->
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="m-portlet m-portlet--tabs m-portlet--warning m-portlet--head-solid-bg m-portlet--head-sm">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-caption">
                            <div class="m-portlet__head-title">
                                <h4 class="m-portlet__head-text">
                                    <i class="icon-share font-blue"></i>
                                    维保提醒
                                </h4>
                            </div>
                        </div>
                    </div>
                    <div class="m-portlet__body">
                        <div class="tab-content">
                            <table id="maintanceTable" data-toolbar="#toolbar"></table>


                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</content>

<pageResources>
    <script src="${basePath}/resources/kuyun-admin/plugins/echarts/echarts.min.js"></script>
    <script src="${basePath}/resources/metronic-admin/assets/kuyun/home2.js"></script>


</pageResources>

</body>
</html>