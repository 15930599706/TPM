<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css"
	href="/TPM/images/css/inputQust.css">
<script type="text/javascript" src="../../../images/js/inputQust.js"></script>
<title></title>
<style type="text/css">
.centerwapper:hover{
	box-shadow: 0 0px 0px #d8d8d8;
	border:0px;
}
.centerwapper{
border:0px;
background-color:rgb(243,244,245);
}
}
</style>
</head>
<body>
	<div class="centerwapper" style="margin: 5 auto;">
		<div class="content" id="content" style="height: 85px;">
			<p id="queName1" class="queName1">多项填空题</p>
			<!-- <+> -->
			<p id="queName2" class="queName2">提示内容</p>
			<!-- <+> -->
			<p class="queName3">
				是否必答
				<!-- <+> -->
				<select id="ansType" name="ansType">
					<option value="yes">是</option>
					<option value="no">否</option>
				</select>
			</p>
		</div>

		<div class="multFill_wap">
			<ul>
				<li style="border: 0px;">
					<div class="div_title_question">
						<div id="multFill_wap_qtest_id" class="multFill_wap_qtest">
							${question.biaoti }</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>