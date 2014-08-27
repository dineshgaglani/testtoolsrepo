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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WebTestResultPageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WebTestResultPageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WebRequestResult" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}WebRequestResultType"/>
 *         &lt;element name="RedirectedPages" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RedirectedPage" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}WebTestResultRedirectedPageType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WebTestResultPageType", propOrder = {
    "webRequestResult",
    "redirectedPages"
})
public class WebTestResultPageType {

    @XmlElement(name = "WebRequestResult", required = true)
    protected WebRequestResultType webRequestResult;
    @XmlElement(name = "RedirectedPages")
    protected RedirectedPages redirectedPages;

    /**
     * Gets the value of the webRequestResult property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.WebRequestResultType }
     *     
     */
    public WebRequestResultType getWebRequestResult() {
        return webRequestResult;
    }

    /**
     * Sets the value of the webRequestResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.WebRequestResultType }
     *     
     */
    public void setWebRequestResult(WebRequestResultType value) {
        this.webRequestResult = value;
    }

    /**
     * Gets the value of the redirectedPages property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.WebTestResultPageType.RedirectedPages }
     *     
     */
    public RedirectedPages getRedirectedPages() {
        return redirectedPages;
    }

    /**
     * Sets the value of the redirectedPages property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.WebTestResultPageType.RedirectedPages }
     *     
     */
    public void setRedirectedPages(RedirectedPages value) {
        this.redirectedPages = value;
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
     *         &lt;element name="RedirectedPage" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}WebTestResultRedirectedPageType" maxOccurs="unbounded" minOccurs="0"/>
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
        "redirectedPage"
    })
    public static class RedirectedPages {

        @XmlElement(name = "RedirectedPage")
        protected List<WebTestResultRedirectedPageType> redirectedPage;

        /**
         * Gets the value of the redirectedPage property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the redirectedPage property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRedirectedPage().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link com.expedia.aggregator.data.WebTestResultRedirectedPageType }
         * 
         * 
         */
        public List<WebTestResultRedirectedPageType> getRedirectedPage() {
            if (redirectedPage == null) {
                redirectedPage = new ArrayList<WebTestResultRedirectedPageType>();
            }
            return this.redirectedPage;
        }

    }

}
