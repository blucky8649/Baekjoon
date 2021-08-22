import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main11726 {
    public static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new int[n+1];
        

        System.out.println(recur(n));
        
    }
    public static int recur (int n){

        if( n == 0 || n == 1){
            return dp[n] = 1;
        }
        if(dp[n] > 0){
            return dp[n];
        }
            dp[n] = (recur(n-1) + recur(n-2)) % 10007;
        return dp[n];
    }
    
}
