package net.rytong.admin.sys.util;

import java.util.HashMap;
import java.util.Map;

public class CacheUtil {
	/**
	 * 用户登录信息缓存
	 * key: USER_ID 用户ID
	 * value: SECURITY_TOKEN 安全密钥
	 */
	public static final Map<Long, String> userLoginInfoMap = new HashMap<Long, String>();
}
