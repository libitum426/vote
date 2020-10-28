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
<title>品牌列表</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		权限管理 <span class="c-gray en">&gt;</span> 品牌列表 <a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
		<form action="brandList.do" method="post">
		<div class="text-c">
			品牌编码：
			<input type="text" name="code" id="code" placeholder="品牌编码" style="width:250px" class="input-text" value="${query.code }">
			品牌名称：
			<input type="text" name="name" id="name" placeholder="品牌名称" style="width:250px" class="input-text" value="${query.name }">
			<button type="submit" class="btn btn-success" id="" name="">
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>
		</form>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> <a href="javascript:;"
				onclick="brand_add('添加品牌','${ctx}/system/toAddBrand.do','800')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					添加品牌</a></span>
		</div>
		<div class="mt-20">
			<table class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr>
						<th scope="col" colspan="8">品牌列表</th>
					</tr>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="checkAll" id="checkAll"></th>
						<th>编码</th>
						<th>名称</th>
						<th>创建人</th>
						<th>创建时间</th>
						<th>更新人</th>
						<th>更新时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dataObj" items="${biVoteBrands}">
						<tr class="text-c">
							<td><input type="checkbox" id="brandId" name="brandId" value="${dataObj.id }"></td>
							<td>${dataObj.code }</td>
							<td>${dataObj.name }</td>
							<td>${dataObj.createdBy }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${dataObj.creationDate}"></fmt:formatDate></td>
							<td>${dataObj.lastUpdatedBy }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${dataObj.lastUpdateDate}"></fmt:formatDate></td>
							<td><a title="编辑" href="javascript:;" onclick="brand_add('编辑品牌','${ctx}/system/toAddBrand.do?id=${dataObj.id}','800')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		$('.table-sort').dataTable({
			"aaSorting": [[ 2, "desc" ]],//默认第几个排序
			"bStateSave": false,//状态保存
			"aoColumnDefs": [
			  {"orderable":false,"aTargets":[0,2]}// 制定列不参与排序
			]
		});
	
		function brand_add(title,url,w,h){
			layer_show(title,url,w,h);
		}
		/*管理员-删除*/
		function del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				//此处请求后台程序，下方是成功后的前台处理……

				$(obj).parents("tr").remove();
				layer.msg('已删除!', {
					icon : 1,
					time : 1000
				});
			});
		}
		
		function datadel(){
			var vote = "";
			var i = 0;
			$("input[name='brandId']:checkbox").each(function() {
				if ($(this).prop("checked")) {
					i++;
					if (vote == "") {
						vote = $(this).val();
					} else {
						vote = vote + "," + $(this).val();
					}
				}
			});
				if( i<= 0){
					layer.msg('尚未选择要操作的数据!', {
						icon : 5,
						time : 1000
					});
					return ;
				}
				layer.confirm("是否删除选中的品牌?",function(){
					$.ajax({
						type : 'POST',
						url : ctx + '/system/deleteBrand.do',
						data : {brandId:vote},
						dataType : 'json',
						success : function(data) {
							layer.msg(data.msg, {
								icon : 1,
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