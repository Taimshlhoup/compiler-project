package visitor.python;

import antlr.python.PythonParser;
import antlr.python.PythonParserBaseVisitor;
import ast.ElIfStatement;
import ast.Imported;
import ast.Statement;
import ast.compundStmt.CompoundStatement;
import ast.compundStmt.IfStatement;
import ast.compundStmt.ImportStatement;
import ast.condition.Condition;
import ast.functionDef.Decorator;
import ast.functionDef.FunctionDefinition;
import ast.functionDef.FunctionParameters;
import org.antlr.v4.runtime.tree.TerminalNode;
import symbolTable.SymbolTableManager;
import visitor.UniversalPythonVisitor;

import java.util.ArrayList;
import java.util.List;

public class CompoundStatementVisitor extends PythonParserBaseVisitor<CompoundStatement> {
    UniversalPythonVisitor universalVisitor = new UniversalPythonVisitor();


    @Override
    public CompoundStatement visitAtomExpression(PythonParser.AtomExpressionContext ctx) {
        AtomExpressionVisitor atomExpressionVisitor = new AtomExpressionVisitor();
        return atomExpressionVisitor.visit(ctx.atom_expr());
    }

    @Override
    public CompoundStatement visitSimpleExpression(PythonParser.SimpleExpressionContext ctx) {
        SimpleExpressionVisitor simpleExpressionVisitor = new SimpleExpressionVisitor();
        return simpleExpressionVisitor.visit(ctx.simple_expr());
    }

    @Override
    public CompoundStatement visitIfStatement(PythonParser.IfStatementContext ctx) {
        return visit(ctx.if_stmt());
    }


@Override
public CompoundStatement visitIfStatementDef(PythonParser.IfStatementDefContext ctx) {
    IfStatement ifStatement = new IfStatement(ctx.getStart().getLine());
    ConditionVisitor conditionVisitor = new ConditionVisitor();
    StatementVisitor statementVisitor = new StatementVisitor();

    Condition condition = conditionVisitor.visit(ctx.condition(0));
    ifStatement.setCondition(condition);

    // 🧠 الفحص الذكي المطور: تقييم الشرط برمجياً (سواء كان False أو عملية مقارنة ثابتة مثل 3 > 5)
    boolean isConditionTrue = true;
    if (ctx.condition(0) != null) {
        String condText = ctx.condition(0).getText().trim();
        isConditionTrue = evaluateStaticCondition(condText);
    }

    // 🔥 التحكم بالزيارة وتوليد السيمانتك بناءً على حالة الشرط المقيّم
    if (isConditionTrue) {
       // System.out.println(">>> [DEBUG] If condition is True/Dynamic. Visiting body normally.");
        Statement statement = statementVisitor.visit(ctx.statement(0));
        ifStatement.setStatement(statement);
    } else {
       // System.out.println(">>> [DEBUG] If condition is statically False. Visiting body in Declaration-Only Mode.");

        // 1️⃣ تفعيل وضعية التسجيل التاريخي الصامت فقط
        SymbolTableManager.INSTANCE.setDeclarationOnlyMode(true);

        // 2️⃣ إجبار المفسر على زيارة محتوى الـ If لقراءة المتغيرات وتسجيلها تاريخياً (مثل dead_var)
        Statement statement = statementVisitor.visit(ctx.statement(0));
        ifStatement.setStatement(statement);

        // 3️⃣ إلغاء تفعيل الوضعية فور الخروج من البلوك للعودة للوضع الطبيعي
        SymbolTableManager.INSTANCE.setDeclarationOnlyMode(false);
    }

    // معالجة جمل الـ ELIF المتبقية (تترك كما هي في هيكليتك)
    int elifCount = ctx.ELIF().size();
    List<ElIfStatement> elIfStatements = new ArrayList<>();
    for (int i = 0; i < elifCount; i++) {
        ElIfStatement elIfStatement = new ElIfStatement(ctx.ELIF(i).getSymbol().getLine());
        condition = conditionVisitor.visit(ctx.condition(i + 1));
        Statement statement = statementVisitor.visit(ctx.statement(i + 1));
        elIfStatement.setCondition(condition);
        elIfStatement.setStatement(statement);
        elIfStatements.add(elIfStatement);
    }
    ifStatement.setElifStatements(elIfStatements);

    // معالجة جملة الـ ELSE
    if (ctx.ELSE() != null) {
        int elseStmtIndex = ctx.statement().size() - 1;
        Statement statement = statementVisitor.visit(ctx.statement(elseStmtIndex));
        ifStatement.setElseStatement(statement);
    }

    return ifStatement;
}


    private boolean evaluateStaticCondition(String condText) {
        // إزالة الفراغات لتوحيد وفحص النص بسهولة
        condText = condText.replaceAll("\\s+", "");

        if (condText.equalsIgnoreCase("False")) {
            return false;
        }
        if (condText.equalsIgnoreCase("True")) {
            return true;
        }

        try {
            // فحص عمليات المقارنة بين الأرقام الثابتة وتوليد قيمتها منطقياً
            if (condText.contains(">=")) {
                String[] parts = condText.split(">=");
                return Integer.parseInt(parts[0]) >= Integer.parseInt(parts[1]);
            } else if (condText.contains("<=")) {
                String[] parts = condText.split("<=");
                return Integer.parseInt(parts[0]) <= Integer.parseInt(parts[1]);
            } else if (condText.contains("==")) {
                String[] parts = condText.split("==");
                return Integer.parseInt(parts[0]) == Integer.parseInt(parts[1]);
            } else if (condText.contains("!=")) {
                String[] parts = condText.split("!=");
                return Integer.parseInt(parts[0]) != Integer.parseInt(parts[1]);
            } else if (condText.contains(">")) {
                String[] parts = condText.split(">");
                return Integer.parseInt(parts[0]) > Integer.parseInt(parts[1]);
            } else if (condText.contains("<")) {
                String[] parts = condText.split("<");
                return Integer.parseInt(parts[0]) < Integer.parseInt(parts[1]);
            }
        } catch (Exception e) {
            // إذا كان الشرط ديناميكياً ويحتوي على متغيرات (مثل x > 5)، نعتبره True افتراضياً أثناء الفحص السيمانتيكي
            return true;
        }

        return true; // الافتراضي لأي شرط معقد آخر
    }

    @Override
    public CompoundStatement visitAssignmentStatement(PythonParser.AssignmentStatementContext ctx) {
        AssignmentStatementVisitor assignmentStatementVisitor = new AssignmentStatementVisitor();
        return assignmentStatementVisitor.visit(ctx.assign_stmt());
    }


    @Override
    public CompoundStatement visitFunctionDefinition(PythonParser.FunctionDefinitionContext ctx) {
        return visit(ctx.func_def());
    }


@Override
public CompoundStatement visitFunctionDefDef(PythonParser.FunctionDefDefContext ctx) {
    UniversalPythonVisitor universalVisitor = new UniversalPythonVisitor();
    FunctionDefinition functionDefinition = new FunctionDefinition(ctx.getStart().getLine());

    if (ctx.dec() != null) {
        Decorator decorator = (Decorator) universalVisitor.visit(ctx.dec());
        functionDefinition.setDecorator(decorator);
    }

    String functionName = ctx.NAME().getText();
    functionDefinition.setFunctionName(functionName);

    // تجميع أسماء البارامترات لنقلها للسجل
    List<String> paramNames = new ArrayList<>();
    if (ctx.parameters() != null) {
        String fullParamsText = ctx.parameters().getText().replace("(", "").replace(")", "");
        if (!fullParamsText.isEmpty()) {
            for (String p : fullParamsText.split(",")) {
                paramNames.add(p.trim());
            }
        }
    }


    AtomExpressionVisitor.functionRegistry.put(
            functionName,
            new AtomExpressionVisitor.FunctionMetadata(paramNames, ctx.statement())
    );

    SymbolTableManager.INSTANCE.enterPythonLocalScope();

    try {
        FunctionParameters functionParameters = (FunctionParameters) universalVisitor.visit(ctx.parameters());
        functionDefinition.setFunctionParameters(functionParameters);

        Statement statement = new StatementVisitor().visit(ctx.statement());
        functionDefinition.setFunctionBody(statement);

    } finally {
        SymbolTableManager.INSTANCE.exitPythonLocalScope();
    }

    return functionDefinition;
}

    @Override
    public CompoundStatement visitReturnStatement(PythonParser.ReturnStatementContext ctx) {
        ReturnStatementVisitor returnStatementVisitor = new ReturnStatementVisitor();
        return returnStatementVisitor.visit(ctx.return_stmt());
    }

    @Override
    public CompoundStatement visitImportStatement(PythonParser.ImportStatementContext ctx) {
        return visit(ctx.import_from());
    }

    @Override
    public ImportStatement visitImportFromDef(PythonParser.ImportFromDefContext ctx) {
        ImportStatement importStatement = new ImportStatement(ctx.getStart().getLine());
        StringBuilder moduleBuilder = new StringBuilder();
        List<TerminalNode> moduleNameTokens = ctx.NAME();
        if (!moduleNameTokens.isEmpty()) {
            moduleBuilder.append(moduleNameTokens.getFirst().getText());

            for (int i = 1; i < moduleNameTokens.size() - ctx.imptd().size(); i++) {
                moduleBuilder.append(".").append(moduleNameTokens.get(i).getText());
            }
        }

        String module = moduleBuilder.toString();

        List<Imported> importedList = new ArrayList<>();

        for (PythonParser.ImptdContext imported : ctx.imptd()) {
            importedList.add((Imported) universalVisitor.visit(imported));
        }
        importStatement.setImportedList(importedList);
        importStatement.setModule(module);

        return importStatement;
    }

    @Override
    public CompoundStatement visitGlobalStatement(PythonParser.GlobalStatementContext ctx) {
        return (CompoundStatement) universalVisitor.visit(ctx.global_stmt());
    }
    // ======= إضافة الجسر المفقود لحلقات الـ For =======

    @Override
    public CompoundStatement visitSimpleForLoop(PythonParser.SimpleForLoopContext ctx) {
        // استدعاء الـ Visitor الخاص بالحلقات وتمرير السيطرة له
        ForLoopVisitor forLoopVisitor = new ForLoopVisitor();
        return forLoopVisitor.visit(ctx);
    }

    @Override
    public CompoundStatement visitComplexForLoop(PythonParser.ComplexForLoopContext ctx) {
        // استدعاء الـ Visitor الخاص بالحلقات وتمرير السيطرة له
        ForLoopVisitor forLoopVisitor = new ForLoopVisitor();
        return forLoopVisitor.visit(ctx);
    }
}
