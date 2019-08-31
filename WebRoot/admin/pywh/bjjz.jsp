<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<script type="text/javascript" src="<%=basePath %>/images/js/animate.js"></script>
<script type="text/javascript"
	src="<%=basePath %>/images/js/loadMask.js"></script>
<script type="text/javascript"
	src="<%=basePath %>images/js/jquery.min.js"></script>
<style type="text/css">
#bt {
	margin-top: 5px;
	margin-left: 25px;
}

body {
	font-size: 12px;
}

.t_n {
	width: 212px;
	height: 590px !important;
	background: buttonface;
	float: left;
	border-bottom: 1px solid #000;
	border-left: 1px solid #000
}

.t_n span {
	display: block;
	text-align: center;
	line-height: 50px;
	border-top: 1px solid #000;
	border-right: 1px solid #000;
	width: 211px;
	height: 40px;
}

.t_number {
	border-right: 1px solid #000;
	width: 100%;
	margin-bottom: 5px;
}

.t_number td {
	border-bottom: 1px solid #000;
	border-top: 1px solid #000;
	width: 30px;
	height: 45px;
	text-align: center;
}

.dd {
	height: 536px !important;
	overflow-y: hidden;
}

.t_i {
	width: 700px;
	height: auto;
	float: left;
	border-right: 1px solid #000;
	border-top: 1px solid #000
}

.t_i_h {
	width: 100%;
	overflow-x: hidden;
	background: buttonface;
}

.ee {
	float: left;
	width: 1000%;
}

/* .t_i_h table {
				width:3500px; 
			}
			 */
* {
	box-sizing: border-box;
}

.t_i_h table td div {
	border-right: 1px solid #000;
	border-bottom: 1px solid #000;
	height: 20px;
	text-align: center;
	min-width: 100px;
	width: 100%;
}

.t_i_h table td {
	max-width: 100px;
}

.cc {
	width: 100%;
	height: 552px;
	background: #fff;
	overflow: auto;
}

/* 	.cc table {
				 width:3500px; 
			} */
.cc table td div {
	height: 45px;
	border-bottom: 1px solid #000;
	border-right: 1px solid #000;
	min-width: 100px;
	width: 100%;
	text-align: center;
}
/*  .cc table tr:nth-child(odd) >td  {
    background-color: #FFFFFF;
}
.cc table tr:nth-child(even) >td  {
    background-color: #ECF4FB;
}  */
.cc table tr:hover>td {
	background-color: #ECF4FB;
}

#box1 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	width: 150px;
	height: 20px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 150px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(http://localhost:8080/TPM/images/img/28.png) no-repeat
		28px 2px;
	text-indent: 40px;
	text-align: center;
}

#box1 {
	height: 40px;
	float: left;
	width: 150px;
}

#box2 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	width: 160px;
	height: 20px;
}

#box2 a {
	display: block;
	height: 20px;
	width: 160px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(http://localhost:8080/TPM/images/img/disk-16.png)
		no-repeat 22px 3px;
	text-indent: 40px;
	text-align: center;
}

#box2 {
	height: 40px;
	float: left;
	width: 160px;
}

#box11 {
	padding-left: 24px;
	font-size: 14px;
	color: red;
}
</style>
<script>
	function aa() {
		var a = document.getElementById("cc").scrollTop;
		var b = document.getElementById("cc").scrollLeft;
		document.getElementById("dd").scrollTop = a; 
		document.getElementById("xx").scrollTop = a;
		document.getElementById("mm").scrollTop = a;
		document.getElementById("hh").scrollLeft = b;
	}
</script>
<script type="text/javascript">
		showMask({ info: "正在加载，请稍后……" });
		window.onload = function() {
			hiddenMask();
		}
	</script>
<script type="text/javascript">
			var maxtime = 30 * 60; //一个小时，按秒计算，自己调整!   
              function CountDown() {
                if (maxtime >= 0) {                   
                 minutes = Math.floor(maxtime / 60);
                     seconds = Math.floor(maxtime % 60);
                     msg ="注：为防止登录超时，请在" + minutes + "分" + seconds + "秒"+"内点击“保存”按钮保存所录入的数据，一次录入不完可以录入多次。";
                    document.all["timer"].innerHTML = msg;
//                   if (maxtime == 5 * 60)alert("还剩5分钟");
                        --maxtime;
}
                else{
                    clearInterval(timer);
                    alert("30分钟结束，请点击保存");
                 }
          }
            timer = setInterval("CountDown()", 1000);
</script>
</head>

<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>专业课程矩阵</strong>
		</div>


		<c:if test="${user.adminlevel == 1 || tag == 'revise'}">
			<box id="box1"> <a
				href="${pageContext.request.contextPath}/curriculumMatrix_modifyCurriculum.action?departmentID=${department.departmentid}"><small>修改专业认证课程</small></a>
			</box>
		</c:if>
		<%-- <box id="box2">
			<a href="${pageContext.request.contextPath}/exportcurriculumMatrix_exportMatrix.action?departmentID=${department.departmentid}"><small>生成该专业的课程矩阵</small></a>
		</box> --%>
		<box id="box2"> <a
			href="${pageContext.request.contextPath}/exportcurriculumMatrix_exportMatrix.action?departmentID=${department.departmentid}"
			id="test"><small>生成该专业的课程矩阵</small></a>
		<p id="five"></p>
		</box>

		<script type="text/javascript">		
$(function(){
    $("#test").bind('click',test);
});
function test(){
    alert("点击'确定'进行下载，等待数秒即可。");
    $("#test").css("color","#CCC");
    $("#test").unbind("click");
    setTimeout('$("#test").bind("click",test);$("#test").css("color","red");',5000);

}
</script>
		<c:if test="${user.adminlevel == 1 || tag == 'revise'}">
	&nbsp;	<box id="timer" style="color:red"> <!-- 		注：为防止登录超时，请在<div id="timer" style="color:red"></div>分钟内点击“保存”按钮保存所录入的数据，一次录入不完可以录入多次。 -->
			</box>
		</c:if>
		<h4 style="text-align: center; padding-top: 25px; padding-left: 20px;">专业课程矩阵</h4>
		<div style="width: 1200px;">
			<form id="bt" method="post"
				action="curriculumMatrix_update.action?departmentid=${department.departmentid}">
				<div class="t_n" style="width: 120px; text-align: center;">
					<span style="width: 120px;">课程类别</span>

					<div class="dd" id="dd">
						<table cellpadding="0" cellspacing="0" border="0" class="t_number">
							<tbody>
								<c:forEach items="${curriculumlist }" var="curriculum"
									varStatus="cxh">

									<tr>
										<td>${curriculum.courseLei }</td>
									</tr>
								</c:forEach>

								<c:if test="${not empty professionalList }">
									<c:forEach items="${professionalList }" var="professional"
										varStatus="pxh">
										<c:forEach items="${curriculumlist_pro[pxh.count-1] }"
											var="curriculum_pro">
											<tr>
												<td>${professional.professionalname }:${curriculum_pro.courseLei }</td>
											</tr>
										</c:forEach>
									</c:forEach>

								</c:if>

							</tbody>
						</table>
					</div>
				</div>
				<div class="t_n" style="width: 120px; text-align: center;">
					<span style="width: 120px;">课程性质</span>

					<div class="dd" id="mm">
						<table cellpadding="0" cellspacing="0" border="0" class="t_number">
							<tbody>
								<c:forEach items="${curriculumlist }" var="curriculum"
									varStatus="cxh">

									<tr>
										<td>${curriculum.natureOfCourse.natureOfCoursename }</td>
									</tr>
								</c:forEach>

								<c:if test="${not empty professionalList }">
									<c:forEach items="${professionalList }" var="professional"
										varStatus="pxh">
										<c:forEach items="${curriculumlist_pro[pxh.count-1] }"
											var="curriculum_pro">
											<tr>
												<td>${curriculum_pro.natureOfCourse.natureOfCoursename }</td>
											</tr>
										</c:forEach>
									</c:forEach>

								</c:if>

							</tbody>
						</table>
					</div>
				</div>
				<div class="t_n">
					<span>课程名称</span>
					<div class="dd" id="xx">
						<table cellpadding="0" cellspacing="0" border="0" class="t_number">
							<tbody>
								<c:forEach items="${curriculumlist }" var="curriculum"
									varStatus="cxh">
									<tr>
										<td>${curriculum.curriculumCname }<input type="hidden"
											name="cid" value="${curriculum.curriculumid }"></input></td>
									</tr>

								</c:forEach>

								<c:if test="${not empty professionalList }">
									<c:forEach items="${professionalList }" var="professional"
										varStatus="pxh">
										<c:forEach items="${curriculumlist_pro[pxh.count-1] }"
											var="curriculum_pro">
											<tr>
												<td>${curriculum_pro.curriculumCname}<input
													type="hidden" name="cid"
													value="${curriculum.curriculumid }"></input></td>
											</tr>
										</c:forEach>
									</c:forEach>

								</c:if>

							</tbody>
						</table>
					</div>
				</div>
				<div class="t_i">
					<div class="t_i_h" id="hh">
						<div class="ee">
							<table cellpadding="0" cellspacing="0" border="0"
								style="border-right: 100px;">
								<tr>
									<c:forEach items="${abilitielist }" var="abilitie"
										varStatus="xh">
										<td colspan="${fn:length(anlist[xh.index])}">
											<div>${xh.count }</div>
										</td>
									</c:forEach>
								</tr>
								<tr>
									<c:forEach items="${abilitielist }" var="abilitie"
										varStatus="xh">
										<c:forEach items="${anlist[xh.index] }" var="an"
											varStatus="axh">
											<td>
												<div>${axh.count }</div>
											</td>
										</c:forEach>
									</c:forEach>

								</tr>
							</table>
						</div>
					</div>
					<div class="cc" id="cc" onscroll="aa()">
						<table cellpadding="0" cellspacing="0" border="0">
							<c:forEach items="${curriculumlist }" var="curriculum"
								varStatus="cxh">
								<tr>

									<td><c:forEach items="${abilitielist }" var="abilitie"
											varStatus="xh">
											<c:forEach items="${anlist[xh.index] }" var="an"
												varStatus="axh">
												<c:if test="${user.adminlevel == 1 || tag == 'revise'}">
													<td style="">
														<div>
															<select name="ability${xh.count }dec${axh.count}"
																style="width: 48px">
																<option value="K"
																	<c:if test="${curriculumMatrixlist[cxh.index][xh.index][axh.index].score eq 'K' }">selected="selected"</c:if>></option>
																<option value="H"
																	<c:if test="${curriculumMatrixlist[cxh.index][xh.index][axh.index].score eq 'H' }">selected="selected"</c:if>>H</option>
																<option value="L"
																	<c:if test="${curriculumMatrixlist[cxh.index][xh.index][axh.index].score eq 'L' }">selected="selected"</c:if>>L</option>
																<option value="M"
																	<c:if test="${curriculumMatrixlist[cxh.index][xh.index][axh.index].score eq 'M' }">selected="selected"</c:if>>M</option>
															</select>
														</div>
													</td>
												</c:if>
												<c:if test="${user.adminlevel != 1 && tag != 'revise'}">
													<td style=""><div>
															<c:if
																test="${curriculumMatrixlist[cxh.index][xh.index][axh.index].score eq 'K' }"></c:if>
															<c:if
																test="${curriculumMatrixlist[cxh.index][xh.index][axh.index].score ne 'K' }">${curriculumMatrixlist[cxh.index][xh.index][axh.index].score}</c:if>
														</div></td>
												</c:if>
											</c:forEach>
										</c:forEach></td>

								</tr>

							</c:forEach>




							<c:if test="${not empty professionalList }">
								<c:forEach items="${professionalList }" var="professional"
									varStatus="pxh">

									<c:forEach items="${curriculumlist_pro[pxh.count-1] }"
										var="curriculum" varStatus="cxh">
										<tr>

											<td><c:forEach items="${abilitielist }" var="abilitie"
													varStatus="xh">
													<c:forEach items="${anlist[xh.index] }" var="an"
														varStatus="axh">
														<c:if test="${user.adminlevel == 1 || tag == 'revise'}">
															<td style="">
																<div>
																	<select name="ability${xh.count }dec${axh.count}"
																		style="width: 48px">
																		<option value="K"
																			<c:if test="${curriculumMatrixlist_pro[pxh.index][cxh.index][xh.index][axh.index].score eq 'K' }">selected="selected"</c:if>></option>
																		<option value="H"
																			<c:if test="${curriculumMatrixlist_pro[pxh.index][cxh.index][xh.index][axh.index].score eq 'H' }">selected="selected"</c:if>>H</option>
																		<option value="L"
																			<c:if test="${curriculumMatrixlist_pro[pxh.index][cxh.index][xh.index][axh.index].score eq 'L' }">selected="selected"</c:if>>L</option>
																		<option value="M"
																			<c:if test="${curriculumMatrixlist_pro[pxh.index][cxh.index][xh.index][axh.index].score eq 'M' }">selected="selected"</c:if>>M</option>
																	</select>
																</div>
															</td>
														</c:if>
														<c:if test="${user.adminlevel != 1 && tag != 'revise'}">
															<td style=""><div>
																	<c:if
																		test="${curriculumMatrixlist_pro[pxh.index][cxh.index][xh.index][axh.index].score eq 'K' }"></c:if>
																	<c:if
																		test="${curriculumMatrixlist_pro[pxh.index][cxh.index][xh.index][axh.index].score ne 'K' }">${curriculumMatrixlist_pro[pxh.index][cxh.index][xh.index][axh.index].score}</c:if>
																</div></td>
														</c:if>
													</c:forEach>
												</c:forEach></td>

										</tr>

									</c:forEach>
								</c:forEach>
							</c:if>



						</table>
					</div>
				</div>

				<c:if test="${user.adminlevel == 1 || tag == 'revise'}">
					<tr>
						<td class="text-center" colspan="2"><input type="submit"
							value="保存" class="btn btn-info  "
							style="margin-left: 50%; margin-top: 10px;" /></td>
					</tr>
				</c:if>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
		$("input[type=submit]").parents("form").on("submit", function() {
			showMask({
				info: "正在保存数据，请稍后……"
			});
		});
	</script>
</html>