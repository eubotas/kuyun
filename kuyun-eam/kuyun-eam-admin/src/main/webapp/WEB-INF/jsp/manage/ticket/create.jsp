<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<div id="createDialog" class="crudDialog">
	<form id="createForm" method="post">

		<div class="row">
			<div class="col-sm-12">
                <label for="description">工单描述</label>
				<div class="form-group">
					<textarea id="description" class="form-control" name="description"
						maxlength="200" rows="4"></textarea>

				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-6">
				<label for="ticketTypeId">工单类型</label>
				<div class="form-group">
					<div class="fg-line">
						<select id="ticketTypeId" name="ticketTypeId" style="width: 100%">
							<%--<option value="0">设备模型</option>--%>
							<c:forEach var="ticketType" items="${ticketTypes}">
								<option value="${ticketType.id}">${ticketType.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<div class="col-sm-6">
                <label for="equipmentCategoryId">产品目录</label>
				<div class="form-group">
					<select id="equipmentCategoryId" name="equipmentCategoryId" style="width: 100%">
						<%--<option value="0">设备模型</option>--%>
						<c:forEach var="equipmentCategory" items="${equipmentCategorys}">
							<option value="${equipmentCategory.equipmentCategoryId}">${equipmentCategory.name}</option>
						</c:forEach>
					</select>
					</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<label for="equipmentId">产品型号</label>
				<div class="form-group">
					<div class="fg-line">
						<select id="equipmentId" name="equipmentId" style="width: 100%">
							<%--<option value="0">设备模型</option>--%>
							<c:forEach var="equipment" items="${equipments}">
								<option value="${equipment.equipmentId}">${equipment.name}</option>
							</c:forEach>

						</select>
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<label for="priority">优先级</label>
				<div class="form-group">
					<div class="fg-line">
						<select id="priority"
							name="priority" style="width: 100%">
							<option value="一般">一般</option>
							<option value="紧急">紧急</option>
							<option value="非常紧急">非常紧急</option>

						</select>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<%--<div class="col-sm-6">
				<label for="executorId">执行人</label>
				<div class="form-group">
					<div class="fg-line">
						<select id="executorId" name="executorId" style="width: 100%">
							&lt;%&ndash;<option value="0">设备模型</option>&ndash;%&gt;
							<c:forEach var="user" items="${users}">
								<option value="${user.userId}">${user.realname}</option>
							</c:forEach>
						</select>
					</div>
				</div>--%>
			</div>
			<div class="col-sm-6">
				<label for="endDate">指定完成日期</label>
				<div class="form-group">
                    <div class="input-append date form_datetime">
                        <input size="16" type="text" id="endDate" name="endDate" value="">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<div id="fine-uploader-gallery"></div>
				<input id="imagePath" type="hidden" class="form-control"
					name="imagePath" maxlength="500">
			</div>
			<div class="col-sm-6">
				<div id="fine-uploader-gallery2"></div>
				<input id="voicePath" type="hidden" class="form-control"
					   name="voicePath" maxlength="500">
			</div>
		</div>

		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;"
				onclick="createSubmit();">保存</a> <a
				class="waves-effect waves-button" href="javascript:;"
				onclick="createDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>
//    $(".form_datetime").datetimepicker({
//        format: "yyyy/MM/dd",
//        language: 'zh-CN',
//        pickDate: true,
//        pickTime: false,
//        inputMask: true,
//        pickerPosition: "bottom-left",
//        autoclose: true
//    });

	var galleryUploader = new qq.FineUploader(
			{
				element : document.getElementById("fine-uploader-gallery"),
				template : 'qq-template-gallery',
				request : {
					endpoint : '${uploadServer.endpoint_upload}',
					params : {
						kuyunModule : "eam"
					}
				},
				thumbnails : {
					placeholders : {
						waitingPath : '${basePath}/resources/kuyun-admin/plugins/fileupload/placeholders/waiting-generic.png',
						notAvailablePath : '${basePath}/resources/kuyun-admin/plugins/fileupload/placeholders/not_available-generic.png'
					}
				},
				validation : {
				/*  allowedExtensions: ['jpeg', 'jpg', 'gif', 'png'] */
				},
				chunking : {
					enabled : true,
					concurrent : {
						enabled : true
					},
					success : {
						endpoint : '${uploadServer.endpoint_uploadDone}'
					},
					mandatory : true
				},
				deleteFile : {
					enabled : true,
					forceConfirm : true,
					endpoint : '${uploadServer.endpoint_delete}'
				},
				cors : {
					//all requests are expected to be cross-domain requests
					expected : true,

					//if you want cookies to be sent along with the request
					sendCredentials : true
				}
			/* init file list
			session:{
					endpoint: '${uploadServer.endpoint_list}?ids=${uuids}'
			}, */
			});
</script>
<script>
	function createSubmit() {
		var uploads = galleryUploader.getUploads({
			status : qq.status.UPLOAD_SUCCESSFUL
		});
		var fileUuids = '';
		for (var i = 0; i < uploads.length; i++) {
			fileUuids = fileUuids + uploads[i].uuid + ",";
		}
		console.log("fileUuids = " + fileUuids);
		$('#imagePath').val(fileUuids);
		console.log("imagePage1 value:" + $('#imagePath').val());
		$
				.ajax({
					type : 'post',
					url : '${basePath}/manage/ticket/create',
					data : $('#createForm').serialize(),
					beforeSend : function() {
						if ($('#description').val() == '') {
							$('#description').focus();
							return false;
						}
					},
					success : function(result) {
						if (result.code != 1) {
							if (result.data instanceof Array) {
								$
										.each(
												result.data,
												function(index, value) {
													$
															.confirm({
																theme : 'dark',
																animation : 'rotateX',
																closeAnimation : 'rotateX',
																title : false,
																content : value.errorMsg,
																buttons : {
																	confirm : {
																		text : '确认',
																		btnClass : 'waves-effect waves-button waves-light'
																	}
																}
															});
												});
							} else {
								$
										.confirm({
											theme : 'dark',
											animation : 'rotateX',
											closeAnimation : 'rotateX',
											title : false,
											content : result.data.errorMsg,
											buttons : {
												confirm : {
													text : '确认',
													btnClass : 'waves-effect waves-button waves-light'
												}
											}
										});
							}
						} else {
							createDialog.close();
							$table.bootstrapTable('refresh');
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$
								.confirm({
									theme : 'dark',
									animation : 'rotateX',
									closeAnimation : 'rotateX',
									title : false,
									content : textStatus,
									buttons : {
										confirm : {
											text : '确认',
											btnClass : 'waves-effect waves-button waves-light'
										}
									}
								});
					}
				});
	}
</script>