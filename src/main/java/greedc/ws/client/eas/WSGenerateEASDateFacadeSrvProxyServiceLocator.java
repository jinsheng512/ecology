/**
 * WSGenerateEASDateFacadeSrvProxyServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package greedc.ws.client.eas;

public class WSGenerateEASDateFacadeSrvProxyServiceLocator extends org.apache.axis.client.Service implements greedc.ws.client.eas.WSGenerateEASDateFacadeSrvProxyService {

    public WSGenerateEASDateFacadeSrvProxyServiceLocator() {
    }


    public WSGenerateEASDateFacadeSrvProxyServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSGenerateEASDateFacadeSrvProxyServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSGenerateEASDateFacade
    private java.lang.String WSGenerateEASDateFacade_address = "http://192.168.100.227:6888/ormrpc/services/WSGenerateEASDateFacade";

    public java.lang.String getWSGenerateEASDateFacadeAddress() {
        return WSGenerateEASDateFacade_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSGenerateEASDateFacadeWSDDServiceName = "WSGenerateEASDateFacade";

    public java.lang.String getWSGenerateEASDateFacadeWSDDServiceName() {
        return WSGenerateEASDateFacadeWSDDServiceName;
    }

    public void setWSGenerateEASDateFacadeWSDDServiceName(java.lang.String name) {
        WSGenerateEASDateFacadeWSDDServiceName = name;
    }

    public greedc.ws.client.eas.WSGenerateEASDateFacadeSrvProxy getWSGenerateEASDateFacade() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSGenerateEASDateFacade_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSGenerateEASDateFacade(endpoint);
    }

    public greedc.ws.client.eas.WSGenerateEASDateFacadeSrvProxy getWSGenerateEASDateFacade(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            greedc.ws.client.eas.WSGenerateEASDateFacadeSoapBindingStub _stub = new greedc.ws.client.eas.WSGenerateEASDateFacadeSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSGenerateEASDateFacadeWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSGenerateEASDateFacadeEndpointAddress(java.lang.String address) {
        WSGenerateEASDateFacade_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (greedc.ws.client.eas.WSGenerateEASDateFacadeSrvProxy.class.isAssignableFrom(serviceEndpointInterface)) {
                greedc.ws.client.eas.WSGenerateEASDateFacadeSoapBindingStub _stub = new greedc.ws.client.eas.WSGenerateEASDateFacadeSoapBindingStub(new java.net.URL(WSGenerateEASDateFacade_address), this);
                _stub.setPortName(getWSGenerateEASDateFacadeWSDDServiceName());
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
        if ("WSGenerateEASDateFacade".equals(inputPortName)) {
            return getWSGenerateEASDateFacade();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://192.168.100.227:6888/ormrpc/services/WSGenerateEASDateFacade", "WSGenerateEASDateFacadeSrvProxyService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://192.168.100.227:6888/ormrpc/services/WSGenerateEASDateFacade", "WSGenerateEASDateFacade"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSGenerateEASDateFacade".equals(portName)) {
            setWSGenerateEASDateFacadeEndpointAddress(address);
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
