package com.kuyun.fileuploader.server.controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.io.Files;
import com.kuyun.common.base.BaseController;
import com.kuyun.fileuploader.dao.model.FdFiles;
import com.kuyun.fileuploader.rpc.api.FileUploaderService;
import com.kuyun.upms.client.util.BaseEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 后台controller Created by kuyun on 2017/01/12.
 * 
 * {"success": false, "error": "error message to display", "preventRetry": true,
 * "reset": true}
 */
@Controller
@RequestMapping("/files")
@Api(value = "文件上传")
public class ShowUploadController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(ShowUploadController.class);

	@Autowired
	private FileUploaderService fus;
	
	@Autowired
	private BaseEntityUtil baseEntityUtil;

	/**
	 * 
	 * @param uuids
	 * @return
	 */
	@ApiOperation(value = "列表")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void list(@PathVariable(required = false, value = "id") String id, HttpServletResponse response) {
		List<String> realIds = new ArrayList<String>();
		realIds.add(id);
		List<FdFiles> files = fus.getSavedFileInfo(realIds);
		if(!files.isEmpty()) {
			Path path = FileSystems.getDefault().getPath(files.get(0).getSavedFileName());
			try {
				org.apache.commons.io.IOUtils.copy(Files.asByteSource(path.toFile()).openStream(), response.getOutputStream());
				response.flushBuffer();
			} catch (IOException e) {
				_log.info("Error writing file to output stream. Filename was '{}'", path.toString(), e);
			    throw new RuntimeException("IOError writing file to output stream");
			}
		}else {
			_log.info("Cannot found file with id :"+ id);
		}
	}
}