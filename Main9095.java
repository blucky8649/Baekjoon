import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main9095 {
    public static Long[] dp = new Long[1000001] ;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i++){
            int num = Integer.parseInt(br.readLine());
            sb.append(recur(num)).append('\n');
        }
        System.out.print(sb.toString());
        
    }
    public static long recur (int n){

        if( n == 0 || n == 1){
            return dp[n] = (long) 1;
        }else if(n == 2){
            return dp[n] = (long) 2;
        }else if(n == 3){
            return dp[n] = (long) 4;
        }

        if(dp[n] != null){
            return dp[n];
        }
            dp[n] = ((recur(n-1) + recur(n-2)) + recur(n-3))% 1000000009 ;
        return dp[n] % 1000000009;
    }
    
}
