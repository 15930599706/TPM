<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
  <link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
  <script src="//apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
  <script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<title>题列表</title>
<style>
body{
  font-size: 12px;
}


table{
  border: 1px solid #dddddd;
  display: inline-block;
  font-size: 12px;
}
#table_bottom1 {
	margin-top: 5%;
	margin-left: 51%;
}
.list_bottom{
  height:43px;
    background-color: #b1ccea;
}
#tr_first{
	height:32px;
		background-color: #b1ccea;
}
.odd:hover{
  background-color: #FFE7a2;
      color: #B94A48;
}
input[type=button]:hover{
	cursor:pointer;
}
.list_bottom td a:hover{
	cursor:pointer;
}
.huanye:hover{
	color:#0087E8;
}

.odd_block：hover{
background-color: #FFE7a2;
}
</style>
<script>
	$(function(){
		$("button").click(function(){
			if($(this).css("color")=="rgb(176, 176, 176)"){
				alert("该题已经被添加了!");
			}
			else{
			$(this).css("color","#B0B0B0");
			}
		})
	})
	$(function(){
		$(".huanye").click(function(){
			alert($(this).prop("id"));
			$(window.parent.search_for_button_click($(this).prop("id")));

		})

	})


</script>
</head>
<body>
	<div id="table">
		<table id="questionnaire_question" >
			<tr id="tr_first">
				<th>编号</th>
				<th>标题</th>
				<th>题型</th>
				<th>操作</th>
			</tr>
			<tbody>
			<c:set var="i" value="0" />
				<c:forEach var="question" items="${question_list}">
				<c:set var="i" value="${i+1}" />
					<tr class="odd">
						<td class="odd_block" style="text-align: center;">${question.tihao}</td>
						<td class="odd_block" style="width: 244.4; height: 22px; display:inline-block;overflow:hidden;" title="${question.biaoti}">${question.biaoti}</td>
						<td class="odd_block"  style="text-align: center;padding-left: 15px;padding-right: 15px;">${question.leixing}</td>
						<td class="odd_block"><input  type="button" onclick="$(window.parent.parent.addQus( ${question.tihao }))"
						style="float: left;margin-left: 5px;" value="添加">
						</td>
					</tr>
				</c:forEach>
				<c:if test="${i==0}">
					<tr><span style="color:red;">当前题库中没有该指标点对应类型的问题!</span></tr>
				</c:if>
				<tr class="list_bottom">
							<td>${current_page} / ${tail_page}页 <a class="huanye"style="text-decoration: none" id="1" >首页</a> </td>
							<td><a style="text-decoration: none" class="huanye" id="${current_page-1<=0 ?1:current_page-1}">上一页</a>  </td>
							<td><a style="text-decoration: none" class="huanye" id="${current_page+1>=tail_page ? tail_page : current_page+1 }">下一页</a>  </td>
							<td><a style="text-decoration: none" class="huanye" id="${tail_page }">尾页</a> </td>
					</tr>
			</tbody>
		</table>
	</div>
	<!--
	<div id="table_bottom1">
			${current_page} / ${tail_page}页 <a href="user?cp=1">首页</a> <a
			href="user?cp=${cpage-1<=0 ?1:cpage-1}">上一页</a> <a
			href="user?cp=${cpage+1>=tpage ? tpage : cpage+1 }">下一页</a> <a
			href="user?cp=${tpage }">尾页</a>
	</div>
				 -->
	  <script>
  $(function() {
    $( document ).tooltip();
  });
  </script>
</body>
</html>
