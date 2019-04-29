/**
 * ArrayOfArrayOfString.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package greedc.ws.client.oa;

public class ArrayOfArrayOfString  implements java.io.Serializable {
    private greedc.ws.client.oa.ArrayOfString[] arrayOfString;

    public ArrayOfArrayOfString() {
    }

    public ArrayOfArrayOfString(
           greedc.ws.client.oa.ArrayOfString[] arrayOfString) {
           this.arrayOfString = arrayOfString;
    }


    /**
     * Gets the arrayOfString value for this ArrayOfArrayOfString.
     * 
     * @return arrayOfString
     */
    public greedc.ws.client.oa.ArrayOfString[] getArrayOfString() {
        return arrayOfString;
    }


    /**
     * Sets the arrayOfString value for this ArrayOfArrayOfString.
     * 
     * @param arrayOfString
     */
    public void setArrayOfString(greedc.ws.client.oa.ArrayOfString[] arrayOfString) {
        this.arrayOfString = arrayOfString;
    }

    public greedc.ws.client.oa.ArrayOfString getArrayOfString(int i) {
        return this.arrayOfString[i];
    }

    public void setArrayOfString(int i, greedc.ws.client.oa.ArrayOfString _value) {
        this.arrayOfString[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArrayOfArrayOfString)) return false;
        ArrayOfArrayOfString other = (ArrayOfArrayOfString) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.arrayOfString==null && other.getArrayOfString()==null) || 
             (this.arrayOfString!=null &&
              java.util.Arrays.equals(this.arrayOfString, other.getArrayOfString())));
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
        if (getArrayOfString() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrayOfString());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrayOfString(), i);
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
        new org.apache.axis.description.TypeDesc(ArrayOfArrayOfString.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("webservices.services.weaver.com.cn", "ArrayOfArrayOfString"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrayOfString");
        elemField.setXmlName(new javax.xml.namespace.QName("webservices.services.weaver.com.cn", "ArrayOfString"));
        elemField.setXmlType(new javax.xml.namespace.QName("webservices.services.weaver.com.cn", "ArrayOfString"));
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
