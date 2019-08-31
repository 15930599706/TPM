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

.new {
	margin-left: 74px;
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

.link>a {
	display: block;
	height: 20px;
	width: 160px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	font-size: 13px;
	background: url(<%=basePath%>images/img/Edit-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	text-align: left;
	float: left;
}

.link>a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	width: 160px;
	height: 20px;
}

.link {
	padding-top: 5px;
	height: 20px;
	;
}

#container {
	padding-top: 50px;
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
			<strong>课程内容</strong>


		</div>
		<div id="box9">
			<c:if test="${newtheoreticalLesson.curriculum.courseLei eq '理论课'}">
				<a
					href="${pageContext.request.contextPath}/theoreticalPlan_toupdateTheoLesPage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid}">返回录入其他内容</a>

				<div class="link">

					<a
						href="${pageContext.request.contextPath}/ExperimentContent_toEditTestPage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid}&curriculumId=${newtheoreticalLesson.curriculum.curriculumid }">编辑实验内容</a>
					<a
						href="${pageContext.request.contextPath}/DiscussContent_toEditDiscussPage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid}&curriculumId=${newtheoreticalLesson.curriculum.curriculumid }">编辑讨论课内容</a>
					<a
						href="${pageContext.request.contextPath}/ThreeProject_toEditThreePage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid}&curriculumId=${newtheoreticalLesson.curriculum.curriculumid }">编辑三级项目内容</a>
					<a
						href="${pageContext.request.contextPath}/OtherContent_toEditOtherPage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid}&curriculumId=${newtheoreticalLesson.curriculum.curriculumid }">编辑课程其他内容</a>
				</div>
			</c:if>
			<c:if test="${newtheoreticalLesson.curriculum.courseLei eq '实验课'}">
				<a
					href="${pageContext.request.contextPath}/theoreticalPlan_toupdateTheoLesPage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid}">返回录入其他内容</a>

				<div class="link">

					<a
						href="${pageContext.request.contextPath}/ExperimentContent_toEditTestPage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid}&curriculumId=${newtheoreticalLesson.curriculum.curriculumid }">编辑实验内容</a>
					<a
						href="${pageContext.request.contextPath}/DiscussContent_toEditDiscussPage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid}&curriculumId=${newtheoreticalLesson.curriculum.curriculumid }">编辑讨论课内容</a>
					<a
						href="${pageContext.request.contextPath}/ThreeProject_toEditThreePage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid}&curriculumId=${newtheoreticalLesson.curriculum.curriculumid }">编辑三级项目内容</a>
					<a
						href="${pageContext.request.contextPath}/OtherContent_toEditOtherPage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid}&curriculumId=${newtheoreticalLesson.curriculum.curriculumid }">编辑课程其他内容</a>
				</div>
			</c:if>
			<c:if test="${newtheoreticalLesson.curriculum.courseLei eq '实践课'}">
				<a
					href="${pageContext.request.contextPath}/practicePlan_toHavePracLesOtherPage.action?syllabusId=${syllabusId}&practiceLessonid=${newtheoreticalLesson.practiceLessonid}">返回录入其他内容</a>

				<div class="link">

					<a
						href="${pageContext.request.contextPath}/ExperimentContent_toEditTestPage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.practiceLessonid}&curriculumId=${newtheoreticalLesson.curriculum.curriculumid }">编辑实验内容</a>
					<a
						href="${pageContext.request.contextPath}/DiscussContent_toEditDiscussPage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.practiceLessonid}&curriculumId=${newtheoreticalLesson.curriculum.curriculumid }">编辑讨论课内容</a>
					<a
						href="${pageContext.request.contextPath}/ThreeProject_toEditThreePage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.practiceLessonid}&curriculumId=${newtheoreticalLesson.curriculum.curriculumid }">编辑三级项目内容</a>
					<a
						href="${pageContext.request.contextPath}/OtherContent_toEditOtherPage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.practiceLessonid}&curriculumId=${newtheoreticalLesson.curriculum.curriculumid }">编辑课程其他内容</a>
				</div>
			</c:if>
		</div>





		<div id="container" style="margin-left: 24px;"></div>
		<div id="normal_template" style="display: none; margin-left: 124px;">
			<p class="title">
				<button onclick="changeStatus({{item._id}}, 'edit')">编辑</button>
				<button onclick="onDelete({{item._id}}, {{item.contentTheoid}})">删除</button>
				&nbsp;&nbsp;&nbsp;&nbsp; 第 <span>{{temp.__index+1}}章、</span> <span>{{item.name}}</span>
				(建议学时： <span>{{item.time}}</span>学时) (支撑教学目标： <span>{{item.num}}</span>)
			</p>

			<table class="table1">
				<tr>
					<td class="input" colspan="3" style="height: 110px;">教学目标:<br>
						<textarea style="height: 80px; width: 800px;" readonly="readonly">{{item.aim}}
                       </textarea>
					</td>

				</tr>
				<tr>
					<td class="input" style="height: 100px;">本章主要内容:<br> <textarea
							style="height: 80px; width: 800px;" readonly="readonly">{{item.content}}
                       </textarea>
					</td>

				</tr>
				<!-- 					<tr>
						<td class="input" style="height: 100px;">本章重点:<br>
							<textarea style="height:80px;width:800px;" readonly="readonly">{{item.emphasis}}
                       </textarea>
						</td>

					</tr>
					<tr>
						<td class="input" style="height: 100px;">本章难点:<br>
							<textarea style="height:80px;width:800px;" readonly="readonly">{{item.nodus}}
                       </textarea>
						</td>

					</tr> -->

			</table>
		</div>
		<div id="edit_template" style="display: none; margin-left: 124px;">
			<p class="title-new1">编辑课程内容单元</p>
			<div
				style="width: 800px; height: 40px; font-size: 15px; font-weight: bold;">
				<tr>
					<td>单元名称：</td>
					<td><input type="text" id="update_name" style="width: 250px;"
						value="{{item.name}}" /></td>

					<td>建议学时：</td>
					<td><input type="text" id="update_time" style="width: 100px;"
						value="{{item.time}}" /></td>

					<td>支撑教学目标：</td>
					<td><input type="text" id="update_num" style="width: 110px;"
						value="{{item.num}}" /></td>
				</tr>
			</div>
			<table class="table2">

				<tr>
					<td class="input" colspan="3" style="height: 110px;">教学目标:<br>
						<textarea id="update_aim" style="height: 80px; width: 800px;">{{item.aim}}</textarea>
					</td>

				</tr>
				<tr>
					<td class="input" style="height: 100px;">本章主要内容:<br> <textarea
							id="update_content" style="height: 80px; width: 800px;">{{item.content}}</textarea>
					</td>

				</tr>
				<!-- 					<tr>
						<td class="input" style="height: 100px;">本章重点:<br>
							<textarea id="update_emphasis" style="height:80px;width:800px;">{{item.emphasis}}</textarea>
						</td>

					</tr>
					<tr>
						<td class="input" style="height: 100px;">本章难点:<br>
							<textarea id="update_nodus" style="height:80px;width:800px;">{{item.nodus}}</textarea>
						</td>

					</tr> -->
			</table>
			<p class="title-new1">
				<button onclick="onUpdate({{item._id}}, {{item.contentTheoid}})">更新</button>
				<button onclick="changeStatus({{item._id}}, 'normal')">取消</button>
			</p>
		</div>
	</div>
	<div
		style="background-color: #ffecc5; width: 810px; margin-left: 95px;"
		class="new">
		<p class="title-new">新建课程内容单元 （注：单元章节序号自动生成无需填写，请按顺序填写章节内容）</p>
		<input id="add_index" type="hidden" value={{temp.__index+1}}></input>
		<div
			style="width: 800px; height: 40px; font-size: 15px; font-weight: bold;">
			<tr>
				<td>单元名称：</td>
				<td><input id="add_name" type="text" style="width: 250px;" />
				</td>

				<td>建议学时：</td>
				<td><input id="add_time" type="text" style="width: 100px;" />
				</td>

				<td>支撑教学目标：</td>
				<td><input id="add_num" type="text" style="width: 110px;" /></td>
			</tr>

		</div>
		<table class="table2">
			<tr>
				<td class="input" colspan="3" style="height: 110px;">教学目标:<br>
				<textarea id="add_aim" style="height: 80px; width: 800px;"></textarea></td>
			</tr>
			<tr>
				<td class="input" style="height: 100px;">本章主要内容:<br>
				<textarea id="add_content" style="height: 80px; width: 800px;"></textarea></td>
			</tr>
			<!-- 				<tr>
					<td class="input" style="height: 100px;">本章重点:<br><textarea id="add_emphasis" style="height:80px;width:800px;"></textarea></td>
				</tr>
				<tr>
					<td class="input" style="height: 100px;">本章难点:<br><textarea id="add_nodus" style="height:80px;width:800px;"></textarea></td>
				</tr> -->

		</table>

		<p class="title-new">
			<button onclick="onAdd()">保存</button>
			<button onclick="clearForm()">清除</button>
		</p>
	</div>


</body>
<p id="demo"></p>
<script type="text/javascript">	 
		// 创建实例
		var init_data=${contentTheo}; 
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
			item.time = document.getElementById("add_time").value;
			item.num = document.getElementById("add_num").value;
			item.aim = document.getElementById("add_aim").value;
 			item.content = document.getElementById("add_content").value;
 			
/* 			item.emphasis = document.getElementById("add_emphasis").value; 
			item.nodus = document.getElementById("add_nodus").value; */

			var url = '${pageContext.request.contextPath}/ContentTheo_addContenTheo.action?syllabusId=${syllabusId}';
			console.log(item);
			$.post(url,item,function(data) {
				item.contentTheoid = data.content;
				list.add(item);
				alert("添加成功");
				//clearForm()；
			});
		}

		function onUpdate(id, contentTheoid) {
			var options = {};
			options.name = document.getElementById("update_name").value;
			options.time = document.getElementById("update_time").value;
			options.num = document.getElementById("update_num").value;
			options.aim = document.getElementById("update_aim").value;
			options.content = document.getElementById("update_content").value;
/* 			options.emphasis = document.getElementById("update_emphasis").value;
			options.nodus = document.getElementById("update_nodus").value; */
			options.contentTheoid = contentTheoid;

			var url = '${pageContext.request.contextPath}/ContentTheo_updataContenTheo.action?syllabusId=${syllabusId}';
			console.log(options);
			$.post(url, options, function(data) {
				list.update(id, options);
				alert("更新成功");
			});
		}
		function onDelete(id, contentTheoid) {
			var dele = {};
			dele.contentTheoid = contentTheoid;
			var url = '${pageContext.request.contextPath}/ContentTheo_deleteContenTheo.action';
			console.log(dele);
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
			document.getElementById("add_name").value = "";
			document.getElementById("add_time").value = "";
			document.getElementById("add_num").value = "";
			document.getElementById("add_aim").value = "";
			document.getElementById("add_content").value = "";
/* 			document.getElementById("add_emphasis").value = "";
			document.getElementById("add_nodus").value = ""; */
		}
	</script>

</html>