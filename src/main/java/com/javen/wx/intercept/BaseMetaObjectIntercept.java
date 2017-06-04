package com.javen.wx.intercept;

import java.util.Date;

import com.eova.aop.AopContext;
import com.eova.aop.MetaObjectIntercept;

public class BaseMetaObjectIntercept extends MetaObjectIntercept {
	
	@Override
	public String updateBefore(AopContext ac) throws Exception {
		ac.record.set("update_time", new Date());
		return super.updateBefore(ac);
	}
	
	@Override
	public String addBefore(AopContext ac) throws Exception {
		ac.record.set("create_time", new Date());
		ac.record.set("update_time", new Date());
		return super.addBefore(ac);
	}
}
