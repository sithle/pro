<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
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
	int stream2_id = (Integer) session.getAttribute("stream2_id");
	String stream2_name = (String) session.getAttribute("stream2_name");
	int stream2_port = (Integer) session.getAttribute("stream2_port");
	//System.out.println(username+password+bridge_name+stream1_name+stream1_port+stream2_name+stream2_port);
%>

<!DOCTYPE html>
<html>
<head>
<title>用户配置信息</title>
<link href="../../css/bootstrap-combined.min.css" rel="stylesheet">
<script src="../../js/jquery-2.0.0.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/userinfo.js"></script>
</head>

<body onload="init()">
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<form class="form-horizontal"
					action="http://192.168.6.123:8080/pro/servlet/ChangeUserInfoServlet"
					method="post">
					<fieldset>
						<legend style="margin-left: 60px"><%=bridge_name%>限重系统配置信息
						</legend>
						<div class="control-group">
							<label class="control-label" for="inputUsername">用户名称：</label>
							<div class="controls">
								<input id="inputUsername" name="username" type="text"
									value="<%=username%>" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">用户密码：</label>
							<div class="controls">
								<input id="inputPassword" name="password" type="text"
									value="<%=password%>" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputBridge_name">桥梁名称：</label>
							<div class="controls">
								<input id="inputBridge_name" name="bridge_name" type="text"
									value="<%=bridge_name%>" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputWeight_standard">限重标准：</label>
							<div class="controls">
								<input id="inputWeight_standard" name="weight_standard" type="text"
									value="<%=weight_standard%>" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputStream1_id">桥梁上游ID：</label>
							<div class="controls">
								<input id="inputStream1_id" name="stream1_id" type="text"
									value="<%=stream1_id%>" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputStream1_name">桥梁上游名称：</label>
							<div class="controls">
								<input id="inputStream1_name" name="stream1_name" type="text"
									value="<%=stream1_name%>" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputStream1_port">桥梁上游端口：</label>
							<div class="controls">
								<input id="inputStream1_port" name="stream1_port" type="text"
									value="<%=stream1_port%>" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputStream2_id">桥梁下游ID：</label>
							<div class="controls">
								<input id="inputStream2_id" name="stream2_id" type="text"
									value="<%=stream2_id%>" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputStream2_name">桥梁下游名称：</label>
							<div class="controls">
								<input id="inputStream2_name" name="stream2_name" type="text"
									value="<%=stream2_name%>" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputStream2_port">桥梁下游端口：</label>
							<div class="controls">
								<input id="inputStream2_port" name="stream2_port" type="text"
									value="<%=stream2_port%>" />
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button id="changeInfoBtn" type="button" class="btn"
									onclick="chageInfoPressed()">修改</button>
								<button id="saveInfoBtn" type="submit" class="btn"
									style="margin-left: 110px">保存</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
