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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GraphDescriptorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GraphDescriptorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HorizontalZoomRange" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}RangeType" minOccurs="0"/>
 *         &lt;element name="VerticalZoomRange" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}RangeType" minOccurs="0"/>
 *         &lt;element name="PlotDescriptors" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PlotDescriptor" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}PlotDescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="graphName" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GraphDescriptorType", propOrder = {
    "horizontalZoomRange",
    "verticalZoomRange",
    "plotDescriptors"
})
public class GraphDescriptorType {

    @XmlElement(name = "HorizontalZoomRange")
    protected RangeType horizontalZoomRange;
    @XmlElement(name = "VerticalZoomRange")
    protected RangeType verticalZoomRange;
    @XmlElement(name = "PlotDescriptors")
    protected PlotDescriptors plotDescriptors;
    @XmlAttribute(name = "graphName", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String graphName;

    /**
     * Gets the value of the horizontalZoomRange property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.RangeType }
     *     
     */
    public RangeType getHorizontalZoomRange() {
        return horizontalZoomRange;
    }

    /**
     * Sets the value of the horizontalZoomRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.RangeType }
     *     
     */
    public void setHorizontalZoomRange(RangeType value) {
        this.horizontalZoomRange = value;
    }

    /**
     * Gets the value of the verticalZoomRange property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.RangeType }
     *     
     */
    public RangeType getVerticalZoomRange() {
        return verticalZoomRange;
    }

    /**
     * Sets the value of the verticalZoomRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.RangeType }
     *     
     */
    public void setVerticalZoomRange(RangeType value) {
        this.verticalZoomRange = value;
    }

    /**
     * Gets the value of the plotDescriptors property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.GraphDescriptorType.PlotDescriptors }
     *     
     */
    public PlotDescriptors getPlotDescriptors() {
        return plotDescriptors;
    }

    /**
     * Sets the value of the plotDescriptors property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.GraphDescriptorType.PlotDescriptors }
     *     
     */
    public void setPlotDescriptors(PlotDescriptors value) {
        this.plotDescriptors = value;
    }

    /**
     * Gets the value of the graphName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGraphName() {
        return graphName;
    }

    /**
     * Sets the value of the graphName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGraphName(String value) {
        this.graphName = value;
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
     *         &lt;element name="PlotDescriptor" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}PlotDescriptorType" maxOccurs="unbounded" minOccurs="0"/>
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
        "plotDescriptor"
    })
    public static class PlotDescriptors {

        @XmlElement(name = "PlotDescriptor")
        protected List<PlotDescriptorType> plotDescriptor;

        /**
         * Gets the value of the plotDescriptor property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the plotDescriptor property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPlotDescriptor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link com.expedia.aggregator.data.PlotDescriptorType }
         * 
         * 
         */
        public List<PlotDescriptorType> getPlotDescriptor() {
            if (plotDescriptor == null) {
                plotDescriptor = new ArrayList<PlotDescriptorType>();
            }
            return this.plotDescriptor;
        }

    }

}
