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
 * <p>Java class for ManualTestResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ManualTestResultType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}TestResultType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="Comments" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="testFile" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ManualTestResultType", propOrder = {
    "comments"
})
public class ManualTestResultType
    extends TestResultType
{

    @XmlElement(name = "Comments")
    protected Object comments;
    @XmlAttribute(name = "testFile", required = true)
    protected String testFile;

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setComments(Object value) {
        this.comments = value;
    }

    /**
     * Gets the value of the testFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestFile() {
        return testFile;
    }

    /**
     * Sets the value of the testFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestFile(String value) {
        this.testFile = value;
    }

}
