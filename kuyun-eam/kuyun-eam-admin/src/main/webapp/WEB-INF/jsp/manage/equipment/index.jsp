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

<pageResources>
    <link href="${basePath}/resources/metronic-admin/assets/js/bootstrap4-editable/css/bootstrap-editable.css" rel="stylesheet"/>
    <script src="${basePath}/resources/metronic-admin/assets/js/bootstrap4-editable/js/bootstrap-editable.min.js"></script>
    <script src="${basePath}/resources/metronic-admin/assets/js/bootstrap-table.1.11.1/extensions/editable/bootstrap-table-editable.min.js"></script>

    <jsp:include page="/resources/metronic-admin/file_upload.jsp" flush="true"/>
    <script>
        $.fn.editable.defaults.mode = 'inline';
        var addGalleryUploader;
        var editGalleryUploader;

        $(document).ready(function()
        {
            //codes works on all bootstrap modal windows in application
            $('.modal').on('hidden.bs.modal', function(e)
            {
                jQuery("#add_Form").validate().resetForm();
                jQuery("#edit_Form").validate().resetForm();
            }) ;

            var provinceList;
            var allcity;
            var cityList;

            getCityData();

            generateAddEditForm('template-equipment-addEditForm', 'add_,edit_', null, null, 'addEquipmentFormContainer,editEquipmentFormContainer');
            FormWidgets.init('add');
            FormWidgets.init('edit');

            $('#add_equipmentModelId, #edit_equipmentModelId').select2({minimumResultsForSearch: -1});
            $('#add_province, #edit_province, #add_city, #edit_city').select2({minimumResultsForSearch: -1});

            $('#add_factoryDate, #add_commissioningDate, #add_warrantyStartDate, #add_warrantyEndDate, #edit_factoryDate, #edit_commissioningDate, #edit_warrantyStartDate, #edit_warrantyEndDate').datepicker({
                format: "yyyy/mm/dd",
                orientation: "top left",
                templates: {
                    leftArrow: '<i class="la la-angle-left"></i>',
                    rightArrow: '<i class="la la-angle-right"></i>'
                }
            });

            $('#createButton').click(function(){
                $("#addEquipmentFormContainer").modal("show");
                ajaxGet('${basePath}/manage/equipment/create', function (responseData) {
                    if (responseData) {
                        var data = responseData;
                        addOptionToHtmlSelect(null, "add_equipmentModelId", data.equipmentModels);
                        initProvinceOptions('add');

                    }
                });
            });

            $('#deleteButton').click(function(){
                deleteAction();
            });

            provinceChange('add');
            provinceChange('edit');

            addGalleryUploader = new qq.FineUploader($.extend(uploadOpt, {element : document.getElementById("add_fine-uploader-gallery")}));
            editGalleryUploader = new qq.FineUploader($.extend(uploadOpt, {element : document.getElementById("edit_fine-uploader-gallery")}));

        });


        function getCityData() {
            $.getJSON("/resources/data/areas.json", function(value){
                provinceList = value.province;
                allcity = value.city;
            })
        }

        function getProCity(procode) {
            cityList = [];
            if(procode!=null && procode!=''){
                $.each(allcity,
                    function(i, val) {
                        if (val.id.substr(0, 2) == procode.substr(0, 2)) {
                            cityList.push(val);
                        }
                    }
                );
            }
        }

        function provinceChange(templateId){
            $('#'+templateId+'_province').change(function() {
                getProCity(this.value);
                initCityOptions(templateId);
            });

        }

        function initProvinceOptions(templateId, code) {
            $("#"+templateId+"_province").empty();
            $("#"+templateId+"_city").empty();
            cityList = [];
            $("#"+templateId+"_province").append($("<option></option>"));

            $.each(provinceList, function(id, obj) {
                if (code === obj.id){
                    $("#"+templateId+"_province").append($("<option selected></option>").attr("value",obj.id).text(obj.text));
                    getProCity(code);
                }else {
                    $("#"+templateId+"_province").append($("<option></option>").attr("value",obj.id).text(obj.text));
                }

            });
        }

        function initCityOptions(templateId, code) {
            $("#"+templateId+"_city").empty();
            $.each(cityList, function(id, obj) {
                if (code === obj.id){
                    $("#"+templateId+"_city").append($("<option selected></option>").attr("value",obj.id).text(obj.text));
                }else {
                    $("#"+templateId+"_city").append($("<option></option>").attr("value",obj.id).text(obj.text));
                }

            });
        }

        var $table = $('#table');
        $(function() {
            // bootstrap table初始化
            $table.bootstrapTable({
                url: '${basePath}/manage/equipment/list',
                striped: true,
                search: true,
                searchAlign: 'left',
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
                idField: 'equipmentId',
                sortOrder: 'desc',
                sortName: 't.create_time',
                columns: [
                    {field: 'ck', checkbox: true},
                    {field: 'number', title: '设备编号'},
                    {field: 'name', title: '设备名称'},
                    {field: 'equipmentModelName', title: '模型'},
                    {field: 'maintenancePeriod', title: '图片'},
                    {field: 'maintenancePeriod', title: '启停'},
                    {field: 'action', width: 120, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                ]
            });
        });
        // 格式化操作按钮
        function actionFormatter(value, row, index) {
            return [
                '<shiro:hasPermission name="eam:equipment:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:equipment:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>',
                '<shiro:hasPermission name="eam:equipmentSensor:write"><a id="write" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="数据写入">	<i class="la la-send"></i>	</a></shiro:hasPermission>',
            ].join('');
        }


        var FormWidgets = function () {
            var createForm = function (formid) {
                $("#"+formid+"_Form").validate({
                    // define validation rules
                    rules: {
                        name: {
                            required: true,
                            maxlength: 30
                        },
                        number: {
                            required: true,
                            maxlength: 30
                        },
                        serialNumber: {
                            required: true,
                            maxlength: 30
                        },
                        factoryDate: {
                            required: true
                        },
                        commissioningDate: {
                            required: true
                        },
                        warrantyStartDate: {
                            required: true
                        },
                        warrantyEndDate: {
                            required: true
                        },
                    },
                    submitHandler: function (form) {
                        if(formid === 'add')
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
            var targetUrl='${basePath}/manage/equipment/create';
            var formId='add_Form';
            if(id){
                targetUrl='${basePath}/manage/equipment/update/'+id;
                formId='edit_Form';
                $('#edit_imagePath').val(getUploadFileName(editGalleryUploader));
            }else{
                $('#add_imagePath').val(getUploadFileName(addGalleryUploader));
            }


            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    if(formId=='add_Form') {
                        toastr.success("新建设备成功");
                        $('#addEquipmentFormContainer').modal('toggle');
                    }else{
                        toastr.success("编辑设备成功");
                        $('#editEquipmentFormContainer').modal('toggle');
                    }
                    $table.bootstrapTable('refresh');
                }
            });
        }


        function updateAction(row) {
            jQuery("#editEquipmentFormContainer").modal("show");
            ajaxGet('${basePath}/manage/equipment/update/' + row["equipmentId"], function (responseData) {
                if (responseData) {
                    var data = responseData.equipment;
                    addOptionToHtmlSelect(data.equipmentModelId, "edit_equipmentModelId", responseData.equipmentModels);
                    initProvinceOptions('edit', data.province);
                    initCityOptions('edit', data.city);

                    $("#edit_id").val(data.equipmentId);
                    $("#edit_name").val(data.name);
                    $("#edit_number").val(data.number);
                    $("#edit_serialNumber").val(data.serialNumber);
                    $("#edit_imagePath").val(data.imagePath);
                    $("#edit_longitude").val(data.longitude);
                    $("#edit_latitude").val(data.latitude);
                    $("#edit_factoryDate").val(changeTimeFormat(data.factoryDate));
                    $("#edit_commissioningDate").val(changeTimeFormat(data.commissioningDate));
                    $("#edit_warrantyStartDate").val(changeTimeFormat(data.warrantyStartDate));
                    $("#edit_warrantyEndDate").val(changeTimeFormat(data.warrantyEndDate));
                }
            });
        }

        function sensorAction() {
            $('#sensorDialog').modal('show');
            if(showSensorDialog){
                tableSensor.bootstrapTable( 'refresh', sensorOpt);
            }else{
                showSensorDialog = true;
                tableSensor.bootstrapTable(sensorOpt);
            }
        }

        var selectedEquipmentId, showSensorDialog;
        var tableSensor = $('#tableSensor');
        var sensorOpt={
            url: '${basePath}/manage/equipment/sensor/list',
            queryParams:function(p){
                return { equipmentId : selectedEquipmentId,
                    limit : p.limit,
                    offset: p.offset,
                    search: p.search,
                    sort:   p.sort,
                    order:  p.order
                }
            },
            striped: true,
            search: true,
            searchAlign: 'left',
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
            idField: 'equipment_model_property_id',
            columns: [
                {field: 'name', title: '名称', align: 'center'},
                {field: 'writeValue', title: '写入值', editable: {
                    type: 'text',
                    tpl: '<input type="text" style="width: 80px">',
                    validate: function (value) {
                        if ($.trim(value) == '') {
                            return '写入值不能为空!';
                        }
                    }
                }, align: 'center'}
            ],
            onEditableSave: function(field, row, oldValue, $el) {
                // field:修改的欄位
                // row:修改後的資料(JSON Object)
                // oldValue:修改前的值
                // -------------------------------------------------
                // 可用ajax方法去更新資料
                $.ajax({
                    type: "post",
                    url: '${basePath}/manage/equipment/sensor/write',
                    data: JSON.stringify(row),
                    contentType:"application/json",
                    dataType: 'JSON',
                    success: function(data, status) {
                        if (status == "success") {
                            toastr.success(data.data);
                        }
                    },
                    error: function() {
                        toastr.error('写入数据失败');
                    },
                    complete: function() {

                    }

                });
                // -------------------------------------------------
            }
        };

        window.actionEvents = {
            'click #update': function (e, value, row, index) {
                updateAction(row);

            },
            'click #write': function (e, value, row, index) {
                selectedEquipmentId = row['equipmentId'];
                sensorAction();
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
                deleteRows(rows,'equipmentId','${basePath}/manage/equipment/delete/', "请确认要删除选中的设备吗？", "删除设备成功");
            }//end else
        }

    </script>

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
                            <div id="templateID_fine-uploader-gallery" class="col-sm-9"></div>
                            <input id="templateID_imagePath" type="hidden" class="form-control" name="imagePath">
                        </div>

                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">地区:</label>
                            <div class="col-sm-4">
                                <select id="templateID_province" name="province" style="width: 100%"></select>
                            </div>

                            <div class="col-sm-4">
                                <select id="templateID_city" name="city" style="width: 100%"></select>
                            </div>
                        </div>

                        <div class="form-group m-form__group row">
                            <label class="col-lg-2 col-form-label">
                                设备位置
                            </label>
                            <div id="templateID_addDataChangeButtonDiv">
                                <div class="col-4">
                                    <button id="templateID_addDataChangeButton" class="btn btn-primary m-btn m-btn--icon">
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