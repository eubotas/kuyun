package com.kuyun.fileuploader.rpc.api;

import com.kuyun.fileuploader.dao.model.FdFiles;

public class FileUploaderServiceMock implements FileUploaderService {

	@Override
	public int fileUploaded(FdFiles file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getStorageLocation(FdFiles file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeUploadedFile(FdFiles file) {
		// TODO Auto-generated method stub
		return false;
	}


}
