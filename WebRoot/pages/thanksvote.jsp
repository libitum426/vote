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
<title>结果信息</title>
<style type="text/css">
img { height: auto; width: auto\9; width:100%; }
</style>
</head>

<body>
		
		<img alt="" src="<%=path%>/statics/images/thanksVote.jpg" width="574" height="68">
		<!-- 
		<br>
		<br>
		<span style="font-size: 35pt">
		<font>感谢您的参与，谢谢！</font>
		</span>
		 -->
	<script src="<%=path%>/statics/js/jquery-1.8.3.min.js"></script>
	<script src="<%=path%>/statics/js/jquery-labelauty.js"></script>
</body>
</html>