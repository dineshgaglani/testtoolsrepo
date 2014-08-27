//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.26 at 08:47:47 PM IST 
//


package com.expedia.aggregator.data;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NormalReorderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NormalReorderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Rate" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}PercentType"/>
 *         &lt;element name="Deviation" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MaxPacketLag" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NormalReorderType", propOrder = {
    "rate",
    "deviation",
    "maxPacketLag"
})
public class NormalReorderType {

    @XmlElement(name = "Rate", required = true)
    protected BigDecimal rate;
    @XmlElement(name = "Deviation")
    protected double deviation;
    @XmlElement(name = "MaxPacketLag")
    @XmlSchemaType(name = "unsignedShort")
    protected int maxPacketLag;

    /**
     * Gets the value of the rate property.
     * 
     * @return
     *     possible object is
     *     {@link java.math.BigDecimal }
     *     
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Sets the value of the rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.math.BigDecimal }
     *     
     */
    public void setRate(BigDecimal value) {
        this.rate = value;
    }

    /**
     * Gets the value of the deviation property.
     * 
     */
    public double getDeviation() {
        return deviation;
    }

    /**
     * Sets the value of the deviation property.
     * 
     */
    public void setDeviation(double value) {
        this.deviation = value;
    }

    /**
     * Gets the value of the maxPacketLag property.
     * 
     */
    public int getMaxPacketLag() {
        return maxPacketLag;
    }

    /**
     * Sets the value of the maxPacketLag property.
     * 
     */
    public void setMaxPacketLag(int value) {
        this.maxPacketLag = value;
    }

}
