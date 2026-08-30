
package visitor.jinja;

import antlr.html.HtmlParser;
import antlr.html.HtmlParserBaseVisitor;
import ast.ASTNode;
import ast.jinja.JinjaArgumentsList;
import ast.jinja.jinjaArg.JinjaArgument;
import ast.jinja.jinjaCallExpr.JinjaVariableAccess;

import java.util.ArrayList;
import java.util.List;

public class JinjaVisitor extends HtmlParserBaseVisitor<ASTNode> {



@Override
public JinjaVariableAccess visitJinjaVarAccessOnlyDef(HtmlParser.JinjaVarAccessOnlyDefContext ctx) {


    try {

        String fullVarText = ctx.getText().trim();


        String baseVar = fullVarText;
        if (baseVar.contains(".")) {
            baseVar = baseVar.split("\\.")[0].trim();
        }
        baseVar = baseVar.replaceAll("[^a-zA-Z0-9_]", "");


        symbolTable.SymbolTable jinjaTable = symbolTable.SymbolTableManager.INSTANCE.getJinjaTable();
        symbolTable.SymbolTable pythonTable = visitor.python.AtomExpressionVisitor.pythonScopeAtRender;


        if (jinjaTable != null && jinjaTable.lookup(baseVar) != null) {
            jinjaTable.setAttribute(baseVar, "Type", "Dynamic");
        }

        else if (pythonTable != null && pythonTable.lookup(baseVar) != null) {
            System.err.println("Semantic Error: Missing flask variable '" + fullVarText + "' at line " + ctx.getStart().getLine());
        }

        else {
            System.err.println("Semantic Error: Undefined variable '" + fullVarText + "' in Jinja template at line " + ctx.getStart().getLine());
        }

    } catch (Exception e) {

    }


    return (JinjaVariableAccess) super.visitJinjaVarAccessOnlyDef(ctx);
}



    @Override
    public JinjaArgumentsList visitJinjaArgListDef(HtmlParser.JinjaArgListDefContext ctx) {
        JinjaArgumentsList jinjaArgumentsList = new JinjaArgumentsList(ctx.start.getLine());
        JinjaArgumentVisitor jinjaArgumentVisitor = new JinjaArgumentVisitor();
        List<JinjaArgument> arguments = new ArrayList<>();
        for(int i = 0; i < ctx.j_argument().size();i ++){
            JinjaArgument jinjaArgument = jinjaArgumentVisitor.visit(ctx.j_argument(i));
            arguments.add(jinjaArgument);
        }
        jinjaArgumentsList.setArguments(arguments);
        return jinjaArgumentsList;
    }
}