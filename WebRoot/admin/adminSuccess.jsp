<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<script type="text/javascript"
	src="<%=basePath %>images/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/images/js/animate.js"></script>
<script type="text/javascript"
	src="<%=basePath %>/images/js/loadMask.js"></script>
<head>
<meta charset="UTF-8">
<style type="text/css">
#success {
	position: absolute;
	top: 40%;
	left: 40%;
}
</style>
</head>

<body>

	<div id="success">
		<img src="<%=basePath %>images/img/Success-16.png" /> <strong>${msg }</strong>
		<c:if test="${tag == 'add_todlzhPage' }">
			<c:if test="${user.adminlevel == 5 }">
				<a
					href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}">返回</a>
			</c:if>
			<c:if test="${user.adminlevel == 3 }">
				<a
					href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${newuser.department.departmentid}">返回</a>
			</c:if>
			<c:if test="${user.adminlevel == 1 }">
				<a
					href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${user.department.departmentid}">返回</a>
			</c:if>
		</c:if>


		<c:if test="${tag == 'init_todlzhPage' }">
			<c:if test="${user.adminlevel == 5 }">
				<a
					href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}">返回</a>
			</c:if>
		</c:if>

		<c:if test="${tag == 'del_todlzhPage' }">
			<c:if test="${user.adminlevel == 5 }">
				<a
					href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}">返回</a>
			</c:if>
			<c:if test="${user.adminlevel == 3 }">
				<a
					href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${newuser.department.departmentid}">返回</a>
			</c:if>
			<c:if test="${user.adminlevel == 1 }">
				<a
					href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${user.department.departmentid}">返回</a>
			</c:if>
		</c:if>

		<c:if test="${tag == 'toxxglPage' }">
			<c:if test="${user.adminlevel != 1 }">
				<c:if test="${user.adminlevel != 3 }">
					<a
						href="${pageContext.request.contextPath}/user_toxxglPage.action?username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}">返回</a>
				</c:if>
			</c:if>
		</c:if>
		<!-- &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& -->
		<!-- 课内实验分配到实验室 -->
		<c:if test="${tag == 'goupdateExpdepartment' }">
			<c:if test="${user.adminlevel == 3 }">
				<a
					href="${pageContext.request.contextPath}/theoreticalPlan_toExpdepartment.action?curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&newcurrentpage=${newcurrentpage}&departmenttag=0">确定</a>
			</c:if>
		</c:if>

		<!-- 课内实验分配到专业 -->
		<c:if test="${tag == 'goupdateExpdepart' }">
			<c:if test="${user.adminlevel == 3 }">
				<a
					href="${pageContext.request.contextPath}/theoreticalPlan_toExpdepart.action?curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&newcurrentpage=${newcurrentpage}&departmenttag=0">确定</a>
			</c:if>
		</c:if>


		<!-- 更新完实验员后返回初始进入直接分配实验员的页面 -->
		<c:if test="${tag == 'tollkkcToExper' }">
			<c:if test="${user.adminlevel == 3 }">
				<a
					href="${pageContext.request.contextPath}/theoreticalPlan_toExpusercollege.action?tnum=${user.tnum }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&newcurrentpage=${newcurrentpage}&departmenttag=0">确定</a>
			</c:if>
		</c:if>
		<!-- 实验室主任成功分配实验员后的返回界面 -->
		<c:if test="${tag == 'tollkkcToExperDepart' }">
			<c:if test="${user.experimenterlevel == 3 }">
				<a
					href="${pageContext.request.contextPath}/theoreticalPlan_exptouserdepartment.action?curriculum.college.collegeid=00&experiment.experimentid=${user.experiment.experimentid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&usertag=0&teacher.tnum=${id}&teacher.username=${uname}">确定</a>
			</c:if>
		</c:if>
		<!-- &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& -->
		<c:if test="${tag == 'toxyglPage' }">
			<c:if test="${user.adminlevel != 1 }">
				<c:if test="${user.adminlevel == 5 }">
					<a
						href="${pageContext.request.contextPath}/user_toxyglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}">返回</a>
				</c:if>
				<c:if test="${user.adminlevel == 3 }">
					<a
						href="${pageContext.request.contextPath}/user_toxyglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${newuser.department.departmentid}">返回</a>
				</c:if>
			</c:if>
		</c:if>


		<c:if test="${tag == 'toxglPage' }">
			<c:if test="${user.adminlevel == 5 }">
				<a
					href="${pageContext.request.contextPath}/user_toxglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}">返回</a>
			</c:if>
			<c:if test="${user.adminlevel == 3 }">
				<a
					href="${pageContext.request.contextPath}/user_toxglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${newuser.department.departmentid}">返回</a>
			</c:if>
			<c:if test="${user.adminlevel == 1 }">
				<a
					href="${pageContext.request.contextPath}/user_toxglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${user.department.departmentid}">返回</a>
			</c:if>
		</c:if>

		<c:if test="${tag == 'tosyszrglPage' }">
			<c:if test="${user.adminlevel != 1 }">
				<c:if test="${user.adminlevel == 5 }">
					<td><span class='file'><a
							href="${pageContext.request.contextPath}/user_tosyszrglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&experiment.experimentid=${newuser.experiment.experimentid}"
							target="MainFrame">返回</a></span></td>
				</c:if>
				<c:if test="${user.adminlevel == 3 }">
					<td><span class='file'><a
							href="${pageContext.request.contextPath}/user_tosyszrglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&experiment.experimentid=${newuser.experiment.experimentid}"
							target="MainFrame">返回</a></span></td>
				</c:if>
			</c:if>
		</c:if>

		<c:if test="${tag == 'tosyyglPage' }">
			<c:if test="${user.adminlevel != 1 }">
				<c:if test="${user.adminlevel == 5 }">
					<td><span class='file'><a
							href="${pageContext.request.contextPath}/user_tosyyglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&experiment.experimentid=${newuser.experiment.experimentid}"
							target="MainFrame">返回</a></span></td>
				</c:if>
				<c:if test="${user.adminlevel == 3 }">
					<td><span class='file'><a
							href="${pageContext.request.contextPath}/user_tosyyglPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&experiment.experimentid=${newuser.experiment.experimentid}"
							target="MainFrame">返回</a></span></td>
				</c:if>
			</c:if>
		</c:if>

		<c:if test="${tag == 'down_todlzhPage' }">
			<c:if test="${user.adminlevel == 5 }">
				<a
					href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}">返回</a>
			</c:if>
			<c:if test="${user.adminlevel == 3 }">
				<a
					href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${newuser.department.departmentid}">返回</a>
			</c:if>
			<c:if test="${user.adminlevel == 1 }">
				<a
					href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${user.department.departmentid}">返回</a>
			</c:if>
		</c:if>

		<c:if test="${tag == 'update_todlzhPage' }">
			<c:if test="${user.adminlevel == 5 }">
				<a
					href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&department.departmentid=${newuser.department.departmentid}">返回</a>
			</c:if>
			<c:if test="${user.adminlevel == 3 }">
				<a
					href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${newuser.department.departmentid}">返回</a>
			</c:if>
			<c:if test="${user.adminlevel == 1 }">
				<a
					href="${pageContext.request.contextPath}/user_todlzhPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${user.college.collegeid}&department.departmentid=${user.department.departmentid}">返回</a>
			</c:if>
		</c:if>

		<c:if test="${tag == 'add_tokcxxPage' }">
			<a
				href="${pageContext.request.contextPath}/curriculum_tokcxxPage.action?college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}">确定</a>
		</c:if>

		<c:if test="${tag == 'del_tokcxxPage' }">
			<a
				href="${pageContext.request.contextPath}/curriculum_tokcxxPage.action?college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}">确定</a>
		</c:if>

		<c:if test="${tag == 'tobyyqPage' }">
			<c:if test="${user.adminlevel != 1 }">
				<a
					href="${pageContext.request.contextPath}/analysis_tobyyq.action?tnum=${user.tnum }&collegeid=-1">确定</a>
			</c:if>
			<c:if test="${user.adminlevel == 1 }">
				<a
					href="${pageContext.request.contextPath}/analysis_tobyyqPage.action?departmentid=${user.department.departmentid }">确定</a>
			</c:if>
		</c:if>

		<c:if test="${tag == 'topyapPage' }">
			<c:if test="${user.adminlevel != 1 }">
				<a
					href="${pageContext.request.contextPath}/mainTainOfPT_topyapPage.action?tnum=${user.tnum }&collegeid=-1">确定</a>
			</c:if>
			<c:if test="${user.adminlevel == 1 }">
				<a
					href="${pageContext.request.contextPath}/mainTainOfPT_tobjapPage.action?departmentid=${user.department.departmentid}">确定</a>
			</c:if>
		</c:if>

		<c:if test="${tag == 'toanotherPage' }">
			<c:if test="${user.adminlevel != 1 }">
				<a
					href="${pageContext.request.contextPath}/TrainingAnother_toanother.action?tnum=${user.tnum }&collegeid=-1">确定</a>
			</c:if>
			<c:if test="${user.adminlevel == 1 }">
				<a
					href="${pageContext.request.contextPath}/TrainingAnother_toanotherPage.action?department.departmentid=${user.department.departmentid }">确定</a>
			</c:if>
		</c:if>

		<c:if test="${tag == 'tollkPage' }">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_tollkPage.action?tnum=${user.tnum }&collegeid=${collegeid}&departmentid=${departmentid}">确定</a>
		</c:if>

		<c:if test="${tag == 'tojzsjkPage' }">
			<a
				href="${pageContext.request.contextPath}/practicePlan_tojzsjkPage.action?tnum=${user.tnum }&collegeid=${collegeid}&departmentid=${departmentid}">确定</a>
		</c:if>

		<!-- 把理论课分配给老师 -->
		<c:if test="${tag == 'tollkkcToUser' }">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_tousercollege.action?tnum=${user.tnum }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&newcurrentpage=${newcurrentpage}&
				<%-- <c:if test="${departmenttag eq null || departmenttag eq ''}"> --%>&departmenttag=0">确定</a>
		</c:if>

		<!-- 把课内实验分配给老师 -->
		<c:if test="${tag == 'llkkcToUser' }">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_toExpuser.action?tnum=${user.tnum }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&newcurrentpage=${newcurrentpage}&
				<%-- <c:if test="${departmenttag eq null || departmenttag eq ''}"> --%>&departmenttag=0">确定</a>
			<!-- &departmenttag=${newdepartmenttag1} -->
		</c:if>
		<!-- 系管理把课内实验分配给老师 -->
		<c:if test="${tag == 'tollkkcToTeacherDepartment' }">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_toExpteacher.action?curriculum.college.collegeid=${user.college.collegeid}&teachDepartment.departmentid=${user.department.departmentid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&usertag=0&teacher.tnum=${id}&teacher.username=${uname}">确定</a>
		</c:if>

		<c:if test="${tag == 'llktoteacherdepartment' }">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_toExpteacher.action?curriculum.college.collegeid=${user.college.collegeid}&teachDepartment.departmentid=${user.department.departmentid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&usertag=0&teacher.tnum=${id}&teacher.username=${uname}">确定</a>
		</c:if>




		<c:if test="${tag == 'tollkkcToUserDepartment' }">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_touserdepartment.action?curriculum.college.collegeid=${user.college.collegeid}&teachDepartment.departmentid=${user.department.departmentid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&usertag=0&teacher.tnum=${id}&teacher.username=${uname}">确定</a>
		</c:if>

		<c:if test="${tag == 'tollkkcToUserpageDepartment' }">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_touserdepartment.action?curriculum.college.collegeid=${user.college.collegeid}&teachDepartment.departmentid=${user.department.departmentid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&usertag=0&teacher.tnum=${id}&teacher.username=${uname}">确定</a>
		</c:if>

		<!-- 分配理论课到专业 -->
		<c:if test="${tag == 'llktodepartment' }">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_todepartment.action?curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&newcurrentpage=${newcurrentpage}&
				<%-- <c:if test="${departmenttag eq null || departmenttag eq ''}"> --%>
				&departmenttag=0">确定</a>
		</c:if>

		<!-- 分配实践课到专业 -->
		<c:if test="${tag == 'sjktodepartment' }">
			<a
				href="${pageContext.request.contextPath}/practicePlan_prctodepartment.action?curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${practiceLesson.curriculum.curriculumid}&curriculum.curriculumCname=${practiceLesson.curriculum.curriculumCname}&newcurrentpage=${newcurrentpage}&
				<%-- <c:if test="${departmenttag eq null || departmenttag eq ''}"> --%>
				&departmenttag=0">确定</a>
		</c:if>

		<!-- 分配实践课到老师 -->
		<c:if test="${tag == 'sjktousercollege' }">
			<a
				href="${pageContext.request.contextPath}/practicePlan_prctousercollege.action?tnum=${user.tnum }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&newcurrentpage=${newcurrentpage}&
				<%-- <c:if test="${departmenttag eq null || departmenttag eq ''}"> --%>&departmenttag=0">确定</a>
		</c:if>

		<c:if test="${tag == 'llktouserdepartment' }">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_touserdepartment.action?curriculum.college.collegeid=${user.college.collegeid}&teachDepartment.departmentid=${user.department.departmentid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&usertag=0&teacher.tnum=${id}&teacher.username=${uname}">确定</a>
		</c:if>



		<c:if test="${tag == 'sjktouserdepartment' }">
			<a
				href="${pageContext.request.contextPath}/practicePlan_touserdepartment.action?curriculum.college.collegeid=${user.college.collegeid}&teachDepartment.departmentid=${user.department.departmentid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&usertag=0&teacher.tnum=${id}&teacher.username=${uname}">确定</a>
		</c:if>

		<c:if test="${tag == 'toTeachCalPage' }">
			<a
				href="${pageContext.request.contextPath}/teachCal_toTeachCalPage.action?user.tnum=${user.tnum}&user.college.collegeid=${cid}&user.department.departmentid=${ddid}">确定</a>
		</c:if>

		<c:if test="${tag == 'toViewTopologyPage' }">
			<c:if test="${user.adminlevel != 1 }">
				<a
					href="${pageContext.request.contextPath}/curriculumMatrix_tozyjzpage.action?tnum=${user.tnum }&collegeid=-1">确定</a>
			</c:if>

			<c:if test="${user.adminlevel == 1 }">
				<a href="#" onclick="xiugai()">确定</a>
			</c:if>
		</c:if>
		<script type="text/javascript"> 
		function xiugai(){
		showMask({info: "请稍后……"});
		window.location.href="${pageContext.request.contextPath}/curriculumMatrix_tobjjzpage.action?departmentid=${user.department.departmentid}";
		}
	 </script>

		<c:if test="${tag == 'tobjjzPage' }">
			<c:if test="${user.adminlevel != 1 }">
				<a
					href="${pageContext.request.contextPath}/curriculumMatrix_tozyjzpage.action?tnum=${user.tnum }&collegeid=-1">确定</a>
			</c:if>
			<c:if test="${user.adminlevel == 1 }">
				<a
					href="${pageContext.request.contextPath}/curriculumMatrix_tobjjzpage.action?departmentid=${user.department.departmentid}">确定</a>
			</c:if>
		</c:if>

		<c:if test="${tag == 'toTheoLesPage' }">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_toupdateTheoLesPage.action?tnum=${user.tnum}&syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid }&curriculum.curriculumid=${newtheoreticalLesson.curriculum.curriculumid }&department.departmentid=${newtheoreticalLesson.department.departmentid}">确定</a>
		</c:if>
		<c:if test="${tag == 'toTheoLesPage_other' }">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toHavePracLesOtherPage.action?tnum=${user.tnum}&syllabusId=${syllabusId}&practiceLessonid=${newtheoreticalLesson.practiceLessonid }&curriculum.curriculumid=${newtheoreticalLesson.curriculum.curriculumid }&department.departmentid=${newtheoreticalLesson.department.departmentid}">确定</a>
		</c:if>
		<c:if test="${tag == 'toTheoInnerExperimentPage' }">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_toupdateTheoLesPageInnerExperiment.action?tnum=${user.tnum}&syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid }&curriculum.curriculumid=${newtheoreticalLesson.curriculum.curriculumid }&department.departmentid=${newtheoreticalLesson.department.departmentid}">确定</a>
		</c:if>
		<c:if test="${tag == 'toPracLes_FieldWorkPage' }">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toHavePracLesFieldWorkPage.action?tnum=${user.tnum}&syllabusId=${syllabusId_FieldWorkid}&practiceLessonid=${newpracticeLesson.practiceLessonid}&curriculum.curriculumid=${newpracticeLesson.curriculum.curriculumid }&department.departmentid=${newpracticeLesson.department.departmentid}">确定</a>
		</c:if>

		<c:if test="${tag == 'toPracLes_CourseDesignPage' }">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toHavePracLesCourseDesignPage.action?tnum=${user.tnum}&syllabusId=${syllabusId}&practiceLessonid=${newpracticeLesson.practiceLessonid}&curriculum.curriculumid=${newpracticeLesson.curriculum.curriculumid }&department.departmentid=${newpracticeLesson.department.departmentid}">确定</a>
		</c:if>
		<c:if test="${tag == 'toPracLes_GraPage' }">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toHavePracLesGraduationProjectPage.action?tnum=${user.tnum}&syllabusId=${syllabusId}&practiceLessonid=${newpracticeLesson.practiceLessonid}&curriculum.curriculumid=${newpracticeLesson.curriculum.curriculumid }&department.departmentid=${newpracticeLesson.department.departmentid}">确定</a>
		</c:if>

		<c:if test="${tag == 'topreCreErrPage' }">
			<a
				href="${pageContext.request.contextPath}/errorDisc_topreCreErrPage.action?tnum=${user.tnum }">确定</a>
		</c:if>

		<c:if test="${tag == 'tokeyCouEmptyPage' }">
			<a
				href="${pageContext.request.contextPath}/errorDisc_tokeyCouEmptyPage.action?tnum=${user.tnum }">确定</a>
		</c:if>

		<c:if test="${tag == 'tozypyPage' }">
			<c:if test="${user.adminlevel != 1 }">
				<a
					href="${pageContext.request.contextPath}/ppTrainingConcept_tozypyPage.action?tnum=${user.tnum }&collegeid=-1">确定</a>
			</c:if>
			<c:if test="${user.adminlevel == 1 }">
				<a
					href="${pageContext.request.contextPath}/ppTrainingConcept_tobjzyPage.action?department.departmentid=${user.department.departmentid }">确定</a>
			</c:if>
		</c:if>

		<c:if test="${tag == 'toCheckTheoLes' }">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_toCheckTheoLes.action?user.tnum=${user.tnum}">确定</a>
		</c:if>
		<c:if test="${tag == 'toCheckTheoLes_InnerExperiment' }">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_toCheckTheoLesInnerExperiment.action?user.tnum=${user.tnum}">确定</a>
		</c:if>

		<c:if test="${tag == 'toCheckPracLesFieldWorkPage' }">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesFieldWorkPage.action?user.tnum=${user.tnum}">确定</a>
		</c:if>
		<c:if test="${tag == 'toCheckPracLesCourseDesignPage' }">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesCourseDesignPage.action?user.tnum=${user.tnum}">确定</a>
		</c:if>
		<c:if test="${tag == 'toCheckPracLesInnerExperimentPage' }">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesInnerExperimentPage.action?user.tnum=${user.tnum}">确定</a>
		</c:if>
		<c:if test="${tag == 'toCheckPracLesGraduationProjectPage' }">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesGraduationProjectPage.action?user.tnum=${user.tnum}">确定</a>
		</c:if>
		<c:if test="${tag == 'toCheckPracLesOtherPage' }">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracticeOther.action?user.tnum=${user.tnum}">确定</a>
		</c:if>

		<c:if test="${tag == 'togetInPage' && user.adminlevel == 5}">
			<a
				href="${pageContext.request.contextPath}/user_togetInPage.action?tnum=${user.tnum}">确定</a>
		</c:if>


		<!-- 新增 -->
		<!-- 分配实践课到实验员 -->
		<c:if test="${tag == 'protollkkcToExper' }">
			<c:if test="${user.adminlevel == 3 }">
				<a
					href="${pageContext.request.contextPath}/practicePlan_prctoExpusercollege.action?tnum=${user.tnum }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${practiceLesson.curriculum.curriculumid}&curriculum.curriculumCname=${practiceLesson.curriculum.curriculumCname}&newcurrentpage=${newcurrentpage}&departmenttag=0">确定</a>
			</c:if>
		</c:if>
		<!-- 分配实践课到实验室 -->
		<c:if test="${tag == 'prcgoupdateExpdepartment' }">
			<c:if test="${user.adminlevel == 3 }">
				<a
					href="${pageContext.request.contextPath}/practicePlan_prctoExpdepartment.action?curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&newcurrentpage=${newcurrentpage}&departmenttag=0">确定</a>
			</c:if>
		</c:if>

		<!-- 实验室主任分配实践课到实验员 -->
		<c:if test="${tag == 'protollkkcToExper1' }">
			<c:if test="${user.experimenterlevel == 3 }">
				<a
					href="${pageContext.request.contextPath}/practicePlan_prctoExpuser.action?tnum=${user.tnum }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${practiceLesson.curriculum.curriculumid}&curriculum.curriculumCname=${practiceLesson.curriculum.curriculumCname}&newcurrentpage=${newcurrentpage}&departmenttag=0">确定</a>
			</c:if>
		</c:if>


	</div>

</body>
</html>