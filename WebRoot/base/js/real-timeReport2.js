// 正浮点数的正则表达式
var r = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
// 定义btn的一个标识符
var flag = true;

// real-timeReport2.jsp的初始化函数
function init() {
	// 将save按钮设置为不可用
	$('#save').linkbutton({
		disabled : true
	});
	// 每隔1s显示当前系统时间
	setInterval(function() {
		document.getElementById('currentTime').disabled = false;
		var datetimeStr = new Date().getFullYear() + "-"
				+ (new Date().getMonth() + 1) + "-" + new Date().getDate()
				+ " " + new Date().getHours() + ":" + new Date().getMinutes()
				+ ":" + new Date().getSeconds();
		document.getElementById('currentTime').value = datetimeStr;
		document.getElementById('currentTime').disabled = true;
	}, 1000);

	// 关闭间隔时间的输入
	document.getElementById('timeInterval').disabled = true;

	// 每隔设定的时间间隔刷新一下报表
	setInterval(
			function() {
				if (flag) {
					console.log(document.getElementById('timeInterval').value);
					console.log(flag);
					// 通过调用datagrid的reload函数实现报表重载
					$('#dg').datagrid('reload');
					// 添加详细信息展开框和超重样式
					$('#dg')
							.datagrid(
									{
										rowStyler : function(index, row) {
											var weightStandard = $(
													'#weightStandard').val();
											if (row.weight >= weightStandard) {
												return 'background-color:pink;color:blue;font-weight:bold;';
											}
										},
										view : detailview,
										detailFormatter : function(rowIndex,
												rowData) {
											var strArr = rowData.photo
													.split("\\");
											var arrLength = strArr.length;
											var imgPath = "http://192.168.6.123:8080/pro/"
													+ strArr[arrLength - 4]
													+ "/"
													+ strArr[arrLength - 3]
													+ "/"
													+ strArr[arrLength - 2]
													+ "/"
													+ strArr[arrLength - 1];
											return '<table class="dv-table" border="0" style="width:100%;">'
													+ '<tr>'
													+ '<td rowspan="3" style="width:200px">'
													+ '<img src="'
													+ imgPath
													+ '" style="width:200px;height:200px;margin-right:20px" />"'
													+ '</td>'
													+ '<td class="dv-label">车道:</td>'
													+ '<td>'
													+ rowData.lane
													+ '</td>'
													+ '<td class="dv-label">重量（吨）:</td>'
													+ '<td>'
													+ rowData.weight
													+ '</td>'
													+ '</tr>'
													+ '<tr>'
													+ '<td class="dv-label">车速（km/h）:</td>'
													+ '<td>'
													+ rowData.velocity
													+ '</td>'
													+ '<td class="dv-label">轴数（个）:</td>'
													+ '<td>'
													+ rowData.axis
													+ '</td>'
													+ '</tr>'
													+ '<tr>'
													+ '<td class="dv-label">车牌号:</td>'
													+ '<td>'
													+ rowData.carnumber
													+ '</td>'
													+ '<td class="dv-label">上/下游:</td>'
													+ '<td>'
													+ rowData.stream
													+ '</td>'
													+ '</tr>'
													+ '</table>';
										}
									});
				}
			}, document.getElementById('timeInterval').value * 60 * 1000);
	
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

// 更改按钮onclick
function update() {
	$('#save').linkbutton({
		disabled : false
	});
	$('#update').linkbutton({
		disabled : true
	});
	document.getElementById('timeInterval').disabled = false;
	document.getElementById('timeInterval').focus();
	flag = false;
}

// 保存按钮onclick
function save() {
	if (r.test(document.getElementById('timeInterval').value)) {
		var new_stream2_minute = document.getElementById('timeInterval').value;
		// 调用jQuery提供的Ajax方法
		$
				.ajax({
					type : "POST",
					async : false, // (默认: true)
					// 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为
					// false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
					url : "http://192.168.6.123:8080/pro/servlet/ChangeStream2_mimute?callback=?&new_stream2_minute="
							+ new_stream2_minute,
					data : "{}",
					dataType : "jsonp",
					success : function(msg) {

					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						// alert(errorThrown);
						// alert(XMLHttpRequest.status);// 200
						// alert(XMLHttpRequest.readyState);// 4
						// alert(textStatus);// pasererror
					}
				});
		window.location.href = window.location.href;
	} else {
		alert("请输入正小数！");
		document.getElementById('timeInterval').focus();
	}
	$('#save').linkbutton({
		disabled : true
	});
	$('#update').linkbutton({
		disabled : false
	});
	document.getElementById('timeInterval').disabled = true;
	flag = true;
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