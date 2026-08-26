//package ast.jinja.jinjaStatment;
//
//import ast.Consts;
//import ast.HtmlContent;
//import ast.jinja.jinjaExpression.JinjaExpression;
//
//public class JinjaForStatement extends JinjaStatement {
//    private String id;
//    private HtmlContent htmlContent;
//    private JinjaExpression iterable;
//
//    public JinjaForStatement(int line_number) {
//        super("JinjaForStatement", line_number);
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public void setHtmlContent(HtmlContent htmlContent) {
//        this.htmlContent = htmlContent;
//    }
//
//    public void setIterable(JinjaExpression iterable) {
//        this.iterable = iterable;
//    }
//
//    @Override
//    public String toString() {
//        return super.toString() + " ( " + id +
//                " in " + iterable.toString() + " ) " +
//                Consts.printIndent(3) + htmlContent.toString();
//    }
//}
package ast.jinja.jinjaStatment;

import ast.Consts;
import ast.HtmlContent;
import ast.jinja.jinjaExpression.JinjaExpression;

public class JinjaForStatement extends JinjaStatement {
    private String id;
    private HtmlContent htmlContent;
    private JinjaExpression iterable;

    public JinjaForStatement(int line_number) {
        super("JinjaForStatement", line_number);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHtmlContent(HtmlContent htmlContent) {
        this.htmlContent = htmlContent;
    }

    public void setIterable(JinjaExpression iterable) {
        this.iterable = iterable;
    }

    @Override
    public String toString() {
        return super.toString() + " ( " + id +
                " in " + (iterable != null ? iterable.toString() : "null") + " ) " +
                (htmlContent != null ? Consts.printIndent(3) + htmlContent.toString() : "");
    }
    @Override
    public String generateCode() {
        String iterableName = iterable != null ? iterable.generateCode() : "";

        symbolTable.SymbolEntry entry = symbolTable.SymbolTableManager.INSTANCE
                .getPythonTable().lookup(iterableName);

        Object nodeObj = entry != null ? entry.getAttribute("Node") : null;

        if (nodeObj instanceof ast.complexExp.ListLiteral) {
            ast.complexExp.ListLiteral listLiteral = (ast.complexExp.ListLiteral) nodeObj;
            StringBuilder result = new StringBuilder();
            String bodyTemplate = htmlContent != null ? htmlContent.generateCode() : "";

            if (listLiteral.getListItems() != null) {
                int index = 0;
                for (ast.ASTNode item : listLiteral.getListItems()) {
                    if (item instanceof ast.complexExp.DictionaryLiteral) {
                        ast.complexExp.DictionaryLiteral dict = (ast.complexExp.DictionaryLiteral) item;
                        String iterationHtml = bodyTemplate;

                        // ✅ دعم {{ id.index }} — رقم ترتيب العنصر في القائمة
                        iterationHtml = iterationHtml.replace(
                                "{{ " + id + ".index }}", String.valueOf(index));

                        if (dict.getKeyValues() != null) {
                            for (ast.keyValue.KeyValue kv : dict.getKeyValues()) {
                                String key = kv.getKey().getValue().toString().replaceAll("^\"|\"$", "");
                                String value = kv.getValueCode().replaceAll("^\"|\"$", "");
                                String placeholder = "{{ " + id + "." + key + " }}";
                                iterationHtml = iterationHtml.replace(placeholder, value);
                            }
                        }
                        result.append(iterationHtml);
                        index++;
                    }
                }
            }
            return result.toString();
        }

        // fallback القديم
        StringBuilder code = new StringBuilder();
        code.append("{% for ").append(id).append(" in ").append(iterableName).append(" %}\n");
        if (htmlContent != null) {
            code.append(htmlContent.generateCode());
        }
        code.append("{% endfor %}\n");
        return code.toString();
    }
}
