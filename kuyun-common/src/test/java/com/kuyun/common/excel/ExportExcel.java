package com.kuyun.common.excel;

import com.kuyun.common.excel.dto.PersonExport;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;


public class ExportExcel {

	public static void main(String[] args) throws Exception {
		List<PersonExport> list=getData();
		HSSFWorkbook wb_2003=ExcelUtils.exportExcel_2003(list,null);
		XSSFWorkbook wb_2007=ExcelUtils.exportExcel_2007(list,null);
	}
	
	public static List<PersonExport> getData() throws Exception{
		List<PersonExport> list=new ArrayList<>();
		list.add(new PersonExport("张三", "23", "170", "50"));
		list.add(new PersonExport("李四", "25", "178", "58"));
		return list;
		
	}

}
