/* Generated By:JJTree: Do not edit this line. ASTSwitchLabel.java Version 4.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY= */
package org.orbisgis.core.javaManager.parser;

public class ASTSwitchLabel extends SimpleNode {
  public ASTSwitchLabel(int id) {
    super(id);
  }

  public ASTSwitchLabel(JavaParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JavaParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=552732832bc2d8b3a6fb587e7b203983 (do not edit this line) */