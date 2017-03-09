package pro.utils;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import pro.dao.entity.Car;

/**
 * 导出Excel表
 * 
 * @author lc
 * 
 */
public class CreateExcel {

	// 获取项目的WebRoot目录
	public static String classPath = Thread.currentThread()
			.getContextClassLoader().getResource("").toString();
	public static String Path = classPath.replace("file:/", "")
			.replace("WEB-INF/classes/", "").replace("/", "\\")
			.replace("%20", " ")
			+ "excel\\";

	/**
	 * 导出Excel表
	 * 
	 * @param excelName
	 *            对应Excel文件中的sheet
	 * @param url
	 *            Excel文件的保存地址
	 * @param list
	 *            需要导出的list集合
	 */
	@SuppressWarnings("deprecation")
	public static void getExcel(String sheetName, String excelName,
			List<Car> list, int count) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("车道");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("车速（km/h）");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("重量（吨）");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("轴数（个）");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("车牌号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("照片地址");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);
		cell.setCellValue("上/下游");
		cell.setCellStyle(style);

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Car car = (Car) list.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0)
					.setCellValue(car.getDatetime().toString());
			row.createCell((short) 1).setCellValue(car.getLane());
			row.createCell((short) 2).setCellValue( car.getVelocity());
			row.createCell((short) 3).setCellValue( car.getWeight());
			row.createCell((short) 4).setCellValue(car.getAxis());
			row.createCell((short) 5).setCellValue(car.getCarnumber());
			row.createCell((short) 6).setCellValue(car.getPhoto());
			row.createCell((short) 7).setCellValue(car.getStream());
		}
		row = sheet.createRow((int) list.size() + 1);
		row.createCell((short) 0).setCellValue("超重数量：");
		row.createCell((short) 1).setCellValue((int) count);
		row.createCell((short) 2).setCellValue("车辆总数：");
		row.createCell((short) 3).setCellValue((int) list.size());

		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream(Path + excelName);
			wb.write(fout);
			fout.close();
			// System.out.println("Excel表导出成功！");
		} catch (Exception e) {
			System.out.println("Excel表导出失败，失败原因：" + e.toString());
		}
	}

	/**
	 * 汇总Excel表的导出
	 * 
	 * @param sheetName
	 * @param excelName
	 * @param map
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void getExcel(String sheetName, String excelName,
			Map<String, Object> map) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("地点");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("起始时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("结束时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("最重");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("车辆总数");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("超重总数");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("找到车牌");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);
		cell.setCellValue("大于55吨");
		cell.setCellStyle(style);
		cell = row.createCell((short) 8);
		cell.setCellValue("大于75吨");
		cell.setCellStyle(style);

		List<Map<String, Object>> list = (List<Map<String, Object>>) map
				.get("rows");
		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Map<String, Object> map_ = list.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue((String) map_.get("place"));
			row.createCell((short) 1).setCellValue(
					(String) map_.get("start_time"));
			row.createCell((short) 2).setCellValue(
					(String) map_.get("end_time"));
			row.createCell((short) 3).setCellValue(
					(Double) map_.get("weightest"));
			row.createCell((short) 4).setCellValue(
					(Integer) map_.get("totalnumber"));
			row.createCell((short) 5).setCellValue(
					(Integer) map_.get("overnumber"));
			row.createCell((short) 6).setCellValue(
					(Integer) map_.get("findcarnumber"));
			row.createCell((short) 7).setCellValue(
					(Integer) map_.get("over55number"));
			row.createCell((short) 8).setCellValue(
					(Integer) map_.get("over75number"));
		}

		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream(Path + excelName);
			wb.write(fout);
			fout.close();
			System.out.println("Excel表导出成功！");
		} catch (Exception e) {
			System.out.println("Excel表导出失败，失败原因：" + e.toString());
		}
	}

	/**
	 * 汇总Excel表的导出
	 * 
	 * @param sheetName
	 * @param excelName
	 * @param map
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void getWMExcel(String sheetName, String excelName,
			Map<String, Object> map) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = null;
		cell = row.createCell((short) 0);
		cell.setCellValue("              ");
		cell.setCellStyle(style);
		for (int i = 0; i < 10; i++) {
			cell = row.createCell((short) i + 1);
			cell.setCellValue("0" + i + ":00:00 ~ 0" + i + ":59:59");
			cell.setCellStyle(style);
		}
		for (int i = 10; i < 24; i++) {
			cell = row.createCell((short) i + 1);
			cell.setCellValue(i + ":00:00 ~ " + i + ":59:59");
			cell.setCellStyle(style);
		}
		cell = row.createCell((short) 25);
		cell.setCellValue("总数");
		cell.setCellStyle(style);

		List<Map<String, Object>> list = (List<Map<String, Object>>) map
				.get("rows");
		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Map<String, Object> map_ = list.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue((String) map_.get("time"));
			for (int j = 1; j <= map_.size() - 2; j++) {
				row.createCell((short) j).setCellValue(
						(Integer) map_.get("hour" + j));
			}
			row.createCell((short) 25).setCellValue(
					(Integer) map_.get("number"));
		}

		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream(Path + excelName);
			wb.write(fout);
			fout.close();
			System.out.println("Excel表导出成功！");
		} catch (Exception e) {
			System.out.println("Excel表导出失败，失败原因：" + e.toString());
		}
	}

	/**
	 * 汇总Excel表的导出
	 * 
	 * @param sheetName
	 * @param excelName
	 * @param map
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void getDayExcel(String sheetName, String excelName,
			Map<String, Object> map) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("地点");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("最重");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("车辆总数");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("超重总数");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("找到车牌");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("大于55吨");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);
		cell.setCellValue("大于75吨");
		cell.setCellStyle(style);

		List<Map<String, Object>> list = (List<Map<String, Object>>) map
				.get("rows");
		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Map<String, Object> map_ = list.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue((String) map_.get("place"));
			row.createCell((short) 1).setCellValue((String) map_.get("time"));
			row.createCell((short) 2).setCellValue(
					(Double) map_.get("weightest"));
			row.createCell((short) 3).setCellValue(
					(Integer) map_.get("totalnumber"));
			row.createCell((short) 4).setCellValue(
					(Integer) map_.get("overnumber"));
			row.createCell((short) 5).setCellValue(
					(Integer) map_.get("findcarnumber"));
			row.createCell((short) 6).setCellValue(
					(Integer) map_.get("over55number"));
			row.createCell((short) 7).setCellValue(
					(Integer) map_.get("over75number"));
		}

		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream(Path + excelName);
			wb.write(fout);
			fout.close();
			System.out.println("Excel表导出成功！");
		} catch (Exception e) {
			System.out.println("Excel表导出失败，失败原因：" + e.toString());
		}
	}
}
