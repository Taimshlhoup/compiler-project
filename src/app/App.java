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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        processFile("samples/Testing5/app.py");
        processFile("samples/Testing5/test.j2");
    }

    private static void processFile(String fileName) {
        try {
            // 1. معالجة ملفات Python
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

                // ✅ طباعة الـ AST الخاصة ببايثون
                System.out.println("\n--- AST ---");
                if (program != null) {
                    System.out.println(program.toString());
                }

                // طباعة جدول رموز بايثون
                System.out.println("\n--- Symbol Table after Python ---");
                System.out.println(symbolTable.SymbolTableManager.INSTANCE.getPythonTable());
            }
            // 2. معالجة ملفات HTML و Jinja
            else if (fileName.endsWith(".html") || fileName.endsWith(".j2") || fileName.endsWith(".jinja")) {
                System.out.println("\n--- [Jinja/HTML Analysis] Processing: " + fileName + " ---");

                HtmlLexer lexer = new HtmlLexer(CharStreams.fromFileName(fileName));
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                HtmlParser parser = new HtmlParser(tokens);

                parser.removeErrorListeners();
                parser.addErrorListener(new CustomErrorListener());

                ParseTree tree = parser.html_content();

                visitor.html.HtmlContentVisitor visitor = new visitor.html.HtmlContentVisitor();
                ast.HtmlContent htmlContent = (ast.HtmlContent) visitor.visit(tree);

                // ✅ طباعة الـ AST الخاصة بجينجا
                System.out.println("\n--- AST ---");
                if (htmlContent != null) {
                    System.out.println(htmlContent.toString());
                }

                // 🔥 أولاً: طباعة جدول رموز Python (الخاص بفلاسك) ليكون مرجعاً لك أثناء فحص جينجا
                System.out.println("\n--- Python Symbol Table (Flask Context) ---");
                if (symbolTable.SymbolTableManager.INSTANCE.getPythonTable() != null) {
                    System.out.println(symbolTable.SymbolTableManager.INSTANCE.getPythonTable());
                } else {
                    System.out.println("Python Symbol Table is empty or null!");
                }

                // 🔥 ثانياً: طباعة جدول رموز Jinja
                System.out.println("\n--- Jinja Symbol Table ---");
                if (symbolTable.SymbolTableManager.INSTANCE.getJinjaTable() != null) {
                    System.out.println(symbolTable.SymbolTableManager.INSTANCE.getJinjaTable());
                } else {
                    System.out.println("Jinja Symbol Table is empty or null!");
                }
            }
            // 3. معالجة ملفات CSS
            else if (fileName.endsWith(".css")) {
                System.out.println("\n--- [CSS Analysis] Processing: " + fileName + " ---");

                antlr.css.CssLexer lexer = new antlr.css.CssLexer(CharStreams.fromFileName(fileName));
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                antlr.css.CssParser parser = new antlr.css.CssParser(tokens);

                parser.removeErrorListeners();
                parser.addErrorListener(new CustomErrorListener());

                ParseTree tree = parser.style_sheet();

                visitor.css.StyleSheetVisitor cssVisitor = new visitor.css.StyleSheetVisitor();
                cssVisitor.visit(tree);
            }
            else {
                System.out.println("Skipping file (Unsupported Extension): " + fileName);
            }

        } catch (Exception e) {
            System.err.println("Error processing " + fileName + ": " +
                    (e.getMessage() != null ? e.getMessage() : "Unknown Error"));
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