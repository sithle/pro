// 正浮点数的正则表达式
var r = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;

// queryReport.jsp的初始化函数
function init() {
	document.getElementById('weightInput').value = 0.1;
	
	// 右键15s视频回放，邹崎
    $("#dg").datagrid({  
        onRowContextMenu: function (e, rowIndex, rowData) { //右键时触发事件  
            //三个参数：e里面的内容很多，真心不明白，rowIndex就是当前点击时所在行的索引，rowData当前行的数据  
            e.preventDefault(); //阻止浏览器捕获右键事件  
            $(this).datagrid("clearSelections"); //取消所有选中项  
            $(this).datagrid("selectRow", rowIndex); //根据索引选中该行  
            row = $(this).datagrid("getSelected");
            $("#menu").menu('show', {  
                //显示右键菜单  
                left: e.pageX,//在鼠标点击处显示菜单  
                top: e.pageY  
            });
            index = rowIndex; 
            
            e.preventDefault();  //阻止浏览器自带的右键菜单弹出  
            //alert(rowIndex+" "+row.ID+" "+row.datetime);
        }
    });
}

// 查询函数
function query() {
	if (r.test($('#weightInput').val())) {
		var stream = $('#streamInput').combobox('getText');
		// 载数据
		$('#dg').datagrid('load', {
			start_time : $('#start_timeInput').datetimebox('getValue'),
			end_time : $('#end_timeInput').datetimebox('getValue'),
			stream : stream,
			weight : $('#weightInput').val(),
			isGetExcel : 0,
			weightStandard : $('#weightStandard').val()
		});
		// 展示明细
		$('#dg').datagrid({
			rowStyler:function(index,row){
				var weightStandard = $('#weightStandard').val();
				if (row.weight >= weightStandard){
					return 'background-color:pink;color:blue;font-weight:bold;';
				}
			},
			view: detailview,
			detailFormatter: function(rowIndex, rowData){
				var strArr = rowData.photo.split("\\");
				var arrLength = strArr.length;
				var imgPath = "http://192.168.6.123:8080/pro/"
						+ strArr[arrLength - 4] + "/"
						+ strArr[arrLength - 3] + "/"
						+ strArr[arrLength - 2] + "/"
						+ strArr[arrLength - 1];
				return '<table class="dv-table" border="0" style="width:100%;">'+
				'<tr>'+
					'<td rowspan="3" style="width:200px">'+
						'<img src="'+imgPath+'" style="width:200px;height:200px;margin-right:20px" />"'+	
					'</td>'+
					'<td class="dv-label">车道:</td>'+
					'<td>'+rowData.lane+'</td>'+
					'<td class="dv-label">重量（吨）:</td>'+
					'<td>'+rowData.weight+'</td>'+
				'</tr>'+
				'<tr>'+
					'<td class="dv-label">车速（km/h）:</td>'+
					'<td>'+rowData.velocity+'</td>'+
					'<td class="dv-label">轴数（个）:</td>'+
					'<td>'+rowData.axis+'</td>'+
				'</tr>'+
				'<tr>'+
					'<td class="dv-label">车牌号:</td>'+
					'<td>'+rowData.carnumber+'</td>'+
					'<td class="dv-label">上/下游:</td>'+
					'<td>'+rowData.stream+'</td>'+
				'</tr>'+
				'</table>';
			}
		});
	} else {
		alert("重量变量中请输入正小数！");
		document.getElementById('weightInput').focus();
	}

}

// 弹出Excel表的基本信息设置对话框
function getExcelDialog() {
	if (r.test($('#weightInput').val())) {
		$('#dlg').dialog('open').dialog('setTitle', 'Excel表基本信息');
		document.getElementById("tb").style.display="none"; 
	} else {
		alert("请输入重量参数，要求（正小数）！");
		document.getElementById('weightInput').focus();
	}
}

//导出Excel表函数
function excelAction() {
	var stream = $('#streamInput').combobox('getText');

	$('#dg').datagrid('load', {
		start_time : $('#start_timeInput').datetimebox('getValue'),
		end_time : $('#end_timeInput').datetimebox('getValue'),
		stream : stream,
		weight : $('#weightInput').val(),
		isGetExcel : 1,
		weightStandard : $('#weightStandard').val(),
		sheetname : $('#sheetname').val(),
		excelname : $('#excelname').val()
	});
	//window.open("http://192.168.6.123:8080/pro/servlet/DownloadServlet?filename=" + $('#excelname').val() + ".xls", "下载页面");
	$('#dlg').dialog('close');		// close the dialog
	document.getElementById("tb").style.display="block";
	$('#dg').datagrid('reload');	// reload the user data
}

// Excel对话框取消按钮
function excelCancel() {
	$('#dlg').dialog('close');
	document.getElementById("tb").style.display="block"; 
}

//右键15s视频回放，邹崎
function playback(){
	//alert(row.ID+" "+row.datetime.toString());

	$.ajax({
		type: "post",
		url : "http://192.168.6.123:8080/pro/servlet/PlaybackVideo",
		//contentType: "application/json;charset=utf-8",
		data: {"datetime": row.datetime.toString(), "stream": row.stream.toString()},
		dataType: "json",
		// 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
		//async: true,
		success: function(data) {
			//alert(row.ID+" "+row.datetime);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
/*                         alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus); */
                        alert("error!"+XMLHttpRequest.status+XMLHttpRequest.readyState+textStatus+" "+row.datetime.toString()+row.stream.toString());
                    }
	});
	
	//window.open('test.xhtml','_blank','height=500,width=600,top=0,left=0,toolbar=no,alwaysRaised=yes');
}