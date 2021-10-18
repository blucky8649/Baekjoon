import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11048 {
    static int n, m;
    static int[][] map;
    static int max=Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        int[][] dp = new int[n + 1][m + 1];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1 ; j <= m ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][1] = map[1][1];
        for (int i = 2 ; i <= n ; i++) {
            dp[i][1] = dp[i-1][1] + map[i][1]; 
        }
        for (int i = 2 ; i <= m ; i++) {
            dp[1][i] = dp[1][i-1] + map[1][i]; 
        }


        
        for (int i = 2 ; i <= n ; i++) {
            for (int j = 2 ; j <= m ; j++) {
                dp[i][j] = Math.max(dp[i- 1][j], Math.max(dp[i][j - 1], dp[i - 1][j - 1])) + map[i][j];
            }
        }

        System.out.print(dp[n][m]);
    }
    
    
}
