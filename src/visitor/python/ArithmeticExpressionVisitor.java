
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


        String rightText = ctx.python_expr(1).getText().trim();

        if (rightText.equals("0")) {
            System.err.println("Semantic Error: Division by zero at line " +
                    ctx.getStart().getLine());
        } else {

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

        if (leftT == null || rightT == null || leftT.equals("Name") || rightT.equals("Name")) {
            return;
        }


        if (leftT.equals("Dynamic") || rightT.equals("Dynamic")) {
            return;
        }


        if (!leftT.equals(rightT)) {


            boolean isNumericPromotion = (leftT.equals("Integer") && rightT.equals("Float")) ||
                    (leftT.equals("Float") && rightT.equals("Integer"));

            if (!isNumericPromotion) {
                System.err.println("Semantic Error: Type Mismatch at line " + line +
                        " -> Cannot apply '" + op + "' between '" + leftT + "' and '" + rightT + "'");
                return;
            }
        }


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
