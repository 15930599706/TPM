<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
		
		function check(){	
		var xiid = document.getElementById("xi").value;
		if(xiid != "-1")	//限定有选择时才会提交表单
		{
			return true; 
		} 
		else
		{
			alert("请选择所属专业");
			return false;
		}
	}		
</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="images/css/bootstrap.css" />
<link rel="stylesheet" href="images/css/css.css" />
</head>
<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>添加专业方向</strong>
		</div>
		<div style="width: 500px; margin: auto;">
			<form action="professional_addProDir" method="post" name="addProDir"
				onSubmit="return check();" enctype="multipart/form-data">
				<table class="table table-bordered">

					<tr>
					<tr>
						<td width="20%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">部门（学院）：</td>
						<td><select size="1" id="xueyuan"
							name="department.college.collegeid" onChange="xueyuanSelect()">
								<option value="-1" selected="selected">请选择所属学院</option>
								<c:forEach items="${collegelist}" var="college">
									<option value="${college.collegeid }">${college.collegeCname}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td width="35%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">专业：</td>
						<td><select size="1" id="xi" name="department.departmentid">
								<option value="-1" selected="selected">请选择所属专业</option>
						</select></td>
					</tr>
					<tr>
						<td width="5%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">专业方向代码：</td>
						<td><input type="text" name="professionalid" class="span4"
							value="" required style="width: 162px;" /></td>
					</tr>
					<tr>
						<td width="5%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">专业方向名称：</td>
						<td><input type="text" name="professionalname" class="span4"
							value="" required style="width: 162px;" /></td>
					</tr>
					<tr>
						<td class="text-center" colspan="2"><input type="submit"
							value="确定" class="btn btn-info  " style="width: 80px;" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>