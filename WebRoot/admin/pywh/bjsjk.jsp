<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
	<div class="right_cont">
		<form method="post" name="bjsjk"
			action="practicePlan_sjkUpdateResult.action?practiceLessonid=${practiceLesson.practiceLessonid }&department.departmentid=${user.department.departmentid }&tnum=${user.tnum}">
			<table style="margin-left: 120px;">
				<tr>

					<div class="title_right">
						<strong>编辑集中实践课</strong>
					</div>
				</tr>
				<tr>
					<td class="item-name">课程代码：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.curriculumid"
						value="${practiceLesson.curriculum.curriculumid }"></input></td>
					<td class="item-name">课程名称：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.curriculumCname"
						value=${practiceLesson.curriculum.curriculumCname }></input></td>
				</tr>
				<tr>
					<td class="item-name">开课部门：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.college.collegeid"
						value=${practiceLesson.curriculum.college.collegeCname }></input>
					</td>
					<td class="item-name">课程平台：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.curriculumpingtai"
						value="${practiceLesson.curriculum.curriculumpingtai}"></input></td>
				</tr>
				<tr>
					<td class="item-name">课程性质：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.natureOfCourse.natureOfCoursename"
						value="${practiceLesson.curriculum.natureOfCourse.natureOfCoursename }"></input>
					</td>
					<td class="item-name">课程类别：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.courseLei"
						value="${practiceLesson.curriculum.courseLei }"></input></td>
				</tr>
				<tr>
					<td class="item-name">课程归属：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.courseCategory"
						value="${practiceLesson.curriculum.courseCategory }"></input></td>
					<td class="item-name">学分：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.credit"
						value="${practiceLesson.curriculum.credit }"></input></td>
				</tr>
				<tr>
					<td class="item-name">专业方向：</td>
					<td><input type="text" readonly="readonly"
						name="professional.professionalname"
						value="${practiceLesson.professional.professionalname }"></input>
					</td>
					<td><input type="hidden" readonly="readonly"
						name="professional.professionalid"
						value="${practiceLesson.professional.professionalid }"></input></td>
				</tr>
				<tr>
					<td class="item-name">课外实践学时：</td>
					<td><input type="text" name="hoursOfOutExp"
						value="${practiceLesson.hoursOfOutExp }"></input></td>
					<td class="item-name">课外上机学时：</td>
					<td><input type="text" name="hoursOfOutCom"
						value="${practiceLesson.hoursOfOutCom }"></input></td>
				</tr>
				<tr>
					<td class="item-name">开课学期：</td>
					<td><select size="1" name="xueqi">
							<option value="1"
								<c:if test="${practiceLesson.xueqi eq '1' }">selected="selected"</c:if>>1</option>
							<option value="2"
								<c:if test="${practiceLesson.xueqi eq '2' }">selected="selected"</c:if>>2</option>
							<option value="3"
								<c:if test="${practiceLesson.xueqi eq '3' }">selected="selected"</c:if>>3</option>
							<option value="4"
								<c:if test="${practiceLesson.xueqi eq '4' }">selected="selected"</c:if>>4</option>
							<option value="5"
								<c:if test="${practiceLesson.xueqi eq '5' }">selected="selected"</c:if>>5</option>
							<option value="6"
								<c:if test="${practiceLesson.xueqi eq '6' }">selected="selected"</c:if>>6</option>
							<option value="7"
								<c:if test="${practiceLesson.xueqi eq '7' }">selected="selected"</c:if>>7</option>
							<option value="8"
								<c:if test="${practiceLesson.xueqi eq '8' }">selected="selected"</c:if>>8</option>
							<option value="7、8"
								<c:if test="${practiceLesson.xueqi eq '7、8' }">selected="selected"</c:if>>7、8</option>
							<option value="9"
								<c:if test="${practiceLesson.xueqi eq '9' }">selected="selected"</c:if>>9</option>
							<option value="10"
								<c:if test="${practiceLesson.xueqi eq '10' }">selected="selected"</c:if>>10</option>
							<option value="9、10"
								<c:if test="${practiceLesson.xueqi eq '9、10' }">selected="selected"</c:if>>9、10</option>
					</select></td>
					<td class="item-name">起止周：</td>
					<td nowrap="nowrap"><input type="text" style="width: 60px;"
						name="qizhizhou" value="${practiceLesson.qizhizhou}" /></td>
				</tr>

				<tr>
					<td class="item-name">组织形式：</td>
					<td><select size="1" name="zuzhixingshi">
							<option value="实习"
								<c:if test="${practiceLesson.zuzhixingshi eq '实习' }">selected="selected"</c:if>>实习</option>
							<option value="课程设计（学年论文）"
								<c:if test="${practiceLesson.zuzhixingshi eq '课程设计（学年论文）' }">selected="selected"</c:if>>课程设计（学年论文）</option>
							<option value="实验"
								<c:if test="${practiceLesson.zuzhixingshi eq '实验' }">selected="selected"</c:if>>实验</option>
							<option value="毕业设计"
								<c:if test="${practiceLesson.zuzhixingshi eq '毕业设计' }">selected="selected"</c:if>>毕业设计</option>
							<option value="其他"
								<c:if test="${practiceLesson.zuzhixingshi eq '其他' }">selected="selected"</c:if>>其他</option>
					</select></td>
				</tr>

				<tr>
					<td class="item-name">是否学位课：</td>
					<td><select size="1" name="isxueweike">
							<option value="是"
								<c:if test="${practiceLesson.isxueweike eq '是' }">selected="selected"</c:if>>是</option>
							<option value="否"
								<c:if test="${practiceLesson.isxueweike eq '否' }">selected="selected"</c:if>>否</option>
					</select></td>
					<td class="item-name">是否集中实践：</td>
					<td><select size="1" name="idallpracticeLesson">
							<option value="是"
								<c:if test="${practiceLesson.idallpracticeLesson eq '是' }">selected="selected"</c:if>>是</option>
							<option value="否"
								<c:if test="${practiceLesson.idallpracticeLesson eq '否' }">selected="selected"</c:if>>否</option>
					</select></td>
				</tr>
				<tr>
					<td class="item-name">备注：</td>
					<td colspan="3"><textarea style="width: 420px; height: 90px;"
							name="beizhu">${practiceLesson.beizhu }</textarea></td>

				</tr>
			</table>
			<div class="bottom">
				<button type="submit" value="确认修改">确认修改</button>
			</div>


		</form>
	</div>
</body>

</html>