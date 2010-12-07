//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.03 at 12:56:18 PM CET 
//


package org.orbisgis.core.renderer.persistance.se;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DensityFillType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DensityFillType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/se}FillType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element ref="{http://www.opengis.net/se}PenStroke"/>
 *             &lt;element ref="{http://www.opengis.net/se}Orientation" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;element ref="{http://www.opengis.net/se}Graphic"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/se}Percentage"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DensityFillType", propOrder = {
    "penStroke",
    "orientation",
    "graphic",
    "percentage"
})
public class DensityFillType
    extends FillType
{

    @XmlElement(name = "PenStroke")
    protected PenStrokeType penStroke;
    @XmlElement(name = "Orientation")
    protected ParameterValueType orientation;
    @XmlElementRef(name = "Graphic", namespace = "http://www.opengis.net/se", type = JAXBElement.class)
    protected JAXBElement<? extends GraphicType> graphic;
    @XmlElement(name = "Percentage", required = true)
    protected ParameterValueType percentage;

    /**
     * Gets the value of the penStroke property.
     * 
     * @return
     *     possible object is
     *     {@link PenStrokeType }
     *     
     */
    public PenStrokeType getPenStroke() {
        return penStroke;
    }

    /**
     * Sets the value of the penStroke property.
     * 
     * @param value
     *     allowed object is
     *     {@link PenStrokeType }
     *     
     */
    public void setPenStroke(PenStrokeType value) {
        this.penStroke = value;
    }

    /**
     * Gets the value of the orientation property.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getOrientation() {
        return orientation;
    }

    /**
     * Sets the value of the orientation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setOrientation(ParameterValueType value) {
        this.orientation = value;
    }

    /**
     * Gets the value of the graphic property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link MarkGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CompositeGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PieChartType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TextGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AlternativeGraphicsType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExternalGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AxisChartType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GraphicType }{@code >}
     *     
     */
    public JAXBElement<? extends GraphicType> getGraphic() {
        return graphic;
    }

    /**
     * Sets the value of the graphic property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link MarkGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CompositeGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PieChartType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TextGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AlternativeGraphicsType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExternalGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AxisChartType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GraphicType }{@code >}
     *     
     */
    public void setGraphic(JAXBElement<? extends GraphicType> value) {
        this.graphic = ((JAXBElement<? extends GraphicType> ) value);
    }

    /**
     * Gets the value of the percentage property.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getPercentage() {
        return percentage;
    }

    /**
     * Sets the value of the percentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setPercentage(ParameterValueType value) {
        this.percentage = value;
    }

}
