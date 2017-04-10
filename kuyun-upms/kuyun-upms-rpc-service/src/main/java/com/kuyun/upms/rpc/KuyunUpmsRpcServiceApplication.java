package com.kuyun.upms.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类
 * Created by kuyun on 2017/2/3.
 */
public class KuyunUpmsRpcServiceApplication {

	private static Logger _log = LoggerFactory.getLogger(KuyunUpmsRpcServiceApplication.class);

	public static void main(String[] args) {
		_log.info(">>>>> kuyun-upms-rpc-service 正在启动 <<<<<");
		new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
		_log.info(">>>>> kuyun-upms-rpc-service 启动完成 <<<<<");
	}

}
