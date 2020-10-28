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
<style type="text/css">
#stop {
	display: none
}

#start {
	display: none
}

#closed {
	display: none
}

.a1{
	display:block; 
	width: 100%; 
	float:left; 
	text-a;
	font-size: 70px;
	color: #ffff00
}

.a2{
	display:block; 
	width: 50%; 
	float:left; 
	text-a;
	text-align:center;
	font-size: 30px;
	color: #ffff00
}

.a3{
	display:block; 
	width: 30%; 
	float:left; 
	text-a;
	font-size: 30px;
	color: #ffff00
}

.a4{
	display:block; 
	width: 25%; 
	float:left; 
	text-a;
	font-size: 30px;
	color: #ffff00
}

.r1{
	display:block; 
	width: 100%; 
	float:left; 
	text-a;
	font-size: 60px;
	color: #ffff00
}

.r2{
	display:block; 
	width: 50%; 
	float:left; 
	text-a;
	text-align:left;
	font-size: 30px;
	color: #ffff00
}

.r3{
	display:block; 
	width: 33%; 
	float:left; 
	text-a;
	font-size: 28px;
	text-align:left;
	color: #ffff00
}

.r4{
	display:block; 
	width: 25%; 
	float:left; 
	text-a;
	font-size: 25px;
	text-align:left;
	color: #ffff00
}

</style>
<title>梦洁家纺2016年年度财富大会抽奖页面</title>
<script type="text/javascript">

	$(function() {
		//$("#spanContent").focus();
		window.focus()
		var _gogo;
		var start_btn = $("#start");
		var stop_btn = $("#stop");
		var closed_btn = $("#closed");
		var i = 0;
		var drawId = $("#drawId").val();
		var num;
		var everyNum;
		var drawNum;
		var syNum;
		var flag = true;
		
		$(document).keydown(function(event) {
			if (event.keyCode == 13 && flag) {
				$("#start").click();
			}
			if (event.keyCode == 32 && !flag) {
				$("#stop").click();
			}
			if (event.keyCode == 27) {
				$("#closed").click();
			}
		});

		start_btn.click(function() {
			$.getJSON(ctx + '/getDrawData.do?drawId=' + drawId,function(data) {
				if (data) {
					if(null == data.attributes){
						alert("该奖项已抽完！");
						$("#closed").click();
						return false;
					}
					$("#titelSpan").html("");
					var obj = data.attributes.biVoteUsers;//将JSON字符串转化为对象 
					var len = obj.length;
					num = data.attributes.drawconfig.num;
					everyNum = data.attributes.drawconfig.everyNum;
					drawNum = data.attributes.drawconfig.drawNum;
					syNum = Number(drawNum) - Number(everyNum)*Number(num);
					if(everyNum <= 0){
						everyNum = drawNum;
					}else{
						if(Number(everyNum)*Number(num) >= Number(drawNum)){
							alert("该奖项已抽完！");
							$("#closed").click();
							return false;
						}

						if(num > 0 && Number(everyNum)*Number(num)+Number(everyNum) > Number(drawNum)){
							everyNum = Number(drawNum) - Number(everyNum)*Number(num);
						}
					}
					$("#titelSpan").html("中奖总人数："+drawNum+",中奖剩余人数："+(Number(syNum)>=0?syNum:0));
					if(obj == null || len <= 0){
						alert("抽奖人数不足！");
						$("#closed").click();
						return false;
					}
					_gogo = setInterval(
						function() {
							//var myArray = new Array();
							//var user = obj[0];
							$.post(ctx + "/randomDraw.do",{
								everyNum : everyNum
							},function(resultData){
								$("#divId").empty();
								$("#divId").show();
								//$("#resultId").hide();
								flag = false;
								var html = "";
								var resu = "";
								var sts2 = "";
								var voteUser = resultData.attributes.biVoteUsers;
								for(var j = 0;j<voteUser.length;j++){
									var user = voteUser[j];
									var str = user.code.substr(0, 3) + "新年快乐" + user.code.substr(7, 11);
									var result = user.name+"-"+str;
									
									if(Number(everyNum) <= 5){
										html += '<span class="a1">' + str + '</span>';
										//resu += '<span class="r1">' + result + '</span>';
									}else if(5 < Number(everyNum) && Number(everyNum) <= 20){
										html += '<span class="a2">' + str + '</span>';
										//resu += '<span class="r2">' + result + '</span>';
									}else if(20 < Number(everyNum) && Number(everyNum) <= 30){
										html += '<span class="a3">' + str + '</span>';
										//resu += '<span class="r3">' + result + '</span>';
									}else if(30 < Number(everyNum) && Number(everyNum) <= 40){
										html += '<span class="a4">' + str + '</span>';
										//resu += '<span class="r4">' + result + '</span>';
									}else{
										html += '<span class="a5">' + str + '</span>';
										//resu += '<span class="r5">' + result + '</span>';
									}
									sts2 += "<input type='hidden' name='userId' value='"+user.id+"'>"
								}
								$("#divId").html(html);
								$("#user").html(sts2);
								//$("#resultId").html(resu);
							});
						}, 100); //每隔0.1秒执行一次 
				} else {
					alert("获取抽奖人数失败，请联系管理员!");
				}
			});
		});

		stop_btn.click(function() {
			clearInterval(_gogo);
			flag = true;
			var a = $("input[name='userId']");
			var userIds = "";
			for (var i = 0; i < a.length; i++) {
				if (i == a.length - 1) {
					userIds += a[i].value;
				} else {
					userIds += a[i].value + ',';
				}
			}
			syNum = Number(syNum) - a.length;
			$("#user").empty();
			$("#divId").empty();
			$("#titelSpan").html("中奖总人数："+drawNum+",中奖剩余人数："+(Number(syNum)>=0?syNum:0));
			$.post(ctx + "/updateDrawData.do", {
				userIds : userIds,
				drawId : drawId
			}, function(data) {
				//$("#divId").hide();
				//$("#resultId").show();
				if (data.success) {
					var html = "";
					var obj = data.attributes.biVoteUsers;//将JSON字符串转化为对象 
					var len = obj.length;
					for (var i = 0; i < len; i++) {
						var str = obj[i].code.substr(0, 3) + "新年快乐" + obj[i].code.substr(7, 11);
						var result = obj[i].name+"-"+str;//obj[i].departName+"-"+
						if(len <= 5){
							html += '<span class="r1">' + result + '</span>';
						}else if(5 < len && len <= 20){
							html += '<span class="r2">' + result + '</span>';
						}else if(20 < len && len <= 30){
							html += '<span class="r3">' + result + '</span>';
						}else if(30 < len && len <= 40){
							html += '<span class="r4">' + result + '</span>';
						}else{
							html += '<span class="r5">' + result + '</span>';
						}
					}
					$("#divId").html(html);
				}else{
					alert(data.msg);
					$("#closed").click();
				}
			});
		});

		closed_btn.click(function() {
			parent.location.reload();
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		});
	});
	
	function changeFontSize2(fontSize){ 
		// 获取节点对象 
		var divNode = document.getElementById("divId"); 
		divNode.style.fontSize=fontSize; 
	}
</script>

</head>
<body style="background-image: url('${ctxs}/images/drawtop.jpg');background-size:100% 100%;">
	<span style='font-size: 40px;color: #ffff00;'></span><br>
		<div align="center" style="top: 10px;">
			<span id="spanContent"
				style="font-size: 50px; color:#ffff00; font-family: Tahoma, Arial, '微软雅黑', '黑体', '宋体';">梦洁家纺2016年年度财富大会${drawconfig.name}抽奖</span>
			<br> <span
				style="font-size: 30px; color:#ffff00; font-family: Tahoma, Arial, '微软雅黑', '黑体', '宋体';" id="titelSpan">中奖总人数：${drawconfig.drawNum },中奖剩余人数：${(drawconfig.drawNum - (drawconfig.everyNum * drawconfig.num)) >= 0?(drawconfig.drawNum - (drawconfig.everyNum * drawconfig.num)):0 }</span>
		</div>

	<div align="center">
	
		<input type="hidden" value="${drawconfig.id }" name="drawId" id="drawId">

		<div id="divId" class="result-e">
		
		</div>
		
		<div id="resultId" class="result-e" style="margin-left: 60px;">
		
		</div>



		<div id="user"></div>
	</div>
	
	<div id="divStart">
		<button id="start">开始</button>
	</div>
	<div id="divStop">
		<button id="stop">停止</button>
	</div>
	<div id="divClosed">
		<button id="closed">关闭</button>
	</div>

</body>
</html>