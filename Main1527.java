import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1527 {
    static int a, b, ans = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        for (int i = (a +"").length() ; i <= (b+"").length() ; i++) {
            DFS(0, "", i);    
        }
        System.out.println(ans);
    }
    static void DFS(int k, String str, int i) {
        if (k == i) {
            long num = Long.parseLong(str);
            if (num >= a && num <= b) {
                ans++;
            }
            return;
        }

        DFS(k + 1, str + "4", i);
        DFS(k + 1, str + "7", i);
    }
}
