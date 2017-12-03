package com.doctortrain.web;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.doctortrain.bean.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;


public class AuthInterceptor extends MethodFilterInterceptor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("session_user");
		log.info("- intercept - 监听器 - sessionId:"+ServletActionContext.getRequest().getRequestedSessionId());
		if(user==null){
			return Action.LOGIN;
		}
		return invocation.invoke();
	}

}
