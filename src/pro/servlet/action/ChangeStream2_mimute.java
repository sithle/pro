package pro.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pro.dao.entity.Stream;
import pro.hibernate.util.HibernateUtil;

public class ChangeStream2_mimute extends HttpServlet {

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

		double new_stream2_minute = Double.parseDouble(request.getParameter("new_stream2_minute"));

		Session session = HibernateUtil.getSession();
		Query query = session
				.createQuery("from Stream s where s.stream_name like '%下%'");
		Stream stream = (Stream) query.uniqueResult();
		stream.setStream_minute((float) new_stream2_minute);
		Transaction transaction = session.beginTransaction();
		session.save(stream);
		transaction.commit();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("stream2_minute", new_stream2_minute);
		HibernateUtil.closeSession();

		System.out.println("修改下游时间间隔成功！");
		out.flush();
		out.close();
	}

}
