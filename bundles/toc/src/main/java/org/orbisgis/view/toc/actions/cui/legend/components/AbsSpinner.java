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
package org.orbisgis.view.toc.actions.cui.legend.components;

import javax.swing.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Root class for spinners. Adds a {@code MouseWheelListener} so that the user
 * can update the value by means of the mouse scroll wheel.
 *
 * @author Adam Gouge
 */
public abstract class AbsSpinner extends JSpinner {

    protected static final double SMALL_STEP = 0.1;
    protected static final double LARGE_STEP = 0.5;

    /**
     * Constructor
     *
     * @param model Spinner model
     */
    public AbsSpinner(final SpinnerNumberModel model) {
        super(model);
        // Enable the mouse scroll wheel on spinners.
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                // The new value is the old one minus the wheel rotation
                // times the spin step.
                Double newValue = ((Double) getValue())
                        - e.getWheelRotation() * getSpinStep();
                // Only update if we are within the given range.
                if (model.getMaximum().compareTo(newValue) >= 0
                        && model.getMinimum().compareTo(newValue) <= 0) {
                    setValue(newValue);
                }
            }
        });
    }

    /**
     * Gets the spin step for the mouse scroll wheel listener.
     *
     * @return The spin step.
     */
    protected double getSpinStep() {
        return SMALL_STEP;
    }
}
