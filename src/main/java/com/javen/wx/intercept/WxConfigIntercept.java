package com.javen.wx.intercept;

import com.eova.aop.AopContext;

public class WxConfigIntercept extends BaseMetaObjectIntercept{
	
	
	@Override
	public void queryBefore(AopContext ac) throws Exception {
		int id = ac.user.getInt("id");
		int rId = ac.user.role.getInt("id");
		System.out.println("用户ID>"+id);
		System.out.println("角色ID>"+rId);
		
		if (rId <=2) {
			//查询所有
		}else {
			//根据用户ID查询
			ac.condition = " and uid = " + id;
		}
		
		super.queryBefore(ac);
	}
	
	@Override
	public String addBefore(AopContext ac) throws Exception {
		ac.record.set("uid", ac.user.getInt("id"));
		return super.addBefore(ac);
	}
	
}
