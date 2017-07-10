package com.kuyun.pay.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类
 * Created by kuyun on 2017/3/29.
 */
public class KuyunPayRpcServiceApplication {

	private static Logger _log = LoggerFactory.getLogger(kuyunPayRpcServiceApplication.class);

	public static void main(String[] args) {
		_log.info(">>>>> kuyun-pay-rpc-service 正在启动 <<<<<");
		new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
		_log.info(">>>>> kuyun-pay-rpc-service 启动完成 <<<<<");
	}

}
