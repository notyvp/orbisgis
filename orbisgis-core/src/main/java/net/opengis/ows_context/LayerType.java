//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.12.15 at 03:38:58 PM CET 
//


package net.opengis.ows_context;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import net.opengis.fes._2.FilterType;


/**
 * <p>Java class for LayerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LayerType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/ows-context}AbstractResourceType">
 *       &lt;sequence>
 *         &lt;element name="Server" type="{http://www.opengis.net/ows-context}ServerType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DimensionList" type="{http://www.opengis.net/ows-context}DimensionListType" minOccurs="0"/>
 *         &lt;element name="ResponseCRS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ParameterList" type="{http://www.opengis.net/ows-context}ParameterListType" minOccurs="0"/>
 *         &lt;element name="Depth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Resx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Resy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Resz" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaxFeatures" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/fes/2.0}Filter" minOccurs="0"/>
 *         &lt;element name="InlineGeometry" type="{http://www.opengis.net/ows-context}InlineFeatureCollectionType" minOccurs="0"/>
 *         &lt;element name="StyleList" type="{http://www.opengis.net/ows-context}StyleListType" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/ows-context}Layer" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="queryable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="0" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LayerType", propOrder = {
    "server",
    "dimensionList",
    "responseCRS",
    "parameterList",
    "depth",
    "resx",
    "resy",
    "resz",
    "maxFeatures",
    "filter",
    "inlineGeometry",
    "styleList",
    "layer"
})
public class LayerType
    extends AbstractResourceType
{

    @XmlElement(name = "Server")
    protected List<ServerType> server;
    @XmlElement(name = "DimensionList")
    protected DimensionListType dimensionList;
    @XmlElement(name = "ResponseCRS")
    protected String responseCRS;
    @XmlElement(name = "ParameterList")
    protected ParameterListType parameterList;
    @XmlElement(name = "Depth")
    protected String depth;
    @XmlElement(name = "Resx")
    protected String resx;
    @XmlElement(name = "Resy")
    protected String resy;
    @XmlElement(name = "Resz")
    protected String resz;
    @XmlElement(name = "MaxFeatures")
    protected BigInteger maxFeatures;
    @XmlElement(name = "Filter", namespace = "http://www.opengis.net/fes/2.0")
    protected FilterType filter;
    @XmlElement(name = "InlineGeometry")
    protected InlineFeatureCollectionType inlineGeometry;
    @XmlElement(name = "StyleList")
    protected StyleListType styleList;
    @XmlElement(name = "Layer")
    protected List<LayerType> layer;
    @XmlAttribute(name = "queryable")
    protected Boolean queryable;

    /**
     * Gets the value of the server property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the server property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServerType }
     * 
     * 
     */
    public List<ServerType> getServer() {
        if (server == null) {
            server = new ArrayList<ServerType>();
        }
        return this.server;
    }

    /**
     * Gets the value of the dimensionList property.
     * 
     * @return
     *     possible object is
     *     {@link DimensionListType }
     *     
     */
    public DimensionListType getDimensionList() {
        return dimensionList;
    }

    /**
     * Sets the value of the dimensionList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionListType }
     *     
     */
    public void setDimensionList(DimensionListType value) {
        this.dimensionList = value;
    }

    /**
     * Gets the value of the responseCRS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseCRS() {
        return responseCRS;
    }

    /**
     * Sets the value of the responseCRS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseCRS(String value) {
        this.responseCRS = value;
    }

    /**
     * Gets the value of the parameterList property.
     * 
     * @return
     *     possible object is
     *     {@link ParameterListType }
     *     
     */
    public ParameterListType getParameterList() {
        return parameterList;
    }

    /**
     * Sets the value of the parameterList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterListType }
     *     
     */
    public void setParameterList(ParameterListType value) {
        this.parameterList = value;
    }

    /**
     * Gets the value of the depth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepth() {
        return depth;
    }

    /**
     * Sets the value of the depth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepth(String value) {
        this.depth = value;
    }

    /**
     * Gets the value of the resx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResx() {
        return resx;
    }

    /**
     * Sets the value of the resx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResx(String value) {
        this.resx = value;
    }

    /**
     * Gets the value of the resy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResy() {
        return resy;
    }

    /**
     * Sets the value of the resy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResy(String value) {
        this.resy = value;
    }

    /**
     * Gets the value of the resz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResz() {
        return resz;
    }

    /**
     * Sets the value of the resz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResz(String value) {
        this.resz = value;
    }

    /**
     * Gets the value of the maxFeatures property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxFeatures() {
        return maxFeatures;
    }

    /**
     * Sets the value of the maxFeatures property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxFeatures(BigInteger value) {
        this.maxFeatures = value;
    }

    /**
     * Gets the value of the filter property.
     * 
     * @return
     *     possible object is
     *     {@link FilterType }
     *     
     */
    public FilterType getFilter() {
        return filter;
    }

    /**
     * Sets the value of the filter property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterType }
     *     
     */
    public void setFilter(FilterType value) {
        this.filter = value;
    }

    /**
     * Gets the value of the inlineGeometry property.
     * 
     * @return
     *     possible object is
     *     {@link InlineFeatureCollectionType }
     *     
     */
    public InlineFeatureCollectionType getInlineGeometry() {
        return inlineGeometry;
    }

    /**
     * Sets the value of the inlineGeometry property.
     * 
     * @param value
     *     allowed object is
     *     {@link InlineFeatureCollectionType }
     *     
     */
    public void setInlineGeometry(InlineFeatureCollectionType value) {
        this.inlineGeometry = value;
    }

    /**
     * Gets the value of the styleList property.
     * 
     * @return
     *     possible object is
     *     {@link StyleListType }
     *     
     */
    public StyleListType getStyleList() {
        return styleList;
    }

    /**
     * Sets the value of the styleList property.
     * 
     * @param value
     *     allowed object is
     *     {@link StyleListType }
     *     
     */
    public void setStyleList(StyleListType value) {
        this.styleList = value;
    }

    /**
     * Gets the value of the layer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the layer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLayer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LayerType }
     * 
     * 
     */
    public List<LayerType> getLayer() {
        if (layer == null) {
            layer = new ArrayList<LayerType>();
        }
        return this.layer;
    }

    /**
     * Gets the value of the queryable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isQueryable() {
        if (queryable == null) {
            return false;
        } else {
            return queryable;
        }
    }

    /**
     * Sets the value of the queryable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setQueryable(Boolean value) {
        this.queryable = value;
    }

}