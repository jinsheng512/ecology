/**
 * DetailBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package greedc.ws.client.qb;

public class DetailBean  implements java.io.Serializable {
    private java.lang.String dingdh;

    private java.lang.String hetwb;

    public DetailBean() {
    }

    public DetailBean(
           java.lang.String dingdh,
           java.lang.String hetwb) {
           this.dingdh = dingdh;
           this.hetwb = hetwb;
    }


    /**
     * Gets the dingdh value for this DetailBean.
     * 
     * @return dingdh
     */
    public java.lang.String getDingdh() {
        return dingdh;
    }


    /**
     * Sets the dingdh value for this DetailBean.
     * 
     * @param dingdh
     */
    public void setDingdh(java.lang.String dingdh) {
        this.dingdh = dingdh;
    }


    /**
     * Gets the hetwb value for this DetailBean.
     * 
     * @return hetwb
     */
    public java.lang.String getHetwb() {
        return hetwb;
    }


    /**
     * Sets the hetwb value for this DetailBean.
     * 
     * @param hetwb
     */
    public void setHetwb(java.lang.String hetwb) {
        this.hetwb = hetwb;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DetailBean)) return false;
        DetailBean other = (DetailBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dingdh==null && other.getDingdh()==null) || 
             (this.dingdh!=null &&
              this.dingdh.equals(other.getDingdh()))) &&
            ((this.hetwb==null && other.getHetwb()==null) || 
             (this.hetwb!=null &&
              this.hetwb.equals(other.getHetwb())));
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
        if (getDingdh() != null) {
            _hashCode += getDingdh().hashCode();
        }
        if (getHetwb() != null) {
            _hashCode += getHetwb().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DetailBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://qibei.server.webservice.ecustom", "DetailBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dingdh");
        elemField.setXmlName(new javax.xml.namespace.QName("http://qibei.server.webservice.ecustom", "dingdh"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hetwb");
        elemField.setXmlName(new javax.xml.namespace.QName("http://qibei.server.webservice.ecustom", "hetwb"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
