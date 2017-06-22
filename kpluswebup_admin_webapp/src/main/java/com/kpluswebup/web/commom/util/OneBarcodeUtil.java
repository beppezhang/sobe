package com.kpluswebup.web.commom.util;

import java.awt.image.BufferedImage;  
import java.io.File;
import java.io.FileOutputStream;  

import org.jbarcode.JBarcode;  
import org.jbarcode.encode.Code11Encoder;
import org.jbarcode.encode.Code128Encoder;
import org.jbarcode.paint.BaseLineTextPainter;  
import org.jbarcode.paint.WidthCodedPainter;  
import org.jbarcode.util.ImageUtil;  

import cn.emay.process.emaysms.util.GetRequest;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpuswebup.comom.util.DateUtil;
  
/** 
 * 2015-06-08
 * @author zhoulei
 */  
public class OneBarcodeUtil extends BaseController{  
  
    public void createCode(String str)  
  {  
    try  
    {  
    	JBarcode localJBarcode = new JBarcode(Code128Encoder.getInstance(),WidthCodedPainter.getInstance(),BaseLineTextPainter.getInstance());  
        BufferedImage localBufferedImage = localJBarcode.createBarcode(str);  
        saveToJPEG(localBufferedImage, str+".jpg"); 
    }  
    catch (Exception localException)  
    {  
      localException.printStackTrace();  
    }  
  }  
  
    public void saveToJPEG(BufferedImage paramBufferedImage, String paramString)  
  {  
    saveToFile(paramBufferedImage, paramString, "jpeg");  
  }  
  
  public void saveToPNG(BufferedImage paramBufferedImage, String paramString)  
  {  
    saveToFile(paramBufferedImage, paramString, "png");  
  }  
  
  public void saveToGIF(BufferedImage paramBufferedImage, String paramString)  
  {  
    saveToFile(paramBufferedImage, paramString, "gif");  
  }  
  
  public void saveToFile(BufferedImage paramBufferedImage, String paramString1, String paramString2)  
  {  
    try  
    { 
	  String realPath =  getRequest().getSession().getServletContext().getRealPath("/file/code/");
	  File newFile = new File(realPath, paramString1);
      FileOutputStream localFileOutputStream = new FileOutputStream(newFile);  
      ImageUtil.encodeAndWrite(paramBufferedImage, paramString2, localFileOutputStream, 250, 78);  
      localFileOutputStream.close();  
    }  
    catch (Exception localException)  
    {  
      localException.printStackTrace();  
    }  
  }  
  
} 
