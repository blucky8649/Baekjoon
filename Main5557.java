import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main5557 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        long[][] dp = new long[21][n - 1];
        dp[arr[0]][0] = 1;
        for (int i = 1 ; i <n - 1 ; i++) {
            for (int j = 0 ; j < 21 ; j++) {
                if (dp[j][i-1] > 0) {
                    if (j + arr[i] <= 20) {
                        dp[j + arr[i]][i] += dp[j][i-1];
                    }
                    if (j - arr[i] >= 0) {
                        dp[j - arr[i]][i] += dp[j][i-1];
                    }
                }
            }
        }
        System.out.println(dp[arr[n - 1]][n - 2]);
    
    }
}
