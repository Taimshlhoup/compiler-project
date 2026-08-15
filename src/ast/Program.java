package ast;

import java.util.List;

public class Program extends ASTNode{

    private List<Statement> statements;

    public Program(int line_number, List<Statement> statements) {
        super("Program", line_number);
        this.statements = statements;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        for(Statement s : this.statements){
            stringBuilder.append(Consts.printIndent(1)).append(s.toString());
        }
        return stringBuilder.toString();
    }
    @Override
    public String generateCode() {
        StringBuilder code = new StringBuilder();
        code.append("from flask import Flask, render_template, request, redirect, url_for\n\n");
        code.append("app = Flask(__name__)\n\n");
        code.append("products = []\n\n");

        // ✅ أولاً أضف كل الـ statements
        for (Statement s : this.statements) {
            String stmtCode = s.generateCode();
            if (!stmtCode.isEmpty()) {
                stmtCode = stmtCode.replace(".jinja", ".html").replace(".j2", ".html");
                code.append(stmtCode).append("\n");
            }
        }

        code.append("\nif __name__ == '__main__':\n");
        code.append("    app.run(debug=True)\n");

        return code.toString();
    }
}
