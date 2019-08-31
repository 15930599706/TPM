<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<h2>多项选择题</h2>
		</div>
	</div>
	<div class="centerAll"></div>
	<div class="centerwapper"style="
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
			<!-- <+> -->
			<!-- <p class="queName3">
              是否必答
              <+>
              <select id="ansType" name="ansType">
                  <option value ="yes">是</option>
                  <option value ="no">否</option>
                </select>
            </p> -->
			<ul class="chose_wap" id="chose_wap">
				<c:forEach var="choose_card" items="${clist }">
					<li>
						<div class="chose_left_wap">
							分值 <select class="chose_score">
								<option>${choose_card.fenzhi }</option>
							</select>% <input name="#" type="checkbox" value="" disabled />
						</div>
						<div class="chose_right_wap">
							<p id="chose_text1" class="chose_text" onclick='textnull(1)'
								contenteditable="true">${choose_card.neirong }</p>
							<input class="del_chose_btn" type="button" value="删除">
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<input id="addMul" type="button" name="" onclick="addChoseMul1()"
			value="增加选项"> <input id="hideMinWin" type="button"
			onclick="hide_Min_Window()" value="关闭"> <input id="update"
			type="button" onclick="update_mult_choose( ${question.tihao} )"
			value="保存">
	</div>
	<!--  <div class="rightwapper">
          <ul>
            <li class="addChose_btn">
              <input type="button" name="" onclick="addChoseMul()" value="增加选项">
            </li>
            <li class="submitForm_btn">
                <+>
              <input type="button" name="" value="录入该题" onclick="submit_multChose()">
            </li>
          </ul>
      </div> -->

	</div>
	<script>
    function addChoseMul1(){
        var ul_id = document.getElementById('chose_wap');
        var ul_li = document.createElement("li");
        ul_id.appendChild(ul_li);

        var left_div = document.createElement("div");
        left_div.setAttribute("class","chose_left_wap");
        ul_li.appendChild(left_div);

        left_div.appendChild(document.createTextNode("分值"));

        // var score_text = document.createElement("p");
        // score_text.setAttribute("class","chose_score");
        // score_text.setAttribute("contenteditable","true");
        // score_text.appendChild(document.createTextNode("百分比"));
        // left_div.appendChild(score_text);
        var score_select = document.createElement("select");
        score_select.setAttribute("class","chose_score");
        var option1 = document.createElement("option");
        option1.setAttribute("value","0");
        option1.appendChild(document.createTextNode("0"));
        var option2 = document.createElement("option");
        option2.setAttribute("value","20");
        option2.appendChild(document.createTextNode("20"));
        var option3 = document.createElement("option");
        option3.setAttribute("value","30");
        option3.appendChild(document.createTextNode("30"));
        var option4 = document.createElement("option");
        option4.setAttribute("value","50");
        option4.appendChild(document.createTextNode("50"));
        var option5 = document.createElement("option");
        option5.setAttribute("value","100");
        option5.appendChild(document.createTextNode("100"));
        score_select.appendChild(option1);
        score_select.appendChild(option2);
        score_select.appendChild(option3);
        score_select.appendChild(option4);
        score_select.appendChild(option5);
        left_div.appendChild(score_select);

        left_div.appendChild(document.createTextNode("%"));
        var chose_radio = document.createElement("input");
        chose_radio.setAttribute("name","#");
        chose_radio.setAttribute("type","checkbox");
        chose_radio.setAttribute("name","#");
        left_div.appendChild(chose_radio);
        //ul_li.innerHTML='<input name="#" type="radio"/>';

        var right_div = document.createElement("div");
        right_div.setAttribute("class","chose_right_wap");
        ul_li.appendChild(right_div);

        var chose_text_text = document.createElement("p");
        chose_text_text.setAttribute("class","chose_text");
        chose_text_text.setAttribute("contenteditable","true");
        right_div.appendChild(chose_text_text);
        chose_text_text.appendChild(document.createTextNode("选项"));

        var del_btn = document.createElement("input");
        del_btn.setAttribute("class","del_chose_btn");
        del_btn.setAttribute("type","button");
        del_btn.setAttribute("value","删除");
        right_div.appendChild(del_btn);

        var bgc=document.getElementById("background");
	    var son = document.getElementById('QuestionMinWin');
	    bgc.style.height=500+son.offsetHeight+'px';
    }

    </script>
</body>
</html>
