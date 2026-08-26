package ast.complexExp;

import ast.ASTNode;

import java.util.List;

public class ListItems extends ComplexExpression {
    private List<ASTNode> items;

    public ListItems(int line_number) {
        super("ExpressionList", line_number);
    }

    public void setAtomList(List<ASTNode> items) {
        this.items = items;
    }

    public List<ASTNode> getAtomList() {
        return items;
    }
}