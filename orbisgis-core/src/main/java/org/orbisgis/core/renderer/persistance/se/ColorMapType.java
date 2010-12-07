//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.03 at 12:56:18 PM CET 
//


package org.orbisgis.core.renderer.persistance.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ColorMapType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ColorMapType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.opengis.net/se}Categorize"/>
 *         &lt;element ref="{http://www.opengis.net/se}Interpolate"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ColorMapType", propOrder = {
    "categorize",
    "interpolate"
})
public class ColorMapType {

    @XmlElement(name = "Categorize")
    protected CategorizeType categorize;
    @XmlElement(name = "Interpolate")
    protected InterpolateType interpolate;

    /**
     * Gets the value of the categorize property.
     * 
     * @return
     *     possible object is
     *     {@link CategorizeType }
     *     
     */
    public CategorizeType getCategorize() {
        return categorize;
    }

    /**
     * Sets the value of the categorize property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategorizeType }
     *     
     */
    public void setCategorize(CategorizeType value) {
        this.categorize = value;
    }

    /**
     * Gets the value of the interpolate property.
     * 
     * @return
     *     possible object is
     *     {@link InterpolateType }
     *     
     */
    public InterpolateType getInterpolate() {
        return interpolate;
    }

    /**
     * Sets the value of the interpolate property.
     * 
     * @param value
     *     allowed object is
     *     {@link InterpolateType }
     *     
     */
    public void setInterpolate(InterpolateType value) {
        this.interpolate = value;
    }

}
