//package visitor.jinja;
//
//import antlr.html.HtmlParser;
//import antlr.html.HtmlParserBaseVisitor;
//import ast.jinja.jinjaExpression.JinjaBinaryExpression;
//import ast.jinja.jinjaExpression.JinjaSimpleExpression;
//import ast.jinja.jinjaCallExpr.JinjaCallExpression;
//
//public class JinjaExpressionVisitor extends HtmlParserBaseVisitor<Object> {
//
//    @Override
//    public Object visitJinjaBinaryExpr(HtmlParser.JinjaBinaryExprContext ctx) {
//        JinjaBinaryExpression jinjaBinaryExpression = new JinjaBinaryExpression(ctx.getStart().getLine());
//
//        // استخدام visit() سيقوم بتشغيل JinjaCallExpressionVisitor تلقائياً
//        Object left = visit(ctx.j_call_expr(0));
//        Object right = visit(ctx.j_call_expr(1));
//
//        // التحقق الآمن من النوع قبل الـ Casting
//        if (left instanceof JinjaCallExpression) {
//            jinjaBinaryExpression.setLeft((JinjaCallExpression) left);
//        }
//        if (right instanceof JinjaCallExpression) {
//            jinjaBinaryExpression.setRight((JinjaCallExpression) right);
//        }
//
//        jinjaBinaryExpression.setOperator(ctx.getChild(1).getText());
//        return jinjaBinaryExpression;
//    }
//
//    @Override
//    public Object visitJinjaSimpleExpr(HtmlParser.JinjaSimpleExprContext ctx) {
//        // سطر تتبع (Debug) للتأكد من تسلسل الزيارة
//        System.out.println("DEBUG: Passing through JinjaSimpleExpr -> " + ctx.getText());
//
//        // هنا السلسلة: Expression -> CallExpression -> Atom -> SymbolTable Check
//        Object callExpr = visit(ctx.j_call_expr());
//
//        JinjaSimpleExpression jinjaSimpleExpression = new JinjaSimpleExpression(ctx.getStart().getLine());
//
//        if (callExpr instanceof JinjaCallExpression) {
//            jinjaSimpleExpression.setExpr((JinjaCallExpression) callExpr);
//        }
//
//        return jinjaSimpleExpression;
//    }
//}
//    @Override
//    public Object visitJinjaSimpleExpr(HtmlParser.JinjaSimpleExprContext ctx) {
//        System.out.println("DEBUG: Passing through JinjaSimpleExpr -> " + ctx.getText());
//
//        // إصلاح الانقطاع: استدعاء callVisitor بدلاً من visit() الافتراضية
//        Object callExpr = callVisitor.visit(ctx.j_call_expr());
//
//        if (callExpr == null) {
//            System.err.println("CRITICAL DEBUG: callVisitor returned null for " + ctx.getText());
//        }
//
//        JinjaSimpleExpression jinjaSimpleExpression = new JinjaSimpleExpression(ctx.getStart().getLine());
//
//        if (callExpr instanceof JinjaCallExpression) {
//            jinjaSimpleExpression.setExpr((JinjaCallExpression) callExpr);
//        }
//
//        return jinjaSimpleExpression;
//    }


//package visitor.jinja;
//
//import antlr.html.HtmlParser;
//import antlr.html.HtmlParserBaseVisitor;
//import ast.jinja.jinjaExpression.JinjaBinaryExpression;
//import ast.jinja.jinjaExpression.JinjaSimpleExpression;
//import ast.jinja.jinjaCallExpr.JinjaCallExpression;
//import symbolTable.SymbolEntry;
//import symbolTable.SymbolTableManager;
//
//public class JinjaExpressionVisitor extends HtmlParserBaseVisitor<Object> {
//
//    // تعريف الـ Visitor التالي في السلسلة كمتغير ثابت لتجنب التكرار
//    private final JinjaCallExpressionVisitor callVisitor = new JinjaCallExpressionVisitor();
//
//    @Override
//    public Object visitJinjaBinaryExpr(HtmlParser.JinjaBinaryExprContext ctx) {
//        // 1. جلب أسماء المتغيرات
//        String leftVar = ctx.j_call_expr(0).getText();
//        String rightVar = ctx.j_call_expr(1).getText();
//        String op = ctx.getChild(1).getText(); // سيعيد علامة < أو >
//
//        // 2. البحث عن بياناتهم في جدول الرموز
//        SymbolEntry leftEntry = SymbolTableManager.INSTANCE.lookup(leftVar, "jinja");
//        SymbolEntry rightEntry = SymbolTableManager.INSTANCE.lookup(rightVar, "jinja");
//
//        if (leftEntry != null && rightEntry != null) {
//            String leftType = (String) leftEntry.getAttribute("Type");
//            String rightType = (String) rightEntry.getAttribute("Type");
//
//            // 3. فحص تطابق الأنواع للمقارنة
//            if (!leftType.equals(rightType)) {
//                System.err.println("Semantic Error: Type Mismatch at line " + ctx.getStart().getLine() +
//                        ". Cannot compare '" + leftType + "' with '" + rightType + "' using operator '" + op + "'.");
//            }
//        }
//
//        return super.visitJinjaBinaryExpr(ctx);
//    }
//
//
//@Override
//public Object visitJinjaSimpleExpr(HtmlParser.JinjaSimpleExprContext ctx) {
//    // 1. استخراج اسم المتغير (مثلاً unknown_variable)
//    String varName = ctx.j_call_expr().getText();
//
//    // 2. البحث عنه باستخدام المانجر (اللي صار مربوط ببايثون)
//    SymbolEntry entry = SymbolTableManager.INSTANCE.lookup(varName, "jinja");
//
//    if (entry == null) {
//        // إذا مش موجود، اطبع الخطأ الأحمر اللي بنحبه
//        System.err.println("Semantic Error: Undefined variable '" + varName + "' in Jinja template at line " + ctx.getStart().getLine());
//    } else {
//        // إذا موجود، سجله في جدول الجينجا عشان يظهر بالطباعة النهائية
//        SymbolTableManager.INSTANCE.getJinjaTable().setAttribute(varName, "Type", entry.getAttribute("Type"));
//        SymbolTableManager.INSTANCE.getJinjaTable().setAttribute(varName, "Source", "From Python Context");
//    }
//
//    return super.visitJinjaSimpleExpr(ctx);
//}
//}

package visitor.jinja;

import antlr.html.HtmlParser;
import antlr.html.HtmlParserBaseVisitor;
import ast.jinja.jinjaExpression.JinjaArithmeticExpression;
import symbolTable.SymbolEntry;
import symbolTable.SymbolTableManager;
import visitor.python.AtomExpressionVisitor;

public class JinjaExpressionVisitor extends HtmlParserBaseVisitor<Object> {


    private final JinjaCallExpressionVisitor callVisitor = new JinjaCallExpressionVisitor();


@Override
public Object visitJinjaSimpleExpr(HtmlParser.JinjaSimpleExprContext ctx) {
    String fullText = ctx.j_call_expr().getText().trim();
    if (fullText.contains("(")) {
        return buildSimpleExpr(ctx);
    }
    if (fullText.matches("[0-9]+.*")       ||
            fullText.startsWith("\"")          ||
            fullText.startsWith("'")           ||
            fullText.equalsIgnoreCase("true")  ||
            fullText.equalsIgnoreCase("false") ||
            fullText.equalsIgnoreCase("none")  ||
            fullText.startsWith("[")) {
        return buildSimpleExpr(ctx);
    }

    String baseVar = fullText;
    if (baseVar.contains(".")) baseVar = baseVar.split("\\.")[0].trim();
    if (baseVar.contains("(")) baseVar = baseVar.split("\\(")[0].trim();
    baseVar = baseVar.replaceAll("[^a-zA-Z0-9_]", "");

    symbolTable.SymbolTable jinjaTable  = SymbolTableManager.INSTANCE.getJinjaTable();
    symbolTable.SymbolTable pythonTable = visitor.python.AtomExpressionVisitor.pythonScopeAtRender;

    if (jinjaTable != null && jinjaTable.lookup(baseVar) != null) {
        jinjaTable.setAttribute(baseVar, "Type", "Dynamic");
    } else if (pythonTable != null && pythonTable.lookup(baseVar) != null) {
        System.err.println("Semantic Error: Missing flask variable '" + fullText +
                "' at line " + ctx.getStart().getLine() +
                " -> variable is defined in Python but not passed to render_template()");
    } else if (jinjaTable != null && jinjaTable.isOutOfScope(baseVar)) {
        System.err.println("Semantic Error: Scope error , variable '" + fullText +
                "' is defined inside a block and cannot be accessed outside at line " +
                ctx.getStart().getLine());
    } else {
        System.err.println("Semantic Error: Undefined variable '" + fullText +
                "' in Jinja template at line " + ctx.getStart().getLine());
    }


    return buildSimpleExpr(ctx);
}


    private ast.jinja.jinjaExpression.JinjaSimpleExpression buildSimpleExpr(
            HtmlParser.JinjaSimpleExprContext ctx) {
        ast.jinja.jinjaExpression.JinjaSimpleExpression simpleExpr =
                new ast.jinja.jinjaExpression.JinjaSimpleExpression(ctx.getStart().getLine());

        Object callResult = callVisitor.visit(ctx.j_call_expr());


        if (callResult instanceof ast.jinja.jinjaExpression.JinjaExpression) {
            simpleExpr.setExpr((ast.jinja.jinjaExpression.JinjaExpression) callResult);
        }
        return simpleExpr;
    }
    /**
     * ميثود مساعدة وموحدة لفحص أنواع الجينجا وفصل أخطاء الـ Type Error عن الـ Mismatch
     */
    private void validateTypes(String leftType, String rightType, String operator, int line) {
        // إذا كان المتغير غير معرف أصلاً، نتخطى الفحص لأن ميثود SimpleExpr ستصطاد خطأ الـ Undefined
        if (leftType == null || rightType == null) return;

        // --- القاعدة الأولى: Type Mismatch ---
        // أي عملية أو مقارنة بين نوعين مختلفين فوراً تعطي عدم تطابق
        if (!leftType.equals(rightType)) {
            System.err.println("Semantic Error: Type mismatch at line " + line +
                    " -> Cannot compare '" + leftType + "' with '" + rightType + "' using operator '" + operator + "'.");
            return;
        }

        // --- القاعدة الثانية: Type Error ---
        // الأنواع متطابقة تماماً ولكن العملية بحد ذاتها ممنوعة هندسياً في الجينجا
        if (leftType.equals("Boolean")) {
            // يمنع استخدام عوامل المقارنة الحجمية أو العمليات الحسابية على البوليان
            if (operator.equals(">") || operator.equals("<") || operator.equals(">=") || operator.equals("<=") ||
                    operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")) {
                System.err.println("Semantic Error: Type error at line " + line +
                        " -> Operator '" + operator + "' is not supported for type 'Boolean'");
                return;
            }
        }

        if (leftType.equals("String")) {
            // يمنع الطرح والقسمة والضرب وعوامل المقارنة الحجمية للنصوص في الجينجا
            if (operator.equals("-") || operator.equals("*") || operator.equals("/") ||
                    operator.equals(">") || operator.equals("<") || operator.equals(">=") || operator.equals("<=")) {
                System.err.println("Semantic Error: Type error at line " + line +
                        " -> Operator '" + operator + "' is not supported for type 'String'");
                return;
            }
        }
    }

private void checkVariable(String varName, int line) {
    // تخطى literals
    if (varName.matches("[0-9]+.*") || varName.startsWith("\"") ||
            varName.startsWith("'") || varName.equalsIgnoreCase("true") ||
            varName.equalsIgnoreCase("false") || varName.equalsIgnoreCase("none")) {
        return;
    }

    symbolTable.SymbolTable jinjaTable  = SymbolTableManager.INSTANCE.getJinjaTable();
    symbolTable.SymbolTable pythonTable = AtomExpressionVisitor.pythonScopeAtRender;

    if (jinjaTable != null && jinjaTable.lookup(varName) != null) {
        //  معرّف بـ set أو ممرر عبر render_template
    } else if (pythonTable != null && pythonTable.lookup(varName) != null) {
        //  موجود في Python لكن ما انمرر
        System.err.println("Semantic Error: Missing flask variable '" + varName +
                "' at line " + line +
                " -> variable is defined in Python but not passed to render_template()");
    } else {
        //  غير موجود في أي مكان
        System.err.println("Semantic Error: Undefined variable '" + varName +
                "' in Jinja template at line " + line);
    }
}
    private String inferLiteralType(String text) {
        if (text.startsWith("\"") || text.startsWith("'")) return "String";
        if (text.matches("[0-9]+"))                         return "Integer";
        if (text.matches("[0-9]*\\.[0-9]+"))                return "Float";
        if (text.equalsIgnoreCase("true") ||
                text.equalsIgnoreCase("false"))                 return "Boolean";
        return null;
    }
    private Object visitArithmetic(String left, String right, String op, int line) {
        JinjaArithmeticExpression expr = new JinjaArithmeticExpression(line);
        expr.setLeft(left);
        expr.setOperator(op);
        expr.setRight(right);


        checkVariable(left, line);
        checkVariable(right, line);

        // فحص الأنواع
        SymbolEntry leftEntry  = SymbolTableManager.INSTANCE.lookup(left,  "jinja");
        SymbolEntry rightEntry = SymbolTableManager.INSTANCE.lookup(right, "jinja");
        String leftType  = (leftEntry  != null) ? (String) leftEntry.getAttribute("Type") : inferLiteralType(left);
        String rightType = (rightEntry != null) ? (String) rightEntry.getAttribute("Type") : inferLiteralType(right);

        if (leftType != null && rightType != null) {
            if (!leftType.equals(rightType)) {
                System.err.println("Semantic Error: Type mismatch at line " + line +
                        " -> Cannot perform '" + op + "' on '" + leftType + "' and '" + rightType + "'");
            } else if (leftType.equals("String") || leftType.equals("Boolean")) {
                System.err.println("Semantic Error: Type error at line " + line +
                        " -> Operator '" + op + "' is not supported for type '" + leftType + "'");
            }
        }
        return expr;
    }

@Override
public Object visitJinjaBinaryExpr(HtmlParser.JinjaBinaryExprContext ctx) {
    String leftVar  = ctx.j_call_expr(0).getText();
    String rightVar = ctx.j_call_expr(1).getText();
    String op = ctx.getChild(1).getText();

    checkVariable(leftVar,  ctx.getStart().getLine());
    checkVariable(rightVar, ctx.getStart().getLine());

    SymbolEntry leftEntry  = SymbolTableManager.INSTANCE.lookup(leftVar,  "jinja");
    SymbolEntry rightEntry = SymbolTableManager.INSTANCE.lookup(rightVar, "jinja");
    String leftType  = (leftEntry  != null) ? (String) leftEntry.getAttribute("Type") : null;
    String rightType = (rightEntry != null) ? (String) rightEntry.getAttribute("Type") : null;

    validateTypes(leftType, rightType, op, ctx.getStart().getLine());


    ast.jinja.jinjaExpression.JinjaBinaryExpression binaryExpr =
            new ast.jinja.jinjaExpression.JinjaBinaryExpression(ctx.getStart().getLine());
    return binaryExpr;
}

    @Override
    public Object visitJinjaAddition(HtmlParser.JinjaAdditionContext ctx) {
        return visitArithmetic(
                ctx.j_call_expr(0).getText(),
                ctx.j_call_expr(1).getText(),
                "+", ctx.getStart().getLine()
        );
    }

    @Override
    public Object visitJinjaSubtraction(HtmlParser.JinjaSubtractionContext ctx) {
        return visitArithmetic(
                ctx.j_call_expr(0).getText(),
                ctx.j_call_expr(1).getText(),
                "-", ctx.getStart().getLine()
        );
    }

    @Override
    public Object visitJinjaMultiplication(HtmlParser.JinjaMultiplicationContext ctx) {
        return visitArithmetic(
                ctx.j_call_expr(0).getText(),
                ctx.j_call_expr(1).getText(),
                "*", ctx.getStart().getLine()
        );
    }

    @Override
    public Object visitJinjaDivision(HtmlParser.JinjaDivisionContext ctx) {
        String rightVar = ctx.j_call_expr(1).getText().trim();

        // ✅ Division by Zero check
        if (rightVar.equals("0")) {
            System.err.println("Semantic Error: Division by zero at line " +
                    ctx.getStart().getLine());
        } else {
            // Check if it's a variable with value 0
            symbolTable.SymbolEntry entry = SymbolTableManager.INSTANCE.lookup(rightVar, "jinja");
            if (entry != null && "0".equals(String.valueOf(entry.getAttribute("Value")))) {
                System.err.println("Semantic Error: Division by zero at line " +
                        ctx.getStart().getLine());
            }
        }

        return visitArithmetic(
                ctx.j_call_expr(0).getText(),
                ctx.j_call_expr(1).getText(),
                "/", ctx.getStart().getLine()
        );
    }
}

