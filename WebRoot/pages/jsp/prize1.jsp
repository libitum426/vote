<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style>
*        { margin:0; padding:0}
body     { font-size:12px;}
.result_xx dt,.result_xx dd { float:left; margin:10px;}
.xname   { height:30px;margin:30px; font-size:20px; font-weight:bold; color:#c50000}
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script>
$(function(){

  var auto;
  var arry; //声明储存姓名的数组；
  function funa(){
    arry=["111","222","333","444","555","666","777","888","999","aaa","bbb","ccc","ddd","eee","fff"];      //定义一个数组，存放名字
 var rand=Math.floor(Math.random()*arry.length);   //随记取得数组的下标
 $(".xname").text(arry[rand]);
 auto=setTimeout(funa,100)
  }
  $(".beginclick").click(function(){
      auto=setTimeout(funa,100)
   return false;
  })
  var aleady;  //存储已经选定的人名
  $(".endclick").click(function(){
      clearTimeout(auto);
   var szname=$(".xname").text();
   var jx=$(".jxsz")[0].options.selectedIndex;    //获取选中的是哪一种奖项
   var nowdd=$(".result_xx").eq(jx).find("dd");
   var mm=nowdd.length;
   
   if(mm==jx+1){
        alert("此奖项人选已定！")    
    }else{         
         $(".result_xx").eq(jx).append("<dd>"+szname+"</dd>");
      arry.splice($.inArray(szname,arry),1);   //删除已经选定的数组元素 
     }  
   return false;
  })
})


</script>
</head>

<body>
    <div class="mt30 ml"> 抽取
        <select class="mr20 jxsz">
            <option>一等奖(1名)</option>
            <option>二等奖(2名)</option>
            <option>三等奖(3名)</option>
        </select>
        <a href="" title=""  class="beginclick mr c_red">begin</a>
        <a href="" title="" class="endclick c_red">end</a>
    </div>
    
    <h2 class="xname"></h2>
    
    <dl class="result_xx clearfix">
        <dt>一等奖得主：</dt>
  
    </dl>
    <dl class="result_xx clearfix">
        <dt>二等奖得主：</dt>

    </dl>
    <dl class="result_xx clearfix">
        <dt>三等奖得主：</dt>

    </dl>



</body>
</html>
