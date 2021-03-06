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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WebTestItemsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WebTestItemsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="Request" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}WebTestRequestType" maxOccurs="unbounded"/>
 *           &lt;element name="TransactionTimer">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="Items" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}WebTestItemsType"/>
 *                   &lt;/sequence>
 *                   &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WebTestItemsType", propOrder = {
    "requestOrTransactionTimer"
})
public class WebTestItemsType {

    @XmlElements({
        @XmlElement(name = "Request", type = WebTestRequestType.class),
        @XmlElement(name = "TransactionTimer", type = TransactionTimer.class)
    })
    protected List<Object> requestOrTransactionTimer;

    /**
     * Gets the value of the requestOrTransactionTimer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requestOrTransactionTimer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequestOrTransactionTimer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link com.expedia.aggregator.data.WebTestRequestType }
     * {@link com.expedia.aggregator.data.WebTestItemsType.TransactionTimer }
     * 
     * 
     */
    public List<Object> getRequestOrTransactionTimer() {
        if (requestOrTransactionTimer == null) {
            requestOrTransactionTimer = new ArrayList<Object>();
        }
        return this.requestOrTransactionTimer;
    }


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
     *         &lt;element name="Items" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}WebTestItemsType"/>
     *       &lt;/sequence>
     *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "items"
    })
    public static class TransactionTimer {

        @XmlElement(name = "Items", required = true)
        protected WebTestItemsType items;
        @XmlAttribute(name = "Name", required = true)
        protected String name;

        /**
         * Gets the value of the items property.
         * 
         * @return
         *     possible object is
         *     {@link com.expedia.aggregator.data.WebTestItemsType }
         *     
         */
        public WebTestItemsType getItems() {
            return items;
        }

        /**
         * Sets the value of the items property.
         * 
         * @param value
         *     allowed object is
         *     {@link com.expedia.aggregator.data.WebTestItemsType }
         *     
         */
        public void setItems(WebTestItemsType value) {
            this.items = value;
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

}
