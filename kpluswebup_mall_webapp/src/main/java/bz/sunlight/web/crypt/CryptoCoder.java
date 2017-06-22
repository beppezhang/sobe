package bz.sunlight.web.crypt;

import org.apache.log4j.Logger;

import com.google.common.io.BaseEncoding;

/**
 * 基于AES,DESede等算法 加密与解密
 * 
 * @author sxc
 * 
 */
public final class CryptoCoder {
	
	private static Logger logger = Logger.getLogger(CryptoCoder.class);
	private static AbstractCryptoCoder aesCryptoCoder;
	private static AbstractCryptoCoder desedeCryptoCoder;
	
	public enum CryptoCoderType {
		AES, DESede,
	}

	private CryptoCoder() {
		try {
			aesCryptoCoder  = new AESCryptoCoder();
			desedeCryptoCoder = new DESedeCryptoCoder();			
		} catch (Exception e) {
			logger.error(e);
			logger.error("实例化CryptoCoder服务报错");
		}

	};
	
	private static class CryptoCoderHolder
	{
		private final static CryptoCoder INSTANCE = new CryptoCoder();
	}
	
	public static CryptoCoder getInstance()
	{
		return CryptoCoderHolder.INSTANCE;
	}

	public AbstractCryptoCoder getAesCryptoCoder() {
		return aesCryptoCoder;
	}

	public AbstractCryptoCoder getDesedeCryptoCoder() {
		return desedeCryptoCoder;
	}
	

    /**
     * 加密
     * @param String 待加密数据
     * @param CryptoCoderType 算法枚举
     * @return String 加密后数据
     * @throws Exception
     */	
	public String encrypt(byte[] data,CryptoCoderType cryptoCoderType)
	{
		String crypt = null;
		try {
	        switch(cryptoCoderType)
	        {
	            case AES:
	            	crypt = BaseEncoding.base64().encode(aesCryptoCoder.encrypt(data));
	                break;
	            case DESede:
	            	crypt = BaseEncoding.base64().encode(desedeCryptoCoder.encrypt(data));
	                break;
	            default:
	                break;
	        }		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("加密失败");
			return null;
		}
        return crypt;
	}		
	
    /**
     * 加密
     * 默认 AES
     * @param String 待加密数据
     * @return String 加密后数据
     * @throws Exception
     */	
	public String encrypt(byte[] data)
	{
		return encrypt(data,CryptoCoderType.AES);
	}	
    /**
     * 加密
     * 默认 AES
     * @param String data 待加密数据
     * @return String 加密后数据
     * @throws Exception
     */		
	public String encrypt(String data)
	{
		return encrypt(data.getBytes());
	}
	

    /**
     * 解密 
     * @param btye[] data 待解密数据
     * @param CryptoCoderType 算法枚举
     * @return String 解密后数据
     * @throws Exception
     */		
	public String decrypt(byte[] data,CryptoCoderType cryptoCoderType)
	{
		String crypt = null;
		try {
	        switch(cryptoCoderType)
	        {
	            case AES:
	            	crypt = new String(aesCryptoCoder.decrypt(data));
	                break;
	            case DESede:
	            	crypt = new String(desedeCryptoCoder.decrypt(data));
	                break;
	            default:
	                break;
	        }	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("加密失败");
			return null;			
		}
		return crypt;
	}	
	
    /**
     * 解密 
     * 默认 AES
     * @param btye[] data 待解密数据
     * @return String 解密后数据
     * @throws Exception
     */		
	public String decrypt(byte[] data)
	{
		return decrypt(data,CryptoCoderType.AES);
	}
    /**
     * 解密 
     * @param String data 待解密数据
     * @return String 解密后数据
     * @throws Exception
     */		
	public String decrypt(String data)
	{
		return decrypt(data.getBytes());
	}
	
}
