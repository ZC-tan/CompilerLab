import java.util.ArrayList;
import java.util.HashMap;

public class Expression {
    private String InfixExp;
    private String SymClearedInfixExp;
    private ArrayList<String> ExpToken = new ArrayList<String>();
    private ArrayList<String> SuffixExp = new ArrayList<String>();
    private String calcProcess;
    private HashMap<String,String> temp = new HashMap<String,String>();

    public Expression(){};

    public Expression(String Infix){
        InfixExp = Infix;
    }

    public String symbolClear(){
        StringBuilder symbolCleared = new StringBuilder("");
        int negCount = 0;
        int notCount = 0;
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
            if(InfixExp.charAt(i)=='!'){
                for(int j=i;j<InfixExp.length();j++){
                    if(InfixExp.charAt(j)=='!'){
                        notCount++;
                    }
                    if(InfixExp.charAt(j)!='!'){
                        if(notCount%2==1){
                            symbolCleared.append("!");
                        }
                        i=j;
                        notCount=0;
                        break;
                    }
                }
            }
            if(InfixExp.charAt(i)!='-' && InfixExp.charAt(i)!='+' && InfixExp.charAt(i)!='!'){
                symbolCleared.append(InfixExp.charAt(i));
            }
        }
        SymClearedInfixExp = symbolCleared.toString();
//        System.out.println(SymClearedInfixExp);
        return SymClearedInfixExp;
    }

    public void splitExp(){
        StringBuilder operand = new StringBuilder();
        for(int i=0;i<SymClearedInfixExp.length();i++){
            if(Calculator.isArithmeticOp(SymClearedInfixExp.substring(i,i+1))){
                //运算符（+,-,*,/,%）是独立的token
                ExpToken.add(SymClearedInfixExp.charAt(i)+"");
            }
            else if(Cond.isStartWithRelOp(SymClearedInfixExp.substring(i,i+1))){
                //遇到 条件表达式符号
                if(Cond.isCondOp(SymClearedInfixExp.substring(i,i+2))){
                    //两个符号的条件运算符 >= <= == != && ||
                    ExpToken.add(SymClearedInfixExp.substring(i,i+2));
                    i+=1;
                }
                else{
                    //一个符号的条件运算符 > < !
                    ExpToken.add(SymClearedInfixExp.charAt(i)+"");
                }
            }
            else if(SymClearedInfixExp.charAt(i)=='\\' && SymClearedInfixExp.charAt(i+1)=='%'){
                //遇到寄存器 \%x
                operand.append("%");
                i+=2;
                while(i<SymClearedInfixExp.length() && isOperand(SymClearedInfixExp.charAt(i)+"")){//运算数(变量 和 常数)
                    operand.append(SymClearedInfixExp.charAt(i));
                    i++;
                }
                ExpToken.add(operand.toString());
                i--;
                operand = new StringBuilder();
            }
            else{
                //遇到立即数/变量
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
//        System.out.println(ExpToken);
        //process negatives and not
        for(int i=0;i< ExpToken.size();i++) {
            if(ExpToken.get(i).equals("-")){
                //确定是负号
                if(i==0 || (!Var.isVarChar(ExpToken.get(i-1).charAt(ExpToken.get(i-1).length()-1)) && !ExpToken.get(i-1).equals(")"))){
                    //先把-后面的括起来
                    ExpToken.add(i+1,"(");
                    //找到(或operand
                    int j=(i+1)+1;
                    while(!ExpToken.get(j).equals("(")&&!isOperand(ExpToken.get(j))){
                        j++;
                    }
                    if(ExpToken.get(j).equals("(")){
                        int k=j+1;
                        while(!ExpToken.get(k).equals(")")){
                            k++;
                        }
                        ExpToken.add(k+1,")");
                        ExpToken.add(i,"0");
                        ExpToken.add(i,"(");
                        ExpToken.add(k+2,")");
                    }
                    else{
                        int k=j;
                        ExpToken.add(k+1,")");
                        ExpToken.add(i,"0");
                        ExpToken.add(i,"(");
                        ExpToken.add(k+3,")");
                    }
                }
            }
            else if(ExpToken.get(i).equals("!")){
                //先把!后面的括起来
                ExpToken.add(i+1,"(");
                //找到(或operand
                int j=(i+1)+1;
                while(!ExpToken.get(j).equals("(")&&!isOperand(ExpToken.get(j))){
                    j++;
                }
                if(ExpToken.get(j).equals("(")){
                    int k=j+1;
                    while(!ExpToken.get(k).equals(")")){
                        k++;
                    }
                    ExpToken.add(k+1,")");
                    ExpToken.remove(i);
                    ExpToken.add(i,"(");

                    ExpToken.add(k+2,"==");
                    ExpToken.add(k+3,"0");
                    ExpToken.add(k+4,")");
                }
                else{
                    int k=j;
                    ExpToken.add(k+1,")");
                    ExpToken.remove(i);
                    ExpToken.add(i,"(");
                    ExpToken.add(k+2,"==");
                    ExpToken.add(k+3,"0");
                    ExpToken.add(k+4,")");
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
                    if(Calculator.OpACMPOpB(token,Sym.get(Sym.size()-1))=='G'){
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


    public String expCalc() {
//        System.out.println(ExpToken);
//        System.out.println(SuffixExp);
        //returns the number if no temp reg is used
        //or the register of var if only a var is in exp and no temp reg is used
        //returns the register that save the result (the top reg) if needed temp reg to calculate
//        String res = Register.newRegister();
        //简单常数/变量
        this.toSuffix();
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
                if(Var.isVar(operand1)){
                    String tempVar = Register.newRegister();
//                    System.out.println(tempVar+" = load i32, i32* "+Var.loadVar(operand1));
                    IR.toPrint.add(tempVar+" = load i32, i32* "+Var.loadVar(operand1));
                    operand1 = tempVar;
                }
                if(Var.isVar(operand2)){
                    String tempVar = Register.newRegister();
//                    System.out.println(tempVar+" = load i32, i32* "+Var.loadVar(operand2));
                    IR.toPrint.add(tempVar+" = load i32, i32* "+Var.loadVar(operand2));
                    operand2 = tempVar;
                }
                if(!Register.isReg(operand1)) operand1 = Calculator.toDec(operand1)+"";
                else{
                    if(Register.getReg(operand1).getType().equals("i1") && (Cond.isRelOp("x")||Calculator.isArithmeticOp(x))){
                        String tempZext = Register.newRegister();
//                        System.out.println(tempZext+" = zext i1 "+operand1+" to i32");
                        IR.toPrint.add(tempZext+" = zext i1 "+operand1+" to i32");
                        operand1 = tempZext;
                    }
                    if(Register.getReg(operand1).getType().equals("i32")&&(Cond.isBoolOp(x))){
                        String tempI1 = Register.newRegister("i1");
                        IR.toPrint.add(tempI1+" = " + "icmp ne i32 "+operand1+", "+0);
                        operand1=tempI1;
                    }
                }
                if(!Register.isReg(operand2)) operand2 = Calculator.toDec(operand2)+"";
                else{
                    if(Register.getReg(operand2).getType().equals("i1")&& (Cond.isRelOp("x")||Calculator.isArithmeticOp(x))){
                        String tempZext = Register.newRegister();
//                        System.out.println(tempZext+" = zext i1 "+operand2+" to i32");
                        IR.toPrint.add(tempZext+" = zext i1 "+operand2+" to i32");
                        operand2 = tempZext;
                    }
                    if(Register.getReg(operand2).getType().equals("i32")&&(Cond.isBoolOp(x))){
                        String tempI1 = Register.newRegister("i1");
                        IR.toPrint.add(tempI1+" = " + "icmp ne i32 "+operand2+", "+0);
                        operand2=tempI1;
                    }
                }

                if(Cond.isCondOp(x)){
                    tempRes = Register.newRegister("i1");
                }
                else tempRes = Register.newRegister();
//                System.out.println(tempRes+" = " + getOpCommand(x)+operand2+", "+operand1);
                IR.toPrint.add(tempRes+" = " + getOpCommand(x)+operand2+", "+operand1);
                operands.add(tempRes);
            }
        }
//        return "%"+Register.getNum();
        return operands.get(0);
    }


    public String expCalc(boolean isCond) {
//        System.out.println(ExpToken);
//        System.out.println(SuffixExp);
        //returns the number if no temp reg is used
        //or the register of var if only a var is in exp and no temp reg is used
        //returns the register that save the result (the top reg) if needed temp reg to calculate
//        String res = Register.newRegister();
        //简单常数/变量
        this.toSuffix();
        if(SuffixExp.size()==1){
            String x = SuffixExp.get(0);
            if(Var.isVar(x)){
                String ret =Var.getRegByVarname(x);
                if(isCond && Register.getReg(ret).getType().equals("i32")){
                    String tempI1Res = Register.newRegister("i1");
                    IR.toPrint.add(tempI1Res+" = " + "icmp ne i32 "+ret+", "+0);
                    ret = tempI1Res;
                }
                return ret;
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
                if(Var.isVar(operand1)){
                    String tempVar = Register.newRegister();
//                    System.out.println(tempVar+" = load i32, i32* "+Var.loadVar(operand1));
                    IR.toPrint.add(tempVar+" = load i32, i32* "+Var.loadVar(operand1));
                    operand1 = tempVar;
                }
                if(Var.isVar(operand2)){
                    String tempVar = Register.newRegister();
//                    System.out.println(tempVar+" = load i32, i32* "+Var.loadVar(operand2));
                    IR.toPrint.add(tempVar+" = load i32, i32* "+Var.loadVar(operand2));
                    operand2 = tempVar;
                }
                if(!Register.isReg(operand1)) operand1 = Calculator.toDec(operand1)+"";
                else{
                    if(Register.getReg(operand1).getType().equals("i1") && (Cond.isRelOp("x")||Calculator.isArithmeticOp(x))){
                        String tempZext = Register.newRegister();
//                        System.out.println(tempZext+" = zext i1 "+operand1+" to i32");
                        IR.toPrint.add(tempZext+" = zext i1 "+operand1+" to i32");
                        operand1 = tempZext;
                    }
                    if(Register.getReg(operand1).getType().equals("i32")&&(Cond.isBoolOp(x))){
                        String tempI1 = Register.newRegister("i1");
                        IR.toPrint.add(tempI1+" = " + "icmp ne i32 "+operand1+", "+0);
                        operand1=tempI1;
                    }
                }
                if(!Register.isReg(operand2)) operand2 = Calculator.toDec(operand2)+"";
                else{
                    if(Register.getReg(operand2).getType().equals("i1")&& (Cond.isRelOp("x")||Calculator.isArithmeticOp(x))){
                        String tempZext = Register.newRegister();
//                        System.out.println(tempZext+" = zext i1 "+operand2+" to i32");
                        IR.toPrint.add(tempZext+" = zext i1 "+operand2+" to i32");
                        operand2 = tempZext;
                    }
                    if(Register.getReg(operand2).getType().equals("i32")&&(Cond.isBoolOp(x))){
                        String tempI1 = Register.newRegister("i1");
                        IR.toPrint.add(tempI1+" = " + "icmp ne i32 "+operand2+", "+0);
                        operand2=tempI1;
                    }
                }

                if(Cond.isCondOp(x)){
                    tempRes = Register.newRegister("i1");
                }
                else tempRes = Register.newRegister();
//                System.out.println(tempRes+" = " + getOpCommand(x)+operand2+", "+operand1);
                IR.toPrint.add(tempRes+" = " + getOpCommand(x)+operand2+", "+operand1);
                operands.add(tempRes);
            }
        }
//        return "%"+Register.getNum();
        if(isCond && Register.getReg(operands.get(0)).getType().equals("i32")){
            String tempI1Res = Register.newRegister("i1");
            IR.toPrint.add(tempI1Res+" = " + "icmp ne i32 "+operands.get(0)+", "+0);
            operands.set(0,tempI1Res);
        }
        return operands.get(0);
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


    public static String getOpCommand(String Op){
        if(Op.equals("+")){
            return "add i32 ";
        }
        else if(Op.equals("-")) return "sub i32 ";
        else if(Op.equals("*")) return "mul i32 ";
        else if(Op.equals("/")) return "sdiv i32 ";
        else if(Op.equals("%")) return "srem i32 ";
        else if(Op.equals("<")) return "icmp slt i32 ";
        else if(Op.equals("<=")) return "icmp sle i32 ";
        else if(Op.equals(">")) return "icmp sgt i32 ";
        else if(Op.equals(">=")) return "icmp sge i32 ";
        else if(Op.equals("==")) return "icmp eq i32 ";
        else if(Op.equals("!=")) return "icmp ne i32 ";
        else if(Op.equals("&&")) return "and i1 ";
        else if(Op.equals("||")) return "or i1 ";
        else return "srem i32 ";
    }

    public void appendInfix(String s){
        this.InfixExp+=s;
    }

    public String getInfixExp(){
        return this.InfixExp;
    }

    public ArrayList<String> getSuffixExp(){
        return this.SuffixExp;
    }

    public ArrayList<String> getExpToken(){
        return this.ExpToken;
    }

    public static void main(String[] args) {
        Expression testa = new Expression("-!2");
        testa.toSuffix();
        System.out.println(testa.ExpToken);
        System.out.println(testa.SuffixExp);
        System.out.println(testa.expCalc());
    }
}

//    public void splitExp(){
//        StringBuilder operand = new StringBuilder();
//        for(int i=0;i<SymClearedInfixExp.length();i++){
//            if(Calculator.isArithmeticOp(SymClearedInfixExp.substring(i,i+1))){
//                //运算符（+,-,*,/,%）是独立的token
//                ExpToken.add(SymClearedInfixExp.charAt(i)+"");
//            }
//            else if(Cond.isStartWithRelOp(SymClearedInfixExp.substring(i,i+1))){
//                //遇到 条件表达式符号
//                if(Cond.isRelOp(SymClearedInfixExp.substring(i,i+2))){
//                    //两个符号的条件运算符 >= <= == != && ||
//                    ExpToken.add(SymClearedInfixExp.substring(i,i+2));
//                    i+=1;
//                }
//                else{
//                    //一个符号的条件运算符 > < !
//                    ExpToken.add(SymClearedInfixExp.charAt(i)+"");
//                }
//            }
//            else if(SymClearedInfixExp.charAt(i)=='\\' && SymClearedInfixExp.charAt(i+1)=='%'){
//                //遇到寄存器 \%x
//                operand.append("%");
//                i+=2;
//                while(i<SymClearedInfixExp.length() && isOperand(SymClearedInfixExp.charAt(i)+"")){//运算数(变量 和 常数)
//                    operand.append(SymClearedInfixExp.charAt(i));
//                    i++;
//                }
//                ExpToken.add(operand.toString());
//                i--;
//                operand = new StringBuilder();
//            }
//            else{
//                //遇到立即数/变量
//                while(i<SymClearedInfixExp.length() && (Var.isVarChar(SymClearedInfixExp.charAt(i)))){//运算数(变量 和 常数)
//                    operand.append(SymClearedInfixExp.charAt(i));
//                    i++;
//                }
//                if(!operand.isEmpty()){
//                    ExpToken.add(operand.toString());
//                    i--;
//                    operand = new StringBuilder();
//                }
//            }
//        }
//        System.out.println(ExpToken);
//        //process negatives
//        for(int i=0;i< ExpToken.size();i++){
//            if(i==0 && ExpToken.size() > 1 && ExpToken.get(i).equals("-")){
//                if( Var.isVarChar(ExpToken.get(i+1).charAt(0))){
//                    //处理形如： -a...的式子
//                    ExpToken.add(i,"0");
//                    ExpToken.add(i,"(");
//                    ExpToken.add(i+4,")");
//                }
//                else if((ExpToken.get(i+1).charAt(0))=='('){
//                    //处理形如：-(...的式子
//                    //变成0-(... 不可以
//                    //要变成 (0-(...))
//                    int j=i+1;
//                    while(!ExpToken.get(j).equals(")")){
//                        j+=1;
//                    }
//                    ExpToken.add(i,"0");
//                    ExpToken.add(i,"(");
//                    ExpToken.add(j+2,")");
//                }
//            }
//            else if((ExpToken.get(i).equals("-") && i != 0 && !Var.isVarChar(ExpToken.get(i-1).charAt(ExpToken.get(i-1).length()-1)))  && !ExpToken.get(i-1).equals(")")&& i != ExpToken.size() - 1 && Var.isVarChar(ExpToken.get(i+1).charAt(0))){
//                if(Calculator.isOp(ExpToken.get(i-1))){
//                    //处理形如：...*-a...的式子
//                    ExpToken.add(i,"0");
//                    ExpToken.add(i,"(");
//                    ExpToken.add(i+4,")");
//                }
//            }
//            else if((ExpToken.get(i).equals("-") && i != 0 && !Var.isVarChar(ExpToken.get(i-1).charAt(ExpToken.get(i-1).length()-1)))  && !ExpToken.get(i-1).equals(")")&& i != ExpToken.size() - 1 && ExpToken.get(i+1).charAt(0)=='('){
//                if(Calculator.isOp(ExpToken.get(i-1))){
//                    //处理形如：...*-(...的式子
//                    int j=i+1;
//                    while(!ExpToken.get(j).equals(")")){
//                        j+=1;
//                    }
//                    ExpToken.add(i,"0");
//                    ExpToken.add(i,"(");
//                    ExpToken.add(j+2,")");
//                }
//            }
//        }
//        //process not  eg. !x->(x==0)
//        for(int i=0;i< ExpToken.size();i++){
//            if(ExpToken.get(i).equals("!") && i != ExpToken.size() - 1 && Var.isVarChar(ExpToken.get(i+1).charAt(0))){
//                //处理形如：...!a...的式子
//                ExpToken.remove(i);
//                ExpToken.add(i,"(");
//                ExpToken.add(i+2,")");
//                ExpToken.add(i+2,"==");
//                ExpToken.add(i+3,"0");
//            }
//            else if(ExpToken.get(i).equals("!")  && i != ExpToken.size() - 1 && ExpToken.get(i+1).charAt(0)=='('){
//                //处理形如：...!(...的式子
//                int j=i+1;
//                while(!ExpToken.get(j).equals(")")){
//                    j+=1;
//                }
//                ExpToken.remove(i);
//                ExpToken.add(i,"(");
//                ExpToken.add(j+1,")");
//                ExpToken.add(j+1,"==");
//                ExpToken.add(j+2,"0");
//            }
//        }
//    }


//    public String expCalc_printLater() {
////        System.out.println(ExpToken);
////        System.out.println(SuffixExp);
//        //returns the number if no temp reg is used
//        //or the register of var if only a var is in exp and no temp reg is used
//        //returns the register that save the result (the top reg) if needed temp reg to calculate
////        String res = Register.newRegister();
//        //简单常数/变量
//        this.toSuffix();
//        if(SuffixExp.size()==1){
//            String x = SuffixExp.get(0);
//            if(Var.isVar(x)){
//                return Var.getRegByVarname(x);
//            }
//            else if(Calculator.isNum(x)){
//                int num = Calculator.toDec(x);
//                return ""+num;
//            }
//        }
//        //双目
//        ArrayList<String> operands = new ArrayList<String>();
//        for (String x : SuffixExp) {
//            if(Expression.isOperand(x)){
//                operands.add(x);
//            }
//            else{
//                String operand1 = operands.get(operands.size()-1);
//                String operand2 = operands.get(operands.size()-2);
//                operands.remove(operands.size()-1);
//                operands.remove(operands.size()-1);
//                String tempRes = new String();
//                if(Var.isVar(operand1)){
//                    String tempVar = Register.newRegister();
//                    this.calcProcess = (tempVar+" = load i32, i32* "+Var.loadVar(operand1));
//                    operand1 = tempVar;
//                }
//                if(Var.isVar(operand2)){
//                    String tempVar = Register.newRegister();
//                    this.calcProcess =(tempVar+" = load i32, i32* "+Var.loadVar(operand2));
//                    operand2 = tempVar;
//                }
//                if(!Register.isReg(operand1)) operand1 = Calculator.toDec(operand1)+"";
//                else{
//                    if(Register.getReg(operand1).getType().equals("i1")){
//                        String tempZext = Register.newRegister();
//                        this.calcProcess =(tempZext+" = zext i1 "+operand1+" to i32");
//                        operand1 = tempZext;
//                    }
//                }
//                if(!Register.isReg(operand2)) operand2 = Calculator.toDec(operand2)+"";
//                else{
//                    if(Register.getReg(operand2).getType().equals("i1")){
//                        String tempZext = Register.newRegister();
//                        this.calcProcess =(tempZext+" = zext i1 "+operand2+" to i32");
//                        operand2 = tempZext;
//                    }
//                }
//
//                if(Cond.isRelOp(x)){
//                    tempRes = Register.newRegister("i1");
//                }
//                else tempRes = Register.newRegister();
//                this.calcProcess =(tempRes+" = " + getOpCommand(x)+operand2+", "+operand1);
//                operands.add(tempRes);
//            }
//        }
////        return "%"+Register.getNum();
//        return operands.get(0);
//    }


//    public String expCalc() {
////        System.out.println(ExpToken);
////        System.out.println(SuffixExp);
//        //returns the number if no temp reg is used
//        //or the register of var if only a var is in exp and no temp reg is used
//        //returns the register that save the result (the top reg) if needed temp reg to calculate
////        String res = Register.newRegister();
//        //简单常数/变量
//        this.toSuffix();
//        if(SuffixExp.size()==1){
//            String x = SuffixExp.get(0);
//            if(Var.isVar(x)){
//                return Var.getRegByVarname(x);
//            }
//            else if(Calculator.isNum(x)){
//                int num = Calculator.toDec(x);
//                return ""+num;
//            }
//        }
//        //双目
//        ArrayList<String> operands = new ArrayList<String>();
//        for (String x : SuffixExp) {
//            if(Expression.isOperand(x)){
//                operands.add(x);
//            }
//            else{
//                String operand1 = operands.get(operands.size()-1);
//                String operand2 = operands.get(operands.size()-2);
//                operands.remove(operands.size()-1);
//                operands.remove(operands.size()-1);
//                String tempRes = new String();
//                if(Var.isVar(operand1) && !Var.isVar(operand2)){
//                    String reg1 = Var.getRegByVarname(operand1);
//                    if(reg1==null) System.exit(3);
//                    String tempVar = Register.newRegister();
//                    tempRes = Register.newRegister();
//                    if(!Register.isReg(operand2)) operand2 = Calculator.toDec(operand2)+"";
//                    System.out.println(tempVar+" = load i32, i32* "+reg1);
//                    System.out.println(tempRes+" = " + getOpCommand(x) +" i32 "+operand2+", "+tempVar);
//                }
//                else if(Var.isVar(operand2) && !Var.isVar(operand1)){
//                    String reg2 = Var.getRegByVarname(operand2);
//                    if(reg2==null) System.exit(3);
//                    String tempVar = Register.newRegister();
//                    tempRes = Register.newRegister();
//                    if(!Register.isReg(operand1)) operand1 = Calculator.toDec(operand1)+"";
//                    System.out.println(tempVar+" = load i32, i32* "+reg2);
//                    System.out.println(tempRes+" = " + getOpCommand(x) +" i32 "+tempVar+", "+operand1);
//                }
//                else if(Var.isVar(operand1) && Var.isVar(operand2)){
//                    String reg1 = Var.getRegByVarname(operand1);
//                    String reg2 = Var.getRegByVarname(operand2);
//                    if(reg1==null || reg2==null) System.exit(3);
//                    String tempVar1 = Register.newRegister();
//                    String tempVar2 = Register.newRegister();
//                    tempRes = Register.newRegister();
//                    System.out.println(tempVar1+" = load i32, i32* "+reg1);
//                    System.out.println(tempVar2+" = load i32, i32* "+reg2);
//                    System.out.println(tempRes+" = " + getOpCommand(x) +" i32 "+tempVar2+", "+tempVar1);
//                }
//                else{
//                    if(!Register.isReg(operand1) && !Register.isReg(operand2)){
//                        operand1 = Calculator.toDec(operand1)+"";
//                        operand2 = Calculator.toDec(operand2)+"";
//                    }
//                    else if(Register.isReg(operand1) && !Register.isReg(operand2)){
//                        operand2 = Calculator.toDec(operand2)+"";
//                    }
//                    else if(!Register.isReg(operand1) && Register.isReg(operand2)){
//                        operand1 = Calculator.toDec(operand1)+"";
//                    }
//                    tempRes = Register.newRegister();
//                    System.out.println(tempRes+" = " + getOpCommand(x) +" i32 "+operand2+", "+operand1);
//                }
//                operands.add(tempRes);
//            }
//        }
////        return "%"+Register.getNum();
//        return operands.get(0);
//    }


//    public String expCalc() {
////        System.out.println(ExpToken);
////        System.out.println(SuffixExp);
//        //returns the number if no temp reg is used
//        //or the register of var if only a var is in exp and no temp reg is used
//        //returns the register that save the result (the top reg) if needed temp reg to calculate
////        String res = Register.newRegister();
//        //简单常数/变量
//        this.toSuffix();
//        if(SuffixExp.size()==1){
//            String x = SuffixExp.get(0);
//            if(Var.isVar(x)){
//                return Var.getRegByVarname(x);
//            }
//            else if(Calculator.isNum(x)){
//                int num = Calculator.toDec(x);
//                return ""+num;
//            }
//        }
//        //双目
//        ArrayList<String> operands = new ArrayList<String>();
//        for (String x : SuffixExp) {
//            if(Expression.isOperand(x)){
//                operands.add(x);
//            }
//            else{
//                String operand1 = operands.get(operands.size()-1);
//                String operand2 = operands.get(operands.size()-2);
//                operands.remove(operands.size()-1);
//                operands.remove(operands.size()-1);
//                String tempRes = new String();
//                if(Var.isVar(operand1) && !Var.isVar(operand2)){
//                    //找到存储变量的寄存器(指针)
//                    String reg1 = Var.getRegByVarname(operand1);
//                    if(reg1==null) System.exit(3);
//                    //load 变量
//                    String tempVar = Register.newRegister();
//                    System.out.println(tempVar+" = load i32, i32* "+reg1);
//
//                    //判断中间变量类型
//                    if(!Register.isReg(operand2)) {//是常数，变成十进制
//                        operand2 = Calculator.toDec(operand2)+"";
//                        //判断中间结果类型
//                        if(RelationalExp.isRelOp(x)){
//                            tempRes = Register.newRegister("i1");
//                        }
//                        else tempRes = Register.newRegister();
//                        System.out.println(tempRes+" = " + getOpCommand(x) +operand2+", "+tempVar);
//                    }
//                    else{//是i1，需要extend
//                        if(Register.getReg(operand2).getType().equals("i1")){
//                            String tempZext = Register.newRegister();
//                            System.out.println(tempZext+" = zext i1 "+operand2+" to i32");
//                            if(RelationalExp.isRelOp(x)){
//                                tempRes = Register.newRegister("i1");
//                            }
//                            else tempRes = Register.newRegister();
//                            System.out.println(tempRes+" = " + getOpCommand(x) +tempZext+", "+tempVar);
//                        }
//                    }
//                }
//                else if(Var.isVar(operand2) && !Var.isVar(operand1)){
//
//                    String reg2 = Var.getRegByVarname(operand2);
//                    if(reg2==null) System.exit(3);
//
//                    String tempVar = Register.newRegister();
//                    System.out.println(tempVar+" = load i32, i32* "+reg2);
//
//                    if(!Register.isReg(operand1)) {
//                        operand1 = Calculator.toDec(operand1)+"";
//                        if(RelationalExp.isRelOp(x)){
//                            tempRes = Register.newRegister("i1");
//                        }
//                        else tempRes = Register.newRegister();
//                        System.out.println(tempRes+" = " + getOpCommand(x) +tempVar+", "+operand1);
//                    }
//                    else{
//                        if(Register.getReg(operand1).getType().equals("i1")){
//                            String tempZext = Register.newRegister();
//                            System.out.println(tempZext+" = zext i1 "+operand1+" to i32");
//                            if(RelationalExp.isRelOp(x)){
//                                tempRes = Register.newRegister("i1");
//                            }
//                            else tempRes = Register.newRegister();
//                            System.out.println(tempRes+" = " + getOpCommand(x) +tempVar+", "+tempZext);
//                        }
//                    }
//                }
//                else if(Var.isVar(operand1) && Var.isVar(operand2)){
//                    String reg1 = Var.getRegByVarname(operand1);
//                    String reg2 = Var.getRegByVarname(operand2);
//                    if(reg1==null || reg2==null) System.exit(3);
//
//                    String tempVar1 = Register.newRegister();
//                    String tempVar2 = Register.newRegister();
//                    System.out.println(tempVar1+" = load i32, i32* "+reg1);
//                    System.out.println(tempVar2+" = load i32, i32* "+reg2);
//
//                    if(RelationalExp.isRelOp(x)){
//                        tempRes = Register.newRegister("i1");
//                    }
//                    else tempRes = Register.newRegister();
//
//                    System.out.println(tempRes+" = " + getOpCommand(x)+tempVar2+", "+tempVar1);
//                }
//                else{
//                    if(!Register.isReg(operand1) && !Register.isReg(operand2)){
//                        operand1 = Calculator.toDec(operand1)+"";
//                        operand2 = Calculator.toDec(operand2)+"";
//                        if(RelationalExp.isRelOp(x)){
//                            tempRes = Register.newRegister("i1");
//                        }
//                        else tempRes = Register.newRegister();
//                        System.out.println(tempRes+" = " + getOpCommand(x) +operand2+", "+operand1);
//                    }
//                    else if(Register.isReg(operand1) && !Register.isReg(operand2)){
//                        operand2 = Calculator.toDec(operand2)+"";
//                        if(Register.getReg(operand1).getType().equals("i1")){
//                            String tempZext = Register.newRegister();
//                            System.out.println(tempZext+" = zext i1 "+operand1+" to i32");
//                            if(RelationalExp.isRelOp(x)){
//                                tempRes = Register.newRegister("i1");
//                            }
//                            else tempRes = Register.newRegister();
//                            System.out.println(tempRes+" = " + getOpCommand(x) +operand2+", "+tempZext);
//                        }
//                        else{
//                            if(RelationalExp.isRelOp(x)){
//                                tempRes = Register.newRegister("i1");
//                            }
//                            else tempRes = Register.newRegister();
//                            System.out.println(tempRes+" = " + getOpCommand(x) +operand2+", "+operand1);
//                        }
//                    }
//                    else if(!Register.isReg(operand1) && Register.isReg(operand2)){
//                        operand1 = Calculator.toDec(operand1)+"";
//                        if(Register.getReg(operand2).getType().equals("i1")){
//                            String tempZext = Register.newRegister();
//                            System.out.println(tempZext+" = zext i1 "+operand2+" to i32");
//                            if(RelationalExp.isRelOp(x)){
//                                tempRes = Register.newRegister("i1");
//                            }
//                            else tempRes = Register.newRegister();
//                            System.out.println(tempRes+" = " + getOpCommand(x) +tempZext+", "+operand1);
//                        }
//                        else{
//                            if(RelationalExp.isRelOp(x)){
//                                tempRes = Register.newRegister("i1");
//                            }
//                            else tempRes = Register.newRegister();
//                            System.out.println(tempRes+" = " + getOpCommand(x) +operand2+", "+operand1);
//                        }
//                    }
//                    else{
//                        if(Register.getReg(operand1).getType().equals("i1")){
//                            String tempZext = Register.newRegister();
//                            System.out.println(tempZext+" = zext i1 "+operand1+" to i32");
//                            if(RelationalExp.isRelOp(x)){
//                                tempRes = Register.newRegister("i1");
//                            }
//                            else tempRes = Register.newRegister();
//                            System.out.println(tempRes+" = " + getOpCommand(x) +operand2+", "+tempZext);
//                        }
//                        if(Register.getReg(operand2).getType().equals("i1")){
//                            String tempZext = Register.newRegister();
//                            System.out.println(tempZext+" = zext i1 "+operand2+" to i32");
//                            if(RelationalExp.isRelOp(x)){
//                                tempRes = Register.newRegister("i1");
//                            }
//                            else tempRes = Register.newRegister();
//                            System.out.println(tempRes+" = " + getOpCommand(x) +operand2+", "+operand1);
//                        }
//                        else{
//                            if(RelationalExp.isRelOp(x)){
//                                tempRes = Register.newRegister("i1");
//                            }
//                            else tempRes = Register.newRegister();
//                            System.out.println(tempRes+" = " + getOpCommand(x) +operand2+", "+operand1);
//                        }
//                    }
//                }
//                operands.add(tempRes);
//            }
//        }
////        return "%"+Register.getNum();
//        return operands.get(0);
//    }
