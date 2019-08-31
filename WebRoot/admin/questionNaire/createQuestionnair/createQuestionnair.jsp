<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css"
	href="/TPM/images/css/inputQust.css">
<script type="text/javascript" src="/TPM/images/js/inputQust.js"></script>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<title>创建问卷</title>
<style>
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
.creatpaper_topbar {
	/* border: 1px solid blue; */
	/* height: 64px; */
	width: 200px;
	position: fixed;
  right: 200px;
	top: 200px;
}

.creatpaper_topbar .creatpaper_topbar_title {
	float: left;
	color: white;
	line-height: 64px;
	margin-left: 60px;
}

.creatpaper_topbar_add {
	display: inline-block;
	float: right;
	background-color: inherit; outline：none;
	margin-left: 50px;
	font-size:12px;
	height: 32px;
	border: solid 2px white; /* #0078c8 */
	margin-top: 16px;
	margin-right: 10px;
	color:white;
    width: 90px;

}
.setQusInfo_close a{
	border-width: 0px; /* 边框宽度 */
		border-radius: 3px; /* 边框半径 */
		background: #49afcd; /* 背景颜色 */
		cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
		outline: none; /* 不显示轮廓线 */
		font-family: Microsoft YaHei; /* 设置字体 */
		color: white; /* 字体颜色 */
}
.creatpaper_topbar_add:hover {
	color: orange;
}
#createQuestionnair,#new_topbar_add{
border-width: 0px; /* 边框宽度 */
	border-radius: 3px; /* 边框半径 */
	background: #49afcd; /* 背景颜色 */
	cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
	outline: none; /* 不显示轮廓线 */
	font-family: Microsoft YaHei; /* 设置字体 */
	color: white; /* 字体颜色 */
}
#createQuestionnair:hover { /* 鼠标移入按钮范围时改变颜色 */
	background: #5599FF;
	}
.centerwapper_creatpaper{
	/* box-shadow: 0 10px 10px #d8d8d8; */
	margin-top: 20px;
	}
</style>
</head>
<body>
	<div class="header">
		<div class="header-left">
			<strong>创建问卷</strong>
		</div>
	</div>
	<div class="main">
		<div class="creatpaper_topbar">
			<input id="new_topbar_add" class="creatpaper_topbar_add" type="button"
				href="javascript:void(0)" onclick="showQUS()" name="" value="添加问题">
				<button class="creatpaper_topbar_add" id="createQuestionnair" style="width:90px;height:32px;margin-top: 50px;">创建问卷</button>
		</div>
		<input id="questionnairTitle" contenteditable="true"
			style="display: block; margin-top: 20px;; margin-left: 50px; border: 1px, solid, blue; width: 800px; height: 28px; font-size: 17px; text-align: center; line-height: 40px;" value="请添加问卷标题"></input>


		<div class="centerwapper_creatpaper" style="border:0px;">
			<div class="content" id="qus_add_content"></div>

		</div>
	</div>

	<div id="setQusInfo" class="setQusInfo">

		<div id="setQusInfo_close" class="setQusInfo_close">
			<a style="float: right; margin-bottom: 5px; text-decoration:none;font-size:15px;line-height: 28px;width:100px;text-align:center;margin-top:8px;margin-right:21px;"
				class="menuExit" href="javascript:void(0)" onclick="hideQUS()">添加完成</a>
			<span id="showmsg" style="color:red;font-size:20px;"></span>
		</div>
		<div id="setQusInfo_load" class="setQusInfo_load"style="width: 952.2; height: 585px;">

			<iframe id="setQusInfo_load_son" name="setQusInfo_load_son"
				src="questionSelect.jsp" width="100%" height="100%" style="height: 579px; border: none;width: 808px;"></iframe>

		</div>
	</div>
	<div id="setFillPropertyBcg" class="setFillPropertyBcg"></div>
</body>
<script>
  $(function(){
	  $("#questionnairTitle").focus();
	  $("#questionnairTitle").select();
  })
  function addQus(questionId)
  {

//       alert($(event.target).parents().prevAll().eq(2).text());//这个东西换成提示，不用点击的那种
      $("#showmsg").html("添加"+questionId+"成功！");
      var qusDiv = document.createElement("div");
      qusDiv.setAttribute("class","addQusDiv");
      var f = document.getElementById('qus_add_content');
      f.appendChild(qusDiv);
      qusDiv.innerHTML = '<button style="margin-left:676px;margin-top:18px;" onclick="rm(this)">不添加以下这道题</button><input  type="hidden" name="questionId" value="'+questionId+'"/>'+
     '<iframe id="my_son" name="my_son"  src="/TPM/QuestionPreview?questionId='+questionId+'"  width="98%" height="100%"></iframe>';
    		 }

  /* 创建问卷，向后台提交数据 */
  $("#createQuestionnair").click(function(){

	  var questionIdArr = "";
      $("input[name='questionId']").each(function(){
    	  questionIdArr+=$(this).val()+",";//添加至数组
      });
      var ansTypeArr="";
      $("iframe[name='my_son']").contents().find(" #ansType option:selected").each(function(){

    	  ansTypeArr+=$(this).val()+",";//添加至数组
    	  })
 	  var title=$("#questionnairTitle").val();

	  $.ajax({
          url:'/TPM/CreateQuestionnairServlet.servlet',
          data:{'qlist':questionIdArr,
        	    'tlist':ansTypeArr,
        	     title :title},
          type:'post',
          success:function(msg){
              if(msg=='success'){
            	  alert('添加成功');
                  console.log('添加成功');
              }else if(msg=='fail'){
            	  alert('添加失败');
                  console.log('添加失败');
              }else{
            	  alert('出现不可预知的错误');
            	  console.log('出现不可预知的错误');
              }
          }
      });
  });

  function rm(self){
	  $(self).parent().toggle();
	  $(self).parent().remove();
  }

  </script>
</html>
