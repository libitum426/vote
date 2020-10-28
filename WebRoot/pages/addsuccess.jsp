<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/pages/common/head.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>结果页面</title>
<%response.setHeader("Refresh","5;URL=deployList.do");%>
</head>

<body>
	<fieldset id="inputs" class="display">
		<legend>新增投票配置</legend>
		<font size="5px" color="red">${tip }</font>
	</fieldset>
</body>
</html>