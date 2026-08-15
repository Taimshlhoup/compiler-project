package ast.htmlElement;

import ast.Consts;
import ast.tagContent.TagElementItem;

import java.util.List;

public class TagElement extends HtmlElement {
    private List<TagElementItem> tags;
    private String tagName;
    public TagElement(int line_number) {
        super("TagElement", line_number);
    }

    public void setTags(List<TagElementItem> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        if (tags != null) {
            for (TagElementItem tagElementItem : tags) {
                if (tagElementItem != null) {
                    stringBuilder.append(Consts.printIndent(3)).append(tagElementItem);
                }
            }
        }
        return stringBuilder.toString();
    }
    @Override
    public String generateCode() {
        if (tagName == null) return "";

        // وسم إغلاق
        if (tagName.startsWith("/")) {
            return "</" + tagName.substring(1) + ">";
        }

        // وسم افتتاح مع attributes
        StringBuilder code = new StringBuilder();
        code.append("<").append(tagName);

        if (tags != null) {
            for (int i = 0; i < tags.size(); i++) {
                // ✅ تخطَّ أول عنصر لأنه tagName نفسه
                if (i == 0) continue;
                TagElementItem tag = tags.get(i);
                if (tag != null) {
                    String tagCode = tag.generateCode();
                    if (!tagCode.isEmpty()) {
                        code.append(" ").append(tagCode);
                    }
                }
            }
        }
        code.append(">");
        return code.toString();
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
