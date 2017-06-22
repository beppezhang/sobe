package bz.sunlight.web.crypt;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.StopWatch;

import com.google.common.io.BaseEncoding;

public class CryptoCoderTest {
	
	public static String inputStr = "{userName:13764072031,password:123456}";
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
		inputStr = "{userName:13764072031,password:123456}";
		CryptoCoder.getInstance().encrypt(inputStr);
    }	
	
	@Test
	public void test()
	{
		StopWatch sw = new StopWatch();
		sw.start("加密");
		String cryp = CryptoCoder.getInstance().encrypt(inputStr);
		sw.stop();
		System.out.println("原文\t"+inputStr);
		System.out.println("加密后\t"+cryp);
		sw.start("解密");
		String outputStr = CryptoCoder.getInstance().decrypt(BaseEncoding.base64().decode(cryp));
		sw.stop();
		System.out.println("解密后\t"+outputStr);
		Assert.assertEquals(inputStr, outputStr);
		System.out.println(sw.prettyPrint());
	}
}

