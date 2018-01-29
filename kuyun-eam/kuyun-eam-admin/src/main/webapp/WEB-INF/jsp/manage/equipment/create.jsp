<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="createDialog" class="crudDialog">
	<form id="createForm" method="post">
        <input hidden name="productLineId" value="${productLineId}"/>
		<div class="row">
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="name">设备名称</label>
						<input id="name" type="text" class="form-control" name="name" maxlength="200">
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<select id="equipmentCategoryId" name="equipmentCategoryId" style="width: 100%">
							<c:forEach var="category" items="${equipmentCategories}">
								<option value="${category.equipmentCategoryId}">${category.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="number">设备型号</label>
						<input id="number" type="text" class="form-control" name="number" maxlength="500">
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="output">产量</label>
						<input id="output" type="text" class="form-control" name="output" maxlength="20">
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="dimension">外形尺寸</label>
						<input id="dimension" type="text" class="form-control" name="dimension" maxlength="20">
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="weight">设备重量</label>
						<input id="weight" type="text" class="form-control" name="weight" maxlength="20">
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="power">使用电源</label>
						<input id="power" type="text" class="form-control" name="power" maxlength="20">
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="capacity">装机容量</label>
						<input id="capacity" type="text" class="form-control" name="capacity" maxlength="20">
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="serialNumber">出厂编号</label>
						<input id="serialNumber" type="text" class="form-control" name="serialNumber" maxlength="20">
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="factoryDate">出厂日期</label>
						<input id="factoryDate" type="text" class="form-control" name="factoryDate">
					</div>
				</div>
			</div>




			<%--<div class="col-sm-6">--%>
				<%--<div class="form-group">--%>
					<%--<div class="fg-line">--%>
						<%--<label for="longitude">设备位置:经度</label>--%>
						<%--<input id="longitude" type="text" class="form-control" name="longitude" maxlength="100">--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>
			<%--<div class="col-sm-6">--%>
				<%--<div class="form-group">--%>
					<%--<div class="fg-line">--%>
						<%--<label for="latitude">纬度</label>--%>
						<%--<input id="latitude" type="text" class="form-control" name="latitude" maxlength="100">--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>




			<%--<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="commissioningDate">投产日期</label>
						<input id="commissioningDate" type="text" class="form-control" name="commissioningDate">
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="warrantyStartDate">质保开始日期</label>
						<input id="warrantyStartDate" type="text" class="form-control" name="warrantyStartDate">
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="warrantyEndDate">质保结束日期</label>
						<input id="warrantyEndDate" type="text" class="form-control" name="warrantyEndDate">
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="maintenancePeriod">维保周期</label>
						<input id="maintenancePeriod" type="text" class="form-control" name="maintenancePeriod">
					</div>
				</div>
			</div>--%>

			<div class="col-sm-12">
				<div id="fine-uploader-gallery"></div>
				<input id="imagePath" type="hidden" class="form-control"  name="imagePath" maxlength="500">
			</div>

		</div>

		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="createDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>
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
                //sendCredentials : true
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
        status: qq.status.UPLOAD_SUCCESSFUL
    });
    var fileUuids = '';
    for (var i = 0; i < uploads.length; i++) {
        fileUuids = fileUuids + uploads[i].uuid + ",";
    }
    console.log("fileUuids = " + fileUuids);
    $('#imagePath').val(fileUuids);

    $.ajax({
        type: 'post',
        url: '${basePath}/manage/${productLineId}/equipment/create',
        data: $('#createForm').serialize(),
        beforeSend: function() {
            if ($('#name').val() == '') {
                $('#name').focus();
                return false;
            }
        },
        success: function(result) {
			if (result.code != 1) {
				if (result.data instanceof Array) {
					$.each(result.data, function(index, value) {
						$.confirm({
							theme: 'dark',
							animation: 'rotateX',
							closeAnimation: 'rotateX',
							title: false,
							content: value.errorMsg,
							buttons: {
								confirm: {
									text: '确认',
									btnClass: 'waves-effect waves-button waves-light'
								}
							}
						});
					});
				} else {
						$.confirm({
							theme: 'dark',
							animation: 'rotateX',
							closeAnimation: 'rotateX',
							title: false,
							content: result.data.errorMsg,
							buttons: {
								confirm: {
									text: '确认',
									btnClass: 'waves-effect waves-button waves-light'
								}
							}
						});
				}
			} else {
				createDialog.close();
				$table.bootstrapTable('refresh');
			}
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
			$.confirm({
				theme: 'dark',
				animation: 'rotateX',
				closeAnimation: 'rotateX',
				title: false,
				content: textStatus,
				buttons: {
					confirm: {
						text: '确认',
						btnClass: 'waves-effect waves-button waves-light'
					}
				}
			});
        }
    });
}
</script>