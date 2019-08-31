<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath%>images/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath%>images/css/css.css" />
<script type="text/javascript"
	src="<%=basePath%>images/js/jquery1.9.0.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>images/js/sdmenu.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/laydate/laydate.js"></script>
<script type="text/javascript" src="<%=basePath%>images/js/vue.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/vue-resource.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/jQuery-1.11.3.min.js"></script>
<style type="text/css">
table {
	border: 2px solid #0088CC;
	margin-top: 20px;
}

td {
	border-top: 2px solid #0088CC;
}

th {
	border-top: 1px solid #0088CC;
	padding: 10px 0;
}

#bt {
	margin-left: 555px;
	margin-top: 10px;
}

#box3 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	width: 135px;
	height: 20px;
}

#box3 a {
	display: block;
	height: 20px;
	width: 135px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/disk-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	text-align: center;
}

#box3 {
	height: 20px;
	float: left;
	width: 135px;
}
</style>
</head>

<body>
	<div class="right_cont">
		<div id="app">
			<div class="title_right">
				<strong>编辑毕业要求及指标分解</strong>
			</div>
			<div id="box3">
				<a
					href="${pageContext.request.contextPath}/export_hahah.action?departmentid=${department.departmentid}">生成word文档</a>
			</div>
			<h4 style="text-align: center; margin-top: 50px;">毕业要求及指标分解</h4>

			<table border="0" cellspacing="0"
				style="width: 100%; text-align: center;">
				<tr>
					<th>毕业要求</th>
					<th>指标点数目</th>
					<th>指标点内容</th>
				</tr>
				<tr v-for="item in arr">
					<td style="width: 300px; text-align: left; padding-left: 25px;"><span
						style="font-weight: bold;">{{ item.title }}</span>&nbsp;&nbsp; {{
						item.abilitycontent }}</td>
					<td><span style="padding-left: 5px; padding-right: 10px;">{{
							item.point_arr.length }}</span></td>
					<td>
						<div v-for="(point, index) in item.point_arr">
							<textarea rows="1" v-model="item.point_arr[index]"
								style="width: 85%" disabled></textarea>
						</div>
					</td>
				</tr>
			</table>
			<div>{{ tip }}</div>
		</div>
</body>
<script type="text/javascript">
		var vm = new Vue({
			el: "#app",
			data: {
				arr: [
					${ability}

				],
				max_point: 10,
				tip: ""
			},
			methods: {
				addPoint: function(item) {
					if(item.point_arr.length >= this.max_point) return;
					item.point_arr.push("");
				},
				deletePoint: function(item, index) {
					var num = item.point_arr.length;
					if(num <= 1) return;
					var new_point_arr = [];
					for(var i = 0; i < num; i++) {
						if(i != index) new_point_arr.push(item.point_arr[i]);
					}
					item.point_arr = new_point_arr;
				},
				submitData: function() {
					var url = '${pageContext.request.contextPath}/analysis_addanalysis.action?departmentid=${department.departmentid }&data='+encodeURI(encodeURI(JSON.stringify(this.arr)));
					console.log(url);
					this.$http.post(url, { // 数据
						//departmentid: JSON.stringify(this.arr)
					}, { /* 配置参数 */ }).then(function(response) {
						// 如果成功执行
						alert("保存成功！");
					}, function(response) {
						// 如果失败执行
					});
				}
			}
		});
	</script>

</html>