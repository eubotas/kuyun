﻿﻿﻿<%@ page contentType="text/html; charset=utf-8" %>
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


<pageResources>
    <jsp:include page="/resources/metronic-admin/file_upload.jsp" flush="true"/>

    <script>
        var editGalleryUploader;
        $(document).ready(function()
        {
            radioBoxcheck(${user.sex},'sex');
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
                        username: {
                            required: true,
                            minlength: 2,
                            maxlength: 20
                        },
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
        // 格式化性别
        function sexFormatter(value, row, index) {
            if (value == 1) {
                return '男';
            }
            if (value == 2) {
                return '女';
            }
            return '-';
        }
    </script>


</pageResources>

<content>

    <div class="m-portlet m-portlet--mobile">
        <div class="m-portlet__body" style="width:60%; padding-left:10%;">
            <form id="update_Form" class="m-form m-form--fit m-form--label-align-right">
            <div class="form-group">
                <label for="username">帐号:  </label>
                <label for="username">${user.username}</label>

                <input id="username" type="hidden" class="form-control" name="username" maxlength="20" value="${user.username}">
            </div>
            <div class="form-group">
                <label for="realname">姓名  *</label>
                <input id="realname" type="text" class="form-control" name="realname" maxlength="20" value="${user.realname}">
            </div>
            <div class="form-group">
                <label for="edit_imagePath">头像</label>
                <image src='${user.avatar}' />
                <button type="button" class="btn btn-secondary" id="modifyImage" style="margin-left:70%;">
                    修改头像
                </button>
                <div id="edit_fine-uploader-gallery" class="col-sm-9" style="display: none"></div>
                <input id="edit_imagePath" type="hidden" class="form-control" name="avatar" value="${user.avatar}">
            </div>
            <div class="form-group">
                <label for="phone">电话</label>
                <input id="phone" type="text" class="form-control" name="phone" maxlength="20" value="${user.phone}">
            </div>
            <div class="form-group">
                <label for="email">邮箱</label>
                <input id="email" type="text" class="form-control" name="email" maxlength="50" value="${user.email}">
            </div>
            <div class="m-form__group form-group">
                <div class="m-radio-inline">
                    <label class="m-radio">
                        <input id="sex_1" type="radio" name="sex" value="1" checked>男
                        <span></span>
                    </label>
                    <label class="m-radio">
                        <input id="sex_0" type="radio" name="sex" value="0">女<span></span>
                    </label>
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

</body>
</html>