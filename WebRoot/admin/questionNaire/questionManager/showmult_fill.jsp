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
<style>
</style>
</head>
<body>
	<div class="header">
		<div class="header-left">
			<h2>多项填空题</h2>
		</div>

	</div>
	<div class="centerAll"></div>
	<div class="centerwapper" style="
    margin-bottom: 0px;
">
		<div class="content" id="content">
			<!-- <+> -->
			<p id="queName1" class="queName1" contenteditable="false">多项填空题</p>
			<!-- <+> -->
			<p id="queName2" class="queName2" contenteditable="true">提示内容</p>
			<!-- <+> -->
			<!-- <p class="queName3">
              是否必答
              <+>
              <select id="ansType" name="ansType">
                  <option value ="yes">是</option>
                  <option value ="no">否</option>
                </select>
            </p> -->
		</div>

		<div class="multFill_wap">
			<ul>
				<li style="border: 0px;">
					<div class="multFill_wap">
						<ul>
							<li style="border: 1px;">
								<div class="div_title_question">
									<p style="display: inline-block;">题目:</p>
									<div id="multFill_wap_qtest_id" class="multFill_wap_qtest"
										contenteditable="true" style="display: inline-block;">
										${question.biaoti }</div>
								</div>
							</li>
						</ul>
					</div>
					<div id="setFillPropertyLight" class="setFillPropertyLight">
						<div id="multFill_que_menu_top" class="multFill_que_menu_top">

						</div>
						<div id="multFill_que_menu_center"
							class="multFill_que_menu_center">
							<ul id="multFill_que_menu_center_ul_0">
								<li class="cap1">填空</li>
								<li class="cap2">范围</li>
								<li class="cap3"><a class="menuExit"
									href="javascript:void(0)" onclick="hideMiniMeu()">确定</a></li>
							</ul>
							<!-- <++++++++++++++> -->
							<ul id="multFill_que_menu_center_ul_1">
								<ul class="cap1">填空1
								</ul>
								<ul class="cap2">
									<span style="float: left;">最小字数：</span>
									<p contenteditable="true" id="min1"
										style="width: 40px; border: 1px solid black; float: left;">0</p>
									<span style="float: left; margin-left: 5px;">最大字数：</span>
									<p contenteditable="true" id="max1"
										style="width: auto; border: 1px solid black; float: left;">100</p>
								</ul>
							</ul>
							<ul id="multFill_que_menu_center_ul_2">
								<ul class="cap1">填空1
								</ul>
								<ul class="cap2">
									<span style="float: left;">最小字数：</span>
									<p contenteditable="true" id="min2"
										style="width: 40px; border: 1px solid black; float: left;">0</p>
									<span style="float: left; margin-left: 5px;">最大字数：</span>
									<p contenteditable="true" id="max2"
										style="width: auto; border: 1px solid black; float: left;">100</p>
								</ul>
							</ul>
							<ul id="multFill_que_menu_center_ul_3">
								<ul class="cap1">填空1
								</ul>
								<ul class="cap2">
									<span style="float: left;">最小字数：</span>
									<p contenteditable="true" id="min3"
										style="width: 40px; border: 1px solid black; float: left;">0</p>
									<span style="float: left; margin-left: 5px;">最大字数：</span>
									<p contenteditable="true" id="max3"
										style="width: auto; border: 1px solid black; float: left;">100</p>
								</ul>
							</ul>
							<!-- <++++++++++++++> -->
						</div>

					</div>
					</p>
				</li>
			</ul>
		</div>
		<br /> <input type="button" onclick="Insertsign()" id="addFill"
			name="" value="插入填空符"> <input type="button"
			href="javascript:void(0)" onclick="showMiniMeu()" id="fillshuxing"
			name="" value="填空属性"> <input id="hideMinWin" type="button"
			onclick="hide_Min_Window()" value="关闭"> <input id="update"
			type="button" onclick="update_mult_fill( ${question.tihao} )"
			value="保存">
	</div>
	<!-- <div class="rightwapper" id="rightwapper">
          <ul>
            <li class="submitForm_btn">
              <input type="button" onclick="Insertsign()" id="multFill_Insertsign" name="" value="插入填空符">

              <input type="button"  href = "javascript:void(0)" onclick ="showMiniMeu()"   name="" value="填空属性">
              <+>
              <input type="button" name="" onclick="submit_multFill()" value="录入该题">
            </li>
          </ul>
      </div> -->

	</div>
	<div id="setFillPropertyBcg" class="setFillPropertyBcg"></div>

</body>
</html>
