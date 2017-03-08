<%@page import="pro.dao.entity.Stream"%>
<%@page import="pro.dao.entity.User"%>
<%@page import="org.hibernate.Query"%>
<%@page import="pro.hibernate.util.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>

<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");

	// 从表单获取username和password的值
	String username = request.getParameter("username");
	String password = request.getParameter("password");

	// 从数据库里面查找和username相等的数据
	Session ss = HibernateUtil.getSession();
	Query query = ss.createQuery("from User u where u.username='"
			+ username + "'");
	User user = (User) query.uniqueResult();

	if (user != null && password.equals(user.getPassword())) {

		session.setAttribute("username", username);

		session.setAttribute("password", password);

		session.setAttribute("bridge_name", user.getBridge_name());
		
		session.setAttribute("weight_standard", user.getWeight_standard());

		Iterator<Stream> it = user.getStreams().iterator();
		while (it.hasNext()) {
			Stream stream = (Stream) it.next();
			if (stream.getStream_name().contains("上")) {

				session.setAttribute("stream1_name",
						stream.getStream_name());

				session.setAttribute("stream1_port",
						stream.getStream_port());

				session.setAttribute("stream1_id",
						stream.getStream_id());

				session.setAttribute("stream1_minute",
						stream.getStream_minute());

			} else if (stream.getStream_name().contains("下")) {

				session.setAttribute("stream2_name",
						stream.getStream_name());

				session.setAttribute("stream2_port",
						stream.getStream_port());

				session.setAttribute("stream2_id",
						stream.getStream_id());

				session.setAttribute("stream2_minute",
						stream.getStream_minute());

			}
		}

		// 请求服务器转发
		//request.getRequestDispatcher("html/index.html").forward(request, response);
		// 请求重定向
		response.sendRedirect("index.jsp");
		out.clear();
		out = pageContext.pushBody();
		HibernateUtil.closeSession();
	} else {
		// 请求重定向
		response.sendRedirect("login.jsp");
		out.clear();
		out = pageContext.pushBody();
		HibernateUtil.closeSession();
	}
%>





