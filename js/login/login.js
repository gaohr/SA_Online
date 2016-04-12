
	$("#submit").click(function() {
		username_s = $("#username").val();
		password_s = $("#password").val();
		$.getJSON("../../data/users/userInfo.json", null, function(data){
			//alert(data.users.length);
			for(i = 0; i < data.users.length; i++) {
				if(data.users[i].name == username_s && data.users[i].password == password_s) {
					//alert(username_s);
					$.cookie("user_SA", null, { path:"/SA_Online/html" });
					$.cookie("user_SA", username_s, { expires: 7, path: "/SA_Online/html" }); // 存储一个带7天期限的 cookie
					//若不设置路径，用$.cookie("user_SA")方式读取会出错
				}
			}
		});
		
	})

	

