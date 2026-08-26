
package symbolTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SymbolTable {

    private HashMap<String, SymbolEntry> table;
    private SymbolTable parent;
    private Set<String> outOfScopeVars = new HashSet<>();

    public SymbolTable() {
        this.parent = null;
        allocate();
    }

    public SymbolTable(SymbolTable parent) {
        this.parent = parent;
        allocate();
    }

    public void allocate() {
        table = new HashMap<>();
    }

    public void free() {
        table.clear();
    }

    public SymbolEntry lookup(String name) {
        SymbolEntry entry = table.get(name);
        if (entry == null && parent != null) {
            return parent.lookup(name);
        }
        return entry;
    }

    public SymbolEntry insert(String name) {
        if (table.containsKey(name)) {
            System.err.println("Semantic Error: symbol '" + name + "' already defined in current scope!");
            return null;
        }
        SymbolEntry entry = new SymbolEntry(name);
        table.put(name, entry);
        SymbolTableManager.INSTANCE.registerVariable(name);
        return entry;
    }

    public SymbolTable getParent() {
        return this.parent;
    }

    public void setAttribute(String name, String key, Object value) {
        SymbolEntry entry;
        if (key.equals("Type") || key.equals("Value")) {
            entry = table.get(name);
            if (entry == null) {
                entry = new SymbolEntry(name);
                table.put(name, entry);
            }
        } else {
            entry = lookup(name);
            if (entry == null) {
                entry = new SymbolEntry(name);
                table.put(name, entry);
            }
        }
        entry.setAttribute(key, value);
    }

    public Object getAttribute(String name, String key) {
        SymbolEntry entry = lookup(name);
        if (entry == null) return null;
        return entry.getAttribute(key);
    }

    public boolean existsInCurrentScope(String name) {
        return table.containsKey(name);
    }

    // ✅ إضافة outOfScope methods
    public void addOutOfScope(String varName) {
        outOfScopeVars.add(varName);
    }

    public boolean isOutOfScope(String varName) {
        return outOfScopeVars.contains(varName);
    }

    public Set<String> getLocalVarNames() {
        return table.keySet();
    }

    private HashMap<String, SymbolEntry> backupTable;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=================== SYMBOL TABLE ===================\n");
        sb.append(String.format("%-20s | %-20s | %s\n", "Symbol Name", "Type", "Attributes"));
        sb.append("----------------------------------------------------\n");
        for (Map.Entry<String, SymbolEntry> entry : table.entrySet()) {
            SymbolEntry symbolEntry = entry.getValue();
            Object type = symbolEntry.getAttribute("Type");
            Object value = symbolEntry.getAttribute("Value");
            sb.append(String.format("%-20s | %-20s | %s\n",
                    entry.getKey(),
                    (type != null ? type : "null"),
                    (value != null ? value : "null")));
        }
        return sb.toString();
    }
}
