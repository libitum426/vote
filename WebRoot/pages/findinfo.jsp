<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"/>

<title>梦洁家纺个人信息查询</title>
	<link rel="stylesheet" type="text/css" href="statics/css/header.css" />
	<script src="statics/js/jquery-1.8.3.min.js"></script>
	<script src="statics/js/cookie.js"></script>
	<script src="statics/js/common.js"></script>
</head>

<body>

<form id="login" action="/vote/findUserInfo.do" method="post">
	<div class="upd_img">
		<img src="statics/images/logo.png">
	</div>
	<div class="log_style">
		<input class="user" name="code" placeholder="输入手机号查找" value="" autofocus/>
	</div>
	<div class="prompt">
		<span>${tip}</span>
	</div>
	<div class="upd_button">
		<input type="submit" id="submit" value="提  交" style="width: 100%;height: 100%;background: #1AC3DD none repeat scroll 0% 0%;">
	</div>
	</form>

</body>
</html>