package com.kuyun.fileuploder.common;

public class FileUploadServerInfo implements java.io.Serializable {
	
	private String serverBaseUri;
	private String fileuploadBase;
	private String upload;
	private String uploadDone;
	private String delete;
	private String list;
	private String show;
	public String getServerBaseUri() {
		return serverBaseUri;
	}
	public void setServerBaseUri(String serverBaseUri) {
		this.serverBaseUri = serverBaseUri;
	}
	public String getFileuploadBase() {
		return fileuploadBase;
	}
	public void setFileuploadBase(String fileuploadBase) {
		this.fileuploadBase = fileuploadBase;
	}
	public String getUpload() {
		return upload;
	}
	public void setUpload(String upload) {
		this.upload = upload;
	}
	public String getUploadDone() {
		return uploadDone;
	}
	public void setUploadDone(String uploadDone) {
		this.uploadDone = uploadDone;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	
	
	public String getEndpoint_upload() {
		return serverBaseUri+fileuploadBase+upload;
	}
	
	public String getEndpoint_uploadDone() {
		return serverBaseUri+fileuploadBase+uploadDone;
	}

	public String getEndpoint_delete() {
		return serverBaseUri+fileuploadBase+delete;
	}
	public String getEndpoint_list() {
		return serverBaseUri+fileuploadBase+list;
	}
	public String getEndpoint_show() {
		return serverBaseUri+show;
	}
}
