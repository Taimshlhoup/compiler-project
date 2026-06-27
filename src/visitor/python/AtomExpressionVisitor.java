package visitor.python;

import antlr.python.PythonParser;
import antlr.python.PythonParserBaseVisitor;
import ast.argsList.ArgumentsList;
import ast.atom.Atom;
import ast.atomExpression.*;
import symbolTable.SymbolTableManager;

import java.util.ArrayList;
import java.util.List;

public class AtomExpressionVisitor extends PythonParserBaseVisitor<AtomExpression> {
    private final AtomVisitor atomVisitor = new AtomVisitor();

    public static symbolTable.SymbolTable pythonScopeAtRender = null;


    @Override
    public AtomExpression visitDictionaryAccess(PythonParser.DictionaryAccessContext ctx) {
        DictionaryAccess dictionaryAccess = new DictionaryAccess(ctx.getStart().getLine());
        Atom atom = atomVisitor.visit(ctx.atom());
        dictionaryAccess.setVarName(atom.getValue().toString());
        dictionaryAccess.setKey(ctx.STRING().getText());
        return dictionaryAccess;
    }

    @Override
    public AtomExpression visitAttributeAccess(PythonParser.AttributeAccessContext ctx) {
        AttributeAccess attributeAccess = new AttributeAccess(ctx.getStart().getLine());
        Atom atom = atomVisitor.visit(ctx.atom(0));
        List<Atom> atomList = new ArrayList<>();
        for (int i = 1; i < ctx.atom().size(); i++) {
            Atom a = atomVisitor.visit(ctx.atom(i));
            atomList.add(a);
        }
        attributeAccess.setVarName(atom.getValue().toString());
        attributeAccess.setAttributes(atomList);
        return attributeAccess;
    }

    @Override
    public AtomExpression visitMethodAccess(PythonParser.MethodAccessContext ctx) {
        MethodAccess methodAccess = new MethodAccess(ctx.getStart().getLine());
        Atom atom = atomVisitor.visit(ctx.atom());
        List<AtomExpression> atomExpressions = new ArrayList<>();
        for (int i = 0; i < ctx.atom_expr().size(); i++) {
            AtomExpression atomExpression = visit(ctx.atom_expr(i));
            atomExpressions.add(atomExpression);
        }
        methodAccess.setVarName(atom.getValue().toString());
        methodAccess.setMethodCalls(atomExpressions);
        return methodAccess;
    }

    @Override
    public AtomExpression visitObjectCreation(PythonParser.ObjectCreationContext ctx) {
        ObjectCreation objectCreation = new ObjectCreation(ctx.getStart().getLine());
        objectCreation.setVarName(ctx.CLASS_NAME().getText());
        if (ctx.arglist() != null) {
            ArgumentsList argumentsList = new ArgumentListVisitor().visit(ctx.arglist());
            objectCreation.setArgumentsList(argumentsList);
        }
        return objectCreation;
    }


public static class FunctionMetadata {
    public List<String> paramNames;
    public PythonParser.StatementContext bodyCtx;

    public FunctionMetadata(List<String> paramNames, PythonParser.StatementContext bodyCtx) {
        this.paramNames = paramNames;
        this.bodyCtx = bodyCtx;
    }
}
    public static final java.util.Map<String, FunctionMetadata> functionRegistry = new java.util.HashMap<>();


@Override
public AtomExpression visitFunctionCall(PythonParser.FunctionCallContext ctx) {
    FunctionCall functionCall = new FunctionCall(ctx.getStart().getLine());
    String funcName = ctx.NAME().getText();
    functionCall.setVarName(funcName);
    List<String> builtins = List.of(
            "print", "len", "range", "int", "str", "float",
            "render_template", "redirect", "url_for", "append"
    );
    if (!builtins.contains(funcName) && !functionRegistry.containsKey(funcName)) {
        System.err.println("Semantic Error: Undefined function '" + funcName +
                "' at line " + ctx.getStart().getLine());
    }
    // ✅ افحص render_template قبل زيارة الـ arglist
    if ("render_template".equals(funcName) && ctx.arglist() != null) {
        symbolTable.SymbolTable currentScope =
                SymbolTableManager.INSTANCE.getPythonTable();

        String[] args = ctx.arglist().getText().split(",");
        for (String arg : args) {
            if (arg.contains("=")) {
                String[] parts = arg.split("=", 2);
                String valueVar = parts[1].trim()
                        .replaceAll("[^a-zA-Z0-9_]", "");

                if (!valueVar.isEmpty()
                        && !valueVar.matches("[0-9]+.*")
                        && !valueVar.startsWith("'")
                        && !valueVar.equalsIgnoreCase("true")
                        && !valueVar.equalsIgnoreCase("false")
                        && !valueVar.equalsIgnoreCase("none")) {

                    // ✅ الفحص قبل ما يُسجَّل
                    if (currentScope != null &&
                            currentScope.lookup(valueVar) == null) {
                        System.err.println("Semantic Error: Undefined variable '" +
                                valueVar + "' used in render_template() at line " +
                                ctx.getStart().getLine());
                    }
                }
            }
        }
    }

    // بعدين زيارة الـ arglist
    ArgumentsList argumentsList = null;
    if (ctx.arglist() != null) {
        argumentsList = new ArgumentListVisitor().visit(ctx.arglist());
        functionCall.setArgumentsList(argumentsList);
    }



    if ("render_template".equals(funcName)) {
        try {
            symbolTable.SymbolTable currentScope = SymbolTableManager.INSTANCE.getPythonTable();
            pythonScopeAtRender = currentScope;
            symbolTable.SymbolTable jinjaTable = SymbolTableManager.INSTANCE.getJinjaTable();

            if (jinjaTable != null && ctx.arglist() != null) {
                String[] args = ctx.arglist().getText().split(",");
                for (String arg : args) {
                    if (arg.contains("=")) {
                        String[] parts = arg.split("=", 2);
                        String flaskVar = parts[0].trim().replaceAll("[^a-zA-Z0-9_]", "");
                        String valueVar = parts[1].trim().replaceAll("[^a-zA-Z0-9_]", "");




                        if (!flaskVar.isEmpty()) {
                            jinjaTable.insert(flaskVar);
                            jinjaTable.setAttribute(flaskVar, "Type", "Dynamic");

                            if (!valueVar.isEmpty() &&
                                    currentScope != null) {
                                symbolTable.SymbolEntry entry = currentScope.lookup(valueVar);
                                if (entry == null || "Dynamic".equals(entry.getAttribute("Type"))) {
                                    jinjaTable.setAttribute(flaskVar, "UndefinedInPython", "true");

                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        FunctionMetadata funcData = functionRegistry.get(funcName);

        if (funcData != null) {
            List<String> paramNames = funcData.paramNames;
            PythonParser.StatementContext bodyCtx = funcData.bodyCtx;
            //  Wrong Number of Arguments check
            int expectedArgs = paramNames.size();
            int actualArgs = 0;
            if (argumentsList instanceof ast.argsList.AtomArguments) {
                List<ast.atom.Atom> args = ((ast.argsList.AtomArguments) argumentsList).getArgs();
                if (args != null) {
                    actualArgs = args.size();
                }
            }
            if (actualArgs != expectedArgs) {
                System.err.println("Semantic Error: Wrong number of arguments for '" + funcName +
                        "' at line " + ctx.getStart().getLine() +
                        ". Expected " + expectedArgs + ", got " + actualArgs);
            }


            symbolTable.SymbolTableManager.INSTANCE.enterPythonLocalScope();

            try {

                if (argumentsList instanceof ast.argsList.AtomArguments) {
                    List<ast.atom.Atom> args = ((ast.argsList.AtomArguments) argumentsList).getArgs();

                    if (args != null) {
                        for (int i = 0; i < paramNames.size() && i < args.size(); i++) {
                            String pName = paramNames.get(i);
                            String argType = "Dynamic";

                            ast.atom.Atom argumentNode = args.get(i);
                            if (argumentNode != null) {
                                String nodeClass = argumentNode.getClass().getSimpleName();
                                if (nodeClass.contains("String") || nodeClass.contains("Str")) {
                                    argType = "String";
                                } else if (nodeClass.contains("Integer") || nodeClass.contains("Num") || nodeClass.contains("Int")) {
                                    argType = "Integer";
                                }
                            }

                            // 3️⃣ إدخال المتغير وتحديد نوعه داخل السكوب الحالي للـ SymbolTable
                            try {
                                symbolTable.SymbolTable currentScope = symbolTable.SymbolTableManager.INSTANCE.getPythonTable();

                                if (currentScope != null) {
                                    currentScope.insert(pName);
                                    currentScope.setAttribute(pName, "Type", argType);
                                }
                            } catch (Exception e) {
                                // حماية لضمان استمرار مفسر الأكواد في حال حدوث أي استثناء فرعي
                            }
                        }
                    }
                }


                StatementVisitor statementVisitor = new StatementVisitor();
                statementVisitor.visit(bodyCtx);

            }
            finally {

                // لكن لا تقلق، لقد قمنا بحفظ نسخة منه فوق في pythonScopeAtRender بنجاح!
                symbolTable.SymbolTableManager.INSTANCE.exitPythonLocalScope();
            }
        }

        return functionCall;
    }


@Override
public AtomExpression visitSimpleVar(PythonParser.SimpleVarContext ctx) {
    SimpleVariable simpleVariable = new SimpleVariable(ctx.getStart().getLine());
    Atom atom = atomVisitor.visit(ctx.atom());


    simpleVariable.setVarName(atom.getValue().toString());


    if (atom != null) {
        simpleVariable.node_name = atom.node_name;
    }

    return simpleVariable;
}


    @Override
    public AtomExpression visitListAccess(PythonParser.ListAccessContext ctx) {
        ListAccess listAccess = new ListAccess(ctx.getStart().getLine());
        Atom atom = atomVisitor.visit(ctx.atom());
        listAccess.setVarName(atom.getValue().toString());
        listAccess.setIndex(ctx.NUMBER().getText());


        if (atom != null) {
            listAccess.node_name = atom.node_name;
        }
        return listAccess;
    }
}
