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

<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>实践课大纲录入——<font style="color: #0000c6;">其他</font></strong>
		</div>
		<div class="link">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesFieldWorkPage.action?user.tnum=${user.tnum}"><font
				style="color: #000;">编写实习大纲</font></a>
		</div>
		<div class="link1">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesCourseDesignPage.action?user.tnum=${user.tnum}"><font
				style="color: #000;">编写课程设计（学年论文）大纲</font></a>
		</div>
		<div class="link2">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesInnerExperimentPage.action?user.tnum=${user.tnum}"><font
				style="color: #000;">编写实验大纲</font></a>
		</div>
		<div class="link3">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesGraduationProjectPage.action?user.tnum=${user.tnum}"><font
				style="color: #000;">毕业设计大纲</font></a>
		</div>
		<div class="link4">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracticeOther.action?user.tnum=${user.tnum}"><font
				style="color: #0000c6;">编写其他大纲</font></a>
		</div>
		<div style="padding-left: 24px;">
			<div style="border-bottom: #CCCCCC 1 solid; padding-top: 30px;">
				<h4 style="color: green;">已编写 “其他” 大纲：</h4>
			</div>

			<div style="width: 90%; margin-left: 20px; margin-top: 10px;">
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
							<th width="80" nowrap="nowrap"><small>修改大纲</small></th>
							<th width="80" nowrap="nowrap"><small>删除大纲</small></th>
							<th width="80" nowrap="nowrap"><small>生成文档</small></th>
							<th nowrap="nowrap"><small>可供下载</small></th>

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



							<td nowrap="nowrap"><a href="javascript:void(0)"
								onclick="doselectonSubmit('${xh.count}',${haveCount[xh.count-1]})"><small>修改</small></a>
							</td>
							<td nowrap="nowrap"><a href="javascript:void(0)"
								onclick="if (confirm('您确定要删除该大纲吗？')) return dodelete('${xh.count}',${haveCount[xh.count-1]}); else return false;"><small>删除</small></a>
							</td>
							<td nowrap="nowrap"><a
								href="${pageContext.request.contextPath}/ExportWork_exportTheo.action?syllabusid=${syllabusId[xh.count-1]}&theoid=${haveSyllabus.practiceLessonid}&curriculumId=${haveSyllabus.curriculum.curriculumid}"><small>下载</small></a>
							</td>
							<%
							    	List<String> list = new ArrayList<String>();
							    	list.add("未选择");
							    	list.add("是");
							    	list.add("否");
							    	request.setAttribute("list", list);
							 %>
							<td><select size="1" id="load${xh.count}"
								style="width: 10em;" onchange="LoadFlager4('${xh.count}')">
									<c:forEach items="${list}" var="keyword" varStatus="id">
										<option value="${keyword}"
											<c:if test="${keyword eq haveSyllabus.workload4}">
													selected="selected"
										</c:if>>${keyword}</option>
									</c:forEach>
							</select></td>


						</tr>

					</c:forEach>

					</tbody>
				</table>
			</div>

			<div style="border-bottom: #CCCCCC 1 solid;">
				<h4 style="color: red;">
					未编写 “其他” 大纲：<font style="font-size: 14px;">（注意：复制功能将不复制“教学目标及对应关系”，采用此功能后请再自行填写该项！）</font>
				</h4>

			</div>
			<div style="width: 90%; margin-left: 20px; margin-top: 10px;">
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
							<th width="80" nowrap="nowrap"><small>编写新大纲</small></th>
							<th width="80" nowrap="nowrap"><small>复制大纲</small></th>

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


							<td nowrap="nowrap"><a href="javascript:void(0)"
								onclick="notDoselectonSubmit('${xh.count}',${count[xh.count-1]})"><small>编写</small></a>
								<div class="sign_count" style="display: none;">${count[xh.count-1]}</div>
							</td>
							<td><select class="copySelect" size="1"
								style="width: 10em;">
									<option value="-1">请选择大纲序号</option>
									<c:forEach items="${haveSyllabusList}" var="haveSyllabus"
										varStatus="xh">
										<option value="${syllabusId[xh.count-1]}">${xh.count}</option>
									</c:forEach>
							</select></td>
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
		form.action="${pageContext.request.contextPath}/practicePlan_toPracLesOtherPage.action";
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
		form.action="${pageContext.request.contextPath}/practicePlan_toHavePracLesOtherPage.action";
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
		form.action="${pageContext.request.contextPath}/practicePlan_deletePracSyllabusOther.action";
		form.submit();  
	}
	//数据库传是否
	function LoadFlager4(value){
		var name = "do"+value;
		var form=document.getElementById(name);
		var id="load"+value;
		var value = document.getElementById(id).value;
		if(value=='是'){
			form.action="${pageContext.request.contextPath}/practicePlan_LoadFlagUp4.action?isLoad=1&user.tnum=${user.tnum}";
			//                                            theoreticalPlan_toCheckPracticeOther.action?user.tnum=${user.tnum}
		}else{
			form.action="${pageContext.request.contextPath}/practicePlan_LoadFlagUp4.action?isLoad=0&user.tnum=${user.tnum}";
		}
		
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
		form.action="${pageContext.request.contextPath}/practicePlan_toPracLesOtherPageCopy.action?syllabusId_Copy=" + this.value;
		form.submit(); 
	});
</script>

</body>
</html>