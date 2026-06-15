//package symbolTable;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class SymbolTable {
//
//    private HashMap<String, SymbolEntry> table;
//
//    public SymbolTable() {
//        allocate();   // create empty table
//    }
//
//    // allocate: create empty table
//    public void allocate() {
//        table = new HashMap<>();
//    }
//
//    // free: clear table
//    public void free() {
//        table.clear();
//    }
//
//    // lookup: search for a name
//    public SymbolEntry lookup(String name) {
//        return table.get(name);
//    }
//
//    // insert: add new entry
//    public SymbolEntry insert(String name) {
//        if (table.containsKey(name)) {
//            System.out.println("Error: symbol '" + name + "' already defined!");
//            return null;
//        }
//
//        SymbolEntry entry = new SymbolEntry(name);
//        table.put(name, entry);
//        return entry;
//    }
//
//    // set_attribute: add/update attribute of entry
//    public void setAttribute(String name, String key, Object value) {
//        SymbolEntry entry = lookup(name);
//        if (entry == null) {
//            entry = new SymbolEntry(name);
//            table.put(name, entry);
//        }
//        entry.setAttribute(key, value);
//    }
//
//    // get_attribute: retrieve attribute of entry
//    public Object getAttribute(String name, String key) {
//        SymbolEntry entry = lookup(name);
//        if (entry == null) {
//            System.out.println("Error: symbol '" + name + "' not defined!");
//            return null;
//        }
//        return entry.getAttribute(key);
//    }
//    public boolean existsInCurrentScope(String name) {
//        return table.containsKey(name);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("\n=================== SYMBOL TABLE ===================\n");
//        stringBuilder.append(String.format("%-20s | %-20s | %s\n", "Symbol Name", "Type", "Attributes"));
//        stringBuilder.append("----------------------------------------------------\n");
//
//        for (Map.Entry<String, SymbolEntry> entry : table.entrySet()) {
//            String symbolName = entry.getKey();
//            SymbolEntry symbolEntry = entry.getValue();
//
//
//            Object type = symbolEntry.getAttribute("Type");
//            String typeStr = (type != null) ? type.toString() : "null";
//
//
//            Object value = symbolEntry.getAttribute("Value");
//            String valueStr = (value != null) ? value.toString() : "null";
//
//            stringBuilder.append(String.format("%-20s | %-20s | %s\n",
//                    symbolName, typeStr, valueStr));
//        }
//
//        return stringBuilder.toString();
//    }
//
//}
//package symbolTable;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class SymbolTable {
//
//
//    private HashMap<String, SymbolEntry> table;
//    private SymbolTable parent; // الربط مع السكوب الأب
//
//    public SymbolTable() {
//        this.parent = null;
//        allocate();
//    }
//
//    // Constructor لإنشاء سكوب فرعي مرتبط بأب (مثل سكوب داخل Loop)
//    public SymbolTable(SymbolTable parent) {
//        this.parent = parent;
//        allocate();
//    }
//
//    public void allocate() {
//        table = new HashMap<>();
//    }
//
//    public void free() {
//        table.clear();
//    }
//
//    // Lookup مطور: إذا لم يجد الرمز هنا، يبحث في الأب تلقائياً
//    public SymbolEntry lookup(String name) {
//        SymbolEntry entry = table.get(name);
//        if (entry == null && parent != null) {
//            return parent.lookup(name);
//        }
//        return entry;
//    }
//
//    public SymbolEntry insert(String name) {
//        if (table.containsKey(name)) {
//            System.err.println("Semantic Error: symbol '" + name + "' already defined in current scope!");
//            return null;
//        }
//        SymbolEntry entry = new SymbolEntry(name);
//        table.put(name, entry);
//        SymbolTableManager.INSTANCE.registerVariable(name);
//        return entry;
//    }
//
////    public void setAttribute(String name, String key, Object value) {
////        SymbolEntry entry = lookup(name); // يبحث في السلسلة كاملة
////        if (entry == null) {
////            entry = new SymbolEntry(name);
////            table.put(name, entry);
////        }
////        entry.setAttribute(key, value);
////    }
//// 1. جيتر ضروري للمدير (Manager) لكي يستطيع التراجع للأب عند إغلاق السكوب
//public SymbolTable getParent() {
//    return this.parent;
//}
//
//    // 2. تحديث الدالة لمنع تعديل المتغيرات الخارجية (Shadowing Bug)
//    public void setAttribute(String name, String key, Object value) {
//        SymbolEntry entry;
//
//        // 🧠 القاعدة: إذا كنا نحدد "النوع" أو "القيمة" (إسناد جديد)،
//        // يجب أن يتم ذلك في السكوب الحالي حصراً لمنع تخريب السكوب الأب.
//        if (key.equals("Type") || key.equals("Value")) {
//            entry = table.get(name); // نبحث في الجدول الحالي فقط (وليس lookup)
//            if (entry == null) {
//                entry = new SymbolEntry(name);
//                table.put(name, entry);
//            }
//        } else {
//            // للخصائص الأخرى الفرعية، نبحث في شجرة السكوبات كاملة
//            entry = lookup(name);
//            if (entry == null) {
//                entry = new SymbolEntry(name);
//                table.put(name, entry);
//            }
//        }
//        entry.setAttribute(key, value);
//    }
//
//    public Object getAttribute(String name, String key) {
//        SymbolEntry entry = lookup(name);
//        if (entry == null) return null;
//        return entry.getAttribute(key);
//    }
//
//    private HashMap<String, SymbolEntry> backupTable;
//
//    public boolean existsInCurrentScope(String name) {
//        return table.containsKey(name);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("\n=================== SYMBOL TABLE ===================\n");
//        sb.append(String.format("%-20s | %-20s | %s\n", "Symbol Name", "Type", "Attributes"));
//        sb.append("----------------------------------------------------\n");
//        for (Map.Entry<String, SymbolEntry> entry : table.entrySet()) {
//            SymbolEntry symbolEntry = entry.getValue();
//            Object type = symbolEntry.getAttribute("Type");
//            Object value = symbolEntry.getAttribute("Value");
//            sb.append(String.format("%-20s | %-20s | %s\n",
//                    entry.getKey(),
//                    (type != null ? type : "null"),
//                    (value != null ? value : "null")));
//        }
//        return sb.toString();
//    }
//}

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
