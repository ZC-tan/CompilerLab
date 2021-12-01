import java.util.ArrayList;
import java.util.HashMap;

public class Expression {
    private String InfixExp;
    private String SymClearedInfixExp;
    private ArrayList<String> ExpToken = new ArrayList<String>();
    private ArrayList<String> SuffixExp = new ArrayList<String>();
    private HashMap<String,String> temp = new HashMap<String,String>();

    public Expression(String Infix){
        InfixExp = Infix;
    }

    public String symbolClear(){
        StringBuilder symbolCleared = new StringBuilder("");
        int negCount = 0;
        for(int i=0;i<InfixExp.length();i++){
            if(InfixExp.charAt(i)=='-' || InfixExp.charAt(i)=='+'){
                for(int j=i;j<InfixExp.length();j++){
                    if(InfixExp.charAt(j)=='-'){
                        negCount++;
                    }
                    if(InfixExp.charAt(j)!='-' && InfixExp.charAt(j)!='+'){
                        if(negCount%2==0 && i!=0 && (Var.isVarChar(InfixExp.charAt(i-1))||InfixExp.charAt(i-1)==')')){
                            symbolCleared.append("+");
                        }
                        else if(negCount%2==1){
                            symbolCleared.append("-");
                        }
                        i=j;
                        negCount=0;
                        break;
                    }
                }
            }
            if(InfixExp.charAt(i)!='-' && InfixExp.charAt(i)!='+'){
                symbolCleared.append(InfixExp.charAt(i));
            }
        }
        SymClearedInfixExp = symbolCleared.toString();
        return SymClearedInfixExp;
    }

    public static boolean isOperand(String x){
        boolean flag=false;
        if(Calculator.isNum(x) || Var.isVar(x)){
            flag = true;
        }
        else if(x.charAt(0)=='-' && x.length()>1){
            if(Var.isVar(x.substring(1))){
                flag = true;
            }
        }
        else if(Register.isReg(x)){
            flag = true;
        }

        return flag;
    }

    public static boolean isOperandChar(char x){
        if(Var.isVarChar(x) || x=='-'){
            return true;
        }
        else return false;
    }

    public void splitExp(){
        StringBuilder operand = new StringBuilder();
        for(int i=0;i<SymClearedInfixExp.length();i++){
            if(Calculator.isOp(SymClearedInfixExp.substring(i,i+1))){//运算符是独立的token
                ExpToken.add(SymClearedInfixExp.charAt(i)+"");
            }
            else if(SymClearedInfixExp.charAt(i)=='\\' && SymClearedInfixExp.charAt(i+1)=='%'){
                operand.append("%");
                i+=2;
                while(i<SymClearedInfixExp.length() && (Character.isDigit(SymClearedInfixExp.charAt(i)))){//运算数(变量 和 常数)
                    operand.append(SymClearedInfixExp.charAt(i));
                    i++;
                }
                ExpToken.add(operand.toString());
                i--;
                operand = new StringBuilder();
            }
            else{
                while(i<SymClearedInfixExp.length() && (Var.isVarChar(SymClearedInfixExp.charAt(i)))){//运算数(变量 和 常数)
                    operand.append(SymClearedInfixExp.charAt(i));
                    i++;
                }
                if(!operand.isEmpty()){
                    ExpToken.add(operand.toString());
                    i--;
                    operand = new StringBuilder();
                }
            }
        }
        //process negatives
        for(int i=0;i< ExpToken.size();i++){
            if(i==0 && ExpToken.size() > 1 && ExpToken.get(i).equals("-")){
                if( Var.isVarChar(ExpToken.get(i+1).charAt(0))){
                    //处理形如： -a...的式子
//                    operand = new StringBuilder(ExpToken.get(i+1));
//                    operand = operand.insert(0,"-");
//                    ExpToken.set(i+1,operand.toString());
//                    ExpToken.remove(i);
                    ExpToken.add(i,"0");
                    ExpToken.add(i,"(");
                    ExpToken.add(i+4,")");
                }
                else if((ExpToken.get(i+1).charAt(0))=='('){
                    //处理形如：-(...的式子
                    //变成0-(... 不可以
                    //要变成 (0-(...))
                    int j=i+1;
                    while(!ExpToken.get(j).equals(")")){
                        j+=1;
                    }
                    ExpToken.add(i,"0");
                    ExpToken.add(i,"(");
                    ExpToken.add(j+2,")");
                }
            }
            else if((ExpToken.get(i).equals("-") && i != 0 && !Var.isVarChar(ExpToken.get(i-1).charAt(ExpToken.get(i-1).length()-1)))  && !ExpToken.get(i-1).equals(")")&& i != ExpToken.size() - 1 && Var.isVarChar(ExpToken.get(i+1).charAt(0))){
                if(Calculator.isOp(ExpToken.get(i-1))){
                    //处理形如：...*-a...的式子
//                    operand = new StringBuilder(ExpToken.get(i+1));
//                    operand = operand.insert(0,"-");
//                    ExpToken.set(i+1,operand.toString());
//                    ExpToken.remove(i);
                    ExpToken.add(i,"0");
                    ExpToken.add(i,"(");
                    ExpToken.add(i+4,")");
                }
            }
            else if((ExpToken.get(i).equals("-") && i != 0 && !Var.isVarChar(ExpToken.get(i-1).charAt(ExpToken.get(i-1).length()-1)))  && !ExpToken.get(i-1).equals(")")&& i != ExpToken.size() - 1 && ExpToken.get(i+1).charAt(0)=='('){
                if(Calculator.isOp(ExpToken.get(i-1))){
                    //处理形如：...*-(...的式子
                    int j=i+1;
                    while(!ExpToken.get(j).equals(")")){
                        j+=1;
                    }
                    ExpToken.add(i,"0");
                    ExpToken.add(i,"(");
                    ExpToken.add(j+2,")");
                }
            }
        }
    }

    public void toSuffix(){
        symbolClear();
        splitExp();
        ArrayList<String> Sym = new ArrayList<String>();
        for(int i=0;i<ExpToken.size();i++){
            String token = ExpToken.get(i);
            if(isOperand(token)){
                SuffixExp.add(token);
            }
            else if(Calculator.isOp(token)){
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
                    if(Calculator.isOpAGTOpB(token,Sym.get(Sym.size()-1))){
                        Sym.add(token);
                    }
                    else{
                        while(Sym.size()!=0 && (Calculator.OpACMPOpB(token,Sym.get(Sym.size()-1))=='L' || Calculator.OpACMPOpB(token,Sym.get(Sym.size()-1))=='E') && !Sym.get(Sym.size()-1).equals("(")){
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

//    public static String newTemp(){
//        String reg = Register.newRegister();
//
//    }
    public String expCalc() {
//        System.out.println(ExpToken);
//        System.out.println(SuffixExp);
        //returns the number if no temp reg is used
        //or the register of var if only a var is in exp and no temp reg is used
        //returns the register that save the result (the top reg) if needed temp reg to calculate
//        String res = Register.newRegister();
        //简单常数/变量
        if(SuffixExp.size()==1){
            String x = SuffixExp.get(0);
            if(Var.isVar(x)){
                return Var.getRegByVarname(x);
            }
            else if(Calculator.isNum(x)){
                int num = Calculator.toDec(x);
                return ""+num;
            }
        }
        //双目
        ArrayList<String> operands = new ArrayList<String>();
        for (String x : SuffixExp) {
            if(Expression.isOperand(x)){
                operands.add(x);
            }
            else{
                String operand1 = operands.get(operands.size()-1);
                String operand2 = operands.get(operands.size()-2);
                operands.remove(operands.size()-1);
                operands.remove(operands.size()-1);
                String tempRes = new String();
                if(Var.isVar(operand1) && !Var.isVar(operand2)){
                    String reg1 = Var.getRegByVarname(operand1);
                    if(reg1==null) System.exit(3);
                    String tempVar = Register.newRegister();
                    tempRes = Register.newRegister();
                    if(!Register.isReg(operand2)) operand2 = Calculator.toDec(operand2)+"";
                    System.out.println(tempVar+" = load i32, i32* "+reg1);
                    System.out.println(tempRes+" = " + getOpCommand(x) +" i32 "+operand2+", "+tempVar);
                }
                else if(Var.isVar(operand2) && !Var.isVar(operand1)){
                    String reg2 = Var.getRegByVarname(operand2);
                    if(reg2==null) System.exit(3);
                    String tempVar = Register.newRegister();
                    tempRes = Register.newRegister();
                    if(!Register.isReg(operand1)) operand1 = Calculator.toDec(operand1)+"";
                    System.out.println(tempVar+" = load i32, i32* "+reg2);
                    System.out.println(tempRes+" = " + getOpCommand(x) +" i32 "+tempVar+", "+operand1);
                }
                else if(Var.isVar(operand1) && Var.isVar(operand2)){
                    String reg1 = Var.getRegByVarname(operand1);
                    String reg2 = Var.getRegByVarname(operand2);
                    if(reg1==null || reg2==null) System.exit(3);
                    String tempVar1 = Register.newRegister();
                    String tempVar2 = Register.newRegister();
                    tempRes = Register.newRegister();
                    System.out.println(tempVar1+" = load i32, i32* "+reg1);
                    System.out.println(tempVar2+" = load i32, i32* "+reg2);
                    System.out.println(tempRes+" = " + getOpCommand(x) +" i32 "+tempVar2+", "+tempVar1);
                }
                else{
                    if(!Register.isReg(operand1) && !Register.isReg(operand2)){
                        operand1 = Calculator.toDec(operand1)+"";
                        operand2 = Calculator.toDec(operand2)+"";
                    }
                    else if(Register.isReg(operand1) && !Register.isReg(operand2)){
                        operand2 = Calculator.toDec(operand2)+"";
                    }
                    else if(!Register.isReg(operand1) && Register.isReg(operand2)){
                        operand1 = Calculator.toDec(operand1)+"";
                    }
                    tempRes = Register.newRegister();
                    System.out.println(tempRes+" = " + getOpCommand(x) +" i32 "+operand2+", "+operand1);
                }
                operands.add(tempRes);
            }
        }
//        return "%"+Register.getNum();
        return operands.get(0);
    }

    public static String expCalc(ArrayList<String> SuffixExp) {
        //returns the number if no temp reg is used
        //or the register of var if only a var is in exp and no temp reg is used(could be null)
        //returns the register that save the result if needed temp reg to calculate
//        String res = Register.newRegister();
        //简单常数/变量
        if(SuffixExp.size()==1){
            String x = SuffixExp.get(0);
            if(Var.isVar(x)){
                return Var.getRegByVarname(x);
            }
            else if(Calculator.isNum(x)){
                int num = Calculator.toDec(x);
                return ""+num;
            }
        }
        //双目
        ArrayList<String> operands = new ArrayList<String>();
        for (int i=0;i<SuffixExp.size();i++) {
            String x = SuffixExp.get(i);
            if(Func.isRecognizedFunc(x)){
                //TODO :do func and put result back to stack if ret result is num
                // if not then error
                System.out.println("call void @"+x+"(");
            }
            else if(Expression.isOperand(x)){
                operands.add(x);
            }
            else{
                String operand1 = operands.get(operands.size()-1);
                String operand2 = operands.get(operands.size()-2);
                operands.remove(operands.size()-1);
                operands.remove(operands.size()-1);
                String tempRes = new String();
                if(Var.isVar(operand1) && !Var.isVar(operand2)){
                    String reg1 = Var.getRegByVarname(operand1);
                    if(reg1==null) System.exit(3);
                    String tempVar = Register.newRegister();
                    tempRes = Register.newRegister();
                    if(!Register.isReg(operand2)) operand2 = Calculator.toDec(operand2)+"";
                    System.out.println(tempVar+" = load i32, i32* "+reg1);
                    System.out.println(tempRes+" = " + getOpCommand(x) +" i32 "+operand2+", "+tempVar);
                }
                else if(Var.isVar(operand2) && !Var.isVar(operand1)){
                    String reg2 = Var.getRegByVarname(operand2);
                    if(reg2==null) System.exit(3);
                    String tempVar = Register.newRegister();
                    tempRes = Register.newRegister();
                    if(!Register.isReg(operand1)) operand1 = Calculator.toDec(operand1)+"";
                    System.out.println(tempVar+" = load i32, i32* "+reg2);
                    System.out.println(tempRes+" = " + getOpCommand(x) +" i32 "+tempVar+", "+operand1);
                }
                else if(Var.isVar(operand1) && Var.isVar(operand2)){
                    String reg1 = Var.getRegByVarname(operand1);
                    String reg2 = Var.getRegByVarname(operand2);
                    if(reg1==null || reg2==null) System.exit(3);
                    String tempVar1 = Register.newRegister();
                    String tempVar2 = Register.newRegister();
                    tempRes = Register.newRegister();
                    System.out.println(tempVar1+" = load i32, i32* "+reg1);
                    System.out.println(tempVar2+" = load i32, i32* "+reg2);
                    System.out.println(tempRes+" = " + getOpCommand(x) +" i32 "+tempVar2+", "+tempVar1);
                }
                else{
                    if(!Register.isReg(operand1) && !Register.isReg(operand2)){
                        operand1 = Calculator.toDec(operand1)+"";
                        operand2 = Calculator.toDec(operand2)+"";
                    }
                    else if(Register.isReg(operand1) && !Register.isReg(operand2)){
                        operand2 = Calculator.toDec(operand2)+"";
                    }
                    else if(!Register.isReg(operand1) && Register.isReg(operand2)){
                        operand1 = Calculator.toDec(operand1)+"";
                    }
                    tempRes = Register.newRegister();
                    System.out.println(tempRes+" = " + getOpCommand(x) +" i32 "+operand2+", "+operand1);
                }
                operands.add(tempRes);
            }
        }
//        return "%"+Register.getNum();
        return operands.get(0);
    }

    public static String getOpCommand(String Op){
        if(Op.equals("+")){
            return "add";
        }
        else if(Op.equals("-")) return "sub";
        else if(Op.equals("*")) return "mul";
        else if(Op.equals("/")) return "sdiv";
        else return "srem";
        //else if(Op.equals("%")) return ""+(number1%number2);
    }

    public static void main(String[] args) {
        Expression testa = new Expression("1%2");
        testa.toSuffix();
        System.out.println(testa.ExpToken);
        System.out.println(testa.SuffixExp);
        System.out.println(testa.expCalc());
    }

    public void appendInfix(String s){
        this.InfixExp+=s;
    }

    public String getInfixExp(){
        return this.InfixExp;
    }
}
