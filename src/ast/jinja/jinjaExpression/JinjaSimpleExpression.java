

package ast.jinja.jinjaExpression;

public class JinjaSimpleExpression extends JinjaExpression {
    private JinjaExpression expr;  // ✅ غيّر من JinjaCallExpression إلى JinjaExpression

    public JinjaSimpleExpression(int line_number) {
        super("JinjaSimpleExpr node", line_number);
    }

    public void setExpr(JinjaExpression expr) {  // ✅ نفس التغيير هنا
        this.expr = expr;
    }

    @Override
    public String toString() {
        return expr != null ? expr.toString() : "null";
    }
    @Override
    public String generateCode() {
        return expr != null ? expr.generateCode() : "";
    }

    public String generateDisplayCode() {
        return expr != null ? "{{ " + expr.generateCode() + " }}" : "";
    }
}
