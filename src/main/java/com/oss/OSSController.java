/**
 * Copyright (c) 2013-2016, Jieven. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.oss;

import java.util.Date;

import com.eova.common.Easy;
import com.eova.common.utils.EncryptUtil;
import com.eova.common.utils.xx;
import com.eova.config.EovaConfig;
import com.eova.config.EovaConst;
import com.eova.core.IndexController;
import com.eova.model.User;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.oss.model.UserInfo;


/**
 * 自定义 新增或重写 登录 注册 等各种默认系统业务！！！
 *
 * @author Jieven
 * @date 2016-05-11
 * http://www.eova.cn/qa/detail/358
 */
public class OSSController extends IndexController {
	
	
	@Override
	public void doLogin() {
		// 获取请求参数
		String loginId = getPara("loginId");//昵称
		String loginPwd = getPara("loginPwd");
		
		// 验证码验证
		boolean isCaptcha = xx.toBoolean(EovaConfig.getProps().get("isCaptcha"), true);
		if (isCaptcha && !super.validateCaptcha("captcha")) {
			setAttr("msg", "验证码错误，请重新输入！");
			toLogin();
			return;
		}

		// 根据登录名从自定义用户表中查询用户
		Record user = Db.findFirst("select * from wx_admin where nickname = ?",loginId);
		
		// 用户密码验证
		if (user == null) {
			setAttr("msg", "用户不存在");
			toLogin();
			return;
		}
		if (user.getInt("status") == 0) {
			setAttr("msg", "用户不可用");
			toLogin();
			return;
		}
		if (!user.getStr("login_pwd").equals(EncryptUtil.getSM32(loginPwd))) {
			setAttr("msg", "密码错误");
			keepPara("loginId");
			toLogin();
			return;
		}
		
		User sessionUser = new User();
		sessionUser.put("rid", user.getInt("rid"));
		sessionUser.put("login_id", loginId);
		sessionUser.put("id", user.getInt("id"));
		sessionUser.init();// 内部构建用户角色属性，根据rid
		
		try {
			// 由父类根据虚拟eova_user对象进行登录初始化
			// 也就是仍使用EOVA平台内建的权限管理
			loginInit(this, sessionUser);
		} catch (Exception e) {
			e.printStackTrace();
			setAttr("msg", e.getMessage());
			keepPara("loginId");
			toLogin();
			return;
		}

		// 同样关键，将虚拟eova_user对象放入会话，对该属性的使用者来说，没有任何影响，目的达到
		setSessionAttr(EovaConst.USER, sessionUser);

		// 重定向到首页
		redirect("/");
	}
	
	@Override
	public void updatePwd() {
		String oldPwd = getPara("oldPwd");
		String newPwd = getPara("newPwd");
		String confirm = getPara("confirm");

		if (xx.isOneEmpty(oldPwd, newPwd, confirm)) {
			renderJson(new Easy("三个密码都不能为空"));
			return;
		}

		// 新密码和确认密码是否一致
		if (!newPwd.equals(confirm)) {
			renderJson(new Easy("新密码两次输入不一致"));
			return;
		}

		// 当前用户
		User user = getSessionAttr(EovaConst.USER);
		int id = user.getInt("id");
		
		//查询自定义用户表中查询用户
		Record record = Db.findById("wx_admin", id);
		
		String pwd = record.getStr("login_pwd");
//		System.out.println("修改密码的用户ID>"+id);
//		System.out.println("修改密码的用户昵称>"+record.getStr("nickname"));
//		System.out.println("修改密码的用户密码>"+record.getStr("login_pwd"));

		// 旧密码是否正确
		if (!pwd.equals(EncryptUtil.getSM32(oldPwd))) {
			renderJson(new Easy("密码错误"));
			return;
		}

		// 修改密码
		record.set("login_pwd", EncryptUtil.getSM32(newPwd));
		record.set("update_time", new Date());
		Db.update("wx_admin", record);
		renderJson(new Easy());
	}

    @Override
    public void toIndex() {
        System.out.println("我是来自草原的狼，自由奔跑在EOVA上，想干嘛就干嘛！");
        render("/eova/index.html");
    }

    @Override
    protected void loginInit(Controller ctrl, User user) throws Exception {
        super.loginInit(ctrl, user);

        // 添加自定义业务信息到当前用户中
        UserInfo info = UserInfo.dao.findById(user.get("id"));
        if (info != null) {
            user.put("info", info);
            // 页面或表达式 调用用户信息 ${user.info.nickname}
        }

        // 还可以将相关信息放入session中
        // ctrl.setSessionAttr("UserInfo", info);
    }

    @Override
    public void toLogin() {
        // 新手部署错误引导
        int port = getRequest().getServerPort();
        String name = getRequest().getServerName();
        String project = getRequest().getServletContext().getContextPath();
        if (!project.equals("")) {
        	System.out.println("Eova不支持子项目(目录)方式访问,如需同时使用多个项目请使用不同的端口部署Web服务!");
        	String ctx = "http://" + name + ':' + port + project;
        	setAttr("ctx", ctx);
            render("/eova/520.html");
            return;
		}
        
        super.toLogin();
    }
    
 

}