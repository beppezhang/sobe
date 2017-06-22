package bz.sunlight.web.mobile.auth;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.web.CommonControl;
import bz.sunlight.web.HttpConstant;
import bz.sunlight.web.HttpUtils;
import bz.sunlight.web.crypt.CryptoCoder;

import com.google.common.io.BaseEncoding;
import com.kpluswebup.web.member.service.AuthService;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.GsonUtil;
import com.kpuswebup.comom.util.Md5Algorithm;

/**
 * 身份认证
 * 
 * @author sxc
 */
@Controller
@RequestMapping("/v1/auth")
public class AuthControl extends CommonControl {

	// 错误响应
	// Code 400
	// 内容：{ "error": "用户名或密码错误" }
	//
	// Code 401
	// 内容：{ "error": "该用户未被授权访问指定资源" }
	//
	// Code 403
	// 内容：{ "error": "该用户已被禁止访问或请求的头部未包含 AccessToken" }
	//
	// Code 404
	// 内容：{ "error": "该用户不存在" }

	public static final String MSG_400 = "用户名或密码错误";
	public static final String MSG_401 = "该用户未被授权访问指定资源";
	public static final String MSG_403 = "该用户已被禁止访问或请求的头部未包含 AccessToken";
	public static final String MSG_404 = "该用户不存在";
	public static final String MSG_404_TOKEN = "令牌不存在";

	@Autowired
	private AuthService authService;
	@Autowired
	private CachedClient cachedClient;

	
	@RequestMapping(method = RequestMethod.POST, value = "login" )
	public @ResponseBody void login(@RequestBody String values) {
		try {
			LoginInfo loginInfo = GsonUtil.fromJson(values, LoginInfo.class);
			if (StringUtils.isEmpty(loginInfo.getUserName()) || StringUtils.isEmpty(loginInfo.getPassword())) {
				write_404(MSG_404);
			}else
			{
				CustomerVO customerVO = authService.login(loginInfo.getUserName(), loginInfo.getPassword());
				AuthInfo authInfo = null;
				if (customerVO == null) {
					write_404(MSG_404);
				}else
				{
					if (customerVO.getStatus() == 1) {
						// 登录后逻辑
						if (Md5Algorithm.getInstance().md5Digest(loginInfo.getPassword().getBytes())
								.equals(customerVO.getPassword())) {
							authInfo = buildAuthInfo(customerVO.getMainID(),customerVO.getUsername(),customerVO.getName());
							addCookie(authInfo.getAccessToken());
							write(GsonUtil.toJson(authInfo, DateUtil.formatUTC));
						} else {
							write_400(MSG_400);
						}
		
					} else if (customerVO.getStatus() == 2) {
						write_403(MSG_403);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			write_500(null);
		}
	}

	private AuthInfo buildAuthInfo(String mainID,String userName,String name) {
		AuthInfo authInfo;
		authInfo = new AuthInfo();
		//访问令牌的过期时间(UTC 时区)
		authInfo.setExpiresUtc(DateUtil.addToDay(DateUtil.getTimeByUTC(), HttpConstant.EXPIRESUTC));
		// 刷新令牌的过期时间(UTC 时区)
		authInfo.setRefreshTokenExpiresUtc(DateUtil.addToDay(DateUtil.getTimeByUTC(), HttpConstant.REFRESHTOKENEXPIRESUTC));
		//访问令牌
		String accessTokenStr = buildTokenStr(mainID,userName,name,authInfo.getExpiresUtc().getTime());
		authInfo.setAccessToken(CryptoCoder.getInstance().encrypt(accessTokenStr));
		// 刷新访问令牌的令牌
		String refreshTokenStr =  buildTokenStr(mainID,userName,name,authInfo.getRefreshTokenExpiresUtc().getTime());
		authInfo.setRefreshToken(CryptoCoder.getInstance().encrypt(refreshTokenStr));
		authInfo.buildUser(mainID, name);
		// 缓存用户数据
//		cachedClient.set(HttpConstant.API_USERINFO+customerVO.getMainID(), HttpConstant.EXPIRESUTC_SECOND, customerVO);
//		cachedClient.set(HttpConstant.API_AUTHINFO+authInfo.getAccessToken(), HttpConstant.EXPIRESUTC_SECOND, authInfo);
		return authInfo;
	}
	

	/**
	 * mainID_userName_name_time
	 * @param customerVO
	 * @param time
	 * @return
	 */
	private String buildTokenStr(String mainID,String userName,String name, Long expiresUtc) {
//		return mainID+HttpConstant.SEG+userName+HttpConstant.SEG+name+HttpConstant.SEG+time;
		AuthTokenInfo authTokenInfo = new AuthTokenInfo();
		authTokenInfo.setMainID(mainID);
		authTokenInfo.setUserName(userName);
		authTokenInfo.setName(name);
		authTokenInfo.setExpiresUtc(expiresUtc);
		return GsonUtil.toJson(authTokenInfo);
	}

	@RequestMapping(method = RequestMethod.POST, value = "logout")
	public @ResponseBody void logout(@RequestHeader HttpHeaders headers) {
//		try {
//			Object token = headers.get(HttpConstant.ACCESSTOKEN);
//			if(token==null)
//			{
//				write_404(MSG_404_TOKEN);
//			}else
//			{
//				String [] tokenArray = CodeUtil.base64Encode(token.toString()).split(HttpConstant.SEG);
//				cachedClient.delete(HttpConstant.API_USERINFO+tokenArray[0]);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			write_500(null);
//		}
		try {
			write("");
			delCookie();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("注销报错");
			write_500(null);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "refresh")
	public @ResponseBody void refresh(@RequestBody String values) {
		try {
			LoginInfo loginInfo = GsonUtil.fromJson(values, LoginInfo.class);
			String token = loginInfo.getToken();
			if(token==null)
			{
				write_404(MSG_404_TOKEN);
			}else
			{
//				String [] tokenArray = new String(CodeUtil.base64Decode(token.toString())).split(HttpConstant.SEG);
				AuthTokenInfo authTokenInfo = GsonUtil.fromJson(CryptoCoder.getInstance().decrypt(BaseEncoding.base64().decode(token)),AuthTokenInfo.class);
				if(HttpUtils.validateToken(authTokenInfo))
				{
					AuthInfo authInfo = buildAuthInfo(authTokenInfo.getMainID(),authTokenInfo.getUserName(),authTokenInfo.getName());
					addCookie(authInfo.getAccessToken());
					write(GsonUtil.toJson(authInfo, DateUtil.formatUTC));					
				}else
				{
					write_404(MSG_404_TOKEN);					
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			write_500(null);
		}
	}
	
	private class LoginInfo
	{
		private String userName;
		private String password;
		private String token;
		public String getUserName() {
			return userName;
		}
		@SuppressWarnings("unused")
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		@SuppressWarnings("unused")
		public void setPassword(String password) {
			this.password = password;
		}
		public String getToken() {
			return token;
		}
		@SuppressWarnings("unused")
		public void setToken(String token) {
			this.token = token;
		}
		
	}

}
