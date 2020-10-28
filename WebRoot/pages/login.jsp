<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"/>

<link rel="stylesheet" type="text/css" href="statics/css/header.css" />

	<script src="statics/js/jquery-1.8.3.min.js"></script>
	<script src="statics/js/cookie.js"></script>
	<script src="statics/js/common.js"></script>

<title>梦洁家访投票系统</title>

</head>

<body style="background-color: #f0fdfe;">
	<!-- 
<form id="login" action="/vote/login.do" method="post">
    <img alt="" src="/vote/statics/images/logio.png">
    <output style="color: red;azimuth:center; ">${tip}</output>
    <fieldset id="inputs">
        <input id="username" value="" name="code" type="text" placeholder="手机号" autofocus required>   
        <input id="password" value=""  name="userPwd" type="text" placeholder="投票码" required>
    </fieldset>
    <fieldset id="actions" style="azimuth:center;">
    	<div align="center" style="left: 10%">
        	<input type="submit" id="submit" value="提  交">
        </div>
    </fieldset>
</form>
 -->
 <form id="login" action="/vote/login.do" method="post">
	<div class="upd_img">
		<img src="statics/images/logo.png">
	</div>
	<div class="log_style">
		<input class="user" name="code" placeholder="手机号" value="${code==null?'':code}" <c:if test="${code!=null }">readonly="readonly"</c:if>/>
	</div>
	<div class="pas_style">
		<input class="pass" name="userPwd" placeholder="投票码" value="" />
	</div>
	<!-- <div class="log_two">
		<table cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2"><input class="pass" placeholder="投票码"
					value="" /></td>
			</tr>
		</table>
	</div>
	 -->
	<div class="prompt">
		<span>${tip}</span>
	</div>
	<div class="upd_button">
		<input type="submit" id="submit" value="提  交" style="width: 100%;height: 100%;background: #1AC3DD none repeat scroll 0% 0%;">
	</div>
	</form>
</body>
</html>