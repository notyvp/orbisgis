//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.29 at 03:37:47 PM CET 
//


package net.opengis.se._2_0.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LabelType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LabelType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/se/2.0/core}StyledText"/>
 *         &lt;element ref="{http://www.opengis.net/se/2.0/core}HorizontalAlignment" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/se/2.0/core}VerticalAlignment" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="uom" type="{http://www.opengis.net/se/2.0/core}UomType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabelType", propOrder = {
    "styledText",
    "horizontalAlignment",
    "verticalAlignment"
})
@XmlSeeAlso({
    PointLabelType.class,
    LineLabelType.class
})
public abstract class LabelType {

    @XmlElement(name = "StyledText", required = true)
    protected StyledTextType styledText;
    @XmlElement(name = "HorizontalAlignment")
    protected ParameterValueType horizontalAlignment;
    @XmlElement(name = "VerticalAlignment")
    protected ParameterValueType verticalAlignment;
    @XmlAttribute(name = "uom")
    protected String uom;

    /**
     * Gets the value of the styledText property.
     * 
     * @return
     *     possible object is
     *     {@link StyledTextType }
     *     
     */
    public StyledTextType getStyledText() {
        return styledText;
    }

    /**
     * Sets the value of the styledText property.
     * 
     * @param value
     *     allowed object is
     *     {@link StyledTextType }
     *     
     */
    public void setStyledText(StyledTextType value) {
        this.styledText = value;
    }

    /**
     * Gets the value of the horizontalAlignment property.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getHorizontalAlignment() {
        return horizontalAlignment;
    }

    /**
     * Sets the value of the horizontalAlignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setHorizontalAlignment(ParameterValueType value) {
        this.horizontalAlignment = value;
    }

    /**
     * Gets the value of the verticalAlignment property.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getVerticalAlignment() {
        return verticalAlignment;
    }

    /**
     * Sets the value of the verticalAlignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setVerticalAlignment(ParameterValueType value) {
        this.verticalAlignment = value;
    }

    /**
     * Gets the value of the uom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUom() {
        return uom;
    }

    /**
     * Sets the value of the uom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUom(String value) {
        this.uom = value;
    }

}
