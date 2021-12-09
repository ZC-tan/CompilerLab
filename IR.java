import java.util.ArrayList;

public class IR {
    public static ArrayList<String> toPrint= new ArrayList<>();

    public static void appendLast(String x){
        int lastIndex = toPrint.size()-1;
        String toChange = toPrint.get(lastIndex);
        toChange=toChange+x;
        toPrint.set(lastIndex,toChange);
    }

    public static void replaceAtIndex(int index,String oldString,String newString){
        String toChange = toPrint.get(index);
        String changed = toChange.replace(oldString,newString);
        toPrint.set(index,changed);
    }

    public static int nextQuad(){
        return toPrint.size();
    }
}
