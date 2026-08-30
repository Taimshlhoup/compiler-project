
package visitor.html;

import antlr.html.HtmlParser;
import antlr.html.HtmlParserBaseVisitor;
import ast.HtmlContent;
import ast.htmlContentItem.HtmlContentItem;
import ast.htmlContentItem.HtmlTextItem;
import java.util.ArrayList;
import java.util.List;

public class HtmlContentVisitor extends HtmlParserBaseVisitor<Object> {

    @Override
    public Object visitHtmlContent(HtmlParser.HtmlContentContext ctx) {
        HtmlContent htmlContent = new HtmlContent(ctx.getStart().getLine());
        List<HtmlContentItem> htmlContentItems = new ArrayList<>();


        if (ctx.html_content_item() != null) {
            for (HtmlParser.Html_content_itemContext itemCtx : ctx.html_content_item()) {

                Object result = visit(itemCtx);

                if (result instanceof HtmlContentItem) {
                    htmlContentItems.add((HtmlContentItem) result);
                }
            }
        }

        htmlContent.setItems(htmlContentItems);
        return htmlContent;
    }


    @Override
    public Object visitJinjaExprItem(HtmlParser.JinjaExprItemContext ctx) {
        Object result = new visitor.jinja.JinjaExpressionVisitor().visit(ctx.jinjaExpressionBlock());
        return result;
    }

    @Override
    public Object visitJinjaStmtItem(HtmlParser.JinjaStmtItemContext ctx) {
        return new visitor.jinja.JinjaStatementVisitor().visit(ctx.jinjaStatementBlock());
    }


    @Override
    public Object visitHtmlTextItem(HtmlParser.HtmlTextItemContext ctx) {
        HtmlTextItem textItem = new HtmlTextItem(ctx.getStart().getLine());
        textItem.setText(ctx.HTML_TEXT().getText());
        return textItem;
    }
    @Override
    public Object visitJinjaExprBlock(HtmlParser.JinjaExprBlockContext ctx) {
        return visit(ctx.j_expression());
    }
    @Override
    public Object visitHtmlElementItem(HtmlParser.HtmlElementItemContext ctx) {
        return new HtmlElementVisitor().visit(ctx.htmlElement());
    }
}