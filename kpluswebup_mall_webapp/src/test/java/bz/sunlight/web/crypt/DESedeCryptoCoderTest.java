package bz.sunlight.web.crypt;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.StopWatch;

import bz.sunlight.web.crypt.AbstractCryptoCoder;
import bz.sunlight.web.crypt.DESedeCryptoCoder;

import com.google.common.io.BaseEncoding;

public class DESedeCryptoCoderTest {
	public static AbstractCryptoCoder abstractCryptoCoder = null;
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
		abstractCryptoCoder = new DESedeCryptoCoder();
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
	public final void testWithoutKey() throws Exception
	{
		String inputStr = "{userName:13764072031,password:123456}";
		byte[] inputData = null;
		System.out.println("原文:\t"+inputStr);
//		byte[] key = AbstractCryptoCoder.initKey();
		String key_ = "pOwgKiDIwSDIaCaD1W12/adRvGQ78eqG";
		//加密
		StopWatch sww = new StopWatch();
		sww.start("testWithoutKey-加密");		
		inputData = abstractCryptoCoder.encrypt(inputStr.getBytes());
		sww.stop();
		System.out.println("加密后:\t"+BaseEncoding.base64().encode(inputData));
		//解密
		sww.start("testWithoutKey-解密");
		byte[] outputData = abstractCryptoCoder.decrypt(BaseEncoding.base64().decode("OnwF6tPLAxe1wJFGy/6OaOSDTegglq4N/CiLemzvyfcm6TaVATlbFw=="));
		sww.stop();
		String outputStr = new String(outputData);
		System.out.println("解密后:\t"+outputStr);
		//校验
		Assert.assertEquals(inputStr, outputStr);
		System.out.println(sww.prettyPrint());
	}

}
