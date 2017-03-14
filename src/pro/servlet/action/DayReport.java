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

public class DayReport extends HttpServlet {

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
			String hql = "from Car where 1=2";
			String hql2= "from Car2 where 1=2";
			if (stream.equals("全部")) {
				if (!start_time.equals("") && !end_time.equals("")) {
					// 查询在start_time和end_time之间，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= '"
							+ start_time
							+ "' and c.datetime <= '"
							+ end_time
							+ "' and c.weight >= "
							+ weightStandard + " order by c.datetime asc";
					hql2 = "from Car2 c2 where c2.datetime >= '"
							+ start_time
							+ "' and c2.datetime <= '"
							+ end_time
							+ "' and c2.weight >= "
							+ weightStandard + " order by c2.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= '"
							+ start_time
							+ "' and c.weight >= "
							+ weightStandard + " order by c.datetime asc";
					hql2 = "from Car2 c2 where c2.datetime >= '"
							+ start_time
							+ "' and c2.weight >= "
							+ weightStandard + " order by c2.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime <= '"
							+ end_time
							+ "' and c.weight >= "
							+ weightStandard + " order by c.datetime asc";
					hql2 = "from Car2 c2 where c2.datetime <= '"
							+ end_time
							+ " and c.weight >= "
							+ weightStandard + "' order by c2.datetime asc";
				} else {
					// 默认的HQL语句（查询重量大于weight的数据）
					hql = "from Car c where c.weight >= " + weightStandard
							+ " order by c.datetime asc";
					hql2 = "from Car2 c2 where c2.weight >= " + weightStandard
							+ " order by c2.datetime asc";
				}

			} else if(stream.equals("上游")){
				if (!start_time.equals("") && !end_time.equals("")) {
					// 查询在start_time和end_time之间，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= '"
							+ start_time
							+ "' and c.datetime <= '"
							+ end_time
							+ "' and c.weight >= "
							+ weightStandard + " order by c.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= '"
							+ start_time
							+ "' and c.weight >= "
							+ weightStandard + " order by c.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime <= '"
							+ end_time
							+ "' and c.weight >= "
							+ weightStandard + " order by c.datetime asc";
				} else {
					// 默认的HQL语句（查询重量大于weight的数据）
					hql = "from Car c where c.weight >= " + weightStandard
							+ " order by c.datetime asc";
				}

			}
			else {
				if (!start_time.equals("") && !end_time.equals("")) {
					// 查询在start_time和end_time之间，并且重量大于weight的数据
					hql2 = "from Car2 c2 where c2.datetime >= '"
							+ start_time
							+ "' and c2.datetime <= '"
							+ end_time
							+ "' and c2.weight >= "
							+ weightStandard + " order by c2.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql2 = "from Car2 c2 where c2.datetime >= '"
							+ start_time
							+ "' and c2.weight >= "
							+ weightStandard + " order by c2.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql2 = "from Car c2 where c2.datetime <= '"
							+ end_time
							+ "' and c2.weight >= "
							+ weightStandard + " order by c2.datetime asc";
				} else {
					// 默认的HQL语句（查询重量大于weight的数据）
					hql2 = "from Car2 c2 where c2.weight >= " + weightStandard
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
			for (Car2 car : cars2){
				Car car2=new Car();
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

			// 生成要封装成json的map集合
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			// 地点
			String place = "";
			if (stream.equals("全部")) {
				place = "天兴洲大桥上/下游";
			} else {
				place = "天兴洲大桥" + stream;
			}

			// 起始时间
			Timestamp stime = Timestamp.valueOf(start_time.substring(0, 11)
					+ "00:00:00");
			// 结束时间
			Timestamp etime = new Timestamp(
					Timestamp.valueOf(end_time.substring(0, 11) + "00:00:00")
							.getTime() + 1000 * 3600 * 24);

			while (stime.before(etime)) {
				// 满足时间间隔内的第一天list集合
				List<Car> rowCars = new ArrayList<Car>();
				Timestamp tempTime = new Timestamp(
						stime.getTime() + 1000 * 3600 * 24);
				for (Car car : cars) {
					Timestamp carDate = car.getDatetime();
					if (!carDate.before(stime) && !carDate.after(tempTime)) {
						rowCars.add(car);
					}
				}
				// 时间
				String time = stime.toString().substring(0, 10);
				// 最重
				double weightest = 0;
				// 车辆总数
				int totalnumber = rowCars.size();
				// 超重总数
				int overnumber = 0;
				// 找到车牌
				int findcarnumber = 0;
				// 大于55吨
				int over55number = 0;
				// 大于75吨
				int over75number = 0;

				for (Car car : rowCars) {
					if (car.getWeight() > weightest) {
						weightest = car.getWeight();
					}
					if (car.getWeight() >= weightStandard) {
						overnumber++;
					}
					if (!car.getCarnumber().contains("无")) {
						findcarnumber++;
					}
					if (car.getWeight() >= 55) {
						over55number++;
					}
					if (car.getWeight() >= 75) {
						over75number++;
					}
				}

				Map<String, Object> map_ = new HashMap<String, Object>();
				map_.put("place", place);
				map_.put("time", time);
				map_.put("weightest", weightest);
				map_.put("totalnumber", totalnumber);
				map_.put("overnumber", overnumber);
				map_.put("findcarnumber", findcarnumber);
				map_.put("over55number", over55number);
				map_.put("over75number", over75number);

				list.add(map_);
				stime = tempTime;
			}

			map.put("rows", list);

			// 把Car对象封装成JSON数据
			resultJSONString = JsonTools.createJsonString(map);
			out.println(resultJSONString);
			System.out.println(resultJSONString);

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

}
