<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <link rel="stylesheet" type="text/css" href="../student/question(1).css">	 -->
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>答题学生</title>
<style>
	#finished_questionnaire{
		margin-left:15%;
		margin-top:5%;
	}
	#bottom #bottom_right #questionnaireName{

		max-width:150px;
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}

	#finished_questionnaire_list{
	margin-left:10%;
	border:2px solid #ccc;
	cellspacing:0;
	cellpadding:0;
	}
	#finished_questionnaire_list td,#finished_questionnaire_list th
	{
	text-align:center;
	width:150px;
	border-right:1px solid #000;
	    max-width:150px;
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
	.odd th{
	background-color:#b1ccea;
	}
	.odd{
	background-color:#ecf3fb;
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

/* add */
.showWindow{
  position: absolute;
  display: none;
  z-index: 1004;
  width: 800px;
  height: 600px;
  left: 150px;
  top:50px;
  background-color: white;
  border-radius: 15px;
}
.closePerp{
  border: 0px;
  width: 22px;
height: 22px;
float: right;
}
.closePerp:hover{
    cursor: help;
}
.setAllBcg{
  z-index: 1003;
  position: absolute;
  width: 100%;
  height:100%;
  top:0%;
  left:0%;
  opacity: .80;
  background: black;
  display: none;
}
.Perp_load{
  height: 534px;
}
#Perp_load_son{
  margin-top: 30px;
  margin-left: 20px;
}
.showDi:hover{
  cursor: Pointer;
  color: #b1ccea;
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
<div class="bottom_right_done">

	   		<div id="finished_questionnaire">
		        <table id="finished_questionnaire_list">
	   				<tr class="odd">
	   					<th id="questionnaireName">学生</th>
	   					<th id="all_choose"> 答题信息</th>
	   				</tr>
	   				<tbody>

    		 <c:forEach var="Questionnaire" items="${StudentList}"  >
	   			<c:set var="i" value="${i+1}" />
	   			<tr class="odd">
	  				<td id="questionnaireName"><span href="#" >${Questionnaire.name}</span></td>
		    		<td class="hoverstyle">
             <%-- <a href="#" onclick='window.open("/TPM/ShowQnaireAnsServlet?QnaireID="+${Questionnaire.ID})'>查看</a> --%>
              <a class="showDi" id='${i}'>查看</a>
              <span style="display:none;">${Questionnaire.ID}</span>
            </td>
		    	</tr>
    		</c:forEach>
			<tr class="list_bottom">
				<td>${current_page} / ${tail_page}页
		<a style="text-decoration: none"href="/TPM/ShowPublishedQnaireStudentServlet?QuestionnaireStatus=3&QnaireID=${QuesnaireId}&page=1">首页</a></td>
		<td><a style="text-decoration: none"href="/TPM/ShowPublishedQnaireStudentServlet?QuestionnaireStatus=3&QnaireID=${QuesnaireId}&page=${current_page-1<=0 ?1:current_page-1}">上一页</a>
		<a style="text-decoration: none" href="/TPM/ShowPublishedQnaireStudentServlet?QuestionnaireStatus=3&QnaireID=${QuesnaireId}&page=${current_page+1>=tail_page ? tail_page : current_page+1 }">下一页</a>
		<a style="text-decoration: none"href="/TPM/ShowPublishedQnaireStudentServlet?QuestionnaireStatus=3&QnaireID=${QuesnaireId}&page=${tail_page }">尾页</a></td>
			
			</tr>
		 	</tbody>
	   	</table>
	   	</div>


	<!-- 	<div id="table_bottom1">
    	${current_page} / ${tail_page}页
	<a href="/TPM/ShowPublishedQnaireStudentServlet?QuestionnaireStatus=3&QnaireID=${QuesnaireId}&page=1">首页</a>
		<a href="/TPM/ShowPublishedQnaireStudentServlet?QuestionnaireStatus=3&QnaireID=${QuesnaireId}&page=${current_page-1<=0 ?1:current_page-1}">上一页</a>
		<a href="/TPM/ShowPublishedQnaireStudentServlet?QuestionnaireStatus=3&QnaireID=${QuesnaireId}&page=${current_page+1>=tail_page ? tail_page : current_page+1 }">下一页</a>
		<a href="/TPM/ShowPublishedQnaireStudentServlet?QuestionnaireStatus=3&QnaireID=${QuesnaireId}&page=${tail_page }">尾页</a>
	</div> -->	   

  <div class="showWindow">
    <div class="paperName" style="display: inline-block;margin-left: 10px;margin-top: 10px;"></div>
    <div id="closePerp" class="closePerp">
              <a style="float:right;margin:5px;text-decoration:none; " class="menuExit" href = "javascript:void(0)">❌</a>
    </div>
    <div id="Perp_load" class="Perp_load">
       <iframe id="Perp_load_son" name="Perp_load_son"   width="720px" height="499px"></iframe>
      <%-- <div id="Perp_load_son" name="Perp_load_son"  style="width:720px;height:499px;" ></div> --%>


    </div>
      <button style="float:right;margin:5px;text-decoration:none;" class="menuNext" onclick="menuNext(${i})">下一份</button>
      <button style="float:right;margin:5px;text-decoration:none;" class="menuPrev" onclick="menuPrev()">上一份</button>

  </div>
    <div  class="setAllBcg"></div>

<script>
  var accessK;
  $(".odd td .showDi").click(function(){
    $(".setAllBcg").css("display","block");
      $(".showWindow").css("display","block");

       $("#Perp_load_son").attr("src","/TPM/ShowQnaireAnsServlet?QnaireId=${QuesnaireId}&QnaireID="+$(this).next().text());
        // $("#Perp_load_son").attr("src","/TPM/ShowQnaireAnsServlet?QnaireID="+$(this).next().text());
      // $.ajax({
      //    type: 'get',
      //    url: '/TPM/ShowQnaireAnsServlet?QnaireID='+$(this).next().text(),
      //    async: true,
      //    dataType: 'json',
      //    data:{
      //      },
      //     beforeSend:function(){
      //   	   alert("1");
      //     },
      //      complete:function(){
      //        alert("2");
      //      },
      //    success: function (result) {
      //    }
      //  })

      	// $.get('/TPM/ShowQnaireAnsServlet?QnaireID='+$(this).next().text(),function(data){
      	// 	$("#Perp_load_son").html(data);//初始化加载界面
      	// });




      accessK = $(this).attr("id");
      $(".paperName").text("第"+accessK+"份答卷");
      // alert(accessK);
  });
  $(".closePerp").click(function(){
    $(".setAllBcg").css("display","none");
      $(".showWindow").css("display","none");
  })
  function menuNext(maxPage){
    if (accessK == maxPage) {
      alert("这是最后一份答卷啦！");
    }
    else {
           var t = parseInt(accessK)+1;
          $("#"+t).click();
    }
  }
  function menuPrev(){
    if (accessK == 1) {
      alert("这就是第一份答卷啦！");
    }
    else {
          var t =  parseInt(accessK)-1;
          $("#"+t).click();
    }
  }
</script>
</body>
</html>
