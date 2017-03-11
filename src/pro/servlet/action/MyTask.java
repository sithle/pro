package pro.servlet.action;
import java.util.*; 
 
import javax.servlet.*; 
import org.hibernate.Query;
import org.hibernate.Session;


import java.util.List;
import java.sql.Timestamp;
import pro.dao.entity.Car;
import pro.dao.entity.Car2;
import pro.hibernate.util.HibernateUtil;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


import pro.dao.entity.Alarm;


public class MyTask extends TimerTask { 
 // private static final int C_SCHEDULE_HOUR = 0; 
  private static boolean isRunning = false; 
  private ServletContext context = null; 

  public MyTask() { 
  } 
  public MyTask(ServletContext context) { 
    this.context = context; 
  } 

  public void run() { 
    //Calendar cal = Calendar.getInstance(); 
    if (!isRunning) { 
      
        isRunning = true; 
        context.log("开始执行指定任务"); 
        //TODO 添加自定义的详细任务
        System.out.println("Test Test" );
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
		String hql2="from Car2 c2 where c2.datetime >= '"
				+ satrtTimestamp
				+ "'and c2.datetime<='"+endTimestamp+"'";
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("查询的hql语句：" + hql);
		Query query = session.createQuery(hql);
		Query query2 = session.createQuery(hql2);
		List<Car> cars = query.list();
		List<Car2> cars2 = query2.list();
		
		if(cars.size()==0&&cars2.size()==0){
			//1.创建sessionFactory对象
			
			SessionFactory sessionFactory = null;
			
			Configuration configuration = new Configuration().configure();
			
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			
			
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
			
			
			//2.创建session对象
			Session session2 = sessionFactory.openSession();
			
			//3.开启事物
			Transaction transaction = session2.beginTransaction();
			//4.试行保存操作
			Alarm alarms = new Alarm(satrtTimestamp, endTimestamp, "上游与下游没有采集数据上报");
			session2.save(alarms);
			//5.提交事物
			
			transaction.commit();
			
			//6.关闭session
			
			session2.close();
			
			sessionFactory.close();
			
		
		}
		else if(cars.size()==0&&cars2.size()>0){
			//1.创建sessionFactory对象
			
			SessionFactory sessionFactory = null;
			
			Configuration configuration = new Configuration().configure();
			
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			
			
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
			
			
			//2.创建session对象
			Session session2 = sessionFactory.openSession();
			
			//3.开启事物
			Transaction transaction = session2.beginTransaction();
			//4.试行保存操作
			Alarm alarms = new Alarm(satrtTimestamp, endTimestamp, "上游与没有采集数据上报");
			session2.save(alarms);
			//5.提交事物
			
			transaction.commit();
			
			//6.关闭session
			
			session2.close();
			
			sessionFactory.close();
			
		}
		else if(cars.size()>0&&cars2.size()==0){
			//1.创建sessionFactory对象
			
			SessionFactory sessionFactory = null;
			
			Configuration configuration = new Configuration().configure();
			
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			
			
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
			
			
			//2.创建session对象
			Session session2 = sessionFactory.openSession();
			
			//3.开启事物
			Transaction transaction = session2.beginTransaction();
			//4.试行保存操作
			Alarm alarms = new Alarm(satrtTimestamp, endTimestamp, "下游没有采集数据上报");
			session2.save(alarms);
			//5.提交事物
			
			transaction.commit();
			
			//6.关闭session
			
			session2.close();
			
			sessionFactory.close();
			
			
		}
        isRunning = false; 
        context.log("指定任务执行结束"); 
      
    } 
    else { 
      context.log("上一次任务执行还未结束"); 
    } 
  } 

}