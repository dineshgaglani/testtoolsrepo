//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.26 at 08:47:47 PM IST 
//


package com.expedia.aggregator.data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}VirtualChannel"/>
 *         &lt;element ref="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}Timestamp" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "virtualChannel",
    "timestamp"
})
@XmlRootElement(name = "Emulation")
public class Emulation {

    @XmlElement(name = "VirtualChannel", required = true)
    protected VirtualChannel virtualChannel;
    @XmlElement(name = "Timestamp", required = true)
    protected List<Timestamp> timestamp;

    /**
     * Gets the value of the virtualChannel property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.VirtualChannel }
     *     
     */
    public VirtualChannel getVirtualChannel() {
        return virtualChannel;
    }

    /**
     * Sets the value of the virtualChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.VirtualChannel }
     *     
     */
    public void setVirtualChannel(VirtualChannel value) {
        this.virtualChannel = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timestamp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimestamp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link com.expedia.aggregator.data.Timestamp }
     * 
     * 
     */
    public List<Timestamp> getTimestamp() {
        if (timestamp == null) {
            timestamp = new ArrayList<Timestamp>();
        }
        return this.timestamp;
    }

}
