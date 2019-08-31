<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <link rel="stylesheet" type="text/css" href="../student/question(1).css">	 -->
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
	.sidebar { width: 180px; float: left; padding-left: 35px;}
	.sidebar>ul { list-style: none; padding: 0; margin: 0;}
	.sidebar>ul>li { font-size: 12px; font-weight: 400; padding: 0010px; margin-top: 5px;}
	.sidebar>ul>li>ul {display: block; list-style: none; margin: 5px010px0; padding: 10px0010px; font-size: 12px; }
	.sidebar a { line-height: 1.5;}
	.sidebar a:hover { color: #e74430; cursor:pointer;}
	.sidebar-selected { color: #e74430;}
	#finished_questionnaire{
		margin-top:20px;
	}
	#bottom #bottom_right #questionnaireName{

		max-width:150px;
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
	#questionnaireName:first-child:hover{
	background-color:#b1ccea;
	}
	#questionnaireName:hover{
	background-color:#FFE7a2;
	cursor:pointer;
	}
	#finished_questionnaire_list{
	margin-left:50px;
	border: 1px solid #dddddd;
  border-spacing: 1px;
	}
  table{
    display: inline-block;
  }
	#finished_questionnaire_list td,#finished_questionnaire_list th
	{
	text-align:center;
	width:150px;
	    max-width:150px;
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
  .odd{
    height: 24.2px;
  }
	.odd th{
      background-color: #B1CCEB;

	}
	.odd:nth-child(even){
	background-color:#ecf3fb;
	}
	#ending_time,
	#start_time{
	margin-right:30px;
	}
	#publish{
    margin-left: 50px;
margin-top: 5px;
}
	#table_bottom1{
	margin-top:2%;
	margin-left:30%;
	}
	#publish_btn{
	margin-left:50px;

	}

	.bottom_right_done{
	min-width:900px;
	width:1000px;
	}
.list_bottom{
	height:31.4px;
		background-color: #b1ccea;
}
input[type=checkbox] {
	cursor:pointer;
}
#publish_btn{
  width: 620px;
	height: 40px; /* 高度 */
	border-width: 0px; /* 边框宽度 */
	border-radius: 3px; /* 边框半径 */
	background: #5599FF; /* 背景颜色 */
	cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
	outline: none; /* 不显示轮廓线 */
	font-family: Microsoft YaHei; /* 设置字体 */
	color: white; /* 字体颜色 */
}
#publish_btn:hover { /* 鼠标移入按钮范围时改变颜色 */
	background: #b1ccea;
	}
tr td a{
  text-decoration: none;
}
.odd:not(first-child):hover{
  background-color: #FFE7a2;
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
            			<div id="publish">
		    		    <p id="publishnext" style="display:inline-block">问卷开始时间</p>

			    	 	<input id="start_time" type="datetime-local" style="display:inline-block">

			    		<p id="publishnext" style="display:inline-block">问卷结束时间</p>
			    		<input id="ending_time" type="datetime-local" style="display:inline-block">

				    	<div id="grade"  style="display:inline-block;">
				    		 <p id="publishnext" style="display:inline-block">年级</p>
				    		<select id="student_grade">
				    		 <c:forEach var="grade" items="${ allGrade}"  >

	   			             <option id="${ grade}">${ grade}</option>
    		                 </c:forEach>
				    		</select>
				    	</div>
	  		    		  </div>
	   		<div id="finished_questionnaire">

		<table id="finished_questionnaire_list">
	   				<tr class="odd">
	   					<th id="questionnaireName">问卷名称</th>
	   					<th id="all_choose"> <span style="line-height:27.8px">全选</span><input type="checkbox" name='check' id='check0'></th>
	   					<th>查看</th>
              <th>操作</th>
	   				</tr>
	   				<tbody>

    		 <c:forEach var="Questionnaire" items="${ questionnaireList}"  >
	   			<c:set var="i" value="${i+1}" />
	   			<tr class="odd">
	  				<td id="questionnaireName"><span href="#" onclick='window.open("/TPM/ShowQuestionnaireServlet?QnaireID="+${Questionnaire.ID}+"&status=Admin"
	  				)'>${ Questionnaire.name}</span></td>
		    		<td><input id="check${i}" value="${ Questionnaire.ID}" type="checkbox" name='check'></td>
            	<td><a onclick='window.open("/TPM/ShowQuestionnaireServlet?QnaireID="+${Questionnaire.ID}+"&status=Admin")' style="color:blue;cursor:pointer;">查看</a></td>
		    		<td><a href="javascript:void(0)" onclick="delqn(${Questionnaire.ID})">删除</a></td>
		    	</tr>
    		</c:forEach>

    		<tr  class="list_bottom">
		    	<td colspan=4>${current_page} / ${tail_page}页&nbsp
				<a style="text-decoration: none"href="/TPM/AdminGetQnaireServlet.servlet?QuestionnaireStatus=3&page=1&status=Admin">首页</a>&nbsp
				<a style="text-decoration: none" href="/TPM/AdminGetQnaireServlet.servlet?QuestionnaireStatus=3&page=${current_page-1<=0 ?1:current_page-1}&status=Admin">上一页</a>&nbsp
				<a style="text-decoration: none"href="/TPM/AdminGetQnaireServlet.servlet?QuestionnaireStatus=3&page=${current_page+1>=tail_page ? tail_page : current_page+1 }&status=Admin">下一页</a>&nbsp
				<a style="text-decoration: none"href="/TPM/AdminGetQnaireServlet.servlet?QuestionnaireStatus=3&page=${tail_page }&status=Admin">尾页</a></td>
    		</tr>

		 	</tbody>
	   	</table>
	   	<input type="button" value="发布" id="publish_btn" onclick="getQniareID(); M()">
	   	</div>


	<!-- <div id="table_bottom1">
    	${current_page} / ${tail_page}页
		<a href="/TPM/AdminGetQnaireServlet?QuestionnaireStatus=3&page=1&status=Admin">首页</a>
		<a href="/TPM/AdminGetQnaireServlet?QuestionnaireStatus=3&page=${current_page-1<=0 ?1:current_page-1}&status=Admin">上一页</a>
		<a href="/TPM/AdminGetQnaireServlet?QuestionnaireStatus=3&page=${current_page+1>=tail_page ? tail_page : current_page+1 }&status=Admin">下一页</a>
		<a href="/TPM/AdminGetQnaireServlet?QuestionnaireStatus=3&page=${tail_page }&status=Admin">尾页</a>

	</div> -->

<script>

    function delqn(id){
    	//删除某一个问卷
    	htmlobj=$.ajax({url:"DelQuestionnaire.servlet?id="+id,async:false});
        if (htmlobj.responseText.toString().indexOf("ok")!=-1 ) {
             alert("删除成功！");
             window.location.href="AdminGetQnaireServlet.servlet?QuestionnaireStatus=3&page=1&status=Admin";
        }else{
      	     alert("删除失败!");
        }
    }



    document.getElementById("check0").onclick=function checked_funct(){//全选按钮
	var c0=document.getElementById("check0");
	if(c0.checked==true){
		for(var i=1;i<=${i};i++){
			var all_choose=document.getElementById("all_choose");
			document.getElementById("check"+i).checked=true;
		}
	}else{
		for(var i=1;i<=${i};i++){
			document.getElementById("check"+i).checked=false;
		}
	}
};

function fix(num, length) {
    return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
}

var checked=new Array();

function publish_Questionnaire()
{
    var end = new Date();
    end.setHours(end.getHours() + 1);
    var endTime = end.getFullYear() + "-" + fix((end.getMonth() + 1), 2) + "-" + fix(end.getDate(), 2) +"T" + fix(end.getHours(), 2) + ":" + fix(end.getMinutes(), 2);

    var now = new Date();
    now.setHours(now.getHours()+1);
    var startTime = now.getFullYear() + "-" + fix((now.getMonth() + 1), 2) + "-" + fix(now.getDate(), 2) +"T" + fix(now.getHours(), 2) + ":" + fix(now.getMinutes(), 2);

    document.getElementById("start_time").setAttribute("value",startTime);
    document.getElementById("ending_time").setAttribute("value",endTime);


	var gradehao=$("select").val();


	 $.ajax({
		  traditional: true,
	      type: 'get',
	      url: '/TPM/PublishQnaireServlet.servlet',
	      data:{
	    	  "starttime":startTime,
	    	  "endtime":endTime,
	    	  "gradehao":gradehao,
	    	  checked,
	         },
	     success: function (data) {
	    	 alert("成功");
	    	 checked=[];
	     },
	     error: function(){
	        	alert("error");
	        	checked=[];
	        }
	     }) ;


}


	function getQniareID(){//获取准备发布的问卷ID
		for(var i=1;i<=${i};i++){
			var check=document.getElementById("check"+i);
			if(check.checked==true){
				var s=check.getAttribute("value")
				checked.push(s);
			}
		}
	}
	function CompareDate(d1,d2)
	{
	 /* return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/")))); */
	 s1=new Date(d1);
	 s2=new Date(d2);
	 if(s1.getTime()>s2.getTime()){
		 alert("截止时间比开始时间早，请重新设置");
	 }else{
		 publish_Questionnaire();
	 }

	}

	function M(){//比较两个时间是否符合要求
		var startTime=$("#start_time").val;
	    var endTime = $("#ending_time").val;
		CompareDate(startTime,endTime);
	}

	function f(){//设置时间初始值
		var now = new Date();
	    now.setHours(now.getHours());
	    var startime = now.getFullYear() + "-" + fix((now.getMonth() + 1), 2) + "-" + fix(now.getDate(), 2) +"T" + fix(now.getHours(), 2) + ":" + fix(now.getMinutes(), 2);
	   /*  var end = new Date();
	    end.setHours(now.getHours());
	    var endtime = end.getFullYear() + "-" + fix((end.getMonth() + 1), 2) + "-" + fix(end.getDate(), 2) +"T" + fix(end.getHours(), 2) + ":" + fix(end.getMinutes(), 2); */

	    document.getElementById("start_time").setAttribute("value",startime);
	    document.getElementById("ending_time").setAttribute("value",startime);
	};
	f();




</script>
</body>
</html>
