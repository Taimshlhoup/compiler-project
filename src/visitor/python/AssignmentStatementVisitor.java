
package visitor.python;

import antlr.python.PythonParser;
import antlr.python.PythonParserBaseVisitor;
import ast.TemplateLiteral;
import ast.arithmeticExpr.ArithmeticExpression;
import ast.assignStmt.*;
import ast.compundStmt.PythonExpression;
import ast.condition.Condition;
import symbolTable.SymbolTable;
import symbolTable.SymbolTableManager;

public class AssignmentStatementVisitor extends PythonParserBaseVisitor<AssignmentStatement> {
    private final PythonExpressionVisitor pythonExpressionVisitor = new PythonExpressionVisitor();
    private final SymbolTable sb = SymbolTableManager.INSTANCE.getPythonTable();



    @Override
    public AssignmentStatement visitComparisonAssignStmt(PythonParser.ComparisonAssignStmtContext ctx) {
        PythonExpression var = pythonExpressionVisitor.visit(ctx.python_expr());
        Condition condition = new ConditionVisitor().visit(ctx.condition());

        validateAndInsert(var.symbolTablePrint(), condition.node_name, condition.symbolTablePrint(), ctx.getStart().getLine());

        ComparisonAssignmentStmt stmt = new ComparisonAssignmentStmt(ctx.getStart().getLine());
        stmt.setVar(var);
        stmt.setValue(condition);
        return stmt;
    }


    private void validateAndInsert(String name, String newType, String value, int line) {
        // تسجيل المتغير تاريخياً دائماً بمجرد رؤيته في أي مكان بالكود
        SymbolTableManager.INSTANCE.registerVariable(name);

        // 🚨 حماية: إذا كنا في فرع غير محقق، نكتفي بالتسجيل التاريخي ونخرج فوراً دون تعديل السكوب النشط
        if (SymbolTableManager.INSTANCE.isDeclarationOnlyMode()) {
            return;
        }

        if (!sb.existsInCurrentScope(name)) {
            sb.insert(name);
            sb.setAttribute(name, "Type", newType);
            sb.setAttribute(name, "Value", value);
        } else {
            String oldType = (String) sb.getAttribute(name, "Type");
            if (oldType != null && !oldType.equals("Pending") && !oldType.equals(newType)) {
                System.err.println("Semantic Error: Type Mismatch at line " + line + ". Cannot assign " + newType + " to " + oldType);
            } else {
                sb.setAttribute(name, "Type", newType);
                sb.setAttribute(name, "Value", value);
            }
        }
    }

    @Override
    public AssignmentStatement visitArithmeticAssignStmt(PythonParser.ArithmeticAssignStmtContext ctx) {
        String varName = ctx.python_expr().getText().trim();

        // 🚨 حماية الإدخال الأولي الفوري في حالة الـ declarationOnlyMode
        if (SymbolTableManager.INSTANCE.isDeclarationOnlyMode()) {
            SymbolTableManager.INSTANCE.registerVariable(varName);
        } else if (!sb.existsInCurrentScope(varName)) {
            sb.insert(varName);
            sb.setAttribute(varName, "Type", "Pending");
        }

        PythonExpression varNode = pythonExpressionVisitor.visit(ctx.python_expr());
        ArithmeticExpression valueNode = new ArithmeticExpressionVisitor().visit(ctx.arithmetic_expr());

        validateAndInsert(varName, valueNode.node_name, valueNode.symbolTablePrint(), ctx.getStart().getLine());

        ArithmeticAssignStatement stmt = new ArithmeticAssignStatement(ctx.getStart().getLine());
        stmt.setVar(varNode);
        stmt.setValue(valueNode);
        return stmt;
    }


    @Override
    public AssignmentStatement visitPythonExpressionAssignStmt(PythonParser.PythonExpressionAssignStmtContext ctx) {
        // 1. استخراج اسم المتغير نصياً (بدون زيارة الـ Visitor لمنع رسالة Undefined)
        String varName = ctx.python_expr(0).getText().trim();

        // 2. زيارة الطرف الأيمن أولاً لجلب القيمة والنوع
        PythonExpression valueNode = pythonExpressionVisitor.visit(ctx.python_expr(1));
        String newType = valueNode.node_name;
        String valueStr = valueNode.symbolTablePrint();

        // 3. استخدام الدالة الموحدة للإدخال أو فحص النوع (هنا سيتم الإدخال لأول مرة بصمت)
        validateAndInsert(varName, newType, valueStr, ctx.getStart().getLine());
        sb.setAttribute(varName, "Node", valueNode);
        // 4. الآن يمكن زيارة الطرف الأيسر لبناء الـ AST فقط
        PythonExpression varNode = pythonExpressionVisitor.visit(ctx.python_expr(0));

        PythonExpressionAssignStatement stmt = new PythonExpressionAssignStatement(ctx.getStart().getLine());
        stmt.setVar(varNode);
        stmt.setValue(valueNode);
        return stmt;
    }


}
