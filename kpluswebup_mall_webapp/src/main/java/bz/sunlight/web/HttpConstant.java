package bz.sunlight.web;

public class HttpConstant {
	public static final int CODE_400 = 400;
	public static final int CODE_401 = 401;
	public static final int CODE_403 = 403;
	public static final int CODE_404 = 404;
	public static final int CODE_500 = 500;
	public static final String CONTENTTYPE_JSON = "application/json;charset=utf-8";
	public static final String API_USERINFO = "api_user_info_";
	public static final String API_REFRESH_TOKEN = "api_refresh_token";
	public static final String API_AUTHINFO = "api_authinfo_token";
	public static final String ACCESSTOKEN = "AccessToken";
	public static final int EXPIRESUTC = 1;// 天/单位
	public static final int REFRESHTOKENEXPIRESUTC = 2;//天/单位
	public static final int COOOKIEEXPIRESUTC = 24 * 60 * 60 * 7;
	public static final int EXPIRESUTC_SECOND = 24 * 60 * 60 * EXPIRESUTC;
	public static final int REFRESHTOKENEXPIRESUTC_SECOND = 24 * 60 * 60 * REFRESHTOKENEXPIRESUTC;
	public static final String SEG = "_";
	public static final String COOKIE_NAME = "api_cookie_soubei";
	
	
	public static final String API_MSG_401 = "未被授权访问指定资源";
	public static final String API_MSG_403 = "请求的头部未包含 AccessToken，或该用户已被列入黑名单";
}
