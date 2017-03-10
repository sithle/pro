package pro.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pro.dao.entity.Car;
import pro.dao.entity.Car2;
import pro.hibernate.util.HibernateUtil;
import pro.json.tools.JsonTools;
import pro.utils.CreateExcel;

public class WMReport extends HttpServlet {

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
				&& request.getParameter("end_time") != null
				&& request.getParameter("stream") != null
				&& request.getParameter("weightStandard") != null) {

			String resultJSONString = "";
			String start_time = request.getParameter("start_time");
			String end_time = request.getParameter("end_time");
			String stream = request.getParameter("stream");
			double weightStandard = Double.valueOf(request
					.getParameter("weightStandard"));
			System.out.println(start_time + " " + end_time + " " + stream + " "
					+ weightStandard);

			// HQL语句
			String hql = "";
			if (stream.equals("上游")) {
				if (!start_time.equals("") && !end_time.equals("")) {
					// 查询在start_time和end_time之间，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= '"
							+ start_time
							+ "' and c.datetime <= '"
							+ end_time
							+ "' and c.weight >= " + weightStandard
							+ " order by c.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= '"
							+ start_time
							+ "' and c.weight >= " + weightStandard
							+ " order by c.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime <= '"
							+ end_time
							+ "' and c.weight >= " + weightStandard
							+ " order by c.datetime asc";
				} else {
					// 默认的HQL语句（查询重量大于weight的数据）
					hql = "from Car c where c.weight >= " + weightStandard
							+ " order by c.datetime asc";
				}
			} else if(stream.equals("下游")){
				if (!start_time.equals("") && !end_time.equals("")) {
					// 查询在start_time和end_time之间，并且重量大于weight的数据
					hql = "from Car2 c where c.datetime >= '"
							+ start_time
							+ "' and c.datetime <= '"
							+ end_time
							+ "' and c.weight >= " + weightStandard
							+ " order by c.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql = "from Car2 c where c.datetime >= '"
							+ start_time
							+ "' and c.weight >= " + weightStandard
							+ " order by c.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql = "from Car2 c where c.datetime <= '"
							+ end_time
							+ "' and c.weight >= " + weightStandard
							+ " order by c.datetime asc";
				} else {
					// 默认的HQL语句（查询重量大于weight的数据）
					hql = "from Car2 c where c.weight >= " + weightStandard
							+ " order by c.datetime asc";
				}
			}else {
				//查询上下游车辆信息
			}

			// 查询，使用HQL语句
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Car2> cars = query.list();
			transaction.commit();

			// 生成要封装成json的map集合
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Timestamp stime = Timestamp.valueOf(start_time);
			Timestamp etime = Timestamp.valueOf(end_time);

			List<Integer> columnCount = new ArrayList<Integer>();
			for (int i = 0; i < 24; i++) {
				columnCount.add(0);
			}

			while (stime.before(etime)) {
				Map<String, Object> map_ = new HashMap<String, Object>();
				map_.put("time", stime.toString().substring(0, 10));
				int rowCount = 0;
				for (int i = 0; i < 24; i++) {
					int count = 0;
					for (Car2 car : cars) {
						Timestamp carTime = car.getDatetime();
						Timestamp hourTime = new Timestamp(
								stime.getTime() + 1000 * 60 * 60);
						if (carTime.after(stime) && carTime.before(hourTime)) {
							count++;
						}
					}
					map_.put("hour" + (i + 1), count);
					rowCount += count;
					columnCount.set(i, columnCount.get(i) + count);
					stime.setTime(stime.getTime() + 1000 * 60 * 60);
					if (stime.after(etime)) {
						break;
					}
				}
				map_.put("number", rowCount);
				list.add(map_);
				System.out.println("rowCount:"+rowCount);
				int averageNum = rowCount/24;
			}
						

			Map<String, Object> map__ = new HashMap<String, Object>();
			map__.put("time", "总数");
			int total = 0;
			for (int i = 0; i < 24; i++) {
				map__.put("hour" + (i + 1), columnCount.get(i));
				total += columnCount.get(i);
				System.out.print("columnCount.get(i):"+columnCount.get(i));
			}
			System.out.println("总数为："+total);
			//平均数为averageNum
			int averageNum = total/24;
			List<Integer> averageTime = new ArrayList<Integer>();
			for (int i = 0; i < 24; i++) {
				if (columnCount.get(i)>averageNum) {
					averageTime.add(i+1);
				}
			}
			map__.put("fastigium", averageTime.toString());
			map__.put("number", total);
			list.add(map__);

			map.put("rows", list);

			// 把Car对象封装成JSON数据
			resultJSONString = JsonTools.createJsonString(map);
			out.println(resultJSONString);
			System.out.println(resultJSONString);

			// 判断是否需要导出该查询内容的Excel表
			if (request.getParameter("isGetExcel").equals("1")) {
				String sheetname = request.getParameter("sheetname");
				String excelname = request.getParameter("excelname");
				CreateExcel.getWMExcel(sheetname, excelname + ".xls", map);
			}
		}

		out.flush();
		out.close();
	}

}
