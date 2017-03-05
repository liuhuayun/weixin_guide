package com.javen.common;

/**
 * 系统常量
 */
public class Consts {
	
	/**
	 * 缓存枚举
	 */
	public enum CacheName {
		session,
		halfHour,
		hour,
		oneDay,
		defaultCache;

		public String get() {
			return this.name();
		}
	}
}
