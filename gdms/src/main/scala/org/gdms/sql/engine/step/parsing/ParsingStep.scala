/*
 * The GDMS library (Generic Datasources Management System)
 * is a middleware dedicated to the management of various kinds of
 * data-sources such as spatial vectorial data or alphanumeric. Based
 * on the JTS library and conform to the OGC simple feature access
 * specifications, it provides a complete and robust API to manipulate
 * in a SQL way remote DBMS (PostgreSQL, H2...) or flat files (.shp,
 * .csv...). It is produced by the "Atelier SIG" team of
 * the IRSTV Institute <http://www.irstv.cnrs.fr/> CNRS FR 2488.
 * 
 * 
 * Team leader : Erwan BOCHER, scientific researcher,
 * 
 * User support leader : Gwendall Petit, geomatic engineer.
 * 
 * Previous computer developer : Pierre-Yves FADET, computer engineer, Thomas LEDUC, 
 * scientific researcher, Fernando GONZALEZ CORTES, computer engineer.
 * 
 * Copyright (C) 2007 Erwan BOCHER, Fernando GONZALEZ CORTES, Thomas LEDUC
 * 
 * Copyright (C) 2010 Erwan BOCHER, Alexis GUEGANNO, Maxence LAURENT, Antoine GOURLAY
 * 
 * This file is part of Gdms.
 * 
 * Gdms is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * Gdms is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Gdms. If not, see <http://www.gnu.org/licenses/>.
 * 
 * For more information, please consult: <http://www.orbisgis.org/>
 * 
 * or contact directly:
 * info@orbisgis.org
 */

package org.gdms.sql.engine.step.parsing

import java.io.ByteArrayInputStream
import java.io.IOException
import java.util.Properties
import org.antlr.runtime.CommonTokenStream
import org.antlr.runtime.MismatchedTokenException
import org.antlr.runtime.RecognitionException
import org.antlr.runtime.tree.CommonTree
import org.gdms.sql.engine.ANTLRCaseInsensitiveInputStream
import org.gdms.sql.engine.AbstractEngineStep
import org.gdms.sql.engine.ParseException
import org.gdms.sql.parser.GdmSQLLexer
import org.gdms.sql.parser.GdmSQLParser

case object ParsingStep extends AbstractEngineStep[String, CommonTree]("Parsing") {
  def doOperation(sql: String)(implicit p: Properties) = {
    
    val input = getInput(sql)
    val parser = new GdmSQLParser(new CommonTokenStream(new GdmSQLLexer(input)))
    
    try {
      // entry point of the parser
      parser.start_rule.getTree.asInstanceOf[CommonTree]
    } catch {
      case e: RecognitionException => throw new ParseException(getErrorMessage(e), e)
      case e if e.getCause.isInstanceOf[RecognitionException] => 
        throw new ParseException(getErrorMessage(e.getCause.asInstanceOf[RecognitionException]), e)
      case e => throw e
    }
  }
  
  private def getInput(sql: String) = {
    try {
    new ANTLRCaseInsensitiveInputStream(new ByteArrayInputStream(sql.getBytes()));
    } catch {
      // never happens
      case e: IOException => throw new ParseException("Internal error: failed to open a stream on input string.", e)
    }
  }
  
  private def getErrorMessage(e: RecognitionException): String = {
    implicit val b = new StringBuilder
    
    setErrorLocation(e)
    setErrorDescription(e)
    
    b.toString
  }
  
  private def setErrorLocation(e: RecognitionException)(implicit b: StringBuilder) {
    b.append("Parse error on line ").append(e.line)
    b.append(" at character ").append(e.charPositionInLine)
    b.append(": ")
  }
  
  private def setErrorDescription(e: RecognitionException)(implicit b: StringBuilder) {
    e.getUnexpectedType match {
      case -1 => b.append("unexpected end of query")
      case GdmSQLParser.ID => b.append("found identifier '" + e.token.getText)
      case i => b.append("found ").append(getTokenName(i))
    }
    
    e match {
      case ex: MismatchedTokenException => b.append(", expected ").append(getTokenName(ex.expecting))
      case _ => b.append(", unexpected at this position")
    }
    b.append(".")
  }
  
  private def getTokenName(token: Int) = {
    val t = GdmSQLParser.tokenNames(token).replace("T_", "")
    
    t.toLowerCase match {
      case "semi" => "';'"
      case "comma" => "','"
      case "lparen" => "'('"
      case "rparen" => "')'"
      case "eq" => "="
      case _ => "'" + t + "'"
    }
  }
}
