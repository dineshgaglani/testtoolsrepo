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
 * <p>Java class for DevelopmentServerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DevelopmentServerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="pathToWebSite" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="webApplicationRoot" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DevelopmentServerType")
public class DevelopmentServerType {

    @XmlAttribute(name = "pathToWebSite", required = true)
    protected String pathToWebSite;
    @XmlAttribute(name = "webApplicationRoot", required = true)
    protected String webApplicationRoot;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Gets the value of the pathToWebSite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPathToWebSite() {
        return pathToWebSite;
    }

    /**
     * Sets the value of the pathToWebSite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPathToWebSite(String value) {
        this.pathToWebSite = value;
    }

    /**
     * Gets the value of the webApplicationRoot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebApplicationRoot() {
        return webApplicationRoot;
    }

    /**
     * Sets the value of the webApplicationRoot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebApplicationRoot(String value) {
        this.webApplicationRoot = value;
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

}
