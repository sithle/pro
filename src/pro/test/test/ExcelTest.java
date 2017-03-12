package pro.test.test;
import org.junit.Test;
import pro.dao.entity.Car;
import pro.hibernate.util.HibernateUtil;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.util.CellRangeAddress;

import pro.dao.entity.Car;
public class ExcelTest {
	@Test
	public  void test() {
		String start_time = "2016-09-01 00:00:00";
		String end_time= "2016-09-01 20:00:00";
		String hql = "from Car c where c.datetime >= '"
				+ start_time
				+ "'and c.datetime<='"+end_time+"'";
		int count=100;
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("查询的hql语句：" + hql);
		Query query = session.createQuery(hql);
		List<Car> list = query.list();
		System.out.println("casr：" + list);
		 HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Test");// 创建工作表(Sheet)
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个水平居中格式
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		style.setWrapText(true);
		
		HSSFFont font = workbook.createFont();
		font.setFontName("楷体");
		font.setFontHeightInPoints((short)16);
		
		HSSFFont font2 = workbook.createFont();
		font2.setFontName("楷体");
		font2.setFontHeightInPoints((short)12);
		String a="Hello";
		String b="word";
		String[] subStr ={a,b};
		String sText = subStr[0]+"\r\n"  + subStr[1];
		HSSFRichTextString textString = new HSSFRichTextString(sText);
		textString.applyFont( 
				sText.indexOf(subStr[0]), 
				sText.indexOf(subStr[0]) + subStr[0].length(),
				font);
		textString.applyFont( 
				sText.indexOf(subStr[1]), 
				sText.indexOf(subStr[1]) + subStr[1].length(),
				font2);
		
		//style.setFont(font);
		HSSFRow row = sheet.createRow(0);
		sheet.setColumnWidth(0, 20 * 256);
		sheet.setColumnWidth(1, 11 * 256);
		sheet.setColumnWidth(2, 11 * 256);
		sheet.setColumnWidth(3, 11 * 256);
		sheet.setColumnWidth(4, 11 * 256);
		sheet.setColumnWidth(5, 16 * 256);
		sheet.setColumnWidth(6, 18 * 256);
		sheet.setColumnWidth(7, 16 * 256);
		row.setHeightInPoints(43);
		HSSFCell cell=row.createCell(0);
		

		
		
		CellRangeAddress region=new CellRangeAddress(0, 0, 0, 7);
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
			System.out.println("test：" + i);
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
		cell2_.setCellValue("超重数量：");
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
		
		/*row = sheet.createRow((int) list.size() + 2);
		row.setHeightInPoints(88);
		CellRangeAddress region2=new CellRangeAddress(list.size() + 2, list.size() + 2, 0, 7);
		sheet.addMergedRegion(region2);*/
		
		
		
		
		String filePath="d:\\sample.xls";
		 
		 try{
		 FileOutputStream out = new FileOutputStream(filePath);
		 workbook.write(out);
		 out.close();
		 System.out.println("OK!");
		 }
		 
		 
		 catch (Exception e) {
				System.out.println("Excel表导出失败，失败原因：" + e.toString());
			}
		
		
		
	}

}
