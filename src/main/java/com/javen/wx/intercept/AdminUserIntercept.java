package com.javen.wx.intercept;

import com.eova.aop.AopContext;
import com.eova.common.Easy;
import com.eova.common.utils.EncryptUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class AdminUserIntercept extends BaseMetaObjectIntercept{
	
	@Override
	public void queryBefore(AopContext ac) throws Exception {
		int id = ac.user.getInt("id");
		int rId = ac.user.role.getInt("id");
				
		System.out.println("用户ID>"+id);
		System.out.println("角色ID>"+rId);
		
		if (rId <=2) {
			//查询所有app信息
		}else {
			//根据用户ID查询app信息
			ac.condition = " and id = " + id;
			System.out.println("根据用户ID查询 ....");
		}
		
		super.queryBefore(ac);
	}
	
	@Override
	public String addBefore(AopContext ac) throws Exception {
		//这里可以做手机以及邮箱的验证 为了方便只做昵称唯一的处理
		
//		String mobilephone = ac.record.getStr("mobilephone");
//		String email = ac.record.getStr("email");
//		System.out.println("添加的信息>"+mobilephone+" "+email+" "+nickname);
		String nickname = ac.record.getStr("nickname");
		
		Record user = Db.findFirst("select * from wx_admin where nickname=?",nickname);
		if (user !=null) {
			return Easy.error("昵称存在,禁止重复添加！");
		}
		
		ac.record.set("login_pwd", EncryptUtil.getSM32(ac.record.getStr("login_pwd")));
		return super.addBefore(ac);
	}
}
