package ast.jinja.jinjaExpression;

public class JinjaArithmeticExpression extends JinjaExpression {
    private String left;
    private String operator;
    private String right;

    public JinjaArithmeticExpression(int line_number) {
        super("JinjaArithmeticExpression", line_number);
    }

    public void setLeft(String left) { this.left = left; }
    public void setOperator(String operator) { this.operator = operator; }
    public void setRight(String right) { this.right = right; }

    public String getLeft() { return left; }
    public String getOperator() { return operator; }
    public String getRight() { return right; }

    @Override
    public String toString() {
        return super.toString() + " ( " + left + " " + operator + " " + right + " ) ";
    }
    @Override
    public String generateCode() {
        return left + " " + operator + " " + right;
    }

}