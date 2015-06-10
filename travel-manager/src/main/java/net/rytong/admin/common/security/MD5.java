package net.rytong.admin.common.security;

import java.security.MessageDigest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * MD5加密
 * 
 * @author wu.jinqing
 * @date 2014年12月11日
 */
public class MD5 {
	private static Logger logger = LogManager.getLogger(MD5.class);
	
	private static final String ALGORITHM = "MD5";
	private static final String ENCODING = "UTF-8";// 编码格式
	private static final String KEY = "1AE43D1C4D63F48E18F0EA2D0C26152E5E862097";// 密钥
	private static MessageDigest md;
	
	static
	{
		try
		{
			md = MessageDigest.getInstance(MD5.ALGORITHM);
		}catch(Exception e)
		{
			logger.info("MD5加密初始化出错, 错误信息: {}", e.getMessage());
		}
		
	}
	
	/**
	 * MD5加密
	 * 
	 * @param content
	 *            需要解密的内容
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String content){
		
		if (content == null || content.trim().length() == 0)
		{
			logger.info("encrypt content is empty.");
			return content;
		}

		try {
			return parseByte2HexStr(service(content + MD5.KEY));
		} catch (Exception e) {
			logger.info("MD5加密出错, 错误信息: {}", e.getMessage());
			return null;
		}
	}

	private static byte[] service(String content) throws Exception{
		try
		{
			md.update(content.getBytes(MD5.ENCODING));
			byte[] bs = md.digest();
			md.reset();
	
			return bs;
		}catch(Exception e)
		{
			logger.info("MD5加密出错, 错误信息: {}", e.getMessage());
			throw e;
		}
	}

	/**
	 * 将二进制转换成十六进制
	 * 
	 * @param buf
	 * @return
	 */
	private static String parseByte2HexStr(byte[] buf) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
}
