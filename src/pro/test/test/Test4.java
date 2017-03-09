package pro.test.test;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.lang.Iterable;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import java.sql.Timestamp;
import pro.dao.entity.Car;
import pro.hibernate.util.HibernateUtil;
import pro.json.tools.JsonTools;


public class Test4 {
		public static void main(String args[]){
			String start_time = "2016-09-01 00:00:00";
			String end_time= "2016-12-01 20:00:00";

			Map<String,Integer> map = new HashMap();

			Double weight =100.0;
			String hql="";
			hql = "from Car c where c.datetime >= '"
					+ start_time
					+ "' and c.datetime <= '"
					+ end_time
					+ "' and c.weight >= "
					+ weight + " and c.carnumber!='无' order by c.datetime desc";
			Session session = HibernateUtil.getSessionFactory().openSession();
			System.out.println("查询的hql语句：" + hql);
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Car> cars = query.list();
			System.out.println("查询的全部车辆：" + cars);
			for (Car car2 : cars) {
				if(map.containsKey(car2.getCarnumber())){
					map.put(car2.getCarnumber(), map.get(car2.getCarnumber())+1);
				}
				else{
					map.put(car2.getCarnumber(), 1);
				}
			}
			System.out.println("查询的全部车辆：(map)" + map);
			Iterator<Car> it = cars.iterator();
			while(it.hasNext()){
				Car car=(Car)it.next();
				if(map.get(car.getCarnumber())<=1){
					it.remove();
					
				}
			}
			System.out.println("查询的全部车辆：(remove)" + cars);
			//System.out.println("查询的全部车辆：" + cars);
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
			System.out.println("查询的全部车辆：" + list);
			map_json.put("rows", list);
			String resultJSONString = "";
			// 把Car对象封装成JSON数据
			resultJSONString = JsonTools.createJsonString(map_json);
			System.out.println("查询的全部车辆2222：" + resultJSONString);
		}
}