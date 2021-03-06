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
 * <p>Java class for UnitTestResultTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UnitTestResultTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NotDataDriven"/>
 *     &lt;enumeration value="DataDrivenTest"/>
 *     &lt;enumeration value="DataDrivenDataRow"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UnitTestResultTypeEnum")
@XmlEnum
public enum UnitTestResultTypeEnum {

    @XmlEnumValue("NotDataDriven")
    NOT_DATA_DRIVEN("NotDataDriven"),
    @XmlEnumValue("DataDrivenTest")
    DATA_DRIVEN_TEST("DataDrivenTest"),
    @XmlEnumValue("DataDrivenDataRow")
    DATA_DRIVEN_DATA_ROW("DataDrivenDataRow");
    private final String value;

    UnitTestResultTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UnitTestResultTypeEnum fromValue(String v) {
        for (UnitTestResultTypeEnum c: UnitTestResultTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
