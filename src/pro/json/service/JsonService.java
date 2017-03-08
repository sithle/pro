package pro.json.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import pro.dao.entity.Car;
import pro.dao.entity.Stream;
import pro.hibernate.util.HibernateUtil;

public class JsonService {

	/**
	 * 自定义json数据结构
	 * 
	 * @param car
	 *            传入一辆车
	 * @return
	 */
	public static List<Map<String, Object>> getCarList(Car car) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("datetime", car.getDatetime().toString());
		map.put("lane", car.getLane());
		map.put("velocity", car.getVelocity());
		map.put("weight", car.getWeight());
		map.put("axis", car.getAxis());
		map.put("carnumber", car.getCarnumber());
		map.put("photo", car.getPhoto());
		map.put("stream", car.getStream());
		map.put("stream_name", getStream_name(car.getStream()));
		map.put("stream_minute", getStream_minute(car.getStream()));
		list.add(map);
		return list;
	}

	/**
	 * 自定义json数据结构
	 * 
	 * @param cars传入多辆车
	 * @return
	 */
	public static List<Map<String, Object>> getCarsList(List<Car> cars) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Car car : cars) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("datetime", car.getDatetime().toString());
			map.put("lane", car.getLane());
			map.put("velocity", car.getVelocity());
			map.put("weight", car.getWeight());
			map.put("axis", car.getAxis());
			map.put("carnumber", car.getCarnumber());
			map.put("photo", car.getPhoto());
			map.put("stream", car.getStream());
			map.put("stream_name", getStream_name(car.getStream()));
			map.put("stream_minute", getStream_minute(car.getStream()));
			list.add(map);
		}
		return list;
	}

	/**
	 * 通过stream查找数据库的stream_name
	 * 
	 * @param port
	 *            端口号
	 * @return
	 */
	public static String getStream_name(String stream) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "from Stream s where s.stream_name like '%" + stream
				+ "%'";
		Query query = session.createQuery(hql);
		Stream stream_ = (Stream) query.uniqueResult();
		String stream_name = stream_.getStream_name();
		if (session != null) {
			session.close();
		}
		return stream_name;
	}

	/**
	 * 通过端口号查找数据库的stream_minute
	 * 
	 * @param port
	 *            端口号
	 * @return
	 */
	public static double getStream_minute(String stream) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "from Stream s where s.stream_name like '%" + stream
				+ "%'";
		Query query = session.createQuery(hql);
		Stream stream_ = (Stream) query.uniqueResult();
		double stream_minute = stream_.getStream_minute();
		if (session != null) {
			session.close();
		}
		return stream_minute;
	}

}
