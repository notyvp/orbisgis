/* Generated By:JavaCC: Do not edit this line. SQLEngineVisitor.java Version 4.2 */
package org.gdms.sql.parser;

public interface SQLEngineVisitor
{
  public Object visit(SimpleNode node, Object data);
  public Object visit(ASTSQLAndExpr node, Object data);
  public Object visit(ASTSQLBetweenClause node, Object data);
  public Object visit(ASTSQLColRef node, Object data);
  public Object visit(ASTSQLCompareExpr node, Object data);
  public Object visit(ASTSQLCompareExprRight node, Object data);
  public Object visit(ASTSQLCompareOp node, Object data);
  public Object visit(ASTSQLDelete node, Object data);
  public Object visit(ASTSQLDrop node, Object data);
  public Object visit(ASTSQLAlter node, Object data);
  public Object visit(ASTSQLAdd node, Object data);
  public Object visit(ASTSQLAddColumn node, Object data);
  public Object visit(ASTSQLAddPrimaryKey node, Object data);
  public Object visit(ASTSQLDropColumn node, Object data);
  public Object visit(ASTSQLRename node, Object data);
  public Object visit(ASTSQLRenameColumn node, Object data);
  public Object visit(ASTSQLRenameTable node, Object data);
  public Object visit(ASTSQLType node, Object data);
  public Object visit(ASTSQLExistsClause node, Object data);
  public Object visit(ASTSQLFunction node, Object data);
  public Object visit(ASTSQLFunctionArgs node, Object data);
  public Object visit(ASTSQLGroupBy node, Object data);
  public Object visit(ASTSQLGroupByList node, Object data);
  public Object visit(ASTSQLId node, Object data);
  public Object visit(ASTSQLIdSequence node, Object data);
  public Object visit(ASTSQLInClause node, Object data);
  public Object visit(ASTSQLInsert node, Object data);
  public Object visit(ASTSQLIsClause node, Object data);
  public Object visit(ASTSQLLeftJoinClause node, Object data);
  public Object visit(ASTSQLLikeClause node, Object data);
  public Object visit(ASTSQLLiteral node, Object data);
  public Object visit(ASTSQLNotExpr node, Object data);
  public Object visit(ASTSQLOrderBy node, Object data);
  public Object visit(ASTSQLOrderByElem node, Object data);
  public Object visit(ASTSQLOrderByList node, Object data);
  public Object visit(ASTSQLOrderDirection node, Object data);
  public Object visit(ASTSQLOrExpr node, Object data);
  public Object visit(ASTSQLPattern node, Object data);
  public Object visit(ASTSQLProductExpr node, Object data);
  public Object visit(ASTSQLProductSymbol node, Object data);
  public Object visit(ASTSQLRightJoinClause node, Object data);
  public Object visit(ASTSQLScript node, Object data);
  public Object visit(ASTSQLSelect node, Object data);
  public Object visit(ASTSQLSelectInto node, Object data);
  public Object visit(ASTSQLSelectAllCols node, Object data);
  public Object visit(ASTSQLSelectAllModifier node, Object data);
  public Object visit(ASTSQLSelectAllModifierExcept node, Object data);
  public Object visit(ASTSQLSelectAllInTableModifier node, Object data);
  public Object visit(ASTSQLSelectAllColsInTable node, Object data);
  public Object visit(ASTSQLSelectCols node, Object data);
  public Object visit(ASTSQLSelectLimit node, Object data);
  public Object visit(ASTSQLSelectList node, Object data);
  public Object visit(ASTSQLSelectOffset node, Object data);
  public Object visit(ASTSQLStatement node, Object data);
  public Object visit(ASTSQLSumExpr node, Object data);
  public Object visit(ASTSQLSumSymbol node, Object data);
  public Object visit(ASTSQLTableList node, Object data);
  public Object visit(ASTSQLTableRef node, Object data);
  public Object visit(ASTSQLTerm node, Object data);
  public Object visit(ASTSQLUnaryExpr node, Object data);
  public Object visit(ASTSQLUnion node, Object data);
  public Object visit(ASTSQLUpdate node, Object data);
  public Object visit(ASTSQLUpdateAssignment node, Object data);
  public Object visit(ASTSQLLValueElement node, Object data);
  public Object visit(ASTSQLValueList node, Object data);
  public Object visit(ASTSQLWhere node, Object data);
  public Object visit(ASTSQLCreate node, Object data);
  public Object visit(ASTSQLInto node, Object data);
  public Object visit(ASTSQLCreateArgsList node, Object data);
  public Object visit(ASTSQLColumnDefinition node, Object data);
  public Object visit(ASTSQLDataTypeConstraint node, Object data);
  public Object visit(ASTSQLTypeArgLiteral node, Object data);
  public Object visit(ASTSQLTypeArgs node, Object data);
}
/* JavaCC - OriginalChecksum=a6dbaa9bda6145edade479651fb3b81d (do not edit this line) */
