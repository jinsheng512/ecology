/**
 * QiBeiServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package greedc.ws.client.qb;

public class QiBeiServiceLocator extends org.apache.axis.client.Service implements greedc.ws.client.qb.QiBeiService {

    public QiBeiServiceLocator() {
    }


    public QiBeiServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public QiBeiServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for QiBeiServiceHttpPort
    private java.lang.String QiBeiServiceHttpPort_address = "http://localhost:8080/services/QiBeiService";

    public java.lang.String getQiBeiServiceHttpPortAddress() {
        return QiBeiServiceHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String QiBeiServiceHttpPortWSDDServiceName = "QiBeiServiceHttpPort";

    public java.lang.String getQiBeiServiceHttpPortWSDDServiceName() {
        return QiBeiServiceHttpPortWSDDServiceName;
    }

    public void setQiBeiServiceHttpPortWSDDServiceName(java.lang.String name) {
        QiBeiServiceHttpPortWSDDServiceName = name;
    }

    public greedc.ws.client.qb.QiBeiServicePortType getQiBeiServiceHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(QiBeiServiceHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getQiBeiServiceHttpPort(endpoint);
    }

    public greedc.ws.client.qb.QiBeiServicePortType getQiBeiServiceHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            greedc.ws.client.qb.QiBeiServiceHttpBindingStub _stub = new greedc.ws.client.qb.QiBeiServiceHttpBindingStub(portAddress, this);
            _stub.setPortName(getQiBeiServiceHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setQiBeiServiceHttpPortEndpointAddress(java.lang.String address) {
        QiBeiServiceHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (greedc.ws.client.qb.QiBeiServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                greedc.ws.client.qb.QiBeiServiceHttpBindingStub _stub = new greedc.ws.client.qb.QiBeiServiceHttpBindingStub(new java.net.URL(QiBeiServiceHttpPort_address), this);
                _stub.setPortName(getQiBeiServiceHttpPortWSDDServiceName());
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
        if ("QiBeiServiceHttpPort".equals(inputPortName)) {
            return getQiBeiServiceHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("webservices.services.weaver.com.cn", "QiBeiService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("webservices.services.weaver.com.cn", "QiBeiServiceHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("QiBeiServiceHttpPort".equals(portName)) {
            setQiBeiServiceHttpPortEndpointAddress(address);
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
