import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2758 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0 ; tc < t ; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken()); // 뽑아야 할 숫자의 수
            int m = Integer.parseInt(st.nextToken()); // 1 부터 m 까지의 로또 숫자 범위

            long[][] dp = new long[m + 1][n + 1];
            
            for (int i = 1 ; i <= m ; i++) {
                dp[i][1] = i;
            }
            for (int x = 2 ; x < dp[0].length ; x++) {
                for (int y = 1 ; y < dp.length ; y++) {
                    dp[y][x] = dp[y-1][x] + dp[y / 2][x - 1];
                }
            }
            System.out.println(dp[m][n]);
        }
    }
}
