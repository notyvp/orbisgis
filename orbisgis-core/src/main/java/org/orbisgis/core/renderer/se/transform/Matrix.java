/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.orbisgis.core.renderer.se.transform;

import java.awt.geom.AffineTransform;
import javax.xml.bind.JAXBElement;
import org.gdms.data.feature.Feature;
import org.orbisgis.core.map.MapTransform;

import org.orbisgis.core.renderer.persistance.se.MatrixType;
import org.orbisgis.core.renderer.persistance.se.ObjectFactory;
import org.orbisgis.core.renderer.se.SeExceptions.InvalidStyle;
import org.orbisgis.core.renderer.se.common.Uom;
import org.orbisgis.core.renderer.se.parameter.ParameterException;
import org.orbisgis.core.renderer.se.parameter.SeParameterFactory;
import org.orbisgis.core.renderer.se.parameter.real.RealLiteral;
import org.orbisgis.core.renderer.se.parameter.real.RealParameter;
import org.orbisgis.core.renderer.se.parameter.real.RealParameterContext;

/**
 * Affine Transformation based on RealParameters
 * Warning: conversion to pixel unit will give strange behavior !
 * @author maxence
 */
public final class Matrix implements Transformation {


	private final double DEF_A = 1.0;
	private final double DEF_B = 0.0;
	private final double DEF_C = 0.0;
	private final double DEF_D = 1.0;
	private final double DEF_E = 0.0;
	private final double DEF_F = 0.0;
    /**
     * Create an identity matrix
     *
     */
    public Matrix() {
        setA(new RealLiteral(DEF_A));
        setB(new RealLiteral(DEF_B));
        setC(new RealLiteral(DEF_C));
        setD(new RealLiteral(DEF_D));
        setE(new RealLiteral(DEF_E));
        setF(new RealLiteral(DEF_F));
    }

    public Matrix(double a, double b, double c, double d, double e, double f) {
        setA(new RealLiteral(a));
        setB(new RealLiteral(b));
        setC(new RealLiteral(c));
        setD(new RealLiteral(d));
        setE(new RealLiteral(e));
        setF(new RealLiteral(f));
    }

    /**
     * a null cell means new RealLiteral(0.0)
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     */
    public Matrix(RealParameter a, RealParameter b, RealParameter c,
            RealParameter d, RealParameter e, RealParameter f) {
		this();
        if (a != null) {
			setA(a);
        }
        if (b != null) {
			setB(b);
        }
        if (c != null) {
			setC(c);
        }
        if (d != null) {
			setD(d);
        }
        if (e != null) {
			setE(e);
        }
        if (f != null) {
			setF(f);
        }
    }

    Matrix(MatrixType m) throws InvalidStyle {
		this();
        if (m.getA() != null)
            this.setA(SeParameterFactory.createRealParameter(m.getA()));
        if (m.getB() != null)
            this.setB(SeParameterFactory.createRealParameter(m.getB()));
        if (m.getC() != null)
            this.setC(SeParameterFactory.createRealParameter(m.getC()));
        if (m.getD() != null)
            this.setD(SeParameterFactory.createRealParameter(m.getD()));
        if (m.getE() != null)
            this.setE(SeParameterFactory.createRealParameter(m.getE()));
        if (m.getF() != null)
            this.setF(SeParameterFactory.createRealParameter(m.getF()));
    }

    public RealParameter getA() {
        return a;
    }

    public void setA(RealParameter a) {
        if (a == null) {
            a = new RealLiteral(0.0);
        }
        this.a = a;
		this.a.setContext(RealParameterContext.realContext);
    }

    public RealParameter getB() {
        return b;
    }

    public void setB(RealParameter b) {
        if (b == null) {
            b = new RealLiteral(0.0);
        }
        this.b = b;
		this.b.setContext(RealParameterContext.realContext);
    }

    public RealParameter getC() {
        return c;
    }

    public void setC(RealParameter c) {
        if (c == null) {
            c = new RealLiteral(0.0);
        }
        this.c = c;
		this.c.setContext(RealParameterContext.realContext);
    }

    public RealParameter getD() {
        return d;
    }

    public void setD(RealParameter d) {
        if (d == null) {
            d = new RealLiteral(0.0);
        }
        this.d = d;
		this.d.setContext(RealParameterContext.realContext);
    }

    public RealParameter getE() {
        return e;
    }

    public void setE(RealParameter e) {
        if (e == null) {
            e = new RealLiteral(0.0);
        }
        this.e = e;
		this.e.setContext(RealParameterContext.realContext);
    }

    public RealParameter getF() {
        return f;
    }

    public void setF(RealParameter f) {
        if (f == null) {
            f = new RealLiteral(0.0);
        }
        this.f = f;
		this.f.setContext(RealParameterContext.realContext);
    }

    @Override
    public boolean dependsOnFeature(){
        return (a.dependsOnFeature()
                || b.dependsOnFeature()
                || c.dependsOnFeature()
                || d.dependsOnFeature()
                ||e.dependsOnFeature()
                ||f.dependsOnFeature());
    }

    @Override
    public AffineTransform getAffineTransform(Feature feat, Uom uom, MapTransform mt, Double width, Double height) throws ParameterException {
        return new AffineTransform(
                //Uom.toPixel(a.getValue(feat), uom, mt.getDpi(), mt.getScaleDenominator(), null),
				a.getValue(feat),
				b.getValue(feat),
				c.getValue(feat),
                //Uom.toPixel(b.getValue(feat), uom, mt.getDpi(), mt.getScaleDenominator(), null),
                //Uom.toPixel(c.getValue(feat), uom, mt.getDpi(), mt.getScaleDenominator(), null),
                //Uom.toPixel(d.getValue(feat), uom, mt.getDpi(), mt.getScaleDenominator(), null),
				d.getValue(feat),
                Uom.toPixel(e.getValue(feat), uom, mt.getDpi(), mt.getScaleDenominator(), width),
                Uom.toPixel(f.getValue(feat), uom, mt.getDpi(), mt.getScaleDenominator(), height));
    }

    @Override
    public boolean allowedForGeometries() {
        return false;
    }

    /**
     * This method simplifiy the matrix.
     * Every matrix element which doesn't depends on a feature is converted to a single RealLiteral
     *
     * @throws ParameterException when somethung wen wrong...
     */
    public void simplify() throws ParameterException {
        if (!a.dependsOnFeature()) {
            setA(new RealLiteral(a.getValue(null)));
        }
        if (!b.dependsOnFeature()) {
            setB(new RealLiteral(b.getValue(null)));
        }
        if (!c.dependsOnFeature()) {
            setC(new RealLiteral(c.getValue(null)));
        }
        if (!d.dependsOnFeature()) {
            setD(new RealLiteral(d.getValue(null)));
        }
        if (!e.dependsOnFeature()) {
            setE(new RealLiteral(e.getValue(null)));
        }
        if (!f.dependsOnFeature()) {
            setF(new RealLiteral(f.getValue(null)));
        }
    }

    @Override
    public JAXBElement<?> getJAXBElement(){
        MatrixType m = this.getJAXBType();
        
        ObjectFactory of = new ObjectFactory();
        return of.createMatrix(m);
    }

    @Override
    public MatrixType getJAXBType(){
        MatrixType m = new MatrixType();
        m.setA(a.getJAXBParameterValueType());
        m.setB(b.getJAXBParameterValueType());
        m.setC(c.getJAXBParameterValueType());
        m.setD(d.getJAXBParameterValueType());
        m.setE(e.getJAXBParameterValueType());
        m.setF(f.getJAXBParameterValueType());

        return m;
    }

    private RealParameter a;
    private RealParameter b;
    private RealParameter c;
    private RealParameter d;
    private RealParameter e;
    private RealParameter f;
}
