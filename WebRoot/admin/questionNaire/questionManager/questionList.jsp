<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>

 <link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
  <script src="//apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
  <script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<title>题列表</title>
<style>
#table_bottom1 {
	margin-top: 5%;


	margin-left: 51%;
}

.delete:hover {
	background-color: #b1ccea;
	cursor: pointer;
}

#questionDiv {
	width: 200px;
	height: 200px;
	background-color: pink;
	/* float:left; */
}

#QuestonMinWin {
	width: 625px;
	height: 390px;
	z-index: 99999;
	border: 2px solid #ccc;
	background-color: #fff;
	position: absolute;
	top: 200px;
	left: 236px;
}

#bottom_righ {
	position: relitive;
}
.list_bottom,#tr_first{
	height:50px;
		background-color: #b1ccea;
}
.odd{
height:22.400px;
}
.odd_block{
background-color:rgb(236,243,251);
}
.list_bottom td a:hover{
	cursor:pointer;
}
.huanye:hover{
	color:#0087E8;
}
#questionnaire_question{
  margin-left: 50px;
  border: 1px solid #dddddd;
 border-collapse:collapse;
 display: inline-block;
 font-size: 12px;
}
#tr_first{
height: 32px;
}
.list_bottom{
  height: 43px;
}

#questionnaire_question tbody th,#questionnaire_question tbody td{
  border-top: 1px solid #dddddd；border-left: 1px solid #dddddd;
}
tr not(:last-of-type):hover{
    color: #B94A48;
    cursor: pointer;
}
</style>
</head>
<body>

	<div id="table">
		<table id="questionnaire_question" >
			<tr id="tr_first">
				<th style="width: 70px;">编号</th>
				<th style="width: 330px;">标题</th>
				<th style="width: 100px;">题型</th>
				<th style="width: 105.8;">操作</th>
			</tr>
			<tbody id="tbody">
				<c:set var="i" value="0" />
				<c:forEach var="question" items="${question_list}">
					<c:set var="i" value="${i+1}" />
					<tr id="del${i}" class="odd">
						<td class="odd_block" style="text-align: center;">${question.tihao}</td>
						<td class="odd_block" style="  border-top: 1px solid #dddddd；border-left: 1px solid #dddddd;width: 330px; height: 22px; display:inline-block;overflow:hidden;" title="${question.biaoti}">${question.biaoti}</td>
						<td class="odd_block"  style="  border-top: 1px solid #dddddd；border-left: 1px solid #dddddd;text-align: center;padding-left: 15px;padding-right: 15px;">${question.leixing}</td>
						<td class="odd_block"  style="border-top: 1px solid #dddddd；border-left: 1px solid #dddddd;"><input class="delete" type="button" onclick="window.parent.showQue(${question.tihao})" style="float: left;margin-left: 5px;" value="查看">
						<input class="delete" id="delete${i}" type="button" onclick="deleteQuestion(${i},${question.tihao})" style="float: left;margin-left: 5px;" value="删除"></td>
							<!-- <td><p class="delete" id="delete${i}"onclick="deleteQuestion(${i},${question.tihao})" style="float: left;">删除</p></td> -->
					</tr>
				</c:forEach>
				<c:if test="${i==0}">
					<tr><span style="color:red;DISPLAY: BLOCK;color:red;margin-left: 50px;">当前题库中没有该指标点对应类型的问题!</span></tr>
				</c:if>
				<tr class="list_bottom">
					<td colspan=4 style="text-align:center;">${current_page} / ${tail_page}页
					<a style="text-decoration: none" class="huanye" id="1">首页</a> &nbsp
					<a style="text-decoration: none" class="huanye" id="${current_page-1<=0 ?1:current_page-1}" >上一页</a>  &nbsp
					<a style="text-decoration: none" class="huanye" id="${current_page+1>=tail_page ? tail_page : current_page+1 }">下一页</a> &nbsp
					<a style="text-decoration: none" class="huanye" id="${tail_page }">尾页</a></td>
				</tr>
			</tbody>
		</table>
	</div>

	<script>
	  $(function() {
		    $( document ).tooltip();
		  });
	$(function(){
		$(".huanye").click(function(){
			//alert($(this).prop("id"));
			//alert('/TPM/QuestionListServlet.servlet?page='+$(this).prop("id")+'&zhibiao='+$("#zhibiao").val()+"&type="+$("#questionClass").val());
			$(window.parent.search_for_button_click($(this).prop("id")));
			// $(this).prop("href",'/TPM/QuestionListServlet.servlet?page='+$(this).prop("id")+'&zhibiao='+$("#zhibiao").val()+"&type="+$("#questionClass").val());

		})

	})
	 function deleteQuestion(i,tihao){

		var td=document.getElementById("tbody");
		var de=document.getElementById("del"+i);
		td.removeChild(de);

		$(function(){
			 $.ajax({
			      type: 'get',
			       url: '/TPM/DelectTiServlet.servlet',
			     // dataType: 'json',
			      data:{
			    	  "queNum":tihao,
			         },
			     success: function (data) {
			    	 alert("删除成功");
			     },
			       error: function(){
			        	alert("error");
			        }
			     })
		})
		}
	</script>
</body>
</html>
