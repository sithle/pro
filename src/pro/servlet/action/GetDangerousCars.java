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
import pro.dao.entity.Car2;
import pro.hibernate.util.HibernateUtil;
import pro.json.service.JsonService;
import pro.json.tools.JsonTools;
import pro.utils.CreateExcel;

public class GetDangerousCars extends HttpServlet {

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

		// response.setContentType("text/html; charset=utf-8");
		response.setContentType("application/x-json");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		if (request.getParameter("start_time") != null
				&& request.getParameter("end_time") != null
				&& request.getParameter("stream") != null
				&& request.getParameter("weight") != null) {

			String carsJSONString = "";
			String start_time = request.getParameter("start_time");
			String end_time = request.getParameter("end_time");
			String stream = request.getParameter("stream");
			Double weight = Double.valueOf(request.getParameter("weight"));

			// HQL语句
			String hql = "from Car where 1=2";
			String hql2 = "from Car2 where 1=2";
			if (stream.equals("全部")) {
				if (!start_time.equals("") && !end_time.equals("")) {
					// 查询在start_time和end_time之间，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= '" + start_time
							+ "' and c.datetime <= '" + end_time
							+ "' and c.weight >= " + weight
							+ " order by c.datetime asc";
					hql2 = "from Car2 c2 where c2.datetime >= '" + start_time
							+ "' and c2.datetime <= '" + end_time
							+ "' and c2.weight >= " + weight
							+ " order by c2.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= '" + start_time
							+ "' and c.weight >= " + weight
							+ " order by c.datetime asc";
					hql2 = "from Car2 c2 where c2.datetime >= '" + start_time
							+ "' and c2.weight >= " + weight
							+ " order by c2.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime <= '" + end_time
							+ "' and c.weight >= " + weight
							+ " order by c.datetime asc";
					hql2 = "from Car2 c2 where c2.datetime <= '" + end_time
							+ " and c.weight >= " + weight
							+ "' order by c2.datetime asc";
				} else {
					// 默认的HQL语句（查询重量大于weight的数据）
					hql = "from Car c where c.weight >= " + weight
							+ " order by c.datetime asc";
					hql2 = "from Car2 c2 where c2.weight >= " + weight
							+ " order by c2.datetime asc";
				}

			} else if (stream.equals("上游")) {
				if (!start_time.equals("") && !end_time.equals("")) {
					// 查询在start_time和end_time之间，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= '" + start_time
							+ "' and c.datetime <= '" + end_time
							+ "' and c.weight >= " + weight
							+ " order by c.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= '" + start_time
							+ "' and c.weight >= " + weight
							+ " order by c.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime <= '" + end_time
							+ "' and c.weight >= " + weight
							+ " order by c.datetime asc";
				} else {
					// 默认的HQL语句（查询重量大于weight的数据）
					hql = "from Car c where c.weight >= " + weight
							+ " order by c.datetime asc";
				}

			} else {
				if (!start_time.equals("") && !end_time.equals("")) {
					// 查询在start_time和end_time之间，并且重量大于weight的数据
					hql2 = "from Car2 c2 where c2.datetime >= '" + start_time
							+ "' and c2.datetime <= '" + end_time
							+ "' and c2.weight >= " + weight
							+ " order by c2.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql2 = "from Car2 c2 where c2.datetime >= '" + start_time
							+ "' and c2.weight >= " + weight
							+ " order by c2.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql2 = "from Car c2 where c2.datetime <= '" + end_time
							+ "' and c2.weight >= " + weight
							+ " order by c2.datetime asc";
				} else {
					// 默认的HQL语句（查询重量大于weight的数据）
					hql2 = "from Car2 c2 where c2.weight >= " + weight
							+ " order by c2.datetime asc";
				}

			}
			Session session = HibernateUtil.getSessionFactory().openSession();
			System.out.println("查询的hql语句：" + hql);
			System.out.println("查询的hql语句：" + hql2);
			Query query = session.createQuery(hql);
			Query query2 = session.createQuery(hql2);
			@SuppressWarnings("unchecked")
			List<Car> cars = query.list();
			List<Car2> cars2 = query2.list();
			for (Car2 car : cars2) {
				Car car2 = new Car();
				car2.setId(car.getId());
				car2.setCarnumber(car.getCarnumber());
				car2.setAxis(car.getAxis());
				car2.setDatetime(car.getDatetime());
				car2.setLane(car.getLane());
				car2.setPhoto(car.getPhoto());
				car2.setStream(car.getStream());
				car2.setVelocity(car.getVelocity());
				car2.setWeight(car.getWeight());
				cars.add(car2);
			}
			System.out.println("查询的全部车辆：" + cars);

			// 封装分页的数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", cars.size());
			// 页码，初始值为1
			int page = Integer.parseInt(request.getParameter("page"));
			// 一页显示多少行，初始值为10
			int rows = Integer.parseInt(request.getParameter("rows"));
			int index = (page - 1) * rows;
			List<Car> cars_ = new ArrayList<Car>();
			for (int i = 0; i < rows; i++) {
				if ((i + index) < cars.size()) {
					cars_.add(cars.get(i + index));
				} else {
					break;
				}
			}

			System.out.println("分页的车辆：" + cars_);
			map.put("rows", JsonService.getCarsList(cars_));

			// 添加页脚的数据（超重的数量和总共数量）

			// 记录超过危险重量的车辆
			int count = 0;
			Double dangerousWeight = Double.valueOf(request
					.getParameter("weight"));
			for (Car car : cars) {
				if (car.getWeight() >= dangerousWeight) {
					count++;
				}
			}
			Map<String, Object> mapFoot1 = new HashMap<String, Object>();
			mapFoot1.put("datetime", "超过危险重量车辆数");
			mapFoot1.put("lane", count + "辆");
			Map<String, Object> mapFoot2 = new HashMap<String, Object>();
			mapFoot2.put("datetime", "车辆总数");
			mapFoot2.put("lane", cars.size());
			List<Map<String, Object>> listFoot = new ArrayList<Map<String, Object>>();
			listFoot.add(mapFoot1);
			listFoot.add(mapFoot2);
			map.put("footer", listFoot);

			// 把Car对象封装成JSON数据
			carsJSONString = JsonTools.createJsonString(map);
			out.println(carsJSONString);
			// System.out.println(carsJSONString);

			// 判断是否需要导出该查询内容的Excel表
			if (request.getParameter("isGetExcel").equals("1")) {
				String sheetname = request.getParameter("sheetname");
				String excelname = request.getParameter("excelname");
				CreateExcel
						.getExcel(sheetname, excelname + ".xls", cars, count);

				out.println("<!DOCTYPE HTML>");
				out.println("<HTML>");
				// 自动跳转
				out.print("<meta http-equiv= refresh content=3;url=http://192.168.6.123:8080/pro/servlet/DownloadServlet?filename="
						+ excelname + ".xls>");
				out.println("  <HEAD><TITLE></TITLE></HEAD>");
				out.println("  <BODY>");
				out.println("<div class='alert alert-success'>正在下载...</div>");
				out.println("  </BODY>");
				out.println("</HTML>");
			}
			if (session != null) {
				session.close();
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
