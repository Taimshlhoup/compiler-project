
package visitor.jinja;

import antlr.html.HtmlParser;
import antlr.html.HtmlParserBaseVisitor;
import ast.jinja.jinjaCallExpr.*;
import symbolTable.SymbolTableManager;
import ast.jinja.JinjaArgumentsList;

public class JinjaCallExpressionVisitor extends HtmlParserBaseVisitor<Object> {


    private final JinjaAtomVisitor atomVisitor = new JinjaAtomVisitor();

@Override
public Object visitJinjaAtomOnly(HtmlParser.JinjaAtomOnlyContext ctx) {
    Object atomResult = atomVisitor.visit(ctx.j_atom());


    if (atomResult instanceof ast.atom.Atom) {
        return new JinjaAtom(ctx.getStart().getLine(), (ast.atom.Atom) atomResult);
    }


    return atomResult;
}

    @Override
    public Object visitJinjaFunctionCall(HtmlParser.JinjaFunctionCallContext ctx) {
        String funcName = ctx.J_NAME().getText().trim();


        if (SymbolTableManager.INSTANCE.lookup(funcName, "python") == null) {
            System.err.println("Semantic Error: Undefined function '" + funcName +
                    "' at line " + ctx.getStart().getLine());
        }
        JinjaFunctionCall jinjaFunctionCall = new JinjaFunctionCall(ctx.getStart().getLine());
        jinjaFunctionCall.setFunctionName(funcName);

        if (ctx.j_argument_list() != null) {

            Object args = visit(ctx.j_argument_list());
            if (args instanceof JinjaArgumentsList) {
                jinjaFunctionCall.setArgumentsList((JinjaArgumentsList) args);
            }
        }

        return jinjaFunctionCall;
    }


    @Override
    public Object visitJinjaVarAccessOnly(HtmlParser.JinjaVarAccessOnlyContext ctx) {
        return visit(ctx.j_var_access());
    }


    @Override
    public Object visitJinjaVarAccessOnlyDef(HtmlParser.JinjaVarAccessOnlyDefContext ctx) {
        JinjaVariableAccess access = new JinjaVariableAccess(ctx.getStart().getLine());
        StringBuilder name = new StringBuilder(ctx.J_NAME(0).getText());
        for (int i = 1; i < ctx.J_NAME().size(); i++) {
            name.append(".").append(ctx.J_NAME(i).getText());
        }
        access.setDottedName(name.toString());
        return access;
    }

    @Override
    public Object visitJinjaFilteredExpr(HtmlParser.JinjaFilteredExprContext ctx) {
        return visit(ctx.j_var_access());
    }
}
