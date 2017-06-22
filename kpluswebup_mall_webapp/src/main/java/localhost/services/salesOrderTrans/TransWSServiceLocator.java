/**
 * TransWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.services.salesOrderTrans;

public class TransWSServiceLocator extends org.apache.axis.client.Service implements localhost.services.salesOrderTrans.TransWSService {

    public TransWSServiceLocator() {
    }


    public TransWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TransWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for salesOrderTrans
    private java.lang.String salesOrderTrans_address = "http://localhost/services/salesOrderTrans";

    public java.lang.String getsalesOrderTransAddress() {
        return salesOrderTrans_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String salesOrderTransWSDDServiceName = "salesOrderTrans";

    public java.lang.String getsalesOrderTransWSDDServiceName() {
        return salesOrderTransWSDDServiceName;
    }

    public void setsalesOrderTransWSDDServiceName(java.lang.String name) {
        salesOrderTransWSDDServiceName = name;
    }

    public localhost.services.salesOrderTrans.TransWS getsalesOrderTrans() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(salesOrderTrans_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getsalesOrderTrans(endpoint);
    }

    public localhost.services.salesOrderTrans.TransWS getsalesOrderTrans(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.services.salesOrderTrans.SalesOrderTransSoapBindingStub _stub = new localhost.services.salesOrderTrans.SalesOrderTransSoapBindingStub(portAddress, this);
            _stub.setPortName(getsalesOrderTransWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setsalesOrderTransEndpointAddress(java.lang.String address) {
        salesOrderTrans_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.services.salesOrderTrans.TransWS.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.services.salesOrderTrans.SalesOrderTransSoapBindingStub _stub = new localhost.services.salesOrderTrans.SalesOrderTransSoapBindingStub(new java.net.URL(salesOrderTrans_address), this);
                _stub.setPortName(getsalesOrderTransWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("salesOrderTrans".equals(inputPortName)) {
            return getsalesOrderTrans();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost/services/salesOrderTrans", "TransWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost/services/salesOrderTrans", "salesOrderTrans"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("salesOrderTrans".equals(portName)) {
            setsalesOrderTransEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
