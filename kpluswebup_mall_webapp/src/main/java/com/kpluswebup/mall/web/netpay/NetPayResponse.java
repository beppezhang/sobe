package com.kpluswebup.mall.web.netpay;

import javax.servlet.http.HttpServletRequest;

public class NetPayResponse {
	private String merid;
	  private String orderno;
	  private String transdate;
	  private String transamt;
	  private String amount;
	  private String currencycode;
	  private String transtype;
	  private String status;
	  private String checkvalue;
	  private String GateId;
	  private String Priv1;

	  public String getMerid()
	  {
	    return this.merid;
	  }
	  public void setMerid(String merid) {
	    this.merid = merid;
	  }
	  public String getOrderno() {
	    return this.orderno;
	  }
	  public void setOrderno(String orderno) {
	    this.orderno = orderno;
	  }
	  public String getTransdate() {
	    return this.transdate;
	  }
	  public void setTransdate(String transdate) {
	    this.transdate = transdate;
	  }
	  public String getAmount() {
	    return this.amount;
	  }
	  public void setAmount(String amount) {
	    this.amount = amount;
	  }
	  public String getCurrencycode() {
	    return this.currencycode;
	  }
	  public void setCurrencycode(String currencycode) {
	    this.currencycode = currencycode;
	  }
	  public String getTranstype() {
	    return this.transtype;
	  }
	  public void setTranstype(String transtype) {
	    this.transtype = transtype;
	  }
	  public String getStatus() {
	    return this.status;
	  }
	  public void setStatus(String status) {
	    this.status = status;
	  }
	  public String getCheckvalue() {
	    return this.checkvalue;
	  }
	  public void setCheckvalue(String checkvalue) {
	    this.checkvalue = checkvalue;
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

	  public void init(HttpServletRequest request) {
	    setMerid(request.getParameter("merid"));
	    setOrderno(request.getParameter("orderno"));
	    setCurrencycode(request.getParameter("currencycode"));
	    setTransdate(request.getParameter("transdate"));
	    setTranstype(request.getParameter("transtype"));
	    setTransamt(request.getParameter("amount"));
	    setStatus(request.getParameter("status"));
	    setCheckvalue(request.getParameter("checkvalue"));
	    setPriv1(request.getParameter("priv1"));
	  }
	  public String getTransamt() {
	    return this.transamt;
	  }
	  public void setTransamt(String transamt) {
	    this.transamt = transamt;
	  }
}
