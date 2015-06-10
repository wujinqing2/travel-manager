package net.rytong.admin.common.util;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 数据校验工具类
 * 
 * @author wu.jinqing
 * @date 2015年4月30日
 */
public class Assert {
	private static final String PHONE_NUMBER_REGEX = "^1[0-9]{10}$";
//	private static final String EMAIL_REGEX = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z_]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	private static final String EMAIL_REGEX = "^[0-9a-zA-Z][0-9a-zA-Z_]+@[0-9a-zA-Z]+(\\.[0-9a-zA-Z]+)+$";
	
	private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
	
	/**
	 * <pre>
	 * 判断字符串arg是否不为空，如果arg == null || arg.trim().length() == 0返回false，否则返回true
	 * <pre>
	 * 
	 * @param arg
	 * @return
	 */
	public static boolean isNotEmpty(String arg)
	{
		return (arg == null || arg.trim().length() == 0) ? false : true;
	}
	
	/**
	 * <pre>
	 * 判断集合是否不为空，如果c == null || c.size() == 0返回false，否则返回true
	 * <pre>
	 * 
	 * @param c
	 * @return
	 */
	public static <T> boolean isNotEmpty(Collection<T> c)
	{
		return (c == null || c.size() == 0) ? false : true;
	}
	
	/**
	 * <pre>
	 * 判断指定数组是否不为空，如果c == null || c.length == 0返回false，否则返回true
	 * <pre>
	 * 
	 * @param c
	 * @return
	 */
	public static <T> boolean isNotEmpty(T[] c)
	{
		return (c == null || c.length == 0) ? false : true;
	}
	
	/**
	 * <pre>
	 * 判断集合是否不为空，如果c == null || c.size() == 0返回false，否则返回true
	 * <pre>
	 * 
	 * @param m
	 * @return
	 */
	public static <K, V> boolean isNotEmpty(Map<K, V> m)
	{
		return (m == null || m.size() == 0) ? false : true;
	}
	
	/**
	 * <pre>
	 *  判断arg是否不为null, 如果arg == null返回false，否则返回true
	 * <pre>
	 * 
	 * @param arg
	 * @return
	 */
	public static <T> boolean isNotNull(T arg)
	{
		return arg == null ? false : true;
	}
	
	/**
	 * <pre>
	 * 去除字符串arg两端的空格
	 * <pre>
	 * 
	 * @param arg
	 * @return  arg != null ? arg.trim() : null
	 */
	public static String trim(String arg)
	{
		return arg != null ? arg.trim() : null;
	}
	
	/**
	 * <pre>
	 * 去除字符串arg两端的空格
	 * <pre>
	 * 
	 * @param arg
	 * @return  arg != null ? arg.trim() : ""
	 */
	public static String trimToEmpty(String arg)
	{
		return arg != null ? arg.trim() : "";
	}
	
	/**
	 * <pre>
	 *  手机号验证
	 * <pre>
	 * 
	 * @param arg
	 * @return
	 */
	public static boolean isPhoneNumber(String arg)
	{
		return PHONE_NUMBER_PATTERN.matcher(trimToEmpty(arg)).matches();
	}
	
	/**
	 * <pre>
	 *  邮箱验证
	 * <pre>
	 * 
	 * @param arg
	 * @return
	 */
	public static boolean isEmail(String arg)
	{
		return EMAIL_PATTERN.matcher(trimToEmpty(arg)).matches();
	}
	
	public static void main(String[] args) {
//		System.out.println(isEmail("aa_aa@ss.com.cn" ));
		System.out.println(isEmail("1231_23@s.qqcom" ));
	}
}
