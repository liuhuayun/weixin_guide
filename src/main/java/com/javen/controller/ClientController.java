package com.javen.controller;

import java.util.Map;

import com.javen.utils.UserAgentUtil;
import com.jfinal.core.Controller;

public class ClientController extends Controller{
	
	public void index(){
		Map<String, Object> clientInfo = UserAgentUtil.getClientInfo(getHeader("User-Agent"));
		setAttr("clientInfo", clientInfo);
		render("client.jsp");
	}
}
