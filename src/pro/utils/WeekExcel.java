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
public class WeekExcel {
	// 获取项目的WebRoot目录
	public static String classPath = Thread.currentThread()
			.getContextClassLoader().getResource("").toString();
	public static String Path = classPath.replace("file:/", "")
			.replace("WEB-INF/classes/", "").replace("/", "\\")
			.replace("%20", " ")
			+ "excel\\";
	public static void getExcel(String sheetName, String excelName,
			List<Map<String, Object>> list, String starttime,String endtime)
	{
		Session ss = HibernateUtil.getSession();
		Query query = ss.createQuery("from User u" );
		User user = (User) query.uniqueResult();
	    String bridgename=user.getBridge_name();
	    //System.out.println(bridgename);
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
			String a="中心"+bridgename+"超限超载车过桥周记录表";
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
			
			HSSFRow row = sheet.createRow(0);			
			sheet.setColumnWidth(0, 17 * 256);				//定义每列宽度
			sheet.setColumnWidth(1, 17 * 256);
			sheet.setColumnWidth(2, 13 * 256);
			sheet.setColumnWidth(3, 13 * 256);
			sheet.setColumnWidth(4, 13 * 256);
			row.setHeightInPoints(43);
			HSSFCell cell=row.createCell(0);
			CellRangeAddress region=new CellRangeAddress(0, 0, 0, 4);  //首行1~5列单元格合并
			sheet.addMergedRegion(region);
			cell.setCellValue(textString);
			cell.setCellStyle(style);
			
			HSSFRow row1_ = sheet.createRow((int) 1);
			row1_.setHeightInPoints(22);
			HSSFCellStyle style1_ = workbook.createCellStyle();
			style1_.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style1_.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style1_.setBorderTop(HSSFCellStyle. BORDER_MEDIUM);     //上下左粗边框右细
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
			style2_.setBorderTop(HSSFCellStyle. BORDER_MEDIUM);   //上下粗边框右细
			style2_.setBorderBottom(HSSFCellStyle. BORDER_MEDIUM);
			style2_.setBorderRight(HSSFCellStyle. BORDER_THIN);
			style2_.setFont(font1_);
			
			HSSFCellStyle style3_ = workbook.createCellStyle();
			style3_.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
			style3_.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style3_.setBorderTop(HSSFCellStyle. BORDER_MEDIUM);     //上下右粗边框
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
			
			HSSFCellStyle style7_ = workbook.createCellStyle();
			style7_.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style7_.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			style7_.setBorderTop(HSSFCellStyle. BORDER_MEDIUM); 
			style7_.setBorderBottom(HSSFCellStyle. BORDER_MEDIUM);
			style7_.setBorderLeft(HSSFCellStyle. BORDER_MEDIUM);
			style7_.setBorderRight(HSSFCellStyle. BORDER_THIN);
			style7_.setFont(font1_);
			
			HSSFCell cell1=row1_.createCell(0);
			cell1.setCellValue("地点");
			cell1.setCellStyle(style1_);
			cell1 = row1_.createCell(1);
			cell1.setCellValue("时间");
			cell1.setCellStyle(style2_);
			cell1 = row1_.createCell(2);
			cell1.setCellValue("超重总数");
			cell1.setCellStyle(style2_);
			cell1 = row1_.createCell(3);
			cell1.setCellValue("大于55吨");
			cell1.setCellStyle(style2_);
			cell1 = row1_.createCell(4);
			cell1.setCellValue("大于75吨 ");
			cell1.setCellStyle(style3_);
			
			
			for (int i = 0; i < list.size(); i++) {
				//System.out.println("test：" + i);
				row = sheet.createRow((int) i + 2);
				row.setHeightInPoints(22);
				Map<String, Object> map_=list.get(i);
				// 第四步，创建单元格，并设置值
				HSSFCell cell0_=row.createCell(0);
				cell0_.setCellStyle(style4_);
				cell0_.setCellValue((String)map_.get("address"));
				HSSFCell cell1_=row.createCell(1);
				cell1_.setCellValue((String)map_.get("date"));
				cell1_.setCellStyle(style5_);
				HSSFCell cell2_=row.createCell(2);
				cell2_.setCellValue((Integer)map_.get("num"));
				cell2_.setCellStyle(style5_);
				HSSFCell cell3_=row.createCell(3);
				cell3_.setCellValue(map_.get("over55").toString());
				cell3_.setCellStyle(style5_);
				HSSFCell cell4_=row.createCell(4);
				cell4_.setCellValue(map_.get("over75").toString());
				cell4_.setCellStyle(style6_);
			                   }
			
			row = sheet.createRow(list.size()+ 2);
			row.setHeightInPoints(88);
			HSSFCell cell2=row.createCell(0);
			cell2.setCellStyle(style7_);
			cell2.setCellValue("备注说明：");
			cell2 = row.createCell(1);
	      	cell2.setCellStyle(style2_);
			cell2 = row.createCell(2);
			cell2.setCellStyle(style2_);
			cell2 = row.createCell(3);
			cell2.setCellStyle(style2_);
			cell2 = row.createCell(4);
			cell2.setCellStyle(style3_);
			CellRangeAddress region2=new CellRangeAddress(list.size()+1, list.size()+1, 0, 4);  //首行1~5列单元格合并
			sheet.addMergedRegion(region2);
			
			row = sheet.createRow(list.size()+ 3);
			row.createCell(0).setCellValue("填表人:");
			row.createCell(3).setCellValue("部门负责人:");
			
			
			
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
