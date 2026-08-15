package ast.jinja.jinjaCallExpr;

import ast.jinja.JinjaArgumentsList;

public class JinjaFunctionCall extends JinjaCallExpression {
    private JinjaArgumentsList argumentsList;
    private String functionName;
    public JinjaFunctionCall(int line_number) {
        super("JinjaFunctionCall", line_number);
    }

    public void setArgumentsList(JinjaArgumentsList argumentsList) {
        this.argumentsList = argumentsList;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    @Override
    public String toString() {
        if (argumentsList == null) {
            return functionName + "()";
        }
        return functionName + " ( " + argumentsList.toString() + " )";
    }
    @Override
    public String generateCode() {
        return functionName + "(" +
                (argumentsList != null ? argumentsList.generateCode() : "") + ")";
    }
}
