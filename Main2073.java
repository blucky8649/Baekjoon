import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2073 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int D = Integer.parseInt(st.nextToken()); // 늘려야 할 수도 배관의 길이
        int P = Integer.parseInt(st.nextToken()); // 파이프 종류의 개수 
        int dp[] = new int[D + 1];
        dp[0] = Integer.MAX_VALUE; 
 
        for (int i = 0 ; i < P ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int len = Integer.parseInt(st.nextToken());
            int cap = Integer.parseInt(st.nextToken());

            for (int j = D ; j >= len ; j--) {
                dp[j] = Math.max(dp[j], Math.min(dp[j - len], cap));
            }
        }
        System.out.println(dp[D]);
        
        
    }
}
