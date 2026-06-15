//package visitor.jinja;
//
//import antlr.html.HtmlParser;
//import antlr.html.HtmlParserBaseVisitor;
//import ast.ASTNode;
//import ast.jinja.JinjaArgumentsList;
//import ast.jinja.jinjaArg.JinjaArgument;
//import ast.jinja.jinjaCallExpr.JinjaVariableAccess;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class JinjaVisitor extends HtmlParserBaseVisitor<ASTNode> {
//
//    @Override
//    public JinjaVariableAccess visitJinjaVarAccessOnlyDef(HtmlParser.JinjaVarAccessOnlyDefContext ctx) {
//        JinjaVariableAccess jinjaVariableAccess = new JinjaVariableAccess(ctx.start.getLine());
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(ctx.J_NAME(0));
//        for (int i = 1; i < ctx.J_NAME().size(); i++) {
//            stringBuilder.append(".").append(ctx.J_NAME(i));
//        }
//        jinjaVariableAccess.setDottedName(stringBuilder.toString());
//        return jinjaVariableAccess;
//    }
//
//    @Override
//    public JinjaArgumentsList visitJinjaArgListDef(HtmlParser.JinjaArgListDefContext ctx) {
//        JinjaArgumentsList jinjaArgumentsList = new JinjaArgumentsList(ctx.start.getLine());
//        JinjaArgumentVisitor jinjaArgumentVisitor = new JinjaArgumentVisitor();
//        List<JinjaArgument> arguments = new ArrayList<>();
//        for(int i = 0; i < ctx.j_argument().size();i ++){
//            JinjaArgument jinjaArgument = jinjaArgumentVisitor.visit(ctx.j_argument(i));
//            arguments.add(jinjaArgument);
//        }
//        jinjaArgumentsList.setArguments(arguments);
//        return jinjaArgumentsList;
//    }
//}
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
    // ❌ احذف السطر القديم jinjaVariableAccess = new JinjaVariableAccess(); من هنا

    try {
        // 1. النص الكامل للمتغير
        String fullVarText = ctx.getText().trim();

        // 2. استخراج الاسم الجذري
        String baseVar = fullVarText;
        if (baseVar.contains(".")) {
            baseVar = baseVar.split("\\.")[0].trim();
        }
        baseVar = baseVar.replaceAll("[^a-zA-Z0-9_]", "");

        // 3. استدعاء الجداول للمقارنة
        symbolTable.SymbolTable jinjaTable = symbolTable.SymbolTableManager.INSTANCE.getJinjaTable();
        symbolTable.SymbolTable pythonTable = visitor.python.AtomExpressionVisitor.pythonScopeAtRender;

        // 🟢 الحالة الأولى: المتغير ممرر وسليم
        if (jinjaTable != null && jinjaTable.lookup(baseVar) != null) {
            jinjaTable.setAttribute(baseVar, "Type", "Dynamic");
        }
        // 🟡 الحالة الثانية: مفقود في فلاسك ومعرف في بايثون
        else if (pythonTable != null && pythonTable.lookup(baseVar) != null) {
            System.err.println("Semantic Error: Missing flask variable '" + fullVarText + "' at line " + ctx.getStart().getLine());
        }
        // 🔴 الحالة الثالثة: غير معرف نهائياً
        else {
            System.err.println("Semantic Error: Undefined variable '" + fullVarText + "' in Jinja template at line " + ctx.getStart().getLine());
        }

    } catch (Exception e) {
        // حماية لضمان استمرار المفسر
    }

    // 🔥 الحل المنقذ: نقوم بعمل تحويل (Casting) للتابع الأساسي ليقبله الكومبايلر فوراً
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