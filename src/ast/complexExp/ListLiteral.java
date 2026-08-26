package ast.complexExp;

import ast.ASTNode;

import java.util.List;

public class ListLiteral extends ComplexExpression {
    private List<ASTNode> listItems;

    public ListLiteral(int line_number) {
        super("ListLiteral", line_number);
    }

    public void setListItems(List<ASTNode> listItems) {
        this.listItems = listItems;
    }

    public List<ASTNode> getListItems() {
        return listItems;
    }
    @Override
    public String symbolTablePrint() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" [ ");
        if (listItems != null) {
            for (ASTNode listItem : listItems) {
                stringBuilder.append(listItem.toString())
                        .append((listItems.indexOf(listItem) == listItems.size() - 1)
                                ? "" : ", ");
            }
        }
        stringBuilder.append(" ] ");

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString()).append(" ( [ ");
        if (listItems != null) {
            for (ASTNode listItem : listItems) {
                stringBuilder.append(listItem.toString())
                        .append((listItems.indexOf(listItem) == listItems.size() - 1)
                                ? "" : ", ");
            }
        }
        stringBuilder.append(" ] ) ");

        return stringBuilder.toString();
    }
    @Override
    public String generateCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (listItems != null) {
            for (int i = 0; i < listItems.size(); i++) {
                sb.append(listItems.get(i).generateCode());
                if (i < listItems.size() - 1) sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}