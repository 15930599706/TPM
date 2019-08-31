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
function search_for_button_click(page){
	/* location.replace('QuestionListGetList?abilityId='+$("#able").val()+"&zhibiao="
			+$("#indexPpint").val()+"&type="+$("#questionClass").val()); */
			//alert(page);
			 $("#table_load").prop("src",'/TPM/QuestionListServlet.servlet?page='+page+'&zhibiao='+$("#zhibiao").val()+"&type="+$("#questionClass").val());

			// $("#table_load").load('/TPM/QuestionSelectListServlet.servlet?page='+page+'&zhibiao='+$("#zhibiao").val()+"&type="+$("#questionClass").val());
		//$("#table_load").load("/TPM/QuestionListServlet.servlet?page=1&zhibiao="+$("#zhibiao").val()+"&type="+$("#questionClass").val());
}
</script>
<style>
#body {
	height: 100%;
	width: 100%;
}
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
#bottom_righ {
	position: relitive;

    height: 46.8;

}

#background {
	display: none;
	position: absolute;
	z-index: 999;
	width: 150%;
	height: 150%;
	top: 0%;
	left: 0%;
}

#H_btn {
	width: 50px;
	height: 50px;
}

#QuestionMinWin {
	width: 800px;
	min-width: 800px;
	z-index: 99999;
	border: 2px solid #ccc;
	background-color: #fff;
	position: absolute;
	top: 10px;
	left:50px;
}

#hide_btn {
	display: inline-block;
	width: 100px;
	height: 25px;
	border: 2px solid #ccc;
	background-color: #ecf3fb;
	text-align: center;
	padding: 12px;
	color: blue;
}

#hide_btn:hover {
	border: 2px solid blue;
	background-color: blue;
	color: #fff;
}

#addFill, #fillShuxing, #addMul, #hideMinWin, #addChoice, #update {
	width: 80px;
	height: 36px;
}
#bottom_search{
    height: 57.463;
    margin-bottom: 0px;

    width: 835px;
    margin-top: 20px;

}
#table{
	margin-left:192px;

}
select{
	height:23px;
}
#zhibiao{

    width: 290px;

}
#zhibiao option{
display:inline-block;

	width:100px;
	overflow:hidden;
}

</style>

</head>
<body style="
    width: 966px;
    height: 644px;
    border-bottom-width: 100px;">
		<div class="header">
		<div class="header-left">
			<strong>问题管理</strong>
		</div>
	</div>
	<div id="bottom_righ">
		<div id="choice" position="absolute">
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
		<iframe id="table_load" name="table_load"  src='' style="width: 773px;height: 428px;border:0px;" ></iframe>
	</div>
	<div id="background" style="background-color: rgba(1, 1, 1, 0.8);">
		<div id="QuestionMinWin" style="left: 123px;top: 80px;">
			<div id="QuestionMinWin_span"
				style="margin-left: 10px; margin-top: 10px;"></div>
			<!-- <span id="hide_btn" onclick="hide_MInWin()">关闭</span> -->
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
			          t.appendChild(document.createTextNode(zhibiao_item_str.substring(0,18)+"..."));
			          t.setAttribute("value",obj[i].指标分解ID);

			          t.setAttribute("title",obj[i].指标分解ID+":"+zhibiao_item_str);
			          select_zhibiao.appendChild(t);
			     }
					 $('#search1').trigger("click");
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
			function showQue(i){

			var view=document.getElementById("background");
			view.style.display="block";
			var QuestionMinWin=document.getElementById("QuestionMinWin_span");
			$("#QuestionMinWin").load('/TPM/QuestionShow?questionId='+i);
		}

		 function lookQuestion(i,biaoti,tihao){
			var view=document.getElementById("background");
			view.style.display="block";
			var QuestionMinWin=document.getElementById("QuestionMinWin_span");
			 var ajax;
			function getMajorList1(){
			    if(window.XMLHttpRequest){
			      ajax=new XMLHttpRequest();
			    }else if(window.ActiveXObject){
			      ajax=new ActiveXObject();
			    }
			    var url="/TPM/GetSuoyinServlet.servlet?queNum="+tihao;
			    ajax.onreadystatechange=doGetMajorList1;
			    ajax.open("post",url,true);
			    ajax.send();
			  }
			function doGetMajorList1(){
			    if(ajax.readyState==4 && ajax.status==200){
				     var str=ajax.responseText;
				     alert(str);
				     var obj = eval('(' + str + ')'); //由JSON字符串转换为JSON对象

				     var select_ability = document.getElementById("QuestionMinWin_span");
				          for (var i = 0; i < obj.length; i++) {
				    	  var t = document.createElement("p");
				          t.appendChild(document.createTextNode(obj[i].标题));
				          t.appendChild(document.createTextNode(obj[i].题号));
				          t.appendChild(document.createTextNode(obj[i].提示内容));
				          t.setAttribute("id","tishi");
				          t.setAttribute("value",obj[i].提示内容);
				          select_ability.appendChild(t);
				    	 }

				     }

				}

			getMajorList1();

			 }
		function hide_Min_Window(){
			var del=document.getElementById("QuestionMinWin");
			del.innerHTML="";
			/* var tishi=document.getElementById("tishi");

			del.removeChild(tishi); */
			 var view=document.getElementById("background");
			view.style.display="none";
			}


		function getMulFillNum(){           //获取选项数目并为选项设置id
			  var cap2 = document.getElementsByClassName("cap2");

			  var fNum = cap2.length - 1;//选项数量
			  return fNum;
			}


		function getNum(){           //获取选项数目并为选项设置id
			  var chose_score = document.getElementsByClassName("chose_score");
			  var chose_text = document.getElementsByClassName("chose_text");
			  var chose_Num = chose_score.length;//选项数量
			  //alert(chose_Num);
			  for (var i = 0; i < chose_Num; i++) {
			    chose_score[i].setAttribute("id","chose_score"+(i+1));
			    //alert(chose_score[i].id);
			    chose_text[i].setAttribute("id","chose_text"+(i+1));
			  }
			  return chose_Num;
			}

		function update_single_fill(tihao){
			  var qusType = '单项填空题';
			  var  caption = $("#queName1").text();//标题
			  //alert(caption);
			  var  tipText= $("#queName2").text();//提示内容
			  //alert(tipText);
			  var  ansType = $("#ansType option:selected").val();//是否必答
			  //alert(ansType);
			      $.ajax({

			     type: 'get',
			     url: '/TPM/UpdateQuestionServlet.servlet',
			     contentType: 'application/json;charset=utf-8',
			     data:{
			    	 	'queNum':tihao,
			             'queType':qusType,
			             'caption':caption,
			             'tipText':tipText,
			             'ansType':ansType

			         },
			     success: function (data) { //返回json结果
			         alert("单项填空题修改成功");
			         hide_Min_Window();
			         search_for_button_click();

			     }
			     });
			}

		function update_mult_fill(tihao){
			  var qusType = '多项填空题';

			  var  caption = $("#multFill_wap_qtest_id").text().toString();//标题
			  var  tipText= $("#queName2").text();//提示内容
			  var  ansType = $("#ansType option:selected").val();//是否必答
			 var count = getMulFillNum();
			  var minSize= new Array();
			  var maxSize = new Array();
			  for (var i = 1; i <= count; i++) {
			    minSize[i] = $("#min"+i).text();
			    maxSize[i] = $("#max"+i).text();
			  }
			    $.ajax({
			    type: 'get',
			    url: '/TPM/UpdateQuestionServlet.servlet',
			    traditional: true,
			    contentType: 'application/json;charset=utf-8',
			    data:{
			    	   'queNum':tihao,
			           'queType' : qusType,
			           'caption':caption,
			           'tipText':tipText,
			           'ansType':ansType,
			           'count':count,
			           minSize,
			           maxSize
			       },
			    success: function (data) { //返回json结果
			       alert("多项填空题成功");
			       hide_Min_Window();
			       search_for_button_click();
			    }
			    });
			}

		function update_mult_choose(tihao){
		     var  qusType = '多选题';

		     var  caption = $("#queName1").text();//标题
		    //alert(caption);
		     var  tipText= $("#queName2").text();//提示内容
		     //alert(tipText);
		     var  ansType = $("#ansType option:selected").val();//是否必答
		    // alert(ansType);

		     var chose_Num=getNum();//获取选项数目
		     //alert(chose_Num);
		     var chose_score_array = new Array();
		     var chose_text_array = new Array();
		     for (var i = 1; i <= chose_Num; i++) //获取每个选项的分数 和 内容
		     {
		       chose_score_array[i] = $("#"+"chose_score"+i+" option:selected").val();
		      //alert(chose_score_array[i]);
		      chose_text_array[i] = $("#"+"chose_text"+i).text();
		       //alert(chose_text_array[i]);
		     }


		     $.ajax({
		    type: 'get',
		    url: '/TPM/UpdateQuestionServlet.servlet',
		    contentType: 'application/json;charset=utf-8',
		    traditional: true,
		     data:{
		    	 	 'queNum':tihao,
		             'queType':qusType,
		             'caption':caption,
		             'tipText':tipText,
		             'ansType':ansType,
		                "chose_Num":chose_Num,
		                chose_score_array,
		                chose_text_array
		        },
		    success: function (data) { //返回json结果
		        alert("多项选择题成功");
		        hide_Min_Window();
		        search_for_button_click();
		    }
		    });


		}



		function update_file(tihao){
			  var qusType = '文件上传题';
			  var  caption = $("#queName1").text();//标题
			  //alert(caption);
			  var  tipText= $("#queName2").text();//提示内容
			  //alert(tipText);
			  var  ansType = $("#ansType option:selected").val();//是否必答
			  //alert(ansType);
			      $.ajax({
			     type: 'get',
			     url: '/TPM/UpdateQuestionServlet.servlet',
			     contentType: 'application/json;charset=utf-8',
			     data:{
			    	     'queNum':tihao,
			             'queType':qusType,

			             'caption':caption,
			             'tipText':tipText,
			             'ansType':ansType

			         },
			     success: function (data) { //返回json结果
			         alert("文件上传题成功");
			         hide_Min_Window();
			         search_for_button_click();
			     }
			     });
			}



		function update_single_choose(tihao){


			      var qusType = '单选题';
			      var  caption = $("#queName1").text();//标题
			     //alert(caption);
			      var  tipText= $("#queName2").text();//提示内容
			      //alert(tipText);
			      var  ansType = $("#ansType option:selected").val();//是否必答
			     // alert(ansType);

			      var chose_Num=getNum();//获取选项数目
			      //alert(chose_Num);
			      var chose_score_array = new Array();
			      var chose_text_array = new Array();
			      for (var i = 1; i <= chose_Num; i++) //获取每个选项的分数 和 内容
			      {
			        chose_score_array.push($("#"+"chose_score"+i+" option:selected").val());
			      // alert(chose_score_array[i]);
			       chose_text_array.push($("#"+"chose_text"+i).text());
			        //alert(chose_text_array[i]);
			      }

			      //前端向后台发送数据
			      //获取指标点表头信息专业
			      $.ajax({
			     type: 'get',
			     url: '/TPM/UpdateQuestionServlet.servlet',
			     contentType: 'application/json;charset=utf-8',
			     traditional: true,
			      data:{
			    	  	  'queNum':tihao,
			              'queType':qusType,
			              'caption':caption,
			              'tipText':tipText,
			              'ansType':ansType,
			                "chose_Num":chose_Num,
			                 chose_score_array,
			                 chose_text_array
			         },
			     success: function (data) { //返回json结果
			         alert("单项选择题修改成功");
			         hide_Min_Window();
			         search_for_button_click();
			     }
			     });


		}

</script>
</html>
