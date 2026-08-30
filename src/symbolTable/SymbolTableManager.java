

package symbolTable;

public enum SymbolTableManager {
    INSTANCE;


    private SymbolTable pythonTable;
    private SymbolTable jinjaTable;
    private final SymbolTable htmlTable;


    private boolean declarationOnlyMode = false;



    SymbolTableManager() {
        pythonTable = new SymbolTable();


        jinjaTable = new SymbolTable();
        htmlTable = new SymbolTable(pythonTable);
    }

    public SymbolTable getPythonTable() {
        return pythonTable;
    }

    public SymbolTable getJinjaTable() {
        return jinjaTable;
    }

    public SymbolTable getHtmlTable() {
        return htmlTable;
    }

    public void setJinjaTable(SymbolTable newTable) {
        this.jinjaTable = newTable;
    }


    public void enterJinjaLocalScope() {

        jinjaTable = new SymbolTable(jinjaTable);
    }

    public void exitJinjaLocalScope() {

        if (jinjaTable.getParent() != null) {
            jinjaTable = jinjaTable.getParent();
        }
    }


    private final java.util.Set<String> declaredVariablesHistory = new java.util.HashSet<>();


    public void registerVariable(String name) {
        declaredVariablesHistory.add(name);
    }


    public boolean hasVariableExisted(String name) {
        return declaredVariablesHistory.contains(name);
    }


    public void setDeclarationOnlyMode(boolean mode) {
        this.declarationOnlyMode = mode;
    }

    public boolean isDeclarationOnlyMode() {
        return this.declarationOnlyMode;
    }


    public void enterPythonLocalScope() {

        pythonTable = new SymbolTable(pythonTable);
    }

    public void exitPythonLocalScope() {

        if (pythonTable.getParent() != null) {
            pythonTable = pythonTable.getParent();
        }
    }

    // Lookup ذكي ومحدث
    public SymbolEntry lookup(String name, String lang) {
        return switch (lang.toLowerCase()) {
            case "python" -> pythonTable.lookup(name);
            case "jinja" -> jinjaTable.lookup(name);
            case "html" -> htmlTable.lookup(name);
            default -> null;
        };
    }
}