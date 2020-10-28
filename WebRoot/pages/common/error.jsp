<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Error Page</title>
	<script type="text/javascript">
		function showDetail()
		{
			var elm = document.getElementById('detail_system_error_msg');
			if(elm.style.display == '') {
				elm.style.display = 'none';
			}else {
				elm.style.display = '';
			}
		}
	</script>
</head>

<body>

<div id="content">
	<%
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
	   LoggerFactory.getLogger(requestUri).error(exception.getMessage(), exception);
	%>
	<img alt="system internal error" src="<c:url value="/statics/images/error.gif"/>" />
	<h3>
	对不起,发生系统内部错误,不能处理你的请求<br />
	</h3>
	<!--<b>错误信息:</b>--> <%//exception.getMessage()%>
	<br>

	<!--  <button onclick="history.back();">返回</button>-->
	<br>

	<p><a href="#" onclick="showDetail();">点击这里查看具体错误消息</a>,报告以下错误消息给系统管理员,可以更加快速的解决问题</p>

	<div id="detail_system_error_msg" style="display:none">
		<pre><%exception.printStackTrace(new java.io.PrintWriter(out));%></pre>
	</div> 
</div>
</body>
</html>