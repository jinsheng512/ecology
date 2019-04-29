/**
 * WSUpdeteEASStatusFacadeSrvProxyServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package greedc.ws.client.eas;

public class WSUpdeteEASStatusFacadeSrvProxyServiceLocator extends org.apache.axis.client.Service implements greedc.ws.client.eas.WSUpdeteEASStatusFacadeSrvProxyService {

    public WSUpdeteEASStatusFacadeSrvProxyServiceLocator() {
    }


    public WSUpdeteEASStatusFacadeSrvProxyServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSUpdeteEASStatusFacadeSrvProxyServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSUpdeteEASStatusFacade
    private java.lang.String WSUpdeteEASStatusFacade_address = "http://10.0.0.95:56898/ormrpc/services/WSUpdeteEASStatusFacade";

    public java.lang.String getWSUpdeteEASStatusFacadeAddress() {
        return WSUpdeteEASStatusFacade_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSUpdeteEASStatusFacadeWSDDServiceName = "WSUpdeteEASStatusFacade";

    public java.lang.String getWSUpdeteEASStatusFacadeWSDDServiceName() {
        return WSUpdeteEASStatusFacadeWSDDServiceName;
    }

    public void setWSUpdeteEASStatusFacadeWSDDServiceName(java.lang.String name) {
        WSUpdeteEASStatusFacadeWSDDServiceName = name;
    }

    public greedc.ws.client.eas.WSUpdeteEASStatusFacadeSrvProxy getWSUpdeteEASStatusFacade() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSUpdeteEASStatusFacade_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSUpdeteEASStatusFacade(endpoint);
    }

    public greedc.ws.client.eas.WSUpdeteEASStatusFacadeSrvProxy getWSUpdeteEASStatusFacade(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            greedc.ws.client.eas.WSUpdeteEASStatusFacadeSoapBindingStub _stub = new greedc.ws.client.eas.WSUpdeteEASStatusFacadeSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSUpdeteEASStatusFacadeWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSUpdeteEASStatusFacadeEndpointAddress(java.lang.String address) {
        WSUpdeteEASStatusFacade_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (greedc.ws.client.eas.WSUpdeteEASStatusFacadeSrvProxy.class.isAssignableFrom(serviceEndpointInterface)) {
                greedc.ws.client.eas.WSUpdeteEASStatusFacadeSoapBindingStub _stub = new greedc.ws.client.eas.WSUpdeteEASStatusFacadeSoapBindingStub(new java.net.URL(WSUpdeteEASStatusFacade_address), this);
                _stub.setPortName(getWSUpdeteEASStatusFacadeWSDDServiceName());
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
        if ("WSUpdeteEASStatusFacade".equals(inputPortName)) {
            return getWSUpdeteEASStatusFacade();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://127.0.0.1:56898/ormrpc/services/WSUpdeteEASStatusFacade", "WSUpdeteEASStatusFacadeSrvProxyService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://127.0.0.1:56898/ormrpc/services/WSUpdeteEASStatusFacade", "WSUpdeteEASStatusFacade"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSUpdeteEASStatusFacade".equals(portName)) {
            setWSUpdeteEASStatusFacadeEndpointAddress(address);
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
