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
	<title></title>
	<script type="text/javascript">
// 模板下载
function downLoad() {
	  window.location.href = ctx + "/template/download.do?fileName=${fileName}.xls";
 }
 
function checkWarehouse(){
	document.getElementById('promptTip').style.display="block";
	document.getElementById('buttonTip').style.display="none";
	return true;
}
	</script>
  </head>
  <body>
	<div class="pd-20">
		<div class="text-c">
	  	  <form class="Huiform" action="${ctx}/${url}" method="post" enctype="multipart/form-data" onsubmit="return checkWarehouse();" id="form-excl-exp">
	  	  	<span class="btn-upload form-group">
				<input class="input-text upload-url" type="text" name="excelName" id="uploadfile-2" readonly  datatype="*" nullmsg="请添加附件！" style="width:200px">
				<a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont">&#xe63e;</i> 浏览文件</a>
				<input type="file" multiple name="excel" class="input-file">
			</span>
	   		<button type="button" class="btn btn-success" onClick="downLoad();"><i class="Hui-iconfont">&#xe641;</i>模板下载</button>
	    	<div style="text-align:center;margin-top:30px;">
	    		<span id="promptTip" style="display: none">正在上传,请勿关闭...</span>
	    		<button type="submit" class="btn btn-success" id="submit" name="submit"><i class="Hui-iconfont">&#xe645;</i>导入</button>
	    		<!-- <span id="buttonTip"><input type="submit" id="submit" name="submit" class="btn_sure2" value="上&nbsp;&nbsp;传"/></span> -->
	    	</div>
	  	  </form>
	  	  <script type="text/javascript">
		  	$(function(){
				$("#form-excl-exp").Validform({
					tiptype:2,
					showAllError:true,
					callback:function(form){
						form[0].submit();
					}
				});
			});
	  	  </script>
		</div>
	</div>
  </body>
</html>