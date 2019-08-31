<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath %>/images/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath %>/images/css/css.css" />
<script type="text/javascript"
	src="<%=basePath %>/images/js/jquery1.9.0.min.js"></script>
<script type="text/javascript"
	src="<%=basePath %>/images/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/images/js/sdmenu.js"></script>
<script type="text/javascript"
	src="<%=basePath %>/images/js/laydate/laydate.js"></script>

<link rel="stylesheet"
	href="<%=basePath %>admin/editor/themes/default/default.css" />
<link rel="stylesheet"
	href="<%=basePath %>admin/editor/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=basePath %>admin/editor/kindeditor.js"></script>
<script charset="utf-8" src="<%=basePath %>admin/editor/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="<%=basePath %>admin/editor/plugins/code/prettify.js"></script>
<style type="text/css">
.bj {
	padding-left: 24px;
}
</style>
<script>
			KindEditor.ready(function(K) {
				var editor1 = K.create('textarea[class="content"]', {
					cssPath: 'editor/plugins/code/prettify.css',
					uploadJson: 'editor/jsp/upload_json.jsp',
					fileManagerJson: 'editor/jsp/file_manager_json.jsp',
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

</head>

<body>
	<div class="right_cont">

		<div class="title_right">
			<strong>课外安排与要求</strong>
		</div>
		<form
			action="${pageContext.request.contextPath}/TrainingAnother_updataanother.action"
			method="post" name="form1">
			<div class="bj">
				<div>
					<tr>
						<td><font size="2px" color="#000000">专业名称：</td>
						<td><font size="2px" color="red">${department.departmentCname}</td>
					</tr>

				</div>
				<c:if test="${user.adminlevel ==1 || tag == 'revise'}">
					<div>
						<input type="hidden" name="trainingAnotherid"
							value="${trainingAnother.trainingAnotherid}" /> <input
							type="hidden" name="department.departmentid"
							value="${department.departmentid}" />
						<tr>
							<h5>
								<font size="3px" color="#000000">课外安排与要求:
							</h5>
							<td><textarea style="width: 460px; height: 150px;"
									name="extracurricular">${trainingAnother.extracurricular}</textarea></td>

						</tr>
					</div>
					<div>
						<tr>
							<h5>
								<font size="3px" color="#000000">企业集中实践安排与要求:
							</h5>
							<td><textarea name="businessPractice"
									style="width: 460px; height: 150px;">${trainingAnother.businessPractice}</textarea></td>

						</tr>
					</div>
					<div>
						<tr>
							<h5>
								<font size="3px" color="#000000">企业教师授课安排与要求:
							</h5>
							<td><textarea name="businessTeacher"
									style="width: 460px; height: 150px;">${trainingAnother.businessTeacher}</textarea></td>

						</tr>
					</div>

					<div class="bottom" style="padding-top: 15px; padding-left: 200px;">
						<button type="submit" value="保存">保存</button>
					</div>
				</c:if>


				<c:if test="${user.adminlevel !=1 && tag != 'revise'}">
					<div>
						<input type="hidden" name="trainingAnotherid"
							value="${trainingAnother.trainingAnotherid}" /> <input
							type="hidden" name="department.departmentid"
							value="${department.departmentid}" />
						<tr>
							<h5>
								<font size="3px" color="#000000">课外安排与要求:
							</h5>
							<td>${trainingAnother.extracurricular}</td>

						</tr>
					</div>
					<div>
						<tr>
							<h5>
								<font size="3px" color="#000000">企业集中实践安排与要求:
							</h5>
							<td>${trainingAnother.businessPractice}</td>

						</tr>
					</div>
					<div>
						<tr>
							<h5>
								<font size="3px" color="#000000">企业教师授课安排与要求:
							</h5>
							<td>${trainingAnother.businessTeacher}</td>

						</tr>
					</div>

				</c:if>

			</div>
		</form>
	</div>
</body>