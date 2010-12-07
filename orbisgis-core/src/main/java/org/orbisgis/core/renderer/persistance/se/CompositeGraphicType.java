//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.03 at 12:56:18 PM CET 
//


package org.orbisgis.core.renderer.persistance.se;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CompositeGraphicType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CompositeGraphicType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/se}GraphicType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/se}Graphic" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompositeGraphicType", propOrder = {
    "graphic"
})
public class CompositeGraphicType
    extends GraphicType
{

    @XmlElementRef(name = "Graphic", namespace = "http://www.opengis.net/se", type = JAXBElement.class)
    protected List<JAXBElement<? extends GraphicType>> graphic;

    /**
     * Gets the value of the graphic property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the graphic property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGraphic().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link MarkGraphicType }{@code >}
     * {@link JAXBElement }{@code <}{@link CompositeGraphicType }{@code >}
     * {@link JAXBElement }{@code <}{@link PieChartType }{@code >}
     * {@link JAXBElement }{@code <}{@link TextGraphicType }{@code >}
     * {@link JAXBElement }{@code <}{@link AlternativeGraphicsType }{@code >}
     * {@link JAXBElement }{@code <}{@link ExternalGraphicType }{@code >}
     * {@link JAXBElement }{@code <}{@link AxisChartType }{@code >}
     * {@link JAXBElement }{@code <}{@link GraphicType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends GraphicType>> getGraphic() {
        if (graphic == null) {
            graphic = new ArrayList<JAXBElement<? extends GraphicType>>();
        }
        return this.graphic;
    }

}
