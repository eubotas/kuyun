package com.kuyun.fileuploader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类
 * Created by kuyun on 2017/2/3.
 */
public class KuyunFileUploaderRpcServiceApplication {

	private static Logger _log = LoggerFactory.getLogger(KuyunFileUploaderRpcServiceApplication.class);

	public static void main(String[] args) {
		_log.info(">>>>> kuyun-FileUploader-rpc-service 正在启动 <<<<<");
		new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
		_log.info(">>>>> kuyun-FileUploader-rpc-service 启动完成 <<<<<");
	}

}
