package bz.sunlight.web.crypt;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.StopWatch;

import bz.sunlight.web.crypt.AESCryptoCoder;
import bz.sunlight.web.crypt.AbstractCryptoCoder;

import com.google.common.io.BaseEncoding;

public class AESCryptoCoderTest {
	
	public static AbstractCryptoCoder abstractCryptoCoder = null;
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
		abstractCryptoCoder = new AESCryptoCoder();
    }	
	
	@Test
	public final void testByte() throws Exception
	{
		String inputStr = "{userName:13764072031,password:123456}";
		byte[] inputData = inputStr.getBytes();
		System.out.println("原文:\t"+inputStr);
		//初始化密钥
		byte[] key = abstractCryptoCoder.initKey();
		
		System.out.println("密钥:\t"+Base64.encodeBase64String(key));
		//加密
		StopWatch sw = new StopWatch();
		sw.start("testByte-加密");		
		inputData = abstractCryptoCoder.encrypt(inputData, key);
		sw.stop();
		System.out.println("加密后:\t"+Base64.encodeBase64String(inputData));
		//解密
		sw.start("testByte-解密");
		byte[] outputData = abstractCryptoCoder.decrypt(inputData, key);
		sw.stop();
		String outputStr = new String(outputData);
		System.out.println("解密后:\t"+outputStr);
		//校验
		Assert.assertEquals(inputStr, outputStr);
		System.out.println(sw.prettyPrint());
	}
	
	@Test
	public final void testStr() throws Exception
	{
		String inputStr = "{userName:13764072031,password:123456}";
		byte[] inputData = null;
		System.out.println("原文:\t"+inputStr);
		//初始化密钥
		byte[] key = abstractCryptoCoder.initKey();
		String key_ = "ImsIf93fgjgmyinhe8K7gg==";
		System.out.println("密钥:\t"+key_);
//		System.out.println("密钥文本:\t"+BaseEncoding.base64().encode(key));
		//加密
		StopWatch sw = new StopWatch();
		sw.start("testStr-加密");
		inputData = abstractCryptoCoder.encrypt(inputStr.getBytes(), BaseEncoding.base64().decode(key_));//Base64.decodeBase64
		sw.stop();
		sw.start("testStr-加密后");
		System.out.println("加密后:\t"+BaseEncoding.base64().encode(inputData));
		sw.stop();
		//解密
		sw.start("testStr-解密");
		byte[] outputData = abstractCryptoCoder.decrypt(BaseEncoding.base64().decode("mBt3CsKnTYjIYvZjtxXGUZ+EVwS0Q/l6ahdPW0s5eoWnSUPGldeK8630IeUzINJz"),BaseEncoding.base64().decode(key_));
		sw.stop();
		String outputStr = new String(outputData);
		System.out.println("解密后:\t"+outputStr);
		//校验
		Assert.assertEquals(inputStr, outputStr);
		
		System.out.println(sw.prettyPrint());
	}	
	
	@Test
	public final void testWithoutKey() throws Exception
	{
		String inputStr = "{userName:13764072031,password:123456}";
		byte[] inputData = null;
		System.out.println("原文:\t"+inputStr);
//		byte[] key = AbstractCryptoCoder.initKey();
		String key_ = "ImsIf93fgjgmyinhe8K7gg==";
		//加密
		StopWatch sww = new StopWatch();
		sww.start("testWithoutKey-加密");		
		inputData = abstractCryptoCoder.encrypt(inputStr.getBytes());
		sww.stop();
		System.out.println("加密后:\t"+BaseEncoding.base64().encode(inputData));
		//解密
		sww.start("testWithoutKey-解密");
		byte[] outputData = abstractCryptoCoder.decrypt(BaseEncoding.base64().decode("mBt3CsKnTYjIYvZjtxXGUZ+EVwS0Q/l6ahdPW0s5eoWnSUPGldeK8630IeUzINJz"));
		sww.stop();
		String outputStr = new String(outputData);
		System.out.println("解密后:\t"+outputStr);
		//校验
		Assert.assertEquals(inputStr, outputStr);
		System.out.println(sww.prettyPrint());
	}	
}
