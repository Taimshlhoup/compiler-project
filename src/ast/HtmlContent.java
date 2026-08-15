package ast;

import ast.htmlContentItem.HtmlContentItem;

import java.util.List;

public class HtmlContent extends ASTNode {
    private List<HtmlContentItem> items;

    public HtmlContent(int line_number) {
        super("HtmlContent", line_number);
    }

    public void setItems(List<HtmlContentItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        if (items != null) {
            for (HtmlContentItem htmlContentItem : items) {
                stringBuilder.append(Consts.printIndent(4))
                        .append(htmlContentItem.toString());
            }
        }

        return stringBuilder.toString();
    }
    @Override
    public String generateCode() {
        StringBuilder code = new StringBuilder();
        if (items != null) {
            for (HtmlContentItem item : items) {
                if (item instanceof ast.jinja.jinjaExpression.JinjaSimpleExpression) {
                    code.append(((ast.jinja.jinjaExpression.JinjaSimpleExpression) item).generateDisplayCode());
                } else if (item instanceof ast.jinja.jinjaExpression.JinjaArithmeticExpression) {
                    code.append("{{ ").append(item.generateCode()).append(" }}");
                } else if (item instanceof ast.htmlElement.TagElement) {
                    code.append(item.generateCode());
                } else {
                    // HtmlTextItem — بدون سطر جديد
                    code.append(item.generateCode());
                }
            }
        }
        return code.toString();
    }
}
