//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.29 at 03:37:47 PM CET 
//


package net.opengis.se._2_0.core;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for directionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="directionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="toUpper"/>
 *     &lt;enumeration value="toLower"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "directionType")
@XmlEnum
public enum DirectionType {

    @XmlEnumValue("toUpper")
    TO_UPPER("toUpper"),
    @XmlEnumValue("toLower")
    TO_LOWER("toLower");
    private final String value;

    DirectionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DirectionType fromValue(String v) {
        for (DirectionType c: DirectionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
