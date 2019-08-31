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
#bt {
	margin-left: 555px;
	margin-top: 10px;
}

#bto {
	margin-left: 122px;
	margin-top: 5px;
}

body {
	margin: 0;
	padding: 0;
	font-size: 12px;
}

dt {
	padding: 10px;
}

i {
	font-style: normal;
	color: #000000;
}

#detail {
	position: absolute;
	width: 350px;
	height: 120px;
	border: 1px solid #ccc;
	background: #efefef;
	display: none;
	color: #000000;
}

#detail .tit {
	background: #B1CCEB;
	display: block;
	height: 22px;
	cursor: move;
	color: #000000;
}

#detail .tit i {
	float: right;
	padding-right: 9px;
	padding-top: 2px;
	cursor: default;
	font-size: 12px;
	color: #000000;
}

#detail2 {
	position: absolute;
	width: 350px;
	height: 120px;
	border: 1px solid #ccc;
	background: #efefef;
	display: none;
	color: #000000;
}

#detail2 .tit {
	background: #B1CCEB;
	display: block;
	height: 22px;
	cursor: move;
	color: #000000;
}

#detail2 .tit i {
	float: right;
	padding-right: 9px;
	padding-top: 2px;
	cursor: default;
	font-size: 12px;
	color: #000000;
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
	background: url(<%=basePath%>images/img/add-16.png) no-repeat 25px 2px;
	text-indent: 40px;
	text-align: center;
}

#box1 {
	height: 20px;
	float: left;
	width: 122px;
	padding-top: 20px;
	padding-left: 10px;
}

#box11 a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	cursor: pointer;
	width: 230px;
	height: 20px;
}

#box11 a {
	display: block;
	height: 20px;
	width: 230px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/import1-16.png) no-repeat 25px
		2px;
	text-indent: 40px;
	text-align: center;
}

#box11 {
	float: left;
	width: 1000px;
	padding-top: 10px;
	padding-left: 10px;
}

#box12 {
	float: left;
	width: 1000px;
	padding-left: 40px;
	_height: 20px;
	min-height: 20px;
}

#box0 a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	cursor: pointer;
	width: 122px;
	height: 20px;
}

#box0 a {
	display: block;
	height: 20px;
	width: 122px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/delete.png) no-repeat 25px 2px;
	text-indent: 40px;
	text-align: center;
}

#box0 {
	height: 20px;
	float: left;
	width: 122px;
	padding-top: 20px;
	padding-left: 24px;
}

#box2 {
	padding-top: 210px;
	padding-left: 30px;
	width: 1000px;
}

#box4 {
	font-family: "microsoft yahei";
	height: 100px;
	padding-left: 25px;
	padding-top: 16px;
}

#box9 {
	font-family: "microsoft yahei";
	height: 100px;
	padding-left: 25px;
	padding-top: 16px;
}

#box3 {
	font-family: "microsoft yahei";
	height: 30px;
	padding-top: 20px;
}
</style>
</head>
<script language="javascript">
	function save() {
		var form = document.getElementById("ap");
		form.action = "${pageContext.request.contextPath}/mainTainOfPT_updatemainTainOfPTNew.action?departmentid=${department.departmentid}";
		form.submit();
	}

	function bjapMoreNew() { //填写新的培养计划	
		if ($("input[type='checkbox']").is(':checked')) {
			var form = document.getElementById("noP");
			form.action = "${pageContext.request.contextPath}/mainTainOfPT_bjapNew.action?departmentid=${department.departmentid}";
			form.submit();
		}

		else {
			alert("请选择要填写的专业方向！");
			return false;
		}
	}

	function search(value) {
		var formid = "havaProfessional" + value;
		//		var selValue=document.getElementById("havePro").value;
		/* if(selValue == "-1")
		{return false;}
		else
		{return true;} */
		//		if(selValue != "-1")
		//		{
		var form = document.getElementById(formid);
		form.action = "${pageContext.request.contextPath}/mainTainOfPT_bjapMore.action?departmentid=${department.departmentid}";
		form.submit();
		//		}
	}

	function deletePro(value) {
		var formid = "havaProfessional" + value;
		var form = document.getElementById(formid);
		form.action = "${pageContext.request.contextPath}/mainTainOfPT_bjapdeletePro.action?departmentid=${department.departmentid}";
		form.submit();
	}
	
	function changePro(value){
		var formid = "havaProfessional" + value;
		var form = document.getElementById(formid);
		form.action = "${pageContext.request.contextPath}/mainTainOfPT_bjaptochangeProPage.action?departmentid=${department.departmentid}";
		form.submit();
	}
</script>
<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>培养计划总体安排表</strong>
		</div>
		<c:if
			test="${user.adminlevel == 1 || tag == 'revise' ||user.adminlevel == 5}">
			<div id="box11">
				<c:if test="${user.adminlevel == 1}">
					<a
						onclick="if (confirm('修改填写方式后，所有专业方向所填写的总体安排表将一并清空！')) return true; else return false;"
						href="mainTainOfPT_changetag.action?departmentid=${department.departmentid }">改为整个专业填写一张总体安排表</a>
				</c:if>

				<c:if test="${user.adminlevel == 5}">
					<a
						onclick="if (confirm('修改填写方式后，所有专业方向所填写的总体安排表将一并清空！')) return true; else return false;"
						href="mainTainOfPT_revisechangetag.action?departmentid=${department.departmentid }">改为整个专业填写一张总体安排表</a>
				</c:if>
			</div>
		</c:if>
		<div id="box12">
			<div
				style="_height: 10px; min-height: 10px; padding-top: 18px; float: left;">
				<table>
					<td style="vertical-align: top; font-size: 13px;">未填写专业方向：</td>
					<td style="vertical-align: top; font-size: 13px;">
						<form method="post" id="noP">
							<c:forEach items="${noProfessionalList }" var="noProfessional">
								<li style="list-style-type: none;"><input id="noPro"
									name="professionalList" type="checkbox"
									value="${noProfessional.professionalid }"
									style="align: center; font-size: 13px;">${noProfessional.professionalname}
								</li>
							</c:forEach>
							<div id="box3">
								<c:if test="${not empty noProfessionalList }">
									<button onclick="return bjapMoreNew()" style="width: 170px;">填写新培养计划总体安排表</button>
								</c:if>
							</div>
						</form>
					</td>
				</table>
			</div>

			<div
				style="_height: 10px; min-height: 10px; padding-left: 30px; padding-top: 18px; float: left;">
				<table>
					<td style="vertical-align: top; font-size: 13px;">已填写专业方向：</td>

					<td style="vertical-align: top; font-size: 13px;"><c:forEach
							items="${haveProfessionalList}" varStatus="xh">
							<form method="post" id="havaProfessional${xh.count}"
								name="havaProfessional${xh.count}">
								<li style="list-style-type: none; height: 35px;"><c:forEach
										items="${haveProfessionalList[xh.count-1]}"
										var="haveProfessional">
					(${haveProfessional.professionalname})
					<input type="hidden" name="professionalList"
											value="${haveProfessional.professionalid}">
									</c:forEach>

									<button onclick="search('${xh.count}')">查看</button>
									<button onclick="deletePro('${xh.count}')">删除</button>
									<button onclick="changePro('${xh.count}')">修改应用专业方向</button> </br></li>
							</form>
						</c:forEach></td>

				</table>
			</div>
		</div>
		<div>

			<!-- </form>  -->
			<c:if test="${search == 'yes' }">
				<div id="box1">
					<a id="t1" target="MainFrame">添加培养事件</a>
				</div>
				<div id="box0">
					<a id="t2" target="MainFrame">刪除培养事件</a>
				</div>
				<div id="box2">
					<h4 style="text-align: center">培养计划总体安排表</h4>


					<form id="ap" method='post'
						action='${pageContext.request.contextPath}/mainTainOfPT_updatemainTainOfPTNew.action?departmentid=${department.departmentid}'>
						<div style="height: 30px; padding-top: 5px;">
							应用的专业方向为：
							<c:forEach items="${professionalList}" var="professional">
			${professional.professionalname}
			<input type="hidden" name="professionalList"
									value="${professional.professionalid }">
							</c:forEach>
						</div>

						<table border="1" cellpadding="4" width="100%">
							<tr>
								<th>学年</th>
								<th>学期</th>
								<th colspan="20">教学进度安排（周）</th>
								<th>理论教学</th>
								<c:forEach items="${trainingEventslist }" var="trainingEvents">
									<th>${trainingEvents.trainingEventsname }</th>
								</c:forEach>
								<th>小计</th>
							</tr>
							<tr>
								<th></th>
								<th></th>
								<th>1</th>
								<th>2</th>
								<th>3</th>
								<th>4</th>
								<th>5</th>
								<th>6</th>
								<th>7</th>
								<th>8</th>
								<th>9</th>
								<th>10</th>
								<th>11</th>
								<th>12</th>
								<th>13</th>
								<th>14</th>
								<th>15</th>
								<th>16</th>
								<th>17</th>
								<th>18</th>
								<th>19</th>
								<th>20</th>
								<th>A</th>
								<c:forEach items="${trainingEventslist }" var="trainingEvents">
									<th>${trainingEvents.trainingEventscode }</th>
								</c:forEach>
								<th></th>
							</tr>
							<c:forEach items="${mainTainOfPTlist }" var="mainTainOfPT"
								varStatus="xh">
								<tr>
									<th><c:if
											test="${mainTainOfPT.semester == 1 || mainTainOfPT.semester == 2}">一</c:if>
										<c:if
											test="${mainTainOfPT.semester == 3 || mainTainOfPT.semester == 4}">二</c:if>
										<c:if
											test="${mainTainOfPT.semester == 5 || mainTainOfPT.semester == 6}">三</c:if>
										<c:if
											test="${mainTainOfPT.semester == 7 || mainTainOfPT.semester == 8}">四</c:if>
										<c:if
											test="${mainTainOfPT.semester == 9 || mainTainOfPT.semester == 10}">五</c:if>
									</th>
									<th><c:if test="${mainTainOfPT.semester == 1}">1</c:if> <c:if
											test="${mainTainOfPT.semester == 2}">2</c:if> <c:if
											test="${mainTainOfPT.semester == 3}">3</c:if> <c:if
											test="${mainTainOfPT.semester == 4}">4</c:if> <c:if
											test="${mainTainOfPT.semester == 5}">5</c:if> <c:if
											test="${mainTainOfPT.semester == 6}">6</c:if> <c:if
											test="${mainTainOfPT.semester == 7}">7</c:if> <c:if
											test="${mainTainOfPT.semester == 8}">8</c:if> <c:if
											test="${mainTainOfPT.semester == 9}">9</c:if> <c:if
											test="${mainTainOfPT.semester == 10}">10</c:if></th>
									<th><select style="width: 40px;" name="week1">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week1 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week1 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>

											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week2">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week2 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week2 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>

											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week3">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week3 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week3 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>

											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week4">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week4 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week4 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>

											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week5">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week5 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week5 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>

											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week6">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week6 }">selected="selected"</c:if>>A</option>

											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week6 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>

											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week7">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week7 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week7 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>
											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week8">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week8 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week8 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>
											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week9">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week9 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week9 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>
											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week10">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week10 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week10 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>
											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week11">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week11 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week11}">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>
											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week12">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week12 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week12 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>
											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week13">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week13 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week13 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>
											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week14">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week14 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week14 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>
											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week15">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week15 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week15 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>
											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week16">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week16 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week16 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>
											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week17">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week17 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week17 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>
											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week18">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week18 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week18 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>
											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week19">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week19 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week19 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>
											<option></option>
									</select></th>
									<th><select style="width: 40px;" name="week20">

											<option value="A"
												<c:if test="${'A' eq mainTainOfPT.week20 }">selected="selected"</c:if>>A</option>
											<c:forEach items="${trainingEventslist }"
												var="trainingEvents">
												<option value="${trainingEvents.trainingEventscode }"
													<c:if test="${trainingEvents.trainingEventscode eq mainTainOfPT.week20 }">selected="selected"</c:if>>${trainingEvents.trainingEventscode
													}</option>
											</c:forEach>
											<option></option>
									</select></th>
									<c:if test="${mainTainOfPT.semester == 1}">
										<th><input
											style="width: 21px; height: 15px; text-align: center"
											type="text" name="semester1"
											value="${liTrainingEvents.semester1 }" /> <c:forEach
												items="${trainingEventslist }" var="trainingEvents">
												<th><input
													style="width: 21px; height: 15px; text-align: center"
													type="text" name="semester1"
													value="${trainingEvents.semester1 }" />
											</c:forEach>
										<th><input
											style="width: 25px; height: 15px; text-align: center"
											type="text" name="semester1" value="26" readonly="readonly" />
									</c:if>
									<c:if test="${mainTainOfPT.semester == 2}">
										<th><input
											style="width: 21px; height: 15px; text-align: center"
											type="text" name="semester2"
											value="${liTrainingEvents.semester2 }" /> <c:forEach
												items="${trainingEventslist }" var="trainingEvents">
												<th><input
													style="width: 21px; height: 15px; text-align: center"
													type="text" name="semester2"
													value="${trainingEvents.semester2 }" />
											</c:forEach>
										<th><input
											style="width: 25px; height: 15px; text-align: center"
											type="text" name="semester2" value="26" readonly="readonly" />
									</c:if>
									<c:if test="${mainTainOfPT.semester == 3}">
										<th><input
											style="width: 21px; height: 15px; text-align: center"
											type="text" name="semester3"
											value="${liTrainingEvents.semester3 }" /> <c:forEach
												items="${trainingEventslist }" var="trainingEvents">
												<th><input
													style="width: 21px; height: 15px; text-align: center"
													type="text" name="semester3"
													value="${trainingEvents.semester3 }" />
											</c:forEach>
										<th><input
											style="width: 25px; height: 15px; text-align: center"
											type="text" name="semester3" value="26" readonly="readonly" />
									</c:if>
									<c:if test="${mainTainOfPT.semester == 4}">
										<th><input
											style="width: 21px; height: 15px; text-align: center"
											type="text" name="semester4"
											value="${liTrainingEvents.semester4 }" /> <c:forEach
												items="${trainingEventslist }" var="trainingEvents">
												<th><input
													style="width: 21px; height: 15px; text-align: center"
													type="text" name="semester4"
													value="${trainingEvents.semester4 }" />
											</c:forEach>
										<th><input
											style="width: 25px; height: 15px; text-align: center"
											type="text" name="semester4" value="26" readonly="readonly" />
									</c:if>
									<c:if test="${mainTainOfPT.semester == 5}">
										<th><input
											style="width: 21px; height: 15px; text-align: center"
											type="text" name="semester5"
											value="${liTrainingEvents.semester5 }" /> <c:forEach
												items="${trainingEventslist }" var="trainingEvents">
												<th><input
													style="width: 21px; height: 15px; text-align: center"
													type="text" name="semester5"
													value="${trainingEvents.semester5 }" />
											</c:forEach>
										<th><input
											style="width: 25px; height: 15px; text-align: center"
											type="text" name="semester5" value="26" readonly="readonly" />
									</c:if>
									<c:if test="${mainTainOfPT.semester == 6}">
										<th><input
											style="width: 21px; height: 15px; text-align: center"
											type="text" name="semester6"
											value="${liTrainingEvents.semester6 }" /> <c:forEach
												items="${trainingEventslist }" var="trainingEvents">
												<th><input
													style="width: 21px; height: 15px; text-align: center"
													type="text" name="semester6"
													value="${trainingEvents.semester6 }" />
											</c:forEach>
										<th><input
											style="width: 25px; height: 15px; text-align: center"
											type="text" name="semester6" value="26" readonly="readonly" />
									</c:if>
									<c:if test="${mainTainOfPT.semester == 7}">
										<th><input
											style="width: 21px; height: 15px; text-align: center"
											type="text" name="semester7"
											value="${liTrainingEvents.semester7 }" /> <c:forEach
												items="${trainingEventslist }" var="trainingEvents">
												<th><input
													style="width: 21px; height: 15px; text-align: center"
													type="text" name="semester7"
													value="${trainingEvents.semester7 }" />
											</c:forEach>
										<th><input
											style="width: 25px; height: 15px; text-align: center"
											type="text" name="semester7" value="26" readonly="readonly" />
									</c:if>
									<c:if test="${mainTainOfPT.semester == 8}">
										<th><input
											style="width: 21px; height: 15px; text-align: center"
											type="text" name="semester8"
											value="${liTrainingEvents.semester8 }" /> <c:forEach
												items="${trainingEventslist }" var="trainingEvents">
												<th><input
													style="width: 21px; height: 15px; text-align: center"
													type="text" name="semester8"
													value="${trainingEvents.semester8 }" />
											</c:forEach>
										<th><input
											style="width: 25px; height: 15px; text-align: center"
											type="text" name="semester8"
											value="${trainingEvents.semester8 }" />
									</c:if>
									<c:if test="${mainTainOfPT.semester == 9}">
										<th><input
											style="width: 21px; height: 15px; text-align: center"
											type="text" name="semester9"
											value="${liTrainingEvents.semester9 }" /> <c:forEach
												items="${trainingEventslist }" var="trainingEvents">
												<th><input
													style="width: 21px; height: 15px; text-align: center"
													type="text" name="semester9"
													value="${trainingEvents.semester9 }" />
											</c:forEach>
										<th><input
											style="width: 25px; height: 15px; text-align: center"
											type="text" name="semester9"
											value="${trainingEvents.semester9 }" />
									</c:if>
									<c:if test="${mainTainOfPT.semester == 10}">
										<th><input
											style="width: 21px; height: 15px; text-align: center"
											type="text" name="semester10"
											value="${liTrainingEvents.semester10 }" /> <c:forEach
												items="${trainingEventslist }" var="trainingEvents">
												<th><input
													style="width: 21px; height: 15px; text-align: center"
													type="text" name="semester10"
													value="${trainingEvents.semester10 }" />
											</c:forEach>
										<th><input
											style="width: 25px; height: 15px; text-align: center"
											type="text" name="semester10"
											value="${trainingEvents.semester10 }" />
									</c:if>
								</tr>
							</c:forEach>

						</table>

						<tr>
							<td class="text-center" colspan="2"><input id="bt"
								type="button" onclick="save()" value="保存" class="btn btn-info  "
								style="width: 80px;" /></td>
							<td class="text-center" colspan="2"><input id="bt"
								type="submit" value="提交" class="btn btn-info  "
								style="width: 80px;" /></td>
						</tr>
					</form>
				</div>
			</c:if>
		</div>
	</div>


	<div id="detail2">
		<div class="tit">
			<input type="button" class="close" value="X" />
		</div>
		<div id="box9">

			<form
				action="${pageContext.request.contextPath}/mainTainOfPT_bjapdelTrainingEventsNew.action"
				method="post">
				<c:forEach items="${professionalList}" var="professional">
					<input type="hidden" name="professionalList"
						value="${professional.professionalid }">
				</c:forEach>
				<div>
					事件名称：<select name="trainingEventsid">
						<c:forEach items="${trainingEventslist }" var="trainingEvents">
							<option value="${trainingEvents.trainingEventsid }">${trainingEvents.trainingEventsname
								}</option>
						</c:forEach>
					</select>
				</div>
				<input type="hidden" name="departmentid"
					value="${department.departmentid }">
				<div id="bto">
					<input type="submit" value="提交" />
				</div>
			</form>

		</div>
	</div>
	<div id="detail">
		<div class="tit">
			<input type="button" class="close" value="X" />
		</div>
		<div id="box4">

			<form
				action="${pageContext.request.contextPath}/mainTainOfPT_bjapaddTrainingEventsNew.action"
				method="post">
				<c:forEach items="${professionalList}" var="professional">
					<input type="hidden" name="professionalList"
						value="${professional.professionalid }">
				</c:forEach>
				<div>
					事件名称：<input type="text" name="trainingEventsname" required /> <input
						type="hidden" name="departmentid"
						value="${department.departmentid }">
				</div>
				<div id="bto">
					<input type="submit" value="提交" />
				</div>
			</form>

		</div>
	</div>
	<script type="text/javascript">
		$("#t2").click(function() {
			popWin("detail2");
		});
	</script>
	<script type="text/javascript">
		$("#t1").click(function() {
			popWin("detail");
		});
	</script>

</body>

</html>