
package visitor.python;

import antlr.python.PythonParser;
import antlr.python.PythonParserBaseVisitor;
import ast.compundStmt.ForLoop;
import symbolTable.SymbolTable;
import symbolTable.SymbolTableManager;

public class ForLoopVisitor extends PythonParserBaseVisitor<ForLoop> {

    @Override
    public ForLoop visitSimpleForLoop(PythonParser.SimpleForLoopContext ctx) {



        if (ctx.atom() != null) {
            String varName = ctx.atom().getText().trim();


            SymbolTable currentSb = SymbolTableManager.INSTANCE.getPythonTable();
            if (!currentSb.existsInCurrentScope(varName)) {
                currentSb.insert(varName);
            }
            currentSb.setAttribute(varName, "Type", "Integer");
        }

        ForLoop forLoop = new ForLoop(ctx.getStart().getLine());


        boolean shouldVisitBody = true;
        if (ctx.python_expr() != null) {

            String exprText = ctx.python_expr().getText().replaceAll("\\s+", "");

            if (exprText.equals("range(0)")) {

                shouldVisitBody = false;
            }
        }


        if (ctx.statement() != null && shouldVisitBody) {
            new StatementVisitor().visit(ctx.statement());
        }

        return forLoop;
    }

    @Override
    public ForLoop visitComplexForLoop(PythonParser.ComplexForLoopContext ctx) {

        if (ctx.atom(1) != null) {
            String varName = ctx.atom(1).getText().trim();


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
