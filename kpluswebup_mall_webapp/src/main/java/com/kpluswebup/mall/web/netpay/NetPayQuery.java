package com.kpluswebup.mall.web.netpay;

public class NetPayQuery {
	private String MerId;
	  private String TransType;
	  private String OrdId;
	  private String TransDate;
	  private String Version;
	  private String Resv;
	  private String ChkValue;

	  public String getMerId()
	  {
	    return this.MerId;
	  }
	  public void setMerId(String merId) {
	    this.MerId = merId;
	  }
	  public String getTransType() {
	    return this.TransType;
	  }
	  public void setTransType(String transType) {
	    this.TransType = transType;
	  }
	  public String getOrdId() {
	    return this.OrdId;
	  }
	  public void setOrdId(String ordId) {
	    this.OrdId = ordId;
	  }
	  public String getTransDate() {
	    return this.TransDate;
	  }
	  public void setTransDate(String transDate) {
	    this.TransDate = transDate;
	  }
	  public String getVersion() {
	    return this.Version;
	  }
	  public void setVersion(String version) {
	    this.Version = version;
	  }
	  public String getResv() {
	    return this.Resv;
	  }
	  public void setResv(String resv) {
	    this.Resv = resv;
	  }
	  public String getChkValue() {
	    return this.ChkValue;
	  }
	  public void setChkValue(String chkValue) {
	    this.ChkValue = chkValue;
	  }
}
