package com.kuyun.common.excel;

import com.kuyun.common.excel.dto.PersonImport;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public class ImportExcel {

	public static void main(String[] args) throws Exception{
//		HSSFWorkbook wb_2003=new HSSFWorkbook(new FileInputStream(new File("测试.xls")));
//		XSSFWorkbook wb_2007=new XSSFWorkbook(new FileInputStream(new File("测试.xlsx")));
		
		Workbook workbook1 = ExcelUtils.getWorkbook("测试.xls");
		Workbook workbook2 = ExcelUtils.getWorkbook("测试.xlsx");
		
		List<PersonImport> list=ExcelUtils.importExcel(workbook1, PersonImport.class);
		List<PersonImport> list1=ExcelUtils.importExcel(workbook2, PersonImport.class);
	}

}
