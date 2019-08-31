<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath%>images/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath%>images/css/css.css" />
<script type="text/javascript"
	src="<%=basePath%>images/js/jquery1.9.0.min.js"></script>
<script type="text/javascript">
			$(document).ready(function() {
				$("#firstpane .menu_body:eq(0)").show();
				$("#firstpane h3.menu_head").click(function() {
					$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
					$(this).siblings().removeClass("current");
				});
			});
		</script>

<style type="text/css">
.menu_list {
	width: 700px;
	margin: 0 auto;
}

.menu_head {
	height: 47px;
	line-height: 47px;
	padding-left: 38px;
	font-size: 14px;
	color: #525252;
	cursor: pointer;
	border: 1px solid #e1e1e1;
	position: relative;
	margin: 0px;
	font-weight: bold;
	background: #f1f1f1;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
	margin-top: -1px;
}

.menu_list .current {
	background: #f1f1f1;
}

.menu_body {
	line-height: 38px;
	border-left: 1px solid #e1e1e1;
	backguound: #fff;
	border-right: 1px solid #e1e1e1;
	border-bottom: 1px solid #e1e1e1;
}

.check {
	width: 120px;
}

.tr {
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
}

#bt {
	padding-left: 300px;
	padding-top: 50px;
}

table {
	border-collapse: separate;
	border-spacing: 10px;
	width: 700px;
	table-layout: fixed; /* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
}

td {
	word-break: keep-all; /* 不换行 */
	white-space: nowrap; /* 不换行 */
	overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
	text-overflow: ellipsis;
	/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用*/
}
</style>

</head>

<body>

	<div class="right_cont">
		<div class="title_right">
			<strong>专业毕业要求及指标点分解</strong>
		</div>
		<div id="firstpane" class="menu_list">
			<h4 style="text-align: center">专业毕业要求及指标点分解</h4>
			<c:forEach items="${abilityList }" var="ability" varStatus="xh">
				<h3 class="menu_head current" data-id="${ability.abilityid }">${xh.count }、${ability.abilitycontent }</h3>
				<div style="display: block" class="menu_body">
					<table>
						<!-- <tr>
				<td width="20px"><input type="checkbox" class="check multiselect-all" onclick="swapCheck(this)" /></td>
						<td><small>全选</small></td>
					</tr> -->
						<c:forEach items="${analysisList[xh.index] }" var="analysis"
							varStatus="ct">
							<tr>
								<td width="20px"><c:forEach items="${abilitySelectList }"
										var="ability2" varStatus="axh">
										<c:if test="${ability.abilityid eq ability2.abilityid }">
											<c:forEach items="${analysisSelectList[axh.index] }"
												var="analysis2">
												<c:if
													test="${analysis.analysisid eq analysis2.analisis.analysisid }">
													<input type="checkbox" class="check" disabled
														checked="checked" data-id="${analysis.analysisid }" />
												</c:if>
											</c:forEach>
										</c:if>
										<%-- <c:if test="${ability.abilityid ne ability2.abilityid }">
								<c:if test="${axh.index }"><input type="checkbox" class="check" data-id="${analysis.analysisid }"/></c:if>
							</c:if> --%>
									</c:forEach></td>
								<td><small>${xh.count }-${ct.count }${analysis.analysiscontent }</small></td>
							</tr>
						</c:forEach>
					</table>
				</div>

			</c:forEach>
			<p style="text-align: left; margin-top: 5px;">注：默认勾选的指标点是从专业课程矩阵中自动关联的。</p>
			<div style="text-align: center; margin-top: 5px;">
				<button onclick="save()">下一步</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
			function save() {
				var datas = [];
				$(".menu_head").each(function() {
					var $this = $(this);
					var data = {
						id: $this.data("id"),
						items: []
					};
					$this.next().find("input:checked").not(".multiselect-all").each(function (){
						data.items.push($(this).data("id"));
					});
					datas.push(data);
				});
				
				console.log(datas);
				var url = "${pageContext.request.contextPath}/abilityAndTeachObj_updateSelectIndexTheoInnerExperiment.action?syllabusId=${syllabusId}";
				$.post(url, {data: JSON.stringify(datas) }, function(res) {
				window.location.href = "${pageContext.request.contextPath}/abilityAndTeachObj_toAimTheoPageInnerExperiment.action?syllabusId=${syllabusId}&theoreticalLessonId=${newtheoreticalLesson.theoreticalLessonid}";
					console.log(res);
				});
			}
			
			function swapCheck(control) {
				var isCheckAll = control.checked;
				$(control).parents(".menu_body").find("input[type='checkbox']").each(function() {
					this.checked = isCheckAll;
				});
			}
		</script>

</body>

</html>