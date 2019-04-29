/**
 * ArrayOfWorkflowRequestLog.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package greedc.ws.client.oa;

public class ArrayOfWorkflowRequestLog  implements java.io.Serializable {
    private greedc.ws.client.oa.WorkflowRequestLog[] workflowRequestLog;

    public ArrayOfWorkflowRequestLog() {
    }

    public ArrayOfWorkflowRequestLog(
           greedc.ws.client.oa.WorkflowRequestLog[] workflowRequestLog) {
           this.workflowRequestLog = workflowRequestLog;
    }


    /**
     * Gets the workflowRequestLog value for this ArrayOfWorkflowRequestLog.
     * 
     * @return workflowRequestLog
     */
    public greedc.ws.client.oa.WorkflowRequestLog[] getWorkflowRequestLog() {
        return workflowRequestLog;
    }


    /**
     * Sets the workflowRequestLog value for this ArrayOfWorkflowRequestLog.
     * 
     * @param workflowRequestLog
     */
    public void setWorkflowRequestLog(greedc.ws.client.oa.WorkflowRequestLog[] workflowRequestLog) {
        this.workflowRequestLog = workflowRequestLog;
    }

    public greedc.ws.client.oa.WorkflowRequestLog getWorkflowRequestLog(int i) {
        return this.workflowRequestLog[i];
    }

    public void setWorkflowRequestLog(int i, greedc.ws.client.oa.WorkflowRequestLog _value) {
        this.workflowRequestLog[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArrayOfWorkflowRequestLog)) return false;
        ArrayOfWorkflowRequestLog other = (ArrayOfWorkflowRequestLog) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.workflowRequestLog==null && other.getWorkflowRequestLog()==null) || 
             (this.workflowRequestLog!=null &&
              java.util.Arrays.equals(this.workflowRequestLog, other.getWorkflowRequestLog())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getWorkflowRequestLog() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getWorkflowRequestLog());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getWorkflowRequestLog(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ArrayOfWorkflowRequestLog.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "ArrayOfWorkflowRequestLog"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowRequestLog");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "WorkflowRequestLog"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "WorkflowRequestLog"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
