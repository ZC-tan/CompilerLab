import java.util.ArrayList;

public class Register {
    public static ArrayList<Register> registers = new ArrayList<Register>();
    private static int num = 0;
    private static int top_label_num=0;
    private int id=0;
    private String type = "int";

    public Register(){
        this.id=num;
    }

    public Register(String type){
        this.id=num;
        this.type=type;
    }

    public static String newRegister(){
        num++;
        registers.add(new Register());
        return "%reg"+num;
    }

    public static String newRegister(String type){
        num++;
        registers.add(new Register(type));
        return "%reg"+num;
    }

    public static String newBlock(){
        top_label_num++;
        return "%label"+top_label_num;
    }

    public static boolean isReg(String x){
        if(x.startsWith("%") && x.length()>1) return true;
        else return false;
    }

    public static Register getReg(String x){
        int i;
        for(i=1;i<x.length();i++){
            if(Character.isDigit(x.charAt(i))){
                break;
            }
        }
        int num = Integer.parseInt(x.substring(i));
        for(Register reg : registers){
            if(reg.id == num){
                return reg;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
