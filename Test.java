import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;


class Singleton {
    private static Singleton one;
    private Singleton() {
    }

    public static Singleton getInstance() {
        if(one==null) {
            one = new Singleton();
        }
        return one;
    }
}
public class Test {
    public static void main(String[] args) {
        int a = 6;
        int b = 2;

        System.out.println((int) Math.pow(a, b) % 10);
    }
}