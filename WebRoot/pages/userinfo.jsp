<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%
	String path = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>座位信息</title>
</head>

<body>
		<br>
		<br>
		<br>
	<fieldset id="inputs">
		<legend style="font-size: 10pt">座位信息</legend>
		<span style="font-size: 20pt">${voteUser.name} 您好!
		<br>
		您的座位信息为：
		 ${voteUser.lineNum}桌;${voteUser.columnNum}号;
		<br>
		<font color="red">请按要求就坐!</font>
		</span>
	</fieldset>
	<img alt="" src="<%=path%>/statics/images/zuowei.jpg" width="340">
	<script src="<%=path%>/statics/js/jquery-1.8.3.min.js"></script>
	<script src="<%=path%>/statics/js/jquery-labelauty.js"></script>
</body>
</html>