
package visitor.jinja;

import antlr.html.HtmlParser;
import antlr.html.HtmlParserBaseVisitor;
import ast.HtmlContent;
import ast.jinja.jinjaExpression.JinjaExpression;
import ast.jinja.jinjaStatment.*;
import symbolTable.SymbolTable;
import symbolTable.SymbolTableManager;
import visitor.html.HtmlContentVisitor;

public class JinjaStatementVisitor extends HtmlParserBaseVisitor<JinjaStatement> {
    private final HtmlContentVisitor universalVisitor = new HtmlContentVisitor();

    @Override
    public JinjaStatement visitJinjaSetStmt(HtmlParser.JinjaSetStmtContext ctx) {
        return visit(ctx.j_set_stmt());
    }


@Override
public JinjaSetStatement visitJinjaSetStmtDef(HtmlParser.JinjaSetStmtDefContext ctx) {
    JinjaSetStatement jinjaSetStatement = new JinjaSetStatement(ctx.start.getLine());

    String varName = ctx.J_NAME().getText();
    jinjaSetStatement.setVariableName(varName);

    JinjaExpressionVisitor jinjaExpressionVisitor = new JinjaExpressionVisitor();
    JinjaExpression value = (JinjaExpression) jinjaExpressionVisitor.visit(ctx.j_expression());
    jinjaSetStatement.setValue(value);

    SymbolTable currentScope = SymbolTableManager.INSTANCE.getJinjaTable();
    currentScope.insert(varName);


    String exprText = ctx.j_expression().getText().trim();
    String inferredType = inferType(exprText);
    currentScope.setAttribute(varName, "Type", inferredType);


    currentScope.setAttribute(varName, "Value", exprText);

    return jinjaSetStatement;
}


    private String inferType(String value) {
        if (value.matches("[0-9]+"))           return "Integer";
        if (value.matches("[0-9]*\\.[0-9]+"))  return "Float";
        if (value.startsWith("\"") ||
                value.startsWith("'"))             return "String";
        if (value.equals("true") ||
                value.equals("false"))             return "Boolean";
        if (value.equals("none"))              return "None";
        if (value.startsWith("["))             return "List";
        return "Dynamic";
    }


    @Override
    public JinjaStatement visitJinjaExtendsStmt(HtmlParser.JinjaExtendsStmtContext ctx) {
        return visit(ctx.j_extends_stmt());
    }

    @Override
    public JinjaExtendStatement visitJinjaExtendsStmtDef(HtmlParser.JinjaExtendsStmtDefContext ctx) {
        JinjaExtendStatement jinjaExtendStatement = new JinjaExtendStatement(ctx.start.getLine());
        jinjaExtendStatement.setExtended(ctx.J_STRING().getText());
        return jinjaExtendStatement;
    }

    @Override
    public JinjaStatement visitJinjaBlockStmt(HtmlParser.JinjaBlockStmtContext ctx) {
        return visit(ctx.j_block_stmt());
    }

    @Override
    public JinjaBlockStatement visitJinjaBlockStmtDef(HtmlParser.JinjaBlockStmtDefContext ctx) {
        JinjaBlockStatement jinjaBlockStatement = new JinjaBlockStatement(ctx.start.getLine());
        jinjaBlockStatement.setBlockName(ctx.J_NAME().getFirst().getText());
        HtmlContent htmlContent = (HtmlContent) universalVisitor.visit(ctx.html_content());
        jinjaBlockStatement.setHtmlContent(htmlContent);
        return jinjaBlockStatement;
    }

    @Override
    public JinjaStatement visitJinjaForStmt(HtmlParser.JinjaForStmtContext ctx) {
        return visit(ctx.j_for_stmt());
    }

    @Override
    public JinjaForStatement visitJinjaForStmtDef(HtmlParser.JinjaForStmtDefContext ctx) {
        JinjaForStatement jinjaForStatement = new JinjaForStatement(ctx.start.getLine());
        String loopVar = ctx.J_NAME().getText();
        jinjaForStatement.setId(loopVar);


        SymbolTable parentScope = SymbolTableManager.INSTANCE.getJinjaTable();


        SymbolTable childScope = new SymbolTable(parentScope);


        childScope.insert(loopVar);

        childScope.setAttribute(loopVar, "Type", "Dynamic");


        SymbolTableManager.INSTANCE.setJinjaTable(childScope);


        try {
            JinjaExpressionVisitor jinjaExpressionVisitor = new JinjaExpressionVisitor();
            JinjaExpression jinjaExpression = (JinjaExpression) jinjaExpressionVisitor.visit(ctx.j_expression());


            HtmlContent htmlContent = (HtmlContent) universalVisitor.visit(ctx.html_content());

            jinjaForStatement.setIterable(jinjaExpression);
            jinjaForStatement.setHtmlContent(htmlContent);

        } finally {
            for (String varName : childScope.getLocalVarNames()) {
                parentScope.addOutOfScope(varName);
            }
            SymbolTableManager.INSTANCE.setJinjaTable(parentScope);
        }

        return jinjaForStatement;
    }

    @Override
    public JinjaStatement visitJinjaIfStmt(HtmlParser.JinjaIfStmtContext ctx) {
        return visit(ctx.j_if_stmt());
    }

    @Override
    public JinjaStatement visitJinjaIfStmtDef(HtmlParser.JinjaIfStmtDefContext ctx) {
        JinjaIfStatement jinjaIfStatement = new JinjaIfStatement(ctx.start.getLine());

        JinjaExpression jinjaExpression = (JinjaExpression) new JinjaExpressionVisitor().visit(ctx.j_expression());
        HtmlContent htmlContent = (HtmlContent) universalVisitor.visit(ctx.html_content());

        jinjaIfStatement.setHtmlContent(htmlContent);
        jinjaIfStatement.setCondition(jinjaExpression);
        return jinjaIfStatement;
    }


}
