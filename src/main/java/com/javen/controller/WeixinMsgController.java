
package com.javen.controller;

import com.javen.model.Config;
import com.javen.model.Keyword;
import com.javen.model.Subscription;
import com.jfinal.kit.JsonKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.jfinal.MsgControllerAdapter;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;

/**
 * 
 * @author Javen
 * 将此 DemoController 在YourJFinalConfig 中注册路由，
 * 并设置好weixin开发者中心的 URL 与 token ，使 URL 指向该
 * DemoController 继承自父类 WeixinController 的 index
 * 方法即可直接运行看效果，在此基础之上修改相关的方法即可进行实际项目开发
 */
public class WeixinMsgController extends MsgControllerAdapter{
	String uuid ;
	int configId ;
	String helpStr;
	/**
	 * 如果要支持多公众账号，只需要在此返回各个公众号对应的  ApiConfig 对象即可
	 * 可以通过在请求 url 中挂参数来动态从数据库中获取 ApiConfig 属性值
	 */
	public ApiConfig getApiConfig() {
	    uuid = getPara("uuid");
		Config wc = Config.dao.findByUUID(uuid);
		ApiConfig ac = new ApiConfig();
		configId=wc.getId();
		// 配置微信 API 相关常量
		ac.setToken(wc.getToken());
		ac.setAppId(wc.getAppId());
		ac.setAppSecret(wc.getAppSecret());

		/**
		 *  是否对消息进行加密，对应于微信平台的消息加解密方式：
		 *  1：true进行加密且必须配置 encodingAesKey
		 *  2：false采用明文模式，同时也支持混合模式
		 */
		ac.setEncryptMessage(wc.getEncryptMessage());
		ac.setEncodingAesKey(wc.getEncodingAesKey());
		
		return ac;
	}

	/**
	 * 实现父类抽方法，处理文本消息
	 * 本例子中根据消息中的不同文本内容分别做出了不同的响应，同时也是为了测试 jfinal weixin sdk的基本功能：
	 *     本方法仅测试了 OutTextMsg、OutNewsMsg、OutMusicMsg 三种类型的OutMsg，
	 *     其它类型的消息会在随后的方法中进行测试
	 */
	protected void processInTextMsg(InTextMsg inTextMsg) {
		//获取关注对象的配置
		Subscription ws=Subscription.dao.findConfigId(configId);
		if (ws!=null) {
			helpStr=ws.getStr("text");
		}
		
		String msgContent = inTextMsg.getContent().trim();
		// 帮助提示
		if ("help".equalsIgnoreCase(msgContent) || "帮助".equals(msgContent) || "?".equalsIgnoreCase(msgContent) || "？".equalsIgnoreCase(msgContent)) {
			OutTextMsg outMsg = new OutTextMsg(inTextMsg);
			outMsg.setContent(helpStr);
			render(outMsg);
		}else {
			//关键字回复
			Keyword wkw=Keyword.dao.findKeyWord(configId, msgContent);
			if (wkw!=null) {
				//类型（1、文本 2、多图文 等）
				int type=wkw.getReplyType();
				if (type==1) {
					String content=wkw.getContent();
					renderOutTextMsg(content);
				}else if (type==2){
					
				}
			}else {
				renderOutTextMsg("\t文本消息已成功接收，内容为： " + inTextMsg.getContent() + "\n\n" + helpStr);
			}
			
		}
	}

	@Override
	protected void processInFollowEvent(InFollowEvent inFollowEvent) {

		if (InFollowEvent.EVENT_INFOLLOW_SUBSCRIBE.equals(inFollowEvent.getEvent())){
			OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
			outMsg.setContent("感谢你的关注");
			render(outMsg);
		}
		if (InFollowEvent.EVENT_INFOLLOW_UNSUBSCRIBE.equals(inFollowEvent.getEvent())){
			System.out.println("取消关注");
		}

		
	}

	@Override
	protected void processInMenuEvent(InMenuEvent inMenuEvent) {
		System.out.println("。。。。。。");
		OutTextMsg outMsg = new OutTextMsg(inMenuEvent);
		outMsg.setContent("菜单事件内容是：" + inMenuEvent.getEventKey());
		render(outMsg);
	}
}






