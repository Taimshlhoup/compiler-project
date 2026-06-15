package visitor.python;

import antlr.python.PythonParser;
import antlr.python.PythonParserBaseVisitor;
import ast.Statement;
import ast.compundStmt.CompoundStatement;

import java.util.ArrayList;
import java.util.List;

public class StatementVisitor extends PythonParserBaseVisitor<Statement> {
    @Override
    public Statement visitCompoundStatement(PythonParser.CompoundStatementContext ctx) {
        Statement statement = new Statement(ctx.getStart().getLine());
        List<CompoundStatement> compoundStatementList = new ArrayList<>();
        CompoundStatementVisitor compoundStatementVisitor = new CompoundStatementVisitor();
        for (int i = 0; i < ctx.compound_stmt().size(); i++) {
            compoundStatementList.add(compoundStatementVisitor.visit(ctx.compound_stmt(i)));
        }
        statement.setCompoundStatements(compoundStatementList);
        return statement;
    }

    @Override
    public Statement visitPassStatement(PythonParser.PassStatementContext ctx) {
        Statement statement = new Statement(ctx.getStart().getLine());
        statement.setPass(true);
        return statement;
    }
}
