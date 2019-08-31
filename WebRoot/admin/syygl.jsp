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
	width: 105px;
	height: 20px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 105px;
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
	width: 90px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/import1-16.png) no-repeat 25px
		3px;
	text-indent: 40px;
	text-align: center;
}

#box3 a:hover {
	font-family: "microsoft yahei";
	color: #000;
	width: 120px;
	height: 20px;
}

#box1 {
	height: 40px;
	float: left;
	width: 110px;
}

#box3 {
	height: 35px;
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
        //清空实验室下拉框
			var experimentElement = document.getElementById("shiyanshi");
			experimentElement.options.length = 1;
			var xueyuanid = document.getElementById("xueyuan").value;
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
								if(str[0] == '${newuser.experiment.experimentid}')	//设置专业的默认选定值
								{
									option.selected = true;
								} 
								experimentElement.appendChild(option);

							}
						}
					}
				}
			}
			 
		}
		function selectSubmit()
		{
			//var xueyuanid = document.getElementById("xueyuan").value;
			//var xiid = document.getElementById("shiyanshi").value;
			var form=document.getElementById("dlzh");
			form.action="${pageContext.request.contextPath}/user_tosyyglPage.action?tnum=${user.tnum}";
			form.submit();   
		}	
		
				
		function tjsyy_check(){	
			var shiyanshiid = document.getElementById("shiyanshi").value;
			if(shiyanshiid != "-1")	//限定有选择时才会提交表单
			{
				var form=document.getElementById("dlzh");
				form.action="${pageContext.request.contextPath}/user_totjsyyPage.action?tnum=${user.tnum}";
				form.submit(); 
			} 
			else
			{
				alert("请选择实验室");
			}
		}
	

		
	</script>

<body onload="xueyuanSelect()">

	<div class="right_cont">
		<div class="title_right">
			<strong>实验员管理</strong>
		</div>

		<div id="box1">
			<a href="#" target="MainFrame" onclick="tjsyy_check()">添加实验员</a>
		</div>
		<form method="post"
			action="${pageContext.request.contextPath}/user_tosyyglPage.action?tnum=${user.tnum}"
			id="dlzh">
			<div id="box2">
				<td>学院：</td>
				<c:if test="${user.adminlevel == 5 }">
					<td><select size="1" id="xueyuan" name="college.collegeid"
						onChange="xueyuanSelect()">
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
				<td>实验室：</td>

				<td><select size="1" id="shiyanshi"
					name="experiment.experimentid" onChange="selectSubmit()">
						<option value="-1" selected="selected">全部实验室</option>
				</select></td>

				<td width="40%" align="right" nowrap="nowrap">教师姓名：</td>
				<td><input name="username" size="1"
					value="${newuser.username }" /></td>


				<td class="text-center" colspan="0"><input type="submit"
					value="检索" class="btn btn-info  " style="width: 50px;" /></td>
			</div>
		</form>
		<span Style="font-size: 15px; color: red; text-align: center;">${msg }</span>
		<div style="width: 70%; margin-left: 25px;">
			<form
				action="${pageContext.request.contextPath}/user_tosyyglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&experiment.experimentid=${newuser.experiment.experimentid}"
				method="post" name="form3">
				<table class="table table-bordered table-striped table-hover">
					<tbody>
					<thead>
						<tr align="center">
							<th nowrap="nowrap"><small>教工号</small></th>
							<th nowrap="nowrap"><small>姓名</small></th>
							<th nowrap="nowrap"><small>部门（学院）</small></th>
							<th nowrap="nowrap"><small>实验室</small></th>
							<th nowrap="nowrap"><small>职责</small></th>
							<th width="80" nowrap="nowrap"><small>操作</small></th>
						</tr>
					</thead>
					<c:forEach items="${pageBean.userlist }" var="users">
						<tr align="center" class="even">
							<td nowrap="nowrap"><small>${users.tnum }</small></td>
							<td nowrap="nowrap"><small>${users.username }</small></td>
							<td nowrap="nowrap"><small>${users.college.collegeCname }</small></td>
							<td nowrap="nowrap"><small>${users.experiment.experimentname }</small></td>
							<td nowrap="nowrap"><small><c:if
										test="${users.experimenterlevel == 1 }">实验员</c:if>
									<c:if test="${users.experimenterlevel == 3 }">实验室主任</c:if></small></td>
							<td nowrap="nowrap"><c:if
									test="${users.tnum ne sessionScope.user.tnum}">
									<a
										href="${pageContext.request.contextPath}/user_toeasyexperimenter.action?modifyid=${user.tnum}&tnum=${users.tnum }"
										onclick="if (confirm('您确定要将该用户权限设置成普通教师吗？')) return true; else return false;"><small>删除</small></a>
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
											href="${pageContext.request.contextPath}/user_tosyyglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&experiment.experimentid=${newuser.experiment.experimentid}"><small>首页</small></a>&nbsp;&nbsp;&nbsp;
									<a
											href="${pageContext.request.contextPath}/user_tosyyglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&experiment.experimentid=${newuser.experiment.experimentid}&currentpage=${currentpage-1 }"><small>上一页</small></a>
									</c:if>&nbsp;&nbsp;&nbsp; <small>共${pageBean.totalCount }条记录,共计${pageBean.totalPage }页,当前第${pageBean.currentpage }页</small>&nbsp;&nbsp;&nbsp;
									<c:if test="${pageBean.currentpage != pageBean.totalPage }">
										<a
											href="${pageContext.request.contextPath}/user_tosyyglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&experiment.experimentid=${newuser.experiment.experimentid}&currentpage=${currentpage+1 }"><small>下一页</small></a>&nbsp;&nbsp;&nbsp;
									<a
											href="${pageContext.request.contextPath}/user_tosyyglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&experiment.experimentid=${newuser.experiment.experimentid}&currentpage=${pageBean.totalPage }"><small>尾页</small></a>
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

</body>
</html>
