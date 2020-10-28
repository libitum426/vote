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
<title>投票配置</title>
</head>

<body>
	<div class="pd-20">
		<form action="${ctx}/system/addDeploy.do" method="post" class="form form-horizontal" id="form-depart-edit">
			<input type="hidden" id="id" name="id" value="${biVoteDeploy.id }">
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>品牌：</label>
				<div class="formControls col-5">
					<span class="select-box">
						<select name="brandCode1" id="brandCode1" class="select" datatype="*2-16" nullmsg="品牌不能为空" onchange="selectBrand();">
							<option value=""></option>
							<c:forEach var="brand" items="${biVoteBrands}">
								<option value="${brand.code}" <c:if test="${brand.code == biVoteDeploy.brandCode }">selected="selected"</c:if> >${brand.name}</option>
							</c:forEach>
						</select>
						<input type="hidden" name="brandCode" id="brandCode" value="${biVoteDeploy.brandCode}">
						<input type="hidden" name="brandName" id="brandName" value="${biVoteDeploy.brandName}">
					</span>
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>标题：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${biVoteDeploy.voteTitle}" placeholder="标题" id="voteTitle" name="voteTitle" <c:if test="${biVoteDepart.id != null }">readonly="readonly"</c:if> datatype="*2-200" nullmsg="标题不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>最大投票数：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${biVoteDeploy.maxVote}" placeholder="最大投票数" id="maxVote" name="maxVote" datatype="*1-20" nullmsg="最大投票数不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>最小投票数：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${biVoteDeploy.minVote}" placeholder="最小投票数" id="minVote" name="minVote" datatype="*1-20" nullmsg="最小投票数不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>投票人数：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${biVoteDeploy.voteNum}" placeholder="投票人数" id="voteNum" name="voteNum" datatype="*1-20" nullmsg="投票人数不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>投票率：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="<fmt:formatNumber type="NUMBER" value="${biVoteDeploy.voteRate}"></fmt:formatNumber>" placeholder="投票率" id="voteRate" name="voteRate" datatype="*1-20" nullmsg="投票率不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>投票开始时间：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="<fmt:formatDate value="${biVoteDeploy.startDate}" pattern="yyyy-MM-dd"/>" onClick="WdatePicker()" placeholder="投票开始时间" id="startDate" name="startDate" datatype="*1-20" nullmsg="投票开始时间不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>投票结束时间：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="<fmt:formatDate value="${biVoteDeploy.endDate}" pattern="yyyy-MM-dd"/>" onClick="WdatePicker()" placeholder="投票结束时间" id="endDate" name="endDate" datatype="*1-20" nullmsg="投票结束时间不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">备注：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${biVoteDeploy.remark}" placeholder="备注" id="remark" name="remark">
				</div>
				<div class="col-4"> </div>
			</div>
			<div class="row cl">
			<div class="col-10 col-offset-2">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		
		$(function(){
			$("#form-depart-edit").Validform({
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
		
		//选择品牌联动
		function selectBrand() {
			$('#brandCode').val($('#brandCode1').val());
			$('#brandName').val($('#brandCode1').find("option:selected").text());
		}
	</script>
</body>
</html>