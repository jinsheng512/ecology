/**
 * WSGeneratePaymentFacadeSrvProxyServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _227._100._168._192.ormrpc.services.WSGeneratePaymentFacade;

public class WSGeneratePaymentFacadeSrvProxyServiceLocator extends org.apache.axis.client.Service implements _227._100._168._192.ormrpc.services.WSGeneratePaymentFacade.WSGeneratePaymentFacadeSrvProxyService {

    public WSGeneratePaymentFacadeSrvProxyServiceLocator() {
    }


    public WSGeneratePaymentFacadeSrvProxyServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSGeneratePaymentFacadeSrvProxyServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSGeneratePaymentFacade
    private java.lang.String WSGeneratePaymentFacade_address = "http://192.168.100.227:6888/ormrpc/services/WSGeneratePaymentFacade";

    public java.lang.String getWSGeneratePaymentFacadeAddress() {
        return WSGeneratePaymentFacade_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSGeneratePaymentFacadeWSDDServiceName = "WSGeneratePaymentFacade";

    public java.lang.String getWSGeneratePaymentFacadeWSDDServiceName() {
        return WSGeneratePaymentFacadeWSDDServiceName;
    }

    public void setWSGeneratePaymentFacadeWSDDServiceName(java.lang.String name) {
        WSGeneratePaymentFacadeWSDDServiceName = name;
    }

    public _227._100._168._192.ormrpc.services.WSGeneratePaymentFacade.WSGeneratePaymentFacadeSrvProxy getWSGeneratePaymentFacade() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSGeneratePaymentFacade_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSGeneratePaymentFacade(endpoint);
    }

    public _227._100._168._192.ormrpc.services.WSGeneratePaymentFacade.WSGeneratePaymentFacadeSrvProxy getWSGeneratePaymentFacade(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            _227._100._168._192.ormrpc.services.WSGeneratePaymentFacade.WSGeneratePaymentFacadeSoapBindingStub _stub = new _227._100._168._192.ormrpc.services.WSGeneratePaymentFacade.WSGeneratePaymentFacadeSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSGeneratePaymentFacadeWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSGeneratePaymentFacadeEndpointAddress(java.lang.String address) {
        WSGeneratePaymentFacade_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (_227._100._168._192.ormrpc.services.WSGeneratePaymentFacade.WSGeneratePaymentFacadeSrvProxy.class.isAssignableFrom(serviceEndpointInterface)) {
                _227._100._168._192.ormrpc.services.WSGeneratePaymentFacade.WSGeneratePaymentFacadeSoapBindingStub _stub = new _227._100._168._192.ormrpc.services.WSGeneratePaymentFacade.WSGeneratePaymentFacadeSoapBindingStub(new java.net.URL(WSGeneratePaymentFacade_address), this);
                _stub.setPortName(getWSGeneratePaymentFacadeWSDDServiceName());
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
        if ("WSGeneratePaymentFacade".equals(inputPortName)) {
            return getWSGeneratePaymentFacade();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://192.168.100.227:6888/ormrpc/services/WSGeneratePaymentFacade", "WSGeneratePaymentFacadeSrvProxyService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://192.168.100.227:6888/ormrpc/services/WSGeneratePaymentFacade", "WSGeneratePaymentFacade"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSGeneratePaymentFacade".equals(portName)) {
            setWSGeneratePaymentFacadeEndpointAddress(address);
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
