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
 * <p>Java class for TestMixType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TestMixType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PercentageOfTestsStarted"/>
 *     &lt;enumeration value="PercentageOfUsersRunning"/>
 *     &lt;enumeration value="UserPacing"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TestMixType")
@XmlEnum
public enum TestMixType {

    @XmlEnumValue("PercentageOfTestsStarted")
    PERCENTAGE_OF_TESTS_STARTED("PercentageOfTestsStarted"),
    @XmlEnumValue("PercentageOfUsersRunning")
    PERCENTAGE_OF_USERS_RUNNING("PercentageOfUsersRunning"),
    @XmlEnumValue("UserPacing")
    USER_PACING("UserPacing");
    private final String value;

    TestMixType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TestMixType fromValue(String v) {
        for (TestMixType c: TestMixType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
