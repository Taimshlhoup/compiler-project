package visitor.html;

import antlr.html.HtmlParser;
import antlr.html.HtmlParserBaseVisitor;
import ast.htmlElement.HtmlElement;
import ast.htmlElement.StyleSheet;
import ast.htmlElement.TagElement;
import ast.tagContent.TagElementItem;
import visitor.css.StyleSheetVisitor;

import java.util.ArrayList;
import java.util.List;

public class HtmlElementVisitor extends HtmlParserBaseVisitor<HtmlElement> {

    @Override
    public HtmlElement visitTagElement(HtmlParser.TagElementContext ctx) {
        TagElement tagElement = new TagElement(ctx.getStart().getLine());

        if (ctx.tag_content() != null && ctx.tag_content().size() > 0) {
            String firstTag = ctx.tag_content(0).getText();
            if (firstTag.equals("/")) {

                if (ctx.tag_content().size() > 1) {
                    tagElement.setTagName("/" + ctx.tag_content(1).getText());
                }
            } else {

                tagElement.setTagName(firstTag);
            }
        }

        TagContentVisitor tagContentVisitor = new TagContentVisitor();
        if (ctx.tag_content() != null) {
            List<TagElementItem> tagElementItemList = new ArrayList<>();
            for (int i = 0; i < ctx.tag_content().size(); i++) {
                TagElementItem tagElementItem = tagContentVisitor.visit(ctx.tag_content(i));
                tagElementItemList.add(tagElementItem);
            }
            tagElement.setTags(tagElementItemList);
        }
        return tagElement;
    }

    @Override
    public HtmlElement visitStyleElement(HtmlParser.StyleElementContext ctx) {
        return (StyleSheet) new StyleSheetVisitor().visit(ctx.style_sheet());
    }
}
