package ast.jinja.jinjaStatment;

import ast.jinja.jinjaExpression.JinjaExpression;

public class JinjaSetStatement extends JinjaStatement {
    private String variableName;
    private JinjaExpression value;

    public JinjaSetStatement(int line_number) {
        super("JinjaSetStatement", line_number);
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public void setValue(JinjaExpression value) {
        this.value = value;
    }

    public String getVariableName() {
        return variableName;
    }

    public JinjaExpression getValue() {
        return value;
    }

//    @Override
//    public String toString() {
//        return super.toString() +
//                " ( " + variableName +
//                " = " + value.toString() + " ) ";
//    }
@Override
public String toString() {
    return super.toString() +
            " ( " + variableName +
            " = " + (value != null ? value.toString() : "null") + " ) ";
}
}