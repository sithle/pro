// queryReport.jsp的初始化函数
function init() {
	document.getElementById('weightStandard').disabled = true;
}

// 查询函数
function query() {
	var starttime = $('#start_timeInput').datetimebox('getValue');
	var endtime = $('#end_timeInput').datetimebox('getValue');
	console.log(starttime);
	if (starttime != "" && endtime != "") {
		var stream = $('#streamInput').combobox('getText');
		document.getElementById('weightStandard').disabled = false;
		var weightStandard = $('#weightStandard').val();
		document.getElementById('weightStandard').disabled = true;
		// 载数据
		$('#dg').datagrid('load', {
			start_time : $('#start_timeInput').datetimebox('getValue'),
			end_time : $('#end_timeInput').datetimebox('getValue'),
			stream : stream,
			isGetExcel : 0,
			weightStandard : weightStandard
		});
	} else {
		alert("起始时间和终止时间不能为空！");
	}
}

// 弹出Excel表的基本信息设置对话框
function getExcelDialog() {
	var start_time = $('#start_timeInput').datetimebox('getValue');
	var end_time = $('#end_timeInput').datetimebox('getValue');
	if (start_time != "" && end_time != "") {
		$('#dlg').dialog('open').dialog('setTitle', 'Excel表基本信息');
		document.getElementById("tb").style.display = "none";
	} else {
		alert("起始时间和终止时间不能为空！");
	}

}

// 导出Excel表函数
function excelAction() {
	var stream = $('#streamInput').combobox('getText');
	document.getElementById('weightStandard').disabled = false;
	var weightStandard = $('#weightStandard').val();
	document.getElementById('weightStandard').disabled = true;
	$('#dg').datagrid('load', {
		start_time : $('#start_timeInput').datetimebox('getValue'),
		end_time : $('#end_timeInput').datetimebox('getValue'),
		stream : stream,
		isGetExcel : 1,
		weightStandard : weightStandard,
		sheetname : $('#sheetname').val(),
		excelname : $('#excelname').val()
	});
	// window.open("http://192.168.6.123:8080/pro/servlet/DownloadServlet?filename="
	// + $('#excelname').val() + ".xls", "下载页面");
	$('#dlg').dialog('close'); // close the dialog
	document.getElementById("tb").style.display = "block";
	$('#dg').datagrid('reload'); // reload the user data
}

// Excel对话框取消按钮
function excelCancel() {
	$('#dlg').dialog('close');
	document.getElementById("tb").style.display = "block";
}