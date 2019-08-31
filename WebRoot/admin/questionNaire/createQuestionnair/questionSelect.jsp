<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">
<title>问题管理</title>

<link rel="stylesheet" type="text/css"
	href="/TPM/images/css/questionManager.css" />
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
  <script src="//apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
  <script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script type="text/javascript">
$(function() {
    $( document ).tooltip();
  });
function returnUel(){
	 var url1='/TPM/QuestionSelectListServlet.servlet?page=1&zhibiao='+$("#zhibiao").val()+"&type="+$("#questionClass").val();
	return url1;
}
function search_for_button_click(page){
	//alert(page);
		 $("#jj_son").prop("src",'/TPM/QuestionSelectListServlet.servlet?page='+page+'&zhibiao='+$("#zhibiao").val()+"&type="+$("#questionClass").val());
		//$("#table_load").load("/TPM/QuestionSelectListServlet.servlet?page=1&zhibiao="+$("#zhibiao").val()+"&type="+$("#questionClass").val());
}
</script>
<style>
body{
	color: #333333;
	font-size: 12px;
	font-family: "microsoft yahei";
}
#zhibiao{
    width: 216px;
}
#search1{
	width: 72px;
height: 27px;
	border-width: 0px; /* 边框宽度 */
		border-radius: 3px; /* 边框半径 */
		background: #49afcd; /* 背景颜色 */
		cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
		outline: none; /* 不显示轮廓线 */
		font-family: Microsoft YaHei; /* 设置字体 */
		color: white; /* 字体颜色 */
}
select{
  padding: 3px 3px;
	font-size: 12px;
}
</style>
</head>
<body style="overflow-x : hidden ;overflow-y: hidden ;  ">
	<div id="bottom_righ"
    style="width: 889px;
    height: 540.7;"
	>
	<!--  	<p id="naire">问题管理</p> -->
		<div id="choice" style="margin-left: 51.238;">
			<ul id="bottom_search">
				<li>
					<p>能力</p> <select id="ability"
					onchange="getzhibiao( $('#ability option:selected').val() )"></select>
					<!--  -->
				</li>
				<li>
					<p>指标点</p> <select id="zhibiao">
				</select>
				</li>
				<li>
					<p>题型</p> <select id="questionClass">
						<option value="无要求">无要求</option>
						<option value="单选题">单项选择题</option>
						<option value="多选题">多项选择题</option>
						<option value="单项填空题">单项填空题</option>
						<option value="多项填空题">多项填空题</option>
						<option value="文件上传题">文件上传题</option>
				</select>
				</li>
				<li><input type="button" onclick="search_for_button_click(1)"
					id="search1" value="查找"></li>
			</ul>
		</div>
		<div id="table_load">
			<iframe id="jj_son" name="jj_son"  src='' width="98%" height="100%" style="border:0px;height: 467.7;width: 727.213;margin-left: 100px;margin-top: 20px;"></iframe>
		</div>
	</div>

</body>
<script type="text/javascript">

		//2、请求分解点方法
		function getzhibiao(ability_id){
			$("#zhibiao").find("option").remove();
		    if(window.XMLHttpRequest){
		      ajax=new XMLHttpRequest();
		    }else if(window.ActiveXObject){
		      ajax=new ActiveXObject();
		    }
		    var url="/TPM/GetZhibiaoServlet.servlet?"+"ID="+ability_id;
		    ajax.onreadystatechange=doGetzhibiao;
		    ajax.open("post",url,true);
		    ajax.send();
		  }
		function doGetzhibiao(){
		    if(ajax.readyState==4 && ajax.status==200){
			     var str=ajax.responseText;
			     var obj = eval('(' + str + ')'); //由JSON字符串转换为JSON对象
			     var select_zhibiao = document.getElementById("zhibiao");
			     for (var i = 0; i < obj.length; i++) {
			          var t = document.createElement("option");
			          var zhibiao_item_str=obj[i].指标分解内容;
			          t.appendChild(document.createTextNode(zhibiao_item_str.substring(0,15)+"..."));
			          t.setAttribute("value",obj[i].指标分解ID);

			          t.setAttribute("title",obj[i].指标分解ID+":"+zhibiao_item_str);
			          select_zhibiao.appendChild(t);
			     }
			}
		}
		//1、直接请求相应的能力
		var request_num=0;
		var ajax;
		function getMajorList(){
			$("#ability").find("option").remove();
		    if(window.XMLHttpRequest){
		      ajax=new XMLHttpRequest();
		    }else if(window.ActiveXObject){
		      ajax=new ActiveXObject();
		    }
		    var url="/TPM/GetAbilityListServlet.servlet?"+"对应系=2001";
		    ajax.onreadystatechange=doGetMajorList;
		    ajax.open("post",url,true);
		    ajax.send();
		  }
		function doGetMajorList(){
		    if(ajax.readyState==4 && ajax.status==200){
			     var str=ajax.responseText;
			     //alert(str);
			     var obj = eval('(' + str + ')'); //由JSON字符串转换为JSON对象
			     var select_ability = document.getElementById("ability");
			     for (var i = 0; i < obj.length; i++) {
			          var t = document.createElement("option");

			          var item_str=obj[i].毕业生应获得的知识和能力名;
			          t.appendChild(document.createTextNode(item_str));
			          t.setAttribute("value",obj[i].毕业生应获得的知识和能力ID);

			          select_ability.appendChild(t);
			     }
			     if(request_num==0){
			    	 request_num=1;
			    	 getzhibiao(1);
			     }
			}
		}
		getMajorList();

</script>
</html>
