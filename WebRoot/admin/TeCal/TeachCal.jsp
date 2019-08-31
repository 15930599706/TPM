<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<script src="<%=basePath%>images/js/jquery-1.7.2.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>images/js/tc.min.js"></script>
<style type="text/css">
#box5 {
	font-family: "microsoft yahei";
	height: 70px;
	padding-left: 25px;
	padding-top: 15px;
}

#box1 a:hover {
	font-family: "microsoft yahei";
	color: #000;
	background-color: #D2E7F5;
	border: 1px solid #B1CCEB;
	width: 88px;
	height: 20px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 88px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #0000C6;
	background: url(<%=basePath%>images/img/add-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box1 {
	height: 60px;
	float: left;
	width: 100px;
}

#box2 {
	font-family: "microsoft yahei";
	height: 50px;
	float: left;
	width: 1000px;
	margin-left: 24px;
	margin-top: 10px;
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
	height: 100px;
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
	background: url(<%=basePath%>images/img/disk-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box8 {
	height: 30px;
	float: left;
	width: 100px;
}

#box6 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	width: 120px;
	height: 20px;
}

#box6 a {
	display: block;
	height: 20px;
	width: 120px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/import1-16.png) no-repeat 25px
		3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
	cursor: pointer
}

#box6 {
	height: 30px;
	float: left;
	width: 120px;
}

#box0 {
	height: 30px;
	float: left;
	width: 250px;
	padding-top: 1px;
}

#box7 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	width: 97px;
	height: 20px;
}

#box7 a {
	display: block;
	height: 20px;
	width: 97px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
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
	color: #000;
	background-color: #D2E7F5;
	border: 1px solid #B1CCEB;
	width: 188px;
	height: 20px;
}

#box3 a {
	display: block;
	height: 20px;
	width: 188px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #0000C6;
	background: url(<%=basePath%>images/img/disk-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box3 {
	height: 60px;
	float: left;
	width: 188px;
}

#box4 a:hover {
	font-family: "microsoft yahei";
	color: #000;
	background-color: #D2E7F5;
	border: 1px solid #B1CCEB;
	width: 188px;
	height: 20px;
}

#box4 a {
	display: block;
	height: 20px;
	width: 188px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #0000C6;
	background: url(<%=basePath%>images/img/disk-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box4 {
	height: 60px;
	float: left;
	width: 180px;
	padding-left: 20px;
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
								if(str[0] == '${newuserdepartid}')	//设置专业的默认选定值
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
				var form=document.getElementById("findcurriculum");
				form.action="${pageContext.request.contextPath}/teachCal_toTeachCalPage.action?user.tnum=${user.tnum}";
				form.submit();
		}	
	</script>
<body
	<c:if test="${sessionScope.user.adminlevel == 5 || sessionScope.user.adminlevel == 3}">onload="xueyuanSelect()"</c:if>>
	<div class="right_cont">
		<div class="title_right">
			<strong>教学日历管理</strong>
		</div>
		<div id="box8">
			<a
				href="${pageContext.request.contextPath}/teachCal_downSample.action"
				target="MainFrame">下载模板</a>
		</div>
		<div id="box6">
			<a id="t1" target="MainFrame">上传教学日历</a>

		</div>
		<div id="box0">
			<span style="color: red">（上传前请将文件名改为“xxx课程教学日历”）</span>
		</div>
		<div id="box2">
			<c:if
				test="${sessionScope.user.adminlevel == 5 || sessionScope.user.adminlevel == 3}">
				<form
					action="${pageContext.request.contextPath}/theoreticalPlan_todepartment.action"
					method="post" id="findcurriculum">
					<td>开课学院：</td>
					<td><select size="1" id="xueyuan"
						name="user.college.collegeid" onChange="selectSubmit()">
							<!-- onchange="selectSubmit()"       <c:if test="${college.collegeid eq curriculum.college.collegeid }">selected="selected"</c:if> -->
							<c:if test="${sessionScope.user.adminlevel == 5}">
								<option value="-1">请选择学院</option>
							</c:if>
							<c:forEach items="${pageBean.collegelist }" var="college">
								<option value="${college.collegeid}"
									<c:if test="${newusercollegeid == college.collegeid }">selected="selected"</c:if>>${college.collegeCname}</option>
							</c:forEach>
					</select></td>

					<td>专业：</td>
					<td><select size="1" id="xi"
						name="user.department.departmentid" onChange="selectSubmit()">
							<option value="-1" selected="selected">全部专业</option>
					</select></td>
				</form>
			</c:if>
		</div>

		<div style="width: 50%; margin-left: 24px;">
			<form
				action="${pageContext.request.contextPath}/teachCal_toTeachCalPage.action?user.tnum=${user.tnum}&user.college.collegeid=${newusercollegeid}&user.department.departmentid=${newuserdepartid}"
				method="post" name="form3">
				<table class="table table-bordered table-striped table-hover">

					<tbody>
					<thead>
						<tr align="center">
							<th nowrap="nowrap"><small>标题</small></th>
							<th nowrap="nowrap"><small>作者</small></th>
							<th nowrap="nowrap"><small>日期</small></th>
							<th nowrap="nowrap"><small>删除</small></th>
						</tr>
					</thead>
					<c:forEach items="${pageBean.teachCallist}" var="teachCal"
						varStatus="xh">
						<tr align="center">
							<td nowrap="nowrap"><small><a
									href="${pageContext.request.contextPath}/teachCal_downLoadFile.action?teachCalfileid=${teachCal.teachCalfileid}">${teachCal.teachCalfilename }
								</a></small></td>
							<td nowrap="nowrap"><small>${teachCal.user.username}</small></td>
							<td nowrap="nowrap"><small>${teachCal.teachCalfiletime }</small></td>

							<td nowrap="nowrap"><a
								href="${pageContext.request.contextPath}/teachCal_delTeachCalPage.action?teachCalfileid=${teachCal.teachCalfileid}"><small>删除</small></a>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${pageBean.totalCount == 0 }">
						<tr align="center">
							<th nowrap="nowrap" colspan="11">无人上传教学日历</th>
						</tr>
					</c:if>
					<c:if test="${pageBean.totalCount != 0 }">
						<tfoot>
							<tr align="center">
								<th nowrap="nowrap" colspan="9"><input type="hidden"
									name="pageCount" value="" /> <input type="hidden" name="page"
									value="" /> <c:if test="${pageBean.currentpage != 1}">
										<a
											href="${pageContext.request.contextPath}/teachCal_toTeachCalPage.action?user.tnum=${user.tnum}&user.college.collegeid=${newusercollegeid}&user.department.departmentid=${newuserdepartid}"
											onClick="top2()"><small>首页</small></a>&nbsp;&nbsp;&nbsp;
										<a
											href="${pageContext.request.contextPath}/teachCal_toTeachCalPage.action?user.tnum=${user.tnum}&currentpage=${currentpage-1 }&user.college.collegeid=${newusercollegeid}&user.department.departmentid=${newuserdepartid}"
											onClick="pre2()"><small>上一页</small></a>&nbsp;&nbsp;&nbsp;
										</c:if> <small>共${pageBean.totalCount }条记录,共计${pageBean.totalPage }页,当前第${pageBean.currentpage }页</small>&nbsp;&nbsp;&nbsp;
									<c:if test="${pageBean.currentpage != pageBean.totalPage}">
										<a
											href="${pageContext.request.contextPath}/teachCal_toTeachCalPage.action?user.tnum=${user.tnum}&currentpage=${currentpage+1 }&user.college.collegeid=${newusercollegeid}&user.department.departmentid=${newuserdepartid}"
											onClick="next2()"><small>下一页</small></a>&nbsp;&nbsp;&nbsp;
											<a
											href="${pageContext.request.contextPath}/teachCal_toTeachCalPage.action?user.tnum=${user.tnum}&currentpage=${pageBean.totalPage}&user.college.collegeid=${newusercollegeid}&user.department.departmentid=${newuserdepartid}"
											onClick="last2()"><small>尾页</small></a>
									</c:if> <small> 第</small><input type="number" name="currentpage"
									onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
									class="span1" /><small>页 </small> <input type="submit"
									value="跳转">&nbsp;&nbsp;&nbsp;</th>
							</tr>
						</tfoot>
					</c:if>
					</tbody>

				</table>
			</form>
		</div>
	</div>

	<div id="detail">
		<div class="tit">
			<input type="button" class="close" value="X" />
		</div>
		<div id="box5">
			<form name="upfile"
				action="${pageContext.request.contextPath}/teachCal_addteachCal.action"
				method="post" enctype="multipart/form-data">
				<input type="hidden" name="user.tnum" value="${user.tnum }"></input>
				<span style="color: red;">上传文件大小需小于50M！</span></br> <input type="file"
					name="upload" required style="padding-top: 6px;" /> <input
					id="upfile" type="submit" value="上传" required
					style="margin-top: 6px;" />
			</form>
		</div>
		<script type="text/javascript">
	$("#t1").click(function(){
			popWin("detail");
	});
</script>
</body>