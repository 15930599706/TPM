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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath%>images/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath%>images/css/css.css" />
<script type="text/javascript" src="<%=basePath%>images/js/vue.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/vue-resource.min.js"></script>

<style type="text/css">
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	padding: 0;
	font-size: 12px;
}

#box1 a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	cursor: pointer;
	width: 122px;
	height: 20px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 122px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/add-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box1 {
	float: left;
	width: 30%;
	padding: 10px;
}

#box1 p {
	color: red;
}

#box2 {
	padding: 10px;
	float: left;
	width: 70%;
}

#t1 {
	margin-top: 10px;
	margin-left: 100px;
}
</style>
</head>

<body>
	<div class="right_cont">
		<div id="container">
			<div class="title_right">
				<strong>教学目标与毕业要求的对应关系</strong>
			</div>
			<div id="box9">
				<c:if test="${newtheoreticalLesson.curriculum.courseLei eq '理论课'}">
					<a
						href="${pageContext.request.contextPath}/theoreticalPlan_toupdateTheoLesPage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid}">返回录入其他内容</a>

				</c:if>
				<c:if test="${newtheoreticalLesson.curriculum.courseLei eq '实验课'}">
					<a
						href="${pageContext.request.contextPath}/theoreticalPlan_toupdateTheoLesPage.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid}">返回录入其他内容</a>

				</c:if>
				<c:if test="${newtheoreticalLesson.curriculum.courseLei eq '实践课'}">
					<a
						href="${pageContext.request.contextPath}/practicePlan_toHavePracLesOtherPage.action?syllabusId=${syllabusId}&practiceLessonid=${newtheoreticalLesson.practiceLessonid}">返回录入其他内容</a>


				</c:if>
			</div>
			<div id="box1">
				<p>
					<textarea style="width: 100%;" rows="3" v-model="tempGoalContent"></textarea>
					<button id="t1" v-on:click="addGoal()">添加教学目标</button>
				</p>
				<p v-for="(item, index) in teachingGoals">
					<button v-on:click="removeGoal(index)">删除</button>
					<span>{{ index+1 }}、{{ item.teachObjContent_InnerExperiment
						}}</span>
				</p>
			</div>

			<div id="box2">
				<h4 style="text-align: center">教学目标与毕业要求的对应关系</h4>
				<p>
				<table border="1" cellpadding="4" width="100%">
					<tr>
						<th>毕业要求</th>
						<th>指标点</th>
						<th>课程教学目标</th>
					</tr>
					<tr v-for="(item, index) in relations">
						<td>{{ item.description }}</td>
						<td>
							<p v-for="(point, point_index) in item.points">{{ point }}</p>
						</td>
						<td style="width: 100px; text-align: left;">
							<!-- <p>
									<input type="checkbox" v-on:change="swapCheck(item.goals, $event)"/><span>全选</span>
								</p> -->
							<p v-for="(goal, goal_index) in teachingGoals">
								<input type="checkbox"
									v-model="item.goals[goal.teachObj_InnerExperimentid]" /><span>教学目标{{
									goal_index + 1 }}</span>
							</p>
						</td>
					</tr>

				</table>
				</p>
				<p>
					<button v-on:click="save()"
						style="margin-left: 350px; margin-top: 20px;">提交</button>
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript">
			var g_common_config = {
				emulateJSON: true,
				headers: {
					"Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
				}
			};
			var vm = new Vue({
				el: "#container",
				data: {
					tempGoalContent: "",
					teachingGoals: ${TeachObjJson},
					relations: [
						${json}
					],
			
				},
				
				computed: {
					c_result: function() {
						var result = [];
						this.relations.forEach(function(elem) {
							var goals = [];
							for(var key in elem.goals) {
								if(elem.goals[key]) goals.push(key);
							}
							result.push({
								id: elem.id,
								goals: goals
							});
						});
						return result;
					}
				},
				methods: {
					addGoal: function() {
					    var that = this;
					    if(that.tempGoalContent.trim()==""){
					       alert("请输入教学目标内容！");
					       return;
					    }					
						var url = "${pageContext.request.contextPath}/teachObj_addTeachObjInnerExperiment.action?syllabusId=${syllabusId}";
						that.$http.post(url, {
							data: that.tempGoalContent
						}, g_common_config).then(function(res) {
							if(res.body.error) {
								alert("添加教学目标失败！");
							}else {
								that.teachingGoals.push({
									teachObj_InnerExperimentid: parseInt(res.body.message),
									teachObjContent_InnerExperiment: that.tempGoalContent
								});
								alert("添加教学目标成功！");
								that.tempGoalContent = "";
								this.refreshRelations();
							}
						});
					},
					removeGoal: function(index) {
						var url = "${pageContext.request.contextPath}/teachObj_delTeachObjInnerExperiment.action?";
						var that = this;
						this.$http.post(url, {
							data: that.teachingGoals[index].teachObj_InnerExperimentid
						}, g_common_config).then(function(res) {
							if(res.error) alert("删除教学目标失败！");
							else {
								alert("删除教学目标成功！");
								this.teachingGoals.splice(index, 1);
								this.refreshRelations();
							}
						});
					},
					refreshRelations: function() {
						var that = this;
						that.relations.forEach(function(relation) {
							var goals = {};
							that.teachingGoals.forEach(function(goal) {
								goals[goal.teachObj_InnerExperimentid] = relation.goals[goal.teachObj_InnerExperimentid] == true;
							});
							relation.goals = goals;
						})
					},
					swapCheck: function(controller, event) {
						var checkedAll = event.currentTarget.checked;
						for(var key in controller) {
							controller[key] = checkedAll;
						}
					},
					save: function() {
					var emptyEle = [];
						this.relations.forEach(function(item) {
							var b = false;
							for(var k in item.goals) {
								if(item.goals[k]) {
									b = true;
									break;
								}
							}
							if(!b) {
								emptyEle.push({
									title: item.description
								});
							}
						});
						if(emptyEle.length) {
							var alertStr = "";
							emptyEle.forEach(function(item) {
								alertStr += item.title + "--没有选择教学目标\n";
							});
							alert(alertStr);
							return;
						}
						var url = "${pageContext.request.contextPath}/abilityAndTeachObj_addAbilityAndTeachObjInnerExperiment.action?syllabusId=${syllabusId}";
						this.$http.post(url, {
							data: JSON.stringify(this.c_result)
						}, g_common_config).then(function(res) {
							alert("保存成功，请填写其他内容！");
							window.location.href = "${pageContext.request.contextPath}/practicePlan_toHavePracLesInnerExperimentPage.action?tnum=${user.tnum}&syllabusId=${syllabusId}&practiceLessonid=${newpracticeLesson.practiceLessonid }&curriculum.curriculumid=${newpracticeLesson.curriculum.curriculumid }&department.departmentid=${newpracticeLesson.department.departmentid}";
						});
					}
				},
				created: function() {
					this.refreshRelations();
				}
			});
		</script>

</body>

</html>