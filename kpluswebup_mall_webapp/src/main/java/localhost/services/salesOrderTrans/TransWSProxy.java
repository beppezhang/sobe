package localhost.services.salesOrderTrans;

public class TransWSProxy implements localhost.services.salesOrderTrans.TransWS {
  private String _endpoint = null;
  private localhost.services.salesOrderTrans.TransWS transWS = null;
  
  public TransWSProxy() {
    _initTransWSProxy();
  }
  
  public TransWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initTransWSProxy();
  }
  
  private void _initTransWSProxy() {
    try {
      transWS = (new localhost.services.salesOrderTrans.TransWSServiceLocator()).getsalesOrderTrans();
      if (transWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)transWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)transWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (transWS != null)
      ((javax.xml.rpc.Stub)transWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public localhost.services.salesOrderTrans.TransWS getTransWS() {
    if (transWS == null)
      _initTransWSProxy();
    return transWS;
  }
  
  public java.lang.String salesOrderTransSave(java.lang.String xmlStr1) throws java.rmi.RemoteException{
    if (transWS == null)
      _initTransWSProxy();
    return transWS.salesOrderTransSave(xmlStr1);
  }
  
  
}