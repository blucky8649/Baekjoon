import java.io.IOException;
import java.util.Scanner;


public class Main2193 {
    public static long[] dp = new long[91];
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        
        dp[0] = dp[1] = 1;

        for(int i = 2 ; i <= n ; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println(dp[n-1]);
    }
    
}
