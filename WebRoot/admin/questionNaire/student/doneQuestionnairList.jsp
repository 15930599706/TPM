<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="">

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

.sidebar {
	width: 180px;
	float: left;
	padding-left: 35px;
}

.sidebar>ul {
	list-style: none;
	padding: 0;
	margin: 0;
}

.sidebar>ul>li {
	font-size: 18px;
	font-weight: 400;
	padding: 0010px;
	margin-top: 5px;
}

.sidebar>ul>li>ul {
	display: block;
	list-style: none;
	margin: 5px010px0;
	padding: 10px0010px;
	font-size: 14px;
}

.sidebar a {
	line-height: 1.5;
}

.sidebar a:hover {
	color: #e74430;
	cursor: pointer;
}

.sidebar-selected {
	color: #e74430;
}

#finished_questionnaire {
	margin-top: 20px;
}

#bottom #bottom_right c{
	max-width: 150px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
#questionnaireName {
height:24px;
}
#questionnaireName-first{
	background-color: #b1ccea;
	height:31.4px;
	}
	.odd:hover{
	background-color: #FFE7a2; 
    color: #B94A48;
	}
#finished_questionnaire_list {
	margin-left: 50px;
	border: 1px solid #dddddd;
	cellspacing: 0;
	cellpadding: 0;
	border-collapse:collapse;
}

#finished_questionnaire_list td, #finished_questionnaire_list th {
	text-align: center;
	width: 150px;
	max-width: 150px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	border: 1px solid #dddddd;
}
.odd:hover{
	background-color:#FFE7a2;
}

.odd th,
.odd-first th {
	background-color: #b1ccea;
}

.odd,
.odd-first {
	background-color: #fff;
}

#table_bottom1 {
	margin-top: 2%;
	margin-left: 54%;
}
.list_bottom{
	height:43px;
	background-color: #b1ccea;
}

</style>
</head>
<body>
	<div class="bottom_right_done">

<div class="header">
		<div class="header-left">
			<strong>
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
					<th id="questionnaireName-first">问卷名称</th>
					<th>问卷状态</th>
					<th>开始时间</th>
					<th>截止时间</th>					
				    <th>操作</th>					
				</tr>
				<tbody>
					<c:forEach var="Questionnaire" items="${questionnaireList}">
						<tr class="odd">
							<td id="questionnaireName">
								<c:if test="${Questionnaire.status==0 }">
									<a href="#" style="text-decoration:none" onclick='window.open("/TPM/ShowQuestionnaireServlet_studo.servlet?QnaireID="+${Questionnaire.ID})'>${Questionnaire.name}</a>
								</c:if> 
								<c:if test="${Questionnaire.status==1 }">
									<span>${ Questionnaire.name}</span>
								</c:if>
							</td>
							<td>
							<c:if test="${Questionnaire.status==0 }">
							<span style="color:red">未做</span>
						    </c:if> 
						    <c:if test="${Questionnaire.status==1 }">
						    <span style="color:green">已做</span>
							</c:if>
							</td>
							<td>${ Questionnaire.starttime}</td>
							<td>${ Questionnaire.endtime}</td>
							<c:if test="${Questionnaire.status==0 }">
							<td><a href="#" style="color:blue;" onclick='window.open("/TPM/ShowQuestionnaireServlet_studo.servlet?QnaireID="+${Questionnaire.ID})'>答题</a></td>
						    </c:if> 
						    <c:if test="${Questionnaire.status==1 }">
							<td><a href="#" style="color:#ccc;">已答</a></td>
						    </c:if> 
						</tr>
					</c:forEach>
					<tr class="list_bottom">
							<td colspan="5">${current_page} / ${tail_page}页 <a style="text-decoration: none" href="GetQuestionnaireServlet.servlet?QuestionnaireStatus=${QuestionnaireStatus}&cpage=1">首页</a>
							<a style="text-decoration: none" href="GetQuestionnaireServlet.servlet?QuestionnaireStatus=${QuestionnaireStatus}&cpage=${current_page-1<=0 ?1:current_page-1}">上一页</a>
							<a style="text-decoration: none" href="GetQuestionnaireServlet.servlet?QuestionnaireStatus=${QuestionnaireStatus}&cpage=${current_page+1>=tail_page ? tail_page : current_page+1 }">下一页</a>
							<a style="text-decoration: none" href="GetQuestionnaireServlet.servlet?QuestionnaireStatus=${QuestionnaireStatus}&cpage=${tail_page }">尾页</a>
							</td> 
					</tr>	
				</tbody>
			</table>
		</div>
	</div>
	

</body>
</html>