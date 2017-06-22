package SMS;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import sun.misc.BASE64Encoder;
import cn.com.flaginfo.ems.send.EmsSend;


public class EmsSendTest {

	public static void main(String[] args) {
		String info = null;
		try{
			HttpClient httpclient = new HttpClient();
			 SimpleDateFormat df1 = new SimpleDateFormat("yyyy年MM月dd日");
			 
			PostMethod post = new PostMethod("http://ums.zj165.com:8888/sms/Api/Send.do");//
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"GB2312");
			post.addParameter("SpCode", "223388");
			post.addParameter("LoginName", "haz_pzkj");
			post.addParameter("Password", "ds123456");
			 String s = "";
             while (s.length() < 6)
                 s += (int) (Math.random() * 10);
            // 	您于"+df1.format(new Date())+"申请了卡塔网络找回密码的手机校验码是" + s
             post.addParameter("MessageContent", "您于"+df1.format(new Date())+"申请了卡塔网络找回密码的手机校验码是" + s);
			//post.addParameter("MessageContent", "您的订单1234567890987654商家已完成价格修改，请到我的订单中完成支持。");
			post.addParameter("UserNumber", "13065736120");
			post.addParameter("SerialNumber", "20150719185101121313");
			post.addParameter("ScheduleTime", "20150719185101");
			post.addParameter("ExtendAccessNum", "");
			post.addParameter("f", "1");
			httpclient.executeMethod(post);
			info = new String(post.getResponseBody(),"GB2312");
			System.out.println(info);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static Integer sendMsg(String content,String phone){
		String info = null;
		String ssss=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		try{
			HttpClient httpclient = new HttpClient();
			PostMethod post = new PostMethod("http://ums.zj165.com:8888/sms/Api/Send.do");//
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"GB2312");
			post.addParameter("SpCode", "223388");
			post.addParameter("LoginName", "haz_pzkj");
			post.addParameter("Password", "ds123456");
			post.addParameter("MessageContent", content);
			post.addParameter("UserNumber", phone);
			post.addParameter("SerialNumber", ssss+"123456");
			post.addParameter("ScheduleTime", ssss);
			post.addParameter("ExtendAccessNum", "");
			post.addParameter("f", "1");
			httpclient.executeMethod(post);
			info = new String(post.getResponseBody(),"GB2312");
			System.out.println(content);
			System.out.println(info);
			if(info.startsWith("result=0")){
				return 0;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	public static String getByteString( byte[] buff_out )
	{
		StringBuffer strBuf = new StringBuffer(buff_out.length * 3);
		strBuf.append("Length[");
		strBuf.append(buff_out.length);
		strBuf.append("];Content[");
		for ( int i = 0 ; i < buff_out.length ; ++i ) {
			int l = buff_out[i] & 0x0F;
			int h = (buff_out[i] & 0xF0) >> 4;

			char ll = (char) (l > 9 ? 'a' + l - 10 : '0' + l);
			char hh = (char) (h > 9 ? 'a' + h - 10 : '0' + h);

			strBuf.append(hh);
			strBuf.append(ll);
			strBuf.append(" ");
		}
		strBuf.append("]");
		return strBuf.toString().toUpperCase();
	}
}
