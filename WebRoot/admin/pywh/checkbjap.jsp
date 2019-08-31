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
	color: #000;
	background-color: #D2E7F5;
	border: 1px solid #B1CCEB;
	width: 118px;
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
	height: 40px;
	float: left;
	width: 120px;
	padding-top: 10px;
	padding-left: 30px;
}

#box0 a:hover {
	font-family: "microsoft yahei";
	color: #000;
	background-color: #D2E7F5;
	border: 1px solid #B1CCEB;
	width: 118px;
	height: 20px;
}

#box0 a {
	display: block;
	height: 20px;
	width: 120px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/delete.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box0 {
	height: 40px;
	float: left;
	width: 120px;
	padding-top: 10px;
	padding-left: 100px;
}

#box2 {
	padding-top: 12px;
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
</style>
</head>


<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>培养计划总体安排表</strong>
		</div>

		<div id="box2">
			<h4 style="text-align: center">培养计划总体安排表</h4>
			<form>
				<table border="1" cellpadding="4" width="100%">
					<tr>
						<th rowspan="2">学年</th>
						<th rowspan="2">学期</th>
						<th colspan="20">教学进度安排（周）</th>
						<th>理论教学</th>
						<c:forEach items="${trainingEventslist }" var="trainingEvents">
							<th>${trainingEvents.trainingEventsname }</th>
						</c:forEach>
						<th>小计</th>
					</tr>
					<tr>

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
						<th style="width: 15px;">A</th>
						<c:forEach items="${trainingEventslist }" var="trainingEvents">
							<th>${trainingEvents.trainingEventscode }</th>
						</c:forEach>
						<th style="width: 15px;"></th>
					</tr>
					<c:forEach items="${mainTainOfPTlist }" var="mainTainOfPT"
						varStatus="xh">
						<tr>
							<th style="width: 15px;"><c:if
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
							<th style="width: 15px;"><c:if
									test="${mainTainOfPT.semester == 1}">1</c:if> <c:if
									test="${mainTainOfPT.semester == 2}">2</c:if> <c:if
									test="${mainTainOfPT.semester == 3}">3</c:if> <c:if
									test="${mainTainOfPT.semester == 4}">4</c:if> <c:if
									test="${mainTainOfPT.semester == 5}">5</c:if> <c:if
									test="${mainTainOfPT.semester == 6}">6</c:if> <c:if
									test="${mainTainOfPT.semester == 7}">7</c:if> <c:if
									test="${mainTainOfPT.semester == 8}">8</c:if> <c:if
									test="${mainTainOfPT.semester == 9}">9</c:if> <c:if
									test="${mainTainOfPT.semester == 10}">10</c:if></th>
							<th>${mainTainOfPT.week1 }</th>
							<th>${mainTainOfPT.week2 }</th>
							<th>${mainTainOfPT.week3 }</th>
							<th>${mainTainOfPT.week4 }</th>
							<th>${mainTainOfPT.week5 }</th>
							<th>${mainTainOfPT.week6 }</th>
							<th>${mainTainOfPT.week7 }</th>
							<th>${mainTainOfPT.week8 }</th>
							<th>${mainTainOfPT.week9 }</th>
							<th>${mainTainOfPT.week10 }</th>
							<th>${mainTainOfPT.week11}</th>
							<th>${mainTainOfPT.week12 }</th>
							<th>${mainTainOfPT.week13 }</th>
							<th>${mainTainOfPT.week14 }</th>
							<th>${mainTainOfPT.week15 }</th>
							<th>${mainTainOfPT.week16 }</th>
							<th>${mainTainOfPT.week17 }</th>
							<th>${mainTainOfPT.week18 }</th>
							<th>${mainTainOfPT.week19 }</th>
							<th>${mainTainOfPT.week20 }</th>
							<c:if test="${mainTainOfPT.semester == 1}">
								<th style="width: 15px;">${liTrainingEvents.semester1 }</th>
								<c:forEach items="${trainingEventslist }" var="trainingEvents">
									<th style="width: 15px;">${trainingEvents.semester1 }</th>
								</c:forEach>
								<th style="width: 15px;">${trainingEvents.semester1 }</th>
							</c:if>
							<c:if test="${mainTainOfPT.semester == 2}">
								<th style="width: 15px;">${liTrainingEvents.semester2 }</th>
								<c:forEach items="${trainingEventslist }" var="trainingEvents">
									<th style="width: 15px;">${trainingEvents.semester2 }</th>
								</c:forEach>
								<th style="width: 15px;">${trainingEvents.semester2 }</th>
							</c:if>
							<c:if test="${mainTainOfPT.semester == 3}">
								<th style="width: 15px;">${liTrainingEvents.semester3 }</th>
								<c:forEach items="${trainingEventslist }" var="trainingEvents">
									<th style="width: 15px;">${trainingEvents.semester3 }</th>
								</c:forEach>
								<th style="width: 15px;">${trainingEvents.semester3 }</th>
							</c:if>
							<c:if test="${mainTainOfPT.semester == 4}">
								<th style="width: 15px;">${liTrainingEvents.semester4 }</th>
								<c:forEach items="${trainingEventslist }" var="trainingEvents">
									<th style="width: 15px;">${trainingEvents.semester4 }</th>
								</c:forEach>
								<th style="width: 15px;">${trainingEvents.semester4 }</th>
							</c:if>
							<c:if test="${mainTainOfPT.semester == 5}">
								<th style="width: 15px;">${liTrainingEvents.semester5 }</th>
								<c:forEach items="${trainingEventslist }" var="trainingEvents">
									<th style="width: 15px;">${trainingEvents.semester5 }</th>
								</c:forEach>
								<th style="width: 15px;">${trainingEvents.semester5 }</th>
							</c:if>
							<c:if test="${mainTainOfPT.semester == 6}">
								<th style="width: 15px;">${liTrainingEvents.semester6 }</th>
								<c:forEach items="${trainingEventslist }" var="trainingEvents">
									<th style="width: 15px;">${trainingEvents.semester6 }</th>
								</c:forEach>
								<th style="width: 15px;">${trainingEvents.semester6 }</th>
							</c:if>
							<c:if test="${mainTainOfPT.semester == 7}">
								<th style="width: 15px;">${liTrainingEvents.semester7 }</th>
								<c:forEach items="${trainingEventslist }" var="trainingEvents">
									<th style="width: 15px;">${trainingEvents.semester7 }</th>
								</c:forEach>
								<th style="width: 15px;">${trainingEvents.semester7 }</th>
							</c:if>
							<c:if test="${mainTainOfPT.semester == 8}">
								<th style="width: 15px;">${liTrainingEvents.semester8 }</th>
								<c:forEach items="${trainingEventslist }" var="trainingEvents">
									<th style="width: 15px;">${trainingEvents.semester8 }</th>
								</c:forEach>
								<th style="width: 15px;">${trainingEvents.semester8 }</th>
							</c:if>
							<c:if test="${mainTainOfPT.semester == 9}">
								<th style="width: 15px;">${liTrainingEvents.semester9 }</th>
								<c:forEach items="${trainingEventslist }" var="trainingEvents">
									<th style="width: 15px;">${trainingEvents.semester9 }</th>
								</c:forEach>
								<th style="width: 15px;">${trainingEvents.semester9 }</th>
							</c:if>
							<c:if test="${mainTainOfPT.semester == 10}">
								<th style="width: 15px;">${liTrainingEvents.semester10 }</th>
								<c:forEach items="${trainingEventslist }" var="trainingEvents">
									<th style="width: 15px;">${trainingEvents.semester10 }</th>
								</c:forEach>
								<th style="width: 15px;">${trainingEvents.semester10 }</th>
							</c:if>
						</tr>
					</c:forEach>

				</table>


			</form>
		</div>
	</div>
</body>

</html>