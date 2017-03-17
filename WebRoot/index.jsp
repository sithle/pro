<%@page import="java.sql.Timestamp"%>
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
	int stream1_id = (Integer) session.getAttribute("stream1_id");
	String stream1_name = (String) session.getAttribute("stream1_name");
	int stream1_port = (Integer) session.getAttribute("stream1_port");
	int stream2_id = (Integer) session.getAttribute("stream2_id");
	String stream2_name = (String) session.getAttribute("stream2_name");
	int stream2_port = (Integer) session.getAttribute("stream2_port");

	Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	session.setAttribute("currentTime", currentTime);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="css/default.css" />
<link rel="stylesheet" type="text/css"
	href="js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
<script type="text/javascript" src='js/outlook2.js'></script>
<script type="text/javascript" src="js/init.js"></script>

<script type="text/javascript">
	var _menus = {
		"menus" : [ {
			"menuid" : "1",
			"icon" : "icon-sys",
			"menuname" : "基本配置",
			"menus" : [ {
				"menuid" : "12",
				"menuname" : "配置信息",
				"icon" : "icon-log",
				"url" : "base/jsp/userinfo.jsp"
			} ]
		}, {
			"menuid" : "8",
			"icon" : "icon-sys",
			"menuname" : "大桥上游",
			"menus" : [ {
				"menuid" : "21",
				"menuname" : "上游实时报表",
				"icon" : "icon-nav",
				"url" : "base/jsp/real-timeReport1.jsp"
			} ]
		}, {
			"menuid" : "56",
			"icon" : "icon-sys",
			"menuname" : "大桥下游",
			"menus" : [ {
				"menuid" : "31",
				"menuname" : "下游实时报表",
				"icon" : "icon-nav",
				"url" : "base/jsp/real-timeReport2.jsp"
			} ]
		}, {
			"menuid" : "28",
			"icon" : "icon-sys",
			"menuname" : "查询报表",
			"menus" : [ {
				"menuid" : "41",
				"menuname" : "明细报表",
				"icon" : "icon-nav",
				"url" : "base/jsp/queryReport.jsp"
			}, {
				"menuid" : "42",
				"menuname" : "情况汇总",
				"icon" : "icon-nav",
				"url" : "base/jsp/resultReport.jsp"
			}, {
				"menuid" : "43",
				"menuname" : "高峰时段统计",
				"icon" : "icon-nav",
				"url" : "base/jsp/wmReport.jsp"
			}, {
				"menuid" : "61",
				"menuname" : "危险数据统计",
				"icon" : "icon-nav",
				"url" : "base/jsp/dangerousDataReport.jsp"
			}, {
				"menuid" : "44",
				"menuname" : "按天汇总",
				"icon" : "icon-nav",
				"url" : "base/jsp/dayReport.jsp"
			} , {
				"menuid" : "46",
				"menuname" : "黑名单汇总",
				"icon" : "icon-nav",
				"url" : "base/jsp/blacklist.jsp"
			},{
				"menuid" : "47",
				"menuname" : "报警信息",
				"icon" : "icon-nav",
				"url" : "base/jsp/alarminfo.jsp"
			},{
				"menuid" : "48",
				"menuname" : "周报表",
				"icon" : "icon-nav",
				"url" : "base/jsp/WeekReport.jsp"
			},{
				"menuid" : "49",
				"menuname" : "月报表",
				"icon" : "icon-nav",
				"url" : "base/jsp/MonthReport.jsp"
			},{
				"menuid" : "60",
				"menuname" : "年报表",
				"icon" : "icon-nav",
				"url" : "base/jsp/yearReport.jsp"
			},{
				"menuid" : "45",
				"menuname" : "Excel下载",
				"icon" : "icon-nav",
				"url" : "base/jsp/download.jsp"
			} ]
		} ]
	};
	//设置登录窗口
	function openPwd() {
		$('#w').window({
			title : '修改密码',
			width : 300,
			modal : true,
			shadow : true,
			closed : true,
			height : 160,
			resizable : false
		});
	}
	//关闭登录窗口
	function closePwd() {
		$('#w').window('close');
	}

	//修改密码
	function serverLogin() {
		var $newpass = $('#txtNewPass');
		var $rePass = $('#txtRePass');

		if ($newpass.val() == '') {
			msgShow('系统提示', '请输入密码！', 'warning');
			return false;
		}
		if ($rePass.val() == '') {
			msgShow('系统提示', '请再一次输入密码！', 'warning');
			return false;
		}

		if ($newpass.val() != $rePass.val()) {
			msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
			return false;
		}

		$.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(
				msg) {
			msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
			$newpass.val('');
			$rePass.val('');
			close();
		});

	}

	$(function() {

		openPwd();

		$('#editpass').click(function() {
			$('#w').window('open');
		});

		$('#btnEp').click(function() {
			serverLogin();
		});

		$('#btnCancel').click(function() {
			closePwd();
		});

		$('#loginOut').click(function() {
			$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

				if (r) {
					location.href = '/pro/login.jsp';
				}
			});
		});
	});
</script>

</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">

	<noscript>
		<div
			style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>


	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 30px; background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%; line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
		<span style="float:right; padding-right:20px;" class="head">| <a
			href="#" id="loginOut">安全退出</a>
		</span> <span style="padding-left:10px; font-size: 16px; "><img
			src="images/blocks.gif" width="20" height="20" align="absmiddle" />
			<%=bridge_name%>限重系统</span>
	</div>

	<!--页面底部footer-->
	<div region="south" split="true"
		style="height: 30px; background: #D2E0F2; ">
		<div class="footer">Design By lc</div>
	</div>

	<!--  导航内容 -->
	<div region="west" hide="true" split="true" title="导航菜单"
		style="width:180px;" id="west">
		<div id="nav" class="easyui-accordion" fit="true" border="false">
		</div>
	</div>

	<!--默认欢迎界面-->
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y:hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<!--屏蔽默认欢迎页面-->
			<div title="HOME" style="padding:20px;overflow:hidden; color:red; ">
				<h1 style="font-size:24px;">Welcome!</h1>
			</div>
		</div>
	</div>

	<!--页面中间设置的右键菜单-->
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>

</body>
</html>
