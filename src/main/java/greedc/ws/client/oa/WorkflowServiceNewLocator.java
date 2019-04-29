/**
 * WorkflowServiceNewLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package greedc.ws.client.oa;

public class WorkflowServiceNewLocator extends org.apache.axis.client.Service implements greedc.ws.client.oa.WorkflowServiceNew {

    public WorkflowServiceNewLocator() {
    }


    public WorkflowServiceNewLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WorkflowServiceNewLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WorkflowServiceNewHttpPort
    private java.lang.String WorkflowServiceNewHttpPort_address = "http://localhost:8080/services/WorkflowServiceNew";

    public java.lang.String getWorkflowServiceNewHttpPortAddress() {
        return WorkflowServiceNewHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WorkflowServiceNewHttpPortWSDDServiceName = "WorkflowServiceNewHttpPort";

    public java.lang.String getWorkflowServiceNewHttpPortWSDDServiceName() {
        return WorkflowServiceNewHttpPortWSDDServiceName;
    }

    public void setWorkflowServiceNewHttpPortWSDDServiceName(java.lang.String name) {
        WorkflowServiceNewHttpPortWSDDServiceName = name;
    }

    public greedc.ws.client.oa.WorkflowServiceNewPortType getWorkflowServiceNewHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WorkflowServiceNewHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWorkflowServiceNewHttpPort(endpoint);
    }

    public greedc.ws.client.oa.WorkflowServiceNewPortType getWorkflowServiceNewHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            greedc.ws.client.oa.WorkflowServiceNewHttpBindingStub _stub = new greedc.ws.client.oa.WorkflowServiceNewHttpBindingStub(portAddress, this);
            _stub.setPortName(getWorkflowServiceNewHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWorkflowServiceNewHttpPortEndpointAddress(java.lang.String address) {
        WorkflowServiceNewHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (greedc.ws.client.oa.WorkflowServiceNewPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                greedc.ws.client.oa.WorkflowServiceNewHttpBindingStub _stub = new greedc.ws.client.oa.WorkflowServiceNewHttpBindingStub(new java.net.URL(WorkflowServiceNewHttpPort_address), this);
                _stub.setPortName(getWorkflowServiceNewHttpPortWSDDServiceName());
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
        if ("WorkflowServiceNewHttpPort".equals(inputPortName)) {
            return getWorkflowServiceNewHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("webservices.services.weaver.com.cn", "WorkflowServiceNew");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("webservices.services.weaver.com.cn", "WorkflowServiceNewHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WorkflowServiceNewHttpPort".equals(portName)) {
            setWorkflowServiceNewHttpPortEndpointAddress(address);
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
