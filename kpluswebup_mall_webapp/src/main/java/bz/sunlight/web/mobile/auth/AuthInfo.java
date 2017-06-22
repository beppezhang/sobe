package bz.sunlight.web.mobile.auth;

import java.util.Date;

public class AuthInfo {
	// {
	//     "accessToken": [string], // 访问令牌
	//     "expiresUtc": [dateTime], // 访问令牌的过期时间(UTC 时区)
	//     "refreshToken": [string], // 刷新访问令牌的令牌
	//     "refreshTokenExpiresUtc": [dateTime], // 刷新令牌的过期时间(UTC 时区)
	//     "user": {
	//         "id": [string], // 用户 Id
	//         "name": [string], // 用户姓名
	//     }
	// }
	private String accessToken; // 访问令牌
	private Date expiresUtc; // 访问令牌的过期时间(UTC 时区)
	private String refreshToken; // 刷新访问令牌的令牌
	private Date refreshTokenExpiresUtc; // 刷新令牌的过期时间(UTC 时区)
	private User user;
	
	
	public void buildUser(String id,String name)
	{
		user = new User();
		user.setId(id);
		user.setName(name);
	}
	
	private class User {
		private String id; // 用户 Id
		private String name;// 用户姓名

		@SuppressWarnings("unused")
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@SuppressWarnings("unused")
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getExpiresUtc() {
		return expiresUtc;
	}

	public void setExpiresUtc(Date expiresUtc) {
		this.expiresUtc = expiresUtc;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Date getRefreshTokenExpiresUtc() {
		return refreshTokenExpiresUtc;
	}

	public void setRefreshTokenExpiresUtc(Date refreshTokenExpiresUtc) {
		this.refreshTokenExpiresUtc = refreshTokenExpiresUtc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
