package com.javen.utils;

import java.util.HashMap;
import java.util.Map;

public class UserAgentUtil {
	
	public static Map<String,Object> getClientInfo(String userAgent){
		System.out.println(userAgent);
		Map<String, Object> clientMap = new HashMap<String,Object>();
		clientMap.put("ie", userAgent.indexOf("Trident") > -1);//IE内核
		clientMap.put("opera", userAgent.indexOf("Presto") > -1);//opera内核
		clientMap.put("goole", userAgent.indexOf("AppleWebKit") > -1); //苹果、谷歌内核
		clientMap.put("firefox", userAgent.indexOf("Gecko") > -1 && userAgent.indexOf("KHTML") == -1);//火狐内核
		clientMap.put("mobile", userAgent.indexOf("AppleWebKit") > -1 && userAgent.indexOf("Mobile") > -1);//是否为移动终端
		clientMap.put("ios", userAgent.indexOf("Mac OS X") > -1);//ios终端
		clientMap.put("android", userAgent.indexOf("Android") > -1 || userAgent.indexOf("Adr") > -1);//android终端
		clientMap.put("iPhone", userAgent.indexOf("iPhone") > -1);//是否为iPhone或者QQHD浏览器
		clientMap.put("iPad", userAgent.indexOf("iPad") > -1);//是否iPad
		//clientMap.put("webApp", userAgent.indexOf("Safari") == -1);//是否web应该程序，没有头部与底部
		clientMap.put("weixin", userAgent.indexOf("MicroMessenger") > -1); //是否微信 （2015-01-22新增）
		clientMap.put("qq", userAgent.indexOf("QQ") > -1);//是否QQ
		return clientMap;
	}
}
