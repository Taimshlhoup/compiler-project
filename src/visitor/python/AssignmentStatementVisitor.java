//package visitor.python;
//
//import antlr.python.PythonParser;
//import antlr.python.PythonParserBaseVisitor;
//import ast.TemplateLiteral;
//import ast.arithmeticExpr.ArithmeticExpression;
//import ast.assignStmt.*;
//import ast.compundStmt.PythonExpression;
//import ast.condition.Condition;
//import symbolTable.SymbolTable;
//import symbolTable.SymbolTableManager;
//
//public class AssignmentStatementVisitor extends PythonParserBaseVisitor<AssignmentStatement> {
//    private final PythonExpressionVisitor pythonExpressionVisitor = new PythonExpressionVisitor();
//    private final SymbolTable sb = SymbolTableManager.INSTANCE.getPythonTable();
//
//    @Override
//    public AssignmentStatement visitComparisonAssignStmt(PythonParser.ComparisonAssignStmtContext ctx) {
//        ComparisonAssignmentStmt comparisonAssignmentStmt = new ComparisonAssignmentStmt(ctx.getStart().getLine());
//        PythonExpression pythonExpression = pythonExpressionVisitor.visit(ctx.python_expr());
//
//        String symbolEntryName = pythonExpression.symbolTablePrint();
//
//        // التعديل: إدخال المتغير قبل زيارة الطرف الأيمن لتجنب Undefined Variable Error
//        if (!sb.existsInCurrentScope(symbolEntryName)) {
//            sb.insert(symbolEntryName);
//            sb.setAttribute(symbolEntryName, "Type", "Pending"); // حالة مؤقتة
//        }
//
//        Condition condition = new ConditionVisitor().visit(ctx.condition());
//        String newType = condition.node_name;
//
//        // فحص توافق الأنواع (Type Mismatch)
//        String oldType = (String) sb.getAttribute(symbolEntryName, "Type");
//        if (oldType != null && !oldType.equals("Pending") && !oldType.equals(newType)) {
//            System.err.println("Semantic Error: Type Mismatch at line " + ctx.getStart().getLine() +
//                    ". Cannot assign " + newType + " to " + oldType);
//        }
//
//        sb.setAttribute(symbolEntryName, "Value", condition.symbolTablePrint());
//        sb.setAttribute(symbolEntryName, "Type", newType);
//
//        comparisonAssignmentStmt.setVar(pythonExpression);
//        comparisonAssignmentStmt.setValue(condition);
//        return comparisonAssignmentStmt;
//    }
//
//    @Override
//    public AssignmentStatement visitTemplateLiteralAssignStmt(PythonParser.TemplateLiteralAssignStmtContext ctx) {
//        TemplateLiteralAssignmentStatement templateLiteralAssignmentStatement
//                = new TemplateLiteralAssignmentStatement(ctx.getStart().getLine());
//
//        PythonExpression pythonExpression = pythonExpressionVisitor.visit(ctx.python_expr());
//        String symbolEntryName = pythonExpression.symbolTablePrint();
//
//        // إدخال المتغير مبكراً
//        if (!sb.existsInCurrentScope(symbolEntryName)) {
//            sb.insert(symbolEntryName);
//            sb.setAttribute(symbolEntryName, "Type", "Pending");
//        }
//
//        TemplateLiteral templateLiteral = new TemplateLiteralVisitor().visit(ctx.template_literal());
//        String newType = "String";
//
//        String oldType = (String) sb.getAttribute(symbolEntryName, "Type");
//        if (oldType != null && !oldType.equals("Pending") && !oldType.equals(newType)) {
//            System.err.println("Semantic Error: Type Mismatch at line " + ctx.getStart().getLine() +
//                    ". Cannot assign " + newType + " to " + oldType);
//        }
//
//        sb.setAttribute(symbolEntryName, "Value", "Multiline String");
//        sb.setAttribute(symbolEntryName, "Type", newType);
//
//        templateLiteralAssignmentStatement.setVar(pythonExpression);
//        templateLiteralAssignmentStatement.setTemplateLiteral(templateLiteral);
//        return templateLiteralAssignmentStatement;
//    }
//
//    @Override
//    public AssignmentStatement visitPythonExpressionAssignStmt(PythonParser.PythonExpressionAssignStmtContext ctx) {
//        PythonExpressionAssignStatement pythonExpressionAssignStatement
//                = new PythonExpressionAssignStatement(ctx.getStart().getLine());
//
//        PythonExpression var = pythonExpressionVisitor.visit(ctx.python_expr(0));
//        String symbolEntryName = var.symbolTablePrint();
//
//        // إدخال المتغير مبكراً
//        if (!sb.existsInCurrentScope(symbolEntryName)) {
//            sb.insert(symbolEntryName);
//            sb.setAttribute(symbolEntryName, "Type", "Pending");
//        }
//
//        PythonExpression value = pythonExpressionVisitor.visit(ctx.python_expr(1));
//        String newType = value.node_name;
//
//        String oldType = (String) sb.getAttribute(symbolEntryName, "Type");
//        if (oldType != null && !oldType.equals("Pending") && !oldType.equals(newType)) {
//            System.err.println("Semantic Error: Type Mismatch at line " + ctx.getStart().getLine() +
//                    ". Cannot assign " + newType + " to " + oldType);
//        }
//
//        sb.setAttribute(symbolEntryName, "Value", value.symbolTablePrint());
//        sb.setAttribute(symbolEntryName, "Type", newType);
//
//        pythonExpressionAssignStatement.setVar(var);
//        pythonExpressionAssignStatement.setValue(value);
//        return pythonExpressionAssignStatement;
//    }
//
//    @Override
//    public AssignmentStatement visitArithmeticAssignStmt(PythonParser.ArithmeticAssignStmtContext ctx) {
//        ArithmeticAssignStatement arithmeticAssignStatement = new ArithmeticAssignStatement(ctx.getStart().getLine());
//        PythonExpression pythonExpression = pythonExpressionVisitor.visit(ctx.python_expr());
//        String symbolEntryName = pythonExpression.symbolTablePrint();
//
//        // إدخال المتغير مبكراً قبل فحص المعادلة الحسابية
//        if (!sb.existsInCurrentScope(symbolEntryName)) {
//            sb.insert(symbolEntryName);
//            sb.setAttribute(symbolEntryName, "Type", "Pending");
//        }
//
//        ArithmeticExpression arithmeticExpression = new ArithmeticExpressionVisitor().visit(ctx.arithmetic_expr());
//        String newType = arithmeticExpression.node_name;
//
//        String oldType = (String) sb.getAttribute(symbolEntryName, "Type");
//        if (oldType != null && !oldType.equals("Pending") && !oldType.equals(newType)) {
//            System.err.println("Semantic Error: Type Mismatch at line " + ctx.getStart().getLine() +
//                    ". Cannot assign " + newType + " to " + oldType);
//        }
//
//        sb.setAttribute(symbolEntryName, "Value", arithmeticExpression.symbolTablePrint());
//        sb.setAttribute(symbolEntryName, "Type", newType);
//
//        arithmeticAssignStatement.setVar(pythonExpression);
//        arithmeticAssignStatement.setValue(arithmeticExpression);
//        return arithmeticAssignStatement;
//    }
//}

//package visitor.python;
//
//import antlr.python.PythonParser;
//import antlr.python.PythonParserBaseVisitor;
//import ast.TemplateLiteral;
//import ast.arithmeticExpr.ArithmeticExpression;
//import ast.assignStmt.*;
//import ast.compundStmt.PythonExpression;
//import ast.condition.Condition;
//import symbolTable.SymbolTable;
//import symbolTable.SymbolTableManager;
//
//public class AssignmentStatementVisitor extends PythonParserBaseVisitor<AssignmentStatement> {
//    private final PythonExpressionVisitor pythonExpressionVisitor = new PythonExpressionVisitor();
//    private final SymbolTable sb = SymbolTableManager.INSTANCE.getPythonTable();
//
//
//    // دالة موحدة لمعالجة الإدخال وفحص النوع لمنع التكرار
//    private void validateAndInsert(String name, String newType, String value, int line) {
//        if (!sb.existsInCurrentScope(name)) {
//            sb.insert(name);
//            sb.setAttribute(name, "Type", newType);
//            sb.setAttribute(name, "Value", value);
//        } else {
//            String oldType = (String) sb.getAttribute(name, "Type");
//
//            // التحقق من توافق الأنواع (Type Mismatch)
//            if (oldType != null && !oldType.equals("Pending") && !oldType.equals(newType)) {
//                System.err.println("Semantic Error: Type Mismatch at line " + line +
//                        ". Cannot assign " + newType + " to " + oldType);
//            } else {
//                // تحديث النوع والقيمة
//                sb.setAttribute(name, "Type", newType);
//                sb.setAttribute(name, "Value", value);
//            }
//        }
//    }
//
//    @Override
//    public AssignmentStatement visitComparisonAssignStmt(PythonParser.ComparisonAssignStmtContext ctx) {
//        PythonExpression var = pythonExpressionVisitor.visit(ctx.python_expr());
//        Condition condition = new ConditionVisitor().visit(ctx.condition());
//
//        validateAndInsert(var.symbolTablePrint(), condition.node_name, condition.symbolTablePrint(), ctx.getStart().getLine());
//
//        ComparisonAssignmentStmt stmt = new ComparisonAssignmentStmt(ctx.getStart().getLine());
//        stmt.setVar(var);
//        stmt.setValue(condition);
//        return stmt;
//    }
//
////    @Override
////    public AssignmentStatement visitArithmeticAssignStmt(PythonParser.ArithmeticAssignStmtContext ctx) {
////        PythonExpression var = pythonExpressionVisitor.visit(ctx.python_expr());
////        ArithmeticExpression arithmeticExpr = new ArithmeticExpressionVisitor().visit(ctx.arithmetic_expr());
////
////        validateAndInsert(var.symbolTablePrint(), arithmeticExpr.node_name, arithmeticExpr.symbolTablePrint(), ctx.getStart().getLine());
////
////        ArithmeticAssignStatement stmt = new ArithmeticAssignStatement(ctx.getStart().getLine());
////        stmt.setVar(var);
////        stmt.setValue(arithmeticExpr);
////        return stmt;
////    }
//@Override
//public AssignmentStatement visitArithmeticAssignStmt(PythonParser.ArithmeticAssignStmtContext ctx) {
//    // 1. استخراج اسم المتغير نصياً أولاً دون زيارة الـ Visitor كاملاً لمنع الخطأ
//    String varName = ctx.python_expr().getText().trim();
//
//    // 2. إدخال المتغير في الجدول فوراً إذا لم يكن موجوداً (هذا يجعله "مُعرفاً")
//    if (!sb.existsInCurrentScope(varName)) {
//        sb.insert(varName);
//        sb.setAttribute(varName, "Type", "Pending");
//    }
//
//    // 3. الآن يمكننا زيارة الطرف الأيسر والأيمن بأمان
//    PythonExpression varNode = pythonExpressionVisitor.visit(ctx.python_expr());
//    ArithmeticExpression valueNode = new ArithmeticExpressionVisitor().visit(ctx.arithmetic_expr());
//
//    // 4. إكمال منطق فحص الأنواع (Type Check)
//    validateAndInsert(varName, valueNode.node_name, valueNode.symbolTablePrint(), ctx.getStart().getLine());
//
//    // بناء عقدة الـ AST
//    ArithmeticAssignStatement stmt = new ArithmeticAssignStatement(ctx.getStart().getLine());
//    stmt.setVar(varNode);
//    stmt.setValue(valueNode);
//    return stmt;
//}
//
////    @Override
////    public AssignmentStatement visitPythonExpressionAssignStmt(PythonParser.PythonExpressionAssignStmtContext ctx) {
////        PythonExpression var = pythonExpressionVisitor.visit(ctx.python_expr(0));
////        PythonExpression value = pythonExpressionVisitor.visit(ctx.python_expr(1));
////
////        validateAndInsert(var.symbolTablePrint(), value.node_name, value.symbolTablePrint(), ctx.getStart().getLine());
////
////        PythonExpressionAssignStatement stmt = new PythonExpressionAssignStatement(ctx.getStart().getLine());
////        stmt.setVar(var);
////        stmt.setValue(value);
////        return stmt;
////    }
//@Override
//public AssignmentStatement visitPythonExpressionAssignStmt(PythonParser.PythonExpressionAssignStmtContext ctx) {
//    // 1. استخراج اسم المتغير نصياً (بدون زيارة الـ Visitor لمنع رسالة Undefined)
//    String varName = ctx.python_expr(0).getText().trim();
//
//    // 2. زيارة الطرف الأيمن أولاً لجلب القيمة والنوع
//    PythonExpression valueNode = pythonExpressionVisitor.visit(ctx.python_expr(1));
//    String newType = valueNode.node_name;
//    String valueStr = valueNode.symbolTablePrint();
//
//    // 3. استخدام الدالة الموحدة للإدخال أو فحص النوع (هنا سيتم الإدخال لأول مرة بصمت)
//    validateAndInsert(varName, newType, valueStr, ctx.getStart().getLine());
//
//    // 4. الآن يمكن زيارة الطرف الأيسر لبناء الـ AST فقط
//    PythonExpression varNode = pythonExpressionVisitor.visit(ctx.python_expr(0));
//
//    PythonExpressionAssignStatement stmt = new PythonExpressionAssignStatement(ctx.getStart().getLine());
//    stmt.setVar(varNode);
//    stmt.setValue(valueNode);
//    return stmt;
//}
//
//    // ... يمكنك تطبيق نفس النمط على TemplateLiteralAssignStmt
//}

package visitor.python;

import antlr.python.PythonParser;
import antlr.python.PythonParserBaseVisitor;
import ast.TemplateLiteral;
import ast.arithmeticExpr.ArithmeticExpression;
import ast.assignStmt.*;
import ast.compundStmt.PythonExpression;
import ast.condition.Condition;
import symbolTable.SymbolTable;
import symbolTable.SymbolTableManager;

public class AssignmentStatementVisitor extends PythonParserBaseVisitor<AssignmentStatement> {
    private final PythonExpressionVisitor pythonExpressionVisitor = new PythonExpressionVisitor();
    private final SymbolTable sb = SymbolTableManager.INSTANCE.getPythonTable();


    // دالة موحدة لمعالجة الإدخال وفحص النوع لمنع التكرار
//    private void validateAndInsert(String name, String newType, String value, int line) {
//        if (!sb.existsInCurrentScope(name)) {
//            sb.insert(name);
//            // 🚀 تتبع تاريخي: تسجيل المتغير كمتغير تم تعريفه في الكود لمنع خلطه مع الأخطاء الأعمى
//            SymbolTableManager.INSTANCE.registerVariable(name);
//
//            sb.setAttribute(name, "Type", newType);
//            sb.setAttribute(name, "Value", value);
//        } else {
//            String oldType = (String) sb.getAttribute(name, "Type");
//
//            // التحقق من توافق الأنواع (Type Mismatch)
//            if (oldType != null && !oldType.equals("Pending") && !oldType.equals(newType)) {
//                System.err.println("Semantic Error: Type Mismatch at line " + line +
//                        ". Cannot assign " + newType + " to " + oldType);
//            } else {
//                // تحديث النوع والقيمة
//                sb.setAttribute(name, "Type", newType);
//                sb.setAttribute(name, "Value", value);
//            }
//        }
//    }

    @Override
    public AssignmentStatement visitComparisonAssignStmt(PythonParser.ComparisonAssignStmtContext ctx) {
        PythonExpression var = pythonExpressionVisitor.visit(ctx.python_expr());
        Condition condition = new ConditionVisitor().visit(ctx.condition());

        validateAndInsert(var.symbolTablePrint(), condition.node_name, condition.symbolTablePrint(), ctx.getStart().getLine());

        ComparisonAssignmentStmt stmt = new ComparisonAssignmentStmt(ctx.getStart().getLine());
        stmt.setVar(var);
        stmt.setValue(condition);
        return stmt;
    }

    //    @Override
//    public AssignmentStatement visitArithmeticAssignStmt(PythonParser.ArithmeticAssignStmtContext ctx) {
//        PythonExpression var = pythonExpressionVisitor.visit(ctx.python_expr());
//        ArithmeticExpression arithmeticExpr = new ArithmeticExpressionVisitor().visit(ctx.arithmetic_expr());
//
//        validateAndInsert(var.symbolTablePrint(), arithmeticExpr.node_name, arithmeticExpr.symbolTablePrint(), ctx.getStart().getLine());
//
//        ArithmeticAssignStatement stmt = new ArithmeticAssignStatement(ctx.getStart().getLine());
//        stmt.setVar(var);
//        stmt.setValue(arithmeticExpr);
//        return stmt;
//    }
//    @Override
//    public AssignmentStatement visitArithmeticAssignStmt(PythonParser.ArithmeticAssignStmtContext ctx) {
//        // 1. استخراج اسم المتغير نصياً أولاً دون زيارة الـ Visitor كاملاً لمنع الخطأ
//        String varName = ctx.python_expr().getText().trim();
//
//        // 2. إدخال المتغير في الجدول فوراً إذا لم يكن موجوداً (هذا يجعله "مُعرفاً")
//        if (!sb.existsInCurrentScope(varName)) {
//            sb.insert(varName);
//            // 🚀 تتبع تاريخي: تسجيل المتغير هنا أيضاً لأنه تعريف أولي
//            SymbolTableManager.INSTANCE.registerVariable(varName);
//
//            sb.setAttribute(varName, "Type", "Pending");
//        }
//
//        // 3. الآن يمكننا زيارة الطرف الأيسر والأيمن بأمان
//        PythonExpression varNode = pythonExpressionVisitor.visit(ctx.python_expr());
//        ArithmeticExpression valueNode = new ArithmeticExpressionVisitor().visit(ctx.arithmetic_expr());
//
//        // 4. إكمال منطق فحص الأنواع (Type Check)
//        validateAndInsert(varName, valueNode.node_name, valueNode.symbolTablePrint(), ctx.getStart().getLine());
//
//        // بناء عقدة الـ AST
//        ArithmeticAssignStatement stmt = new ArithmeticAssignStatement(ctx.getStart().getLine());
//        stmt.setVar(varNode);
//        stmt.setValue(valueNode);
//        return stmt;
//    }
    private void validateAndInsert(String name, String newType, String value, int line) {
        // تسجيل المتغير تاريخياً دائماً بمجرد رؤيته في أي مكان بالكود
        SymbolTableManager.INSTANCE.registerVariable(name);

        // 🚨 حماية: إذا كنا في فرع غير محقق، نكتفي بالتسجيل التاريخي ونخرج فوراً دون تعديل السكوب النشط
        if (SymbolTableManager.INSTANCE.isDeclarationOnlyMode()) {
            return;
        }

        if (!sb.existsInCurrentScope(name)) {
            sb.insert(name);
            sb.setAttribute(name, "Type", newType);
            sb.setAttribute(name, "Value", value);
        } else {
            String oldType = (String) sb.getAttribute(name, "Type");
            if (oldType != null && !oldType.equals("Pending") && !oldType.equals(newType)) {
                System.err.println("Semantic Error: Type Mismatch at line " + line + ". Cannot assign " + newType + " to " + oldType);
            } else {
                sb.setAttribute(name, "Type", newType);
                sb.setAttribute(name, "Value", value);
            }
        }
    }

    @Override
    public AssignmentStatement visitArithmeticAssignStmt(PythonParser.ArithmeticAssignStmtContext ctx) {
        String varName = ctx.python_expr().getText().trim();

        // 🚨 حماية الإدخال الأولي الفوري في حالة الـ declarationOnlyMode
        if (SymbolTableManager.INSTANCE.isDeclarationOnlyMode()) {
            SymbolTableManager.INSTANCE.registerVariable(varName);
        } else if (!sb.existsInCurrentScope(varName)) {
            sb.insert(varName);
            sb.setAttribute(varName, "Type", "Pending");
        }

        PythonExpression varNode = pythonExpressionVisitor.visit(ctx.python_expr());
        ArithmeticExpression valueNode = new ArithmeticExpressionVisitor().visit(ctx.arithmetic_expr());

        validateAndInsert(varName, valueNode.node_name, valueNode.symbolTablePrint(), ctx.getStart().getLine());

        ArithmeticAssignStatement stmt = new ArithmeticAssignStatement(ctx.getStart().getLine());
        stmt.setVar(varNode);
        stmt.setValue(valueNode);
        return stmt;
    }

    //    @Override
//    public AssignmentStatement visitPythonExpressionAssignStmt(PythonParser.PythonExpressionAssignStmtContext ctx) {
//        PythonExpression var = pythonExpressionVisitor.visit(ctx.python_expr(0));
//        PythonExpression value = pythonExpressionVisitor.visit(ctx.python_expr(1));
//
//        validateAndInsert(var.symbolTablePrint(), value.node_name, value.symbolTablePrint(), ctx.getStart().getLine());
//
//        PythonExpressionAssignStatement stmt = new PythonExpressionAssignStatement(ctx.getStart().getLine());
//        stmt.setVar(var);
//        stmt.setValue(value);
//        return stmt;
//    }
    @Override
    public AssignmentStatement visitPythonExpressionAssignStmt(PythonParser.PythonExpressionAssignStmtContext ctx) {
        // 1. استخراج اسم المتغير نصياً (بدون زيارة الـ Visitor لمنع رسالة Undefined)
        String varName = ctx.python_expr(0).getText().trim();

        // 2. زيارة الطرف الأيمن أولاً لجلب القيمة والنوع
        PythonExpression valueNode = pythonExpressionVisitor.visit(ctx.python_expr(1));
        String newType = valueNode.node_name;
        String valueStr = valueNode.symbolTablePrint();

        // 3. استخدام الدالة الموحدة للإدخال أو فحص النوع (هنا سيتم الإدخال لأول مرة بصمت)
        validateAndInsert(varName, newType, valueStr, ctx.getStart().getLine());

        // 4. الآن يمكن زيارة الطرف الأيسر لبناء الـ AST فقط
        PythonExpression varNode = pythonExpressionVisitor.visit(ctx.python_expr(0));

        PythonExpressionAssignStatement stmt = new PythonExpressionAssignStatement(ctx.getStart().getLine());
        stmt.setVar(varNode);
        stmt.setValue(valueNode);
        return stmt;
    }

    // ... يمكنك تطبيق نفس النمط على TemplateLiteralAssignStmt
}
