<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String GetCarsJSONServlet = basePath + "servlet/GetCarsJSON";

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
<title>查询报表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="description" content="查询报表">

<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.2/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="../jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.2/datagrid-detailview.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../js/queryReport.js"></script>

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
	<h2><%=bridge_name%>查询与报表
	</h2>
	<div class="demo-info" style="margin-bottom:10px">
		<div class="demo-tip icon-tip">&nbsp;</div>
		<div>温馨提示：上游对应的端口号为<%=stream1_port %>; 下游对应的端口号为<%=stream2_port %>.</div>
	</div>

	<table id="dg" class="easyui-datagrid" style="width:950px;height:435px"
		url="<%=GetCarsJSONServlet%>" title="查询与报表" iconCls="icon-search" toolbar="#tb"
		rownumbers="true" pagination="true" singleSelect="true" fitColumns="false" showFooter="true" >
		<thead>
			<tr>
				<th field="datetime" width="140" align="center">时间</th>
				<th field="lane" width="100" align="center">车道</th>
				<th field="velocity" width="100" align="center">车速（km/h）</th>
				<th field="weight" width="100" align="center" sortable="true">重量（吨）</th>
				<th field="axis" width="100" align="center">轴数（个）</th>
				<th field="carnumber" width="100" align="center">车牌号</th>
				<th field="photo" width="140" align="center">照片地址</th>
				<th field="stream" width="100" align="center">上/下游</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:3px">
		起始时间： <input id="start_timeInput" class="easyui-datetimebox" style="width:160px"/>
		结束时间：<input id="end_timeInput" class="easyui-datetimebox" style="width:160px"/>
		上/下游：<input id="streamInput" class="easyui-combobox" style="width:50px"
					url="data/combobox_data.json"
					valueField="id" textField="text"/>
		重量(吨)>=<input id="weightInput" type="text" class="easyui-text" style="width: 30px" />
		超重标准(吨)：<input id="weightStandard" type="text" class="easyui-text" style="width: 30px" value="<%=weight_standard %>" />
		<a id="queryBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="query()">查询</a>
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
	<div id="menu" class="easyui-menu" style="display: none;">  
	      <!--放置一个隐藏的菜单Div-->  
	      <div id="btn_More" data-options="iconCls:'icon-remove'" style="width: 150px;" align="center" onclick="playback()">全景视频查看</div>        
	</div> 
	
	<input id="stream1_port" type="text" value="<%=stream1_port %>" style="width:0;height:0" />
	<input id="stream2_port" type="text" value="<%=stream2_port %>" style="width:0;height:0" />
	<script>$('#start_timeInput').datetimebox({showSeconds: false});</script>
<script>$('#end_timeInput').datetimebox({showSeconds: false});</script>
</body>
</html>
