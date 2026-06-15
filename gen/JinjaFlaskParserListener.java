// Generated from C:/Users/k/Desktop/compiler/grammars/JinjaFlaskParser.g4 by ANTLR 4.13.2
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JinjaFlaskParser}.
 */
public interface JinjaFlaskParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Program}
	 * labeled alternative in {@link JinjaFlaskParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProgram(JinjaFlaskParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Program}
	 * labeled alternative in {@link JinjaFlaskParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProgram(JinjaFlaskParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CompoundStatement}
	 * labeled alternative in {@link JinjaFlaskParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(JinjaFlaskParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CompoundStatement}
	 * labeled alternative in {@link JinjaFlaskParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(JinjaFlaskParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PassStatement}
	 * labeled alternative in {@link JinjaFlaskParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPassStatement(JinjaFlaskParser.PassStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PassStatement}
	 * labeled alternative in {@link JinjaFlaskParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPassStatement(JinjaFlaskParser.PassStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(JinjaFlaskParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(JinjaFlaskParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignmentStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(JinjaFlaskParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignmentStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(JinjaFlaskParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomExpression}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpression(JinjaFlaskParser.AtomExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomExpression}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpression(JinjaFlaskParser.AtomExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SimpleExpression}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimpleExpression(JinjaFlaskParser.SimpleExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SimpleExpression}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimpleExpression(JinjaFlaskParser.SimpleExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForLoopStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterForLoopStatement(JinjaFlaskParser.ForLoopStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForLoopStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitForLoopStatement(JinjaFlaskParser.ForLoopStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PythonExpression}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterPythonExpression(JinjaFlaskParser.PythonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PythonExpression}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitPythonExpression(JinjaFlaskParser.PythonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionDefinition}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(JinjaFlaskParser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionDefinition}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(JinjaFlaskParser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(JinjaFlaskParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(JinjaFlaskParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ImportStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterImportStatement(JinjaFlaskParser.ImportStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ImportStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitImportStatement(JinjaFlaskParser.ImportStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GlobalStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterGlobalStatement(JinjaFlaskParser.GlobalStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GlobalStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitGlobalStatement(JinjaFlaskParser.GlobalStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComplexReturn}
	 * labeled alternative in {@link JinjaFlaskParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void enterComplexReturn(JinjaFlaskParser.ComplexReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComplexReturn}
	 * labeled alternative in {@link JinjaFlaskParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void exitComplexReturn(JinjaFlaskParser.ComplexReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SimpleReturn}
	 * labeled alternative in {@link JinjaFlaskParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimpleReturn(JinjaFlaskParser.SimpleReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SimpleReturn}
	 * labeled alternative in {@link JinjaFlaskParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimpleReturn(JinjaFlaskParser.SimpleReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GlobalStatementDef}
	 * labeled alternative in {@link JinjaFlaskParser#global_stmt}.
	 * @param ctx the parse tree
	 */
	void enterGlobalStatementDef(JinjaFlaskParser.GlobalStatementDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GlobalStatementDef}
	 * labeled alternative in {@link JinjaFlaskParser#global_stmt}.
	 * @param ctx the parse tree
	 */
	void exitGlobalStatementDef(JinjaFlaskParser.GlobalStatementDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ImportFromDef}
	 * labeled alternative in {@link JinjaFlaskParser#import_from}.
	 * @param ctx the parse tree
	 */
	void enterImportFromDef(JinjaFlaskParser.ImportFromDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ImportFromDef}
	 * labeled alternative in {@link JinjaFlaskParser#import_from}.
	 * @param ctx the parse tree
	 */
	void exitImportFromDef(JinjaFlaskParser.ImportFromDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Imported}
	 * labeled alternative in {@link JinjaFlaskParser#imptd}.
	 * @param ctx the parse tree
	 */
	void enterImported(JinjaFlaskParser.ImportedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Imported}
	 * labeled alternative in {@link JinjaFlaskParser#imptd}.
	 * @param ctx the parse tree
	 */
	void exitImported(JinjaFlaskParser.ImportedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStatementDef}
	 * labeled alternative in {@link JinjaFlaskParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStatementDef(JinjaFlaskParser.IfStatementDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStatementDef}
	 * labeled alternative in {@link JinjaFlaskParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStatementDef(JinjaFlaskParser.IfStatementDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanCondition}
	 * labeled alternative in {@link JinjaFlaskParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterBooleanCondition(JinjaFlaskParser.BooleanConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanCondition}
	 * labeled alternative in {@link JinjaFlaskParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitBooleanCondition(JinjaFlaskParser.BooleanConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotExpression}
	 * labeled alternative in {@link JinjaFlaskParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(JinjaFlaskParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotExpression}
	 * labeled alternative in {@link JinjaFlaskParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(JinjaFlaskParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComparisonExpression}
	 * labeled alternative in {@link JinjaFlaskParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(JinjaFlaskParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComparisonExpression}
	 * labeled alternative in {@link JinjaFlaskParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(JinjaFlaskParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomComplexExpression}
	 * labeled alternative in {@link JinjaFlaskParser#python_expr}.
	 * @param ctx the parse tree
	 */
	void enterAtomComplexExpression(JinjaFlaskParser.AtomComplexExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomComplexExpression}
	 * labeled alternative in {@link JinjaFlaskParser#python_expr}.
	 * @param ctx the parse tree
	 */
	void exitAtomComplexExpression(JinjaFlaskParser.AtomComplexExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComplexExpression}
	 * labeled alternative in {@link JinjaFlaskParser#python_expr}.
	 * @param ctx the parse tree
	 */
	void enterComplexExpression(JinjaFlaskParser.ComplexExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComplexExpression}
	 * labeled alternative in {@link JinjaFlaskParser#python_expr}.
	 * @param ctx the parse tree
	 */
	void exitComplexExpression(JinjaFlaskParser.ComplexExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ListAccess}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void enterListAccess(JinjaFlaskParser.ListAccessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ListAccess}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void exitListAccess(JinjaFlaskParser.ListAccessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DictionaryAccess}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void enterDictionaryAccess(JinjaFlaskParser.DictionaryAccessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DictionaryAccess}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void exitDictionaryAccess(JinjaFlaskParser.DictionaryAccessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AttributeAccess}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void enterAttributeAccess(JinjaFlaskParser.AttributeAccessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AttributeAccess}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void exitAttributeAccess(JinjaFlaskParser.AttributeAccessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MethodAccess}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void enterMethodAccess(JinjaFlaskParser.MethodAccessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MethodAccess}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void exitMethodAccess(JinjaFlaskParser.MethodAccessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ObjectCreation}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void enterObjectCreation(JinjaFlaskParser.ObjectCreationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ObjectCreation}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void exitObjectCreation(JinjaFlaskParser.ObjectCreationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionCall}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(JinjaFlaskParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCall}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(JinjaFlaskParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SimpleVar}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void enterSimpleVar(JinjaFlaskParser.SimpleVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SimpleVar}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void exitSimpleVar(JinjaFlaskParser.SimpleVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Generator}
	 * labeled alternative in {@link JinjaFlaskParser#complex_expr}.
	 * @param ctx the parse tree
	 */
	void enterGenerator(JinjaFlaskParser.GeneratorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Generator}
	 * labeled alternative in {@link JinjaFlaskParser#complex_expr}.
	 * @param ctx the parse tree
	 */
	void exitGenerator(JinjaFlaskParser.GeneratorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ListComprehension}
	 * labeled alternative in {@link JinjaFlaskParser#complex_expr}.
	 * @param ctx the parse tree
	 */
	void enterListComprehension(JinjaFlaskParser.ListComprehensionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ListComprehension}
	 * labeled alternative in {@link JinjaFlaskParser#complex_expr}.
	 * @param ctx the parse tree
	 */
	void exitListComprehension(JinjaFlaskParser.ListComprehensionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DictionaryLiteral}
	 * labeled alternative in {@link JinjaFlaskParser#complex_expr}.
	 * @param ctx the parse tree
	 */
	void enterDictionaryLiteral(JinjaFlaskParser.DictionaryLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DictionaryLiteral}
	 * labeled alternative in {@link JinjaFlaskParser#complex_expr}.
	 * @param ctx the parse tree
	 */
	void exitDictionaryLiteral(JinjaFlaskParser.DictionaryLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ListLiteral}
	 * labeled alternative in {@link JinjaFlaskParser#complex_expr}.
	 * @param ctx the parse tree
	 */
	void enterListLiteral(JinjaFlaskParser.ListLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ListLiteral}
	 * labeled alternative in {@link JinjaFlaskParser#complex_expr}.
	 * @param ctx the parse tree
	 */
	void exitListLiteral(JinjaFlaskParser.ListLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LessThanOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterLessThanOperator(JinjaFlaskParser.LessThanOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LessThanOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitLessThanOperator(JinjaFlaskParser.LessThanOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GreaterThanOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterGreaterThanOperator(JinjaFlaskParser.GreaterThanOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GreaterThanOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitGreaterThanOperator(JinjaFlaskParser.GreaterThanOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterEqualOperator(JinjaFlaskParser.EqualOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitEqualOperator(JinjaFlaskParser.EqualOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GreaterEqualOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterGreaterEqualOperator(JinjaFlaskParser.GreaterEqualOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GreaterEqualOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitGreaterEqualOperator(JinjaFlaskParser.GreaterEqualOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LessEqualOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterLessEqualOperator(JinjaFlaskParser.LessEqualOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LessEqualOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitLessEqualOperator(JinjaFlaskParser.LessEqualOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotEqualOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterNotEqualOperator(JinjaFlaskParser.NotEqualOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotEqualOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitNotEqualOperator(JinjaFlaskParser.NotEqualOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OrOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterOrOperator(JinjaFlaskParser.OrOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OrOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitOrOperator(JinjaFlaskParser.OrOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterInOperator(JinjaFlaskParser.InOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitInOperator(JinjaFlaskParser.InOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotInOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterNotInOperator(JinjaFlaskParser.NotInOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotInOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitNotInOperator(JinjaFlaskParser.NotInOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IsOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterIsOperator(JinjaFlaskParser.IsOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IsOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitIsOperator(JinjaFlaskParser.IsOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IsNotOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterIsNotOperator(JinjaFlaskParser.IsNotOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IsNotOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitIsNotOperator(JinjaFlaskParser.IsNotOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PythonExpressionAssignStmt}
	 * labeled alternative in {@link JinjaFlaskParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void enterPythonExpressionAssignStmt(JinjaFlaskParser.PythonExpressionAssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PythonExpressionAssignStmt}
	 * labeled alternative in {@link JinjaFlaskParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void exitPythonExpressionAssignStmt(JinjaFlaskParser.PythonExpressionAssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComparisonAssignStmt}
	 * labeled alternative in {@link JinjaFlaskParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void enterComparisonAssignStmt(JinjaFlaskParser.ComparisonAssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComparisonAssignStmt}
	 * labeled alternative in {@link JinjaFlaskParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void exitComparisonAssignStmt(JinjaFlaskParser.ComparisonAssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticAssignStmt}
	 * labeled alternative in {@link JinjaFlaskParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticAssignStmt(JinjaFlaskParser.ArithmeticAssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticAssignStmt}
	 * labeled alternative in {@link JinjaFlaskParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticAssignStmt(JinjaFlaskParser.ArithmeticAssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TemplateLiteralAssignStmt}
	 * labeled alternative in {@link JinjaFlaskParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void enterTemplateLiteralAssignStmt(JinjaFlaskParser.TemplateLiteralAssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TemplateLiteralAssignStmt}
	 * labeled alternative in {@link JinjaFlaskParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void exitTemplateLiteralAssignStmt(JinjaFlaskParser.TemplateLiteralAssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HtmlContentDoubleTemplate}
	 * labeled alternative in {@link JinjaFlaskParser#template_literal}.
	 * @param ctx the parse tree
	 */
	void enterHtmlContentDoubleTemplate(JinjaFlaskParser.HtmlContentDoubleTemplateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HtmlContentDoubleTemplate}
	 * labeled alternative in {@link JinjaFlaskParser#template_literal}.
	 * @param ctx the parse tree
	 */
	void exitHtmlContentDoubleTemplate(JinjaFlaskParser.HtmlContentDoubleTemplateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HtmlContentSingleTemplate}
	 * labeled alternative in {@link JinjaFlaskParser#template_literal}.
	 * @param ctx the parse tree
	 */
	void enterHtmlContentSingleTemplate(JinjaFlaskParser.HtmlContentSingleTemplateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HtmlContentSingleTemplate}
	 * labeled alternative in {@link JinjaFlaskParser#template_literal}.
	 * @param ctx the parse tree
	 */
	void exitHtmlContentSingleTemplate(JinjaFlaskParser.HtmlContentSingleTemplateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SimpleForLoop}
	 * labeled alternative in {@link JinjaFlaskParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void enterSimpleForLoop(JinjaFlaskParser.SimpleForLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SimpleForLoop}
	 * labeled alternative in {@link JinjaFlaskParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void exitSimpleForLoop(JinjaFlaskParser.SimpleForLoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComplexForLoop}
	 * labeled alternative in {@link JinjaFlaskParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void enterComplexForLoop(JinjaFlaskParser.ComplexForLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComplexForLoop}
	 * labeled alternative in {@link JinjaFlaskParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void exitComplexForLoop(JinjaFlaskParser.ComplexForLoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionDefDef}
	 * labeled alternative in {@link JinjaFlaskParser#func_def}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefDef(JinjaFlaskParser.FunctionDefDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionDefDef}
	 * labeled alternative in {@link JinjaFlaskParser#func_def}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefDef(JinjaFlaskParser.FunctionDefDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Decorator}
	 * labeled alternative in {@link JinjaFlaskParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterDecorator(JinjaFlaskParser.DecoratorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Decorator}
	 * labeled alternative in {@link JinjaFlaskParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitDecorator(JinjaFlaskParser.DecoratorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionParameters}
	 * labeled alternative in {@link JinjaFlaskParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterFunctionParameters(JinjaFlaskParser.FunctionParametersContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionParameters}
	 * labeled alternative in {@link JinjaFlaskParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitFunctionParameters(JinjaFlaskParser.FunctionParametersContext ctx);
	/**
	 * Enter a parse tree produced by the {@code KeywordParams}
	 * labeled alternative in {@link JinjaFlaskParser#fun_params}.
	 * @param ctx the parse tree
	 */
	void enterKeywordParams(JinjaFlaskParser.KeywordParamsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code KeywordParams}
	 * labeled alternative in {@link JinjaFlaskParser#fun_params}.
	 * @param ctx the parse tree
	 */
	void exitKeywordParams(JinjaFlaskParser.KeywordParamsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PositionalParams}
	 * labeled alternative in {@link JinjaFlaskParser#fun_params}.
	 * @param ctx the parse tree
	 */
	void enterPositionalParams(JinjaFlaskParser.PositionalParamsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PositionalParams}
	 * labeled alternative in {@link JinjaFlaskParser#fun_params}.
	 * @param ctx the parse tree
	 */
	void exitPositionalParams(JinjaFlaskParser.PositionalParamsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NameAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterNameAtom(JinjaFlaskParser.NameAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NameAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitNameAtom(JinjaFlaskParser.NameAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClassAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterClassAtom(JinjaFlaskParser.ClassAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ClassAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitClassAtom(JinjaFlaskParser.ClassAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterNumberAtom(JinjaFlaskParser.NumberAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitNumberAtom(JinjaFlaskParser.NumberAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterStringAtom(JinjaFlaskParser.StringAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitStringAtom(JinjaFlaskParser.StringAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NoneAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterNoneAtom(JinjaFlaskParser.NoneAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NoneAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitNoneAtom(JinjaFlaskParser.NoneAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterBooleanAtom(JinjaFlaskParser.BooleanAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitBooleanAtom(JinjaFlaskParser.BooleanAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TrueAtom}
	 * labeled alternative in {@link JinjaFlaskParser#bool_exp}.
	 * @param ctx the parse tree
	 */
	void enterTrueAtom(JinjaFlaskParser.TrueAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TrueAtom}
	 * labeled alternative in {@link JinjaFlaskParser#bool_exp}.
	 * @param ctx the parse tree
	 */
	void exitTrueAtom(JinjaFlaskParser.TrueAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FalseAtom}
	 * labeled alternative in {@link JinjaFlaskParser#bool_exp}.
	 * @param ctx the parse tree
	 */
	void enterFalseAtom(JinjaFlaskParser.FalseAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FalseAtom}
	 * labeled alternative in {@link JinjaFlaskParser#bool_exp}.
	 * @param ctx the parse tree
	 */
	void exitFalseAtom(JinjaFlaskParser.FalseAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ListItems}
	 * labeled alternative in {@link JinjaFlaskParser#list_items}.
	 * @param ctx the parse tree
	 */
	void enterListItems(JinjaFlaskParser.ListItemsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ListItems}
	 * labeled alternative in {@link JinjaFlaskParser#list_items}.
	 * @param ctx the parse tree
	 */
	void exitListItems(JinjaFlaskParser.ListItemsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code KeyValuePairs}
	 * labeled alternative in {@link JinjaFlaskParser#dict_maker}.
	 * @param ctx the parse tree
	 */
	void enterKeyValuePairs(JinjaFlaskParser.KeyValuePairsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code KeyValuePairs}
	 * labeled alternative in {@link JinjaFlaskParser#dict_maker}.
	 * @param ctx the parse tree
	 */
	void exitKeyValuePairs(JinjaFlaskParser.KeyValuePairsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomKeyValue}
	 * labeled alternative in {@link JinjaFlaskParser#key_value}.
	 * @param ctx the parse tree
	 */
	void enterAtomKeyValue(JinjaFlaskParser.AtomKeyValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomKeyValue}
	 * labeled alternative in {@link JinjaFlaskParser#key_value}.
	 * @param ctx the parse tree
	 */
	void exitAtomKeyValue(JinjaFlaskParser.AtomKeyValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SimpleKeyValue}
	 * labeled alternative in {@link JinjaFlaskParser#key_value}.
	 * @param ctx the parse tree
	 */
	void enterSimpleKeyValue(JinjaFlaskParser.SimpleKeyValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SimpleKeyValue}
	 * labeled alternative in {@link JinjaFlaskParser#key_value}.
	 * @param ctx the parse tree
	 */
	void exitSimpleKeyValue(JinjaFlaskParser.SimpleKeyValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticExpression}
	 * labeled alternative in {@link JinjaFlaskParser#simple_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpression(JinjaFlaskParser.ArithmeticExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticExpression}
	 * labeled alternative in {@link JinjaFlaskParser#simple_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpression(JinjaFlaskParser.ArithmeticExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SimpleComparisonExpression}
	 * labeled alternative in {@link JinjaFlaskParser#simple_expr}.
	 * @param ctx the parse tree
	 */
	void enterSimpleComparisonExpression(JinjaFlaskParser.SimpleComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SimpleComparisonExpression}
	 * labeled alternative in {@link JinjaFlaskParser#simple_expr}.
	 * @param ctx the parse tree
	 */
	void exitSimpleComparisonExpression(JinjaFlaskParser.SimpleComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Addition}
	 * labeled alternative in {@link JinjaFlaskParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterAddition(JinjaFlaskParser.AdditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Addition}
	 * labeled alternative in {@link JinjaFlaskParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitAddition(JinjaFlaskParser.AdditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Subtraction}
	 * labeled alternative in {@link JinjaFlaskParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterSubtraction(JinjaFlaskParser.SubtractionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Subtraction}
	 * labeled alternative in {@link JinjaFlaskParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitSubtraction(JinjaFlaskParser.SubtractionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Division}
	 * labeled alternative in {@link JinjaFlaskParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterDivision(JinjaFlaskParser.DivisionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Division}
	 * labeled alternative in {@link JinjaFlaskParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitDivision(JinjaFlaskParser.DivisionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Multiplication}
	 * labeled alternative in {@link JinjaFlaskParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplication(JinjaFlaskParser.MultiplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Multiplication}
	 * labeled alternative in {@link JinjaFlaskParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplication(JinjaFlaskParser.MultiplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomArgs}
	 * labeled alternative in {@link JinjaFlaskParser#arglist}.
	 * @param ctx the parse tree
	 */
	void enterAtomArgs(JinjaFlaskParser.AtomArgsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomArgs}
	 * labeled alternative in {@link JinjaFlaskParser#arglist}.
	 * @param ctx the parse tree
	 */
	void exitAtomArgs(JinjaFlaskParser.AtomArgsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComplexArgs}
	 * labeled alternative in {@link JinjaFlaskParser#arglist}.
	 * @param ctx the parse tree
	 */
	void enterComplexArgs(JinjaFlaskParser.ComplexArgsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComplexArgs}
	 * labeled alternative in {@link JinjaFlaskParser#arglist}.
	 * @param ctx the parse tree
	 */
	void exitComplexArgs(JinjaFlaskParser.ComplexArgsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PositionalArgument}
	 * labeled alternative in {@link JinjaFlaskParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterPositionalArgument(JinjaFlaskParser.PositionalArgumentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PositionalArgument}
	 * labeled alternative in {@link JinjaFlaskParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitPositionalArgument(JinjaFlaskParser.PositionalArgumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code KeywordArgument}
	 * labeled alternative in {@link JinjaFlaskParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterKeywordArgument(JinjaFlaskParser.KeywordArgumentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code KeywordArgument}
	 * labeled alternative in {@link JinjaFlaskParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitKeywordArgument(JinjaFlaskParser.KeywordArgumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HtmlContent}
	 * labeled alternative in {@link JinjaFlaskParser#html_content}.
	 * @param ctx the parse tree
	 */
	void enterHtmlContent(JinjaFlaskParser.HtmlContentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HtmlContent}
	 * labeled alternative in {@link JinjaFlaskParser#html_content}.
	 * @param ctx the parse tree
	 */
	void exitHtmlContent(JinjaFlaskParser.HtmlContentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HtmlElementItem}
	 * labeled alternative in {@link JinjaFlaskParser#html_content_item}.
	 * @param ctx the parse tree
	 */
	void enterHtmlElementItem(JinjaFlaskParser.HtmlElementItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HtmlElementItem}
	 * labeled alternative in {@link JinjaFlaskParser#html_content_item}.
	 * @param ctx the parse tree
	 */
	void exitHtmlElementItem(JinjaFlaskParser.HtmlElementItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HtmlTextItem}
	 * labeled alternative in {@link JinjaFlaskParser#html_content_item}.
	 * @param ctx the parse tree
	 */
	void enterHtmlTextItem(JinjaFlaskParser.HtmlTextItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HtmlTextItem}
	 * labeled alternative in {@link JinjaFlaskParser#html_content_item}.
	 * @param ctx the parse tree
	 */
	void exitHtmlTextItem(JinjaFlaskParser.HtmlTextItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaStmtItem}
	 * labeled alternative in {@link JinjaFlaskParser#html_content_item}.
	 * @param ctx the parse tree
	 */
	void enterJinjaStmtItem(JinjaFlaskParser.JinjaStmtItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaStmtItem}
	 * labeled alternative in {@link JinjaFlaskParser#html_content_item}.
	 * @param ctx the parse tree
	 */
	void exitJinjaStmtItem(JinjaFlaskParser.JinjaStmtItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaExprItem}
	 * labeled alternative in {@link JinjaFlaskParser#html_content_item}.
	 * @param ctx the parse tree
	 */
	void enterJinjaExprItem(JinjaFlaskParser.JinjaExprItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaExprItem}
	 * labeled alternative in {@link JinjaFlaskParser#html_content_item}.
	 * @param ctx the parse tree
	 */
	void exitJinjaExprItem(JinjaFlaskParser.JinjaExprItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TagElement}
	 * labeled alternative in {@link JinjaFlaskParser#htmlElement}.
	 * @param ctx the parse tree
	 */
	void enterTagElement(JinjaFlaskParser.TagElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TagElement}
	 * labeled alternative in {@link JinjaFlaskParser#htmlElement}.
	 * @param ctx the parse tree
	 */
	void exitTagElement(JinjaFlaskParser.TagElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StyleElement}
	 * labeled alternative in {@link JinjaFlaskParser#htmlElement}.
	 * @param ctx the parse tree
	 */
	void enterStyleElement(JinjaFlaskParser.StyleElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StyleElement}
	 * labeled alternative in {@link JinjaFlaskParser#htmlElement}.
	 * @param ctx the parse tree
	 */
	void exitStyleElement(JinjaFlaskParser.StyleElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HtmlAttribute}
	 * labeled alternative in {@link JinjaFlaskParser#tag_content}.
	 * @param ctx the parse tree
	 */
	void enterHtmlAttribute(JinjaFlaskParser.HtmlAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HtmlAttribute}
	 * labeled alternative in {@link JinjaFlaskParser#tag_content}.
	 * @param ctx the parse tree
	 */
	void exitHtmlAttribute(JinjaFlaskParser.HtmlAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClosingMarker}
	 * labeled alternative in {@link JinjaFlaskParser#tag_content}.
	 * @param ctx the parse tree
	 */
	void enterClosingMarker(JinjaFlaskParser.ClosingMarkerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ClosingMarker}
	 * labeled alternative in {@link JinjaFlaskParser#tag_content}.
	 * @param ctx the parse tree
	 */
	void exitClosingMarker(JinjaFlaskParser.ClosingMarkerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StyleSheet}
	 * labeled alternative in {@link JinjaFlaskParser#style_sheet}.
	 * @param ctx the parse tree
	 */
	void enterStyleSheet(JinjaFlaskParser.StyleSheetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StyleSheet}
	 * labeled alternative in {@link JinjaFlaskParser#style_sheet}.
	 * @param ctx the parse tree
	 */
	void exitStyleSheet(JinjaFlaskParser.StyleSheetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CssRule}
	 * labeled alternative in {@link JinjaFlaskParser#ruleSet}.
	 * @param ctx the parse tree
	 */
	void enterCssRule(JinjaFlaskParser.CssRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CssRule}
	 * labeled alternative in {@link JinjaFlaskParser#ruleSet}.
	 * @param ctx the parse tree
	 */
	void exitCssRule(JinjaFlaskParser.CssRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CssSelectorDeclaration}
	 * labeled alternative in {@link JinjaFlaskParser#selector_decl}.
	 * @param ctx the parse tree
	 */
	void enterCssSelectorDeclaration(JinjaFlaskParser.CssSelectorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CssSelectorDeclaration}
	 * labeled alternative in {@link JinjaFlaskParser#selector_decl}.
	 * @param ctx the parse tree
	 */
	void exitCssSelectorDeclaration(JinjaFlaskParser.CssSelectorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CssSelectorList}
	 * labeled alternative in {@link JinjaFlaskParser#css_selector_list}.
	 * @param ctx the parse tree
	 */
	void enterCssSelectorList(JinjaFlaskParser.CssSelectorListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CssSelectorList}
	 * labeled alternative in {@link JinjaFlaskParser#css_selector_list}.
	 * @param ctx the parse tree
	 */
	void exitCssSelectorList(JinjaFlaskParser.CssSelectorListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code QualifiedSelector}
	 * labeled alternative in {@link JinjaFlaskParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedSelector(JinjaFlaskParser.QualifiedSelectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code QualifiedSelector}
	 * labeled alternative in {@link JinjaFlaskParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedSelector(JinjaFlaskParser.QualifiedSelectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StandaloneSimpleSelector}
	 * labeled alternative in {@link JinjaFlaskParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void enterStandaloneSimpleSelector(JinjaFlaskParser.StandaloneSimpleSelectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StandaloneSimpleSelector}
	 * labeled alternative in {@link JinjaFlaskParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void exitStandaloneSimpleSelector(JinjaFlaskParser.StandaloneSimpleSelectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeAndIdSelector}
	 * labeled alternative in {@link JinjaFlaskParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void enterTypeAndIdSelector(JinjaFlaskParser.TypeAndIdSelectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeAndIdSelector}
	 * labeled alternative in {@link JinjaFlaskParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void exitTypeAndIdSelector(JinjaFlaskParser.TypeAndIdSelectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeSelector}
	 * labeled alternative in {@link JinjaFlaskParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void enterTypeSelector(JinjaFlaskParser.TypeSelectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeSelector}
	 * labeled alternative in {@link JinjaFlaskParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void exitTypeSelector(JinjaFlaskParser.TypeSelectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DeclarationBlock}
	 * labeled alternative in {@link JinjaFlaskParser#declarationList}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationBlock(JinjaFlaskParser.DeclarationBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DeclarationBlock}
	 * labeled alternative in {@link JinjaFlaskParser#declarationList}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationBlock(JinjaFlaskParser.DeclarationBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CssDeclaration}
	 * labeled alternative in {@link JinjaFlaskParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterCssDeclaration(JinjaFlaskParser.CssDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CssDeclaration}
	 * labeled alternative in {@link JinjaFlaskParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitCssDeclaration(JinjaFlaskParser.CssDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionArguments}
	 * labeled alternative in {@link JinjaFlaskParser#css_function_args}.
	 * @param ctx the parse tree
	 */
	void enterFunctionArguments(JinjaFlaskParser.FunctionArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionArguments}
	 * labeled alternative in {@link JinjaFlaskParser#css_function_args}.
	 * @param ctx the parse tree
	 */
	void exitFunctionArguments(JinjaFlaskParser.FunctionArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CssFunctionCall}
	 * labeled alternative in {@link JinjaFlaskParser#css_function_call}.
	 * @param ctx the parse tree
	 */
	void enterCssFunctionCall(JinjaFlaskParser.CssFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CssFunctionCall}
	 * labeled alternative in {@link JinjaFlaskParser#css_function_call}.
	 * @param ctx the parse tree
	 */
	void exitCssFunctionCall(JinjaFlaskParser.CssFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 */
	void enterFunctionTerm(JinjaFlaskParser.FunctionTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 */
	void exitFunctionTerm(JinjaFlaskParser.FunctionTermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 */
	void enterStringTerm(JinjaFlaskParser.StringTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 */
	void exitStringTerm(JinjaFlaskParser.StringTermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ColorTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 */
	void enterColorTerm(JinjaFlaskParser.ColorTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ColorTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 */
	void exitColorTerm(JinjaFlaskParser.ColorTermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnitNumberTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 */
	void enterUnitNumberTerm(JinjaFlaskParser.UnitNumberTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnitNumberTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 */
	void exitUnitNumberTerm(JinjaFlaskParser.UnitNumberTermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 */
	void enterNumberTerm(JinjaFlaskParser.NumberTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 */
	void exitNumberTerm(JinjaFlaskParser.NumberTermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentifierTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierTerm(JinjaFlaskParser.IdentifierTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentifierTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierTerm(JinjaFlaskParser.IdentifierTermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaStmtBlock}
	 * labeled alternative in {@link JinjaFlaskParser#jinjaStatementBlock}.
	 * @param ctx the parse tree
	 */
	void enterJinjaStmtBlock(JinjaFlaskParser.JinjaStmtBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaStmtBlock}
	 * labeled alternative in {@link JinjaFlaskParser#jinjaStatementBlock}.
	 * @param ctx the parse tree
	 */
	void exitJinjaStmtBlock(JinjaFlaskParser.JinjaStmtBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaExprBlock}
	 * labeled alternative in {@link JinjaFlaskParser#jinjaExpressionBlock}.
	 * @param ctx the parse tree
	 */
	void enterJinjaExprBlock(JinjaFlaskParser.JinjaExprBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaExprBlock}
	 * labeled alternative in {@link JinjaFlaskParser#jinjaExpressionBlock}.
	 * @param ctx the parse tree
	 */
	void exitJinjaExprBlock(JinjaFlaskParser.JinjaExprBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaExtendsStmt}
	 * labeled alternative in {@link JinjaFlaskParser#jStatement}.
	 * @param ctx the parse tree
	 */
	void enterJinjaExtendsStmt(JinjaFlaskParser.JinjaExtendsStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaExtendsStmt}
	 * labeled alternative in {@link JinjaFlaskParser#jStatement}.
	 * @param ctx the parse tree
	 */
	void exitJinjaExtendsStmt(JinjaFlaskParser.JinjaExtendsStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaBlockStmt}
	 * labeled alternative in {@link JinjaFlaskParser#jStatement}.
	 * @param ctx the parse tree
	 */
	void enterJinjaBlockStmt(JinjaFlaskParser.JinjaBlockStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaBlockStmt}
	 * labeled alternative in {@link JinjaFlaskParser#jStatement}.
	 * @param ctx the parse tree
	 */
	void exitJinjaBlockStmt(JinjaFlaskParser.JinjaBlockStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaForStmt}
	 * labeled alternative in {@link JinjaFlaskParser#jStatement}.
	 * @param ctx the parse tree
	 */
	void enterJinjaForStmt(JinjaFlaskParser.JinjaForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaForStmt}
	 * labeled alternative in {@link JinjaFlaskParser#jStatement}.
	 * @param ctx the parse tree
	 */
	void exitJinjaForStmt(JinjaFlaskParser.JinjaForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaIfStmt}
	 * labeled alternative in {@link JinjaFlaskParser#jStatement}.
	 * @param ctx the parse tree
	 */
	void enterJinjaIfStmt(JinjaFlaskParser.JinjaIfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaIfStmt}
	 * labeled alternative in {@link JinjaFlaskParser#jStatement}.
	 * @param ctx the parse tree
	 */
	void exitJinjaIfStmt(JinjaFlaskParser.JinjaIfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaSetStmt}
	 * labeled alternative in {@link JinjaFlaskParser#jStatement}.
	 * @param ctx the parse tree
	 */
	void enterJinjaSetStmt(JinjaFlaskParser.JinjaSetStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaSetStmt}
	 * labeled alternative in {@link JinjaFlaskParser#jStatement}.
	 * @param ctx the parse tree
	 */
	void exitJinjaSetStmt(JinjaFlaskParser.JinjaSetStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaExtendsStmtDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_extends_stmt}.
	 * @param ctx the parse tree
	 */
	void enterJinjaExtendsStmtDef(JinjaFlaskParser.JinjaExtendsStmtDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaExtendsStmtDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_extends_stmt}.
	 * @param ctx the parse tree
	 */
	void exitJinjaExtendsStmtDef(JinjaFlaskParser.JinjaExtendsStmtDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaBlockStmtDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_block_stmt}.
	 * @param ctx the parse tree
	 */
	void enterJinjaBlockStmtDef(JinjaFlaskParser.JinjaBlockStmtDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaBlockStmtDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_block_stmt}.
	 * @param ctx the parse tree
	 */
	void exitJinjaBlockStmtDef(JinjaFlaskParser.JinjaBlockStmtDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaForStmtDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_for_stmt}.
	 * @param ctx the parse tree
	 */
	void enterJinjaForStmtDef(JinjaFlaskParser.JinjaForStmtDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaForStmtDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_for_stmt}.
	 * @param ctx the parse tree
	 */
	void exitJinjaForStmtDef(JinjaFlaskParser.JinjaForStmtDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaIfStmtDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterJinjaIfStmtDef(JinjaFlaskParser.JinjaIfStmtDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaIfStmtDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitJinjaIfStmtDef(JinjaFlaskParser.JinjaIfStmtDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaSetStmtDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_set_stmt}.
	 * @param ctx the parse tree
	 */
	void enterJinjaSetStmtDef(JinjaFlaskParser.JinjaSetStmtDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaSetStmtDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_set_stmt}.
	 * @param ctx the parse tree
	 */
	void exitJinjaSetStmtDef(JinjaFlaskParser.JinjaSetStmtDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaBinaryExpr}
	 * labeled alternative in {@link JinjaFlaskParser#j_expression}.
	 * @param ctx the parse tree
	 */
	void enterJinjaBinaryExpr(JinjaFlaskParser.JinjaBinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaBinaryExpr}
	 * labeled alternative in {@link JinjaFlaskParser#j_expression}.
	 * @param ctx the parse tree
	 */
	void exitJinjaBinaryExpr(JinjaFlaskParser.JinjaBinaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaSimpleExpr}
	 * labeled alternative in {@link JinjaFlaskParser#j_expression}.
	 * @param ctx the parse tree
	 */
	void enterJinjaSimpleExpr(JinjaFlaskParser.JinjaSimpleExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaSimpleExpr}
	 * labeled alternative in {@link JinjaFlaskParser#j_expression}.
	 * @param ctx the parse tree
	 */
	void exitJinjaSimpleExpr(JinjaFlaskParser.JinjaSimpleExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaFilteredExpr}
	 * labeled alternative in {@link JinjaFlaskParser#j_call_expr}.
	 * @param ctx the parse tree
	 */
	void enterJinjaFilteredExpr(JinjaFlaskParser.JinjaFilteredExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaFilteredExpr}
	 * labeled alternative in {@link JinjaFlaskParser#j_call_expr}.
	 * @param ctx the parse tree
	 */
	void exitJinjaFilteredExpr(JinjaFlaskParser.JinjaFilteredExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaFunctionCall}
	 * labeled alternative in {@link JinjaFlaskParser#j_call_expr}.
	 * @param ctx the parse tree
	 */
	void enterJinjaFunctionCall(JinjaFlaskParser.JinjaFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaFunctionCall}
	 * labeled alternative in {@link JinjaFlaskParser#j_call_expr}.
	 * @param ctx the parse tree
	 */
	void exitJinjaFunctionCall(JinjaFlaskParser.JinjaFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaVarAccessOnly}
	 * labeled alternative in {@link JinjaFlaskParser#j_call_expr}.
	 * @param ctx the parse tree
	 */
	void enterJinjaVarAccessOnly(JinjaFlaskParser.JinjaVarAccessOnlyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaVarAccessOnly}
	 * labeled alternative in {@link JinjaFlaskParser#j_call_expr}.
	 * @param ctx the parse tree
	 */
	void exitJinjaVarAccessOnly(JinjaFlaskParser.JinjaVarAccessOnlyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaAtomOnly}
	 * labeled alternative in {@link JinjaFlaskParser#j_call_expr}.
	 * @param ctx the parse tree
	 */
	void enterJinjaAtomOnly(JinjaFlaskParser.JinjaAtomOnlyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaAtomOnly}
	 * labeled alternative in {@link JinjaFlaskParser#j_call_expr}.
	 * @param ctx the parse tree
	 */
	void exitJinjaAtomOnly(JinjaFlaskParser.JinjaAtomOnlyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaVarAccessOnlyDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_var_access}.
	 * @param ctx the parse tree
	 */
	void enterJinjaVarAccessOnlyDef(JinjaFlaskParser.JinjaVarAccessOnlyDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaVarAccessOnlyDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_var_access}.
	 * @param ctx the parse tree
	 */
	void exitJinjaVarAccessOnlyDef(JinjaFlaskParser.JinjaVarAccessOnlyDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaArgListDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_argument_list}.
	 * @param ctx the parse tree
	 */
	void enterJinjaArgListDef(JinjaFlaskParser.JinjaArgListDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaArgListDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_argument_list}.
	 * @param ctx the parse tree
	 */
	void exitJinjaArgListDef(JinjaFlaskParser.JinjaArgListDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaPosArg}
	 * labeled alternative in {@link JinjaFlaskParser#j_argument}.
	 * @param ctx the parse tree
	 */
	void enterJinjaPosArg(JinjaFlaskParser.JinjaPosArgContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaPosArg}
	 * labeled alternative in {@link JinjaFlaskParser#j_argument}.
	 * @param ctx the parse tree
	 */
	void exitJinjaPosArg(JinjaFlaskParser.JinjaPosArgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaKwArg}
	 * labeled alternative in {@link JinjaFlaskParser#j_argument}.
	 * @param ctx the parse tree
	 */
	void enterJinjaKwArg(JinjaFlaskParser.JinjaKwArgContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaKwArg}
	 * labeled alternative in {@link JinjaFlaskParser#j_argument}.
	 * @param ctx the parse tree
	 */
	void exitJinjaKwArg(JinjaFlaskParser.JinjaKwArgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaNumberAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 */
	void enterJinjaNumberAtom(JinjaFlaskParser.JinjaNumberAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaNumberAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 */
	void exitJinjaNumberAtom(JinjaFlaskParser.JinjaNumberAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaStringAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 */
	void enterJinjaStringAtom(JinjaFlaskParser.JinjaStringAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaStringAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 */
	void exitJinjaStringAtom(JinjaFlaskParser.JinjaStringAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaTrueAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 */
	void enterJinjaTrueAtom(JinjaFlaskParser.JinjaTrueAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaTrueAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 */
	void exitJinjaTrueAtom(JinjaFlaskParser.JinjaTrueAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaFalseAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 */
	void enterJinjaFalseAtom(JinjaFlaskParser.JinjaFalseAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaFalseAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 */
	void exitJinjaFalseAtom(JinjaFlaskParser.JinjaFalseAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaNoneAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 */
	void enterJinjaNoneAtom(JinjaFlaskParser.JinjaNoneAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaNoneAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 */
	void exitJinjaNoneAtom(JinjaFlaskParser.JinjaNoneAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaNameAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 */
	void enterJinjaNameAtom(JinjaFlaskParser.JinjaNameAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaNameAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 */
	void exitJinjaNameAtom(JinjaFlaskParser.JinjaNameAtomContext ctx);
}