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
<title>用户列表</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		投票管理 <span class="c-gray en">&gt;</span> 投票人列表 <a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
		<form action="userList.do" method="post">
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
			<input type="text" name="departName" id="departName" placeholder="部门名称" style="width:150px" class="input-text" value="${query.departName }">
			手机号：
			<input type="text" name="code" id="code" placeholder="手机号" style="width:150px" class="input-text" value="${query.code }">
			工号：
			<input type="text" name="jobNum" id="jobNum" placeholder="工号" style="width:150px" class="input-text" value="${query.jobNum }">
			是否有权限:
			<span class="select-box inline">
			<select id="isVote" name="isVote" class="select">
				<option value=""></option>
				<option value="0" <c:if test="${query.isVote == '0'}">selected="selected"</c:if>>无</option>
				<option value="1" <c:if test="${query.isVote == '1'}">selected="selected"</c:if>>有</option>
			</select>
			</span>
			<button type="submit" class="btn btn-success" id="" name="">
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>
		</form>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
				<a href="javascript:;" onclick="changeIsVote('0')" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					取消权限</a>
				<a href="javascript:;" onclick="changeIsVote('1')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					增加权限</a>
				<a href="javascript:;" onclick="user_exp('批量导入','${ctx}/system/toExpUser.do?url=system/expUsers.do&fileName=BiVoteUserTemplate','100','800')"
					class="btn btn-primary radius"><i class="Hui-iconfont">&#xe645;</i>批量导入</a>
				<a href="javascript:;" onclick="initUserData()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe66c;</i>
					初始化</a>
			</span>
		</div>
		<div class="mt-20">
			<table class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr>
						<th scope="col" colspan="12">投票人列表</th>
					</tr>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="checkAll" id="checkAll"></th>
						<th>编号</th>
						<th>名称</th>
						<th>投票码</th>
						<th>品牌名称</th>
						<th>抽奖权限</th>
						<th>投票权限</th>
						<th>部门名称</th>
						<th>桌位号</th>
						<th>座位号</th>
						<th>是否签到</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dataObj" items="${biVoteUsers}">
						<tr class="text-c">
							<td><input type="checkbox" id="userId" name="userId" value="${dataObj.id }"></td>
							<td>${dataObj.code }</td>
							<td>${dataObj.name }</td>
							<td>${dataObj.password }</td>
							<td>${dataObj.brandName }</td>
							<td>
								<c:if test="${dataObj.isDraw == 0}">有</c:if>
								<c:if test="${dataObj.isDraw == 1}">无</c:if>
							</td>
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
							<td><c:if test="${dataObj.isDelete == 1}">
								<a style="text-decoration:none" href="javaScript:void()" onclick="kaoqin('${dataObj.id}','0');" title="缺勤">
									<i class="Hui-iconfont">&#xe60e;缺勤</i>
								</a>
								</c:if>
								<c:if test="${dataObj.isDelete == 0}">
								<a href="javaScript:void()" onclick="kaoqin('${dataObj.id}','1');">
									<i class="Hui-iconfont">&#xe605;签到</i>
								</a>
								</c:if>
								<a style="text-decoration:none" onClick="user_add('投票人编辑','${ctx}/system/toEditUser.do?userId=${dataObj.id}','800')" href="javascript:;" title="编辑">
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
			"aaSorting": [[ 7, "desc" ]],//默认第几个排序
			"bStateSave": false,//状态保存
			"paging": false, // 禁止分页
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
				if(flag == '1'){
					cons = "是否确定增加选中的"+i+"位人员的投票权限?";
				}
				if(flag == '0'){
					cons = "是否确定取消选中的"+i+"位人员的投票权限?";
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
						url : ctx + '/system/addVote.do',
						data : {userId:vote,flag:flag},
						dataType : 'json',
						success : function(data) {
							layer.msg(data.msg, {
								icon : 5,
								time : 1000
							});
							location.replace(location.href);
						}
					});
				});
		}
		
		function kaoqin(id,flag){
			var cons;
			if(flag == 0){
				cons = "是否确认缺勤？";
			}else{
				cons = "是否确认签到？";
			}
			layer.confirm(cons,function(){
				$.ajax({
					type : 'POST',
					url : ctx + '/system/kaoqing.do',
					data : {userId:id,flag:flag},
					dataType : 'json',
					success : function(data) {
						layer.msg(data.msg, {
							icon : 6,
							time : 1000
						},function(){
							location.replace(location.href);
						});
					}
				});
			});
		}
		
		function initUserData(){
			layer.confirm("确认要初始化投票人数据？",function(){
				$.ajax({
					type : 'POST',
					url : ctx + '/system/initUserData.do',
					dataType : 'json',
					success : function(data) {
						layer.msg(data.msg, {
							icon : 6,
							time : 1000
						},function(){
							location.replace(location.href);
						});
					}
				});
			});
		}
		
	</script>
</body>
</html>