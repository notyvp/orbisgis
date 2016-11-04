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
package org.orbisgis.view.toc.actions.cui.legend.panels;

import net.miginfocom.swing.MigLayout;
import org.orbisgis.legend.thematic.EnablesStroke;
import org.orbisgis.legend.thematic.LineParameters;
import org.orbisgis.legend.thematic.OnVertexOnInterior;
import org.orbisgis.legend.thematic.categorize.AbstractCategorizedLegend;
import org.orbisgis.legend.thematic.categorize.CategorizedLine;
import org.orbisgis.legend.thematic.categorize.CategorizedPoint;
import org.orbisgis.legend.thematic.map.MappedLegend;
import org.orbisgis.legend.thematic.recode.AbstractRecodedLegend;
import org.orbisgis.legend.thematic.recode.RecodedLine;
import org.orbisgis.legend.thematic.recode.RecodedPoint;
import org.orbisgis.legend.thematic.uom.SymbolUom;
import org.orbisgis.view.toc.actions.cui.components.CanvasSE;
import org.orbisgis.view.toc.actions.cui.legend.components.AbsFieldsComboBox;
import org.orbisgis.view.toc.actions.cui.legend.components.EnableStrokeCheckBox;
import org.orbisgis.view.toc.actions.cui.legend.components.LineUOMComboBox;
import org.orbisgis.view.toc.actions.cui.legend.components.NonSpatialFieldsComboBox;
import org.orbisgis.view.toc.actions.cui.legend.components.NumericalFieldsComboBox;
import org.orbisgis.view.toc.actions.cui.legend.components.OnVertexOnInteriorButtonGroup;
import org.orbisgis.view.toc.actions.cui.legend.components.SymbolUOMComboBox;
import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;

import javax.sql.DataSource;
import javax.swing.*;

/**
 * Value and Interval Classification settings panel.
 *
 * @author Adam Gouge
 */
public final class SettingsPanel<K, U extends LineParameters> extends JPanel {

    private static final I18n I18N = I18nFactory.getI18n(SettingsPanel.class);

    private MappedLegend<K, U> legend;
    private CanvasSE preview;
    private TablePanel<K, U> tablePanel;

    private AbsFieldsComboBox fieldComboBox;
    private LineUOMComboBox<K, U> lineUOMComboBox;

    /**
     * Constructor
     *
     * @param legend     Legend
     * @param dataSource DataSource for the fields combo box
     * @param preview    Preview
     * @param tablePanel Table Panel
     */
    public SettingsPanel(MappedLegend<K, U> legend,
                         DataSource dataSource,String table,
                         CanvasSE preview,
                         TablePanel<K, U> tablePanel) {
        super(new MigLayout("wrap 2", AbsPanel.COLUMN_CONSTRAINTS));
        this.legend = legend;
        this.preview = preview;
        this.tablePanel = tablePanel;
        if (legend instanceof AbstractCategorizedLegend) {
            this.fieldComboBox = NumericalFieldsComboBox.createInstance(
                    dataSource,table,
                    (AbstractCategorizedLegend) legend);
        } else if (legend instanceof AbstractRecodedLegend) {
            this.fieldComboBox = new NonSpatialFieldsComboBox(
                    dataSource,table,
                    (AbstractRecodedLegend) legend);
        } else {
            throw new IllegalStateException("Settings panels are only available" +
                    " for Classifications for now.");
        }
        this.lineUOMComboBox = new LineUOMComboBox<K, U>(legend, preview, tablePanel);
        init();
    }

    /**
     * Initialize the settings panel.
     */
    private void init() {
        setBorder(BorderFactory.createTitledBorder(I18N.tr("General settings")));

        // Field chooser
        if (legend instanceof AbstractCategorizedLegend) {
            add(new JLabel(I18N.tr(AbsPanel.NUMERIC_FIELD)));
        } else if (legend instanceof AbstractRecodedLegend) {
            add(new JLabel(I18N.tr(AbsPanel.NONSPATIAL_FIELD)));
        } else {
            throw new IllegalStateException("Settings panels are only available" +
                    " for Classifications for now.");
        }
        add(fieldComboBox, AbsPanel.COMBO_BOX_CONSTRAINTS);

        // Unit of measure - line width
        add(new JLabel(I18N.tr(AbsPanel.LINE_WIDTH_UNIT)));
        add(lineUOMComboBox, AbsPanel.COMBO_BOX_CONSTRAINTS);

        beforeFallbackSymbol();

        // Fallback symbol
        add(preview, "span 2, align center");
        add(new JLabel(I18N.tr("Fallback symbol")), "span 2, align center");
    }

    private void beforeFallbackSymbol() {
        if (point()) {
            add(new JLabel(I18N.tr(AbsPanel.SYMBOL_SIZE_UNIT)));
            add(new SymbolUOMComboBox<K, U>((SymbolUom) legend, preview, tablePanel),
                    AbsPanel.COMBO_BOX_CONSTRAINTS);

            add(new JLabel(I18N.tr(AbsPanel.PLACE_SYMBOL_ON)), "span 1 2");
            add(new OnVertexOnInteriorButtonGroup((OnVertexOnInterior) legend, preview, tablePanel),
                    "span 1 2");
        }
        if (!line()) {
            add(new EnableStrokeCheckBox((EnablesStroke) legend, lineUOMComboBox),
                    "span 2, align center");
        }
    }

    private boolean line() {
        return legend instanceof CategorizedLine || legend instanceof RecodedLine;
    }

    private boolean point() {
        return legend instanceof CategorizedPoint || legend instanceof RecodedPoint;
    }

    /**
     * Gets the currently selected field from the fields combo box.
     *
     * @return The currently selected field from the fields combo box
     */
    public String getSelectedField() {
        return (String) fieldComboBox.getSelectedItem();
    }
}
