//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.26 at 08:47:47 PM IST 
//


package com.expedia.aggregator.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NormalLatencyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NormalLatencyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Average" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}MsecType"/>
 *         &lt;element name="Deviation" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}MsecType"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NormalLatencyType", propOrder = {

})
public class NormalLatencyType {

    @XmlElement(name = "Average", required = true)
    protected MsecType average;
    @XmlElement(name = "Deviation", required = true)
    protected MsecType deviation;

    /**
     * Gets the value of the average property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.MsecType }
     *     
     */
    public MsecType getAverage() {
        return average;
    }

    /**
     * Sets the value of the average property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.MsecType }
     *     
     */
    public void setAverage(MsecType value) {
        this.average = value;
    }

    /**
     * Gets the value of the deviation property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.MsecType }
     *     
     */
    public MsecType getDeviation() {
        return deviation;
    }

    /**
     * Sets the value of the deviation property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.MsecType }
     *     
     */
    public void setDeviation(MsecType value) {
        this.deviation = value;
    }

}
