package com.kuyun.fileuploader.rpc.api;

import java.util.List;

import com.kuyun.fileuploader.dao.model.FdFiles;
import com.kuyun.fileuploader.dao.model.FdOssFiles;

public interface FileUploaderService {
	
	//return uploaded file id
	int fileUploaded(FdFiles file);
	
	//get saved file info
	List<FdFiles> getSavedFileInfo(List<String> uuids);
	
	boolean removeUploadedFile(FdFiles file);
	
	String generateLocalStorageBaseLocation(String module, String uuid);
	
	FdFiles getFileInfo(String uuid);
	
	
	
	//below are OSS related.
	
	FdOssFiles getOssInfo(int id);
	
	boolean updateOSSInfo(FdFiles file, FdOssFiles ossFile);
	
}
