public class Type {
    public static String typeToIR(String type){
        if(type.equals("int")){
            return "i32";
        }
        else if(type.equals("void")){
            return "void";
        }
        return null;
    }
}
