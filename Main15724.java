import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main15724 {
    static int n, m;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int map[][] = new int[n + 1][m + 1];
        int dp[][] = new int[n + 1][m + 1];

        
        for (int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1 ; j <= m ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[1][1] = map[1][1];

        for (int i = 1 ; i <= n ; i++) {
            for (int j = 1 ; j <= m ; j++) {
                dp[i][j] = map[i][j] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }
        for (int i = 0 ; i <= n ; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        int k = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < k ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken()) ;
            int y2 = Integer.parseInt(st.nextToken()) ;
            int x2 = Integer.parseInt(st.nextToken()) ;

            System.out.println(dp[y2][x2] - (dp[y2][x1 - 1] + dp[y1 - 1][x2] - dp[y1 - 1][x1 - 1]));
           
            



        }
    }
}

