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
<title>编辑抽奖配置</title>
</head>
<body>
	<div class="pd-20">
		<form action="${ctx}/drawz/editDrawz.do" method="post" class="form form-horizontal" id="form-draw-edit">
			<input type="hidden" id="drawId" name="drawId" value="${biVoteDraw.drawId }">
			<input type="hidden" id="isFlag" name="isFlag" value="1">
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>抽奖标题：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${biVoteDraw.drawTitle}" placeholder="抽奖标题" id="drawTitle" name="drawTitle" datatype="*2-100" nullmsg="抽奖标题不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>抽奖类型：</label>
				<div class="formControls col-5">
					<select name="drawType" id="drawType" class="select">
						<option value="0" <c:if test="${biVoteDraw.drawType == 0 }">selected="selected"</c:if>>内部</option>
						<option value="1" <c:if test="${biVoteDraw.drawType == 1 }">selected="selected"</c:if>>外部</option>
					</select>
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">开始时间：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text Wdate" placeholder="开始时间" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')||\'%y-%M-%d\'}'})" value="<fmt:formatDate value='${biVoteDraw.startDate }' pattern='yyyy-MM-dd'/>">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">结束时间：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text Wdate" placeholder="结束时间" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d\'}'})" value="<fmt:formatDate value='${biVoteDraw.endDate }' pattern='yyyy-MM-dd'/>">
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