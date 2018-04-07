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

		<div class="row">
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<select id="templateType" name="templateType" style="width: 100%">
							<c:forEach var="code" items="${codes}">
								<option value="${code.codeValue}">${code.codeName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="name">名称</label>
						<input id="name" type="text" class="form-control" name="name" maxlength="20">
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div id="fine-uploader-gallery"></div>
				<input id="path" type="hidden" class="form-control"  name="path" maxlength="500">
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
                expected : true

                //if you want cookies to be sent along with the request
                //sendCredentials : true
            }

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
    $('#path').val(fileUuids);

    $.ajax({
        type: 'post',
        url: '${basePath}/manage/fileTemplate/create',
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