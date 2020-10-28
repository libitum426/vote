<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="/pages/common/meta.jsp"%>
<%@ include file="/pages/common/_header.jsp"%>
<%@ include file="/pages/common/_footer.jsp"%>
	
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="/favicon.ico">
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctxs }/admin/lib/html5.js"></script>
<script type="text/javascript" src="${ctxs }/admin/lib/respond.min.js"></script>
<script type="text/javascript" src="${ctxs }/admin/lib/PIE_IE678.js"></script>
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="${ctxs }/admin/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${ctxs }/css/flip_099.css" />
<title>梦洁家纺2016年年会奖项列表</title>
<style type="text/css">
*{margin:0; padding:0;}
body{font-size:12px; font-family: Tahoma,Arial,"微软雅黑","黑体","宋体";}
.meimei{width:180px; margin:20px 28px 20px 10px; overflow:hidden; float:left; height:320px; text-align:left; _margin:20px 30px 20px 5px;} 
h3{font-size:16px; color:#fff; height:25px; line-height:25px; clear:both; padding:5px 10px; 
margin:10px; margin-bottom:0px; background:#87CEEB; -moz-border-radius:3px; 
-webkit-border-radius:3px;}
h4{color:#B0DC10; font-size:14px; line-height:20px; margin:10px 0px;}
p{color:#336699; font-size:15px; line-height:18px; face:华文行楷 ;}
a{border:2px solid #e9eaeb; border-radius:3px; -moz-border-radius:3px; 
-webkit-border-radius:3px; padding:3px; display:block; height:160px;}
img{border:none;}
a:hover{border:2px solid #0586d8;}
.clear{clear:both;}
</style>
</head>
<body>
<nav class="breadcrumb">
		<a class="btn btn-primary radius"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:viod();" title="刷新" onclick="drawUser();">中奖名单</a>
</nav>

	<c:forEach var="dataObj" items="${drawconfigs}">
		<div class="meimei">
			<div id="flipbox" onclick="draw_opera('梦洁家纺2016年年会抽奖','${ctx}/operaDraw.do?drawId=${dataObj.id}');">${dataObj.name}</div>
		</div>	
    </c:forEach>
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