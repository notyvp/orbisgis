/**
 * OrbisGIS is a GIS application dedicated to scientific spatial simulation.
 * This cross-platform GIS is developed at French IRSTV institute and is able to
 * manipulate and create vector and raster spatial information.
 *
 * OrbisGIS is distributed under GPL 3 license. It is produced by the "Atelier SIG"
 * team of the IRSTV Institute <http://www.irstv.fr/> CNRS FR 2488.
 *
 * Copyright (C) 2007-2012 IRSTV (FR CNRS 2488)
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
package org.orbisgis.view.toc.actions.cui.legends;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.EventHandler;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import org.apache.log4j.Logger;
import org.gdms.data.DataSource;
import org.gdms.data.schema.Metadata;
import org.gdms.data.types.TypeFactory;
import org.gdms.driver.DriverException;
import org.orbisgis.core.renderer.se.common.Uom;
import org.orbisgis.legend.Legend;
import org.orbisgis.legend.structure.fill.constant.ConstantSolidFill;
import org.orbisgis.legend.structure.stroke.ConstantColorAndDashesPSLegend;
import org.orbisgis.legend.structure.stroke.constant.ConstantPenStroke;
import org.orbisgis.legend.thematic.uom.StrokeUom;
import org.orbisgis.sif.UIFactory;
import org.orbisgis.sif.UIPanel;
import org.orbisgis.sif.components.ColorPicker;
import org.orbisgis.sif.components.JNumericSpinner;
import org.orbisgis.sif.common.ContainerItemProperties;
import org.orbisgis.view.toc.actions.cui.components.CanvasSE;
import org.orbisgis.view.toc.actions.cui.legend.ILegendPanel;
import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;

/**
 * This class proposes some methods that will be common to all the panels built
 * for unique symbols.
 * @author Alexis Guéganno
 */
public abstract class PnlUniqueSymbolSE extends  AbstractFieldPanel implements ILegendPanel, UIPanel {

        private static final Logger LOGGER = Logger.getLogger("gui."+PnlUniqueSymbolSE.class);
        private static final I18n I18N = I18nFactory.getI18n(PnlUniqueSymbolSE.class);
        private String id;
        private CanvasSE preview;
        private ContainerItemProperties[] strokeUoms;

        /**
         * Rebuild the {@code CanvasSe} instance used to display a preview of
         * the current symbol.
         */
        public void initPreview(){
                Legend leg = getLegend();
                if(leg != null){
                        preview= new CanvasSE(leg.getSymbolizer());
                        preview.repaint();
                }
        }

        /**
         * Gets the {@code CanvasSe} instance used to display a preview of
         * the current symbol.
         * @return
         */
        public CanvasSE getPreview(){
                return preview;
        }

        /**
         * Build a {@code JLabel} from {@code name} with x-alignment set to
         * {@code Component.LEFT_ALIGNMENT}.
         * @param name
         * @return
         */
        public JLabel buildText(String name){
                JLabel c1 = new JLabel(name);
                c1.setAlignmentX(Component.LEFT_ALIGNMENT);
                return c1;
        }

        /**
         * Retrieve a spinner with the wanted listener.
         * @param cps The stroke that will be configured with the spinner.
         * @return
         *      The wanted {@code JNumericSpinner}.
         */
        public JNumericSpinner getLineWidthSpinner(final ConstantPenStroke cps){
                final JNumericSpinner jns = new JNumericSpinner(4, 0, Integer.MAX_VALUE, 0.01);
                ChangeListener cl = EventHandler.create(ChangeListener.class, cps, "lineWidth", "source.value");
                jns.addChangeListener(cl);
                jns.setValue(cps.getLineWidth());
                jns.setMaximumSize(new Dimension(60,30));
                jns.setPreferredSize(new Dimension(60,30));
                ChangeListener cl2 = EventHandler.create(ChangeListener.class, preview, "repaint");
                jns.addChangeListener(cl2);
                return jns;
        }

        /**
         * Gets a spinner that is linked with the opacity of the {@code
         * ConstantSolidFill} given in argument.
         * @param cps
         * @return
         */
        public JNumericSpinner getLineOpacitySpinner(final ConstantSolidFill cps){
                final JNumericSpinner jns = new JNumericSpinner(4, 0, 1, 0.01);
                ChangeListener cl = EventHandler.create(ChangeListener.class, cps, "opacity", "source.value");
                jns.addChangeListener(cl);
                jns.setValue(cps.getOpacity());
                jns.setMaximumSize(new Dimension(60,30));
                jns.setPreferredSize(new Dimension(60,30));
                ChangeListener cl2 = EventHandler.create(ChangeListener.class, preview, "repaint");
                jns.addChangeListener(cl2);
                return jns;
        }

        /**
         * Get a {@code TextField} instance linked to the given parameter.
         * @param cps
         *      The parameter we want to configure with our panel
         * @return
         *      A {@code JTextField} embedded in a {@code JPanel}.
         */
        public JPanel getDashArrayField(final ConstantColorAndDashesPSLegend cps){
                JPanel cont = new JPanel();
                final JTextField jrf = new JTextField(8);
                ActionListener al = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                cps.setDashArray(((JTextField)e.getSource()).getText());
                        }
                };
                jrf.addActionListener(al);
                FocusListener fl = new FocusListener() {
                        @Override public void focusGained(FocusEvent e) {}
                        @Override public void focusLost(FocusEvent e) {
                                JTextField jtf = (JTextField)e.getSource();
                                String tmp = jtf.getText();
                                cps.setDashArray(tmp);
                                if(!tmp.equals(cps.getDashArray())){
                                        LOGGER.warn(I18N.tr("Could not validate your input."));
                                        jtf.setText(cps.getDashArray());
                                }
                        }
                };
                jrf.addFocusListener(fl);
                FocusListener prev = EventHandler.create(FocusListener.class, preview, "repaint");
                jrf.addFocusListener(prev);
                jrf.setText(cps.getDashArray());
                cont.add(jrf);
                return cont;
        }

        /**
         * Get a {@code JPanel} that contains a {@code JLabel}. If the {@code
         * JLabel} is clicked, a dialog is open to let the user choose a color.
         * This {@code JLabel} is linked to the given {@code USParameter}.
         * @param c
         * @return
         */
        public JPanel getColorField(final ConstantSolidFill c){
                JLabel lblFill = getFilledLabel(c.getColor());
                PropertyChangeListener pcl = EventHandler.create(PropertyChangeListener.class,c,"color","newValue");
                PropertyChangeListener pcl2 = EventHandler.create(PropertyChangeListener.class, preview, "repaint");
                lblFill.addPropertyChangeListener("background", pcl);
                lblFill.addPropertyChangeListener("background", pcl2);
                JPanel jp = new JPanel();
                jp.add(lblFill);
                return jp;
        }

        @Override
        public String getId(){
                return id;
        }

        @Override
        public void setId(String id){
                this.id = id;
        }

        /**
         * Gets the value contained in the {@code Uom} enum with their
         * internationalized representation in a {@code
         * ContainerItemProperties} array.
         * @return
         */
        public ContainerItemProperties[] getUomProperties(){
                Uom[] us = Uom.values();
                ContainerItemProperties[] cips = new ContainerItemProperties[us.length];
                for(int i = 0; i<us.length; i++){
                        Uom u = us[i];
                        ContainerItemProperties cip = new ContainerItemProperties(u.name(), u.toLocalizedString());
                        cips[i] = cip;
                }
                return cips;
        }

        /**
         * ComboBox to configure the unit of measure used to draw th stroke.
         * @param input
         * @return
         */
        public JComboBox getLineUomCombo(StrokeUom input){
                CanvasSE prev = getPreview();
                strokeUoms= getUomProperties();
                String[] values = new String[strokeUoms.length];
                for (int i = 0; i < values.length; i++) {
                        values[i] = I18N.tr(strokeUoms[i].getLabel());
                }
                final JComboBox jcc = new JComboBox(values);
                ActionListener acl = EventHandler.create(ActionListener.class, prev, "repaint");
                ActionListener acl2 = EventHandler.create(ActionListener.class, this, "updateLUComboBox", "source.selectedIndex");
                jcc.addActionListener(acl2);
                jcc.addActionListener(acl);
                jcc.setSelectedItem(input.getStrokeUom().toString().toUpperCase());
                return jcc;
        }

        /**
         * Sets the underlying graphic to use the ith element of the combobox
         * as its well-known name. Used when changing the combobox selection.
         * @param index
         */
        public void updateLUComboBox(int index){
                ((StrokeUom)getLegend()).setStrokeUom(Uom.fromString(strokeUoms[index].getKey()));
        }
}
