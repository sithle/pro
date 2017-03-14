package pro.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sun.mail.handlers.image_gif;

import pro.hibernate.util.HibernateUtil;
import pro.json.tools.JsonTools;
import pro.utils.CreateExcel;

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

		String resultJSONString = "";

		if (request.getParameter("year_text") != null
				&& request.getParameter("weight_standard") != null) {
			String yearString = request.getParameter("year_text");
			String weightStandardString = request
					.getParameter("weight_standard");
			System.out.println("年份及超重标准:" + yearString + ";"
					+ weightStandardString);

			Double weightStandard = Double.valueOf(weightStandardString);

			// hql语句
			String hql = null;
			String hql2 = null;

			Calendar cal_start = Calendar.getInstance();
			Calendar cal_end = Calendar.getInstance();

			int yearINT = Integer.valueOf(yearString);
			// 上游每月超重车辆总数统计
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (int i = 1; i < 13; i++) {
				if (i < 12) {
					cal_start.set(yearINT, i, 1);
					cal_end.set(yearINT, i + 1, 1);
				} else {
					cal_start.set(yearINT, i, 1);
					cal_end.set(yearINT, i, 31);
				}

				String start_time = cal_start.toString();
				String end_time = cal_end.toString();
				hql = "select count(c) from Car c where c.datetime >='"
						+ start_time + "'and c.datetime < '" + end_time
						+ "' and c.weight >= '" + weightStandard + "'";
				Session session = HibernateUtil.getSessionFactory()
						.getCurrentSession();
				org.hibernate.Query query = session.createQuery(hql);
				int count = (Integer) query.uniqueResult();
				// list.set(i, count);

				Map<String, Object> map_ = new HashMap<String, Object>();
				map_.put("place", "天兴洲上游");
				map_.put("time", yearString + i);
				map_.put("overnumber", count);
				list.add(map_);
			}
			map.put("rows", list);
			resultJSONString = JsonTools.createJsonString(map);
			out.println(resultJSONString);
			System.out.println(resultJSONString);

			// 下游每月超重车辆总数统计
			List<Integer> list2 = new ArrayList<Integer>();
			for (int i = 1; i < 13; i++) {
				if (i < 12) {
					cal_start.set(yearINT, i, 1);
					cal_end.set(yearINT, i + 1, 1);
				} else {
					cal_start.set(yearINT, i, 1);
					cal_end.set(yearINT, i, 31);
				}

				String start_time = cal_start.toString();
				String end_time = cal_end.toString();
				hql = "select count(c2) from Car2 c2 where c2.datetime >='"
						+ start_time + "'and c2.datetime < '" + end_time
						+ "' and c2.weight >= '" + weightStandard + "'";
				Session session = HibernateUtil.getSessionFactory()
						.getCurrentSession();
				org.hibernate.Query query = session.createQuery(hql);
				int count = (Integer) query.uniqueResult();
				list2.set(i, count);
			}

			// out.println(list);
			// 判断是否需要导出该查询内容的Excel表
			if (request.getParameter("isGetExcel").equals("1")) {
				String sheetname = request.getParameter("sheetname");
				String excelname = request.getParameter("excelname");
				CreateExcel.getDayExcel(sheetname, excelname + ".xls", map);
			}
		}

		out.flush();
		out.close();
	}

	/**
	 * Returns information about the servlet, such as author, version, and
	 * copyright.
	 * 
	 * @return String information about this servlet
	 */
	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
