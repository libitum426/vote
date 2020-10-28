<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/pages/common/head.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>配置列表</title>

<script type="text/javascript">
	function toBackDeployList() {
		window.location.href = ctx + "/deployList.do";
	}

	//选择品牌联动
	function selectBrand() {
		$('#brandCode').val($('#brandCode1').val());
		$('#brandName').val($('#brandCode1').find("option:selected").text());
	}
</script>


</head>

<body>
	<form action="<%=path%>/deploy.do" method="post" name="deployFprm"
		id="deployForm">
			品牌:<select id="brandCode1" name="brandCode1"
				onchange="selectBrand();">
				<option value=""></option>
				<option value="1010">梦洁(MENDALE)</option>
				<option value="1020">寐(MINE)</option>
				<option value="1030">新材料</option>
				<option value="1040">梦洁宝贝</option>
				<option value="1050">芭比</option>
				<option value="1060">平实美学</option>
				<option value="1070">觅（MEE）</option>
				<option value="1080">美颂(海外代理)</option>
				<option value="1090">丝绸（SICHOU）</option>
				<option value="1100">BESELF</option>
			</select><span style="color: red">*</span> <input type="hidden" id="brandCode"
				name="brandCode" value=""> <input type="hidden"
				id="brandName" name="brandName" value=""> 标题:<input
				type="text" id="voteTitle" name="voteTitle" value=""><span
				style="color: red">*</span> 最大投票数:<input type="text" id="maxVote"
				name="maxVote" value=""><span style="color: red">*</span>
			最小投票数:<input type="text" id="minVote" name="minVote" value=""><span
				style="color: red">*</span> <br> 投票开始时间:<input type="text"
				name="startDate" value=""> 投票结束时间:<input type="text"
				name="endDate" value=""> 备注:
			<textarea rows="" cols="" name="remark"></textarea>
			<br> <input type="submit" value="保存"><input
				type="button" value="返回" onclick="toBackDeployList();">
	</form>
</body>
</html>