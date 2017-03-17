package pro.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import org.hibernate.Query;
import org.hibernate.Session;

import pro.dao.entity.Car;
import pro.dao.entity.Car2;
import pro.dao.entity.User;
import pro.hibernate.util.HibernateUtil;
import pro.json.tools.JsonTools;
import pro.utils.WeekExcel;

public class MonthReport extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/x-json");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("start_time") != null
				&& request.getParameter("weightStandard") != null) {

			//String start_time = request.getParameter("start_time");
			double weight = Double.valueOf(request
					.getParameter("weightStandard"));
			String dateString = request.getParameter("start_time");
			List<String> datelist = new ArrayList<String>();
			//datelist.add(dateString);
			String dateString_=new String();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, Object> map_1 = new HashMap<String, Object>();
			Map<String, Object> map_2 = new HashMap<String, Object>();
			Map<String, Object> map_3 = new HashMap<String, Object>();
			Map<String, Object> map_4 = new HashMap<String, Object>();
			Map<String, Object> map_5 = new HashMap<String, Object>();
			Map<String, Object> map_6 = new HashMap<String, Object>();
			Map<String, Object> map_json = new HashMap<String, Object>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Date date=new Date();
			Session ss = HibernateUtil.getSession();
			Query query = ss.createQuery("from User u" );
			User user = (User) query.uniqueResult();
		    String bridgename=user.getBridge_name();
		    //double weight=0.0;
		    int total_1=0,over55_1=0,over75_1=0,total_2=0,over55_2=0,over75_2=0;
		    //int total_3=0,over55_3=0,over75_3=0,avg_3=0;	
			try{
			
		    date = sdf.parse(dateString);

			}
			catch (ParseException e) {
				System.out.println(e.getMessage());
			}
			Calendar cal=Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.DATE, 1);
			cal.roll(Calendar.DATE, -1);
			int maxDate = cal.get(Calendar.DATE); 
			cal.set(Calendar.DATE, 1);
			dateString_=sdf.format(cal.getTime());
			datelist.add(dateString_);
			for(int i=0;i<maxDate;i++)
				{
				cal.add(Calendar.DATE, 1);
				dateString_=sdf.format(cal.getTime());
				datelist.add(dateString_);
				}
			System.out.println(datelist);
			for(int i=0;i<datelist.size()-1;i++)
				{	
				   System.out.println(1);
				
				   Map<String, Object> map = new HashMap<String, Object>();
					map.put("address", bridgename+"上游");
					System.out.println(21);
					map.put("date", datelist.get(i));
					System.out.println(2);
					String hqlString1="select count(*) from Car c where  c.datetime >= '"
									+ datelist.get(i)
									+ "' and c.datetime <= '"
									+ datelist.get(i+1)
									+ "' and c.weight >= "
									+ weight+"and c.flag='0'";
					Query query2 = ss.createQuery(hqlString1);
					int num=((Number)query2.uniqueResult()).intValue(); 
					total_1+=num;
					map.put("num",num);
					map.put("address", bridgename+"上游");
					map.put("date", datelist.get(i));
					String hqlString2="select count(*) from Car c where  c.datetime >= '"
									+ datelist.get(i)
									+ "' and c.datetime <= '"
									+ datelist.get(i+1)
									+ "' and c.weight >= "
									+ 55+"and c.flag='0'";
					Query query3 = ss.createQuery(hqlString2);
					int over55=((Number)query3.uniqueResult()).intValue(); 
					over55_1+=over55;
					map.put("over55",over55);
					String hqlString3="select count(*) from Car c where  c.datetime >= '"
							+ datelist.get(i)
							+ "' and c.datetime <= '"
							+ datelist.get(i+1)
							+ "' and c.weight >= "
							+ 75+"and c.flag='0'";
					Query query4 = ss.createQuery(hqlString3);
					int over75=((Number)query4.uniqueResult()).intValue(); 
					over75_1+=over75;
					map.put("over75",over75);
					list.add(map);
					}
					map_1.put("address", "上游合计");
					map_1.put("date", "\\");
					map_1.put("num", total_1);
					map_1.put("over55", over55_1);
					map_1.put("over75", over75_1);
					list.add(map_1);
					map_2.put("address", "上游日均");
					map_2.put("date", "\\");
					map_2.put("num", total_1/maxDate);
					map_2.put("over55", "/");
					map_2.put("over75", "/");
					list.add(map_2);
					
					
					for(int i=0;i<datelist.size()-1;i++)
					{	
					   System.out.println(1);
					
					   Map<String, Object> map = new HashMap<String, Object>();
						map.put("address", bridgename+"下游");
						System.out.println(21);
						map.put("date", datelist.get(i));
						System.out.println(2);
						String hqlString1="select count(*) from Car2 c where  c.datetime >= '"
										+ datelist.get(i)
										+ "' and c.datetime <= '"
										+ datelist.get(i+1)
										+ "' and c.weight >= "
										+ weight+"and c.flag='0'";
						Query query2 = ss.createQuery(hqlString1);
						int num=((Number)query2.uniqueResult()).intValue(); 
						total_2+=num;
						map.put("num",num);
						map.put("address", bridgename+"下游");
						map.put("date", datelist.get(i));
						String hqlString2="select count(*) from Car2 c where  c.datetime >= '"
										+ datelist.get(i)
										+ "' and c.datetime <= '"
										+ datelist.get(i+1)
										+ "' and c.weight >= "
										+ 55+"and c.flag='0'";
						Query query3 = ss.createQuery(hqlString2);
						int over55=((Number)query3.uniqueResult()).intValue(); 
						over55_2+=over55;
						map.put("over55",over55);
						String hqlString3="select count(*) from Car2 c where  c.datetime >= '"
								+ datelist.get(i)
								+ "' and c.datetime <= '"
								+ datelist.get(i+1)
								+ "' and c.weight >= "
								+ 75+"and c.flag='0'";
						Query query4 = ss.createQuery(hqlString3);
						int over75=((Number)query4.uniqueResult()).intValue(); 
						over75_2+=over75;
						map.put("over75",over75);
						list.add(map);
						}
					map_3.put("address", "下游合计");
					map_3.put("date", "\\");
					map_3.put("num", total_2);
					map_3.put("over55", over55_1);
					map_3.put("over75", over75_1);
					list.add(map_3);
					map_4.put("address", "下游日均");
					map_4.put("date", "\\");
					map_4.put("num", total_2/maxDate);
					map_4.put("over55", "/");
					map_4.put("over75", "/");
					list.add(map_4);
					map_5.put("address", "周合计数");
					map_5.put("date", "\\");
					map_5.put("num", total_2+total_1);
					map_5.put("over55", over55_1+over55_2);
					map_5.put("over75", over75_1+over75_2);
					list.add(map_5);
					map_6.put("address", "本周日平均数");
					map_6.put("date", "\\");
					map_6.put("num", (total_2+total_1)/maxDate);
					map_6.put("over55", "/");
					map_6.put("over75", "/");
					list.add(map_6);
					System.out.println(list);
					System.out.println(list);
					map_json.put("rows", list);

			


			String resultJSONString = "";
			// 把Car对象封装成JSON数据
			resultJSONString = JsonTools.createJsonString(map_json);
			out.println(resultJSONString);
			System.out.println(resultJSONString);

			if (request.getParameter("isGetExcel").equals("1")) {
				String sheetname = request.getParameter("sheetname");
				String excelname = request.getParameter("excelname");
				WeekExcel
						.getExcel(sheetname, excelname + ".xls",list,dateString,datelist.get(maxDate-1));
				
				out.println("<!DOCTYPE HTML>");
				out.println("<HTML>");
				//自动跳转
				out.print("<meta http-equiv= refresh content=3;url=http://192.168.6.123:8080/pro/servlet/DownloadServlet?filename="+excelname+".xls>");
				out.println("  <HEAD><TITLE></TITLE></HEAD>");
				out.println("  <BODY>");
				out.println("<div class='alert alert-success'>正在下载...</div>");
				out.println("  </BODY>");
				out.println("</HTML>");
			}


	}
		out.flush();
		out.close();
	}

}