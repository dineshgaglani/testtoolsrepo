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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NetworkType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NetworkType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BandwidthInKbps" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="NetworkProfileConfigurationXml" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkType")
public class NetworkType {

    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "BandwidthInKbps", required = true)
    protected float bandwidthInKbps;
    @XmlAttribute(name = "NetworkProfileConfigurationXml")
    protected String networkProfileConfigurationXml;

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
     * Gets the value of the bandwidthInKbps property.
     * 
     */
    public float getBandwidthInKbps() {
        return bandwidthInKbps;
    }

    /**
     * Sets the value of the bandwidthInKbps property.
     * 
     */
    public void setBandwidthInKbps(float value) {
        this.bandwidthInKbps = value;
    }

    /**
     * Gets the value of the networkProfileConfigurationXml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkProfileConfigurationXml() {
        return networkProfileConfigurationXml;
    }

    /**
     * Sets the value of the networkProfileConfigurationXml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkProfileConfigurationXml(String value) {
        this.networkProfileConfigurationXml = value;
    }

}
