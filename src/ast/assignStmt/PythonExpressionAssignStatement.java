package ast.assignStmt;

import ast.compundStmt.PythonExpression;

public class PythonExpressionAssignStatement extends AssignmentStatement {
    private PythonExpression value;

    public PythonExpressionAssignStatement(int line_number) {
        super("PythonExpressionAssignStatement", line_number);
    }

    public void setValue(PythonExpression value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString() + value.toString();
    }
    @Override
    public String generateCode() {
        return super.generateCode() + (value != null ? value.generateCode() : "");
    }
}
