import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
//        File myObj = new File("filename.txt");
//        Scanner in = new Scanner(myObj);
        String input = in.nextLine() + "\n";
        while(in.hasNextLine()){
            input+=in.nextLine();
            input+="\n";
        }
//        System.out.println(input);
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

//        System.out.println();
//        System.out.println(Var.varsReg);
    }
}
