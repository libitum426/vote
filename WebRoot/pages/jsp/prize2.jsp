<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>random</title>
<style>
#awardListDom {
	width: 100%;
}
</style>
</head>
<body>
		<table>
			<tr>
				<td>奖项列表</td>
			</tr>
			<tr>
				<td><input type="text" value="" size="150" id="awardListDom" /></td>
			</tr>
			<tr>
				<td>抽到的奖</td>
			</tr>
			<tr>
				<td><input type="text" value="" id="num" /></td>
			</tr>
			<tr>
				<td><input type="button" id="submit" value="开始抽奖" onclick="prize()" /></td>
			</tr>												
		</table>
	<script>
		//防止连续抽取
		var secs = 5;

		function update(num) {
			if (num == secs) {
				document.getElementById("submit").value = "开始抽奖";
				document.getElementById("submit").disabled = false;
			} else {
				var printnr = secs - num;
				document.getElementById("submit").value = "恭喜获奖 (" + printnr + ")";
			}
		}

		function prize() {
			document.getElementById("submit").disabled = true;
			for ( var i = 1; i <= secs; i++) {
				window.setTimeout("update(" + i + ")", i * 1000);
			}
		}

		/* 
		 * 思路：随机抽奖，抽一个奖项便减少一个
		 * Math 对象方法：http://www.w3school.com.cn/jsref/jsref_obj_math.asp
		 *  -random():返回 0 ~ 1 之间的随机数。
		 *  -floor():获取整数
		 * 数组操作：
		 *  - splice(x,y); x:起始位置， y:获取并删除个数
		 */

		function random(min, max) {
			return Math.floor(min + Math.random() * (max - min));
		}
		var awardListDom = document.getElementById("awardListDom"), num = document
				.getElementById("num"), submit = document
				.getElementById("submit");
		var awardList = [ "一等奖", "二等奖", "二等奖", "三等奖", "三等奖", "三等奖", "鼓励奖",
				"鼓励奖", "鼓励奖", "鼓励奖", "谢谢参与", "谢谢参与", "谢谢参与", "谢谢参与", "谢谢参与",
				"谢谢参与" ];

		awardListDom.value = awardList;
		submit.onclick = function() {
			//引用数组
			var oldArray = awardList;
			var rNum = random(0, oldArray.length);

			if (oldArray.length < 1) {
				awardListDom.value = "活动结束";
				num.value = "活动结束";
			} else {
				num.value = oldArray[rNum];
				oldArray.splice(rNum, 1);
				awardListDom.value = oldArray;
			}
			prize();
		}
	</script>
</body>
</html>
