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
		
		String car_s = "";
		if(stream==1){
			car_s = "上游";
		}else if(stream==2){
			car_s = "下游";
		}

		// 查询，使用HQL语句
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		// HQL语句
		String hql = "from Car c where c.datetime >= str_to_date('"
				+ currentTime + "','%Y-%m-%d %H:%i:%s') and c.stream='"
				+ car_s + "'";
		System.out.println("GetRealTimeReportJSON.java"+hql);
		Query query = session.createQuery(hql);
		List<Car> cars = new ArrayList<Car>();
		cars = query.list();
		System.out.println(cars);

		// 把Car对象封装成JSON数据
		String carsJSONString = JsonTools.createJsonString(JsonService
				.getCarsList(cars));
		System.out.println(carsJSONString);
		out.println(carsJSONString);
		transaction.commit();

		out.flush();
		out.close();

	}

}
