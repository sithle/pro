package pro.hibernate.test;
//import java.util.Date;

import org.hibernate.Session;
/*import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;*/
import org.hibernate.Query;
import pro.hibernate.util.HibernateUtil;
//import static org.junit.Assert.*;
import pro.dao.entity.User;
import org.junit.Test;

public class Sithletest2 {
	@Test
	public void test() {
		Session ss = HibernateUtil.getSession();
		Query query = ss.createQuery("from User u" );
		User user = (User) query.uniqueResult();
	    String bridgename=user.getBridge_name();
	    System.out.println(bridgename);
	
	}


}
