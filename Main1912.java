import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1912 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] num = new int [n + 1];
        int[] dp = new int [n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1 ; i <= n ; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = num[1];

        for(int i = 2 ; i <= n ; i++){
            dp[i] = Math.max(Math.max(dp[i-2] + num[i-1] + num[i], num[i-1] + num[i]), num[i]);
            
        }
        int max=-1000;
        for(int i = 1 ; i <= n ; i++){
            if(max < dp[i]){
                max = dp[i];
            }
        }
        System.out.println(max);
        
    }
    
}
