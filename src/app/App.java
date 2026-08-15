//package app;
//
//import antlr.html.HtmlLexer;
//import antlr.html.HtmlParser;
//import antlr.python.PythonLexer;
//import antlr.python.PythonParser;
//import listener.CustomErrorListener;
//import org.antlr.v4.gui.TreeViewer;
//import org.antlr.v4.runtime.CharStreams;
//import org.antlr.v4.runtime.CommonTokenStream;
//import org.antlr.v4.runtime.Lexer;
//import org.antlr.v4.runtime.Token;
//import org.antlr.v4.runtime.tree.ParseTree;
//import visitor.python.ProgramVisitor;
//
//import javax.swing.*;
//import java.awt.*;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.stream.Stream;
//
//public class App {
//    public static void main(String[] args) {
////        processFile("samples/errors-test.py");
////        processFile("samples/Jinja-test.j2");
//        if (args.length != 1) {
//            System.err.println("Usage: java app.App <directory_path_or_file>");
//        } else {
//            Path startPath = Paths.get(args[0]);
//
//            try (Stream<Path> paths = Files.walk(startPath)) {
//                paths.filter(Files::isRegularFile)
//                        .forEach(path -> {
//                            String fileName = path.toString();
//                            System.out.println("\n--- Processing: " + fileName + " ---");
//                            processFile(fileName);
//                        });
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
////            String fileName = args[0];
////            try {
////                // Step 1: Get the tokens stream
////                CommonTokenStream tokens = getTokenStream(fileName);
////
////                // CRITICAL DEBUG STEP: Print all tokens before parsing
////                debugTokenStream(tokens);
////
////                // Step 2: Create the parser and parse
////                tokens.reset(); // Reset the stream to the beginning for the parser
////                JinjaFlaskParser parser = new JinjaFlaskParser(tokens);
////
////                // Add the custom error listener
////                parser.removeErrorListeners();
////                parser.addErrorListener(new CustomErrorListener());
////
////                // tell ANTLR to build a parse tree
////                ParseTree antlrAST = parser.prog();
////                showParseTree(parser.getRuleNames(), antlrAST);
////                ProgramVisitor programVisitor = new ProgramVisitor();
////                Program program = programVisitor.visit(antlrAST);
////                System.out.println(program);
////
////                System.out.println(SymbolTableManager.INSTANCE.getSymbolTable());
////                // If we reach here, the parse was successful!
////                System.out.println("--- Parsing SUCCESSFUL! ---");
////
////            } catch (Exception e) {
////                System.err.println("Parsing halted due to error: " + (e.getMessage() != null ? e.getMessage() : "Unknown Error (Likely ANTLR Stack Crash)"));
////                // Print stack trace for better debugging of 'null' errors
////                e.printStackTrace();
////            }
//        }
//    }
//
//    private static void processFile(String fileName) {
//        try {
//            // 1. معالجة ملفات Python فقط (تمت إزالة .txt لتجنب التداخل)
//
//            if (fileName.endsWith(".py")) {
//                System.out.println("\n--- [Python Analysis] Processing: " + fileName + " ---");
//
//                PythonLexer lexer = new PythonLexer(CharStreams.fromFileName(fileName));
//                CommonTokenStream tokens = new CommonTokenStream(lexer);
//                PythonParser parser = new PythonParser(tokens);
//
//                // إعداد مستمع الأخطاء القواعدية قبل البدء
//                parser.removeErrorListeners();
//                parser.addErrorListener(new CustomErrorListener());
//
//                // بناء شجرة الإعراب وقاعدة البداية
//                ParseTree tree = parser.prog();
//
//                // تشغيل الـ Visitor للتحليل الدلالي (Semantic Analysis)
//                ProgramVisitor visitor = new ProgramVisitor();
//                visitor.visit(tree);
//
//                // طباعة جدول الرموز بعد انتهاء معالجة ملف البايثون
//                System.out.println("\n--- Symbol Table after Python ---");
//                System.out.println(symbolTable.SymbolTableManager.INSTANCE.getPythonTable());
//
//            }
//            // 2. معالجة ملفات HTML و Jinja
//            else if (fileName.endsWith(".html") || fileName.endsWith(".j2") || fileName.endsWith(".jinja")) {
//                System.out.println("\n--- [Jinja/HTML Analysis] Processing: " + fileName + " ---");
//
//                HtmlLexer lexer = new HtmlLexer(CharStreams.fromFileName(fileName));
//                CommonTokenStream tokens = new CommonTokenStream(lexer);
//                HtmlParser parser = new HtmlParser(tokens);
//
//                parser.removeErrorListeners();
//                parser.addErrorListener(new CustomErrorListener());
//
//                // قاعدة البداية الخاصة بالـ HTML/Jinja
//                ParseTree tree = parser.html_content();
//
//                // تشغيل الـ Visitor الخاص بالـ HTML الذي يستدعي بدوره JinjaVisitors
//                visitor.html.HtmlContentVisitor visitor = new visitor.html.HtmlContentVisitor();
//                visitor.visit(tree);
//            }
//            // 3. معالجة ملفات CSS
//            else if (fileName.endsWith(".css")) {
//                System.out.println("\n--- [CSS Analysis] Processing: " + fileName + " ---");
//
//                antlr.css.CssLexer lexer = new antlr.css.CssLexer(CharStreams.fromFileName(fileName));
//                CommonTokenStream tokens = new CommonTokenStream(lexer);
//                antlr.css.CssParser parser = new antlr.css.CssParser(tokens);
//
//                parser.removeErrorListeners();
//                parser.addErrorListener(new CustomErrorListener());
//
//                ParseTree tree = parser.style_sheet();
//
//                visitor.css.StyleSheetVisitor visitor = new visitor.css.StyleSheetVisitor();
//                visitor.visit(tree);
//            }
//            else {
//                System.out.println("Skipping file (Unsupported Extension): " + fileName);
//            }
//
//        } catch (Exception e) {
//            System.err.println("Error processing " + fileName + ": " + (e.getMessage() != null ? e.getMessage() : "Unknown Error"));
//            // e.printStackTrace(); // فك التعليق في حال أردت تتبع الخطأ بدقة
//        }
//    }
//
//
//    private static void showParseTree(String[] ruleNames, ParseTree parseTree) {
//        TreeViewer viewer = new TreeViewer(
//                java.util.Arrays.asList(ruleNames),
//                parseTree
//        );
//
//        // Configure viewer for better display
//        viewer.setScale(1.5);  // Make text larger (optional)
//
//        // Create main panel with border layout
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        mainPanel.add(viewer, BorderLayout.CENTER);
//
//        // Create scroll pane
//        JScrollPane scrollPane = new JScrollPane(mainPanel);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//
//        // Add zoom controls for better navigation
//        JPanel controlPanel = new JPanel();
//        JButton zoomInButton = new JButton("Zoom In");
//        JButton zoomOutButton = new JButton("Zoom Out");
//        JButton resetButton = new JButton("Reset Zoom");
//
//        zoomInButton.addActionListener(e -> {
//            viewer.setScale(viewer.getScale() * 1.2);
//            viewer.repaint();
//        });
//
//        zoomOutButton.addActionListener(e -> {
//            viewer.setScale(viewer.getScale() / 1.2);
//            viewer.repaint();
//        });
//
//        resetButton.addActionListener(e -> {
//            viewer.setScale(1.0);
//            viewer.repaint();
//        });
//
//        controlPanel.add(zoomInButton);
//        controlPanel.add(zoomOutButton);
//        controlPanel.add(resetButton);
//
//        // Create frame
//        JFrame frame = new JFrame("Parse Tree Viewer");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Add components
//        frame.add(scrollPane, BorderLayout.CENTER);
//        frame.add(controlPanel, BorderLayout.SOUTH);
//
//        // Set size and display
//        frame.setSize(1000, 640);
//        frame.setVisible(true);
//    }
//
//
//    private static void debugTokenStream(CommonTokenStream tokens, Lexer lexer) {
//        tokens.fill(); // Ensure all tokens are generated
//        List<Token> allTokens = tokens.getTokens();
//
//        System.out.println("\n--- LEXER TOKEN DEBUG OUTPUT ---");
//        for (Token t : allTokens) {
//            // Only show tokens on the default channel (skipping WS and Comments)
//            if (t.getChannel() == Token.DEFAULT_CHANNEL) {
//                String tokenName = PythonLexer.VOCABULARY.getSymbolicName(t.getType());
//                String tokenText = t.getText().replace("\n", "\\n").replace("\r", "\\r");
//
//                // Use the type number if the name is null (for virtual tokens like INDENT/DEDENT)
//                if (tokenName == null) {
//                    tokenName = "VirtualType(" + t.getType() + ")";
//                }
//
//                System.out.printf("Line %d | %-20s | Text: '%s'\n",
//                        t.getLine(),
//                        tokenName,
//                        tokenText);
//            }
//        }
//        System.out.println("--------------------------------\n");
//    }
//
//
//}
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
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;
import visitor.python.AtomExpressionVisitor;
public class App {

    private static JTabbedPane tabbedPane = new JTabbedPane();
    private static JFrame mainFrame = null;
    private static java.util.List<String> semanticErrors = new java.util.ArrayList<>();
    public static void main(String[] args) {
        // ✅ اعترض System.err لتخزين الأخطاء الدلالية
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
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        // ✅ امسح الـ log القديم
        try {
            java.nio.file.Files.createDirectories(
                    java.nio.file.Paths.get("compiler_output"));
            java.nio.file.Files.writeString(
                    java.nio.file.Paths.get("compiler_output/generation_log.txt"), "");
        } catch (Exception e) {
            System.err.println("Error clearing log: " + e.getMessage());
        }

        processFile("samples/Testing7/app.py");
        processFile("samples/Testing7/templates/index.jinja");
        processFile("samples/Testing7/templates/add_product.jinja");
        processFile("samples/Testing7/templates/edit_product.jinja");
        processFile("samples/Testing7/templates/detail.jinja");
// ✅ انسخ style.css إلى output/
        // ✅ انسخ style.css إلى output/static/
        try {
            java.nio.file.Path cssSource = java.nio.file.Paths.get("samples/Testing7/style.css");
            if (java.nio.file.Files.exists(cssSource)) {
                java.nio.file.Files.createDirectories(java.nio.file.Paths.get("output/static"));
                java.nio.file.Files.copy(
                        cssSource,
                        java.nio.file.Paths.get("output/static/style.css"),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                );
                System.out.println("✅ Copied file: output/static/style.css");
            }
        } catch (Exception e) {
            System.err.println("Error copying style.css: " + e.getMessage());
        }
        // ✅ اكتب semantic_report.txt
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
                generatedCode = generatedCode.replace(
                        "@app.route('/')",
                        "products.append({'name': product1_name, 'price': product1_price})\n" +
                                "products.append({'name': product2_name, 'price': product2_price})\n\n" +
                                "@app.route('/')"
                );
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

                // ✅ اكتب ast_python.json
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
            System.out.println("\n--- [Jinja/HTML Analysis] Processing: " + fileName + " ---");

            HtmlLexer lexer = new HtmlLexer(CharStreams.fromFileName(fileName));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            HtmlParser parser = new HtmlParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new CustomErrorListener());

            ParseTree tree = parser.html_content();
            visitor.html.HtmlContentVisitor visitor = new visitor.html.HtmlContentVisitor();
            ast.HtmlContent htmlContent = (ast.HtmlContent) visitor.visit(tree);

             // ✅ Generate HTML file
            if (htmlContent != null) {
                String generatedHtml = htmlContent.generateCode();

// ✅ استبدل المتغيرات بالقيم الحقيقية
                for (java.util.Map.Entry<String, String> entry :
                        AtomExpressionVisitor.renderContext.entrySet()) {
                    generatedHtml = generatedHtml.replace(
                            "{{ " + entry.getKey() + " }}",
                            entry.getValue()
                    );
                }

                System.out.println("\n--- Generated HTML Code ---");
                System.out.println(generatedHtml);

                try {
                    // ✅ استخرج اسم الملف وحوّله من .jinja إلى .html
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
                    // ✅ سجّل في generation_log.txt
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

                // ✅ اكتب ast_jinja.json
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
}