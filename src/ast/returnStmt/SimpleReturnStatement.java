package ast.returnStmt;

import ast.atom.Atom;

public class SimpleReturnStatement extends ReturnStatement {
    private Atom atom;
    public SimpleReturnStatement(int line_number) {
        super("SimpleReturnStatement", line_number);
    }

    public void setAtom(Atom atom) {
        this.atom = atom;
    }

    @Override
    public String toString() {
        return super.toString() + atom.toString();
    }
    @Override
    public String generateCode() {
        return super.generateCode() + (atom != null ? atom.generateCode() : "");
    }

}
