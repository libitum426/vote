<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="/pages/common/meta.jsp"%>
<%@ include file="/pages/common/_header.jsp"%>
<%@ include file="/pages/common/_footer.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>添加品牌</title>
</head>
<body>
	<div class="pd-20">
		<form action="${ctx}/system/addBrand.do" method="post" class="form form-horizontal" id="form-brand-edit">
			<input type="hidden" id="id" name="id" value="${biVoteBrand.id }">
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>品牌编码：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${biVoteBrand.code}" placeholder="品牌编码" id="code" name="code" <c:if test="${biVoteBrand.id != null }">readonly="readonly"</c:if> datatype="*2-16" nullmsg="品牌编码不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>品牌名称：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${biVoteBrand.name}" placeholder="品牌名称" id="name" name="name" datatype="*2-20" nullmsg="品牌名称不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
			<div class="col-10 col-offset-2">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" id="brandSbt">
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
		</form>
		<script type="text/javascript">
			function brand_add(){
				$.ajax({
					type : 'POST',
					url : ctx + '/system/addBrand.do',
					data : $("#form-brand-edit").serialize(),
					dataType : 'json',
					success : function(data) {
						layer.msg(data.msg);
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
						refresh();
					}
				});
			}
			$(function(){
				$("#form-brand-edit").Validform({
					tiptype:2,
					showAllError:true,
					ajaxPost:true,
					callback:function(data){
						layer.msg(data.msg,{
							icon : 6,
							time : 1000
						},function(){
							parent.location.reload();
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
						});
					}
				});
			});
		</script>
	</div>
</body>
</html>