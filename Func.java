import java.util.ArrayList;
import java.util.HashMap;

public class Func {
    public static ArrayList<Func> recFunc = new ArrayList<>();
    private String funcName;
    private String retType;
    private int paramCount;
    private String[] paramType;

    static{
        recFunc.add(new Func("getint","int",0,new String[]{}));
        recFunc.add(new Func("getch","int",0,new String[]{}));
        recFunc.add(new Func("getarray","int",1,new String[]{"int[]"}));
        recFunc.add(new Func("putint","void",1,new String[]{"int"}));
        recFunc.add(new Func("putch","void",1,new String[]{"int"}));
        recFunc.add(new Func("putarray","void",2,new String[]{"int","int[]"}));
    }

    public Func(String funcName,String retType, int paramCount, String[] paramType){
        this.funcName = funcName;
        this.paramCount = paramCount;
        this.retType = retType;
        this.paramType = paramType;
    }

    public Func(){

    }

    public static void printMainFuncRetIR(String retExp){
        //have to see if is var or num
        //if var then load
        Expression retStat = new Expression(retExp);
        retStat.toSuffix();
        String ret = retStat.expCalc();
        if(ret == null){
            System.exit(3);
        }

        if(Var.varsReg.containsValue(ret)){
            String tempRet = Register.newRegister();
            System.out.println(tempRet+" = load i32, i32* "+ret);
            System.out.println("ret i32 "+tempRet);
        }
        else if(Calculator.isNum(ret)){
            System.out.println("ret i32 "+ret);
        }
    }

    public static void doStmtIR(){

    }

    public static boolean isRecognizedFunc(String ident){
        for(Func f: recFunc){
            if(ident.equals(f.funcName)){
                return true;
            }
        }
        return false;
    }

    public static Func getFuncByName(String funcName){
        for(Func x:recFunc){
            if(x.funcName.equals(funcName)){
                return x;
            }
        }
        return null;
    }

    public static boolean isValidFuncParamCount(String funcName, int count){
        Func func = getFuncByName(funcName);
        if(func.paramCount==count){
            return true;
        }
        else return false;
    }

    public static String callFunc(String funcName,ArrayList<String> Params){
        Func f = getFuncByName(funcName);

        if(f.retType.equals("int")){
            String ret = Register.newRegister();
            System.out.print(ret + " = ");
            printCallFuncIR(funcName,Params);
            return ret;
        }
        printCallFuncIR(funcName,Params);
        return null;
    }
    public static void printCallFuncIR(String funcName,ArrayList<String> Params){
        int paramCount = Params.size();
        if(!isValidFuncParamCount(funcName,paramCount)){
            System.exit(5);
        }
        Func f = getFuncByName(funcName);

        String params = new String();
        for(int i=0;i<Params.size();i++){
            if(Var.varsReg.containsValue(Params.get(i))){
                String tempRet = Register.newRegister();
                System.out.println(tempRet+" = load i32, i32* "+Params.get(i));
                params+=Type.typeToIR(f.paramType[i])+" "+tempRet;
            }
            else{
                params+= Type.typeToIR(f.paramType[i])+" "+Params.get(i);
            }
            if(i!=Params.size()-1){
                params+=",";
            }
        }
        System.out.println("call "+Type.typeToIR(f.retType) + " @"+f.funcName+"("
                +params+")"
        );
    }
}
