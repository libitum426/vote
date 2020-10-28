<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/pages/common/meta.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctxs }/admin/lib/html5.js"></script>
<script type="text/javascript" src="${ctxs }/admin/lib/respond.min.js"></script>
<script type="text/javascript" src="${ctxs }/admin/lib/PIE_IE678.js"></script>
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<link href="${ctxs }/admin/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxs }/admin/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${ctxs }/admin/skin/default/skin.css" rel="stylesheet" type="text/css" id="skin" />
<link href="${ctxs }/admin/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${ctxs }/admin/css/style.css" rel="stylesheet" type="text/css" />
<title>梦洁家纺投票系统首页</title>
</head>
<body>

<header class="Hui-header cl"> <a class="Hui-logo l" title="H-ui.admin v2.3" href="/">梦洁家纺投票系统首页</a> <a class="Hui-logo-m l" href="/" title="H-ui.admin">梦洁家纺</a> <span class="Hui-subtitle l">V1.0</span>
	<nav class="mainnav cl" id="Hui-nav">
		<ul>
			<li class="dropDown dropDown_click"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
				<ul class="dropDown-menu radius box-shadow">
					<li><a href="javascript:;" onclick="article_add('添加资讯','article-add.html')"><i class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
					<li><a href="javascript:;" onclick="picture_add('添加资讯','picture-add.html')"><i class="Hui-iconfont">&#xe613;</i> 图片</a></li>
					<li><a href="javascript:;" onclick="product_add('添加资讯','product-add.html')"><i class="Hui-iconfont">&#xe620;</i> 产品</a></li>
					<li><a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
				</ul>
			</li>
		</ul>
	</nav>
	<ul class="Hui-userbar">
		<li>${USER.name}</li>
		<li class="dropDown dropDown_hover"><a href="#" class="dropDown_A">${USER.code} <i class="Hui-iconfont">&#xe6d5;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="#">个人信息</a></li>
				<li><a href="#">切换账户</a></li>
				<li><a href="#" onclick="loginOut()">退出</a></li>
			</ul>
		</li>
		<li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>
		<li id="Hui-skin" class="dropDown right dropDown_hover"><a href="javascript:;" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
				<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
				<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
				<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
				<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
				<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
			</ul>
		</li>
	</ul>
	<a aria-hidden="false" class="Hui-nav-toggle" href="#"></a> </header>


<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
		<dl id="menu-vote">
			<dt>
				<i class="Hui-iconfont">&#xe620;</i> 投票管理<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a _href="system/userList.do" href="javascript:void(0)">投票人列表</a>
					</li>
					<li><a _href="system/deployList.do" href="javascript:void(0)">投票配置列表</a>
					</li>
					<li><a _href="system/candidateList.do" href="javascript:void(0)">候选人列表</a>
					</li>
					<c:if test="${USER.brandCode == null }">
					<li><a _href="drawz/drawzList.do" href="javascript:void(0)">抽奖配置列表</a>
					</li>
					<li><a _href="draw/drawconfigList.do" href="javascript:void(0)">奖项配置列表</a>
					</li>
					<li><a _href="drawuser/drawUserList.do" href="javascript:void(0)">抽奖用户列表</a>
					</li>
					<li><a _href="queryDrawUsers.do" href="javascript:void(0)">中奖名单列表</a>
					</li>
					</c:if>
				</ul>
			</dd>
		</dl>
		<c:if test="${USER.brandCode == null }">
			<dl id="menu-system">
				<dt>
					<i class="Hui-iconfont">&#xe62e;</i> 权限管理<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a _href="system/userList.do" href="javascript:void(0)">管理员列表</a>
						</li>
						<li><a _href="system/brandList.do" href="javascript:void(0)">品牌列表</a>
						</li>
						<li><a _href="system/deptList.do" href="javascript:void(0)">部门列表</a>
						</li>
					</ul>
				</dd>
			</dl>
		</c:if>
	</div>
</aside>
<div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span><em></em></li>
			</ul>
		</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
	</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="${ctx}/pages/myConsole.jsp"></iframe>
		</div>
	</div>
</section>
<script type="text/javascript" src="${ctxs }/admin/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${ctxs }/admin/lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${ctxs }/admin/js/H-ui.js"></script> 
<script type="text/javascript" src="${ctxs }/admin/js/H-ui.admin.js"></script> 
<script type="text/javascript">
/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}

var ctx = '${ctx}';
function loginOut(){
	//location.href = ctx + "/loginout.do";
	layer.confirm("您确定要退出吗？", function(){
		$.ajax({
			type: "POST",
			url : ctx + "/loginout.do",
			//data : {id : id},
			dataType: 'json',
			success : function(data) {
				layer.msg(data.msg,{icon: 6,time:1000},function(){
					location.href = ctx + "/adminLogin.do";
				});
			}
		});
	});
}
</script> 
<script type="text/javascript">
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s)})();
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>