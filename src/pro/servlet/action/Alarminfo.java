package pro.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;


import pro.dao.entity.Alarm;
import pro.hibernate.util.HibernateUtil;
import pro.json.tools.JsonTools;

public class Alarminfo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/x-json");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("start_time") != null
				&& request.getParameter("end_time") != null
		) {

			String start_time = request.getParameter("start_time");
			String end_time = request.getParameter("end_time");


			String hql= "from Alarm c where c.starttime >= '"
					+ start_time
					+ "' and c.endtime <= '"
					+ end_time
					+ "' order by c.starttime asc";

			Session session = HibernateUtil.getSessionFactory().openSession();
			System.out.println("查询的hql语句：" + hql);
			
			Query query = session.createQuery(hql);
			;
			@SuppressWarnings("unchecked")
			List<Alarm> alarms = query.list();
			Map<String, Object> map_json = new HashMap<String, Object>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Iterator<Alarm> it = alarms.iterator();
			while(it.hasNext()){
				Map<String, Object> map_1 = new HashMap<String, Object>();
				Alarm alarm=(Alarm)it.next();
				map_1.put("starttime", alarm.getStarttime().toString().substring(0, 19));
				map_1.put("endtime",alarm.getEndtime().toString().substring(0, 19));
				map_1.put("info", alarm.getInfo());

				list.add(map_1);
				
			}
			
			

		

			
			
		
				map_json.put("rows", list);

			String resultJSONString = "";
			// 把Car对象封装成JSON数据
			resultJSONString = JsonTools.createJsonString(map_json);
			out.println(resultJSONString);
			System.out.println(resultJSONString);




	}
		out.flush();
		out.close();
	}

}