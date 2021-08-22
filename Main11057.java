import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main11057 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[1001][10];
        for(int i = 0 ; i < 10 ; i++){
            dp[1][i] = i+1;
        }

        for(int i = 2 ; i <= n ; i++){
            dp[i][0] = 1;
            for(int j = 1 ; j < 10 ; j++){
                dp[i][j] = dp[i][j-1] + dp[i - 1][j];
            }
        }
       

        System.out.println(dp[n][9]);
    }

    
    
}
