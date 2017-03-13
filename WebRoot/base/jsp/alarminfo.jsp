<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String GetCarsJSONServlet = basePath + "servlet/Alarminfo";

	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	String username = (String) session.getAttribute("username");
	String password = (String) session.getAttribute("password");
	if (username == null && password == null) {
		response.sendRedirect("login.jsp");
	}
	String bridge_name = (String) session.getAttribute("bridge_name");
	double weight_standard = (Double) session.getAttribute("weight_standard");
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
<title>情况汇总</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="description" content="情况汇总">

<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.2/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="../jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.2/datagrid-detailview.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../js/alarminfo.js"></script>

<style type="text/css">
	.dv-table td{
		border:0;
	}
	.dv-label{
		font-weight:bold;
		color:#15428B;
		width:100px;
	}
</style>

</head>

<body onload="init()">
	<h2><%=bridge_name%>报警信息查询
	</h2>
	<div class="demo-info" style="margin-bottom:10px">
		<div class="demo-tip icon-tip">&nbsp;</div>
		<div>温馨提示：</div>
	</div>

	<table id="dg" class="easyui-datagrid" style="width:750px;height:435px"
		url="<%=GetCarsJSONServlet %>" title="报警信息" iconCls="icon-search" toolbar="#tb"
		rownumbers="true" pagination="false" singleSelect="true" fitColumns="false" showFooter="false" >
		<thead>
			<tr>
				<th field="starttime" width="150" align="center">起始时间</th>
				<th field="endtime" width="150" align="center">结束时间</th>
				<th field="info" width="280" align="center">报警信息</th>

				
				
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:3px">
		起始时间： <input id="start_timeInput" class="easyui-datebox" style="width:160px"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		结束时间：<input id="end_timeInput" class="easyui-datebox" style="width:160px"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

		<a id="queryBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="query()">查询</a>
		
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:300px;height:150px;padding:10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div style="text-align: center;">
			<form id="ff" method="post">
	    		<table>
	    			<tr>
	    				<td>sheet的名字：</td>
	    				<td><input id="sheetname" class="easyui-validatebox" type="text" name="sheetname" data-options="required:true" value="default"></input></td>
	    			</tr>
	    			<tr>
	    				<td>Excel的名字：</td>
	    				<td><input id="excelname" class="easyui-validatebox" type="text" name="excelname" data-options="required:true" value="default"></input></td>
	    			</tr>
	    		</table>
	 	   </form>
	    </div>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="excelAction()">确定</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" 
			onclick="excelCancel()">取消</a>
	</div>
	
	<input id="stream1_port" type="text" value="<%=stream1_port %>" style="width:0;height:0" />
	<input id="stream2_port" type="text" value="<%=stream2_port %>" style="width:0;height:0" />
</body>
</html>
