import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main2579 {
    public static int[] floor = new int[10001];
    public static int[] dp = new int[10001];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 1 ; i <= n ; i++){
            floor[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = floor[1];
        dp[2] = floor[1] + floor[2];

        for(int i = 3 ; i <= n ; i++){
            dp[i] = Math.max(dp[i-3] + floor[i-1] + floor[i], dp[i-2] + floor[i]);
            
        }
        System.out.println(dp[n]);
        
        
    }
    
}
