package com.kuyun.fileuploader.rpc.api;

import java.util.List;

import com.kuyun.fileuploader.dao.model.FdFiles;
import com.kuyun.fileuploader.dao.model.FdOssFiles;

public class FileUploaderServiceMock implements FileUploaderService {

	@Override
	public FdFiles getFileInfo(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateLocalStorageBaseLocation(String module, String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateOSSInfo(FdFiles file, FdOssFiles ossFile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int fileUploaded(FdFiles file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FdFiles> getSavedFileInfo(List<String> uuids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FdOssFiles getOssInfo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeUploadedFile(FdFiles file) {
		// TODO Auto-generated method stub
		return false;
	}


}
