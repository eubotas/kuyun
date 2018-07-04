<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.kuyun.common.util.BasePath" %>
<%@ page import="com.kuyun.eam.admin.util.SessionUser" %>
<%@ page contentType="text/html; charset=utf-8"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<c:set var="eamPath" value="<%=BasePath.getInstance().getKuyunEamAdmin(request)%>"/>
<c:set var="upmsPath" value="<%=BasePath.getInstance().getKuyunUpmsServer()%>"/>
<c:set var="user" value="<%=(new SessionUser()).getUser(request)%>"/>

<!-- BEGIN: Topbar -->
<div id="m_header_topbar" class="m-topbar  m-stack m-stack--ver m-stack--general">
    <div class="m-stack__item m-topbar__nav-wrapper">
        <ul class="m-topbar__nav m-nav m-nav--inline">
            <%--<li class="--%>
	<%--m-nav__item m-dropdown m-dropdown--large m-dropdown--arrow m-dropdown--align-center m-dropdown--mobile-full-width m-dropdown--skin-light	m-list-search m-list-search--skin-light" data-dropdown-toggle="click" data-dropdown-persistent="true" id="m_quicksearch" data-search-type="dropdown">--%>
                <%--<a href="#" class="m-nav__link m-dropdown__toggle">--%>
												<%--<span class="m-nav__link-icon">--%>
													<%--<i class="flaticon-search-1"></i>--%>
												<%--</span>--%>
                <%--</a>--%>
                <%--<div class="m-dropdown__wrapper">--%>
                    <%--<span class="m-dropdown__arrow m-dropdown__arrow--center"></span>--%>
                    <%--<div class="m-dropdown__inner ">--%>
                        <%--<div class="m-dropdown__header">--%>
                            <%--<form class="m-list-search__form">--%>
                                <%--<div class="m-list-search__form-wrapper">--%>
                                                    <%--<span class="m-list-search__form-input-wrapper">--%>
																	<%--<input id="m_quicksearch_input" autocomplete="off" type="text" name="q" class="m-list-search__form-input" value="" placeholder="知识搜索...">--%>
																<%--</span>--%>
                                    <%--<span class="m-list-search__form-icon-close" id="m_quicksearch_close">--%>
																	<%--<i class="la la-remove"></i>--%>
																<%--</span>--%>
                                <%--</div>--%>
                            <%--</form>--%>
                        <%--</div>--%>
                        <%--<div class="m-dropdown__body">--%>
                            <%--<div class="m-dropdown__scrollable m-scrollable" data-max-height="300" data-mobile-max-height="200">--%>
                                <%--<div class="m-dropdown__content"></div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</li>--%>
            <li class="m-nav__item m-topbar__notifications m-topbar__notifications--img m-dropdown m-dropdown--large m-dropdown--header-bg-fill m-dropdown--arrow m-dropdown--align-center 	m-dropdown--mobile-full-width" data-dropdown-toggle="click" data-dropdown-persistent="true">
                <a href="#" class="m-nav__link m-dropdown__toggle" id="m_topbar_notification_icon">
                    <span id="redDot" class="m-nav__link-badge"></span>
                    <span class="m-nav__link-icon">
													<i class="flaticon-music-2"></i>
												</span>
                </a>
                <div class="m-dropdown__wrapper">
                    <span class="m-dropdown__arrow m-dropdown__arrow--center"></span>
                    <div class="m-dropdown__inner">
                        <div class="m-dropdown__header m--align-center" id="alarmTitle" style="background: url(${basePath}/resources/metronic-admin/assets/app/media/img/misc/notification_bg.jpg); background-size: cover;">

                        </div>
                        <div class="m-dropdown__body">
                            <div class="m-dropdown__content">

                                <div class="tab-content">
                                    <div class="tab-pane active" id="topbar_notifications_notifications" role="tabpanel">
                                        <div class="m-scrollable" data-scrollable="true" data-max-height="250" data-mobile-max-height="200">
                                            <div class="m-list-timeline m-list-timeline--skin-light">
                                                <div class="m-list-timeline__items" id="alarmList">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <%--<li class="m-nav__item m-topbar__quick-actions m-topbar__quick-actions--img m-dropdown m-dropdown--large m-dropdown--header-bg-fill m-dropdown--arrow m-dropdown--align-right m-dropdown--align-push m-dropdown--mobile-full-width m-dropdown--skin-light" data-dropdown-toggle="click">--%>
                <%--<a href="#" class="m-nav__link m-dropdown__toggle">--%>
                    <%--<span class="m-nav__link-badge m-badge m-badge--dot m-badge--info m--hide"></span>--%>
                    <%--<span class="m-nav__link-icon">--%>
													<%--<i class="flaticon-share"></i>--%>
												<%--</span>--%>
                <%--</a>--%>
                <%--<div class="m-dropdown__wrapper">--%>
                    <%--<span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>--%>
                    <%--<div class="m-dropdown__inner">--%>
                        <%--<div class="m-dropdown__header m--align-center" style="background: url(${basePath}/resources/metronic-admin/assets/app/media/img/misc/quick_actions_bg.jpg); background-size: cover;">--%>
                                            <%--<span class="m-dropdown__header-title">--%>
															<%--快捷入口--%>
														<%--</span>--%>
                        <%--</div>--%>
                        <%--<div class="m-dropdown__body m-dropdown__body--paddingless">--%>
                            <%--<div class="m-dropdown__content">--%>
                                <%--<div class="m-scrollable" data-scrollable="false" data-max-height="380" data-mobile-max-height="200">--%>
                                    <%--<div class="m-nav-grid m-nav-grid--skin-light">--%>
                                        <%--<div class="m-nav-grid__row">--%>
                                            <%--<a href="#" class="m-nav-grid__item">--%>
                                                <%--<i class="m-nav-grid__icon flaticon-file"></i>--%>
                                                <%--<span class="m-nav-grid__text">--%>
																				<%--提报工单--%>
																			<%--</span>--%>
                                            <%--</a>--%>
                                            <%--<a href="#" class="m-nav-grid__item">--%>
                                                <%--<i class="m-nav-grid__icon flaticon-time"></i>--%>
                                                <%--<span class="m-nav-grid__text">--%>
																				<%--新增用户--%>
																			<%--</span>--%>
                                            <%--</a>--%>
                                        <%--</div>--%>
                                        <%--<div class="m-nav-grid__row">--%>
                                            <%--<a href="#" class="m-nav-grid__item">--%>
                                                <%--<i class="m-nav-grid__icon flaticon-folder"></i>--%>
                                                <%--<span class="m-nav-grid__text">--%>
																				<%--Create New Task--%>
																			<%--</span>--%>
                                            <%--</a>--%>
                                            <%--<a href="#" class="m-nav-grid__item">--%>
                                                <%--<i class="m-nav-grid__icon flaticon-clipboard"></i>--%>
                                                <%--<span class="m-nav-grid__text">--%>
																				<%--Completed Tasks--%>
																			<%--</span>--%>
                                            <%--</a>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</li>--%>
            <li class="m-nav__item m-topbar__user-profile m-topbar__user-profile--img  m-dropdown m-dropdown--medium m-dropdown--arrow m-dropdown--header-bg-fill m-dropdown--align-right m-dropdown--mobile-full-width m-dropdown--skin-light" data-dropdown-toggle="click">
                <a href="#" class="m-nav__link m-dropdown__toggle">
												<span class="m-topbar__userpic">
                                                    <c:if test="${ not empty user.avatar }">
                                                        <img src="${basePath}${user.avatar}" class="m--img-rounded m--marginless m--img-centered" alt="My profile"/>
                                                    </c:if>
                                                    <c:if test="${empty user.avatar }">
													    <img src="${basePath}/resources/metronic-admin/assets/app/media/img/users/user4.jpg" class="m--img-rounded m--marginless m--img-centered" alt="My profile"/>
                                                    </c:if>
												</span>
                                        <span class="m-topbar__username m--hide">
                                                ${user.realname}
												</span>
                </a>
                <div class="m-dropdown__wrapper">
                    <span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>
                    <div class="m-dropdown__inner">
                        <div class="m-dropdown__header m--align-center" style="background: url(${basePath}/resources/metronic-admin/assets/app/media/img/misc/user_profile_bg.jpg); background-size: cover;">
                            <div class="m-card-user m-card-user--skin-dark">
                                <div class="m-card-user__pic">
                                    <c:if test="${ not empty user.avatar }">
                                        <img src="${basePath}${user.avatar}" class="m--img-rounded m--marginless" alt="My profile"/>
                                    </c:if>
                                    <c:if test="${empty user.avatar }">
                                        <img src="${basePath}/resources/metronic-admin/assets/app/media/img/users/user4.jpg" class="m--img-rounded m--marginless" alt="My profile"/>
                                    </c:if>
                                </div>
                                <div class="m-card-user__details">
                                                    <span class="m-card-user__name m--font-weight-500">
																	${user.realname}
																</span>
                                    <a href="" class="m-card-user__email m--font-weight-300 m-link">
                                        ${user.email}
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="m-dropdown__body">
                            <div class="m-dropdown__content">
                                <ul class="m-nav m-nav--skin-light">
                                    <li class="m-nav__section m--hide">
                                                        <span class="m-nav__section-text">
																		Section
																	</span>
                                    </li>
                                    <li class="m-nav__item">
                                        <a href="${upmsPath}/manage/user/modify/${user.userId}" class="m-nav__link">
                                            <i class="m-nav__link-icon flaticon-profile-1"></i>
                                            <span class="m-nav__link-title">
																			<span class="m-nav__link-wrap">
																				<span class="m-nav__link-text">
																					我的资料
																				</span>
																			</span>
																		</span>
                                        </a>
                                    </li>
                                    <li class="m-nav__separator m-nav__separator--fit"></li>
                                    <li class="m-nav__item">
                                        <a href="${upmsPath}/sso/logout" class="btn m-btn--pill    btn-secondary m-btn m-btn--custom m-btn--label-brand m-btn--bolder">
                                            退出
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </li>

        </ul>
    </div>
</div>
<!-- END: Topbar -->

<script src="${basePath}/resources/kuyun-admin/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/metronic-admin/assets/app/js/refresh_alarm.js"></script>
<script>
    var eamPath = "${eamPath}";
    var refreshTime =5000;
    refreshAlarm();
    setInterval(function(){ refreshAlarm(); }, refreshTime);

</script>
