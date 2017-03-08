package pro.hibernate.test;
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
import pro.json.service.JsonService;
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


public class Test2 {
		public static void main(String args[]){
			String start_time = "2016-09-01 00:00:00";
			String end_time= "2016-09-01 20:00:00";



			double weight = 50;

			String hql = "from Car c where c.datetime >= '"
					+ start_time
					+ "'and c.datetime<='"+end_time+"'";
			String hql2 = "from Car2 c2 where c2.datetime >= '"
					+ start_time
					+ "'and c2.datetime<='"+end_time+"'";
			String hql3="from Car c where 1=2";

			Session session = HibernateUtil.getSessionFactory().openSession();
			System.out.println("查询的hql语句：" + hql);
			Query query = session.createQuery(hql);
			Query query2 = session.createQuery(hql2);
			Query query3 = session.createQuery(hql3);
			
			@SuppressWarnings("unchecked")
			List<Car> cars = query.list();
			List<Car2> cars2 = query2.list();
			List<Car> test = query3.list();
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
				cars.add(car2);
			}
			
			System.out.println("查询的全部车辆：" + test);

}
}