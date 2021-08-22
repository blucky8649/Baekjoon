import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main10844 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[101][11];

        for(int i = 1 ; i < 10 ; i++){
            dp[1][i] = 1;
        }

        for(int i = 2 ; i <= n ; i++){
            dp[i][0] = dp[i-1][0 + 1]; 
            for(int j = 1 ; j <= 9 ; j++){
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
            }
        }
        int sum=0;
        for(int i = 0 ; i <= 10 ; i++){
            sum += dp[n][i];
        }

        System.out.println(sum% 1000000000);

    }
    
}
