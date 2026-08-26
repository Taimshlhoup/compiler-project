package ast.complexExp;

import ast.keyValue.KeyValue;

import java.util.List;

public class DictionaryLiteral extends ComplexExpression {
    private List<KeyValue> keyValues;

    public DictionaryLiteral(int line_number) {
        super("DictionaryLiteral", line_number);
    }

    public void setKeyValues(List<KeyValue> keyValues) {
        this.keyValues = keyValues;
    }
    public List<KeyValue> getKeyValues() {
        return keyValues;
    } 
    @Override
    public String symbolTablePrint() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" { ");
        for (KeyValue keyValue : keyValues) {
            stringBuilder.append(keyValue.symbolTablePrint())
                    .append((keyValues.indexOf(keyValue) == (keyValues.size() - 1)) ? "" : ", ");
        }
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString()).append(" { ");
        for (KeyValue keyValue : keyValues) {
            stringBuilder.append(keyValue.toString())
                    .append((keyValues.indexOf(keyValue) == (keyValues.size() - 1)) ? "" : ", ");
        }
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
    @Override
    public String generateCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (keyValues != null) {
            for (int i = 0; i < keyValues.size(); i++) {
                sb.append(keyValues.get(i).generateCode());
                if (i < keyValues.size() - 1) sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
