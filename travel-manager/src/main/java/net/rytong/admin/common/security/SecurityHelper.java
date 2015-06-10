package net.rytong.admin.common.security;



public class SecurityHelper {
	private static String key1 = "3EF991E9C4C3202D52E1D2858A28552A";// MD5.encrypt("{wujq&chinaebi&ceair}")
	private static String key2 = "2B89F43CD6737D406FA8192F92CADD8BFF5F3AE3";// SHA1.encrypt("{wujq&chinaebi&ceair}")
	private static String key3 = "DE9902C7F572815ADC4D172534E0F5F48119BA40";// SHA1.encrypt("{key1&key2}")
	
	/**
	 * <pre>
	 * 生成加密字符串(动态的, 相同的内容每次加密后的密钥都不一样)
	 * <pre>
	 * 
	 * @param content
	 * @return token
	 */
	public static String generateToken(String content)
	{
		return SHA1.encrypt(MD5.encrypt(content + SHA1.encrypt(content + key1 + System.currentTimeMillis()) + key2 + System.currentTimeMillis()) + key3 + System.currentTimeMillis());
	}
	
	/**
	 * <pre>
	 * 生成加密字符串(固定的, 相同的内容每次加密后的密钥都相同)
	 * <pre>
	 * 
	 * @param content
	 * @return token
	 */
	public static String encrypt(String content)
	{
		return MD5.encrypt(SHA1.encrypt(content + MD5.encrypt(content + key1) + key2) + key3);
	}
	
}
