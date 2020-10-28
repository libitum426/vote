<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/pages/common/meta.jsp"%>
<%@ include file="/pages/common/_header.jsp"%>
<%@ include file="/pages/common/_footer.jsp"%>
<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
		content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<title>投票结果监控</title>
	<script type="text/javascript"> 
		function resize(){  
	        document.getElementById('content').style.height = $(document.body).height() - 10 +"px";  
	    }    
		$(document).ready(function(){ 
			resize();
			$("#content").attr("src","${biServerUrl}?formlet=vote%2F%5B6295%5D%5B7968%5D%5B7ed3%5D%5B679c%5D%5B76d1%5D%5B63a7%5D.frm&__bypagesize__=false"+
			"&n=${biVoteDeploy.voteNum}&rate=${biVoteDeploy.voteRate}&id=${biVoteDeploy.id}");
		}); 
		
	</script> 
  </head>
  <body style="clear: none;">
  	<div>
		<iframe id="content" width="100%" src=""></iframe>
	</div>
  </body>

</html>