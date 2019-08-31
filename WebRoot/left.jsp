<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<HTML>

<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath%>images/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath%>images/css/css.css" />
<script type="text/javascript" src="<%=basePath%>images/js/sdmenu.js"></script>
<script type="text/javascript" src="<%=basePath%>images/js/jquery.js"></script>
<link rel="stylesheet" href="<%=basePath%>images/css/screen.css"
	media="screen" />
<link rel="stylesheet"
	href="<%=basePath%>images/css/jquery.treetable.css" />
<link rel="stylesheet"
	href="<%=basePath%>images/css/jquery.treetable.theme.default.css" />
<style type="text/css">
* {
	moz-user-select: -moz-none;
	-moz-user-select: none;
	-o-user-select: none;
	-khtml-user-select: none;
	-webkit-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

a {
	color: #0087E8;
}

a:hover {
	text-decoration: none;
	color: #f37028;
}

a:active {
	color: #f37028;
	text-decoration: none;
}

a:focus {
	color: #f37028;
	text-decoration: none;
}

#mid {
	background-color: #E0E0E0;
}
/*	
			#long {
				cursor: w-resize;
				position: absolute;
				height: 100%;
				width: 100%;
			}
			*/
#pointer {
	cursor: pointer;
	position: absolute;
	height: 30px;
	width: 100%;
	top: 50%;
}

#long:hover, #pointer:hover {
	background-color: #FFCC00;
}
</style>
</HEAD>

<body style="overflow: hidden;">
	<div id="left" class="left" style="overflow: auto; width: 252px;">
		<div id="main" style="width: 255px;">
			<table id="example-advanced">
				<tbody>
					<tr data-tt-id='3'>
						<td><span class='folder'><a
								href="${pageContext.request.contextPath}/user_tomainPage.action"
								target="MainFrame">培养计划管理系统</a></span></td>
					</tr>
					<c:if test="${empty StuID }">
					<tr data-tt-id='3-1' data-tt-parent-id='3'>
						<td><span class='folder'>个人信息维护</span></td>
					</tr>
					<tr data-tt-id='3-1-1' data-tt-parent-id='3-1'>
						<td><span class='file'><a
								href="user_toeditpwdPage.action" target="MainFrame">修改登录密码</a></span></td>
					</tr>
					<c:if test="${user.adminlevel != 0 || user.experimenterlevel == 3}">
						<tr data-tt-id='4-1' data-tt-parent-id='3'>
							<td><span class='folder'>管理员任务</span></td>
						</tr>
						<c:if test="${user.adminlevel == 5 }">
							<tr data-tt-id='4-1-1' data-tt-parent-id='4-1'>
								<td><span class='folder'>信息导入</span></td>
							</tr>

							<tr data-tt-id='4-1-1-1' data-tt-parent-id='4-1-1'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/user_togetInPage.action?tnum=${user.tnum}"
										target="MainFrame">信息导入</a></span></td>
							</tr>
						</c:if>
						<c:if test="${user.adminlevel > 0}">
							<tr data-tt-id='4-1-2' data-tt-parent-id='4-1'>
								<td><span class='folder'>登录用户管理</span></td>
							</tr>

							<c:if test="${user.adminlevel == 5 }">
								<tr data-tt-id='4-1-2-1' data-tt-parent-id='4-1-2'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}"
											target="MainFrame">登录账户管理</a></span></td>
								</tr>
								<tr data-tt-id='4-1-2-2' data-tt-parent-id='4-1-2'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/user_toxxglPage.action?username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}"
											target="MainFrame">学校管理员管理</a></span></td>
								</tr>
								<tr data-tt-id='4-1-2-3' data-tt-parent-id='4-1-2'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/user_toxyglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}"
											target="MainFrame">学院管理员管理</a></span></td>
								</tr>
								<tr data-tt-id='4-1-2-4' data-tt-parent-id='4-1-2'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/user_toxglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}"
											target="MainFrame">专业管理员管理</a></span></td>
								</tr>


								<tr data-tt-id='4-1-2-5' data-tt-parent-id='4-1-2'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/user_tosyszrglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&experiment.experimentid=${newuser.experiment.experimentid}"
											target="MainFrame">实验室主任管理</a></span></td>
								</tr>
								<tr data-tt-id='4-1-2-6' data-tt-parent-id='4-1-2'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/user_tosyyglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&experiment.experimentid=${newuser.experiment.experimentid}"
											target="MainFrame">实验员管理</a></span></td>
								</tr>
							</c:if>

							<c:if test="${user.adminlevel == 3 }">
								<tr data-tt-id='4-1-2-1' data-tt-parent-id='4-1-2'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${newuser.department.departmentid}"
											target="MainFrame">登录账户管理</a></span></td>
								</tr>
								<tr data-tt-id='4-1-2-3' data-tt-parent-id='4-1-2'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/user_toxyglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${newuser.department.departmentid}"
											target="MainFrame">学院管理员管理</a></span></td>
								</tr>
								<tr data-tt-id='4-1-2-4' data-tt-parent-id='4-1-2'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/user_toxglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${newuser.department.departmentid}"
											target="MainFrame">专业管理员管理</a></span></td>
								</tr>


								<tr data-tt-id='4-1-2-5' data-tt-parent-id='4-1-2'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/user_tosyszrglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&experiment.experimentid=${newuser.experiment.experimentid}"
											target="MainFrame">实验室主任管理</a></span></td>
								</tr>
								<tr data-tt-id='4-1-2-6' data-tt-parent-id='4-1-2'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/user_tosyyglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&experiment.experimentid=${newuser.experiment.experimentid}"
											target="MainFrame">实验员管理</a></span></td>
								</tr>
							</c:if>
							<c:if test="${user.adminlevel == 1 }">
								<tr data-tt-id='4-1-2-1' data-tt-parent-id='4-1-2'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${user.department.departmentid}"
											target="MainFrame">登录账户管理</a></span></td>
								</tr>
								<tr data-tt-id='4-1-2-4' data-tt-parent-id='4-1-2'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/user_toxglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${user.department.departmentid}"
											target="MainFrame">专业管理员管理</a></span></td>
								</tr>
							</c:if>

						</c:if>

						<c:if test="${user.adminlevel == 5 }">
							<tr data-tt-id='4-1-3' data-tt-parent-id='4-1'>
								<td><span class='folder'>课程分配管理</span></td>
							</tr>
							<c:if test="${user.experimenterlevel == 3}">
								<tr data-tt-id='4-1-3-1' data-tt-parent-id='4-1-3'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/theoreticalPlan_exptouserdepartment.action?curriculum.college.collegeid=00&experiment.experimentid=${user.experiment.experimentid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&usertag=0&teacher.tnum=${id}&teacher.username=${uname}"
											target="MainFrame">课内实验分配到实验员</a></span></td>
								</tr>
							</c:if>
							<tr data-tt-id='4-1-3-2' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/theoreticalPlan_tokcfpPage.action?curriculum.college.collegeid=01&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}"
										target="MainFrame">理论课程分配情况</a></span></td>
							</tr>
							<tr data-tt-id='4-1-3-3' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/practicePlan_prctokcfpPage.action?curriculum.college.collegeid=01&curriculum.curriculumid=${practiceLesson.curriculum.curriculumid}&curriculum.curriculumCname=${practiceLesson.curriculum.curriculumCname}"
										target="MainFrame">实践课程分配情况</a></span></td>
							</tr>
						</c:if>

						<c:if test="${user.adminlevel == 3 }">
							<tr data-tt-id='4-1-3' data-tt-parent-id='4-1'>
								<td><span class='folder'>课程分配管理</span></td>
							</tr>
							<tr data-tt-id='4-1-3-1' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/theoreticalPlan_todepartment.action?curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=0"
										target="MainFrame">分配理论课程到专业</a></span></td>
							</tr>
							<tr data-tt-id='4-1-3-2' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/practicePlan_prctodepartment.action?curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${practiceLesson.curriculum.curriculumid}&curriculum.curriculumCname=${practiceLesson.curriculum.curriculumCname}&departmenttag=0"
										target="MainFrame">分配实践课程到专业</a></span></td>
							</tr>
							<tr data-tt-id='4-1-3-3' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/theoreticalPlan_tousercollege.action?tnum=${user.tnum }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=0"
										target="MainFrame">分配理论课程到老师</a></span></td>
							</tr>
							<tr data-tt-id='4-1-3-10' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/practicePlan_prctoExpdepartment.action?curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${practiceLesson.curriculum.curriculumid}&curriculum.curriculumCname=${practiceLesson.curriculum.curriculumCname}&departmenttag=0"
										target="MainFrame">分配实践课程到实验室</a></span></td>
							</tr>
							<tr data-tt-id='4-1-3-11' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/practicePlan_prctoExpusercollege.action?tnum=${user.tnum }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${practiceLesson.curriculum.curriculumid}&curriculum.curriculumCname=${practiceLesson.curriculum.curriculumCname}&departmenttag=0"
										target="MainFrame">分配实践课程到实验员</a></span></td>
							</tr>

							<tr data-tt-id='4-1-3-4' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/practicePlan_prctousercollege.action?tnum=${user.tnum }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=0"
										target="MainFrame">分配实践课程到老师</a></span></td>
							</tr>
							<tr data-tt-id='4-1-3-5' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/theoreticalPlan_toExpdepartment.action?curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=0"
										target="MainFrame">分配课内实验到实验室</a></span></td>
							</tr>
							<tr data-tt-id='4-1-3-6' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/theoreticalPlan_toExpusercollege.action?tnum=${user.tnum }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=0"
										target="MainFrame">分配课内实验到实验员</a></span></td>
							</tr>
							<tr data-tt-id='4-1-3-7' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/theoreticalPlan_toExpdepart.action?curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=0"
										target="MainFrame">分配课内实验到专业</a></span></td>
							</tr>
							<tr data-tt-id='4-1-3-8' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/theoreticalPlan_toExpuser.action?tnum=${user.tnum }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=0"
										target="MainFrame">分配课内实验到老师</a></span></td>
							</tr>

							<c:if test="${user.experimenterlevel == 3}">
								<tr data-tt-id='4-1-3-9' data-tt-parent-id='4-1-3'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/theoreticalPlan_exptouserdepartment.action?curriculum.college.collegeid=00&experiment.experimentid=${user.experiment.experimentid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&usertag=0&teacher.tnum=${id}&teacher.username=${uname}"
											target="MainFrame">课内实验分配到实验员</a></span></td>
								</tr>
							</c:if>

						</c:if>

						<c:if test="${user.adminlevel == 1 }">
							<tr data-tt-id='4-1-3' data-tt-parent-id='4-1'>
								<td><span class='folder'>课程分配管理</span></td>
							</tr>
							<tr data-tt-id='4-1-3-1' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/theoreticalPlan_touserdepartment.action?curriculum.college.collegeid=${user.college.collegeid}&teachDepartment.departmentid=${user.department.departmentid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&usertag=0&teacher.tnum=${id}&teacher.username=${uname}"
										target="MainFrame">分配理论课程到老师</a></span></td>
							</tr>
							<tr data-tt-id='4-1-3-2' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/practicePlan_touserdepartment.action?curriculum.college.collegeid=${user.college.collegeid}&teachDepartment.departmentid=${user.department.departmentid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&usertag=0&teacher.tnum=${id}&teacher.username=${uname}"
										target="MainFrame">分配实践课程到老师</a></span></td>
							</tr>
							<tr data-tt-id='4-1-3-3' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/theoreticalPlan_tousercollege.action?tnum=${user.tnum }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=0"
										target="MainFrame">查看理论课分配情况</a></span></td>
							</tr>
							<tr data-tt-id='4-1-3-4' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/practicePlan_prctousercollege.action?tnum=${user.tnum }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=0"
										target="MainFrame">查看实践课分配情况</a></span></td>
							</tr>
							<tr data-tt-id='4-1-3-6' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/theoreticalPlan_toExpteacher.action?curriculum.college.collegeid=${user.college.collegeid}&teachDepartment.departmentid=${user.department.departmentid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&usertag=0&teacher.tnum=${id}&teacher.username=${uname}"
										target="MainFrame">分配课内实验到老师</a></span></td>
							</tr>
							<c:if test="${user.experimenterlevel == 3}">
								<tr data-tt-id='4-1-3-5' data-tt-parent-id='4-1-3'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/theoreticalPlan_exptouserdepartment.action?curriculum.college.collegeid=00&experiment.experimentid=${user.experiment.experimentid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&usertag=0&teacher.tnum=${id}&teacher.username=${uname}"
											target="MainFrame">课内实验分配到实验员</a></span></td>
								</tr>
								<%--李艳李婧    2018-07-29--%>
								<tr data-tt-id='4-1-3-7' data-tt-parent-id='4-1-3'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/practicePlan_prctoExpuser.action?tnum=${user.tnum }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${practiceLesson.curriculum.curriculumid}&curriculum.curriculumCname=${practiceLesson.curriculum.curriculumCname}&departmenttag=0"
											target="MainFrame">实践课程分配到实验员</a></span></td>
								</tr>
							</c:if>
						</c:if>

						<c:if test="${user.adminlevel == 0 }">
							<tr data-tt-id='4-1-3' data-tt-parent-id='4-1'>
								<td><span class='folder'>课程分配管理</span></td>
							</tr>
							<tr data-tt-id='4-1-3-1' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/theoreticalPlan_exptouserdepartment.action?curriculum.college.collegeid=00&experiment.experimentid=${user.experiment.experimentid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&usertag=0&teacher.tnum=${id}&teacher.username=${uname}"
										target="MainFrame">课内实验分配到实验员</a></span></td>
							</tr>

							<%--李艳李婧    2018-07-29--%>
							<tr data-tt-id='4-1-3-2' data-tt-parent-id='4-1-3'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/practicePlan_prctoExpuser.action?tnum=${user.tnum }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${practiceLesson.curriculum.curriculumid}&curriculum.curriculumCname=${practiceLesson.curriculum.curriculumCname}&departmenttag=0"
										target="MainFrame">实践课程分配到实验员</a></span></td>
							</tr>

						</c:if>


						<c:if test="${user.adminlevel == 5 }">
							<tr data-tt-id='4-1-4' data-tt-parent-id='4-1'>
								<td><span class='folder'>通知下载管理</span></td>
							</tr>
							<tr data-tt-id='4-1-4-1' data-tt-parent-id='4-1-4'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/notice_totzfbPage.action"
										target="MainFrame">通知发布管理</a></span></td>
							</tr>
							<tr data-tt-id='4-1-4-2' data-tt-parent-id='4-1-4'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/noticeFile_tozyxzPage.action"
										target="MainFrame">下载资源管理</a></span></td>
							</tr>
						</c:if>


						<c:if test="${user.adminlevel > 0 }">
							<tr data-tt-id='4-1-5' data-tt-parent-id='4-1'>
								<td><span class='folder'>培养计划管理</span></td>
							</tr>
							<c:if test="${user.adminlevel == 1 }">
								<tr data-tt-id='4-1-5-2' data-tt-parent-id='4-1-5'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/ppTrainingConcept_tobjzyPage.action?department.departmentid=${user.department.departmentid }"
											target="MainFrame">专业人才培养理念</a></span></td>
								</tr>
								<tr data-tt-id='4-1-5-7' data-tt-parent-id='4-1-5'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/topology_toViewTopologyPage.action?department.departmentid=${user.department.departmentid }"
											target="MainFrame">主干课程拓扑图</a></span></td>
								</tr>
								<tr data-tt-id='4-1-5-8' data-tt-parent-id='4-1-5'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/theoreticalPlan_toExportPlanPage.action?tnum=${user.tnum }"
											target="MainFrame">生成培养计划</a></span></td>
								</tr>
								<tr data-tt-id='4-1-5-9' data-tt-parent-id='4-1-5'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/analysis_tobyyqPage.action?departmentid=${user.department.departmentid }"
											target="MainFrame">毕业要求及指标分解</a></span></td>
								</tr>
								<tr data-tt-id='4-1-5-10' data-tt-parent-id='4-1-5'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/curriculumMatrix_tobjjzpage.action?departmentid=${user.department.departmentid}"
											target="MainFrame">专业课程矩阵</a></span></td>
								</tr>
							</c:if>
							<c:if test="${user.adminlevel != 1 }">
								<tr data-tt-id='4-1-5-1' data-tt-parent-id='4-1-5'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/ptBasicInformation_topyxxPage.action?tnum=${user.tnum }&collegeid=-1"
											target="MainFrame">培养计划基本信息</a></span></td>
								</tr>

								<tr data-tt-id='4-1-5-2' data-tt-parent-id='4-1-5'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/ppTrainingConcept_tozypyPage.action?tnum=${user.tnum }&collegeid=-1"
											target="MainFrame">专业人才培养理念</a></span></td>
								</tr>
								<tr data-tt-id='4-1-5-3' data-tt-parent-id='4-1-5'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/mainTainOfPT_topyapPage.action?tnum=${user.tnum }&collegeid=-1"
											target="MainFrame">培养计划总体安排表</a></span></td>
								</tr>
								<tr data-tt-id='4-1-5-4' data-tt-parent-id='4-1-5'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/TrainingAnother_toanother.action?tnum=${user.tnum }&collegeid=-1"
											target="MainFrame">课外安排与要求</a></span></td>
								</tr>
								<tr data-tt-id='4-1-5-5' data-tt-parent-id='4-1-5'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/theoreticalPlan_tollkPage.action?tnum=${user.tnum }"
											target="MainFrame">理论课管理</a></span></td>
								</tr>
								<tr data-tt-id='4-1-5-6' data-tt-parent-id='4-1-5'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/practicePlan_tojzsjkPage.action?tnum=${user.tnum }"
											target="MainFrame">集中实践课管理</a></span></td>
								</tr>

								<tr data-tt-id='4-1-5-7' data-tt-parent-id='4-1-5'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/topology_tozgkpPage.action?tnum=${user.tnum }&collegeid=-1"
											target="MainFrame">主干课程拓扑图</a></span></td>
								</tr>
								<tr data-tt-id='4-1-5-8' data-tt-parent-id='4-1-5'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/theoreticalPlan_toExportPlanPage.action?tnum=${user.tnum }"
											target="MainFrame">生成培养计划</a></span></td>

								</tr>
								<tr data-tt-id='4-1-5-9' data-tt-parent-id='4-1-5'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/analysis_tobyyq.action?tnum=${user.tnum }&collegeid=-1"
											target="MainFrame">毕业要求及指标分解</a></span></td>
								</tr>
								<tr data-tt-id='4-1-5-10' data-tt-parent-id='4-1-5'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/curriculumMatrix_tozyjzpage.action?tnum=${user.tnum }&collegeid=-1"
											target="MainFrame">专业课程矩阵</a></span></td>
								</tr>
							</c:if>

							<tr data-tt-id='4-1-6' data-tt-parent-id='4-1'>
								<td><span class='folder'>课程信息维护</span></td>
							</tr>
							<c:if test="${user.adminlevel == 1 }">
								<tr data-tt-id='4-1-6-3' data-tt-parent-id='4-1-6'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/curriculum_tokcxxPage.action?college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}"
											target="MainFrame">课程信息维护</a></span></td>
								</tr>
							</c:if>
							<c:if test="${user.adminlevel != 1 }">
								<tr data-tt-id='4-1-6-3' data-tt-parent-id='4-1-6'>
									<td><span class='file'><a
											href="${pageContext.request.contextPath}/curriculum_tokcxxPage.action?college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}"
											target="MainFrame">课程信息维护</a></span></td>
								</tr>
							</c:if>

						</c:if>
						<c:if test="${user.adminlevel > 1 }">
							<tr data-tt-id='4-1-7' data-tt-parent-id='4-1'>
								<td><span class='folder'>错误发现与追踪</span></td>
							</tr>
							<tr data-tt-id='4-1-7-1' data-tt-parent-id='4-1-7'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/errorDisc_toproIncoPage.action?tnum=${user.tnum }"
										target="MainFrame">专业信息不完整</a></span></td>
							</tr>
							<tr data-tt-id='4-1-7-2' data-tt-parent-id='4-1-7'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/errorDisc_toavePerPage.action?tnum=${user.tnum }"
										target="MainFrame">平均周学时</a></span></td>
							</tr>
							<tr data-tt-id='4-1-7-3' data-tt-parent-id='4-1-7'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/errorDisc_tokeyCouEmptyPage.action?tnum=${user.tnum }"
										target="MainFrame">关键课程信息为空</a></span></td>
							</tr>
							<tr data-tt-id='4-1-7-4' data-tt-parent-id='4-1-7'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/errorDisc_topreCreErrPage.action?tnum=${user.tnum }"
										target="MainFrame">学时学分对应错误</a></span></td>
							</tr>
							<tr data-tt-id='4-1-7-5' data-tt-parent-id='4-1-7'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/errorDisc_tocreditEmptyPage.action?tnum=${user.tnum }"
										target="MainFrame">专业要求总学分为空</a></span></td>
							</tr>

							<tr data-tt-id='4-1-7-6' data-tt-parent-id='4-1-7'>
								<td><span class='file'><a
										href="${pageContext.request.contextPath}/errorDisc_tototalCrePage.action?tnum=${user.tnum }"
										target="MainFrame">低于专业应修学分</a></span></td>
							</tr>
						</c:if>
					</c:if>
					<tr data-tt-id='4-2' data-tt-parent-id='3'>
						<td><span class='folder'>教学大纲管理</span></td>
					</tr>
					<tr data-tt-id='4-2-1' data-tt-parent-id='4-2'>
						<td><span class='file'><a
								href="${pageContext.request.contextPath}/theoreticalPlan_toCheckTheoLes.action?user.tnum=${user.tnum}"
								target="MainFrame">理论课大纲录入</a></span></td>
					</tr>
					<tr data-tt-id='4-2-2' data-tt-parent-id='4-2'>
						<td><span class='file'><a
								href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesFieldWorkPage.action?user.tnum=${user.tnum}"
								target="MainFrame">实践课大纲录入</a></span></td>
					</tr>
					<tr data-tt-id='4-2-2' data-tt-parent-id='4-2'>
						<td><span class='file'><a
								href="${pageContext.request.contextPath}/theoreticalPlan_toCheckTheoLesAdmin1.action?tnum=${user.tnum}&curriculum.college.collegeid=<c:if test="${user.adminlevel == 3 || user.adminlevel == 1}">${user.college.collegeid }</c:if><c:if test="${user.adminlevel == 5}">-1</c:if>"
								target="MainFrame"> 大纲下载</a></span></td>
					</tr>
					<c:if
						test="${user.adminlevel == 1 || user.adminlevel == 3 || user.adminlevel == 5}">
						<tr data-tt-id='4-2-3' data-tt-parent-id='4-2'>
							<td><span class='file'><a
									href="${pageContext.request.contextPath}/theoreticalPlan_toCheckTheoLesAdmin.action?tnum=${user.tnum }&curriculum.college.collegeid=<c:if test="${user.adminlevel == 1 || user.adminlevel == 3}">${user.college.collegeid }</c:if><c:if test="${user.adminlevel == 5}">-1</c:if>"
									target="MainFrame">理论课大纲编写情况</a></span></td>
						</tr>
						<tr data-tt-id='4-2-4' data-tt-parent-id='4-2'>
							<td><span class='file'><a
									href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesFieldWorkPageAdmin.action?tnum=${user.tnum }&curriculum.college.collegeid=<c:if test="${user.adminlevel == 1 || user.adminlevel == 3}">${user.college.collegeid }</c:if><c:if test="${user.adminlevel == 5}">-1</c:if>"
									target="MainFrame">实践课大纲编写情况</a></span></td>
						</tr>
					</c:if>

					<%-- <c:if test="${user.adminlevel == 3 || user.adminlevel == 5}">
							<tr data-tt-id='4-2-3' data-tt-parent-id='4-2'>
								<td><span class='file'><a href="${pageContext.request.contextPath}/theoreticalPlan_toCheckTheoLesAdmin.action?tnum=${user.tnum }&curriculum.college.collegeid=<c:if test="${user.adminlevel == 3}">${user.college.collegeid }</c:if><c:if test="${user.adminlevel == 5}">-1</c:if>" target="MainFrame">理论课大纲编写情况</a></span></td>
							</tr>
							<tr data-tt-id='4-2-4' data-tt-parent-id='4-2'>
								<td><span class='file'><a href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesFieldWorkPageAdmin.action?tnum=${user.tnum }&curriculum.college.collegeid=<c:if test="${user.adminlevel == 3}">${user.college.collegeid }</c:if><c:if test="${user.adminlevel == 5}">-1</c:if>" target="MainFrame">实践课大纲编写情况</a></span></td>
							</tr>
						</c:if> --%>

					<tr data-tt-id='4-3' data-tt-parent-id='3'>
						<td><span class='folder'>教学日历管理</span></td>
					</tr>
					<tr data-tt-id='4-3-1' data-tt-parent-id='4-3'>
						<td><span class='file'><a
								href="${pageContext.request.contextPath}/teachCal_toTeachCalPage.action?user.tnum=${user.tnum}&user.college.collegeid=${cid}&user.department.departmentid=${ddid}"
								target="MainFrame">教学日历管理</a></span></td>
					</tr>
					<tr data-tt-id='4-4' data-tt-parent-id='3'>
						<td><span class='folder'>基础信息检索</span></td>
					</tr>
					<tr data-tt-id='4-4-1' data-tt-parent-id='4-4'>
						<td><span class='file'><a
								href="${pageContext.request.contextPath}/user_toxybmPage.action"
								target="MainFrame">查看学院编码信息</a></span></td>
					</tr>
					<tr data-tt-id='4-4-2' data-tt-parent-id='4-4'>
						<td><span class='file'><a
								href="${pageContext.request.contextPath}/user_tozybmPage.action?college.collegeid=-1"
								target="MainFrame">查看专业编码信息</a></span></td>
					</tr>
					<tr data-tt-id='4-4-2' data-tt-parent-id='4-4'>
						<td><span class='file'><a
								href="${pageContext.request.contextPath}/user_tosysbmPage.action?college.collegeid=-1"
								target="MainFrame">查看实验室编码信息</a></span></td>
					</tr>
					<tr data-tt-id='4-4-3' data-tt-parent-id='4-4'>
						<td><span class='file'><a
								href="${pageContext.request.contextPath}/curriculum_findcurriculum.action?college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}"
								target="MainFrame">课程库信息</a></span></td>
					</tr>
					<tr data-tt-id='4-4-4' data-tt-parent-id='4-4'>
						<td><span class='file'><a
								href="${pageContext.request.contextPath}/user_tozyfxPage.action?department.departmentid=-1&college.collegeid=-1"
								target="MainFrame">专业方向信息</a></span></td>
					</tr>

					<%-- <c:if test="${user.experimenterlevel == 3}">
							<tr data-tt-id='4-5' data-tt-parent-id='3'>
								<td><span class='folder'>课程分配管理</span></td>
							</tr>
							<tr data-tt-id='4-5-1' data-tt-parent-id='4-5'>
								<td><span class='file'><a href="${pageContext.request.contextPath}/theoreticalPlan_exptouserdepartment.action?curriculum.college.collegeid=00&experiment.experimentid=${user.experiment.experimentid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&usertag=0&teacher.tnum=${id}&teacher.username=${uname}" target="MainFrame">课内实验分配到实验员</a></span></td>
							</tr>
						</c:if> --%>
				<tbody>
                    </c:if>
                    
                   
					<!-- DYH -->
					<tr data-tt-id='4-5' data-tt-parent-id='3'>
						<td><span class='folder'>问卷管理系统</span></td>
					</tr>
					 <c:if test="${empty StuID}">
					<tr data-tt-id='4-5-1' data-tt-parent-id='4-5'>
						<td><span class='folder'>录入题目</span></td>
					</tr>
					<tr data-tt-id='4-5-1-1' data-tt-parent-id='4-5-1'>
						<td><span class='file'><a
								href="/TPM/admin/questionNaire/createQuestion/single_choose.jsp"
								target="MainFrame">单选题</a></span></td>
					</tr>
					<tr data-tt-id='4-5-1-2' data-tt-parent-id='4-5-1'>
						<td><span class='file'><a
								href="/TPM/admin/questionNaire/createQuestion/mult_choose.jsp"
								target="MainFrame">多选题</a></span></td>
					</tr>
					<tr data-tt-id='4-5-1-3' data-tt-parent-id='4-5-1'>
						<td><span class='file'><a
								href="/TPM/admin/questionNaire/createQuestion/single_fill.jsp"
								target="MainFrame">单项填空题</a></span></td>
					</tr>
					<tr data-tt-id='4-5-1-4' data-tt-parent-id='4-5-1'>
						<td><span class='file'><a
								href="/TPM/admin/questionNaire/createQuestion/mult_fill.jsp"
								target="MainFrame">多项填空题</a></span></td>
					</tr>
					<tr data-tt-id='4-5-1-5' data-tt-parent-id='4-5-1'>
						<td><span class='file'><a
								href="/TPM/admin/questionNaire/createQuestion/file.jsp"
								target="MainFrame">文件上传题</a></span></td>
					</tr>
				    </c:if>

					<c:if test="${not empty StuID }">
					<tr data-tt-id='4-5-4' data-tt-parent-id='4-5'>
						<td><span class='folder'>我的问卷</span></td>
					</tr>
					<tr data-tt-id='4-5-4-1' data-tt-parent-id='4-5-4'>
						<td><span class='file'><a
								href="/TPM/GetQuestionnaireServlet.servlet?QuestionnaireStatus=3&cpage=1"
								target="MainFrame">全部问卷</a></span></td>
					</tr>
					<tr data-tt-id='4-5-4-2' data-tt-parent-id='4-5-4'>
						<td><span class='file'><a
								href="/TPM/GetQuestionnaireServlet.servlet?QuestionnaireStatus=1&cpage=1"
								target="MainFrame">已做问卷</a></span></td>
					</tr>
					<tr data-tt-id='4-5-4-3' data-tt-parent-id='4-5-4'>
						<td><span class='file'><a
								href="/TPM/GetQuestionnaireServlet.servlet?QuestionnaireStatus=0&cpage=1"
								target="MainFrame">未做问卷</a></span></td>
					</tr>
					</c:if>
					<c:if test="${empty StuID }">
					<tr data-tt-id='4-5-5' data-tt-parent-id='4-5'>
						<td><span class='folder'>问卷管理
						</span></td>
					</tr>
					<tr data-tt-id='4-5-5-1' data-tt-parent-id='4-5-5'>
						<td><span class='file'><a
								href="/TPM/AdminGetQnaireServlet.servlet?QuestionnaireStatus=3&page=1&status=Admin"
								target="MainFrame">我的问卷</a></span></td>
					</tr>
						<tr data-tt-id='4-5-5-2' data-tt-parent-id='4-5-5'>
						<td><span class='file'><a
								href="/TPM/admin/questionNaire/createQuestionnair/createQuestionnair.jsp"
								target="MainFrame">创建问卷</a></span></td>
					</tr>
					<tr data-tt-id='4-5-5-3' data-tt-parent-id='4-5-5'>
						<td><span class='file'><a
								href="/TPM/admin/questionNaire/questionManager/questionManager.jsp"
								target="MainFrame">问题管理</a></span></td>
					</tr>
					<tr data-tt-id='4-5-5-2' data-tt-parent-id='4-5-5'>
						<td><span class='file'><a
								href="/TPM/ShowPublishedServlet.servlet?QuestionnaireStatus=3&page=1"
								target="MainFrame">已发布问卷</a></span></td>
					</tr>
					

					<tr data-tt-id='4-5-6' data-tt-parent-id='4-5'>
						<td><span class='folder'><a
								href="/TPM/AdminGetQnaireServlet.servlet?QuestionnaireStatus=3&page=1&status=expert"
								target="MainFrame">问卷分析</a></span></td>
								<tr data-tt-id='4-5-6-1' data-tt-parent-id='4-5-6'>
						<td><span class='file'><a
								href="/TPM/AdminGetQnaireServlet.servlet?QuestionnaireStatus=3&page=1&status=expert"
								target="MainFrame">完成问卷分析</a></span></td>
					</tr>
					
					</tr>
					</c:if>
				</tbody>

				</tbody>
			</table>
		</div>
		<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
		<script src="<%=basePath%>images/js/jquery.treetable.js"></script>
		<script>
			
				$("#example-advanced").treetable({
					expandable: true
				});

				
			
			</script>
	</div>
	<div id="mid"
		style="position: absolute; right: 0; top: 0; height: 100%; width: 6px;">
		<div id="long"></div>
		<div id="pointer" onclick="HideScreen()">
			<img id="img1"
				style="margin-top: 12px; position: absolute; height: 8px; width: 100%;"
				src="<%=basePath%>images/img/440-arrow-simle-left.png"
				align="center" />
		</div>
	</div>
</body>
<script type="text/javascript">
		var screen = true;

		function HideScreen() {
			if(screen) {
				parent.topwin.cols = "6,*"
				document.getElementById("img1").src = '<%=basePath%>images/img/439-arrow-simle-right.png';
			} else {
				parent.topwin.cols = "258,*"
				document.getElementById("img1").src = '<%=basePath%>images/img/440-arrow-simle-left.png';
			}
			screen = !screen;
		}
	</script>

</html>