import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2225 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        final int mod = 1_000_000_000;

        int n = Integer.parseInt(st.nextToken()); // 0 부터 n까지
        int k = Integer.parseInt(st.nextToken()); // 정수 사용 횟수
        int[][] dp = new int[k+ 1][n + 1];
        Arrays.fill(dp[1], 1); // 정수 하나를 사용해서 만들 수 있는 수는 자기 자신 뿐
        for (int i = 2 ; i <= k ; i++) {
            for (int j = 0 ; j <= n ; j++) {
                for (int a = 0 ; a <= j ; a++) {
                    dp[i][j] += dp[i-1][a];
                    dp[i][j] %= mod;
                }
            }
        }
        System.out.println(dp[k][n] % mod);

        
    }
}
