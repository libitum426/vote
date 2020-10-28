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
window.onload=function(){
   document.onmousemove=function(ev){
       var oevent=ev||event;
       var aimg=document.getElementById('wrapper').getElementsByTagName('img');
       var odiv=document.getElementById('wrapper');
       for(var i=0;i<aimg.length;i++){
              var x=aimg[i].offsetLeft+aimg[i].offsetWidth/2;
              var y=aimg[i].offsetTop+odiv.offsetTop+aimg[i].offsetHeight/2;
              var a=x-oevent.clientX;
              var b=y-oevent.clientY;
              var dis=parseInt(Math.sqrt(a*a+b*b));
              var scale=1-dis/200;
            if(scale<0.5){
                scale=0.5;
            }
            aimg[i].width=scale*128;
       }
   }
}

function toggle(targetid,drawName,drawRemark){
    if (document.getElementById){
        target=document.getElementById(targetid);
            if (target.style.display=="block"){
                target.style.display="none";
            } else {
                target.style.display="block";
            }
       //$("#"+targetid).html("<span style='font-size: 60px'>"+drawName+"奖品</span><br><span style='font-size: 60px;color: red;'>"+drawRemark+"</span>");
    }
}
</script>
</head>
<body style="background-image: url('${ctxs}/images/drawtop.jpg');background-size:100% 100%;">
<!-- 
<nav class="breadcrumb">
		<a class="btn btn-primary radius"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:viod();" title="刷新" onclick="drawUser();">中奖名单</a>
</nav>
 -->
<div id="div0" style="display: none;text-align: center;">
	<span style='font-size: 100px;color: #ffff00;'></span><br>
	<span style='font-size: 60px;color: #ffff00;'></span><br>
	<img alt="" src="${ctxs }/images/draw/draw_z.jpg" height="400" style="margin-top: 20Px">
</div>

<div id="div1" style="display: none;text-align: center;">
	<span style='font-size: 100px;color: #ffff00;'></span><br>
	<span style='font-size: 60px;color: #ffff00;'></span><br>
	<img alt="" src="${ctxs }/images/draw/draw_d.jpg" height="400" style="margin-top: 20Px">
</div>

<div id="div2" style="display: none;text-align: center;">
	<span style='font-size: 100px;color: #ffff00;'></span><br>
	<span style='font-size: 60px;color: #ffff00;'></span><br>
	<img alt="" src="${ctxs }/images/draw/draw_1.jpg" height="400" style="margin-top: 20Px">
</div>

<div id="div3" style="display: none;text-align: center;">
	<span style='font-size: 100px;color: #ffff00;'></span><br>
	<span style='font-size: 60px;color: #ffff00;'></span><br>
	<img alt="" src="${ctxs }/images/draw/draw_2.jpg" height="400" style="margin-top: 20Px">
</div>

<div id="div4" style="display: none;text-align: center;">
	<span style='font-size: 100px;color: #ffff00;'></span><br>
	<span style='font-size: 60px;color: #ffff00;'></span><br>
	<img alt="" src="${ctxs }/images/draw/draw_3.jpg" height="400" style="margin-top: 20Px">
</div>

<div id="div5" style="display: none;text-align: center;">
	<span style='font-size: 100px;color: #ffff00;'></span><br>
	<span style='font-size: 60px;color: #ffff00;'></span><br>
	<img alt="" src="${ctxs }/images/draw/draw_x.jpg" height="400" style="margin-top: 20Px">
</div>

<div id="wrapper">
	<c:forEach var="dataObj" items="${drawconfigs}">
		<c:if test="${dataObj.name == '终极大奖' }">
			<a href="#" onmouseover="toggle('div0')" onmouseout="toggle('div0')" onclick="draw_opera('梦洁家纺2016年年度财富大会抽奖','${ctx}/operaMoreDraw.do?drawId=${dataObj.id}');"><img src="${ctxs}/images/${dataObj.picPath}" width="64"></a>
		</c:if>
		<c:if test="${dataObj.name == '大奖' }">
			<a href="#" onmouseover="toggle('div1')" onmouseout="toggle('div1')" onclick="draw_opera('梦洁家纺2016年年度财富大会抽奖','${ctx}/operaMoreDraw.do?drawId=${dataObj.id}');"><img src="${ctxs}/images/${dataObj.picPath}" width="64"></a>
		</c:if>
		<c:if test="${dataObj.name == '一等奖' }">
			<a href="#" onmouseover="toggle('div2')" onmouseout="toggle('div2')" onclick="draw_opera('梦洁家纺2016年年度财富大会抽奖','${ctx}/operaMoreDraw.do?drawId=${dataObj.id}');"><img src="${ctxs}/images/${dataObj.picPath}" width="64"></a>
		</c:if>
		<c:if test="${dataObj.name == '二等奖' }">
			<a href="#" onmouseover="toggle('div3')" onmouseout="toggle('div3')" onclick="draw_opera('梦洁家纺2016年年度财富大会抽奖','${ctx}/operaMoreDraw.do?drawId=${dataObj.id}');"><img src="${ctxs}/images/${dataObj.picPath}" width="64"></a>
		</c:if>
		<c:if test="${dataObj.name == '三等奖' }">
			<a href="#" onmouseover="toggle('div4')" onmouseout="toggle('div4')" onclick="draw_opera('梦洁家纺2016年年度财富大会抽奖','${ctx}/operaMoreDraw.do?drawId=${dataObj.id}');"><img src="${ctxs}/images/${dataObj.picPath}" width="64"></a>
		</c:if>
		<c:if test="${dataObj.name == '幸运奖' }">
			<a href="#" onmouseover="toggle('div5')" onmouseout="toggle('div5')" onclick="draw_opera('梦洁家纺2016年年度财富大会抽奖','${ctx}/operaMoreDraw.do?drawId=${dataObj.id}');"><img src="${ctxs}/images/${dataObj.picPath}" width="64"></a>
		</c:if>
	</c:forEach>
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
				window.location.href="drawConfigList.do";
			}
		});
		layer.full(index);
	}
</script>
</html>