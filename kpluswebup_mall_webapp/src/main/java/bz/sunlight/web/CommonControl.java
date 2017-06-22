package bz.sunlight.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;

import com.google.gson.JsonObject;
import com.kpuswebup.comom.util.CodeUtil;
import com.kpuswebup.comom.util.CookieUtil;

public class CommonControl {

	public HttpServletResponse getResponse() {
		WebAsyncManager asyncManager = WebAsyncUtils
				.getAsyncManager(getRequest());
		ServletWebRequest webRequest = (ServletWebRequest) ReflectionTestUtils
				.getField(asyncManager, "asyncWebRequest");
		return webRequest.getResponse();
	}

	public HttpServletRequest getRequest() {
		ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes());
		return servletRequestAttributes.getRequest();
	}

	public HttpServletResponse getJSONResponse() {
		HttpServletResponse response = getResponse();
		response.setContentType(HttpConstant.CONTENTTYPE_JSON);
		return response;
	}

	public void write(String value) throws IOException {
		HttpServletResponse response = getJSONResponse();
		write(response, value);
	}

	public void write(HttpServletResponse response, String value)
			throws IOException {
		response.getWriter().write(value);
	}

	public void write(String value, int code) throws IOException {
		HttpServletResponse response = getJSONResponse();
		setStatus(response, code);
		write(response, value);
	}
	
	public void write(HttpServletResponse response, String value, int code) throws IOException {
		setStatus(response, code);
		write(response, value);
	}	

	public void write_400(String value) throws IOException {
		HttpServletResponse response = getJSONResponse();
		setStatus(response, HttpConstant.CODE_400);
		write(response, buildError(value).toString());
	}

	public void write_401(String value) throws IOException {
		HttpServletResponse response = getJSONResponse();
		setStatus(response, HttpConstant.CODE_401);
		write(response, buildError(value).toString());
	}

	public void write_403(String value) throws IOException {
		HttpServletResponse response = getJSONResponse();
		setStatus(response, HttpConstant.CODE_403);
		write(response, buildError(value).toString());
	}

	public void write_404(String value) throws IOException {
		HttpServletResponse response = getJSONResponse();
		setStatus(response, HttpConstant.CODE_404);
		write(response, buildError(value).toString());
	}

	public void write_500(String value) {
		HttpServletResponse response = getJSONResponse();
		setStatus(response, HttpConstant.CODE_500);
		JsonObject jsonObj = new JsonObject();
		if (value != null) {
			jsonObj.addProperty("error", value);
		} else {
			jsonObj.addProperty("error", "系统报错");
		}
		try {
			response.getWriter().write(jsonObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String [] getAuthInfo(HttpServletRequest request, HttpServletResponse response)
	{
		String token = request.getHeader(HttpConstant.ACCESSTOKEN);
		if (token == null){
			CookieUtil cookie = new CookieUtil(request, response, 0);
			token = cookie.getCookieValue(HttpConstant.COOKIE_NAME);
		}
    	try {
        	String[] tokenArray = new String(CodeUtil.base64Decode(token.toString())).split(HttpConstant.SEG);
        	return tokenArray;			
		} catch (Exception e) {
			System.out.println("token解码报错");
			return null;
		}

	}
	
	public void addCookie(String token)
	{
		CookieUtil cookie = new CookieUtil(getRequest(), getResponse(), HttpConstant.COOOKIEEXPIRESUTC);
		cookie.addCookie(HttpConstant.COOKIE_NAME, token);
	}	
	public void delCookie()
	{
		CookieUtil cookie = new CookieUtil(getRequest(), getResponse(), 0);
		cookie.deleteCookie(HttpConstant.COOKIE_NAME);
	}		
	
	private void setStatus(HttpServletResponse response, int code) {
		response.setStatus(code);
	}

	private JsonObject buildError(String value) {
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("error", value);
		return jsonObj;
	}

}
