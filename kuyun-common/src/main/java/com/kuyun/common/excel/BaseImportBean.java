package com.kuyun.common.excel;

import java.io.Serializable;
import java.lang.reflect.Field;

public abstract class BaseImportBean implements BaseExcelBean, Serializable {

	/**
	 * 设置每个Field的值(通过getDeclaredField方法实现)
	 * 
	 * @param item
	 *            Field的name
	 * @param value
	 *            单元格内的值
	 * @throws Exception
	 */
	void setValue(String item, String value, Class<?> beanClass) throws Exception {
		Field field = beanClass.getDeclaredField(item);
		field.setAccessible(true);
		field.set(this, value);
	}

}
