<%
	String path = request.getContextPath();
%>

<link rel="stylesheet" type="text/css" href="<%=path%>/statics/css/dataTables.jqueryui.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/statics/css/dataTables.jqueryui.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/statics/css/jquery.dataTables_themeroller.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/statics/css/jquery.dataTables.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/statics/css/jquery.dataTables.min.css"/>


<link href="${ctxs}/admin/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxs}/admin/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${ctxs}/admin/skin/default/skin.css" id="skin" rel="stylesheet" type="text/css" />
<link href="${ctxs}/admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctxs}/admin/iconfont/iconfont.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=path%>/statics/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/statics/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="<%=path%>/statics/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/statics/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=path%>/statics/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctxs}/js/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="${ctxs}/js/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${ctxs}/admin/H-ui.js"></script> 
<script type="text/javascript" src="${ctxs}/admin/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctxs}/js/Validform/5.3.2/Validform.min.js"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript">
var ctx = "<%=path%>";

function toBack(){
	window.location.href = ctx + "/voteList.do";
}
</script>