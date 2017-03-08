package pro.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pro.dao.entity.Stream;
import pro.dao.entity.User;
import pro.hibernate.util.HibernateUtil;

public class ChangeUserInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String bridge_name = request.getParameter("bridge_name");
		float weight_standard = Float.parseFloat(request.getParameter("weight_standard"));
		int stream1_id = Integer.parseInt(request.getParameter("stream1_id"));
		String stream1_name = request.getParameter("stream1_name");
		int stream1_port = Integer.parseInt(request
				.getParameter("stream1_port"));
		int stream2_id = Integer.parseInt(request.getParameter("stream2_id"));
		String stream2_name = request.getParameter("stream2_name");
		int stream2_port = Integer.parseInt(request
				.getParameter("stream2_port"));

		// 从数据库里面查找和username相等的数据
		Session ss = HibernateUtil.getSession();
		Query query = ss.createQuery("from User u where u.username='"
				+ username + "'");
		User user = (User) query.uniqueResult();
		user.setPassword(password);
		user.setBridge_name(bridge_name);
		user.setWeight_standard(weight_standard);

		Set<Stream> streams = user.getStreams();
		Iterator<Stream> it = streams.iterator();
		while (it.hasNext()) {
			Stream stream = it.next();
			if (stream.getStream_id() == stream1_id) {
				stream.setStream_name(stream1_name);
				stream.setStream_port(stream1_port);
			} else if (stream.getStream_id() == stream2_id) {
				stream.setStream_name(stream2_name);
				stream.setStream_port(stream2_port);
			}
		}
		user.setStreams(streams);

		Transaction transaction = ss.beginTransaction();
		ss.save(user);
		transaction.commit();

		HttpSession session = request.getSession();

		if (user != null && password.equals(user.getPassword())) {

			session.setAttribute("username", user.getUsername());

			session.setAttribute("password", user.getPassword());

			session.setAttribute("bridge_name", user.getBridge_name());
			
			session.setAttribute("weight_standard", user.getWeight_standard());

			Iterator<Stream> it_ = user.getStreams().iterator();
			while (it_.hasNext()) {
				Stream stream = (Stream) it_.next();
				if (stream.getStream_name().contains("上")) {

					session.setAttribute("stream1_name",
							stream.getStream_name());

					session.setAttribute("stream1_port",
							stream.getStream_port());

					session.setAttribute("stream1_id", stream.getStream_id());

					session.setAttribute("stream1_minute",
							stream.getStream_minute());

				} else if (stream.getStream_name().contains("下")) {

					session.setAttribute("stream2_name",
							stream.getStream_name());

					session.setAttribute("stream2_port",
							stream.getStream_port());

					session.setAttribute("stream2_id", stream.getStream_id());

					session.setAttribute("stream2_minute",
							stream.getStream_minute());

				}
			}
		}

		HibernateUtil.closeSession();

		out.println("<!DOCTYPE HTML>");
		out.println("<HTML>");
		//自动跳转
		out.print("<meta http-equiv= refresh content=3;url=http://192.168.6.123:8080/pro/base/jsp/userinfo.jsp>");
		out.println("  <HEAD><TITLE></TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("<div class='alert alert-success'>保存成功！请等待3秒，自动跳转中...</div>");
		out.println("  </BODY>");
		out.println("</HTML>");
		
		out.flush();
		out.close();
	}

}
