import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;

public class Calculator {
    private String input;
    //private String InfixExp;
    //private static String SuffixExp;
    private ArrayList<String> SuffixExp = new ArrayList<String>();
    //private ArrayList<String> Sym = new ArrayList<String>();
    private ArrayList<String> ExpToken = new ArrayList<String>();

    public Calculator(String input){
        this.input = input;
    }

    public static int toDec(String num){
        int dec;
        int hex;
        int oct;

        if(num.startsWith("-")){
            if(num.startsWith("-0x")||num.startsWith("-0X")){
                hex = Integer.parseInt(num.substring(3),16);
                dec = hex*-1;
            }
            else if(num.startsWith("-0") && num.length()>2){
                oct = Integer.parseInt(num.substring(2),8);
                dec = oct*-1;
            }
            else{
                return Integer.parseInt(num);
            }
        }
        else if(num.startsWith("0x") || num.startsWith("0X")){
            hex = Integer.parseInt(num.substring(2),16);
            dec = hex;
        }
        else if(num.startsWith("0")&& num.length()>2){
            oct = Integer.parseInt(num.substring(1),8);
            dec = oct;
        }
        else{
            return Integer.parseInt(num);
        }
        return dec;
    }



    public String symbolClear(String exp){
        StringBuilder symbolCleared = new StringBuilder("");
        int negCount = 0;

//        if(exp.charAt(0)=='+'){
//            exp = exp.substring(1);
//        }
        for(int i=0;i<exp.length();i++){
            if(exp.charAt(i)=='-' || exp.charAt(i)=='+'){
                for(int j=i;j<exp.length();j++){
                    if(exp.charAt(j)=='-'){
                        negCount++;
                    }
                    if(exp.charAt(j)!='-' && exp.charAt(j)!='+'){
                        if(negCount%2==0 && i!=0 && Character.isDigit(exp.charAt(i-1))){
                            symbolCleared.append("+");
//                            symbolCleared.append(exp.charAt(j));
                        }
                        else if(negCount%2==1){
                            symbolCleared.append("-");
//                            symbolCleared.append(exp.charAt(j));
                        }
                        i=j;
                        negCount=0;
                        break;
                    }
                }
            }
            if(exp.charAt(i)!='-' && exp.charAt(i)!='+'){
                symbolCleared.append(exp.charAt(i));
            }
        }
        return symbolCleared.toString();
    }

    public void splitExp(String exp){
        StringBuilder num = new StringBuilder();
        for(int i=0;i<exp.length();i++){
            if(isOp(exp.substring(i,i+1))){
                ExpToken.add(exp.charAt(i)+"");
            }
            while(i<exp.length() && (Character.isDigit(exp.charAt(i))|| Character.isLetter(exp.charAt(i)))){
                num.append(exp.charAt(i));
                i++;
            }
            if(!num.isEmpty()){
                ExpToken.add(num.toString());
                i--;
                num = new StringBuilder();
            }
        }
        //process negatives
        for(int i=0;i< ExpToken.size();i++){
            if(i==0 && ExpToken.size() > 1 && ExpToken.get(i).equals("-")){
                if( Character.isDigit(ExpToken.get(i+1).charAt(0))){
                    num = new StringBuilder(ExpToken.get(i+1));
                    num = num.insert(0,"-");
                    ExpToken.set(i+1,num.toString());
                    ExpToken.remove(i);
                }
                else if((ExpToken.get(i+1).charAt(0))=='('){
                    ExpToken.add(0,"0");
                }
            }
            else if((ExpToken.get(i).equals("-") && i != 0 && !Character.isDigit(ExpToken.get(i-1).charAt(ExpToken.get(i-1).length()-1)))  && !ExpToken.get(i-1).equals(")")&& i != ExpToken.size() - 1 && Character.isDigit(ExpToken.get(i+1).charAt(0))){
                if(isOp(ExpToken.get(i-1))){
                    num = new StringBuilder(ExpToken.get(i+1));
                    num = num.insert(0,"-");
                    ExpToken.set(i+1,num.toString());
                    ExpToken.remove(i);
                }
            }
            else if((ExpToken.get(i).equals("-") && i != 0 && !Character.isDigit(ExpToken.get(i-1).charAt(ExpToken.get(i-1).length()-1)))  && !ExpToken.get(i-1).equals(")")&& i != ExpToken.size() - 1 && ExpToken.get(i+1).charAt(0)=='('){
                if(isOp(ExpToken.get(i-1))){
                    int j=i+1;
                    while(!isNum(ExpToken.get(j))){//find the first next num in tokens list
                        j+=1;
                    }
                    num = new StringBuilder(ExpToken.get(j));
                    num = num.insert(0,"-");
                    ExpToken.set(j,num.toString());
                    ExpToken.remove(i);
                }
            }
        }
    }

    public void toSuffix(){
        ArrayList<String> Sym = new ArrayList<String>();
        for(int i=0;i<ExpToken.size();i++){
            String token = ExpToken.get(i);
            if(isNum(token)){
                SuffixExp.add(token);
            }
            else if(isOp(token)){
                if(Sym.size()==0 || token.equals("(")){
                    Sym.add(token);
                }
                else if( token.equals(")")){
                    while(!Sym.get(Sym.size()-1).equals("(")){
                        SuffixExp.add(Sym.get(Sym.size()-1));
                        Sym.remove(Sym.size()-1);
                    }
                    Sym.remove(Sym.size()-1);
                }
                else{
                    if(isOpAGTOpB(token,Sym.get(Sym.size()-1))){
                        Sym.add(token);
                    }
                    else{
                        while(Sym.size()!=0 && (OpACMPOpB(token,Sym.get(Sym.size()-1))=='L' || OpACMPOpB(token,Sym.get(Sym.size()-1))=='E') && !Sym.get(Sym.size()-1).equals("(")){
                            SuffixExp.add(Sym.get(Sym.size()-1));
                            Sym.remove(Sym.size()-1);
                        }
                        Sym.add(token);
                    }
                }
            }
        }
        while(Sym.size()>0){
            SuffixExp.add(Sym.get(Sym.size()-1));
            Sym.remove(Sym.size()-1);
        }
    }

    public static boolean isOpAGTOpB(String OpA, String OpB){
        if ((OpA.equals("*")|| OpA.equals("/")) && !OpB.equals("*") && !OpB.equals("/") && !OpB.equals("("))
            return true;
        else
            return false;
    }

    public static char OpACMPOpB(String OpA, String OpB){
        if((OpA.equals("*") || OpA.equals("/") || OpA.equals("%")) && (OpB.equals("+") || OpB.equals("-"))){
            return 'G';
        }
        else if((OpA.equals("+") || OpA.equals("-")) && (OpB.equals("*") || OpB.equals("/") || OpB.equals("%"))){
            return 'L';
        }
        else return 'E';
    }

    public static boolean isOp(String  x){
        return x.equals("+")||x.equals("-")||x.equals("*")||x.equals("/")||x.equals("(")||x.equals(")")||x.equals("%");
    }

    public static boolean isNum(String x){
        boolean num = true;
        for(int i=0;i<x.length();i++){
            if(i==0 && x.charAt(i)=='-'){
                if(x.length()<2 || !Character.isDigit(x.charAt(i+1))){
                    num = false;
                }
            }
            else if(!Character.isDigit(x.charAt(i)) && !Character.isLetter(x.charAt(i))){
                num = false;
            }
        }
        return num;
    }

    public static String doOp(String num1,String Op,String num2){
        int number1 = toDec(num1);
        int number2 = toDec(num2);
        if(Op.equals("+")){
            return ""+(number1 + number2);
        }
        else if(Op.equals("-")) return ""+(number1-number2);
        else if(Op.equals("*")) return ""+(number1*number2);
        else if(Op.equals("/")) return ""+(number1/number2);
        else return ""+(number1%number2);
        //else if(Op.equals("%")) return ""+(number1%number2);
    }

    public int calc(){
        splitExp(symbolClear(input));
        toSuffix();
        ArrayList<String> num = new ArrayList<String>();
        for(String x:SuffixExp){
            if(isNum(x)){
                num.add(x);
            }
            else{
                if(num.size()<2 && (x.equals("-")||x.equals("+"))){
                    String NegNum = num.get(num.size()-1);
                    num.remove(num.size()-1);
                    NegNum = doOp("0",x,NegNum);
                    num.add(NegNum);
                }
                else{
                    String num1 = num.get(num.size()-1);
                    String num2 = num.get(num.size()-2);
                    num.remove(num.size()-1);
                    num.remove(num.size()-1);
                    String doOpRes = doOp(num2,x,num1);
                    num.add(doOpRes);
                }
            }
        }
        return toDec(num.get(0));
    }


    public static void main(String[] args) {
//       System.out.println(toDec("010"));
//        Calculator c = new Calculator("1+-(--+-((-+(-+(0xF)))))");
        Calculator c = new Calculator("1+-(-(2))");
//        c.splitExp(c.symbolClear(c.input));
//        c.toSuffix();
//        System.out.println(c.ExpToken);
//        System.out.println(c.SuffixExp);
        System.out.println(c.calc());
    }





//    public static String toDec(String num){
//        String dec;
//
//        if(num.startsWith("0x") || num.startsWith("0X")){
//            int hex = Integer.parseInt(num.substring(2),16);
//            dec = ""+hex;
//        }
//        else if(num.startsWith("0")){
//            int oct = Integer.parseInt(num.substring(1),8);
//            dec = ""+oct;
//        }
//        else{
//            return num;
//        }
//        return dec;
//    }
}


