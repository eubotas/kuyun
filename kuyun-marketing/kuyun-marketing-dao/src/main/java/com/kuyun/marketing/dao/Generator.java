package com.kuyun.marketing.dao;

import com.kuyun.common.util.MybatisGeneratorUtil;
import com.kuyun.common.util.PropertiesFileUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 4/8/2017.
 */
public class Generator {

    // 根据命名规范，只修改此常量值即可
    private static String MODULE = "kuyun-marketing";
    private static String DATABASE = "kuyun";
    private static String TABLE_PREFIX = "mkt_";
    private static String PACKAGE_NAME = "com.kuyun.marketing";
    private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");
    private static String JDBC_URL = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");
    private static String JDBC_USERNAME = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");
    private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");
    // 需要insert后返回主键的表配置，key:表名,value:主键名
    private static Map<String, String> LAST_INSERT_ID_TABLES = new HashMap<String, String>();
    static {
        LAST_INSERT_ID_TABLES.put("mkt_sms", "id");
    }

    /**
     * 自动代码生成
     * @param args
     */
    public static void main(String[] args) throws Exception {
        MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, MODULE, DATABASE, TABLE_PREFIX, PACKAGE_NAME, LAST_INSERT_ID_TABLES);
    }
}
