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
			<h2>单项填空题</h2>
		</div>
	</div>
	<div class="centerAll"></div>
	<div class="centerwapper" style="
    margin-bottom: 0px;
">

		<div class="content" id="content">



			<!-- <+> -->
			<p style="display: inline-block;">题目:</p>
			<p id="queName1" class="queName1" contenteditable="true"
				style="display: inline-block;">${question.biaoti }</p>
			<br />
			<!-- <+> -->
			<p style="display: inline-block;">提示内容:</p>
			<p id="queName2" class="queName2" contenteditable="true"
				style="display: inline-block;">${question.tishineirong }</p>
			<br />
			<!-- <+> -->
			<!-- <p class="queName3">
            是否必答
            <+>
            <select id="ansType" name="ansType">
                <option value ="yes">是</option>
                <option value ="no">否</option>
              </select>
          </p> -->
			<div class="single_inputText"></div>
		</div>
		<input id="hideMinWin" type="button" onclick="hide_Min_Window()"
			value="关闭"> <input id="update" type="button"
			onclick="update_single_fill( ${question.tihao} )" value="保存">
	</div>
	<!-- <div class="rightwapper">
          <ul>
            <li class="submitForm_btn">
                <+>
              <input type="button" name=""onclick="submit_singleFill()" value="录入该题">
            </li>
          </ul>
      </div> -->

	</div>
</body>
</html>
