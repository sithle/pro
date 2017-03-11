package pro.hibernate.test;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import pro.dao.entity.Car;
import pro.hibernate.util.HibernateUtil;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

public class SithleTest {
	@Test
	public void main(){
	
		//Date date1=new Date(new java.util.Date().getTime());
	    java.util.Date javaDate = new java.util.Date();
	    long javaTime = javaDate.getTime();

	    long starttime=javaTime-3600*1000;
	    long endtime=javaTime;
	    
	    java.sql.Timestamp satrtTimestamp = new java.sql.Timestamp(starttime);
	    java.sql.Timestamp endTimestamp = new java.sql.Timestamp(endtime);
	    System.out.println(satrtTimestamp);
	    System.out.println(endTimestamp);
		String hql = "from Car c where c.datetime >= '"
				+ satrtTimestamp
				+ "'and c.datetime<='"+endTimestamp+"'";
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("查询的hql语句：" + hql);
		Query query = session.createQuery(hql);
		List<Car> cars = query.list();
		System.out.println("查询的全部车辆：" + cars);
		System.out.println("数量" + cars.size());
		
		
		
		
	}
}
