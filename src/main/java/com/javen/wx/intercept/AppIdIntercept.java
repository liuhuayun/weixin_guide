package com.javen.wx.intercept;

import com.eova.aop.AopContext;

public class AppIdIntercept extends BaseMetaObjectIntercept{
	@Override
	public void queryBefore(AopContext ac) throws Exception {
		int id = ac.user.getInt("id");
		int rId = ac.user.role.getInt("id");
		
		if (rId > 2) {
			ac.condition = " and app_id in (select app_id from wx_config where uid = "+id+")";
		}
		
		super.queryBefore(ac);
	}
	
}
