//package visitor.python;
//
//import antlr.python.PythonParser;
//import antlr.python.PythonParserBaseVisitor;
//import ast.compundStmt.PythonExpression;
//
//
//public class PythonExpressionVisitor extends PythonParserBaseVisitor<PythonExpression> {
//    private final ComplexExpressionVisitor complexExpressionVisitor = new ComplexExpressionVisitor();
//
//    @Override
//    public PythonExpression visitComplexExpression(PythonParser.ComplexExpressionContext ctx) {
//        return complexExpressionVisitor.visit(ctx.complex_expr());
//    }
//
//    @Override
//    public PythonExpression visitAtomComplexExpression(PythonParser.AtomComplexExpressionContext ctx) {
//        AtomExpressionVisitor atomExpressionVisitor = new AtomExpressionVisitor();
//        return atomExpressionVisitor.visit(ctx.atom_expr());
//    }
//
//}
package visitor.python;

import antlr.python.PythonParser;
import antlr.python.PythonParserBaseVisitor;
import ast.compundStmt.PythonExpression;

public class PythonExpressionVisitor extends PythonParserBaseVisitor<PythonExpression> {
    private final ComplexExpressionVisitor complexExpressionVisitor = new ComplexExpressionVisitor();

    @Override
    public PythonExpression visitComplexExpression(PythonParser.ComplexExpressionContext ctx) {

        PythonExpression expr = complexExpressionVisitor.visit(ctx.complex_expr());


        if (expr != null) {

        }

        return expr;
    }

    @Override
    public PythonExpression visitAtomComplexExpression(PythonParser.AtomComplexExpressionContext ctx) {
        AtomExpressionVisitor atomExpressionVisitor = new AtomExpressionVisitor();
        PythonExpression expr = atomExpressionVisitor.visit(ctx.atom_expr());


        return expr;
    }
}

