﻿﻿<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
												工单统计
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
                                    统计类别
                                </h1>
                            </div>
                        </div>
                    </div>

                    <div id="ticketList" class="m-portlet__body">
                        <ul class="m-nav m-nav--hover-bg m-portlet-fit--sides" id="models">
                            <li id="all" class="m-nav__item"><a href="javascript:;" onclick="toAction('all');" class="m-nav__link"> <span class="m-nav__link-text">累计报修(${ticketSummaryVo.totalTicketCount})</span></a></li>
                            <li id="init" class="m-nav__item"><a href="javascript:;" onclick="toAction('init');" class="m-nav__link"> <span class="m-nav__link-text">待派工(${ticketSummaryVo.noAppointTicketCount})</span></a></li>
                            <li id="processing" class="m-nav__item"><a href="javascript:;" onclick="toAction('processing');" class="m-nav__link"> <span class="m-nav__link-text">维修中(${ticketSummaryVo.processingTicketCount})</span></a></li>
                            <li id="notResolved" class="m-nav__item"><a href="javascript:;" onclick="toAction('notResolved');" class="m-nav__link"> <span class="m-nav__link-text">未完成(${ticketSummaryVo.notResolvedTicketCount})</span></a></li>
                            <li id="resolved" class="m-nav__item"><a href="javascript:;" onclick="toAction('resolved');" class="m-nav__link"> <span class="m-nav__link-text">已完成(${ticketSummaryVo.resolvedTicketCount})</span></a></li>
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
                        <table id="table" data-toolbar="#toolbar"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>

</content>

<pageResources>
<script>
    $(document).ready(function() {
        selectedItemColor("ticketList", "${category}");
    });

    function toAction(type){
        window.location = '${basePath}/manage/ticket/summary?category='+ type;
    }

    var $table = $('#table');
    $(function() {
        $table.bootstrapTable({
            url: '${basePath}/manage/ticket/list?category=${category}',
            striped: true,
            search: true,
            searchAlign: 'left',
            toolbarAlign: 'right',
            minimumCountColumns: 2,
            clickToSelect: true,
            pagination: true,
            paginationLoop: false,
            sidePagination: 'server',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            maintainSelected: true,
            idField: 'ticketId',
            columns: [
                {field: 'ck', checkbox: true},
                {field: 'ticketNumber', title: '工单编号', align: 'center'},
                {field: 'serviceman', title: '维修人',  align: 'center'},
                {field: 'servicePhone', title: '维修人电话',  align: 'center'},
                {field: 'customerContacts', title: '提报人',  align: 'center'},
                {field: 'customerPhone', title: '提报人电话',  align: 'center'},
                {field: 'status', title: '当前状态',  align: 'center', formatter: 'formatStatus'},
                {field: 'description', title: '工单描述' },
                {field: 'action', width: 100, title: '详细', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
            ]
        });
        setSearchPlaceholder('工单编号');
    });

    function actionFormatter(value, row, index) {
        return [
            '<a id="detail" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="详细">	<i class="la flaticon-file-1"></i>	</a>',
        ].join('');
    }

    window.actionEvents = {
        'click #detail': function (e, value, row, index) {
            var ticketId = row["ticketId"];
            window.location = '${basePath}/manage/ticket/detail/' +ticketId;
        }
    };
</script>
</pageResources>
</body>
</html>