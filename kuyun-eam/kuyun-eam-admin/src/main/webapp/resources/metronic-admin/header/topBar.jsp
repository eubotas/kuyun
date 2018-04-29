<%@ page import="com.kuyun.upms.common.constant.UpmsConstant" %>
<%@ page import="com.kuyun.common.util.BasePath" %>
<%@ page contentType="text/html; charset=utf-8"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<c:set var="upmsPath" value="<%=BasePath.kuyunUpmsServer%>"/>

<style>
    .m-list-timeline__items .m-list-timeline__item .m-list-timeline__text{
        display: table-cell;
        text-align: left;
        vertical-align: middle;
        width: 20% ;
        padding: 0 5px 0 0;
        font-size: 1rem;
    }
</style>
<!-- BEGIN: Topbar -->
<div id="m_header_topbar" class="m-topbar  m-stack m-stack--ver m-stack--general">
    <div class="m-stack__item m-topbar__nav-wrapper">
        <ul class="m-topbar__nav m-nav m-nav--inline">
            <li class="
	m-nav__item m-dropdown m-dropdown--large m-dropdown--arrow m-dropdown--align-center m-dropdown--mobile-full-width m-dropdown--skin-light	m-list-search m-list-search--skin-light" data-dropdown-toggle="click" data-dropdown-persistent="true" id="m_quicksearch" data-search-type="dropdown">
                <a href="#" class="m-nav__link m-dropdown__toggle">
												<span class="m-nav__link-icon">
													<i class="flaticon-search-1"></i>
												</span>
                </a>
                <div class="m-dropdown__wrapper">
                    <span class="m-dropdown__arrow m-dropdown__arrow--center"></span>
                    <div class="m-dropdown__inner ">
                        <div class="m-dropdown__header">
                            <form class="m-list-search__form">
                                <div class="m-list-search__form-wrapper">
                                                    <span class="m-list-search__form-input-wrapper">
																	<input id="m_quicksearch_input" autocomplete="off" type="text" name="q" class="m-list-search__form-input" value="" placeholder="Search...">
																</span>
                                    <span class="m-list-search__form-icon-close" id="m_quicksearch_close">
																	<i class="la la-remove"></i>
																</span>
                                </div>
                            </form>
                        </div>
                        <div class="m-dropdown__body">
                            <div class="m-dropdown__scrollable m-scrollable" data-max-height="300" data-mobile-max-height="200">
                                <div class="m-dropdown__content"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <li class="m-nav__item m-topbar__notifications m-topbar__notifications--img m-dropdown m-dropdown--large m-dropdown--header-bg-fill m-dropdown--arrow m-dropdown--align-center 	m-dropdown--mobile-full-width" data-dropdown-toggle="click" data-dropdown-persistent="true">
                <a href="#" class="m-nav__link m-dropdown__toggle" id="m_topbar_notification_icon">
                    <span class="m-nav__link-badge m-badge m-badge--dot m-badge--dot-small m-badge--danger"></span>
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
            <li class="m-nav__item m-topbar__quick-actions m-topbar__quick-actions--img m-dropdown m-dropdown--large m-dropdown--header-bg-fill m-dropdown--arrow m-dropdown--align-right m-dropdown--align-push m-dropdown--mobile-full-width m-dropdown--skin-light" data-dropdown-toggle="click">
                <a href="#" class="m-nav__link m-dropdown__toggle">
                    <span class="m-nav__link-badge m-badge m-badge--dot m-badge--info m--hide"></span>
                    <span class="m-nav__link-icon">
													<i class="flaticon-share"></i>
												</span>
                </a>
                <div class="m-dropdown__wrapper">
                    <span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>
                    <div class="m-dropdown__inner">
                        <div class="m-dropdown__header m--align-center" style="background: url(${basePath}/resources/metronic-admin/assets/app/media/img/misc/quick_actions_bg.jpg); background-size: cover;">
                                            <span class="m-dropdown__header-title">
															Quick Actions
														</span>
                            <span class="m-dropdown__header-subtitle">
															Shortcuts
														</span>
                        </div>
                        <div class="m-dropdown__body m-dropdown__body--paddingless">
                            <div class="m-dropdown__content">
                                <div class="m-scrollable" data-scrollable="false" data-max-height="380" data-mobile-max-height="200">
                                    <div class="m-nav-grid m-nav-grid--skin-light">
                                        <div class="m-nav-grid__row">
                                            <a href="#" class="m-nav-grid__item">
                                                <i class="m-nav-grid__icon flaticon-file"></i>
                                                <span class="m-nav-grid__text">
																				Generate Report
																			</span>
                                            </a>
                                            <a href="#" class="m-nav-grid__item">
                                                <i class="m-nav-grid__icon flaticon-time"></i>
                                                <span class="m-nav-grid__text">
																				Add New Event
																			</span>
                                            </a>
                                        </div>
                                        <div class="m-nav-grid__row">
                                            <a href="#" class="m-nav-grid__item">
                                                <i class="m-nav-grid__icon flaticon-folder"></i>
                                                <span class="m-nav-grid__text">
																				Create New Task
																			</span>
                                            </a>
                                            <a href="#" class="m-nav-grid__item">
                                                <i class="m-nav-grid__icon flaticon-clipboard"></i>
                                                <span class="m-nav-grid__text">
																				Completed Tasks
																			</span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <li class="m-nav__item m-topbar__user-profile m-topbar__user-profile--img  m-dropdown m-dropdown--medium m-dropdown--arrow m-dropdown--header-bg-fill m-dropdown--align-right m-dropdown--mobile-full-width m-dropdown--skin-light" data-dropdown-toggle="click">
                <a href="#" class="m-nav__link m-dropdown__toggle">
												<span class="m-topbar__userpic">
													<img src="${basePath}/resources/metronic-admin/assets/app/media/img/users/user4.jpg" class="m--img-rounded m--marginless m--img-centered" alt=""/>
												</span>
                    <span class="m-topbar__username m--hide">
													Nick
												</span>
                </a>
                <div class="m-dropdown__wrapper">
                    <span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>
                    <div class="m-dropdown__inner">
                        <div class="m-dropdown__header m--align-center" style="background: url(${basePath}/resources/metronic-admin/assets/app/media/img/misc/user_profile_bg.jpg); background-size: cover;">
                            <div class="m-card-user m-card-user--skin-dark">
                                <div class="m-card-user__pic">
                                    <img src="${basePath}/resources/metronic-admin/assets/app/media/img/users/user4.jpg" class="m--img-rounded m--marginless" alt="" />
                                </div>
                                <div class="m-card-user__details">
                                                    <span class="m-card-user__name m--font-weight-500">
																	Mark Andre
																</span>
                                    <a href="" class="m-card-user__email m--font-weight-300 m-link">
                                        mark.andre@gmail.com
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
                                        <a href="#" class="m-nav__link">
                                            <i class="m-nav__link-icon flaticon-profile-1"></i>
                                            <span class="m-nav__link-title">
																			<span class="m-nav__link-wrap">
																				<span class="m-nav__link-text">
																					My Profile
																				</span>
																				<span class="m-nav__link-badge">
																					<span class="m-badge m-badge--success">
																						2
																					</span>
																				</span>
																			</span>
																		</span>
                                        </a>
                                    </li>

                                    <li class="m-nav__separator m-nav__separator--fit"></li>
                                    <li class="m-nav__item">
                                        <a href="<%=com.kuyun.common.util.BasePath.kuyunUpmsServer%>/sso/logout" class="btn m-btn--pill    btn-secondary m-btn m-btn--custom m-btn--label-brand m-btn--bolder">
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
<script>

    ajaxGet('${basePath}/manage/alarm/list', function (responseData) {
        if (responseData) {
            var total=responseData.total;
            if(total > 0) {
                $('#alarmTitle').html('<span id="topAlarmNum" class="m-dropdown__header-title">' + total +
                '条</span><span class="m-dropdown__header-subtitle">报警信息</span>');
            }

            var data = responseData.rows;
            var html ="";
            $.each(data,function(i, val) {
                html = html+ generateAlarmHtml(val);
            });
            $('#alarmList').html(html);
        }
    });


    function generateAlarmHtml(row) {
        var rowHtml=
    '<div class="m-list-timeline__item m-list-timeline__item--read">'+
        '<span class="m-list-timeline__badge"></span>'+
        '<span href="'+alarmAddr(row)+'" class="m-list-timeline__text">'+
            row.messageTitle +
        '</span>'+ alarmStatus(row.alarmStatus)+
        '<span class="m-list-timeline__time">'+
        alarmTime(row.updateTime)+
    '</span></div>';

        return rowHtml;
    }

    function alarmStatus(status){
        if('ANU'== status)
            return '<span class="m-badge m-badge--danger m-badge--wide"> 需要处理</span>';
        else if('CNU'== status)
            return '<span class="m-badge m-badge--success m-badge--wide"> 处理结束</span>';
        else
            return "";
    }

    function alarmAddr(row){
        if(row.alarmType == 'E')
            return "";
        else
            return "";
    }

    function alarmTime(time){
        if(!time)
            return "";
        var diff = ((new Date()).getTime() - time)/1000;

        if(diff/60 > 30*24*60)
            return '1月前';
        else if(diff/60 > 10*24*60)
            return '10天前';
        else if(diff/60 > 5*24*60)
            return '5天前';
        else if(diff/60 > 48*60)
            return '2天前';
        else if(diff/60 > 24*60)
            return '1天前';
        else if(diff/60 > 120)
            return '2小时前';
        else if(diff/60 > 60)
            return '1小时前';
        else if(diff/60 > 20)
            return '20分钟前';
        else if( diff/60 >5)
            return '5分钟前';
        else
            return '1分钟前';
    }


    function ajaxGet(targetUrl, callSuccess, callError)
    {
        $.ajax({
            type: 'get',
            url: targetUrl,
            success: function (result) {
                callSuccess(result);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if(callError)
                    callError(XMLHttpRequest, textStatus);
                else
                    alert(textStatus);
            }
        });
    }
</script>