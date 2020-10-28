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
  
<style type="text/css">  
body {font:12px "\5B8B\4F53",sans-serif;text-align:center;height: 100%;background-image: url('${ctxs}/images/bg.jpg');background-size:100% 100%; background-repeat:no-repeat}  
.result_box {padding-top:100px;margin:0 auto;width:460px;padding:100px 0;text-align:center;
	filter:alpha(Opacity=80);/*支持 IE 浏览器*/
	-moz-opacity:0.5;/*支持 FireFox 浏览器*/
	opacity: 0.5;/*支持 Chrome, Opera, Safari 等浏览器*/
}  
.result_box #oknum {padding-top:100px;width:460px;color:red;font-size:50pt;font-family:Verdana;text-align:center;border:none;}  
</style>  
<title>抽奖活动</title> 

<script type="text/javascript">  
$(function() {
	//$("#spanContent").focus();
	window.focus();
	var _gogo;
	var start_btn = $("#start");
	var stop_btn = $("#stop");
	var closed_btn = $("#closed");
	var drawId = $("#drawId").val();
	var flag = true;
	var drawCofigId;
	var drawName;
	
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
		drawConfigId = 0;
		$.getJSON(ctx + '/getComeDrawData.do?drawId=' + drawId,function(data) {
			if (data) {
				if(null == data.attributes){
					alert("该奖项已抽完！");
					$("#closed").click();
					return false;
				}
				//$("#titelSpan").html("");
				var obj = data.attributes.results;//将JSON字符串转化为对象 
				var old = data.attributes.oldConfigs;
				var oldLen = old.length;
				var len = obj.length;
				if(len <= 0){
					alert("奖品已抽完,请下次再来！");
					return false;
				}
				_gogo = setInterval(
					function() {
						flag = false;
						var num = GetRnd(0,len);
						var num1 = GetRnd(0,oldLen);
						var name = old[num1].name;
						drawName = obj[num].name;
						drawConfigId = obj[num].id;
						$("#oknum").val(name);
					}, 10); //每隔0.1秒执行一次 
			} else {
				alert("获取抽奖人数失败，请联系管理员!");
			}
		});
	});

	stop_btn.click(function() {
		clearInterval(_gogo);
		$("#oknum").val(drawName);
		flag = true;
		$.post(ctx + "/updateComeDrawData.do", {
			drawId : drawConfigId
		}, function(data) {
			if (data.success) {
				layer.msg("恭喜您中奖,请到领奖台领奖!",{
					icon : 6,
					time : 1000
				});
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

function GetRnd(min,max){     
    return parseInt(Math.random()*(max-min+1));     
} 
</script>  
</head>     
<body>
<div align="center">
	<div class="result_box">
		<input type="text" id="oknum" name="oknum" value="抽奖结果" />
		<input type="hidden" value="${drawId }" name="drawId" id="drawId">
	</div>  
	<div class="button_box">
		<button class="btn" id="start" style="display: none">开始</button>
		<button class="btn" id="stop" style="display: none">停止</button>
		<button class="btn" id="close" style="display: none">关闭</button>
	</div>  
</div>
</body>     
</html>  
 </body>  
</html>