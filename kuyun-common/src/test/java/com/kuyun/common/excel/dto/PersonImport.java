package com.kuyun.common.excel.dto;


import com.kuyun.common.excel.BaseImportBean;

public class PersonImport extends BaseImportBean {
	private String name;
	private String age;
	private String height;
	private String weight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Override
	public String[] getHeaderName() {
		return new String[] { "姓名", "年龄", "身高", "体重" };
	}

	@Override
	public String[] getSortedProperty() {
		return new String[] { "name", "age", "height", "weight" };
	}
}
