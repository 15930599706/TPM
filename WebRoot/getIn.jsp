<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath %>/images/css/css.css" />
<link rel="stylesheet" href="<%=basePath %>/images/css/bootstrap.css" />
<script type="text/javascript"
	src="<%=basePath %>images/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/animate.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/loadMask.js"></script>
<style type="text/css">
a {
	display: block;
	height: 20px;
	width: 140px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: blue;
	background: url(<%=basePath %>images/img/46.png) no-repeat 19px 10px;
	text-indent: 40px;
	padding-top: 10px;
	text-align: left;
	font-size: 14px;
}

a:hover {
	text-decoration: underline;
}

#b1 a {
	display: block;
	height: 20px;
	width: 140px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: blue;
	background: url(<%=basePath %>/images/img/46.png) no-repeat 19px 10px;
	text-indent: 40px;
	text-align: left;
	font-size: 14px;
}

#b1 a:hover {
	text-decoration: underline;
}

#box1 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	width: 100px;
	height: 20px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 100px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath %>/images/img/add-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box1 {
	height: 20px;
	float: left;
	width: 100px;
	padding-top: 65px;
}

#box3 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	width: 110px;
	height: 20px;
}

#box3 a {
	display: block;
	height: 20px;
	width: 110px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath %>/images/img/add-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box3 {
	height: 20px;
	float: left;
	width: 110px;
	padding-top: 65px;
}

#box2 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	width: 130px;
	height: 20px;
}

#box2 a {
	display: block;
	height: 20px;
	width: 130px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath %>/images/img/add-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box2 {
	height: 20px;
	float: left;
	width: 200px;
	padding-top: 65px;
}
</style>

</head>
<script language="javascript">
		function createXMLHttpRequest() {
			var ajax = null;
			try {
				ajax = new ActiveXObject("microsoft.xmlhttp");
			} catch(e1) {
				try {
					ajax = new XMLHttpRequest();
				} catch(e2) {
					alert("你的浏览器不支持ajax，请更换浏览器");
				}
			}
			return ajax;
		}

		function xueyuanSelect() {
			//清空学院下拉框
			var departmentElement = document.getElementById("xi");
			departmentElement.options.length = 1;
			var xueyuanid = document.getElementById("xueyuan").value;
			if(xueyuanid != "-1") {
				//NO1)
				var ajax = createXMLHttpRequest();
				//NO2)
				var method = "POST";
				var url = "${pageContext.request.contextPath}/link_findDepartment";
				ajax.open(method, url);
				//NO3)
				ajax.setRequestHeader("content-type", "application/x-www-form-urlencoded");
				//NO4)
				var id = "id=" + xueyuanid;
				ajax.send(id);
				//-------------------------------------------等待				
				//NO5				
				ajax.onreadystatechange = function() {
					if(ajax.readyState == 4) {
						if(ajax.status == 200) {
							//NO6)返回JAVA格式的JSON文本
							var jsonJAVA = ajax.responseText;
							//p所代表的是java格式的json文本，是不能直接被js执行的
							//解决方案：将java格式的json文本，转成js格式的json文本
							//如何做：用js提供的一个函数搞定							
							var jsonJS = eval("(" + jsonJAVA + ")");
							var array = jsonJS.departList;
							var size = array.length;
							for(var i = 0; i < size; i++) {
								var depart = array[i];
								var str = depart.split(" ");
								var option = document.createElement("option");
								option.value = str[0];
								option.innerHTML = str[1];
								departmentElement.appendChild(option);

							}
						}
					}
				}
			}
		}

		function selectSubmit() {
			var xiid = document.getElementById("xi").value;
			if(xiid == "-1") //限定有选择时才会提交表单
			{
				alert("请选择专业");
			} else {
				var form = document.getElementById("totalCredit");
				if(form.score.value.trim() == "") {
					alert("请输入学分");
				} else {
					form.action = "${pageContext.request.contextPath}/scoreThreshold_setTotalCredit.action";
					form.submit();
				}
			}

		}

		function weekHourSubmit() {
			var form = document.getElementById("avePer");
			if(form.weekHour.value.trim() == "") {
				alert("请输入平均周学时阈值");
			} else {
				form.action = "${pageContext.request.contextPath}/scoreThreshold_setAvePerThreshold.action";
				form.submit();
			}
		}
		
		
		
		function xueyuanSelect_delete() {
			//清空学院下拉框
			var departmentElement = document.getElementById("xi_delete");
			departmentElement.options.length = 1;
			var xueyuanid = document.getElementById("xueyuan_delete").value;
			if(xueyuanid != "-1") {
				//NO1)
				var ajax = createXMLHttpRequest();
				//NO2)
				var method = "POST";
				var url = "${pageContext.request.contextPath}/link_findDepartment";
				ajax.open(method, url);
				//NO3)
				ajax.setRequestHeader("content-type", "application/x-www-form-urlencoded");
				//NO4)
				var id = "id=" + xueyuanid;
				ajax.send(id);
				//-------------------------------------------等待				
				//NO5				
				ajax.onreadystatechange = function() {
					if(ajax.readyState == 4) {
						if(ajax.status == 200) {
							//NO6)返回JAVA格式的JSON文本
							var jsonJAVA = ajax.responseText;
							//p所代表的是java格式的json文本，是不能直接被js执行的
							//解决方案：将java格式的json文本，转成js格式的json文本
							//如何做：用js提供的一个函数搞定							
							var jsonJS = eval("(" + jsonJAVA + ")");
							var array = jsonJS.departList;
							var size = array.length;
							for(var i = 0; i < size; i++) {
								var depart = array[i];
								var str = depart.split(" ");
								var option = document.createElement("option");
								option.value = str[0];
								option.innerHTML = str[1];
								departmentElement.appendChild(option);

							}
						}
					}
				}
			}
		}
		
		function zhuanyeSelect(){		//用于加载三级联动
		$("#zhuanyefangxiang").attr("disabled",false);
        //清空专业方向下拉框		
		var professionalElement = document.getElementById("zhuanyefangxiang");
		professionalElement.options.length = 1;								
		var zhuanyeid = document.getElementById("xi_delete").value;		
		if(zhuanyeid != "-1"){		
				//NO1)
				var ajax = createXMLHttpRequest();
				//NO2)
				var method = "POST";
				var url = "${pageContext.request.contextPath}/link_findDepartmentDirect";
				ajax.open(method,url);
				//NO3)
				ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");
				//NO4)
				var id="id="+zhuanyeid;
				ajax.send(id);						
				//-------------------------------------------等待				
				//NO5				
				ajax.onreadystatechange = function(){
					if(ajax.readyState == 4){					 
						if(ajax.status == 200){					
							//NO6)返回JAVA格式的JSON文本
							var jsonJAVA = ajax.responseText;														 
							//p所代表的是java格式的json文本，是不能直接被js执行的
							//解决方案：将java格式的json文本，转成js格式的json文本
							//如何做：用js提供的一个函数搞定							
							var jsonJS = eval("("+jsonJAVA+")");																				
							var array = jsonJS.departDirectList;														
							var size = array.length;
 							if(size == 0)
							{
								$("#zhuanyefangxiang").attr("disabled",true);								 																							
							}							
							else{ 							
								for(var i=0;i<size;i++){
									var departDirect = array[i];
									var str=departDirect.split(" ");																						
	 								var option = document.createElement("option"); 								
	 								option.value = str[0];
									option.innerHTML = str[1];																							
									professionalElement.appendChild(option); 								
								}
							}					
						}
					}			
				}
			} 			 
		}
		
	function delDep()
	{
	  	var xiid = document.getElementById("xi_delete").value;
 		if(xiid != "-1")	//限定有选择时才会提交表单
		{
			var form=document.getElementById("edit_depAndpro");
			form.action="${pageContext.request.contextPath}/department_deleteDepartment.action";
			form.submit();   
		} 
	 	else
		{
			alert("请选择要删除的专业");
			return false;
		}
	}
	
	function delPro()
	{
  		var proid = document.getElementById("zhuanyefangxiang").value;
		if(proid != "-1")	//限定有选择时才会提交表单
		{
			var form=document.getElementById("edit_depAndpro");
			form.action="${pageContext.request.contextPath}/professional_deleteProfessional.action";
			form.submit();   
		} 
	 	else
		{
			alert("请选择要删除的专业方向");
			return false;
		} 
	}
	
	
	function xueyuanSelect_experiment() {
			//清空实验室下拉框
			var experimentElement = document.getElementById("shiyanshi");
			experimentElement.options.length = 1;
			var xueyuanid = document.getElementById("xueyuan_experiment").value;
			if(xueyuanid != "-1") {
				//NO1)
				var ajax = createXMLHttpRequest();
				//NO2)
				var method = "POST";
				var url = "${pageContext.request.contextPath}/link_findExperiment";
				ajax.open(method, url);
				//NO3)
				ajax.setRequestHeader("content-type", "application/x-www-form-urlencoded");
				//NO4)
				var id = "id=" + xueyuanid;
				ajax.send(id);
				//-------------------------------------------等待				
				//NO5				
				ajax.onreadystatechange = function() {
					if(ajax.readyState == 4) {
						if(ajax.status == 200) {
							//NO6)返回JAVA格式的JSON文本
							var jsonJAVA = ajax.responseText;
							//p所代表的是java格式的json文本，是不能直接被js执行的
							//解决方案：将java格式的json文本，转成js格式的json文本
							//如何做：用js提供的一个函数搞定							
							var jsonJS = eval("(" + jsonJAVA + ")");
							var array = jsonJS.experimentList;
							var size = array.length;
							for(var i = 0; i < size; i++) {
								var depart = array[i];
								var str = depart.split(" ");
								var option = document.createElement("option");
								option.value = str[0];
								option.innerHTML = str[1];
								experimentElement.appendChild(option);

							}
						}
					}
				}
			}
		}

	function delExp()
	{
  		var expid = document.getElementById("shiyanshi").value;
		if(expid != "-1")	//限定有选择时才会提交表单
		{
			var form=document.getElementById("edit_experiment");
			form.action="${pageContext.request.contextPath}/experiment_deleteExperiment.action";
			form.submit();   
		} 
	 	else
		{
			alert("请选择要删除的实验室");
			return false;
		} 
	}
	
	</script>

<body>
	<div class="right_cont" style="width: 1200px;">
		<div class="title_right">
			<strong>信息导入</strong>
		</div>
		<!-- <p>${msg }</p>-->

		<div
			style="float: left; width: 480px; height: 400px; border: 2px solid #BBBBBB; margin-left: 30px; margin-top: 10px;">
			<div style="padding-top: 10px; border-bottom: 1px solid #dedede;">
				<span> <strong style="color: blue; font-size: 15px;">导入信息:</strong></span>

			</div>
			<div style="padding-top: 30px;">
				<span style="color: red; font-size: 13px;">导入顺序：学院->专业->用户->课程性质->专业方向->课程->专业总学分</span>
			</div>
			<div style="padding-top: 20px; padding-left: 6px; font-size: 14px;">

				<form action="user_importCollegeExcel.action"
					enctype="multipart/form-data" method="post">
					<li style="list-style: none; height: 30px; width: 470px;">
						导入学院：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="file"
						name="excelFile" id="excelFile" required><input
						type="submit" value="导入学院">
					</li>
				</form>
				<form action="user_importDepartmentExcel.action"
					enctype="multipart/form-data" method="post">
					<li style="list-style: none; height: 30px;">
						导入专业：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="file"
						name="excelFile" id="excelFile" required><input
						type="submit" value="导入专业">
					</li>
				</form>
				<form action="user_importUserExcel.action"
					enctype="multipart/form-data" method="post">
					<li style="list-style: none; height: 30px;">
						导入用户：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="file"
						name="excelFile" id="excelFile" required><input
						type="submit" value="导入用户">
					</li>
				</form>
				<form action="user_importNatureOfCourseExcel.action"
					enctype="multipart/form-data" method="post">
					<li style="list-style: none; height: 30px;">
						导入课程性质：&nbsp;&nbsp;<input type="file" name="excelFile"
						id="excelFile" required><input type="submit"
						value="导入课程性质">
					</li>
				</form>
				<form action="user_importProfessionalExcel.action"
					enctype="multipart/form-data" method="post">
					<li style="list-style: none; height: 30px;">
						导入专业方向：&nbsp;&nbsp;<input type="file" name="excelFile"
						id="excelFile" required><input type="submit"
						value="导入专业方向">
					</li>
				</form>
				<form action="user_importCurriculumExcel.action"
					enctype="multipart/form-data" method="post">
					<li style="list-style: none; height: 30px;">
						导入课程：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="file"
						name="excelFile" id="excelFile" required><input
						type="submit" value="导入课程">
					</li>
				</form>
				<form action="user_importScoreThresholdExcel.action"
					enctype="multipart/form-data" method="post">
					<li style="list-style: none; height: 30px;">导入专业总学分：<input
						type="file" name="excelFile" id="excelFile" required><input
						type="submit" value="导入学分阈值">
					</li>
				</form>
				<form action="user_importExperimentExcel.action"
					enctype="multipart/form-data" method="post">
					<li style="list-style: none; height: 30px;">导入实验室：<input
						type="file" name="excelFile" id="excelFile" required><input
						type="submit" value="导入实验室">
					</li>
				</form>
				<form action="user_importExperimenterExcel.action"
					enctype="multipart/form-data" method="post">
					<li style="list-style: none; height: 30px;">导入实验员：<input
						type="file" name="excelFile" id="excelFile" required><input
						type="submit" value="导入实验员">
					</li>
				</form>
			</div>
		</div>
		<div
			style="float: left; width: 320px; height: 400px; border: 2px solid #BBBBBB; margin-left: 25px;">
			<div style="padding-top: 10px; border-bottom: 1px solid #dedede;">
				<span> <strong style="color: blue; font-size: 15px;">下载信息：</strong></span>

			</div>
			<div style="padding-top: 30px; padding-left: 2px; width: 365px;">

				<div style="width: 150px; float: left; height: 400px;">
					<li style="list-style: none;"><a
						href="user_exportCollegeExcel.action">学院信息</a></li>
					<li style="list-style: none;"><a
						href="user_exportDepartmentExcel.action">专业信息</a></li>
					<li style="list-style: none;"><a
						href="user_exportCurriculumExcel.action">课程信息</a></li>
					<li style="list-style: none;"><a
						href="user_exportProfessionalExcel.action">专业方向信息</a></li>
				</div>
				<div style="float: left; width: 1px;">
					<table
						style="height: 185px; border-color: #aaa; border-left-style: dashed; border-width: 1px;">
						<tr>
							<td valign="top"></td>
						</tr>
					</table>
				</div>
				<div
					style="width: 180px; float: left; padding-left: 10px; height: 400px;"
					id="b1">
					<li style="list-style: none;"><a
						href="user_exportCollegeModelExcel.action">学院模板</a></li>
					<li style="list-style: none;"><a
						href="user_exportDepartmentModelExcel.action">专业模板</a></li>
					<li style="list-style: none;"><a
						href="user_exportUserModelExcel.action">用户模板</a></li>
					<li style="list-style: none;"><a
						href="user_exportNatureOfCourseModelExcel.action">课程性质模板</a></li>
					<li style="list-style: none;"><a
						href="user_exportProfessionalModelExcel.action">专业方向模板</a></li>
					<li style="list-style: none;"><a
						href="user_exportCurriculumModelExcel.action">课程模板</a></li>
					<li style="list-style: none;"><a
						href="user_exportScoreThresholdExcel.action">专业总学分模板</a></li>
					<li style="list-style: none;"><a
						href="user_exportExperimentModelExcel.action">实验室模板</a></li>
					<li style="list-style: none;"><a
						href="user_exportExperimenterModelExcel.action">实验员模板</a></li>
				</div>
			</div>
		</div>
		<div style="margin-left: 30px; margin-top: 450px; height: 666px;">
			<div style="float: left;">
				<div style="width: 230px; height: 220px; border: 2px solid #BBBBBB;">

					<form method="post" id="totalCredit">
						<div style="padding-top: 10px; border-bottom: 1px solid #dedede;">

							<span> <strong style="color: blue; font-size: 15px;">设置专业总学分：</strong></span>

						</div>
						<div
							style="padding-top: 25px; padding-left: 10px; font-size: 14px;">
							<li style="list-style: none; height: 40px;">学院： <select
								size="1" id="xueyuan" name="college.collegeid"
								onChange="xueyuanSelect()" style="width: 160px;">
									<option value="-1" selected="selected">请选择学院</option>
									<c:forEach items="${pageBean.collegelist}" var="college">
										<option value="${college.collegeid }">${college.collegeCname}</option>
									</c:forEach>
							</select>
							</li>
							<li style="list-style: none; height: 40px;">专业： <select
								size="1" id="xi" name="department.departmentid"
								style="width: 160px;">
									<option value="-1" selected="selected">请选择专业</option>
							</select>
							</li>

							<li style="list-style: none;">总学分： <input type="number"
								id="score" name="score" style="width: 50px;" required />&nbsp;<small>学分</small>
							</li> <input style="margin-left: 87px; margin-top: 20px;"
								onclick="selectSubmit()" value="确定" type="button"></input>
						</div>
					</form>
				</div>

				<div
					style="width: 230px; height: 220px; border: 2px solid #BBBBBB; margin-top: 10px;">
					<form method="post" id="avePer">
						<div style="padding-top: 10px; border-bottom: 1px solid #dedede;">

							<span> <strong style="color: blue; font-size: 15px;">设置平均周学时：</strong></span>
							当前值为${weekHour }
						</div>
						<div
							style="padding-top: 25px; padding-left: 10px; font-size: 14px;">

							<li style="list-style: none;">平均周学时： <input type="number"
								id="weekHour" name="weekHour" style="width: 50px;" size="1"
								required />&nbsp;<small>学时</small>
							</li> <input style="margin-left: 87px; margin-top: 20px;"
								onclick="weekHourSubmit()" value="确定" type="button"></input>
						</div>
					</form>
				</div>
			</div>
			<div
				style="width: 310px; height: 453px; border: 2px solid #BBBBBB; margin-left: 30px; float: left;">
				<form method="post" id="edit_depAndpro">
					<div style="padding-top: 10px; border-bottom: 1px solid #dedede;">

						<span> <strong style="color: blue; font-size: 15px;">添加/删除专业（方向）：</strong></span>

					</div>
					<div id="box1">
						<a href="department_toaddProPage.action">添加专业</a>
					</div>
					<div id="box2">
						<a href="professional_toaddProDirPage.action">添加专业方向</a>
					</div>
					<div
						style="padding-top: 130px; padding-left: 25px; font-size: 14px;">
						<li style="list-style: none; height: 40px;">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;院：
							<select size="1" id="xueyuan_delete" name="college.collegeid"
							onChange="xueyuanSelect_delete()" style="width: 160px;">
								<option value="-1" selected="selected">请选择学院</option>
								<c:forEach items="${pageBean.collegelist}" var="college">
									<option value="${college.collegeid }">${college.collegeCname}</option>
								</c:forEach>
						</select>
						</li>
						<li style="list-style: none; height: 40px;">专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：
							<select size="1" id="xi_delete" name="departmentid"
							style="width: 160px;" onChange="zhuanyeSelect()">
								<option value="-1" selected="selected">请选择专业</option>
						</select>
						</li>
						<li style="list-style: none; height: 40px;">专业方向： <select
							size="1" id="zhuanyefangxiang" name="professionalid"
							style="width: 160px;">
								<option value="-1" selected="selected">请选择专业方向</option>
						</select>
						</li>
						<div>

							<input style="margin-left: 20px; margin-top: 15px;"
								onclick="if (confirm('删除该专业后，与该专业有关的所有信息将一并删除，您确定删除吗？')) return delDep(); else return false;"
								value="删除所选专业" type="button"></input> <input
								style="margin-left: 10px; margin-top: 15px;"
								onclick="if (confirm('删除该专业方向后，与该专业方向有关的所有信息将一并删除，您确定删除吗？')) return delPro(); else return false;"
								value="删除所选专业方向" type="button"></input>

						</div>
					</div>

				</form>
			</div>



			<div
				style="width: 310px; height: 453px; border: 2px solid #BBBBBB; margin-left: 30px; float: left;">
				<form method="post" id="edit_experiment">
					<div style="padding-top: 10px; border-bottom: 1px solid #dedede;">

						<span> <strong style="color: blue; font-size: 15px;">添加/删除实验室：</strong></span>

					</div>
					<div id="box3">
						<a href="experiment_toaddExperimentPage.action">添加实验室</a>
					</div>

					<div
						style="padding-top: 130px; padding-left: 25px; font-size: 14px;">
						<li style="list-style: none; height: 40px;">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;院：
							<select size="1" id="xueyuan_experiment" name="college.collegeid"
							onChange="xueyuanSelect_experiment()" style="width: 160px;">
								<option value="-1" selected="selected">请选择学院</option>
								<c:forEach items="${pageBean.collegelist}" var="college">
									<option value="${college.collegeid }">${college.collegeCname}</option>
								</c:forEach>
						</select>
						</li>
						<li style="list-style: none; height: 40px;">&nbsp;实&nbsp;验&nbsp;室：
							<select size="1" id="shiyanshi" name="experimentid"
							style="width: 160px;">
								<option value="-1" selected="selected">请选择实验室</option>
						</select>
						</li>
						<div>
							<input style="margin-left: 70px; margin-top: 15px;"
								onclick="if (confirm('删除该实验室后，与该实验室有关的所有信息将一并删除，您确定删除吗？')) return delExp(); else return false;"
								value="删除所选实验室" type="button"></input>
						</div>
					</div>

				</form>
			</div>





		</div>
</body>
<script type="text/javascript">
		$("input[type=submit]").parents("form").on("submit", function() {
			showMask({
				info: "正在导入文件，请稍后……"
			});
		});
	</script>

</html>