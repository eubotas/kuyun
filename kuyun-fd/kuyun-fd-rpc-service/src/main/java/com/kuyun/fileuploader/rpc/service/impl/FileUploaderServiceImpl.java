package com.kuyun.fileuploader.rpc.service.impl;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.util.key.SnowflakeIdWorker;
import com.kuyun.fileuploader.dao.mapper.FdFilesMapper;
import com.kuyun.fileuploader.dao.mapper.FdOssFilesMapper;
import com.kuyun.fileuploader.dao.model.FdFiles;
import com.kuyun.fileuploader.rpc.api.FileUploaderService;
import com.kuyun.fileuploder.common.Storage;

@Service
@Transactional
public class FileUploaderServiceImpl implements FileUploaderService {

	private static Logger _log = LoggerFactory.getLogger(FileUploaderServiceImpl.class);
	
	@Autowired
	FdFilesMapper fdFilesMapper;
	
	@Autowired
    FdOssFilesMapper fdOssFilesMapper;
	
	@Autowired
	Storage storage;
	
	@Autowired
	SnowflakeIdWorker snowflakeIdWorker;
	
	@Override
	public int fileUploaded(FdFiles file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getStorageLocation(FdFiles file) {
		//check input file is validate
		if (file.getFileName().isEmpty() || file.getMoudleName().isEmpty() || file.getSize() == 0 || file.getMime().isEmpty()) {
			throw new IllegalArgumentException("file is invalid.\r\n file name or modulename or mime cannot be empty.\r\n file size cannot be zero\r\n"+ file.toString());
		}
		String filename = snowflakeIdWorker.nextId() + "" + file.getFileName().substring(file.getFileName().lastIndexOf("."));
		Path path = FileSystems.getDefault().getPath(storage.getLocation(),file.getMoudleName(),filename);
		return path.toString();
	}

	@Override
	public boolean removeUploadedFile(FdFiles file) {
		// TODO Auto-generated method stub
		return false;
	}


}
