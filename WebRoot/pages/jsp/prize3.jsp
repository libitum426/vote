<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE html>
<html>
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
	<label for="awardListDom">奖项列表</label>
	<br>
	<input type="text" value="" id="awardListDom">
	<br>
	<label for="num">抽到的奖</label>
	<br>
	<input type="text" value="" id="num">
	<br>
	<input type="text" value="0" id="prize3">
	<button id="submit3">开始三等奖抽奖</button>
	<br>
	<input type="text" value="0" id="prize2">
	<button id="submit2">开始二等奖抽奖</button>
	<br>
	<input type="text" value="0" id="prize">
	<button id="submit">开始一等奖抽奖</button>
	<br>
	<br>
	<br>
	<label for="prizeName3">三等奖获奖名单</label>
	<input type="text" id="prizeName3" size="50" />
	<br>
	<br>
	<br>
	<label for="prizeName2">二等奖获奖名单</label>
	<input type="text" id="prizeName2" size="50" />	
	<br>
	<br>
	<br>	
	<label for="prizeName">一等奖获奖名单</label>
	<input type="text" id="prizeName" size="50" />		
		
	<script>
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
		var awardListDom = document.getElementById("awardListDom"), 
		num = document.getElementById("num"), 
		prize = document.getElementById("prize"), 
		prize2 = document.getElementById("prize2"), 
		prize3 = document.getElementById("prize3"), 
		prizeName = document.getElementById("prizeName"), 
		prizeName2 = document.getElementById("prizeName2"), 
		prizeName3 = document.getElementById("prizeName3"), 		
		submit = document.getElementById("submit"),
		submit2 = document.getElementById("submit2"),
		submit3 = document.getElementById("submit3");
		var awardList = [ "杨昊", "刘尹", "刘洋", "曹晓波", "谢苹", "任萍", "伍文全",
				"何洪波", "傅苗", "刘科", "吴小艳", "姚波", "姜中", "张海艳", "李林",
				"王靖元","柳微微" ];

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
				submit.disabled = true;
			}
			prizeName.value = num.value;
		};

		submit2.onclick = function() {
			//引用数组	
			var prizeNum = 3; //二等奖个数
			var oldArray = awardList;
			if(prize2.value >0){
				submit2.disabled = true;
			}else{
				num.value = "";
				for(var i=0;i<prizeNum;i++){
					
					var rNum = random(0, oldArray.length);
		
					if (oldArray.length < 1) {
						awardListDom.value = "活动结束";
						num.value = "活动结束";
					} else {
						num.value = num.value + " " + oldArray[rNum];
						oldArray.splice(rNum, 1);
						awardListDom.value = oldArray;
						prize2.value = prizeNum;
						submit2.disabled = true;
					}
				}
				prizeName2.value = num.value;
			}
		};	
		
		submit3.onclick = function() {
			//引用数组	
			var prizeNum = 6; //三等奖个数
			var oldArray = awardList;
			if(prize3.value >0){
				submit3.disabled = true;
			}else{
				num.value = "";
				for(var i=0;i<prizeNum;i++){
					var rNum = random(0, oldArray.length);
		
					if (oldArray.length < 1) {
						awardListDom.value = "活动结束";
						num.value = "活动结束";
					} else {
						num.value = num.value + " " + oldArray[rNum];
						oldArray.splice(rNum, 1);
						awardListDom.value = oldArray;
						prize3.value = prizeNum;
						submit3.disabled = true;
					}
				}
				prizeName3.value = num.value;
			}
		};			
	</script>
</body>
</html>
