package com.kpuswebup.comom.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;


public class NumToolsTest {
	
    private Number number = null;
    private String value = null;	
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
 		
    }
    @Before
    public void setUp() throws Exception
    {
    	number = 123456.87583;
    
    }
    
    @After
    public void tearDown() throws Exception
    {
    	
    } 
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    	
    }
    


    @Test
    public void testFormatTwoNumber()
    {
        value = "123456.88"; // 123456.87583
        Assert.assertTrue(NumTools.formatTwo(number).equals(value));
    }

    @Test
    public void testFormatFourNumber()
    {
        value = "123456.8758"; // 123456.87583
        Assert.assertTrue(NumTools.formatFour(number).equals(value));
    }

    @Test
    public void testFormatFourDouble()
    {
        double d = 123456.8758; // 123456.87583
        Assert.assertTrue(NumTools.formatFour(d).doubleValue() == d);
    }

    @Test
    public void testFormatTwoDouble()
    {
        double d = 123456.88; // 123456.87583
        Assert.assertTrue(NumTools.formatTwo(d).doubleValue() == d);
    }

    @Test
    public void testFormatFourFloat()
    {
        float f = 123456.8758f; // 123456.87583
        Assert.assertTrue(NumTools.formatFour(f).floatValue() == f);
    }

    @Test
    public void testFormatTwoFloat()
    {
        float f = 123456.88f; // 123456.87583
        Assert.assertTrue(NumTools.formatTwo(f).floatValue() == f);
    }

    @Test
    public void testFormatNumberInt()
    {
        value = "123456.876"; // 123456.87583
        Assert.assertTrue(NumTools.format(number, 3).equals(value));
    }

    @Test
    public void testFormatNumberString()
    {
        value = "123,456.875830"; // 123456.87583
        Assert.assertTrue(NumTools.format(number, "#,##0.000000").equals(value));
    }

    @Test
    public void testFormatCommaTwo()
    {
        value = "123,456.88"; // 123456.87583
        Assert.assertTrue(NumTools.formatCommaTwo(number).equals(value));
    }

    @Test
    public void testFormatCommaFour()
    {
        value = "123,456.8758"; // 123456.87583
        Assert.assertTrue(NumTools.formatCommaFour(number).equals(value));
    }

    @Test
    public void testFormatCurrency()
    {
        value = "￥123,456.88"; // 123456.87583
        Assert.assertTrue(NumTools.formatCurrency(number).equals(value));
    }

    @Test
    public void testParseCurrency()
    {
        value = "￥123,456.88";
        try {
			number = NumTools.parseCurrency(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Assert.assertTrue(NumTools.formatCurrency(number).equals(value));
    }

    @Test
    public void testFormatPercentage()
    {
        number = 0.87655;
        value = "87.66%";
        Assert.assertTrue(NumTools.formatPercentage(number).equals(value));
    }

    @Test
    public void testFormatPermillage()
    {
        number = 0.87655;
        value = "876.55‰";
        Assert.assertTrue(NumTools.formatPermillage(number).equals(value));
    }

    @Test
    public void testParsePercentPercentage()
    {
        value = "87.66%";
        number = NumTools.parsePercentPercentage(value);
        Assert.assertTrue(NumTools.formatPercentage(number).equals(value));
    }

    @Test
    public void testParsePercentPermillage()
    {
        value = "876.55‰";
        number = NumTools.parsePercentPermillage(value);
        Assert.assertTrue(NumTools.formatPermillage(number).equals(value));
    }

    public static void main(String[] args)
    {
        JUnitCore.runClasses(NumToolsTest.class);
    }    
}
