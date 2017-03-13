package pro.utils;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.hibernate.Query;
import pro.hibernate.util.HibernateUtil;
import pro.dao.entity.Car;
import pro.dao.entity.User;

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
	//@SuppressWarnings("deprecation")
	public static void getExcel(String sheetName, String excelName,
			List<Car> list, int count,String starttime,String endtime) {  //已更新 by sithle
		// 第一步，创建一个webbook，对应一个Excel文件
		 HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Test");// 创建工作表(Sheet)
			HSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style.setWrapText(true);
			
			HSSFFont font = workbook.createFont();//设置首行标题字体
			font.setFontName("楷体");
			font.setFontHeightInPoints((short)16);
			
			HSSFFont font2 = workbook.createFont();//设置首行日期字体
			font2.setFontName("楷体");
			font2.setFontHeightInPoints((short)12);
			String a="中心天兴洲大桥车辆明细表";
			String b=starttime+" —— "+endtime;
			String[] subStr ={a,b};
			String sText = subStr[0]+"\r\n"  + subStr[1];
			HSSFRichTextString textString = new HSSFRichTextString(sText); 
			textString.applyFont( 										//为首行的标题和日期设置不同的字体
					sText.indexOf(subStr[0]), 
					sText.indexOf(subStr[0]) + subStr[0].length(),
					font);
			textString.applyFont( 
					sText.indexOf(subStr[1]), 
					sText.indexOf(subStr[1]) + subStr[1].length(),
					font2);
			
			//style.setFont(font);
			HSSFRow row = sheet.createRow(0);			
			sheet.setColumnWidth(0, 20 * 256);				//定义每列宽度
			sheet.setColumnWidth(1, 11 * 256);
			sheet.setColumnWidth(2, 11 * 256);
			sheet.setColumnWidth(3, 11 * 256);
			sheet.setColumnWidth(4, 11 * 256);
			sheet.setColumnWidth(5, 16 * 256);
			sheet.setColumnWidth(6, 18 * 256);
			sheet.setColumnWidth(7, 16 * 256);
			row.setHeightInPoints(43);
			HSSFCell cell=row.createCell(0);
			

			
			
			CellRangeAddress region=new CellRangeAddress(0, 0, 0, 7);  //首行1~8列单元格合并
			sheet.addMergedRegion(region);
			cell.setCellValue(textString);
			cell.setCellStyle(style);
			
			
			
			HSSFRow row1_ = sheet.createRow((int) 1);
			row1_.setHeightInPoints(22);
			HSSFCellStyle style1_ = workbook.createCellStyle();
			style1_.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style1_.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style1_.setBorderTop(HSSFCellStyle. BORDER_MEDIUM);
			style1_.setBorderBottom(HSSFCellStyle. BORDER_MEDIUM);
			style1_.setBorderLeft(HSSFCellStyle. BORDER_MEDIUM);
			style1_.setBorderRight(HSSFCellStyle. BORDER_THIN);
			HSSFFont font1_ = workbook.createFont();
			font1_.setFontName("宋体");
			font1_.setFontHeightInPoints((short)10);
			style1_.setFont(font1_);
			
			HSSFCellStyle style2_ = workbook.createCellStyle();
			style2_.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style2_.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style2_.setBorderTop(HSSFCellStyle. BORDER_MEDIUM);
			style2_.setBorderBottom(HSSFCellStyle. BORDER_MEDIUM);
			style2_.setBorderRight(HSSFCellStyle. BORDER_THIN);
			style2_.setFont(font1_);
			
			HSSFCellStyle style3_ = workbook.createCellStyle();
			style3_.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style3_.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style3_.setBorderTop(HSSFCellStyle. BORDER_MEDIUM);
			style3_.setBorderBottom(HSSFCellStyle. BORDER_MEDIUM);
			style3_.setBorderRight(HSSFCellStyle. BORDER_MEDIUM);
			style3_.setFont(font1_);
			
			HSSFCellStyle style4_ = workbook.createCellStyle();
			style4_.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style4_.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style4_.setBorderLeft(HSSFCellStyle. BORDER_MEDIUM);
			style4_.setBorderBottom(HSSFCellStyle. BORDER_THIN);
			style4_.setBorderRight(HSSFCellStyle. BORDER_THIN);
			style4_.setFont(font1_);
			
			HSSFCellStyle style5_ = workbook.createCellStyle();
			style5_.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style5_.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style5_.setBorderBottom(HSSFCellStyle. BORDER_THIN);
			style5_.setBorderRight(HSSFCellStyle. BORDER_THIN);
			style5_.setFont(font1_);
			
			HSSFCellStyle style6_ = workbook.createCellStyle();
			style6_.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style6_.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style6_.setBorderBottom(HSSFCellStyle. BORDER_THIN);
			style6_.setBorderRight(HSSFCellStyle. BORDER_MEDIUM);
			style6_.setFont(font1_);
			
			
			HSSFCell cell1=row1_.createCell(0);
			cell1.setCellValue("时间");
			cell1.setCellStyle(style1_);
			cell1 = row1_.createCell(1);
			cell1.setCellValue("车道");
			cell1.setCellStyle(style2_);
			cell1 = row1_.createCell(2);
			cell1.setCellValue("车速（km/h）");
			cell1.setCellStyle(style2_);
			cell1 = row1_.createCell(3);
			cell1.setCellValue("重量（吨）");
			cell1.setCellStyle(style2_);
			cell1 = row1_.createCell(4);
			cell1.setCellValue("轴数（个）");
			cell1.setCellStyle(style2_);
			cell1 = row1_.createCell(5);
			cell1.setCellValue("车牌号");
			cell1.setCellStyle(style2_);
			cell1 = row1_.createCell(6);
			cell1.setCellValue("照片地址");
			cell1.setCellStyle(style2_);
			cell1 = row1_.createCell(7);
			cell1.setCellValue("上/下游");
			cell1.setCellStyle(style3_);
			for (int i = 1; i < list.size(); i++) {
				//System.out.println("test：" + i);
				row = sheet.createRow((int) i + 1);
				row.setHeightInPoints(22);
				Car car = (Car) list.get(i);
				// 第四步，创建单元格，并设置值
				HSSFCell cell0_=row.createCell(0);
				cell0_.setCellStyle(style4_);
				cell0_.setCellValue(car.getDatetime().toString().substring(0, 19));
				HSSFCell cell1_=row.createCell(1);
				cell1_.setCellValue(car.getLane());
				cell1_.setCellStyle(style5_);
				HSSFCell cell2_=row.createCell(2);
				cell2_.setCellValue( car.getVelocity());
				cell2_.setCellStyle(style5_);
				HSSFCell cell3_=row.createCell(3);
				cell3_.setCellValue( car.getWeight());
				cell3_.setCellStyle(style5_);
				HSSFCell cell4_=row.createCell(4);
				cell4_.setCellValue(car.getAxis());
				cell4_.setCellStyle(style5_);
				HSSFCell cell5_=row.createCell(5);
				cell5_.setCellValue(car.getCarnumber());
				cell5_.setCellStyle(style5_);
				HSSFCell cell6_=row.createCell(6);
				cell6_.setCellValue(car.getPhoto());
				cell6_.setCellStyle(style5_);
				HSSFCell cell7_=row.createCell(7);
				cell7_.setCellValue(car.getStream());
				cell7_.setCellStyle(style6_);
			}
			
			row = sheet.createRow((int) list.size() + 1);
			row.setHeightInPoints(22);
			HSSFCell cell0_=row.createCell(0);
			cell0_.setCellStyle(style1_);
			cell0_.setCellValue("超重数量：");
			HSSFCell cell1_=row.createCell(1);
			cell1_.setCellStyle(style2_);
			cell1_.setCellValue((int)count);
			HSSFCell cell2_=row.createCell(2);
			cell2_.setCellStyle(style2_);
			cell2_.setCellValue("车辆总数：");
			HSSFCell cell3_=row.createCell(3);
			cell3_.setCellStyle(style2_);
			cell3_.setCellValue((int) list.size());
			HSSFCell cell4_=row.createCell(4);
			cell4_.setCellStyle(style2_);
			cell4_.setCellValue("\\");
			HSSFCell cell5_=row.createCell(5);
			cell5_.setCellStyle(style2_);
			cell5_.setCellValue("\\");
			HSSFCell cell6_=row.createCell(6);
			cell6_.setCellStyle(style2_);
			cell6_.setCellValue("/");
			HSSFCell cell7_=row.createCell(7);
			cell7_.setCellStyle(style3_);
			cell7_.setCellValue("/");

		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream(Path + excelName);
			workbook.write(fout);
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
	public static void getBlacklistExcel(String sheetName, String excelName,List<Map<String, Object>> list,String starttime,String endtime)
	{
		//System.out.println("123123211111111");
		//System.out.println(list);
		Session ss = HibernateUtil.getSession();
		Query query = ss.createQuery("from User u" );
		User user = (User) query.uniqueResult();
	    String bridgename=user.getBridge_name();
	    //System.out.println(bridgename);
		 HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(sheetName);// 创建工作表(Sheet)
			HSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style.setWrapText(true);
			
			HSSFFont font = workbook.createFont();//设置首行标题字体
			font.setFontName("楷体");
			font.setFontHeightInPoints((short)16);
			
			HSSFFont font2 = workbook.createFont();//设置首行日期字体
			font2.setFontName("楷体");
			font2.setFontHeightInPoints((short)12);
			String a=bridgename+"超限超载车辆黑名单";
			String b=starttime+" —— "+endtime;
			String[] subStr ={a,b};
			String sText = subStr[0]+"\r\n"  + subStr[1];
			HSSFRichTextString textString = new HSSFRichTextString(sText); 
			textString.applyFont( 										//为首行的标题和日期设置不同的字体
					sText.indexOf(subStr[0]), 
					sText.indexOf(subStr[0]) + subStr[0].length(),
					font);
			textString.applyFont( 
					sText.indexOf(subStr[1]), 
					sText.indexOf(subStr[1]) + subStr[1].length(),
					font2);
			
			//style.setFont(font);
			HSSFRow row = sheet.createRow(0);			
			sheet.setColumnWidth(0, 15 * 256);				//定义每列宽度
			sheet.setColumnWidth(1, 10 * 256);
			sheet.setColumnWidth(2, 10 * 256);
			sheet.setColumnWidth(3, 15 * 256);
			sheet.setColumnWidth(4, 20 * 256);
			sheet.setColumnWidth(5, 17 * 256);

			row.setHeightInPoints(43);
			HSSFCell cell=row.createCell(0);
			

			CellRangeAddress region=new CellRangeAddress(0, 0, 0, 5);  //首行1~6列单元格合并
			sheet.addMergedRegion(region);
			cell.setCellValue(textString);
			cell.setCellStyle(style);
			
			HSSFRow row1_ = sheet.createRow((int) 1);
			row1_.setHeightInPoints(22);
			HSSFCellStyle style1_ = workbook.createCellStyle();
			style1_.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style1_.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style1_.setBorderTop(HSSFCellStyle. BORDER_MEDIUM);
			style1_.setBorderBottom(HSSFCellStyle. BORDER_MEDIUM);
			style1_.setBorderLeft(HSSFCellStyle. BORDER_MEDIUM);
			style1_.setBorderRight(HSSFCellStyle. BORDER_THIN);
			HSSFFont font1_ = workbook.createFont();
			font1_.setFontName("宋体");
			font1_.setFontHeightInPoints((short)10);
			style1_.setFont(font1_);
			
			HSSFCellStyle style2_ = workbook.createCellStyle();
			style2_.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style2_.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style2_.setBorderTop(HSSFCellStyle. BORDER_MEDIUM);
			style2_.setBorderBottom(HSSFCellStyle. BORDER_MEDIUM);
			style2_.setBorderRight(HSSFCellStyle. BORDER_THIN);
			style2_.setFont(font1_);
			
			HSSFCellStyle style3_ = workbook.createCellStyle();
			style3_.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style3_.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style3_.setBorderTop(HSSFCellStyle. BORDER_MEDIUM);
			style3_.setBorderBottom(HSSFCellStyle. BORDER_MEDIUM);
			style3_.setBorderRight(HSSFCellStyle. BORDER_MEDIUM);
			style3_.setFont(font1_);
			
			HSSFCellStyle style4_ = workbook.createCellStyle();
			style4_.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style4_.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style4_.setBorderLeft(HSSFCellStyle. BORDER_MEDIUM);
			style4_.setBorderBottom(HSSFCellStyle. BORDER_THIN);
			style4_.setBorderRight(HSSFCellStyle. BORDER_THIN);
			style4_.setFont(font1_);
			
			HSSFCellStyle style5_ = workbook.createCellStyle();
			style5_.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style5_.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style5_.setBorderBottom(HSSFCellStyle. BORDER_THIN);
			style5_.setBorderRight(HSSFCellStyle. BORDER_THIN);
			style5_.setFont(font1_);
			
			HSSFCellStyle style6_ = workbook.createCellStyle();
			style6_.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style6_.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style6_.setBorderBottom(HSSFCellStyle. BORDER_THIN);
			style6_.setBorderRight(HSSFCellStyle. BORDER_MEDIUM);
			style6_.setFont(font1_);
			
			
			HSSFCell cell1=row1_.createCell(0);
			cell1.setCellValue("车牌");
			cell1.setCellStyle(style1_);
			cell1 = row1_.createCell(1);
			cell1.setCellValue("通过次数");
			cell1.setCellStyle(style2_);
			cell1 = row1_.createCell(2);
			cell1.setCellValue("轴数（个）");
			cell1.setCellStyle(style2_);
			cell1 = row1_.createCell(3);
			cell1.setCellValue("最重时重量（吨）");
			cell1.setCellStyle(style2_);
			cell1 = row1_.createCell(4);
			cell1.setCellValue("最近一次通过时间 ");
			cell1.setCellStyle(style2_);
			cell1 = row1_.createCell(5);
			cell1.setCellValue("最近一次重量（吨）");
			cell1.setCellStyle(style3_);
			
			for (int i = 1; i < list.size(); i++) {
				//System.out.println("test：" + i);
				row = sheet.createRow((int) i + 1);
				row.setHeightInPoints(22);
				Map<String, Object> map=list.get(i);
				// 第四步，创建单元格，并设置值
				HSSFCell cell0_=row.createCell(0);
				cell0_.setCellStyle(style4_);
				cell0_.setCellValue((String)map.get("carnumber"));
				HSSFCell cell1_=row.createCell(1);
				cell1_.setCellValue((Integer)map.get("total"));
				cell1_.setCellStyle(style5_);
				HSSFCell cell2_=row.createCell(2);
				cell2_.setCellValue((String)map.get("axis"));
				cell2_.setCellStyle(style5_);
				HSSFCell cell3_=row.createCell(3);
				cell3_.setCellValue((Double)map.get("weightest"));
				cell3_.setCellStyle(style5_);
				HSSFCell cell4_=row.createCell(4);
				cell4_.setCellValue(map.get("latest").toString().substring(0, 19));
				cell4_.setCellStyle(style5_);
				HSSFCell cell5_=row.createCell(5);
				cell5_.setCellValue((Double)map.get("latestweight"));
				cell5_.setCellStyle(style6_);
			                                  }
			row = sheet.createRow((int) list.size() + 1);
			row.setHeightInPoints(22);
			HSSFCell cell0_=row.createCell(0);
			cell0_.setCellStyle(style1_);
			cell0_.setCellValue("车辆总数：");
			HSSFCell cell1_=row.createCell(1);
			cell1_.setCellStyle(style2_);
			cell1_.setCellValue((int) list.size());
			HSSFCell cell2_=row.createCell(2);
			cell2_.setCellStyle(style2_);
			cell2_.setCellValue("\\");
			HSSFCell cell3_=row.createCell(3);
			cell3_.setCellStyle(style2_);
			cell3_.setCellValue("\\");
			HSSFCell cell4_=row.createCell(4);
			cell4_.setCellStyle(style2_);
			cell4_.setCellValue("/");
			HSSFCell cell5_=row.createCell(5);
			cell5_.setCellStyle(style3_);
			cell5_.setCellValue("/");
			try {
				FileOutputStream fout = new FileOutputStream(Path + excelName);
				workbook.write(fout);
				fout.close();
				// System.out.println("Excel表导出成功！");
			} catch (Exception e) {
				System.out.println("Excel表导出失败，失败原因：" + e.toString());
			}
			
			
	}
}
