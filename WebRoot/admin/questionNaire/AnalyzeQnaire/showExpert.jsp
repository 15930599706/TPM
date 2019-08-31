<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>专家查看</title>
<style>
	body{font-size: 12px;}
  	.header {
  	height: 30px;
  	width: auto;
  	text-align: center;
  	border-bottom: 1px solid #ddd!important;
  	margin-left: 50px;
  	}
  	.header div {
 	 display: inline-block;
  	border-bottom: 1px solid #F60;
  	}
    .header-left {
  	height: 100%;
  	width: 150px;
  	float: left;
  	line-height: 30px;
  	font-weight: flod;
  	}
   	.header-left strong{
  	font-size: 15px;
  	}
	.sidebar { width: 180px; float: left; padding-left: 35px;}
	.sidebar>ul { list-style: none; padding: 0; margin: 0;}
	.sidebar>ul>li { font-size: 18px; font-weight: 400; padding: 0010px; margin-top: 5px;}
	.sidebar>ul>li>ul {display: block; list-style: none; margin: 5px010px0; padding: 10px0010px; font-size: 14px; }
	.sidebar a { line-height: 1.5;}
	.sidebar a:hover { color: #e74430; cursor:pointer;}
	.sidebar-selected { color: #e74430;}
	#finished_questionnaire{
		margin-top:20px;
	}
	td, th
	{
	text-align:center;
	max-width:450px;
	white-space:nowrap;
	overflow:hidden;
	text-overflow:ellipsis;
	border: 1px solid #dddddd;
	}
	#bottom #bottom_right #questionnaireName #questionnaireName-first{
		max-width:150px;
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
	#questionnaireName-num{
	    width:50px;
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
	#questionnaireName{
	width:300px;
	height:24px;
	}
	#questionnaireName-first,#questionnaireName-num{
	background-color: #b1ccea;
	height:31.4px;
	}
    .list_bottom{
	height:43px;
	background-color: #b1ccea;
    }
    td>a{
	text-decoration:none;
	}
	#finished_questionnaire_list{
	margin-left:50px;
	border:1px solid #dddddd;
	cellspacing:0;
	cellpadding:0;
	border-collapse:collapse;
	}
	.odd,
	.odd-first{
	background-color:#fff;
	}
	.odd:hover{
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
	margin-left:64%;
	}
	#publish_btn{
	margin-left:60%;
	}
	.bottom_right_done{
	min-width:900px;
	width:1000px;
	}

</style>
</head>
<body>


<div class="header">
		<div class="header-left">
			<strong>专家查看问卷
			<c:if test="${QuestionnaireStatus==3 }">
				全部问卷
			</c:if>
			<c:if test="${QuestionnaireStatus==0 }">
				未做问卷
			</c:if>
			<c:if test="${QuestionnaireStatus==1 }">
				已做问卷
			</c:if></strong>
		</div>
	</div>
  		<div id="finished_questionnaire">

		<table id="finished_questionnaire_list">
	   				<tr class="odd-first">
	   				    <th id="questionnaireName-num">序号</th>
	   				    <th id="questionnaireName-num">题目编号</th>
	   					<th id="questionnaireName-first">问卷名称</th>
	   					<th id="questionnaireName-num" onclick="look()">操作</th>
	   				</tr>
	   				<tbody>

    		 <c:forEach var="Questionnaire" items="${ questionnaireList}"  >
	   			<c:set var="i" value="${i+1}" />
	   			<tr class="odd">
	   			    <td>${ i }</td>
	   			    <td>${Questionnaire.ID}</td>
	  				<td id="questionnaireName"><a href="#" style="color:blue;" onclick='window.open("/TPM/ShowQuestionnaireServlet.servlet?QnaireID="+${Questionnaire.ID}+"&status=expert")'>${ Questionnaire.name}</a></td>
	  				<td><a href="#" style="color:blue;" onclick='window.open("/TPM/ShowQuestionnaireServlet.servlet?QnaireID="+${Questionnaire.ID}+"&status=expert")'>查看</a></td>
		    	</tr>
    		</c:forEach>
    		<tr class="list_bottom">
  			<td colspan="4">	${current_page} / ${tail_page}页
				<a href="/TPM/AdminGetQnaireServlet.servlet?QuestionnaireStatus=3&page=1&status=expert">首页</a>
				<a href="/TPM/AdminGetQnaireServlet.servlet?QuestionnaireStatus=3&page=${current_page-1<=0 ?1:current_page-1}&status=expert">上一页</a>
				<a href="/TPM/AdminGetQnaireServlet.servlet?QuestionnaireStatus=3&page=${current_page+1>=tail_page ? tail_page : current_page+1 }&status=expert">下一页</a>
				<a href="/TPM/AdminGetQnaireServlet.servlet?QuestionnaireStatus=3&page=${tail_page }&status=expert">尾页</a>
				</td>
			</tr>
		 	</tbody>
	   	</table>
	   	</div>

</body>
</html>
