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
 * Copyright (C) 2015-2017 CNRS (Lab-STICC UMR CNRS 6285)
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
package org.orbisgis.coremap.renderer.se.fill;

import net.opengis.se._2_0.thematic.DensityFillType;
import net.opengis.se._2_0.thematic.ObjectFactory;

import org.orbisgis.coremap.map.MapTransform;
import org.orbisgis.coremap.renderer.se.GraphicNode;
import org.orbisgis.coremap.renderer.se.SeExceptions.InvalidStyle;
import org.orbisgis.coremap.renderer.se.SymbolizerNode;
import org.orbisgis.coremap.renderer.se.graphic.GraphicCollection;
import org.orbisgis.coremap.renderer.se.parameter.ParameterException;
import org.orbisgis.coremap.renderer.se.parameter.SeParameterFactory;
import org.orbisgis.coremap.renderer.se.parameter.real.RealLiteral;
import org.orbisgis.coremap.renderer.se.parameter.real.RealParameter;
import org.orbisgis.coremap.renderer.se.parameter.real.RealParameterContext;
import org.orbisgis.coremap.renderer.se.stroke.PenStroke;

import javax.xml.bind.JAXBElement;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A {@code Fill} implementation where the content of a shape is painted according 
 * to a given density and to a given mark or hatch type.</p>
 * <p>If the hatches are used, ie if {@code isHatched()) is {@code true}, the inner
 * {@code PenStroke} and orientation are used. Otherwise, the shape is filled with 
 * repeated mark, registered as a {@code GraphicCollection} instance.</p>
 * <p>In every cases, the needed coverage percentage must be specified. If not set,
 * It will be defaulted to {@code DEFAULT_PERCENTAGE}.
 * @author Alexis Guéganno, Maxence Laurent
 */
public final class DensityFill extends Fill implements GraphicNode {

    private boolean isHatched;
    private PenStroke hatches;
    private RealParameter orientation;
    private GraphicCollection mark;
    private RealParameter percentageCovered;
    //Some constants we don't want to be considered as magic numbers.
    private static final double ONE_HUNDRED = 100;
    private static final double FIFTY = 50;
    private static final double ONE_HALF= 0.5;

    /**
     * The default covered percentage.
     */
    public static final double DEFAULT_PERCENTAGE = 0.2;

    /**
     * Build a default {@code DensityFill}
     */
    public DensityFill() {
        this.setHatches(new PenStroke());
        this.setHatchesOrientation(new RealLiteral(HatchedFill.DEFAULT_ALPHA));
        this.setPercentageCovered(new RealLiteral(DEFAULT_PERCENTAGE));
    }

    /**
     * Build a new {@code DensityFill}, using the {@code JAXBElement} given in
     * argument.
     * @param f
     * @throws org.orbisgis.coremap.renderer.se.SeExceptions.InvalidStyle
     */
    DensityFill(JAXBElement<DensityFillType> f) throws InvalidStyle {

        DensityFillType t = f.getValue();

        if (t.getPenStroke() != null) {
            this.setHatches(new PenStroke(t.getPenStroke()));

            if (t.getOrientation() != null) {
                this.setHatchesOrientation(SeParameterFactory.createRealParameter(t.getOrientation()));
            }
        } else if (t.getGraphic() != null) {
            this.setGraphicCollection(new GraphicCollection(t.getGraphic(), this));
        }

        if (t.getPercentage() != null) {
            this.setPercentageCovered(SeParameterFactory.createRealParameter(t.getPercentage()));
        }
    }

    /**
     * Set the {@link PenStroke} used to draw the hatches in this {@code 
     * DensityFill}.
     * @param hatches 
     */
    public void setHatches(PenStroke hatches) {
        this.hatches = hatches;
        if (hatches != null) {
            this.isHatched = true;
            this.setGraphicCollection(null);
            hatches.setParent(this);
        }
    }

    /**
     * Get the {@link PenStroke} used to draw the hatches in this {@code 
     * DensityFill}.
     * @return 
     */
    public PenStroke getHatches() {
        return hatches;
    }

    /**
     * Set the orientation of the hatches associated to this {@code DensityFill}.
     * @param orientation angle in degree
     */
    public void setHatchesOrientation(RealParameter orientation) {
        this.orientation = orientation;
        if (this.orientation != null) {
            this.orientation.setContext(RealParameterContext.REAL_CONTEXT);
            this.orientation.setParent(this);
        }
    }

    /**
     * Get the orientation of the hatches associated to this {@code DensityFill}.
     * @return 
     */
    public RealParameter getHatchesOrientation() {
        return orientation;
    }

    @Override
    public void setGraphicCollection(GraphicCollection mark) {
        this.mark = mark;
        if (mark != null) {
            this.isHatched = false;
            mark.setParent(this);
            setHatches(null);
        }
    }

    @Override
    public GraphicCollection getGraphicCollection() {
        return mark;
    }

    /**
     * After using this method, marks will be preferred on hatches to render this
     * {@code DensityFill}
     */
    public void useMarks() {
        isHatched = false;
    }

    /**
     * 
     * @return {@code true} if hatches are used to render this {@code 
     * DensityFill}, false otherwise.
     */
    public boolean useHatches() {
        return isHatched;
    }

    /**
     *
     * @param percent percentage covered by the marks/hatches [0;100]
     */
    public void setPercentageCovered(RealParameter percent) {
        this.percentageCovered = percent;
        if (this.percentageCovered != null) {
            this.percentageCovered.setContext(RealParameterContext.PERCENTAGE_CONTEXT);
            this.percentageCovered.setParent(this);
        }
    }

    /**
     * Get the percentage covered by the marks/hatches.
     * @return 
     * A {@code RealParameter} that is in a {@link RealParameterContext#PERCENTAGE_CONTEXT}
     * if not null.
     */
    public RealParameter getPercentageCovered() {
        return percentageCovered;
    }

    @Override
    public void draw(Graphics2D g2, Map<String,Object> map, Shape shp, boolean selected, MapTransform mt) throws ParameterException, IOException {

        if (isHatched) {
            double alpha = HatchedFill.DEFAULT_ALPHA;
            double pDist;

            if (this.orientation != null) {
                alpha = this.orientation.getValue(map);
            }

            // Stroke width
            double sWidth = hatches.getWidthInPixel(map, mt);

            double percentage = 0.0;

            if (percentageCovered != null) {
                percentage = percentageCovered.getValue(map) * ONE_HUNDRED;
            }

            if (percentage > ONE_HUNDRED) {
                percentage = ONE_HUNDRED;
            }


            // Perpendiculat dist bw two hatches
            pDist = ONE_HUNDRED * sWidth / percentage;

            HatchedFill.drawHatch(g2, map, shp, selected, mt, alpha, pDist, hatches, 0.0);
        } else {

            Paint painter = getPaint(map, selected, mt);

            if (painter != null) {
                g2.setPaint(painter);
                g2.fill(shp);
            }
        }
    }

    @Override
    public Paint getPaint(Map<String,Object> map, boolean selected, MapTransform mt) throws ParameterException, IOException {
        double percentage = 0.0;

        if (percentageCovered != null) {
            percentage = percentageCovered.getValue(map) * ONE_HUNDRED;
        }

        if (percentage > ONE_HUNDRED) {
            percentage = ONE_HUNDRED;
        }

        if (percentage > ONE_HALF) {
            Paint painter = null;

            if (isHatched && hatches != null) {
                return null;
            } else if (mark != null) {
                Rectangle2D bounds = mark.getBounds(map, selected, mt);

                double ratio = Math.sqrt(ONE_HUNDRED / percentage);
                double gapX =  bounds.getWidth()*ratio - bounds.getWidth();
                double gapY =  bounds.getHeight()*ratio - bounds.getHeight();

                painter = GraphicFill.getPaint(map, selected, mt, mark, gapX, gapY, bounds);
            } else {
                throw new ParameterException("Neither marks or hatches are defined");
            }
            return painter;
        }
        return null;
    }

    private double getTextureSize(double markSize, double percentage) {
        double size = ONE_HUNDRED * (markSize) / percentage;

        if (percentage > FIFTY) {
            size -= (size - markSize) / 2.0;
        }
        return size + ONE_HALF;
    }

    @Override
    public DensityFillType getJAXBType() {
        DensityFillType f = new DensityFillType();

        if (isHatched) {
            if (hatches != null) {
                f.setPenStroke(hatches.getJAXBType());
            }
            if (orientation != null) {
                f.setOrientation(orientation.getJAXBParameterValueType());
            }
        } else {
            if (mark != null) {
                f.setGraphic(mark.getJAXBElement());
            }
        }

        if (percentageCovered != null) {
            f.setPercentage(percentageCovered.getJAXBParameterValueType());
        }

        return f;


    }

    @Override
    public List<SymbolizerNode> getChildren() {
        List<SymbolizerNode> ls = new ArrayList<SymbolizerNode>();
        if (isHatched) {
            if (hatches != null) {
                ls.add(hatches);
            }
            if (orientation != null) {
                ls.add(orientation);
            }
        } else {
            if (mark != null) {
                ls.add(mark);
            }
        }
        if (percentageCovered != null) {
            ls.add(percentageCovered);
        }
        return ls;
    }

    @Override
    public JAXBElement<DensityFillType> getJAXBElement() {
        ObjectFactory of = new ObjectFactory();
        return of.createDensityFill(this.getJAXBType());
    }
}
