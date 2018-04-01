package com.kuyun.common.excel;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ExcelUtils {

	public ExcelUtils() {
		throw new UnsupportedOperationException("不支持初始化该类！");
	}

	// 单个sheet写入数据的行数
	private static double SHEET_MAX_SIZE = 10000;

	// 设置CellStyle
	private static CellStyle setCellStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle(); // 生成一个样式
		Font font = wb.createFont();
		font.setFontName("微软雅黑");
		font.setFontHeightInPoints((short) 12); // 设置字体大小
		style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直方向居中
		style.setAlignment(HorizontalAlignment.CENTER);// 水平方向居中
		style.setWrapText(false); // 指定单元格自动换行
		style.setFont(font);
		return style;
	}

	/**
	 * @param <T>
	 *            dto
	 * @param <WB>
	 * @param list
	 *            需要导出的dto的集合
	 * @param params
	 *            有些dto可能需要传递一些参数进行操作，可以在dto内通过this.params获取需要的参数，
	 *            如果不需要传递参数赋值null即可
	 * @return WB 导出的excel对象
	 * 
	 * @throws Exception
	 * 
	 */
	private static <P, T extends BaseExportBean<P>, WB extends Workbook> WB exportExcel(WB wb, List<T> list, P params)
			throws Exception {
		int size, start = 0;
		if (list == null || (size = list.size()) == 0){
			throw new IllegalArgumentException("导出数据为空!");
		}

		int limit = ((SHEET_MAX_SIZE <= size) ? (int) SHEET_MAX_SIZE : size);
		int sheetNum = (int) Math.ceil(size / SHEET_MAX_SIZE);
		T o = list.get(0);
		o.params = params;
		Class<?> beanClass = o.getClass();
		String[] headerName = Objects.requireNonNull(o.getHeaderName(), "headerName不能为空！");
		String[] property = Objects.requireNonNull(o.getSortedProperty(), "property不能为空！");
		CellStyle style = setCellStyle(wb);
		for (int index = 0; index < sheetNum; index++, limit = (index == (sheetNum - 1) ? size
				: (int) ((index + 1) * SHEET_MAX_SIZE))) {
			Sheet sheet = wb.createSheet("sheet" + index);
			sheet.setDefaultColumnWidth(24);
			Row row1 = sheet.createRow(0);
			row1.setHeightInPoints(24);
			for (int i = 0; i < headerName.length; i++) {
				Cell cell = row1.createCell(i);
				cell.setCellStyle(style);
				cell.setCellValue(headerName[i]);
			}
			for (int i = 1, j = 0; start < limit; i++, start++, j = 0) {
				Row row = sheet.createRow(i);
				row.setHeightInPoints(24);
				for (String item : property) {
					Cell cell = row.createCell(j++);
					cell.setCellStyle(style);
					cell.setCellValue(list.get(start).getValue(item, beanClass));
				}
			}
		}
		return wb;
	}

	/**
	 * 导出Excel_2003版本
	 * 
	 * @param list
	 *            需要导出的dto的集合，dto需要继承BaseExportBean
	 * @param params
	 *            有些dto可能需要传递一些参数进行操作，可以在dto内通过this.params获取需要的参数，
	 *            如果不需要传递参数赋值null即可
	 * @return 返回导出的HSSFWorkbook对象
	 * @throws Exception
	 */
	public static <T> HSSFWorkbook exportExcel_2003(List<? extends BaseExportBean<T>> list, T params) throws Exception {
		return exportExcel(new HSSFWorkbook(), list, params);
	}

	/**
	 * 导出Excel_2007版本
	 * 
	 * @param list
	 *            需要导出的dto的集合，dto需要继承BaseExportBean
	 * @param params
	 *            有些dto可能需要传递一些参数进行操作，可以在dto内通过this.params获取需要的参数，
	 *            如果不需要传递参数赋值null即可
	 * @return 返回导出的XSSFWorkbook对象
	 * @throws Exception
	 */
	public static <T> XSSFWorkbook exportExcel_2007(List<? extends BaseExportBean<T>> list, T params) throws Exception {
		return exportExcel(new XSSFWorkbook(), list, params);
	}

	// 将单元格内的值转换为String
	private static String getStringVal(Cell cell) {
		if (cell == null) {
			return "";
		} else {

			if(CellType.NUMERIC.equals(cell.getCellTypeEnum())){
				cell.setCellType(CellType.STRING);
				return cell.getStringCellValue();
			}else if(CellType.STRING.equals(cell.getCellTypeEnum())){
				return cell.getStringCellValue();
			}else if(CellType.FORMULA.equals(cell.getCellTypeEnum())){
				return cell.getCellFormula();
			}else if(CellType.BLANK.equals(cell.getCellTypeEnum())){
				return "";
			}else if(CellType.BOOLEAN.equals(cell.getCellTypeEnum())){
				return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
			}else if(CellType.ERROR.equals(cell.getCellTypeEnum())){
				return "Bad Value!";
			}else {
				return "";
			}
		}
	}

	/**
	 * 将Excel对象解析为ArrayList，解析时默认每个sheet的第一行不计入ArrayList内
	 * 
	 * @param wb
	 *            需要导入的Workbook对象
	 *            Excel中每行要转换成的dto的class对象，dto需要继承BaseImportBean，
	 *            并且dto中的字段类型必须都定义为String类型
	 * @return 返回解析Excel后的ArrayList对象
	 * @throws Exception
	 */
	public static <T extends BaseImportBean> List<T> importExcel(Workbook wb, Class<T> beanClass) throws Exception {
		List<T> list = new ArrayList<>();
		T o_ = beanClass.newInstance();
		String[] property = Objects.requireNonNull(o_.getSortedProperty(), "property不能为空！");
		int index = wb.getNumberOfSheets(); // sheet的数量
		for (int i = 0; i < index; i++) { // 循环每个sheet
			Sheet sheet = wb.getSheetAt(i);
			int rowNum = sheet.getLastRowNum() + 1; // 当前sheet包含的行数(不包括第一行)
			int columnNum = property.length; // sheet包含的列数，以getSortedProperty方法返回的数组长度为准
			for (int j = 1; j < rowNum; j++) { // 默认每个sheet的第一行不是dto
				Row row = sheet.getRow(j);
				T o = beanClass.newInstance();
				for (int k = 0; k < columnNum; k++) {
					o.setValue(property[k], StringUtils.trim(getStringVal(row.getCell(k))), beanClass);
				}
				list.add(o);
			}
		}
		return list;
	}
	
	public static Workbook getWorkbook(String excelFilePath) 
			throws IOException {
		Workbook workbook = null;
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		workbook = getWorkbook(excelFilePath, inputStream);

		return workbook;
	}

	public static Workbook getWorkbook(MultipartFile file)
			throws IOException {
		Workbook workbook = null;
		String fileName = file.getOriginalFilename();

		FileInputStream inputStream = new FileInputStream(multipartToFile(file));

		workbook = getWorkbook(fileName, inputStream);

		return workbook;
	}

	private static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException
	{
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}

	public static Workbook getWorkbook(String fileName, FileInputStream inputStream) throws IOException {
		Workbook workbook;
		if (fileName.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (fileName.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}
		return workbook;
	}
}
