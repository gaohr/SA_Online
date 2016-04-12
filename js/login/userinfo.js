
//	function getCookie(c_name) {
//		if (document.cookie.length>0) {
//			c_start = document.cookie.indexOf(c_name + "=");
//			if (c_start != -1) { 
//				c_start = c_start + c_name.length + 1;
//				c_end=document.cookie.indexOf(";",c_start);
//				if (c_end == -1) {
//					c_end = document.cookie.length;
//				}
//				return unescape(document.cookie.substring(c_start,c_end));
//			}
//		}
//		return "";
//	}
//	function setCookie(c_name, value, expiredays) {
//		var exdate = new Date();
//		exdate.setDate(exdate.getDate() + expiredays);
//		document.cookie = c_name + "=" + escape(value) + ((expiredays ==null ) ? "" : ";expires=" + exdate.toGMTString());
//	}

	//判断是否登录，登录状态时显示用户信息
	$(document).ready(function() {
		username_i = $.cookie("username"); // 读取 cookie;
		if (username_i != null && username_i != "") {
			$("#name").html(username_i);
			$.ajax({
				url:"/iFood/GetUserInfo?un=" + username_i,
				async:true,
				success:function(e) {
					var result = eval("(" + e + ")");
					$("#tel").html(result.tel);
					$("#email").html(result.email);
				},
				error:function() {
					alert("Error!")
				}
			});
		} else {
			window.location.href="../html/Login.html"; 
		}
	})
	$("#username-re").click(function() {
		$.cookie("username", null);
	})
	

