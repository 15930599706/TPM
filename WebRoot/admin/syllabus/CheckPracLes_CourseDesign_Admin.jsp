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
<link rel="stylesheet"
	href="<%=basePath%>images/css/bootstrap-multiselect.css"
	type="text/css">
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
<script type="text/javascript"
	src="<%=basePath%>images/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/bootstrap-multiselect.js"></script>
<style type="text/css">
.link>a {
	display: block;
	height: 20px;
	width: 160px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	font-size: 13px;
	background: url(<%=basePath%>images/img/Edit-16.png) no-repeat 25px 2px;
	text-indent: 45px;
	text-align: left;
	float: left;
}

.link>a:hover {
	text-decoration: underline;
}

.link1>a {
	display: block;
	height: 20px;
	width: 250px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	font-size: 13px;
	background: url(<%=basePath%>images/img/Edit-16.png) no-repeat 25px 2px;
	text-indent: 45px;
	text-align: left;
	float: left;
}

.link1>a:hover {
	text-decoration: underline;
}

.link2>a {
	display: block;
	height: 20px;
	width: 190px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	font-size: 13px;
	background: url(<%=basePath%>images/img/Edit-16.png) no-repeat 25px 2px;
	text-indent: 45px;
	text-align: left;
	float: left;
}

.link2>a:hover {
	text-decoration: underline;
}

.link3>a {
	display: block;
	height: 20px;
	width: 190px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	font-size: 13px;
	background: url(<%=basePath%>images/img/Edit-16.png) no-repeat 25px 2px;
	text-indent: 45px;
	text-align: left;
	float: left;
}

.link3>a:hover {
	text-decoration: underline;
}

#box1 {
	font-family: "microsoft yahei";
	height: 25px;
	padding-left: 25px;
	padding-top: 40px;
	width: 1003px;
}

.link4>a {
	display: block;
	height: 20px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	font-size: 13px;
	background: url(<%=basePath%>images/img/Edit-16.png) no-repeat 25px 2px;
	text-indent: 45px;
	text-align: left;
	float: left;
}

.link4>a:hover {
	text-decoration: underline;
}
</style>
</head>
<script language="javascript">
		function selectSubmit()
		{			
			var form=document.getElementById("search");
			var collegeid =document.getElementById("college").value;
			if(collegeid != "-1")
			{
				form.action="${pageContext.request.contextPath}/practicePlan_toCheckCourseDesignPageAdmin.action?tnum=${user.tnum}";
				form.submit(); 
			}
			 
		}
	</script>
<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>实践课大纲编写情况——<font style="color: #0000c6;">课程设计（学年论文）</font></strong>
		</div>
		<div class="link">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesFieldWorkPageAdmin.action?tnum=${user.tnum }&curriculum.college.collegeid=<c:if test="${user.adminlevel == 3 || user.adminlevel == 1}">${user.college.collegeid }</c:if><c:if test="${user.adminlevel == 5}">-1</c:if>"><font
				style="color: #000;">查看实习大纲</font></a>
		</div>
		<div class="link1">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckCourseDesignPageAdmin.action?tnum=${user.tnum }&curriculum.college.collegeid=<c:if test="${user.adminlevel == 3 || user.adminlevel == 1}">${user.college.collegeid }</c:if><c:if test="${user.adminlevel == 5}">-1</c:if>"><font
				style="color: #0000c6;">查看课程设计（学年论文）大纲</font></a>
		</div>
		<div class="link2">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckInnerExperimentPageAdmin.action?tnum=${user.tnum }&curriculum.college.collegeid=<c:if test="${user.adminlevel == 3 || user.adminlevel == 1}">${user.college.collegeid }</c:if><c:if test="${user.adminlevel == 5}">-1</c:if>"><font
				style="color: #000;">查看实验大纲</font></a>
		</div>
		<div class="link3">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckGraduationProjectPageAdmin.action?tnum=${user.tnum }&curriculum.college.collegeid=<c:if test="${user.adminlevel == 3 || user.adminlevel == 1}">${user.college.collegeid }</c:if><c:if test="${user.adminlevel == 5}">-1</c:if>"><font
				style="color: #000;">查看毕业设计大纲</font></a>
		</div>
		<div class="link4">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckOtherPageAdmin.action?tnum=${user.tnum }&curriculum.college.collegeid=<c:if test="${user.adminlevel == 3 || user.adminlevel == 1}">${user.college.collegeid }</c:if><c:if test="${user.adminlevel == 5}">-1</c:if>"><font
				style="color: #000;">查看其他大纲</font></a>
		</div>
		<form method="post" id="search">
			<div id="box1">
				<td>学院：</td>
				<td><select size="1" id="college"
					name="curriculum.college.collegeid" onChange="selectSubmit()">
						<c:if test="${user.adminlevel == 5 }">
							<option value="-1" selected="selected">请选择学院</option>
						</c:if>
						<c:forEach items="${collegelist}" var="college">
							<option value="${college.collegeid }"
								<c:if test="${college.collegeid eq xueyuan }">selected="selected"</c:if>>${college.collegeCname}</option>
						</c:forEach>
				</select></td> 课程编码:&nbsp;&nbsp;<input type="text" name="curriculum.curriculumid"
					value="${currid }" style="width: 100px;" size="3" />
				课程名称:&nbsp;&nbsp;<input type="text"
					name="curriculum.curriculumCname" value="${currname }"
					style="width: 180px;" size="3" /> <input type="submit" value="检索"
					class="btn btn-info  " style="width: 50px;"
					onclick="selectSubmit()" />
			</div>
		</form>
		<div style="padding-left: 24px;">
			<div style="border-bottom: #CCCCCC 1 solid;">
				<h4 style="color: green;">已编写 “课程设计（学年论文）” 大纲：</h4>
			</div>

			<div style="width: 90%; margin-left: 20px;">
				<table class="table table-bordered table-striped table-hover">

					<tbody>
					<thead>
						<tr align="center">
							<th nowrap="nowrap"><small>序号</small></th>
							<th nowrap="nowrap"><small>课程编号</small></th>
							<th nowrap="nowrap"><small>课程名称</small></th>
							<th nowrap="nowrap"><small>开课学院</small></th>
							<th nowrap="nowrap"><small>学分</small></th>
							<th nowrap="nowrap"><small>总学时</small></th>
							<th nowrap="nowrap"><small>选课学院</small></th>
							<th nowrap="nowrap"><small>选课专业</small></th>
							<th nowrap="nowrap"><small>已编写专业（方向）</small></th>
							<th width="80" nowrap="nowrap"><small>生成文档</small></th>

						</tr>
					</thead>

					<c:forEach items="${haveSyllabusList}" var="haveSyllabus"
						varStatus="xh">
						<tr align="center">
							<td nowrap="nowrap"><small>${xh.count }</small></td>
							<td nowrap="nowrap"><small>${haveSyllabus.curriculum.curriculumid }</small></td>
							<td nowrap="nowrap"><small>${haveSyllabus.curriculum.curriculumCname }</small></td>
							<td nowrap="nowrap"><small>${haveSyllabus.curriculum.college.collegeCname }</small></td>
							<td nowrap="nowrap"><small>${haveSyllabus.curriculum.credit }</small></td>
							<td nowrap="nowrap"><small>${haveSyllabus.curriculum.hoursOfALL }</small></td>
							<td nowrap="nowrap"><small>${haveSyllabus.department.college.collegeCname }</small></td>
							<td nowrap="nowrap"><small>${haveSyllabus.department.departmentCname }</small></td>
							<td nowrap="nowrap"><small> <c:if
										test="${haveCount[xh.count-1] == -1}">
										<option>不区分专业方向</option>
									</c:if>

									<form method="post" id="do${xh.count}" name="do${xh.count}"
										style="margin: auto;">
										<c:if test="${haveCount[xh.count-1] != -1}">
											<select class="example-selectAllNumber" multiple="multiple"
												name="haveselectProfessional">
												<c:forEach items="${newprofessionalList[xh.count-1]}"
													var="newprofessionallist">
													<option
														value="${newprofessionallist.professional.professionalid }"
														<c:forEach items="${haveprofessionalList[xh.count-1]}" var="haveprofessionallist">
													<c:if test="${newprofessionallist.professional.professionalid eq haveprofessionallist.professional.professionalid}">
													selected="selected"
													</c:if>
												</c:forEach>>${newprofessionallist.professional.professionalname}</option>

												</c:forEach>

											</select>
										</c:if>
										<input type="hidden" name="syllabusId"
											value="${syllabusId[xh.count-1]}" /> <input type="hidden"
											name="practiceLessonid"
											value="${haveSyllabus.practiceLessonid}" /> <input
											type="hidden" name="curriculum.curriculumid"
											value="${haveSyllabus.curriculum.curriculumid}" /> <input
											type="hidden" name="department.departmentid"
											value="${haveSyllabus.department.departmentid}" />
									</form>
							</small></td>

							<td nowrap="nowrap"><a
								href="${pageContext.request.contextPath}/ExportWork_exportCourseDesign.action?syllabusid=${syllabusId[xh.count-1]}&courseid=${haveSyllabus.practiceLessonid}"><small>下载</small></a>
							</td>

						</tr>

					</c:forEach>

					</tbody>
				</table>
			</div>

			<div style="border-bottom: #CCCCCC 1 solid;">
				<h4 style="color: red;">未编写 “课程设计（学年论文）”大纲：</h4>

			</div>
			<div style="width: 90%; margin-left: 20px;">
				<table class="table table-bordered table-striped table-hover">

					<tbody>
					<thead>
						<tr align="center">
							<th nowrap="nowrap"><small>课程编号</small></th>
							<th nowrap="nowrap"><small>课程名称</small></th>
							<th nowrap="nowrap"><small>开课学院</small></th>
							<th nowrap="nowrap"><small>学分</small></th>
							<th nowrap="nowrap"><small>总学时</small></th>
							<th nowrap="nowrap"><small>选课学院</small></th>
							<th nowrap="nowrap"><small>选课专业</small></th>
							<th nowrap="nowrap"><small>未编写专业（方向）</small></th>
						</tr>
					</thead>

					<c:forEach items="${syllabusList}" var="syllabus" varStatus="xh">
						<tr align="center">
							<td nowrap="nowrap"><small>${syllabus.curriculum.curriculumid }</small></td>
							<td nowrap="nowrap"><small>${syllabus.curriculum.curriculumCname }</small></td>
							<td nowrap="nowrap"><small>${syllabus.curriculum.college.collegeCname }</small></td>
							<td nowrap="nowrap"><small>${syllabus.curriculum.credit }</small></td>
							<td nowrap="nowrap"><small>${syllabus.curriculum.hoursOfALL }</small></td>
							<td nowrap="nowrap"><small>${syllabus.department.college.collegeCname }</small></td>
							<td nowrap="nowrap"><small>${syllabus.department.departmentCname }</small></td>
							<td nowrap="nowrap"><c:if test="${count[xh.count-1] == -1}">
									<option>不区分专业方向</option>
								</c:if>
								<form method="post" id="notDo${xh.count}"
									name="notDo${xh.count}" style="margin: auto;">
									<c:if test="${count[xh.count-1] != -1}">
										<select class="example-selectAllNumber" multiple="multiple"
											name="selectProfessional">
											<c:forEach items="${professionalList[xh.count-1]}"
												var="professionallist">
												<option
													value="${professionallist.professional.professionalid }"
													selected="selected">${professionallist.professional.professionalname}</option>
											</c:forEach>
										</select>
									</c:if>
									<input type="hidden" name="practiceLessonid"
										value="${syllabus.practiceLessonid}" /> <input type="hidden"
										name="curriculum.curriculumid"
										value="${syllabus.curriculum.curriculumid}" /> <input
										type="hidden" name="department.departmentid"
										value="${syllabus.department.departmentid}" />
								</form></td>
						</tr>

					</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script language="javascript">
	function notDoselectonSubmit(value,value2)
	{	
		var name = "notDo"+value;
		var form=document.getElementById(name);
		// 检测是否有专业被选中
		if(value2 != -1){
		 	if($(form).find("input:checked").length == 0) {
				alert("请选择专业方向！");
				return;
			} 
		}
		form.action="${pageContext.request.contextPath}/practicePlan_toPracLesCourseDesignPage.action";
		form.submit();  
	}
	
	function doselectonSubmit(value,value2)
	{	
		var name = "do"+value;
		var form=document.getElementById(name);
		// 检测是否有专业被选中
 			if(value2 != -1){
		 	if($(form).find("input:checked").length == 0) {
				alert("请选择专业方向！");
				return;
			} 
		}  
		form.action="${pageContext.request.contextPath}/practicePlan_toHavePracLesCourseDesignPage.action";
		form.submit();  
	}
	function dodelete(value,value2)
	{	
		var name = "do"+value;
		var form=document.getElementById(name);
		// 检测是否有专业被选中
		if(value2 != -1){
		 	if($(form).find("input:checked").length == 0) {
				alert("请选择专业方向！");
				return;
			} 
		}
		form.action="${pageContext.request.contextPath}/practicePlan_deletePracSyllabusCourseDesign.action";
		form.submit();  
	}
	</script>
	<script type="text/javascript">
	//checkbox 全选/取消全选  
	var isCheckAll = false;
	function swapCheck() {
		if(isCheckAll) {
			$("input[type='checkbox']").each(function() {
				this.checked = false;
			});
			isCheckAll = false;
		} else {
			$("input[type='checkbox']").each(function() {
				this.checked = true;
			});
			isCheckAll = true;
		}
	}
</script>
	<script type="text/javascript">
	$(document).ready(function() {
		$('.example-selectAllNumber').multiselect({
			includeSelectAllOption: true,
			selectAllNumber: false
		});
	});
</script>
	<script type="text/javascript">
	$(".copySelect").on("change", function() {
		var $parent = $(this).parents("tr");
		var sign_count = $parent.find(".sign_count").text();
		
		var form = $parent.find("form")[0];
		if(sign_count != "-1"){
		 	if($(form).find("input:checked").length == 0) {
				alert("请选择专业方向！");
				this.selectedIndex = 0;
				return;
			} 
		}
		form.action="${pageContext.request.contextPath}/practicePlan_toPracLesCourseDesignPageCopy.action?syllabusId_Copy=" + this.value;
		form.submit(); 
	});
</script>
</body>
</html>