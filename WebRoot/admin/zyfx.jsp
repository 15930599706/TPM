<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
<style type="text/css">
#box1 a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	width: 73px;
	height: 20px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 73px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/export-16.png) no-repeat 25px
		2px;
	text-indent: 40px;
	text-align: center;
}

#box1 {
	height: 40px;
	float: left;
	width: 73px;
}

#box3 {
	height: 40px;
	float: left;
	width: 120px;
}

#box11 {
	font-family: "microsoft yahei";
	height: 50px;
	width: 1000px;
	padding-left: 24px;
	padding-top: 40px;
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
				ajax.setRequestHeader("content-type","application/x-www-form-urlencoded")
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
								if(str[0] == '${newdepart}')	//设置专业的默认选定值
									{
										option.selected = true;
									}   																						
								departmentElement.appendChild(option); 
								
							}												
						}
					}			
				}
			} 
			 
		}
		function selectsubmit()
		{
			var xueyuanid = document.getElementById("xueyuan").value;
			var xiid = document.getElementById("xi").value;
				var form=document.getElementById("findcurriculum");
				form.action="${pageContext.request.contextPath}/user_tozyfxPage.action";
				form.submit();   
		}	
	</script>
<body onload="xueyuanSelect()">
	<div class="right_cont">
		<div class="title_right">
			<strong>专业方向信息</strong>
		</div>

		<div id="box2">
			<div id="box1">
				<a
					href="${pageContext.request.contextPath}/user_exportProfessionalExcel.action"
					target="MainFrame">导出</a>

			</div>
			<div id="box11">
				<form
					action="${pageContext.request.contextPath}/user_tozyfxPage.action"
					method="post" id="findcurriculum">
					<td>开课部门(学院)：</td>
					<td><select size="1" id="xueyuan" name="college.collegeid"
						onchange="xueyuanSelect()">
							<option value="-1">---所有学院---</option>
							<c:forEach items="${pageBean.collegelist }" var="college">
								<option value="${college.collegeid}"
									<c:if test="${college.collegeid eq newcollege }">selected="selected"</c:if>>${college.collegeCname}</option>
							</c:forEach>
					</select></td>
					<td>专业：</td>
					<td><select size="1" id="xi" name="department.departmentid"
						onChange="selectsubmit()">
							<option value="-1">全部专业</option>
					</select></td>
					<td class="text-center" colspan="0"><input type="submit"
						value="检索" class="btn btn-info  " style="width: 50px;" /></td>
				</form>
			</div>
		</div>

		<div style="width: 90%; margin-left: 24px;">
			<form
				action="${pageContext.request.contextPath}/user_tozyfxPage.action?department.departmentid=${newdepart}&college.collegeid=${newcollege}"
				method="post" name="form3">
				<table class="table table-bordered table-striped table-hover">

					<tbody>
					<thead>
						<tr align="center">

							<th nowrap="nowrap"><small>专业方向代码</small></th>
							<th nowrap="nowrap"><small>专业方向名称</small>
							</td>
							<th nowrap="nowrap"><small>专业</small></th>
						</tr>
					</thead>

					<c:forEach items="${pageBean.professionallist }" var="professional">
						<tr align="center">
							<td nowrap="nowrap"><small>${professional.professionalid }</small></td>
							<td nowrap="nowrap"><small>${professional.professionalname }</small></td>
							<td nowrap="nowrap"><small>${professional.department.departmentCname }</small></td>
						</tr>
					</c:forEach>

					<c:if test="${pageBean.totalCount == 0 }">
						<tr align="center">
							<th nowrap="nowrap" colspan="11">此专业尚未划分方向</th>
						</tr>
					</c:if>

					<tfoot>
						<tr align="center">
							<th nowrap="nowrap" colspan="11"><c:if
									test="${pageBean.currentpage != 1 }">
									<a
										href="${pageContext.request.contextPath}/user_tozyfxPage.action?department.departmentid=${newdepart}&college.collegeid=${newcollege}"><small>首页</small></a>&nbsp;&nbsp;&nbsp;
									<a
										href="${pageContext.request.contextPath}/user_tozyfxPage.action?department.departmentid=${newdepart}&college.collegeid=${newcollege}&currentpage=${currentpage-1 }"><small>上一页</small></a>
								</c:if>&nbsp;&nbsp;&nbsp; <small>共${pageBean.totalCount }条记录,共计${pageBean.totalPage }页,当前第${pageBean.currentpage }页</small>&nbsp;&nbsp;&nbsp;
								<c:if test="${pageBean.currentpage != pageBean.totalPage }">
									<a
										href="${pageContext.request.contextPath}/user_tozyfxPage.action?department.departmentid=${newdepart}&college.collegeid=${newcollege}&currentpage=${currentpage+1 }"><small>下一页</small></a>&nbsp;&nbsp;&nbsp;
									<a
										href="${pageContext.request.contextPath}/user_tozyfxPage.action?department.departmentid=${newdepart}&college.collegeid=${newcollege}&currentpage=${pageBean.totalPage }"><small>尾页</small></a>
								</c:if> <small> 第</small><input type="number" name="currentpage"
								onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
								class="span1" /><small>页 </small> <input type="submit"
								value="跳转">&nbsp;&nbsp;&nbsp;</th>
						</tr>
					</tfoot>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>