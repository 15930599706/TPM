<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath %>images/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath %>images/css/css.css" />
<script type="text/javascript"
	src="<%=basePath %>images/js/jquery1.9.0.min.js"></script>
<script type="text/javascript"
	src="<%=basePath %>images/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/sdmenu.js"></script>
<script type="text/javascript"
	src="<%=basePath %>images/js/laydate/laydate.js"></script>

<link rel="stylesheet"
	href="<%=basePath %>admin/editor/themes/default/default.css" />
<link rel="stylesheet"
	href="<%=basePath %>admin/editor/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=basePath %>admin/editor/kindeditor.js"></script>
<script charset="utf-8" src="<%=basePath %>admin/editor/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="<%=basePath %>admin/editor/plugins/code/prettify.js"></script>
<script>
			KindEditor.ready(function(K) {
				var editor1 = K.create('textarea[name="noticecontent"]', {
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


</head>


<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>新建通知</strong>
		</div>
		<div style="width: 900px; margin: auto;">
			<form
				action="${pageContext.request.contextPath}/notice_addnotice.action"
				method="post" name="form1">
				<table class="table table-bordered">

					<tr>
						<td width="40%" height="40px" align="right" nowrap="nowrap"
							bgcolor="#f1f1f1"><strong>通知标题：</strong></td>
						<td><input style="height: 27px;" type="text"
							name="noticetitle" class="span6" required /></td>
					</tr>
					<tr>
						<td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1"><strong>详细内容：</strong></td>
						<td><textarea name="noticecontent" cols="100" rows="8"
								style="width: 700px; height: 400px; visibility: hidden;"></textarea></td>
					</tr>
					<input type="hidden" name="user.tnum" value="${user.tnum }"></input>
					<tr>
						<td class="text-center" colspan="2"><input type="submit"
							value="确定" class="btn btn-info  " style="width: 80px;" /></td>
					</tr>
				</table>

			</form>
		</div>
	</div>
</body>