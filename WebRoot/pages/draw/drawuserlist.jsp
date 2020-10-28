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
<title>抽奖用户列表</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		投票管理 <span class="c-gray en">&gt;</span> 抽奖用户列表 <a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
		<form action="${ctx }/drawuser/drawUserList.do" method="post">
		<div class="text-c">
			手机号：
			<input type="text" name="phone" id="phone" placeholder="手机号" style="width:150px" class="input-text" value="${query.phone }">
			姓名：
			<input type="text" name="name" id="name" placeholder="姓名" style="width:150px" class="input-text" value="${query.name }">
			是否有权限:
			<span class="select-box inline">
			<select id="isFlag" name="isFlag" class="select">
				<option value=""></option>
				<option value="0" <c:if test="${query.isFlag == '0'}">selected="selected"</c:if>>有</option>
				<option value="1" <c:if test="${query.isFlag == '1'}">selected="selected"</c:if>>无</option>
			</select>
			</span>
			<button type="submit" class="btn btn-success" id="" name="">
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>
		</form>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="changeIsActive('1')"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe631;</i>
					批量禁用</a> <a href="javascript:;"
				onclick="changeIsActive('0')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe601;</i>
					批量启用</a> <a href="javascript:;"
				onclick="draw_add('新增抽奖配置','${ctx}/drawuser/toEditDrawUser.do','800')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					添加</a></span>
		</div>
		<div class="mt-20">
			<table class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr>
						<th scope="col" colspan="9">抽奖用户列表</th>
					</tr>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="checkAll" id="checkAll"></th>
						<th>手机号</th>
						<th>姓名</th>
						<th>抽奖标题</th>
						<th>是否有权限</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dataObj" items="${biVoteDrawUsers}">
						<tr class="text-c">
							<td><input type="checkbox" id="userId" name="userId" value="${dataObj.userId }"></td>
							<td>${dataObj.phone }</td>
							<td>${dataObj.name }</td>
							<td>${dataObj.drawTitle }</td>
							<td>
								<c:if test="${dataObj.isFlag == 1}">无</c:if>
								<c:if test="${dataObj.isFlag == 0}">有</c:if>
							</td>
							<td><c:if test="${dataObj.isFlag == 1}">
								<a style="text-decoration:none" href="javaScript:void()" onclick="operaDrawconfig('${dataObj.userId}','0');">
									<i class="Hui-iconfont">&#xe60e;启用</i>
								</a>
								</c:if>
								<c:if test="${dataObj.isFlag == 0}">
								<a href="javaScript:void()" onclick="operaDrawconfig('${dataObj.userId}','1');">
									<i class="Hui-iconfont">&#xe605;禁用</i>
								</a>
								</c:if>
								<a style="text-decoration:none" onClick="draw_add('抽奖用户编辑','${ctx}/drawuser/toEditDrawUser.do?userId=${dataObj.userId}','800')" href="javascript:;" title="编辑">
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
			"ordering":false,
			"bStateSave": false,//状态保存
			"aoColumnDefs": [
			  {"orderable":false,"aTargets":[1,3]}// 制定列不参与排序
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
		/*奖项配置-增加*/
		function draw_add(title, url, w, h) {
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
		
		function changeIsActive(flag){
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
					cons = "是否确定启动选中的"+i+"个?";
				}
				if(flag == '0'){
					cons = "是否确定禁用选中的"+i+"个?";
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
						url : ctx + '/drawuser/operaDrawUser.do',
						data : {drawId:vote,flag:flag},
						dataType : 'json',
						success : function(data) {
							layer.msg(data.msg, {
								icon : 6,
								time : 2000
							},function(){
								location.replace(location.href);
							});
						}
					});
				});
		}
		
		function operaDrawconfig(id,flag){
			var cons;
			if(flag == 0){
				cons = "是否确认启用？";
			}else{
				cons = "是否确认禁用？";
			}
			layer.confirm(cons,function(){
				$.ajax({
					type : 'POST',
					url : ctx + '/drawuser/operaDrawUser.do',
					data : {drawId:id,flag:flag},
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