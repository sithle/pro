package pro.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
/**
 * hibernate 工具类，用于产生session.
 * @author lc
 *
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory;// 会话工厂对象
	private static Session session;// 会话对象

	static {
		// 创建配置对象,创建Configuration对象，读取hibernate.cfg.xml文件，完成初始化
		Configuration config = new Configuration().configure();
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties());
		// 创建服务注册对象
		StandardServiceRegistry ssr = ssrb.build();
		// 初始化会话工厂对象
		sessionFactory = config.buildSessionFactory(ssr);
	}

	// 获取会话工厂对象sessionFactory
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// 获取会话对象session
	public static Session getSession() {
		session = sessionFactory.openSession();
		return session;
	}

	// 关闭session
	public static void closeSession() {
		if (session != null) {
			session.close();
		}
	}
}
