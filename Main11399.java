import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main11399 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] time = new int[n];
        int[] dp = new int[n];
        for(int i = 0 ; i < n ; i++){
            time[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(time);
        int sum = 0;
        dp[0] = time[0];
        for(int i = 1 ; i < n ; i++){
            dp[i] = dp[i-1] + time[i];
            
        }
        for(int i = 0 ; i < n ; i++){
            sum += dp[i];
            
        }
        System.out.println(sum);
    }
    
}
