package com.javen.common;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.javen.controller.WeixinMsgController;
import com.javen.ext.plugin.log.Slf4jLogFactory;
import com.javen.model._MappingKit;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.druid.IDruidStatViewAuth;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.jfinal.weixin.sdk.api.ApiConfigKit;

/**
 * @author Javen
 */
public class APPConfig extends JFinalConfig {
	static Log log = Log.getLog(APPConfig.class);

	/**
	 * 如果生产环境配置文件存在，则优先加载该配置，否则加载开发环境配置文件
	 * 
	 * @param pro
	 *            生产环境配置文件
	 * @param dev
	 *            开发环境配置文件
	 */
	public void loadProp(String pro, String dev) {
		try {
			PropKit.use(pro);
		} catch (Exception e) {
			PropKit.use(dev);
		}
	}

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		loadProp("config_pro.properties", "config.properties");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setEncoding("utf-8");
		me.setViewType(ViewType.JSP);
		// 修改文件上传的默认限制
		me.setMaxPostSize(1024 * 1024 * 200);
		// 设置上传文件保存的路径
		me.setBaseUploadPath(PathKit.getWebRootPath() + File.separator + "upload");
		// 设置Slf4日志
		me.setLogFactory(new Slf4jLogFactory());
		// ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
		ApiConfigKit.setDevMode(me.getDevMode());

	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/msg", WeixinMsgController.class);
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置ActiveRecord插件
		DruidPlugin druidPlugin = createDruidPlugin();
		me.add(druidPlugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		_MappingKit.mapping(arp);
		arp.setShowSql(PropKit.getBoolean("devMode", false));
		me.add(arp);

		// ehcahce插件配置
		me.add(new EhCachePlugin());
	}

	public static DruidPlugin createDruidPlugin() {
		String jdbcUrl = PropKit.get("jdbcUrl");
		String user = PropKit.get("user");
		String password = PropKit.get("password");
		log.error(jdbcUrl + " " + user + " " + password);
		// 配置druid数据连接池插件
		DruidPlugin dp = new DruidPlugin(jdbcUrl, user, password);
		// 配置druid监控
		dp.addFilter(new StatFilter());
		WallFilter wall = new WallFilter();
		wall.setDbType("mysql");
		dp.addFilter(wall);
		return dp;
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {

	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		// Druid监控
		DruidStatViewHandler dvh = new DruidStatViewHandler("/druid", new IDruidStatViewAuth() {

			@Override
			public boolean isPermitted(HttpServletRequest request) {
				return true;
			}
		});
		me.add(dvh);
	}

	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目 运行此 main
	 * 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 8080, "/", 5);// 启动配置项
	}

	@Override
	public void configEngine(Engine arg0) {
		
	}

}
