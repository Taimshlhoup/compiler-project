package ast.keyValue;

import ast.simpleExpr.SimpleExpression;

public class SimpleKeyValue extends KeyValue {

    SimpleExpression value;

    public SimpleKeyValue(int line_number) {
        super("SimpleKeyValue", line_number);
    }

    public void setValue(SimpleExpression value) {
        this.value = value;
    }

    @Override
    public String symbolTablePrint() {
        return super.symbolTablePrint() + value.symbolTablePrint();
    }

    @Override
    public String toString() {
        return super.toString() + value.toString();
    }
    @Override
    public String generateCode() {
        return getKey().getValue() + ": " + value.generateCode();
    }
    @Override
    public String getValueCode() {
        return value.generateCode();
    }
}
