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
	src="<%=basePath%>mages/js/bootstrap.min.js"></script>
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
		<form method="post" name="bjllk"
			action="theoreticalPlan_llkUpdateResult.action?theoreticalLessonid=${theoreticalLesson.theoreticalLessonid }&department.departmentid=${user.department.departmentid }&tnum=${user.tnum }">
			<table style="margin-left: 120px;">
				<tr>
					<div class="title_right">
						<strong>编辑理论课</strong>
					</div>
				</tr>
				<tr>
					<td class="item-name">课程代码：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.curriculumid"
						value="${theoreticalLesson.curriculum.curriculumid }"></input></td>
					<td class="item-name">课程名称：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.curriculumCname"
						value=${theoreticalLesson.curriculum.curriculumCname }></input></td>
				</tr>
				<tr>
					<td class="item-name">开课部门：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.college.collegeid"
						value=${theoreticalLesson.curriculum.college.collegeCname }></input>
					</td>
					<td class="item-name">课程平台：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.curriculumpingtai"
						value="${theoreticalLesson.curriculum.curriculumpingtai}"></input>
					</td>
				</tr>
				<tr>
					<td class="item-name">课程性质：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.natureOfCourse.natureOfCoursename"
						value="${theoreticalLesson.curriculum.natureOfCourse.natureOfCoursename }"></input>
					</td>
					<td class="item-name">课程类别：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.courseLei"
						value="${theoreticalLesson.curriculum.courseLei }"></input></td>
				</tr>
				<tr>
					<td class="item-name">课程归属：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.courseCategory"
						value="${theoreticalLesson.curriculum.courseCategory }"></input></td>
					<td class="item-name">学分：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.credit"
						value="${theoreticalLesson.curriculum.credit }"></input></td>
				</tr>
				<tr>
					<td class="item-name">总学时：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.hoursOfALL"
						value="${theoreticalLesson.curriculum.hoursOfALL }"></input></td>
					<td class="item-name">讲课：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.hoursOfClass"
						value="${theoreticalLesson.curriculum.hoursOfClass }"></input></td>
				</tr>
				<tr>
					<td class="item-name">上机：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.hoursOfCom"
						value="${theoreticalLesson.curriculum.hoursOfCom }"></input></td>
					<td class="item-name">实验：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.hoursOfExp"
						value="${theoreticalLesson.curriculum.hoursOfExp }"></input></td>
				</tr>
				<tr>
					<td class="item-name">其他实践：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.hoursOfPractice"
						value="${theoreticalLesson.curriculum.hoursOfPractice }"></input>
					</td>
					<td class="item-name">周学时：</td>
					<td><input type="text" readonly="readonly"
						name="curriculum.hoursOfWeek"
						value="${theoreticalLesson.curriculum.hoursOfWeek }"></input></td>
				</tr>
				<tr>
					<td class="item-name">专业方向：</td>
					<td><input type="text" readonly="readonly"
						name="professional.professionalname"
						value="${theoreticalLesson.professional.professionalname }"></input>
					</td>
					<td><input type="hidden" readonly="readonly"
						name="professional.professionalid"
						value="${theoreticalLesson.professional.professionalid }"></input>
					</td>
				</tr>
				<tr>
					<td class="item-name">课外实践学时：</td>
					<td><input type="text" name="hoursOfOutExp"
						value="${theoreticalLesson.hoursOfOutExp }"></input></td>
					<td class="item-name">课外上机学时：</td>
					<td><input type="text" name="hoursOfOutCom"
						value="${theoreticalLesson.hoursOfOutCom }"></input></td>
				</tr>
				<tr>
					<td class="item-name">开课学期：</td>
					<td><select size="1" name="xueqi">
							<option value="1"
								<c:if test="${theoreticalLesson.xueqi eq '1' }">selected="selected"</c:if>>1</option>
							<option value="2"
								<c:if test="${theoreticalLesson.xueqi eq '2' }">selected="selected"</c:if>>2</option>
							<option value="3"
								<c:if test="${theoreticalLesson.xueqi eq '3' }">selected="selected"</c:if>>3</option>
							<option value="4"
								<c:if test="${theoreticalLesson.xueqi eq '4' }">selected="selected"</c:if>>4</option>
							<option value="5"
								<c:if test="${theoreticalLesson.xueqi eq '5' }">selected="selected"</c:if>>5</option>
							<option value="6"
								<c:if test="${theoreticalLesson.xueqi eq '6' }">selected="selected"</c:if>>6</option>
							<option value="7"
								<c:if test="${theoreticalLesson.xueqi eq '7' }">selected="selected"</c:if>>7</option>
							<option value="8"
								<c:if test="${theoreticalLesson.xueqi eq '8' }">selected="selected"</c:if>>8</option>
							<option value="9"
								<c:if test="${theoreticalLesson.xueqi eq '9' }">selected="selected"</c:if>>9</option>
							<option value="10"
								<c:if test="${theoreticalLesson.xueqi eq '10' }">selected="selected"</c:if>>10</option>
					</select></td>
					<td class="item-name">是否学位课：</td>
					<td><select size="1" name="isxueweike">
							<option value="是"
								<c:if test="${theoreticalLesson.isxueweike eq '是' }">selected="selected"</c:if>>是</option>
							<option value="否"
								<c:if test="${theoreticalLesson.isxueweike eq '否' }">selected="selected"</c:if>>否</option>
					</select></td>
				</tr>
				<tr>
					<td class="item-name">是否教改：</td>
					<td><select size="1" name="isjiaogai">
							<option value="是"
								<c:if test="${theoreticalLesson.isjiaogai eq '是' }">selected="selected"</c:if>>是</option>
							<option value="否"
								<c:if test="${theoreticalLesson.isjiaogai eq '否' }">selected="selected"</c:if>>否</option>
					</select></td>

					<td class="item-name">是否必选：</td>
					<td><c:if
							test="${theoreticalLesson.curriculum.natureOfCourse.natureOfCoursename ne '专业选修课' }">
							<select size="1" name="isbixuan" disabled>
								<option value=""></option>
							</select>
						</c:if> <c:if
							test="${theoreticalLesson.curriculum.natureOfCourse.natureOfCoursename eq '专业选修课' }">
							<select size="1" name="isbixuan">
								<option value="是"
									<c:if test="${theoreticalLesson.isbixuan eq '是' }">selected="selected"</c:if>>是</option>
								<option value="否"
									<c:if test="${theoreticalLesson.isbixuan eq '否' }">selected="selected"</c:if>>否</option>
							</select>
						</c:if></td>

				</tr>
				<tr>
					<td class="item-name">备注：</td>
					<td colspan="3"><textarea style="width: 420px; height: 90px;"
							name="remark">${theoreticalLesson.remark }</textarea></td>
				</tr>
			</table>
			<div class="bottom">
				<button type="submit" value="确认修改">确认修改</button>
			</div>
		</form>
	</div>
</body>
</html>