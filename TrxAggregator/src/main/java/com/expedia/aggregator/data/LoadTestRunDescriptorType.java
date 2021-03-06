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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoadTestRunDescriptorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LoadTestRunDescriptorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GraphDescriptors" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="GraphDescriptor" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}GraphDescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="isLegendPanelVisible" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="isOverviewPanelVisible" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="isCounterPanelVisible" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="scrollingGraph" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="minMaxGraph" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="showHorizontalGridOnGraph" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="showThresholdsOnGraph" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="showComparison" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="showZoom" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="lockZoom" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="activeConsoleView" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}LoadTestRunDescriptorViewType" default="Graph" />
 *       &lt;attribute name="selectedGraphPanel1" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="selectedGraphPanel2" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="selectedGraphPanel3" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="selectedGraphPanel4" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="graphPanelLayout" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}PanelLayoutType" default="FourGrid" />
 *       &lt;attribute name="tablePanelLayout" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}PanelLayoutType" default="TwoHorizontal" />
 *       &lt;attribute name="selectedTablePanel1" type="{http://www.w3.org/2001/XMLSchema}string" default="Tests" />
 *       &lt;attribute name="selectedTablePanel2" type="{http://www.w3.org/2001/XMLSchema}string" default="Errors" />
 *       &lt;attribute name="selectedTablePanel3" type="{http://www.w3.org/2001/XMLSchema}string" default="Thresholds" />
 *       &lt;attribute name="selectedTablePanel4" type="{http://www.w3.org/2001/XMLSchema}string" default="Transactions" />
 *       &lt;attribute name="controllerName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="isLocalRun" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="testRunId" use="required" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}IDSimpleType" />
 *       &lt;attribute name="repositoryRunId" type="{http://www.w3.org/2001/XMLSchema}int" default="0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoadTestRunDescriptorType", propOrder = {
    "graphDescriptors"
})
public class LoadTestRunDescriptorType {

    @XmlElement(name = "GraphDescriptors")
    protected GraphDescriptors graphDescriptors;
    @XmlAttribute(name = "isLegendPanelVisible")
    protected Boolean isLegendPanelVisible;
    @XmlAttribute(name = "isOverviewPanelVisible")
    protected Boolean isOverviewPanelVisible;
    @XmlAttribute(name = "isCounterPanelVisible")
    protected Boolean isCounterPanelVisible;
    @XmlAttribute(name = "scrollingGraph")
    protected Boolean scrollingGraph;
    @XmlAttribute(name = "minMaxGraph")
    protected Boolean minMaxGraph;
    @XmlAttribute(name = "showHorizontalGridOnGraph")
    protected Boolean showHorizontalGridOnGraph;
    @XmlAttribute(name = "showThresholdsOnGraph")
    protected Boolean showThresholdsOnGraph;
    @XmlAttribute(name = "showComparison")
    protected Boolean showComparison;
    @XmlAttribute(name = "showZoom")
    protected Boolean showZoom;
    @XmlAttribute(name = "lockZoom")
    protected Boolean lockZoom;
    @XmlAttribute(name = "activeConsoleView")
    protected LoadTestRunDescriptorViewType activeConsoleView;
    @XmlAttribute(name = "selectedGraphPanel1")
    protected String selectedGraphPanel1;
    @XmlAttribute(name = "selectedGraphPanel2")
    protected String selectedGraphPanel2;
    @XmlAttribute(name = "selectedGraphPanel3")
    protected String selectedGraphPanel3;
    @XmlAttribute(name = "selectedGraphPanel4")
    protected String selectedGraphPanel4;
    @XmlAttribute(name = "graphPanelLayout")
    protected PanelLayoutType graphPanelLayout;
    @XmlAttribute(name = "tablePanelLayout")
    protected PanelLayoutType tablePanelLayout;
    @XmlAttribute(name = "selectedTablePanel1")
    protected String selectedTablePanel1;
    @XmlAttribute(name = "selectedTablePanel2")
    protected String selectedTablePanel2;
    @XmlAttribute(name = "selectedTablePanel3")
    protected String selectedTablePanel3;
    @XmlAttribute(name = "selectedTablePanel4")
    protected String selectedTablePanel4;
    @XmlAttribute(name = "controllerName")
    protected String controllerName;
    @XmlAttribute(name = "isLocalRun")
    protected Boolean isLocalRun;
    @XmlAttribute(name = "testRunId", required = true)
    protected String testRunId;
    @XmlAttribute(name = "repositoryRunId")
    protected Integer repositoryRunId;

    /**
     * Gets the value of the graphDescriptors property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.LoadTestRunDescriptorType.GraphDescriptors }
     *     
     */
    public GraphDescriptors getGraphDescriptors() {
        return graphDescriptors;
    }

    /**
     * Sets the value of the graphDescriptors property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.LoadTestRunDescriptorType.GraphDescriptors }
     *     
     */
    public void setGraphDescriptors(GraphDescriptors value) {
        this.graphDescriptors = value;
    }

    /**
     * Gets the value of the isLegendPanelVisible property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsLegendPanelVisible() {
        if (isLegendPanelVisible == null) {
            return true;
        } else {
            return isLegendPanelVisible;
        }
    }

    /**
     * Sets the value of the isLegendPanelVisible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsLegendPanelVisible(Boolean value) {
        this.isLegendPanelVisible = value;
    }

    /**
     * Gets the value of the isOverviewPanelVisible property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsOverviewPanelVisible() {
        if (isOverviewPanelVisible == null) {
            return true;
        } else {
            return isOverviewPanelVisible;
        }
    }

    /**
     * Sets the value of the isOverviewPanelVisible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsOverviewPanelVisible(Boolean value) {
        this.isOverviewPanelVisible = value;
    }

    /**
     * Gets the value of the isCounterPanelVisible property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsCounterPanelVisible() {
        if (isCounterPanelVisible == null) {
            return true;
        } else {
            return isCounterPanelVisible;
        }
    }

    /**
     * Sets the value of the isCounterPanelVisible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsCounterPanelVisible(Boolean value) {
        this.isCounterPanelVisible = value;
    }

    /**
     * Gets the value of the scrollingGraph property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isScrollingGraph() {
        if (scrollingGraph == null) {
            return false;
        } else {
            return scrollingGraph;
        }
    }

    /**
     * Sets the value of the scrollingGraph property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setScrollingGraph(Boolean value) {
        this.scrollingGraph = value;
    }

    /**
     * Gets the value of the minMaxGraph property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isMinMaxGraph() {
        if (minMaxGraph == null) {
            return false;
        } else {
            return minMaxGraph;
        }
    }

    /**
     * Sets the value of the minMaxGraph property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMinMaxGraph(Boolean value) {
        this.minMaxGraph = value;
    }

    /**
     * Gets the value of the showHorizontalGridOnGraph property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isShowHorizontalGridOnGraph() {
        if (showHorizontalGridOnGraph == null) {
            return true;
        } else {
            return showHorizontalGridOnGraph;
        }
    }

    /**
     * Sets the value of the showHorizontalGridOnGraph property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowHorizontalGridOnGraph(Boolean value) {
        this.showHorizontalGridOnGraph = value;
    }

    /**
     * Gets the value of the showThresholdsOnGraph property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isShowThresholdsOnGraph() {
        if (showThresholdsOnGraph == null) {
            return false;
        } else {
            return showThresholdsOnGraph;
        }
    }

    /**
     * Sets the value of the showThresholdsOnGraph property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowThresholdsOnGraph(Boolean value) {
        this.showThresholdsOnGraph = value;
    }

    /**
     * Gets the value of the showComparison property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isShowComparison() {
        if (showComparison == null) {
            return true;
        } else {
            return showComparison;
        }
    }

    /**
     * Sets the value of the showComparison property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowComparison(Boolean value) {
        this.showComparison = value;
    }

    /**
     * Gets the value of the showZoom property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isShowZoom() {
        if (showZoom == null) {
            return true;
        } else {
            return showZoom;
        }
    }

    /**
     * Sets the value of the showZoom property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowZoom(Boolean value) {
        this.showZoom = value;
    }

    /**
     * Gets the value of the lockZoom property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isLockZoom() {
        if (lockZoom == null) {
            return true;
        } else {
            return lockZoom;
        }
    }

    /**
     * Sets the value of the lockZoom property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLockZoom(Boolean value) {
        this.lockZoom = value;
    }

    /**
     * Gets the value of the activeConsoleView property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.LoadTestRunDescriptorViewType }
     *     
     */
    public LoadTestRunDescriptorViewType getActiveConsoleView() {
        if (activeConsoleView == null) {
            return LoadTestRunDescriptorViewType.GRAPH;
        } else {
            return activeConsoleView;
        }
    }

    /**
     * Sets the value of the activeConsoleView property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.LoadTestRunDescriptorViewType }
     *     
     */
    public void setActiveConsoleView(LoadTestRunDescriptorViewType value) {
        this.activeConsoleView = value;
    }

    /**
     * Gets the value of the selectedGraphPanel1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedGraphPanel1() {
        return selectedGraphPanel1;
    }

    /**
     * Sets the value of the selectedGraphPanel1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedGraphPanel1(String value) {
        this.selectedGraphPanel1 = value;
    }

    /**
     * Gets the value of the selectedGraphPanel2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedGraphPanel2() {
        return selectedGraphPanel2;
    }

    /**
     * Sets the value of the selectedGraphPanel2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedGraphPanel2(String value) {
        this.selectedGraphPanel2 = value;
    }

    /**
     * Gets the value of the selectedGraphPanel3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedGraphPanel3() {
        return selectedGraphPanel3;
    }

    /**
     * Sets the value of the selectedGraphPanel3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedGraphPanel3(String value) {
        this.selectedGraphPanel3 = value;
    }

    /**
     * Gets the value of the selectedGraphPanel4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedGraphPanel4() {
        return selectedGraphPanel4;
    }

    /**
     * Sets the value of the selectedGraphPanel4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedGraphPanel4(String value) {
        this.selectedGraphPanel4 = value;
    }

    /**
     * Gets the value of the graphPanelLayout property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.PanelLayoutType }
     *     
     */
    public PanelLayoutType getGraphPanelLayout() {
        if (graphPanelLayout == null) {
            return PanelLayoutType.FOUR_GRID;
        } else {
            return graphPanelLayout;
        }
    }

    /**
     * Sets the value of the graphPanelLayout property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.PanelLayoutType }
     *     
     */
    public void setGraphPanelLayout(PanelLayoutType value) {
        this.graphPanelLayout = value;
    }

    /**
     * Gets the value of the tablePanelLayout property.
     * 
     * @return
     *     possible object is
     *     {@link com.expedia.aggregator.data.PanelLayoutType }
     *     
     */
    public PanelLayoutType getTablePanelLayout() {
        if (tablePanelLayout == null) {
            return PanelLayoutType.TWO_HORIZONTAL;
        } else {
            return tablePanelLayout;
        }
    }

    /**
     * Sets the value of the tablePanelLayout property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.expedia.aggregator.data.PanelLayoutType }
     *     
     */
    public void setTablePanelLayout(PanelLayoutType value) {
        this.tablePanelLayout = value;
    }

    /**
     * Gets the value of the selectedTablePanel1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedTablePanel1() {
        if (selectedTablePanel1 == null) {
            return "Tests";
        } else {
            return selectedTablePanel1;
        }
    }

    /**
     * Sets the value of the selectedTablePanel1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedTablePanel1(String value) {
        this.selectedTablePanel1 = value;
    }

    /**
     * Gets the value of the selectedTablePanel2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedTablePanel2() {
        if (selectedTablePanel2 == null) {
            return "Errors";
        } else {
            return selectedTablePanel2;
        }
    }

    /**
     * Sets the value of the selectedTablePanel2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedTablePanel2(String value) {
        this.selectedTablePanel2 = value;
    }

    /**
     * Gets the value of the selectedTablePanel3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedTablePanel3() {
        if (selectedTablePanel3 == null) {
            return "Thresholds";
        } else {
            return selectedTablePanel3;
        }
    }

    /**
     * Sets the value of the selectedTablePanel3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedTablePanel3(String value) {
        this.selectedTablePanel3 = value;
    }

    /**
     * Gets the value of the selectedTablePanel4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedTablePanel4() {
        if (selectedTablePanel4 == null) {
            return "Transactions";
        } else {
            return selectedTablePanel4;
        }
    }

    /**
     * Sets the value of the selectedTablePanel4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedTablePanel4(String value) {
        this.selectedTablePanel4 = value;
    }

    /**
     * Gets the value of the controllerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControllerName() {
        return controllerName;
    }

    /**
     * Sets the value of the controllerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControllerName(String value) {
        this.controllerName = value;
    }

    /**
     * Gets the value of the isLocalRun property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsLocalRun() {
        if (isLocalRun == null) {
            return true;
        } else {
            return isLocalRun;
        }
    }

    /**
     * Sets the value of the isLocalRun property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsLocalRun(Boolean value) {
        this.isLocalRun = value;
    }

    /**
     * Gets the value of the testRunId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestRunId() {
        return testRunId;
    }

    /**
     * Sets the value of the testRunId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestRunId(String value) {
        this.testRunId = value;
    }

    /**
     * Gets the value of the repositoryRunId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getRepositoryRunId() {
        if (repositoryRunId == null) {
            return  0;
        } else {
            return repositoryRunId;
        }
    }

    /**
     * Sets the value of the repositoryRunId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRepositoryRunId(Integer value) {
        this.repositoryRunId = value;
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
     *         &lt;element name="GraphDescriptor" type="{http://microsoft.com/schemas/VisualStudio/TeamTest/2010}GraphDescriptorType" maxOccurs="unbounded" minOccurs="0"/>
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
        "graphDescriptor"
    })
    public static class GraphDescriptors {

        @XmlElement(name = "GraphDescriptor")
        protected List<GraphDescriptorType> graphDescriptor;

        /**
         * Gets the value of the graphDescriptor property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the graphDescriptor property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getGraphDescriptor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link com.expedia.aggregator.data.GraphDescriptorType }
         * 
         * 
         */
        public List<GraphDescriptorType> getGraphDescriptor() {
            if (graphDescriptor == null) {
                graphDescriptor = new ArrayList<GraphDescriptorType>();
            }
            return this.graphDescriptor;
        }

    }

}
