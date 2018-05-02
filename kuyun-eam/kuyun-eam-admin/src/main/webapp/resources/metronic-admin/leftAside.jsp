<%@ page language="java" import="com.kuyun.common.util.BasePath"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<c:set var="eamPath" value="<%=BasePath.kuyunEamAdmin%>"/>
<c:set var="upmsPath" value="<%=com.kuyun.common.util.BasePath.kuyunUpmsServer%>"/>

<script src="${basePath}/resources/kuyun-admin/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/metronic-admin/assets/js/header_common.js"></script>
<!-- BEGIN: Left Aside -->
<button class="m-aside-left-close  m-aside-left-close--skin-dark " id="m_aside_left_close_btn">
    <i class="la la-close"></i>
</button>
<div id="m_aside_left" class="m-grid__item	m-aside-left  m-aside-left--skin-dark ">
    <!-- BEGIN: Aside Menu -->
    <div
            id="m_ver_menu"
            class="m-aside-menu  m-aside-menu--skin-dark m-aside-menu--submenu-skin-dark "
            data-menu-vertical="true"
            data-menu-scrollable="false" data-menu-dropdown-timeout="500"
    >
        <ul id="menu" class="m-menu__nav  m-menu__nav--dropdown-submenu-arrow ">

        </ul>

        <div id="menuDashboard" style="display: none">
            <li class="m-menu__item " aria-haspopup="true" >
                <a  href="${eamPath}/manage/index" class="m-menu__link ">
                    <i class="m-menu__link-icon flaticon-line-graph"></i>
                    <span class="m-menu__link-title">
										<span class="m-menu__link-wrap">
											<span class="m-menu__link-text">
												首页
											</span>
										</span>
									</span>
                </a>
            </li>
        </div>

        <div id="menu_assetsList" style="display: none">
            <li id="menu_assetsTitle" class="m-menu__section">
                <h4 class="m-menu__section-text">
                    资产管理
                </h4>
                <i class="m-menu__section-icon flaticon-more-v3"></i>
            </li>
            <li id="menu_assetsEquipment" class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  data-menu-submenu-toggle="hover">
                <a  href="#" class="m-menu__link m-menu__toggle">
                    <i class="m-menu__link-icon flaticon-layers"></i>
                    <span class="m-menu__link-text">
										设备管理
									</span>
                    <i class="m-menu__ver-arrow la la-angle-right"></i>
                </a>
                <div id="submenu_assetsEquipment" class="m-menu__submenu ">
                    <span class="m-menu__arrow"></span>
                    <ul class="m-menu__subnav">
                        <li class="m-menu__item  m-menu__item--parent" aria-haspopup="true" >
											<span class="m-menu__link">
												<span class="m-menu__link-text">
													设备管理
												</span>
											</span>
                        </li>
                        <li id="submenu_assetsEquipment1" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="eam:equipmentModel:read">
                                <a  href="${eamPath}/manage/equipment/model/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													设备模型管理
												</span>
                                </a></shiro:hasPermission>
                        </li>
                        <li id="submenu_assetsEquipment2" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="eam:equipment:read">
                                <a  href="${eamPath}/manage/equipment/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													设备信息管理
												</span>
                                </a></shiro:hasPermission>
                        </li>
                    </ul>
                </div>
            </li>
            <li id="menu_assetsGateway" class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  data-menu-submenu-toggle="hover">
                <a  href="#" class="m-menu__link m-menu__toggle">
                    <i class="m-menu__link-icon flaticon-cogwheel-2"></i>
                    <span class="m-menu__link-text">
										网关管理
									</span>
                    <i class="m-menu__ver-arrow la la-angle-right"></i>
                </a>
                <div class="m-menu__submenu ">
                    <span class="m-menu__arrow"></span>
                    <ul class="m-menu__subnav">

                        <li id="submenu_assetsGateway1" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="eam:dtu:read">
                                <a  href="${eamPath}/manage/dtu/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													DTU
												</span>
                                </a></shiro:hasPermission>
                        </li>
                        <li id="submenu_assetsGateway2" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="eam:dtu:read">
                                <a  href="${eamPath}/manage/grm/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													智库网关
												</span>
                                </a></shiro:hasPermission>
                        </li>
                    </ul>
                </div>
            </li>
            <li id="menu_assetsCustomer" class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  data-menu-submenu-toggle="hover">
                <a  href="#" class="m-menu__link m-menu__toggle">
                    <i class="m-menu__link-icon flaticon-interface-1"></i>
                    <span class="m-menu__link-text">
										客户管理
									</span>
                    <i class="m-menu__ver-arrow la la-angle-right"></i>
                </a>
                <div class="m-menu__submenu ">
                    <span class="m-menu__arrow"></span>
                    <ul class="m-menu__subnav">

                        <li id="submenu_assetsCustomer1" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="eam:company:read">
                                <a  href="${eamPath}/manage/company/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													客户管理
												</span>
                                </a></shiro:hasPermission>
                        </li>
                    </ul>
                </div>
            </li>
            <li id="menu_assetsTicket" class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  data-menu-submenu-toggle="hover">
                <a  href="#" class="m-menu__link m-menu__toggle">
                    <i class="m-menu__link-icon flaticon-share"></i>
                    <span class="m-menu__link-text">
										工单管理
									</span>
                    <i class="m-menu__ver-arrow la la-angle-right"></i>
                </a>
                <div id="submenu_assetsTicket" class="m-menu__submenu ">
                    <span class="m-menu__arrow"></span>
                    <ul class="m-menu__subnav">
                        <li id="submenu_assetsTicket1" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="eam:ticket:summary">
                                <a  href="${eamPath}/manage/ticket/summary" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													工单统计
												</span>
                                </a></shiro:hasPermission>
                        </li>

                        <li id="submenu_assetsTicket2" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <%--<shiro:hasPermission name="eam:ticket:summary">--%>
                                <a  href="${eamPath}/manage/ticket/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
                                        工单列表
									</span>
                                </a>
                            <%--</shiro:hasPermission>--%>
                        </li>

                        <%--<li id="submenu_assetsTicket2" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">--%>
                            <%--<shiro:hasPermission name="eam:ticket:read">--%>
                                <%--<a  href="${eamPath}/manage/ticket/index?category=notResolved" class="m-menu__link ">--%>
                                    <%--<i class="m-menu__link-bullet m-menu__link-bullet--dot">--%>
                                        <%--<span></span>--%>
                                    <%--</i>--%>
                                    <%--<span class="m-menu__link-text">--%>
													<%--未处理工单--%>
												<%--</span>--%>
                                <%--</a></shiro:hasPermission>--%>
                        <%--</li>--%>

                        <%--<li id="submenu_assetsTicket3" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">--%>
                            <%--<shiro:hasPermission name="eam:ticket:read">--%>
                                <%--<a  href="${eamPath}/manage/ticket/index?category=init" class="m-menu__link ">--%>
                                    <%--<i class="m-menu__link-bullet m-menu__link-bullet--dot">--%>
                                        <%--<span></span>--%>
                                    <%--</i>--%>
                                    <%--<span class="m-menu__link-text">--%>
													<%--未委派工单--%>
												<%--</span>--%>
                                <%--</a></shiro:hasPermission>--%>
                        <%--</li>--%>

                        <%--<li id="submenu_assetsTicket4" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">--%>
                            <%--<shiro:hasPermission name="eam:ticket:read">--%>
                                <%--<a  href="${eamPath}/manage/ticket/index?category=all" class="m-menu__link ">--%>
                                    <%--<i class="m-menu__link-bullet m-menu__link-bullet--dot">--%>
                                        <%--<span></span>--%>
                                    <%--</i>--%>
                                    <%--<span class="m-menu__link-text">--%>
													<%--全部工单--%>
												<%--</span>--%>
                                <%--</a></shiro:hasPermission>--%>
                        <%--</li>--%>

                        <%--<li id="submenu_assetsTicket5" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">--%>
                            <%--<shiro:hasPermission name="eam:ticket:read">--%>
                                <%--<a  href="${eamPath}/manage/ticket/index?category=myAll" class="m-menu__link ">--%>
                                    <%--<i class="m-menu__link-bullet m-menu__link-bullet--dot">--%>
                                        <%--<span></span>--%>
                                    <%--</i>--%>
                                    <%--<span class="m-menu__link-text">--%>
													<%--我的工单--%>
												<%--</span>--%>
                                <%--</a></shiro:hasPermission>--%>
                        <%--</li>--%>

                        <li id="submenu_assetsTicket3" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="eam:ticketType:read">
                                <a  href="${eamPath}/manage/ticket/type/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													工单类型
												</span>
                                </a></shiro:hasPermission>
                        </li>
                        <li id="submenu_assetsTicket4" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="eam:TicketAssessment:read">
                                <a  href="${eamPath}/manage/ticket/tag/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													评价标签
												</span>
                                </a></shiro:hasPermission>
                        </li>


                    </ul>
                </div>
            </li>
            <li id="menu_assetsMaintancePlan" class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  data-menu-submenu-toggle="hover">
                <a  href="#" class="m-menu__link m-menu__toggle">
                    <i class="m-menu__link-icon flaticon-multimedia-1"></i>
                    <span class="m-menu__link-text">
										维修计划
									</span>
                    <i class="m-menu__ver-arrow la la-angle-right"></i>
                </a>

                <div id="submenu_assetsMaintancePlan" class="m-menu__submenu ">
                    <span class="m-menu__arrow"></span>
                    <ul class="m-menu__subnav">

                        <li id="submenu_assetsMaintancePlan1" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="eam:maintainPlan:read">
                                <a  href="${eamPath}/manage/maintainPlan/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													维修计划
												</span>
                                </a></shiro:hasPermission>
                        </li>
                    </ul>
                </div>
            </li>
            <li id="menu_assetsKnowledge" class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  data-menu-submenu-toggle="hover">
                <a  href="#" class="m-menu__link m-menu__toggle">
                    <i class="m-menu__link-icon flaticon-interface-7"></i>
                    <span class="m-menu__link-text">
										知识管理
									</span>
                    <i class="m-menu__ver-arrow la la-angle-right"></i>
                </a>
                <div id="submenu_assetsKnowledge" class="m-menu__submenu ">
                    <span class="m-menu__arrow"></span>
                    <ul class="m-menu__subnav">

                        <li id="submenu_assetsKnowledge1" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="eam:knowledge:read">
                                <a  href="${eamPath}/manage/knowledge/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													知识搜索
												</span>
                                </a></shiro:hasPermission>
                        </li>

                        <li id="submenu_assetsKnowledge2" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="eam:trainingVideo:read">
                                <a  href="${eamPath}/manage/knowledge/training/video/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													培训视频
												</span>
                                </a></shiro:hasPermission>
                        </li>

                        <li id="submenu_assetsKnowledge3" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="eam:trainingDoc:read">
                                <a  href="${eamPath}/manage/knowledge/training/doc/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													培训文档
												</span>
                                </a></shiro:hasPermission>
                        </li>


                        <li id="submenu_assetsKnowledge4" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="eam:maintainKnowledge:read">
                                <a  href="${eamPath}/manage/knowledge/maintain/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													保养知识
												</span>
                                </a></shiro:hasPermission>
                        </li>

                        <li id="submenu_assetsKnowledge5" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="eam:maintainKnowledge:read">
                                <a  href="${eamPath}/manage/knowledge/repair/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													维修知识
												</span>
                                </a></shiro:hasPermission>
                        </li>
                        <li id="submenu_assetsKnowledge6" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="eam:equipmentManual:read">
                                <a  href="${eamPath}/manage/knowledge/manual/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													设备手册
												</span>
                                </a></shiro:hasPermission>
                        </li>

                    </ul>
                </div>
            </li>
        </div>
        <div id="menu_equiOpList" style="display: none">
            <li  id="menu_equiOpTitle" class="m-menu__section">
                <h4 class="m-menu__section-text">
                    设备运营管理
                </h4>
                <i class="m-menu__section-icon flaticon-more-v3"></i>
            </li>
            <li id="menu_equiOp" class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" >
                <a  href="#" class="m-menu__link m-menu__toggle">
                    <i class="m-menu__link-icon flaticon-interface-9"></i>
                    <span class="m-menu__link-text">
										设备运营
									</span>
                    <i class="m-menu__ver-arrow la la-angle-right"></i>
                </a>
                <div class="m-menu__submenu" id="submenu_equiOp">
                    <span class="m-menu__arrow"></span>
                    <ul class="m-menu__subnav">

                        <li id="submenu_equiOp_DataCollect" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <a  href="${eamPath}/manage/equipment/monitor/list" class="m-menu__link ">
                                <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                    <span></span>
                                </i>
                                <span class="m-menu__link-text">
													监控中心
												</span>
                            </a>
                        </li>
                        <li id="submenu_equiOp_Alerm" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <a  href="${eamPath}/manage/alarm/record/center" class="m-menu__link ">
                                <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                    <span></span>
                                </i>
                                <span class="m-menu__link-text">
													报警中心
												</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </li>
        </div>
        <div id="menu_systemList" style="display: none">
            <li id="menu_systemTitle" class="m-menu__section">
                <h4 class="m-menu__section-text">
                    系统设置
                </h4>
                <i class="m-menu__section-icon flaticon-more-v3"></i>
            </li>
            <li id="menu_system" class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  data-menu-submenu-toggle="hover">
                <shiro:hasPermission name="upms:system:create">
                    <a  href="#" class="m-menu__link m-menu__toggle">
                        <i class="m-menu__link-icon flaticon-interface-9"></i>
                        <span class="m-menu__link-text">
										系统设置
									</span>
                        <i class="m-menu__ver-arrow la la-angle-right"></i>
                    </a>
                </shiro:hasPermission>
                <div id="submenu_system" class="m-menu__submenu " style="display: block;">
                    <span class="m-menu__arrow"></span>
                    <ul class="m-menu__subnav">
                        <li id="submenu_system1" class="m-menu__item " aria-haspopup="true"  onclick="setmenu(this);">
                            <shiro:hasPermission name="upms:company:read">
                                <a  href="${upmsPath}/manage/company/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													公司管理
												</span>
                                </a>
                            </shiro:hasPermission>
                        </li>
                        <li id="submenu_system2" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="upms:organization:read">
                                <a  href="${upmsPath}/manage/organization/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													部门管理
												</span>
                                </a></shiro:hasPermission>
                        </li>
                        <li id="submenu_system3" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="upms:user:read">
                                <a  href="${upmsPath}/manage/user/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													用户管理
												</span>
                                </a></shiro:hasPermission>
                        </li>
                        <li id="submenu_system4" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="upms:role:read">
                                <a  href="${upmsPath}/manage/role/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													角色管理
												</span>
                                </a></shiro:hasPermission>
                        </li>
                        <li id="submenu_system5" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="upms:permission:read">
                                <a  href="${upmsPath}/manage/permission/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													权限管理
												</span>
                                </a></shiro:hasPermission>
                        </li>
                        <li id="submenu_system6" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="upms:system:read">
                                <a  href="${upmsPath}/manage/system/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													系统管理
												</span>
                                </a></shiro:hasPermission>
                        </li>
                        <li id="submenu_system7" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="upms:session:read">
                                <a  href="${upmsPath}/manage/session/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													会话管理
												</span>
                                </a></shiro:hasPermission>
                        </li>
                        <li id="submenu_system8" class="m-menu__item " aria-haspopup="true" onclick="setmenu(this);">
                            <shiro:hasPermission name="upms:log:read">
                                <a  href="${upmsPath}/manage/log/index" class="m-menu__link ">
                                    <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                        <span></span>
                                    </i>
                                    <span class="m-menu__link-text">
													日志管理
												</span>
                                </a></shiro:hasPermission>
                        </li>
                    </ul>
                </div>
            </li>
        </div>


    </div>
    <!-- END: Aside Menu -->
</div>
<!-- END: Left Aside -->
<script>
    var menuSelectItem=getCookie("kuyunMenuSelectItem");
    $(document).ready(function() {
        if(!menuSelectItem || menuSelectItem==""){  //default
            $("#menu").append($("#menuDashboard").html()).append($("#menu_equiOpList").html());
        }else{
            selectedInit(menuSelectItem);
        }
    });



</script>
