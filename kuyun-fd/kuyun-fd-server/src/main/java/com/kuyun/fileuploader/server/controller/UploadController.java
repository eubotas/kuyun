package com.kuyun.fileuploader.server.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Splitter;
import com.google.common.io.ByteSink;
import com.google.common.io.FileWriteMode;
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
@RestController
@RequestMapping("/fd")
@Api(value = "文件上传")
public class UploadController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	private FileUploaderService fus;

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	private static String MODULE = "eam";


	@CrossOrigin(origins = "*")
	@ApiOperation(value = "上传")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Object upload(@RequestParam("qqfile") MultipartFile file, @RequestParam("qquuid") String uuid,
						 @RequestParam("qqfilename") String qqfilename, @RequestParam("qqtotalfilesize") long qqtotalfilesize,
						 @RequestParam(required = false, value = "qqpartindex") int qqpartindex,
						 @RequestParam(required = false, value = "qqpartbyteoffset") long qqpartbyteoffset,
						 @RequestParam(required = false, value = "qqtotalparts") int qqtotalparts,
						 @RequestParam(required = false, value = "qqchunksize") long qqchunksize,
						 @RequestParam(required = false, defaultValue = "defaultMoudle", value = "kuyunModule") String module) {

		// save file to local folder
		String baseLocation = fus.generateLocalStorageBaseLocation(MODULE, uuid);
		Path path = FileSystems.getDefault().getPath(baseLocation, qqfilename + "." + qqpartindex);
		File f = path.toFile();
		Map<String, Object> result = new HashMap<>();
		try {
			Files.createParentDirs(f);
			Files.touch(f);
			ByteSink bs = Files.asByteSink(f, FileWriteMode.APPEND);
			bs.writeFrom(file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("error", e.getMessage());
			return result;
		}

		result.put("success", true);
		return result;
	}
	/**
	 * MIME cannot be get from fineuploader, leave it blank currently
	 * @param uuid
	 * @param qqfilename
	 * @param qqtotalfilesize
	 * @param qqtotalparts
	 * @param module
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@ApiOperation(value = "上传")
	@RequestMapping(value = "/uploadDone", method = RequestMethod.POST)
	public Object uploadDone(@RequestParam("qquuid") String uuid, @RequestParam("qqfilename") String qqfilename,
							 @RequestParam("qqtotalfilesize") long qqtotalfilesize,
							 @RequestParam(required = false, value = "qqtotalparts") int qqtotalparts,
							 @RequestParam(required = false, defaultValue = "defaultMoudle", value = "kuyunModule") String module) {
		Map<String, Object> result = new HashMap<>();

		// combine file chunks

		String baseLocation = fus.generateLocalStorageBaseLocation(module, uuid);
		Path path = FileSystems.getDefault().getPath(baseLocation, qqfilename);
		File f = path.toFile();
		if (qqtotalparts <= 1) {
			Path pathi = FileSystems.getDefault().getPath(baseLocation, qqfilename + ".0");
			File fi = pathi.toFile();
			try {
				Files.move(fi, f);
			} catch (IOException e) {

				result.put("success", false);
				result.put("error", e.getMessage());
				return result;
			}
		} else {
			try {
				Files.touch(f);
				ByteSink bs = Files.asByteSink(f, FileWriteMode.APPEND);
				for (int i = 0; i < qqtotalparts; i++) {
					Path pathi = FileSystems.getDefault().getPath(baseLocation, qqfilename + "." + i);
					File fi = pathi.toFile();
					bs.writeFrom(Files.asByteSource(fi).openStream());
					fi.delete();
				}
			} catch (IOException e) {

				result.put("success", false);
				result.put("error", e.getMessage());
				return result;
			}
		}

		// save info to db
		FdFiles fdfile = new FdFiles();
		fdfile.setFileName(qqfilename);
		fdfile.setSize(qqtotalfilesize);
		fdfile.setUuid(uuid);
		fdfile.setMoudleName(module);
		fdfile.setDeleteFlag(false);
		fdfile.setSavedFileName(path.toString());

		baseEntityUtil.addAddtionalValue(fdfile);

		fus.fileUploaded(fdfile);

		result.put("success", true);

		return result;
	}

	@CrossOrigin(origins = "*")
	@ApiOperation(value = "删除")
	@RequestMapping(value = "/delete/{uuid}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("uuid") String uuid) {
		FdFiles file = fus.getFileInfo(uuid);
		if ( file != null) {

			fus.removeUploadedFile(file);
			//delete file folder
			String baseLocation = fus.generateLocalStorageBaseLocation(MODULE, uuid);
			try {
				FileUtils.deleteDirectory(FileUtils.getFile(baseLocation));
			} catch (IOException e) {
				_log.info("Delete File Error,{}", e.getMessage());
			}
		}

		Map<String, Object> result = new HashMap<>();
		result.put("success", true);

		return result;

	}

	/**
	 *
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@ApiOperation(value = "列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list(@RequestParam(required = false, value = "ids") String ids) {
		List<String> realIds = new ArrayList<String>();

		for ( String id : Splitter.on(',')
				.trimResults()
				.omitEmptyStrings()
				.split(ids) ) {

			try {
				realIds.add(id);
			} catch (NumberFormatException e) {
				//just ignore the invalid ids
			}
		}

		List<FdFiles> files = fus.getSavedFileInfo(realIds);
		List<Map<String, Object>> result = new  ArrayList<Map<String, Object>>();
		for (FdFiles file: files) {
			Map<String,Object> f = new HashMap<String,Object>();
			f.put("name", file.getFileName());
			f.put("uuid", file.getUuid());
			f.put("size", file.getSize());
			result.add(f);
		}
		return result.toArray();
	}
}