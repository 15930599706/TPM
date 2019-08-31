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
<script type="text/javascript" src="<%=basePath%>images/js/List.js"></script>
<style type="text/css">
p.title {
	font-size: 16px;
	font-weight: bold;
	color: white;
	padding: 15px 0px 15px 10px;
	width: 800px;
	height: 5px;
}

.input {
	font-size: 15px;
	font-weight: bold;
}

p.title-new {
	font-size: 16px;
	font-weight: bold;
	color: white;
	padding: 15px 0px 15px 10px;
	background-color: #ff6a00;
	width: 800px;
}

.title>span {
	color: #000000;
	font-size: 12px;
	font-family: "微软雅黑";
	padding-left: 5px;
}

p.title-new1 {
	font-size: 16px;
	font-weight: bold;
	color: white;
	padding: 15px 0px 15px 10px;
	background-color: #447539;
	width: 800px;
}

#edit_template {
	background-color: #dcf5d6;
	width: 810px;
}

#box9 a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	width: 160px;
	height: 20px;
}

#box9 {
	height: 35px;
	float: left;
	width: 160px;
}

#box9 a {
	display: block;
	height: 20px;
	width: 160px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/back-16.png) no-repeat 25px 2px;
	text-indent: 40px;
	text-align: center;
}
</style>
</head>

<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>课程设计教材信息</strong>
		</div>
		<div id="box9">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toHavePracLesCourseDesignPage.action?syllabusId=${syllabusId}&practiceLessonid=${newpracticeLesson.practiceLessonid}">返回录入其他内容</a>
		</div>
		<div id="container" style="margin-top: 45px; margin-left: 24px;"></div>
		<div id="normal_template" style="display: none;">
			<p class="title">
				<button onclick="changeStatus({{item._id}}, 'edit')">编辑</button>
				<button
					onclick="onDelete({{item._id}}, {{item.practiceBooks_CourseDesignid}})">删除</button>
				<span>{{temp.__index+1}}、</span> <span>{{item.name}}</span>
			</p>
		</div>
		<div id="edit_template" style="display: none;">
			<p class="title-new1">编辑教材及相关资源</p>
			<div
				style="width: 760px; height: 40px; font-size: 15px; font-weight: bold;">
				<tr>
					<td>资源名称：</td>
					<td><input id="update_name" type="text" value="{{item.name}}"
						style="width: 500px;" /></td>
				</tr>
			</div>
			<p class="title-new1">
				<button
					onclick="onUpdate({{item._id}}, {{item.practiceBooks_CourseDesignid}})">更新</button>
				<button onclick="changeStatus({{item._id}}, 'normal')">取消</button>
			</p>
		</div>

		<div
			style="background-color: #ffecc5; width: 810px; margin-left: 24px;">
			<p class="title-new">添加教材及相关资源</p>
			<div
				style="width: 760px; height: 40px; font-size: 15px; font-weight: bold;">
				<tr>
					<td>资源名称：</td>
					<td><input id="add_name" type="text" style="width: 500px;" />
					</td>
				</tr>
			</div>
			<p class="title-new">
				<button onclick="onAdd()">保存</button>
				<button onclick="clearForm()">清除</button>
			</p>
		</div>
	</div>
</body>
<script type="text/javascript">
		var init_data =${practiceBook};
		// 创建实例
		var list = new List(container, init_data);
		// 指定模板
		list.setNormalTemplate(document.getElementById("normal_template").innerHTML);
		list.setEditTemplate(document.getElementById("edit_template").innerHTML);
		//刷新
		list.refresh();

		// 事件
		function onAdd() {
			var item = {};
			item.name = document.getElementById("add_name").value;
			var url = '${pageContext.request.contextPath}/practiceBookCourseDesign_addCourseBooks.action?syllabusId=${syllabusId}';
			console.log(item);
			$.post(url, item, function(data) {
			item.practiceBooks_CourseDesignid = data.content;
				list.add(item);
				alert("添加成功");
			});
		}

		function onUpdate(id,practiceBooks_CourseDesignid) {
			var options = {};
			options.name = document.getElementById("update_name").value;
			options.practiceBooks_CourseDesignid = practiceBooks_CourseDesignid;
			var url = '${pageContext.request.contextPath}/practiceBookCourseDesign_updateCourseBooks.action?syllabusId=${syllabusId}';
			console.log(options);
			$.post(url, options, function(data) {
				list.update(id, options);
				alert("更新成功");
			});
		}

		function onDelete(id,practiceBooks_CourseDesignid) {
			var dele = {};
			dele.practiceBooks_CourseDesignid = practiceBooks_CourseDesignid;
			var url = '${pageContext.request.contextPath}/practiceBookCourseDesign_deleteCourseBooks.action';
			console.log(dele);
			$.post(url, dele, function(data) {
				list.delete(id, dele);
				alert("删除成功");
			});
		}
		// 切换状态
		function changeStatus(id, status) {

			list.changeStatus(id, status);
		}
		// 清空表单
		function clearForm() {
			document.getElementById("add_name").value = "";
		}
	</script>

</html>