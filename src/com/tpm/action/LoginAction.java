package com.tpm.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.User;
import com.tpm.service.UserService;

public class LoginAction extends ActionSupport implements ModelDriven<User>{
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	User user = new User();
	public User getModel() {
		return user;
	}
	public String login(){//登录
		String result = userService.login(user);
		if(result.equals("login")){
			return "loginin";
		}else{return "loginsuccess";}
	}
	public String outlogin() throws Exception{//退出

		ActionContext ac = ActionContext.getContext();
		Map session = ac.getSession();
		session.clear();
		return "outlogin";
	}
}
