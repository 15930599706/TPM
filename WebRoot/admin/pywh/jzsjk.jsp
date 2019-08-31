<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" href="<%=basePath%>images/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath%>images/css/css.css" />
<script type="text/javascript"
	src="<%=basePath%>images/js/jquery1.9.0.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>images/js/sdmenu.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/laydate/laydate.js"></script>
<script src="<%=basePath%>images/js/jquery-1.7.2.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>images/js/tc.min.js"></script>
<style type="text/css">
#box5 {
	font-family: "microsoft yahei";
	height: 50px;
	padding-left: 25px;
	padding-top: 25px;
}

#box1 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	width: 142px;
	height: 20px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 142px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/add-16.png) no-repeat 25px 2px;
	text-indent: 40px;
	text-align: center;
}

#box1 {
	height: 20px;
	float: left;
	width: 142px;
}

#box2 {
	font-family: "microsoft yahei";
	height: 40px;
	float: left;
	width: 1250px;
	padding-left: 25px;
}

#sl {
	width: 160px;
}

#lb #xq {
	width: 100px;
}

#detail {
	position: absolute;
	width: 350px;
	height: 120px;
	border: 1px solid #ccc;
	background: #efefef;
	display: none;
	color: #000000;
}

#detail .tit {
	background: #B1CCEB;
	display: block;
	height: 22px;
	cursor: move;
	color: #000000;
}

#detail .tit i {
	float: right;
	padding-right: 9px;
	padding-top: 2px;
	cursor: default;
	font-size: 12px;
	color: #000000;
}

#detail2 {
	position: absolute;
	width: 300px;
	height: 100px;
	border: 1px solid #555;
	background: #555;
	display: none;
}

#detail2 .tit {
	background: #333;
	display: block;
	height: 33px;
	cursor: move;
}

#detail2 .tit i {
	float: right;
	line-height: 33px;
	padding: 0 8px;
	color: #000000;
	cursor: default;
}

body {
	margin: 0;
	padding: 0;
	font-size: 12px;
}

dt {
	padding: 10px;
}

p {
	height: 100px;
	line-height: 100px;
	border: 1px solid #000000;
	margin: 10px;
}

i {
	font-style: normal;
	color: #000000;
}

#box8 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	width: 97px;
	height: 20px;
}

#box8 a {
	display: block;
	height: 20px;
	width: 97px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/46.png) no-repeat 25px 2px;
	text-indent: 40px;
	text-align: center;
}

#box8 {
	height: 25px;
	float: left;
	width: 97px;
}

#box6 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	cursor: pointer;
	width: 163px;
	height: 20px;
}

#box6 a {
	display: block;
	height: 20px;
	width: 163px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/export-16.png) no-repeat 25px
		2px;
	text-indent: 40px;
	text-align: center;
}

#box6 {
	height: 40px;
	float: left;
	width: 150px;
}

#box7 a:hover {
	font-family: "microsoft yahei";
	color: #000;
	background-color: #D2E7F5;
	border: 1px solid #B1CCEB;
	width: 88px;
	height: 20px;
}

#box7 a {
	display: block;
	height: 20px;
	width: 88px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #0000C6;
	background: url(<%=basePath%>images/img/export-16.png) no-repeat 25px
		3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box7 {
	height: 60px;
	float: left;
	width: 100px;
}

#box3 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	width: 180px;
	height: 20px;
}

#box3 a {
	display: block;
	height: 20px;
	width: 180px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/disk-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	text-align: center;
}

#box3 {
	height: 60px;
	float: left;
	width: 180px;
}

#box4 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	width: 153px;
	height: 20px;
}

#box4 a {
	display: block;
	height: 20px;
	width: 153px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/disk-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	text-align: center;
}

#box4 {
	height: 60px;
	float: left;
	width: 153px;
	padding-left: 5px;
}

#box {
	font-family: "microsoft yahei";
	height: 50px;
	float: left;
	width: 1000px;
}

#bo {
	font-family: "microsoft yahei";
	padding-top: 70px;
	height: 61px;
	width: 900px;
	padding-left: 24px;
}
</style>
</head>
<script language="javascript">
	function createXMLHttpRequest() {
           var ajax = null;
	   try{
		   ajax = new ActiveXObject("microsoft.xmlhttp");
	   }catch(e1){
		try{
			ajax = new XMLHttpRequest();
		}catch(e2){
			alert("你的浏览器不支持ajax，请更换浏览器");
		}
	  }
	     return ajax;
        }
        
        function xueyuanSelect(){
        //清空学院下拉框
		var departmentElement = document.getElementById("xi");
		departmentElement.options.length = 1;								
		var xueyuanid = document.getElementById("xueyuan").value;			
		if(xueyuanid != "-1"){
				//NO1)
				var ajax = createXMLHttpRequest();
				//NO2)
				var method = "POST";
				var url = "${pageContext.request.contextPath}/link_findDepartment";
				ajax.open(method,url);
				//NO3)
				ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");
				//NO4)
				var id="id="+xueyuanid;
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
							var array = jsonJS.departList;														
							var size = array.length;
							for(var i=0;i<size;i++){
								var depart = array[i];
								var str=depart.split(" ");																						
 								var option = document.createElement("option"); 								
 								option.value = str[0];
								option.innerHTML = str[1];		
								if(str[0] == '${xi}')
									{
										option.selected = true;
									}																					
								departmentElement.appendChild(option); 								
							}												
						}
					}	
					zhuanyeSelect();		
				}
			} 
			 
		}
		
		function zhuanyeSelect(){		//用于加载三级联动
		 $("#zhuanyefangxiang").attr("disabled",false);
        //清空专业方向下拉框		
		var professionalElement = document.getElementById("zhuanyefangxiang");
		professionalElement.options.length = 1;								
		var zhuanyeid = document.getElementById("xi").value;		
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
									if(str[0] == '${fangxiang}')
										{
											option.selected = true;
										} 																						
									professionalElement.appendChild(option); 								
								}
							}					
						}
					}			
				}
			} 			 
		}
		
		function zhuanyeSubmit(){		//用于学校管理员和学院管理员在选择专业时若不分专业方向，则提交表单
		 $("#zhuanyefangxiang").attr("disabled",false);
        //清空专业方向下拉框		
		var professionalElement = document.getElementById("zhuanyefangxiang");
		professionalElement.options.length = 1;								
		var zhuanyeid = document.getElementById("xi").value;		
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
								selectSubmit();
							}							
							else{ 							
								for(var i=0;i<size;i++){
									var departDirect = array[i];
									var str=departDirect.split(" ");																						
	 								var option = document.createElement("option"); 								
	 								option.value = str[0];
									option.innerHTML = str[1];	
									if(str[0] == '${fangxiang}')
										{
											option.selected = true;
										} 																						
									professionalElement.appendChild(option); 								
								}
							}					
						}
					}			
				}
			} 			 
		}
		
		function selectSubmit()
		{
			
			var xueyuanid = document.getElementById("xueyuan").value;
			var xiid = document.getElementById("xi").value;
			if(xueyuanid != "-1" && xiid != "-1" )	//限定有选择时才会提交表单
			{
				var form=document.getElementById("jzsjk");
				form.action="${pageContext.request.contextPath}/practicePlan_jzsjkResult.action?tnum=${user.tnum}";				
				form.submit();  
			}  
		}		
		
		function practicePlan_downPracticePlan(){	//导出选择的实践课
			var zhuanyeid = document.getElementById("xi").value;
			var zhuanyefangxiangid = document.getElementById("zhuanyefangxiang").value;
			if(zhuanyeid == "-1")
			{
				alert("请选择专业");
			}
			else
			{
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
								var form=document.getElementById("jzsjk");
								form.action="${pageContext.request.contextPath}/practicePlan_downPracticePlan.action?professional.professionalid=-1";
								form.submit();  
							}							
							else{ 		
								if(zhuanyefangxiangid == "-1")
								{
									alert("请选择专业方向");
								}
								else
								{
									var form=document.getElementById("jzsjk");
									form.action="${pageContext.request.contextPath}/practicePlan_downPracticePlan.action";
									form.submit(); 
								}					
	
							}					
						}
					}			
				}
			}
		
	}
	
	function tjsjk_Submit()	//表单提交
	{
		var xueyuanid = document.getElementById("xueyuan").value;
		var xiid = document.getElementById("xi").value;
		if(xueyuanid != "-1" && xiid != "-1")	//限定有选择时才会提交表单
		{
			var form=document.getElementById("jzsjk");
			form.action="practicePlan_totjsjkPage.action?collegeid="+xueyuanid+"&departmentid="+xiid;
			form.submit();  
		}
		else
		{
			alert("请选择专业");
		}  
	}
		
	</script>

<body
	<c:if test="${user.adminlevel != 1 }"> onload="xueyuanSelect()"</c:if>
	<c:if test="${user.adminlevel == 1 }"> onload="zhuanyeSelect()"</c:if>>
	<div class="right_cont">
		<div class="title_right">
			<strong>集中实践课管理</strong>
		</div>
		<div>
			<c:if test="${user.adminlevel == 1 || user.adminlevel == 5 }">
				<div id="box1">
					<a href="#" target="MainFrame" onclick="tjsjk_Submit()">添加集中实践课</a>
				</div>
			</c:if>
			<div id="box6">
				<a href="#" target="MainFrame"
					onclick="practicePlan_downPracticePlan()" id="t1"
					target="MainFrame">导出所选集中实践课</a>
			</div>
		</div>
		<form method="post" id="jzsjk">
			<div id="box2">
				<td>学院：</td>
				<td><c:if test="${user.adminlevel == 5 }">
						<select size="1" id="xueyuan" name="department.college.collegeid"
							onChange="xueyuanSelect()">
							<option value="-1" selected="selected">请选择学院</option>
							<c:forEach items="${collegelist}" var="college">
								<option value="${college.collegeid }"
									<c:if test="${college.collegeid eq xueyuan }">selected="selected"</c:if>>${college.collegeCname}</option>
							</c:forEach>
						</select>
					</c:if> <c:if test="${user.adminlevel != 5 }">
						<select size="1" id="xueyuan" name="department.college.collegeid">
							<c:forEach items="${collegelist}" var="college">
								<option value="${college.collegeid }"
									<c:if test="${college.collegeid eq xueyuan }">selected="selected"</c:if>>${college.collegeCname}</option>
							</c:forEach>
						</select>
					</c:if></td>
				<td>专业：</td>
				<td><c:if test="${user.adminlevel != 1 }">
						<select size="1" id="xi" name="department.departmentid"
							onChange="zhuanyeSubmit()">
							<option value="-1" selected="selected">请选择专业</option>
						</select>
					</c:if> <c:if test="${user.adminlevel == 1 }">
						<select size="1" id="xi" name="department.departmentid">
							<c:forEach items="${departmentlist}" var="department">
								<option value="${department.departmentid }"
									<c:if test="${department.departmentid eq xi }">selected="selected"</c:if>>${department.departmentCname}</option>
							</c:forEach>
						</select>
					</c:if></td>

				<td>专业方向：</td>
				<td><select size="1" id="zhuanyefangxiang"
					name="professional.professionalid" onChange="selectSubmit()">
						<option value="-1" selected="selected">请选择专业方向</option>
				</select></td>

				<td>学期：</td>
				<td><select size="1" id="xq" name="xueqi"
					onChange="selectSubmit()" style="width: 80px;">
						<option value="">【全部】</option>
						<option value="1"
							<c:if test="${xueqi eq '1' }">selected="selected"</c:if>>1</option>
						<option value="2"
							<c:if test="${xueqi eq '2' }">selected="selected"</c:if>>2</option>
						<option value="3"
							<c:if test="${xueqi eq '3' }">selected="selected"</c:if>>3</option>
						<option value="4"
							<c:if test="${xueqi eq '4' }">selected="selected"</c:if>>4</option>
						<option value="5"
							<c:if test="${xueqi eq '5' }">selected="selected"</c:if>>5</option>
						<option value="6"
							<c:if test="${xueqi eq '6' }">selected="selected"</c:if>>6</option>
						<option value="7"
							<c:if test="${xueqi eq '7' }">selected="selected"</c:if>>7</option>
						<option value="8"
							<c:if test="${xueqi eq '8' }">selected="selected"</c:if>>8</option>
						<option value="7、8"
							<c:if test="${xueqi eq '7、8' }">selected="selected"</c:if>>7、8</option>
						<option value="9"
							<c:if test="${xueqi eq '9' }">selected="selected"</c:if>>9</option>
						<option value="10"
							<c:if test="${xueqi eq '10' }">selected="selected"</c:if>>10</option>

				</select></td>
			</div>
			<div id="box2">
				<td>课程平台：</td>
				<td><select size="1" name="curriculum.curriculumpingtai"
					onChange="selectSubmit()" style="width: 100px;">
						<option value="" selected="selected">【全部】</option>
						<option value="公共教育平台"
							<c:if test="${pingtai eq '公共教育平台' }">selected="selected"</c:if>>公共教育平台</option>
						<option value="专业教育平台"
							<c:if test="${pingtai eq '专业教育平台' }">selected="selected"</c:if>>专业教育平台</option>

				</select></td>
				<td>课程性质：</td>
				<td><select size="1"
					name="curriculum.natureOfCourse.natureOfCourseid"
					onChange="selectSubmit()" style="width: 100px;">
						<option value="">【全部】</option>
						<option value="06"
							<c:if test="${xingzhi eq '06' }">selected="selected"</c:if>>公共必选课</option>
						<option value="09"
							<c:if test="${xingzhi eq '09' }">selected="selected"</c:if>>公共选修课</option>
						<option value="10"
							<c:if test="${xingzhi eq '10' }">selected="selected"</c:if>>专业必选课</option>
						<option value="12"
							<c:if test="${xingzhi eq '12' }">selected="selected"</c:if>>专业选修课</option>
				</select></td>
				<td>课程类别：</td>
				<td><select size="1" name="curriculum.courseLei"
					onChange="selectSubmit()" style="width: 100px;">
						<option value="实践课"
							<c:if test="${leibie eq '实践课' }">selected="selected"</c:if>>实践课</option>
				</select></td>
				<td>课程归属：</td>
				<td><select size="1" name="curriculum.courseCategory"
					onChange="selectSubmit()" style="width: 150px;">
						<option value="">【全部】</option>
						<option value="社会科学类"
							<c:if test="${guishu eq '社会科学类' }">selected="selected"</c:if>>社会科学类</option>
						<option value="文化与艺术类"
							<c:if test="${guishu eq '文化与艺术类' }">selected="selected"</c:if>>文化与艺术类</option>
						<option value="数学与逻辑类"
							<c:if test="${guishu eq '数学与逻辑类' }">selected="selected"</c:if>>数学与逻辑类</option>
						<option value="科学与技术类"
							<c:if test="${guishu eq '科学与技术类' }">selected="selected"</c:if>>科学与技术类</option>
						<option value="创新创业与发展类"
							<c:if test="${guishu eq '创新创业与发展类' }">selected="selected"</c:if>>创新创业与发展类</option>
				</select></td>
			</div>
			<c:if test="${not empty totalCredit}">
				<div id="bo">
					<strong style="color: red;">总学分${totalCredit };&nbsp&nbsp&nbsp
						<c:if test="${totalCredit > 0 }">
						公共教育平台总学分 ${publicTotalCredit }，占总学分比例${publicProportion };&nbsp&nbsp&nbsp专业教育平台总学分${professionalTotalCredit }，占总学分比例${professionalProportion }	
					</c:if>
					</strong>
				</div>
			</c:if>
		</form>

		<c:if test="${user.adminlevel == 5 }">
			<div style="margin-left: 24px; margin-top: 15px;">
				<c:if test="${not empty practiceLessonList}">
					<input class=" btn btn-info" type="button"
						onclick="if (confirm('您确定删除所选的实践课吗？删除后该课程的大纲也将一并删除！')) return delCheck(); else return false;"
						value="删除所选项">
				</c:if>
			</div>
		</c:if>

		<form action="" method="post" id="delPracMore">
			<div style="width: 90%; margin-left: 24px; margin-top: 15px;">
				<table class="table table-bordered table-striped table-hover">
					<tbody>
					<thead>

						<tr align="center">
							<c:if test="${user.adminlevel == 5 }">
								<th nowrap="nowrap"><input type="checkbox"
									onclick="swapCheck(this)" /></th>
							</c:if>
							<th nowrap="nowrap"><small>课程平台</small></th>
							<th nowrap="nowrap"><small>课程性质</small></th>
							<th nowrap="nowrap"><small>课程类别</small></th>
							<th nowrap="nowrap"><small>课程归属</small></th>
							<th nowrap="nowrap"><small>授课学院</small></th>
							<th nowrap="nowrap"><small>课程编码</small></th>
							<th nowrap="nowrap"><small>课程名称</small></th>
							<th nowrap="nowrap"><small>学分</small></th>
							<th nowrap="nowrap"><small>课外实验</small></th>
							<th nowrap="nowrap"><small>课外上机</small></th>
							<th nowrap="nowrap"><small>学期</small></th>
							<th nowrap="nowrap"><small>起止周</small></th>
							<th nowrap="nowrap"><small>专业方向</small></th>
							<th nowrap="nowrap"><small>组织形式</small></th>
							<th nowrap="nowrap"><small>是否学位课</small></th>
							<th nowrap="nowrap"><small>是否集中实践</small></th>
							<th nowrap="nowrap"><small>备注</small></th>
							<c:if test="${user.adminlevel == 1 || user.adminlevel == 5 }">
								<th nowrap="nowrap"><small>编辑</small></th>
								<th nowrap="nowrap"><small>删除</small></th>
							</c:if>
						</tr>
					</thead>
					<c:forEach items="${practiceLessonList }" var="practiceLesson">
						<tr align="center">
							<c:if test="${user.adminlevel == 5 }">
								<td><input type="checkbox" name="deletepracticeLessonList"
									value="${practiceLesson.practiceLessonid }" /></td>
							</c:if>
							<td nowrap="nowrap"><small>${practiceLesson.curriculum.curriculumpingtai }</small></td>
							<td nowrap="nowrap"><small>${practiceLesson.curriculum.natureOfCourse.natureOfCoursename }</small></td>
							<td nowrap="nowrap"><small>${practiceLesson.curriculum.courseLei }</small></td>
							<td nowrap="nowrap"><small>${practiceLesson.curriculum.courseCategory }</small></td>
							<td nowrap="nowrap"><small>${practiceLesson.curriculum.college.collegeCname }</small></td>
							<td nowrap="nowrap"><small>${practiceLesson.curriculum.curriculumid }</small></td>
							<td nowrap="nowrap"><small>${practiceLesson.curriculum.curriculumCname }</small></td>
							<td nowrap="nowrap"><small>${practiceLesson.curriculum.credit }</small></td>
							<th nowrap="nowrap"><small>${practiceLesson.hoursOfOutExp}</small></th>
							<th nowrap="nowrap"><small>${practiceLesson.hoursOfOutCom}</small></th>
							<td nowrap="nowrap"><small>${practiceLesson.xueqi}</small></td>
							<td nowrap="nowrap"><small>${practiceLesson.qizhizhou}</small></td>
							<td nowrap="nowrap"><small>${practiceLesson.professional.professionalname }</small></td>
							<td nowrap="nowrap"><small>${practiceLesson.zuzhixingshi}</small></td>
							<td nowrap="nowrap"><small>${practiceLesson.isxueweike}</small></td>
							<td nowrap="nowrap"><small>${practiceLesson.idallpracticeLesson}</small></td>
							<td nowrap="nowrap" style="width: 9em;"><small>${practiceLesson.beizhu}</small></td>
							<c:if test="${user.adminlevel == 1 || user.adminlevel == 5 }">
								<td nowrap="nowrap"><a
									href="practicePlan_tobjsjkPage.action?practiceLessonid=${practiceLesson.practiceLessonid }"><small>编辑</small></a></td>
								<td nowrap="nowrap"><a
									href="practicePlan_delsjk.action?practiceLessonid=${practiceLesson.practiceLessonid }"
									onclick="if (confirm('您确定要将此课程从培养计划中删除吗？删除后该课程的大纲也将一并删除！')) return true; else return false;"><small>删除</small></a></td>
							</c:if>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">  
        // checkbox 全选/取消全选  
        function swapCheck(control) {
        	$("input[type='checkbox']").each(function() {
            	this.checked = control.checked;
            });
        }
        
        function delCheck()
        {
	      if ($("input[type='checkbox']").is(':checked')) 
	      {
				var form = document.getElementById("delPracMore");
				form.action = "${pageContext.request.contextPath}/practicePlan_delsjkMore.action";
				form.submit();
			}
			
			else 
			{
				alert("请选择要删除的实践课！");
				return false;
			}
		}
        </script>
</html>