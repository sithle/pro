package pro.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import antlr.debug.TraceAdapter;

import com.sun.mail.handlers.image_gif;

import pro.hibernate.util.HibernateUtil;
import pro.json.tools.JsonTools;
import pro.utils.YearExcel;

public class YearReport extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/x-json");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String startTimeString = request.getParameter("start_time");
		String endTimeString = request.getParameter("end_time");
		String yearTimeString = request.getParameter("year_text");
//		String yearString = "2017";
		String stream= request.getParameter("stream");
		String weightStandardString = request.getParameter("weightStandard");
		System.out.println("开始时间:"+startTimeString+"结束时间："+endTimeString+"年份："+yearTimeString+"上下游："+stream);
		
		String resultJSONString = "";

		if (yearTimeString != null
				&& weightStandardString != null) {
			System.out.println("输入年及超重标准:" + yearTimeString + ";"
					+ weightStandardString);

			Double weightStandard = Double.valueOf(weightStandardString);

			// hql语句
			String hql = null;
			String hql01 = null;
			String hql02 = null;
			

			Calendar cal_start = Calendar.getInstance();
			Calendar cal_end = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			int yearINT = Integer.valueOf(yearTimeString);
			

			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();//datagrid数据
//=====================上游每月超重车辆总数统计===================================================================
			List<Integer> total0 = new ArrayList<Integer>();
			List<Integer> total1 = new ArrayList<Integer>();
			List<Integer> total2 = new ArrayList<Integer>();
			
			for (int i = 0; i < 12; i++) {
				if (i < 11) {
					cal_start.set(yearINT, i, 1);
					cal_end.set(yearINT, i + 1, 1);
					
				} else {
					cal_start.set(yearINT, i, 1);
					cal_end.set(yearINT, i, 31);
				}

				String start_time = df.format(cal_start.getTime());
				String end_time = df.format(cal_end.getTime());
				System.out.println("起始时间："+start_time+"终止时间： "+end_time);
				hql = "select count(c) from Car c where c.datetime >='"
						+ start_time + "'and c.datetime < '" + end_time
						+ "' and c.weight >= '" + weightStandard + "'and c.flag ='"+ 0 +"'";
				hql01 = "select count(c) from Car c where c.datetime >='"
						+ start_time + "'and c.datetime < '" + end_time
						+ "' and c.weight >= '" + 55.0 + "'and c.flag ='"+ 0 +"'";
				hql02 = "select count(c) from Car c where c.datetime >='"
						+ start_time + "'and c.datetime < '" + end_time
						+ "' and c.weight >= '" + 75.0 + "'and c.flag ='"+ 0 +"'";
				Session session = HibernateUtil.getSessionFactory()
						.getCurrentSession();
				Transaction transaction = session.beginTransaction();
				org.hibernate.Query query = session.createQuery(hql);			
				int count = ((Number)query.uniqueResult()).intValue();  
				transaction.commit();
				//55.0
				Session session1 = HibernateUtil.getSessionFactory()
						.getCurrentSession();
				Transaction transaction1 = session1.beginTransaction();
				org.hibernate.Query query1 = session1.createQuery(hql01);			
				int count1 = ((Number)query1.uniqueResult()).intValue();  
				transaction1.commit();
				//75.0
				Session session2 = HibernateUtil.getSessionFactory()
						.getCurrentSession();
				Transaction transaction2 = session2.beginTransaction();
				org.hibernate.Query query2 = session2.createQuery(hql02);			
				int count2 = ((Number)query2.uniqueResult()).intValue();  
				transaction2.commit();
				
				
				// list.set(i, count);
				total0.add(count);
				total1.add(count1);
				total2.add(count2);
				
				Map<String, Object> map_ = new HashMap<String, Object>();
				map_.put("place", "天兴洲大桥上游");
				map_.put("time", yearTimeString + "年"+ (i+1) + "月" );
				map_.put("overnumber", count);
				map_.put("over55number", count1);
				map_.put("over75number", count2);
				list.add(map_);
			}
			Map<String,Object>map_ = new HashMap<String, Object>();
			map_.put("place", "本年上游合计");
			map_.put("time", "/");
			int total00 = 0;
			for (Iterator<Integer> iterator = total0.iterator(); iterator.hasNext();) {
				int i = iterator.next();
				total00 = total00+i;
			}
			int total01 = 0;
			for (Iterator<Integer> iterator = total1.iterator(); iterator.hasNext();) {
				int i = iterator.next();
				total01 = total01+i;
			}
			int total02 = 0;
			for (Iterator<Integer> iterator = total2.iterator(); iterator.hasNext();) {
				int i = iterator.next();
				total02 = total02+i;
			}
			map_.put("overnumber", total00);
			map_.put("over55number", total01);
			map_.put("over75number", total02);
			list.add(map_);
			
			Map<String, Object>map2 = new HashMap<String, Object>();
			map2.put("place", "本年上游日均");
			map2.put("time", "/");
			map2.put("overnumber", total00/365);
			map2.put("over55number", total01/365);
			map2.put("over75number", total02/365);
			list.add(map2);
//---------------------------上游数据--------------------------
			
//===========================下游超重车辆数据处理====================
			List<Integer> totala = new ArrayList<Integer>();
			List<Integer> totalb = new ArrayList<Integer>();
			List<Integer> totalc = new ArrayList<Integer>();
			
			String hql2 = null;
			String hql21 = null;
			String hql22 = null;
			for (int i = 0; i < 12; i++) {
				if (i < 11) {
					cal_start.set(yearINT, i, 1);
					cal_end.set(yearINT, i + 1, 1);
					
				} else {
					cal_start.set(yearINT, i, 1);
					cal_end.set(yearINT, i, 31);
				}

				String start_time = df.format(cal_start.getTime());
				String end_time = df.format(cal_end.getTime());
				System.out.println("起始时间："+start_time+"终止时间： "+end_time);
				hql2 = "select count(c2) from Car2 c2 where c2.datetime >='"
						+ start_time + "'and c2.datetime < '" + end_time
						+ "' and c2.weight >= '" + weightStandard + "'and c2.flag ='"+ 0 +"'";
				hql21 = "select count(c2) from Car2 c2 where c2.datetime >='"
						+ start_time + "'and c2.datetime < '" + end_time
						+ "' and c2.weight >= '" + 55.0 + "'and c2.flag ='"+ 0 +"'";
				hql22 = "select count(c2) from Car2 c2 where c2.datetime >='"
						+ start_time + "'and c2.datetime < '" + end_time
						+ "' and c2.weight >= '" + 75.0 + "'and c2.flag ='"+ 0 +"'";
				Session session = HibernateUtil.getSessionFactory()
						.getCurrentSession();
				Transaction transaction = session.beginTransaction();
				org.hibernate.Query query = session.createQuery(hql2);			
				int count = ((Number)query.uniqueResult()).intValue();  
				transaction.commit();
				//55.0
				Session session1 = HibernateUtil.getSessionFactory()
						.getCurrentSession();
				Transaction transaction1 = session1.beginTransaction();
				org.hibernate.Query query1 = session1.createQuery(hql21);			
				int count1 = ((Number)query1.uniqueResult()).intValue();  
				transaction1.commit();
				//75.0
				Session session2 = HibernateUtil.getSessionFactory()
						.getCurrentSession();
				Transaction transaction2 = session2.beginTransaction();
				org.hibernate.Query query2 = session2.createQuery(hql22);			
				int count2 = ((Number)query2.uniqueResult()).intValue();  
				transaction2.commit();
				
				
				// list.set(i, count);
				totala.add(count);
				totalb.add(count1);
				totalc.add(count2);
				
				Map<String, Object> map03 = new HashMap<String, Object>();
				map03.put("place", "天兴洲大桥下游");
				map03.put("time", yearTimeString + "年"+ (i+1) + "月" );
				map03.put("overnumber", count);
				map03.put("over55number", count1);
				map03.put("over75number", count2);
				list.add(map03);
			}
			Map<String,Object>map02 = new HashMap<String, Object>();
			map02.put("place", "本年下游合计");
			map02.put("time", "/");
			int total10 = 0;
			for (Iterator<Integer> iterator = totala.iterator(); iterator.hasNext();) {
				int i = iterator.next();
				total10 = total10+i;
			}
			int total11 = 0;
			for (Iterator<Integer> iterator = totalb.iterator(); iterator.hasNext();) {
				int i = iterator.next();
				total11 = total11+i;
			}
			int total12 = 0;
			for (Iterator<Integer> iterator = totalc.iterator(); iterator.hasNext();) {
				int i = iterator.next();
				total12 = total12+i;
			}
			map02.put("overnumber", total10);
			map02.put("over55number", total11);
			map02.put("over75number", total12);
			list.add(map02);
			
			Map<String, Object>map3 = new HashMap<String, Object>();
			map3.put("place", "本年下游日均");
			map3.put("time", "/");
			map3.put("overnumber", total10/365);
			map3.put("over55number", total11/365);
			map3.put("over75number", total12/365);
			list.add(map3);
//--------------------------下游数据--------------------------------
			Map<String, Object>map40 = new HashMap<String, Object>();
			map40.put("place", "本年车流总计");
			map40.put("time", "/");
			map40.put("overnumber", (total10+total00));
			map40.put("over55number", (total11+total01));
			map40.put("over75number", (total12+total02));
			list.add(map40);
			
			Map<String, Object>map41 = new HashMap<String, Object>();
			map41.put("place", "本年车流日均");
			map41.put("time", "/");
			map41.put("overnumber", (total10+total00)/365);
			map41.put("over55number", (total11+total01)/365);
			map41.put("over75number", (total12+total02)/365);
			list.add(map41);
			
//---------------------------输出-------------------			
			map.put("rows", list);
			resultJSONString = JsonTools.createJsonString(map);
			out.println(resultJSONString);
			System.out.println(resultJSONString);

			// 判断是否需要导出该查询内容的Excel表
			if (request.getParameter("isGetExcel").equals("1")) {
				String sheetname = request.getParameter("sheetname");
				String excelname = request.getParameter("excelname");
				YearExcel.getExcel(sheetname, excelname+".xls", list, yearTimeString);
//				CreateExcel.getDayExcel(sheetname, excelname + ".xls", map);
			}
		}
//		this.StringOutPut("error", response);
		out.flush();
		out.close();
	}
	
	/*public void StringOutPut (String str, HttpServletResponse response){
		System.out.println(str);
		response.setHeader("content-type","text/html;charset=UTF-8");
		//response.setCharacterEncoding("UTF8");
		try {
			response.getOutputStream().write(str.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
