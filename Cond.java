import java.util.ArrayList;

public class Cond{
    public static ArrayList<Cond> conds= new ArrayList<Cond>();
    private int label_id;
    public ArrayList<String> true_list = new ArrayList<>();
    public ArrayList<String> false_list = new ArrayList<>();


    public static boolean isStartWithRelOp(String x){
        return x.startsWith("<")||x.startsWith(">")||x.startsWith("=")||x.startsWith("!")
                ||x.startsWith("&")||x.startsWith("|");
    }

    public static boolean isRelOp(String x){
        return x.equals("<")||x.equals(">")||x.equals(">=")||x.equals("<=")
                ||x.equals("==")||x.equals("!=")||x.equals("!")
                ||x.equals("&&")||x.equals("||");
    }

    public void backpatch(String x,boolean trueOrFalse){
        if(trueOrFalse==true){
            for(int i=0;i<true_list.size();i++){
                int index = Integer.parseInt(true_list.get(i));
                IR.replaceAtIndex(index,"@TC",x);
            }
        }
        else {
            for (int i = 0; i < false_list.size(); i++) {
                int index = Integer.parseInt(false_list.get(i));
                IR.replaceAtIndex(index, "@FC", x);
            }
        }
    }

    public static void backpatch(ArrayList<String> chain,String x) {
        for (int i = 0; i < chain.size(); i++) {
            int index = Integer.parseInt(chain.get(i));
            IR.replaceAtIndex(index, "@C", x);
        }
    }

}

/*note:
!a can be treated as a==0
--> [a, 0, ==]
--> icmp eq i32 %a, 0
 */

