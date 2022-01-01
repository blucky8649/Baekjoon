import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main14567 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken()); // 과목의 수
        int m = Integer.parseInt(st.nextToken()); // 조건의 수

        int[][] sub = new int[m][2];
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1); 
        for (int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            sub[i][0] = Integer.parseInt(st.nextToken());
            sub[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sub, (o1, o2) -> o1[0] - o2[0]);
        
        for (int i = 0 ; i < sub.length ; i++) {
            dp[sub[i][1]] = Math.max(dp[sub[i][1]], dp[sub[i][0]]+1); 
        }
        for (int i = 1 ; i < dp.length ; i++) {
            System.out.print(dp[i] + " ");            
        }
    }
}
