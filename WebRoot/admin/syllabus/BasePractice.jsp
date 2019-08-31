<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
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
<link rel="stylesheet" href="../editor/themes/default/default.css" />
<link rel="stylesheet" href="../editor/plugins/code/prettify.css" />
<script charset="utf-8" src="../editor/kindeditor.js"></script>
<script charset="utf-8" src="../editor/lang/zh_CN.js"></script>
<script charset="utf-8" src="../editor/plugins/code/prettify.js"></script>
<script>
			KindEditor.ready(function(K) {
				var editor1 = K.create('textarea[name="content1"]', {
					cssPath: '../editor/plugins/code/prettify.css',
					uploadJson: '../editor/jsp/upload_json.jsp',
					fileManagerJson: '../editor/jsp/file_manager_json.jsp',
					allowFileManager: true,
					afterCreate: function() {
						var self = this;
						K.ctrl(document, 13, function() {
							self.sync();
							document.forms['form1'].submit();
						});
						K.ctrl(self.edit.doc, 13, function() {
							self.sync();
							document.forms['form1'].submit();
						});
					}
				});
				prettyPrint();
			});
		</script>
<style type="text/css">
.bottom {
	padding-top: 10px;
	padding-left: 350px;
}

table {
	font-family: "微软雅黑";
	font-size: 13px;
	color: #000000;
}

.item-name {
	height: 50px;
	text-align: right;
}

.input {
	height: 50px;
	text-align: left;
}
</style>
</head>

<body>
	<form
		action="${pageContext.request.contextPath}/FieldWork_updateFieldWork.action?syllabusId_FieldWorkid=${syllabusId_FieldWorkid}&practiceLessonid=${newpracticeLesson.practiceLessonid}"
		method="post">
		<div class="right_cont">
			<table style="margin-left: 120px;">
				<tr>

					<div class="title_right">
						<strong>课程概况</strong>
					</div>
				</tr>
				<tr>
					<td class="item-name">课程名称：</td>
					<td>${curriculum.curriculumCname }</td>
					<td class="item-name">课程编码：</td>
					<td>${curriculum.curriculumid }</td>
				</tr>
				<tr>
					<td class="item-name">英文名称：</td>
					<td>${curriculum.curriculumEname }</td>
					<td class="item-name">课程类别：</td>
					<td>${curriculum.natureOfCourse.natureOfCoursename }</td>
				</tr>
				<tr>

					<td class="item-name">学时：</td>
					<td>${curriculum.hoursOfALL }</td>
				</tr>

				<tr>
					<td class="item-name">适用专业：</td>
					<c:if test="${flage == 1}">
						<td class="input"><c:forEach items="${applicationSyllabus}"
								var="applicationSyllabus">
						${applicationSyllabus.professional.professionalname} </br>
							</c:forEach></td>
					</c:if>

					<c:if test="${flage == -1}">
						<td class="input"><input type="text" name="fordepartment"
							disabled
							value="${applicationSyllabus.department.departmentCname }" /></td>
					</c:if>

				</tr>
				<tr>
					<td class="item-name">对应理论课：</td>
					<td colspan="3" class="input"><input type="text"
						name="maptheo" value="${fieldWork.maptheo}" style="width: 398px;" /></td>

				</tr>
			</table>
			<table style="margin-left: 130px;">
				<tr>

					<div class="title_right">
						<strong>学时分配</strong>
					</div>
				</tr>
				<tr>
					<td><textarea name="distribPer"
							style="width: 460px; height: 150px;">${fieldWork.distribPer}</textarea></td>
					</td>

				</tr>


			</table>
			<table style="margin-left: 130px;">
				<tr>

					<div class="title_right">
						<strong>成绩考核与评定</strong>
					</div>
				</tr>
				<tr>
					<td><textarea name="assess"
							style="width: 460px; height: 150px;">${fieldWork.assess}</textarea></td>
					</td>

				</tr>
				<input type="hidden" name="fieldWorkid"
					value="${fieldWork.fieldWorkid}" />
			</table>

			<div class="bottom">
				<button type="submit" value="提交">提交</button>
			</div>
</body>

</html>
