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
package org.orbisgis.coremap.layerModel.mapcatalog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Nicolas Fortin
 */
public class WorkspaceTest {
        /**
         * Test of parseXML method, of class Workspace.
         * @throws Exception 
         */
        @Test
        public void testParseXML() throws Exception {
                // Unused URL
                URL apiUrl = new URL("http://poulpe.heig-vd.ch/scapc2/serviceapi/index.php");
                ConnectionProperties cParams = new ConnectionProperties(apiUrl, null,new File("target/maps"));

                // Read the xml file in test resources
                FileReader inFile = new FileReader(new File(RemoteMapCatalogTest.class.getResource("context.xml").toURI()));
                BufferedReader in = new BufferedReader(inFile);
                XMLInputFactory factory = XMLInputFactory.newInstance();
                
                // Parse Data
                XMLStreamReader parser;
                parser = factory.createXMLStreamReader(in);
                Workspace mapCatalog =
                        new Workspace(cParams,
                        "default");
                List<RemoteMapContext> context = new ArrayList<RemoteMapContext>();
                mapCatalog.parseXML(context, parser);                        
                parser.close();
                
                assertEquals(context.size(), 2);

                assertEquals(context.get(0).getDescription().getTitle(Locale.ENGLISH), "My custom context");
                assertEquals(context.get(0).getDescription().getAbstract(Locale.ENGLISH), "Some text here.");
                assertEquals(context.get(0).getDate(), Workspace.parseDate("2012.10.12 15:13:29 CEST"));
                assertEquals(context.get(0).getId(), 0);

                assertEquals(context.get(1).getDescription().getDefaultTitle(), "Représentation par diagrammes");
                assertEquals(context.get(1).getDate(), Workspace.parseDate("2012.10.12 15:13:34 CEST"));
                assertEquals(context.get(1).getId(), 1);
        }
}
