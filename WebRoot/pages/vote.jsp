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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="content-language" content="en">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<title>${deploy.voteTitle}页面</title>
<link rel="stylesheet" href="<%=path%>/statics/css/jquery-labelauty.css">
<link rel="stylesheet" href="<%=path%>/statics/css/FR.css">
<style>
ul {
	list-style-type: none;
}

li {
	display: inline-block;
}

li {
	margin: 0px 10px;
}

input.labelauty+label {
	font: 12px "Microsoft Yahei";
	width: 70px;
	height: 130px;
}

#voteBtn {
	background-color: #e9ebec;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#fddb6f),
		to(#ffb94b));
	background-image: -webkit-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: -moz-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: -ms-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: -o-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: linear-gradient(top, #fddb6f, #ffb94b);
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
	-moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0
		rgba(255, 255, 255, 0.3) inset;
	-webkit-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0
		rgba(255, 255, 255, 0.3) inset;
	box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.3)
		inset;
	border-width: 1px;
	border-style: solid;
	border-color: #d69e31 #e3a037 #d5982d #e3a037;
	float: left;
	height: 35px;
	padding: 1px;
	width: 80px;
	cursor: pointer;
	font: bold 15px Arial, Helvetica;
	color: #8f5a0a;
}

#voteBtn:hover, #submit:focus {
	background-color: #fddb6f;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#ffb94b),
		to(#fddb6f));
	background-image: -webkit-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: -moz-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: -ms-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: -o-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: linear-gradient(top, #ffb94b, #fddb6f);
}

#voteBtn:active {
	outline: none;
	-moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
	-webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
}

#voteBtn::-moz-focus-inner {
	border: none;
}

fieldset {
	border: 0px none;
	padding: 0px;
	margin: 10px;
}
</style>
</head>

<body>
	<div>
	<div style="position: fixed;border-left: 0px solid transparent;padding-left: 10px;background:#FFFFFF;width:100%;text-align: center;">
		<li><input type="button" id="voteBtn" value="提 交"
			onclick="submitVote();">
		</li>
		<br>
		<li>
			<font size="2pt">${deploy.voteTitle}</font>
		</li>
		<br>
		<li>
			<font size="2pt">您的可投票数：${deploy.minVote}-${deploy.maxVote}票</font>
		</li>
		<br>
		<li>
			<font size="2pt"><span id="spanId">您已投:0票;剩余:${deploy.maxVote}票</span></font>
		</li>
	</div>
	<input type="hidden" name="maxVote" id="maxVote"
		value="${deploy.maxVote }">
	<input type="hidden" name="minVote" id="minVote"
		value="${deploy.minVote }">
	<input type="hidden" name="userId" id="userId" value="${voteUser.id}">
	<input type="hidden" name="deployId" id="deployId" value="${deploy.id}">
	<div style="border-top: 110px solid transparent;padding-left: 10px;">
		<c:forEach var="dept" items="${deptNames }">
			<p><font size="3pt">${dept}</font></p>
			<c:forEach var="dataObj" items="${voteCandidates}">
				<c:if test="${dataObj.deptName == dept }">
					<li>
						<input type="checkbox" name="checkbox" data-labelauty='${dataObj.name}<img src="queryEmpPhoto.do?jobNum=${dataObj.jobNum}" style="max-width: 60px;">'
						value="${dataObj.id}" onchange="validateNum(this);">
					</li>
				</c:if>
			</c:forEach>
		</c:forEach>
	</ul>
	</div>
	<script src="<%=path%>/statics/js/FR.js"></script>
	<script src="<%=path%>/statics/js/jquery-1.8.3.js"></script>
	<script src="<%=path%>/statics/js/jquery-1.8.3.min.js"></script>
	<script src="<%=path%>/statics/js/jquery-labelauty.js"></script>
	<script type="text/javascript">
	
		var ctx = "<%=path%>";
		var maxVote = $("#maxVote").val();
		var minVote = $("#minVote").val();
		var vote = new Array();
		$(function() {
			$(':input').labelauty();
		});

		function submitVote() {
			var vote = "";
			var i = 0;
			$("input[type='checkbox']:checkbox").each(function() {
				if ($(this).prop("checked")) {
					i++;

					if (vote == "") {
						vote = $(this).val();
					} else {
						vote = vote + "," + $(this).val();
					}
				}
			});
			if (minVote != 0) {
				if (i < minVote) {
					FR.Msg.alert("提示","您还需选中 " + (minVote - i) + " 票才可以提交!");
					return false;
				}
			}
			if (maxVote != 0) {
				if (i > maxVote) {
					FR.Msg.alert("提示","您需要取消 " + (i - maxVote) + " 票才可以提交!");
					return false;
				}
			}
			var d = {
				userId : $("#userId").val(),
				deployId : $("#deployId").val(),
				candId : vote
			};

			FR.Msg.confirm("提示","是否确定提交投票?",function(value){
				if(value){
					FR.ajax({
						type : 'POST',
						url : ctx + '/toVote.do',
						data : d,
						dataType : 'json',
						success : function(data) {
							FR.Msg.alert("提示",data.msg);
							if (data.success) {
								window.location.href = "thanksVote.do";
							}
						}
					});
				}
			});
			
		}

		function validateNum(obj) {
			$("#spanId").html("");
			var i = 0;
			$("input[type='checkbox']:checkbox").each(function() {
				if ($(this).prop("checked")) {
					i++;
				}
			});
			if (maxVote != 0) {
				if (i > maxVote) {
					FR.Msg.alert("提示","已超过投票数量");
					$("#spanId").html("您已达到要求,可以提交投票;剩余:0票");
					$(obj).removeAttr("checked");
				}else{
					if(i>= minVote){
						$("#spanId").html("您已达到要求,可以提交投票;剩余:"+(maxVote - i)+"票");
					}else{
						$("#spanId").html("您已投:"+i+"票;剩余:"+(maxVote - i)+"票");
					}
				}
			}
		}
	</script>
</body>
</html>