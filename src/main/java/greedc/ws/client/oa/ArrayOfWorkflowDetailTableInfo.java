/**
 * ArrayOfWorkflowDetailTableInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package greedc.ws.client.oa;

public class ArrayOfWorkflowDetailTableInfo  implements java.io.Serializable {
    private greedc.ws.client.oa.WorkflowDetailTableInfo[] workflowDetailTableInfo;

    public ArrayOfWorkflowDetailTableInfo() {
    }

    public ArrayOfWorkflowDetailTableInfo(
           greedc.ws.client.oa.WorkflowDetailTableInfo[] workflowDetailTableInfo) {
           this.workflowDetailTableInfo = workflowDetailTableInfo;
    }


    /**
     * Gets the workflowDetailTableInfo value for this ArrayOfWorkflowDetailTableInfo.
     * 
     * @return workflowDetailTableInfo
     */
    public greedc.ws.client.oa.WorkflowDetailTableInfo[] getWorkflowDetailTableInfo() {
        return workflowDetailTableInfo;
    }


    /**
     * Sets the workflowDetailTableInfo value for this ArrayOfWorkflowDetailTableInfo.
     * 
     * @param workflowDetailTableInfo
     */
    public void setWorkflowDetailTableInfo(greedc.ws.client.oa.WorkflowDetailTableInfo[] workflowDetailTableInfo) {
        this.workflowDetailTableInfo = workflowDetailTableInfo;
    }

    public greedc.ws.client.oa.WorkflowDetailTableInfo getWorkflowDetailTableInfo(int i) {
        return this.workflowDetailTableInfo[i];
    }

    public void setWorkflowDetailTableInfo(int i, greedc.ws.client.oa.WorkflowDetailTableInfo _value) {
        this.workflowDetailTableInfo[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArrayOfWorkflowDetailTableInfo)) return false;
        ArrayOfWorkflowDetailTableInfo other = (ArrayOfWorkflowDetailTableInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.workflowDetailTableInfo==null && other.getWorkflowDetailTableInfo()==null) || 
             (this.workflowDetailTableInfo!=null &&
              java.util.Arrays.equals(this.workflowDetailTableInfo, other.getWorkflowDetailTableInfo())));
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
        if (getWorkflowDetailTableInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getWorkflowDetailTableInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getWorkflowDetailTableInfo(), i);
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
        new org.apache.axis.description.TypeDesc(ArrayOfWorkflowDetailTableInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "ArrayOfWorkflowDetailTableInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowDetailTableInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "WorkflowDetailTableInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "WorkflowDetailTableInfo"));
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
