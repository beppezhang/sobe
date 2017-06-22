package com.kpluswebup.web.wechat;

import java.net.URI;
import java.net.URL;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientConnectionManager {
	
	
	/**
	 * 获取SSL验证的HttpClient
	 * @param httpClient
	 * @return
	 */
	@SuppressWarnings("deprecation")
    public static HttpClient getSSLInstance(HttpClient httpClient){
		ClientConnectionManager ccm = httpClient.getConnectionManager();
		SchemeRegistry sr = ccm.getSchemeRegistry();
		sr.register(new Scheme("https", MySSLSocketFactory.getInstance(), 443));
		httpClient =  new DefaultHttpClient(ccm,httpClient.getParams());
		return httpClient;
	}
	
	/**
	 * 模拟浏览器post提交
	 * 
	 * @param url
	 * @return
	 */
	public static HttpPost getPostMethod(String url) {
		HttpPost pmethod = new HttpPost(url); // 设置响应头信息
		pmethod.addHeader("Connection", "keep-alive");
		pmethod.addHeader("Accept", "*/*");
		pmethod.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		pmethod.addHeader("Host", "mp.weixin.qq.com");
		pmethod.addHeader("X-Requested-With", "XMLHttpRequest");
		pmethod.addHeader("Cache-Control", "max-age=0");
		pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
		return pmethod;
	}

	/**
	 * 模拟浏览器GET提交
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public static HttpGet getGetMethod(String strUrl) throws Exception {
	    /* 解决订阅号修改菜单时抛出URISyntaxException
	     * 问题出在url中涉及了特殊字符，如‘｜’‘&’等，所以不能直接用String代替URI来访问
	     * 可以先把String转成URL，再通过URL生成URI的方法来解决此问题
	     */
	    URL url = new URL(strUrl);
	    URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
		HttpGet pmethod = new HttpGet(uri);
		// 设置响应头信息
		pmethod.addHeader("Connection", "keep-alive");
		pmethod.addHeader("Cache-Control", "max-age=0");
		pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
		pmethod.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/;q=0.8");
		return pmethod;
	}
}
