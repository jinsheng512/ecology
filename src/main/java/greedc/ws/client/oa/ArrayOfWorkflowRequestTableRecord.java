/**
 * ArrayOfWorkflowRequestTableRecord.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package greedc.ws.client.oa;

public class ArrayOfWorkflowRequestTableRecord  implements java.io.Serializable {
    private greedc.ws.client.oa.WorkflowRequestTableRecord[] workflowRequestTableRecord;

    public ArrayOfWorkflowRequestTableRecord() {
    }

    public ArrayOfWorkflowRequestTableRecord(
           greedc.ws.client.oa.WorkflowRequestTableRecord[] workflowRequestTableRecord) {
           this.workflowRequestTableRecord = workflowRequestTableRecord;
    }


    /**
     * Gets the workflowRequestTableRecord value for this ArrayOfWorkflowRequestTableRecord.
     * 
     * @return workflowRequestTableRecord
     */
    public greedc.ws.client.oa.WorkflowRequestTableRecord[] getWorkflowRequestTableRecord() {
        return workflowRequestTableRecord;
    }


    /**
     * Sets the workflowRequestTableRecord value for this ArrayOfWorkflowRequestTableRecord.
     * 
     * @param workflowRequestTableRecord
     */
    public void setWorkflowRequestTableRecord(greedc.ws.client.oa.WorkflowRequestTableRecord[] workflowRequestTableRecord) {
        this.workflowRequestTableRecord = workflowRequestTableRecord;
    }

    public greedc.ws.client.oa.WorkflowRequestTableRecord getWorkflowRequestTableRecord(int i) {
        return this.workflowRequestTableRecord[i];
    }

    public void setWorkflowRequestTableRecord(int i, greedc.ws.client.oa.WorkflowRequestTableRecord _value) {
        this.workflowRequestTableRecord[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArrayOfWorkflowRequestTableRecord)) return false;
        ArrayOfWorkflowRequestTableRecord other = (ArrayOfWorkflowRequestTableRecord) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.workflowRequestTableRecord==null && other.getWorkflowRequestTableRecord()==null) || 
             (this.workflowRequestTableRecord!=null &&
              java.util.Arrays.equals(this.workflowRequestTableRecord, other.getWorkflowRequestTableRecord())));
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
        if (getWorkflowRequestTableRecord() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getWorkflowRequestTableRecord());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getWorkflowRequestTableRecord(), i);
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
        new org.apache.axis.description.TypeDesc(ArrayOfWorkflowRequestTableRecord.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "ArrayOfWorkflowRequestTableRecord"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowRequestTableRecord");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "WorkflowRequestTableRecord"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "WorkflowRequestTableRecord"));
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
