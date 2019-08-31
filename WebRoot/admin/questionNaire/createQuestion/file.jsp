<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<link rel="stylesheet" type="text/css"
	href="../../../images/css/inputQust.css">
<script type="text/javascript" src="../../../images/js/inputQust.js"></script>
<script type="text/javascript" src="../../../images/js/submit.js"></script>
<title></title>
</head>
<body>
	<div class="header">
		<div class="header-left">
			<h2>文件上传题</h2>
		</div>

	</div>
	<div class="centerAll"></div>
	<div class="centerwapper">

		<div class="content" id="content">

			<ul class="indexPoint_box">
				<li>专业： <!-- <+> --> <select name="option_catchValue"
					id="option_catchValue" onchange="changeMajer()">
						<option value="">请选择一个专业</option>
				</select>
				</li>
				<li>能力： <!-- <+> --> <select name="option_catchValue_ability"
					id="option_catchValue_ability" onchange="changeAbility()">
						<option value="">请选择一项能力</option>
				</select>
				</li>
			</ul>
			<div class="zhibiaodian">
				<div class="leftwatext">指标点：</div>
				<div id="selectBoard">
					<ul id="option_catchValue_indexPoint">

					</ul>
					<span id="showData"></span>
				</div>
			</div>

			<!-- <+> -->
			<p id="queName1" class="queName1" contenteditable="true">请点击编辑题目内容：</p>
			<!-- <+> -->
			<p id="queName2" class="queName2" contenteditable="true">提示内容</p>
			<!-- <+> -->
			<p class="queName3">
				是否必答
				<!-- <+> -->
				<select id="ansType" name="ansType">
					<option value="yes">是</option>
					<option value="no">否</option>
				</select>
			</p>
			<div class="fillBor">
				<img src="" alt=""> 选择文件（大小不超过4M）
			</div>
		</div>
	</div>
	<div class="rightwapper" id="rightwapper3">
		<ul>
			<li class="submitForm_btn">
				<!-- <+> --> <input type="button" name="" onclick="submit_fill()"
				value="录入该题">
			</li>
		</ul>
	</div>
	</div>
</body>
</html>
