<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/pages/common/meta.jsp"%>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctxs }/admin/lib/html5.js"></script>
<script type="text/javascript" src="${ctxs }/admin/lib/respond.min.js"></script>
<script type="text/javascript" src="${ctxs }/admin/lib/PIE_IE678.js"></script>
<![endif]-->
<link href="${ctxs }/admin/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxs }/admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="${ctxs }/admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctxs }/admin/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctxs}/admin/js/jquery.js"></script>
<script type="text/javascript" src="${ctxs}/admin/js/jQuery.md5.js"></script>
<script type="text/javascript" src="${ctxs}/admin/lib/layer/1.9.3/layer.js"></script>
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>投票系统后台登陆</title>
<script type="text/javascript">
	function addCookie(objName, objValue, objHours) {//添加cookie
		var str = objName + "=" + escape(objValue);
		if (objHours > 0) {//为0时不设定过期时间，浏览器关闭时cookie自动消失
			var date = new Date();
			var ms = objHours * 3600 * 1000;
			date.setTime(date.getTime() + ms);
			str += "; expires=" + date.toGMTString();
		}
		document.cookie = str;
	}

	function getCookie(objName) {//获取指定名称的cookie的值
		var arrStr = document.cookie.split("; ");
		for ( var i = 0; i < arrStr.length; i++) {
			var temp = arrStr[i].split("=");
			if (temp[0] == objName)
				return unescape(temp[1]);
		}
	}

	function delCookie(name) {
		var date = new Date();
		date.setTime(date.getTime() - 10000);
		document.cookie = name + "=a; expires=" + date.toGMTString();
	}

	var keyUserName = "sts_loginname";
	var keyUserPwd = "sts_loginpassword";
	$(function() {
		if (top.location !== self.location) {
			top.location = self.location;
		}
	});
	
	$(document).keypress(function(e) { 
	   // 回车键事件 
	   if(e.which == 13) { 
	     loginForward();
	   } 
	});

	function loginForward() {
		var name = $("#userName").val();
		var pwd = $("#userPwd").val();
		if (name == null || $.trim(name).length == 0) {
		    layer.msg('您好，请输入用户名!',function(){
		        $("#userName").focus();
		    });
		} else if (pwd == null || $.trim(pwd).length == 0) {
			layer.msg('您好，请输入密码!',function(){
			    $("#userPwd").focus();
			});
		} else {
			if (document.getElementById("chkUserName").checked) {
				addCookie(keyUserName, name, 0);
				addCookie(keyUserPwd, pwd, 0);
			} else {
				delCookie(keyUserName);
				delCookie(keyUserPwd);
			}
			
			$("#userPwd").val($.md5($("#userPwd").val()).toUpperCase());
			document.all.loginForm.submit();
		}
	}
</script>
</head>
<body>
<div class="header"></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal" action="${ctx}/adminLogin.do" method="post" id="loginForm">
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-8">
          <input id="userName" name="userName" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-8">
          <input id="userPwd" name="userPwd" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <label for="online">
            <input type="checkbox" name="chkUserName" id="chkUserName" value="" checked="checked">
            使我保持登录状态</label>
        </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-3">
        	<input id="loginButton" name="loginButton" type="button" type="button" class="btn btn-success radius size-L"
							value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;" onclick="javascript:loginForward();"/> 
			<input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">Copyright 梦洁家纺股份有限公司</div>
</body>
</html>

<c:if test="${tip=='visitinvild'}">
    <c:set var="tip" value="您好，请登录！！"></c:set>
</c:if>
<c:if test="${param.tip == 'loginAgain'}">
    <c:set var="tip" value="请重新登录！"></c:set>
</c:if>
<c:if test="${tip != '' && tip != null}">
    <script>
        layer.msg('${tip}',function(){
           if ("${userName}" == "") {
				$("#userName").focus();
			} else {
				$("#userPwd").focus();
			}
        });
    </script>
 </c:if>
