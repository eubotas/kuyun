package com.kuyun.common.excel;

public interface BaseExcelBean {

	/**
	 * 返回sheet对应的第一行的每一列的中文名字 例如{"第一列名字","第二列名字","第三列名字"}
	 */
	String[] getHeaderName();

	/**
	 * 返回dto中定义的每个Field按指定顺序返回 例如{"firstColumn","secondColumn","thirdColumn"}
	 * 如果只需要导出dto中的一部分字段，只需要添加需要导出的Field
	 */
	String[] getSortedProperty();

}
