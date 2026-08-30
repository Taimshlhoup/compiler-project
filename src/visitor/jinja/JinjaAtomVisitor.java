
package visitor.jinja;

import antlr.html.HtmlParser;
import antlr.html.HtmlParserBaseVisitor;
import ast.atom.Bool;
import ast.atom.None;
import ast.atom.Name; // أضفت هذا الاستيراد لبناء الشجرة بشكل صحيح
import ast.atom.Str;
import ast.jinja.jinjaExpression.JinjaListLiteral;
import symbolTable.SymbolTableManager;

import java.util.ArrayList;
import java.util.List;

public class JinjaAtomVisitor extends HtmlParserBaseVisitor<Object> {



    @Override
    public Object visitJinjaNoneAtom(HtmlParser.JinjaNoneAtomContext ctx) {
        return new None(ctx.start.getLine());
    }

    @Override
    public Object visitJinjaNameAtom(HtmlParser.JinjaNameAtomContext ctx) {
        String varName = ctx.J_NAME().getText().trim();


        System.out.println("--- CRITICAL DEBUG: Checking '" + varName + "' in Python Table ---");


        if (SymbolTableManager.INSTANCE.lookup(varName, "python") == null) {
            System.err.println("Semantic Error: Undefined Jinja variable '" + varName + "' at line " + ctx.getStart().getLine());
        }


        Name nameNode = new Name(ctx.getStart().getLine());
        nameNode.setValue(varName);

        return nameNode;
    }
    @Override
    public JinjaListLiteral visitJinjaListAtom(HtmlParser.JinjaListAtomContext ctx) {
        JinjaListLiteral list = new JinjaListLiteral(ctx.start.getLine());
        List<String> items = new ArrayList<>();


        HtmlParser.JinjaListLiteralContext listCtx =
                (HtmlParser.JinjaListLiteralContext) ctx.j_list_literal();

        for (HtmlParser.J_atomContext atomCtx : listCtx.j_atom()) {
            items.add(atomCtx.getText());
        }

        list.setItems(items);
        return list;
    }
    @Override
    public Object visitJinjaNumberAtom(HtmlParser.JinjaNumberAtomContext ctx) {
        ast.atom.Number number = new ast.atom.Number(ctx.getStart().getLine());
        number.setValue(ctx.J_NUMBER().getText());
        return number;
    }

    @Override
    public Object visitJinjaStringAtom(HtmlParser.JinjaStringAtomContext ctx) {
        Str str = new Str(ctx.getStart().getLine());
        str.setValue(ctx.J_STRING().getText());
        return str;
    }

    @Override
    public Object visitJinjaTrueAtom(HtmlParser.JinjaTrueAtomContext ctx) {
        Bool bool = new Bool(ctx.getStart().getLine());
        bool.setValue(true);
        return bool;
    }

    @Override
    public Object visitJinjaFalseAtom(HtmlParser.JinjaFalseAtomContext ctx) {
        Bool bool = new Bool(ctx.getStart().getLine());
        bool.setValue(false);
        return bool;
    }
}
