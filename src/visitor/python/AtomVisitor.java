//package visitor.python;
//
//import antlr.python.PythonParser;
//import antlr.python.PythonParserBaseVisitor;
//import ast.atom.*;
//import ast.atom.Number;
//import symbolTable.SymbolTable;
//import symbolTable.SymbolTableManager;
//
//public class AtomVisitor extends PythonParserBaseVisitor<Atom> {
//
//    // الحصول على النسخة الوحيدة من جدول الرموز (Singleton)
//    private final SymbolTable sb = SymbolTableManager.INSTANCE.getPythonTable();
//
//    @Override
//    public Atom visitNameAtom(PythonParser.NameAtomContext ctx) {
//        // 1. استخراج اسم المتغير
//        String varName = ctx.NAME().getText();
//
//        // 2. الفحص الدلالي (Semantic Check): هل المتغير مُعرّف مسبقاً؟
//        // نقوم بعملية lookup؛ إذا كانت النتيجة null فهذا خطأ "Undefined Variable"
//        if (sb.lookup(varName) == null) {
//            System.err.println("Semantic Error: Undefined variable '" + varName + "' at line " + ctx.getStart().getLine());
//        }
//
//        // 3. إكمال عملية بناء العقدة في شجرة الـ AST
//        Name name = new Name(ctx.getStart().getLine());
//        name.setValue(varName);
//        return name;
//    }
//
//    @Override
//    public ClassAtom visitClassAtom(PythonParser.ClassAtomContext ctx) {
//        ClassAtom classAtom = new ClassAtom(ctx.getStart().getLine());
//        classAtom.setValue(ctx.CLASS_NAME());
//        return classAtom;
//    }
//
//    @Override
//    public Atom visitNumberAtom(PythonParser.NumberAtomContext ctx) {
//        Number number = new Number(ctx.getStart().getLine());
//        number.setValue(ctx.NUMBER().getText());
//        return number;
//    }
//
//    @Override
//    public Atom visitStringAtom(PythonParser.StringAtomContext ctx) {
//        Str str = new Str(ctx.getStart().getLine());
//        str.setValue(ctx.STRING().getText());
//        return str;
//    }
//
//    @Override
//    public Atom visitNoneAtom(PythonParser.NoneAtomContext ctx) {
//        return new None(ctx.getStart().getLine());
//    }
//
//    @Override
//    public Atom visitBooleanAtom(PythonParser.BooleanAtomContext ctx) {
//        Bool bool = new Bool(ctx.getStart().getLine());
//        // التأكد من نوع القيمة البوليانية (True أو False)
//        if (ctx.getChild(0) instanceof PythonParser.TrueAtomContext) {
//            bool.setValue("True");
//        } else {
//            bool.setValue("False");
//        }
//        return bool;
//    }
//}
//package visitor.python;
//
//import antlr.python.PythonParser;
//import antlr.python.PythonParserBaseVisitor;
//import ast.atom.*;
//import ast.atom.Number;
//import symbolTable.SymbolTable;
//import symbolTable.SymbolTableManager;
//
//public class AtomVisitor extends PythonParserBaseVisitor<Atom> {
//
//    @Override
//    public Atom visitNameAtom(PythonParser.NameAtomContext ctx) {
//        String varName = ctx.NAME().getText();
//        SymbolTable sb = SymbolTableManager.INSTANCE.getPythonTable();
//
//        // 1. الفحص الدلالي
//        Object symbolInfo = sb.lookup(varName);
//        if (symbolInfo == null) {
//            System.err.println("Semantic Error: Undefined variable '" + varName + "' at line " + ctx.getStart().getLine());
//        }
//
//        // 2. بناء عقدة الـ AST
//        Name name = new Name(ctx.getStart().getLine());
//        name.setValue(varName);
//
//        // 3. تمرير النوع المخزن في جدول الرموز إلى عقدة الـ AST
//        if (symbolInfo != null) {
//            name.node_name = (String) sb.getAttribute(varName, "Type");
//        }
//
//        return name;
//    }
//
//    @Override
//    public Atom visitNumberAtom(PythonParser.NumberAtomContext ctx) {
//        Number number = new Number(ctx.getStart().getLine());
//        String textValue = ctx.NUMBER().getText();
//        number.setValue(textValue);
//
//        // تحديد ما إذا كان الرقم Integer أم Float لضبط النوع بدقة
//        if (textValue.contains(".")) {
//            number.node_name = "Float";
//        } else {
//            number.node_name = "Integer";
//        }
//
//        return number;
//    }
//
//    @Override
//    public Atom visitStringAtom(PythonParser.StringAtomContext ctx) {
//        Str str = new Str(ctx.getStart().getLine());
//        str.setValue(ctx.STRING().getText());
//
//        // ضبط النوع كـ String
//        str.node_name = "String";
//        return str;
//    }
//
//    @Override
//    public Atom visitBooleanAtom(PythonParser.BooleanAtomContext ctx) {
//        Bool bool = new Bool(ctx.getStart().getLine());
//
//        // فحص النص مباشرة بدلاً من استدعاء ميثود قد لا تكون موجودة
//        String text = ctx.getText();
//        if (text.equals("True")) {
//            bool.setValue("True");
//        } else {
//            bool.setValue("False");
//        }
//
//        bool.node_name = "Boolean";
//        return bool;
//    }
//
//    @Override
//    public Atom visitNoneAtom(PythonParser.NoneAtomContext ctx) {
//        None none = new None(ctx.getStart().getLine());
//        none.node_name = "NoneType";
//        return none;
//    }
//}

package visitor.python;

import antlr.python.PythonParser;
import antlr.python.PythonParserBaseVisitor;
import ast.atom.*;
import ast.atom.Number;
import symbolTable.SymbolEntry;
import symbolTable.SymbolTable;
import symbolTable.SymbolTableManager;

public class AtomVisitor extends PythonParserBaseVisitor<Atom> {

@Override
public Atom visitNameAtom(PythonParser.NameAtomContext ctx) {
    String varName = ctx.getText().trim();


    SymbolTable pythonTable = SymbolTableManager.INSTANCE.getPythonTable();
    SymbolEntry symbolInfo = pythonTable.lookup(varName);

    if (symbolInfo == null) {
        if (isFunctionParameter(ctx, varName)) {
            pythonTable.insert(varName);
            pythonTable.setAttribute(varName, "Type", "Dynamic");
            symbolInfo = pythonTable.lookup(varName);
        } else {

            if (!SymbolTableManager.INSTANCE.isDeclarationOnlyMode()) {

                if (SymbolTableManager.INSTANCE.hasVariableExisted(varName)) {
                    System.err.println("Semantic Error: Scope error '" + varName + "' at line " + ctx.getStart().getLine());
                } else {
                    System.err.println("Semantic Error: Undefined variable '" + varName + "' at line " + ctx.getStart().getLine());
                }

            }
        }
    }


    Name name = new Name(ctx.getStart().getLine());
    name.setValue(varName);


    if (symbolInfo != null) {
        name.node_name = (String) pythonTable.getAttribute(varName, "Type");
    }

    return name;
}

    /**
     * 🚀 تابع مساعد فائق الأمان يصعد في شجرة الـ Context ويحلل النص ديناميكياً بدون ميثودز مخصصة
     */
    private boolean isFunctionParameter(org.antlr.v4.runtime.RuleContext ctx, String varName) {
        org.antlr.v4.runtime.RuleContext current = ctx.getParent();

        while (current != null) {
            String className = current.getClass().getSimpleName();

            // التحقق إذا كنا داخل سياق دالة بأي مسمى كان
            if (className.equals("FunctionDefDefContext") ||
                    className.equals("FunctionDefinitionContext") ||
                    className.equals("Func_defContext")) {


                for (int i = 0; i < current.getChildCount(); i++) {
                    org.antlr.v4.runtime.tree.ParseTree child = current.getChild(i);
                    String childClass = child.getClass().getSimpleName();


                    if (childClass.contains("Parameter") || childClass.contains("Arg") || childClass.contains("Context")) {
                        if (checkParamText(child.getText(), varName)) {
                            return true;
                        }
                    }
                }


                StringBuilder headerBuilder = new StringBuilder();
                for (int i = 0; i < Math.min(current.getChildCount(), 5); i++) {
                    headerBuilder.append(current.getChild(i).getText());
                }

                String headerText = headerBuilder.toString();
                if (headerText.contains("(") && headerText.contains(")")) {
                    int start = headerText.indexOf("(");
                    int end = headerText.lastIndexOf(")");
                    if (start < end) {
                        String paramsText = headerText.substring(start + 1, end);
                        if (checkParamText(paramsText, varName)) {
                            return true;
                        }
                    }
                }
            }
            current = current.getParent(); // الاستمرار بالصعود في الشجرة
        }
        return false;
    }


    private boolean checkParamText(String paramsText, String varName) {
        // إزالة الأقواس المحيطة بالبرامترات لتسهيل فحصها
        String clean = paramsText.replaceAll("[()]", "");
        if (clean.trim().isEmpty()) return false;

        // تقسيم البرامترات بالفاصلة لدعم الدوال متعددة المدخلات
        String[] splitParams = clean.split(",");
        for (String param : splitParams) {
            String name = param.trim();

            // إذا كان البرامتر يحتوي على Type Hint مثل (b: int)
            if (name.contains(":")) {
                name = name.split(":")[0].trim();
            }
            // إذا كان البرامتر يحتوي على قيمة افتراضية مثل (b = 5)
            if (name.contains("=")) {
                name = name.split("=")[0].trim();
            }

            if (name.equals(varName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Atom visitNumberAtom(PythonParser.NumberAtomContext ctx) {
        Number number = new Number(ctx.getStart().getLine());
        String textValue = ctx.NUMBER().getText();
        number.setValue(textValue);

        if (textValue.contains(".")) {
            number.node_name = "Float";
        } else {
            number.node_name = "Integer";
        }

        return number;
    }

    @Override
    public Atom visitStringAtom(PythonParser.StringAtomContext ctx) {
        Str str = new Str(ctx.getStart().getLine());
        str.setValue(ctx.STRING().getText());
        str.node_name = "String";
        return str;
    }

    @Override
    public Atom visitBooleanAtom(PythonParser.BooleanAtomContext ctx) {
        Bool bool = new Bool(ctx.getStart().getLine());
        String text = ctx.getText();
        if (text.equals("True")) {
            bool.setValue("True");
        } else {
            bool.setValue("False");
        }
        bool.node_name = "Boolean";
        return bool;
    }

    @Override
    public Atom visitNoneAtom(PythonParser.NoneAtomContext ctx) {
        None none = new None(ctx.getStart().getLine());
        none.node_name = "NoneType";
        return none;
    }
}
