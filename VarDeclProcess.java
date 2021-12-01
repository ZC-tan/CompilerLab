import java.util.Dictionary;
import java.util.HashMap;

public class VarDeclProcess {
    public static HashMap<String,String> varsReg= new HashMap<String,String>();
    String varname;
    String vartype = new String("int");
    String reg;

    public VarDeclProcess(String varname){
        this.varname = varname;
        this.reg = Register.newRegister();
        varsReg.put(varname,this.reg);
        printIR();
    }

    public VarDeclProcess(String varname, String vartype){
        this.varname = varname;
        this.reg = Register.newRegister();
        varsReg.put(varname,this.reg);
        printIR();
    }

    public void printIR(){
        String IRtype = new String();
        if(vartype.equals("int")){
            IRtype = "i32";
        };
        System.out.println(this.reg + " = alloca "+IRtype);
    }

}
