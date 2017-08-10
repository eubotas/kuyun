package com.kuyun.fileuploader.rpc.api;

import com.kuyun.fileuploader.dao.model.FdFiles;

public interface FileUploaderService {
	
	//return uploaded file id
	int fileUploaded(FdFiles file);
	
	//local storage for save file
	String getStorageLocation(FdFiles file);
	
	boolean removeUploadedFile(FdFiles file);
	
}
