import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while(in.hasNextLine()){
            input+=in.nextLine();
        }

        CharStream inputStream = CharStreams.fromString(input);

        minisysyLexer lexer = new minisysyLexer(inputStream);
//        MyLexer lexer = new MyLexer(inputStream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(MyErrorListener.INSTANCE);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        minisysyParser parser = new minisysyParser(tokenStream);
//        parser.setErrorHandler(new BailErrorStrategy());
        parser.removeErrorListeners();
        parser.addErrorListener(MyErrorListener.INSTANCE);

        ParseTree tree = parser.compUnit();
        Visitor visitor = new Visitor();
        visitor.visit(tree);
    }
}
