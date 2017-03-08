<%@page import="pro.utils.FileFinder"%>
<%@page import="java.io.File"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>

<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");

	String fileName = request.getParameter("fileName");
	System.out.println(fileName);
	// 获取项目的WebRoot目录
	String classPath = Thread.currentThread().getContextClassLoader()
			.getResource("").toString();
	String baseDirName = classPath.replace("file:/", "")
			.replace("WEB-INF/classes/", "").replace("/", "\\")
			.replace("%20", " ")
			+ "excel";
	String targetFileName = fileName;
	List<File> files = FileFinder.findFiles(baseDirName,
			targetFileName, 0);
	if (files.size() > 0) {
		for (File file : files) {
			file.delete();
		}
	}
	out.print("删除成功！请重新刷新此页面...");
%>