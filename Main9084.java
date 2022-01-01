import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main9084 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int testcase = 0 ; testcase < t ; testcase++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int[] coin = new int[n];
            for (int i = 0 ; i < n ; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }
            int k = Integer.parseInt(br.readLine());
            int dp[] = new int[k + 1];
            dp[0] = 1; 
            for (int c : coin) {
                for (int i = c ; i <= k ; i++) {
                    dp[i] += dp[i - c];
                }
            }
            System.out.println(dp[k]);
        }
    }
}
