import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1309 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];

        dp[1] = 3;
        for(int i = 1 ; i <= N ; i++ ){
            dp[i] = (dp[i-1]-1)+dp[i-1];
        }

        
    }
    
}
