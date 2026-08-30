
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


    private boolean isFunctionParameter(org.antlr.v4.runtime.RuleContext ctx, String varName) {
        org.antlr.v4.runtime.RuleContext current = ctx.getParent();

        while (current != null) {
            String className = current.getClass().getSimpleName();


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
            current = current.getParent();
        }
        return false;
    }


    private boolean checkParamText(String paramsText, String varName) {

        String clean = paramsText.replaceAll("[()]", "");
        if (clean.trim().isEmpty()) return false;


        String[] splitParams = clean.split(",");
        for (String param : splitParams) {
            String name = param.trim();


            if (name.contains(":")) {
                name = name.split(":")[0].trim();
            }

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
