var r = /^[0-9]*[1-9][0-9]*$/; // 整数的正则表达式

// 初始化函数，将userinfo.jsp的文本框变成不可编辑的, 给一些空间添加监听事件.
function init() {
	document.getElementById('inputUsername').disabled = true;
	document.getElementById('inputPassword').disabled = true;
	document.getElementById('inputBridge_name').disabled = true;
	document.getElementById('inputWeight_standard').disabled = true;
	document.getElementById('inputStream1_id').disabled = true;
	document.getElementById('inputStream1_name').disabled = true;
	document.getElementById('inputStream1_port').disabled = true;
	document.getElementById('inputStream2_id').disabled = true;
	document.getElementById('inputStream2_name').disabled = true;
	document.getElementById('inputStream2_port').disabled = true;
	document.getElementById('saveInfoBtn').disabled = true;

	// 元素即将失去焦点时触发
	document.getElementById('inputStream1_port').addEventListener("focusout",
			function() {
				var v = document.getElementById('inputStream1_port').value;
				if (!r.test(v)) {
					alert("请输入正整数！");
					document.getElementById('inputStream1_port').focus();
				}
			});

	// 元素即将失去焦点时触发
	document.getElementById('inputStream2_port').addEventListener("focusout",
			function() {
				var v = document.getElementById('inputStream2_port').value;
				if (!r.test(v)) {
					alert("请输入正整数！");
					document.getElementById('inputStream2_port').focus();
				}
			});

	// 元素mouseover触发
	document.getElementById('saveInfoBtn').addEventListener("mouseover",
			function() {
				document.getElementById('inputUsername').disabled = false;
				document.getElementById('inputStream1_id').disabled = false;
				document.getElementById('inputStream2_id').disabled = false;
			});
	
	// 元素mouseout 触发
	document.getElementById('saveInfoBtn').addEventListener("mouseout",
			function() {
				document.getElementById('inputUsername').disabled = true;
				document.getElementById('inputStream1_id').disabled = true;
				document.getElementById('inputStream2_id').disabled = true;
			});
	
}

// 将userinfo.jsp的文本框变成可编辑的.
function chageInfoPressed() {
	// document.getElementById('inputUsername').disabled = false;
	document.getElementById('inputPassword').disabled = false;
	document.getElementById('inputBridge_name').disabled = false;
	document.getElementById('inputWeight_standard').disabled = false;
	document.getElementById('inputStream1_name').disabled = false;
	document.getElementById('inputStream1_port').disabled = false;
	document.getElementById('inputStream2_name').disabled = false;
	document.getElementById('inputStream2_port').disabled = false;
	document.getElementById('saveInfoBtn').disabled = false;
	document.getElementById('inputPassword').focus();
}