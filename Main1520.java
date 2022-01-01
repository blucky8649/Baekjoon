import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1520 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < m ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                if (j > 0 && map[i][j] < map[i][j-1]) {
                    dp[i][j] += dp[i][j-1];
                }

                if (j < m - 1 && map[i][j] < map[i][j+1]) {
                    dp[i][j] += dp[i][j+1];
                }
                if (i > 0 && map[i][j] < map[i - 1][j]) {
                    dp[i][j] += dp[i - 1][j];
                }
            }
        }

    }
}
