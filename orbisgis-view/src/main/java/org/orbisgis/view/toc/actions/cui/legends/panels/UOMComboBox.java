package org.orbisgis.view.toc.actions.cui.legends.panels;

import org.orbisgis.core.renderer.se.common.Uom;
import org.orbisgis.legend.thematic.LineParameters;
import org.orbisgis.legend.thematic.map.MappedLegend;
import org.orbisgis.sif.components.WideComboBox;
import org.orbisgis.view.toc.actions.cui.components.CanvasSE;
import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: adam
 * Date: 26/07/13
 * Time: 14:39
 * To change this template use File | Settings | File Templates.
 */
public abstract class UOMComboBox<K, U extends LineParameters> extends WideComboBox {


    private static final I18n I18N = I18nFactory.getI18n(LineUOMComboBox.class);

    protected MappedLegend<K, U> legend;
    protected CanvasSE preview;
    protected TablePanel<K, U> tablePanel;

    public UOMComboBox(MappedLegend<K, U> legend,
                           CanvasSE preview,
                           TablePanel<K, U> tablePanel) {
        super(Uom.getLocalizedStrings());
        this.legend = legend;
        this.preview = preview;
        this.tablePanel = tablePanel;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePreview();
            }
        });
    }

    protected abstract void updatePreview();
}
