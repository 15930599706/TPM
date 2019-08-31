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
<style type="text/css">
#nav, #nva ul {
	padding: 0;
	margin: 0;
	list-style: none;
}

#nav li {
	float: left;
	position: relative;
	width: 10em;
	border: 1px solid #ccc;
	background-color: #fff;
	color: #000;
	font-size: 85%;
}

* html #nav a {
	width: 100%
}

#nav ul {
	display: none;
	position: absolute;
	padding: 0;
}

#nav ul li {
	border-bottom: 1px solid #ddd;
	border-top: 1px solid #fff;
	border-left: 1px solid #ddd;
	border-right: 1px solid #ddd;
	font-size: 100%;
	margin-bottom: -1px;
	margin-top: 1px;
	padding: 0;
	list-style-type: none;
}

#nav li:hover ul {
	display: block;
}

#nav li:hover {
	cursor: pointer;
}

.check {
	float: left;
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
	height: 50px;
	float: left;
	width: 1200px;
	padding-top: 5px;
	padding-left: 24px;
}
</style>
</head>
<script language="javascript">	
		function selectSubmit()	//表单提交
			{
					var form=document.getElementById("findcurriculum");
					form.action="${pageContext.request.contextPath}/theoreticalPlan_tokcfpPage.action";
					form.submit();   
			}
	</script>
<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>理论课程分配信息</strong>
		</div>
		<div id="box2">
			<form
				action="${pageContext.request.contextPath}/theoreticalPlan_tokcfpPage.action"
				method="post" id="findcurriculum">
				<td>开课部门(学院)：</td>
				<td><select size="1" name="curriculum.college.collegeid"
					onChange="selectSubmit()">
						<c:forEach items="${pageBean.collegelist }" var="college">
							<option value="${college.collegeid}"
								<c:if test="${college.collegeid == cid}">selected="selected"</c:if>>${college.collegeCname}</option>
						</c:forEach>
				</select></td>

				<td width="40%" align="right" nowrap="nowrap">课程编号：</td>
				<td><input size="1" name="curriculum.curriculumid"
					class="span3" value="${id}" style="width: 110px;" /></td>
				<td width="40%" align="right" nowrap="nowrap">课程名称：</td>
				<td><input size="1" name="curriculum.curriculumCname"
					class="span3" value="${cname}" style="width: 150px;" /></td>
				<tr>
					<td class="text-center" colspan="0"><input type="submit"
						value="检索" class="btn btn-info  " style="width: 50px;" /></td>
			</form>
		</div>
		<div style="width: 90%; margin-left: 24px;">

			<table class="table table-bordered table-striped table-hover">

				<tbody>
				<thead>
					<tr align="center">

						<th nowrap="nowrap"><small>课程代码</small></th>
						<th nowrap="nowrap"><small>课程中文名称</small>
						</td>
						<th nowrap="nowrap"><small>课程性质</small></th>
						<th nowrap="nowrap"><small>课程类别</small></th>
						<th nowrap="nowrap"><small>开课学院</small></th>
						<th nowrap="nowrap"><small>选课专业——任课老师</small></th>
					</tr>
				</thead>

				<c:forEach items="${pageBean.curriculumlist }" var="curriculum"
					varStatus="xh">
					<tr align="center">
						<%-- <form method="post"  id="like${xh.count}" name="formlike${xh.count }"> --%>
						<td nowrap="nowrap"><small>${curriculum.curriculumid }</small></td>
						<td nowrap="nowrap"><small>${curriculum.curriculumCname }</small></td>
						<td nowrap="nowrap"><small>${curriculum.natureOfCourse.natureOfCoursename }</small></td>
						<td nowrap="nowrap"><small>${curriculum.courseLei }</small></td>
						<td nowrap="nowrap"><small>${curriculum.college.collegeCname }</small></td>
						<td nowrap="nowrap">
							<form method="post" id="like${xh.count}"
								name="formlike${xh.count }" style="margin: auto;">
								<select name="depart" size="1">
									<c:forEach
										items="${pageBean.theoreticalLessonlist[xh.count-1]}"
										var="theoreticalLesson">

										<c:if test="${theoreticalLesson.theoreticalLessonid ne null}">
											<c:if test="${theoreticalLesson.teacher.username ne null}">
												<c:if
													test="${theoreticalLesson.professional.professionalname ne null}">
													<option
														value="${theoreticalLesson.professional.professionalid}">${theoreticalLesson.department.departmentCname}（${theoreticalLesson.professional.professionalname}）——${theoreticalLesson.teacher.username }</option>
												</c:if>
												<c:if
													test="${theoreticalLesson.professional.professionalname eq null}">
													<option
														value="${theoreticalLesson.department.departmentid}">${theoreticalLesson.department.departmentCname}——${theoreticalLesson.teacher.username }</option>
												</c:if>
											</c:if>
											<c:if test="${theoreticalLesson.teacher.username eq null}">
												<c:if
													test="${theoreticalLesson.professional.professionalname ne null}">
													<option
														value="${theoreticalLesson.professional.professionalid}">${theoreticalLesson.department.departmentCname}（${theoreticalLesson.professional.professionalname}）——未分配！</option>
												</c:if>
												<c:if
													test="${theoreticalLesson.professional.professionalname eq null}">
													<option
														value="${theoreticalLesson.department.departmentid}">${theoreticalLesson.department.departmentCname}——未分配！</span></option>
												</c:if>
											</c:if>
										</c:if>
										<c:if test="${theoreticalLesson.theoreticalLessonid eq null}">
											<option value="#">无选课学院</option>
										</c:if>

									</c:forEach>

								</select> <input type="hidden" name="curriculum.curriculumid"
									value="${curriculum.curriculumid }" />
							</form>
						</td>
						<!-- </form> -->

					</tr>
				</c:forEach>
				<c:if test="${pageBean.totalCount == 0 }">
					<tr align="center">
						<th nowrap="nowrap" colspan="11">没有符合您搜索要求的课程</th>
					</tr>
				</c:if>
				<form method="post" name="form11"
					action="${pageContext.request.contextPath}/theoreticalPlan_tokcfpPage.action?curriculum.college.collegeid=
						${cid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}
						&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=0">
					<tfoot>
						<tr align="center">
							<th nowrap="nowrap" colspan="11"><c:if
									test="${pageBean.currentpage != 1 }">
									<a
										href="${pageContext.request.contextPath}/theoreticalPlan_tokcfpPage.action?curriculum.college.collegeid=${cid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}"><small>首页</small></a>&nbsp;&nbsp;&nbsp;
										<a
										href="${pageContext.request.contextPath}/theoreticalPlan_tokcfpPage.action?currentpage=${currentpage-1 }&curriculum.college.collegeid=${cid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}"><small>上一页</small></a>
								</c:if>&nbsp;&nbsp;&nbsp; <small>共${pageBean.totalCount }条记录,共计${pageBean.totalPage }页,当前第${pageBean.currentpage }页</small>&nbsp;&nbsp;&nbsp;
								<c:if test="${pageBean.currentpage != pageBean.totalPage }">
									<a
										href="${pageContext.request.contextPath}/theoreticalPlan_tokcfpPage.action?currentpage=${currentpage+1 }&curriculum.college.collegeid=${cid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}"><small>下一页</small></a>&nbsp;&nbsp;&nbsp;
										<a
										href="${pageContext.request.contextPath}/theoreticalPlan_tokcfpPage.action?currentpage=${pageBean.totalPage }&curriculum.college.collegeid=${cid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}"><small>尾页</small></a>
								</c:if> <small> 第</small><input type="number" name="currentpage"
								onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
								class="span1" /><small>页 </small> <input type="submit"
								value="跳转">&nbsp;&nbsp;&nbsp;</th>
						</tr>

					</tfoot>
				</form>

				</tbody>
			</table>

		</div>


		<script language="javascript">
		function selectonSubmit(value)
		{	
			var name = "like"+value;
			/* alert(name); */
			var form=document.getElementById(name);
			/* alert(form); */
			form.action="${pageContext.request.contextPath}/theoreticalPlan_kctousercollege.action?teacher.tnum&teacher.username";
			form.submit();  
		}
</script>
</body>
</html>
