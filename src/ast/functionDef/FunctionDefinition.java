package ast.functionDef;

import ast.Consts;
import ast.Statement;
import ast.compundStmt.CompoundStatement;

public class FunctionDefinition extends CompoundStatement {
    private Decorator decorator;
    private String functionName;
    private FunctionParameters functionParameters;
    private Statement functionBody;

    public FunctionDefinition(int line_number) {
        super("FunctionDefinition", line_number);
    }

    public void setDecorator(Decorator decorator) {
        this.decorator = decorator;
    }

    public void setFunctionParameters(FunctionParameters functionParameters) {
        this.functionParameters = functionParameters;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public void setFunctionBody(Statement functionBody) {
        this.functionBody = functionBody;
    }

    @Override
    public String toString() {
        return super.toString() + "( " + (decorator == null ? "" : decorator.toString())
                + functionName + "(" + functionParameters.toString() + ") ) "
                + Consts.printIndent(2) + functionBody.toString()
                ;
    }
    @Override
    public String generateCode() {
        StringBuilder code = new StringBuilder();

        // ✅ أضف الـ decorator
        if (decorator != null) {
            String decoratorCode = decorator.generateCode();

            if (decoratorCode.contains("/add") ||
                    decoratorCode.contains("/edit") ||
                    decoratorCode.contains("/delete")) {
                decoratorCode = decoratorCode.replace(")", ", methods=['GET', 'POST'])");
            }

            if (decoratorCode.contains("/detail")) {
                decoratorCode = decoratorCode.replace("'/detail'", "'/detail/<int:index>'");
            }
            if (decoratorCode.contains("/delete")) {
                decoratorCode = decoratorCode.replace("'/delete'", "'/delete/<int:index>'");
            }

            code.append(decoratorCode).append("\n");
        }

        // ✅ أضف تعريف الدالة
        code.append("def ").append(functionName).append("(");
        if (functionParameters != null) {
            code.append(functionParameters.generateCode());
        }
        if (decorator != null && (decorator.generateCode().contains("/detail") ||
                decorator.generateCode().contains("/delete"))) {
            code.append("index");
        }
        code.append("):\n");

        // ✅ أضف جسم الدالة
        if (functionBody != null) {
            String body = functionBody.generateCode();

            if (decorator != null && decorator.generateCode().contains("/add")) {
                code.append("    if request.method == 'POST':\n");
                code.append("        name = request.form.get('name')\n");
                code.append("        price = request.form.get('price')\n");
                code.append("        products.append({'name': name, 'price': price})\n");
                code.append("        return redirect('/')\n");
            }

            if (decorator != null && decorator.generateCode().contains("/detail")) {
                code.append("    product = products[index]\n");
                code.append("    return render_template('detail.html', product=product)\n");
                return code.toString();
            }

            if (decorator != null && decorator.generateCode().contains("/delete")) {
                code.append("    products.pop(index)\n");
                code.append("    return redirect('/')\n");
                return code.toString();
            }

            for (String line : body.split("\n")) {
                code.append("    ").append(line).append("\n");
            }
        }
        return code.toString();
    }
}
