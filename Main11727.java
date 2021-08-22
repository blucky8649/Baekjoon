import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main11727 {
    public static Integer[] dp = new Integer[1001] ;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        

        System.out.println(recur(n));
        
    }
    public static int recur (int n){

        if( n == 0 || n == 1){
            return dp[n] = 1;
        }else if(n == 2){
            return dp[n] = 3;
        }

        if(dp[n] != null){
            return dp[n];
        }
            dp[n] = (recur(n-1) + (recur(n-2)*2)) % 10007;
        return dp[n];
    }
    
}
