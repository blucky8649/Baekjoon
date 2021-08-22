import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2156 {
    public static int[] wine;
    public static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        int n = Integer.parseInt(br.readLine());
        wine = new int[n+1];
        dp = new int[n+1];
        for(int i = 1 ; i <= n ; i++){
            wine[i] = Integer.parseInt(br.readLine());
        }


        dp[0] = wine[0];
        dp[1] = wine[1];
        if (n > 1) {
			dp[2] = wine[1] + wine[2];
		}
        for(int i = 3 ; i <= n ; i++){
            dp[i] = Math.max(Math.max(dp[i-3] + wine[i-1]+ wine[i], dp[i-2] + wine[i]), dp[i-1]);
           
            
        }
        System.out.println(dp[n]);
        
    }
    
}
