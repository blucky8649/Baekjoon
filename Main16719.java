import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main16719 {
    static boolean[] check;
    static char[] cr;
    static ArrayList<Character> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        cr = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        check = new boolean[cr.length];
        
        make(0, str.length());

    }
    static void make(int start, int end) {
        int idx = -1;
        int min = Integer.MAX_VALUE;
        for (int i = start ; i < end; i++) {
            if (!check[i] && cr[i] < min) {
                min = cr[i];
                idx = i;
            }
        }
        if (min == Integer.MAX_VALUE) return;
        check[idx] = true;
        for (int i = 0 ; i < cr.length ; i++) {
            if (check[i]) {
                System.out.print(cr[i]);
            }
        }
        System.out.println();
        make(idx + 1, end);
        make(start, idx);
    }
}
