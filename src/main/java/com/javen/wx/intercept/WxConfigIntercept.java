package com.javen.wx.intercept;

import com.eova.aop.AopContext;

public class WxConfigIntercept extends BaseMetaObjectIntercept{

	@Override
	public void queryBefore(AopContext ac) throws Exception {
		int id = ac.user.getInt("id");
		int rId = ac.user.role.getInt("id");
		
		if (rId > 2) {
			ac.condition = " and uid = " + id;
		}
		
		super.queryBefore(ac);
	}
	@Override
	public String addBefore(AopContext ac) throws Exception {
		ac.record.set("rmid", String.valueOf(System.currentTimeMillis()));
		ac.record.set("uid", ac.user.getInt("id"));
		return super.addBefore(ac);
	}
	
}
