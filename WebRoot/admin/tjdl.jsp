<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
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

<link rel="stylesheet" href="editor/themes/default/default.css" />
<link rel="stylesheet" href="editor/plugins/code/prettify.css" />
<script charset="utf-8" src="editor/kindeditor.js"></script>
<script charset="utf-8" src="editor/lang/zh_CN.js"></script>
<script charset="utf-8" src="editor/plugins/code/prettify.js"></script>
<script>
	KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
				cssPath : 'editor/plugins/code/prettify.css',
				uploadJson : 'editor/jsp/upload_json.jsp',
				fileManagerJson : 'editor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['form1'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['form1'].submit();
					});
				}
			});
			prettyPrint();
		});
		
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
		departmentElement.options.length = 0;								
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
			
									if(str[0] == '${xi}')	//设置专业的默认选定值
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
</script>
</head>
<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>添加用户</strong>
		</div>
		<div style="width: 900px; margin: auto;">
			<form action="${pageContext.request.contextPath}/user_adduser.action"
				method="post" name="form1">
				<table class="table table-bordered">
					<tr>
						<input type="hidden" name="adminlevel" value="0" />
						<td width="20%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">职工号：</td>
						<td><input type="text" name="tnum" class="span4" value=""
							required /></td>
					</tr>

					<tr>
						<td width="20%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">密码：</td>
						<td><input type="text" name="password" class="span4" value=""
							required /></td>
					</tr>
					<tr>
						<td width="20%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">姓名：</td>
						<td><input type="text" name="username" class="span4" value=""
							required /></td>
					</tr>
					<tr>
						<td width="20%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">性别：</td>
						<td><input name="sex" type="radio" value="男"
							checked="checked" />男 <input name="sex" type="radio" value="女" />女</td>
					</tr>
					<tr>
						<c:if test="${sessionScope.user.adminlevel == 5 }">
							<tr>
								<td width="20%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">部门（学院）：</td>
								<td><select name="college.collegeid" id="xueyuan"
									onChange="xueyuanSelect()" size="1">
										<c:forEach items="${collegelist}" var="college">
											<option value="${college.collegeid }">${college.collegeCname }</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td width="35%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">专业：</td>
								<td><select name="department.departmentid" id="xi" size="1">

								</select></td>
							</tr>
						</c:if>
						<c:if test="${sessionScope.user.adminlevel == 3 }">
							<tr>
								<td width="20%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">部门（学院）：</td>
								<td><select name="college.collegeid" id="xueyuan" size="1">
										<c:forEach items="${collegelist}" var="college">
											<option value="${college.collegeid }">${college.collegeCname }</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td width="35%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">专业：</td>
								<td><select name="department.departmentid" id="xi" size="1">
										<c:forEach items="${departmentlist}" var="department">
											<option value="${department.departmentid }">${department.departmentCname }</option>
										</c:forEach>
								</select></td>
							</tr>
						</c:if>
						<c:if test="${sessionScope.user.adminlevel == 1 }">
							<tr>
								<td width="20%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">部门（学院）：</td>
								<td><select name="college.collegeid" id="xueyuan" size="1">
										<c:forEach items="${collegelist}" var="college">
											<option value="${college.collegeid }">${college.collegeCname }</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td width="35%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">专业：</td>
								<td><select name="department.departmentid" id="xi" size="1">
										<c:forEach items="${departmentlist}" var="department">
											<option value="${department.departmentid }">${department.departmentCname }</option>
										</c:forEach>
								</select></td>
							</tr>
						</c:if>
					<tr>
						<td class="text-center" colspan="2"><input type="submit"
							value="确定" class="btn btn-info  " style="width: 80px;" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>