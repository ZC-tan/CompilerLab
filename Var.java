import java.util.ArrayList;
import java.util.HashMap;

public class Var {
    public static HashMap<String,String> varsReg= new HashMap<String,String>();
    public static ArrayList<Var> vars = new ArrayList<Var>();
    boolean isConst = false;
    String varname;
    String vartype = new String("int");
    String reg;

    public static int tempCount=0;

    //Constructor
    private Var(){

    }

    public Var(String varname){
        this.varname = varname;
        this.reg = Register.newRegister();
        varsReg.put(varname,this.reg);
        vars.add(this);
    }

    public Var(String varname, String vartype){
        this.varname = varname;
        this.vartype = vartype;
        this.reg = Register.newRegister();
        varsReg.put(varname,this.reg);
        vars.add(this);
    }

    public Var(String varname, String vartype, boolean isConst){
        this.varname = varname;
        this.vartype = vartype;
        if(isConst == true)        this.isConst = true;
        this.reg = Register.newRegister();
        varsReg.put(varname,this.reg);
        vars.add(this);
    }

    public static Var newTempVar(String vartype, String reg){
        Var tempVar = new Var();
        tempVar.varname = "temp"+tempCount;
        tempVar.vartype = vartype;
        tempVar.reg = reg;
        varsReg.put(tempVar.varname,reg);
        vars.add(tempVar);
        tempCount++;
        return tempVar;
    }

    //Declaration
    public void varDecl(){
        printVarDeclIR();
    }
    public void printVarDeclIR(){
        String IRtype = new String();
        if(vartype.equals("int")){
            IRtype = "i32";
        }
        System.out.println(this.reg + " = alloca "+IRtype);
//        System.out.println(Var.getRegByVarname(this.varname) + " = alloca "+IRtype);
    }

    //Init
    public void VarInit(){

    }

    public static void printInitValIR(String varName,String initExp){
        String reg = getRegByVarname(varName);
        Expression exp = new Expression(initExp);
        exp.toSuffix();
        System.out.println("store i32 "+exp.expCalc()+", i32* "+reg);
    }

    //Assign Value(Statement, not Init)
    //need to check if it is const, if cosnt exit program
    public static void assignVar(String varName,String assignExp){
        printVarAssignIR(varName,assignExp);
    }

    public static void printVarAssignIR(String varName,String assignExp){
        Var var = getVarByVarname(varName);
        if(var.isConst == false){
            Expression exp = new Expression(assignExp);
            exp.toSuffix();
            String assignValue = exp.expCalc();
            if(Var.varsReg.containsValue(assignValue)){
                String tempRet = Register.newRegister();
                System.out.println(tempRet+" = load i32, i32* "+assignValue);
                System.out.println("store i32 "+tempRet+", i32* "+var.reg);
            }
            else System.out.println("store i32 "+exp.expCalc()+", i32* "+var.reg);
        }
        else{
            System.exit(2);
        }
    }


    public static boolean isVar(String x){
        boolean isvar = true;
        if(!isVarFirstChar(x.charAt(0))){
            isvar = false;
        }
        for(int i=1;i<x.length();i++){
            if(!isVarChar(x.charAt(i))){
                isvar = false;
                break;
            }
        }
        return isvar;
    }

    public static boolean isVarOrReg(String x){
        boolean isvar = true;
        boolean isreg = true;
        if(!isVarFirstChar(x.charAt(0))){
            isvar = false;
        }
        for(int i=1;i<x.length();i++){
            if(!isVarChar(x.charAt(i))){
                isvar = false;
                break;
            }
        }

        if(!Register.isReg(x)) isreg = false;

        return isvar || isreg;
    }

    public static boolean isVarFirstChar(char x){
        boolean varFirstChar = true;
        if(x!='_'&& !Character.isLetter(x)){
            varFirstChar = false;
        }
        return varFirstChar;
    }

    public static boolean isVarChar(char x){
        boolean varChar = true;
        if(x!='_'&& !Character.isLetter(x)&&!Character.isDigit(x)){
            varChar = false;
        }
        return varChar;
    }

    public static String getRegByVarname(String varName){
        return varsReg.get(varName);
    }

    public static Var getVarByVarname(String varName){
        for(Var var:vars){
            if(var.varname.equals(varName)){
                return var;
            }
        }
        return null;
    }

}
