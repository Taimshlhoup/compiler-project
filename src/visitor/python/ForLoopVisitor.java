//package visitor.python;
//
//import antlr.python.PythonParser;
//import antlr.python.PythonParserBaseVisitor;
//import ast.atom.Atom;
//import ast.compundStmt.ForLoop;
//import ast.compundStmt.PythonExpression;
//import ast.condition.Condition;
//
//public class ForLoopVisitor extends PythonParserBaseVisitor<ForLoop> {
//    AtomVisitor atomVisitor = new AtomVisitor();
//    PythonExpressionVisitor pythonExpressionVisitor = new PythonExpressionVisitor();
//
//    @Override
//    public ForLoop visitSimpleForLoop(PythonParser.SimpleForLoopContext ctx) {
//        ForLoop forLoop = new ForLoop(ctx.getStart().getLine());
//        Atom atom = atomVisitor.visit(ctx.atom());
//        PythonExpression pythonExpression = pythonExpressionVisitor.visit(ctx.python_expr());
//        forLoop.setVar(atom);
//        forLoop.setIter(pythonExpression);
//        return forLoop;
//    }
//
//    @Override
//    public ForLoop visitComplexForLoop(PythonParser.ComplexForLoopContext ctx) {
//        ForLoop forLoop = new ForLoop(ctx.getStart().getLine());
//        Atom atom = atomVisitor.visit(ctx.atom(0));
//        PythonExpression pythonExpression = pythonExpressionVisitor.visit(ctx.python_expr());
//        forLoop.setVar(atom);
//        forLoop.setIter(pythonExpression);
//        if (ctx.condition() != null) {
//            Condition condition = new ConditionVisitor().visit(ctx.condition());
//            forLoop.setCondition(condition);
//        }
//        return forLoop;
//    }
//
//
//}

//package visitor.python;
//
//import antlr.python.PythonParser;
//import antlr.python.PythonParserBaseVisitor;
//import ast.compundStmt.ForLoop;
//import symbolTable.SymbolTable;
//import symbolTable.SymbolTableManager;
//
//public class ForLoopVisitor extends PythonParserBaseVisitor<ForLoop> {
//    // إذا كان لديك تعريفات لـ Visitors أخرى هنا (مثل atomVisitor) اتركها كما هي
//
//    @Override
//    public ForLoop visitSimpleForLoop(PythonParser.SimpleForLoopContext ctx) {
//        // 🔍 جملة فحص للتأكد من دخول الدالة
//        System.out.println(">>> [DEBUG] Entered visitSimpleForLoop");
//
//        // ✨ الترتيب الحرج: تسجيل العداد في أعلى الدالة أولاً وقبل كل شيء
//        if (ctx.atom() != null) {
//            String varName = ctx.atom().getText().trim();
//            System.out.println(">>> [DEBUG] Simple For Loop Variable Name: " + varName);
//
//            SymbolTable currentSb = SymbolTableManager.INSTANCE.getPythonTable();
//            if (!currentSb.existsInCurrentScope(varName)) {
//                currentSb.insert(varName);
//            }
//            currentSb.setAttribute(varName, "Type", "Integer");
//        }
//
//        // بناء الـ AST الخاص بنسختك القديمة (ضع أكوادك القديمة هنا)
//        ForLoop forLoop = new ForLoop(ctx.getStart().getLine());
//        // مثال: if (ctx.atom() != null) forLoop.setVar(...);
//
//        return forLoop;
//    }
//
//    @Override
//    public ForLoop visitComplexForLoop(PythonParser.ComplexForLoopContext ctx) {
//        // 🔍 جملة فحص للتأكد من دخول الدالة
//        System.out.println(">>> [DEBUG] Entered visitComplexForLoop");
//
//        // ✨ الترتيب الحرج: تسجيل العداد في أعلى الدالة أولاً وقبل كل شيء
//        // ملاحظة: تأكد هل العداد في الحلقة المعقدة هو atom(0) في الغرامر لديك؟
//        if (ctx.atom(0) != null) {
//            String varName = ctx.atom(0).getText().trim();
//            System.out.println(">>> [DEBUG] Complex For Loop Variable Name: " + varName);
//
//            SymbolTable currentSb = SymbolTableManager.INSTANCE.getPythonTable();
//            if (!currentSb.existsInCurrentScope(varName)) {
//                currentSb.insert(varName);
//            }
//            currentSb.setAttribute(varName, "Type", "Integer");
//        }
//
//        // بناء الـ AST الخاص بنسختك القديمة (ضع أكوادك القديمة هنا)
//        ForLoop forLoop = new ForLoop(ctx.getStart().getLine());
//
//        return forLoop;
//    }
//}

package visitor.python;

import antlr.python.PythonParser;
import antlr.python.PythonParserBaseVisitor;
import ast.compundStmt.ForLoop;
import symbolTable.SymbolTable;
import symbolTable.SymbolTableManager;

public class ForLoopVisitor extends PythonParserBaseVisitor<ForLoop> {

    @Override
    public ForLoop visitSimpleForLoop(PythonParser.SimpleForLoopContext ctx) {

     //   System.out.println(">>> [DEBUG] Entered visitSimpleForLoop");

        if (ctx.atom() != null) {
            String varName = ctx.atom().getText().trim();
           // System.out.println(">>> [DEBUG] Simple For Loop Variable Name: " + varName);

            SymbolTable currentSb = SymbolTableManager.INSTANCE.getPythonTable();
            if (!currentSb.existsInCurrentScope(varName)) {
                currentSb.insert(varName);
            }
            currentSb.setAttribute(varName, "Type", "Integer");
        }

        ForLoop forLoop = new ForLoop(ctx.getStart().getLine());

        // 🧠 فحص ذكي: هل التعبير هو range(0)؟
        boolean shouldVisitBody = true;
        if (ctx.python_expr() != null) {
            // نقرأ النص ونحذف منه الفراغات لضمان دقة المقارنة
            String exprText = ctx.python_expr().getText().replaceAll("\\s+", "");

            if (exprText.equals("range(0)")) {
               // System.out.println(">>> [DEBUG] Loop range is 0. Skipping body traversal!");
                shouldVisitBody = false; // الكومبايلر لن يدخل للحلقة
            }
        }

        // 🔥 لن يدخل الكومبايلر لزيارة المحتوى إلا إذا كانت الـ range أكبر من 0
        if (ctx.statement() != null && shouldVisitBody) {
            new StatementVisitor().visit(ctx.statement());
        }

        return forLoop;
    }

    @Override
    public ForLoop visitComplexForLoop(PythonParser.ComplexForLoopContext ctx) {
        //System.out.println(">>> [DEBUG] Entered visitComplexForLoop");

        // 💡 تعديل هام: العداد هو atom(1) حسب ترتيب الغرامر الخاص بك
        if (ctx.atom(1) != null) {
            String varName = ctx.atom(1).getText().trim();
            //System.out.println(">>> [DEBUG] Complex For Loop Variable Name: " + varName);

            SymbolTable currentSb = SymbolTableManager.INSTANCE.getPythonTable();
            if (!currentSb.existsInCurrentScope(varName)) {
                currentSb.insert(varName);
            }
            currentSb.setAttribute(varName, "Type", "Integer");
        }

        ForLoop forLoop = new ForLoop(ctx.getStart().getLine());


        if (ctx.condition() != null) {
            new ConditionVisitor().visit(ctx.condition());
        }

        return forLoop;
    }
}
