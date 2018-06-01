<%@ page contentType="text/html; charset=utf-8" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<link href="${basePath}/resources/kuyun-admin/plugins/fileupload/fine-uploader-gallery.css" rel="stylesheet">
<script src="${basePath}/resources/kuyun-admin/plugins/fileupload/fine-uploader.js"></script>

<!-- Fine Uploader Gallery template
====================================================================== -->
<script type="text/template" id="qq-template-gallery">
    <div class="qq-uploader-selector qq-uploader qq-gallery" qq-drop-area-text="Drop files here">
        <div class="qq-total-progress-bar-container-selector qq-total-progress-bar-container">
            <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-total-progress-bar-selector qq-progress-bar qq-total-progress-bar"></div>
        </div>
        <div class="qq-upload-drop-area-selector qq-upload-drop-area" qq-hide-dropzone>
            <span class="qq-upload-drop-area-text-selector"></span>
        </div>
        <div class="qq-upload-button-selector qq-upload-button">
            <div>上传文件</div>
        </div>
        <span class="qq-drop-processing-selector qq-drop-processing">
    <span>Processing dropped files...</span>
<span class="qq-drop-processing-spinner-selector qq-drop-processing-spinner"></span>
    </span>
        <ul class="qq-upload-list-selector qq-upload-list" role="region" aria-live="polite" aria-relevant="additions removals">
            <li>
                <span role="status" class="qq-upload-status-text-selector qq-upload-status-text"></span>
                <div class="qq-progress-bar-container-selector qq-progress-bar-container">
                    <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-progress-bar-selector qq-progress-bar"></div>
                </div>
                <span class="qq-upload-spinner-selector qq-upload-spinner"></span>
                <div class="qq-thumbnail-wrapper">
                    <img class="qq-thumbnail-selector" qq-max-size="120" qq-server-scale>
                </div>
                <button type="button" class="qq-upload-cancel-selector qq-upload-cancel">X</button>
                <button type="button" class="qq-upload-retry-selector qq-upload-retry">
                    <span class="qq-btn qq-retry-icon" aria-label="Retry"></span>
                    Retry
                </button>

                <div class="qq-file-info">
                    <div class="qq-file-name">
                        <span class="qq-upload-file-selector qq-upload-file"></span>
                        <span class="qq-edit-filename-icon-selector qq-edit-filename-icon" aria-label="Edit filename"></span>
                    </div>
                    <input class="qq-edit-filename-selector qq-edit-filename" tabindex="0" type="text">
                    <span class="qq-upload-size-selector qq-upload-size"></span>
                    <button type="button" class="qq-btn qq-upload-delete-selector qq-upload-delete">
                        <span class="qq-btn qq-delete-icon" aria-label="Delete"></span>
                    </button>
                    <button type="button" class="qq-btn qq-upload-pause-selector qq-upload-pause">
                        <span class="qq-btn qq-pause-icon" aria-label="Pause"></span>
                    </button>
                    <button type="button" class="qq-btn qq-upload-continue-selector qq-upload-continue">
                        <span class="qq-btn qq-continue-icon" aria-label="Continue"></span>
                    </button>
                </div>
            </li>
        </ul>

        <dialog class="qq-alert-dialog-selector">
            <div class="qq-dialog-message-selector"></div>
            <div class="qq-dialog-buttons">
                <button type="button" class="qq-cancel-button-selector">关闭</button>
            </div>
        </dialog>

        <dialog class="qq-confirm-dialog-selector">
            <div class="qq-dialog-message-selector"></div>
            <div class="qq-dialog-buttons">
                <button type="button" class="qq-cancel-button-selector">否</button>
                <button type="button" class="qq-ok-button-selector">是</button>
            </div>
        </dialog>

        <dialog class="qq-prompt-dialog-selector">
            <div class="qq-dialog-message-selector"></div>
            <input type="text">
            <div class="qq-dialog-buttons">
                <button type="button" class="qq-cancel-button-selector">取消</button>
                <button type="button" class="qq-ok-button-selector">确定</button>
            </div>
        </dialog>
    </div>
</script>

<script>
    function getUploadFileName(galleryUploader){
        var uploads = galleryUploader.getUploads({
            status : qq.status.UPLOAD_SUCCESSFUL
        });
        var fileUuids = '';
        for (var i = 0; i < uploads.length; i++) {
            fileUuids = fileUuids + uploads[i].uuid + "/" + uploads[i].name + "::";
        }

        return fileUuids;
    }

    var uploadOpt={
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
            // sendCredentials : true
        }
        /* init file list
         session:{
         endpoint: '${uploadServer.endpoint_list}?ids=${uuids}'
         }, */
    };

    var uploadImageOpt=$.extend({validation : { allowedExtensions: ['jpeg', 'jpg', 'gif', 'png']}},uploadOpt);

    function deleteUploadFile(i, filePathId){
        $('#edit_browseFile'+i).css("display","none");
        var ids=$('#'+filePathId).val().split("::");
        ids.splice(i,1);
        $('#'+filePathId).val(ids.join("::"));
    }

    function showOneFile(file, i, filePathId){
        var fileName=file.substring(file.lastIndexOf("/")+1,file.length);
        return '<div id="edit_browseFile'+i+'" style="border:1px solid #777; display:inline; padding:8px 10px 5px 10px;">'+
            '<span id="edit_browseDeleteName'+i+'">'+fileName+'</span>'+
            '<img id="edit_browseDeleteLink'+i+'" onclick="deleteUploadFile('+i+', \''+filePathId+'\')" src="/resources/metronic-admin/assets/images/delete-simple-14x14.png" style="padding-left:15px; cursor:pointer;">'+
            '</div>&nbsp;&nbsp;'
    }

    function showFiles(paths, filePathId){
        if(!paths)
            return ;
        var fileList="";
        var ids=paths.split("::");
        for(var i=0;i<ids.length;i++){
            fileList=fileList + showOneFile(ids[i], i, filePathId);
        }
        $('#edit_fileExistingSection').html(fileList).css("display","block");
    }
</script>