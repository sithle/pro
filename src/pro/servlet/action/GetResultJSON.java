package pro.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;
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

import pro.dao.entity.Car;
import pro.hibernate.util.HibernateUtil;
import pro.json.tools.JsonTools;
import pro.utils.CreateExcel;

public class GetResultJSON extends HttpServlet {

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
			System.out.println(start_time + " " + end_time + " " + stream
					+ " " + weightStandard);

			// HQL语句
			String hql = "";
			if (stream.equals("全部")) {
				if (!start_time.equals("") && !end_time.equals("")) {
					// 查询在start_time和end_time之间，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= str_to_date('"
							+ start_time
							+ "','%Y-%m-%d %H:%i:%s') and c.datetime <= str_to_date('"
							+ end_time
							+ "','%Y-%m-%d %H:%i:%s') and c.weight >= " + 0
							+ " order by c.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= str_to_date('"
							+ start_time
							+ "','%Y-%m-%d %H:%i:%s') and c.weight >= " + 0
							+ " order by c.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime <= str_to_date('"
							+ end_time
							+ "','%Y-%m-%d %H:%i:%s') and c.weight >= " + 0
							+ " order by c.datetime asc";
				} else {
					// 默认的HQL语句（查询重量大于weight的数据）
					hql = "from Car c where c.weight >= " + 0
							+ " order by c.datetime asc";
				}
			} else {
				if (!start_time.equals("") && !end_time.equals("")) {
					// 查询在start_time和end_time之间，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= str_to_date('"
							+ start_time
							+ "','%Y-%m-%d %H:%i:%s') and c.datetime <= str_to_date('"
							+ end_time
							+ "','%Y-%m-%d %H:%i:%s') and c.weight >= " + 0
							+ " and c.stream = '" + stream
							+ "' order by c.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= str_to_date('"
							+ start_time
							+ "','%Y-%m-%d %H:%i:%s') and c.weight >= " + 0
							+ " and c.stream = '" + stream
							+ "' order by c.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime <= str_to_date('"
							+ end_time
							+ "','%Y-%m-%d %H:%i:%s') and c.weight >= " + 0
							+ " and c.stream = '" + stream
							+ "' order by c.datetime asc";
				} else {
					// 默认的HQL语句（查询重量大于weight的数据）
					hql = "from Car c where c.weight >= " + 0
							+ " and c.stream = '" + stream
							+ "' order by c.datetime asc";
				}
			}

			// 查询，使用HQL语句
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Car> cars = query.list();

			// 上游的车辆
			List<Car> cars1 = new ArrayList<Car>();
			// 下游的车辆
			List<Car> cars2 = new ArrayList<Car>();
			for (Car car : cars) {
				if (car.getStream().equals("上游")) {
					cars1.add(car);
				} else if (car.getStream().equals("下游")) {
					cars2.add(car);
				}
			}
			// 记录超重的车数，count1是上游，count2是下游
			int count1 = 0;
			int count2 = 0;
			// 记录最重的重量
			double weightest1 = 0;
			double weightest2 = 0;
			// 记录找到车牌的数量
			int findCount1 = 0;
			int findCount2 = 0;
			// 记录重量超过55吨的数量
			int over55Count1 = 0;
			int over55Count2 = 0;
			// 记录重量超过75吨的数量
			int over75Count1 = 0;
			int over75Count2 = 0;
			for (Car car : cars1) {
				if (car.getWeight() >= weightStandard) {
					count1++;
				}
				if (car.getWeight() > weightest1) {
					weightest1 = car.getWeight();
				}
				if (!car.getPhoto().contains("无")) {
					findCount1++;
				}
				if (car.getWeight() > 55) {
					over55Count1++;
				}
				if (car.getWeight() > 75) {
					over75Count1++;
				}
			}
			for (Car car : cars2) {
				if (car.getWeight() >= weightStandard) {
					count2++;
				}
				if (car.getWeight() > weightest2) {
					weightest2 = car.getWeight();
				}
				if (!car.getPhoto().contains("无")) {
					findCount2++;
				}
				if (car.getWeight() > 55) {
					over55Count2++;
				}
				if (car.getWeight() > 75) {
					over75Count2++;
				}
			}

			// 生成要封装成json的map集合
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map_1 = new HashMap<String, Object>();
			Map<String, Object> map_2 = new HashMap<String, Object>();
			if (cars1.size() != 0 && cars2.size() != 0) {
				map_1.put("place", "天兴洲大桥上游");
				map_1.put("start_time", start_time);
				map_1.put("end_time", end_time);
				map_1.put("weightest", weightest1);
				map_1.put("totalnumber", cars1.size());
				map_1.put("overnumber", count1);
				map_1.put("findcarnumber", findCount1);
				map_1.put("over55number", over55Count1);
				map_1.put("over75number", over75Count1);

				map_2.put("place", "天兴洲大桥下游");
				map_2.put("start_time", start_time);
				map_2.put("end_time", end_time);
				map_2.put("weightest", weightest2);
				map_2.put("totalnumber", cars2.size());
				map_2.put("overnumber", count2);
				map_2.put("findcarnumber", findCount2);
				map_2.put("over55number", over55Count2);
				map_2.put("over75number", over75Count2);

				list.add(map_1);
				list.add(map_2);
				map.put("rows", list);
			} else if (cars1.size() != 0 && cars2.size() == 0) {
				map_1.put("place", "天兴洲大桥上游");
				map_1.put("start_time", start_time);
				map_1.put("end_time", end_time);
				map_1.put("weightest", weightest1);
				map_1.put("totalnumber", cars1.size());
				map_1.put("overnumber", count1);
				map_1.put("findcarnumber", findCount1);
				map_1.put("over55number", over55Count1);
				map_1.put("over75number", over75Count1);

				list.add(map_1);
				map.put("rows", list);
			} else if (cars1.size() == 0 && cars2.size() != 0) {
				map_2.put("place", "天兴洲大桥下游");
				map_2.put("start_time", start_time);
				map_2.put("end_time", end_time);
				map_2.put("weightest", weightest2);
				map_2.put("totalnumber", cars2.size());
				map_2.put("overnumber", count2);
				map_2.put("findcarnumber", findCount2);
				map_2.put("over55number", over55Count2);
				map_2.put("over75number", over75Count2);

				list.add(map_2);
				map.put("rows", list);
			}

			// 把Car对象封装成JSON数据
			resultJSONString = JsonTools.createJsonString(map);
			out.println(resultJSONString);
			System.out.println(resultJSONString);

			// 判断是否需要导出该查询内容的Excel表
			if (request.getParameter("isGetExcel").equals("1")) {
				String sheetname = request.getParameter("sheetname");
				String excelname = request.getParameter("excelname");
				CreateExcel.getExcel(sheetname, excelname + ".xls", map);
			}

			if (session != null) {
				session.close();
			}
		}

		out.flush();
		out.close();
	}

}
