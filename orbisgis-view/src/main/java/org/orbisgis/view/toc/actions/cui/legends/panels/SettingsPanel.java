package org.orbisgis.view.toc.actions.cui.legends.panels;

import net.miginfocom.swing.MigLayout;
import org.gdms.data.DataSource;
import org.orbisgis.legend.thematic.EnablesStroke;
import org.orbisgis.legend.thematic.LineParameters;
import org.orbisgis.legend.thematic.OnVertexOnCentroid;
import org.orbisgis.legend.thematic.categorize.AbstractCategorizedLegend;
import org.orbisgis.legend.thematic.categorize.CategorizedLine;
import org.orbisgis.legend.thematic.categorize.CategorizedPoint;
import org.orbisgis.legend.thematic.map.MappedLegend;
import org.orbisgis.legend.thematic.recode.AbstractRecodedLegend;
import org.orbisgis.legend.thematic.recode.RecodedLine;
import org.orbisgis.legend.thematic.recode.RecodedPoint;
import org.orbisgis.legend.thematic.uom.SymbolUom;
import org.orbisgis.view.toc.actions.cui.components.CanvasSE;
import org.orbisgis.view.toc.actions.cui.legends.AbstractFieldPanel;
import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: adam
 * Date: 25/07/13
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public class SettingsPanel<K, U extends LineParameters> extends JPanel {

    private static final I18n I18N = I18nFactory.getI18n(SettingsPanel.class);

    private MappedLegend<K, U> legend;
    private DataSource dataSource;
    private CanvasSE preview;
    private TablePanel<K, U> tablePanel;

    private FieldComboBox fieldComboBox;
    private LineUOMComboBox<K, U> lineUOMComboBox;

    public SettingsPanel(MappedLegend<K, U> legend,
                         DataSource dataSource,
                         CanvasSE preview,
                         TablePanel<K, U> tablePanel) {
        super(new MigLayout("wrap 2", AbstractFieldPanel.COLUMN_CONSTRAINTS));
        this.legend = legend;
        this.dataSource = dataSource;
        this.preview = preview;
        this.tablePanel = tablePanel;
        if (legend instanceof AbstractCategorizedLegend) {
            this.fieldComboBox = new NumericalFieldsComboBox(
                    dataSource,
                    (AbstractCategorizedLegend) legend);
        } else if (legend instanceof AbstractRecodedLegend) {
            this.fieldComboBox = new NonSpatialFieldsComboBox(
                    dataSource,
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
        add(new JLabel(I18N.tr(AbstractFieldPanel.FIELD)));

        add(fieldComboBox, AbstractFieldPanel.COMBO_BOX_CONSTRAINTS);

        // Unit of measure - line width
        add(new JLabel(I18N.tr(AbstractFieldPanel.LINE_WIDTH_UNIT)));
        add(lineUOMComboBox, AbstractFieldPanel.COMBO_BOX_CONSTRAINTS);

        beforeFallbackSymbol();

        // Fallback symbol
        add(preview, "span 2, align center");
        add(new JLabel(I18N.tr("Fallback symbol")), "span 2, align center");
    }

    private void beforeFallbackSymbol() {
        if (point()) {
            add(new JLabel(I18N.tr(AbstractFieldPanel.SYMBOL_SIZE_UNIT)));
            add(new SymbolUOMComboBox<K, U>((SymbolUom) legend, preview, tablePanel),
                    AbstractFieldPanel.COMBO_BOX_CONSTRAINTS);

            add(new JLabel(I18N.tr(AbstractFieldPanel.PLACE_SYMBOL_ON)), "span 1 2");
            add(new OnVertexOnCentroidPanel((OnVertexOnCentroid) legend, preview, tablePanel),
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

    public String getSelectedField() {
        return (String) fieldComboBox.getSelectedItem();
    }
}
