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
			<strong>单项选择题</strong>
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
					id="option_catchValue_ability" onchange="changeAbility();">
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

			<ul class="chose_wap" id="chose_wap">
				<li>
					<div class="chose_left_wap">
						分值
						<!-- <p class="chose_score" contenteditable="true">百分比</p> -->
						<select class="chose_score" contenteditable="true">
							<option value="0">0</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="50">50</option>
							<option value="100" selected>100</option>
						</select>% <input class="radio" name="#" type="radio" value="" disabled />
					</div>
					<div class="chose_right_wap">
						<!-- onmouseover="this.focus()" -->
						<p id="chose_text1" class="chose_text" contenteditable="true"
							onclick='textnull(1)'>选项A</p>

						<input class="del_chose_btn" type="button" value="删除">
					</div>
				</li>
				<li>

					<div class="chose_left_wap">
						分值 <select class="chose_score" contenteditable="true">
							<option value="0">0</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="50">50</option>
							<option value="100">100</option>
						</select>% <input class="radio" name="#" type="radio" value="" disabled />
					</div>
					<div class="chose_right_wap">
						<p class="chose_text" id="chose_text2" contenteditable="true"
							onclick='textnull(2)'>选项B</p>
						<input class="del_chose_btn" type="button" value="删除">
					</div>

				</li>
				<li>
					<div class="chose_left_wap">
						分值 <select class="chose_score" contenteditable="true">
							<option value="0">0</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="50">50</option>
							<option value="100">100</option>
						</select>% <input class="radio" name="#" type="radio" value="" disabled />
					</div>
					<div class="chose_right_wap">
						<p class="chose_text" id="chose_text3" onclick='textnull(3)'
							contenteditable="true">选项C</p>
						<input class="del_chose_btn" type="button" value="删除">
					</div>
				</li>

				<li>
					<div class="chose_left_wap">
						分值 <select class="chose_score" contenteditable="true">
							<option value="0">0</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="50">50</option>
							<option value="100">100</option>
						</select>% <input class="radio" name="#" type="radio" value="" disabled />
					</div>
					<div class="chose_right_wap">
						<p class="chose_text" id="chose_text4" onclick='textnull(4)'
							contenteditable="true">选项D</p>
						<input class="del_chose_btn" type="button" value="删除">
					</div>
				</li>
			</ul>
		</div>
	</div>
	<div class="rightwapper">
		<ul>
			<li class="addChose_btn"><input type="button" name=""
				onclick="addChosesSingle()" value="增加选项"></li>
			<li class="submitForm_btn"><input type="button" name=""
				value="录入该题" onclick="submit_singleChose()"></li>
		</ul>
	</div>
	</div>
</body>
</html>
