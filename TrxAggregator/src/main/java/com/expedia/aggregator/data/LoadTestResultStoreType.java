//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.26 at 08:47:47 PM IST 
//


package com.expedia.aggregator.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoadTestResultStoreType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LoadTestResultStoreType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="Database"/>
 *     &lt;enumeration value="XmlResultFile"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LoadTestResultStoreType")
@XmlEnum
public enum LoadTestResultStoreType {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("Database")
    DATABASE("Database"),
    @XmlEnumValue("XmlResultFile")
    XML_RESULT_FILE("XmlResultFile");
    private final String value;

    LoadTestResultStoreType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LoadTestResultStoreType fromValue(String v) {
        for (LoadTestResultStoreType c: LoadTestResultStoreType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
