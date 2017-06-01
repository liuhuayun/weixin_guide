package com.javen.user;

import java.util.Date;

import com.eova.aop.AopContext;
import com.eova.aop.MetaObjectIntercept;

public class WxConfigIntercept extends MetaObjectIntercept{
	
	@Override
	public String deleteBefore(AopContext ac) throws Exception {
		// TODO Auto-generated method stub
		return super.deleteBefore(ac);
	}
	
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
		// TODO Auto-generated method stub
		ac.record.set("uid", ac.user.getInt("id"));
		ac.record.set("create_time", new Date());
		ac.record.set("update_time", new Date());
		return super.addBefore(ac);
	}
	@Override
	public String updateBefore(AopContext ac) throws Exception {
		ac.record.set("update_time", new Date());
		return super.updateBefore(ac);
	}
}
