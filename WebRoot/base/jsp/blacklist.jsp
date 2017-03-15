<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String GetCarsJSONServlet = basePath + "servlet/Blacklist";

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
<script type="text/javascript" src="../js/blacklist.js"></script>

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
	<h2><%=bridge_name%>黑名单查询
	</h2>
	<div class="demo-info" style="margin-bottom:10px">
		<div class="demo-tip icon-tip">&nbsp;</div>
		<div>温馨提示：默认查询100吨以上且通过两次的车辆</div>
	</div>

	<table id="dg" class="easyui-datagrid" style="width:950px;height:435px"
		url="<%=GetCarsJSONServlet %>" title="黑名单" iconCls="icon-search" toolbar="#tb"
		rownumbers="true" pagination="false" singleSelect="true" fitColumns="false" showFooter="false" >
		<thead>
			<tr>
				<th field="carnumber" width="130" align="center">车牌</th>
				<th field="total" width="100" align="center">通过次数</th>
				<th field="axis" width="100" align="center">轴数</th>
				<th field="weightest" width="130" align="center">最重时重量（吨）</th>
				<th field="latest" width="130" align="center">最近一次通过时间</th>
				
				<th field="latestweight" width="130" align="center">最近一次重量（吨）</th>
				
				
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:3px">
		起始时间： <input id="start_timeInput" class="easyui-datetimebox" style="width:160px"/>
		结束时间：<input id="end_timeInput" class="easyui-datetimebox" style="width:160px"/>
		上/下游：<input id="streamInput" class="easyui-combobox" style="width:50px"
					url="data/combobox_data.json"
					valueField="id" textField="text"/>
		标准(吨)：<input id="weightStandard" type="text" class="easyui-text" style="width: 30px" value="100" />
		通过次数：<input id="total" type="text" class="easyui-text" style="width: 30px" value="2" />
		<a id="queryBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="query()">汇总</a>
		<a id="excelBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'" onclick="getExcelDialog()">Excel</a>
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
	<script>$('#start_timeInput').datetimebox({showSeconds: false});</script>
<script>$('#end_timeInput').datetimebox({showSeconds: false});</script>
</body>
</html>
