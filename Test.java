public class Test {
    public static void change(String[] y){
        y[0]="x";
        y[1]="y";
    }

    public static void main(String[] args) {
        String[] x = new String[]{"a","b"};
        change(x);
        System.out.println(x[0]);
//        System.out.println(x[1]);
//        System.out.println(x[2]);
    }
}
