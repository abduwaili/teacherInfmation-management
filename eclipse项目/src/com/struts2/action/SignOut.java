package com.struts2.action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class SignOut extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String result;

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String execute() throws Exception {
		System.out.println("signOut");
		ActionContext context=ActionContext.getContext();
		context.getSession().remove("teacher");
		context.getSession().remove("student");
		context.getSession().remove("isTeacher");
		context.getSession().remove("isLogin");
		Map<String, Object>ret = new HashMap<String, Object>();
		ret.put("signout", "success");
		JSONObject jsonObject = JSONObject.fromObject(ret);
		result = jsonObject.toString();
		return SUCCESS;
	}

	
}
