package pro.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import pro.hibernate.util.HibernateUtil;
import pro.json.tools.JsonTools;
import pro.utils.CreateExcel;

public class Blacklist extends HttpServlet {

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

			String start_time = request.getParameter("start_time");
			String end_time = request.getParameter("end_time");
			String stream = request.getParameter("stream");
			double weight = Double.valueOf(request
					.getParameter("weightStandard"));
			int total_ = Integer.valueOf(request
					.getParameter("total"));

			Map<String,Integer> map = new HashMap();

			
			/*String hql="";
			hql = "from Car c where c.datetime >= '"
					+ start_time
					+ "' and c.datetime <= '"
					+ end_time
					+ "' and c.weight >= "
					+ weight + " and c.carnumber!='无' order by c.datetime asc";
			Session session = HibernateUtil.getSessionFactory().openSession();
			System.out.println("查询的hql语句：" + hql);
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Car> cars = query.list();*/
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
							+ weight + " order by c.datetime asc";
					hql2 = "from Car2 c2 where c2.datetime >= '"
							+ start_time
							+ "' and c2.datetime <= '"
							+ end_time
							+ "' and c2.weight >= "
							+ weight + " order by c2.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= '"
							+ start_time
							+ "' and c.weight >= "
							+ weight + " order by c.datetime asc";
					hql2 = "from Car2 c2 where c2.datetime >= '"
							+ start_time
							+ "' and c2.weight >= "
							+ weight + " order by c2.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime <= '"
							+ end_time
							+ "' and c.weight >= "
							+ weight + " order by c.datetime asc";
					hql2 = "from Car2 c2 where c2.datetime <= '"
							+ end_time
							+ " and c.weight >= "
							+ weight + "' order by c2.datetime asc";
				} else {
					// 默认的HQL语句（查询重量大于weight的数据）
					hql = "from Car c where c.weight >= " + weight
							+ " order by c.datetime asc";
					hql2 = "from Car2 c2 where c2.weight >= " + weight
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
							+ weight + " order by c.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime >= '"
							+ start_time
							+ "' and c.weight >= "
							+ weight + " order by c.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql = "from Car c where c.datetime <= '"
							+ end_time
							+ "' and c.weight >= "
							+ weight + " order by c.datetime asc";
				} else {
					// 默认的HQL语句（查询重量大于weight的数据）
					hql = "from Car c where c.weight >= " + weight
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
							+ weight + " order by c2.datetime asc";
				} else if (!start_time.equals("") && end_time.equals("")) {
					// 查询时间大于等于start_time，并且重量大于weight的数据
					hql2 = "from Car2 c2 where c2.datetime >= '"
							+ start_time
							+ "' and c2.weight >= "
							+ weight + " order by c2.datetime asc";
				} else if (start_time.equals("") && !end_time.equals("")) {
					// 查询时间小于等于end_time，并且重量大于weight的数据
					hql2 = "from Car c2 where c2.datetime <= '"
							+ end_time
							+ "' and c2.weight >= "
							+ weight + " order by c2.datetime asc";
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
			for (Car car2_ : cars) {
				if(map.containsKey(car2_.getCarnumber())){
					map.put(car2_.getCarnumber(), map.get(car2_.getCarnumber())+1);
				}
				else{
					map.put(car2_.getCarnumber(), 1);
				}
			}
			System.out.println("查询的全部车辆：" + map);
			Iterator<Car> it = cars.iterator();
			while(it.hasNext()){
				Car car=(Car)it.next();
				if(map.get(car.getCarnumber())<=total_-1){
					it.remove();
					
				}
			}
			
			System.out.println("查询的全部车辆222：" + cars);
			Map<String, Object> map_json = new HashMap<String, Object>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
			Iterator<Car> it2 = cars.iterator();
			int flag=0;
			while(it2.hasNext()){
				Map<String, Object> map_1 = new HashMap<String, Object>();
				Car car=(Car)it2.next();
				String carnumber=car.getCarnumber();
				
				if(flag==0){
					int num=1;
					map_1.put("total", num);
					map_1.put("carnumber", carnumber);
					map_1.put("axis", car.getAxis());
					map_1.put("weightest",car.getWeight());
					map_1.put("latest", car.getDatetime());
					map_1.put("latestweight", car.getWeight());
					list.add(map_1);
					flag=1;
					continue;
				}
				
		        for(int i = 0;i < list.size(); i ++){
		        	
		            
		        
				if(list.get(i).get("carnumber").equals(carnumber)){
					
					
					int b=(Integer)list.get(i).get("total");
					Double w=(Double)list.get(i).get("weightest");
					Timestamp t=(Timestamp)list.get(i).get("latest");
					
					b+=1;
					list.get(i).put("total", b);
					if(w<car.getWeight()){
						list.get(i).put("weightest", car.getWeight());
						
					}
					
					if(t.before(car.getDatetime())){
						list.get(i).put("latest", car.getDatetime());
						list.get(i).put("latestweight", car.getWeight());
					}
					flag=1;
					break;
					
					}
				else{
					flag+=1;
					
					//System.out.println(i+"22222" );
					
				}

				}
		        if(flag-1==list.size()){
					int num=1;
					
					map_1.put("total", num);
					map_1.put("carnumber", carnumber);
					map_1.put("axis", car.getAxis());
					map_1.put("weightest",car.getWeight());
					map_1.put("latest", car.getDatetime());
					map_1.put("latestweight", car.getWeight());
					list.add(map_1);
					flag=1;
		        }
					

			}
			  for(int i = 0;i < list.size(); i ++){
				  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				  Timestamp date=(Timestamp)list.get(i).get("latest");
				  String str = df.format(date);
				  list.get(i).put("latest", str);
				  
			  }
			
			
		
				map_json.put("rows", list);

			String resultJSONString = "";
			// 把Car对象封装成JSON数据
			resultJSONString = JsonTools.createJsonString(map_json);
			out.println(resultJSONString);
			System.out.println(resultJSONString);

			if (request.getParameter("isGetExcel").equals("1")) {
				String sheetname = request.getParameter("sheetname");
				String excelname = request.getParameter("excelname");
				CreateExcel
						.getBlacklistExcel(sheetname, excelname + ".xls",list,start_time,end_time);
				
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