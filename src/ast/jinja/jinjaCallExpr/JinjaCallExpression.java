//package ast.jinja.jinjaCallExpr;
//
//import ast.ASTNode;
//
//public abstract class JinjaCallExpression extends ASTNode {
//    public JinjaCallExpression(String node_name, int line_number) {
//        super(node_name, line_number);
//    }
//}
package ast.jinja.jinjaCallExpr;

import ast.jinja.jinjaExpression.JinjaExpression;

public abstract class JinjaCallExpression extends JinjaExpression {
    public JinjaCallExpression(String node_name, int line_number) {
        super(node_name, line_number);
    }
}