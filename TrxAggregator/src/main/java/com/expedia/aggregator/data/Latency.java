//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.26 at 08:47:47 PM IST 
//


package com.expedia.aggregator.data;

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
 *       &lt;choice>
 *         &lt;element name="Fixed" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}FixedLatencyType"/>
 *         &lt;element name="Uniform" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}UniformLatencyType"/>
 *         &lt;element name="Normal" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}NormalLatencyType"/>
 *         &lt;element name="Linear" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}LinearLatencyType"/>
 *         &lt;element name="Burst" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}BurstLatencyType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fixed",
    "uniform",
    "normal",
    "linear",
    "burst"
})
@XmlRootElement(name = "Latency")
public class Latency {

    @XmlElement(name = "Fixed")
    protected FixedLatencyType fixed;
    @XmlElement(name = "Uniform")
    protected UniformLatencyType uniform;
    @XmlElement(name = "Normal")
    protected NormalLatencyType normal;
    @XmlElement(name = "Linear")
    protected LinearLatencyType linear;
    @XmlElement(name = "Burst")
    protected BurstLatencyType burst;

    /**
     * Gets the value of the fixed property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.FixedLatencyType }
     *     
     */
    public FixedLatencyType getFixed() {
        return fixed;
    }

    /**
     * Sets the value of the fixed property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.FixedLatencyType }
     *     
     */
    public void setFixed(FixedLatencyType value) {
        this.fixed = value;
    }

    /**
     * Gets the value of the uniform property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.UniformLatencyType }
     *     
     */
    public UniformLatencyType getUniform() {
        return uniform;
    }

    /**
     * Sets the value of the uniform property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.UniformLatencyType }
     *     
     */
    public void setUniform(UniformLatencyType value) {
        this.uniform = value;
    }

    /**
     * Gets the value of the normal property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.NormalLatencyType }
     *     
     */
    public NormalLatencyType getNormal() {
        return normal;
    }

    /**
     * Sets the value of the normal property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.NormalLatencyType }
     *     
     */
    public void setNormal(NormalLatencyType value) {
        this.normal = value;
    }

    /**
     * Gets the value of the linear property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.LinearLatencyType }
     *     
     */
    public LinearLatencyType getLinear() {
        return linear;
    }

    /**
     * Sets the value of the linear property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.LinearLatencyType }
     *     
     */
    public void setLinear(LinearLatencyType value) {
        this.linear = value;
    }

    /**
     * Gets the value of the burst property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.BurstLatencyType }
     *     
     */
    public BurstLatencyType getBurst() {
        return burst;
    }

    /**
     * Sets the value of the burst property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.BurstLatencyType }
     *     
     */
    public void setBurst(BurstLatencyType value) {
        this.burst = value;
    }

}
