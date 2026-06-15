package ast.jinja.jinjaExpression;

import java.util.List;

public class JinjaListLiteral extends JinjaExpression {
    private List<String> items;

    public JinjaListLiteral(int line_number) {
        super("JinjaListLiteral", line_number);
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return super.toString() + " ( " + items.toString() + " ) ";
    }
}
