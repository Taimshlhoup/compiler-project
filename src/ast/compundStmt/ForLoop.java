package ast.compundStmt;

import ast.atom.Atom;
import ast.condition.Condition;

import java.util.List;

public class ForLoop extends CompoundStatement {
    private Atom var;
    private PythonExpression iter;
    private Condition condition;

    public ForLoop(int line_number) {
        super("ForLoop", line_number);
    }

    public void setVar(Atom var) {
        this.var = var;
    }

    public void setIter(PythonExpression iter) {
        this.iter = iter;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }


    @Override
    public String symbolTablePrint() {
             return "for " +
            (var != null ? var.toString() : "null") + " in " +
            (iter != null ? iter.symbolTablePrint() : "null") +
            (condition == null ? "" : " if " + condition.symbolTablePrint());
}

    @Override
    public String toString() {
        return super.toString() +
                " ( " + (var != null ? var.toString() : "null") + " in " +
                (iter != null ? iter.toString() : "null") +
                (condition == null ? "" : condition.toString()) + " ) ";
    }
}
