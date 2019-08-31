<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <link rel="stylesheet" type="text/css" href="../student/question(1).css">	 -->
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
body{font-size: 12px;}
  .header {
  	height: 30px;
  	width: auto;
  	text-align: center;
  	border-bottom: 1px solid #ddd!important;
  	margin-left: 50px;
  	/* background-color: #0078c8; */
  }

  .header div {
  	display: inline-block;
  border-bottom: 1px solid #F60;
  }

  .header-left {
  	height: 100%;
  	width: 150px;
  	float: left;
  	/* color: white; */
  	line-height: 30px;

  	font-weight: flod;

  }
  .header-left strong{
  	font-size: 15px;
  }

	#finished_questionnaire{
		margin-top:20px;
	}
	#bottom #bottom_right #questionnaireName{
		max-width:150px;
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
	#finished_questionnaire_list{
	margin-left:50px;
	border:1px solid #dddddd;
	cellspacing:0;
	cellpadding:0;
	}
	#finished_questionnaire_list td,#finished_questionnaire_list th
	{
	text-align:center;
	width:150px;
	border-right:1px solid #dddddd;
  border-top:1px solid #dddddd;

	    max-width:150px;
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
	.odd th{
	background-color:#b1ccea;
	}
	.odd{
	background-color:#ecf3fb;、

	}
  .oooosa .odd:hover{
    cursor: pointer;
    background-color: #FFE7a2;
    color: #B94A48;
  }
	#ending_time,
	#start_time{
	margin-right:30px;
	}
	#publish{
	margin-left:10%;}
	#table_bottom1{
	margin-top:2%;
	margin-left:30%;
	}
	#publish_btn{
	margin-left:60%;
	}
	.bottom_right_done{
	min-width:900px;
	width:1000px;
	}
	.hoverstyle:hover{
	background-color:#FFE7a2;
	}
	.list_bottom{
	height:50px;
		background-color: #b1ccea;
}
</style>
</head>
<body>
  <div class="header">
		<div class="header-left">
			<strong>我的问卷</strong>
		</div>
	</div>
<div class="bottom_right_done">
	    <p id="naire">
			<c:if test="${QuestionnaireStatus==3 }">
				全部问卷
			</c:if>
			<c:if test="${QuestionnaireStatus==0 }">
				未做问卷
			</c:if>
			<c:if test="${QuestionnaireStatus==1 }">
				已做问卷
			</c:if>
		</p>
	   		<div id="finished_questionnaire">

		<table id="finished_questionnaire_list">
	   				<tr class="odd" height="40px;">
	   					<th id="all_choose"> 序号</th>
	   					<th id="questionnaireName">问卷名称</th>
	   					<th id="all_choose"> 做题年级</th>
	   					<th id="all_choose"> 做题时间</th>
	   					<th id="all_choose"> 操作</th>
	   				</tr>
	   				<tbody class="oooosa">

    		 <c:forEach var="Questionnaire" items="${QuestionnaireList}"  >
	   			<c:set var="i" value="${i+1}" />
	   			<tr class="odd" height="35px;">
	   				<td>${i}</td>
	  				<td id="questionnaireName"><span href="#" >${Questionnaire.name}</span></td>
		    		<td>${Questionnaire.grade}</td>
           			<td>${Questionnaire.starttime}</td>
		    		<td class="hoverstyle">
           				 <a href="#" style="text-decoration:none;"onclick='window.open("/TPM/ShowPublishedQnaireStudentServlet?page=1&QnaireID="+${Questionnaire.ID})'>查看问卷做题情况</a>
           			</td>

		    	</tr>
    		</c:forEach>

    		<tr class="list_bottom">
    	<td colspan="5">${current_page} / ${tail_page}页 &nbsp
		<a style="text-decoration: none"href="/TPM/ShowPublishedServlet.servlet?QuestionnaireStatus=3&page=1">首页</a>&nbsp
		<a style="text-decoration: none"href="/TPM/ShowPublishedServlet.servlet?QuestionnaireStatus=3&page=${current_page-1<=0 ?1:current_page-1}">上一页</a>&nbsp
		<a style="text-decoration: none"href="/TPM/ShowPublishedServlet.servlet?QuestionnaireStatus=3&page=${current_page+1>=tail_page ? tail_page : current_page+1 }">下一页</a>&nbsp
		<a style="text-decoration: none"href="/TPM/ShowPublishedServlet.servlet?QuestionnaireStatus=3&page=${tail_page }">尾页</a></td>
			</tr>

		 	</tbody>
	   	</table>
	   	</div>


	<!-- <div id="table_bottom1">
    	${current_page} / ${tail_page}页
	<a href="/TPM/ShowPublishedServlet?QuestionnaireStatus=3&page=1">首页</a>
		<a href="/TPM/ShowPublishedServlet?QuestionnaireStatus=3&page=${current_page-1<=0 ?1:current_page-1}">上一页</a>
		<a href="/TPM/ShowPublishedServlet?QuestionnaireStatus=3&page=${current_page+1>=tail_page ? tail_page : current_page+1 }">下一页</a>
		<a href="/TPM/ShowPublishedServlet?QuestionnaireStatus=3&page=${tail_page }">尾页</a>
	</div> -->

<script>


</script>
</body>
</html>
