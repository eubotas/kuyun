package com.kuyun.api.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类
 * Created by kuyun on 2017/2/19.
 */
public class KuyunApiRpcServiceApplication {

	private static Logger _log = LoggerFactory.getLogger(KuyunApiRpcServiceApplication.class);

	public static void main(String[] args) {
		_log.info(">>>>> kuyun-api-rpc-service 正在启动 <<<<<");
		new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
		_log.info(">>>>> kuyun-api-rpc-service 启动完成 <<<<<");
	}

}
