package pro.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pro.dao.entity.Car2;
import pro.dao.entity.Car;
import pro.hibernate.util.HibernateUtil;
import pro.json.service.JsonService;
import pro.json.tools.JsonTools;

public class GetRealTimeReportJSON extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.setContentType("text/html; charset=utf-8");
		response.setContentType("application/x-json");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String currentTime = request.getParameter("currentTime");
		int stream = Integer.parseInt(request.getParameter("stream"));
		System.out.println(currentTime);

		
		
		if(stream==1){
		// 查询，使用HQL语句
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		// HQL语句
		String hql = "from Car c where c.datetime >='"
				+ currentTime +"' and c.flag='0'";
		System.out.println("GetRealTimeReportJSON.java"+hql);
		Query query = session.createQuery(hql);
		List<Car> cars = new ArrayList<Car>();
		cars = query.list();
		System.out.println(hql);

		// 把Car对象封装成JSON数据
		String carsJSONString = JsonTools.createJsonString(JsonService
				.getCarsList(cars));
		System.out.println(carsJSONString);
		out.println(carsJSONString);
		transaction.commit();}
		else if(stream==2){
			// 查询，使用HQL语句
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			// HQL语句
			String hql = "from Car2 c where c.datetime >='"
					+ currentTime +"' and c.flag='0'";
			System.out.println("GetRealTimeReportJSON.java"+hql);
			Query query = session.createQuery(hql);
			List<Car2> car2s = new ArrayList<Car2>();
			car2s = query.list();
			System.out.println(hql);

			// 把Car对象封装成JSON数据
			String carsJSONString = JsonTools.createJsonString(JsonService
					.getCar2sList(car2s));
			System.out.println(carsJSONString);
			out.println(carsJSONString);
			transaction.commit();
		}
		
		
		
		
		
		

		out.flush();
		out.close();

	}

}
