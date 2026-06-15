//package visitor.python;
//
//import antlr.python.PythonParser;
//import antlr.python.PythonParserBaseVisitor;
//import ast.arithmeticExpr.ArithmeticExpression;
//import ast.arithmeticExpr.Operator;
//import ast.compundStmt.PythonExpression;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ArithmeticExpressionVisitor extends PythonParserBaseVisitor<ArithmeticExpression> {
//    private final PythonExpressionVisitor pythonExpressionVisitor = new PythonExpressionVisitor();
//
//    @Override
//    public ArithmeticExpression visitAddition(PythonParser.AdditionContext ctx) {
//        return evaluate(Operator.Addition, ctx.getStart().getLine(), ctx.python_expr());
//    }
//
//    @Override
//    public ArithmeticExpression visitSubtraction(PythonParser.SubtractionContext ctx) {
//        return evaluate(Operator.Subtraction, ctx.getStart().getLine(), ctx.python_expr());
//    }
//
//    @Override
//    public ArithmeticExpression visitDivision(PythonParser.DivisionContext ctx) {
//        return evaluate(Operator.Division, ctx.getStart().getLine(), ctx.python_expr());
//    }
//
//    @Override
//    public ArithmeticExpression visitMultiplication(PythonParser.MultiplicationContext ctx) {
//        return evaluate(Operator.Multiplication, ctx.getStart().getLine(), ctx.python_expr());
//
//    }
//
//    private ArithmeticExpression evaluate(Operator operator, int line,
//                                          List<PythonParser.Python_exprContext> pythonExprs) {
//        ArithmeticExpression arithmeticExpression =
//                new ArithmeticExpression("Addition", line);
//        arithmeticExpression.setOperator(operator);
//        PythonExpression left = pythonExpressionVisitor.visit(pythonExprs.getFirst());
//        List<PythonExpression> right = new ArrayList<>();
//        for (int i = 1; i < pythonExprs.size(); i++) {
//            PythonExpression pythonExpression = pythonExpressionVisitor.visit(pythonExprs.get(i));
//            right.add(pythonExpression);
//        }
//        arithmeticExpression.setLeft(left);
//        arithmeticExpression.setRight(right);
//        return arithmeticExpression;
//    }
//}
//package visitor.python;
//
//import antlr.python.PythonParser;
//import antlr.python.PythonParserBaseVisitor;
//import ast.arithmeticExpr.ArithmeticExpression;
//import ast.arithmeticExpr.Operator;
//import ast.compundStmt.PythonExpression;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ArithmeticExpressionVisitor extends PythonParserBaseVisitor<ArithmeticExpression> {
//    private final PythonExpressionVisitor pythonExpressionVisitor = new PythonExpressionVisitor();
//
//    @Override
//    public ArithmeticExpression visitAddition(PythonParser.AdditionContext ctx) {
//        return evaluate(Operator.Addition, ctx.getStart().getLine(), ctx.python_expr());
//    }
//
//    @Override
//    public ArithmeticExpression visitSubtraction(PythonParser.SubtractionContext ctx) {
//        return evaluate(Operator.Subtraction, ctx.getStart().getLine(), ctx.python_expr());
//    }
//
//    @Override
//    public ArithmeticExpression visitDivision(PythonParser.DivisionContext ctx) {
//        ArithmeticExpression expr = evaluate(Operator.Division, ctx.getStart().getLine(), ctx.python_expr());
//        expr.node_name = "Float";
//        return expr;
//    }
//
//    @Override
//    public ArithmeticExpression visitMultiplication(PythonParser.MultiplicationContext ctx) {
//        return evaluate(Operator.Multiplication, ctx.getStart().getLine(), ctx.python_expr());
//    }
//
//    private ArithmeticExpression evaluate(Operator operator, int line,
//                                          List<PythonParser.Python_exprContext> pythonExprs) {
//        ArithmeticExpression arithmeticExpression = new ArithmeticExpression("ArithmeticOperation", line);
//        arithmeticExpression.setOperator(operator);
//
//        PythonExpression left = pythonExpressionVisitor.visit(pythonExprs.getFirst());
//        arithmeticExpression.setLeft(left);
//
//        String resultType = left.node_name;
//        List<PythonExpression> right = new ArrayList<>();
//
//        for (int i = 1; i < pythonExprs.size(); i++) {
//            PythonExpression currentRight = pythonExpressionVisitor.visit(pythonExprs.get(i));
//            right.add(currentRight);
//
//            // --- فحص صحة العملية (Semantic Check) ---
//            checkTypeValidity(resultType, currentRight.node_name, operator, line);
//
//            // منطق ترقية الأنواع (Type Promotion)
//            if (currentRight.node_name != null && currentRight.node_name.equals("Float")) {
//                resultType = "Float";
//            } else if (resultType == null || resultType.equals("Integer")) {
//                if (currentRight.node_name != null) {
//                    resultType = currentRight.node_name;
//                }
//            }
//        }
//
//        arithmeticExpression.setRight(right);
//        arithmeticExpression.node_name = resultType;
//        return arithmeticExpression;
//    }
//
//    // دالة التحقق من صحة العملية بين الأنواع
//    private void checkTypeValidity(String leftT, String rightT, Operator op, int line) {
//        if (leftT == null || rightT == null) return;
//
//        // 1. منع جمع نص مع أي شيء ليس نصاً (مثل z = "hello" + 5)
//        if (op == Operator.Addition) {
//            if (leftT.equals("String") || rightT.equals("String")) {
//                if (!leftT.equals(rightT)) {
//                    System.err.println("Semantic Error: Can only concatenate str (not \"" +
//                            (leftT.equals("String") ? rightT : leftT) + "\") to str at line " + line);
//                }
//            }
//        }
//        // 2. منع أي عملية حسابية أخرى على النصوص (مثل s = "a" - "b")
//        else {
//            if (leftT.equals("String") || rightT.equals("String")) {
//                System.err.println("Semantic Error: Unsupported operand type(s) for " + op +
//                        ": '" + leftT + "' and '" + rightT + "' at line " + line);
//            }
//        }
//    }
//}
//package visitor.python;
//
//import antlr.python.PythonParser;
//import antlr.python.PythonParserBaseVisitor;
//import ast.arithmeticExpr.ArithmeticExpression;
//import ast.arithmeticExpr.Operator;
//import ast.compundStmt.PythonExpression;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ArithmeticExpressionVisitor extends PythonParserBaseVisitor<ArithmeticExpression> {
//    private final PythonExpressionVisitor pythonExpressionVisitor = new PythonExpressionVisitor();
//
//    @Override
//    public ArithmeticExpression visitAddition(PythonParser.AdditionContext ctx) {
//        return evaluate(Operator.Addition, ctx.getStart().getLine(), ctx.python_expr());
//    }
//
//    @Override
//    public ArithmeticExpression visitSubtraction(PythonParser.SubtractionContext ctx) {
//        return evaluate(Operator.Subtraction, ctx.getStart().getLine(), ctx.python_expr());
//    }
//
//    @Override
//    public ArithmeticExpression visitDivision(PythonParser.DivisionContext ctx) {
//        ArithmeticExpression expr = evaluate(Operator.Division, ctx.getStart().getLine(), ctx.python_expr());
//        expr.node_name = "Float";
//        return expr;
//    }
//
//    @Override
//    public ArithmeticExpression visitMultiplication(PythonParser.MultiplicationContext ctx) {
//        return evaluate(Operator.Multiplication, ctx.getStart().getLine(), ctx.python_expr());
//    }
//
//    private ArithmeticExpression evaluate(Operator operator, int line,
//                                          List<PythonParser.Python_exprContext> pythonExprs) {
//        ArithmeticExpression arithmeticExpression = new ArithmeticExpression("ArithmeticOperation", line);
//        arithmeticExpression.setOperator(operator);
//
//        PythonExpression left = pythonExpressionVisitor.visit(pythonExprs.getFirst());
//        arithmeticExpression.setLeft(left);
//
//        String resultType = left.node_name;
//        List<PythonExpression> right = new ArrayList<>();
//
//        for (int i = 1; i < pythonExprs.size(); i++) {
//            PythonExpression currentRight = pythonExpressionVisitor.visit(pythonExprs.get(i));
//            right.add(currentRight);
//
//            // --- فحص صحة العملية (Semantic Check) ---
//            checkTypeValidity(resultType, currentRight.node_name, operator, line);
//
//            // منطق ترقية الأنواع (Type Promotion)
//            if (currentRight.node_name != null && currentRight.node_name.equals("Float")) {
//                resultType = "Float";
//            } else if (resultType == null || resultType.equals("Integer")) {
//                if (currentRight.node_name != null) {
//                    resultType = currentRight.node_name;
//                }
//            }
//        }
//
//        arithmeticExpression.setRight(right);
//        arithmeticExpression.node_name = resultType;
//        return arithmeticExpression;
//    }
//
//    /**
//     * دالة التحقق من صحة العملية المفصلة بدقة هندسية وصارمة بناءً على طلبك
//     */
////    private void checkTypeValidity(String leftT, String rightT, Operator op, int line) {
////        if (leftT == null || rightT == null) return;
////
////        // --- القاعدة الأولى: Type Mismatch (مثل السطر 12 والسطر 20) ---
////        // أي عملية حسابية (جمع، طرح، ضرب، قسمة) بين نوعين مختلفين فوراً تعطي mismatch
////        if (!leftT.equals(rightT)) {
////            System.err.println("Semantic Error: Type mismatch at line " + line +
////                    " -> Cannot apply '" + op + "' between '" + leftT + "' and '" + rightT + "'");
////            return; // نخرج فوراً لأننا قفشنا الخطأ
////        }
////
////        // --- القاعدة الثانية: Type Error (مثل الأسطر 13، 16، 17) ---
////        // هنا الأنواع متطابقة تماماً (مثلاً String مع String) ولكن العملية نفسها ممنوعة
////        if (leftT.equals("String")) {
////            // الطرح والقسمة والضرب ممنوعين تماماً على النصوص المتطابقة
////            if (op == Operator.Subtraction || op == Operator.Division || op == Operator.Multiplication) {
////                System.err.println("Semantic Error: Type error at line " + line +
////                        " -> Operator '" + op + "' is not supported for type 'String'");
////            }
////        }
////
////        if (leftT.equals("Boolean")) {
////            // جميع العمليات الحسابية ممنوعة على القيم المنطقية حتى لو كانت متطابقة
////            System.err.println("Semantic Error: Type error at line " + line +
////                    " -> Operator '" + op + "' is not supported for type 'Boolean'");
////        }
////    }
//    /**
//     * دالة التحقق من صحة العملية - النسخة الاحترافية لمنع تكرار أخطاء المتغيرات غير المعرفة
//     */
////    private void checkTypeValidity(String leftT, String rightT, Operator op, int line) {
////        // 1. إذا كان أحد الطرفين فارغاً، أو معرّفاً كـ "Name" (مما يعني أنه متغير غير معرف اصطاده كلاس آخر)
////        // نتخطى الفحص فوراً لمنع طباعة mismatch غير دقيق
////        if (leftT == null || rightT == null || leftT.equals("Name") || rightT.equals("Name")) {
////            return;
////        }
////
////        // --- القاعدة الأولى: Type Mismatch (مثل السطر 12 والسطر 20) ---
////        // لن يدخل هنا إلا إذا كانت الأنواع معروفة وحقيقية (مثل String, Integer, Float) ولكنها مختلفة
////        if (!leftT.equals(rightT)) {
////            System.err.println("Semantic Error: Type Mismatch at line " + line +
////                    " -> Cannot apply '" + op + "' between '" + leftT + "' and '" + rightT + "'");
////            return;
////        }
////
////        // --- القاعدة الثانية: Type Error (مثل الأسطر 13، 16، 17) ---
////        if (leftT.equals("String")) {
////            if (op == Operator.Subtraction || op == Operator.Division || op == Operator.Multiplication) {
////                System.err.println("Semantic Error: Type error at line " + line +
////                        " -> Operator '" + op + "' is not supported for type 'String'");
////            }
////        }
////
////        if (leftT.equals("Boolean")) {
////            System.err.println("Semantic Error: Type error at line " + line +
////                    " -> Operator '" + op + "' is not supported for type 'Boolean'");
////        }
////    }
//    /**
//     * دالة التحقق من صحة العملية - النسخة الاحترافية المحدثة لدعم جمع الـ Integer والـ Float في البايثون
//     */
//    private void checkTypeValidity(String leftT, String rightT, Operator op, int line) {
//        // 1. إذا كان أحد الطرفين فارغاً، أو معرّفاً كـ "Name"
//        // نتخطى الفحص فوراً لمنع طباعة mismatch غير دقيق
//        if (leftT == null || rightT == null || leftT.equals("Name") || rightT.equals("Name")) {
//            return;
//        }
//
//        // --- القاعدة الأولى: Type Mismatch (مع استثناء الـ Integer والـ Float) ---
//        if (!leftT.equals(rightT)) {
//
//            // الحيلة الذكية: نتحقق إذا كانت العملية بين Integer و Float
//            boolean isNumericPromotion = (leftT.equals("Integer") && rightT.equals("Float")) ||
//                    (leftT.equals("Float") && rightT.equals("Integer"));
//
//            // إذا لم تكن عملية ترقية رقمية (مثلاً جمع String مع Integer)، اطبَع الخطأ
//            if (!isNumericPromotion) {
//                System.err.println("Semantic Error: Type Mismatch at line " + line +
//                        " -> Cannot apply '" + op + "' between '" + leftT + "' and '" + rightT + "'");
//                return;
//            }
//            // إذا كانت بين Integer و Float، سيتجاهل الـ return ويُكمل الفحص بسلام دون طباعة أخطاء!
//        }
//
//        // --- القاعدة الثانية: Type Error ---
//        if (leftT.equals("String")) {
//            if (op == Operator.Subtraction || op == Operator.Division || op == Operator.Multiplication) {
//                System.err.println("Semantic Error: Type error at line " + line +
//                        " -> Operator '" + op + "' is not supported for type 'String'");
//            }
//        }
//
//        if (leftT.equals("Boolean")) {
//            System.err.println("Semantic Error: Type error at line " + line +
//                    " -> Operator '" + op + "' is not supported for type 'Boolean'");
//        }
//    }
//}
package visitor.python;

import antlr.python.PythonParser;
import antlr.python.PythonParserBaseVisitor;
import ast.arithmeticExpr.ArithmeticExpression;
import ast.arithmeticExpr.Operator;
import ast.compundStmt.PythonExpression;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticExpressionVisitor extends PythonParserBaseVisitor<ArithmeticExpression> {
    private final PythonExpressionVisitor pythonExpressionVisitor = new PythonExpressionVisitor();

    @Override
    public ArithmeticExpression visitAddition(PythonParser.AdditionContext ctx) {
        return evaluate(Operator.Addition, ctx.getStart().getLine(), ctx.python_expr());
    }

    @Override
    public ArithmeticExpression visitSubtraction(PythonParser.SubtractionContext ctx) {
        return evaluate(Operator.Subtraction, ctx.getStart().getLine(), ctx.python_expr());
    }

    @Override
    public ArithmeticExpression visitDivision(PythonParser.DivisionContext ctx) {

        // ✅ Division by Zero check
        String rightText = ctx.python_expr(1).getText().trim();

        if (rightText.equals("0")) {
            System.err.println("Semantic Error: Division by zero at line " +
                    ctx.getStart().getLine());
        } else {
            // Check if it's a variable with value 0
            symbolTable.SymbolEntry entry = symbolTable.SymbolTableManager.INSTANCE
                    .getPythonTable().lookup(rightText);
            if (entry != null) {
                Object value = entry.getAttribute("Value");
                if ("0".equals(String.valueOf(value))) {
                    System.err.println("Semantic Error: Division by zero at line " +
                            ctx.getStart().getLine());
                }
            }
        }

        ArithmeticExpression expr = evaluate(Operator.Division, ctx.getStart().getLine(), ctx.python_expr());
        expr.node_name = "Float";
        return expr;
    }

    @Override
    public ArithmeticExpression visitMultiplication(PythonParser.MultiplicationContext ctx) {
        return evaluate(Operator.Multiplication, ctx.getStart().getLine(), ctx.python_expr());
    }

    private ArithmeticExpression evaluate(Operator operator, int line,
                                          List<PythonParser.Python_exprContext> pythonExprs) {
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression("ArithmeticOperation", line);
        arithmeticExpression.setOperator(operator);

        PythonExpression left = pythonExpressionVisitor.visit(pythonExprs.getFirst());
        arithmeticExpression.setLeft(left);

        String resultType = left.node_name;
        List<PythonExpression> right = new ArrayList<>();

        for (int i = 1; i < pythonExprs.size(); i++) {
            PythonExpression currentRight = pythonExpressionVisitor.visit(pythonExprs.get(i));
            right.add(currentRight);

            // --- فحص صحة العملية (Semantic Check) ---
            checkTypeValidity(resultType, currentRight.node_name, operator, line);


            if ("Dynamic".equals(resultType) || "Dynamic".equals(currentRight.node_name)) {
                resultType = "Dynamic";
            } else if (currentRight.node_name != null && currentRight.node_name.equals("Float")) {
                resultType = "Float";
            } else if (resultType == null || resultType.equals("Integer")) {
                if (currentRight.node_name != null) {
                    resultType = currentRight.node_name;
                }
            }
        }

        arithmeticExpression.setRight(right);
        arithmeticExpression.node_name = resultType;
        return arithmeticExpression;
    }


    private void checkTypeValidity(String leftT, String rightT, Operator op, int line) {
        // 1. إذا كان أحد الطرفين فارغاً، أو معرّفاً كـ "Name"
        if (leftT == null || rightT == null || leftT.equals("Name") || rightT.equals("Name")) {
            return;
        }

        // 🚀 القاعدة الذهبية للـ Dynamic: إذا كان أحد الأطراف Dynamic، نتخطى كل الفحوصات بسلام!
        if (leftT.equals("Dynamic") || rightT.equals("Dynamic")) {
            return;
        }

        // --- القاعدة الأولى: Type Mismatch (مع استثناء الـ Integer والـ Float) ---
        if (!leftT.equals(rightT)) {

            // التحقق إذا كانت العملية بين Integer و Float للترقية الرقمية
            boolean isNumericPromotion = (leftT.equals("Integer") && rightT.equals("Float")) ||
                    (leftT.equals("Float") && rightT.equals("Integer"));

            if (!isNumericPromotion) {
                System.err.println("Semantic Error: Type Mismatch at line " + line +
                        " -> Cannot apply '" + op + "' between '" + leftT + "' and '" + rightT + "'");
                return;
            }
        }

        // --- القاعدة الثانية: Type Error (حالات خاصة للأنواع الصريحة المعلومة) ---
        if (leftT.equals("String")) {
            if (op == Operator.Subtraction || op == Operator.Division || op == Operator.Multiplication) {
                System.err.println("Semantic Error: Type error at line " + line +
                        " -> Operator '" + op + "' is not supported for type 'String'");
            }
        }

        if (leftT.equals("Boolean")) {
            System.err.println("Semantic Error: Type error at line " + line +
                    " -> Operator '" + op + "' is not supported for type 'Boolean'");
        }
    }
}
