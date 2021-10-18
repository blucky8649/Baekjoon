import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main2579 {
    public static int[] floor = new int[10001];
    public static int[] dp = new int[10001];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        int[] arr= new int[n + 1];

        for (int i = 1 ;i <= n ; i++) { 
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = arr[0];
        dp[1] = arr[1];
        
        if (n >= 2) {
            dp[2] = arr[1] + arr[2];
        }

        for (int i = 3 ; i <= n ; i++) {
            dp[i] = Math.max(arr[i - 1] + dp[i - 3], dp[i - 2]) + arr[i];
        }
        System.out.println(dp[n]);
        
        
    }
    
}
