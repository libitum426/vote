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
<title>中奖名单</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 中奖名单 <a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="expOut();"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe644;</i>
					导出</a></span>
		</div>
		<div class="mt-20">
			<table class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr>
						<th scope="col" colspan="7">中奖名单</th>
					</tr>
					<tr class="text-c">
						<th>品牌名称</th>
						<th>部门名称</th>
						<th>手机号</th>
						<th>姓名</th>
						<th>工号</th>
						<th>中奖等级</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dataObj" items="${biVoteUsers}">
						<tr class="text-c">
							<td>${dataObj.brandName }</td>
							<td>${dataObj.departName }</td>
							<td>${dataObj.code }</td>
							<td>${dataObj.name }</td>
							<td>${dataObj.jobNum }</td>
							<td>${dataObj.drawName }</td>
							<td>
								<c:if test="${dataObj.drawStatus == 0}"><a href="javaScript:void()" onclick="updateDrawResult('${dataObj.drawResultId}');">
									<i class="Hui-iconfont">&#xe605;领奖</i>
								</a></c:if>
								<c:if test="${dataObj.drawStatus != 0}">已领奖</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
	$('.table-sort').dataTable({
		"aaSorting": [[ 5, "asc" ]],//默认第几个排序
		"bStateSave": false,//状态保存
		"aoColumnDefs": [
		  {"orderable":false,"aTargets":[0,3]}// 制定列不参与排序
		]
	});
		//导出Excel
		function expOut(){
			window.location = ctx + "/exp.do";
		}
		
		function updateDrawResult(id){
			alert(id);
			layer.confirm("是否领奖?",function(){
				$.ajax({
					type : 'POST',
					url : ctx + '/updateDrawResult.do',
					data : {drawResultId:id},
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