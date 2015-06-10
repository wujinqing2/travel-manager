package net.rytong.admin.common.security;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * AES加密、解密
 * 
 * @author wu.jinqing
 * @date 2014年12月11日
 */
public class AES {
	private static Logger logger = LogManager.getLogger(AES.class);
	
	private static final String ALGORITHM = "AES";
	private static final String ENCODING = "UTF-8";// 编码格式
	private static final int KEY_SIZE = 128;// 固定值: 128
	private static final String KEY = "D82B21A9A292DE5516E5FB4DF369EB2E7D177496";// 密钥(为保证历史数据能正确解密，该密钥在确定之后千万不要修改)

	/**
	 * AES加密
	 * 
	 * @param content 需要解密的内容
	 * @return
	 * @throws Exception 
	 */
	public static String encrypt(String content) 
	{
		if(content == null || content.trim().length() == 0)
		{
			logger.info("content is empty.");
			return content;
		}
		
		try {
			return parseByte2HexStr(service(content.getBytes(AES.ENCODING), Cipher.ENCRYPT_MODE));
		} catch (Exception e) {
			logger.info("AES加密出错, 错误信息: {}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * AES解密
	 * 
	 * @param content 密文
	 * @return
	 */
	public static String decrypt(String content)
	{
		if(content == null || content.trim().length() == 0)
		{
			logger.info("content is empty.");
			return content;
		}
		
		try {
			return new String(service(parseHexStr2Byte(content), Cipher.DECRYPT_MODE), AES.ENCODING);
		} catch (Exception e) {
			logger.info("AES解密出错, 错误信息: {}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 加密、解密
	 * 
	 * @param content
	 * @param mode Cipher.DECRYPT_MODE | Cipher.ENCRYPT_MODE
	 * @return
	 */
	private static byte[] service(byte[] content, int mode) throws Exception{
		try {
			KeyGenerator kgen = KeyGenerator
					.getInstance(AES.ALGORITHM);
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
			secureRandom.setSeed(AES.KEY.getBytes());
			kgen.init(AES.KEY_SIZE, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat,
					AES.ALGORITHM);
			Cipher cipher = Cipher.getInstance(AES.ALGORITHM);// 创建密码器
			cipher.init(mode, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 解密
		} catch (Exception e) {
			logger.info("AES加密或解密出错, 错误信息: {}", e.getMessage());
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

	/**
	 * 将十六进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
