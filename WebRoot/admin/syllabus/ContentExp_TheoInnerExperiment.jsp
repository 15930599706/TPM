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
	margin-top: 20px;
}

p.title1 {
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

p.title-new2 {
	font-size: 16px;
	font-weight: bold;
	color: white;
	padding: 15px 20px 15px 10px;
	background-color: #447539;
	width: 780px;
	margin-top: 20px;
}

.new {
	margin-left: 70px;
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
	color: #000;
	background-color: #D2E7F5;
	border: 1px solid #B1CCEB;
	width: 160px;
	height: 30px;
}

#container {
	padding-top: 17px;
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
			<strong>实验项目</strong>
		</div>
		<div id="box9">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_toupdateTheoLesPageInnerExperiment.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid}">返回录入其他内容</a>
		</div>
		<div id="container" style="margin-top: 45px; margin-left: 24px;"></div>



		<div id="edit_template" style="display: none;">
			<p class="title-new2">编辑实验项目内容单元</p>
			<div
				style="width: 800px; height: 40px; font-size: 15px; font-weight: bold;">
				<tr>
					<td>单元名称：</td>
					<td><input type="text" id="update_name{{item._id}}"
						style="width: 250px;" readonly="readonly" value="{{item.name}}" />
					</td>

					<td>建议学时：</td>
					<td><input type="text" id="update_time{{item._id}}"
						style="width: 100px;" readonly="readonly" value="{{item.time}}" />
					</td>

					<td>支撑教学目标：</td>
					<td><input type="text" id="update_num{{item._id}}"
						style="width: 110px;" value="{{item.num}}" /></td>
				</tr>
			</div>

			<table class="table1">

				<tr>
					<td class="input" colspan="3" style="height: 110px;">实验设备及材料:<br>
						<textarea id="update_equipment{{item._id}}"
							style="height: 80px; width: 800px;">{{item.equipment}}</textarea>
					</td>
				</tr>
				<tr>
					<td class="input" style="height: 100px;">实验原理:<br> <textarea
							id="update_theory{{item._id}}"
							style="height: 80px; width: 800px;">{{item.theory}}</textarea>
					</td>
				</tr>
				<tr>
					<td class="input" style="height: 100px;">教学要求:<br> <textarea
							id="update_request{{item._id}}"
							style="height: 80px; width: 800px;">{{item.request}}</textarea>
					</td>
				</tr>
			</table>
			<p class="title-new1">
				<button
					onclick="onUpdate({{item._id}}, {{item.expermentProject_Theoid}})">更新</button>
				<button onclick="changeStatus({{item._id}}, 'normal')">取消</button>
			</p>
		</div>

		<div id="normal_template" style="display: none;">
			<p class="title">
				实验项目 <span>{{temp.__index+1}}章、</span> <span>{{item.name}}</span>
				(建议学时： <span>{{item.time}}</span>学时) (支撑教学目标： <span>{{item.num}}</span>)
			</p>
			<table class="table2">

				<tr>
					<td class="input" colspan="3" style="height: 110px;">实验设备及材料:<br>
						<textarea id="update_equipment" readonly="readonly"
							style="height: 80px; width: 800px;">{{item.equipment}}</textarea></td>
					</td>

				</tr>
				<tr>
					<td class="input" style="height: 100px;">实验原理:<br> <textarea
							id="update_theory" readonly="readonly"
							style="height: 80px; width: 800px;">{{item.theory}}</textarea></td>
					</td>

				</tr>
				<tr>
					<td class="input" style="height: 100px;">教学要求:<br> <textarea
							id="update_request" readonly="readonly"
							style="height: 80px; width: 800px;">{{item.request}}</textarea></td>
					</td>

				</tr>
			</table>
			<p class="title">
				<button onclick="changeStatus({{item._id}}, 'edit')">编辑</button>
			</p>
		</div>
	</div>
	<div
		style="background-color: #ffecc5; width: 810px; margin-left: 24px; display: none;"
		class="new">
		<p class="title-new">新建实验项目内容单元 （注：单元章节序号自动生成无需填写，请按顺序填写章节内容）</p>
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
				<td class="input" colspan="3" style="height: 110px;">实验设备及材料:<br>
				<textarea id="add_equipment" style="height: 80px; width: 800px;"></textarea></td>
			</tr>
			<tr>
				<td class="input" style="height: 100px;">实验原理:<br>
				<textarea id="add_theory" style="height: 80px; width: 800px;"></textarea></td>
			</tr>
			<tr>
				<td class="input" style="height: 100px;">教学要求:<br>
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
		var init_data=${textBooks};
		var list = new List(container, init_data);
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
			item.time = document.getElementById("add_time").value;
			item.num = document.getElementById("add_num").value;
			item.equipment = document.getElementById("add_equipment").value;
			item.theory = document.getElementById("add_theory").value;
			item.request = document.getElementById("add_request").value;

			var url = '${pageContext.request.contextPath}/ExpermentProjectInnerExperiment_addTheoExpermentProject.action?syllabusId=${syllabusId}';
			console.log(item);
			$.post(url,item,function(data) {
				item.expermentProject_Theoid = data.content;
				list.add(item);
				alert("添加成功");
			});
		}

		function onUpdate(id,expermentProject_Theoid) {
			var options = {};
			options.name = document.getElementById("update_name"+id).value;
			options.time = document.getElementById("update_time"+id).value;
			options.num = document.getElementById("update_num"+id).value;
			options.equipment = document.getElementById("update_equipment"+id).value;
			options.theory = document.getElementById("update_theory"+id).value;
			options.request = document.getElementById("update_request"+id).value;
			options.expermentProject_Theoid = expermentProject_Theoid;

			var url = '${pageContext.request.contextPath}/ExpermentProjectInnerExperiment_updateTheoExpermentProject.action?syllabusId=${syllabusId}';
			console.log(options);
			$.post(url, options, function(data) {
				list.update(id, options);
				alert("更新成功");
			//	changeStatus(id, "edit");
			});
		}

		function onDelete(id,expermentProject_Theoid) {
			var dele = {};
			dele.expermentProject_Theoid = expermentProject_Theoid;
			var url = '${pageContext.request.contextPath}/ExpermentProjectInnerExperiment_deleteTheoExpermentProject.action';
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
			document.getElementById("add_equipment").value = "";
			document.getElementById("add_theory").value = "";
			document.getElementById("add_request").value = "";
	

		}
	</script>

</html>