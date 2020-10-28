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
<title>编辑奖项配置</title>
</head>
<body>
	<div class="pd-20">
		<form action="${ctx}/draw/editDraw.do" method="post" class="form form-horizontal" id="form-draw-edit">
			<input type="hidden" id="id" name="id" value="${drawConfig.id }">
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>奖项名称：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${drawConfig.name}" placeholder="奖项名称" id="name" name="name" datatype="*2-16" nullmsg="奖项名称不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>奖项人数：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${drawConfig.drawNum}" placeholder="奖项人数" id="drawNum" name="drawNum" datatype="n" nullmsg="奖项人数不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>每次抽奖人数：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${drawConfig.everyNum}" placeholder="每次抽奖人数" id="everyNum" name="everyNum" datatype="n" nullmsg="每次抽奖人数不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>奖品设置：</label>
				<div class="formControls col-5">
					<textarea rows="" cols="" class="textarea" placeholder="奖品设置" name="drawRemark" id="drawRemark" datatype="*" nullmsg="奖品设置不能为空">${drawConfig.drawRemark}</textarea>
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>排序号：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${drawConfig.orderNum}" placeholder="排序号,按升序排序" id="orderNum" name="orderNum" datatype="n" nullmsg="排序号不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">抽奖配置：</label>
				<div class="formControls col-5">
					<span class="select-box">
						<select name="drawId" id="drawId" class="select">
							<option value=""></option>
							<c:forEach var="obj" items="${biVoteDraws}">
								<option value="${obj.drawId}" <c:if test="${obj.drawId == drawConfig.drawId }">selected="selected"</c:if> >${obj.drawTitle}</option>
							</c:forEach>
						</select>
					</span>
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">图片地址：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${drawConfig.picPath}" placeholder="图片地址" id="picPath" name="picPath">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">开始时间：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text Wdate" placeholder="开始时间" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')||\'%y-%M-%d\'}'})">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">结束时间：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text Wdate" placeholder="结束时间" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d\'}'})">
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