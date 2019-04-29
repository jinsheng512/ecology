/**
 * WorkflowRequestTableRecord.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package greedc.ws.client.oa;

public class WorkflowRequestTableRecord  implements java.io.Serializable {
    private java.lang.Integer recordOrder;
    private greedc.ws.client.oa.ArrayOfWorkflowRequestTableField workflowRequestTableFields;

    public WorkflowRequestTableRecord() {
    }

    public WorkflowRequestTableRecord(
           java.lang.Integer recordOrder,
           greedc.ws.client.oa.ArrayOfWorkflowRequestTableField workflowRequestTableFields) {
           this.recordOrder = recordOrder;
           this.workflowRequestTableFields = workflowRequestTableFields;
    }


    /**
     * Gets the recordOrder value for this WorkflowRequestTableRecord.
     * 
     * @return recordOrder
     */
    public java.lang.Integer getRecordOrder() {
        return recordOrder;
    }


    /**
     * Sets the recordOrder value for this WorkflowRequestTableRecord.
     * 
     * @param recordOrder
     */
    public void setRecordOrder(java.lang.Integer recordOrder) {
        this.recordOrder = recordOrder;
    }


    /**
     * Gets the workflowRequestTableFields value for this WorkflowRequestTableRecord.
     * 
     * @return workflowRequestTableFields
     */
    public greedc.ws.client.oa.ArrayOfWorkflowRequestTableField getWorkflowRequestTableFields() {
        return workflowRequestTableFields;
    }


    /**
     * Sets the workflowRequestTableFields value for this WorkflowRequestTableRecord.
     * 
     * @param workflowRequestTableFields
     */
    public void setWorkflowRequestTableFields(greedc.ws.client.oa.ArrayOfWorkflowRequestTableField workflowRequestTableFields) {
        this.workflowRequestTableFields = workflowRequestTableFields;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WorkflowRequestTableRecord)) return false;
        WorkflowRequestTableRecord other = (WorkflowRequestTableRecord) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.recordOrder==null && other.getRecordOrder()==null) || 
             (this.recordOrder!=null &&
              this.recordOrder.equals(other.getRecordOrder()))) &&
            ((this.workflowRequestTableFields==null && other.getWorkflowRequestTableFields()==null) || 
             (this.workflowRequestTableFields!=null &&
              this.workflowRequestTableFields.equals(other.getWorkflowRequestTableFields())));
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
        if (getRecordOrder() != null) {
            _hashCode += getRecordOrder().hashCode();
        }
        if (getWorkflowRequestTableFields() != null) {
            _hashCode += getWorkflowRequestTableFields().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WorkflowRequestTableRecord.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "WorkflowRequestTableRecord"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordOrder");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "recordOrder"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowRequestTableFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "workflowRequestTableFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "ArrayOfWorkflowRequestTableField"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
