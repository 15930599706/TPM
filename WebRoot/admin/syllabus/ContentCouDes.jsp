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
<script type="text/javascript">
			function timedMsg() {
				var t = setTimeout("alert('设置成功')", 1000)
			}
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
		action="${pageContext.request.contextPath}/ConCourseDesign_updateConCourseDesign.action?syllabusId=${syllabusId}&practiceLessonid=${newpracticeLesson.practiceLessonid}"
		method="post">
		<div class="right_cont">
			<table style="margin-left: 120px;">

				<table style="margin-left: 130px;">
					<tr>

						<div class="title_right">
							<strong>课程设计（论文）的基本内容</strong>
						</div>
					</tr>
					<tr>
						<td class="input" colspan="3" style="height: 128px;"><textarea
								name="content" style="height: 150px; width: 460px;">${conCourseDesign.content}</textarea></td>
						</td>

					</tr>

				</table>
				<table style="margin-left: 130px;">
					<tr>

						<div class="title_right">
							<strong> 课程设计（论文）的基本要求</strong>
						</div>
					</tr>
					<tr>
					<tr>
						<td class="input" colspan="3" style="height: 128px;"><textarea
								name="request" style="height: 150px; width: 460px;">${conCourseDesign.request}</textarea></td>
						</td>

					</tr>

					<input type="hidden" name="conCourseDesignid"
						value="${conCourseDesign.conCourseDesignid}" />
				</table>
				<table style="margin-left: 130px;">
					<tr>

						<div class="bottom">
							<button type="submit" value="提交">提交</button>
						</div>
						</div>
						</form>
</body>

</html>
