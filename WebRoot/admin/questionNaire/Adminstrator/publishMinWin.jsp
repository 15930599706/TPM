<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
     <link rel="stylesheet" type="text/css" href="../../../images/css/inputQust.css">
    <script type="text/javascript" src="../../../images/js/inputQust.js"></script>
    <script type="text/javascript" src="../../../images/js/submit.js"></script>
    <title></title>
    <style>
    #publishnext{
	display:inline-block;
	width:14%;
	}
	#publish_message{
	text-align:center;
	}
	#startTime{
	margin-top:50px;
	margin-bottom:30px;
	}
	#publish_content{
	width:582px;
	height:300px;
	}
	</style>
  </head>
  <body>
   <div class="header">
	<div class="header-left"><h2>问卷发布时间</h2></div>

</div>
    <div class="centerAll">
 </div>
      <div class="centerwapper">

          <div class="publish_content" id="publish_content">

            <div id="publish">   
            		<div id="startTime">   	
		    		    <p id="publishnext" style="diaplay:inline-block">问卷开始时间</p>
			    		<input id="start_time" type="datetime-local"></div>
			    	</div > 	
			    	<div id="endTime">
			    		 <p id="publishnext" style="diaplay:inline-block">问卷结束时间</p>
			    		<input id="ending_time" type="datetime-local"></div>
			    	</div>	
  		    		    <div id="publish"> 
					    <input id="publish_btn" type="button" value="发布">
					     <input id="hideMinWin" type="button" onclick="hide_Min_Window()" value="关闭">
                        <input id="update" type="button" onclick="f()" value="保存">
			</div>
          </div>
         
      </div>
      <!-- <div class="rightwapper" id="rightwapper3">
          <ul>
            <li class="submitForm_btn">
              <+>
              <input type="button" name="" onclick="submit_fill()" value="录入该题">
            </li>
          </ul>
      </div> -->
       
    </div>
    <script>
    function f(){
    	var now = new Date();
        now.setHours(now.getHours() + 1);
        var str = now.getFullYear() + "-" + fix((now.getMonth() + 1), 2) + "-" + fix(now.getDate(), 2) +"-" + fix(now.getHours(), 2) + ":" + fix(now.getMinutes(), 2);
        $("#start_time").val(str);

    
    	/* var s=$("#start_time").val();
    	var str = now.getFullYear() + "-" + fix((now.getMonth() + 1), 2) + "-" + fix(now.getDate(), 2) + "T" + fix(now.getHours(), 2) + ":" + fix(now.getMinutes(), 2); */
    	alert(str);
    }
    function fix(num, length) {
        return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
    }


    </script>
  </body>
</html>
