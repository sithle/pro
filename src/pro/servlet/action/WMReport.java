package pro.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

import com.mysql.jdbc.Connection;

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
							+ weightStandard + " and c.flag = '"+ 0 +"'order by c.datetime asc";
					hql2 = "from Car2 c2 where c2.datetime >= '"
							+ start_time
							+ "' and c2.datetime <= '"
							+ end_time
							+ "' and c2.weight >= "
							+ weightStandard + " and c2.flag = '"+ 0 +"'order by c2.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= '"
							+ start_time
							+ "' and c.weight >= "
							+ weightStandard + " and c.flag = '"+ 0 +"'order  by c.datetime asc";
					hql2 = "from Car2 c2 where c2.datetime >= '"
							+ start_time
							+ "' and c2.weight >= "
							+ weightStandard + " and c2.flag = '"+ 0 +"'order by c2.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime <= '"
							+ end_time
							+ "' and c.weight >= "
							+ weightStandard + " and c.flag = '"+ 0 +"'order  by c.datetime asc";
					hql2 = "from Car2 c2 where c2.datetime <= '"
							+ end_time
							+ " and c2.weight >= "
							+ weightStandard + "' and c2.flag = '"+ 0 +"'order  by c2.datetime asc";
				} else {
					// 默认的HQL语句（查询重量大于weight的数据）
					hql = "from Car c where c.weight >= " + weightStandard
							+ " and c.flag = '"+ 0 +"'order  by c.datetime asc";
					hql2 = "from Car2 c2 where c2.weight >= " + weightStandard
							+ " and c2.flag = '"+ 0 +"'order  by c2.datetime asc";
				}

			} else if(stream.equals("上游")){
				if (!start_time.equals("") && !end_time.equals("")) {
					// 查询在start_time和end_time之间，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= '"
							+ start_time
							+ "' and c.datetime <= '"
							+ end_time
							+ "' and c.weight >= "
							+ weightStandard + " and c.flag = '"+ 0 +"'order  by c.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= '"
							+ start_time
							+ "' and c.weight >= "
							+ weightStandard + " and c.flag = '"+ 0 +"'order  by c.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime <= '"
							+ end_time
							+ "' and c.weight >= "
							+ weightStandard + " and c.flag = '"+ 0 +"'order  by c.datetime asc";
				} else {
					// 默认的HQL语句（查询重量大于weight的数据）
					hql = "from Car c where c.weight >= " + weightStandard
							+ " and c.flag = '"+ 0 +"'order  by c.datetime asc";
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
							+ weightStandard + " and c2.flag = '"+ 0 +"'order  by c2.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql2 = "from Car2 c2 where c2.datetime >= '"
							+ start_time
							+ "' and c2.weight >= "
							+ weightStandard + " and c2.flag = '"+ 0 +"'order  by c2.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql2 = "from Car c2 where c2.datetime <= '"
							+ end_time
							+ "' and c2.weight >= "
							+ weightStandard + " and c2.flag = '"+ 0 +"'order  by c2.datetime asc";
				} else {
					// 默认的HQL语句（查询重量大于weight的数据）
					hql2 = "from Car2 c2 where c2.weight >= " + weightStandard
							+ " and c2.flag = '"+ 0 +"'order by c2.datetime asc";
				}

			}

			// 查询，使用HQL语句
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			System.out.println("查询的hql语句：" + hql);
			System.out.println("查询的hql语句：" + hql2);
			Query query = session.createQuery(hql);
			Query query2 = session.createQuery(hql2);
			@SuppressWarnings("unchecked")
			List<Car> cars = query.list();
			List<Car2> cars2 = query2.list();
			transaction.commit();
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

			Timestamp stime = Timestamp.valueOf(start_time+":00");
			Timestamp etime = Timestamp.valueOf(end_time+":59");

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
					for (Car car : cars) {
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
				
				System.out.println("rowCount:"+rowCount);
				int averageNum = rowCount/24;
				 
//				—=============超重车辆高峰时段===========================
				Map<String, Integer> map2 = new HashMap<String, Integer>();
				for (int i = 0; i < 24; i++) {
					int hourCount = (Integer) map_.get("hour"+(i+1));
					if(hourCount>averageNum){
						map2.put(""+(i+1), hourCount);
					}
				}
				
				List<Map.Entry<String, Integer>> averageTime = new ArrayList<Map.Entry<String, Integer>>(  
				        map2.entrySet()); 
				String fastigium = null;
				List<String> timeIntegers = new ArrayList<String>();
				if (averageTime.size()<5) {
					for (int i = 0; i < averageTime.size(); i++) {
						if (averageTime.get(i).getValue()!=0) {
							timeIntegers.add(averageTime.get(i).getKey());
						}
					}
					fastigium = timeIntegers.toString();
				}else {
					Collections.sort(averageTime, new Comparator<Map.Entry<String, Integer>>() {  
					    public int compare(Map.Entry<String, Integer> o1,  
					            Map.Entry<String, Integer> o2) {  
					        return ( o2.getValue()-o1.getValue());  
					    }  
					});     
					System.out.println("averageTime排序："+averageTime);
					for (int i = 0; i < averageTime.subList(0, 4).size(); i++) {
						if (averageTime.subList(0, 4).get(i).getValue()!=0) {
							timeIntegers.add(averageTime.subList(0, 4).get(i).getKey());
						}
					}
					fastigium = timeIntegers.toString();
				}
				map_.put("fastigium", fastigium);
//				=================超重车辆高峰时段代码end================================
				list.add(map_);
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
	
	private String getFastigium(int hourCount, int averageNum) {
		Map<String, Integer> map2 = new HashMap<String, Integer>();
		for (int i = 0; i < 24; i++) {
			if(hourCount>averageNum){
				map2.put(""+(i+1), hourCount);
			}
		}
		
		List<Map.Entry<String, Integer>> averageTime = new ArrayList<Map.Entry<String, Integer>>(  
		        map2.entrySet()); 
		String fastigium = null;
		List<String> timeIntegers = new ArrayList<String>();
		if (averageTime.size()<5) {
			for (int i = 0; i < averageTime.size(); i++) {
				if (averageTime.get(i).getValue()!=0) {
					timeIntegers.add(averageTime.get(i).getKey());
				}
			}
			fastigium = timeIntegers.toString();
		}else {
			Collections.sort(averageTime, new Comparator<Map.Entry<String, Integer>>() {  
			    public int compare(Map.Entry<String, Integer> o1,  
			            Map.Entry<String, Integer> o2) {  
			        return ( o2.getValue()-o1.getValue());  
			    }  
			});     
			System.out.println("averageTime排序："+averageTime);
			for (int i = 0; i < averageTime.subList(0, 4).size(); i++) {
				if (averageTime.subList(0, 4).get(i).getValue()!=0) {
					timeIntegers.add(averageTime.subList(0, 4).get(i).getKey());
				}
			}
			fastigium = timeIntegers.toString();
		}
		return fastigium;
		
	}

}
