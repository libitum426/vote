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
<title>voteList</title>
</head>

<body>
<fieldset id="input">
	<legend>列表</legend>
	<a href="userList.do">用户列表</a>
	<a href="deployList.do">投票列表</a>
	<a href="candidateList.do">候选人列表</a>
</fieldset>
	<script src="<%=path %>/statics/js/jquery-1.8.3.min.js"></script>
	<script src="<%=path %>/statics/js/jquery-labelauty.js"></script>
	<script type="text/javascript">
		var ctx = '${request.getContextPath()}';
	</script>
</body>
</html>