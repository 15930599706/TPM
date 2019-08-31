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
	background-color: #07519A;
	width: 800px;
}

#edit_template {
	background-color: #dcf5d6;
	width: 810px;
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

p.title-new1 {
	font-size: 16px;
	font-weight: bold;
	color: white;
	padding: 15px 0px 15px 10px;
	background-color: #447539;
	width: 800px;
}

.new {
	margin-left: 70px;
}

.link>a {
	display: block;
	background: url(<%=basePath%>images/img/back-16.png) no-repeat 20px 2px;
	height: 20px;
	width: 80px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: blue;
	font-size: 13px;
	text-indent: 40px;
	text-align: left;
	float: left;
}

.link>a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	width: 100px;
	height: 30px;
}

#container {
	padding-top: 17px;
}
</style>
</head>

<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>三级项目内容</strong>

		</div>
		<div class="link">
			<c:if test="${newtheoreticalLesson.curriculum.courseLei eq '理论课'}">
				<a
					href="${pageContext.request.contextPath}/ContentTheo_toContentTheoPage.action?syllabusId=${syllabusId}&theoreticalLessonId=${newtheoreticalLesson.theoreticalLessonid}&curriculumId=${newtheoreticalLesson.curriculum.curriculumid }">返回</a>
			</c:if>
			<c:if test="${newtheoreticalLesson.curriculum.courseLei eq '实践课'}">
				<a
					href="${pageContext.request.contextPath}/ContentTheo_toContentTheoPage.action?syllabusId=${syllabusId}&theoreticalLessonId=${newtheoreticalLesson.practiceLessonid}&curriculumId=${newtheoreticalLesson.curriculum.curriculumid }"><small>返回</small></a>
			</c:if>
		</div>

		<div id="container" style="padding-top: 30px; padding-left: 24px;"></div>
		<div id="normal_template" style="display: none;">
			<p class="title">
				<button onclick="changeStatus({{item._id}}, 'edit')">编辑</button>
				<button onclick="onDelete({{item._id}}, {{item.threeProjectid}})">删除</button>
				&nbsp;&nbsp;&nbsp;&nbsp; 三级项目<span>{{temp.__index+1}}</span>
				(支撑教学目标： <span>{{item.num}}</span>)
			</p>

			<table class="table1">

				<tr>
					<td class="input" colspan="3" style="height: 110px;">三级项目目标:<br>
						<textarea style="height: 80px; width: 800px;" readonly="readonly">{{item.aim}}
                       </textarea></td>
					</td>

				</tr>
				<tr>
					<td class="input" style="height: 100px;">三级项目内容:<br> <textarea
							style="height: 80px; width: 800px;" readonly="readonly">{{item.content}}
                       </textarea></td>
					</td>

				</tr>
				<tr>
					<td class="input" style="height: 100px;">三级项目的实施:<br> <textarea
							style="height: 80px; width: 800px;" readonly="readonly">{{item.implementation}}
                       </textarea></td>
					</td>

				</tr>
				<tr>
					<td class="input" style="height: 100px;">三级项目的进程安排:<br> <textarea
							style="height: 80px; width: 800px;" readonly="readonly">{{item.schedule}}
                       </textarea></td>
					</td>

				</tr>
				<tr>
					<td class="input" style="height: 100px;">三级项目的要求和成绩评定:<br>
						<textarea style="height: 80px; width: 800px;" readonly="readonly">{{item.request}}
                       </textarea></td>
					</td>

				</tr>


			</table>
		</div>
		<div id="edit_template" style="display: none;">
			<p class="title-new1">编辑三级项目内容单元</p>
			<div
				style="width: 800px; height: 40px; font-size: 15px; font-weight: bold;">
				<tr>

					<td>支撑教学目标：</td>
					<td><input type="text" id="update_num" style="width: 110px;"
						value="{{item.num}}" /></td>
				</tr>
			</div>
			<table class="table2">

				<tr>
					<td class="input" colspan="3" style="height: 110px;">三级项目目标:<br>
						<textarea id="update_aim" style="height: 80px; width: 800px;">{{item.aim}}</textarea></td>
					</td>

				</tr>
				<tr>
					<td class="input" style="height: 100px;">三级项目内容:<br> <textarea
							id="update_content" style="height: 80px; width: 800px;">{{item.content}}</textarea></td>
					</td>

				</tr>
				<tr>
					<td class="input" style="height: 100px;">三级项目的实施:<br> <textarea
							id="update_implementation" style="height: 80px; width: 800px;">{{item.implementation}}</textarea></td>
					</td>

				</tr>
				<tr>
					<td class="input" style="height: 100px;">三级项目的进程安排:<br> <textarea
							id="update_schedule" style="height: 80px; width: 800px;">{{item.schedule}}</textarea></td>
					</td>

				</tr>
				<tr>
					<td class="input" style="height: 100px;">三级项目的要求和成绩评定:<br>
						<textarea id="update_request" style="height: 80px; width: 800px;">{{item.request}}</textarea></td>
					</td>

				</tr>

			</table>
			<p class="title-new1">
				<button onclick="onUpdate({{item._id}},{{item.threeProjectid}})">更新</button>
				<button onclick="changeStatus({{item._id}}, 'normal')">取消</button>
			</p>
		</div>
	</div>
	<div
		style="background-color: #ffecc5; width: 810px; margin-left: 95px;"
		class="new">
		<p class="title-new">新建三级项目内容单元</p>
		<div
			style="width: 800px; height: 40px; font-size: 15px; font-weight: bold;">
			<tr>


				<td>支撑教学目标：</td>
				<td><input id="add_num" type="text" style="width: 110px;" /></td>
			</tr>

		</div>
		<table class="table2">

			<tr>
				<td class="input" colspan="3" style="height: 110px;">三级项目目标:<br>
				<textarea id="add_aim" style="height: 80px; width: 800px;"></textarea></td>
			</tr>
			<tr>
				<td class="input" style="height: 100px;">三级项目内容:<br>
				<textarea id="add_content" style="height: 80px; width: 800px;"></textarea></td>
			</tr>
			<tr>
				<td class="input" style="height: 100px;">三级项目的实施:<br>
				<textarea id="add_implementation"
						style="height: 80px; width: 800px;"></textarea></td>
			</tr>
			<tr>
				<td class="input" style="height: 100px;">三级项目的进程安排:<br>
				<textarea id="add_schedule" style="height: 80px; width: 800px;"></textarea></td>
			</tr>
			<tr>
				<td class="input" style="height: 100px;">三级项目的要求和成绩评定:<br>
				<textarea id="add_request" style="height: 80px; width: 800px;"></textarea></td>
			</tr>
		</table>
		<p class="title-new">
			<button onclick="onAdd()">保存</button>
			<button onclick="clearForm()">清除</button>
		</p>
	</div>
	</div>
</body>
<script type="text/javascript">
		// 创建实例
		var init_data=${threeProject};
	
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
			
			item.num = document.getElementById("add_num").value;
			item.aim = document.getElementById("add_aim").value;
			item.content = document.getElementById("add_content").value;
			item.implementation = document.getElementById("add_implementation").value;
			item.schedule = document.getElementById("add_schedule").value;
			item.request = document.getElementById("add_request").value;

			var url = '${pageContext.request.contextPath}/ThreeProject_addThreeProject.action?syllabusId=${syllabusId}';
			console.log(item);
			$.post(url, item, function(data) {
			item.threeProjectid = data.content;
				list.add(item);
				alert("添加成功");
			});
		}

		function onUpdate(id,threeProjectid) {
			var options = {};
		
			options.num = document.getElementById("update_num").value;
			options.aim = document.getElementById("update_aim").value;
			options.content = document.getElementById("update_content").value;
			options.implementation = document.getElementById("update_implementation").value;
			options.schedule = document.getElementById("update_schedule").value;
			options.request = document.getElementById("update_request").value;
			options.threeProjectid = threeProjectid;

			var url = '${pageContext.request.contextPath}/ThreeProject_updateThreeProject.action?syllabusId=${syllabusId}';
			console.log(options);
			$.post(url, options, function(data) {
				list.update(id, options);
				alert("更新成功");
			});
		}

		function onDelete(id,threeProjectid) {
			var dele={};
			dele.threeProjectid = threeProjectid;
			var url = '${pageContext.request.contextPath}/ThreeProject_deleteThreeProject.action';
			$.post(url, dele, function(data) {
				list.delete(id,dele);
				alert("删除成功");
			});
		}
		// 切换状态
		function changeStatus(id, status) {

			list.changeStatus(id, status);
		}
		// 清空表单
		function clearForm() {
			
			document.getElementById("add_num").value = "";
			document.getElementById("add_aim").value = "";
			document.getElementById("add_content").value = "";
			document.getElementById("add_implementation").value = "";
			document.getElementById("add_schedule").value = "";
			document.getElementById("add_request").value = "";

		}
	</script>

</html>