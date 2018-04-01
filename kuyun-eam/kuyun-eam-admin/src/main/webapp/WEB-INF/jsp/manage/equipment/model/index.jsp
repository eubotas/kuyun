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
												设备模型
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
			<div class="col-xl-3 col-lg-4">
				<div class="m-portlet m-portlet--full-height  ">
					<div class="m-portlet__head">
						<div class="m-portlet__head-caption">
							<div class="m-portlet__head-title">
								<h1 class="m-portlet__head-text">
									设备模型
								</h1>
							</div>
						</div>
						<div class="m-portlet__head-tools">
							<ul class="m-portlet__nav">
								<li class="m-portlet__nav-item">
									<a href="#" class="btn btn-outline-metal m-btn m-btn--icon m-btn--icon-only" title="新建">
										<i class="la la-plus"></i>
									</a>
								</li>
							</ul>
						</div>
					</div>

					<div class="m-portlet__body">
						<ul class="m-nav m-nav--hover-bg m-portlet-fit--sides" id="models">
						</ul>
						<div class="m-portlet__body-separator"></div>

					</div>
				</div>
			</div>
			<div class="col-xl-9 col-lg-8">
				<div class="m-portlet m-portlet--full-height">
					<div class="m-portlet__head">
						<div class="m-portlet__head-caption">
							<div class="m-portlet__head-title">
												<span class="m-portlet__head-icon">
													<i class="flaticon-multimedia"></i>
												</span>
								<h3 class="m-portlet__head-text">
									设备模型参数
								</h3>
							</div>
						</div>
					</div>


					<div class="m-portlet__body">

						<div id="toolbar">
							<div>
								<span>&nbsp;</span>
                                <shiro:hasPermission name="eam:equipmentModel:create">
								<button id="createButton" class="btn btn-outline-primary m-btn m-btn--icon m-btn--icon-only" title="新建">
									<i class="la la-plus"></i>
								</button>
                                </shiro:hasPermission>

                                <shiro:hasPermission name="eam:equipmentModel:delete">
								<button id="deleteButton" class="btn btn-outline-danger m-btn m-btn--icon m-btn--icon-only" title="删除">
									<i class="la la-remove"></i>
								</button>
                                </shiro:hasPermission>
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
	<div id="addModelPropertyFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		 aria-hidden="true">
	</div>

	<div id="editModelPropertyFormContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		 aria-hidden="true">
	</div>

	<div class="modal fade" id="template-modelProperty-addEditForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		 aria-hidden="true">
		<form id="templateID_Form" class="m-form m-form--fit m-form--label-align-right">
			<input id="equipmentModelId" type="hidden" name="equipmentModelId" value="">

			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">
							templateTitleName_设备模型
						</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group m-form__group row">
							<label for="templateID_name" class="form-control-label">
								参数名称:*
							</label>
							<div class="col-6">
								<input type="text" class="form-control" id="templateID_name" name="name">
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
	<!--end::Modal-->

</content>


<pageResources>


<script>
    $(document).ready(function()
    {
        EquipmentModels.init();
        generateAddEditForm('template-modelProperty-addEditForm', 'add_,edit_', null, null, 'addModelPropertyFormContainer,editModelPropertyFormContainer');
        FormWidgets.init('add');
        FormWidgets.init('edit');


        $('#createButton').click(function(){
           var equipmentModelId = $('#equipmentModelId').val();
           if (equipmentModelId == ''){
               swWarn("请选择一个设备模型");
		   }else {
               $("#addModelPropertyFormContainer").modal("show");
		   }
        });

        $('#deleteButton').click(function(){
            deleteAction();
        });

        $("#add_dataType_analog, #add_dataType_digital").change(function () {

            if ($("#add_dataType_analog").is(":checked")) {
                console.log("select analog");
                $('#add_displayType').show();
            }

            if ($("#add_dataType_digital").is(":checked")) {
                console.log("select digital");
                $('input[type="radio"][name="displayType"]').prop('checked', false);
                $('#add_displayType').hide();
            }

        });

        $("#edit_dataType_analog, #edit_dataType_digital").change(function () {

            if ($("#add_dataType_analog, #edit_dataType_analog").is(":checked")) {
                console.log("select analog");
                $('#edit_displayType').show();
            }

            if ($("#add_dataType_digital, #edit_dataType_digital").is(":checked")) {
                console.log("select digital");
                $('input[type="radio"][name="displayType"]').prop('checked', false);
                $('#edit_displayType').hide();
            }

        });

    });


    var EquipmentModels = function () {
        var getEquipmentModels = function () {

            ajaxGet('${basePath}/manage/equipment/model/list', function (responseData) {
				if (responseData) {
					var data = responseData;

                    $.each(data.rows, function(index, row) {

                        var html = '<li class="m-nav__item">' +
                            '<a href="javascript:void(0)" class="m-nav__link" onclick="showModelPropertis(' + row.equipmentModelId +')"> ' +
                            '<span class="m-nav__link-text">' + row.name + '</span>' +
                            '</a></li>';

                        $("#models").append(html);

                    });

				}
			});

		}

        return {
            // public functions
            init: function () {
                getEquipmentModels();
            }
        };
	}();

    function showModelPropertis(id) {
        $('#equipmentModelId').val(id);
        refreshTable();
    }

    function refreshTable() {
        var id = $('#equipmentModelId').val();
        var opt = {
            url: "${basePath}/manage/equipment/model/property/list/"+id
        };

        $table.bootstrapTable('refresh', opt);
    }

    var $table = $('#table');
    $(function() {
        // bootstrap table初始化
        $table.bootstrapTable({
            url: '${basePath}/manage/equipment/model/property/list/-1',
            striped: true,
            search: true,
            searchAlign: 'right',
            toolbarAlign: 'right',
            minimumCountColumns: 2,
            clickToSelect: true,
            detailView: true,
            detailFormatter: 'detailFormatter',
            pagination: true,
            paginationLoop: false,
            sidePagination: 'server',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            maintainSelected: true,
            idField: 'equipmentModelId',
            columns: [
                {field: 'ck', checkbox: true},
                {field: 'equipmentModelId', title: '设备模型ID', sortable: true, align: 'center'},
                {field: 'name', title: '设备模型名称'},
                {field: 'number', title: '设备模型编号'},
                {field: 'createTime', title: '创建时间', formatter: 'timeFormatter'},
                {field: 'action', width: 150, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
            ]
        });
    });
    // 格式化操作按钮
    function actionFormatter(value, row, index) {
        return [
            '<a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a>',
            '<a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-success m-btn--icon m-btn--icon-only m-btn--pill" title="读写指令">	<i class="la la-recycle"></i>	</a>',
            '<a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="报警设定">	<i class="la la-bell"></i>	</a>'
        ].join('');
    }

    var FormWidgets = function () {
        var createForm = function (formid) {
            $("#"+formid+"_Form").validate({
                // define validation rules
                rules: {
                    name: {
                        required: true,
                        minlength: 2,
                        maxlength: 20
                    },
                    unit: {
                        required: true,
                        minlength: 1,
                        maxlength: 20
                    },
                },
                submitHandler: function (form) {
                    if(formid == 'add')
                        submitForm();
                    else{
                        submitForm($('#edit_id').val());
                    }

                }
            });
        }

        return {
            // public functions
            init: function (formid) {
                createForm(formid);
            }
        };
    }();

    function submitForm(id) {
        var targetUrl='${basePath}/manage/equipment/model/create';
        var formId='add_Form';
        if(id){
            targetUrl='${basePath}/manage/equipment/model/update/'+id;
            formId='edit_Form';
        }

        ajaxPost(targetUrl, formId, function(result) {
            if (result.code != 1) {
                sendErrorInfo(result);
            } else {
                if(formId=='add_Form') {
                    toastr.success("新建设备模型参数成功");
                    $('#addModelPropertyFormContainer').modal('toggle');
                }else{
                    toastr.success("编辑设备模型参数成功");
                    $('#editModelPropertyFormContainer').modal('toggle');
                }
                refreshTable();
            }
        });
    }

    function updateAction(row) {
        $("#editModelPropertyFormContainer").modal("show");

        ajaxGet('${basePath}/manage/equipment/model/update/' + row["equipmentModelPropertyId"], function (responseData) {
            if (responseData) {
                var data = responseData.equipmentModelProperties;
                // 赋值
                setModelProperty(data);
            }
        });
    }

    function setModelProperty(data) {
        $("#edit_id").val(data.equipmentModelPropertyId);
        $("#edit_name").val(data.name);
        $("#edit_unit").val(data.unit);

        if ('analog' == data.dataType) {
            $("#edit_dataType_analog").prop('checked', true);
        } else if ('digital' == data.dataType) {
            $("#edit_dataType_digital").prop('checked', true);
        }

        if ('pie' == data.displayType) {
            $("#edit_displayType_pie").prop('checked', true);
        } else if ('led' == data.displayType) {
            $("#edit_displayType_led").prop('checked', true);
        } else if ('guage' == data.displayType) {
            $("#edit_displayType_guage").prop('checked', true);
        }
    }

    window.actionEvents = {
        'click #update': function (e, value, row, index) {
            updateAction(row);

        },
        'click #delete': function (e, value, row, index) {
            var rows = new Array();
            rows.push(row);
            deleteActionImpl(rows);
        }
    };

    function deleteAction(){
        var rows = $table.bootstrapTable('getSelections');
        deleteActionImpl(rows);
    }

    function deleteActionImpl(rows) {

        if (rows.length == 0) {
            swWarn("请至少选择一条记录");
        }else {
            deleteRows(rows,'equipmentModelPropertyId','${basePath}/manage/equipment/model/delete/', "请确认要删除选中的设备模型参数吗？", "删除设备模型参数成功");
            refreshTable();
        }
    }

</script>




</pageResources>


</body>
</html>