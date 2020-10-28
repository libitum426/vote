<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/pages/common/head.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>用户列表</title>

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

function kaoqing(id){
	if(confirm("是否确认缺勤?")){
		$.ajax({
			type: "POST",
			url : "kaoqing.do",
			data : {userId : id},
			dataType: 'json',
			success : function(data) {
				alert(data.msg);
				if (data.success) {
					window.location.href = ctx + "/userList.do";
				}
			}
		});
	}
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
			cons = "是否确定增加选中的"+i+"位人员的投票权限?";
		}
		if(flag == '0'){
			cons = "是否确定取消选中的"+i+"位人员的投票权限?";
		}
		if (confirm(cons)) {
			$.ajax({
				type : 'POST',
				url : ctx + '/addVote.do',
				data : {userId:vote,flag:flag},
				dataType : 'json',
				success : function(data) {
					alert(data.msg);
					if (data.success) {
						window.location.href = ctx + "/userList.do";
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
		<form action="userList.do" method="post">
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
			部门名称：<select id="departName" name="departName">
				<option value=""></option>
				<c:forEach var="dept" items="${deptNames}">
					<option value="${dept}" <c:if test="${dept == query.departName }">selected="selected"</c:if> >${dept}</option>
				</c:forEach>
			</select>
			桌位号:<input type="text" id="lineNum" name="lineNum" value="${query.lineNum }">
			座位号:<input type="text" id="columnNum" name="columnNum" value="${query.columnNum }">
			是否有权限:<select id="isVote" name="isVote">
				<option value=""></option>
				<option value="0" <c:if test="${query.isVote == '0'}">selected="selected"</c:if>>无</option>
				<option value="1" <c:if test="${query.isVote == '1'}">selected="selected"</c:if>>有</option>
			</select>
			<input type="submit" value="搜索">
			<input type="button" value="设置投票" onclick="changeIsVote('1');">
			<input type="button" value="取消投票" onclick="changeIsVote('0');">
			<input type="button" value="返回" onclick="toBack();">
		</form>
	</fieldset>
	<fieldset id="inputs" class="display">
		<legend>用户列表</legend>
		<table>
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll"></th>
					<th>编号</th>
					<th>名称</th>
					<th>品牌名称</th>
					<th>入职年限</th>
					<th>投票权限</th>
					<th>部门名称</th>
					<th>桌位号</th>
					<th>座位号</th>
					<th>是否签到</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="userListTbody">
				<c:forEach var="dataObj" items="${biVoteUsers}">
					<tr>
						<td><input type="checkbox" id="userId" name="userId" value="${dataObj.id }"></td>
						<td>${dataObj.code }</td>
						<td>${dataObj.name }</td>
						<td>${dataObj.brandName }</td>
						<td>${dataObj.entryDate }</td>
						<td>
							<c:if test="${dataObj.isVote == 1}">有</c:if>
							<c:if test="${dataObj.isVote == 0}">无</c:if>
						</td>
						<td>${dataObj.departName }</td>
						<td>${dataObj.lineNum }</td>
						<td>${dataObj.columnNum }</td>
						<td>
							<c:if test="${dataObj.isDelete == 1}">已到</c:if>
							<c:if test="${dataObj.isDelete == 0}">未到</c:if>
						</td>
						<td><a href="javaScript:void()" onclick="kaoqing('${dataObj.id}');">缺勤</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
</body>
</html>