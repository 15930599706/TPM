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
<script type="text/javascript" src="<%=basePath %>/images/js/animate.js"></script>
<script type="text/javascript"
	src="<%=basePath %>/images/js/loadMask.js"></script>
<style type="text/css">
#box2 {
	font-family: "microsoft yahei";
	height: 40px;
	float: left;
	width: 1250px;
}

#box20 {
	font-family: "microsoft yahei";
	height: 50px;
	float: left;
	width: 1250px;
	padding-left: 22px;
}

#box3 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	width: 185px;
	height: 20px;
}

#box3 a {
	display: block;
	height: 20px;
	width: 185px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/disk-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	text-align: center;
}

#box3 {
	height: 20px;
	float: left;
	width: 185px;
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
	height: 20px;
	float: left;
	width: 153px;
	padding-left: 5px;
}

#box5 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	cursor: pointer;
	width: 180px;
	height: 30px;
}

#box5 a {
	display: block;
	height: 30px;
	width: 180px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/export-16.png) no-repeat 25px
		3px;
	text-indent: 33px;
	text-align: center;
}

#box5 {
	height: 30px;
	float: left;
	width: 180px;
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
								departmentElement.appendChild(option); 									
							
							}																			
						}
					}	
				}						
			} 			
		}				
		
		function exportword_profession(){	//生成专业培养方案专业id提交
  			var xiid = document.getElementById("xi").value;
			if(xiid != "-1")	//限定有选择时才会提交表单
			{
				var form=document.getElementById("llk");
				form.action="${pageContext.request.contextPath}/exportword_profession.action?pid="+xiid;
				form.submit();   
			} 
		 	else
			{
				alert("请选择专业");
			}   
			
		}
		
		function exportword_college(){	//生成学院培养方案学院id提交
			var xueyuanid = document.getElementById("xueyuan").value;
			if(xueyuanid != "-1")	//限定有选择时才会提交表单
			{
				var form=document.getElementById("llk");
				form.action="${pageContext.request.contextPath}/exportword_college.action?cid="+xueyuanid;
				form.submit();  
			} 
			else
			{
				alert("请选择学院");
			}
		}
	
		function exportword_school(){	//生成全校培养方案
			var form=document.getElementById("llk");
			form.action="${pageContext.request.contextPath}/exportword_school.action";
			form.submit();  
		}

	</script>

<body
	<c:if test="${user.adminlevel != 1 }"> onload="xueyuanSelect()"</c:if>
	<c:if test="${user.adminlevel == 1 }"> onload="zhuanyeSelect()"</c:if>>
	<div class="right_cont">
		<div class="title_right">
			<strong>生成培养计划</strong>
		</div>

		<div>
			<div id="box2">
				<c:if test="${user.adminlevel >= 1 }">
					<div id="box3">
						<a href="#" target="MainFrame" onclick="exportword_profession()">生成当前专业培养方案</a>
					</div>
				</c:if>
				<c:if test="${user.adminlevel >= 3 }">
					<div id="box3">
						<a href="#" target="MainFrame" onclick="exportword_college()">生成当前学院培养方案</a>
					</div>
				</c:if>
				<c:if test="${user.adminlevel >= 5 }">
					<div id="box4">
						<a href="#" onclick="exportword_school()" target="MainFrame">生成全校培养方案</a>
					</div>
				</c:if>


			</div>
			<div id="box20">
				<td>学院：</td>
				<td><c:if test="${user.adminlevel == 5 }">
						<select size="1" id="xueyuan" name="department.college.collegeid"
							onChange="xueyuanSelect()">
							<option value="-1" selected="selected">请选择学院</option>
							<c:forEach items="${collegelist}" var="college">
								<option value="${college.collegeid }">${college.collegeCname}</option>
							</c:forEach>
						</select>
					</c:if> <c:if test="${user.adminlevel != 5 }">
						<select size="1" id="xueyuan" name="department.college.collegeid">
							<c:forEach items="${collegelist}" var="college">
								<option value="${college.collegeid }">${college.collegeCname}</option>
							</c:forEach>
						</select>
					</c:if></td>

				<td>专业：</td>
				<td><c:if test="${user.adminlevel != 1 }">
						<select size="1" id="xi" name="department.departmentid">
							<option value="-1" selected="selected">请选择专业</option>
						</select>
					</c:if> <c:if test="${user.adminlevel == 1 }">
						<select size="1" id="xi" name="department.departmentid">
							<c:forEach items="${departmentlist}" var="department">
								<option value="${department.departmentid }">${department.departmentCname}</option>
							</c:forEach>
						</select>
					</c:if></td>

			</div>

			</form>
			<div id="box2">
				<c:if test="${user.adminlevel >= 5 }">
					<div id="box5">
						<a
							href="${pageContext.request.contextPath}/theoreticalPlan_downTheoreticalPlan_all.action"
							target="MainFrame">导出全校所选理论课</a>

					</div>
					<div id="box5">
						<a
							href="${pageContext.request.contextPath}/practicePlan_downPracticePlan_all.action"
							target="MainFrame">导出全校所选实践课</a>
					</div>
				</c:if>

			</div>


			<form method="post" id="llk">
</body>
</html>