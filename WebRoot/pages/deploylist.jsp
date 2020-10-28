<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/pages/common/head.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>配置列表</title>

<script type="text/javascript">
//页面加载
$(function() {
	// 表格复选框
	$("#checkAll").click(function() {
		$('[name=userId]:checkbox').attr('checked', this.checked);
	});
	// 表格行
	$(".list_table:not(.notop) tr:gt(0) td:not(:first-child):not(:last-child)").bind("click",function(){
		$(this).closest('tr').find(':checkbox').attr("checked", !$(this).closest('tr').find(':checkbox').attr("checked"));
		$(this).closest('tr').find(':radio').attr("checked", true);
		
		var flag = true;
		$('.list_table [name=userId]:checkbox').each(function() {
			if (!this.checked) {
				flag = false;
			}
		});
		$('#checkAll').attr('checked', flag);
	});
});

//跳转至新增页面
function toAddDeploy(){
	window.location.href = ctx + "/toAddDeploy.do";
}

function changeIsVote(flag){
	var vote = "";
	var i = 0;
	$("input[name='userId']:checkbox").each(function() {
		if ($(this).prop("checked")) {
			i++;
			if (vote == "") {
				vote = $(this).val();
			} else {
				vote = vote + "," + $(this).val();
			}
		}
	});
		var cons = "";
		if(flag == '1'){
			cons = "是否确定冻结选中的"+i+"条投票设置?";
		}
		if(flag == '0'){
			cons = "是否确定启用选中的"+i+"条投票设置?";
		}
		if (confirm(cons)) {
			$.ajax({
				type : 'POST',
				url : ctx + '/changeDeploy.do',
				data : {userId:vote,flag:flag},
				dataType : 'json',
				success : function(data) {
					alert(data.msg);
					if (data.success) {
						window.location.href = ctx + "/deployList.do";
					}
				}
			});
		}
}

</script>


</head>

<body>
	<fieldset>
		<legend>搜索项</legend>
		<font color="red">${tip}</font>
		<form action="deployList.do" method="post">
			品牌：<select id="brandCode" name="brandCode">
				<option value=""></option>
				<option value="1010" <c:if test="${query.brandCode == '1010'}">selected="selected"</c:if>>梦洁(MENDALE)</option>
				<option value="1020" <c:if test="${query.brandCode == '1020'}">selected="selected"</c:if>>寐(MINE)</option>
				<option value="1030" <c:if test="${query.brandCode == '1030'}">selected="selected"</c:if>>新材料</option>
				<option value="1040" <c:if test="${query.brandCode == '1040'}">selected="selected"</c:if>>梦洁宝贝</option>
				<option value="1050" <c:if test="${query.brandCode == '1050'}">selected="selected"</c:if>>芭比</option>
				<option value="1060" <c:if test="${query.brandCode == '1060'}">selected="selected"</c:if>>平实美学</option>
				<option value="1070" <c:if test="${query.brandCode == '1070'}">selected="selected"</c:if>>觅（MEE）</option>
				<option value="1080" <c:if test="${query.brandCode == '1080'}">selected="selected"</c:if>>美颂(海外代理)</option>
				<option value="1090" <c:if test="${query.brandCode == '1090'}">selected="selected"</c:if>>丝绸（SICHOU）</option>
				<option value="1100" <c:if test="${query.brandCode == '1100'}">selected="selected"</c:if>>BESELF</option>
			</select>
			是否启用:<select id="isFlag" name="isFlag">
				<option value=""></option>
				<option value="0" <c:if test="${query.isFlag == '0'}">selected="selected"</c:if>>启用</option>
				<option value="1" <c:if test="${query.isFlag == '1'}">selected="selected"</c:if>>冻结</option>
			</select>
			<input type="submit" value="搜索">
			<input type="button" value="新增" onclick="toAddDeploy();">
			<input type="button" value="启用" onclick="changeIsVote('0');">
			<input type="button" value="冻结" onclick="changeIsVote('1');">
			<input type="button" value="返回" onclick="toBack();">
		</form>
	</fieldset>
	<fieldset id="inputs" class="display">
		<legend>配置列表</legend>
		<table>
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll"></th>
					<th>品牌名称</th>
					<th>投票标题</th>
					<th>最小投票数</th>
					<th>最大投票数</th>
					<th>投票开始时间</th>
					<th>投票结束时间</th>
					<th>是否启用</th>
				</tr>
			</thead>
			<tbody id="userListTbody">
				<c:forEach var="dataObj" items="${biVoteDeploys}">
					<tr>
						<td><input type="checkbox" id="userId" name="userId" value="${dataObj.id }"></td>
						<td>${dataObj.brandName }</td>
						<td>${dataObj.voteTitle }</td>
						<td>${dataObj.minVote }</td>
						<td>${dataObj.maxVote }</td>
						<td>${dataObj.startDate }</td>
						<td>${dataObj.endDate }</td>
						<td>
							<c:if test="${dataObj.isFlag == 1}">冻结</c:if>
							<c:if test="${dataObj.isFlag == 0}">启用</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
</body>
</html>