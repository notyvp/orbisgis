package org.orbisgis.core.ui.plugins.toc;

import java.util.Observable;

import org.orbisgis.core.DataManager;
import org.orbisgis.core.Services;
import org.orbisgis.core.images.IconNames;
import org.orbisgis.core.layerModel.ILayer;
import org.orbisgis.core.layerModel.LayerException;
import org.orbisgis.core.layerModel.MapContext;
import org.orbisgis.core.ui.editors.map.MapContextManager;
import org.orbisgis.core.ui.pluginSystem.AbstractPlugIn;
import org.orbisgis.core.ui.pluginSystem.PlugInContext;
import org.orbisgis.core.ui.pluginSystem.PlugInContext.LayerSelectionTest;
import org.orbisgis.core.ui.pluginSystem.PlugInContext.LayerTest;
import org.orbisgis.core.ui.pluginSystem.workbench.Names;
import org.orbisgis.core.ui.pluginSystem.workbench.WorkbenchContext;
import org.orbisgis.core.ui.pluginSystem.workbench.WorkbenchFrame;

public class CreateGroupPlugIn extends AbstractPlugIn {

	public boolean execute(PlugInContext context) throws Exception {
		getPlugInContext().executeLayers();
		return true;
	}

	public void initialize(PlugInContext context) throws Exception {
		WorkbenchContext wbContext = context.getWorkbenchContext();
		WorkbenchFrame frame = wbContext.getWorkbench().getFrame().getToc();
		context.getFeatureInstaller().addPopupMenuItem(frame, this,
				new String[] { Names.POPUP_TOC_LAYERS_CREATE_PATH1 },
				Names.POPUP_TOC_LAYERS_CREATE_GROUP, false,
				getIcon(IconNames.POPUP_TOC_LAYERS_CREATE_ICON), wbContext);
	}

	public void execute(MapContext mapContext, ILayer resource) {
		MapContextManager vcm = (MapContextManager) Services
				.getService(MapContextManager.class);
		DataManager dataManager = (DataManager) Services
				.getService(DataManager.class);
		if (vcm.getActiveMapContext() != null) {
			ILayer newLayerCollection = dataManager
					.createLayerCollection("group" + System.currentTimeMillis());

			if ((resource == null) || (!resource.acceptsChilds())) {
				resource = vcm.getActiveMapContext().getLayerModel();
			}
			try {
				resource.addLayer(newLayerCollection);
			} catch (LayerException e) {
				throw new RuntimeException("bug!");
			}
		}
	}

	public boolean isVisible() {
		return getPlugInContext().checkLayerAvailability(
				new LayerSelectionTest[]{ LayerSelectionTest.INFERIOR_EQUAL , LayerSelectionTest.ACTIVE_MAPCONTEXT},
				1,
				new LayerTest[]{ LayerTest.ACCEPT_CHILDS }, 
				false);
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}