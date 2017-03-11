package pro.hibernate.test;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import static org.junit.Assert.*;
import pro.dao.entity.Alarm;
import org.junit.Test;

public class Sithletest2 {
	@Test
	public void test() {
	    java.util.Date javaDate = new java.util.Date();
	    long javaTime = javaDate.getTime();

	    long starttime=javaTime-3600*1000;
	    long endtime=javaTime;
	    
	    java.sql.Timestamp satrtTimestamp = new java.sql.Timestamp(starttime);
	    java.sql.Timestamp endTimestamp = new java.sql.Timestamp(endtime);
		//1.创建sessionFactory对象
		
		SessionFactory sessionFactory = null;
		
		Configuration configuration = new Configuration().configure();
		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		
		
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		
		
		//2.创建session对象
		Session session = sessionFactory.openSession();
		
		//3.开启事物
		Transaction transaction = session.beginTransaction();
		//4.试行保存操作
		Alarm alarms = new Alarm(satrtTimestamp, endTimestamp, "测试");
		session.save(alarms);
		//5.提交事物
		
		transaction.commit();
		
		//6.关闭session
		
		session.close();
		
		sessionFactory.close();
		
	
	}


}
