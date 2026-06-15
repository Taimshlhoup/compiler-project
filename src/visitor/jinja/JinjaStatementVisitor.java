//package visitor.jinja;
//
//import antlr.html.HtmlParser;
//import antlr.html.HtmlParserBaseVisitor;
//import ast.HtmlContent;
//import ast.jinja.jinjaExpression.JinjaExpression;
//import ast.jinja.jinjaStatment.*;
//import symbolTable.SymbolEntry;
//import visitor.html.HtmlContentVisitor;
//
//
//public class JinjaStatementVisitor extends HtmlParserBaseVisitor<JinjaStatement> {
//    private final HtmlContentVisitor universalVisitor = new HtmlContentVisitor();
//
//
//    @Override
//    public JinjaStatement visitJinjaExtendsStmt(HtmlParser.JinjaExtendsStmtContext ctx) {
//        return visit(ctx.j_extends_stmt());
//    }
//
//    @Override
//    public JinjaExtendStatement visitJinjaExtendsStmtDef(HtmlParser.JinjaExtendsStmtDefContext ctx) {
//        JinjaExtendStatement jinjaExtendStatement = new JinjaExtendStatement(ctx.start.getLine());
//        jinjaExtendStatement.setExtended(ctx.J_STRING().getText());
//        return jinjaExtendStatement;
//    }
//
//    @Override
//    public JinjaStatement visitJinjaBlockStmt(HtmlParser.JinjaBlockStmtContext ctx) {
//        return visit(ctx.j_block_stmt());
//    }
//
//    @Override
//    public JinjaBlockStatement visitJinjaBlockStmtDef(HtmlParser.JinjaBlockStmtDefContext ctx) {
//        JinjaBlockStatement jinjaBlockStatement = new JinjaBlockStatement(ctx.start.getLine());
//        jinjaBlockStatement.setBlockName(ctx.J_NAME().getFirst().getText());
//        HtmlContent htmlContent = (HtmlContent) universalVisitor.visit(ctx.html_content());
//        jinjaBlockStatement.setHtmlContent(htmlContent);
//        return jinjaBlockStatement;
//    }
//
//    @Override
//    public JinjaStatement visitJinjaForStmt(HtmlParser.JinjaForStmtContext ctx) {
//        return visit(ctx.j_for_stmt());
//    }
//
////    @Override
////    public JinjaForStatement visitJinjaForStmtDef(HtmlParser.JinjaForStmtDefContext ctx) {
////        JinjaForStatement jinjaForStatement = new JinjaForStatement(ctx.start.getLine());
////        jinjaForStatement.setId(ctx.J_NAME().getText());
////
////        JinjaExpressionVisitor jinjaExpressionVisitor = new JinjaExpressionVisitor();
////
////        // التعديل المطلوب هنا في السطر 50
////        JinjaExpression jinjaExpression = (JinjaExpression) jinjaExpressionVisitor.visit(ctx.j_expression());
////
////        // تأكد أيضاً من عمل Casting لـ HtmlContent إذا كان الـ universalVisitor يعيد Object
////        HtmlContent htmlContent = (HtmlContent) universalVisitor.visit(ctx.html_content());
////
////        jinjaForStatement.setIterable(jinjaExpression);
////        jinjaForStatement.setHtmlContent(htmlContent);
////        return jinjaForStatement;
////    }
//@Override
//public JinjaForStatement visitJinjaForStmtDef(HtmlParser.JinjaForStmtDefContext ctx) {
//    JinjaForStatement jinjaForStatement = new JinjaForStatement(ctx.start.getLine());
//    String loopVar = ctx.J_NAME().getText();
//    jinjaForStatement.setId(loopVar);
//
//    // =======================================================
//    // 1. فتح نطاق محلي جديد للحلقة في جدول الرموز
//    // (تأكد من اسم الميثود لديك، قد تكون st.pushScope() أو مشابه)
//    symbolTable.pushScope();
//
//    // 2. تعريف متغير الحلقة داخل السكوب الجديد ليكون محلياً (مثل user)
//    symbolTable.insert(loopVar, new SymbolEntry("String"));
//    // =======================================================
//
//    JinjaExpressionVisitor jinjaExpressionVisitor = new JinjaExpressionVisitor();
//    JinjaExpression jinjaExpression = (JinjaExpression) jinjaExpressionVisitor.visit(ctx.j_expression());
//
//    // زيارة محتوى الحلقة الداخلي: أي عملية set تحدث هنا ستُسجل في السكوب الجديد
//    HtmlContent htmlContent = (HtmlContent) universalVisitor.visit(ctx.html_content());
//
//    jinjaForStatement.setIterable(jinjaExpression);
//    jinjaForStatement.setHtmlContent(htmlContent);
//
//    // =======================================================
//    // 3. تدمير السكوب المحلي للحلقة قبل الخروج من التابع (هنا السر!)
//    symbolTable.popScope();
//    // =======================================================
//
//    return jinjaForStatement;
//}
//
//    @Override
//    public JinjaStatement visitJinjaIfStmt(HtmlParser.JinjaIfStmtContext ctx) {
//        return visit(ctx.j_if_stmt());
//    }
//
//    @Override
//    public JinjaStatement visitJinjaIfStmtDef(HtmlParser.JinjaIfStmtDefContext ctx) {
//        JinjaIfStatement jinjaIfStatement = new JinjaIfStatement(ctx.start.getLine());
//
//        // التعديل المطلوب هنا في السطر 65
//        JinjaExpression jinjaExpression = (JinjaExpression) new JinjaExpressionVisitor().visit(ctx.j_expression());
//
//        HtmlContent htmlContent = (HtmlContent) universalVisitor.visit(ctx.html_content());
//
//        jinjaIfStatement.setHtmlContent(htmlContent);
//        jinjaIfStatement.setCondition(jinjaExpression);
//        return jinjaIfStatement;
//    }
//
//
//}

//package visitor.jinja;
//
//import antlr.html.HtmlParser;
//import antlr.html.HtmlParserBaseVisitor;
//import ast.HtmlContent;
//import ast.jinja.jinjaExpression.JinjaExpression;
//import ast.jinja.jinjaStatment.*;
//import symbolTable.SymbolEntry;
//import symbolTable.SymbolTable;
//import symbolTable.SymbolTableManager;
//import visitor.html.HtmlContentVisitor;
//
//public class JinjaStatementVisitor extends HtmlParserBaseVisitor<JinjaStatement> {
//    private final HtmlContentVisitor universalVisitor = new HtmlContentVisitor();
//
//    // جلب جدول الرموز الخاص بالجنجا من الـ Manager لمنع خطأ الـ symbolTable الأحمر
//    private final SymbolTable symbolTable = SymbolTableManager.INSTANCE.getJinjaTable();
//
//    @Override
//    public JinjaStatement visitJinjaExtendsStmt(HtmlParser.JinjaExtendsStmtContext ctx) {
//        return visit(ctx.j_extends_stmt());
//    }
//
//    @Override
//    public JinjaExtendStatement visitJinjaExtendsStmtDef(HtmlParser.JinjaExtendsStmtDefContext ctx) {
//        JinjaExtendStatement jinjaExtendStatement = new JinjaExtendStatement(ctx.start.getLine());
//        jinjaExtendStatement.setExtended(ctx.J_STRING().getText());
//        return jinjaExtendStatement;
//    }
//
//    @Override
//    public JinjaStatement visitJinjaBlockStmt(HtmlParser.JinjaBlockStmtContext ctx) {
//        return visit(ctx.j_block_stmt());
//    }
//
//    @Override
//    public JinjaBlockStatement visitJinjaBlockStmtDef(HtmlParser.JinjaBlockStmtDefContext ctx) {
//        JinjaBlockStatement jinjaBlockStatement = new JinjaBlockStatement(ctx.start.getLine());
//        jinjaBlockStatement.setBlockName(ctx.J_NAME().getFirst().getText());
//        HtmlContent htmlContent = (HtmlContent) universalVisitor.visit(ctx.html_content());
//        jinjaBlockStatement.setHtmlContent(htmlContent);
//        return jinjaBlockStatement;
//    }
//
//    @Override
//    public JinjaStatement visitJinjaForStmt(HtmlParser.JinjaForStmtContext ctx) {
//        return visit(ctx.j_for_stmt());
//    }
//
//    @Override
//    public JinjaForStatement visitJinjaForStmtDef(HtmlParser.JinjaForStmtDefContext ctx) {
//        JinjaForStatement jinjaForStatement = new JinjaForStatement(ctx.start.getLine());
//        String loopVar = ctx.J_NAME().getText();
//        jinjaForStatement.setId(loopVar);
//
//        // 1. جلب السكوب الأب الحالي
//        SymbolTable parentScope = SymbolTableManager.INSTANCE.getJinjaTable();
//
//        // 2. إنشاء سكوب فرعي جديد يربطه بالأب
//        SymbolTable childScope = new SymbolTable(parentScope);
//
//        // تسجيل متغير الحلقة داخل السكوب الفرعي
//        childScope.insert(loopVar);
//        childScope.setAttribute(loopVar, "Type", "String");
//
//        // تحويل المانيجر ليقرأ ويكتب داخل السكوب الفرعي (كل الـ Visitors ستتوجه لهنا)
//        SymbolTableManager.INSTANCE.setJinjaTable(childScope);
//
//        // زيارة محتوى الحلقة
//        JinjaExpressionVisitor jinjaExpressionVisitor = new JinjaExpressionVisitor();
//        JinjaExpression jinjaExpression = (JinjaExpression) jinjaExpressionVisitor.visit(ctx.j_expression());
//        HtmlContent htmlContent = (HtmlContent) universalVisitor.visit(ctx.html_content());
//
//        jinjaForStatement.setIterable(jinjaExpression);
//        jinjaForStatement.setHtmlContent(htmlContent);
//
//        // 3. عند الخروج: استعادة السكوب الأب وتدمير الفرعي بالكامل
//        SymbolTableManager.INSTANCE.setJinjaTable(parentScope);
//
//        return jinjaForStatement;
//    }
//
//    @Override
//    public JinjaStatement visitJinjaIfStmt(HtmlParser.JinjaIfStmtContext ctx) {
//        return visit(ctx.j_if_stmt());
//    }
//
//    @Override
//    public JinjaStatement visitJinjaIfStmtDef(HtmlParser.JinjaIfStmtDefContext ctx) {
//        JinjaIfStatement jinjaIfStatement = new JinjaIfStatement(ctx.start.getLine());
//
//        JinjaExpression jinjaExpression = (JinjaExpression) new JinjaExpressionVisitor().visit(ctx.j_expression());
//        HtmlContent htmlContent = (HtmlContent) universalVisitor.visit(ctx.html_content());
//
//        jinjaIfStatement.setHtmlContent(htmlContent);
//        jinjaIfStatement.setCondition(jinjaExpression);
//        return jinjaIfStatement;
//    }
//}
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

// ✅ استنتاج النوع من القيمة
    String exprText = ctx.j_expression().getText().trim();
    String inferredType = inferType(exprText);
    currentScope.setAttribute(varName, "Type", inferredType);

// ✅ Add this line to store the actual value
    currentScope.setAttribute(varName, "Value", exprText);

    return jinjaSetStatement;
}

    // ✅ دالة مساعدة لاستنتاج النوع
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

        // 1. جلب السكوب الأب الحالي لجينجا
        SymbolTable parentScope = SymbolTableManager.INSTANCE.getJinjaTable();

        // 2. إنشاء سكوب فرعي جديد يربطه بالأب لحصر المتغير داخله
        SymbolTable childScope = new SymbolTable(parentScope);

        // تسجيل متغير الحلقة داخل السكوب الفرعي فوراً
        childScope.insert(loopVar);
        // 🧠 جعلناه Dynamic لأن عناصر المصفوفات في جينجا متغيرة الأنواع
        childScope.setAttribute(loopVar, "Type", "Dynamic");

        // تحويل المانيجر ليقرأ ويكتب داخل السكوب الفرعي
        SymbolTableManager.INSTANCE.setJinjaTable(childScope);

        // 🛡️ استخدام try-finally لضمان تدمير السكوب حتى لو انهار المفسر بالداخل
        try {
            JinjaExpressionVisitor jinjaExpressionVisitor = new JinjaExpressionVisitor();
            JinjaExpression jinjaExpression = (JinjaExpression) jinjaExpressionVisitor.visit(ctx.j_expression());

            // خلال زيارة الـ body، أي عملية طباعة أو إسناد ستتعامل مع الـ childScope
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
