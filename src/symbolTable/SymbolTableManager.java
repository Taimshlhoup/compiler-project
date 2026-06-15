
//
//public enum SymbolTableManager {
//    INSTANCE;
//
//    private final SymbolTable symbolTable;
//
//    SymbolTableManager() {
//        symbolTable = new SymbolTable();
//    }
//
//    public SymbolTable getSymbolTable() {
//        return symbolTable;
//    }
//
//    // أضف هذه الدالة هنا لتتمكن من استدعاء lookup مباشرة
//    public Object lookup(String name) {
//        return symbolTable.lookup(name);
//    }
//}
//package symbolTable;
//
//public enum SymbolTableManager {
//    INSTANCE;
//
//    private final SymbolTable pythonTable;
//    private SymbolTable jinjaTable;
//    private final SymbolTable htmlTable;
//
//    SymbolTableManager() {
//        // بايثون هو السكوب الأساسي (Global)
//        pythonTable = new SymbolTable();
//
//        // الجينجا والـ HTML يرثان من بايثون لضمان مشاركة المتغيرات
//        jinjaTable = new SymbolTable(pythonTable);
//        htmlTable = new SymbolTable(pythonTable);
//    }
//
//    public SymbolTable getPythonTable() {
//        return pythonTable;
//    }
//
//    public SymbolTable getJinjaTable() {
//        return jinjaTable;
//    }
//
//    public SymbolTable getHtmlTable() {
//        return htmlTable;
//    }
//
//    public void setJinjaTable(SymbolTable newTable) {
//        this.jinjaTable = newTable;
//    }
//
//    // Lookup ذكي: يستخدم التكرارية الموجودة في SymbolTable
//    public SymbolEntry lookup(String name, String lang) {
//        return switch (lang.toLowerCase()) {
//            case "python" -> pythonTable.lookup(name);
//            case "jinja" -> jinjaTable.lookup(name); // سيبحث في جينجا ثم بايثون
//            case "html" -> htmlTable.lookup(name);   // سيبحث في HTML ثم بايثون
//            default -> null;
//        };
//    }
//}
//package symbolTable;
//
//public enum SymbolTableManager {
//    INSTANCE;
//
//    // 1. أزلنا كلمة final هنا لنتمكن من تغيير المؤشر عند الدخول والخروج من الدوال
//    private SymbolTable pythonTable;
//    private SymbolTable jinjaTable;
//    private final SymbolTable htmlTable;
//
//    SymbolTableManager() {
//        // بايثون هو السكوب الأساسي العام (Global)
//        pythonTable = new SymbolTable();
//
//        // الجينجا والـ HTML يرثان من بايثون العام لضمان مشاركة المتغيرات
//        // في جافا، سيحتفظان بالإشارة إلى السكوب العام لبايثون كـ parent حتى لو تغير مؤشر pythonTable لاحقاً
//        jinjaTable = new SymbolTable(pythonTable);
//        htmlTable = new SymbolTable(pythonTable);
//    }
//
//    public SymbolTable getPythonTable() {
//        return pythonTable;
//    }
//
//    public SymbolTable getJinjaTable() {
//        return jinjaTable;
//    }
//
//    public SymbolTable getHtmlTable() {
//        return htmlTable;
//    }
//
//    public void setJinjaTable(SymbolTable newTable) {
//        this.jinjaTable = newTable;
//    }
//
//    // 🚀 إضافة دوال التحكم بالسكوب المحلي للـ Jinja ديناميكياً
//    public void enterJinjaLocalScope() {
//        // ننشئ سكوب فرعي للـ Jinja، والأب له هو سكوب الجينجا الحالي
//        jinjaTable = new SymbolTable(jinjaTable);
//    }
//
//    public void exitJinjaLocalScope() {
//        // نعود خطوة للوراء للسكوب الأب عند الخروج من الـ Loop أو الـ Block
//        if (jinjaTable.getParent() != null) {
//            jinjaTable = jinjaTable.getParent();
//        }
//    }
//
//    // ضيف هاد السطر بأول الكلاس مع المتغيرات المشتركة
//    private final java.util.Set<String> declaredVariablesHistory = new java.util.HashSet<>();
//
//    // دالة لتسجيل المتغير عند رؤيته
//    public void registerVariable(String name) {
//        declaredVariablesHistory.add(name);
//    }
//
//    // دالة للفحص إن كان المتغير قد تم تعريفه في أي مكان بالملف
//    public boolean hasVariableExisted(String name) {
//        return declaredVariablesHistory.contains(name);
//    }
//
//    // 🚀 2. إضافة دوال التحكم بالسكوب المحلي لبايثون ديناميكياً
//    public void enterPythonLocalScope() {
//        // ننشئ سكوب فرعي جديد، ويكون الأب (parent) له هو السكوب الحالي
//        pythonTable = new SymbolTable(pythonTable);
//    }
//
//    public void exitPythonLocalScope() {
//        // نعود خطوة للخلف للسكوب الأب عند الخروج من الدالة
//        if (pythonTable.getParent() != null) {
//            pythonTable = pythonTable.getParent();
//        }
//    }
//
//    // Lookup ذكي ومحدث
//    public SymbolEntry lookup(String name, String lang) {
//        return switch (lang.toLowerCase()) {
//            case "python" -> pythonTable.lookup(name);
//            case "jinja" -> jinjaTable.lookup(name); // سيبحث في جينجا ثم بايثون العام
//            case "html" -> htmlTable.lookup(name);   // سيبحث في HTML ثم بايثون العام
//            default -> null;
//        };
//    }
//}

package symbolTable;

public enum SymbolTableManager {
    INSTANCE;

    // 1. أزلنا كلمة final هنا لنتمكن من تغيير المؤشر عند الدخول والخروج من الدوال
    private SymbolTable pythonTable;
    private SymbolTable jinjaTable;
    private final SymbolTable htmlTable;

    // 🚀 إضافة متغيّر متحكم بوضع التتبع الذكي للفروع غير المحققة
    private boolean declarationOnlyMode = false;

//    SymbolTableManager() {
//        // بايثون هو السكوب الأساسي العام (Global)
//        pythonTable = new SymbolTable();
//
//        // الجينجا والـ HTML يرثان من بايثون العام لضمان مشاركة المتغيرات
//        // في جافا، سيحتفظان بالإشارة إلى السكوب العام لبايثون كـ parent حتى لو تغير مؤشر pythonTable لاحقاً
//        jinjaTable = new SymbolTable(pythonTable);
//        htmlTable = new SymbolTable(pythonTable);
//    }

    SymbolTableManager() {
        pythonTable = new SymbolTable();

        // ✅ jinjaTable منفصل عن pythonTable - لا يرث منه
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

    // 🚀 إضافة دوال التحكم بالسكوب المحلي للـ Jinja ديناميكياً
    public void enterJinjaLocalScope() {
        // ننشئ سكوب فرعي للـ Jinja، والأب له هو سكوب الجينجا الحالي
        jinjaTable = new SymbolTable(jinjaTable);
    }

    public void exitJinjaLocalScope() {
        // نعود خطوة للوراء للسكوب الأب عند الخروج من الـ Loop أو الـ Block
        if (jinjaTable.getParent() != null) {
            jinjaTable = jinjaTable.getParent();
        }
    }

    // ضيف هاد السطر بأول الكلاس مع المتغيرات المشتركة
    private final java.util.Set<String> declaredVariablesHistory = new java.util.HashSet<>();

    // دالة لتسجيل المتغير عند رؤيته
    public void registerVariable(String name) {
        declaredVariablesHistory.add(name);
    }

    // دالة للفحص إن كان المتغير قد تم تعريفه في أي مكان بالملف
    public boolean hasVariableExisted(String name) {
        return declaredVariablesHistory.contains(name);
    }

    // 🚀 دوال التحكم بوضعية التسجيل التاريخي فقط (Declaration-Only Mode)
    public void setDeclarationOnlyMode(boolean mode) {
        this.declarationOnlyMode = mode;
    }

    public boolean isDeclarationOnlyMode() {
        return this.declarationOnlyMode;
    }

    // 🚀 2. إضافة دوال التحكم بالسكوب المحلي لبايثون ديناميكياً
    public void enterPythonLocalScope() {
        // ننشئ سكوب فرعي جديد، ويكون الأب (parent) له هو السكوب الحالي
        pythonTable = new SymbolTable(pythonTable);
    }

    public void exitPythonLocalScope() {
        // نعود خطوة للخلف للسكوب الأب عند الخروج من الدالة
        if (pythonTable.getParent() != null) {
            pythonTable = pythonTable.getParent();
        }
    }

    // Lookup ذكي ومحدث
    public SymbolEntry lookup(String name, String lang) {
        return switch (lang.toLowerCase()) {
            case "python" -> pythonTable.lookup(name);
            case "jinja" -> jinjaTable.lookup(name); // سيبحث في جينجا ثم بايثون العام
            case "html" -> htmlTable.lookup(name);   // سيبحث في HTML ثم بايثون العام
            default -> null;
        };
    }
}