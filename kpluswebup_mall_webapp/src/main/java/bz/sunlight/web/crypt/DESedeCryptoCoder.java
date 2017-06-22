package bz.sunlight.web.crypt;

import com.google.common.io.BaseEncoding;

public class DESedeCryptoCoder extends AbstractCryptoCoder {

    /** 
     * 密钥算法 
     * */  
    public static String KEY_ALGORITHM="DESede";  
      
    /** 
     * 加密/解密算法/工作模式/填充方式 
     * Java 6 支持 PKCS5Padding 填充方式
     * Bouncy Castle 支持PKCS7Padding 填充方式
     * */  
    public static String CIPHER_ALGORITHM="DESede/ECB/PKCS5Padding";
    
    
    /**
     * 密钥长度
     * JDK6
     * AES 要求密钥长度为 128位,168位或256位
     * 默认 168位
     */
    public static int KEY_SIZE=168;
    
    /**
     * 密钥明文
     */
    public static final String KEY_STR = "pOwgKiDIwSDIaCaD1W12/adRvGQ78eqG";  	
	
	public DESedeCryptoCoder() throws Exception {
		super(KEY_ALGORITHM,CIPHER_ALGORITHM,KEY_SIZE,KEY_STR);
	}

    /**
     * 实现父类方法
     * 解密 
     * @param btye[] data 待解密数据
     * @return byte[] 解密后数据
     * @throws Exception
     */	
	@Override
	public byte[] decrypt(byte[] data) throws Exception {
		return decrypt(data,BaseEncoding.base64().decode(KEY_STR));
	}
	
    /**
     * 实现父类方法
     * 加密
     * @param byte[] data 待加密数据
     * @param byte[] key 密钥
     * @return byte[] 加密后数据
     * @throws Exception
     */		
	@Override
	public byte[] encrypt(byte[] data) throws Exception {
		return encrypt(data,BaseEncoding.base64().decode(KEY_STR));
	}

}
