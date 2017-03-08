<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登录界面</title>
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap-social.css" rel="stylesheet" type="text/css">
<link href="css/templatemo_style.css" rel="stylesheet" type="text/css">
</head>


<body class="templatemo-bg-image-1">
	<div class="container">
		<div class="col-md-12">
			<form class="form-horizontal templatemo-login-form-2"
				action="dologin.jsp" method="post">
				<div class="row">
					<div class="col-md-12">
						<h1>用户登录 / Login</h1>
					</div>
				</div>
				<div class="row">
					<div class="templatemo-one-signin col-md-6">
						<div class="form-group">
							<div class="col-md-12">
								<label for="username" class="control-label">用户名</label>
								<div class="templatemo-input-icon-container">
									<i class="fa fa-user"></i> <input type="text"
										class="form-control" name="username" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<label for="password" class="control-label">密码</label>
								<div class="templatemo-input-icon-container">
									<i class="fa fa-lock"></i> <input type="password"
										class="form-control" name="password" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<div class="checkbox">
									<label> <input type="checkbox"> 记住密码 </label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<input name="login" type="submit" class="btn btn-warning"
									value="登录">
							</div>
						</div>

					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
