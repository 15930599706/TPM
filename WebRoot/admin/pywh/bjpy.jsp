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
<style type="text/css">
.bottom {
	padding-top: 10px;
	padding-left: 222px;
}

.bj {
	padding-left: 50px;
}
</style>

</head>
<script language="javascript">
function check()
{
//pyxx.trainingObjectives.value = pyxx.trainingObjectives.value.trim();
//alert(pyxx.trainingObjectives.value.trim());
return true;
}
</script>
<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>编辑培养计划基本信息</strong>
		</div>
		<form name="pyxx"
			action="${pageContext.request.contextPath}/ptBasicInformation_updateptBasicInformation.action"
			method="post" onsubmit="return check()">
			<div class="bj">
				<div>
					<tr>
						<td><font size="2px" color="#000000">专业名称：</td>
						<td><font size="2px" color="red">${department.departmentCname }</font></td>
					</tr>
				</div>
				<c:if test="${user.adminlevel == 1 || tag == 'revise'}">
					<div>
						<h5>
							一、学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;制： <select size="1"
								name="learningTime">
								<option value="四年"
									<c:if test="${ptBasicInformation.learningTime eq '四年'}">selected="selected"</c:if>>四年</option>
								<option value="五年"
									<c:if test="${ptBasicInformation.learningTime eq '五年'}">selected="selected"</c:if>>五年</option>

							</select>
					</div>
					<div>
						<h5>
							二、起用年级： <select size="1" name="enableGrade">
								<option value="2017"
									<c:if test="${ptBasicInformation.enableGrade eq '2017'}">selected="selected"</c:if>>2017</option>
								<%-- <option value="2018" <c:if test="${ptBasicInformation.enableGrade eq '2018'}">selected="selected"</c:if>>2018</option>
					<option value="2019" <c:if test="${ptBasicInformation.enableGrade eq '2019'}">selected="selected"</c:if>>2019</option>
					<option value="2020" <c:if test="${ptBasicInformation.enableGrade eq '2020'}">selected="selected"</c:if>>2020</option>
					<option value="2021" <c:if test="${ptBasicInformation.enableGrade eq '2021'}">selected="selected"</c:if>>2021</option> --%>

							</select>
					</div>

					<div>
						<tr>
							<h5>
								三、培养目标:<span style="color: red;">(1000字以内)</span>
							</h5>
							<td><textarea name="trainingObjectives" maxlength="1000"
									style="width: 555px; height: 99px;" required>${ptBasicInformation.trainingObjectives }</textarea></td>
						</tr>
					</div>
					<div>
						<tr>
							<h5>
								四、培养要求:<span style="color: red;">(1000字以内)</span>
							</h5>
							<td><textarea name="trainingRequirements" maxlength="1000"
									style="width: 555px; height: 99px" required>${ptBasicInformation.trainingRequirements }</textarea></td>
						</tr>
					</div>
					<div>
						<h5>
							五、毕业生应获得知识和能力的个数: <select size="1" name="abilityCount">
								<option value="6"
									<c:if test="${ptBasicInformation.abilityCount == 6}">selected="selected"</c:if>>6</option>
								<option value="7"
									<c:if test="${ptBasicInformation.abilityCount == 7}">selected="selected"</c:if>>7</option>
								<option value="8"
									<c:if test="${ptBasicInformation.abilityCount == 8}">selected="selected"</c:if>>8</option>
								<option value="9"
									<c:if test="${ptBasicInformation.abilityCount == 9}">selected="selected"</c:if>>9</option>
								<option value="10"
									<c:if test="${ptBasicInformation.abilityCount == 10}">selected="selected"</c:if>>10</option>
								<option value="11"
									<c:if test="${ptBasicInformation.abilityCount == 11}">selected="selected"</c:if>>11</option>
								<option value="12"
									<c:if test="${ptBasicInformation.abilityCount == 12 || empty ptBasicInformation.abilityCount || ptBasicInformation.abilityCount == 0}">selected="selected"</c:if>>12</option>
								<option value="13"
									<c:if test="${ptBasicInformation.abilityCount == 13}">selected="selected"</c:if>>13</option>
								<option value="14"
									<c:if test="${ptBasicInformation.abilityCount == 14}">selected="selected"</c:if>>14</option>
								<option value="15"
									<c:if test="${ptBasicInformation.abilityCount == 15}">selected="selected"</c:if>>15</option>
							</select>
					</div>
					<div>
						<tr>
							<h5>六、主干学科:</h5>
							<td><textarea name="mainCourses" maxlength="255"
									style="width: 555px; height: 99px" required>${ptBasicInformation.mainCourses }</textarea></td>
						</tr>
					</div>
					<div>
						<tr>
							<h5>
								七、学位课:<span style="color: red;">(400字以内)</span>
							</h5>
							<td><textarea name="degreeCourses" maxlength="450"
									style="width: 555px; height: 99px" required>${ptBasicInformation.degreeCourses }</textarea></td>
						</tr>
					</div>
					<div>
						<h5>
							八、授予学位： <select size="1" name="degree">
								<option value="哲学学士"
									<c:if test="${ptBasicInformation.degree eq '哲学学士'}">selected="selected"</c:if>>哲学学士</option>
								<option value="经济学学士"
									<c:if test="${ptBasicInformation.degree eq '经济学学士'}">selected="selected"</c:if>>经济学学士</option>
								<option value="法学学士"
									<c:if test="${ptBasicInformation.degree eq '法学学士'}">selected="selected"</c:if>>法学学士</option>
								<option value="教育学学士"
									<c:if test="${ptBasicInformation.degree eq '教育学学士'}">selected="selected"</c:if>>教育学学士</option>
								<option value="文学学士"
									<c:if test="${ptBasicInformation.degree eq '文学学士'}">selected="selected"</c:if>>文学学士</option>
								<option value="历史学学士"
									<c:if test="${ptBasicInformation.degree eq '历史学学士'}">selected="selected"</c:if>>历史学学士</option>
								<option value="理学学士"
									<c:if test="${ptBasicInformation.degree eq '理学学士'}">selected="selected"</c:if>>理学学士</option>
								<option value="工学学士"
									<c:if test="${ptBasicInformation.degree eq '工学学士'}">selected="selected"</c:if>>工学学士</option>
								<option value="农学学士"
									<c:if test="${ptBasicInformation.degree eq '农学学士'}">selected="selected"</c:if>>农学学士</option>
								<option value="医学学士"
									<c:if test="${ptBasicInformation.degree eq '医学学士'}">selected="selected"</c:if>>医学学士</option>
								<option value="管理学学士"
									<c:if test="${ptBasicInformation.degree eq '管理学学士'}">selected="selected"</c:if>>管理学学士</option>
								<option value="艺术学学士"
									<c:if test="${ptBasicInformation.degree eq '艺术学学士'}">selected="selected"</c:if>>艺术学学士</option>
								<option value="军事学学士"
									<c:if test="${ptBasicInformation.degree eq '军事学学士'}">selected="selected"</c:if>>军事学学士</option>
							</select>
					</div>
				</c:if>
				<c:if test="${user.adminlevel != 1 && tag != 'revise'}">
					<c:if test="${not empty ptBasicInformation.degree}">

						<div>
							<h5>
								一、学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;制：${ptBasicInformation.learningTime }

							
						</div>
						<div>
							<h5>二、起用年级：${ptBasicInformation.enableGrade}
						</div>

						<div>
							<tr>
								<h5>三、培养目标:</h5>
								<td><p>${ptBasicInformation.trainingObjectives }</p></td>
							</tr>
						</div>
						<div>
							<tr>
								<h5>四、培养要求:</h5>
								<td><p>${ptBasicInformation.trainingRequirements }</p></td>
							</tr>
						</div>
						<div>
							<h5>五、毕业生应获得知识和能力的个数:${ptBasicInformation.abilityCount }
						</div>
						<c:forEach items="${abilitylist }" var="ability" varStatus="xh">
							<tr>
								<td>${xh.count }、${ability.abilityname }</td>
							</tr>
							<tr>
								<td>
									<p>${ability.abilitycontent }</p>
								</td>
							</tr>
						</c:forEach>
						<div>
							<tr>
								<h5>六、主干学科:</h5>
								<td><p>${ptBasicInformation.mainCourses }</p></td>
							</tr>
						</div>
						<div>
							<tr>
								<h5>七、学位课:</h5>
								<td><p>${ptBasicInformation.degreeCourses }</p></td>
							</tr>
						</div>
						<div>
							<h5>八、授予学位：${ptBasicInformation.degree}
						</div>

					</c:if>
					<c:if test="${empty ptBasicInformation.degree}">
						<font size="2px" color="red">${department.departmentCname }还未填写培养计划基本信息！</font>
					</c:if>
				</c:if>

				<input type="hidden" name="department.departmentid"
					value="${department.departmentid}">
				<c:if
					test="${ptBasicInformation.ptBasicInformationid ne '' && not empty ptBasicInformation.ptBasicInformationid}">
					<input type="hidden" name="ptBasicInformationid"
						value="${ptBasicInformation.ptBasicInformationid }">
				</c:if>
				<c:if test="${user.adminlevel == 1 || tag == 'revise'}">
					<div class="bottom">
						<button type="submit" value="下一步">下一步</button>
					</div>
				</c:if>
			</div>
		</form>
</body>