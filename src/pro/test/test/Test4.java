package pro.test.test;
import java.io.IOException;
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

import pro.dao.entity.Car;
import pro.hibernate.util.HibernateUtil;


public class Test4 {
		public static void main(String args[]){
			String start_time = "2016-09-01 00:00:00";
			String end_time= "2016-09-01 20:00:00";

			Map<String,Integer> map = new HashMap();

			Double weight =50.0;
			String hql="";
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
			Iterator<Car> it = cars.iterator();
			while(it.hasNext()){
				Car car=(Car)it.next();
				if(map.get(car.getCarnumber())<=1){
					it.remove();
					
				}
			}
			
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
					list.add(map_1);
					flag=1;
					continue;
				}
				
		        for(int i = 0;i < list.size(); i ++){
		        	
		            
		        
				if(list.get(i).get("carnumber").equals(carnumber)){
					
					
					int b=(Integer)list.get(i).get("total");
					b+=1;
					list.get(i).put("total", b);
					break;
					
					}
				else{
					flag+=1;
					
					//System.out.println(i+"22222" );
					
				}
					
				}
		        if(flag-list.size()==1){
					int num=1;
					map_1.put("total", num);
					map_1.put("carnumber", carnumber);
					list.add(map_1);
					flag=1;
		        }
			}
			System.out.println("查询的全部车辆：" + list);
			
		}
}