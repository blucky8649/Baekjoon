import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17485 {
    static int n, m;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] dst = new int[n + 1][m];
        int[][][] dp = new int[n + 1][m][3];

        // 1. 거리 지도 만들기
        for (int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int j = 0 ; j < m ; j++) {
                dst[i][j] = Integer.parseInt(st.nextToken());
                if (i == 1) {
                    dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = dst[i][j];
                }
            }
        }

        for (int i = 1 ; i <= n ; i++) {
            dp[i][0][0] = Integer.MAX_VALUE;
            dp[i][m-1][2] = Integer.MAX_VALUE;
        }
        
        for (int i = 2 ; i <= n ; i++) {
            for (int j = 0 ; j < m; j++) {
                if (check(j-1) && check(j+1)) {
                    dp[i][j][0] = Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2]) + dst[i][j];
                    dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2]) + dst[i][j];
                    dp[i][j][2] = Math.min(dp[i-1][j+1][0], dp[i-1][j+1][1]) + dst[i][j];
                } else if (!check(j-1) && check(j+1)) {
                    dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2]) + dst[i][j];
                    dp[i][j][2] = Math.min(dp[i-1][j+1][0], dp[i-1][j+1][1]) + dst[i][j];
                } else {
                    dp[i][j][0] = Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2]) + dst[i][j];
                    dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2]) + dst[i][j];

                }
            }

        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                answer = Math.min(answer, dp[n][i][j]);
            }
        }

       
        System.out.println(answer);


    }
    static boolean check(int y) {
        if (y < 0 || y >= m) {
            return false;
        }
        return true;
    }
}
