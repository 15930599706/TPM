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
<script type="text/javascript" src="<%=basePath%>images/css/prettify.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/bootstrap-multiselect.js"></script>

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

button>a {
	color: #fff;
}

#box0 {
	float: left;
	width: 80px;
	font-family: "microsoft yahei";
	height: 40px;
	padding-top: 5px;
	padding-left: 24px;
}

#box00 {
	float: left;
	width: 700px;
	font-family: "microsoft yahei";
	height: 40px;
	padding-top: 10px;
}

#box2 {
	font-family: "microsoft yahei";
	height: 40px;
	float: left;
	width: 1200px;
	padding-top: 5px;
	padding-left: 24px;
}
</style>
</head>
<body>


	<div class="right_cont">
		<div class="title_right">
			<strong>课内实验分配实验室</strong>
		</div>
		<%-- ${pageContext.request.contextPath}/theoreticalPlan_todepartment.action?curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=0" --%>
		<div id="box2">
			<form
				action="${pageContext.request.contextPath}/theoreticalPlan_toExpdepartment.action"
				method="post" id="findcurriculum">
				<td>开课部门(学院)：</td>
				<td><select size="1" name="curriculum.college.collegeid">
						<!-- onchange="selectSubmit()"       <c:if test="${college.collegeid eq curriculum.college.collegeid }">selected="selected"</c:if> -->
						<c:forEach items="${pageBean.collegelist }" var="college">
							<option value="${college.collegeid}">${college.collegeCname}</option>
						</c:forEach>

				</select></td>
				<td>课程状况：</td>
				<td><select size="1" name="departmenttag">
						<!-- onchange="selectSubmit()" -->
						<option value="0"
							<c:if test="${newdepartmenttag eq '0' }">selected="selected"</c:if>>---所有课程---</option>
						<option value="-1"
							<c:if test="${newdepartmenttag eq '-1' }">selected="selected"</c:if>>未分配课程</option>
						<option value="1"
							<c:if test="${newdepartmenttag eq '1' }">selected="selected"</c:if>>已分配课程</option>

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


		<div style="width: 1000px;">
			<div id="box0">
				<button class="btn btn-info  ">
					<a
						href="${pageContext.request.contextPath}/theoreticalPlan_exportInclassexperimentExcel.action?curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=0">导出课表</a>
				</button>
			</div>
		</div>
	</div>
	<div style="width: 90%; margin-left: 95px;">

		<table class="table table-bordered table-striped table-hover">
			<tbody>
			<thead>
				<tr>
				<tr align="center">

					<th nowrap="nowrap"><small>课程代码</small></th>
					<th nowrap="nowrap"><small>课程中文名称</small>
					</td>
					<th nowrap="nowrap"><small>课程性质</small></th>
					<th nowrap="nowrap"><small>课程类别</small></th>
					<th nowrap="nowrap"><small>开课学院</small></th>
					<th nowrap="nowrap"><small>选课专业——分配情况</small></th>
					<th nowrap="nowrap"><small>分配状态</small></th>
					<th nowrap="nowrap"><small>操作</small></th>
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
							<select class="example-selectAllNumber" multiple="multiple"
								name="depart">
								<c:forEach items="${pageBean.theoreticalLessonlist[xh.count-1]}"
									var="theoreticalLesson">
									<label> <c:if
											test="${theoreticalLesson.cteachDepartment.departmentCname ne null or theoreticalLesson.cteacher.username ne null or theoreticalLesson.experiment.experimentname ne null or theoreticalLesson.experimenter.username ne null}">
											<span style="color: green;"> <!-- 如果课内实验任课老师不空时 --> <c:if
													test="${theoreticalLesson.cteacher.username ne null and theoreticalLesson.cteachDepartment.departmentCname eq null}">
													<c:if
														test="${theoreticalLesson.professional.professionalname ne null}">
														<option
															value="${theoreticalLesson.professional.professionalid}">${theoreticalLesson.department.departmentCname}（${theoreticalLesson.professional.professionalname}）——${theoreticalLesson.cteacher.username }(老师)</span>
											</option>
										</c:if> <c:if
											test="${theoreticalLesson.professional.professionalname eq null}">
											<span style="color: green;">
												<option value="${theoreticalLesson.department.departmentid}">${theoreticalLesson.department.departmentCname}——${theoreticalLesson.cteacher.username }(老师)
											</span>
											</option>
										</c:if> </c:if> <!-- 如果课内实验开课专业不空时 --> <c:if
											test="${theoreticalLesson.cteachDepartment.departmentCname ne null and theoreticalLesson.cteacher.username eq null}">
											<c:if
												test="${theoreticalLesson.professional.professionalname ne null}">
												<option
													value="${theoreticalLesson.professional.professionalid}">${theoreticalLesson.department.departmentCname}（${theoreticalLesson.professional.professionalname}）——${theoreticalLesson.cteachDepartment.departmentCname }</span></option>
											</c:if>
											<c:if
												test="${theoreticalLesson.professional.professionalname eq null}">
												<span style="color: green;">
													<option
														value="${theoreticalLesson.department.departmentid}">${theoreticalLesson.department.departmentCname}——${theoreticalLesson.cteachDepartment.departmentCname }
												</span>
												</option>
											</c:if>
										</c:if> <!-- 课内实验开课专业分配到老师时 --> <c:if
											test="${theoreticalLesson.cteachDepartment.departmentCname ne null and theoreticalLesson.cteacher.username ne null}">
											<c:if
												test="${theoreticalLesson.professional.professionalname ne null}">
												<option
													value="${theoreticalLesson.professional.professionalid}">${theoreticalLesson.department.departmentCname}（${theoreticalLesson.professional.professionalname}）——${theoreticalLesson.cteacher.username }(老师)</span></option>
											</c:if>
											<c:if
												test="${theoreticalLesson.professional.professionalname eq null}">
												<span style="color: green;">
													<option
														value="${theoreticalLesson.department.departmentid}">${theoreticalLesson.department.departmentCname}——${theoreticalLesson.cteacher.username }(老师)
												</span>
												</option>
											</c:if>
										</c:if> <!-- 如果实验室不空时 --> <c:if
											test="${theoreticalLesson.experiment.experimentname ne null and theoreticalLesson.experimenter.username eq null}">
											<c:if
												test="${theoreticalLesson.professional.professionalname ne null}">
												<option
													value="${theoreticalLesson.professional.professionalid}">${theoreticalLesson.department.departmentCname}（${theoreticalLesson.professional.professionalname}）——${theoreticalLesson.experiment.experimentname }</span></option>
											</c:if>
											<c:if
												test="${theoreticalLesson.professional.professionalname eq null}">
												<span style="color: green;">
													<option
														value="${theoreticalLesson.department.departmentid}">${theoreticalLesson.department.departmentCname}——${theoreticalLesson.experiment.experimentname }
												</span>
												</option>
											</c:if>
										</c:if> <!-- 如果实验员不空时 --> <c:if
											test="${theoreticalLesson.experimenter.username ne null and theoreticalLesson.experiment.experimentname eq null}">
											<c:if
												test="${theoreticalLesson.professional.professionalname ne null}">
												<option
													value="${theoreticalLesson.professional.professionalid}">${theoreticalLesson.department.departmentCname}（${theoreticalLesson.professional.professionalname}）——${theoreticalLesson.experimenter.username }(实验员)</span></option>
											</c:if>
											<c:if
												test="${theoreticalLesson.professional.professionalname eq null}">
												<span style="color: green;">
													<option
														value="${theoreticalLesson.department.departmentid}">${theoreticalLesson.department.departmentCname}——${theoreticalLesson.experimenter.username }(实验员)
												</span>
												</option>
											</c:if>
										</c:if> <!-- 由实验室分配到实验员时 --> <c:if
											test="${theoreticalLesson.experimenter.username ne null and theoreticalLesson.experiment.experimentname ne null}">
											<c:if
												test="${theoreticalLesson.professional.professionalname ne null}">
												<option
													value="${theoreticalLesson.professional.professionalid}">${theoreticalLesson.department.departmentCname}（${theoreticalLesson.professional.professionalname}）——${theoreticalLesson.experimenter.username }(实验员)</span></option>
											</c:if>
											<c:if
												test="${theoreticalLesson.professional.professionalname eq null}">
												<span style="color: green;">
													<option
														value="${theoreticalLesson.department.departmentid}">${theoreticalLesson.department.departmentCname}——${theoreticalLesson.experimenter.username }(实验员)
												</span>
												</option>
											</c:if>
										</c:if> </c:if> <!-- 已分配课程 --> <c:if
											test="${theoreticalLesson.cteachDepartment.departmentCname eq null and theoreticalLesson.cteacher.username eq null and theoreticalLesson.experiment.experimentname eq null and theoreticalLesson.experimenter.username eq null}">
											<c:if
												test="${theoreticalLesson.experiment.experimentname eq null}">
												<span style="color: red;"> <c:if
														test="${theoreticalLesson.professional.professionalname ne null}">
														<option
															value="${theoreticalLesson.professional.professionalid}">${theoreticalLesson.department.departmentCname}（${theoreticalLesson.professional.professionalname}）——未分配！</span>
												</option>
											</c:if>
											<c:if
												test="${theoreticalLesson.professional.professionalname eq null}">
												<option value="${theoreticalLesson.department.departmentid}">${theoreticalLesson.department.departmentCname}——未分配！</span></option>
											</c:if>
										</c:if> </c:if> <!-- 未分配 -->
									</label>
								</c:forEach>

							</select> <input type="hidden" name="curriculum.curriculumid"
								value="${curriculum.curriculumid }" />
						</form>
					</td>
					<td nowrap="nowrap"><small> <c:forEach
								items="${pageBean.theoreticalLessonlist[xh.count-1]}"
								var="theoreticalLesson" end="0">
								<c:if
									test="${theoreticalLesson.cteachDepartment.departmentCname eq null and theoreticalLesson.cteacher.username eq null and theoreticalLesson.experiment.experimentname eq null and theoreticalLesson.experimenter.username eq null}">
									<span style="color: red;"> 未分配</span>
								</c:if>
								<c:if
									test="${theoreticalLesson.cteachDepartment.departmentCname ne null or theoreticalLesson.cteacher.username ne null or theoreticalLesson.experiment.experimentname ne null or theoreticalLesson.experimenter.username ne null}">
									<span style="color: green;"> 已分配<%-- (<c:if test="${theoreticalLesson.experiment.experimentname ne null}"><span>实验室</span></c:if><c:if test="${theoreticalLesson.experiment.experimentname eq null and theoreticalLesson.experimenter.username ne null}"><span>实验员</span></c:if>) --%></span>
								</c:if>

							</c:forEach> <%-- <c:if test="${curriculum.department.departmentCname ne null}"><a href="${pageContext.request.contextPath}/curriculum_gogivedepartment.action?curriculumid=${curriculum.curriculumid }&college.collegeid=${user.college.collegeid}">修改开课专业</a></c:if> --%>
					</small></td>
					<td nowrap="nowrap"><small> <c:forEach
								items="${pageBean.theoreticalLessonlist[xh.count-1]}"
								var="theoreticalLesson" end="0">
								<c:if
									test="${theoreticalLesson.experiment.experimentname eq null}">
									<a href="javascript:void(0)"
										onclick="selectonSubmit('${xh.count}')">分配任课实验室</a>
								</c:if>
								<c:if
									test="${theoreticalLesson.experiment.experimentname ne null}">
									<a href="javascript:void(0)"
										onclick="selectonSubmit('${xh.count}')">修改任课实验室</a>
								</c:if>
							</c:forEach> <%-- <c:if test="${curriculum.department.departmentCname ne null}"><a href="${pageContext.request.contextPath}/curriculum_gogivedepartment.action?curriculumid=${curriculum.curriculumid }&college.collegeid=${user.college.collegeid}">修改开课专业</a></c:if> --%>
					</small></td>


				</tr>
			</c:forEach>
			<c:if test="${pageBean.totalCount == 0 }">
				<tr align="center">
					<th nowrap="nowrap" colspan="11">没有符合您搜索要求的课程</th>
				</tr>
			</c:if>



			<form method="post" name="form11"
				action="${pageContext.request.contextPath}/theoreticalPlan_toExpdepartment.action?curriculum.college.collegeid=
						${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}
						&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=0">
				<tfoot>
					<tr align="center">
						<th nowrap="nowrap" colspan="11"><c:if
								test="${pageBean.currentpage != 1 }">
								<a
									href="${pageContext.request.contextPath}/theoreticalPlan_toExpdepartment.action?curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=0"><small>首页</small></a>&nbsp;&nbsp;&nbsp;
										<a
									href="${pageContext.request.contextPath}/theoreticalPlan_toExpdepartment.action?currentpage=${currentpage-1 }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=${newdepartmenttag}"><small>上一页</small></a>
							</c:if>&nbsp;&nbsp;&nbsp; <small>共${pageBean.totalCount }条记录,共计${pageBean.totalPage }页,当前第${pageBean.currentpage }页</small>&nbsp;&nbsp;&nbsp;
							<c:if test="${pageBean.currentpage != pageBean.totalPage }">
								<a
									href="${pageContext.request.contextPath}/theoreticalPlan_toExpdepartment.action?currentpage=${currentpage+1 }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=${newdepartmenttag}"><small>下一页</small></a>&nbsp;&nbsp;&nbsp;
										
										<a
									href="${pageContext.request.contextPath}/theoreticalPlan_toExpdepartment.action?currentpage=${pageBean.totalPage }&curriculum.college.collegeid=${user.college.collegeid}&curriculum.curriculumid=${theoreticalLesson.curriculum.curriculumid}&curriculum.curriculumCname=${theoreticalLesson.curriculum.curriculumCname}&departmenttag=${newdepartmenttag}"><small>尾页</small></a>
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

	<script type="text/javascript">
			function selectSubmit() {
				var form = document.getElementById("findcurriculum");
				form.action = "${pageContext.request.contextPath}/curriculum_todepartment.action";
				form.submit();
			}
		</script>
	<script language="javascript">
		function selectonSubmit(value)
		{	
			var name = "like"+value;
			/* alert(name); */
			var form = document.getElementById(name);
			// 检测是否有专业被选中
			if($(form).find("input:checked").length == 0) {
				alert("请选择选课专业！");
				return;
			}
			/* alert(form); */
			form.action = "${pageContext.request.contextPath}/theoreticalPlan_gogiveExpdepartment.action?departmenttag&newcurrentpage=${currentpage}";
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

</body>
</html>
