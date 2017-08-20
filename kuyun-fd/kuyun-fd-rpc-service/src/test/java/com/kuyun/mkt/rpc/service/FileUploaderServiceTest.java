package com.kuyun.mkt.rpc.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.io.Files;
import com.kuyun.fileuploader.dao.model.FdFiles;
import com.kuyun.fileuploader.rpc.api.FileUploaderService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:applicationContext.xml",
        "classpath:META-INF/spring/applicationContext-jdbc.xml",
        "classpath:META-INF/spring/applicationContext-listener.xml",
        "classpath:META-INF/spring/applicationContext-fd.xml",
})
public class FileUploaderServiceTest {
	
	@SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private FileUploaderService fileUploaderService;
	
	
	@Test
	public void getFileLocation() {
		
//		FdFiles file = new FdFiles();
//		file.setFileName("aa.txt");
//		file.setSize(10l);
//		file.setMoudleName("eam");
//		file.setMime("text/plain");
//		
////		String name = fileUploaderService.generateLocalStorageLocation(file);
//		
//		System.out.println(name);
//		assert !name.isEmpty();
//		
		
	}

}
