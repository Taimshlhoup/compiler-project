// Generated from C:/Users/k/Desktop/compiler/grammars/JinjaFlaskParser.g4 by ANTLR 4.13.2
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JinjaFlaskParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JinjaFlaskParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Program}
	 * labeled alternative in {@link JinjaFlaskParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(JinjaFlaskParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CompoundStatement}
	 * labeled alternative in {@link JinjaFlaskParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(JinjaFlaskParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PassStatement}
	 * labeled alternative in {@link JinjaFlaskParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPassStatement(JinjaFlaskParser.PassStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(JinjaFlaskParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignmentStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(JinjaFlaskParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AtomExpression}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomExpression(JinjaFlaskParser.AtomExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleExpression}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExpression(JinjaFlaskParser.SimpleExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForLoopStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoopStatement(JinjaFlaskParser.ForLoopStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PythonExpression}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPythonExpression(JinjaFlaskParser.PythonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionDefinition}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(JinjaFlaskParser.FunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(JinjaFlaskParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ImportStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportStatement(JinjaFlaskParser.ImportStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GlobalStatement}
	 * labeled alternative in {@link JinjaFlaskParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalStatement(JinjaFlaskParser.GlobalStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComplexReturn}
	 * labeled alternative in {@link JinjaFlaskParser#return_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexReturn(JinjaFlaskParser.ComplexReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleReturn}
	 * labeled alternative in {@link JinjaFlaskParser#return_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleReturn(JinjaFlaskParser.SimpleReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GlobalStatementDef}
	 * labeled alternative in {@link JinjaFlaskParser#global_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalStatementDef(JinjaFlaskParser.GlobalStatementDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ImportFromDef}
	 * labeled alternative in {@link JinjaFlaskParser#import_from}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportFromDef(JinjaFlaskParser.ImportFromDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Imported}
	 * labeled alternative in {@link JinjaFlaskParser#imptd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImported(JinjaFlaskParser.ImportedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfStatementDef}
	 * labeled alternative in {@link JinjaFlaskParser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatementDef(JinjaFlaskParser.IfStatementDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanCondition}
	 * labeled alternative in {@link JinjaFlaskParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanCondition(JinjaFlaskParser.BooleanConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotExpression}
	 * labeled alternative in {@link JinjaFlaskParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(JinjaFlaskParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComparisonExpression}
	 * labeled alternative in {@link JinjaFlaskParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(JinjaFlaskParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AtomComplexExpression}
	 * labeled alternative in {@link JinjaFlaskParser#python_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomComplexExpression(JinjaFlaskParser.AtomComplexExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComplexExpression}
	 * labeled alternative in {@link JinjaFlaskParser#python_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexExpression(JinjaFlaskParser.ComplexExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ListAccess}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListAccess(JinjaFlaskParser.ListAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DictionaryAccess}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDictionaryAccess(JinjaFlaskParser.DictionaryAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AttributeAccess}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeAccess(JinjaFlaskParser.AttributeAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MethodAccess}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodAccess(JinjaFlaskParser.MethodAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ObjectCreation}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectCreation(JinjaFlaskParser.ObjectCreationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionCall}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(JinjaFlaskParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleVar}
	 * labeled alternative in {@link JinjaFlaskParser#atom_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleVar(JinjaFlaskParser.SimpleVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Generator}
	 * labeled alternative in {@link JinjaFlaskParser#complex_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenerator(JinjaFlaskParser.GeneratorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ListComprehension}
	 * labeled alternative in {@link JinjaFlaskParser#complex_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListComprehension(JinjaFlaskParser.ListComprehensionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DictionaryLiteral}
	 * labeled alternative in {@link JinjaFlaskParser#complex_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDictionaryLiteral(JinjaFlaskParser.DictionaryLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ListLiteral}
	 * labeled alternative in {@link JinjaFlaskParser#complex_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListLiteral(JinjaFlaskParser.ListLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessThanOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessThanOperator(JinjaFlaskParser.LessThanOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GreaterThanOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterThanOperator(JinjaFlaskParser.GreaterThanOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualOperator(JinjaFlaskParser.EqualOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GreaterEqualOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterEqualOperator(JinjaFlaskParser.GreaterEqualOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessEqualOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessEqualOperator(JinjaFlaskParser.LessEqualOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotEqualOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotEqualOperator(JinjaFlaskParser.NotEqualOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrOperator(JinjaFlaskParser.OrOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInOperator(JinjaFlaskParser.InOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotInOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotInOperator(JinjaFlaskParser.NotInOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IsOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsOperator(JinjaFlaskParser.IsOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IsNotOperator}
	 * labeled alternative in {@link JinjaFlaskParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsNotOperator(JinjaFlaskParser.IsNotOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PythonExpressionAssignStmt}
	 * labeled alternative in {@link JinjaFlaskParser#assign_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPythonExpressionAssignStmt(JinjaFlaskParser.PythonExpressionAssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComparisonAssignStmt}
	 * labeled alternative in {@link JinjaFlaskParser#assign_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonAssignStmt(JinjaFlaskParser.ComparisonAssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArithmeticAssignStmt}
	 * labeled alternative in {@link JinjaFlaskParser#assign_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticAssignStmt(JinjaFlaskParser.ArithmeticAssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TemplateLiteralAssignStmt}
	 * labeled alternative in {@link JinjaFlaskParser#assign_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateLiteralAssignStmt(JinjaFlaskParser.TemplateLiteralAssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HtmlContentDoubleTemplate}
	 * labeled alternative in {@link JinjaFlaskParser#template_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlContentDoubleTemplate(JinjaFlaskParser.HtmlContentDoubleTemplateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HtmlContentSingleTemplate}
	 * labeled alternative in {@link JinjaFlaskParser#template_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlContentSingleTemplate(JinjaFlaskParser.HtmlContentSingleTemplateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleForLoop}
	 * labeled alternative in {@link JinjaFlaskParser#for_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleForLoop(JinjaFlaskParser.SimpleForLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComplexForLoop}
	 * labeled alternative in {@link JinjaFlaskParser#for_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexForLoop(JinjaFlaskParser.ComplexForLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionDefDef}
	 * labeled alternative in {@link JinjaFlaskParser#func_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefDef(JinjaFlaskParser.FunctionDefDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Decorator}
	 * labeled alternative in {@link JinjaFlaskParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecorator(JinjaFlaskParser.DecoratorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionParameters}
	 * labeled alternative in {@link JinjaFlaskParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionParameters(JinjaFlaskParser.FunctionParametersContext ctx);
	/**
	 * Visit a parse tree produced by the {@code KeywordParams}
	 * labeled alternative in {@link JinjaFlaskParser#fun_params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordParams(JinjaFlaskParser.KeywordParamsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PositionalParams}
	 * labeled alternative in {@link JinjaFlaskParser#fun_params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositionalParams(JinjaFlaskParser.PositionalParamsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NameAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNameAtom(JinjaFlaskParser.NameAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ClassAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassAtom(JinjaFlaskParser.ClassAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberAtom(JinjaFlaskParser.NumberAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringAtom(JinjaFlaskParser.StringAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NoneAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoneAtom(JinjaFlaskParser.NoneAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanAtom}
	 * labeled alternative in {@link JinjaFlaskParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanAtom(JinjaFlaskParser.BooleanAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TrueAtom}
	 * labeled alternative in {@link JinjaFlaskParser#bool_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueAtom(JinjaFlaskParser.TrueAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FalseAtom}
	 * labeled alternative in {@link JinjaFlaskParser#bool_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseAtom(JinjaFlaskParser.FalseAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ListItems}
	 * labeled alternative in {@link JinjaFlaskParser#list_items}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListItems(JinjaFlaskParser.ListItemsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code KeyValuePairs}
	 * labeled alternative in {@link JinjaFlaskParser#dict_maker}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyValuePairs(JinjaFlaskParser.KeyValuePairsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AtomKeyValue}
	 * labeled alternative in {@link JinjaFlaskParser#key_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomKeyValue(JinjaFlaskParser.AtomKeyValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleKeyValue}
	 * labeled alternative in {@link JinjaFlaskParser#key_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleKeyValue(JinjaFlaskParser.SimpleKeyValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArithmeticExpression}
	 * labeled alternative in {@link JinjaFlaskParser#simple_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticExpression(JinjaFlaskParser.ArithmeticExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleComparisonExpression}
	 * labeled alternative in {@link JinjaFlaskParser#simple_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleComparisonExpression(JinjaFlaskParser.SimpleComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Addition}
	 * labeled alternative in {@link JinjaFlaskParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddition(JinjaFlaskParser.AdditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Subtraction}
	 * labeled alternative in {@link JinjaFlaskParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtraction(JinjaFlaskParser.SubtractionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Division}
	 * labeled alternative in {@link JinjaFlaskParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivision(JinjaFlaskParser.DivisionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Multiplication}
	 * labeled alternative in {@link JinjaFlaskParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplication(JinjaFlaskParser.MultiplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AtomArgs}
	 * labeled alternative in {@link JinjaFlaskParser#arglist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomArgs(JinjaFlaskParser.AtomArgsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComplexArgs}
	 * labeled alternative in {@link JinjaFlaskParser#arglist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexArgs(JinjaFlaskParser.ComplexArgsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PositionalArgument}
	 * labeled alternative in {@link JinjaFlaskParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositionalArgument(JinjaFlaskParser.PositionalArgumentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code KeywordArgument}
	 * labeled alternative in {@link JinjaFlaskParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordArgument(JinjaFlaskParser.KeywordArgumentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HtmlContent}
	 * labeled alternative in {@link JinjaFlaskParser#html_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlContent(JinjaFlaskParser.HtmlContentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HtmlElementItem}
	 * labeled alternative in {@link JinjaFlaskParser#html_content_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlElementItem(JinjaFlaskParser.HtmlElementItemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HtmlTextItem}
	 * labeled alternative in {@link JinjaFlaskParser#html_content_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlTextItem(JinjaFlaskParser.HtmlTextItemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaStmtItem}
	 * labeled alternative in {@link JinjaFlaskParser#html_content_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaStmtItem(JinjaFlaskParser.JinjaStmtItemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaExprItem}
	 * labeled alternative in {@link JinjaFlaskParser#html_content_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaExprItem(JinjaFlaskParser.JinjaExprItemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TagElement}
	 * labeled alternative in {@link JinjaFlaskParser#htmlElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagElement(JinjaFlaskParser.TagElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StyleElement}
	 * labeled alternative in {@link JinjaFlaskParser#htmlElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyleElement(JinjaFlaskParser.StyleElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HtmlAttribute}
	 * labeled alternative in {@link JinjaFlaskParser#tag_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlAttribute(JinjaFlaskParser.HtmlAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ClosingMarker}
	 * labeled alternative in {@link JinjaFlaskParser#tag_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClosingMarker(JinjaFlaskParser.ClosingMarkerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StyleSheet}
	 * labeled alternative in {@link JinjaFlaskParser#style_sheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyleSheet(JinjaFlaskParser.StyleSheetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CssRule}
	 * labeled alternative in {@link JinjaFlaskParser#ruleSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCssRule(JinjaFlaskParser.CssRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CssSelectorDeclaration}
	 * labeled alternative in {@link JinjaFlaskParser#selector_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCssSelectorDeclaration(JinjaFlaskParser.CssSelectorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CssSelectorList}
	 * labeled alternative in {@link JinjaFlaskParser#css_selector_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCssSelectorList(JinjaFlaskParser.CssSelectorListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code QualifiedSelector}
	 * labeled alternative in {@link JinjaFlaskParser#css_selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedSelector(JinjaFlaskParser.QualifiedSelectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StandaloneSimpleSelector}
	 * labeled alternative in {@link JinjaFlaskParser#css_selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStandaloneSimpleSelector(JinjaFlaskParser.StandaloneSimpleSelectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeAndIdSelector}
	 * labeled alternative in {@link JinjaFlaskParser#css_selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeAndIdSelector(JinjaFlaskParser.TypeAndIdSelectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeSelector}
	 * labeled alternative in {@link JinjaFlaskParser#css_selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSelector(JinjaFlaskParser.TypeSelectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DeclarationBlock}
	 * labeled alternative in {@link JinjaFlaskParser#declarationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationBlock(JinjaFlaskParser.DeclarationBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CssDeclaration}
	 * labeled alternative in {@link JinjaFlaskParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCssDeclaration(JinjaFlaskParser.CssDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionArguments}
	 * labeled alternative in {@link JinjaFlaskParser#css_function_args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionArguments(JinjaFlaskParser.FunctionArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CssFunctionCall}
	 * labeled alternative in {@link JinjaFlaskParser#css_function_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCssFunctionCall(JinjaFlaskParser.CssFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionTerm(JinjaFlaskParser.FunctionTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringTerm(JinjaFlaskParser.StringTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ColorTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorTerm(JinjaFlaskParser.ColorTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnitNumberTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitNumberTerm(JinjaFlaskParser.UnitNumberTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberTerm(JinjaFlaskParser.NumberTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdentifierTerm}
	 * labeled alternative in {@link JinjaFlaskParser#cssterm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierTerm(JinjaFlaskParser.IdentifierTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaStmtBlock}
	 * labeled alternative in {@link JinjaFlaskParser#jinjaStatementBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaStmtBlock(JinjaFlaskParser.JinjaStmtBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaExprBlock}
	 * labeled alternative in {@link JinjaFlaskParser#jinjaExpressionBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaExprBlock(JinjaFlaskParser.JinjaExprBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaExtendsStmt}
	 * labeled alternative in {@link JinjaFlaskParser#jStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaExtendsStmt(JinjaFlaskParser.JinjaExtendsStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaBlockStmt}
	 * labeled alternative in {@link JinjaFlaskParser#jStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaBlockStmt(JinjaFlaskParser.JinjaBlockStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaForStmt}
	 * labeled alternative in {@link JinjaFlaskParser#jStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaForStmt(JinjaFlaskParser.JinjaForStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaIfStmt}
	 * labeled alternative in {@link JinjaFlaskParser#jStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaIfStmt(JinjaFlaskParser.JinjaIfStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaSetStmt}
	 * labeled alternative in {@link JinjaFlaskParser#jStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaSetStmt(JinjaFlaskParser.JinjaSetStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaExtendsStmtDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_extends_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaExtendsStmtDef(JinjaFlaskParser.JinjaExtendsStmtDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaBlockStmtDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_block_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaBlockStmtDef(JinjaFlaskParser.JinjaBlockStmtDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaForStmtDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_for_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaForStmtDef(JinjaFlaskParser.JinjaForStmtDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaIfStmtDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaIfStmtDef(JinjaFlaskParser.JinjaIfStmtDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaSetStmtDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_set_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaSetStmtDef(JinjaFlaskParser.JinjaSetStmtDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaBinaryExpr}
	 * labeled alternative in {@link JinjaFlaskParser#j_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaBinaryExpr(JinjaFlaskParser.JinjaBinaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaSimpleExpr}
	 * labeled alternative in {@link JinjaFlaskParser#j_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaSimpleExpr(JinjaFlaskParser.JinjaSimpleExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaFilteredExpr}
	 * labeled alternative in {@link JinjaFlaskParser#j_call_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaFilteredExpr(JinjaFlaskParser.JinjaFilteredExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaFunctionCall}
	 * labeled alternative in {@link JinjaFlaskParser#j_call_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaFunctionCall(JinjaFlaskParser.JinjaFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaVarAccessOnly}
	 * labeled alternative in {@link JinjaFlaskParser#j_call_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaVarAccessOnly(JinjaFlaskParser.JinjaVarAccessOnlyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaAtomOnly}
	 * labeled alternative in {@link JinjaFlaskParser#j_call_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaAtomOnly(JinjaFlaskParser.JinjaAtomOnlyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaVarAccessOnlyDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_var_access}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaVarAccessOnlyDef(JinjaFlaskParser.JinjaVarAccessOnlyDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaArgListDef}
	 * labeled alternative in {@link JinjaFlaskParser#j_argument_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaArgListDef(JinjaFlaskParser.JinjaArgListDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaPosArg}
	 * labeled alternative in {@link JinjaFlaskParser#j_argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaPosArg(JinjaFlaskParser.JinjaPosArgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaKwArg}
	 * labeled alternative in {@link JinjaFlaskParser#j_argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaKwArg(JinjaFlaskParser.JinjaKwArgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaNumberAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaNumberAtom(JinjaFlaskParser.JinjaNumberAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaStringAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaStringAtom(JinjaFlaskParser.JinjaStringAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaTrueAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaTrueAtom(JinjaFlaskParser.JinjaTrueAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaFalseAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaFalseAtom(JinjaFlaskParser.JinjaFalseAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaNoneAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaNoneAtom(JinjaFlaskParser.JinjaNoneAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaNameAtom}
	 * labeled alternative in {@link JinjaFlaskParser#j_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaNameAtom(JinjaFlaskParser.JinjaNameAtomContext ctx);
}