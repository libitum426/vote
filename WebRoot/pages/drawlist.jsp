<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="/pages/common/meta.jsp"%>
<%@ include file="/pages/common/_header.jsp"%>
<%@ include file="/pages/common/_footer.jsp"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style type="text/css">

.titleSpan{
	font-size: 50px; 
	color:#ffff00; 
	font-family: Tahoma, Arial, '微软雅黑', '黑体', '宋体';
}

.xTitleSpan{
	font-size: 30px; 
	color:#ffff00; 
	font-family: Tahoma, Arial, '微软雅黑', '黑体', '宋体';
}

</style>
<title>梦洁家纺-爱在家庭</title>
<link rel="stylesheet" type="text/css" href="${ctxs}/css/drawstyle.css" />
<script type="text/javascript">
</script>
</head>
<body style="background-image: url('${ctxs}/images/bg2.jpg');background-size:100% 100%; background-repeat:no-repeat;">
<div style="padding-top: 120px;text-align: center;" align="center">
	<ul>
		<c:forEach var="draw" items="${draws }">
			<li>
				<a href="#" onclick="draw_opera('2017年梦洁春夏新品发布会抽奖活动','${ctx}/comeDraw.do?drawId=${draw.drawId}');"><font size="6px" color="red">${draw.drawTitle }</font></a>
			</li>
		</c:forEach>
	</ul>
</div>
</body>
<script type="text/javascript">

	function drawUser(){
		window.open("queryDrawUsers.do");
		//window.location.href="queryDrawUsers.do"
	}

	function operaDraw(drawId){
		alert(drawId);
	}
	
	function draw_opera(title,url){
		var index = layer.open({
			type: 2,
			title: title,
			content: url,
			cancel:function() {
				window.location.href="drawList.do";
			}
		});
		layer.full(index);
	}
</script>
</html>