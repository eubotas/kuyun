<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<html lang="zh-cn">
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
												首页
											</span>
						</a>
					</li>
					<li class="m-nav__separator">
						-
					</li>
					<li class="m-nav__item">
						<a href="" class="m-nav__link">
											<span class="m-nav__link-text">
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
	<div class="row">
		<div class="col-md-6 col-sm-6">
			<div class="portlet light bordered">
				<div class="portlet-body">
					<div  id="echarts_world" style="height:300px;"></div>
				</div>
			</div>
		</div>
		<div class="col-md-6 col-sm-6">
			<div class="portlet light" style="height: 100px;padding: 0">
				<div class="bg-green bg-font-green" style="width: 50%;height: 50%;float:left;border:1px solid #fff; border-radius: 4px">
					<div class="text-left" style="height: 100%;line-height:100%;width: 70%;padding: 5px 10px;float:left">
						<h5>设备数量</h5>
					</div>
					<div class="text-right" style="height: 100%;line-height:100%;width: 30%;padding: 5px 10px;float:left">
						<h5>287</h5>
					</div>
				</div>
				<div class="bg-green bg-font-green" style="width: 50%;height: 50%;float:left;border:1px solid #fff; border-radius: 4px">
					<div class="text-left" style="height: 100%;line-height:100%;width: 70%;padding: 5px 10px;float:left">
						<h5>用户数量</h5>
					</div>
					<div class="text-right" style="height: 100%;line-height:100%;width: 30%;padding: 5px 10px;float:left">
						<h5>24</h5>
					</div>
				</div>
				<div class="bg-green bg-font-green" style="width: 50%;height: 50%;float:left;border:1px solid #fff; border-radius: 4px">
					<div class="text-left" style="height: 100%;line-height:100%;width: 70%;padding: 5px 10px;float:left">
						<h5>报警提醒数量</h5>
					</div>
					<div class="text-right" style="height: 100%;line-height:100%;width: 30%;padding: 5px 10px;float:left">
						<h5>19</h5>
					</div>
				</div>
				<div class="bg-green bg-font-green" style="width: 50%;height: 50%;float:left;border:1px solid #fff; border-radius: 4px">
					<div class="text-left" style="height: 100%;line-height:100%;width: 70%;padding: 5px 10px;float:left">
						<h5>总产量</h5>
					</div>
					<div class="text-right" style="height: 100%;line-height:100%;width: 30%;padding: 5px 10px;float:left">
						<h5>23546</h5>
					</div>
				</div>
			</div>

			<div class="row portlet light bordered" style="width:100%; height: 217px;padding: 0; margin:-5px 0">
				<div id="echarts_pie1" style="height: 100%; width: 50%;border:1px solid #fff; border-radius: 4px">
				</div>
				<div id="echarts_pie2" style="height: 100%; width: 49%;border:1px solid #fff; border-radius: 4px">
				</div>
			</div>

		</div>
	</div>


	<div class="row">
		<div class="col-lg-6 col-md-6 col-sm-6">
			<div class="portlet light bordered" style="padding: 5px">
				<div class="portlet-body">
					<div id="echarts_line"  style="height:360px;"></div>
					<div class='actions text-center' style="height:40px;">
						<a href="#" role="button" class="btn green" ng-click="checkoption3()">当天能耗 </a>
						<a href="#" role="button" class="btn purple-medium" ng-click="checkoption2()">当月能耗 </a>
						<a href="#" role="button" class="btn red-sunglo" ng-click="checkoption1()">当年能耗 </a>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-6 col-md-6 col-sm-6">
			<div class="portlet light bordered" style="padding: 5px">
				<div class="portlet-body">
					<div id="echarts_bar1"  style="height:400px;"></div>
				</div>
			</div>
		</div>
		<div class="col-lg-6 col-md-6 col-sm-6">
			<div class="portlet light bordered" style="padding: 5px">
				<div class="portlet-body">
					<div  id="echarts_bar2" style="height:400px;"></div>
				</div>
			</div>
		</div>
		<div class="col-lg-6 col-md-6 col-sm-6">
			<div class="portlet light bordered" style="padding: 5px; height:420px">
				<div class="portlet-title" >
					<div class="caption">
						<i class="fa fa-warning"></i>故障报警 </div>
					<div class="tools"> </div>
				</div>
				<div class="portlet-body" >
					<table class="table table-striped table-bordered table-hover" id="table_Alarm_1">
						<thead style="padding-top: -10px">
						<tr>
							<th> 报警类容 </th>
							<th> 设备编号 </th>
							<th> 设备名称 </th>
							<th> 报警时间 </th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td> 电压过高 </td>
							<td> NS00983 </td>
							<td> 空压机 </td>
							<td> 2017-06-03 12:12:30 </td>
						</tr>
						<tr>
							<td> 电压过高 </td>
							<td> NS00983 </td>
							<td> 空压机 </td>
							<td> 2017-06-04 16:24:12 </td>
						</tr>
						<tr>
							<td> 电压过高 </td>
							<td> NS00983 </td>
							<td> 空压机 </td>
							<td> 2017-06-04 19:45:39 </td>
						</tr>
						<tr>
							<td> 电压过高 </td>
							<td> NS00983 </td>
							<td> 空压机 </td>
							<td> 2017-06-05 6:38:03 </td>
						</tr>
						<tr>
							<td> 电压过高 </td>
							<td> NS00983 </td>
							<td> 空压机 </td>
							<td> 2017-06-06 13:31:28 </td>
						</tr>
						<tr>
							<td> 电压过高 </td>
							<td> NS00983 </td>
							<td> 空压机 </td>
							<td> 2017-06-06 18:16:40 </td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>

	</div>

	<script src="${basePath}/resources/metronic-admin/assets/js/echarts/echarts.min.js"></script>
	<script src="http://gallery.echartsjs.com/dep/echarts/map/js/world.js"></script>
	<script src="${basePath}/resources/metronic-admin/assets/kuyun/home.js"></script>
</content>

</body>
</html>