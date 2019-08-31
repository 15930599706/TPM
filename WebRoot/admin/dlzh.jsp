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

#box1 a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	width: 97px;
	height: 20px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 97px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/add-16.png) no-repeat 25px 2px;
	text-indent: 40px;
	text-align: center;
}

#box3 a {
	display: block;
	height: 20px;
	width: 97px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/import1-16.png) no-repeat 25px
		2px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box3 a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	cursor: pointer;
	width: 97px;
	height: 20px;
}

#box1 {
	height: 20px;
	float: left;
	width: 120px;
}

#box3 {
	height: 20px;
	float: left;
	width: 120px;
}

#box2 {
	font-family: "microsoft yahei";
	height: 35px;
	padding-left: 25px;
	padding-top: 50px;
	width: 1003px;
}

#box4 {
	font-family: "microsoft yahei";
	height: 50px;
	padding-left: 25px;
	padding-top: 25px;
}
</style>
</head>

<body
	<c:if test="${user.adminlevel == 5 }">onload="xueyuanSelect()"</c:if>>

	<div class="right_cont">
		<div class="title_right">
			<strong>登录账户管理</strong>
		</div>

		<div id="box1">
			<a
				href="${pageContext.request.contextPath}/user_totjdlPage.action?tnum=${user.tnum}"
				target="MainFrame">添加用户</a>

		</div>
		<c:if test="${user.adminlevel == 5 }">
			<div id="box3">

				<a id="t1" target="MainFrame"
					hidden="${pageContext.request.contextPath}/user_importUserExcel.action">批量导入</a>

			</div>
		</c:if>
		<form method="post"
			action="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}"
			id="dlzh">
			<div id="box2">
				<td>学院：</td>
				<c:if test="${user.adminlevel == 5 }">
					<td><select size="1" id="xueyuan" name="college.collegeid"
						onChange="selectSubmit()">
							<option value="-1" selected="selected">全部学院</option>
							<c:forEach items="${pageBean.collegelist}" var="college">
								<option value="${college.collegeid }"
									<c:if test="${newuser.college.collegeid == college.collegeid }">selected="selected"</c:if>>${college.collegeCname}</option>
							</c:forEach>
					</select></td>
				</c:if>
				<c:if test="${user.adminlevel != 5 }">
					<td><select size="1" id="xueyuan" name="college.collegeid"
						onChange="xueyuanSelect()">
							<c:forEach items="${pageBean.collegelist}" var="college">
								<option value="${college.collegeid }"
									<c:if test="${newuser.college.collegeid == college.collegeid }">selected="selected"</c:if>>${college.collegeCname}</option>
							</c:forEach>
					</select></td>
				</c:if>
				<td>专业：</td>
				<c:if test="${user.adminlevel != 1 }">
					<c:if test="${user.adminlevel == 5 }">
						<td><select size="1" id="xi" name="department.departmentid"
							onChange="selectSubmit()">
								<option value="-1" selected="selected">全部专业</option>
						</select></td>
					</c:if>
					<c:if test="${user.adminlevel == 3 }">
						<td><select size="1" id="xi" name="department.departmentid"
							onChange="selectSubmit()">
								<option value="-1" selected="selected">全部专业</option>
								<c:forEach items="${pageBean.departmentlist}" var="department">
									<option value="${department.departmentid }"
										<c:if test="${newuser.department.departmentid == department.departmentid }">selected="selected"</c:if>>${department.departmentCname}</option>
								</c:forEach>
						</select></td>
					</c:if>
				</c:if>
				<c:if test="${user.adminlevel == 1 }">
					<td><select size="1" id="xi" name="department.departmentid">
							<c:forEach items="${pageBean.departmentlist}" var="department">
								<option value="${department.departmentid }"
									<c:if test="${newuser.department.departmentid == department.departmentid }">selected="selected"</c:if>>${department.departmentCname}</option>
							</c:forEach>
					</select></td>
				</c:if>
				<td width="40%" align="right" nowrap="nowrap">教师姓名：</td>
				<td><input name="username" size="1"
					value="${newuser.username }" /></td>
				<tr>

					<td class="text-center" colspan="0"><input type="submit"
						value="检索" class="btn btn-info  " style="width: 50px;" /></td>
			</div>
		</form>
		<span Style="font-size: 15px; color: red; text-align: center;">${msg }</span>
		<div style="width: 70%; margin-left: 25px;">
			<form
				action="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.cepartmentid=${newuser.department.departmentid}"
				method="post" name="form3">
				<table class="table table-bordered table-striped table-hover">
					<tbody>
					<thead>
						<tr align="center">
							<th nowrap="nowrap"><small>教工号</small></th>
							<th nowrap="nowrap"><small>姓名</small></th>
							<th nowrap="nowrap"><small>性别</small></th>
							<th nowrap="nowrap"><small>部门（学院）</small></th>
							<th nowrap="nowrap"><small>专业</small></th>
							<th width="80" nowrap="nowrap"><small>操作</small></th>
						</tr>
					</thead>
					<c:forEach items="${pageBean.userlist }" var="users">
						<tr align="center" class="even">
							<td nowrap="nowrap"><small>${users.tnum }</small></td>
							<td nowrap="nowrap"><small>${users.username }</small></td>
							<td nowrap="nowrap"><small>${users.sex }</small></td>
							<td nowrap="nowrap"><small>${users.college.collegeCname }</small></td>
							<td nowrap="nowrap"><small>${users.department.departmentCname }</small></td>
							<td nowrap="nowrap"><c:if
									test="${users.adminlevel <= user.adminlevel  }">
									<a
										href="${pageContext.request.contextPath}/user_tobjdlPage.action?tnum=${users.tnum }&adminlevel=${user.adminlevel}"><small>编辑</small></a>
									<c:if test="${users.tnum ne sessionScope.user.tnum}">
										<a
											href="${pageContext.request.contextPath}/user_deluser.action?tnum=${users.tnum }"
											onclick="if (confirm('您确定要删除该用户吗？')) return true; else return false;"><small>删除</small></a>
									</c:if>
								</c:if> <c:if test="${user.adminlevel == 5}">
									<a
										href="${pageContext.request.contextPath}/user_resetpassword.action?tnum=${users.tnum }"
										onclick="if (confirm('您确定要重置该用户的密码吗？')) return true; else return false;"><small>重置密码</small></a>
								</c:if></td>
						</tr>
					</c:forEach>
					<c:if test="${pageBean.totalCount ==0}">
						<tr align="center">
							<th nowrap="nowrap" colspan="9"><small>没有符合要求的用户！</small></th>
						</tr>
					</c:if>
					<c:if test="${pageBean.totalPage != 1}">
						<tfoot>
							<tr align="center">
								<th nowrap="nowrap" colspan="9"><c:if
										test="${pageBean.currentpage != 1 }">
										<a
											href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}"><small>首页</small></a>&nbsp;&nbsp;&nbsp;
									<a
											href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}&currentpage=${currentpage-1 }"><small>上一页</small></a>
									</c:if>&nbsp;&nbsp;&nbsp; <small>共${pageBean.totalCount }条记录,共计${pageBean.totalPage }页,当前第${pageBean.currentpage }页</small>&nbsp;&nbsp;&nbsp;
									<c:if test="${pageBean.currentpage != pageBean.totalPage }">
										<a
											href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}&currentpage=${currentpage+1 }"><small>下一页</small></a>&nbsp;&nbsp;&nbsp;
									<a
											href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}&currentpage=${pageBean.totalPage }"><small>尾页</small></a>
									</c:if> <small> 第</small><input type="number" name="currentpage"
									onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
									class="span1" /><small>页 </small> <input type="submit"
									value="跳转">&nbsp;&nbsp;&nbsp;</th>
							</tr>
						</tfoot>
					</c:if>
					</tr>
					</tfoot>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div id="detail">
		<div class="tit">
			<input type="button" class="close" value="X" />
		</div>
		<div id="box4">

			<form name="form1"
				action="${pageContext.request.contextPath}/user_importUserExcel.action"
				enctype="multipart/form-data" method="post">
				<input type="file" name="excelFile" id="excelFile" /> <input
					type="button" id="queryBtn" value="导入">
			</form>


		</div>
	</div>
	<script type="text/javascript">
	$("#t1").click(function(){
			popWin("detail");
	});
	
	
	$("#queryBtn").click(function(){
            
            var files = document.getElementById('excelFile').files;
            if(files.length == 0){
                alert("请选择需要导入的文件.");
                document.form1.uploadFileCtrl.focus();
                return;
            }
            
            document.form1.submit();
        });
</script>
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
								if(str[0] == '${newuser.department.departmentid}')	//设置专业的默认选定值
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
		function selectSubmit()
		{
			var xueyuanid = document.getElementById("xueyuan").value;
			var xiid = document.getElementById("xi").value;
				var form=document.getElementById("dlzh");
				form.action="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}";
				form.submit();   
		}	
	</script>

</body>
</html>
