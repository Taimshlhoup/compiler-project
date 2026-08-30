
package app;


import antlr.html.HtmlLexer;
import antlr.html.HtmlParser;
import antlr.python.PythonLexer;
import antlr.python.PythonParser;
import listener.CustomErrorListener;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import visitor.python.ProgramVisitor;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.List;

import visitor.python.AtomExpressionVisitor;
public class App {

    private enum InteractivityMode { LOCAL_STORAGE, SERVER_REGENERATION }
    private static final InteractivityMode MODE = InteractivityMode.LOCAL_STORAGE;


    private static final String BASE_DIR =
            (MODE == InteractivityMode.LOCAL_STORAGE) ? "samples/Testing7" : "samples/Testing8";
    private static JTabbedPane tabbedPane = new JTabbedPane();
    private static JFrame mainFrame = null;
    private static java.util.List<String> semanticErrors = new java.util.ArrayList<>();
    public static void main(String[] args) throws Exception {

        java.io.PrintStream originalErr = System.err;
        java.io.ByteArrayOutputStream errBuffer = new java.io.ByteArrayOutputStream();
        System.setErr(new java.io.PrintStream(errBuffer) {
            @Override
            public void println(String x) {
                if (x != null && x.startsWith("Semantic Error")) {
                    semanticErrors.add(x);
                }
                originalErr.println(x);
            }
        });

        try {
            SwingUtilities.invokeAndWait(() -> {
                mainFrame = new JFrame("AST Viewer");
                mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                mainFrame.setSize(1000, 700);
                mainFrame.setLocationRelativeTo(null);
                tabbedPane.setBackground(new Color(45, 45, 48));
                tabbedPane.setForeground(Color.WHITE);
                tabbedPane.setFont(new Font("Arial", Font.BOLD, 13));
                mainFrame.add(tabbedPane);
                mainFrame.setVisible(true);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            java.nio.file.Files.createDirectories(
                    java.nio.file.Paths.get("compiler_output"));
            java.nio.file.Files.writeString(
                    java.nio.file.Paths.get("compiler_output/generation_log.txt"), "");
        } catch (Exception e) {
            System.err.println("Error clearing log: " + e.getMessage());
        }

        processFile(BASE_DIR + "/app.py");
        processFile(BASE_DIR + "/templates/index.jinja");
        processFile(BASE_DIR + "/templates/add_product.jinja");
        if (MODE == InteractivityMode.LOCAL_STORAGE) {
            processFile(BASE_DIR + "/templates/edit_product.jinja");
        }
        processFile(BASE_DIR + "/templates/detail.jinja");

        java.nio.file.WatchService watchService = null;

        if (MODE == InteractivityMode.LOCAL_STORAGE) {
            watchService = java.nio.file.FileSystems.getDefault().newWatchService();
            java.nio.file.Path watchPath = java.nio.file.Paths.get(BASE_DIR);
            watchPath.register(watchService, java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY);
            System.out.println("👀 Watching for changes in " + BASE_DIR + "...");
        }
        if (MODE == InteractivityMode.SERVER_REGENERATION) {
            startAddProductServer();
        }
        try {
            java.nio.file.Path cssSource = java.nio.file.Paths.get(BASE_DIR + "/style.css");
            if (java.nio.file.Files.exists(cssSource)) {
                java.nio.file.Files.createDirectories(java.nio.file.Paths.get("output/static"));
                java.nio.file.Files.copy(
                        cssSource,
                        java.nio.file.Paths.get("output/static/style.css"),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                );
                System.out.println("✅ Copied file: output/static/style.css");
                java.nio.file.Files.createDirectories(java.nio.file.Paths.get("output/templates"));
                java.nio.file.Files.copy(cssSource, java.nio.file.Paths.get("output/templates/style.css"),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                System.out.println("✅ Copied file: output/templates/style.css");
            }
        } catch (Exception e) {
            System.err.println("Error copying style.css: " + e.getMessage());
        }

        try {
            java.nio.file.Files.createDirectories(
                    java.nio.file.Paths.get("compiler_output"));
            StringBuilder report = new StringBuilder();
            report.append("=== Semantic Analysis Report ===\n\n");
            if (semanticErrors.isEmpty()) {
                report.append("No semantic errors found.\n");
            } else {
                for (String error : semanticErrors) {
                    report.append(error).append("\n");
                }
            }
            java.nio.file.Files.writeString(
                    java.nio.file.Paths.get("compiler_output/semantic_report.txt"),
                    report.toString()
            );
            System.out.println("✅ Generated file: compiler_output/semantic_report.txt");
        } catch (Exception e) {
            System.err.println("Error writing semantic report: " + e.getMessage());
        }
        if (MODE == InteractivityMode.LOCAL_STORAGE) {
            while (true) {
                java.nio.file.WatchKey key = watchService.take();
                for (java.nio.file.WatchEvent<?> event : key.pollEvents()) {
                    System.out.println("🔄 Change detected! Regenerating...");
                    processFile(BASE_DIR + "/app.py");
                    processFile(BASE_DIR + "/templates/index.jinja");
                    processFile(BASE_DIR + "/templates/add_product.jinja");
                    processFile(BASE_DIR + "/templates/edit_product.jinja");
                    processFile(BASE_DIR + "/templates/detail.jinja");
                    System.out.println("✅ Regeneration complete!");
                }
                key.reset();
            }
        } else {

            System.out.println("Server mode active. Press Ctrl+C to stop.");
            Thread.currentThread().join(); // يبقي البرنامج شغالًا للأبد بدون busy-loop
        }


    }

private static void processFile(String fileName) {
    try {
        if (fileName.endsWith(".py")) {
            System.out.println("\n--- [Python Analysis] Processing: " + fileName + " ---");

            PythonLexer lexer = new PythonLexer(CharStreams.fromFileName(fileName));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            PythonParser parser = new PythonParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new CustomErrorListener());

            ParseTree tree = parser.prog();
            ProgramVisitor visitor = new ProgramVisitor();
                ast.Program program = (ast.Program) visitor.visit(tree);

            System.out.println("\n--- Generated Python Code ---");
            if (program != null) {
                String generatedCode = program.generateCode();


                symbolTable.SymbolTable currentPyScope = symbolTable.SymbolTableManager.INSTANCE.getPythonTable();
                boolean hasProduct1 = currentPyScope != null && currentPyScope.lookup("product1_name") != null;
                boolean hasProduct2 = currentPyScope != null && currentPyScope.lookup("product2_name") != null;
                if(hasProduct1 && hasProduct2){
                    generatedCode = generatedCode.replace(
                        "@app.route('/')",
                        "products.append({'name': product1_name, 'price': product1_price})\n" +
                                "products.append({'name': product2_name, 'price': product2_price})\n\n" +
                                "@app.route('/')"
                );}
                System.out.println(generatedCode);

                // ✅ اكتب الكود في ملف حقيقي
                try {
                    java.nio.file.Files.createDirectories(java.nio.file.Paths.get("output"));
                    java.nio.file.Files.writeString(java.nio.file.Paths.get("output/app.py"), generatedCode);
                    System.out.println("✅ Generated file: output/app.py");
                } catch (Exception e) {
                    System.err.println("Error writing file: " + e.getMessage());
                }
            }
            System.out.println("\n--- Symbol Table after Python ---");
            System.out.println(symbolTable.SymbolTableManager.INSTANCE.getPythonTable());


            if (program != null) {
                showASTWindow(program.toString(), " Python AST - " + fileName);


                try {
                    java.nio.file.Files.createDirectories(
                            java.nio.file.Paths.get("compiler_output"));
                    java.nio.file.Files.writeString(
                            java.nio.file.Paths.get("compiler_output/ast_python.json"),
                            "{\n  \"ast\": \"" +
                                    program.toString().replace("\"", "'").replace("\n", "\\n") +
                                    "\"\n}"
                    );
                    System.out.println("✅ Generated file: compiler_output/ast_python.json");
                } catch (Exception e) {
                    System.err.println("Error writing AST: " + e.getMessage());
                }
            }

        } else if (fileName.endsWith(".html") || fileName.endsWith(".j2") || fileName.endsWith(".jinja")) {
            System.out.println("DEBUG: Starting Jinja processing for: " + fileName);
            System.out.println("\n--- [Jinja/HTML Analysis] Processing: " + fileName + " ---");

            HtmlLexer lexer = new HtmlLexer(CharStreams.fromFileName(fileName));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            HtmlParser parser = new HtmlParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new CustomErrorListener());

            ParseTree tree = parser.html_content();
            visitor.html.HtmlContentVisitor visitor = new visitor.html.HtmlContentVisitor();
            ast.HtmlContent htmlContent = (ast.HtmlContent) visitor.visit(tree);


            if (htmlContent != null) {
                String generatedHtml = htmlContent.generateCode();


                for (java.util.Map.Entry<String, String> entry :
                        AtomExpressionVisitor.renderContext.entrySet()) {
                    generatedHtml = generatedHtml.replace(
                            "{{ " + entry.getKey() + " }}",
                            entry.getValue()
                    );
                }

                generatedHtml = generatedHtml
                        .replace("href=\"/\"", "href=\"index.html\"")
                        .replace("href=\"/add\"", "href=\"add_product.html\"")
                        .replace("href=\"/detail\"", "href=\"detail.html\"")
                        .replace("href=\"/delete\"", "href=\"delete.html\"")
                        .replace("href=\"/edit\"", "href=\"edit_product.html\"");

                System.out.println("\n--- Generated HTML Code ---");
                System.out.println(generatedHtml);

                String outputFileNameForScript = java.nio.file.Paths.get(fileName)
                        .getFileName().toString()
                        .replace(".jinja", ".html")
                        .replace(".j2", ".html");

                if(MODE == InteractivityMode.LOCAL_STORAGE){
                    if (outputFileNameForScript.equals("index.html")) {
                        StringBuilder defaultProductsJs = new StringBuilder("[");
                        symbolTable.SymbolEntry productsEntry = symbolTable.SymbolTableManager.INSTANCE
                                .getPythonTable().lookup("products");
                        Object nodeObj = productsEntry != null ? productsEntry.getAttribute("Node") : null;

                        if (nodeObj instanceof ast.complexExp.ListLiteral) {
                            ast.complexExp.ListLiteral listLiteral = (ast.complexExp.ListLiteral) nodeObj;
                            boolean first = true;
                            for (ast.ASTNode item : listLiteral.getListItems()) {
                                if (item instanceof ast.complexExp.DictionaryLiteral) {
                                    ast.complexExp.DictionaryLiteral dict = (ast.complexExp.DictionaryLiteral) item;
                                    String pName = "", pPrice = "0";
                                    for (ast.keyValue.KeyValue kv : dict.getKeyValues()) {
                                        String key = kv.getKey().getValue().toString().replaceAll("^\"|\"$", "");
                                        String val = kv.getValueCode().replaceAll("^\"|\"$", "");
                                        if (key.equals("name")) pName = val;
                                        if (key.equals("price")) pPrice = val;
                                    }
                                    if (!first) defaultProductsJs.append(",");
                                    defaultProductsJs.append("{name:\"").append(pName).append("\",price:").append(pPrice).append("}");
                                    first = false;
                                }
                            }
                        }
                        defaultProductsJs.append("]");

                        generatedHtml += "\n<script>\n" +
                                "const defaultProducts = " + defaultProductsJs.toString() + ";\n" +
                                "let products = JSON.parse(localStorage.getItem('products'));\n" +
                                "if (!products) { products = defaultProducts; localStorage.setItem('products', JSON.stringify(products)); }\n" +
                                "const c = document.getElementById('productList');\n" +
                                "products.forEach((p,i)=>{ c.innerHTML += " +
                                "'<div class=\"product-card\">' +" +
                                "'<h3>'+p.name+'</h3>' +" +
                                "'<p class=\"price\">$'+p.price+'</p>' +" +
                                "'<div class=\"card-actions\">' +" +
                                "'<a href=\"detail.html?id='+i+'\">View</a>' +" +
                                "'<a class=\"delete-link\" href=\"#\" onclick=\"del('+i+')\">Delete</a>' +" +
                                "'</div></div>'; });\n" +
                                "function del(i){ products.splice(i,1); localStorage.setItem('products',JSON.stringify(products)); location.reload(); }\n" +
                                "</script>";
                    }

                if (outputFileNameForScript.equals("add_product.html")) {
                    generatedHtml += "\n<script>\n" +
                            "function addProduct(){\n" +
                            "  const name=document.getElementById('name').value;\n" +
                            "  const price=document.getElementById('price').value;\n" +
                            "  let products=JSON.parse(localStorage.getItem('products'))||[];\n" +
                            "  products.push({name:name,price:price});\n" +
                            "  localStorage.setItem('products',JSON.stringify(products));\n" +
                            "  window.location.href='index.html';\n" +
                            "}\n" +
                            "</script>";
                }

                if (outputFileNameForScript.equals("detail.html")) {
                    generatedHtml += "\n<script>\n" +
                            "const params=new URLSearchParams(window.location.search);\n" +
                            "const id=params.get('id');\n" +
                            "const products=JSON.parse(localStorage.getItem('products'))||[];\n" +
                            "const p=products[id];\n" +
                            "if(p){ document.getElementById('productDetail').innerHTML=" +
                            "'<p>Name: '+p.name+'</p><p>Price: '+p.price+'</p>'; }\n" +
                            "</script>";
                }

                }
                if (MODE == InteractivityMode.SERVER_REGENERATION
                        && outputFileNameForScript.equals("index.html")) {
                    generatedHtml += "\n<script>\n" +
                            "function deleteProduct(i){\n" +
                            "  fetch('http://localhost:8080/delete?index='+i)\n" +
                            "    .then(()=>{ window.location.href='index.html'; })\n" +
                            "    .catch(()=>{ alert('تأكد أن الجافا شغالة'); });\n" +
                            "}\n" +
                            "</script>";
                }
                if (MODE == InteractivityMode.SERVER_REGENERATION
                        && outputFileNameForScript.equals("add_product.html")) {
                    generatedHtml += "\n<script>\n" +
                            "function addProduct(){\n" +
                            "  const name=document.getElementById('name').value;\n" +
                            "  const price=document.getElementById('price').value;\n" +
                            "  fetch('http://localhost:8080/add?name='+encodeURIComponent(name)+'&price='+price)\n" +
                            "    .then(()=>{ window.location.href='index.html'; })\n" +
                            "    .catch(()=>{ alert('تأكد أن الجافا شغالة'); });\n" +
                            "}\n" +
                            "</script>";
                }
                try {

                    String outputFileName = java.nio.file.Paths.get(fileName)
                            .getFileName().toString()
                            .replace(".jinja", ".html")
                            .replace(".j2", ".html");

                    java.nio.file.Files.createDirectories(java.nio.file.Paths.get("output/templates"));
                    java.nio.file.Files.writeString(
                            java.nio.file.Paths.get("output/templates/" + outputFileName),
                            generatedHtml
                    );
                    System.out.println("✅ Generated file: output/" + outputFileName);


                    if (MODE == InteractivityMode.SERVER_REGENERATION && outputFileName.equals("index.html")) {
                        symbolTable.SymbolEntry productsEntry = symbolTable.SymbolTableManager.INSTANCE
                                .getPythonTable().lookup("products");
                        Object nodeObj = productsEntry != null ? productsEntry.getAttribute("Node") : null;
                        if (nodeObj instanceof ast.complexExp.ListLiteral) {
                            ast.complexExp.ListLiteral listLiteral = (ast.complexExp.ListLiteral) nodeObj;
                            int idx = 0;
                            for (ast.ASTNode item : listLiteral.getListItems()) {
                                if (item instanceof ast.complexExp.DictionaryLiteral) {
                                    ast.complexExp.DictionaryLiteral dict = (ast.complexExp.DictionaryLiteral) item;
                                    String pName = "", pPrice = "";
                                    for (ast.keyValue.KeyValue kv : dict.getKeyValues()) {
                                        String key = kv.getKey().getValue().toString().replaceAll("^\"|\"$", "");
                                        String val = kv.getValueCode().replaceAll("^\"|\"$", "");
                                        if (key.equals("name")) pName = val;
                                        if (key.equals("price")) pPrice = val;
                                    }
                                    String detailHtml =
                                            "<link rel=\"stylesheet\" href=\"style.css\">\n" +
                                                    "<h2>Product Details</h2>\n" +
                                                    "<div id=\"productDetail\"><p>Name: " + pName + "</p><p>Price: " + pPrice + "</p></div>\n" +
                                                    "<a href=\"index.html\">Back</a>";
                                    try {
                                        java.nio.file.Files.writeString(
                                                java.nio.file.Paths.get("output/templates/detail_" + idx + ".html"), detailHtml);
                                        System.out.println("✅ Generated file: output/detail_" + idx + ".html");
                                    } catch (Exception e) {
                                        System.err.println("Error writing detail_" + idx + ".html: " + e.getMessage());
                                    }
                                    idx++;
                                }
                            }
                        }
                    }


                    try {
                        java.nio.file.Files.createDirectories(
                                java.nio.file.Paths.get("compiler_output"));
                        String logEntry = "Generated: output/" + outputFileName +
                                " from: " + fileName + "\n";
                        java.nio.file.Files.writeString(
                                java.nio.file.Paths.get("compiler_output/generation_log.txt"),
                                logEntry,
                                java.nio.file.StandardOpenOption.CREATE,
                                java.nio.file.StandardOpenOption.APPEND
                        );
                    } catch (Exception e) {
                        System.err.println("Error writing log: " + e.getMessage());
                    }
                } catch (Exception e) {
                    System.err.println("Error writing file: " + e.getMessage());
                }
            }

            System.out.println("\n--- Python Symbol Table (Flask Context) ---");
            System.out.println(symbolTable.SymbolTableManager.INSTANCE.getPythonTable());

            System.out.println("\n--- Jinja Symbol Table ---");
            System.out.println(symbolTable.SymbolTableManager.INSTANCE.getJinjaTable());

            if (htmlContent != null) {
                showASTWindow(htmlContent.toString(), " Jinja/HTML AST - " + fileName);


                try {
                    java.nio.file.Files.createDirectories(
                            java.nio.file.Paths.get("compiler_output"));
                    java.nio.file.Files.writeString(
                            java.nio.file.Paths.get("compiler_output/ast_jinja.json"),
                            "{\n  \"ast\": \"" +
                                    htmlContent.toString().replace("\"", "'").replace("\n", "\\n") +
                                    "\"\n}",
                            java.nio.file.StandardOpenOption.CREATE,
                            java.nio.file.StandardOpenOption.APPEND
                    );
                    System.out.println("✅ Generated file: compiler_output/ast_jinja.json");
                } catch (Exception e) {
                    System.err.println("Error writing AST: " + e.getMessage());
                }
            }

        } else if (fileName.endsWith(".css")) {
            System.out.println("\n--- [CSS Analysis] Processing: " + fileName + " ---");

            antlr.css.CssLexer lexer = new antlr.css.CssLexer(CharStreams.fromFileName(fileName));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            antlr.css.CssParser parser = new antlr.css.CssParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new CustomErrorListener());

            ParseTree tree = parser.style_sheet();
            visitor.css.StyleSheetVisitor cssVisitor = new visitor.css.StyleSheetVisitor();
            cssVisitor.visit(tree);

        } else {
            System.out.println("Skipping file: " + fileName);
        }

    } catch (Exception e) {
        System.out.println("Error processing " + fileName + ": " +
                (e.getMessage() != null ? e.getMessage() : "Unknown Error"));
        e.printStackTrace(System.out);
    }

}


private static void showASTWindow(String astText, String tabTitle) {
    SwingUtilities.invokeLater(() -> {

        DefaultMutableTreeNode root = buildTreeFromText(astText);
        JTree tree = new JTree(new DefaultTreeModel(root));
        tree.setBackground(new Color(30, 30, 30));
        tree.setForeground(new Color(212, 212, 212));
        tree.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
        tree.setRowHeight(24);
        tree.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        tree.setCellRenderer(new javax.swing.tree.DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                          boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                String text = value.toString();
                setBackgroundNonSelectionColor(new Color(30, 30, 30));
                setBackgroundSelectionColor(new Color(0, 122, 204));

                if (text.contains("Statement") || text.contains("Stmt")) {
                    setForeground(sel ? Color.WHITE : new Color(86, 156, 214));
                } else if (text.contains("Expression") || text.contains("Expr")) {
                    setForeground(sel ? Color.WHITE : new Color(78, 201, 176));
                } else if (text.contains("Content") || text.contains("Html")) {
                    setForeground(sel ? Color.WHITE : new Color(206, 145, 120));
                } else if (text.contains("=") || text.contains("in ")) {
                    setForeground(sel ? Color.WHITE : new Color(220, 220, 170));
                } else {
                    setForeground(sel ? Color.WHITE : new Color(212, 212, 212));
                }
                setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
                return this;
            }
        });

        expandAll(tree);

        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));

        // ===== Bottom Buttons =====
        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomBar.setBackground(new Color(45, 45, 48));

        JButton expandBtn  = new JButton("Expand All");
        JButton collapseBtn = new JButton("Collapse All");

        for (JButton btn : new JButton[]{expandBtn, collapseBtn}) {
            btn.setBackground(new Color(0, 122, 204));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setFont(new Font("Arial", Font.PLAIN, 13));
            bottomBar.add(btn);
        }

        expandBtn.addActionListener(e -> expandAll(tree));
        collapseBtn.addActionListener(e -> collapseAll(tree));

        // ===== Panel للـ Tab =====
        JPanel tabPanel = new JPanel(new BorderLayout());
        tabPanel.setBackground(new Color(30, 30, 30));
        tabPanel.add(scrollPane, BorderLayout.CENTER);
        tabPanel.add(bottomBar, BorderLayout.SOUTH);


        tabbedPane.addTab(tabTitle, tabPanel);
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    });
}

    private static DefaultMutableTreeNode buildTreeFromText(String astText) {
        String[] lines = astText.split("\n");
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("AST");
        java.util.Stack<DefaultMutableTreeNode> stack = new java.util.Stack<>();
        stack.push(root);
        java.util.Stack<Integer> levels = new java.util.Stack<>();
        levels.push(-1);

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            int level = getIndentLevel(line);
            String nodeText = line.replaceAll("^[\\s|]+", "").trim();
            if (nodeText.isEmpty()) continue;

            DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeText);

            while (levels.size() > 1 && levels.peek() >= level) {
                stack.pop();
                levels.pop();
            }

            stack.peek().add(node);
            stack.push(node);
            levels.push(level);
        }
        return root;
    }

    private static int getIndentLevel(String line) {
        int count = 0;
        for (char c : line.toCharArray()) {
            if (c == '|' || c == ' ') count++;
            else break;
        }
        return count / 4;
    }

    private static void expandAll(JTree tree) {
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }
    }

    private static void collapseAll(JTree tree) {
        for (int i = tree.getRowCount() - 1; i >= 1; i--) {
            tree.collapseRow(i);
        }
    }

    private static void showParseTree(String[] ruleNames, ParseTree parseTree) {
        TreeViewer viewer = new TreeViewer(
                java.util.Arrays.asList(ruleNames),
                parseTree
        );

        viewer.setScale(1.5);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(viewer, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel controlPanel = new JPanel();
        JButton zoomInButton = new JButton("Zoom In");
        JButton zoomOutButton = new JButton("Zoom Out");
        JButton resetButton = new JButton("Reset Zoom");

        zoomInButton.addActionListener(e -> {
            viewer.setScale(viewer.getScale() * 1.2);
            viewer.repaint();
        });

        zoomOutButton.addActionListener(e -> {
            viewer.setScale(viewer.getScale() / 1.2);
            viewer.repaint();
        });

        resetButton.addActionListener(e -> {
            viewer.setScale(1.0);
            viewer.repaint();
        });

        controlPanel.add(zoomInButton);
        controlPanel.add(zoomOutButton);
        controlPanel.add(resetButton);

        JFrame frame = new JFrame("Parse Tree Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setSize(1000, 640);
        frame.setVisible(true);
    }

    private static void debugTokenStream(CommonTokenStream tokens, Lexer lexer) {
        tokens.fill();
        List<Token> allTokens = tokens.getTokens();

        System.out.println("\n--- LEXER TOKEN DEBUG OUTPUT ---");
        for (Token t : allTokens) {
            if (t.getChannel() == Token.DEFAULT_CHANNEL) {
                String tokenName = PythonLexer.VOCABULARY.getSymbolicName(t.getType());
                String tokenText = t.getText().replace("\n", "\\n").replace("\r", "\\r");

                if (tokenName == null) {
                    tokenName = "VirtualType(" + t.getType() + ")";
                }

                System.out.printf("Line %d | %-20s | Text: '%s'\n",
                        t.getLine(),
                        tokenName,
                        tokenText);
            }
        }
        System.out.println("--------------------------------\n");
    }
    private static void startAddProductServer() {
        try {
            com.sun.net.httpserver.HttpServer server =
                    com.sun.net.httpserver.HttpServer.create(new java.net.InetSocketAddress(8080), 0);

            // ===== Endpoint الأول: /add (موجود من قبل) =====
            server.createContext("/add", exchange -> {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

                String query = exchange.getRequestURI().getQuery();
                String name = "";
                String price = "0";
                if (query != null) {
                    for (String pair : query.split("&")) {
                        String[] kv = pair.split("=", 2);
                        if (kv.length == 2) {
                            String key = kv[0];
                            String value = java.net.URLDecoder.decode(kv[1], "UTF-8");
                            if (key.equals("name")) name = value;
                            if (key.equals("price")) price = value;
                        }
                    }
                }

                boolean success = addProductToSource(name, price);

                if (success) {
                    System.out.println("🔄 Product added via UI, regenerating...");
                    processFile(BASE_DIR + "/app.py");
                    processFile(BASE_DIR + "/templates/index.jinja");
                    processFile(BASE_DIR + "/templates/add_product.jinja");
                    processFile(BASE_DIR + "/templates/detail.jinja");
                    System.out.println("✅ Regeneration complete!");
                }

                String response = success ? "OK" : "FAILED";
                exchange.sendResponseHeaders(200, response.length());
                try (java.io.OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            });


            server.createContext("/delete", exchange -> {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

                String query = exchange.getRequestURI().getQuery(); // index=N
                int index = -1;
                if (query != null) {
                    for (String pair : query.split("&")) {
                        String[] kv = pair.split("=", 2);
                        if (kv.length == 2 && kv[0].equals("index")) {
                            try { index = Integer.parseInt(kv[1]); } catch (Exception ignored) {}
                        }
                    }
                }

                boolean success = (index >= 0) && deleteProductFromSource(index);

                if (success) {
                    System.out.println("🔄 Product deleted via UI, regenerating...");
                    processFile(BASE_DIR + "/app.py");
                    processFile(BASE_DIR + "/templates/index.jinja");
                    processFile(BASE_DIR + "/templates/add_product.jinja");
                    processFile(BASE_DIR + "/templates/detail.jinja");
                    System.out.println("✅ Regeneration complete!");
                }

                String response = success ? "OK" : "FAILED";
                exchange.sendResponseHeaders(200, response.length());
                try (java.io.OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            });

            server.setExecutor(null);
            server.start();
            System.out.println("🌐 Add-product server running at http://localhost:8080");

        } catch (Exception e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    private static boolean addProductToSource(String name, String price) {
        try {
            java.nio.file.Path appPyPath = java.nio.file.Paths.get(BASE_DIR + "/app.py");
            String content = java.nio.file.Files.readString(appPyPath);


            java.util.regex.Pattern pattern =
                    java.util.regex.Pattern.compile("products\\s*=\\s*\\[(.*?)]", java.util.regex.Pattern.DOTALL);
            java.util.regex.Matcher matcher = pattern.matcher(content);

            if (!matcher.find()) {
                System.err.println("Error: 'products = [...]' not found in app.py");
                return false;
            }

            String existingItems = matcher.group(1).trim();
            String newItem = "{\"name\": \"" + name + "\", \"price\": " + price + "}";

            String updatedItems = existingItems.isEmpty()
                    ? newItem
                    : existingItems + ", " + newItem;

            String updatedLine = "products = [" + updatedItems + "]";
            String newContent = matcher.replaceFirst(java.util.regex.Matcher.quoteReplacement(updatedLine));

            java.nio.file.Files.writeString(appPyPath, newContent);
            return true;

        } catch (Exception e) {
            System.err.println("Error updating app.py: " + e.getMessage());
            return false;
        }
    }
    private static boolean deleteProductFromSource(int indexToDelete) {
        try {
            java.nio.file.Path appPyPath = java.nio.file.Paths.get(BASE_DIR + "/app.py");
            String content = java.nio.file.Files.readString(appPyPath);

            java.util.regex.Pattern pattern =
                    java.util.regex.Pattern.compile("products\\s*=\\s*\\[(.*?)]", java.util.regex.Pattern.DOTALL);
            java.util.regex.Matcher matcher = pattern.matcher(content);

            if (!matcher.find()) {
                System.err.println("Error: 'products = [...]' not found in app.py");
                return false;
            }

            String itemsStr = matcher.group(1).trim();


            java.util.List<String> items = new java.util.ArrayList<>();
            int depth = 0;
            StringBuilder current = new StringBuilder();
            for (char c : itemsStr.toCharArray()) {
                if (c == '{') depth++;
                if (c == '}') depth--;
                if (c == ',' && depth == 0) {
                    items.add(current.toString().trim());
                    current.setLength(0);
                } else {
                    current.append(c);
                }
            }
            if (current.toString().trim().length() > 0) {
                items.add(current.toString().trim());
            }

            if (indexToDelete < 0 || indexToDelete >= items.size()) {
                System.err.println("Error: index out of range: " + indexToDelete);
                return false;
            }

            items.remove(indexToDelete);

            String updatedItems = String.join(", ", items);
            String updatedLine = "products = [" + updatedItems + "]";
            String newContent = matcher.replaceFirst(java.util.regex.Matcher.quoteReplacement(updatedLine));

            java.nio.file.Files.writeString(appPyPath, newContent);
            return true;

        } catch (Exception e) {
            System.err.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }
}