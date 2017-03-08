var urlStr = "http://localhost:8080/pro/servlet/";

var user_data; //

// index.jsp的初始化函数
function init() {
	// ajax("GetCarsJSON", "2016-04-15 14:19:22", "2016-04-18 15:29:41", 0);
}

// Ajax从服务器拿数据
function ajax(url_servlet, start_time, end_time, weight) {
	var url = urlStr + url_servlet + "?callback=?&start_time=" + start_time
			+ "&end_time=" + end_time + "&weight=" + weight;
	// 调用jQuery提供的Ajax方法
	$.ajax({
		type : "POST",
		async : true, // (默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为
		// false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
		url : url,
		data : "{}",
		dataType : "jsonp",
		success : function(msg) {
			console.log(msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
			alert(XMLHttpRequest.status);// 200
			alert(XMLHttpRequest.readyState);// 4
			alert(textStatus);// pasererror
		}
	});
}