<%@page import="java.sql.Timestamp"%>
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

	int stream2_id = (Integer) session.getAttribute("stream2_id");
	String stream2_name = (String) session.getAttribute("stream2_name");
	int stream2_port = (Integer) session.getAttribute("stream2_port");
	double stream2_minute = (Double) session
			.getAttribute("stream2_minute");

	Timestamp currentTime = (Timestamp) session.getAttribute("currentTime");
	String GetRealTimeReportJSONServlet = basePath
			+ "servlet/GetRealTimeReportJSON?currentTime="
			+ currentTime.toString() + "&stream=" + "2";
%>

<!DOCTYPE html>
<html>
<head>
<title>实时报表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="description" content="实时报表">

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
	src="../jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../js/real-timeReport2.js"></script>

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

<body onload="init()">
	<h2><%=bridge_name%>下游实时报表
	</h2>
	<div class="demo-info" style="margin-bottom:10px">
		<div class="demo-tip icon-tip">&nbsp;</div>
		<div>温馨提示：此报表是以打开此页面的时间为准，每隔n分钟查询一次数据库，拿到在此时间之后的数据，以此达到动态报表的功能。</div>
	</div>

	<table id="dg" class="easyui-datagrid" style="width:790px;height:450px"
		url="<%=GetRealTimeReportJSONServlet%>" title="实时报表"
		iconCls="icon-search" toolbar="#tb" rownumbers="true"
		pagination="false" singleSelect="true">
		<thead>
			<tr>
				<th field="datetime" width="140px" align="center">时间</th>
				<th field="lane" width=85px align="center">车道</th>
				<th field="velocity" width="85px" align="center">车速（km/h）</th>
				<th field="weight" width="85px" align="center" sortable="true">重量（吨）</th>
				<th field="axis" width="85px" align="center">轴数（个）</th>
				<th field="carnumber" width="85px" align="center">车牌号</th>
				<th field="photo" width="85px" align="center">照片地址</th>
				<th field="stream" width="85px" align="center">上/下游</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:3px">
		<span>当前系统时间：</span> <input type="text" id="currentTime"
			class="easyui-text" style="width: 120px" /> <span>&nbsp;&nbsp;&nbsp;&nbsp;刷新间隔（分钟）：</span>
		<input type="text" id="timeInterval" class="easyui-text"
			style="width: 120px" value="<%=stream2_minute%>" />&nbsp;&nbsp;&nbsp;&nbsp;
		<a id="update" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit'" onclick="update()">更改</a> <a
			id="save" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save'" onclick="save()">保存</a> <span>&nbsp;&nbsp;&nbsp;&nbsp;超重标准(吨)：</span>
		<input id="weightStandard" type="text" class="easyui-text"
			style="width: 30px" value="<%=weight_standard%>" />
	</div>
	<div id="menu" class="easyui-menu" style="display: none;">  
	      <!--放置一个隐藏的菜单Div-->  
	      <div id="btn_More" data-options="iconCls:'icon-remove'" style="width: 150px;" align="center" onclick="playback()">全景视频查看</div>        
	</div> 

</body>
</html>
