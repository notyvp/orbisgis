//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.29 at 03:37:47 PM CET 
//


package net.opengis.se._2_0.core;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import net.opengis.se._2_0.thematic.AxisChartType;
import net.opengis.se._2_0.thematic.PieChartType;


/**
 * <p>Java class for PointSymbolizerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PointSymbolizerType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/se/2.0/core}SymbolizerType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/se/2.0/core}Geometry" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/se/2.0/core}Graphic"/>
 *       &lt;/sequence>
 *       &lt;attribute name="uom" type="{http://www.opengis.net/se/2.0/core}UomType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PointSymbolizerType", propOrder = {
    "geometry",
    "graphic"
})
public class PointSymbolizerType
    extends SymbolizerType
{

    @XmlElement(name = "Geometry")
    protected GeometryType geometry;
    @XmlElementRef(name = "Graphic", namespace = "http://www.opengis.net/se/2.0/core", type = JAXBElement.class)
    protected JAXBElement<? extends GraphicType> graphic;
    @XmlAttribute(name = "uom")
    protected String uom;

    /**
     * Gets the value of the geometry property.
     * 
     * @return
     *     possible object is
     *     {@link GeometryType }
     *     
     */
    public GeometryType getGeometry() {
        return geometry;
    }

    /**
     * Sets the value of the geometry property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeometryType }
     *     
     */
    public void setGeometry(GeometryType value) {
        this.geometry = value;
    }

    /**
     * Gets the value of the graphic property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PieChartType }{@code >}
     *     {@link JAXBElement }{@code <}{@link MarkGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExternalGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AxisChartType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PointTextGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GraphicReferenceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CompositeGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AlternativeGraphicsType }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link PieChartType }{@code >}
     *     {@link JAXBElement }{@code <}{@link MarkGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExternalGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AxisChartType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PointTextGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GraphicReferenceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CompositeGraphicType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AlternativeGraphicsType }{@code >}
     *     
     */
    public void setGraphic(JAXBElement<? extends GraphicType> value) {
        this.graphic = value;
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
