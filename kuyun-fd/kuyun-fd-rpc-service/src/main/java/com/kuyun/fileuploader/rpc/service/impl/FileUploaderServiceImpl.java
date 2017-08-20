package com.kuyun.fileuploader.rpc.service.impl;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuyun.common.util.key.SnowflakeIdWorker;
import com.kuyun.fileuploader.dao.model.FdFiles;
import com.kuyun.fileuploader.dao.model.FdFilesExample;
import com.kuyun.fileuploader.dao.model.FdOssFiles;
import com.kuyun.fileuploader.rpc.api.FdFilesService;
import com.kuyun.fileuploader.rpc.api.FdOssFilesService;
import com.kuyun.fileuploader.rpc.api.FileUploaderService;
import com.kuyun.fileuploder.common.Storage;

@Service
@Transactional
public class FileUploaderServiceImpl implements FileUploaderService {

	private static Logger _log = LoggerFactory.getLogger(FileUploaderServiceImpl.class);

	@Autowired
	FdFilesService fdFilesService;

	@Autowired
	FdOssFilesService fdOssFilesService;

	@Autowired
	Storage storage;

	@Autowired
	SnowflakeIdWorker snowflakeIdWorker;

	@Override
	public int fileUploaded(FdFiles file) {
		// check input file is validate
		if (file.getFileName().isEmpty() || file.getMoudleName().isEmpty() || file.getSize() == 0
				|| file.getSavedFileName().isEmpty()) {
			throw new IllegalArgumentException(
					"file is invalid.\r\n file name or modulename or mime or savedFileName cannot be empty.\r\n file size cannot be zero\r\n"
							+ file.toString());
		}
		return fdFilesService.insertSelective(file);

	}

	/**
	 * logic remove the file record
	 */
	@Override
	public boolean removeUploadedFile(FdFiles file) {
		if (file.getUuid() == null || file.getUuid().isEmpty()) {
			throw new IllegalArgumentException("file id cannot be empty" + file.toString());
		}
		file.setDeleteFlag(true);

		fdFilesService.updateByPrimaryKey(file);
		return true;
	}

	@Override
	public String generateLocalStorageBaseLocation(String module, String uuid) {
		Path path = FileSystems.getDefault().getPath(storage.getLocation(), module, uuid);
		return path.toString();
	}

	@Override
	public boolean updateOSSInfo(FdFiles file, FdOssFiles ossFile) {
		// check input file is validate
		// create the relationship with oss record
		return true;
	}

	@Override
	public List<FdFiles> getSavedFileInfo(List<String> uuids) {
		// check input file is validate
		if (uuids.isEmpty()) {
			throw new IllegalArgumentException("id cannot be negative.");
		}
		FdFilesExample example = new FdFilesExample();
		example.createCriteria().andUuidIn(uuids).andDeleteFlagEqualTo(false);

		return fdFilesService.selectByExample(example);

	}

	@Override
	public FdFiles getFileInfo(String uuid) {
		// check input file is validate
		if (uuid.isEmpty()) {
			throw new IllegalArgumentException("uuid cannot be empty.");
		}
		FdFilesExample example = new FdFilesExample();
		example.createCriteria().andUuidEqualTo(uuid);

		List<FdFiles> files = fdFilesService.selectByExample(example);
		
		if (files.isEmpty()) {
			return null;
		} else {
			return files.get(0);
		}
	}

	@Override
	public FdOssFiles getOssInfo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
