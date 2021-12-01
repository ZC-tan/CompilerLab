import java.util.ArrayList;

public class Register {
    public static ArrayList<String> registers = new ArrayList<String>();
    private static int num = 0;

    public static String newRegister(){
        num++;
        return "%"+num;
    }

    public static boolean isReg(String x){
        if(x.startsWith("%")) return true;
        else return false;
    }

    public static int getNum() {
        return num;
    }
}
