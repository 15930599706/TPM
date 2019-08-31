            function judgepwd(){
              var pwd = document.getElementById("pwd").value;
              if(pwd.length<6 || pwd.length>11){
                document.getElementById("__tishi").innerHTML="<font color='red'>密码长度应为6-11位！</font>";
                  document.getElementById("pwd").focus();
              }
              else{
                document.getElementById("__tishi").innerHTML="";
              }
            }
            function judge_pwd() {
              var pwd = document.getElementById("pwd").value;
              var _pwd = document.getElementById("_pwd").value;
              if(pwd == _pwd) {
              document.getElementById("tishi").innerHTML="<font color='green'>两次密码相同</font>";
              document.getElementById("submit").disabled = false;
              }
              else {
              document.getElementById("tishi").innerHTML="<font color='red'>两次密码不相同</font>";
              document.getElementById("submit").disabled = true;
              }
            }
            function onfFuc(){
              var pwd = document.getElementById("pwd").value;
              var _pwd = document.getElementById("_pwd").value;
              if(_pwd==''){
                document.getElementById("tishi").innerHTML="<font color='red' size='5px'>请再次输入密码</font>";
                document.getElementById("submit").disabled = true;
              }
            }

            function checkUserName(){
                var ssn=document.getElementById("phoneNum").value;
              	if(ssn.length!=11){
                	document.getElementById("_tishi").innerHTML="<font color='red'>用户名长度应为11位！</font>";
                document.getElementById("phoneNum").focus();
                	return false;
              	}
                if( ssn.length==0) {
                	document.getElementById("_tishi").innerHTML="<font color='red' >请填写用户名！</font>";
                document.getElementById("phoneNum").focus();
                	return false;
              	}
                 else{
                   	document.getElementById("_tishi").innerHTML="";
                 }
              	return true;
          }
        function ajxTest(){

      			var text_user_name= document.getElementById('phoneNum');
      			//异步请求
      			var xmlhttp;
      			if (window.XMLHttpRequest)
      				xmlhttp=new XMLHttpRequest();
      			else
      				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
      			xmlhttp.onreadystatechange=function()
      			{
      				if (xmlhttp.readyState==4 && xmlhttp.status==200)
      				{
      					 var obj = JSON.parse(xmlhttp.responseText);   
      					 
      					if(obj[0].status==0){
                  document.getElementById("_tishi").innerHTML="<font color='green' >用户名可用！</font>";
      					}else if(obj[0].status==1){
      					  document.getElementById("_tishi").innerHTML="<font color='red' >用户名已存在！</font>";
      					}
      				}
      			}
      			xmlhttp.open("POST","/TPM/JudgeAccountServlet.servlet",true);
      			xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
      			xmlhttp.send("phoneNum="+$("#phoneNum").val()
      			);
      			return false;
      		}
$(function(){
	$("#login_submit").click(function(){
		  if($("#phoneNum").val()==""|| $(".mm").val()==""){
			  alert("用户名或密码为空！");
		  }
		  else{
			    $.ajax({
			                        type:"POST",
			                        url: '/TPM/StuLoginServlet.servlet',
			                        
			                        data:{
			                        	"status":0,
			                             "phoneNum":$("#phoneNum").val(),
			                             "dengluming" : $("input[name='dengluming']:checked").val(),
			                             "pwd":$(".mm").val()},
			                        dataType:'json',
			                        cache:false,
			                        async:true,
			                        success: function(data) {
			                        	 
			                        	if(data[0].status==0){
			                        		window.location.href="/TPM/index.jsp";
			                                alert("登陆成功");
			                        	}
			                        	else{
			                        		alert("用户名密码不匹配！")
			                        	}
			                        	
			                        },
			                        error: function() {
			                            alert("error！")
			
			                        }
			                    });
		  }
	  })
})
	
