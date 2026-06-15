//package visitor.jinja;
//
//import antlr.html.HtmlParser;
//import antlr.html.HtmlParserBaseVisitor;
//import ast.atom.*;
//import ast.atom.Number;
//import symbolTable.SymbolTable;
//import symbolTable.SymbolTableManager;
//
//public class JinjaAtomVisitor extends HtmlParserBaseVisitor<Atom> {
//
//    // استخدام نسخة جدول الرموز الموحدة للمشروع لضمان رؤية متغيرات Python
//    private final SymbolTable sb = SymbolTableManager.INSTANCE.getSymbolTable();
//
//    @Override
//    public Atom visitJinjaNumberAtom(HtmlParser.JinjaNumberAtomContext ctx) {
//        Number number = new Number(ctx.start.getLine());
//        number.setValue(ctx.J_NUMBER().getText());
//        return number;
//    }
//
//    @Override
//    public Atom visitJinjaStringAtom(HtmlParser.JinjaStringAtomContext ctx) {
//        Str str = new Str(ctx.start.getLine());
//        // تنظيف علامات التنصيص من النص إذا وجدت
//        String value = ctx.J_STRING().getText().replaceAll("^['\"]|['\"]$", "");
//        str.setValue(value);
//        return str;
//    }
//
//    @Override
//    public Atom visitJinjaTrueAtom(HtmlParser.JinjaTrueAtomContext ctx) {
//        Bool bool = new Bool(ctx.start.getLine());
//        bool.setValue("True");
//        return bool;
//    }
//
//    @Override
//    public Atom visitJinjaFalseAtom(HtmlParser.JinjaFalseAtomContext ctx) {
//        Bool bool = new Bool(ctx.start.getLine());
//        bool.setValue("False");
//        return bool;
//    }
//
//    @Override
//    public Atom visitJinjaNoneAtom(HtmlParser.JinjaNoneAtomContext ctx) {
//        return new None(ctx.start.getLine());
//    }
//
//    @Override
//    public Atom visitJinjaNameAtom(HtmlParser.JinjaNameAtomContext ctx) {
//        // 1. استخراج اسم المتغير (المعرف)
//        String varName = ctx.J_NAME().getText();
//
//        // 2. الفحص الدلالي الحاسم:
//        // إذا كان المتغير غير موجود في جدول الرموز، نطبع الخطأ فوراً في الـ Terminal
//        if (sb.lookup(varName) == null) {
//            System.err.println("Semantic Error: Undefined Jinja variable '" + varName + "' at line " + ctx.start.getLine());
//        }
//
//        Name name = new Name(ctx.start.getLine());
//        name.setValue(varName);
//        return name;
//    }
//}
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

//    @Override
//    public Object visitJinjaFalseAtom(HtmlParser.JinjaFalseAtomContext ctx) {
//        return null;
//    }

    @Override
    public Object visitJinjaNoneAtom(HtmlParser.JinjaNoneAtomContext ctx) {
        return new None(ctx.start.getLine());
    }

    @Override
    public Object visitJinjaNameAtom(HtmlParser.JinjaNameAtomContext ctx) {
        String varName = ctx.J_NAME().getText().trim();

        // سطر تتبع للتأكد من سير عملية التحليل في الـ Terminal
        System.out.println("--- CRITICAL DEBUG: Checking '" + varName + "' in Python Table ---");

        // الفحص الدلالي: تمرير "python" كمعامل ثانٍ لإصلاح خطأ Screenshot (217).jpg
        if (SymbolTableManager.INSTANCE.lookup(varName, "python") == null) {
            System.err.println("Semantic Error: Undefined Jinja variable '" + varName + "' at line " + ctx.getStart().getLine());
        }

        // بناء عقدة الـ AST لإرجاعها (بناءً على ما ظهر في Screenshot (216).jpg)
        Name nameNode = new Name(ctx.getStart().getLine());
        nameNode.setValue(varName);

        return nameNode;
    }
    @Override
    public JinjaListLiteral visitJinjaListAtom(HtmlParser.JinjaListAtomContext ctx) {
        JinjaListLiteral list = new JinjaListLiteral(ctx.start.getLine());
        List<String> items = new ArrayList<>();

        // Cast إلى JinjaListLiteralContext لأنها تحتوي على j_atom()
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
