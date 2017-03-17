<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String WMReportServlet = basePath + "servlet/WMReport";

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
<title>高峰时段统计</title>

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
<script type="text/javascript" src="../js/wmReport.js"></script>

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
	<h2><%=bridge_name%>高峰时段统计
	</h2>
	<div class="demo-info" style="margin-bottom:10px">
		<div class="demo-tip icon-tip">&nbsp;</div>
		<div>温馨提示：起始时间必须以 xxxx-xx-xx 00:00 开始，结束时间最好以 xxxx-xx-xx 23:59结束</div>
	</div>

	<table id="dg" class="easyui-datagrid" style="width:1050px;height:435px"
		url="<%=WMReportServlet %>" title="高峰时段统计" iconCls="icon-search" toolbar="#tb"
		rownumbers="false" pagination="false" singleSelect="true" fitColumns="false" showFooter="false" >
		<thead>
			<tr>
				<th field="time" width="70" align="center"></th>
				<th field="hour1" width="33" align="center">1点</th>
				<th field="hour2" width="33" align="center">2点</th>
				<th field="hour3" width="33" align="center">3点</th>
				<th field="hour4" width="33" align="center">4点</th>
				<th field="hour5" width="33" align="center">5点</th>
				<th field="hour6" width="33" align="center">6点</th>
				<th field="hour7" width="33" align="center">7点</th>
				<th field="hour8" width="33" align="center">8点</th>
				<th field="hour9" width="33" align="center">9点</th>
				<th field="hour10" width="33" align="center">10点</th>
				<th field="hour11" width="33" align="center">11点</th>
				<th field="hour12" width="33" align="center">12点</th>
				<th field="hour13" width="33" align="center">13点</th>
				<th field="hour14" width="33" align="center">14点</th>
				<th field="hour15" width="33" align="center">15点</th>
				<th field="hour16" width="33" align="center">16点</th>
				<th field="hour17" width="33" align="center">17点</th>
				<th field="hour18" width="33" align="center">18点</th>
				<th field="hour19" width="33" align="center">19点</th>
				<th field="hour20" width="33" align="center">20点</th>
				<th field="hour21" width="33" align="center">21点</th>
				<th field="hour22" width="33" align="center">22点</th>
				<th field="hour23" width="33" align="center">23点</th>
				<th field="hour24" width="33" align="center">24点</th>
				<th field="fastigium" width="70" align="center">高峰时段</th>
				<th field="number" width="33" align="center">总数</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:3px">
		起始时间： <input id="start_timeInput" class="easyui-datetimebox" style="width:160px"/>
		结束时间：<input id="end_timeInput" class="easyui-datetimebox" style="width:160px"/>
		上/下游：<input id="streamInput" class="easyui-combobox" style="width:50px"
					url="data/combobox_data.json"
					valueField="id" textField="text"/>
		超重标准(吨)：<input id="weightStandard" type="text" class="easyui-text" style="width: 30px" value="<%=weight_standard %>" />
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
