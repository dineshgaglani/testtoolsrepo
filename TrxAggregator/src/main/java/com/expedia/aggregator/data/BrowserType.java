//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.26 at 08:47:47 PM IST 
//


package com.expedia.aggregator.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BrowserType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BrowserType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Headers" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}HeadersType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MaxConnections" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BrowserType", propOrder = {
    "headers"
})
public class BrowserType {

    @XmlElement(name = "Headers")
    protected HeadersType headers;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "MaxConnections")
    protected Integer maxConnections;

    /**
     * Gets the value of the headers property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.HeadersType }
     *     
     */
    public HeadersType getHeaders() {
        return headers;
    }

    /**
     * Sets the value of the headers property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.HeadersType }
     *     
     */
    public void setHeaders(HeadersType value) {
        this.headers = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the maxConnections property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxConnections() {
        return maxConnections;
    }

    /**
     * Sets the value of the maxConnections property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxConnections(Integer value) {
        this.maxConnections = value;
    }

}
