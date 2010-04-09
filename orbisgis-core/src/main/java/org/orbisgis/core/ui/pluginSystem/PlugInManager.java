package org.orbisgis.core.ui.pluginSystem;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;
import org.orbisgis.core.Splash;
import org.orbisgis.core.ui.pluginSystem.utils.StringUtil;
import org.orbisgis.core.ui.pluginSystem.workbench.WorkbenchContext;

import com.vividsolutions.jts.util.Assert;



public class PlugInManager {

	private static Logger LOG = Logger.getLogger(PlugInManager.class);
	private WorkbenchContext context;
	private Collection configurations = new ArrayList();
	private ClassLoader classLoader;
	private File plugInDirectory;

	/**
	 * @param plugInDirectory
	 *            null to leave unspecified
	 */
	public PlugInManager(WorkbenchContext context, File plugInDirectory)
			throws Exception {
		
		
		/*Assert.isTrue((plugInDirectory == null)
                || plugInDirectory.isDirectory());*/
			
	
		classLoader = plugInDirectory != null ? new URLClassLoader(
				toURLs((File[]) findFilesRecursively(plugInDirectory).toArray(
						new File[] {}))) : getClass().getClassLoader();		
		this.context = context;
		// Find the configurations right away so they get reported to the splash		
		configurations
				.addAll(plugInDirectory != null ? findConfigurations(plugInDirectory)
						: new ArrayList());		
/*		 configurations.addAll(findConfigurations(context.getWorkbench()
				 .getProperties().getConfigurationClasses()));*/
		 
		this.plugInDirectory = plugInDirectory;
	}

	public void load() throws Exception {		
		/*loadPlugInClasses(context.getWorkbench().getProperties()
				 .getPlugInClasses(getClassLoader()));*/		 
		loadConfigurations();
	}

	private void loadConfigurations() throws Exception {
		for (Iterator i = configurations.iterator(); i.hasNext();) {
			Configuration configuration = (Configuration) i.next();
			configuration.configure(new PlugInContext(context));
		}
	}
	
	private void loadPlugInClasses(List plugInClasses) throws Exception {
		for (Iterator i = plugInClasses.iterator(); i.hasNext();) {
			Class plugInClass = (Class) i.next();
			PlugIn plugIn = (PlugIn) plugInClass.newInstance();
			plugIn.initialize(new PlugInContext(context));
		}
	}
	
    public ClassLoader getClassLoader() {
        return classLoader;
    }

	private URL[] toURLs(File[] files) {
		URL[] urls = new URL[files.length];
		for (int i = 0; i < files.length; i++) {
			try {
				urls[i] = new URL("jar:file:" + files[i].getPath() + "!/");
			} catch (MalformedURLException e) {
				Assert.shouldNeverReachHere(e.toString());
			}
		}
		return urls;
	}

	private Collection findFilesRecursively(File directory) {
		Assert.isTrue(directory.isDirectory());
		Collection files = new ArrayList();
		for (Iterator i = Arrays.asList(directory.listFiles()).iterator(); i
				.hasNext();) {
			File file = (File) i.next();
			if (file.isDirectory()) {
				files.addAll(findFilesRecursively(file));
			}
			if (!file.isFile()) {
				continue;
			}
			files.add(file);
		}
		return files;
	}

	private Collection findConfigurations(File plugInDirectory)
			throws Exception {
		ArrayList configurations = new ArrayList();
		for (Iterator i = findFilesRecursively(plugInDirectory).iterator(); i
				.hasNext();) {
			File file = (File) i.next();
			try {
				configurations.addAll(findConfigurations(classes(new ZipFile(
						file), classLoader)));
			} catch (ZipException e) {
				// Might not be a zipfile. Eat it.
			}
		}
		return configurations;
	}

	private Collection findConfigurations(List classes) throws Exception {
		ArrayList configurations = new ArrayList();
		for (Iterator i = classes.iterator(); i.hasNext();) {
			Class c = (Class) i.next();
			if (!Configuration.class.isAssignableFrom(c)) {
				continue;
			}
			LOG.debug("Loading " + c.getName());
			System.out.println("Loading " + c.getName());
			Configuration configuration = (Configuration) c.newInstance();
			configurations.add(configuration);

		}
		return configurations;
	}

	private List classes(ZipFile zipFile, ClassLoader classLoader) {
		ArrayList classes = new ArrayList();
		for (Enumeration e = zipFile.entries(); e.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) e.nextElement();
			// Filter by filename; otherwise we'll be loadingversionLabel all the classes,
			// which takes
			// significantly longer
			if (!(entry.getName().endsWith("Extension.class") || entry
					.getName().endsWith("Configuration.class"))) {
				// Include "Configuration" for backwards compatibility. 				
				continue;
			}
			Class c = toClass(entry, classLoader);
			if (c != null) {
				classes.add(c);
			}
		}
		return classes;
	}

	private Class toClass(ZipEntry entry, ClassLoader classLoader) {
		if (entry.isDirectory()) {
			return null;
		}
		if (!entry.getName().endsWith(".class")) {
			return null;
		}
		if (entry.getName().indexOf("$") != -1) {
			// I assume it's not necessary to load inner classes explicitly.
			return null;
		}
		String className = entry.getName();
		className = className.substring(0, className.length()
				- ".class".length());
		className = StringUtil.replaceAll(className, "/", ".");
		Class candidate = null;
		try {
			Splash.updateText("Loading plugin " + className);
			candidate = classLoader.loadClass(className);
		} catch (ClassNotFoundException e) {
			Assert.shouldNeverReachHere("Class not found: " + className
					+ ". Refine class name algorithm.");
			return null;
		} catch (Throwable t) {
			LOG.error("Throwable encountered loading " + className + ":");
			// e.g. java.lang.VerifyError: class
			// org.apache.xml.serialize.XML11Serializer
			// overrides final method
			t.printStackTrace(System.out);
			return null;
		}
		return candidate;
	}

}