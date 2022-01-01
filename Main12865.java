import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main12865 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken()); // 물품의 갯수
        int k = Integer.parseInt(st.nextToken()); // 가방이 수용할 수 있는 최대 무게

        // 물건의 무게와 가치
        int weight[] = new int[n + 1];
        int value[] = new int[n + 1];
        
        for (int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        int dp[][] = new int[n + 1][k + 1];

        // i는 물건의 인덱스, j는 가방의 수용가능한 무게
        for (int i = 1 ; i <= n ; i++) {
            for (int j = 1 ; j <= k ; j++) {
                // 물건이 더이상 가방에 안들어가면 
                if (weight[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                }

                else {
                    // 이전에 담은 무게를 그대로 넣거나, 아니면 다른 물건(현재 수용 무게 - 현재 인덱스 무게에서 넣을 수 있는 최고 값진 물건) + 현재 인덱스의 물건을 넣거나
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i]);
                }
            }
        }
       System.out.println(dp[n][k]);
    }
}
