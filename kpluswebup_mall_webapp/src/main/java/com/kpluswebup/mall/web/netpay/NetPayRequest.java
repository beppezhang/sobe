package com.kpluswebup.mall.web.netpay;

public class NetPayRequest {
	private String MerId;
	  private String OrdId;
	  private String TransAmt;
	  private String CuryId;
	  private String TransDate;
	  private String TransType;
	  private String Version;
	  private String BgRetUrl;
	  private String PageRetUrl;
	  private String GateId;
	  private String Priv1;
	  private String ChkValue;

	  public String getMerId()
	  {
	    return this.MerId;
	  }
	  public void setMerId(String merId) {
	    this.MerId = merId;
	  }
	  public String getOrdId() {
	    return this.OrdId;
	  }
	  public void setOrdId(String ordId) {
	    this.OrdId = ordId;
	  }
	  public String getTransAmt() {
	    return this.TransAmt;
	  }
	  public void setTransAmt(String transAmt) {
	    this.TransAmt = transAmt;
	  }
	  public String getCuryId() {
	    return this.CuryId;
	  }
	  public void setCuryId(String curyId) {
	    this.CuryId = curyId;
	  }
	  public String getTransDate() {
	    return this.TransDate;
	  }
	  public void setTransDate(String transDate) {
	    this.TransDate = transDate;
	  }
	  public String getTransType() {
	    return this.TransType;
	  }
	  public void setTransType(String transType) {
	    this.TransType = transType;
	  }
	  public String getVersion() {
	    return this.Version;
	  }
	  public void setVersion(String version) {
	    this.Version = version;
	  }
	  public String getBgRetUrl() {
	    return this.BgRetUrl;
	  }
	  public void setBgRetUrl(String bgRetUrl) {
	    this.BgRetUrl = bgRetUrl;
	  }
	  public String getPageRetUrl() {
	    return this.PageRetUrl;
	  }
	  public void setPageRetUrl(String pageRetUrl) {
	    this.PageRetUrl = pageRetUrl;
	  }
	  public String getGateId() {
	    return this.GateId;
	  }
	  public void setGateId(String gateId) {
	    this.GateId = gateId;
	  }
	  public String getPriv1() {
	    return this.Priv1;
	  }
	  public void setPriv1(String priv1) {
	    this.Priv1 = priv1;
	  }
	  public String getChkValue() {
	    return this.ChkValue;
	  }
	  public void setChkValue(String chkValue) {
	    this.ChkValue = chkValue;
	  }
}
