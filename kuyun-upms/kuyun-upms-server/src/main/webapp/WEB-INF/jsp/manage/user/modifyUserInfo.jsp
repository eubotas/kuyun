﻿﻿<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<c:set var="upmsPath" value="<%=com.kuyun.common.util.BasePath.kuyunUpmsServer%>"/>
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
												用户信息
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
        <div class="m-portlet__body" style="width:60%; padding-left:10%;">
            <form id="update_Form" class="m-form m-form--fit m-form--label-align-right">
                <div class="form-group m-form__group row">
                    <label class="col-3 col-form-label">姓名</label>
                    <div class="col-sm-6">
                        <input id="realname" type="text" class="form-control" name="realname">
                    </div>
                </div>
            <div class="form-group m-form__group row">
                <label class="col-3 col-form-label"for="edit_imagePath">头像</label>
                <div class="col-sm-9">
                    <image src='${user.avatar}' />
                    <button type="button" class="btn btn-secondary" id="modifyImage">
                        修改头像
                    </button>
                    <div id="edit_fine-uploader-gallery" class="col-sm-9" style="display: none"></div>
                    <input id="edit_imagePath" type="hidden" class="form-control" name="avatar" value="${user.avatar}">
                </div>
            </div>
            <div class="form-group m-form__group row">
                <label class="col-3 col-form-label">电话</label>
                <div class="col-sm-6">
                    <input id="phone" type="text" class="form-control" name="phone" maxlength="20" value="${user.phone}">
                </div>
            </div>
            <div class="form-group m-form__group row">
                <label class="col-3 col-form-label">邮箱</label>
                <div class="col-sm-6">
                    <input id="email" type="text" class="form-control" name="email" maxlength="50" value="${user.email}">
                </div>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="edit_id" name="id" value="${user.userId}">
                <button type="submit" class="btn btn-primary" id="submit">
                    提交
                </button>
            </div>

            </form>
        </div>
    </div>


</content>


<pageResources>
    <jsp:include page="/resources/metronic-admin/file_upload.jsp" flush="true"/>

    <script>
        $(document).ready(function()
        {
            FormWidgets.init('update');

            $('#modifyImage').click(function (){
                var obj=document.getElementById('edit_fine-uploader-gallery').style;
                if(!obj.display || obj.display == 'none')
                    obj.display = 'block';
                else obj.display ='none';
            });
            editGalleryUploader = new qq.FineUploader($.extend(uploadOpt, {element : document.getElementById("edit_fine-uploader-gallery")}));

        });

        var FormWidgets = function () {
            var createForm = function (formid) {
                $("#"+formid+"_Form").validate({
                    rules: {
                        realname: {
                            required: true,
                            minlength: 2,
                            maxlength: 20
                        }
                    },
                    submitHandler: function (form) {
                            submitForm($('#edit_id').val());
                    }
                });
            }

            return {
                init: function (formid) {
                    createForm(formid);
                }
            };
        }();

        function submitForm(id) {
            var targetUrl='${upmsPath}/manage/user/update/'+id;
            var formId='update_Form';
            $('#edit_imagePath').val(getUploadFileName(editGalleryUploader));

            ajaxPost(targetUrl, formId, function(result) {
                if (result.code != 1) {
                    sendErrorInfo(result);
                } else {
                    toastr.success("编辑用户成功");
                }
            });
        }


        // 格式化图标
        function avatarFormatter(value) {
            if(value != null && value != '')
                return '<img src="${basePath}' + value + '" style="width:20px;height:20px;"/>';
            else
                return "";

        }
    </script>

</pageResources>


</body>
</html>