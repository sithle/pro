package pro.hibernate.test;

//import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.relational.Database;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

import pro.dao.entity.News;

public class HibernateTest {

	@Test
	public void main() {
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
		News news = new News("Java", "liujin", new Date(new java.util.Date().getTime()),"test");
		session.save(news);
		//5.提交事物
		
		transaction.commit();
		
		//6.关闭session
		
		session.close();
		
		sessionFactory.close();
		
		
		
	}

}
