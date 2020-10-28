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
<title>候选人列表</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		投票管理 <span class="c-gray en">&gt;</span> 候选人列表 <a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
		<form action="candidateList.do" method="post">
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
			部门名称：
			<input type="text" name="deptName" id="deptName" placeholder="部门名称" style="width:150px" class="input-text" value="${query.deptName }">
			工号：
			<input type="text" name="jobNum" id="jobNum" placeholder="工号" style="width:150px" class="input-text" value="${query.jobNum }">
			是否有权限:
			<span class="select-box inline">
			<select id="isDelete" name="isDelete" class="select">
				<option value=""></option>
				<option value="0" <c:if test="${query.isDelete == '0'}">selected="selected"</c:if>>有</option>
				<option value="1" <c:if test="${query.isDelete == '1'}">selected="selected"</c:if>>无</option>
			</select>
			</span>
			<button type="submit" class="btn btn-success" id="" name="">
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>
		</form>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="changeIsVote('1')"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					取消权限</a> <a href="javascript:;"
				onclick="changeIsVote('0')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					增加权限</a> <a href="javascript:;"
				onclick="user_exp('批量导入','${ctx}/system/toExpUser.do?url=system/expCandidate.do&fileName=BiVoteCandidateTemplate','100','800')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe645;</i>
					批量导入</a></span>
		</div>
		<div class="mt-20">
			<table class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr>
						<th scope="col" colspan="11">候选人列表</th>
					</tr>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="checkAll" id="checkAll"></th>
						<th>品牌名称</th>
						<th>部门名称</th>
						<th>工号</th>
						<th>姓名</th>
						<th>是否有权限</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dataObj" items="${biVoteCandidates}">
						<tr class="text-c">
							<td><input type="checkbox" id="userId" name="userId" value="${dataObj.id }"></td>
							<td>${dataObj.brandName }</td>
							<td>${dataObj.deptName }</td>
							<td>${dataObj.jobNum }</td>
							<td>${dataObj.name }</td>
							<td>
								<c:if test="${dataObj.isDelete == 1}">无</c:if>
								<c:if test="${dataObj.isDelete == 0}">有</c:if>
							</td>
							<td>
								<a style="text-decoration:none" onClick="user_add('候选人编辑','${ctx}/system/toEditCandidate.do?id=${dataObj.id}','800')" href="javascript:;" title="编辑">
									<i class="Hui-iconfont">&#xe6df;编辑</i>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		$('.table-sort').dataTable({
			"aaSorting": [[ 1, "desc" ]],//默认第几个排序
			"bStateSave": false,//状态保存
			"aoColumnDefs": [
			  {"orderable":false,"aTargets":[0,4]}// 制定列不参与排序
			]
		});
		/*
		 参数解释：
		 title	标题
		 url		请求的url
		 id		需要操作的数据id
		 w		弹出层宽度（缺省调默认值）
		 h		弹出层高度（缺省调默认值）
		 */
		/*管理员-增加*/
		function user_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
		/*管理员-删除*/
		function admin_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				//此处请求后台程序，下方是成功后的前台处理……

				$(obj).parents("tr").remove();
				layer.msg('已删除!', {
					icon : 1,
					time : 1000
				});
			});
		}
		/*管理员-编辑*/
		function admin_edit(title, url, id, w, h) {
			layer_show(title, url, w, h);
		}
		
		/*用户-导入*/
		function user_exp(title, url, id, w, h) {
			layer_show(title, url, w, h);
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
				if(flag == '0'){
					cons = "是否确定增加选中的"+i+"位人员的被投票权限?";
				}
				if(flag == '1'){
					cons = "是否确定取消选中的"+i+"位人员的被投票权限?";
				}
				if( i<= 0){
					layer.msg('尚未选择要操作的数据!', {
						icon : 5,
						time : 1000
					});
					return ;
				}
				layer.confirm(cons,function(){
					$.ajax({
						type : 'POST',
						url : ctx + '/system/addCandidate.do',
						data : {userId:vote,flag:flag},
						dataType : 'json',
						success : function(data) {
							layer.msg(data.msg, {
								icon : 5,
								time : 3000
							});
							location.replace(location.href);
						}
					});
				});
		}
		
	</script>
</body>
</html>