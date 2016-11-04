/**
 * OrbisGIS is a java GIS application dedicated to research in GIScience.
 * OrbisGIS is developed by the GIS group of the DECIDE team of the 
 * Lab-STICC CNRS laboratory, see <http://www.lab-sticc.fr/>.
 *
 * The GIS group of the DECIDE team is located at :
 *
 * Laboratoire Lab-STICC – CNRS UMR 6285
 * Equipe DECIDE
 * UNIVERSITÉ DE BRETAGNE-SUD
 * Institut Universitaire de Technologie de Vannes
 * 8, Rue Montaigne - BP 561 56017 Vannes Cedex
 * 
 * OrbisGIS is distributed under GPL 3 license.
 *
 * Copyright (C) 2007-2014 CNRS (IRSTV FR CNRS 2488)
 * Copyright (C) 2015-2016 CNRS (Lab-STICC UMR CNRS 6285)
 *
 * This file is part of OrbisGIS.
 *
 * OrbisGIS is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * OrbisGIS is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * OrbisGIS. If not, see <http://www.gnu.org/licenses/>.
 *
 * For more information, please consult: <http://www.orbisgis.org/>
 * or contact directly:
 * info_at_ orbisgis.org
 */

package org.orbisgis.wpsservice.controller.parser;

import net.opengis.ows._2.*;
import net.opengis.wps._2_0.*;
import net.opengis.wps._2_0.LiteralDataType.LiteralDataDomain;
import org.orbisgis.wpsgroovyapi.attributes.DescriptionTypeAttribute;
import org.orbisgis.wpsgroovyapi.attributes.InputAttribute;
import org.orbisgis.wpsgroovyapi.attributes.LiteralDataAttribute;
import org.orbisgis.wpsservice.controller.utils.ObjectAnnotationConverter;
import org.orbisgis.wpsservice.model.*;

import javax.xml.bind.JAXBElement;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sylvain PALOMINOS
 **/

public class LiteralDataParser implements Parser {

    public LiteralDataDomain getLiteralDataDomain(Field f, Object defaultValue){
        LiteralDataDomain literalDataDomain = new LiteralDataDomain();
        literalDataDomain.setDefault(true);

        //Get the type of the field to use it as the input type
        if(f.getType().equals(Integer.class)){
            if(defaultValue != null){
                ValueType valueType = new ValueType();
                valueType.setValue(defaultValue.toString());
                literalDataDomain.setDefaultValue(valueType);
            }
            else {
                AllowedValues allowedValues = new AllowedValues();
                List<Object> objectList = new ArrayList<>();
                RangeType rangeType = new RangeType();

                ValueType maxValue = new ValueType();
                maxValue.setValue(Integer.toString(Integer.MAX_VALUE));
                rangeType.setMaximumValue(maxValue);

                ValueType minValue = new ValueType();
                minValue.setValue(Integer.toString(Integer.MIN_VALUE));
                rangeType.setMinimumValue(minValue);

                ValueType spacingValue = new ValueType();
                spacingValue.setValue(Integer.toString(1));
                rangeType.setSpacing(spacingValue);

                objectList.add(rangeType);
                allowedValues.getValueOrRange().clear();
                allowedValues.getValueOrRange().addAll(objectList);
                literalDataDomain.setAllowedValues(allowedValues);

                ValueType value = new ValueType();
                value.setValue("0");
                literalDataDomain.setDefaultValue(value);
            }

            DomainMetadataType domainMetadataType = new DomainMetadataType();
            domainMetadataType.setValue(DataType.INTEGER.name());
            domainMetadataType.setReference(DataType.INTEGER.getUri().toString());
            literalDataDomain.setDataType(domainMetadataType);
        }
        else if(f.getType().equals(Double.class)){
            AllowedValues allowedValues = new AllowedValues();
            List<Object> objectList = new ArrayList<>();
            RangeType rangeType = new RangeType();
            ValueType maxValue = new ValueType();
            maxValue.setValue(Double.toString(Double.MAX_VALUE));

            rangeType.setMaximumValue(maxValue);
            ValueType minValue = new ValueType();
            minValue.setValue(Double.toString(-Double.MAX_VALUE));

            rangeType.setMaximumValue(minValue);
            ValueType spacingValue = new ValueType();
            spacingValue.setValue(Double.toString(1));

            rangeType.setSpacing(spacingValue);
            objectList.add(rangeType);
            allowedValues.getValueOrRange().clear();
            allowedValues.getValueOrRange().addAll(objectList);

            ValueType valueType = new ValueType();
            valueType.setValue(Double.toString(0));
            literalDataDomain.setDefaultValue(valueType);
            DomainMetadataType domainMetadataType = new DomainMetadataType();
            domainMetadataType.setValue(DataType.DOUBLE.name());
            domainMetadataType.setReference(DataType.DOUBLE.getUri().toString());
            literalDataDomain.setDataType(domainMetadataType);

            //Sets the default value
            if(defaultValue != null) {
                ValueType defaultValueType = new ValueType();
                defaultValueType.setValue(defaultValue.toString());
                literalDataDomain.setDefaultValue(defaultValueType);
            }
        }
        else if(f.getType().equals(String.class)){
            AnyValue anyValue = new AnyValue();
            literalDataDomain.setAnyValue(anyValue);
            //Creates the domain metadata object
            DomainMetadataType domainMetadataType = new DomainMetadataType();
            domainMetadataType.setValue(DataType.STRING.name());
            domainMetadataType.setReference(DataType.STRING.getUri().toString());
            //Sets the domain metadata
            literalDataDomain.setDataType(domainMetadataType);

            //Sets the default value
            if(defaultValue != null) {
                ValueType defaultValueType = new ValueType();
                defaultValueType.setValue(defaultValue.toString());
                literalDataDomain.setDefaultValue(defaultValueType);
            }
        }
        else if(f.getType().equals(Boolean.class)){

            //Create the allowed values object
            AllowedValues allowedValues = new AllowedValues();
            List<Object> objectList = new ArrayList<>();
            ValueType trueValue = new ValueType();
            trueValue.setValue(Boolean.toString(true));
            objectList.add(trueValue);
            ValueType falseValue = new ValueType();
            falseValue.setValue(Boolean.toString(false));
            objectList.add(falseValue);
            allowedValues.getValueOrRange().clear();
            allowedValues.getValueOrRange().addAll(objectList);
            //Adds the allowed values to the literal data domain
            literalDataDomain.setAllowedValues(allowedValues);
            //Sets the default value
            if(defaultValue != null && (Boolean) defaultValue) {
                ValueType defaultValueType = new ValueType();
                defaultValueType.setValue(defaultValue.toString());
                literalDataDomain.setDefaultValue(defaultValueType);
            }

            //Creates the domain metadata object
            DomainMetadataType domainMetadataType = new DomainMetadataType();
            domainMetadataType.setValue(DataType.BOOLEAN.name());
            domainMetadataType.setReference(DataType.BOOLEAN.getUri().toString());
            //Sets the domain metadata
            literalDataDomain.setDataType(domainMetadataType);
        }
        else if(f.getType().equals(Byte.class)){
            AllowedValues allowedValues = new AllowedValues();
            List<Object> objectList = new ArrayList<>();
            RangeType rangeType = new RangeType();
            ValueType maxValue = new ValueType();
            maxValue.setValue(Byte.toString(Byte.MAX_VALUE));

            rangeType.setMaximumValue(maxValue);
            ValueType minValue = new ValueType();
            minValue.setValue(Byte.toString(Byte.MIN_VALUE));

            rangeType.setMaximumValue(minValue);
            ValueType spacingValue = new ValueType();
            spacingValue.setValue(Integer.toString(1));

            rangeType.setSpacing(spacingValue);
            objectList.add(rangeType);
            allowedValues.getValueOrRange().clear();
            allowedValues.getValueOrRange().addAll(objectList);

            ValueType valueType = new ValueType();
            valueType.setValue(Integer.toString(0));
            literalDataDomain.setDefaultValue(valueType);
            DomainMetadataType domainMetadataType = new DomainMetadataType();
            domainMetadataType.setValue(DataType.BYTE.name());
            domainMetadataType.setReference(DataType.BYTE.getUri().toString());
            literalDataDomain.setDataType(domainMetadataType);

            //Sets the default value
            if(defaultValue != null) {
                ValueType defaultValueType = new ValueType();
                defaultValueType.setValue(defaultValue.toString());
                literalDataDomain.setDefaultValue(defaultValueType);
            }
        }
        else if(f.getType().equals(Float.class)){
            AllowedValues allowedValues = new AllowedValues();
            List<Object> objectList = new ArrayList<>();
            RangeType rangeType = new RangeType();
            ValueType maxValue = new ValueType();
            maxValue.setValue(Float.toString(Float.MAX_VALUE));

            rangeType.setMaximumValue(maxValue);
            ValueType minValue = new ValueType();
            minValue.setValue(Float.toString(-Float.MAX_VALUE));

            rangeType.setMaximumValue(minValue);
            ValueType spacingValue = new ValueType();
            spacingValue.setValue(Float.toString(1));

            rangeType.setSpacing(spacingValue);
            objectList.add(rangeType);
            allowedValues.getValueOrRange().clear();
            allowedValues.getValueOrRange().addAll(objectList);

            ValueType valueType = new ValueType();
            valueType.setValue(Float.toString(0));
            literalDataDomain.setDefaultValue(valueType);
            DomainMetadataType domainMetadataType = new DomainMetadataType();
            domainMetadataType.setValue(DataType.FLOAT.name());
            domainMetadataType.setReference(DataType.FLOAT.getUri().toString());
            literalDataDomain.setDataType(domainMetadataType);

            //Sets the default value
            if(defaultValue != null) {
                ValueType defaultValueType = new ValueType();
                defaultValueType.setValue(defaultValue.toString());
                literalDataDomain.setDefaultValue(defaultValueType);
            }
        }
        else if(f.getType().equals(Long.class)){
            AllowedValues allowedValues = new AllowedValues();
            List<Object> objectList = new ArrayList<>();
            RangeType rangeType = new RangeType();
            ValueType maxValue = new ValueType();
            maxValue.setValue(Long.toString(Long.MAX_VALUE));

            rangeType.setMaximumValue(maxValue);
            ValueType minValue = new ValueType();
            minValue.setValue(Long.toString(Long.MIN_VALUE));

            rangeType.setMaximumValue(minValue);
            ValueType spacingValue = new ValueType();
            spacingValue.setValue(Long.toString(1));

            rangeType.setSpacing(spacingValue);
            objectList.add(rangeType);
            allowedValues.getValueOrRange().clear();
            allowedValues.getValueOrRange().addAll(objectList);

            ValueType valueType = new ValueType();
            valueType.setValue(Long.toString(0));
            literalDataDomain.setDefaultValue(valueType);
            DomainMetadataType domainMetadataType = new DomainMetadataType();
            domainMetadataType.setValue(DataType.LONG.name());
            domainMetadataType.setReference(DataType.LONG.getUri().toString());
            literalDataDomain.setDataType(domainMetadataType);

            //Sets the default value
            if(defaultValue != null) {
                ValueType defaultValueType = new ValueType();
                defaultValueType.setValue(defaultValue.toString());
                literalDataDomain.setDefaultValue(defaultValueType);
            }
        }
        else if(f.getType().equals(Short.class)){
            AllowedValues allowedValues = new AllowedValues();
            List<Object> objectList = new ArrayList<>();
            RangeType rangeType = new RangeType();
            ValueType maxValue = new ValueType();
            maxValue.setValue(Short.toString(Short.MAX_VALUE));

            rangeType.setMaximumValue(maxValue);
            ValueType minValue = new ValueType();
            minValue.setValue(Short.toString(Short.MIN_VALUE));

            rangeType.setMaximumValue(minValue);
            ValueType spacingValue = new ValueType();
            spacingValue.setValue(Integer.toString(1));

            rangeType.setSpacing(spacingValue);
            objectList.add(rangeType);
            allowedValues.getValueOrRange().clear();
            allowedValues.getValueOrRange().addAll(objectList);

            ValueType valueType = new ValueType();
            valueType.setValue(Integer.toString(0));
            literalDataDomain.setDefaultValue(valueType);
            DomainMetadataType domainMetadataType = new DomainMetadataType();
            domainMetadataType.setValue(DataType.INTEGER.name());
            domainMetadataType.setReference(DataType.INTEGER.getUri().toString());
            literalDataDomain.setDataType(domainMetadataType);

            //Sets the default value
            if(defaultValue != null) {
                ValueType defaultValueType = new ValueType();
                defaultValueType.setValue(defaultValue.toString());
                literalDataDomain.setDefaultValue(defaultValueType);
            }
        }
        else if(f.getType().equals(Character.class)){
            AllowedValues allowedValues = new AllowedValues();
            List<Object> objectList = new ArrayList<>();
            RangeType rangeType = new RangeType();
            ValueType maxValue = new ValueType();
            maxValue.setValue(Character.toString(Character.MAX_VALUE));

            rangeType.setMaximumValue(maxValue);
            ValueType minValue = new ValueType();
            minValue.setValue(Character.toString(Character.MIN_VALUE));

            rangeType.setMaximumValue(minValue);
            ValueType spacingValue = new ValueType();
            spacingValue.setValue(Integer.toString(1));

            rangeType.setSpacing(spacingValue);
            objectList.add(rangeType);
            allowedValues.getValueOrRange().clear();
            allowedValues.getValueOrRange().addAll(objectList);

            ValueType valueType = new ValueType();
            valueType.setValue(Integer.toString(0));
            literalDataDomain.setDefaultValue(valueType);
            DomainMetadataType domainMetadataType = new DomainMetadataType();
            domainMetadataType.setValue(DataType.UNSIGNED_BYTE.name());
            domainMetadataType.setReference(DataType.UNSIGNED_BYTE.getUri().toString());
            literalDataDomain.setDataType(domainMetadataType);

            //Sets the default value
            if(defaultValue != null) {
                ValueType defaultValueType = new ValueType();
                defaultValueType.setValue(defaultValue.toString());
                literalDataDomain.setDefaultValue(defaultValueType);
            }
        }

        return literalDataDomain;
    }

    @Override
    public InputDescriptionType parseInput(Field f, Object defaultValue, URI processId) {
        InputDescriptionType input = new InputDescriptionType();
        LiteralDataType data = ObjectAnnotationConverter.annotationToObject(f.getAnnotation(LiteralDataAttribute.class),
                getLiteralDataDomain(f, defaultValue));
        JAXBElement<LiteralDataType> jaxbElement = new net.opengis.wps._2_0.ObjectFactory().createLiteralData(data);
        input.setDataDescription(jaxbElement);
        //Instantiate the returned input

        ObjectAnnotationConverter.annotationToObject(f.getAnnotation(InputAttribute.class), input);
        ObjectAnnotationConverter.annotationToObject(f.getAnnotation(DescriptionTypeAttribute.class), input);

        if(input.getIdentifier() == null){
            CodeType codeType = new CodeType();
            codeType.setValue(processId+":input:"+f.getName());
            input.setIdentifier(codeType);
        }
        return input;
    }

    @Override
    public OutputDescriptionType parseOutput(Field f, URI processId) {
        OutputDescriptionType output = new OutputDescriptionType();
        LiteralDataType data = ObjectAnnotationConverter.annotationToObject(f.getAnnotation(LiteralDataAttribute.class),
                getLiteralDataDomain(f, null));
        JAXBElement<LiteralDataType> jaxbElement = new net.opengis.wps._2_0.ObjectFactory().createLiteralData(data);
        output.setDataDescription(jaxbElement);
        //Instantiate the returned input

        ObjectAnnotationConverter.annotationToObject(f.getAnnotation(DescriptionTypeAttribute.class), output);

        if(output.getIdentifier() == null){
            CodeType codeType = new CodeType();
            codeType.setValue(processId+":output:"+f.getName());
            output.setIdentifier(codeType);
        }
        return output;
    }

    @Override
    public Class getAnnotation() {
        return LiteralDataAttribute.class;
    }

}
