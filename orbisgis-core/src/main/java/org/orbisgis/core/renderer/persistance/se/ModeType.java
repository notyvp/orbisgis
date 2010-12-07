//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.03 at 12:56:18 PM CET 
//


package org.orbisgis.core.renderer.persistance.se;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ModeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="linear"/>
 *     &lt;enumeration value="cosine"/>
 *     &lt;enumeration value="cubic"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ModeType")
@XmlEnum
public enum ModeType {

    @XmlEnumValue("linear")
    LINEAR("linear"),
    @XmlEnumValue("cosine")
    COSINE("cosine"),
    @XmlEnumValue("cubic")
    CUBIC("cubic");
    private final String value;

    ModeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ModeType fromValue(String v) {
        for (ModeType c: ModeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
