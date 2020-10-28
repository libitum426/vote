<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@ include file="/pages/common/meta.jsp"%>
	<%@ include file="/pages/common/head.jsp"%>
	  <script type="text/javascript"> 
	  	function resize(){  
	        document.getElementById('content').style.height = document.body.clientHeight - 10 +"px";  
	    } 
		$(document).ready(function(){ 
			resize();
			$("#content").attr("src","${biServerUrl}?formlet=vote%2F%5B6295%5D%5B7968%5D%5B7ed3%5D%5B679c%5D%5B516c%5D%5B5e03%5D%5B8868%5D%5B5355%5D.frm&op=write"+
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