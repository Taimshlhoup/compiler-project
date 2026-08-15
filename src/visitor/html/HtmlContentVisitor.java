//package visitor.html;
//
//import antlr.html.HtmlParser;
//import antlr.html.HtmlParserBaseVisitor;
//import ast.HtmlContent;
//import ast.htmlContentItem.HtmlContentItem;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HtmlContentVisitor extends HtmlParserBaseVisitor<HtmlContent> {
//
//    @Override
//    public HtmlContent visitHtmlContent(HtmlParser.HtmlContentContext ctx) {
//        HtmlContent htmlContent = new HtmlContent(ctx.getStart().getLine());
//        List<HtmlContentItem> htmlContentItems = new ArrayList<>();
//        HtmlContentItemVisitor htmlContentItemVisitor = new HtmlContentItemVisitor();
//
//        // التأكد من وجود عناصر في السياق
//        if (ctx.html_content_item() != null) {
//            for (int i = 0; i < ctx.html_content_item().size(); i++) {
//
//                // سطر للتحقق من نوع العنصر الذي يراه الـ Parser (للإصلاح فقط)
//                System.out.println("DEBUG: Visiting item #" + i + " -> " + ctx.html_content_item(i).getText());
//
//                HtmlContentItem htmlContentItem = htmlContentItemVisitor.visit(ctx.html_content_item(i));
//
//                if (htmlContentItem != null) {
//                    htmlContentItems.add(htmlContentItem);
//                }
//            }
//        }
//
//        htmlContent.setItems(htmlContentItems);
//
//        // إذا طبع 0، فهناك مشكلة في الـ Lexer/Grammar
//        System.out.println("--- Finished visiting " + htmlContentItems.size() + " HTML items ---");
//
//        return htmlContent;
//    }
//}
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

        // التحقق من وجود عناصر بناءً على السطر 9 في الجرامر
        if (ctx.html_content_item() != null) {
            for (HtmlParser.Html_content_itemContext itemCtx : ctx.html_content_item()) {
                //System.out.println("DEBUG: Visiting item -> " + itemCtx.getText());

                // استدعاء visit بشكل متسلسل لفتح محتوى الجينجا
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