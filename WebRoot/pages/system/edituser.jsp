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
<title>编辑投票人</title>
</head>
<body>
	<div class="pd-20">
		<form action="${ctx}/system/editUser.do" method="post" class="form form-horizontal" id="form-user-edit">
			<input type="hidden" id="id" name="id" value="${biVoteUser.id }">
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>品牌：</label>
				<div class="formControls col-5">
					<span class="select-box">
						<select name="brandCode" id="brandCode" class="select" nullmsg="品牌不能为空" onchange="getBrandName()">
							<option value=""></option>
							<c:forEach var="brand" items="${biVoteBrands}">
								<option value="${brand.code}" <c:if test="${brand.code == biVoteUser.brandCode }">selected="selected"</c:if> >${brand.name}</option>
							</c:forEach>
						</select>
						<input type="hidden" name="brandName" id="brandName" value="${biVoteUser.brandName}">
					</span>
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">部门编码：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${biVoteUser.departCode}" placeholder="部门编码" id="departCode" name="departCode">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>部门名称：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${biVoteUser.departName}" placeholder="部门名称" id="departName" name="departName" datatype="*2-16" nullmsg="部门名称不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>手机号：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${biVoteUser.code}" placeholder="手机号" id="code" name="code" datatype="*2-16" nullmsg="手机号不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>工号：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${biVoteUser.jobNum}" placeholder="工号" id="jobNum" name="jobNum" datatype="*2-16" nullmsg="工号不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>姓名：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${biVoteUser.name}" placeholder="姓名" id="name" name="name" datatype="*2-16" nullmsg="姓名不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>桌位号：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${biVoteUser.lineNum}" placeholder="桌位号" id="lineNum" name="lineNum" datatype="*" nullmsg="桌位号不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>座位号：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${biVoteUser.columnNum}" placeholder="座位号" id="columnNum" name="columnNum" datatype="*" nullmsg="座位号不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
			<div class="col-10 col-offset-2">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
		</form>
		<script type="text/javascript">
			$(function(){
				$("#form-user-edit").Validform({
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
			
			function getBrandName(){
				var brandName = $("#brandCode").find("option:selected").text();
				$("#brandName").val(brandName);
			}
		</script>
	</div>
</body>
</html>