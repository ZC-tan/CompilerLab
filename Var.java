import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Var {
    public static ArrayList<ArrayList<Var>> vars = new ArrayList<>();
    boolean isGlobal = false;
    int globalVal;
    boolean isConst = false;
    String varname;
    String vartype ="int";
    String reg;

    //    public static HashMap<String,String> varsReg= new HashMap<String,String>();
    public static ArrayList<HashMap<String,String>> varTable = new ArrayList<>();
    public static int newBlockId=0;
    public static Stack<Integer> blockStack = new Stack<>();
    int block=0;

    static{
        //block0: to put global variables
        blockStack.add(0);
        Var.varTable.add(new HashMap<String,String>());
        Var.vars.add(new ArrayList<Var>());
    }

    //Constructor
    private Var(){

    }

//    public Var(String varname){
//        this.varname = varname;
//        this.reg = Register.newRegister();
//        varTable.get(this.block).put(varname,this.reg);
//        vars.add(this);
//    }

    public Var(String varname, String vartype){
//        if(getVarByVarname(varname)!=null){
//            System.exit(6);
//        }
        this.varname = varname;
        this.vartype = vartype;
        this.reg = Register.newRegister();
        this.block = curBlock();
        varTable.get(this.block).put(varname,this.reg);
        vars.get(this.block).add(this);
    }

    public Var(String varname, String vartype, boolean isConst){
//        if(getVarByVarname(varname)!=null){
//            System.exit(6);
//        }
        this.varname = varname;
        this.vartype = vartype;
        if(isConst == true) this.isConst = true;
        this.reg = Register.newRegister();
        this.block = curBlock();
        varTable.get(this.block).put(varname,this.reg);
        vars.get(this.block).add(this);
    }

    public Var(String varname, String vartype, boolean isConst,boolean isGlobal){
//        if(getVarByVarname(varname)!=null){
//            System.exit(6);
//        }
        this.varname = varname;
        this.vartype = vartype;
        if(isConst == true) this.isConst = true;
        if(isGlobal) this.isGlobal = true;
        this.reg = "@"+varname;
        this.block = curBlock();
        varTable.get(this.block).put(varname,this.reg);
        vars.get(this.block).add(this);
    }

//    public Var(String varname, String vartype, int block){
//        if(getVarByVarname(varname)!=null){
//            System.exit(6);
//        }
//        this.varname = varname;
//        this.vartype = vartype;
//        this.reg = Register.newRegister();
//        this.block = block;
//        varTable.get(this.block).put(varname,this.reg);
//        vars.add(this);
//    }
//
//    public Var(String varname, String vartype, boolean isConst,int block){
//        if(getVarByVarname(varname)!=null){
//            System.exit(6);
//        }
//        this.varname = varname;
//        this.vartype = vartype;
//        if(isConst == true) this.isConst = true;
//        this.reg = Register.newRegister();
//        this.block = block;
//        varTable.get(this.block).put(varname,this.reg);
//        vars.add(this);
//    }


    //Declaration
    public void varDecl(){
        printVarDeclIR();
    }
    public void printVarDeclIR(){
        String IRtype = new String();
        if(vartype.equals("int")){
            IRtype = "i32";
        }
//        System.out.println(this.reg + " = alloca "+IRtype);
        IR.toPrint.add(this.reg + " = alloca "+IRtype);
//        System.out.println(Var.getRegByVarname(this.varname) + " = alloca "+IRtype);
    }
    public void printGlobalDeclIR(String initVal){
        String IRtype = new String();
        if(vartype.equals("int")){
            IRtype = "i32";
        }
        IR.toPrint.add(0,this.reg + " = dso_local global "+IRtype+" "+initVal);
        this.globalVal = Integer.parseInt(initVal);
    }

    //Init
    public void VarInit(){

    }

    public static void printInitValIR(String varName,String initExp){
        String reg = getRegByVarname(varName);
        Expression exp = new Expression(initExp);
//        System.out.println("store i32 "+exp.expCalc()+", i32* "+reg);
        String initReg = exp.expCalc();
//        if(getVarByVarname(varName).block==0){//if is global
//            for(String token:exp.getSuffixExp()){
//                if(isVar(token)){
//                    if(!getVarByVarname(token).isConst){
//                        System.exit(11);
//                    }
//                }
//            }
//        }
        IR.toPrint.add("store i32 "+initReg+", i32* "+reg);
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
            String assignValue = exp.expCalc();
            if(existsVarRegInOutern(assignValue)){
                String tempRet = Register.newRegister();
//                System.out.println(tempRet+" = load i32, i32* "+assignValue);
//                System.out.println("store i32 "+tempRet+", i32* "+var.reg);
                IR.toPrint.add(tempRet+" = load i32, i32* "+assignValue);
                IR.toPrint.add("store i32 "+tempRet+", i32* "+var.reg);
            }
            else{
//                System.out.println("store i32 "+exp.expCalc()+", i32* "+var.reg);
                IR.toPrint.add("store i32 "+assignValue+", i32* "+var.reg);
            }
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

//    public static boolean isVarOrReg(String x){
//        boolean isvar = true;
//        boolean isreg = true;
//        if(!isVarFirstChar(x.charAt(0))){
//            isvar = false;
//        }
//        for(int i=1;i<x.length();i++){
//            if(!isVarChar(x.charAt(i))){
//                isvar = false;
//                break;
//            }
//        }
//
//        if(!Register.isReg(x)) isreg = false;
//
//        return isvar || isreg;
//    }

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

    public static boolean existsVarRegInOutern(String reg){
        int near;
        for(near=blockStack.size()-1;near>=0;near--){
            int block=blockStack.get(near);
            if(varTable.get(block).containsValue(reg)){
                return true;
            }
        }
        return false;
    }

    public static String getRegByVarname(String varName){
        //get var's reg with same varname within nearest block
        int near;
        for(near=blockStack.size()-1;near>=0;near--){
            int block=blockStack.get(near);
            if(isVarExistsinBlock(varName,block)){
                return getVarRegInBlock(varName,block);
            }
        }
        return null;
    }

    public static String getVarRegInBlock(String varName,int block){
        HashMap<String,String> varsRegs = varTable.get(block);
        return varsRegs.get(varName);
    }

    public static Var getVarByVarname(String varName){
        //get var with same varname in nearest block
        int near;
        for(near=blockStack.size()-1;near>=0;near--){
            int block=blockStack.get(near);
            if(isVarExistsinBlock(varName,block)){
                for(Var var:vars.get(block)){
                    if(var.varname.equals(varName)){
                        return var;
                    }
                }
            }
        }
        return null;
    }

    public static String loadVar(String varName){
        String varReg = getRegByVarname(varName);
        if(varReg==null) System.exit(9);
        return varReg;
    }

    public static boolean isVarExistsinBlock(String varname,int block){
        boolean flag=false;
        HashMap<String,String> varsRegs = varTable.get(block);
        if(varsRegs.containsKey(varname)){
            flag =true;
        }
        return flag;
    }

    public static int curBlock(){
        return blockStack.peek();
    }

    public static void main(String[] args) {
        newBlockId++;
        blockStack.push(newBlockId);
        Var.varTable.add(new HashMap<String,String>());
        Var a = new Var("a","int");
        System.out.println(getRegByVarname("a"));
        Var b = new Var("b","int");
        System.out.println(getRegByVarname("b"));
    }
}
