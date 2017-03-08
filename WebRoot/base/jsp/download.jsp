<%@page import="java.io.File"%>
<%@page import="pro.utils.FileFinder"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	String username = (String) session.getAttribute("username");
	String password = (String) session.getAttribute("password");
	if (username == null && password == null) {
		response.sendRedirect("login.jsp");
	}
	String bridge_name = (String) session.getAttribute("bridge_name");
	double weight_standard = (Double) session
			.getAttribute("weight_standard");
	int stream1_id = (Integer) session.getAttribute("stream1_id");
	String stream1_name = (String) session.getAttribute("stream1_name");
	int stream1_port = (Integer) session.getAttribute("stream1_port");
	double stream1_minute = (Double) session
			.getAttribute("stream1_minute");
	int stream2_id = (Integer) session.getAttribute("stream2_id");
	String stream2_name = (String) session.getAttribute("stream2_name");
	int stream2_port = (Integer) session.getAttribute("stream2_port");
	double stream2_minute = (Double) session
			.getAttribute("stream2_minute");
%>

<!DOCTYPE HTML>
<html>
<head>
<title>下载excel</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="description" content="下载excel">

<link rel="stylesheet" type="text/css"
	href="../jquery-easyui-1.3.2/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript"
	src="../jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="../jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../jquery-easyui-1.3.2/datagrid-detailview.js"></script>
<script type="text/javascript"
	src="../jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>

<style type="text/css">
.dv-table td {
	border: 0;
}

.dv-label {
	font-weight: bold;
	color: #15428B;
	width: 100px;
}
</style>

</head>

<body">
	<h2><%=bridge_name%>下载excel
	</h2>
	<div class="demo-info" style="margin-bottom:10px">
		<div class="demo-tip icon-tip">&nbsp;</div>
		<div>温馨提示：</div>
	</div>
	<%
		// 获取项目的WebRoot目录
		String classPath = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		String baseDirName = classPath.replace("file:/", "")
				.replace("WEB-INF/classes/", "").replace("/", "\\")
				.replace("%20", " ")
				+ "excel";
		String targetFileName = "*.xls";

		List<File> files = FileFinder.findFiles(baseDirName,
				targetFileName, 0);
	%>
	<div>
		<%
			if(files.size()<1){
		%>
				<h6>无Excel文件可供加载！</h6>
		<%
			} 
		%>
		<table>
		<%
			for (File file : files) {
				String fileName = file.getName();
		%>
		<tr>
			<td><%=fileName%></td>
			<td><a href="../../excel/<%=fileName%>">下载</a></td>
			<td><a href="delete.jsp?fileName=<%=fileName%>">删除</a></td>
		</tr>
		<%
			}
		%>
		</table>
	</div>
</body>
</html>
