import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1149 {
    public static int[][] house = new int[1001][3];
    public static int[][] dp = new int[1001][3];
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        for(int i = 1 ; i <= n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < 3 ; j++){
                house[i][j] = Integer.parseInt(st.nextToken());

            }

        }

        for(int i = 1 ; i <= n ;i++ ){
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + house[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + house[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + house[i][2];
        }

        System.out.print(Math.min(Math.min(dp[n][0],dp[n][1]),dp[n][2]) );
        
    }
    
}
