package pro.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import pro.dao.entity.Car;
import pro.hibernate.util.HibernateUtil;
import pro.json.tools.JsonTools;
import pro.utils.CreateExcel;

public class Blacklist extends HttpServlet {

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
				&& request.getParameter("stream") != null
				&& request.getParameter("weightStandard") != null) {


			// 生成要封装成json的map集合
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map_1 = new HashMap<String, Object>();
			Map<String, Object> map_2 = new HashMap<String, Object>();
		
				map_1.put("carnumber", "鄂F12345");
				map_1.put("total", "25");
				map_1.put("axis", "5");
				map_1.put("weightest", "100");
				map_1.put("latest", "2016-09-01 22:12:13");
				map_1.put("latestweight", "90");

				
				map_2.put("carnumber", "鄂F12235");
				map_2.put("total", "15");
				map_2.put("axis", "3");
				map_2.put("weightest", "120");
				map_2.put("latest", "2016-09-01 22:11:13");
				map_2.put("latestweight", "80");				
			

				list.add(map_1);
				list.add(map_2);
				map.put("rows", list);

			String resultJSONString = "";
			// 把Car对象封装成JSON数据
			resultJSONString = JsonTools.createJsonString(map);
			out.println(resultJSONString);
			System.out.println(resultJSONString);




	}
		out.flush();
		out.close();
	}

}