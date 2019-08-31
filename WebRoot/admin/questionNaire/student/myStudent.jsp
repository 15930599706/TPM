<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="">
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>

<style>
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
	margin-left: 15%;
	margin-top: 5%;
}

#bottom #bottom_right #questionnaireName {
	max-width: 150px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

#finished_questionnaire_list {
	margin-left: 10%;
}

#finished_questionnaire_list td {
	max-width: 150px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
*
/
</style>
</head>
<body>

	<div id="header"></div>
	<div id="bottom">
		<div id="bottom_left">
			<div class="sidebar">
				<ul>
					<li id="student_ul_header">学生问卷管理
						<ul>
							<li id="sidebar-selected"><a onclick="all_questionnaire()"
								class="sidebar-selected">全部 </a></li>
							<li><a onclick="finished_questionnaire()">已做问卷 </a></li>
							<li><a onclick="not_finished_questionnaire()">待做问卷</a></li>
							<li><a onclick="">P3 </a></li>
							<li><a onclick="">P4</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<div id="bottom_right"></div>
	</div>
	<script>
	
	/* function search_for_button_click(){
		/* location.replace('QuestionListGetList?abilityId='+$("#able").val()+"&zhibiao="
				+$("#indexPpint").val()+"&type="+$("#questionClass").val()); */
		/* if($("#questionClass").val().toString()=="无要求"){
			$("#table_load").load("/TPM/QuestionListServlet.servlet?page=1&zhibiao="+$("#zhibiao").val());
		}else{
			$("#table_load").load("/TPM/QuestionListServlet.servlet?page=1&zhibiao="+$("#zhibiao").val()+"&type="+$("#questionClass").val());
		}	
	}  */
	
		 function finished_questionnaire(){
		    $('#bottom_right').load('/TPM/GetQuestionnaireServlet.servlet?QuestionnaireStatus=1')
			
		}
		function not_finished_questionnaire(){
		    $('#bottom_right').load('/TPM/GetQuestionnaireServlet.servlet?QuestionnaireStatus=0')
			
		}
		function all_questionnaire(){
			$('#bottom_right').load('/TPM/GetQuestionnaireServlet.servlet?QuestionnaireStatus=3') 
			
		} 
		
	</script>
</body>
</html>