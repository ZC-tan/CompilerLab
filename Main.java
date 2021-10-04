import com.sun.security.jgss.GSSUtil;
import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static File file;
    private static BufferedReader br;
    private static String line;
    private static String[] wordsBySpace;
    private static String[] words;
    private static String word;
    private static ArrayList<String> reserved = new ArrayList<String>();
    private static HashMap<String,String> symbol = new HashMap<String,String>();
    private static String symbolRegex = "=|;|(|)|\\{|\\}|\\+|\\*|/|<|>|==";
    private static Pattern p = Pattern.compile(symbolRegex);

    static{
        reserved.add("if");
        reserved.add("else");
        reserved.add("while");
        reserved.add("break");
        reserved.add("continue");
        reserved.add("return");
    }

    static{
        symbol.put("=","Assign");
        symbol.put(";","Semicolon");
        symbol.put("(","LPar");
        symbol.put(")","RPar");
        symbol.put("{","LBrace");
        symbol.put("}","RBrace");
        symbol.put("+","Plus");
        symbol.put("*","Mult");
        symbol.put("/","Div");
        symbol.put(">","Gt");
        symbol.put("<","Lt");
        symbol.put("==","Eq");
    }

    public static boolean isReserved(String word){
        if(reserved.contains(word)){
            return true;
        }
        return false;
    }

    public static String whichReserved(String word){
        int pos = reserved.indexOf(word);
        String reservedWord = reserved.get(pos);
        return reservedWord.substring(0,1).toUpperCase()+reservedWord.substring(1);
    }

    public static boolean isSymbol(String word){
        if(symbol.containsKey(word)){
            return true;
        }
        return false;
    }

    public static boolean isVar(String word){
        if(word.matches("^[a-zA-Z_]+[a-zA-Z_0-9$]*$")){
            return true;
        }
        else return false;
    }

    public static boolean isNum(String word){
        if(word.matches("[0-9$]+")){
            return true;
        }
        else return false;
    }

    public static void printToken(String word){
        if(isReserved(word)){
            System.out.println(whichReserved(word));
        }
        else if(isSymbol(word)){
            System.out.println(symbol.get(word));
        }
        else if(isVar(word)){
            System.out.println("Ident("+word+")");
        }
        else if(isNum(word)){
            System.out.println("Number("+word+")");
        }
        else {
            System.out.println("Err");
        }
    }

    public static void main(String[] args) throws IOException {
        //file = new File(args[0]);
        file = new File(args[0]);
        br = new BufferedReader(new FileReader(file));
        while((line = br.readLine())!=null){
            wordsBySpace = line.trim().split("\\s+");
            for(int i=0;i< wordsBySpace.length;i++){
                words=wordsBySpace[i].split("((?<===|;|\\(|\\)|\\{|\\}|\\+|\\*|/|<|>)|(?=(?!^)==|;|\\(|\\)|\\{|\\}|\\+|\\*|/|<|>))");
//                words=wordsBySpace[i].split("((?=(?!^)[=;(){}+*/<>\\(==\\)]))");
                for(int j=0;j< words.length;j++){
                    word=words[j];
//                    System.out.println(word);
                printToken(word);
                }
            }
        }
    }


}
