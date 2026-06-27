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

public class App {

    private static JTabbedPane tabbedPane = new JTabbedPane();
    private static JFrame mainFrame = null;
    public static void main(String[] args) {

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
        processFile("samples/Testing4/app.py");
        processFile("samples/Testing4/test.j2");
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

            System.out.println("\n--- Symbol Table after Python ---");
            System.out.println(symbolTable.SymbolTableManager.INSTANCE.getPythonTable());


            if (program != null) {
                showASTWindow(program.toString(), " Python AST - " + fileName);
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

            System.out.println("\n--- Python Symbol Table (Flask Context) ---");
            System.out.println(symbolTable.SymbolTableManager.INSTANCE.getPythonTable());

            System.out.println("\n--- Jinja Symbol Table ---");
            System.out.println(symbolTable.SymbolTableManager.INSTANCE.getJinjaTable());

            if (htmlContent != null) {
                showASTWindow(htmlContent.toString(), " Jinja/HTML AST - " + fileName);
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
        System.err.println("Error processing " + fileName + ": " +
                (e.getMessage() != null ? e.getMessage() : "Unknown Error"));
        e.printStackTrace();
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