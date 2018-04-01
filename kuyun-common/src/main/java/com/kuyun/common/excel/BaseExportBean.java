package com.kuyun.common.excel;

import java.lang.reflect.Field;

public abstract class BaseExportBean<T> implements BaseExcelBean {

	/**
	 * 需要传给dto的参数
	 */
	protected T params;

	/**
	 * 获取每个Field的值(通过getDeclaredField方法实现)
	 * 
	 * @param item
	 *            Field的name
	 * @return
	 * @throws Exception
	 */
	String getValue(String item, Class<?> beanClass) throws Exception {
		Field field = beanClass.getDeclaredField(item);
		field.setAccessible(true);
		Object o = field.get(this);
		if (o == null) {
			return "";
		}
		return o.toString();
	}

}
