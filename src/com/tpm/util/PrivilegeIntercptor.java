package com.tpm.util;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PrivilegeIntercptor extends AbstractInterceptor{
	private static final long serialVersionUID=1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		Object StuID=actionContext.getSession().get("StuID");		
		Object user = actionContext.getSession().get("user");
		if(user!=null||StuID!=null){
			return invocation.invoke();
		}else{
			actionContext.put("error", "您还未登录，请先登录！");
			return Action.LOGIN;
		}
	}
	
}
