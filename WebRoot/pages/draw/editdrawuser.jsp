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
<title>编辑抽奖用户</title>
</head>
<body>
	<div class="pd-20">
		<form action="${ctx}/drawuser/editDrawUser.do" method="post" class="form form-horizontal" id="form-draw-edit">
			<input type="hidden" id="userId" name="userId" value="${drawUser.userId }">
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>手机号：</label>
				<div class="formControls col-5">
					<c:if test="${empty drawUser.userId}">
						<input type="text" class="input-text" value="${drawUser.phone}" placeholder="手机号" id="phone" name="phone" datatype="*2-11" nullmsg="手机号不能为空">
					</c:if>
					<c:if test="${not empty drawUser.userId}">
						<input type="text" class="input-text" value="${drawUser.phone}" placeholder="手机号" id="phone" name="phone" disabled="disabled">
					</c:if>
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>姓名：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${drawUser.name}" placeholder="姓名" id="name" name="name" datatype="*2-11" nullmsg="姓名不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">抽奖标题：</label>
				<div class="formControls col-5">
					<span class="select-box">
						<select name="drawId" id="drawId" class="select">
							<option value="">请选择</option>
							<c:forEach var="draw" items="${draws}">
								<option value="${draw.drawId}" <c:if test="${draw.drawId == drawUser.drawId }">selected="selected"</c:if> >${draw.drawTitle}</option>
							</c:forEach>
						</select>
					</span>
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">是否有权限：</label>
				<div class="formControls col-5">
					<span class="select-box">
						<select id="isFlag" name="isFlag" class="select">
							<option value="0" <c:if test="${drawUser.isFlag == '0'}">selected="selected"</c:if>>有</option>
							<option value="1" <c:if test="${drawUser.isFlag == '1'}">selected="selected"</c:if>>无</option>
						</select>
					</span>
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
				$("#form-draw-edit").Validform({
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