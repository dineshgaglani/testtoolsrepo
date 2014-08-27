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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeploymentItemsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeploymentItemsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DeploymentItem" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="filename" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="outputDirectory" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "DeploymentItemsType", propOrder = {
    "deploymentItem"
})
@XmlSeeAlso({
    TestRunConfiguration.Deployment.class,
    TestSettingsType.Deployment.class,
    BaseTestType.DeploymentItems.class
})
public class DeploymentItemsType {

    @XmlElement(name = "DeploymentItem")
    protected List<DeploymentItem> deploymentItem;

    /**
     * Gets the value of the deploymentItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deploymentItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeploymentItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link com.expedia.aggregator.data.DeploymentItemsType.DeploymentItem }
     * 
     * 
     */
    public List<DeploymentItem> getDeploymentItem() {
        if (deploymentItem == null) {
            deploymentItem = new ArrayList<DeploymentItem>();
        }
        return this.deploymentItem;
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
     *       &lt;attribute name="filename" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="outputDirectory" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class DeploymentItem {

        @XmlAttribute(name = "filename", required = true)
        protected String filename;
        @XmlAttribute(name = "outputDirectory")
        protected String outputDirectory;

        /**
         * Gets the value of the filename property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFilename() {
            return filename;
        }

        /**
         * Sets the value of the filename property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFilename(String value) {
            this.filename = value;
        }

        /**
         * Gets the value of the outputDirectory property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOutputDirectory() {
            return outputDirectory;
        }

        /**
         * Sets the value of the outputDirectory property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOutputDirectory(String value) {
            this.outputDirectory = value;
        }

    }

}
