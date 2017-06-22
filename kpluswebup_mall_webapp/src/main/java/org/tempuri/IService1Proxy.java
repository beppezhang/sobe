package org.tempuri;

public class IService1Proxy implements org.tempuri.IService1 {
  private String _endpoint = null;
  private org.tempuri.IService1 iService1 = null;
  
  public IService1Proxy() {
    _initIService1Proxy();
  }
  
  public IService1Proxy(String endpoint) {
    _endpoint = endpoint;
    _initIService1Proxy();
  }
  
  private void _initIService1Proxy() {
    try {
      iService1 = (new org.tempuri.Service1Locator()).getBasicHttpBinding_IService1();
      if (iService1 != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iService1)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iService1)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iService1 != null)
      ((javax.xml.rpc.Stub)iService1)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.IService1 getIService1() {
    if (iService1 == null)
      _initIService1Proxy();
    return iService1;
  }
  
  public java.lang.String buildOrder(java.lang.String sXml) throws java.rmi.RemoteException{
    if (iService1 == null)
      _initIService1Proxy();
    return iService1.buildOrder(sXml);
  }
  
  public java.lang.String orderStatus(java.lang.String sXml) throws java.rmi.RemoteException{
    if (iService1 == null)
      _initIService1Proxy();
    return iService1.orderStatus(sXml);
  }
  
  public java.lang.String KJBuildOrder(java.lang.String sXml) throws java.rmi.RemoteException{
    if (iService1 == null)
      _initIService1Proxy();
    return iService1.KJBuildOrder(sXml);
  }
  
  
}