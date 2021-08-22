import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1932 {
    public static int[][] triAngle;
    public static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        triAngle = new int[n+1][n+1];
        dp = new int[n+1][n+1];
        for (int i = 1 ; i <= n ; i++){
            StringTokenizer st= new StringTokenizer(br.readLine(), " ");
            for(int j = 1 ; st.hasMoreTokens() ; j++){
                triAngle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1 ; i <= n ; i++){
            dp[n][i] = triAngle[n][i];
            
        }

        for(int i = dp.length -2 ; i >= 1 ; i--){
            for(int j = 1 ; j <= i ; j++){
                dp[i][j] = Math.max(dp[i+1][j], dp[i+1][j+1]) + triAngle[i][j];
                
            }
        }
        System.out.println(dp[1][1]);
        
    }
    
}
