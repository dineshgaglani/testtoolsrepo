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
 * <p>Java class for PlotDescriptorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PlotDescriptorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CounterDescriptor" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}CounterDescriptorType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="colorArgb" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="lineStyle" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="fixedRange" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="showOnGraph" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="isSelected" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="counterMetadata" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlotDescriptorType", propOrder = {
    "counterDescriptor"
})
public class PlotDescriptorType {

    @XmlElement(name = "CounterDescriptor", required = true)
    protected CounterDescriptorType counterDescriptor;
    @XmlAttribute(name = "colorArgb", required = true)
    protected int colorArgb;
    @XmlAttribute(name = "lineStyle", required = true)
    protected int lineStyle;
    @XmlAttribute(name = "fixedRange", required = true)
    protected double fixedRange;
    @XmlAttribute(name = "showOnGraph")
    protected Boolean showOnGraph;
    @XmlAttribute(name = "isSelected")
    protected Boolean isSelected;
    @XmlAttribute(name = "counterMetadata")
    protected String counterMetadata;

    /**
     * Gets the value of the counterDescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.CounterDescriptorType }
     *     
     */
    public CounterDescriptorType getCounterDescriptor() {
        return counterDescriptor;
    }

    /**
     * Sets the value of the counterDescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.CounterDescriptorType }
     *     
     */
    public void setCounterDescriptor(CounterDescriptorType value) {
        this.counterDescriptor = value;
    }

    /**
     * Gets the value of the colorArgb property.
     * 
     */
    public int getColorArgb() {
        return colorArgb;
    }

    /**
     * Sets the value of the colorArgb property.
     * 
     */
    public void setColorArgb(int value) {
        this.colorArgb = value;
    }

    /**
     * Gets the value of the lineStyle property.
     * 
     */
    public int getLineStyle() {
        return lineStyle;
    }

    /**
     * Sets the value of the lineStyle property.
     * 
     */
    public void setLineStyle(int value) {
        this.lineStyle = value;
    }

    /**
     * Gets the value of the fixedRange property.
     * 
     */
    public double getFixedRange() {
        return fixedRange;
    }

    /**
     * Sets the value of the fixedRange property.
     * 
     */
    public void setFixedRange(double value) {
        this.fixedRange = value;
    }

    /**
     * Gets the value of the showOnGraph property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isShowOnGraph() {
        if (showOnGraph == null) {
            return true;
        } else {
            return showOnGraph;
        }
    }

    /**
     * Sets the value of the showOnGraph property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowOnGraph(Boolean value) {
        this.showOnGraph = value;
    }

    /**
     * Gets the value of the isSelected property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsSelected() {
        if (isSelected == null) {
            return false;
        } else {
            return isSelected;
        }
    }

    /**
     * Sets the value of the isSelected property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSelected(Boolean value) {
        this.isSelected = value;
    }

    /**
     * Gets the value of the counterMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCounterMetadata() {
        return counterMetadata;
    }

    /**
     * Sets the value of the counterMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCounterMetadata(String value) {
        this.counterMetadata = value;
    }

}
