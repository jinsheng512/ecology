/**
 * ArrayOfWorkflowRequestTableField.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package greedc.ws.client.oa;

public class ArrayOfWorkflowRequestTableField  implements java.io.Serializable {
    private greedc.ws.client.oa.WorkflowRequestTableField[] workflowRequestTableField;

    public ArrayOfWorkflowRequestTableField() {
    }

    public ArrayOfWorkflowRequestTableField(
           greedc.ws.client.oa.WorkflowRequestTableField[] workflowRequestTableField) {
           this.workflowRequestTableField = workflowRequestTableField;
    }


    /**
     * Gets the workflowRequestTableField value for this ArrayOfWorkflowRequestTableField.
     * 
     * @return workflowRequestTableField
     */
    public greedc.ws.client.oa.WorkflowRequestTableField[] getWorkflowRequestTableField() {
        return workflowRequestTableField;
    }


    /**
     * Sets the workflowRequestTableField value for this ArrayOfWorkflowRequestTableField.
     * 
     * @param workflowRequestTableField
     */
    public void setWorkflowRequestTableField(greedc.ws.client.oa.WorkflowRequestTableField[] workflowRequestTableField) {
        this.workflowRequestTableField = workflowRequestTableField;
    }

    public greedc.ws.client.oa.WorkflowRequestTableField getWorkflowRequestTableField(int i) {
        return this.workflowRequestTableField[i];
    }

    public void setWorkflowRequestTableField(int i, greedc.ws.client.oa.WorkflowRequestTableField _value) {
        this.workflowRequestTableField[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArrayOfWorkflowRequestTableField)) return false;
        ArrayOfWorkflowRequestTableField other = (ArrayOfWorkflowRequestTableField) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.workflowRequestTableField==null && other.getWorkflowRequestTableField()==null) || 
             (this.workflowRequestTableField!=null &&
              java.util.Arrays.equals(this.workflowRequestTableField, other.getWorkflowRequestTableField())));
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
        if (getWorkflowRequestTableField() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getWorkflowRequestTableField());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getWorkflowRequestTableField(), i);
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
        new org.apache.axis.description.TypeDesc(ArrayOfWorkflowRequestTableField.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "ArrayOfWorkflowRequestTableField"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowRequestTableField");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "WorkflowRequestTableField"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "WorkflowRequestTableField"));
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
