package bz.sunlight.web.crypt;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.google.common.io.BaseEncoding;

public abstract class AbstractCryptoCoder {
    /** 
     * 密钥算法 
     * */  
//    public String KEY_ALGORITHM="AES";
    protected String KEY_ALGORITHM=null;  
      
    /** 
     * 加密/解密算法/工作模式/填充方式 
     * Java 6 支持 PKCS5Padding 填充方式
     * Bouncy Castle 支持PKCS7Padding 填充方式
     * */  
//    public String CIPHER_ALGORITHM="AES/ECB/PKCS5Padding";
    protected String CIPHER_ALGORITHM=null;
    
    // 密钥明文
//    public final String KEY_STR = "ImsIf93fgjgmyinhe8K7gg==";
    protected String KEY_STR = null;
    
    /**
     * 密钥长度
     */
    protected int KEY_SIZE=128;
    
    public AbstractCryptoCoder() throws Exception
    {
    	initKey();
    }
    
    public AbstractCryptoCoder(String key_algorithm,String cipher_algorithm,int key_size,String key_str) throws Exception
    {
    	this.KEY_ALGORITHM = key_algorithm;
    	this.CIPHER_ALGORITHM = cipher_algorithm;
    	this.KEY_SIZE = key_size;
    	this.KEY_STR = key_str;
    	initKey();
    }    
    /**
     * 转换密钥
     * @param key 二进制密钥
     * @return Key 密钥
     * @throws Exception
     */
    private Key toKey(byte[] key) throws Exception
    {
    	//实例化密钥材料
    	SecretKey secretKey = new SecretKeySpec(key,0,key.length,KEY_ALGORITHM);
    	return secretKey;
    }
    
//    /**
//     * 转换密钥
//     * @param key Base64 编码后的密钥文本
//     * @return Key 密钥
//     * @throws Exception
//     */
//    private static Key toKey(String key) throws Exception
//    {
//    	//实例化密钥材料
//    	SecretKey secretKey = new SecretKeySpec(key.getBytes(),KEY_ALGORITHM);
//    	return secretKey;
//    }
    
    /**
     * 解密
     * @param btye[] data 待解密数据
     * @param btye[] key 密钥
     * @return byte[] 解密后数据
     * @throws Exception
     */
    public byte[] decrypt(byte[] data,byte[] key) throws Exception
    {
    	//还原密钥
    	Key k = toKey(key);
    	/**
    	 * 实例化
    	 * 使用PKCS7Padding填充方式,按如下方式实现
    	 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
    	 */
    	Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
    	// 初始化 设置为 解密模式
    	cipher.init(Cipher.DECRYPT_MODE, k);
    	// 执行操作
    	return cipher.doFinal(data);
    }
    
    /**
     * 重载 
     * 供子类实现
     * 解密 
     * @param btye[] data 待解密数据
     * @return byte[] 解密后数据
     * @throws Exception
     */
//    public byte[] decrypt(byte[] data) throws Exception
//    {
//    	return decrypt(data,BaseEncoding.base64().decode(KEY_STR));
//    }    
    public abstract byte[] decrypt(byte[] data) throws Exception;
    
    /**
     * 加密
     * @param byte[] data 待加密数据
     * @param byte[] key 密钥
     * @return byte[] 加密后数据
     * @throws Exception
     */
    public byte[] encrypt(byte[] data,byte[] key) throws Exception
    {
    	//还原密钥
    	Key k = toKey(key);
    	/**
    	 * 实例化
    	 * 使用PKCS7Padding填充方式,按如下方式实现
    	 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
    	 */
    	Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
    	// 初始化 设置为 加密模式
    	cipher.init(Cipher.ENCRYPT_MODE, k);
    	// 执行操作
    	return cipher.doFinal(data);    	
    }
    
    /**
     * 重载 
     * 供子类实现
     * 加密
     * @param byte[] data 待加密数据
     * @param byte[] key 密钥
     * @return byte[] 加密后数据
     * @throws Exception
     */
//    public byte[] encrypt(byte[] data) throws Exception
//    {
//    	return encrypt(data,BaseEncoding.base64().decode(KEY_STR));
//    	//return encrypt(data,Base64.decodeBase64(KEY_STR));
//    }    
    public abstract byte[] encrypt(byte[] data) throws Exception;
    
    /**
     * 生成密钥
     * @return byte[] 二进制密钥
     * @throws Exception
     */
    public byte[] initKey() throws Exception
    {
    	// 实例化
    	KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
    	// AES 要求密钥长度为 128位,192位或256位
    	if(KEY_SIZE>0)
    		kg.init(KEY_SIZE);
    	//生成私密密钥
    	SecretKey secretKey = kg.generateKey();
    	// 获得密钥的二进制编码形式
    	return secretKey.getEncoded();
    }
    /**
     * 生成密钥
     * @return String 密钥
     * @throws Exception
     */
    public String initKeyToString() throws Exception
    {
    	return BaseEncoding.base64().encode(initKey());
    }    
    
    public static void init()
    {
    	System.out.println("加快加密解密速度");
    }
    public static void main(String[] args) throws Exception {
		//System.out.println(AbstractCryptoCoder.initKeyToString());
    	System.out.println("2012PinganVitality075522628888ForShenZhenBelter075561869839".length());
    	System.out.println("2012PinganVitality075522628888ForShenZhenBelter075561869839".getBytes().length);
    	System.out.println(BaseEncoding.base64().encode("{userName:13764072031,password:123456}".getBytes()));
	}
}

