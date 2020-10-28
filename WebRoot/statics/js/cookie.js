//cookie
$(function(){
	javascript:window.history.forward(1);
	/**
	 * 登录界面
	 * @author xzl
	 */
	$('.log_two img').click(function(){
		var remember = $('.log_two img').attr('src');
		if("./images/handle1.png"==remember){
			$('.log_two img').attr('src','./images/handle2.png');
		}else{
			$('.log_two img').attr('src','./images/handle1.png');
		}
	});
	//cookie
    function setCookie(name, value) {
        var Days = 30;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
    }

    //读取cookies 
    function getCookie(name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

        if (arr = document.cookie.match(reg)) return unescape(arr[2]);
        else return null;
    }

    //删除cookies 
    function delCookie(name) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = getCookie(name);
        if (cval != null) document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
    }
    
    var username = getCookie("userName");
	var password = getCookie("password");
    if(username !=null && password !=null){
    	$('.log_style input').attr('value',username);
    	$('.pass').attr('value',password);
    	$('.log_two img').attr('src','./images/handle1.png');
    }
    if(username!=null && password == null){
    	$('.log_style input').attr('value',username);
    }
    //测试用户点击记住密码以及不记住密码的功能
    $('.upd_button').on('click',function(){
			username = $('.user').val();
			password = $('.pass').val();
			$.ajax({
				type:"post",
				url:rootPath+"/login.do",
				data:{"code":username,"userPwd":password},
				dataType:"json",
				success:function(data){
					var houseId = data.houseId;
					var a = data.isLogin;
					if(a == "Y"){
						if(data.loginState=="0"){
							window.location.href="pages/updatepwd.jsp?houseId="+houseId;
						}else if(data.loginState=="1"){
							window.location.href="pages/homerank.jsp?houseId="+houseId;
						}
					}else{
						$('.prompt span').text("用户名或密码不正确 ，请重试！");
					}
				},
				error:function(){
				}
			});
    	var determine = $('.log_two img').attr('src');
    	if(determine == "./images/handle1.png"){
    		var userName = $('.user').val();
    		var password = $('.pass').val();
    		setCookie("userName", userName);
    		setCookie("password", password);
    	}else{
    		delCookie("password");
    	}
    });
    
});