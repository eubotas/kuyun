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
			<div class="col-sm-6">
				<div class="form-group">
					<div class="fg-line">
						<label for="category">类别</label>
						<input id="category" type="text" class="form-control" name="category" maxlength="30" value="${category}" readonly>
					</div>
				</div>
			</div>

            <div class="col-sm-6">
                <div class="form-group">
                    <div class="fg-line">
                        <label for="codeValue">Code</label>
                        <input id="codeValue" type="text" class="form-control" name="codeValue" maxlength="30">
                    </div>
                </div>
            </div>
		</div>

        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <div class="fg-line">
                        <label for="codeName">Code名称</label>
                        <input id="codeName" type="text" class="form-control" name="codeName" maxlength="100">
                    </div>
                </div>
            </div>

            <div class="col-sm-6">
                <div class="form-group">
                    <div class="fg-line">
                        <label for="description">描述</label>
                        <input id="description" type="text" class="form-control" name="description" maxlength="30">
                    </div>
                </div>
            </div>
        </div>

		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit('${basePath}/manage/codeValue/create',createDialog, 'category,codeValue,codeName');">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="createDialog.close();">取消</a>
		</div>
	</form>
</div>
<script src="/resources/inc/eamcommon.js"></script>

