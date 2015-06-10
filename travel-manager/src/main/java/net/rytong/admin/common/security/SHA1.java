package net.rytong.admin.common.security;

import java.security.MessageDigest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * SHA1加密
 * 
 * @author wu.jinqing
 * @date 2014年12月11日
 */
public class SHA1 {
	private static Logger logger = LogManager.getLogger(SHA1.class);
	
	private static final String ALGORITHM = "SHA-1";
	private static final String ENCODING = "UTF-8";// 编码格式
	private static final String KEY = "E32168757BA43AD6495798403325826A5ACFE56B";// 密钥
	private static MessageDigest md;
	
	static
	{
		try
		{
			md = MessageDigest.getInstance(SHA1.ALGORITHM);
		}catch(Exception e)
		{
			logger.info("SHA1加密初始化出错, 错误信息: {}", e.getMessage());
		}
		
	}
	/**
	 * SHA1加密
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
			return parseByte2HexStr(service(content + SHA1.KEY));
		} catch (Exception e) {
			logger.info("SHA1加密出错, 错误信息: {}", e.getMessage());
			return null;
		}
		
	}

	private static byte[] service(String content) throws Exception{
		try
		{
			md.update(content.getBytes(SHA1.ENCODING));
			byte[] bs = md.digest();
			md.reset();
	
			return bs;
		}catch(Exception e)
		{
			logger.info("SHA1加密出错, 错误信息: {}", e.getMessage());
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
	
	public static void main(String[] args) {
		String key = "${ceair&chinaebi&wujq}";
		
		for(int i = 0; i <= 189725; i++)
		{
			int r = (int)(Math.random() * 1000);
			
			if(String.valueOf(r).contains("3") || String.valueOf(r).contains("4") || String.valueOf(r).contains("7"))
				key = new StringBuilder(key).reverse().toString();
			
			key = SHA1.encrypt(key);
		}
		
		System.out.println(key);
	}
}
