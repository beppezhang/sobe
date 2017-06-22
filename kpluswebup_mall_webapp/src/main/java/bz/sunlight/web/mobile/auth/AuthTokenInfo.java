package bz.sunlight.web.mobile.auth;

public class AuthTokenInfo {
//	mainID_userName_name_time
	private String mainID;
	private String userName;
	private String name;
	private Long expiresUtc;
	public String getMainID() {
		return mainID;
	}
	public void setMainID(String mainID) {
		this.mainID = mainID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getExpiresUtc() {
		return expiresUtc;
	}
	public void setExpiresUtc(Long expiresUtc) {
		this.expiresUtc = expiresUtc;
	}
	
	
}
