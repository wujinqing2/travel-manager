package net.rytong.admin.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeHelper {
	/**格式: yyyyMMddHHmmss**/
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	/**格式: yyyyMMddHHmmssSSS**/
	public static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	/**格式: yyyyMMdd**/
	public static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
	/**格式: yyyy-MM-dd**/
	public static final SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
	/**格式: yyyy-MM-dd HH:mm:ss**/
	public static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * <pre>
	 * 获取当前时间, 格式: yyyyMMddHHmmss
	 * <pre>
	 * 
	 * @return
	 */
	public static Long getCurrentTime()
	{
		return Long.valueOf(sdf.format(Calendar.getInstance().getTime()));
	}
	
	/**
	 * <pre>
	 * 获取当前时间, 格式: yyyyMMdd
	 * <pre>
	 * 
	 * @return
	 */
	public static Long getCurrentDate()
	{
		return Long.valueOf(sdf3.format(Calendar.getInstance().getTime()));
	}
	
	/**
	 * <pre>
	 * 获取当前时间, 格式: yyyyMMddHHmmssSSS
	 * <pre>
	 * 
	 * @return
	 */
	public static Long getCurrentTime1()
	{
		return Long.valueOf(sdf1.format(Calendar.getInstance().getTime()));
	}
	
	/**
	 * <pre>
	 * 获取当前时间, 格式: yyyy-MM-dd HH:mm:ss
	 * <pre>
	 * 
	 * @return
	 */
	public static String getCurrentTime2()
	{
		return sdf2.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * <pre>
	 * 获取当前时间, 格式: yyyy-MM-dd
	 * <pre>
	 * 
	 * @return
	 */
	public static String getCurrentTime4()
	{
		return sdf4.format(Calendar.getInstance().getTime());
	}
}
