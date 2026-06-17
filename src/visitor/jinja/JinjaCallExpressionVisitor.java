//package visitor.jinja;
//
//import antlr.html.HtmlParser;
//import antlr.html.HtmlParserBaseVisitor;
//import ast.atom.Atom;
//import ast.jinja.JinjaArgumentsList;
//import ast.jinja.jinjaCallExpr.JinjaAtom;
//import ast.jinja.jinjaCallExpr.JinjaCallExpression;
//import ast.jinja.jinjaCallExpr.JinjaFilteredExpression;
//import ast.jinja.jinjaCallExpr.JinjaFunctionCall;
//import ast.jinja.jinjaCallExpr.JinjaVariableAccess;
//// استيراد كلاسات جدول الرموز للتحقق من المعرفات
//import symbolTable.SymbolTable;
//import symbolTable.SymbolTableManager;
//
//public class JinjaCallExpressionVisitor extends HtmlParserBaseVisitor<JinjaCallExpression> {
//
//    // الوصول إلى نسخة جدول الرموز المشتركة في المشروع
//    private final SymbolTable sb = SymbolTableManager.INSTANCE.getSymbolTable();
//    JinjaVisitor jinjaVisitor = new JinjaVisitor();
//
//    @Override
//    public JinjaCallExpression visitJinjaFilteredExpr(HtmlParser.JinjaFilteredExprContext ctx) {
//        JinjaFilteredExpression jinjaFilteredExpression = new JinjaFilteredExpression(ctx.start.getLine());
//        JinjaVariableAccess jinjaVariableAccess = (JinjaVariableAccess) jinjaVisitor.visit(ctx.j_var_access());
//        jinjaFilteredExpression.setJinjaVariableAccess(jinjaVariableAccess);
//
//        if (ctx.getChild(2) != null) {
//            jinjaFilteredExpression.setFilterName(ctx.getChild(2).getText());
//        }
//        return jinjaFilteredExpression;
//    }
//
//    @Override
//    public JinjaCallExpression visitJinjaFunctionCall(HtmlParser.JinjaFunctionCallContext ctx) {
//        // 1. استخراج اسم الدالة المطلوب استدعاؤها في Jinja
//        String funcName = ctx.J_NAME().getText();
//
//        // 2. الفحص الدلالي: هل اسم الدالة موجود في جدول الرموز؟
//        // إذا كان lookup يعيد null، فهذا يعني أن الدالة غير معرفة
//        if (sb.lookup(funcName) == null) {
//            System.err.println("Semantic Error: Undefined function or variable '" + funcName + "' at line " + ctx.start.getLine());
//        }
//
//        JinjaFunctionCall jinjaFunctionCall = new JinjaFunctionCall(ctx.start.getLine());
//        jinjaFunctionCall.setFunctionName(funcName);
//
//        if (ctx.j_argument_list() != null) {
//            JinjaArgumentsList jinjaArgumentsList = (JinjaArgumentsList) jinjaVisitor.visit(ctx.j_argument_list());
//            jinjaFunctionCall.setArgumentsList(jinjaArgumentsList);
//        }
//        return jinjaFunctionCall;
//    }
//
//    @Override
//    public JinjaCallExpression visitJinjaVarAccessOnly(HtmlParser.JinjaVarAccessOnlyContext ctx) {
//        // يتم الاعتماد على JinjaVisitor لمعالجة الوصول للمتغيرات وفحصها
//        return (JinjaCallExpression) jinjaVisitor.visit(ctx.j_var_access());
//    }
//
//    @Override
//    public JinjaCallExpression visitJinjaAtomOnly(HtmlParser.JinjaAtomOnlyContext ctx) {
//        // 1. استخدام visit لضمان تشغيل منطق visitJinjaNameAtom الموجود في JinjaAtomVisitor
//        Atom atom = new JinjaAtomVisitor().visit(ctx.j_atom());
//
//        // 2. بناء العقدة وإعادتها
//        return new JinjaAtom(ctx.start.getLine(), atom);
//    }
//}
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

        // فحص وجود الدالة في جدول بايثون
        if (SymbolTableManager.INSTANCE.lookup(funcName, "python") == null) {
            System.err.println("Semantic Error: Undefined function '" + funcName +
                    "' at line " + ctx.getStart().getLine());
        }
        JinjaFunctionCall jinjaFunctionCall = new JinjaFunctionCall(ctx.getStart().getLine());
        jinjaFunctionCall.setFunctionName(funcName);

        if (ctx.j_argument_list() != null) {
            // ملاحظة: إذا كان لديك JinjaArgumentVisitor منفصل، استدعه يدوياً هنا أيضاً
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
