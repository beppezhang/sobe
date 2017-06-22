package bz.sunlight.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import bz.sunlight.web.crypt.CryptoCoder;
import bz.sunlight.web.mobile.auth.AuthTokenInfo;

import com.google.common.io.BaseEncoding;
import com.google.gson.JsonObject;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.GsonUtil;

public class HttpUtils {

//	public static boolean validateToken(String[] tokenArray) {
//		if (tokenArray == null || tokenArray.length <= 0)
//			return false;
//		try {
//			Long expiresUtc = Long.parseLong(tokenArray[3]);
//			if (expiresUtc > DateUtil.getTimeByUTC().getTime()) {
//				return true;
//			}
//		} catch (Exception e) {
//			return false;
//		}
//		return false;
//	}
	
	public static boolean validateToken(AuthTokenInfo authTokenInfo) {
		if (authTokenInfo == null)
			return false;
		try {
			if (authTokenInfo.getExpiresUtc() > DateUtil.getTimeByUTC().getTime()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}	
		
	
	public static boolean validateToken(String token) {
		try {
			if(token!=null && !"".equals(token))
			{
//				String[] tokenArray = new String(CodeUtil.base64Decode(token.toString())).split(HttpConstant.SEG);
				AuthTokenInfo authTokenInfo = GsonUtil.fromJson(CryptoCoder.getInstance().decrypt(BaseEncoding.base64().decode(token)),AuthTokenInfo.class);
				return validateToken(authTokenInfo);			
			}else
			{
				return false;
			}			
		} catch (Exception e) {
			System.out.println("验证token出错");
			return false;
		}


	}	
	
	public static void write(HttpServletResponse response, String value, int code) throws IOException {
		response.setContentType(HttpConstant.CONTENTTYPE_JSON);
		response.setStatus(code);
		response.getWriter().write(buildError(value).toString());
	}	
	
	private static JsonObject buildError(String value) {
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("error", value);
		return jsonObj;
	}
	
}
