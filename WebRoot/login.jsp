<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtm1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录培养计划管理系统</title>
<link href="images/css/login.css" rel="stylesheet" type="text/css" />
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
</head>
<SCRIPT language="JavaScript">  
			function submit(){
				if($("input[name='dengluming']:checked").val()=="tea"){
					var newUrl = '${pageContext.request.contextPath}/login_login.action';    //设置新提交地址
			        $("#login-form").attr('action',newUrl);    //通过jquery为action属性赋值
					return true;
				}else{
					
					if($("#username").val().length!=11){
						alert("学生账号应该为11位");
					}
					else{
						$.ajax({
		                    type:"POST",
		                    url: '/TPM/StuLoginServlet.servlet',
		                    data:{
		                    	"status":0,
		                         "phoneNum":$("#username").val(),
		                         "dengluming" : $("input[name='dengluming']:checked").val(),
		                         "pwd":$("#password").val()},
		                    dataType:'json',
		                    cache:false,
		                    async:true,
		                    success: function(data) {
		                    	if(data[0].status==0){
		                    		window.location.href="/TPM/index.jsp";
		                            //alert("登陆成功");
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
					return false;	
				}
			}		    
function myexplorer(){  
var explorer = window.navigator.userAgent;  
var bower=explorer.match(/MicroMessenger/i);  
if (explorer.indexOf("QQBrowser")>=0 || explorer.indexOf("QQ")>=0){  
    return myexplorer="腾讯QQ";  
}else if(explorer.indexOf("Safari")>=0 && explorer.indexOf("MetaSr")>=0){  
    return myexplorer="搜狗";  
}else if( bower&& bower.toString().toLowerCase()== 'micromessenger'){  
    return myexplorer="微信";  
}else if (!!window.ActiveXObject || "ActiveXObject" in window){//IE  
    if (!window.XMLHttpRequest){  
    alert('   为了保证你可以正常使用本系统，请使用360浏览器极速模式，火狐浏览器或谷歌浏览器进行访问，谢谢！');
        return myexplorer="IE6";  
    }else if (window.XMLHttpRequest && !document.documentMode){ 
    alert('   为了保证你可以正常使用本系统，请使用360浏览器极速模式，火狐浏览器或谷歌浏览器进行访问，谢谢！'); 
        return myexplorer="IE7";  
    }else if (!-[1,] && document.documentMode && !("msDoNotTrack" in window.navigator)){
    alert('   为了保证你可以正常使用本系统，请使用360浏览器极速模式，火狐浏览器或谷歌浏览器进行访问，谢谢！');  
        return myexplorer="IE8";  
    }else{//IE9 10 11  
        var hasStrictMode=(function(){ 
        alert('   为了保证你可以正常使用本系统，请使用360浏览器极速模式，火狐浏览器或谷歌浏览器进行访问，谢谢！'); 
            return this===undefined;  
        }());   
    if (hasStrictMode){  
        if (!!window.attachEvent){  
        alert('   为了保证你可以正常使用本系统，请使用360浏览器极速模式，火狐浏览器或谷歌浏览器进行访问，谢谢！');
            return myexplorer="IE10";  
        }else{  
        alert('   为了保证你可以正常使用本系统，请使用360浏览器极速模式，火狐浏览器或谷歌浏览器进行访问，谢谢！');
            return myexplorer="IE11";  
        }  
    }else{  
    alert('   为了保证你可以正常使用本系统，请使用360浏览器极速模式，火狐浏览器或谷歌浏览器进行访问，谢谢！');
        return myexplorer="IE9";  
    }  
    }  
}else{//非IE  
    if (explorer.indexOf("LBBROWSER") >= 0){  
        return myexplorer="猎豹";  
    }else if(explorer.indexOf("Firefox")>=0){  
        return myexplorer="火狐";  
    }else if(explorer.indexOf("Maxthon")>=0){  
        return myexplorer="遨游";  
    }else if(explorer.indexOf("Chrome")>=0){ 
  //   alert('   为了保证你可以正常使用本系统，请使用360浏览器极速模式，火狐浏览器或谷歌浏览器进行访问，谢谢！');  
        return myexplorer="谷歌（或360伪装）";  
    }else if(explorer.indexOf("Opera")>=0){  
        return myexplorer="欧朋";  
    }else if (explorer.indexOf("TheWorld") >= 0){  
        return myexplorer="世界之窗";  
    }else if(explorer.indexOf("Safari")>=0){  
        return myexplorer="苹果";  
    }else{  
        return myexplorer="其他";  
    }  
}  
}</script>
<!-- <BR>  
<SCRIPT>document.write("客户端浏览器是："+myexplorer());</script>  -->
<body>
	<div
		style="width: 100%; height: 90px; background: url(images/bgg.png);">
		<div
			style="width: 272px; height: 80px; margin-top: 10px; background: url(images/ysulogo.png); float: left; margin-left: 5%;"></div>
		<div
			style="width: 300px; height: 80px; margin-top: 10px; background: url(images/logo.png); float: left;"></div>
		<div></div>
	</div>
	<div class="main-login" style="top: 35%">

		<div class="login-content">
			<h2>用户登录</h2>

			<form action="aabb"  method="post" id="login-form" name="login-form"
				onsubmit="return CheckForm()">
				<SCRIPT language="JavaScript">  
function CheckForm(){ 	
var explorer = window.navigator.userAgent;  
var bower=explorer.match(/MicroMessenger/i);  
if (explorer.indexOf("QQBrowser")>=0 || explorer.indexOf("QQ")>=0){  
    return submit(); 
}else if(explorer.indexOf("Safari")>=0 && explorer.indexOf("MetaSr")>=0){  
	return submit(); 
}else if( bower&& bower.toString().toLowerCase()== 'micromessenger'){  
	return submit();  
}else if (!!window.ActiveXObject || "ActiveXObject" in window){//IE  
    if (!window.XMLHttpRequest){
   alert('   为了保证您可以正常使用本系统，请使用360浏览器极速模式，火狐浏览器或谷歌浏览器进行访问，谢谢！');
        return false;  
    }else if (window.XMLHttpRequest && !document.documentMode){  
        return false;  
    }else if (!-[1,] && document.documentMode && !("msDoNotTrack" in window.navigator)){  
    alert('   为了保证您可以正常使用本系统，请使用360浏览器极速模式，火狐浏览器或谷歌浏览器进行访问，谢谢！');
         return false;  
    }else{//IE9 10 11  
        var hasStrictMode=(function(){ 
        alert('   为了保证您可以正常使用本系统，请使用360浏览器极速模式，火狐浏览器或谷歌浏览器进行访问，谢谢！'); 
            return false;  
        }());   
    if (hasStrictMode){  
        if (!!window.attachEvent){
        alert('   为了保证您可以正常使用本系统，请使用360浏览器极速模式，火狐浏览器或谷歌浏览器进行访问，谢谢！');  
            return false;  
        }else{  
        alert('   为了保证您可以正常使用本系统，请使用360浏览器极速模式，火狐浏览器或谷歌浏览器进行访问，谢谢！');
            return false;  
        }  
    }else{  
        return false;  
    }  
    }  
}else{//非IE  
	
    if (explorer.indexOf("LBBROWSER") >= 0){ 
    	return submit();
    }else if(explorer.indexOf("Firefox")>=0){  
    	return submit(); 
    }else if(explorer.indexOf("Maxthon")>=0){  
    	return submit(); 
    }else if(explorer.indexOf("Chrome")>=0){ 
    	return submit();
    }else if(explorer.indexOf("Opera")>=0){  
    	return submit();
    }else if (explorer.indexOf("TheWorld") >= 0){  
    	return submit(); 
    }else if(explorer.indexOf("Safari")>=0){ 
    	return submit(); 
    }else{  
    	return submit();  
    }  
}  
}</script>
				<div class="login-info">
					<span class="user">&nbsp;</span> <input name="tnum" id="username"
						type="text" class="login-input"
						onFocus="if(this.value=='请输入您的职工号'){this.value='';}"
						onblur="if(!this.value){this.value='请输入您的职工号';}" value="请输入您的职工号" />
				</div>
				<div class="login-info">
					<span class="pwd">&nbsp;</span> <input name="password"
						id="password" type="password" class="login-input"
						onFocus="if(this.value=='请输入您的密码'){this.value='';}"
						onblur="if(!this.value){this.value='请输入您的密码';}" value="请输入您的密码" />
				</div>
				<div class="login-out"
					style="width: 320px; height: 25px; margin-left: auto; margin-right: auto; background: #ffffff; margin-top: 5px;">
					<p style="font-size: 15px; color: red;">${error }</p>
				</div>
				<div style="margin-left: 38%;margin-bottom: 4%; margin-top: -3%;">
				<label style="margin-left:10%;"><input type="radio" name="dengluming" value="tea" checked/>教职工</label>
				<label style="margin-left:10%;"><input type="radio" name="dengluming" value="stu" />学生</label>
				<label style="margin-left:10%;"><input type="radio" name="dengluming" value="com"/>企业</label>
				</div>
				<div class="login-oper">
				<input name="" type="reset" value="重 置" class="login-reset" />
					<input name="" type="submit" value="登 录" class="login-btn" /> 
				</div>
			</form>
		</div>

	</div>
</body>

</html>
