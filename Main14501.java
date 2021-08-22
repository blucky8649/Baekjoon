import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14501 {
    static int max = Integer.MIN_VALUE;
    static int n;
    static int[] t;
    static int[] p;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        t = new int[n];
        p = new int[n];
        for(int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
            
        }
        calc(0, 0);
        System.out.println(max);
    }
    static void calc(int k, int money){
        
        if(k > n) return;
        if(k  == n){
            max = Math.max(max, money);
            return;
        }

        calc(k + t[k], money + p[k]);
        calc(k + 1, money);
        
    }
}
