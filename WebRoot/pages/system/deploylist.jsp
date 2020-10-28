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
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>投票配置列表</title>
</head>

<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		投票管理 <span class="c-gray en">&gt;</span> 投票配置列表 <a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
		<form action="deployList.do" method="post">
		<div class="text-c">
			品牌：
			<span class="select-box inline">
			<select id="brandCode" name="brandCode" class="select">
				<option value=""></option>
				<c:forEach var="brand" items="${biVoteBrands}">
					<option value="${brand.code}" <c:if test="${brand.code == query.brandCode }">selected="selected"</c:if> >${brand.name}</option>
				</c:forEach>
			</select>
			</span>
			是否启用:
			<span class="select-box inline">
			<select id="isFlag" name="isFlag" class="select">
				<option value=""></option>
				<option value="0" <c:if test="${query.isFlag == '0'}">selected="selected"</c:if>>启用</option>
				<option value="1" <c:if test="${query.isFlag == '1'}">selected="selected"</c:if>>冻结</option>
			</select>
			</span>
			<button type="submit" class="btn btn-success" id="" name="">
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>
		</form>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
				<a href="javascript:;" onclick="deploy_add('添加投票配置','${ctx}/system/toAddDeploy.do','800');" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe645;</i>
					新增</a> 
				<a href="javascript:;" onclick="changeIsVote('0');" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe600;</i>
					启用</a> 
				<a href="javascript:;" onclick="changeIsVote('1');" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe6e2;</i>
					冻结</a>
			</span>
		</div>
		
		<div class="mt-20">
			<table class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr>
						<th scope="col" colspan="11">投票配置列表</th>
					</tr>
					<tr class="text-c">
						<th><input type="checkbox" id="checkAll"></th>
						<th>品牌名称</th>
						<th>投票标题</th>
						<th>最小投票数</th>
						<th>最大投票数</th>
						<th>投票开始时间</th>
						<th>投票结束时间</th>
						<th>是否启用</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="userListTbody">
					<c:forEach var="dataObj" items="${biVoteDeploys}">
						<tr class="text-c">
							<td><input type="checkbox" id="userId" name="userId" value="${dataObj.id }"></td>
							<td>${dataObj.brandName }</td>
							<td>${dataObj.voteTitle }</td>
							<td>${dataObj.minVote }</td>
							<td>${dataObj.maxVote }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${dataObj.startDate}"></fmt:formatDate></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${dataObj.endDate}"></fmt:formatDate></td>
							<td>
								<c:if test="${dataObj.isFlag == 1}">冻结</c:if>
								<c:if test="${dataObj.isFlag == 0}">启用</c:if>
							</td>
							<td>
								<a title="编辑" href="javascript:;" onclick="deploy_add('编辑投票配置','${ctx}/system/toAddDeploy.do?deployId=${dataObj.id}','800')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
								<a title="投票结果监控" href="${ctx}/result/monitor.do?deployId=${dataObj.id}" class="ml-5" style="text-decoration:none" target="_blank"><i class="Hui-iconfont">&#xe621;</i></a>
								<a title="投票结果公布" href="${ctx}/result/publish.do?deployId=${dataObj.id}" class="ml-5" style="text-decoration:none" target="_blank"><i class="Hui-iconfont">&#xe6cf;</i></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<script type="text/javascript">
		$('.table-sort').dataTable({
			"aaSorting": [[ 7, "desc" ]],//默认第几个排序
			"bStateSave": false,//状态保存
			"aoColumnDefs": [
			  {"orderable":false,"aTargets":[0,4]}// 制定列不参与排序
			]
		});
		
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
		}
		
		function deploy_add(title,url,w,h){
			layer_show(title,url,w,h);
		}
		/*管理员-增加*/
		function user_add() {
			var url = ctx + "/system/toAddDeploy.do";
			layer_show('新增投票信息', url, '100','800');
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
						url : ctx + '/system/changeDeploy.do',
						data : {userId:vote,flag:flag},
						dataType : 'json',
						success : function(data) {
							alert(data.msg);
							if (data.success) {
								window.location.href = ctx + "/system/deployList.do";
							}
						}
					});
				}
		}
	</script>
</body>
</html>