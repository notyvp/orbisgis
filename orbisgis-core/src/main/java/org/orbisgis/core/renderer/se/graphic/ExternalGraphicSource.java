/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.orbisgis.core.renderer.se.graphic;

import java.io.IOException;
import javax.media.jai.PlanarImage;
import org.gdms.data.feature.Feature;
import org.orbisgis.core.renderer.persistance.se.ExternalGraphicType;
import org.orbisgis.core.renderer.se.parameter.ParameterException;

/**
 *
 * @author maxence
 * @todo implement in InlineContent
 */
public interface ExternalGraphicSource {
    public abstract PlanarImage getPlanarImage(ViewBox viewBox, Feature feat) throws IOException, ParameterException;

    public void setJAXBSource(ExternalGraphicType e);
}
