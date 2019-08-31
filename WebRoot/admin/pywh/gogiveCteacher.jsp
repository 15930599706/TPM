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
<script type="text/javascript" src="<%=basePath%>images/js/bui-min.js"></script>
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
	padding: 2px 0;
	text-align: center;
}

#box3 {
	height: 35px;
	float: left;
	width: 120px;
}

#box4 {
	font-family: "microsoft yahei";
	height: 50px;
	padding-left: 25px;
	padding-top: 25px;
}

#box3 a:hover {
	font-family: "microsoft yahei";
	color: #000;
	background-color: #D2E7F5;
	border: 1px solid #B1CCEB;
	width: 118px;
	height: 20px;
}

#box1 a:hover {
	font-family: "microsoft yahei";
	color: #000;
	background-color: #D2E7F5;
	border: 1px solid #B1CCEB;
	width: 120px;
	height: 20px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 120px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/171.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box1 {
	height: 40px;
	float: left;
	width: 160px;
}

#box2 {
	font-family: "microsoft yahei";
	height: 30px;
	float: left;
	width: 1200px;
	padding-top: 10px;
	padding-left: 24px;
}

#xuanke {
	font-family: "microsoft yahei";
	padding-left: 24px;
}
</style>
</head>

<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>课内实验分配信息</strong>
		</div>
		<div id="box2">
			<form
				action="${pageContext.request.contextPath}/theoreticalPlan_gogiveCteacher.action?curriculumid=${curriculum.curriculumid }"
				method="post" id="findcurriculum">
				<td>开课部门(学院)：</td>
				<td><select size="1" name="curriculum.college.collegeid"
					onchange="selectSubmit()">
						<c:forEach items="${pageBean.collegelist }" var="college">
							<option value="${college.collegeid}"
								<c:if test="${college.collegeid eq curriculum.college.collegeid }">selected="selected"</c:if>>${college.collegeCname}</option>
						</c:forEach>

				</select></td>
				<td>开课专业：</td>
				<td><select size="1" name="cteachDepartment.departmentid"
					onchange="selectSubmit()">
						<c:forEach items="${pageBean.departmentlist }" var="department">
							<option value="${department.departmentid}">${department.departmentCname}</option>
						</c:forEach>

				</select></td>
				<c:forEach items="${pageBean.professionallist}" var="professional">
					<c:if test="${professional.professionalname ne null}">
						<input type="hidden" name="depart"
							value="${professional.professionalid }">
					</c:if>
					<c:if test="${professional.professionalname eq null}">
						<input type="hidden" name="depart"
							value="${professional.department.departmentid }">
					</c:if>
				</c:forEach>
		</div>
		<div id="box2">
			<td width="40%" align="right" nowrap="nowrap">职工号：</td>
			<td><input size="1" name="teacher.tnum" class="span3"
				value="${id }" style="width: 110px" /></td>
			<td width="40%" align="right" nowrap="nowrap">老师姓名：</td>
			<td><input size="1" name="teacher.username" class="span3"
				value="${uname}" style="width: 110px" /></td>

			<tr>
				<td class="text-center" colspan="0"><input type="submit"
					value="检索" class="btn btn-info  " style="width: 50px;" /></td>
				<input type="hidden" name="curriculum.curriculumid"
					value="${curriculum.curriculumid}">
				</form>
		</div>
		<div id="box2">
			<td style="color: red;">分配课程：${curriculum.curriculumCname } <input
				type="hidden" name="curriculum.curriculumid"
				value="${curriculum.curriculumid}">
		</div>
		<div id="xuanke">
			<table style="font-size: 12px;">
				<td style="vertical-align: top">选课专业:</td>
				<td><c:forEach items="${pageBean.professionallist}"
						var="professional">
						<c:if test="${professional.professionalname ne null}">
							<li style="list-style-type: none;">${professional.department.departmentCname}——${professional.professionalname}</li>
							<input type="hidden" name="newchoosedepartlist"
								value="${professional.professionalid }">
						</c:if>

						<c:if test="${professional.professionalname eq null}">
							<li style="list-style-type: none;">${professional.department.departmentCname}</li>
							<input type="hidden" name="newchoosedepartlist"
								value="${professional.department.departmentid }">
						</c:if>
					</c:forEach></td>
			</table>
		</div>
		<div style="width: 90%; margin-left: 24px; padding-top: 10px;">

			<form method="post"
				action="${pageContext.request.contextPath}/theoreticalPlan_gogiveCteacher.action?curriculum.college.collegeid=${user.college.collegeid}&
			cteachDepartment.departmentid=<c:forEach items="${pageBean.departmentlist }" var="department">${department.departmentid}</c:forEach>&usertag=0&
			&teacher.tnum=${id}&teacher.username=${uname}<c:forEach items="${pageBean.professionallist}" var="professional"><c:if test="${professional.professionalname ne null}">&depart=${professional.professionalid }</c:if><c:if test="${professional.professionalname eq null}">&depart=${professional.department.departmentid}</c:if></c:forEach>">
				<table class="table table-bordered table-striped table-hover">

					<tbody>
					<thead>
						<tr align="center">

							<th nowrap="nowrap"><small>职工号</small></th>
							<th nowrap="nowrap"><small>教师姓名</small>
							</td>
							<th nowrap="nowrap"><small>操作</small></th>
						</tr>
					</thead>

					<c:forEach items="${pageBean.userlist }" var="nuser">
						<tr align="center">
							<td nowrap="nowrap"><small>${nuser.tnum }</small></td>
							<td nowrap="nowrap"><small>${nuser.username }</small></td>
							<td nowrap="nowrap"><small> <a
									href="${pageContext.request.contextPath}/theoreticalPlan_kctoteacherbydepart.action?
								curriculum.curriculumid=${curriculum.curriculumid }&cteacher.tnum=${nuser.tnum }&
								<c:forEach items="${pageBean.professionallist}" var="professional">
								<c:if test="${professional.professionalname ne null}">&depart=${professional.professionalid }</c:if><c:if test="${professional.professionalname eq null}">&depart=${professional.department.departmentid}</c:if></c:forEach>">分配任课老师</a>
							</small></td>
						</tr>
					</c:forEach>
					<c:if test="${pageBean.totalCount == 0 }">
						<tr align="center">
							<th nowrap="nowrap" colspan="11">没有符合您搜索要求的老师</th>
						</tr>
					</c:if>
					<tfoot>
						<tr align="center">
							<th nowrap="nowrap" colspan="11"><c:if
									test="${pageBean.currentpage != 1 }">
									<a
										href="${pageContext.request.contextPath}/theoreticalPlan_gogiveCteacher.action?curriculum.college.collegeid=${user.college.collegeid}&
									cteachDepartment.departmentid=<c:forEach items="${pageBean.departmentlist }" var="department">${department.departmentid}</c:forEach>&curriculum.curriculumid=${curriculum.curriculumid}&curriculum.curriculumCname=${curriculum.curriculumCname}&usertag=0&
									&cteacher.tnum=${id}&cteacher.username=${uname}<c:forEach items="${pageBean.professionallist}" var="professional"><c:if test="${professional.professionalname ne null}">&depart=${professional.professionalid }</c:if><c:if test="${professional.professionalname eq null}">&depart=${professional.department.departmentid}</c:if></c:forEach>"><small>首页</small>
									</a>&nbsp;&nbsp;&nbsp;
										<a
										href="${pageContext.request.contextPath}/theoreticalPlan_gogiveCteacher.action?currentpage=${currentpage-1 }&curriculum.college.collegeid=${user.college.collegeid}&
									cteachDepartment.departmentid=<c:forEach items="${pageBean.departmentlist }" var="department">${department.departmentid}</c:forEach>&curriculum.curriculumid=${curriculum.curriculumid}&curriculum.curriculumCname=${curriculum.curriculumCname}&usertag=0&
									&cteacher.tnum=${id}&cteacher.username=${uname}<c:forEach items="${pageBean.professionallist}" var="professional"><c:if test="${professional.professionalname ne null}">&depart=${professional.professionalid }</c:if><c:if test="${professional.professionalname eq null}">&depart=${professional.department.departmentid}</c:if></c:forEach>"><small>上一页</small>
									</a>
								</c:if>&nbsp;&nbsp;&nbsp; <small>共${pageBean.totalCount
								}条记录,共计${pageBean.totalPage }页,当前第${pageBean.currentpage }页</small>&nbsp;&nbsp;&nbsp;
								<c:if test="${pageBean.currentpage != pageBean.totalPage }">
									<a
										href="${pageContext.request.contextPath}/theoreticalPlan_gogiveCteacher.action?currentpage=${currentpage+1 }&curriculum.college.collegeid=${user.college.collegeid}&
									cteachDepartment.departmentid=<c:forEach items="${pageBean.departmentlist }" var="department">${department.departmentid}</c:forEach>
									&curriculum.curriculumid=${curriculum.curriculumid}&curriculum.curriculumCname=${curriculum.curriculumCname}&usertag=0
									&cteacher.tnum=${id}&cteacher.username=${uname}<c:forEach items="${pageBean.professionallist}" var="professional"><c:if test="${professional.professionalname ne null}">&depart=${professional.professionalid }</c:if><c:if test="${professional.professionalname eq null}">&depart=${professional.department.departmentid}</c:if></c:forEach>">
										<small>下一页</small>
									</a>&nbsp;&nbsp;&nbsp;
								<a
										href="${pageContext.request.contextPath}/theoreticalPlan_gogiveCteacher.action?currentpage=${pageBean.totalPage }&curriculum.college.collegeid=${user.college.collegeid}&
									cteachDepartment.departmentid=<c:forEach items="${pageBean.departmentlist }" var="department">${department.departmentid}</c:forEach>
									&curriculum.curriculumid=${curriculum.curriculumid}&curriculum.curriculumCname=${curriculum.curriculumCname}&usertag=0
									&cteacher.tnum=${id}&cteacher.username=${uname}<c:forEach items="${pageBean.professionallist}" var="professional"><c:if test="${professional.professionalname ne null}">&depart=${professional.professionalid }</c:if><c:if test="${professional.professionalname eq null}">&depart=${professional.department.departmentid}</c:if></c:forEach>">
										<small>尾页</small>
									</a>
								</c:if> <input type="hidden" name="curriculum.curriculumid"
								value="${curriculum.curriculumid }"> <input
								type="hidden" name="curriculum.curriculumCname"
								value="${curriculum.curriculumCname }"> <small>
									第</small><input type="number" name="currentpage"
								onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
								class="span1" /><small>页 </small> <input type="submit"
								value="跳转">&nbsp;&nbsp;&nbsp;</th>
						</tr>

					</tfoot>

					</tbody>
				</table>
			</form>
		</div>


		<script type="text/javascript">
	
	function selectSubmit()
		{
			var form=document.getElementById("findcurriculum");
			form.action="${pageContext.request.contextPath}/curriculum_gogiveCteacher.action?curriculumid=${curriculum.curriculumid }";
			form.submit();  
		}
	
</script>
</body>
</html>
